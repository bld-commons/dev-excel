# Domain Classes

All sheet types extend or implement a common hierarchy. The diagram below shows the relationships.

```
BaseSheet
├── SheetData<T extends RowSheet>          implements SheetComponent
│   ├── QuerySheetData<T>
│   ├── SheetDynamicData<T extends DynamicRowSheet>   implements DynamicColumn
│   │   └── DynamicChart<T>
│   ├── LoadSheetData<R, S>
│   └── SheetFunctionTotal<T>
│       └── SheetDynamicFunctionTotal<T>  implements DynamicColumn
└── SheetSummary                           implements SheetComponent

MergeSheet extends BaseSheet              (groups SheetComponent instances on one tab)

RowSheet  (interface)
└── DynamicRowSheet  (abstract class)

FunctionsTotal<T>  (interface — implemented by SheetData subclasses that expose a companion total sheet)
```

---

## `RowSheet`

Marker interface. Every class that represents a data row must implement it.  
Fields are mapped to columns with `@ExcelColumn` + `@ExcelCellLayout`.

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

## `DynamicRowSheet`

Abstract class that extends `RowSheet`. Used alongside `SheetDynamicData` when the number of columns is not known at compile time. Static fields are declared as usual; dynamic columns are stored in the `mapValue` map keyed by the same keys used in `SheetDynamicData.mapExtraColumnAnnotation`.

| Method | Description |
|--------|-------------|
| `addValue(String key, Object value)` | Add a value for a dynamic column |
| `addValues(Map<String,Object> map)` | Add multiple dynamic values at once |
| `clearMap()` | Remove all dynamic values |

```java
public class SalesRow extends DynamicRowSheet {

    // Static columns — declared as fields
    @ExcelColumn(name = "Product", index = 1)
    @ExcelCellLayout(autoSizeColumn = true)
    private String product;

    @ExcelColumn(name = "Region", index = 2)
    @ExcelCellLayout
    private String region;

    // Dynamic columns (e.g. one per month) are stored in mapValue
    // with keys like "jan", "feb", ... "dec"

    public SalesRow(String product, String region) {
        super();
        this.product = product;
        this.region  = region;
    }
    // getters / setters ...
}
```

---

## `SheetData<T extends RowSheet>`

Abstract base for sheets with **static data** (rows populated manually in code). The generic parameter `T` is the `RowSheet` implementation for this sheet.

| Method | Description |
|--------|-------------|
| `addRows(T... rows)` | Append one or more rows |
| `setRows(List<T> rows)` | Replace the entire row list |
| `getRows()` | Return all rows |
| `clear()` | Remove all rows |
| `getRowClass()` | Return the generic type `T` at runtime |
| `setEnableInfoSheet(boolean)` | Control whether the sheet is included in the info output |

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
ReportExcel report = new ReportExcel("Catalogue", List.of(sheet));
byte[] xlsx = generateExcel.createFileXlsx(report);
```

### Full example with multiple annotations

```java
@ExcelSheetLayout(sortAndFilter = true, landscape = false)
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
@ExcelSuperHeaders(superHeaders = @ExcelSuperHeader(
    headerGroups = {
        @ExcelSuperHeaderCell(columnName = "Registry",  columnRange = "${name}:${department}"),
        @ExcelSuperHeaderCell(columnName = "Economics", columnRange = "${salary}:${bonus}")
    }
))
@ExcelSubtotals(labelTotalGroup = "Total", sumForGroup = {"department"})
@ExcelConditionCellLayouts(
    @ExcelConditionCellLayout(
        columns   = {"name", "department", "salary", "bonus"},
        condition = "ISNUMBER(SEARCH(\"total\",LOWER(${name[start]})))",
        excelCellLayout = @ExcelCellLayout(font = @ExcelFont(bold = true),
            rgbForeground = @ExcelRgbColor(red = (byte)255, green = (byte)255, blue = (byte)153))
    )
)
public class EmployeeSheet extends SheetData<EmployeeRow> {
    public EmployeeSheet(String name) { super(name); }
}
```

---

## `QuerySheetData<T extends RowSheet>`

Extends `SheetData`. Rows are populated automatically from a SQL or JPQL query defined with `@ExcelQuery`. Parameters are passed via `addParameters`.

| Method | Description |
|--------|-------------|
| `addParameters(String key, Object value)` | Add a named query parameter |
| `addParameters(Map<String,Object>)` | Add multiple parameters at once |
| `resetParameters()` | Clear all parameters |
| `getMapParameters()` | Return the current parameter map |

```java
// Sheet class
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
@ExcelQuery(
    "SELECT e.id, e.name, e.department, e.salary, e.hire_date " +
    "FROM employee e WHERE e.department IN (:departments) ORDER BY e.department, e.name"
)
public class EmployeeSheet extends QuerySheetData<EmployeeRow> {
    public EmployeeSheet(String name) { super(name); }
}

