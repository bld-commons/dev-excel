# QuerySheetData, LoadSheetData & SheetFunctionTotal

Sheet types for query-driven data, lazy row loading, and companion total sheets.

---

## `QuerySheetData<T extends RowSheet>`

Extends `SheetData`. Rows are populated automatically from a SQL or JPQL query defined with `@ExcelQuery`. Inherits all `SheetData` methods.

| Method | Description |
|--------|-------------|
| `addParameters(String key, Object value)` | Add a named query parameter |
| `addParameters(Map<String,Object>)` | Add multiple parameters at once |
| `resetParameters()` | Clear all parameters |
| `getMapParameters()` | Return the current parameter map |

### Minimal example

```java
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
@ExcelQuery(
    "SELECT e.id, e.name, e.department, e.salary " +
    "FROM employee e WHERE e.department = :dept ORDER BY e.name"
)
public class EmployeeSheet extends QuerySheetData<EmployeeRow> {
    public EmployeeSheet(String name) { super(name); }
}

// Usage
EmployeeSheet sheet = new EmployeeSheet("Employees");
sheet.addParameters("dept", "Engineering");
byte[] xlsx = generateExcel.createFileXlsx(new ReportExcel("report", List.of(sheet)));
```

### JPQL query

```java
@ExcelQuery(
    value       = "select new com.example.EmployeeRow(e.id, e.name, e.department, e.salary) " +
                  "from Employee e where e.department = :dept",
    nativeQuery = false
)
public class EmployeeSheet extends QuerySheetData<EmployeeRow> { ... }
```

### Multiple datasource

```java
@ExcelQuery(
    value         = "SELECT id, name, salary FROM employee WHERE dept = :dept",
    entityManager = SecondaryDbConfig.ENTITY_MANAGER_NAME
)
public class SecondaryEmployeeSheet extends QuerySheetData<EmployeeRow> { ... }
```

### Full example — with pivot, super headers, area borders, and FunctionsTotal

```java
@ExcelSheetLayout(
    areaBorder = {
        @ExcelAreaBorder(
            areaRange        = "${name[header]}:${price[end]}",
            border           = @ExcelBorder(top = BorderStyle.MEDIUM_DASHED, bottom = BorderStyle.MEDIUM_DASHED,
                                            left = BorderStyle.MEDIUM_DASHED, right = BorderStyle.MEDIUM_DASHED),
            includeSuperHeader = true
        )
    }
)
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
@ExcelQuery(
    "SELECT a.id, a.surname, a.name, b.title, b.genre, s.year, s.price " +
    "FROM author a " +
    "JOIN book b ON a.id = b.author_id " +
    "JOIN price_history s ON b.id = s.book_id"
)
@ExcelSuperHeaders(superHeaders = @ExcelSuperHeader(headerGroups = {
    @ExcelSuperHeaderCell(columnName = "Personal Info", columnRange = "${name}:${birthDate}"),
    @ExcelSuperHeaderCell(columnName = "Book Details",  columnRange = "${genre}:${price}")
}))
@ExcelPivot(startColumn = 12)
public class AuthorBookSheet extends QuerySheetData<AuthorBookRow>
        implements FunctionsTotal<AuthorBookTotalsSheet> {

    private AuthorBookTotalsSheet sheetFunctionsTotal = new AuthorBookTotalsSheet();

    public AuthorBookSheet(String name) { super(name); }

    @Override public AuthorBookTotalsSheet getSheetFunctionsTotal() { return sheetFunctionsTotal; }
    @Override public void setSheetFunctionsTotal(AuthorBookTotalsSheet s) { this.sheetFunctionsTotal = s; }
}
```

---

## `LoadSheetData<R extends RowSheet, S extends LoadSheetData<R, S>>`

Extends `SheetData`. Delegates row loading to a `LoadSheetFunction` lambda, invoked by the generator at generation time via `initRows()`. Useful when rows depend on context that is only available at runtime (e.g. state accumulated during earlier sheet generation).

| Method | Description |
|--------|-------------|
| `initRows()` | Called by the generator; must call `loadSheetFunction.loadSheet(this)` |
| `getLoadSheetFunction()` | Return the loading lambda |

