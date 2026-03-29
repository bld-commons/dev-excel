# generator-excel

Spring Boot library for generating Excel (XLS, XLSX, SXSSF) and CSV files from annotated Java objects.
Supports static data, JPA query-driven data, charts, pivot tables, images, subtotals, conditional formatting, and more.

## Maven Dependency

```xml
<dependency>
    <groupId>com.github.bld-commons</groupId>
    <artifactId>generator-excel</artifactId>
    <version>5.1.3</version>
</dependency>
```

## Enable

Add `@EnableExcelGenerator` to your Spring Boot application or test class:

```java
@SpringBootTest
@EnableExcelGenerator
public class MyTest { ... }
```

---

## Core API

### `GenerateExcel`

The main interface to generate Excel files.

| Method | Description |
|--------|-------------|
| `createFileXls(ReportExcel)` | Generate `.xls` (HSSF) as `byte[]` |
| `createFileXls(ReportExcel, OutputStream)` | Write `.xls` to a stream |
| `createFileXlsx(ReportExcel)` | Generate `.xlsx` (XSSF) as `byte[]` |
| `createFileXlsx(ReportExcel, OutputStream)` | Write `.xlsx` to a stream |
| `createBigDataFileXlsx(ReportExcel)` | Generate `.xlsx` with SXSSF (streaming, big data) as `byte[]` |
| `createBigDataFileXlsx(ReportExcel, OutputStream)` | Write SXSSF `.xlsx` to a stream |

### `GenerateCsv`

| Method | Description |
|--------|-------------|
| `generateCsv(CsvData<T>)` | Generate a CSV file as `byte[]` |

---

## Domain Classes

### `ReportExcel`

Container for all sheets to generate.

```java
ReportExcel report = new ReportExcel("MyReport", listBaseSheet);
byte[] bytes = generateExcel.createFileXlsx(report);
```

### `SheetData<T extends RowSheet>`

Abstract base for sheets with static (manually populated) data.

```java
public class SalarySheet extends SheetData<SalaryRow> {
    public SalarySheet(String sheetName) {
        super(sheetName);
    }
}
```

| Method | Description |
|--------|-------------|
| `addRows(T... rows)` | Add one or more rows |
| `getRows()` | Return all rows |
| `clear()` | Remove all rows |

### `QuerySheetData<T extends RowSheet>`

Abstract base for sheets populated via `@ExcelQuery` (native SQL or JPQL).

```java
@ExcelQuery("SELECT id, name FROM user WHERE surname = :surname")
public class UserSheet extends QuerySheetData<UserRow> {
    public UserSheet(String sheetName) { super(sheetName); }
}
```

| Method | Description |
|--------|-------------|
| `getMapParameters()` | Return the query parameters map |
| `addParameters(String, Object)` | Add a single parameter |
| `addParameters(Map<String,Object>)` | Add multiple parameters |
| `resetParameters()` | Clear all parameters |

### `RowSheet`

Marker interface that every row class must implement.

### `CsvData<T extends CsvRow>` / `CsvRow`

Container and marker interface for CSV generation.

---

## Annotations Reference

### Sheet-Level Annotations

#### `@ExcelSheetLayout`

Configures the sheet's general layout (page orientation, paper size, print area, etc.).

#### `@ExcelHeaderLayout`

Configures the visual style of the header row (background color, font, border).

#### `@ExcelMarginSheet`

Sets the page margins.

| Attribute | Type     | Default | Description        |
|-----------|----------|---------|--------------------|
| `top`     | `double` | `1.0`   | Top margin (cm)    |
| `bottom`  | `double` | `1.0`   | Bottom margin (cm) |
| `left`    | `double` | `1.0`   | Left margin (cm)   |
| `right`   | `double` | `1.0`   | Right margin (cm)  |

#### `@ExcelFreezePane`

Freezes rows and/or columns.

#### `@ExcelQuery`

