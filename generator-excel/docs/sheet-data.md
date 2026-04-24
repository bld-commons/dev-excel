# SheetData & SheetSummary

The two most-used base classes. `SheetData` generates a standard tabular sheet; `SheetSummary` generates a two-column key/value summary sheet.

---

## `RowSheet`

Marker interface that every row class must implement. Fields are mapped to columns with `@ExcelColumn` and styled with `@ExcelCellLayout`.

```java
public class ProductRow implements RowSheet {

    @ExcelColumn(name = "ID", index = 1)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private Integer id;

    @ExcelColumn(name = "Name", index = 2)
    @ExcelCellLayout(autoSizeColumn = true)
    private String name;

    @ExcelColumn(name = "Price", index = 3)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
    @ExcelColumnWidth(width = 8)
    private Double price;

    // constructor, getters, setters ...
}
```

---

## `SheetData<T extends RowSheet>`

Abstract base for sheets with static data (rows populated manually in code).

### API

| Method | Description |
|--------|-------------|
| `addRows(T... rows)` | Append one or more rows |
| `setRows(List<T> rows)` | Replace the entire row list |
| `getRows()` | Return all rows |
| `clear()` | Remove all rows |
| `getRowClass()` | Return the generic type `T` at runtime |

### Minimal example

```java
// Sheet class
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
public class ProductSheet extends SheetData<ProductRow> {
    public ProductSheet(String name) { super(name); }
}

// Usage
ProductSheet sheet = new ProductSheet("Products");
sheet.addRows(
    new ProductRow(1, "Widget A", 9.99),
    new ProductRow(2, "Widget B", 14.50)
);
byte[] xlsx = generateExcel.createFileXlsx(new ReportExcel("Catalogue", List.of(sheet)));
```

### Full example — combining multiple annotations

Row class with merge, subtotals, formulas, conditional formatting, dropdowns, and data validation:

```java
@ExcelSubtotals(labelTotalGroup = "Total", sumForGroup = {"department"})
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn      = @ExcelColumn(index = 5, name = "Total Compensation"),
        excelFunction    = @ExcelFunction(
            function     = "sum(${salary},${bonus})",
            nameFunction = "totalCompensation"
        ),
        excelCellsLayout = @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2),
        excelColumnWidth = @ExcelColumnWidth(width = 9)
    )
})
@ExcelConditionCellLayouts(
    @ExcelConditionCellLayout(
        columns   = {"name", "department", "salary", "bonus"},
        condition = "ISNUMBER(SEARCH(\"total\",LOWER(${name[start]})))",
        excelCellLayout = @ExcelCellLayout(
            font          = @ExcelFont(bold = true),
            rgbForeground = @ExcelRgbColor(red = (byte)255, green = (byte)255, blue = (byte)153)
        )
    )
)
public class EmployeeRow implements RowSheet {

    @ExcelColumn(name = "ID", index = 1)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private Integer id;

    @ExcelColumn(name = "Name", index = 2)
    @ExcelCellLayout(autoSizeColumn = true)
    private String name;

    @ExcelColumn(name = "Department", index = 3)
    @ExcelCellLayout
    @ExcelMergeRow(referenceField = "department")
    @ExcelDropDown(
        areaRange = "${Departments.deptRowStart}:${Departments.deptRowEnd}",
        suppressDropDownArrow = true
    )
    private String department;

    @ExcelColumn(name = "Salary", index = 4)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
    @ExcelSubtotal(
        dataConsolidateFunction = DataConsolidateFunction.SUM,
        excelCellLayout = @ExcelCellLayout(
            horizontalAlignment = HorizontalAlignment.RIGHT,
            precision = 2,
            font = @ExcelFont(bold = true)
        )
    )
    private Double salary;

    @ExcelColumn(name = "Bonus", index = 4.1)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
    @ExcelSubtotal(
        dataConsolidateFunction = DataConsolidateFunction.SUM,
        excelCellLayout = @ExcelCellLayout(
            horizontalAlignment = HorizontalAlignment.RIGHT,
            precision = 2,
            font = @ExcelFont(bold = true)
        )
    )
    private Double bonus;

    @ExcelColumn(name = "Hire Date", index = 6)
    @ExcelDate(ColumnDateFormat.YYYY_MM_DD)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
    @ExcelDataValidation(
        "AND(ISNUMBER(${hireDate});${hireDate}=DATE(YEAR(${hireDate});MONTH(${hireDate});DAY(${hireDate})))"
    )
    private Calendar hireDate;

    // constructor, getters, setters ...
}
```

