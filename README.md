# dev-excel

## English

This project provides two libraries built on top of Apache POI to simplify the generation and reading of Excel and CSV files in Spring Boot applications through an annotation-driven approach.

### Libraries

| Library | Artifact | Description |
|---|---|---|
| **generator-excel** | `com.github.bld-commons.excel:generator-excel` | Generates `.xls`, `.xlsx` and CSV files from annotated Java objects |
| **read-excel** | `com.github.bld-commons.excel:read-excel` | Reads `.xls` and `.xlsx` files and maps them to annotated Java objects |

### Requirements

- Java 17+
- Spring Boot 3.x
- Apache POI 5.x (included as transitive dependency)

### Maven dependency

```xml
<!-- Excel Generator -->
<dependency>
    <groupId>com.github.bld-commons.excel</groupId>
    <artifactId>generator-excel</artifactId>
    <version>5.1.2</version>
</dependency>

<!-- Excel Reader -->
<dependency>
    <groupId>com.github.bld-commons.excel</groupId>
    <artifactId>read-excel</artifactId>
    <version>5.1.2</version>
</dependency>
```

### Enable the libraries

Add one of the following annotations to your Spring Boot configuration class:

```java
@EnableExcelGenerator  // activates generator-excel
@EnableExcelRead       // activates read-excel
```

---

## generator-excel

### Main classes

| Class | Description |
|---|---|
| `ReportExcel` | Root object representing the entire Excel document |
| `BaseSheet` | Abstract base class for all sheet types (max 31-character name) |
| `SheetData<T>` | Sheet backed by a typed list of rows |
| `SheetSummary` | Sheet containing summary/aggregated data |
| `MergeSheet` | Sheet that merges multiple sheet components |
| `RowSheet` | Interface implemented by each row entity |
| `GenerateExcel` | Service interface injected via Spring to trigger generation |

### Key annotations

| Annotation | Level | Description |
|---|---|---|
| `@ExcelSheetLayout` | Class | Configures sheet layout, start row/column, merge behaviour |
| `@ExcelHeaderLayout` | Class | Configures the header row appearance |
| `@ExcelColumn` | Field | Maps a field to an Excel column |
| `@ExcelCellLayout` | Field | Configures font, colour, border of a cell |
| `@ExcelFunction` | Field | Applies a formula (sum, subtotal, custom) to a column |
| `@ExcelFunctionRow` | Field | Applies a formula per row |
| `@ExcelChart` | Class | Embeds a chart in the sheet |
| `@ExcelPivot` | Class | Creates a pivot table |
| `@ExcelDataValidation` | Field | Adds data validation / drop-down list |
| `@ExcelMergeRow` | Field | Merges equal consecutive values in a column |
| `@ExcelDate` | Field | Configures date format |
| `@ExcelImage` | Field | Embeds an image |
| `@ExcelQuery` | Class | Populates sheet rows from a JPA query |

### Generation modes

| Method | Format | POI Engine | Notes |
|---|---|---|---|
| `createFileXls` | `.xls` | HSSF | Max 65,535 rows |
| `createFileXlsx` | `.xlsx` | XSSF | Full feature set |
| `createBigDataFileXlsx` | `.xlsx` | SXSSF | Streaming — lower memory, limited features |

### Feature comparison

<table style="width:70%; border: 1px solid #666;">
<tr>
<th style="border: 1px solid #666; text-align: center;">Feature</th>
<th style="border: 1px solid #666; text-align: center;">HSSF (.xls)</th>
<th style="border: 1px solid #666; text-align: center;">XSSF (.xlsx)</th>
<th style="border: 1px solid #666; text-align: center;">SXSSF (big data)</th>
</tr>
<tr><td style="border: 1px solid #666;">CPU and memory efficiency</td><td style="border: 1px solid #666; text-align: center;">Varies</td><td style="border: 1px solid #666; text-align: center;">Varies</td><td style="border: 1px solid #666; text-align: center;">Good</td></tr>
<tr><td style="border: 1px solid #666;">Read files</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
<tr><td style="border: 1px solid #666;">Write files</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td></tr>
<tr><td style="border: 1px solid #666;">Create sheets / rows / cells</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td></tr>
<tr><td style="border: 1px solid #666;">Delete sheets / rows / cells</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
<tr><td style="border: 1px solid #666;">Cell styling</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td></tr>
<tr><td style="border: 1px solid #666;">Shift rows</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
<tr><td style="border: 1px solid #666;">Clone sheets</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
<tr><td style="border: 1px solid #666;">Formula evaluation</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
<tr><td style="border: 1px solid #666;">Cell comments</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
<tr><td style="border: 1px solid #666;">Images</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
<tr><td style="border: 1px solid #666;">Charts</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
<tr><td style="border: 1px solid #666;">Pivot tables</td><td style="border: 1px solid #666; text-align: center;">No</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
</table>