Applied to a `QuerySheetData` subclass to define the SQL or JPQL query for automatic row population.

| Attribute       | Type      | Default | Description                                              |
|-----------------|-----------|---------|----------------------------------------------------------|
| `value`         | `String`  | —       | The SQL or JPQL query string                             |
| `nativeQuery`   | `boolean` | `true`  | `true` for native SQL, `false` for JPQL                  |
| `entityManager` | `String`  | `""`    | Bean name of the `EntityManager` for multiple datasource |

```java
// Native SQL — single datasource
@ExcelQuery("SELECT id, name, surname FROM user WHERE surname = :surname")

// JPQL — single datasource
@ExcelQuery(value = "select new com.example.UserRow(u.id, u.name) from User u",
            nativeQuery = false)

// Multiple datasource — specify which EntityManager to use
@ExcelQuery(value = "select new com.example.UserRow(u.id, u.name) from User u",
            nativeQuery = false,
            entityManager = Db2DatabaseConfiguration.DB2_ENTITY_MANAGER)
```

---

### Column/Row Annotations

#### `@ExcelColumn`

Maps a field to an Excel column.

| Attribute | Type      | Default | Description                                |
|-----------|-----------|---------|--------------------------------------------|
| `name`    | `String`  | —       | Column header label                        |
| `index`   | `double`  | —       | Column position (supports decimals for sub-ordering) |
| `comment` | `String`  | `""`    | Column header tooltip comment              |
| `ignore`  | `boolean` | `false` | Exclude this column from the output        |

#### `@ExcelCellLayout`

Full cell style definition (border, alignment, font, color, number format).

| Attribute              | Type                  | Default                  |
|------------------------|-----------------------|--------------------------|
| `border`               | `ExcelBorder`         | THIN on all sides        |
| `horizontalAlignment`  | `HorizontalAlignment` | `LEFT`                   |
| `verticalAlignment`    | `VerticalAlignment`   | `CENTER`                 |
| `font`                 | `ExcelFont`           | CALIBRI, size 11         |
| `wrap`                 | `boolean`             | `true`                   |
| `rgbFont`              | `ExcelRgbColor[]`     | black                    |
| `rgbForeground`        | `ExcelRgbColor[]`     | white                    |
| `fillPatternType`      | `FillPatternType`     | `SOLID_FOREGROUND`       |
| `precision`            | `int`                 | `-1` (no restriction)    |
| `percent`              | `boolean`             | `false`                  |
| `locked`               | `boolean`             | `false`                  |
| `autoSizeColumn`       | `boolean`             | `false`                  |

#### `@ExcelFont`

| Attribute    | Type            | Default     |
|--------------|-----------------|-------------|
| `font`       | `FontType`      | `CALIBRI`   |
| `bold`       | `boolean`       | `false`     |
| `italic`     | `boolean`       | `false`     |
| `underline`  | `UnderlineType` | `NONE`      |
| `size`       | `short`         | `11`        |

#### `@ExcelBorder`

| Attribute | Type          | Default |
|-----------|---------------|---------|
| `top`     | `BorderStyle` | `NONE`  |
| `bottom`  | `BorderStyle` | `NONE`  |
| `left`    | `BorderStyle` | `NONE`  |
| `right`   | `BorderStyle` | `NONE`  |

#### `@ExcelRgbColor`

| Attribute | Type   | Default |
|-----------|--------|---------|
| `red`     | `byte` | `255`   |
| `green`   | `byte` | `255`   |
| `blue`    | `byte` | `255`   |

#### `@ExcelColumnWidth` / `@ExcelRowHeight`

Set custom column widths or row heights.

#### `@ExcelNumberFormat`

| Attribute | Type     | Default | Description             |
|-----------|----------|---------|-------------------------|
| `value`   | `String` | `""`    | Excel number format code |

#### `@ExcelMergeRow`

Merges consecutive cells in a column that have the same value.