Sheet class combining layout, header style, freeze, super headers, and area borders:

```java
@ExcelSheetLayout(
    sortAndFilter = true,
    landscape     = false,
    areaBorder    = {
        @ExcelAreaBorder(
            areaRange = "${name[header]}:${hireDate[end]}",
            border    = @ExcelBorder(
                top = BorderStyle.MEDIUM, bottom = BorderStyle.MEDIUM,
                left = BorderStyle.MEDIUM, right  = BorderStyle.MEDIUM
            )
        )
    }
)
@ExcelHeaderLayout(
    rowHeight = 3,
    excelHeaderCellLayout = @ExcelHeaderCellLayout(
        rgbForeground = @ExcelRgbColor(red = (byte)31, green = (byte)73, blue = (byte)125),
        rgbFont       = @ExcelRgbColor(red = (byte)255, green = (byte)255, blue = (byte)255),
        font          = @ExcelFont(bold = true)
    )
)
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 2.0, right = 2.0)
@ExcelFreezePane(columnFreez = 2, rowFreez = 1)
@ExcelSuperHeaders(superHeaders = @ExcelSuperHeader(rowHeight = 3, headerGroups = {
    @ExcelSuperHeaderCell(columnName = "Registry",  columnRange = "${name}:${department}"),
    @ExcelSuperHeaderCell(columnName = "Economics", columnRange = "${salary}:${bonus}")
}))
public class EmployeeSheet extends SheetData<EmployeeRow> {

    @ExcelLabel(columnMerge = 5)
    private String reportTitle;

    public EmployeeSheet(String name, String reportTitle) {
        super(name);
        this.reportTitle = reportTitle;
    }

    public String getReportTitle() { return reportTitle; }
    public void setReportTitle(String t) { this.reportTitle = t; }
}
```

---

## `SheetSummary`

Abstract base for two-column key/value summary sheets. Each `@ExcelColumn` field becomes one row: the column `name` is the key, the field value is the value.  
Decorated with `@ExcelSummary` at class level. See [images-labels-summary.md](images-labels-summary.md) for the full `@ExcelSummary` attribute reference.

### Minimal example

```java
@ExcelSheetLayout(startRow = 1, startColumn = 1)
@ExcelSummary(title = "Order Summary", widthColumn1 = 10, widthColumn2 = 15)
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
public class OrderSummary extends SheetSummary {

    @ExcelColumn(name = "Customer",     index = 1)
    @ExcelCellLayout
    private String customer;

    @ExcelColumn(name = "Order Date",   index = 2)
    @ExcelDate(ColumnDateFormat.DD_MM_YYYY)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private Calendar orderDate;

    @ExcelColumn(name = "Total Items",  index = 3)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private Integer totalItems;

    @ExcelColumn(name = "Total Amount", index = 4)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
    private Double totalAmount;

    public OrderSummary(String sheetName, String customer, Calendar orderDate,
                        Integer totalItems, Double totalAmount) {
        super(sheetName);
        this.customer    = customer;
        this.orderDate   = orderDate;
        this.totalItems  = totalItems;
        this.totalAmount = totalAmount;
    }
    // getters / setters ...
}
```

### Full example — with formula referencing another sheet

`SheetSummary` supports `@ExcelFunctionRows` to compute values from other sheets:

