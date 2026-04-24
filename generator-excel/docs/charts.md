# Charts

Annotations for embedding charts in a sheet. Supported only for **XLSX (XSSF)** format.

---

## `@ExcelCharts`

Container annotation applied at **class level** on a `SheetData` subclass. Holds one or more `@ExcelChart` definitions.

| Attribute     | Type           | Description |
|---------------|----------------|-------------|
| `excelCharts` | `ExcelChart[]` | One or more chart definitions |

---

## `@ExcelChart`

Defines a single chart embedded in the sheet.

| Attribute              | Type                   | Default           | Description |
|------------------------|------------------------|-------------------|-------------|
| `id`                   | `String`               | —                 | **Required.** Unique identifier (used to group series) |
| `excelChartCategories` | `ExcelChartCategory[]` | —                 | **Required.** Data series definitions |
| `xAxis`                | `String`               | —                 | **Required.** Cell range for X-axis labels (e.g. `${year[start]}:${year[end]}`) |
| `chartTypes`           | `ChartTypes`           | `LINE`            | Chart type: `LINE`, `BAR`, `PIE`, `AREA`, `SCATTER`, … |
| `sizeRow`              | `int`                  | —                 | **Required.** Chart height in number of rows |
| `sizeColumn`           | `int`                  | —                 | **Required.** Chart width in number of columns |
| `group`                | `boolean`              | `false`           | Group data series by category field |
| `title`                | `String`               | `""`              | Chart title |
| `legendPosition`       | `LegendPosition`       | `BOTTOM`          | Legend position (`TOP`, `BOTTOM`, `LEFT`, `RIGHT`) |
| `categoryAxis`         | `AxisPosition`         | `BOTTOM`          | Position of the category (X) axis |
| `valueAxis`            | `AxisPosition`         | `LEFT`            | Position of the value (Y) axis |
| `lineColor`            | `PresetColor[]`        | `BLACK`           | Colour of each series line/bar |
| `axisLineColor`        | `PresetColor`          | `BLACK`           | Colour of the axis lines |
| `gridLineColor`        | `PresetColor`          | `GRAY`            | Colour of the gridlines |
| `crosses`              | `AxisCrosses`          | `AUTO_ZERO`       | Where the axes cross |
| `crossBetween`         | `AxisCrossBetween`     | `BETWEEN`         | Bar positioning relative to tick marks |
| `showLeaderLines`      | `boolean`              | `true`            | Show leader lines on pie/doughnut charts |
| `smooth`               | `boolean`              | `true`            | Smooth line curves (LINE chart) |
| `excelChartDataLabel`  | `ExcelChartDataLabel`  | (disabled)        | Data label configuration — see [`@ExcelChartDataLabel`](#excelchartdatalabel) |
| `spreadsheetVersion`   | `SpreadsheetVersion`   | `EXCEL2007`       | Target spreadsheet version |

---

## `@ExcelChartCategory`

Defines a single data series within a chart.

| Attribute   | Type     | Default | Description |
|-------------|----------|---------|-------------|
| `fieldName` | `String` | —       | **Required.** Field or expression used as the series label (e.g. `${countryName}`) |
| `function`  | `String` | —       | **Required.** Cell range expression for the series data (e.g. `${births[start]}:${births[end]}`) |
| `rowRegex`  | `String` | `""`    | Regex that must match the row number — used to select a subset of rows |

---

## `@ExcelChartDataLabel`

Configures data labels rendered on chart data points. Used as the `excelChartDataLabel` attribute of `@ExcelChart`.

| Attribute      | Type      | Default | Description |
|----------------|-----------|---------|-------------|
| `enable`       | `boolean` | `false` | `true` to show data labels |
| `showVal`      | `boolean` | `true`  | Show the numeric value |
| `showLegendKey`| `boolean` | `true`  | Show the legend colour box |
| `showCatName`  | `boolean` | `true`  | Show the category name |
| `showSerName`  | `boolean` | `true`  | Show the series name |

---

## `@ExcelBarChartData`

Controls the bar direction when `chartTypes = ChartTypes.BAR`. Applied at **class level** alongside `@ExcelCharts`.

| Attribute | Type           | Default        | Description |
|-----------|----------------|----------------|-------------|
| `value`   | `BarDirection` | `BarDirection.BAR` | `BAR` = horizontal, `COL` = vertical columns |

---

## Examples

**1. Line chart — grouped series (one line per country)**

```java
@ExcelSheetLayout(notMerge = false)
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, right = 1.5, left = 1.5)
@ExcelQuery(
    "SELECT n.country_name, c.year, c.births, c.deaths " +
    "FROM nation n JOIN census c ON n.id = c.nation_id " +
    "ORDER BY n.country_name, c.year"
)
@ExcelCharts(excelCharts = {
    @ExcelChart(
        id                 = "births",
        excelChartCategories = @ExcelChartCategory(
            fieldName = "${countryName}",
            function  = "${births[start]}:${births[end]}"
        ),
        xAxis      = "${year[start]}:${year[end]}",
        chartTypes = ChartTypes.LINE,
        sizeColumn = 10,
        sizeRow    = 20,
        group      = true,
        title      = "Births by Country",
        legendPosition = LegendPosition.BOTTOM,
        smooth     = true
    )
})
public class CensusSheet extends QuerySheetData<CensusRow> {
    public CensusSheet(String name) { super(name); }
}
```

**2. Bar chart — vertical columns with data labels**

```java
@ExcelCharts(excelCharts = {
    @ExcelChart(
        id                 = "sales",
        excelChartCategories = @ExcelChartCategory(
            fieldName = "${product}",
            function  = "${revenue[start]}:${revenue[end]}"
        ),
        xAxis              = "${quarter[start]}:${quarter[end]}",
        chartTypes         = ChartTypes.BAR,
        sizeColumn         = 12,
        sizeRow            = 18,
        title              = "Revenue by Product",
        excelChartDataLabel = @ExcelChartDataLabel(
            enable      = true,
            showVal     = true,
            showCatName = false,
            showSerName = false,
            showLegendKey = false
        )
    )
})
@ExcelBarChartData(BarDirection.COL)
public class SalesSheet extends SheetData<SalesRow> { ... }
```
