# generator-excel

Spring Boot library for generating Excel (XLS, XLSX, SXSSF) and CSV files from annotated Java objects.
Supports static data, JPA query-driven data, charts, pivot tables, images, subtotals, conditional formatting, and more.

---

## Why generator-excel?

The goal: generate this Excel sheet.

| ID | Name | Department | Salary | Start Date |
|----|------|------------|-------:|------------|
| 1  | Alice Rossi | Engineering | 4,500.00 | 2021-03-15 |
| 2  | Bob Verdi | Marketing | 3,800.00 | 2022-07-01 |

Headers in bold, salary right-aligned with 2 decimal places, date formatted as `yyyy-MM-dd`.

---

### With Apache POI — ~70 lines of boilerplate

```java
try (XSSFWorkbook workbook = new XSSFWorkbook()) {
    XSSFSheet sheet = workbook.createSheet("Employees");

    // Header style
    CellStyle headerStyle = workbook.createCellStyle();
    Font headerFont = workbook.createFont();
    headerFont.setBold(true);
    headerStyle.setFont(headerFont);
    headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    // Header row
    Row header = sheet.createRow(0);
    String[] columns = {"ID", "Name", "Department", "Salary", "Start Date"};
    for (int i = 0; i < columns.length; i++) {
        Cell cell = header.createCell(i);
        cell.setCellValue(columns[i]);
        cell.setCellStyle(headerStyle);
    }

    // Number style (salary)
    CellStyle numberStyle = workbook.createCellStyle();
    DataFormat format = workbook.createDataFormat();
    numberStyle.setDataFormat(format.getFormat("#,##0.00"));
    numberStyle.setAlignment(HorizontalAlignment.RIGHT);

    // Date style
    CellStyle dateStyle = workbook.createCellStyle();
    CreationHelper createHelper = workbook.getCreationHelper();
    dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

    // Data rows
    Object[][] data = {
        {1, "Alice Rossi", "Engineering", 4500.00, new GregorianCalendar(2021, 2, 15)},
        {2, "Bob Verdi",   "Marketing",   3800.00, new GregorianCalendar(2022, 6,  1)}
    };
    for (int r = 0; r < data.length; r++) {
        Row row = sheet.createRow(r + 1);
        row.createCell(0).setCellValue((Integer) data[r][0]);
        row.createCell(1).setCellValue((String)  data[r][1]);
        row.createCell(2).setCellValue((String)  data[r][2]);
        Cell salaryCell = row.createCell(3);
        salaryCell.setCellValue((Double) data[r][3]);
        salaryCell.setCellStyle(numberStyle);
        Cell dateCell = row.createCell(4);
        dateCell.setCellValue((Calendar) data[r][4]);
        dateCell.setCellStyle(dateStyle);
    }

    // Auto-size columns
    for (int i = 0; i < columns.length; i++) sheet.autoSizeColumn(i);

    // Write to file
    try (FileOutputStream fos = new FileOutputStream("employees.xlsx")) {
        workbook.write(fos);
    }
}
```

---

### With generator-excel — ~20 lines, zero boilerplate

**1. Row entity**

```java
public class EmployeeRow implements RowSheet {

    @ExcelColumn(name = "ID", index = 1)
    private Integer id;

    @ExcelColumn(name = "Name", index = 2)
    @ExcelCellLayout(autoSizeColumn = true)
    private String name;

    @ExcelColumn(name = "Department", index = 3)
    @ExcelCellLayout(autoSizeColumn = true)
    private String department;

    @ExcelColumn(name = "Salary", index = 4)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
    private Double salary;

    @ExcelColumn(name = "Start Date", index = 5)
    @ExcelDate(ColumnDateFormat.YYYY_MM_DD)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
    private Calendar startDate;

    // getters / setters ...
}
```

**2. Sheet entity**

```java
@ExcelSheetLayout
@ExcelHeaderLayout
public class EmployeeSheet extends SheetData<EmployeeRow> {
    public EmployeeSheet(String name) { super(name); }
}
```

**3. Generate**

```java
@Autowired
private GenerateExcel generateExcel;

public byte[] export(List<EmployeeRow> employees) throws Exception {
    EmployeeSheet sheet = new EmployeeSheet("Employees");
    sheet.setListRowSheet(employees);
    ReportExcel report = new ReportExcel("Company Report", sheet);
    return generateExcel.createFileXlsx(report);
}
```