```java
@ExcelSheetLayout(startRow = 1, startColumn = 1)
@ExcelSummary(
    title        = "Publisher Info",
    widthColumn1 = 10,
    widthColumn2 = 15,
    layout       = @ExcelCellLayout(font = @ExcelFont(bold = true))
)
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn   = @ExcelColumn(index = 5, name = "Total Revenue"),
        excelFunction = @ExcelFunction(
            function     = "sum(${Books.priceRowStart}:${Books.priceRowEnd})",
            nameFunction = "totalRevenue"
        ),
        excelCellsLayout = @ExcelCellLayout(
            horizontalAlignment = HorizontalAlignment.RIGHT,
            precision = 2,
            font = @ExcelFont(bold = true)
        )
    )
})
public class PublisherSummary extends SheetSummary {

    @ExcelColumn(name = "Name",    index = 1)
    @ExcelCellLayout
    private String name;

    @ExcelColumn(name = "City",    index = 2)
    @ExcelCellLayout
    @ExcelRowHeight(height = 3)
    private String city;

    @ExcelColumn(name = "Founded", index = 3)
    @ExcelDate(ColumnDateFormat.YYYY_MM_DD)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private Calendar founded;

    @ExcelColumn(name = "Logo",    index = 4)
    @ExcelImage(resizeHeight = 0.6, resizeWidth = 0.6)
    @ExcelCellLayout
    @ExcelRowHeight(height = 5)
    private String logoPath;

    public PublisherSummary(String sheetName, String name, String city,
                            Calendar founded, String logoPath) {
        super(sheetName);
        this.name     = name;
        this.city     = city;
        this.founded  = founded;
        this.logoPath = logoPath;
    }
    // getters / setters ...
}
```

---

## `MergeSheet`

Merges multiple `SheetComponent` instances (`SheetData` or `SheetSummary` subclasses) into **a single Excel tab**. The sheet name is taken from the `MergeSheet` itself; the names of the inner components are ignored.

| Constructor | Description |
|-------------|-------------|
| `MergeSheet(String sheetName, SheetComponent... sheets)` | Create with inline components |
| `MergeSheet(String sheetName, List<SheetComponent> sheets)` | Create from a list |
| `MergeSheet(String sheetName)` | Create empty, then call `setSheets(...)` |

```java
// Place a summary block above a data table on the same tab
OrderSummary summary = new OrderSummary("_", customer, date, count, total);
OrderSheet   data    = new OrderSheet("_");
data.setRows(rows);

MergeSheet merged = new MergeSheet("Order Report", summary, data);

byte[] xlsx = generateExcel.createFileXlsx(new ReportExcel("report", List.of(merged)));
```

---

## `FunctionsTotal<T extends SheetFunctionTotal<?>>`

Interface implemented by a `SheetData` subclass when it needs a **companion total sheet** — a separate row block that renders the formula results from `@ExcelFunctionRows` immediately below the data.

| Method | Description |
|--------|-------------|
| `getSheetFunctionsTotal()` | Return the companion sheet |
| `setSheetFunctionsTotal(T)` | Set the companion sheet |

```java
// 1. Companion total row
public class SalaryTotalsRow implements RowSheet {

    @ExcelColumn(name = "Total Salary", index = 3)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2,
                     font = @ExcelFont(bold = true))
    private Double totalSalary;
    // getters / setters ...
}

// 2. Companion total sheet — no sheet name (appended inline)
@ExcelSheetLayout(showHeader = false, startRow = -2)
@ExcelHeaderLayout
public class SalaryTotalsSheet extends SheetFunctionTotal<SalaryTotalsRow> { }

// 3. Main sheet implementing FunctionsTotal
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn   = @ExcelColumn(index = 3, name = "Total Salary"),
        excelFunction = @ExcelFunction(
            function     = "sum(${salaryRowStart}:${salaryRowEnd})",
            nameFunction = "totalSalary"
        ),
        excelCellsLayout = @ExcelCellLayout(
            horizontalAlignment = HorizontalAlignment.RIGHT,
            precision = 2, font = @ExcelFont(bold = true)
        )
    )
})
public class SalarySheet extends SheetData<SalaryRow>
        implements FunctionsTotal<SalaryTotalsSheet> {

    private SalaryTotalsSheet sheetFunctionsTotal = new SalaryTotalsSheet();

    public SalarySheet(String name) { super(name); }

    @Override public SalaryTotalsSheet getSheetFunctionsTotal() { return sheetFunctionsTotal; }
    @Override public void setSheetFunctionsTotal(SalaryTotalsSheet s) { this.sheetFunctionsTotal = s; }
}
```

