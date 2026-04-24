# End-to-End Examples

Complete worked examples from project setup to file output, covering common real-world scenarios.

---

## Project Setup

### Maven dependency

```xml
<dependency>
    <groupId>com.github.bld-commons</groupId>
    <artifactId>generator-excel</artifactId>
    <version>5.1.3</version>
</dependency>
```

### Enable the generator

Add `@EnableExcelGenerator` to your Spring Boot application class or test class:

```java
@SpringBootApplication
@EnableExcelGenerator
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

### application.yml

```yaml
com.bld.commons:
  check.annotation: true          # validate annotations at startup
  date.format: dd/MM/yyyy         # global date format for ColumnDateFormat.PARAMETER
  multiple.datasource: false       # set true when using multiple DataSources
```

---

## Example 1 — Simple Static Sheet

The fastest path: annotated row class → sheet class → generate bytes → return from a REST endpoint.

### Row class

```java
public class OrderRow implements RowSheet {

    @ExcelColumn(name = "Order ID", index = 1)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private Integer orderId;

    @ExcelColumn(name = "Customer", index = 2)
    @ExcelCellLayout(autoSizeColumn = true)
    private String customer;

    @ExcelColumn(name = "Product", index = 3)
    @ExcelCellLayout(autoSizeColumn = true)
    private String product;

    @ExcelColumn(name = "Quantity", index = 4)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private Integer quantity;

    @ExcelColumn(name = "Unit Price", index = 5)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
    private Double unitPrice;

    @ExcelColumn(name = "Order Date", index = 6)
    @ExcelDate(ColumnDateFormat.YYYY_MM_DD)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
    private Calendar orderDate;

    // getters / setters ...
}
```

### Sheet class

```java
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
public class OrderSheet extends SheetData<OrderRow> {
    public OrderSheet(String name) { super(name); }
}
```

### Service / controller

```java
@RestController
@RequestMapping("/reports")
public class OrderReportController {

    @Autowired
    private GenerateExcel generateExcel;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(value = "/orders", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity<byte[]> exportOrders() throws Exception {
        List<OrderRow> rows = orderRepository.findAll()
                .stream()
                .map(OrderRow::new)
                .toList();

        OrderSheet sheet = new OrderSheet("Orders");
        sheet.setListRowSheet(rows);

        byte[] bytes = generateExcel.createFileXlsx(new ReportExcel("Order Report", sheet));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"orders.xlsx\"")
                .body(bytes);
    }
}
```

### Write to a file (tests / scripts)

```java
byte[] bytes = generateExcel.createFileXlsx(new ReportExcel("Order Report", sheet));
SpreadsheetUtils.writeToFile("/tmp/", "orders", ".xlsx", bytes);
```

---

## Example 2 — Multi-Sheet Workbook

A single workbook that combines static data, a JPA-query sheet, and a summary sheet.

```
Workbook: "Q1 Report"
  ├── Tab "Orders"      — static OrderSheet
  ├── Tab "Employees"   — QuerySheetData (JPA)
  └── Tab "Summary"     — SheetSummary with cross-sheet formulas
```

### Orders sheet — static data (same as Example 1)

### Employees sheet — JPA query

```java
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
@ExcelQuery("SELECT e.id, e.firstName, e.lastName, e.department, e.salary " +
            "FROM employee e WHERE e.active = true ORDER BY e.department, e.lastName")
public class EmployeeSheet extends QuerySheetData<EmployeeRow> {
    public EmployeeSheet(String name) { super(name); }
}
```

### Summary sheet — cross-sheet formula

```java
public class SummaryRow implements RowSheet {

    @ExcelColumn(name = "Metric", index = 1)
    @ExcelCellLayout(autoSizeColumn = true)
    private String metric;

    @ExcelColumn(name = "Value", index = 2)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
    private Double value;

    // getters / setters ...
}

@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn   = @ExcelColumn(index = 2, name = "Total Salary"),
        excelFunction = @ExcelFunction(
            function     = "sum(${Employees.salaryRowStart}:${Employees.salaryRowEnd})",
            nameFunction = "totalSalary"
        ),
        excelCellsLayout = @ExcelCellLayout(
            horizontalAlignment = HorizontalAlignment.RIGHT,
            precision = 2,
            font = @ExcelFont(bold = true)
        )
    )
})
public class SummarySheet extends SheetSummary { }
```

### Assembling the workbook

```java
@Service
public class Q1ReportService {