### `LoadSheetFunction<R, S>`

Functional interface:

```java
@FunctionalInterface
public interface LoadSheetFunction<R extends RowSheet, S extends LoadSheetData<R, S>> {
    List<R> loadSheet(S loadSheetData);
}
```

### Example

```java
// Sheet class — carries context needed by the loader
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
public class ReportSheet extends LoadSheetData<ReportRow, ReportSheet> {

    private final String department;

    public ReportSheet(String sheetName, String department,
                       LoadSheetFunction<ReportRow, ReportSheet> loader) {
        super(sheetName, loader);
        this.department = department;
    }

    @Override
    public void initRows() {
        this.setRows(loadSheetFunction.loadSheet(this));
    }

    public String getDepartment() { return department; }
}

// Usage — the lambda receives the sheet itself as parameter
ReportSheet sheet = new ReportSheet(
    "Engineering Report",
    "Engineering",
    s -> employeeRepository
            .findByDepartment(s.getDepartment())
            .stream()
            .map(ReportRow::new)
            .toList()
);

byte[] xlsx = generateExcel.createFileXlsx(new ReportExcel("report", List.of(sheet)));
```

---

## `SheetFunctionTotal<T extends RowSheet>`

A companion sheet that renders the formula results of a `SheetData` with `@ExcelFunctionRows` in a separate row block placed immediately below the data. It has no sheet name of its own (empty constructor).

Attach it to the main sheet by implementing `FunctionsTotal` — see the full example in [sheet-data.md](sheet-data.md#functionstotal).

### Key points

- **No sheet name** — `SheetFunctionTotal` uses an empty constructor; the row block is appended to the parent sheet's tab.
- **`@ExcelSheetLayout(showHeader = false)`** — typically the total block does not repeat the header row.
- **`startRow = -2`** — a negative `startRow` means the block is placed relative to the end of the data, not from the top of the sheet.
- The companion sheet can have its own `@ExcelSheetLayout` and `@ExcelAreaBorder` to control the appearance of the total block.

```java
// Companion total row
public class SalaryTotalsRow implements RowSheet {

    @ExcelColumn(name = "Total Salary", index = 3)
    @ExcelCellLayout(
        horizontalAlignment = HorizontalAlignment.RIGHT,
        precision = 2,
        font = @ExcelFont(bold = true)
    )
    private Double totalSalary;
    // getters / setters ...
}

// Companion total sheet
@ExcelSheetLayout(showHeader = false, startRow = -2,
    areaBorder = {
        @ExcelAreaBorder(
            areaRange = "${totalSalaryRowStart}:${totalSalaryRowEnd}",
            border    = @ExcelBorder(
                top = BorderStyle.MEDIUM_DASHED, bottom = BorderStyle.MEDIUM_DASHED,
                left = BorderStyle.MEDIUM_DASHED, right  = BorderStyle.MEDIUM_DASHED
            )
        )
    }
)
@ExcelHeaderLayout
public class SalaryTotalsSheet extends SheetFunctionTotal<SalaryTotalsRow> { }

// Main sheet
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
@ExcelQuery("SELECT id, name, department, salary FROM employee ORDER BY department, name")
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn   = @ExcelColumn(index = 3, name = "Total Salary"),
        excelFunction = @ExcelFunction(
            function     = "sum(${salaryRowStart}:${salaryRowEnd})",
            nameFunction = "totalSalary"
        ),
        excelCellsLayout = @ExcelCellLayout(
            horizontalAlignment = HorizontalAlignment.RIGHT,
            precision = 2,
            font = @ExcelFont(bold = true)
        )
    )
})
public class SalarySheet extends QuerySheetData<SalaryRow>
        implements FunctionsTotal<SalaryTotalsSheet> {

    private SalaryTotalsSheet sheetFunctionsTotal = new SalaryTotalsSheet();

    public SalarySheet(String name) { super(name); }

    @Override public SalaryTotalsSheet getSheetFunctionsTotal() { return sheetFunctionsTotal; }
    @Override public void setSheetFunctionsTotal(SalaryTotalsSheet s) { this.sheetFunctionsTotal = s; }
}
```
