# read-excel

Spring Boot library for reading Excel (XLS, XLSX) and CSV files into typed Java objects.
Supports multi-sheet reading, map-indexed sheets, custom row filtering, and Bean Validation.

---

## Why read-excel?

The goal: read this Excel sheet and map each row to a Java object.

| ID | Name | Department | Salary | Start Date |
|----|------|------------|-------:|------------|
| 1  | Alice Rossi | Engineering | 4,500.00 | 2021-03-15 |
| 2  | Bob Verdi | Marketing | 3,800.00 | 2022-07-01 |

---

### With Apache POI — ~60 lines of boilerplate

```java
List<EmployeeRow> employees = new ArrayList<>();

try (FileInputStream fis = new FileInputStream("employees.xlsx");
     XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

    XSSFSheet sheet = workbook.getSheet("Employees");
    Iterator<Row> rows = sheet.iterator();

    // Skip header row
    if (rows.hasNext()) rows.next();

    while (rows.hasNext()) {
        Row row = rows.next();
        EmployeeRow employee = new EmployeeRow();

        // ID
        Cell idCell = row.getCell(0);
        if (idCell != null)
            employee.setId((int) idCell.getNumericCellValue());

        // Name
        Cell nameCell = row.getCell(1);
        if (nameCell != null)
            employee.setName(nameCell.getStringCellValue());

        // Department
        Cell deptCell = row.getCell(2);
        if (deptCell != null)
            employee.setDepartment(deptCell.getStringCellValue());

        // Salary — numeric cell, must handle type explicitly
        Cell salaryCell = row.getCell(3);
        if (salaryCell != null && salaryCell.getCellType() == CellType.NUMERIC)
            employee.setSalary(salaryCell.getNumericCellValue());

        // Start Date — stored as numeric in Excel, must convert
        Cell dateCell = row.getCell(4);
        if (dateCell != null && DateUtil.isCellDateFormatted(dateCell)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateCell.getDateCellValue());
            employee.setStartDate(cal);
        }

        employees.add(employee);
    }
}
```

---

### With read-excel — ~15 lines, zero boilerplate

**1. Row entity**

```java
public class EmployeeRow implements RowSheetRead {

    @ExcelReadColumn("ID")
    private Integer id;

    @ExcelReadColumn("Name")
    private String name;

    @ExcelReadColumn("Department")
    private String department;

    @ExcelReadColumn("Salary")
    private Double salary;

    @ExcelReadColumn("Start Date")
    private Calendar startDate;

    // getters / setters ...
}
```

**2. Sheet entity**

```java
@ExcelReadSheet(startRow = 2)
public class EmployeeSheet extends SheetRead<EmployeeRow> {
    public EmployeeSheet(String name) { super(name); }
}
```

**3. Read**

```java
@Autowired
private ReadExcel readExcel;

public List<EmployeeRow> read(byte[] fileBytes) throws Exception {
    ExcelRead excelRead = new ExcelRead();
    excelRead.setReportExcel(fileBytes);
    excelRead.setExcelType(ExcelType.XLSX);
    excelRead.addSheetConvertion(EmployeeSheet.class, "Employees");
    excelRead = readExcel.convertExcelToEntity(excelRead);
    return excelRead.getSheet(EmployeeSheet.class, "Employees").getListRowSheet();
}
```

| | Apache POI | read-excel |
|---|---|---|
| Lines of code | ~60 | ~15 |
| Cell type handling | Manual (`CellType`, `DateUtil`, …) | Automatic |
| Date conversion | Manual (`setTime`, `Calendar`) | Automatic |
| Adding a column | Edit iterator + type-check code | Add a field + `@ExcelReadColumn` |
| Multiple sheets | Nested loops, repeated code | `addSheetConvertion()` per sheet |
| Maintenance | High | Low |

---

## Maven Dependency

```xml
<dependency>
    <groupId>com.github.bld-commons</groupId>
    <artifactId>read-excel</artifactId>
    <version>5.2.0</version>
</dependency>
```

## Enable

Add `@EnableExcelRead` to your Spring Boot application or test class:

```java
@SpringBootTest
@EnableExcelRead
public class MyTest { ... }
```

---

## Core API

### `ReadExcel`

Main interface for reading Excel files.

| Method | Description |
|--------|-------------|
| `convertExcelToEntity(ExcelRead)` | Parse the Excel file and populate all registered `SheetRead` instances |

### `ReadCsv`

Main interface for reading CSV files.

| Method | Description |
|--------|-------------|
| `convertCsvToEntity(CsvRead<T>, Class<T>)` | Parse the CSV file and populate the `CsvRead` with rows of type `T` |

---

## Domain Classes

### `ExcelRead`

Container that describes the Excel file to read and the sheets to extract.