    @Autowired
    private GenerateExcel generateExcel;

    public byte[] generate(List<OrderRow> orders) throws Exception {
        // Static orders sheet
        OrderSheet orderSheet = new OrderSheet("Orders");
        orderSheet.setListRowSheet(orders);

        // JPA-driven employees sheet (no manual data loading needed)
        EmployeeSheet employeeSheet = new EmployeeSheet("Employees");

        // Summary sheet
        SummarySheet summarySheet = new SummarySheet();

        ReportExcel report = new ReportExcel("Q1 Report",
                List.of(orderSheet, employeeSheet, summarySheet));

        return generateExcel.createFileXlsx(report);
    }
}
```

---

## Example 3 — Advanced: Formulas, Merges, Super Headers, and FunctionsTotal

A production-style report that combines:
- Multi-level merge rows (author → book rows)
- Per-group formula column (`@ExcelFunctionMergeRow`)
- Cross-sheet SUMIF in a companion total sheet (`FunctionsTotal`)
- Super headers grouping columns visually
- Area border around the data range

### Row class

```java
@ExcelFunctionRows(
    excelFunctions = {
        @ExcelFunctionRow(
            excelColumn      = @ExcelColumn(index = 9, name = "Total Price"),
            excelFunction    = @ExcelFunction(
                function     = "sum(${price},${supplement})",
                nameFunction = "totalPrice"
            ),
            excelCellsLayout = @ExcelCellLayout(
                horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2
            ),
            excelColumnWidth = @ExcelColumnWidth(width = 7)
        )
    },
    excelFunctionMerges = {
        @ExcelFunctionMergeRow(
            excelColumn      = @ExcelColumn(index = 7.1, name = "Price per Author"),
            excelMergeRow    = @ExcelMergeRow(referenceField = "authorId"),
            excelFunction    = @ExcelFunction(
                function     = "sum(${priceRowStart}:${priceRowEnd})",
                anotherTable = false,
                nameFunction = "pricePerAuthor"
            ),
            excelCellsLayout = @ExcelCellLayout(
                horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2
            ),
            excelSubtotal    = @ExcelSubtotal(
                enable                  = true,
                dataConsolidateFunction = DataConsolidateFunction.SUM,
                excelCellLayout         = @ExcelCellLayout(
                    horizontalAlignment = HorizontalAlignment.RIGHT,
                    precision = 2,
                    font = @ExcelFont(bold = true)
                )
            )
        ),
        @ExcelFunctionMergeRow(
            excelColumn   = @ExcelColumn(index = 7.2, name = "Price per Genre"),
            excelMergeRow = @ExcelMergeRow(referenceField = {"genre", "authorId"}),
            excelFunction = @ExcelFunction(
                function     = "sum(${priceRowStart}:${priceRowEnd})",
                anotherTable = false,
                nameFunction = "pricePerGenre"
            )
        )
    }
)
public class BookRow implements RowSheet {

    @ExcelColumn(name = "Author ID", index = 1)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    @ExcelMergeRow
    private Integer authorId;

    @ExcelColumn(name = "First Name", index = 2)
    @ExcelCellLayout
    @ExcelMergeRow(referenceField = "authorId")
    private String firstName;

    @ExcelColumn(name = "Last Name", index = 3)
    @ExcelCellLayout
    @ExcelMergeRow(referenceField = "authorId")
    private String lastName;

    @ExcelColumn(name = "Birth Date", index = 4)
    @ExcelDate(ColumnDateFormat.YYYY_MM_DD)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
    @ExcelMergeRow(referenceField = "authorId")
    private Calendar birthDate;

    @ExcelColumn(name = "Genre", index = 5)
    @ExcelCellLayout
    @ExcelMergeRow(referenceField = "lastName")
    private String genre;

    @ExcelColumn(name = "Title", index = 6)
    @ExcelCellLayout(autoSizeColumn = true)
    private String title;

    @ExcelColumn(name = "Price", index = 7)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
    @ExcelColumnWidth(width = 8)
    private Double price;

    @ExcelColumn(name = "Supplement", index = 8)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
    private Double supplement;

