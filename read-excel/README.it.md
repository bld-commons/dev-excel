# read-excel

Libreria Spring Boot per la lettura di file Excel (XLS, XLSX) e CSV in oggetti Java tipizzati.
Supporta lettura multi-foglio, fogli indicizzati per chiave, filtraggio personalizzato delle righe e Bean Validation.

## Dipendenza Maven

```xml
<dependency>
    <groupId>com.github.bld-commons</groupId>
    <artifactId>read-excel</artifactId>
    <version>5.2.0-SNAPSHOT</version>
</dependency>
```

## Abilitazione

Aggiungere `@EnableExcelRead` alla propria applicazione Spring Boot o classe di test:

```java
@SpringBootTest
@EnableExcelRead
public class MyTest { ... }
```

---

## API Principale

### `ReadExcel`

Interfaccia principale per la lettura di file Excel.

| Metodo | Descrizione |
|--------|-------------|
| `convertExcelToEntity(ExcelRead)` | Analizza il file Excel e popola tutte le istanze `SheetRead` registrate |

### `ReadCsv`

Interfaccia principale per la lettura di file CSV.

| Metodo | Descrizione |
|--------|-------------|
| `convertCsvToEntity(CsvRead<T>, Class<T>)` | Analizza il file CSV e popola il `CsvRead` con righe di tipo `T` |

---

## Classi di Dominio

### `ExcelRead`

Container che descrive il file Excel da leggere e i fogli da estrarre.

```java
ExcelRead excelRead = new ExcelRead();
excelRead.setReportExcel(byteArray);          // da byte[]
excelRead.setReportExcel(inputStream);        // da InputStream
excelRead.setReportExcel("/percorso/file.xlsx"); // da percorso file
excelRead.setExcelType(ExcelType.XLSX);       // XLS o XLSX (default: XLS)
excelRead.setClose(true);                     // chiusura automatica dello stream dopo la lettura

excelRead.addSheetConvertion(MySheet.class, "Foglio1");
excelRead = readExcel.convertExcelToEntity(excelRead);

MySheet sheet = excelRead.getSheet(MySheet.class, "Foglio1");
```

**Metodi principali:**

| Metodo | Descrizione |
|--------|-------------|
| `setReportExcel(byte[])` | Imposta la sorgente da array di byte |
| `setReportExcel(InputStream)` | Imposta la sorgente da stream |
| `setReportExcel(String)` | Imposta la sorgente da percorso file |
| `setExcelType(ExcelType)` | Imposta il formato: `XLS` o `XLSX` |
| `setClose(boolean)` | Chiude automaticamente l'input stream dopo la lettura |
| `addSheetConvertion(Class, String)` | Registra un foglio (semplice `SheetRead`) |
| `addSheetConvertion(Class, String, String)` | Registra un `MapSheetRead` con campo chiave |
| `getSheet(Class<T>, String)` | Recupera il foglio popolato per tipo e nome |

---

### `SheetRead<T extends RowSheetRead>`

Classe base astratta per un foglio in lettura. Estenderla e annotarla con `@ExcelReadSheet`.

```java
@ExcelReadSheet
public class MySheet extends SheetRead<MyRow> {
    public MySheet(String sheetName) {
        super(sheetName);
    }
}
```

**Filtraggio righe** — sovrascrivere `filtered(T)` per scartare righe durante il parsing:

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
        return row.getYear() < this.currentYear; // scarta le righe dell'anno corrente
    }
}
```

**Metodi principali:**

| Metodo | Descrizione |
|--------|-------------|
| `getListRowSheet()` | Restituisce tutte le righe analizzate |
| `size()` | Numero di righe analizzate |
| `filtered(T)` | Sovrascrivere per implementare il filtraggio a livello di riga |
| `getSheetName()` | Nome del foglio |

---

### `MapSheetRead<ID, T extends RowSheetRead>`

Estensione di `SheetRead` che indicizza ogni riga per un campo chiave, permettendo un accesso O(1) alle righe.

```java
@ExcelReadSheet
public class UserSheet extends MapSheetRead<Integer, UserRow> {
    public UserSheet(String sheetName, String keyField) {
        super(sheetName, keyField);
    }
}

// Registrazione con campo chiave
excelRead.addSheetConvertion(UserSheet.class, "Utenti", "idUser");

// Recupero per chiave
UserRow row = sheet.getRow(42);
```

---

### `RowSheetRead`

Interfaccia marker che ogni classe riga deve implementare.

---

### `CsvRead<T extends RowSheetRead>`

Container per la lettura CSV.

```java
CsvRead<MyRow> csvRead = new CsvRead<>();
csvRead.setCsv(byteArray);             // da byte[]
csvRead.setCsv(inputStream);           // da InputStream
csvRead.setCsv("/percorso/file.csv");  // da percorso file
csvRead.setClose(true);                // chiusura automatica dello stream