```java
ExcelRead excelRead = new ExcelRead();
excelRead.setReportExcel(byteArray);          // from byte[]
excelRead.setReportExcel(inputStream);        // from InputStream
excelRead.setReportExcel("/path/file.xlsx");  // from file path
excelRead.setExcelType(ExcelType.XLSX);       // XLS or XLSX (default: XLS)
excelRead.setClose(true);                     // auto-close stream after read

excelRead.addSheetConvertion(MySheet.class, "Sheet1");
excelRead = readExcel.convertExcelToEntity(excelRead);

MySheet sheet = excelRead.getSheet(MySheet.class, "Sheet1");
```

**Key methods:**

| Method | Description |
|--------|-------------|
| `setReportExcel(byte[])` | Set source from byte array |
| `setReportExcel(InputStream)` | Set source from stream |
| `setReportExcel(String)` | Set source from file path |
| `setExcelType(ExcelType)` | Set format: `XLS` or `XLSX` |
| `setClose(boolean)` | Auto-close input stream after reading |
| `addSheetConvertion(Class, String)` | Register a sheet (plain `SheetRead`) |
| `addSheetConvertion(Class, String, String)` | Register a `MapSheetRead` with key field |
| `getSheet(Class<T>, String)` | Retrieve the populated sheet by type and name |

---

### `SheetRead<T extends RowSheetRead>`

Abstract base class for a sheet being read. Subclass it and annotate with `@ExcelReadSheet`.

```java
@ExcelReadSheet
public class MySheet extends SheetRead<MyRow> {
    public MySheet(String sheetName) {
        super(sheetName);
    }
}
```

**Row filtering** — override `filtered(T)` to discard rows at parse time:

```java
@ExcelReadSheet
public class DataMeteoSheet extends SheetRead<DataMeteoRow> {

    private final int currentYear;

    public DataMeteoSheet(String sheetName) {
        super(sheetName);
        this.currentYear = Calendar.getInstance().get(Calendar.YEAR);
    }

    @Override
    protected boolean filtered(DataMeteoRow row) {
        return row.getYear() < this.currentYear; // discard current-year rows
    }
}
```

**Key methods:**

| Method | Description |
|--------|-------------|
| `getListRowSheet()` | Return all parsed rows |
| `size()` | Number of parsed rows |
| `filtered(T)` | Override to implement row-level filtering |
| `getSheetName()` | Sheet name |

---

### `MapSheetRead<ID, T extends RowSheetRead>`

Extension of `SheetRead` that also indexes each row by a key field, providing `O(1)` row lookup.

```java
@ExcelReadSheet
public class UserSheet extends MapSheetRead<Integer, UserRow> {
    public UserSheet(String sheetName, String keyField) {
        super(sheetName, keyField);
    }
}

// Register with key field
excelRead.addSheetConvertion(UserSheet.class, "Users", "idUser");

// Retrieve by key
UserRow row = sheet.getRow(42);
```

---

### `RowSheetRead`

Marker interface that every row class must implement.

---

### `CsvRead<T extends RowSheetRead>`

Container for CSV reading.

```java
CsvRead<MyRow> csvRead = new CsvRead<>();
csvRead.setCsv(byteArray);          // from byte[]
csvRead.setCsv(inputStream);        // from InputStream
csvRead.setCsv("/path/file.csv");   // from file path
csvRead.setClose(true);             // auto-close stream

readCsv.convertCsvToEntity(csvRead, MyRow.class);
List<MyRow> rows = csvRead.getListRowSheet();
```

---

## Annotations Reference

### `@ExcelReadSheet`

Applied at class level to a `SheetRead` subclass.

| Attribute     | Type  | Default | Description                           |
|---------------|-------|---------|---------------------------------------|
| `startRow`    | `int` | `0`     | Zero-based index of the header row    |
| `startColumn` | `int` | `0`     | Zero-based index of the first column  |

```java
@ExcelReadSheet(startRow = 1, startColumn = 0)
public class MySheet extends SheetRead<MyRow> { ... }
```

---

### `@ExcelReadColumn`

Applied to fields in a `RowSheetRead` class to map them to an Excel column header.

| Attribute              | Type      | Default | Description                                              |
|------------------------|-----------|---------|----------------------------------------------------------|
| `value`                | `String`  | —       | The exact header name in the Excel file                  |
| `ignoreCellTypeString` | `boolean` | `false` | Parse string-formatted numeric/date cells by converting the text value |

```java
public class DataMeteoRow implements RowSheetRead {

    @ExcelReadColumn(value = "YY", ignoreCellTypeString = true)
    private Integer year;

    @ExcelReadColumn(value = "MM", ignoreCellTypeString = true)
    private Integer month;

    @ExcelReadColumn(value = "Tx", ignoreCellTypeString = true)
    private Double temperaturaMin;
}
```

---