| Attribute        | Type     | Default | Description                          |
|------------------|----------|---------|--------------------------------------|
| `referenceField` | `String` | —       | Field name to use as merge reference |

#### `@ExcelHeaderCellLayout` / `@ExcelHeaderLayout`

Define the style of the header row cells.

---

### Subtotals

#### `@ExcelSubtotals`

Applied at class level to configure subtotal grouping.

| Attribute        | Type       | Description                                 |
|------------------|------------|---------------------------------------------|
| `labelTotalGroup`| `String`   | Label prefix for each subtotal row          |
| `endLabel`       | `String`   | Text appended to closing subtotal row       |
| `sumForGroup`    | `String[]` | Field names that define grouping levels     |

#### `@ExcelSubtotal`

Applied to individual fields to define how they are aggregated.

| Attribute                  | Type                    | Description                        |
|----------------------------|-------------------------|------------------------------------|
| `dataConsolidateFunction`  | `DataConsolidateFunction`| Aggregation function (SUM, AVG…)   |
| `excelCellLayout`          | `ExcelCellLayout`       | Style for the subtotal cell        |

```java
@ExcelSubtotals(labelTotalGroup = "Total", endLabel = "total", sumForGroup = {"city","state"})
public class SalaryRow implements RowSheet {

    @ExcelColumn(name = "State", index = 0)
    private String state;

    @ExcelColumn(name = "City", index = 0.1)
    private String city;

    @ExcelColumn(name = "Amount", index = 1)
    @ExcelSubtotal(
        dataConsolidateFunction = DataConsolidateFunction.SUM,
        excelCellLayout = @ExcelCellLayout(font = @ExcelFont(bold = true))
    )
    private Double amount;
}
```

---

### Conditional Formatting

#### `@ExcelConditionCellLayouts` / `@ExcelConditionCellLayout`

Apply a cell style when a formula condition evaluates to true.

| Attribute          | Type              | Description                                          |
|--------------------|-------------------|------------------------------------------------------|
| `columns`          | `String[]`        | Column names to apply the conditional formatting to  |
| `condition`        | `String`          | Excel formula (can reference fields with `${field}`) |
| `excelCellLayout`  | `ExcelCellLayout` | Style applied when the condition is true             |

```java
@ExcelConditionCellLayouts(
    @ExcelConditionCellLayout(
        columns = {"state", "city", "district"},
        condition = "ISNUMBER(SEARCH(\"total\", LOWER(${state[start]})))",
        excelCellLayout = @ExcelCellLayout(
            rgbFont = @ExcelRgbColor(red = (byte)255, blue = 0, green = 0),
            rgbForeground = @ExcelRgbColor(blue = (byte)255, red = 0, green = 0)
        )
    )
)
public class SalaryRow implements RowSheet { ... }
```

---

### Charts

#### `@ExcelCharts` / `@ExcelChart`

Embeds one or more charts in a sheet.

| Attribute (ExcelChart)    | Type                   | Default       | Description                       |
|---------------------------|------------------------|---------------|-----------------------------------|
| `id`                      | `String`               | —             | Unique chart identifier           |
| `excelChartCategories`    | `ExcelChartCategory[]` | —             | Data series definitions           |
| `xAxis`                   | `String`               | —             | X-axis cell range reference       |
| `chartTypes`              | `ChartTypes`           | `LINE`        | Chart type (LINE, BAR, PIE…)      |
| `sizeRow`                 | `int`                  | —             | Chart height in rows              |
| `sizeColumn`              | `int`                  | —             | Chart width in columns            |
| `group`                   | `boolean`              | `false`       | Group series by category field    |
| `title`                   | `String`               | `""`          | Chart title                       |
| `legendPosition`          | `LegendPosition`       | `BOTTOM`      | Legend position                   |

#### `@ExcelChartCategory`

