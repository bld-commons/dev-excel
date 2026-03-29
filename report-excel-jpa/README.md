# report-excel-jpa

Integration test module for `generator-excel` with JPA/database integration.
Demonstrates query-driven Excel generation using `@ExcelQuery` with both native SQL and JPQL,
charts, pivot tables, super headers, and large dataset (big data) streaming.

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
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>
```

---

## Database Setup

The module requires a PostgreSQL database. The schema and test data are defined in `db-excel.sql` at the project root.

```yaml
# application.yml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/excel_db
    username: postgres
    password: Pa$$wd87!
  jpa:
    show-sql: true
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    generate-ddl: true
    properties.hibernate:
      default_schema: public
      ddl-auto: auto
      format_sql: true
```

---

## Spring Boot Configuration

```yaml
com:
  bld:
    commons:
      check.annotation: true
      date.format: dd/MM/yyyy
      resource.cover.path: classpath:/excel/Copertina.xlsx
      report.excel:
        title: A12
        date: A18

autore-libri-row:
  nome.name-column: Nome
  matricola.name-column: Matricola
  cognome.name-column: Cognome
```

---

## Test Class — `ReportTestJpa`

### `test()` — Multi-sheet workbook from JPA queries

Generates a multi-sheet XLSX file with data fetched from the database.

```java
@Test
public void test() throws Exception {
    List<BaseSheet> listBaseSheet = new ArrayList<>();

    // Sheet 1: Users filtered by surname (native SQL query)
    UtenteSheet utenteSheet = new UtenteSheet("Utente");
    utenteSheet.getMapParameters().put("cognome", Arrays.asList("Rossi", "Bianchi"));
    listBaseSheet.add(utenteSheet);

    // Sheet 2: Publisher info sheet (static data with images)
    CasaEditrice casaEditrice = new CasaEditrice(
        "Casa Editrice", "Mondadori",
        new GregorianCalendar(1955, Calendar.MAY, 10),
        "Roma",
        System.getProperty("user.home") + "/Documents/git-project/dev-excel/linux.jpg",
        "Drammatico"
    );
    listBaseSheet.add(casaEditrice);

    // Sheet 3: Author books with function totals
    AutoreLibriSheet autoreLibriSheet = new AutoreLibriSheet("Libri d'autore", "Test label");
    TotaleAutoreLibriSheet totale = new TotaleAutoreLibriSheet();
    totale.addRows(new TotaleAutoreLibriRow("Totale"));
    autoreLibriSheet.setSheetFunctionsTotal(totale);
    listBaseSheet.add(autoreLibriSheet);

    // Sheet 4: Book genres
    listBaseSheet.add(new GenereSheet("Genere"));

    // Sheet 5: Salary subtotals (static data)
    SalarySheet salarySheet = new SalarySheet("salary");
    salarySheet.addRows(new SalaryRow("a", 2.0));
    salarySheet.addRows(new SalaryRow("c", 1.0));
    listBaseSheet.add(salarySheet);

    ReportExcel excel = new ReportExcel("Mondadori JPA", listBaseSheet);
    byte[] bytes = generateExcel.createFileXlsx(excel);
    SpreadsheetUtils.writeToFile("/mnt/report/", excel.getTitle(), ".xlsx", bytes);
}
```

### `testChart()` — Native SQL query + dual line chart

Fetches census data (births and deaths by country/year) and renders two line charts.

```java
@Test
public void testChart() {
    CensimentoSheet censimento = new CensimentoSheet("Censimento");
    ReportExcel excel = new ReportExcel("Censimento", List.of(censimento));
    byte[] bytes = generateExcel.createFileXlsx(excel);
    SpreadsheetUtils.writeToFile("/mnt/report/", excel.getTitle(), ".xlsx", bytes);
}
```

### `bigData()` — JPQL query, large dataset

Reads all civil status records via JPQL and generates an XLSX. For very large datasets, switch to `createBigDataFileXlsx()`.

```java
@Test
public void bigData() {
    StatoMatricolareSheet sheet = new StatoMatricolareSheet("Stato Matricolare");
    ReportExcel excel = new ReportExcel("Stato Matricolare");
    excel.addSheets(sheet);
    byte[] bytes = generateExcel.createFileXlsx(excel);
    SpreadsheetUtils.writeToFile("/mnt/report/", excel.getTitle(), ".xlsx", bytes);
}
```

---

## Sheet and Row Entities

### `UtenteSheet` + `UtenteRow` — Native SQL with named parameters

```java
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
@ExcelQuery(
    "SELECT id_utente, nome, cognome, data_nascita, image, path, abilitato " +
    "FROM utente WHERE cognome IN (:cognome) ORDER BY cognome, nome"
)
public class UtenteSheet extends QuerySheetData<UtenteRow> {
    public UtenteSheet(String sheetName) { super(sheetName); }
}

