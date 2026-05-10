# dev-excel

[![Maven Central — generator-excel](https://img.shields.io/maven-central/v/com.github.bld-commons.excel/generator-excel?label=generator-excel)](https://central.sonatype.com/artifact/com.github.bld-commons.excel/generator-excel)
[![Maven Central — read-excel](https://img.shields.io/maven-central/v/com.github.bld-commons.excel/read-excel?label=read-excel)](https://central.sonatype.com/artifact/com.github.bld-commons.excel/read-excel)
[![Maven Central — common-spreadsheet](https://img.shields.io/maven-central/v/com.github.bld-commons/common-spreadsheet?label=common-spreadsheet)](https://central.sonatype.com/artifact/com.github.bld-commons/common-spreadsheet)
[![Javadoc — generator-excel](https://javadoc.io/badge2/com.github.bld-commons.excel/generator-excel/javadoc.svg)](https://javadoc.io/doc/com.github.bld-commons.excel/generator-excel)
[![Javadoc — read-excel](https://javadoc.io/badge2/com.github.bld-commons.excel/read-excel/javadoc.svg)](https://javadoc.io/doc/com.github.bld-commons.excel/read-excel)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Java 17+](https://img.shields.io/badge/Java-17%2B-blue.svg)](https://adoptium.net/)

## English

This project provides two libraries built on top of Apache POI to simplify the generation and reading of Excel and CSV files in Spring Boot applications through an annotation-driven approach.

### Modules

| Module | Type | Description |
|---|---|---|
| [**common-spreadsheet**](common-spreadsheet/README.md) | Library | Shared annotations, utilities and exceptions used by both libraries |
| [**generator-excel**](generator-excel/README.md) | Library | Generates `.xls`, `.xlsx` and CSV files from annotated Java objects |
| [**read-excel**](read-excel/README.md) | Library | Reads `.xls`, `.xlsx` and CSV files and maps them to annotated Java objects |
| [**report-excel**](report-excel/README.md) | Integration test | Static data, Excel/CSV reading, REST endpoints — no database required |
| [**report-excel-jpa**](report-excel-jpa/README.md) | Integration test | Query-driven generation with JPA, charts, pivot tables, big data |
| [**report-excel-multiple-datasource**](report-excel-multiple-datasource/README.md) | Integration test | Multi-sheet generation from two different databases simultaneously |

### Requirements

- Java 17+
- Spring Boot 3.x
- Apache POI 5.x (included as transitive dependency)

### Maven Dependencies

```xml
<!-- Excel / CSV Generator -->
<dependency>
    <groupId>com.github.bld-commons.excel</groupId>
    <artifactId>generator-excel</artifactId>
    <version>5.2.0</version>
</dependency>

<!-- Excel / CSV Reader -->
<dependency>
    <groupId>com.github.bld-commons.excel</groupId>
    <artifactId>read-excel</artifactId>
    <version>5.2.0</version>
</dependency>
```

### Enable the Libraries

Add one of the following annotations to your Spring Boot configuration class or test class:

```java
@EnableExcelGenerator  // activates generator-excel
@EnableExcelRead       // activates read-excel
```

---

## generator-excel

> Full documentation: [generator-excel/README.md](generator-excel/README.md)

### Main Classes

| Class | Description |
|---|---|
| `ReportExcel` | Root object representing the entire Excel document |
| `BaseSheet` | Abstract base class for all sheet types (max 31-character name) |
| `SheetData<T>` | Sheet backed by a typed list of rows |
| `SheetDynamicData<T>` | Variant of `SheetData` that adds runtime extra columns via `addExtraColumnAnnotation(key, cfg)` — declare layout/format/function programmatically without recompiling the row class |
| `QuerySheetData<T>` | Sheet populated automatically from a JPA/SQL query via `@ExcelQuery` |
| `RowSheet` | Interface implemented by each row entity |
| `LockedSheet` | Marker interface; override `password()` for runtime sheet protection (alternative/complement to `@ExcelLocked`) |
| `ExtraColumnAnnotation` | Fluent runtime builder for an extra column's annotations: `cellLayout`, `column`, `dateFormat`, `columnWidth`, `headerCellLayout`, `mergeRow`, `function`, `dropDown`, `subtotal`, `booleanText`, `dataValidation`, `numberFormat`, `image` |
| `GenerateExcel` | Service interface injected via Spring to trigger generation |
| `GenerateCsv` | Service interface for CSV generation |

### Key Annotations

| Annotation | Level | Description |
|---|---|---|
| `@ExcelSheetLayout` | Class | Configures sheet layout, start row/column, merge behaviour |
| `@ExcelHeaderLayout` | Class | Configures the header row appearance |
| `@ExcelMarginSheet` | Class | Sets page margins |
| `@ExcelLocked` | Class | Marks the sheet as protected; combine with `LockedSheet` for runtime password |
| `@ExcelColumn` | Field | Maps a field to an Excel column |
| `@ExcelCellLayout` | Field | Configures font, colour, border, alignment of a cell |
| `@ExcelDate` | Field | Configures date format; `timezone()` (default `${spring.jackson.time-zone:}`) drives `LocalDateTime`/`Instant`/`OffsetDateTime` conversion |
| `@ExcelBooleanText` | Field | Displays boolean values as custom text |
| `@ExcelImage` | Field | Embeds an image from byte array or file path |
| `@ExcelMergeRow` | Field | Merges equal consecutive values in a column; `value()` names the *driver* field — the column merges only when the driver field has merged |
| `@ExcelFunctionMergeRow` | Field | Computed formula column over a merged region; default cell layout is `locked = true` |
| `@ExcelSubtotals` / `@ExcelSubtotal` | Class / Field | Inserts grouped subtotal rows |
| `@ExcelConditionCellLayouts` | Class | Applies conditional cell formatting via Excel formula |
| `@ExcelCharts` / `@ExcelChart` | Class | Embeds one or more charts |
| `@ExcelPivot` | Class | Creates a pivot table (XSSF only) |
| `@ExcelSuperHeaders` | Class | Adds a merged header row above the regular header |
| `@ExcelDataValidation` | Field | Adds dropdown list validation |
| `@ExcelQuery` | Class | Populates rows from a native SQL or JPQL query |
| `@ExcelFunctionRows` / `@ExcelFunctionRow` | Class | Adds computed formula columns entirely via annotation; `${fieldName}` placeholders resolve to cell addresses; default cell layout is `locked = true` |

### Generation Methods

| Method | Format | POI Engine | Notes |
|---|---|---|---|
| `createFileXls` | `.xls` | HSSF | Max 65,535 rows |
| `createFileXlsx` | `.xlsx` | XSSF | Full feature set |
| `createBigDataFileXlsx` | `.xlsx` | SXSSF | Streaming — lower memory, limited features |

### Feature Comparison

| Feature | HSSF (.xls) | XSSF (.xlsx) | SXSSF (big data) |
|---|:---:|:---:|:---:|
| Static data | yes | yes | yes |
| JPA query data | yes | yes | yes |
| Cell styling | yes | yes | yes |
| Images | yes | yes | no |
| Charts | no | yes | no |
| Pivot tables | no | yes | no |
| Subtotals | yes | yes | no |
| Conditional formatting | yes | yes | no |
| Merge rows | yes | yes | no |
| Super headers | yes | yes | no |

---

## read-excel

> Full documentation: [read-excel/README.md](read-excel/README.md)

### Main Classes

| Class | Description |
|---|---|
| `ExcelRead` | Container representing the Excel file to be read (byte array, `InputStream` or file path) |
| `SheetRead<T>` | Abstract class representing a sheet; holds the list of parsed rows |
| `MapSheetRead<ID, T>` | Variant of `SheetRead` that indexes rows by a key field for O(1) lookup |
| `RowSheetRead` | Interface implemented by each row entity |
| `ReadExcel` | Service interface injected via Spring to trigger reading |
| `ReadCsv` | Service interface for CSV reading |

### Key Annotations

| Annotation | Level | Description |
|---|---|---|
| `@ExcelReadSheet` | Class | Configures sheet name, start row and start column |
| `@ExcelReadColumn` | Field | Maps a field to an Excel column header name |
| `@ExcelBooleanText` | Field | Maps a string cell ("Sì"/"No", "Yes"/"No", …) to a `Boolean` field |
| `@ExcelDate` | Field | Specifies the date format when reading string cells |
| `@CsvSettings` | Class | Configures CSV parsing (delimiter, header skip, parallel mode) |
| `@CsvDate` | Field | Specifies the date format for CSV date columns |

### Supported Field Types

`String`, `Integer`, `Double`, `Float`, `Long`, `BigDecimal`, `Boolean`, `Character`, `Date`, `Calendar`, `LocalDate`, `LocalDateTime`, `Instant`, `OffsetDateTime`

> **Performance:** per-class metadata is cached in a `ConcurrentHashMap` after the first read; subsequent reads of the same class incur zero reflection overhead. CSV reading supports parallel parsing via `@CsvSettings(parallel = true)`.

> **Breaking (5.2.0):** `SheetRead`/`MapSheetRead`/`CsvRead` accessors renamed `listRowSheet` → `rows`, `getListRowSheet()` → `getRows()`, `addRowSheet(...)` → `addRow(...)`.

---

## Spring Properties

| Property | Description |
|---|---|
| `com.bld.commons.check.annotation` | Validates annotation consistency at startup |
| `com.bld.commons.resource.cover.path` | Path to the cover sheet template |
| `com.bld.commons.date.format` | Global date format (used with `ColumnDateFormat.PARAMETER`) |
| `com.bld.commons.report.excel.title` | Cell address for the report title in the cover |
| `com.bld.commons.report.excel.date` | Cell address for the date in the cover |
| `com.bld.commons.multiple.datasource` | Enables multiple datasource support for `@ExcelQuery` |

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
```

---

## Exception Handling

| Exception | Module | Description |
|---|---|---|
| `SpreadsheetException` | common-spreadsheet | Base unchecked exception for configuration errors |
| `ExcelGeneratorException` | generator-excel | Thrown when sheet generation fails |
| `CsvGeneratorException` | generator-excel | Thrown when CSV generation fails |
| `ExcelReaderException` | read-excel | Thrown when reading fails; carries a structured `ExcelExceptionType` code and parameter |

---

## Quick Start Examples

See the module-specific READMEs for full annotated examples:

- [Static data with subtotals and conditional formatting](report-excel/README.md#salarytest----static-data-with-subtotals-and-conditional-formatting)
- [JPA query-driven sheets with charts](report-excel-jpa/README.md#reporttestjpa)
- [Multiple datasources in a single workbook](report-excel-multiple-datasource/README.md#test----multi-datasource-excel-generation)
- [Reading multi-sheet XLSX files](read-excel/README.md#reading-a-multi-sheet-xlsx)
- [Row filtering with filtered() hook](read-excel/README.md#reading-with-custom-filtering)
- [CSV reading with Bean Validation](read-excel/README.md#reading-a-csv-with-bean-validation)

---

## Javadoc

- [generator-excel Javadoc](https://javadoc.io/doc/com.github.bld-commons.excel/generator-excel/latest/index.html)
- [read-excel Javadoc](https://javadoc.io/doc/com.github.bld-commons.excel/read-excel/latest/index.html)

---

---

## Italiano

Questo progetto fornisce due librerie basate su Apache POI per semplificare la generazione e la lettura di file Excel e CSV in applicazioni Spring Boot attraverso un approccio annotation-driven.

### Moduli

| Modulo | Tipo | Descrizione |
|---|---|---|
| [**common-spreadsheet**](common-spreadsheet/README.md) | Libreria | Annotazioni, utility ed eccezioni condivise da entrambe le librerie |
| [**generator-excel**](generator-excel/README.md) | Libreria | Genera file `.xls`, `.xlsx` e CSV da oggetti Java annotati |
| [**read-excel**](read-excel/README.md) | Libreria | Legge file `.xls`, `.xlsx` e CSV e li mappa su oggetti Java annotati |
| [**report-excel**](report-excel/README.md) | Test di integrazione | Dati statici, lettura Excel/CSV, endpoint REST — senza database |
| [**report-excel-jpa**](report-excel-jpa/README.md) | Test di integrazione | Generazione query-driven con JPA, grafici, tabelle pivot, big data |
| [**report-excel-multiple-datasource**](report-excel-multiple-datasource/README.md) | Test di integrazione | Generazione multi-foglio da due database diversi in simultanea |

### Requisiti

- Java 17+
- Spring Boot 3.x
- Apache POI 5.x (incluso come dipendenza transitiva)

### Dipendenze Maven

```xml
<!-- Generazione Excel / CSV -->
<dependency>
    <groupId>com.github.bld-commons.excel</groupId>
    <artifactId>generator-excel</artifactId>
    <version>5.2.0</version>
</dependency>

<!-- Lettura Excel / CSV -->
<dependency>
    <groupId>com.github.bld-commons.excel</groupId>
    <artifactId>read-excel</artifactId>
    <version>5.2.0</version>
</dependency>
```

### Abilitare le Librerie

Aggiungere una delle seguenti annotazioni alla propria classe di configurazione Spring Boot o di test:

```java
@EnableExcelGenerator  // attiva generator-excel
@EnableExcelRead       // attiva read-excel
```

---

## generator-excel

> Documentazione completa: [generator-excel/README.md](generator-excel/README.md)

### Classi Principali

| Classe | Descrizione |
|---|---|
| `ReportExcel` | Oggetto radice che rappresenta l'intero documento Excel |
| `BaseSheet` | Classe astratta base per tutti i tipi di foglio (nome max 31 caratteri) |
| `SheetData<T>` | Foglio associato a una lista tipizzata di righe |
| `SheetDynamicData<T>` | Variante di `SheetData` che aggiunge colonne extra a runtime tramite `addExtraColumnAnnotation(key, cfg)` — layout/format/funzioni dichiarate programmaticamente senza ricompilare la riga |
| `QuerySheetData<T>` | Foglio popolato automaticamente da una query JPA/SQL tramite `@ExcelQuery` |
| `RowSheet` | Interfaccia implementata da ogni entità riga |
| `LockedSheet` | Interfaccia marker; sovrascrivere `password()` per la protezione del foglio a runtime (alternativa/complemento a `@ExcelLocked`) |
| `ExtraColumnAnnotation` | Builder fluente runtime per le annotazioni di una colonna extra: `cellLayout`, `column`, `dateFormat`, `columnWidth`, `headerCellLayout`, `mergeRow`, `function`, `dropDown`, `subtotal`, `booleanText`, `dataValidation`, `numberFormat`, `image` |
| `GenerateExcel` | Interfaccia del servizio Spring da iniettare per la generazione |
| `GenerateCsv` | Interfaccia del servizio Spring per la generazione CSV |

### Annotazioni Principali

| Annotazione | Livello | Descrizione |
|---|---|---|
| `@ExcelSheetLayout` | Classe | Configura il layout del foglio, riga/colonna iniziale, comportamento merge |
| `@ExcelHeaderLayout` | Classe | Configura l'aspetto della riga di intestazione |
| `@ExcelMarginSheet` | Classe | Imposta i margini di pagina |
| `@ExcelLocked` | Classe | Marca il foglio come protetto; combinare con `LockedSheet` per password runtime |
| `@ExcelColumn` | Campo | Mappa un campo a una colonna Excel |
| `@ExcelCellLayout` | Campo | Configura font, colore, bordo, allineamento di una cella |
| `@ExcelDate` | Campo | Configura il formato data; `timezone()` (default `${spring.jackson.time-zone:}`) gestisce conversione `LocalDateTime`/`Instant`/`OffsetDateTime` |
| `@ExcelBooleanText` | Campo | Rappresenta i booleani con testo personalizzato |
| `@ExcelImage` | Campo | Inserisce un'immagine da byte array o percorso file |
| `@ExcelMergeRow` | Campo | Unisce valori consecutivi uguali in una colonna; `value()` indica il *driver field* — la colonna viene fusa solo quando il driver risulta fuso |
| `@ExcelFunctionMergeRow` | Campo | Colonna formula calcolata su area mergiata; layout cella di default `locked = true` |
| `@ExcelSubtotals` / `@ExcelSubtotal` | Classe / Campo | Inserisce righe di subtotale raggruppate |
| `@ExcelConditionCellLayouts` | Classe | Applica formattazione condizionale tramite formula Excel |
| `@ExcelCharts` / `@ExcelChart` | Classe | Inserisce uno o più grafici |
| `@ExcelPivot` | Classe | Crea una tabella pivot (solo XSSF) |
| `@ExcelSuperHeaders` | Classe | Aggiunge una riga di intestazione unificata sopra quella standard |
| `@ExcelDataValidation` | Campo | Aggiunge validazione con lista a discesa |
| `@ExcelQuery` | Classe | Popola le righe da query SQL nativa o JPQL |
| `@ExcelFunctionRows` / `@ExcelFunctionRow` | Classe | Aggiunge colonne formula calcolate interamente tramite annotazione; i segnaposto `${nomeCampo}` vengono risolti in indirizzi cella; layout cella di default `locked = true` |

### Modalità di Generazione

| Metodo | Formato | Engine POI | Note |
|---|---|---|---|
| `createFileXls` | `.xls` | HSSF | Massimo 65.535 righe |
| `createFileXlsx` | `.xlsx` | XSSF | Set completo di funzionalità |
| `createBigDataFileXlsx` | `.xlsx` | SXSSF | Streaming — minore memoria, funzionalità limitate |

### Confronto Funzionalità

| Funzionalità | HSSF (.xls) | XSSF (.xlsx) | SXSSF (big data) |
|---|:---:|:---:|:---:|
| Dati statici | si | si | si |
| Dati da query JPA | si | si | si |
| Stile celle | si | si | si |
| Immagini | si | si | no |
| Grafici | no | si | no |
| Tabelle pivot | no | si | no |
| Subtotali | si | si | no |
| Formattazione condizionale | si | si | no |
| Merge righe | si | si | no |
| Super intestazioni | si | si | no |

---

## read-excel

> Documentazione completa: [read-excel/README.md](read-excel/README.md)

### Classi Principali

| Classe | Descrizione |
|---|---|
| `ExcelRead` | Container che rappresenta il file Excel da leggere (byte array, `InputStream` o percorso) |
| `SheetRead<T>` | Classe astratta che rappresenta un foglio; contiene la lista delle righe lette |
| `MapSheetRead<ID, T>` | Variante di `SheetRead` che indicizza le righe per un campo chiave (ricerca O(1)) |
| `RowSheetRead` | Interfaccia implementata da ogni entità riga |
| `ReadExcel` | Interfaccia del servizio Spring da iniettare per la lettura |
| `ReadCsv` | Interfaccia del servizio Spring per la lettura CSV |

### Annotazioni Principali

| Annotazione | Livello | Descrizione |
|---|---|---|
| `@ExcelReadSheet` | Classe | Configura nome foglio, riga e colonna iniziale |
| `@ExcelReadColumn` | Campo | Mappa un campo al nome di intestazione di una colonna Excel |
| `@ExcelBooleanText` | Campo | Mappa una cella stringa ("Sì"/"No", "Yes"/"No", …) a un campo `Boolean` |
| `@ExcelDate` | Campo | Specifica il formato data per la lettura di celle stringa |
| `@CsvSettings` | Classe | Configura il parsing CSV (delimitatore, skip intestazione, modalità parallela) |
| `@CsvDate` | Campo | Specifica il formato data per le colonne data CSV |

### Tipi di Campo Supportati

`String`, `Integer`, `Double`, `Float`, `Long`, `BigDecimal`, `Boolean`, `Character`, `Date`, `Calendar`, `LocalDate`, `LocalDateTime`, `Instant`, `OffsetDateTime`

> **Performance:** i metadati per classe vengono memorizzati in una `ConcurrentHashMap` dopo la prima lettura; le letture successive della stessa classe non eseguono alcuna reflection. La lettura CSV supporta il parsing parallelo tramite `@CsvSettings(parallel = true)`.

> **Breaking (5.2.0):** gli accessor di `SheetRead`/`MapSheetRead`/`CsvRead` sono stati rinominati: `listRowSheet` → `rows`, `getListRowSheet()` → `getRows()`, `addRowSheet(...)` → `addRow(...)`.

---

## Proprietà Spring

| Proprietà | Descrizione |
|---|---|
| `com.bld.commons.check.annotation` | Valida la consistenza delle annotazioni all'avvio |
| `com.bld.commons.resource.cover.path` | Percorso del template del foglio di copertina |
| `com.bld.commons.date.format` | Formato data globale (usato con `ColumnDateFormat.PARAMETER`) |
| `com.bld.commons.report.excel.title` | Indirizzo cella per il titolo nella copertina |
| `com.bld.commons.report.excel.date` | Indirizzo cella per la data nella copertina |
| `com.bld.commons.multiple.datasource` | Abilita il supporto a più datasource per `@ExcelQuery` |

---

## Gestione delle Eccezioni

| Eccezione | Modulo | Descrizione |
|---|---|---|
| `SpreadsheetException` | common-spreadsheet | Eccezione unchecked base per errori di configurazione |
| `ExcelGeneratorException` | generator-excel | Lanciata quando la generazione del foglio fallisce |
| `CsvGeneratorException` | generator-excel | Lanciata quando la generazione CSV fallisce |
| `ExcelReaderException` | read-excel | Lanciata in caso di errore di lettura; contiene codice `ExcelExceptionType` e parametro |

---

## Esempi Rapidi

Vedere i README dei singoli moduli per esempi completi e annotati:

- [Dati statici con subtotali e formattazione condizionale](report-excel/README.md#salarytest----static-data-with-subtotals-and-conditional-formatting)
- [Fogli da query JPA con grafici](report-excel-jpa/README.md#reporttestjpa)
- [Sorgenti dati multiple in un singolo workbook](report-excel-multiple-datasource/README.md#test----multi-datasource-excel-generation)
- [Lettura di file XLSX multi-foglio](read-excel/README.md#reading-a-multi-sheet-xlsx)
- [Filtro righe con hook filtered()](read-excel/README.md#reading-with-custom-filtering)
- [Lettura CSV con Bean Validation](read-excel/README.md#reading-a-csv-with-bean-validation)

---

## Javadoc

- [generator-excel Javadoc](https://javadoc.io/doc/com.github.bld-commons.excel/generator-excel/latest/index.html)
- [read-excel Javadoc](https://javadoc.io/doc/com.github.bld-commons.excel/read-excel/latest/index.html)

---

## Licenza / License

MIT License — © Francesco Baldi