### Quick start — generator-excel

```java
// 1. Define a row entity
@ExcelSheetLayout
public class SalesSheet extends SheetData<SalesRow> { ... }

// 2. Annotate the row fields
public class SalesRow implements RowSheet {
    @ExcelColumn(name = "Product", position = 1)
    private String product;

    @ExcelColumn(name = "Amount", position = 2)
    @ExcelFunction(function = "SUM")
    private BigDecimal amount;
}

// 3. Build and generate
@Autowired
private GenerateExcel generateExcel;

ReportExcel report = new ReportExcel("Sales Report");
SalesSheet sheet = new SalesSheet("Sales");
sheet.addRows(salesRows);
report.addSheet(sheet);

byte[] xlsx = generateExcel.createFileXlsx(report);
```

### Optional Spring properties

| Property | Type | Description |
|---|---|---|
| `com.bld.commons.check.annotation` | Boolean | Validates annotation consistency at startup |
| `com.bld.commons.resource.cover.path` | String | Path to the cover sheet template |
| `com.bld.commons.report.excel.title` | String | Default report title |
| `com.bld.commons.report.excel.date` | String | Default date format for the cover |
| `com.bld.commons.number.empty.rows` | Integer | Number of empty rows inserted after each sheet |
| `com.bld.commons.multiple.datasource` | Boolean | Enables multiple datasource support for `@ExcelQuery` |

### Full Javadoc

- https://javadoc.io/doc/com.github.bld-commons.excel/generator-excel/latest/index.html

---

## read-excel

### Main classes

| Class | Description |
|---|---|
| `ExcelRead` | Container representing the Excel file to be read (byte array or `InputStream`) |
| `SheetRead<T>` | Abstract class representing a sheet; holds the list of parsed rows |
| `MapSheetRead<ID, T>` | Variant of `SheetRead` that indexes rows by a key field |
| `RowSheetRead` | Interface implemented by each row entity |
| `ReadExcel` | Service interface injected via Spring to trigger reading |

### Key annotations

| Annotation | Level | Description |
|---|---|---|
| `@ExcelReadSheet` | Class | Maps a class to an Excel sheet by name and start row/column |
| `@ExcelReadColumn` | Field | Maps a field to an Excel column header name |

### Supported field types

`String`, `Integer`, `Double`, `Float`, `Long`, `BigDecimal`, `BigInteger`, `Boolean`, `Character`, `Date`, `Calendar`

### Quick start — read-excel

```java
// 1. Define the row entity
@ExcelReadSheet(name = "Sales")
public class SalesSheetRead extends SheetRead<SalesRowRead> {
    public SalesSheetRead() { super("Sales"); }
}

// 2. Annotate the row fields
public class SalesRowRead implements RowSheetRead {
    @ExcelReadColumn("Product")
    private String product;

    @ExcelReadColumn("Amount")
    private BigDecimal amount;
}

// 3. Read the file
@Autowired
private ReadExcel readExcel;

ExcelRead excelRead = new ExcelRead();
excelRead.setReportExcel(fileBytes);          // byte[], InputStream or file path
excelRead.setExcelType(ExcelType.XLSX);
excelRead.addSheetConvertion(SalesSheetRead.class, "Sales");
readExcel.convertExcelToEntity(excelRead);

SalesSheetRead sheet = excelRead.getSheet(SalesSheetRead.class, "Sales");
List<SalesRowRead> rows = sheet.getListRowSheet();
```

### Full Javadoc

- https://javadoc.io/doc/com.github.bld-commons.excel/read-excel/latest/index.html

---

## Exception handling

