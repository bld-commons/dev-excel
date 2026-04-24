# generator-excel

Spring Boot library for generating Excel (XLS, XLSX, SXSSF) and CSV files from annotated Java objects.  
Supports static data, JPA query-driven data, charts, pivot tables, images, subtotals, conditional formatting, super headers, and more.

---

## Why generator-excel?

The goal: generate this Excel sheet.

| ID | Name | Department | Salary | Start Date |
|----|------|------------|-------:|------------|
| 1  | Alice Rossi | Engineering | 4,500.00 | 2021-03-15 |
| 2  | Bob Verdi | Marketing | 3,800.00 | 2022-07-01 |

Headers in bold, salary right-aligned with 2 decimal places, date formatted as `yyyy-MM-dd`.

<details>
<summary>With Apache POI — ~70 lines of boilerplate</summary>

```java
try (XSSFWorkbook workbook = new XSSFWorkbook()) {
    XSSFSheet sheet = workbook.createSheet("Employees");

    CellStyle headerStyle = workbook.createCellStyle();
    Font headerFont = workbook.createFont();
    headerFont.setBold(true);
    headerStyle.setFont(headerFont);
    headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    Row header = sheet.createRow(0);
    String[] columns = {"ID", "Name", "Department", "Salary", "Start Date"};
    for (int i = 0; i < columns.length; i++) {
        Cell cell = header.createCell(i);
        cell.setCellValue(columns[i]);
        cell.setCellStyle(headerStyle);
    }

    CellStyle numberStyle = workbook.createCellStyle();
    DataFormat format = workbook.createDataFormat();
    numberStyle.setDataFormat(format.getFormat("#,##0.00"));
    numberStyle.setAlignment(HorizontalAlignment.RIGHT);

    CellStyle dateStyle = workbook.createCellStyle();
    CreationHelper createHelper = workbook.getCreationHelper();
    dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

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

    for (int i = 0; i < columns.length; i++) sheet.autoSizeColumn(i);

    try (FileOutputStream fos = new FileOutputStream("employees.xlsx")) {
        workbook.write(fos);
    }
}
```
</details>

**With generator-excel — ~20 lines, zero boilerplate**

```java
// 1. Row entity
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

// 2. Sheet entity
@ExcelSheetLayout
@ExcelHeaderLayout
public class EmployeeSheet extends SheetData<EmployeeRow> {
    public EmployeeSheet(String name) { super(name); }
}

// 3. Generate
@Autowired GenerateExcel generateExcel;

public byte[] export(List<EmployeeRow> employees) throws Exception {
    EmployeeSheet sheet = new EmployeeSheet("Employees");
    sheet.setListRowSheet(employees);
    return generateExcel.createFileXlsx(new ReportExcel("Company Report", sheet));
}
```

| | Apache POI | generator-excel |
|---|---|---|
| Lines of code | ~70 | ~20 |
| Style management | Manual (`CellStyle`, `Font`, …) | Annotations |
| Date formatting | Manual (`DataFormat`) | `@ExcelDate` |
| Adding a column | Edit style + row code | Add field + `@ExcelColumn` |
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

---

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

| Method | Description |
|--------|-------------|
| `createFileXls(ReportExcel)` | Generate `.xls` (HSSF) as `byte[]` |
| `createFileXls(ReportExcel, OutputStream)` | Write `.xls` to a stream |
| `createFileXlsx(ReportExcel)` | Generate `.xlsx` (XSSF) as `byte[]` |
| `createFileXlsx(ReportExcel, OutputStream)` | Write `.xlsx` to a stream |
| `createBigDataFileXlsx(ReportExcel)` | Generate `.xlsx` with SXSSF (streaming) as `byte[]` |
| `createBigDataFileXlsx(ReportExcel, OutputStream)` | Write SXSSF `.xlsx` to a stream |

### `GenerateCsv`

| Method | Description |
|--------|-------------|
| `generateCsv(CsvData<T>)` | Generate a CSV file as `byte[]` |