readCsv.convertCsvToEntity(csvRead, MyRow.class);
List<MyRow> rows = csvRead.getListRowSheet();
```

---

## Riferimento Annotazioni

### `@ExcelReadSheet`

Applicata a livello di classe su una sottoclasse di `SheetRead`.

| Attributo     | Tipo  | Default | Descrizione                                    |
|---------------|-------|---------|------------------------------------------------|
| `startRow`    | `int` | `0`     | Indice (base zero) della riga di intestazione  |
| `startColumn` | `int` | `0`     | Indice (base zero) della prima colonna         |

```java
@ExcelReadSheet(startRow = 1, startColumn = 0)
public class MySheet extends SheetRead<MyRow> { ... }
```

---

### `@ExcelReadColumn`

Applicata ai campi di una classe `RowSheetRead` per mapparli a un'intestazione di colonna Excel.

| Attributo              | Tipo      | Default | Descrizione                                                                   |
|------------------------|-----------|---------|-------------------------------------------------------------------------------|
| `value`                | `String`  | —       | Il nome esatto dell'intestazione nel file Excel                               |
| `ignoreCellTypeString` | `boolean` | `false` | Interpreta celle con testo numerico/data convertendo il valore stringa        |

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

Mappa il valore testuale di una cella a un campo `Boolean`. Si applica insieme a `@ExcelReadColumn` quando la colonna Excel contiene testo ("Sì"/"No", "Yes"/"No", "true"/"false", ecc.) invece di una cella booleana nativa.

| Attributo  | Tipo     | Descrizione                                  |
|------------|----------|----------------------------------------------|
| `enable`   | `String` | Valore stringa che corrisponde a `true`       |
| `disable`  | `String` | Valore stringa che corrisponde a `false`      |

Il confronto è case-insensitive. Se il valore della cella non corrisponde né a `enable` né a `disable`, il campo viene impostato a `null`.

```java
public class EmployeeRow implements RowSheetRead {

    @ExcelReadColumn(value = "Attivo")
    @ExcelBooleanText(enable = "Sì", disable = "No")
    private Boolean attivo;
}
```

---

### `@ExcelDate`

Specifica il formato della data quando si legge un campo `Date` o `Calendar` da una cella stringa (richiede `ignoreCellTypeString = true` su `@ExcelReadColumn`).

```java
@ExcelReadColumn(value = "Data Assunzione")
@ExcelDate(value = ColumnDateFormat.DD_MM_YYYY)
private Date dataAssunzione;
```

---

### `@CsvSettings`

Applicata a livello di classe su un'implementazione `RowSheetRead` per configurare il parsing CSV.

| Attributo           | Tipo       | Default | Descrizione                                         |
|---------------------|------------|---------|-----------------------------------------------------|
| `delimiter`         | `char`     | `','`   | Carattere delimitatore di colonna                   |
| `skipHeaderRecord`  | `boolean`  | `true`  | Salta la prima riga (intestazione)                  |
| `ignoreColumns`     | `String[]` | `{}`    | Nomi di colonne da ignorare durante il parsing      |
| `parallel`          | `boolean`  | `false` | Analizza i record con uno stream parallelo          |

```java
@CsvSettings(skipHeaderRecord = true, delimiter = ',')
public class EmployeeCsvRow implements RowSheetRead {

    @ExcelReadColumn(value = "Nome")
    private String nome;

    @ExcelReadColumn(value = "Data Assunzione")
    @CsvDate(value = ColumnDateFormat.DD_MM_YYYY)
    private Date dataAssunzione;

