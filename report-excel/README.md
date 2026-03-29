# report-excel

Integration test module for `generator-excel` and `read-excel`.
Demonstrates static data generation, Excel reading, CSV reading, and REST-based reading — without any database dependency.

> This module is **not published** to Maven Central. It exists only to test and demonstrate library features.

---

## Dependencies

```xml
<dependency>
    <groupId>com.github.bld-commons</groupId>
    <artifactId>generator-excel</artifactId>
    <version>5.1.3</version>
</dependency>
<dependency>
    <groupId>com.github.bld-commons</groupId>
    <artifactId>read-excel</artifactId>
    <version>5.1.3</version>
</dependency>
```

---

## Spring Boot Configuration

```yaml
# application.yml
com:
  bld:
    commons:
      check.annotation: true
      date.format: dd/MM/yyyy
      resource.cover.path: classpath:/excel/Copertina.xlsx
      report.excel:
        title: A12     # cell address for report title
        date: A18      # cell address for report date

# Column name overrides via properties (used with ${...} in @ExcelColumn)
autore-libri-row:
  nome.name-column: Nome
  matricola.name-column: Matricola
  cognome.name-column: Cognome

server:
  port: 8080
```

---

## Test Classes

### `SalaryTest` — Static data with subtotals and conditional formatting

Tests the generation of an XLSX file with grouped subtotals and conditional row coloring.

**Entities:**

```java
@ExcelSubtotals(
    labelTotalGroup = "Total",
    endLabel = "total",
    sumForGroup = {"city", "state"}
)
@ExcelConditionCellLayouts(
    @ExcelConditionCellLayout(
        columns = {"state", "city", "district"},
        condition = "ISNUMBER(SEARCH(\"total\", LOWER(${state[start]})))",
        excelCellLayout = @ExcelCellLayout(
            rgbFont       = @ExcelRgbColor(red = (byte)255, blue = 0, green = 0),
            rgbForeground = @ExcelRgbColor(blue = (byte)255, red = 0, green = 0)
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
}

@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
public class SalarySheet extends SheetData<SalaryRow> {
    public SalarySheet(String sheetName) { super(sheetName); }
}
```

**Test:**

```java
@SpringBootTest
@EnableExcelGenerator
@EnableExcelRead
public class SalaryTest {

    @Autowired
    private GenerateExcel generateExcel;

    @Test
    public void testSalary() throws Exception {
        SalarySheet salarySheet = new SalarySheet("salary");
        // Italy - Rome
        salarySheet.addRows(new SalaryRow("Italy", "Rome", "A", 32.0));
        salarySheet.addRows(new SalaryRow("Italy", "Rome", "B", 25.5));
        salarySheet.addRows(new SalaryRow("Italy", "Rome", "C", 12.0));
        salarySheet.addRows(new SalaryRow("Italy", "Rome", "D", 25.0));
        // Italy - Milan
        salarySheet.addRows(new SalaryRow("Italy", "Milan", "A", 21.0));
        salarySheet.addRows(new SalaryRow("Italy", "Milan", "B", 12.0));
        // England - London
        salarySheet.addRows(new SalaryRow("England", "London", "A", 31.0));
        salarySheet.addRows(new SalaryRow("England", "London", "B", 21.0));
        salarySheet.addRows(new SalaryRow("England", "London", "E", 55.0));
        // England - Manchester
        salarySheet.addRows(new SalaryRow("England", "Manchester", "A",  8.0));
        salarySheet.addRows(new SalaryRow("England", "Manchester", "C",  7.0));

        ReportExcel report = new ReportExcel("test", List.of(salarySheet));
        byte[] bytes = generateExcel.createFileXlsx(report);
        SpreadsheetUtils.writeToFile("/mnt/report/", report.getTitle(), ".xlsx", bytes);
    }
}
```

**What the output contains:**

- Subtotal rows automatically inserted for each `city` and `state` group
- The `Amount` column summed with SUM function
- Subtotal rows highlighted in red (text + background) via `@ExcelConditionCellLayouts`

---

### `ReportTest` — Advanced static generation

Tests a variety of features: multi-sheet workbook, images, labels, formulas, charts (via raw Apache POI), dynamic column configuration, and cover sheet.

Key sheets used:
- `CasaEditrice` — custom label sheet with image
- `AutoreLibriSheet` — author/book data with merge rows, function totals, super headers
- `GenereSheet` — book genres
- `TotaleAutoreLibriSheet` — summary function sheet
- `SituazioneUfficiSheet` — office situation data
- `DateSheet` — date/calendar fields

---

### `ReadReportTest` — Excel and CSV reading

#### `testRead()` — Read multiple sheets from XLSX

```java
@Test
public void testRead() throws Exception {
    byte[] report = IOUtils.toByteArray(new FileInputStream("/mnt/report/Mondadori-JPA.xlsx"));

    ExcelRead excelRead = new ExcelRead();
    excelRead.setReportExcel(report);
    excelRead.setExcelType(ExcelType.XLSX);
    excelRead.addSheetConvertion(ReadAutoreLibriSheet.class, "Libri d'autore");
    excelRead.addSheetConvertion(ReadGenereSheet.class, "Genere");
    excelRead = readExcel.convertExcelToEntity(excelRead);

    ReadAutoreLibriSheet sheet = excelRead.getSheet(ReadAutoreLibriSheet.class, "Libri d'autore");
    for (ReadAutoreLibriRow row : sheet.getListRowSheet())
        System.out.println(row);
}
```