| Exception | Module | Description |
|---|---|---|
| `SpreadsheetException` | common-spreadsheet | Base unchecked exception for configuration errors |
| `ExcelGeneratorException` | generator-excel | Thrown when sheet generation fails |
| `CsvGeneratorException` | generator-excel | Thrown when CSV generation fails |
| `ExcelReaderException` | read-excel | Thrown when reading fails; carries a structured `ExcelExceptionType` code |

---

---

## Italiano

Questo progetto fornisce due librerie basate su Apache POI per semplificare la generazione e la lettura di file Excel e CSV in applicazioni Spring Boot attraverso un approccio annotation-driven.

### Librerie

| Libreria | Artifact | Descrizione |
|---|---|---|
| **generator-excel** | `com.github.bld-commons.excel:generator-excel` | Genera file `.xls`, `.xlsx` e CSV da oggetti Java annotati |
| **read-excel** | `com.github.bld-commons.excel:read-excel` | Legge file `.xls` e `.xlsx` e li mappa su oggetti Java annotati |

### Requisiti

- Java 17+
- Spring Boot 3.x
- Apache POI 5.x (incluso come dipendenza transitiva)

### Dipendenza Maven

```xml
<!-- Generazione Excel -->
<dependency>
    <groupId>com.github.bld-commons.excel</groupId>
    <artifactId>generator-excel</artifactId>
    <version>5.1.2</version>
</dependency>

<!-- Lettura Excel -->
<dependency>
    <groupId>com.github.bld-commons.excel</groupId>
    <artifactId>read-excel</artifactId>
    <version>5.1.2</version>
</dependency>
```

### Abilitare le librerie

Aggiungere una delle seguenti annotazioni alla propria classe di configurazione Spring Boot:

```java
@EnableExcelGenerator  // attiva generator-excel
@EnableExcelRead       // attiva read-excel
```

---

## generator-excel

### Classi principali

| Classe | Descrizione |
|---|---|
| `ReportExcel` | Oggetto radice che rappresenta l'intero documento Excel |
| `BaseSheet` | Classe astratta base per tutti i tipi di foglio (nome max 31 caratteri) |
| `SheetData<T>` | Foglio associato a una lista tipizzata di righe |
| `SheetSummary` | Foglio contenente dati di riepilogo/aggregati |
| `MergeSheet` | Foglio che unisce più componenti sheet |
| `RowSheet` | Interfaccia implementata da ogni entità riga |
| `GenerateExcel` | Interfaccia del servizio Spring da iniettare per avviare la generazione |

### Annotazioni principali

| Annotazione | Livello | Descrizione |
|---|---|---|
| `@ExcelSheetLayout` | Classe | Configura il layout del foglio, riga/colonna iniziale, comportamento merge |
| `@ExcelHeaderLayout` | Classe | Configura l'aspetto della riga di intestazione |
| `@ExcelColumn` | Campo | Mappa un campo a una colonna Excel |
| `@ExcelCellLayout` | Campo | Configura font, colore, bordo di una cella |
| `@ExcelFunction` | Campo | Applica una formula (somma, subtotale, personalizzata) a una colonna |
| `@ExcelFunctionRow` | Campo | Applica una formula per riga |
| `@ExcelChart` | Classe | Inserisce un grafico nel foglio |
| `@ExcelPivot` | Classe | Crea una tabella pivot |
| `@ExcelDataValidation` | Campo | Aggiunge validazione dati / lista a discesa |
| `@ExcelMergeRow` | Campo | Unisce valori consecutivi uguali in una colonna |
| `@ExcelDate` | Campo | Configura il formato della data |
| `@ExcelImage` | Campo | Inserisce un'immagine |
| `@ExcelQuery` | Classe | Popola le righe del foglio tramite query JPA |

### Modalità di generazione

| Metodo | Formato | Engine POI | Note |
|---|---|---|---|
| `createFileXls` | `.xls` | HSSF | Massimo 65.535 righe |
| `createFileXlsx` | `.xlsx` | XSSF | Set completo di funzionalità |
| `createBigDataFileXlsx` | `.xlsx` | SXSSF | Streaming — minore memoria, funzionalità limitate |

### Avvio rapido — generator-excel

