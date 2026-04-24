# Dynamic Sheets

Sheet types that support columns whose number, names, and styles are not known at compile time but are defined at runtime.

---

## `DynamicRowSheet`

Abstract class that implements `RowSheet`. Used together with `SheetDynamicData`. Static fields are declared as usual on the subclass; dynamic column values are stored in the `mapValue` map.

The keys in `mapValue` must match the keys used in `SheetDynamicData.mapExtraColumnAnnotation`.

| Method | Description |
|--------|-------------|
| `addValue(String key, Object value)` | Add a value for a dynamic column |
| `addValues(Map<String,Object>)` | Add multiple dynamic values at once |
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

    // Dynamic columns (e.g. one per month) stored in mapValue
    // with keys like "jan", "feb", ..., "dec"

    public SalesRow(String product, String region) {
        super();
        this.product = product;
        this.region  = region;
    }
    // getters / setters ...
}
```

---

## `SheetDynamicData<T extends DynamicRowSheet>`

Extends `SheetData`. Combines static fields (on the `DynamicRowSheet` subclass) with dynamic columns defined at runtime via `mapExtraColumnAnnotation`. Inherits all `SheetData` methods (`addRows`, `setRows`, `clear`, …).

| Method | Description |
|--------|-------------|
| `addExtraColumn(String key, ExtraColumnAnnotation)` | Register a dynamic column |
| `getMapExtraColumnAnnotation()` | Return all dynamic column definitions |

### `ExtraColumnAnnotation`

Holds the runtime equivalent of field-level annotations for a dynamic column. Use the `*Impl` setter variants to configure it.

| Property | Type | Required | Description |
|----------|------|----------|-------------|
| `excelColumn` | `ExcelColumn` | yes | Column header and index |
| `excelCellLayout` | `ExcelCellLayout` | yes | Cell style |
| `excelColumnWidth` | `ExcelColumnWidth` | — | Column width |
| `excelDate` | `ExcelDate` | — | Date format |
| `excelMergeRow` | `ExcelMergeRow` | — | Merge behaviour |
| `excelHeaderCellLayout` | `ExcelHeaderCellLayout` | — | Header cell style |
| `excelSubtotal` | `ExcelSubtotal` | — | Subtotal configuration |
| `excelDropDown` | `ExcelDropDown` | — | Dropdown list |
| `excelDataValidation` | `ExcelDataValidation` | — | Validation rule |
| `excelFunction` | `ExcelFunction` | — | Formula |
| `excelBooleanText` | `ExcelBooleanText` | — | Boolean-to-text mapping |

### Example — monthly sales columns built at runtime

```java
// Row class
@ExcelSubtotals(labelTotalGroup = "Total", sumForGroup = {"region"})
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn   = @ExcelColumn(index = 100, name = "Annual Total"),
        excelFunction = @ExcelFunction(
            function     = "sum(${jan},${feb},${mar},${apr},${may},${jun}," +
                           "${jul},${aug},${sep},${oct},${nov},${dec})",
            nameFunction = "annualTotal",
            anotherTable = false
        ),
        excelCellsLayout = @ExcelCellLayout(
            horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2
        ),
        excelColumnWidth = @ExcelColumnWidth(width = 10)
    )
})
public class SalesRow extends DynamicRowSheet {

    @ExcelColumn(name = "Product", index = 1)
    @ExcelCellLayout(autoSizeColumn = true)
    @ExcelMergeRow(referenceField = "region")
    private String product;

    @ExcelColumn(name = "Region", index = 2)
    @ExcelCellLayout
    @ExcelMergeRow
    private String region;

    public SalesRow(String product, String region) {
        super();
        this.product = product;
        this.region  = region;
    }
    // getters / setters ...
}

// Sheet class
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
public class SalesSheet extends SheetDynamicData<SalesRow> {
    public SalesSheet(String name) { super(name); }
}
```

Building the dynamic columns and rows:

```java
List<String> months      = List.of("jan","feb","mar","apr","may","jun",
                                   "jul","aug","sep","oct","nov","dec");
List<String> monthLabels = List.of("Jan","Feb","Mar","Apr","May","Jun",
                                   "Jul","Aug","Sep","Oct","Nov","Dec");