---

## Class Hierarchy

```
BaseSheet
├── SheetData<T extends RowSheet>                    implements SheetComponent
│   ├── QuerySheetData<T>
│   ├── SheetDynamicData<T extends DynamicRowSheet>  implements DynamicColumn
│   │   └── DynamicChart<T>
│   ├── LoadSheetData<R, S>
│   └── SheetFunctionTotal<T>
│       └── SheetDynamicFunctionTotal<T>             implements DynamicColumn
└── SheetSummary                                     implements SheetComponent

MergeSheet extends BaseSheet    (groups SheetComponent instances on one tab)

RowSheet  (interface)
└── DynamicRowSheet  (abstract class)

FunctionsTotal<T>  (interface — implemented by SheetData subclasses
                    that expose a companion total sheet)
```

---

## Domain Classes Reference

| Category | Classes | Details |
|----------|---------|---------|
| **Static Sheets** | `RowSheet`, `SheetData`, `SheetSummary`, `MergeSheet`, `FunctionsTotal`, `ExcelHyperlink`, `ExcelAttachment` | [→ docs/sheet-data.md](docs/sheet-data.md) |
| **Dynamic Sheets** | `DynamicRowSheet`, `SheetDynamicData`, `ExtraColumnAnnotation`, `DynamicChart`, `SheetDynamicFunctionTotal` | [→ docs/dynamic-sheets.md](docs/dynamic-sheets.md) |
| **Query, Load & Function Sheets** | `QuerySheetData`, `LoadSheetData`, `SheetFunctionTotal` | [→ docs/query-load-function.md](docs/query-load-function.md) |

Full hierarchy overview: [→ docs/domain-classes.md](docs/domain-classes.md)

---

## Annotations Reference

| Category | Annotations | Details |
|----------|-------------|---------|
| **Sheet Layout & Structure** | `@ExcelSheetLayout`, `@ExcelHeaderLayout`, `@ExcelHeaderCellLayout`, `@ExcelMarginSheet`, `@ExcelFreezePane`, `@ExcelAreaBorder`, `@ExcelLocked`, `@ExcelRowHeight` | [→ docs/sheet-layout.md](docs/sheet-layout.md) |
| **Columns & Cells** | `@ExcelColumn`, `@ExcelCellLayout`, `@ExcelFont`, `@ExcelBorder`, `@ExcelRgbColor`, `@ExcelColumnWidth`, `@ExcelNumberFormat`, `@ExcelMergeRow` | [→ docs/columns-cells.md](docs/columns-cells.md) |
| **Functions & Formulas** | `@ExcelFunctionRows`, `@ExcelFunctionRow`, `@ExcelFunctionMergeRow`, `@ExcelFunction`, `@ExcelFunctionSubTotal`, `@ExcelFormulaAlias` | [→ docs/functions-formulas.md](docs/functions-formulas.md) |
| **Subtotals** | `@ExcelSubtotals`, `@ExcelSubtotal` | [→ docs/subtotals.md](docs/subtotals.md) |
| **Conditional Formatting** | `@ExcelConditionCellLayouts`, `@ExcelConditionCellLayout` | [→ docs/conditional-formatting.md](docs/conditional-formatting.md) |
| **Charts** | `@ExcelCharts`, `@ExcelChart`, `@ExcelChartCategory`, `@ExcelChartDataLabel`, `@ExcelBarChartData` | [→ docs/charts.md](docs/charts.md) |
| **Pivot Tables** | `@ExcelPivot`, `@ExcelPivotRow`, `@ExcelPivotColumn`, `@ExcelPivotColumnFunction`, `@ExcelPivotFilter` | [→ docs/pivot-tables.md](docs/pivot-tables.md) |
| **Super Headers** | `@ExcelSuperHeaders`, `@ExcelSuperHeader`, `@ExcelSuperHeaderCell` | [→ docs/super-headers.md](docs/super-headers.md) |
| **Data Validation & Dropdowns** | `@ExcelDataValidation`, `@ExcelDropDown`, `@ExcelBoxMessage`, `IntegerDropDown`, `CharacterDropDown` | [→ docs/data-validation.md](docs/data-validation.md) |
| **Images, Labels & Summary** | `@ExcelImage`, `@ExcelLabel`, `@ExcelSummary` | [→ docs/images-labels-summary.md](docs/images-labels-summary.md) |
| **CSV** | `@CsvColumn`, `@CsvQuery` | [→ docs/csv.md](docs/csv.md) |
| **Advanced / Utilities** | `@ExcelClearRows`, `@ExcelSelectCell`, `@IgnoreCheck` | [→ docs/advanced.md](docs/advanced.md) |