// Usage
EmployeeSheet sheet = new EmployeeSheet("Employees");
sheet.addParameters("departments", List.of("Engineering", "Marketing"));

byte[] xlsx = generateExcel.createFileXlsx(new ReportExcel("report", List.of(sheet)));
```

---

## `SheetSummary`

Abstract base for **two-column key/value summary sheets**. Each field annotated with `@ExcelColumn` becomes one row: the column name is the key, the field value is the value.  
Decorated with `@ExcelSummary` at class level. See [images-labels-summary.md](images-labels-summary.md) for the full annotation reference.

```java
@ExcelSheetLayout(startRow = 1, startColumn = 1)
@ExcelSummary(title = "Order Summary", widthColumn1 = 10, widthColumn2 = 15)
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
public class OrderSummary extends SheetSummary {

    @ExcelColumn(name = "Customer",    index = 1) @ExcelCellLayout private String customer;
    @ExcelColumn(name = "Order Date",  index = 2) @ExcelDate @ExcelCellLayout private Calendar orderDate;
    @ExcelColumn(name = "Total Items", index = 3) @ExcelCellLayout private Integer totalItems;

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

---

## `SheetFunctionTotal<T extends RowSheet>`

A companion sheet attached to a `SheetData` via the `FunctionsTotal` interface. It renders the formula results (from `@ExcelFunctionRows`) of the main sheet in a separate row block, usually placed directly below the data — without a sheet name (empty constructor).

```java
// The companion total sheet
@ExcelSheetLayout(showHeader = false, startRow = -2)
@ExcelHeaderLayout
public class OrderTotalsSheet extends SheetFunctionTotal<OrderTotalsRow> { }

// The row class for the totals sheet
public class OrderTotalsRow implements RowSheet {
    @ExcelColumn(name = "Total Revenue", index = 4)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2,
                     font = @ExcelFont(bold = true))
    private Double totalRevenue;
    // getters / setters ...
}

// Main sheet declares the companion via FunctionsTotal
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn   = @ExcelColumn(index = 4, name = "Total Revenue"),
        excelFunction = @ExcelFunction(
            function     = "sum(${revenueRowStart}:${revenueRowEnd})",
            nameFunction = "totalRevenue"
        )
    )
})
public class OrderSheet extends QuerySheetData<OrderRow>
        implements FunctionsTotal<OrderTotalsSheet> {

    private OrderTotalsSheet sheetFunctionsTotal;

    public OrderSheet(String name) {
        super(name);
        this.sheetFunctionsTotal = new OrderTotalsSheet();
    }

    @Override public OrderTotalsSheet getSheetFunctionsTotal() { return sheetFunctionsTotal; }
    @Override public void setSheetFunctionsTotal(OrderTotalsSheet s) { this.sheetFunctionsTotal = s; }
}
```

---

## `FunctionsTotal<T extends SheetFunctionTotal<?>>`

Interface implemented by a `SheetData` subclass to expose a companion total sheet. The generator reads the companion sheet and appends its formula rows immediately after the data.

| Method | Description |
|--------|-------------|
| `getSheetFunctionsTotal()` | Return the companion total sheet |
| `setSheetFunctionsTotal(T)` | Set the companion total sheet |

---

## `SheetDynamicData<T extends DynamicRowSheet>`

Extends `SheetData`. Combines static fields (declared on the `DynamicRowSheet` subclass) with **dynamic columns** defined at runtime via `mapExtraColumnAnnotation`.  
The map keys in `SheetDynamicData.mapExtraColumnAnnotation` must match the keys used in `DynamicRowSheet.mapValue` on each row.

| Method | Description |
|--------|-------------|
| `addExtraColumn(String key, ExtraColumnAnnotation)` | Register a dynamic column |
| `getMapExtraColumnAnnotation()` | Return all dynamic column definitions |

### `ExtraColumnAnnotation`

Holds the runtime equivalent of field-level annotations for a dynamic column. All setters accept `*Impl` wrapper objects.

| Property | Type | Description |
|----------|------|-------------|
| `excelColumn` | `ExcelColumn` | **Required.** Column header and index |
| `excelCellLayout` | `ExcelCellLayout` | **Required.** Cell style |
| `excelColumnWidth` | `ExcelColumnWidth` | Column width |
| `excelDate` | `ExcelDate` | Date format |
| `excelMergeRow` | `ExcelMergeRow` | Merge behaviour |
| `excelHeaderCellLayout` | `ExcelHeaderCellLayout` | Header cell style |
| `excelSubtotal` | `ExcelSubtotal` | Subtotal config |
| `excelDropDown` | `ExcelDropDown` | Dropdown list |
| `excelDataValidation` | `ExcelDataValidation` | Validation rule |
| `excelFunction` | `ExcelFunction` | Formula |
| `excelBooleanText` | `ExcelBooleanText` | Boolean-to-text mapping |

```java
// Row class for dynamic sheet
public class SalesRow extends DynamicRowSheet {

    @ExcelColumn(name = "Product", index = 1) @ExcelCellLayout private String product;
    @ExcelColumn(name = "Region",  index = 2) @ExcelCellLayout private String region;

    public SalesRow(String product, String region) {
        super();
        this.product = product;
        this.region  = region;
    }
    // getters / setters ...
}

// Sheet class
@ExcelSheetLayout @ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
public class SalesSheet extends SheetDynamicData<SalesRow> {
    public SalesSheet(String name) { super(name); }
}

// Usage — months are the dynamic columns
List<String> months = List.of("jan", "feb", "mar");
double[] startIndex = {3};

SalesSheet sheet = new SalesSheet("Sales");

for (String month : months) {
    ExtraColumnAnnotation col = new ExtraColumnAnnotation();
    col.setExcelColumn(new ExcelColumnImpl(month.toUpperCase(), startIndex[0]++));
    col.setExcelCellLayout(new ExcelCellLayoutImpl(
        true, VerticalAlignment.CENTER,
        new ExcelRgbColor[]{ExcelConstant.RGB_FOREGROUND.getAnnotation()},
        new ExcelRgbColor[]{ExcelConstant.RGB_FONT.getAnnotation()},
        2, HorizontalAlignment.RIGHT,
        ExcelConstant.FONT.getAnnotation(),
        FillPatternType.SOLID_FOREGROUND,
        ExcelConstant.BORDER.getAnnotation()
    ));
    sheet.addExtraColumn(month, col);
}

SalesRow row = new SalesRow("Widget A", "North");
row.addValue("jan", 1200.0);
row.addValue("feb",  980.0);
row.addValue("mar", 1500.0);
sheet.addRows(row);
```

---

## `DynamicChart<T extends DynamicRowSheet>`

Extends `SheetDynamicData`. Adds the ability to define charts programmatically at runtime via `addExcelChart(ExcelChartImpl)`, rather than through the static `@ExcelCharts` annotation.

| Method | Description |
|--------|-------------|
| `addExcelChart(ExcelChartImpl)` | Add a chart definition at runtime |
| `getListExcelChart()` | Return all chart definitions |

```java
@ExcelSheetLayout(notMerge = false)
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
public class SalesDynamicSheet extends DynamicChart<SalesRow> {
    public SalesDynamicSheet(String name) { super(name); }
}
```

---

## `SheetDynamicFunctionTotal<T extends DynamicRowSheet>`

Companion total sheet for `SheetDynamicData`, combining `SheetFunctionTotal` and `DynamicColumn`. Used when the companion total sheet also has dynamic columns.

```java
public class SalesTotalsSheet extends SheetDynamicFunctionTotal<SalesTotalsRow> { }
```

---

## `LoadSheetData<R extends RowSheet, S extends LoadSheetData<R, S>>`

Extends `SheetData`. Delegates row loading to a `LoadSheetFunction` lambda, called at generation time. Useful when rows depend on context only available during generation.

| Method | Description |
|--------|-------------|
| `initRows()` | Called by the generator to trigger `loadSheetFunction.loadSheet(this)` |
| `getLoadSheetFunction()` | Return the loading lambda |

```java
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

// Usage — pass the loading logic as a lambda
ReportSheet sheet = new ReportSheet("Report", "Engineering",
    s -> repository.findByDepartment(s.getDepartment())
                   .stream().map(ReportRow::new).toList()
);
```

---

## `MergeSheet`

Merges multiple `SheetComponent` instances (any `SheetData` or `SheetSummary` subclass) into **a single Excel tab**. Useful for placing a summary and a data table on the same sheet.

| Constructor | Description |
|-------------|-------------|
| `MergeSheet(String sheetName, SheetComponent... sheets)` | Create with inline components |
| `MergeSheet(String sheetName, List<SheetComponent> sheets)` | Create from a list |
| `MergeSheet(String sheetName)` | Create empty, populate via `setSheets` |

```java
// A summary block + a data table on the same tab
OrderSummary  summary = new OrderSummary("ignored", customer, date, count, total);
OrderSheet    data    = new OrderSheet("ignored");
data.setRows(rows);

MergeSheet merged = new MergeSheet("Order Report", summary, data);

ReportExcel report = new ReportExcel("report", List.of(merged));
byte[] xlsx = generateExcel.createFileXlsx(report);
```

---

## `ExcelHyperlink`

A field type for `RowSheet` classes. When declared as a field and annotated with `@ExcelColumn` + `@ExcelCellLayout`, the cell is rendered as a clickable hyperlink inside the workbook (type `DOCUMENT` links to another sheet).

| Constructor parameter | Description |
|-----------------------|-------------|
| `value`         | Display text shown in the cell |
| `address`       | Target sheet name |
| `hyperlinkType` | `HyperlinkType.DOCUMENT`, `URL`, `EMAIL`, `FILE` |
| `row`           | Target row number in the destination sheet |
| `column`        | Target column letter (e.g. `"A"`) |

```java
// Row class with a hyperlink
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

// Usage
IndexSheet index = new IndexSheet("Index");
index.addRows(
    new IndexRow(new ExcelHyperlink("Employees", "Employees", HyperlinkType.DOCUMENT, 1, "A"), "Employee list"),
    new IndexRow(new ExcelHyperlink("Products",  "Products",  HyperlinkType.DOCUMENT, 1, "A"), "Product catalogue")
);
```

---

## `ExcelAttachment<T>`

Attaches an external file to the workbook. `T` is either `byte[]` (raw file content) or `String` (file-system path). Created via the static factory methods.

| Method | Description |
|--------|-------------|
| `ExcelAttachment.newInstance(byte[] attachment)` | Create from raw bytes |
| `ExcelAttachment.newInstance(String attachment)` | Create from file path |
| `setAttachmentType(AttachmentType)` | Set file type (`DOCX`, `PDF`, `XLSX`, …) |
| `setFileName(String)` | Set the display file name inside the workbook |

Supported `AttachmentType` values: `DOCX`, `DOC`, `XLS`, `XLSX`, `PPTX`, `PPT`, `PDF`.

```java
// Field on a SheetSummary subclass
public class PublisherSummary extends SheetSummary {

    @ExcelColumn(name = "Contract", index = 5)
    @ExcelCellLayout
    private ExcelAttachment<String> contract;

    public PublisherSummary(String sheetName, String contractPath) {
        super(sheetName);
        ExcelAttachment<String> att = ExcelAttachment.newInstance(contractPath);
        att.setAttachmentType(AttachmentType.PDF);
        att.setFileName("publisher-contract.pdf");
        this.contract = att;
    }
    // getter / setter ...
}

// Or from raw bytes
ExcelAttachment<byte[]> att = ExcelAttachment.newInstance(Files.readAllBytes(Path.of("/docs/contract.pdf")));
att.setAttachmentType(AttachmentType.PDF);
att.setFileName("contract.pdf");
```