| Attribute    | Type     | Description                                       |
|--------------|----------|---------------------------------------------------|
| `fieldName`  | `String` | Field/expression for the series label             |
| `function`   | `String` | Cell range expression for the series data         |

```java
@ExcelCharts(excelCharts = {
    @ExcelChart(
        id = "births",
        excelChartCategories = @ExcelChartCategory(
            fieldName = "${desNazione}",
            function  = "${numNascite[start]}:${numNascite[end]}"
        ),
        xAxis = "${anno[start]}:${anno[end]}",
        sizeColumn = 10, sizeRow = 20,
        group = true
    )
})
public class CensimentoSheet extends QuerySheetData<CensimentoRow> { ... }
```

---

### Pivot Tables

#### `@ExcelPivot`

Creates a pivot table from the sheet data (XSSF only).

Related: `@ExcelPivotRow`, `@ExcelPivotColumn`, `@ExcelPivotColumnFunction`, `@ExcelPivotFilter`.

---

### Super Headers

#### `@ExcelSuperHeaders` / `@ExcelSuperHeader` / `@ExcelSuperHeaderCell`

Add a merged header row spanning multiple columns above the regular header.

---

### Images

#### `@ExcelImage`

Embeds an image in a cell. Applicable to `byte[]` (raw image data) or `String` (file path) fields.

| Attribute      | Type     | Default | Description                   |
|----------------|----------|---------|-------------------------------|
| `resizeHeight` | `double` | `1.0`   | Resize factor for height      |
| `resizeWidth`  | `double` | `1.0`   | Resize factor for width       |

```java
@ExcelColumn(name = "Photo", index = 4)
@ExcelImage
private byte[] image;

@ExcelColumn(name = "Path", index = 5)
@ExcelImage(resizeHeight = 0.7, resizeWidth = 0.6)
private String path;
```

---

### Labels and Summary

#### `@ExcelLabel`

Adds a free-text label cell above the sheet data area.

#### `@ExcelSummary`

Configures a summary row appended after the last data row.

---

### Functions and Formulas

#### `@ExcelFunction` / `@ExcelFunctionRow` / `@ExcelFunctionRows`

Add formula rows to the sheet.

#### `@ExcelFunctionSubTotal` / `@ExcelFormulaAlias`

Define subtotal formulas and formula aliases.

---

### Data Validation

#### `@ExcelDataValidation` / `@ExcelDropDown`

Add dropdown list validation to a column.

---

## Quick Start Example

### Static Data — SalarySheet

```java
// Row class
@ExcelSubtotals(labelTotalGroup = "Total", endLabel = "total",
                sumForGroup = {"city", "state"})
@ExcelConditionCellLayouts(
    @ExcelConditionCellLayout(
        columns = {"state", "city", "district"},
        condition = "ISNUMBER(SEARCH(\"total\", LOWER(${state[start]})))",
        excelCellLayout = @ExcelCellLayout(
            rgbFont      = @ExcelRgbColor(red = (byte) 255, blue = 0, green = 0),
            rgbForeground = @ExcelRgbColor(blue = (byte) 255, red = 0, green = 0)
        )
    )
)
public class SalaryRow implements RowSheet {

    @ExcelColumn(name = "State", index = 0)
    @ExcelCellLayout
    private String state;

    @ExcelColumn(name = "City", index = 0.1)
    @ExcelCellLayout
    private String city;

    @ExcelColumn(name = "District", index = 0.2)
    @ExcelCellLayout
    private String district;

    @ExcelColumn(name = "Amount", index = 1)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    @ExcelSubtotal(
        dataConsolidateFunction = DataConsolidateFunction.SUM,
        excelCellLayout = @ExcelCellLayout(
            horizontalAlignment = HorizontalAlignment.RIGHT,
            font = @ExcelFont(bold = true)
        )
    )
    private Double amount;

    // constructor, getters, setters...
}

// Sheet class
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
public class SalarySheet extends SheetData<SalaryRow> {
    public SalarySheet(String sheetName) { super(sheetName); }
}

// Test / usage
@SpringBootTest
@EnableExcelGenerator
public class SalaryTest {

    @Autowired
    private GenerateExcel generateExcel;

    @Test
    public void testSalary() throws Exception {
        SalarySheet sheet = new SalarySheet("salary");
        sheet.addRows(new SalaryRow("Italy", "Rome",       "A", 32.0));
        sheet.addRows(new SalaryRow("Italy", "Rome",       "B", 25.5));
        sheet.addRows(new SalaryRow("Italy", "Milan",      "A", 21.0));
        sheet.addRows(new SalaryRow("England", "London",   "A", 31.0));
        sheet.addRows(new SalaryRow("England", "Manchester","A",  8.0));

        ReportExcel report = new ReportExcel("test", List.of(sheet));
        byte[] bytes = generateExcel.createFileXlsx(report);
        SpreadsheetUtils.writeToFile("/mnt/report/", report.getTitle(), ".xlsx", bytes);
    }
}
```