---

## `ExcelHyperlink`

Field type for `RowSheet` classes. When declared as a field annotated with `@ExcelColumn` + `@ExcelCellLayout`, the cell is rendered as a clickable link.

| Constructor parameter | Description |
|-----------------------|-------------|
| `value`         | Display text in the cell |
| `address`       | Target sheet name (for `DOCUMENT`) or URL |
| `hyperlinkType` | `HyperlinkType.DOCUMENT`, `URL`, `EMAIL`, `FILE` |
| `row`           | Target row number |
| `column`        | Target column letter (e.g. `"A"`) |

```java
public class IndexRow implements RowSheet {

    @ExcelColumn(name = "Link", index = 1)
    @ExcelCellLayout(
        font    = @ExcelFont(underline = UnderlineType.SINGLE, italic = true),
        rgbFont = @ExcelRgbColor(red = 0, green = 0, blue = (byte)255)
    )
    private ExcelHyperlink excelHyperlink;

    @ExcelColumn(name = "Description", index = 2)
    @ExcelCellLayout
    private String description;

    public IndexRow(ExcelHyperlink link, String description) {
        this.excelHyperlink = link;
        this.description    = description;
    }
    // getters / setters ...
}

// Usage — build an index sheet linking to other tabs
IndexSheet index = new IndexSheet("Index");
index.addRows(
    new IndexRow(new ExcelHyperlink("Employees", "Employees", HyperlinkType.DOCUMENT, 1, "A"), "Employee list"),
    new IndexRow(new ExcelHyperlink("Products",  "Products",  HyperlinkType.DOCUMENT, 1, "A"), "Product catalogue")
);
```

---

## `ExcelAttachment<T>`

Attaches an external file (PDF, Word, Excel, …) to the workbook. `T` is `byte[]` (raw content) or `String` (file-system path). Use the static factory methods to create instances.

| Method | Description |
|--------|-------------|
| `ExcelAttachment.newInstance(byte[])` | Create from raw bytes |
| `ExcelAttachment.newInstance(String)` | Create from file path |
| `setAttachmentType(AttachmentType)` | Set file type |
| `setFileName(String)` | Set display name inside the workbook |

`AttachmentType` values: `DOCX`, `DOC`, `XLS`, `XLSX`, `PPTX`, `PPT`, `PDF`.

```java
// File path — attach a PDF contract
ExcelAttachment<String> att = ExcelAttachment.newInstance("/docs/contract.pdf");
att.setAttachmentType(AttachmentType.PDF);
att.setFileName("publisher-contract.pdf");

// Raw bytes
ExcelAttachment<byte[]> att2 = ExcelAttachment.newInstance(Files.readAllBytes(Path.of("/docs/report.xlsx")));
att2.setAttachmentType(AttachmentType.XLSX);
att2.setFileName("annual-report.xlsx");
```

Declared as a field inside a `SheetSummary`:

```java
public class PublisherSummary extends SheetSummary {

    @ExcelColumn(name = "Contract", index = 5)
    @ExcelCellLayout
    private ExcelAttachment<String> contract;

    public PublisherSummary(String sheetName, String contractPath) {
        super(sheetName);
        ExcelAttachment<String> att = ExcelAttachment.newInstance(contractPath);
        att.setAttachmentType(AttachmentType.PDF);
        att.setFileName("contract.pdf");
        this.contract = att;
    }
    // getter / setter ...
}
```