#### `testDataMeteo()` — Read with custom row filtering

Reads a meteorological Excel file and discards rows from the current year via a custom `filtered()` override:

```java
@ExcelReadSheet
public class DataMeteoSheet extends SheetRead<DataMeteoRow> {

    private final int currentYear;

    public DataMeteoSheet(String sheetName) {
        super(sheetName);
        this.currentYear = Calendar.getInstance().get(Calendar.YEAR);
    }

    @Override
    protected boolean filtered(DataMeteoRow t) {
        return t.getYear() < this.currentYear;
    }
}

public class DataMeteoRow implements RowSheetRead {
    @ExcelReadColumn(value = "YY",       ignoreCellTypeString = true) private Integer year;
    @ExcelReadColumn(value = "MM",       ignoreCellTypeString = true) private Integer month;
    @ExcelReadColumn(value = "DD",       ignoreCellTypeString = true) private Integer day;
    @ExcelReadColumn(value = "Tn",       ignoreCellTypeString = true) private Double  temperaturaMax;
    @ExcelReadColumn(value = "Tx",       ignoreCellTypeString = true) private Double  temperaturaMin;
    @ExcelReadColumn(value = "WD",       ignoreCellTypeString = true) private Double  direzioneVento;
    @ExcelReadColumn(value = "WS",       ignoreCellTypeString = true) private Double  velocitaVento;
    @ExcelReadColumn(value = "RHn",      ignoreCellTypeString = true) private Double  umiditaMax;
    @ExcelReadColumn(value = "RHx",      ignoreCellTypeString = true) private Double  umiditaMin;
    @ExcelReadColumn(value = "RR0-12",   ignoreCellTypeString = true) private Double  precipitazioneAm;
    @ExcelReadColumn(value = "RR12-24",  ignoreCellTypeString = true) private Double  precipitazionePm;
    @ExcelReadColumn(value = "SD",       ignoreCellTypeString = true) private Double  mantoNevoso;
    @ExcelReadColumn(value = "SunRAD",   ignoreCellTypeString = true) private Double  radiazioneSolare;
    @ExcelReadColumn(value = "SunDUR",   ignoreCellTypeString = true) private Double  durataSoleggiamento;
}
```

```java
@Test
public void testDataMeteo() throws Exception {
    ExcelRead excelRead = new ExcelRead();
    excelRead.setExcelType(ExcelType.XLSX);
    excelRead.setReportExcel("/mnt/report/test_data_meteo.xlsx");
    excelRead.addSheetConvertion(DataMeteoSheet.class, "sheet");
    excelRead = readExcel.convertExcelToEntity(excelRead);

    DataMeteoSheet sheet = excelRead.getSheet(DataMeteoSheet.class, "sheet");
    logger.info("Rows read (past years only): " + sheet.size());
}
```

#### `testReadCsv()` — Read CSV with Bean Validation

```java
@CsvSettings(delimiter = ',', skipHeaderRecord = true)
public class RendicontazioneMassivaImportColumn implements RowSheetRead {
    @NotBlank  private String  codice;
    @NotNull   private Integer importo;
    // ...
}

@Test
public void testReadCsv() throws Exception {
    byte[] csv = IOUtils.toByteArray(new FileInputStream("/mnt/report/Test.csv"));
    CsvRead<RendicontazioneMassivaImportColumn> csvRead = new CsvRead<>();
    csvRead.setCsv(csv);
    readCsv.convertCsvToEntity(csvRead, RendicontazioneMassivaImportColumn.class);
    logger.info("Rows: " + csvRead.getListRowSheet().size());
}
```

---

## REST Controller

The module also exposes REST endpoints for reading Excel files via HTTP:

| Method | Endpoint                | Description                                    |
|--------|-------------------------|------------------------------------------------|
| POST   | `/excel/sheet-read`     | Read sheets by class names (JSON body)         |
| POST   | `/excel/excel-read`     | Read using `@JsonSheet` annotation-driven config |

**Request body (`ExcelModel`):**

```json
{
  "name": "test excel",
  "excel": "data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,..."
}
```

The Excel file is passed as a Base64-encoded data URI.

---

## Project Structure

```
report-excel/
├── src/
│   ├── main/java/bld/report/
│   │   ├── ReportExcelApplication.java
│   │   └── controller/
│   │       ├── ExcelController.java
│   │       └── entity/   (ReadAutoreLibriSheet, ReadGenereSheet, ...)
│   └── test/java/bld/report/
│       ├── junit/
│       │   ├── SalaryTest.java
│       │   ├── ReportTest.java
│       │   └── ReadReportTest.java
│       └── generator/junit/entity/  (SalaryRow, SalarySheet, AutoreLibriSheet, ...)
│       └── read/junit/entity/       (DataMeteoRow, DataMeteoSheet, RendicontazioneMassivaImportColumn, ...)
└── src/test/resources/
    └── application.yml
```