| | Apache POI | generator-excel |
|---|---|---|
| Lines of code | ~70 | ~20 |
| Style management | Manual (`CellStyle`, `Font`, …) | Annotations |
| Date formatting | Manual (`DataFormat`) | `@ExcelDate` |
| Adding a column | Edit style + row creation code | Add a field + `@ExcelColumn` |
| Maintenance | High | Low |

---

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

#### `@ExcelColumnWidth`

Sets a fixed column width (in centimetres) on a `RowSheet` field. When omitted, the default width of **5 cm** is used.

| Attribute | Type  | Default | Description              |
|-----------|-------|---------|--------------------------|
| `width`   | `int` | `5`     | Column width in centimetres |

```java
public class BookRow implements RowSheet {

    @ExcelColumn(name = "Title", index = 1)
    @ExcelCellLayout(autoSizeColumn = true)
    private String title;

    @ExcelColumn(name = "Price", index = 2)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
    @ExcelColumnWidth(width = 10)   // fixed width of 10 cm
    private Double price;

    // getters / setters ...
}
```

The annotation can also appear inside `@ExcelFunctionRow` or `@ExcelFunctionMergeRow` to control the width of formula columns:

```java
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn      = @ExcelColumn(index = 3, name = "Total Price"),
        excelFunction    = @ExcelFunction(function = "sum(${price},${supplement})", nameFunction = "totalPrice"),
        excelColumnWidth = @ExcelColumnWidth(width = 7)
    )
})
public class BookRow implements RowSheet { ... }
```

#### `@ExcelRowHeight`

Sets a fixed row height (in centimetres). Can be placed at **class level** on a `RowSheet` (applies to every data row) or at **field level** inside a `SheetSummary` class (applies to the summary row that field belongs to). The default value `-1` lets Excel auto-size the height.

| Attribute | Type    | Default | Description                            |
|-----------|---------|---------|----------------------------------------|
| `height`  | `short` | `-1`    | Row height in centimetres; `-1` = auto |

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

Formula rows are added below the data area by annotating the `RowSheet` class with `@ExcelFunctionRows`.
Each formula row is configured either with `@ExcelFunctionRow` (simple row) or `@ExcelFunctionMergeRow` (merged cells + formula).
The actual Excel formula is defined inside `@ExcelFunction`.

---

#### `@ExcelFunctionRows`

Container annotation applied at **class level** on a `RowSheet`. Holds two independent lists:

| Attribute              | Type                    | Default | Description                                   |
|------------------------|-------------------------|---------|-----------------------------------------------|
| `excelFunctions`       | `ExcelFunctionRow[]`    | `{}`    | Simple formula rows appended below the data   |
| `excelFunctionMerges`  | `ExcelFunctionMergeRow[]`| `{}`   | Formula rows whose cells span merged groups   |

Both lists are processed independently and can coexist on the same class.

---

#### `@ExcelFunctionRow`

Configures a single formula row. Is a parameter of `@ExcelFunctionRows.excelFunctions`.

| Attribute               | Type                   | Default                                      | Description                                   |
|-------------------------|------------------------|----------------------------------------------|-----------------------------------------------|
| `excelColumn`           | `ExcelColumn`          | —                                            | **Required.** Column header and position      |
| `excelFunction`         | `ExcelFunction`        | —                                            | **Required.** Formula definition              |
| `excelCellsLayout`      | `ExcelCellLayout`      | right-aligned, precision 2                   | Style for the result cell                     |
| `excelColumnWidth`      | `ExcelColumnWidth`     | default width                                | Column width                                  |
| `excelHeaderCellLayout` | `ExcelHeaderCellLayout`| default header style                         | Style for the header cell of this column      |
| `excelSubtotal`         | `ExcelSubtotal`        | `enable=false`, function=SUM                 | Subtotal for this formula column (disabled by default) |
| `excelNumberFormat`     | `ExcelNumberFormat`    | `""` (inherits sheet default)                | Number format applied to the result cell      |

---

#### `@ExcelFunctionMergeRow`

Like `@ExcelFunctionRow`, but merges consecutive cells in the column that share the same value in a reference field, then applies the formula over each merged group. Is a parameter of `@ExcelFunctionRows.excelFunctionMerges`.