    // getters / setters ...
}
```

### Companion total row

```java
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn   = @ExcelColumn(index = 2, name = "Total per Author"),
        excelFunction = @ExcelFunction(
            function     = "sumif(${authorIdRowStart}:${authorIdRowEnd}," +
                           "${filterAuthorId},${priceRowStart}:${priceRowEnd})",
            nameFunction = "totalPerAuthor"
        ),
        excelCellsLayout = @ExcelCellLayout(
            horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2
        )
    )
})
public class BookTotalsRow implements RowSheet {

    @ExcelColumn(name = "Author ID", index = 1)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private Integer filterAuthorId;

    public BookTotalsRow(Integer filterAuthorId) {
        this.filterAuthorId = filterAuthorId;
    }
    // getters / setters ...
}
```

### Companion total sheet

```java
@ExcelSheetLayout(showHeader = false, startRow = -2,
    areaBorder = {
        @ExcelAreaBorder(
            areaRange = "${totalPerAuthorRowStart}:${totalPerAuthorRowEnd}",
            border    = @ExcelBorder(
                top    = BorderStyle.MEDIUM_DASHED,
                bottom = BorderStyle.MEDIUM_DASHED,
                left   = BorderStyle.MEDIUM_DASHED,
                right  = BorderStyle.MEDIUM_DASHED
            )
        )
    }
)
@ExcelHeaderLayout
public class BookTotalsSheet extends SheetFunctionTotal<BookTotalsRow> { }
```

### Main sheet

```java
@ExcelSheetLayout(
    startRow = 1,
    groupRow = true,
    areaBorder = {
        @ExcelAreaBorder(
            areaRange          = "${authorId[header]}:${supplement[end]}",
            border             = @ExcelBorder(
                top    = BorderStyle.MEDIUM,
                bottom = BorderStyle.MEDIUM,
                left   = BorderStyle.MEDIUM,
                right  = BorderStyle.MEDIUM
            ),
            includeSuperHeader = true
        )
    }
)
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
@ExcelSuperHeaders(superHeaders = @ExcelSuperHeader(headerGroups = {
    @ExcelSuperHeaderCell(columnName = "Author Info", columnRange = "${authorId}:${birthDate}"),
    @ExcelSuperHeaderCell(columnName = "Book Details",  columnRange = "${genre}:${supplement}")
}))
public class BookSheet extends SheetData<BookRow>
        implements FunctionsTotal<BookTotalsSheet> {

    private BookTotalsSheet sheetFunctionsTotal = new BookTotalsSheet();

    public BookSheet(String name) { super(name); }

    @Override public BookTotalsSheet getSheetFunctionsTotal() { return sheetFunctionsTotal; }
    @Override public void setSheetFunctionsTotal(BookTotalsSheet s) { this.sheetFunctionsTotal = s; }
}
```

### Generation

```java
BookSheet bookSheet = new BookSheet("Books");
bookSheet.setListRowSheet(buildBookRows());

BookTotalsSheet totals = new BookTotalsSheet();
totals.addRows(new BookTotalsRow(1001));
totals.addRows(new BookTotalsRow(1002));
bookSheet.setSheetFunctionsTotal(totals);

byte[] xlsx = generateExcel.createFileXlsx(new ReportExcel("Book Catalog", bookSheet));
```

---

## Example 4 — Subtotals with Conditional Formatting

Automatic subtotal rows for each group, with a conditional format that highlights total rows in blue text and red background.

```java
@ExcelSubtotals(
    labelTotalGroup = "Total",
    endLabel        = "total",
    sumForGroup     = {"region", "country"}
)
@ExcelConditionCellLayouts(
    @ExcelConditionCellLayout(
        columns       = {"region", "country", "city"},
        condition     = "ISNUMBER(SEARCH(\"total\", LOWER(${region[start]})))",
        excelCellLayout = @ExcelCellLayout(
            rgbFont       = @ExcelRgbColor(red = (byte) 255, blue = 0, green = 0),
            rgbForeground = @ExcelRgbColor(blue = (byte) 255, red = 0, green = 0)
        )
    )
)
public class SalesRow implements RowSheet {

    @ExcelColumn(name = "Region",  index = 0) @ExcelCellLayout private String region;
    @ExcelColumn(name = "Country", index = 1) @ExcelCellLayout private String country;
    @ExcelColumn(name = "City",    index = 2) @ExcelCellLayout private String city;