```java
// 1. Definire l'entità riga
@ExcelSheetLayout
public class VenditeSheet extends SheetData<VenditeRow> { ... }

// 2. Annotare i campi della riga
public class VenditeRow implements RowSheet {
    @ExcelColumn(name = "Prodotto", position = 1)
    private String prodotto;

    @ExcelColumn(name = "Importo", position = 2)
    @ExcelFunction(function = "SUM")
    private BigDecimal importo;
}

// 3. Costruire e generare
@Autowired
private GenerateExcel generateExcel;

ReportExcel report = new ReportExcel("Report Vendite");
VenditeSheet sheet = new VenditeSheet("Vendite");
sheet.addRows(listaVendite);
report.addSheet(sheet);

byte[] xlsx = generateExcel.createFileXlsx(report);
```

### Proprietà Spring opzionali

| Proprietà | Tipo | Descrizione |
|---|---|---|
| `com.bld.commons.check.annotation` | Boolean | Valida la consistenza delle annotazioni all'avvio |
| `com.bld.commons.resource.cover.path` | String | Percorso del template del foglio di copertina |
| `com.bld.commons.report.excel.title` | String | Titolo di default del report |
| `com.bld.commons.report.excel.date` | String | Formato data di default per la copertina |
| `com.bld.commons.number.empty.rows` | Integer | Numero di righe vuote inserite dopo ogni foglio |
| `com.bld.commons.multiple.datasource` | Boolean | Abilita il supporto a più datasource per `@ExcelQuery` |

---

## read-excel

### Classi principali

| Classe | Descrizione |
|---|---|
| `ExcelRead` | Container che rappresenta il file Excel da leggere (byte array o `InputStream`) |
| `SheetRead<T>` | Classe astratta che rappresenta un foglio; contiene la lista delle righe lette |
| `MapSheetRead<ID, T>` | Variante di `SheetRead` che indicizza le righe per un campo chiave |
| `RowSheetRead` | Interfaccia implementata da ogni entità riga |
| `ReadExcel` | Interfaccia del servizio Spring da iniettare per avviare la lettura |

### Annotazioni principali

| Annotazione | Livello | Descrizione |
|---|---|---|
| `@ExcelReadSheet` | Classe | Mappa una classe a un foglio Excel per nome e riga/colonna iniziale |
| `@ExcelReadColumn` | Campo | Mappa un campo al nome intestazione di una colonna Excel |

### Tipi di campo supportati

`String`, `Integer`, `Double`, `Float`, `Long`, `BigDecimal`, `BigInteger`, `Boolean`, `Character`, `Date`, `Calendar`

### Avvio rapido — read-excel

```java
// 1. Definire l'entità riga
@ExcelReadSheet(name = "Vendite")
public class VenditeSheetRead extends SheetRead<VenditeRowRead> {
    public VenditeSheetRead() { super("Vendite"); }
}

// 2. Annotare i campi della riga
public class VenditeRowRead implements RowSheetRead {
    @ExcelReadColumn("Prodotto")
    private String prodotto;

    @ExcelReadColumn("Importo")
    private BigDecimal importo;
}

// 3. Leggere il file
@Autowired
private ReadExcel readExcel;

ExcelRead excelRead = new ExcelRead();
excelRead.setReportExcel(fileBytes);         // byte[], InputStream o percorso file
excelRead.setExcelType(ExcelType.XLSX);
excelRead.addSheetConvertion(VenditeSheetRead.class, "Vendite");
readExcel.convertExcelToEntity(excelRead);

VenditeSheetRead foglio = excelRead.getSheet(VenditeSheetRead.class, "Vendite");
List<VenditeRowRead> righe = foglio.getListRowSheet();
```

---

## Gestione delle eccezioni

| Eccezione | Modulo | Descrizione |
|---|---|---|
| `SpreadsheetException` | common-spreadsheet | Eccezione unchecked base per errori di configurazione |
| `ExcelGeneratorException` | generator-excel | Lanciata quando la generazione del foglio fallisce |
| `CsvGeneratorException` | generator-excel | Lanciata quando la generazione CSV fallisce |
| `ExcelReaderException` | read-excel | Lanciata in caso di errore di lettura; contiene un codice `ExcelExceptionType` strutturato |

---

## Licenza / License

MIT License — © Francesco Baldi