---

## Quick Start — Static Data

```java
@ExcelSubtotals(labelTotalGroup = "Total", endLabel = "total", sumForGroup = {"city", "state"})
public class SalaryRow implements RowSheet {

    @ExcelColumn(name = "State",  index = 0) @ExcelCellLayout private String state;
    @ExcelColumn(name = "City",   index = 1) @ExcelCellLayout private String city;

    @ExcelColumn(name = "Amount", index = 2)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    @ExcelSubtotal(
        dataConsolidateFunction = DataConsolidateFunction.SUM,
        excelCellLayout = @ExcelCellLayout(font = @ExcelFont(bold = true))
    )
    private Double amount;
}

@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
public class SalarySheet extends SheetData<SalaryRow> {
    public SalarySheet(String name) { super(name); }
}

// Test
@SpringBootTest
@EnableExcelGenerator
public class SalaryTest {

    @Autowired GenerateExcel generateExcel;

    @Test
    public void test() throws Exception {
        SalarySheet sheet = new SalarySheet("Salary");
        sheet.addRows(new SalaryRow("Italy",   "Rome",   32.0));
        sheet.addRows(new SalaryRow("Italy",   "Milan",  21.0));
        sheet.addRows(new SalaryRow("England", "London", 31.0));

        byte[] bytes = generateExcel.createFileXlsx(new ReportExcel("report", List.of(sheet)));
        SpreadsheetUtils.writeToFile("/tmp/", "report", ".xlsx", bytes);
    }
}
```

## Quick Start — Query-Based Data (JPA)

```java
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
@ExcelQuery("SELECT id, name, surname, hire_date FROM employee WHERE dept = :dept ORDER BY surname")
public class EmployeeSheet extends QuerySheetData<EmployeeRow> {
    public EmployeeSheet(String name) { super(name); }
}

// Usage
EmployeeSheet sheet = new EmployeeSheet("Employees");
sheet.addParameters("dept", "Engineering");
byte[] bytes = generateExcel.createFileXlsx(new ReportExcel("report", List.of(sheet)));
```

---

## Spring Properties

| Property | Description |
|----------|-------------|
| `com.bld.commons.check.annotation` | Enable annotation validation at startup (`true`/`false`) |
| `com.bld.commons.date.format` | Global date format (used with `ColumnDateFormat.PARAMETER`) |
| `com.bld.commons.multiple.datasource` | Enable multiple datasource support (`true`/`false`) |

```yaml
com.bld.commons:
  check.annotation: true
  date.format: dd/MM/yyyy
  multiple.datasource: false
```

---

## Format Support

| Feature                | XLS (HSSF) | XLSX (XSSF) | SXSSF (Big Data) |
|------------------------|:----------:|:-----------:|:----------------:|
| Static data            | yes | yes | yes |
| JPA query data         | yes | yes | yes |
| Subtotals              | yes | yes | no  |
| Charts                 | no  | yes | no  |
| Pivot tables           | no  | yes | no  |
| Conditional formatting | yes | yes | no  |
| Merge rows             | yes | yes | no  |
| Images                 | yes | yes | no  |
| Super headers          | yes | yes | no  |