    @ExcelColumn(name = "Revenue", index = 3)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    @ExcelSubtotal(
        dataConsolidateFunction = DataConsolidateFunction.SUM,
        excelCellLayout         = @ExcelCellLayout(
            horizontalAlignment = HorizontalAlignment.RIGHT,
            font = @ExcelFont(bold = true)
        )
    )
    private Double revenue;

    public SalesRow(String region, String country, String city, Double revenue) { ... }
}

@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
public class SalesSheet extends SheetData<SalesRow> {
    public SalesSheet(String name) { super(name); }
}

// Usage
SalesSheet sheet = new SalesSheet("Sales");
sheet.addRows(new SalesRow("Europe",   "Italy",   "Rome",   12000.0));
sheet.addRows(new SalesRow("Europe",   "Italy",   "Milan",   9500.0));
sheet.addRows(new SalesRow("Europe",   "France",  "Paris",  18000.0));
sheet.addRows(new SalesRow("Americas", "USA",     "New York", 31000.0));
sheet.addRows(new SalesRow("Americas", "USA",     "Chicago", 14500.0));

byte[] xlsx = generateExcel.createFileXlsx(new ReportExcel("Sales Report", sheet));
```

The generator automatically inserts subtotal rows for `(Europe, Italy)`, `(Europe, France)`, `(Europe)`, `(Americas, USA)`, `(Americas)`, and a grand total.

---

## Best Practices

### Column indexing

Use decimal indices (e.g. `0.1`, `0.2`, `7.1`, `7.2`) to insert computed or formula columns between existing data columns without renumbering everything.

### Auto-size vs fixed width

`@ExcelCellLayout(autoSizeColumn = true)` is convenient but triggers a full column scan. For large sheets, prefer `@ExcelColumnWidth(width = N)` to set widths explicitly — it is much faster.

### Row height

`@ExcelRowHeight` defaults to `-1` (Excel auto-fit). Set an explicit pixel value only when you need uniform row heights for visual consistency or when wrap text is enabled.

### Sheet name length

Excel limits sheet tab names to 31 characters. Use `@Size(max = 31)` (or `@Size(max = SpreadsheetUtils.SHEET_NAME_SIZE)`) on the constructor parameter to catch violations early.

### Merge rows and sort order

`@ExcelMergeRow` merges consecutive rows that share the same value in the reference field. The data **must be sorted** by that field before the sheet is populated; the library does not sort automatically.

### Formula placeholder syntax

| Pattern | Expands to |
|---------|-----------|
| `${fieldName}` | The column letter of `fieldName` in the current row |
| `${fieldNameRowStart}` | First data row letter+number (e.g. `C2`) |
| `${fieldNameRowEnd}` | Last data row letter+number |
| `${field[start]}` / `${field[end]}` | Array alias — same as `RowStart` / `RowEnd` |
| `${field[start+N]}` / `${field[end-N]}` | Offset from first/last row |
| `${field.field-value[start]}` | Value of `field` in the first data row |
| `${SheetName.fieldRowStart}` | Cross-sheet reference |

### MergeSheet: grouping tabs

Use `MergeSheet` to place several `SheetComponent` instances (e.g. a `SheetData` and a `SheetSummary`) on **one Excel tab**. Each component controls its own `startRow` via `@ExcelSheetLayout`.

### Annotation validation at startup

Set `com.bld.commons.check.annotation: true` in `application.yml`. The library then validates all annotated classes at startup and fails fast if an annotation is misconfigured, rather than producing a corrupt file at runtime.

### Testing

```java
@SpringBootTest
@EnableExcelGenerator
class OrderReportTest {

    @Autowired GenerateExcel generateExcel;

    @Test
    void shouldGenerateNonEmptyFile() throws Exception {
        OrderSheet sheet = new OrderSheet("Orders");
        sheet.addRows(new OrderRow(1, "Alice", "Widget", 3, 9.99, Calendar.getInstance()));
        byte[] bytes = generateExcel.createFileXlsx(new ReportExcel("Test", sheet));
        assertThat(bytes).isNotEmpty();
        SpreadsheetUtils.writeToFile("/tmp/", "test-orders", ".xlsx", bytes);
    }
}
```