    @ExcelReadColumn(value = "Attivo")
    private Boolean attivo;  // legge stringhe "true"/"false"
}
```

---

### `@CsvDate`

Specifica il formato della data per un campo `Date` o `Calendar` in lettura da CSV. Separata da `@ExcelDate` perché il formato nel CSV può differire da quello Excel.

| Attributo    | Tipo                | Default  | Descrizione                          |
|--------------|---------------------|----------|--------------------------------------|
| `value`      | `ColumnDateFormat`  | —        | Pattern del formato data             |
| `separator`  | `String`            | `"/"`    | Carattere separatore nel pattern     |
| `timezone`   | `String`            | `"UTC"`  | Fuso orario per il parsing           |

```java
@ExcelReadColumn(value = "Data Assunzione")
@CsvDate(value = ColumnDateFormat.DD_MM_YYYY)
private Date dataAssunzione;
```

---

## Tipi di Campo Supportati

| Tipo Java    | Note                                                              |
|--------------|-------------------------------------------------------------------|
| `String`     | Letto via `DataFormatter`; le celle formula usano il valore cache |
| `Integer`    | Da cella numerica o stringa                                       |
| `Long`       | Da cella numerica o stringa                                       |
| `Double`     | Da cella numerica o stringa                                       |
| `Float`      | Da cella numerica o stringa                                       |
| `BigDecimal` | Da cella numerica o stringa                                       |
| `Boolean`    | Da cella booleana oppure tramite `@ExcelBooleanText`              |
| `Date`       | Da cella data o stringa con formato `@ExcelDate`                  |
| `Calendar`   | Da cella data o stringa con formato `@ExcelDate`                  |
| `Character`  | Cella stringa con singolo carattere                               |

---

## Costanti

### `ExcelType`

| Valore | Formato                        |
|--------|--------------------------------|
| `XLS`  | HSSF (`.xls`) — **predefinito** |
| `XLSX` | XSSF (`.xlsx`)                 |

---

## Performance

`ReadExcelImpl` e `ReadCsvImpl` utilizzano una `ConcurrentHashMap` statica con chiave la classe riga. Al primo utilizzo di una classe vengono eseguiti una sola volta la scansione dei campi, la risoluzione delle annotazioni (`@ExcelReadColumn`, `@ExcelBooleanText`, `@ExcelDate`, `@CsvDate`) e la ricerca dei setter; i risultati vengono memorizzati nella cache. Le letture successive della stessa classe non eseguono alcuna reflection.

Inoltre, `BeanWrapperImpl` viene istanziato una sola volta per record (anziché per ogni campo), riducendo l'allocazione di oggetti su file di grandi dimensioni.

La cache è condivisa per tutta la durata del contesto Spring ed è sicura per l'accesso concorrente.

---

## Gestione delle Eccezioni

### `ExcelReaderException`

Eccezione unchecked lanciata in caso di errore di lettura. Contiene un `code` strutturato e un `parameter` opzionale.

| Codice                  | Trigger                                                   |
|-------------------------|-----------------------------------------------------------|
| `MAX_SHEET_NAME`        | Il nome del foglio supera i 31 caratteri                  |
| `SHEET_NOT_FOUND`       | Il nome del foglio non è presente nel workbook            |
| `COLUMN_NOT_FOUND`      | L'intestazione di colonna non è trovata nel foglio        |
| `CHARACTER_NOT_VALID`   | Il valore stringa non è convertibile in `Character`       |
| `KEY_FIELD_IS_NOT_NULL` | Il `keyField` è obbligatorio per `MapSheetRead`           |
| `MULTIPLE_SHEET_NAME`   | Due fogli registrati con lo stesso nome                   |

```java
try {
    excelRead = readExcel.convertExcelToEntity(excelRead);
} catch (ExcelReaderException e) {
    System.out.println(e.getCode());      // codice errore strutturato
    System.out.println(e.getParameter()); // entità coinvolta (nome foglio/colonna)
}
```

---

## Esempi Completi

### Lettura di un XLSX Multi-Foglio

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

### Lettura con Filtraggio Personalizzato

```java
// Scarta le righe il cui anno è >= l'anno corrente
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

// Classe riga
public class DataMeteoRow implements RowSheetRead {
    @ExcelReadColumn(value = "YY", ignoreCellTypeString = true) private Integer year;
    @ExcelReadColumn(value = "MM", ignoreCellTypeString = true) private Integer month;
    @ExcelReadColumn(value = "Tn", ignoreCellTypeString = true) private Double  temperaturaMax;
    @ExcelReadColumn(value = "Tx", ignoreCellTypeString = true) private Double  temperaturaMin;
    // ... altri campi ...
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
    System.out.println("Righe lette: " + sheet.size());
}
```

### Lettura di un CSV con Bean Validation

```java
@CsvSettings(delimiter = ',', skipHeaderRecord = true)
public class RendicontazioneMassivaImportColumn implements RowSheetRead {

    @NotBlank
    private String codice;

    @NotNull
    @Positive
    private Integer importo;

    // getter/setter...
}

// Test
@Test
public void testReadCsv() throws Exception {
    byte[] csv = IOUtils.toByteArray(new FileInputStream("/mnt/report/Test.csv"));
    CsvRead<RendicontazioneMassivaImportColumn> csvRead = new CsvRead<>();
    csvRead.setCsv(csv);
    readCsv.convertCsvToEntity(csvRead, RendicontazioneMassivaImportColumn.class);
    System.out.println("Righe: " + csvRead.getListRowSheet().size());
}
```

### Lettura via JSON (endpoint REST)

Il modulo `report-excel` espone anche endpoint REST per leggere file Excel inviati come JSON con codifica Base64:

```json
POST /excel/sheet-read
{
  "name": "test excel",
  "excel": "data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,..."
}
```

Questo permette di leggere file Excel senza richiedere un percorso file locale.