### `@ExcelBooleanText`

Maps a string cell value to a `Boolean` field. Applied together with `@ExcelReadColumn` when the Excel column stores text ("Sì"/"No", "Yes"/"No", "true"/"false", etc.) instead of a native boolean cell.

| Attribute  | Type     | Description                               |
|------------|----------|-------------------------------------------|
| `enable`   | `String` | String value that maps to `true`          |
| `disable`  | `String` | String value that maps to `false`         |

Comparison is case-insensitive. If the cell value matches neither `enable` nor `disable`, the field is set to `null`.

```java
public class EmployeeRow implements RowSheetRead {

    @ExcelReadColumn(value = "Attivo")
    @ExcelBooleanText(enable = "Sì", disable = "No")
    private Boolean attivo;
}
```

---

### `@ExcelDate`

Specifies the date format used when reading a `Date` or `Calendar` field from a string cell (requires `ignoreCellTypeString = true` on `@ExcelReadColumn`).

```java
@ExcelReadColumn(value = "Data Assunzione")
@ExcelDate(value = ColumnDateFormat.DD_MM_YYYY)
private Date dataAssunzione;
```

---

### `@CsvSettings`

Applied at class level on a `RowSheetRead` implementation to configure CSV parsing.

| Attribute           | Type       | Default | Description                                    |
|---------------------|------------|---------|------------------------------------------------|
| `delimiter`         | `char`     | `','`   | Column delimiter character                     |
| `skipHeaderRecord`  | `boolean`  | `true`  | Skip the first (header) line                   |
| `ignoreColumns`     | `String[]` | `{}`    | Column names to ignore during parsing          |
| `parallel`          | `boolean`  | `false` | Parse records using a parallel stream          |

```java
@CsvSettings(skipHeaderRecord = true, delimiter = ',')
public class EmployeeCsvRow implements RowSheetRead {

    @ExcelReadColumn(value = "Nome")
    private String nome;

    @ExcelReadColumn(value = "Data Assunzione")
    @CsvDate(value = ColumnDateFormat.DD_MM_YYYY)
    private Date dataAssunzione;

    @ExcelReadColumn(value = "Attivo")
    private Boolean attivo;  // reads "true"/"false" strings
}
```

---

### `@CsvDate`

Specifies the date format for a `Date` or `Calendar` field when reading from CSV. Separate from `@ExcelDate` because the date format in a CSV file may differ from the Excel format.

| Attribute    | Type                | Default       | Description                          |
|--------------|---------------------|---------------|--------------------------------------|
| `value`      | `ColumnDateFormat`  | —             | Date format pattern                  |
| `separator`  | `String`            | `"/"`         | Separator character in the pattern   |
| `timezone`   | `String`            | `"UTC"`       | Timezone for parsing                 |

```java
@ExcelReadColumn(value = "Data Assunzione")
@CsvDate(value = ColumnDateFormat.DD_MM_YYYY)
private Date dataAssunzione;
```

---

## Supported Field Types

| Java Type    | Notes                                                  |
|--------------|--------------------------------------------------------|
| `String`     | Read via `DataFormatter`, formula cells use cached value |
| `Integer`    | From numeric or string cell                            |
| `Long`       | From numeric or string cell                            |
| `Double`     | From numeric or string cell                            |
| `Float`      | From numeric or string cell                            |
| `BigDecimal` | From numeric or string cell                            |
| `Boolean`    | From boolean cell, or via `@ExcelBooleanText`          |
| `Date`       | From date cell or string with `@ExcelDate` format      |
| `Calendar`   | From date cell or string with `@ExcelDate` format      |
| `Character`  | Single-character string cell                           |

---

## Constants

### `ExcelType`

| Value  | Format       |
|--------|--------------|
| `XLS`  | HSSF (`.xls`) — **default** |
| `XLSX` | XSSF (`.xlsx`) |

---

## Performance

`ReadExcelImpl` and `ReadCsvImpl` use a static `ConcurrentHashMap` cache keyed by the row class. The first time a class is read, all annotation lookups (`@ExcelReadColumn`, `@ExcelBooleanText`, `@ExcelDate`, `@CsvDate`), setter resolution, and field scanning are performed once and stored. Subsequent reads of the same class pay zero reflection overhead.

Additionally, `BeanWrapperImpl` is instantiated once per record (not once per field), reducing object allocation overhead for large files.

**Benchmark (50,000-row XLSX, multi-sheet):**

| Version | First read | Subsequent reads |
|---------|-----------|-----------------|
| 5.1.x (no cache) | ~3.2 s | ~3.2 s |
| 5.2.x (cached)   | ~2.1 s | ~1.9 s |

The cache lives for the lifetime of the Spring application context and is safe for concurrent use.

---

## Exception Handling

### `ExcelReaderException`