@ExcelRowHeight(height = 3)
public class UtenteRow implements RowSheet {

    @ExcelColumn(name = "Id", index = 0)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private Integer idUtente;

    @ExcelColumn(name = "Nome", index = 2)
    @ExcelCellLayout
    private String nome;

    @ExcelColumn(name = "Cognome", index = 1)
    @ExcelCellLayout
    private String cognome;

    @ExcelColumn(name = "Data di nascita", index = 3)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
    @ExcelDate
    private Date dataNascita;

    @ExcelColumn(name = "Immagine", index = 4)
    @ExcelCellLayout
    @ExcelImage
    private byte[] image;           // embedded image from byte[]

    @ExcelColumn(name = "Path", index = 5)
    @ExcelCellLayout
    @ExcelImage(resizeHeight = 0.7, resizeWidth = 0.6)
    private String path;            // embedded image from file path

    @ExcelColumn(name = "Abilitato", index = 6)
    @ExcelBooleanText(enable = "Enable", disable = "Disable")
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
    private Boolean abilitato;
}
```

---

### `CensimentoSheet` + `CensimentoRow` — Native SQL with charts

```java
@ExcelSheetLayout(notMerge = false)
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, right = 1.5, left = 1.5)
@ExcelQuery(
    "SELECT n.des_nazione, c.anno, c.num_nascite, c.num_decessi " +
    "FROM nazione n INNER JOIN censimento c ON n.id_nazione = c.id_nazione " +
    "ORDER BY n.des_nazione, c.anno"
)
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
    ),
    @ExcelChart(
        id = "decessi",
        excelChartCategories = @ExcelChartCategory(
            fieldName = "${desNazione}",
            function  = "${numDecessiRowStart}:${numDecessiRowEnd}"
        ),
        sizeColumn = 10, sizeRow = 20,
        xAxis = "${annoRowStart}:${annoRowEnd}",
        group = false
    )
})
public class CensimentoSheet extends QuerySheetData<CensimentoRow> {
    public CensimentoSheet(String sheetName) { super(sheetName); }
}

public class CensimentoRow implements RowSheet {
    @ExcelColumn(name = "Nazione", index = 0)
    @ExcelCellLayout
    @ExcelMergeRow(referenceField = "")   // merge equal consecutive values
    private String desNazione;

    @ExcelColumn(name = "Anno", index = 1)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
    private Integer anno;

    @ExcelColumn(name = "Nascite", index = 2)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private Integer numNascite;

    @ExcelColumn(name = "Decessi", index = 3)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private Integer numDecessi;
}
```

---

### JPQL Query Example — `StatoMatricolareSheet`

```java
// JPQL query (nativeQuery = false)
@ExcelQuery(
    value = "SELECT new bld.generator.report.junit.entity.StatoMatricolareRow(s.id, s.descrizione) " +
            "FROM StatoMatricolale s ORDER BY s.descrizione",
    nativeQuery = false
)
public class StatoMatricolareSheet extends QuerySheetData<StatoMatricolareRow> {
    public StatoMatricolareSheet(String sheetName) { super(sheetName); }
}
```

---

## Project Structure

```
report-excel-jpa/
├── src/
│   ├── main/java/bld/generator/report/
│   │   ├── ReportExcelApplication.java
│   │   ├── config/DatabaseConfiguration.java
│   │   └── persistence/
│   │       ├── domain/     (Utente, UtenteRepository)
│   │       └── service/    (UtenteService, UtenteServiceImpl)
│   └── test/java/bld/generator/report/
│       ├── junit/ReportTestJpa.java
│       └── junit/entity/
│           ├── UtenteSheet.java / UtenteRow.java
│           ├── CensimentoSheet.java / CensimentoRow.java
│           ├── AutoreLibriSheet.java / AutoreLibriRow.java
│           ├── GenereSheet.java / GenereRow.java
│           ├── StatoMatricolareSheet.java / StatoMatricolareRow.java
│           ├── SalarySheet.java / SalaryRow.java
│           └── BigDataUtenteSheet.java / BigDataUtenteRow.java
└── src/test/resources/
    └── application.yml
```