### Query-Based Data — UtenteSheet (JPA)

```java
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
@ExcelQuery("SELECT id_utente, nome, cognome, data_nascita, image, path, abilitato " +
            "FROM utente WHERE cognome IN (:cognome) ORDER BY cognome, nome")
public class UtenteSheet extends QuerySheetData<UtenteRow> {
    public UtenteSheet(String sheetName) { super(sheetName); }
}

// Usage
UtenteSheet sheet = new UtenteSheet("Users");
sheet.getMapParameters().put("cognome", Arrays.asList("Rossi", "Bianchi"));

ReportExcel report = new ReportExcel("report", List.of(sheet));
byte[] bytes = generateExcel.createFileXlsx(report);
```

### Chart Sheet

```java
@ExcelSheetLayout(notMerge = false)
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, right = 1.5, left = 1.5)
@ExcelQuery("SELECT n.des_nazione, c.anno, c.num_nascite, c.num_decessi " +
            "FROM nazione n INNER JOIN censimento c ON n.id_nazione = c.id_nazione " +
            "ORDER BY n.des_nazione, c.anno")
@ExcelCharts(excelCharts = {
    @ExcelChart(
        id = "nascite",
        excelChartCategories = @ExcelChartCategory(
            fieldName = "${desNazione}",
            function  = "${numNascite[start]}:${numNascite[end]}"
        ),
        sizeColumn = 10, sizeRow = 20,
        xAxis = "${anno[start]}:${anno[end]}",
        group = true
    )
})
public class CensimentoSheet extends QuerySheetData<CensimentoRow> {
    public CensimentoSheet(String sheetName) { super(sheetName); }
}
```

---

## Spring Properties

| Property                              | Description                                              |
|---------------------------------------|----------------------------------------------------------|
| `com.bld.commons.check.annotation`    | Enable annotation validation at startup (`true`/`false`) |
| `com.bld.commons.date.format`         | Global date format (used with `ColumnDateFormat.PARAMETER`) |
| `com.bld.commons.multiple.datasource` | Enable multiple datasource support (`true`/`false`)      |

```yaml
com.bld.commons:
  check.annotation: true
  date.format: dd/MM/yyyy
  multiple.datasource: false
```

---

## Format Support

| Feature                  | XLS (HSSF) | XLSX (XSSF) | SXSSF (Big Data) |
|--------------------------|:----------:|:-----------:|:----------------:|
| Static data              | yes        | yes         | yes              |
| JPA query data           | yes        | yes         | yes              |
| Subtotals                | yes        | yes         | no               |
| Charts                   | no         | yes         | no               |
| Pivot tables             | no         | yes         | no               |
| Conditional formatting   | yes        | yes         | no               |
| Merge rows               | yes        | yes         | no               |
| Images                   | yes        | yes         | no               |
| Super headers            | yes        | yes         | no               |
