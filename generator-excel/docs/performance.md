# Performance and Large Datasets

Guidelines for choosing the right format, using SXSSF streaming correctly, and keeping memory usage under control.

---

## Choosing the Right Format

| | XLS (HSSF) | XLSX (XSSF) | SXSSF (streaming) |
|---|:---:|:---:|:---:|
| Max rows | ~65 k | ~1 M | ~1 M |
| Subtotals | yes | yes | **no** |
| Charts | no | yes | **no** |
| Pivot tables | no | yes | **no** |
| Conditional formatting | yes | yes | **no** |
| Merge rows | yes | yes | **no** |
| Images | yes | yes | **no** |
| Super headers | yes | yes | **no** |
| Memory usage | medium | high | **low** |

**Rule of thumb:**
- < 50 k rows and rich formatting → XLSX (`createFileXlsx`)
- > 50 k rows, plain data only → SXSSF (`createBigDataFileXlsx`)
- Legacy `.xls` required → XLS (`createFileXls`)

---

## SXSSF Streaming

SXSSF (Streaming API for XLSX) writes rows directly to a temp file as they are processed, keeping only a configurable window of rows in memory. It is the right choice for exports in the hundreds of thousands of rows where memory pressure would be a concern with XSSF.

### Row class

SXSSF supports the same column/cell annotations as XSSF. Keep the row class simple — no subtotals, no merges.

```java
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn      = @ExcelColumn(index = 6, name = "Score"),
        excelFunction    = @ExcelFunction(
            function     = "sum(${valueA},${valueB})+${userId}",
            nameFunction = "score"
        ),
        excelCellsLayout = @ExcelCellLayout(
            horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2
        )
    )
})
public class UserRow implements RowSheet {

    @ExcelColumn(name = "ID", index = 0)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private Integer userId;

    @ExcelColumn(name = "Last Name", index = 1)
    @ExcelCellLayout
    private String lastName;

    @ExcelColumn(name = "First Name", index = 2)
    @ExcelCellLayout
    private String firstName;

    @ExcelColumn(name = "Birth Date", index = 3)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
    @ExcelDate(ColumnDateFormat.DD_MM_YYYY)
    private Date birthDate;

    @ExcelColumn(name = "Value A", index = 4)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private Double valueA;

    @ExcelColumn(name = "Value B", index = 5)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private Double valueB;

    // getters / setters ...
}
```

### Sheet class

```java
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
public class UserSheet extends SheetData<UserRow> {
    public UserSheet(String name) { super(name); }
}
```

### Generation — streaming 500 000 rows

```java
@Service
public class LargeExportService {

    @Autowired
    private GenerateExcel generateExcel;

    @Autowired
    private UserRepository userRepository;

    public byte[] exportAllUsers() throws Exception {
        UserSheet sheet = new UserSheet("Users");

        // Load all rows — use pagination (see tip below) for very large sets
        List<User> users = userRepository.findAll();
        for (User u : users) {
            sheet.addRows(new UserRow(u.getId(), u.getLastName(),
                                     u.getFirstName(), u.getBirthDate()));
        }

        // Use createBigDataFileXlsx — NOT createFileXlsx
        return generateExcel.createBigDataFileXlsx(
                new ReportExcel("User Export", sheet));
    }
}
```

### Writing directly to an OutputStream (no byte[] buffer)

For very large outputs, bypass the in-memory `byte[]` entirely and write straight to the response stream or a file:

```java
// HTTP streaming
@GetMapping("/export/users")
public void streamUsers(HttpServletResponse response) throws Exception {
    response.setContentType(
        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=\"users.xlsx\"");

    UserSheet sheet = new UserSheet("Users");
    // ... populate rows ...

    generateExcel.createBigDataFileXlsx(
            new ReportExcel("Users", sheet),
            response.getOutputStream());
}

// File streaming
try (FileOutputStream fos = new FileOutputStream("/tmp/users.xlsx")) {
    generateExcel.createBigDataFileXlsx(new ReportExcel("Users", sheet), fos);
}
```

---

## SXSSF Limitations

These features are **not available** when using `createBigDataFileXlsx`:

- `@ExcelSubtotals` / `@ExcelSubtotal` — grouping rows are not supported
- `@ExcelCharts` — chart rendering requires XSSF
- `@ExcelPivot` — pivot table generation requires XSSF
- `@ExcelConditionCellLayouts` — conditional formatting is not applied
- `@ExcelMergeRow` — cell merging is not applied
- `@ExcelImage` / `@ExcelLabel` — images and label cells are skipped
- `@ExcelSuperHeaders` — super-header rows are not generated
- `SheetFunctionTotal` / `FunctionsTotal` — companion total sheets are ignored

For these features, use `createFileXlsx` and design the query or data loading to return a manageable number of rows (e.g. filter, aggregate server-side before export).

---

## Memory and Performance Tips

### Paginate the query instead of loading all rows at once

```java
// Spring Data pagination
Pageable page = PageRequest.of(0, 10_000);
Page<User> result;
do {
    result = userRepository.findAll(page);
    for (User u : result.getContent()) {
        sheet.addRows(new UserRow(u));
    }
    page = result.nextPageable();
} while (result.hasNext());
```

### Use fixed column widths in large sheets

`@ExcelCellLayout(autoSizeColumn = true)` scans every cell in the column to compute the optimal width. For sheets with many rows this is a measurable cost. Prefer `@ExcelColumnWidth(width = N)` instead:

```java
@ExcelColumn(name = "Last Name", index = 1)
@ExcelCellLayout
@ExcelColumnWidth(width = 6)   // fixed width — much faster than autoSizeColumn
private String lastName;
```

### Keep `@ExcelFunctionRows` formulas simple in SXSSF

In streaming mode, formula placeholders still resolve correctly, but each formula cell is evaluated by Excel on open — not pre-computed by the library. Complex formulas referencing large ranges (e.g. `SUMIF` over 500 k rows) can slow down the first open of the workbook in Excel. Prefer aggregating server-side and writing the result as a plain value where possible.

### Use `QuerySheetData` to push filtering and aggregation to the database

```java
@ExcelQuery(
    "SELECT dept, COUNT(*) AS headcount, SUM(salary) AS payroll " +
    "FROM employee GROUP BY dept ORDER BY dept"
)
public class DeptSummarySheet extends QuerySheetData<DeptSummaryRow> {
    public DeptSummarySheet(String name) { super(name); }
}
```

Returning 50 aggregated rows from SQL is far cheaper than loading 50 000 individual rows and computing totals in Java.

### Avoid re-generating unchanged workbooks

If the underlying data changes infrequently, cache the `byte[]` result (e.g. in Redis) and invalidate on data change rather than regenerating on every request.

### JVM heap sizing

A rule of thumb: XSSF workbooks use roughly 2–5 MB of heap per 10 000 rows depending on column count and styles. For a 200 k-row XSSF export you may need `-Xmx2g` or more. With SXSSF the heap footprint is roughly constant (the row window is small) regardless of total row count.