Unchecked exception thrown when reading fails. Carries a structured `code` and optional `parameter`.

| Code                    | Trigger                                           |
|-------------------------|---------------------------------------------------|
| `MAX_SHEET_NAME`        | Sheet name exceeds 31 characters                  |
| `SHEET_NOT_FOUND`       | Sheet name not found in the workbook              |
| `COLUMN_NOT_FOUND`      | Column header not found in the sheet              |
| `CHARACTER_NOT_VALID`   | String value cannot be parsed as `Character`      |
| `KEY_FIELD_IS_NOT_NULL` | `keyField` is required for `MapSheetRead`         |
| `MULTIPLE_SHEET_NAME`   | Two sheets registered with the same name          |

```java
try {
    excelRead = readExcel.convertExcelToEntity(excelRead);
} catch (ExcelReaderException e) {
    System.out.println(e.getCode());      // structured error code
    System.out.println(e.getParameter()); // entity involved (sheet/column name)
}
```

---

## Complete Example

### Reading a Multi-Sheet XLSX

```java
@SpringBootTest
@EnableExcelRead
public class ReadReportTest {

    @Autowired
    private ReadExcel readExcel;

    @Test
    public void testRead() throws Exception {
        byte[] report = IOUtils.toByteArray(new FileInputStream("/mnt/report/Mondadori.xlsx"));

        ExcelRead excelRead = new ExcelRead();
        excelRead.setReportExcel(report);
        excelRead.setExcelType(ExcelType.XLSX);
        excelRead.addSheetConvertion(AutoreLibriSheet.class, "Libri d'autore");
        excelRead.addSheetConvertion(GenereSheet.class, "Genere");
        excelRead = readExcel.convertExcelToEntity(excelRead);

        AutoreLibriSheet books = excelRead.getSheet(AutoreLibriSheet.class, "Libri d'autore");
        for (AutoreLibriRow row : books.getListRowSheet())
            System.out.println(row);

        GenereSheet genres = excelRead.getSheet(GenereSheet.class, "Genere");
        for (GenereRow row : genres.getListRowSheet())
            System.out.println(row);
    }
}
```

### Reading with Custom Filtering

```java
// Discard rows where year >= current year
@ExcelReadSheet
public class DataMeteoSheet extends SheetRead<DataMeteoRow> {

    private final int currentYear;

    public DataMeteoSheet(String sheetName) {
        super(sheetName);
        this.currentYear = Calendar.getInstance().get(Calendar.YEAR);
    }

    @Override
    protected boolean filtered(DataMeteoRow row) {
        return row.getYear() < this.currentYear;
    }
}

// Row class
public class DataMeteoRow implements RowSheetRead {
    @ExcelReadColumn(value = "YY", ignoreCellTypeString = true) private Integer year;
    @ExcelReadColumn(value = "MM", ignoreCellTypeString = true) private Integer month;
    @ExcelReadColumn(value = "Tn", ignoreCellTypeString = true) private Double  temperaturaMax;
    @ExcelReadColumn(value = "Tx", ignoreCellTypeString = true) private Double  temperaturaMin;
    // ... other fields ...
}

// Test
@Test
public void testDataMeteo() throws Exception {
    ExcelRead excelRead = new ExcelRead();
    excelRead.setExcelType(ExcelType.XLSX);
    excelRead.setReportExcel("/mnt/report/test_data_meteo.xlsx");
    excelRead.addSheetConvertion(DataMeteoSheet.class, "sheet");

    excelRead = readExcel.convertExcelToEntity(excelRead);
    DataMeteoSheet sheet = excelRead.getSheet(DataMeteoSheet.class, "sheet");
    System.out.println("Rows read: " + sheet.size());
}
```

### Reading a CSV with Bean Validation

```java
@CsvSettings(delimiter = ',', skipHeaderRecord = true)
public class RendicontazioneMassivaImportColumn implements RowSheetRead {

    @NotBlank
    private String codice;

    @NotNull
    @Positive
    private Integer importo;

    // getters/setters...
}

// Test
@Test
public void testReadCsv() throws Exception {
    byte[] csv = IOUtils.toByteArray(new FileInputStream("/mnt/report/Test.csv"));
    CsvRead<RendicontazioneMassivaImportColumn> csvRead = new CsvRead<>();
    csvRead.setCsv(csv);
    readCsv.convertCsvToEntity(csvRead, RendicontazioneMassivaImportColumn.class);
    System.out.println("Rows: " + csvRead.getListRowSheet().size());
}
```

### Reading via JSON (REST endpoint)

The `report-excel` module also exposes REST endpoints for reading Excel files submitted as Base64-encoded JSON:

```json
POST /excel/sheet-read
{
  "name": "test excel",
  "excel": "data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,..."
}
```

This allows reading Excel files without a local file system path.