SalesSheet sheet = new SalesSheet("Monthly Sales");

// Register one dynamic column per month
for (int i = 0; i < months.size(); i++) {
    ExtraColumnAnnotation col = new ExtraColumnAnnotation();
    col.setExcelColumn(new ExcelColumnImpl(monthLabels.get(i), 3 + i));
    col.setExcelCellLayout(new ExcelCellLayoutImpl(
        true, VerticalAlignment.CENTER,
        new ExcelRgbColor[]{ExcelConstant.RGB_FOREGROUND.getAnnotation()},
        new ExcelRgbColor[]{ExcelConstant.RGB_FONT.getAnnotation()},
        2, HorizontalAlignment.RIGHT,
        ExcelConstant.FONT.getAnnotation(),
        FillPatternType.SOLID_FOREGROUND,
        ExcelConstant.BORDER.getAnnotation()
    ));
    col.setExcelColumnWidth(new ExcelColumnWidthImpl(6));
    sheet.addExtraColumn(months.get(i), col);
}

// Add rows with dynamic values
SalesRow row = new SalesRow("Widget A", "North");
row.addValue("jan", 1200.0);
row.addValue("feb",  980.5);
row.addValue("mar", 1530.0);
// ... other months ...
sheet.addRows(row);

byte[] xlsx = generateExcel.createFileXlsx(new ReportExcel("Sales", List.of(sheet)));
```

---

## `DynamicChart<T extends DynamicRowSheet>`

Extends `SheetDynamicData`. Adds the ability to define charts **programmatically at runtime** via `addExcelChart`, instead of the static `@ExcelCharts` annotation. Useful when the number or type of chart series depends on the dynamic columns.

| Method | Description |
|--------|-------------|
| `addExcelChart(ExcelChartImpl)` | Add a chart definition at runtime |
| `getListExcelChart()` | Return all chart definitions |

```java
// Sheet class
@ExcelSheetLayout(notMerge = false)
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
public class SalesChartSheet extends DynamicChart<SalesRow>
        implements FunctionsTotal<SalesTotalsSheet> {

    private SalesTotalsSheet sheetFunctionsTotal;

    public SalesChartSheet(String name) { super(name); }

    @Override public SalesTotalsSheet getSheetFunctionsTotal() { return sheetFunctionsTotal; }
    @Override public void setSheetFunctionsTotal(SalesTotalsSheet s) { this.sheetFunctionsTotal = s; }
}
```

Building and attaching charts at runtime:

```java
SalesChartSheet sheet = new SalesChartSheet("Sales Chart");

// ... register dynamic columns and add rows (same as SheetDynamicData) ...

// Build a chart programmatically
ExcelChartImpl chart = new ExcelChartImpl();
chart.setId("monthlySales");
chart.setChartTypes(ChartTypes.BAR);
chart.setSizeColumn(12);
chart.setSizeRow(20);
chart.setXAxis("${jan[start]}:${dec[end]}");
chart.setGroup(true);
// add categories ...
sheet.addExcelChart(chart);
```

---

## `SheetDynamicFunctionTotal<T extends DynamicRowSheet>`

Companion total sheet for `SheetDynamicData` or `DynamicChart`, combining `SheetFunctionTotal` and `DynamicColumn`. Used when the companion total sheet itself also needs dynamic columns (same keys as the main sheet).

```java
// Companion total row
public class SalesTotalsRow extends DynamicRowSheet {

    @ExcelColumn(name = "Annual Total", index = 100)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2,
                     font = @ExcelFont(bold = true))
    private Double annualTotal;
    // getters / setters ...
}

// Companion total sheet
@ExcelSheetLayout(showHeader = false, startRow = -2)
@ExcelHeaderLayout
public class SalesTotalsSheet extends SheetDynamicFunctionTotal<SalesTotalsRow> { }
```

At runtime, copy the same `mapExtraColumnAnnotation` from the main sheet to the companion so column widths and styles match:

```java
SalesChartSheet main = new SalesChartSheet("Sales");
// ... register dynamic columns on main ...

SalesTotalsSheet totals = new SalesTotalsSheet();
totals.setMapExtraColumnAnnotation(main.getMapExtraColumnAnnotation());
main.setSheetFunctionsTotal(totals);
```