| Attribute               | Type                   | Default                        | Description                                              |
|-------------------------|------------------------|--------------------------------|----------------------------------------------------------|
| `excelColumn`           | `ExcelColumn`          | —                              | **Required.** Column header and position                 |
| `excelFunction`         | `ExcelFunction`        | —                              | **Required.** Formula definition                         |
| `excelMergeRow`         | `ExcelMergeRow`        | —                              | **Required.** Field(s) that determine merge boundaries   |
| `excelCellsLayout`      | `ExcelCellLayout`      | right-aligned, precision 2     | Style for the merged result cell                         |
| `excelColumnWidth`      | `ExcelColumnWidth`     | default width                  | Column width                                             |
| `excelHeaderCellLayout` | `ExcelHeaderCellLayout`| default header style           | Style for the header cell of this column                 |
| `excelSubtotal`         | `ExcelSubtotal`        | `enable=false`, function=SUM   | Subtotal for this merged formula column                  |
| `excelNumberFormat`     | `ExcelNumberFormat`    | `""` (inherits sheet default)  | Number format applied to each merged result cell         |

---

#### `@ExcelFunction`

Defines the Excel formula and its identifier. Used inside `@ExcelFunctionRow` and `@ExcelFunctionMergeRow`.

| Attribute        | Type                    | Default               | Description                                                              |
|------------------|-------------------------|-----------------------|--------------------------------------------------------------------------|
| `function`       | `String`                | —                     | **Required.** Excel formula with `${fieldName}` placeholders             |
| `nameFunction`   | `String`                | —                     | **Required.** Unique name used to reference this formula's result cells  |
| `anotherTable`   | `boolean`               | `true`                | `true` if the formula references columns from another sheet/table        |
| `alias`          | `ExcelFormulaAlias[]`   | `{}`                  | Named cell-range aliases used inside the formula                         |
| `onSubTotalRow`  | `ExcelFunctionSubTotal` | `value=false`         | Configuration for a grand-total row appended below all data rows         |

##### Formula placeholder syntax

The `function` string supports the following placeholders that are resolved to actual Excel cell addresses at generation time:

| Placeholder                              | Resolves to                                               | Use case                                 |
|------------------------------------------|-----------------------------------------------------------|------------------------------------------|
| `${fieldName}`                           | The cell in the current row for that field                | Row-level formula (sum across columns)   |
| `${fieldNameRowStart}:${fieldNameRowEnd}`| First to last cell of that column in the data range       | Column aggregate (sum, average, …)       |
| `${fieldName[start]}:${fieldName[end]}`  | Same as above (alternative syntax)                        | Column aggregate                         |
| `${fieldName[start+N]}:${fieldName[end-M]}`| Column range with row offset                            | Exclude header/footer rows from range    |
| `${fieldName.field-value[start]}`        | The first cell value of that column                       | Reference the first cell as a constant   |
| `${SheetName.fieldRowStart}:${SheetName.fieldRowEnd}` | Cross-sheet column range                    | Formula referencing another sheet        |

When `anotherTable=false` the formula acts on the same table; ranges like `${fieldRowStart}:${fieldRowEnd}` are resolved relative to the current sheet's data block.

---

#### `@ExcelFunctionSubTotal`

Controls whether a **grand-total row** is appended at the very bottom of a formula column (below all data rows). Configured via the `onSubTotalRow` attribute of `@ExcelFunction`.

| Attribute        | Type             | Default                             | Description                                |
|------------------|------------------|-------------------------------------|--------------------------------------------|
| `value`          | `boolean`        | `false`                             | `true` to append the grand-total row       |
| `excelCellLayout`| `ExcelCellLayout`| right-aligned, bold font            | Style applied to the grand-total cell      |

---

#### `@ExcelFormulaAlias`

Defines a **named alias** for a cell or cell range, so that the alias can be used as a placeholder in formula strings. Useful for cross-sheet references or when the natural field-name syntax is insufficient.

| Attribute     | Type      | Default | Description                                                                 |
|---------------|-----------|---------|-----------------------------------------------------------------------------|
| `alias`       | `String`  | —       | **Required.** The placeholder name used in `${alias}` expressions           |
| `coordinate`  | `String`  | —       | **Required.** Target coordinate — field name with optional `[start]`/`[end]` or `Genere.genere[start]` cross-sheet syntax |
| `sheet`       | `String`  | `""`    | Sheet name for cross-sheet references (leave empty for the current sheet)   |
| `blockColumn` | `boolean` | `false` | Produce an absolute column reference (`$A1`)                               |
| `blockRow`    | `boolean` | `false` | Produce an absolute row reference (`A$1`)                                   |

---

#### Examples — Functions and Formulas

**1. Row formula (sum of two columns on the same row)**

```java
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn = @ExcelColumn(index = 9, name = "Total Price"),
        excelFunction = @ExcelFunction(
            function    = "sum(${price},${supplement})",
            nameFunction = "totalPrice"
        ),
        excelCellsLayout = @ExcelCellLayout(
            horizontalAlignment = HorizontalAlignment.RIGHT,
            precision = 2,
            locked = true
        ),
        excelColumnWidth = @ExcelColumnWidth(width = 7)
    )
})
public class BookRow implements RowSheet { ... }
```

**2. Column aggregate (sum all rows of a column)**

```java
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn  = @ExcelColumn(index = 8, name = "Total Price"),
        excelFunction = @ExcelFunction(
            function     = "sum(${priceRowStart}:${priceRowEnd})",
            nameFunction = "totalPrice"
        )
    )
})
public class TotalsRow implements RowSheet { ... }
```

**3. Conditional aggregate (SUMIF)**

```java
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn  = @ExcelColumn(index = 2, name = "Total by ID"),
        excelFunction = @ExcelFunction(
            function     = "sumif(${idRowStart}:${idRowEnd},${filterId},${priceRowStart}:${priceRowEnd})",
            nameFunction = "totalById"
        )
    )
})
public class TotalsRow implements RowSheet {
    @ExcelColumn(name = "ID", index = 1)
    private Integer filterId;
}
```

**4. Column aggregate with row offset**

```java
// Exclude the first and last rows of the data range
@ExcelFunction(
    function     = "sum(${price[start+1]}:${price[end-1]})",
    nameFunction = "trimmedTotal"
)
```

**5. Percentage formula with number format and grand-total row**

```java
@ExcelFunctionRows(
    excelFunctions = @ExcelFunctionRow(
        excelColumn  = @ExcelColumn(index = 3, name = "% Coverage"),
        excelFunction = @ExcelFunction(
            function     = "${presence}/${headcount}",
            nameFunction = "coverageRate",
            onSubTotalRow = @ExcelFunctionSubTotal(
                value = true,
                excelCellLayout = @ExcelCellLayout(
                    horizontalAlignment = HorizontalAlignment.RIGHT,
                    font = @ExcelFont(bold = true)
                )
            )
        ),
        excelNumberFormat = @ExcelNumberFormat("0.00%")
    )
)
public class StaffRow implements RowSheet { ... }
```

**6. Merged formula (subtotal per group)**

```java
@ExcelFunctionRows(
    excelFunctionMerges = {
        @ExcelFunctionMergeRow(
            excelColumn   = @ExcelColumn(index = 7.1, name = "Total per Author"),
            excelMergeRow = @ExcelMergeRow(referenceField = "authorId"),
            excelFunction = @ExcelFunction(
                function      = "sum(${priceRowStart}:${priceRowEnd})",
                nameFunction  = "totalPerAuthor",
                anotherTable  = false
            ),
            excelSubtotal = @ExcelSubtotal(
                enable = true,
                dataConsolidateFunction = DataConsolidateFunction.SUM,
                excelCellLayout = @ExcelCellLayout(
                    horizontalAlignment = HorizontalAlignment.RIGHT,
                    precision = 2,
                    font = @ExcelFont(bold = true)
                )
            ),
            excelCellsLayout = @ExcelCellLayout(
                horizontalAlignment = HorizontalAlignment.RIGHT,
                precision = 2
            )
        )
    }
)
public class BookRow implements RowSheet { ... }
```

**7. Multi-field merge boundaries**

The `referenceField` of `@ExcelMergeRow` inside `@ExcelFunctionMergeRow` accepts an array; cells are merged only when **all** listed fields are equal:

```java
@ExcelFunctionMergeRow(
    excelColumn   = @ExcelColumn(index = 7.3, name = "Total per Genre"),
    excelMergeRow = @ExcelMergeRow(referenceField = {"genre", "authorId"}),
    excelFunction = @ExcelFunction(
        function     = "sum(${priceRowStart}:${priceRowEnd})",
        nameFunction = "totalPerGenre",
        anotherTable = false
    )
)
```

**8. Cross-sheet reference via alias**

```java
@ExcelFunctionRow(
    excelColumn  = @ExcelColumn(index = 10, name = "Test Date"),
    excelFunction = @ExcelFunction(
        function     = "${TestSheet.dataA}",
        nameFunction = "testRef"
    )
)
```

---

### Data Validation

---

#### `@ExcelDataValidation`

Applies a **formula-based validation rule** to a field's column. If the formula evaluates to `FALSE` for the entered value, Excel shows an error dialog. Applied directly on the field.

| Attribute  | Type                  | Default                                         | Description                                              |
|------------|-----------------------|-------------------------------------------------|----------------------------------------------------------|
| `value`    | `String`              | —                                               | **Required.** Excel validation formula; use `${fieldName}` to reference the current cell |
| `alias`    | `ExcelFormulaAlias[]` | `{}`                                            | Aliases for cell references used inside the formula      |
| `errorBox` | `ExcelBoxMessage`     | STOP, title "Error", message "The value is not valid" | Error dialog shown when validation fails           |

`@ExcelBoxMessage` attributes:

| Attribute   | Type       | Default | Description                                    |
|-------------|------------|---------|------------------------------------------------|
| `show`      | `boolean`  | `true`  | Whether to display the error dialog            |
| `boxStyle`  | `BoxStyle` | —       | `STOP`, `WARNING`, or `INFORMATION`            |
| `title`     | `String`   | —       | Dialog title                                   |
| `message`   | `String`   | —       | Dialog message body                            |

```java
// Validate that the field contains a real date (not a number entered manually)
@ExcelColumn(name = "Birth Date", index = 4)
@ExcelDate(value = ColumnDateFormat.YYYY_MM_DD)
@ExcelDataValidation(
    "AND(ISNUMBER(${birthDate});${birthDate}=DATE(YEAR(${birthDate});MONTH(${birthDate});DAY(${birthDate})))"
)
private Calendar birthDate;
```

---

#### `@ExcelDropDown`

Creates a **dropdown list** in a cell by referencing a range from another sheet or table in the same workbook. Applied directly on the field.

| Attribute              | Type                  | Default                                         | Description                                                       |
|------------------------|-----------------------|-------------------------------------------------|-------------------------------------------------------------------|
| `areaRange`            | `String`              | —                                               | **Required.** Cell range formula (use `${alias}` placeholders)    |
| `suppressDropDownArrow`| `boolean`             | `true`                                          | `false` to show the dropdown arrow in the cell                    |
| `alias`                | `ExcelFormulaAlias[]` | `{}`                                            | Aliases that map placeholder names to actual cell range addresses |
| `errorBox`             | `ExcelBoxMessage`     | STOP, title "Error", message "The value is not valid" | Error dialog shown when an invalid value is entered         |

##### `areaRange` syntax

The `areaRange` value is an Excel cell-range expression. Placeholders of the form `${aliasName}` are resolved using the `alias` array.

Two reference styles are supported:

| Style             | Example                                         | Description                                       |
|-------------------|-------------------------------------------------|---------------------------------------------------|
| Alias-based       | `${genreStart}:${genreEnd}`                     | Aliases defined in `alias` resolve to cell addresses |
| Auto-resolved     | `${SheetName.fieldRowStart}:${SheetName.fieldRowEnd}` | Resolved automatically from another sheet's data range |

```java
// Style 1 — explicit aliases pointing to another sheet
@ExcelColumn(name = "Genre", index = 5)
@ExcelDropDown(
    areaRange = "${genreStart}:${genreEnd}",
    alias = {
        @ExcelFormulaAlias(alias = "genreStart", coordinate = "genre[start]", sheet = "Genre"),
        @ExcelFormulaAlias(alias = "genreEnd",   coordinate = "genre[end]",   sheet = "Genre")
    }
)
private String genre;

// Style 2 — auto-resolved cross-sheet range (no explicit aliases needed)
@ExcelColumn(name = "Genre", index = 5)
@ExcelDropDown(
    areaRange = "${Genre.genreRowStart}:${Genre.genreRowEnd}",
    suppressDropDownArrow = true
)
private String genre;
```

---

#### `IntegerDropDown` / `CharacterDropDown` — inline value list

For simple static lists that do not reference another sheet, use the wrapper types `IntegerDropDown` and `CharacterDropDown`. Declare the field with one of these types and initialise it with the list of allowed values:

```java
// Integer dropdown — values 0, 1, 2
@ExcelColumn(name = "Option", index = 8)
@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
private IntegerDropDown option;

// Character dropdown — values A, B, C
@ExcelColumn(name = "Option", index = 9)
@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
private CharacterDropDown optionChar;

// In the constructor
this.option    = new IntegerDropDown(null, Arrays.asList(0, 1, 2));
this.optionChar = new CharacterDropDown(null, Arrays.asList('A', 'B', 'C'));
```

No `@ExcelDropDown` annotation is needed — the type itself signals that a dropdown should be generated.

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
