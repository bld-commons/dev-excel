# generator-excel

Libreria Spring Boot per la generazione di file Excel (XLS, XLSX, SXSSF) e CSV da oggetti Java annotati.
Supporta dati statici, dati da query JPA, grafici, tabelle pivot, immagini, subtotali, formattazione condizionale e molto altro.

## Dipendenza Maven

```xml
<dependency>
    <groupId>com.github.bld-commons</groupId>
    <artifactId>generator-excel</artifactId>
    <version>5.1.3</version>
</dependency>
```

## Abilitazione

Aggiungere `@EnableExcelGenerator` alla propria applicazione Spring Boot o classe di test:

```java
@SpringBootTest
@EnableExcelGenerator
public class MyTest { ... }
```

---

## API Principale

### `GenerateExcel`

Interfaccia principale per la generazione di file Excel.

| Metodo | Descrizione |
|--------|-------------|
| `createFileXls(ReportExcel)` | Genera un file `.xls` (HSSF) come `byte[]` |
| `createFileXls(ReportExcel, OutputStream)` | Scrive un file `.xls` su uno stream |
| `createFileXlsx(ReportExcel)` | Genera un file `.xlsx` (XSSF) come `byte[]` |
| `createFileXlsx(ReportExcel, OutputStream)` | Scrive un file `.xlsx` su uno stream |
| `createBigDataFileXlsx(ReportExcel)` | Genera un file `.xlsx` con SXSSF (streaming, big data) come `byte[]` |
| `createBigDataFileXlsx(ReportExcel, OutputStream)` | Scrive un file `.xlsx` SXSSF su uno stream |

### `GenerateCsv`

| Metodo | Descrizione |
|--------|-------------|
| `generateCsv(CsvData<T>)` | Genera un file CSV come `byte[]` |

---

## Classi di Dominio

### `ReportExcel`

Container per tutti i fogli da generare.

```java
ReportExcel report = new ReportExcel("MyReport", listBaseSheet);
byte[] bytes = generateExcel.createFileXlsx(report);
```

### `SheetData<T extends RowSheet>`

Classe base astratta per fogli con dati statici (popolati manualmente).

```java
public class SalarySheet extends SheetData<SalaryRow> {
    public SalarySheet(String sheetName) {
        super(sheetName);
    }
}
```

| Metodo | Descrizione |
|--------|-------------|
| `addRows(T... rows)` | Aggiunge una o più righe |
| `getRows()` | Restituisce tutte le righe |
| `clear()` | Rimuove tutte le righe |

### `QuerySheetData<T extends RowSheet>`

Classe base astratta per fogli popolati tramite `@ExcelQuery` (SQL nativo o JPQL).

```java
@ExcelQuery("SELECT id, name FROM user WHERE surname = :surname")
public class UserSheet extends QuerySheetData<UserRow> {
    public UserSheet(String sheetName) { super(sheetName); }
}
```

| Metodo | Descrizione |
|--------|-------------|
| `getMapParameters()` | Restituisce la mappa dei parametri di query |
| `addParameters(String, Object)` | Aggiunge un singolo parametro |
| `addParameters(Map<String,Object>)` | Aggiunge più parametri |
| `resetParameters()` | Rimuove tutti i parametri |

### `RowSheet`

Interfaccia marker che ogni classe riga deve implementare.

### `CsvData<T extends CsvRow>` / `CsvRow`

Container e interfaccia marker per la generazione CSV.

---

## Riferimento Annotazioni

### Annotazioni a Livello di Foglio

#### `@ExcelSheetLayout`

Configura il layout generale del foglio (orientamento pagina, dimensione carta, area di stampa, ecc.).

#### `@ExcelHeaderLayout`

Configura lo stile visivo della riga di intestazione (colore di sfondo, font, bordo).

#### `@ExcelMarginSheet`

Imposta i margini di pagina.

| Attributo | Tipo     | Default | Descrizione          |
|-----------|----------|---------|----------------------|
| `top`     | `double` | `1.0`   | Margine superiore (cm) |
| `bottom`  | `double` | `1.0`   | Margine inferiore (cm) |
| `left`    | `double` | `1.0`   | Margine sinistro (cm)  |
| `right`   | `double` | `1.0`   | Margine destro (cm)    |

#### `@ExcelFreezePane`

Blocca righe e/o colonne.

#### `@ExcelQuery`

Applicata a una sottoclasse di `QuerySheetData` per definire la query SQL o JPQL per il popolamento automatico delle righe.

| Attributo       | Tipo      | Default | Descrizione                                                       |
|-----------------|-----------|---------|-------------------------------------------------------------------|
| `value`         | `String`  | —       | La stringa SQL o JPQL                                             |
| `nativeQuery`   | `boolean` | `true`  | `true` per SQL nativo, `false` per JPQL                           |
| `entityManager` | `String`  | `""`    | Nome del bean `EntityManager` per datasource multipli            |

```java
// SQL nativo — datasource singolo
@ExcelQuery("SELECT id, name, surname FROM user WHERE surname = :surname")

// JPQL — datasource singolo
@ExcelQuery(value = "select new com.example.UserRow(u.id, u.name) from User u",
            nativeQuery = false)

// Datasource multipli — specificare quale EntityManager usare
@ExcelQuery(value = "select new com.example.UserRow(u.id, u.name) from User u",
            nativeQuery = false,
            entityManager = Db2DatabaseConfiguration.DB2_ENTITY_MANAGER)
```

---

### Annotazioni Colonna/Riga

#### `@ExcelColumn`

Mappa un campo a una colonna Excel.

| Attributo | Tipo      | Default | Descrizione                                               |
|-----------|-----------|---------|------------------------------------------------------------|
| `name`    | `String`  | —       | Etichetta dell'intestazione della colonna                  |
| `index`   | `double`  | —       | Posizione della colonna (supporta decimali per sotto-ordinamento) |
| `comment` | `String`  | `""`    | Commento tooltip dell'intestazione                         |
| `ignore`  | `boolean` | `false` | Esclude questa colonna dall'output                         |

#### `@ExcelCellLayout`

Definizione completa dello stile di cella (bordo, allineamento, font, colore, formato numero).

| Attributo              | Tipo                  | Default                  |
|------------------------|-----------------------|--------------------------|
| `border`               | `ExcelBorder`         | THIN su tutti i lati     |
| `horizontalAlignment`  | `HorizontalAlignment` | `LEFT`                   |
| `verticalAlignment`    | `VerticalAlignment`   | `CENTER`                 |
| `font`                 | `ExcelFont`           | CALIBRI, dimensione 11   |
| `wrap`                 | `boolean`             | `true`                   |
| `rgbFont`              | `ExcelRgbColor[]`     | nero                     |
| `rgbForeground`        | `ExcelRgbColor[]`     | bianco                   |
| `fillPatternType`      | `FillPatternType`     | `SOLID_FOREGROUND`       |
| `precision`            | `int`                 | `-1` (nessuna restrizione) |
| `percent`              | `boolean`             | `false`                  |
| `locked`               | `boolean`             | `false`                  |
| `autoSizeColumn`       | `boolean`             | `false`                  |

#### `@ExcelFont`

| Attributo    | Tipo            | Default     |
|--------------|-----------------|-------------|
| `font`       | `FontType`      | `CALIBRI`   |
| `bold`       | `boolean`       | `false`     |
| `italic`     | `boolean`       | `false`     |
| `underline`  | `UnderlineType` | `NONE`      |
| `size`       | `short`         | `11`        |

#### `@ExcelBorder`

| Attributo | Tipo          | Default |
|-----------|---------------|---------|
| `top`     | `BorderStyle` | `NONE`  |
| `bottom`  | `BorderStyle` | `NONE`  |
| `left`    | `BorderStyle` | `NONE`  |
| `right`   | `BorderStyle` | `NONE`  |

#### `@ExcelRgbColor`

| Attributo | Tipo   | Default |
|-----------|--------|---------|
| `red`     | `byte` | `255`   |
| `green`   | `byte` | `255`   |
| `blue`    | `byte` | `255`   |

#### `@ExcelColumnWidth` / `@ExcelRowHeight`

Impostano larghezze di colonna o altezze di riga personalizzate.

#### `@ExcelNumberFormat`

| Attributo | Tipo     | Default | Descrizione                        |
|-----------|----------|---------|------------------------------------|
| `value`   | `String` | `""`    | Codice formato numero Excel        |

#### `@ExcelMergeRow`

Unisce celle consecutive in una colonna che hanno lo stesso valore.

| Attributo        | Tipo     | Default | Descrizione                                   |
|------------------|----------|---------|-----------------------------------------------|
| `referenceField` | `String` | —       | Nome del campo da usare come riferimento di merge |

#### `@ExcelHeaderCellLayout` / `@ExcelHeaderLayout`

Definiscono lo stile delle celle della riga di intestazione.

---

### Subtotali

#### `@ExcelSubtotals`

Applicata a livello di classe per configurare il raggruppamento dei subtotali.

| Attributo         | Tipo       | Descrizione                                      |
|-------------------|------------|--------------------------------------------------|
| `labelTotalGroup` | `String`   | Prefisso etichetta per ogni riga di subtotale    |
| `endLabel`        | `String`   | Testo aggiunto alla riga di chiusura del subtotale |
| `sumForGroup`     | `String[]` | Nomi dei campi che definiscono i livelli di raggruppamento |

#### `@ExcelSubtotal`

Applicata ai singoli campi per definire come vengono aggregati.

| Attributo                  | Tipo                     | Descrizione                               |
|----------------------------|--------------------------|-------------------------------------------|
| `dataConsolidateFunction`  | `DataConsolidateFunction`| Funzione di aggregazione (SUM, AVG, …)   |
| `excelCellLayout`          | `ExcelCellLayout`        | Stile per la cella del subtotale          |

```java
@ExcelSubtotals(labelTotalGroup = "Totale", endLabel = "totale", sumForGroup = {"city","state"})
public class SalaryRow implements RowSheet {

    @ExcelColumn(name = "Regione", index = 0)
    private String state;

    @ExcelColumn(name = "Città", index = 0.1)
    private String city;

    @ExcelColumn(name = "Importo", index = 1)
    @ExcelSubtotal(
        dataConsolidateFunction = DataConsolidateFunction.SUM,
        excelCellLayout = @ExcelCellLayout(font = @ExcelFont(bold = true))
    )
    private Double amount;
}
```

---

### Formattazione Condizionale

#### `@ExcelConditionCellLayouts` / `@ExcelConditionCellLayout`

Applica uno stile di cella quando una condizione formula è vera.

| Attributo          | Tipo              | Descrizione                                                    |
|--------------------|-------------------|----------------------------------------------------------------|
| `columns`          | `String[]`        | Nomi delle colonne a cui applicare la formattazione condizionale |
| `condition`        | `String`          | Formula Excel (può riferirsi ai campi tramite `${field}`)      |
| `excelCellLayout`  | `ExcelCellLayout` | Stile applicato quando la condizione è vera                    |

```java
@ExcelConditionCellLayouts(
    @ExcelConditionCellLayout(
        columns = {"state", "city", "district"},
        condition = "ISNUMBER(SEARCH(\"totale\", LOWER(${state[start]})))",
        excelCellLayout = @ExcelCellLayout(
            rgbFont = @ExcelRgbColor(red = (byte)255, blue = 0, green = 0),
            rgbForeground = @ExcelRgbColor(blue = (byte)255, red = 0, green = 0)
        )
    )
)
public class SalaryRow implements RowSheet { ... }
```

---

### Grafici

#### `@ExcelCharts` / `@ExcelChart`

Inserisce uno o più grafici in un foglio.

| Attributo (ExcelChart)    | Tipo                   | Default       | Descrizione                          |
|---------------------------|------------------------|---------------|--------------------------------------|
| `id`                      | `String`               | —             | Identificatore univoco del grafico   |
| `excelChartCategories`    | `ExcelChartCategory[]` | —             | Definizioni delle serie di dati      |
| `xAxis`                   | `String`               | —             | Riferimento intervallo cella asse X  |
| `chartTypes`              | `ChartTypes`           | `LINE`        | Tipo di grafico (LINE, BAR, PIE, …)  |
| `sizeRow`                 | `int`                  | —             | Altezza del grafico in righe         |
| `sizeColumn`              | `int`                  | —             | Larghezza del grafico in colonne     |
| `group`                   | `boolean`              | `false`       | Raggruppa le serie per campo categoria |
| `title`                   | `String`               | `""`          | Titolo del grafico                   |
| `legendPosition`          | `LegendPosition`       | `BOTTOM`      | Posizione della legenda              |

#### `@ExcelChartCategory`

| Attributo    | Tipo     | Descrizione                                          |
|--------------|----------|------------------------------------------------------|
| `fieldName`  | `String` | Campo/espressione per l'etichetta della serie        |
| `function`   | `String` | Espressione dell'intervallo celle per i dati serie   |

```java
@ExcelCharts(excelCharts = {
    @ExcelChart(
        id = "nascite",
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

### Tabelle Pivot

#### `@ExcelPivot`

Crea una tabella pivot dai dati del foglio (solo XSSF).

Correlate: `@ExcelPivotRow`, `@ExcelPivotColumn`, `@ExcelPivotColumnFunction`, `@ExcelPivotFilter`.

---

### Super Intestazioni

#### `@ExcelSuperHeaders` / `@ExcelSuperHeader` / `@ExcelSuperHeaderCell`

Aggiunge una riga di intestazione unificata che si estende su più colonne sopra l'intestazione standard.

---

### Immagini

#### `@ExcelImage`

Incorpora un'immagine in una cella. Applicabile a campi `byte[]` (dati immagine raw) o `String` (percorso file).

| Attributo      | Tipo     | Default | Descrizione                       |
|----------------|----------|---------|-----------------------------------|
| `resizeHeight` | `double` | `1.0`   | Fattore di ridimensionamento altezza |
| `resizeWidth`  | `double` | `1.0`   | Fattore di ridimensionamento larghezza |

```java
@ExcelColumn(name = "Foto", index = 4)
@ExcelImage
private byte[] image;

@ExcelColumn(name = "Percorso", index = 5)
@ExcelImage(resizeHeight = 0.7, resizeWidth = 0.6)
private String path;
```

---

### Etichette e Riepilogo

#### `@ExcelLabel`

Aggiunge una cella con testo libero sopra l'area dati del foglio.

#### `@ExcelSummary`

Configura una riga di riepilogo aggiunta dopo l'ultima riga di dati.

---

### Funzioni e Formule

#### `@ExcelFunction` / `@ExcelFunctionRow` / `@ExcelFunctionRows`

Aggiungono righe con formule al foglio.

#### `@ExcelFunctionSubTotal` / `@ExcelFormulaAlias`

Definiscono formule di subtotale e alias di formula.

---

### Validazione Dati

#### `@ExcelDataValidation` / `@ExcelDropDown`

Aggiungono la validazione con lista a discesa a una colonna.

---

## Esempio Rapido

### Dati Statici — SalarySheet

```java
// Classe riga
@ExcelSubtotals(labelTotalGroup = "Totale", endLabel = "totale",
                sumForGroup = {"city", "state"})
@ExcelConditionCellLayouts(
    @ExcelConditionCellLayout(
        columns = {"state", "city", "district"},
        condition = "ISNUMBER(SEARCH(\"totale\", LOWER(${state[start]})))",
        excelCellLayout = @ExcelCellLayout(
            rgbFont       = @ExcelRgbColor(red = (byte) 255, blue = 0, green = 0),
            rgbForeground = @ExcelRgbColor(blue = (byte) 255, red = 0, green = 0)
        )
    )
)
public class SalaryRow implements RowSheet {

    @ExcelColumn(name = "Regione", index = 0)
    @ExcelCellLayout
    private String state;

    @ExcelColumn(name = "Città", index = 0.1)
    @ExcelCellLayout
    private String city;

    @ExcelColumn(name = "Distretto", index = 0.2)
    @ExcelCellLayout
    private String district;

    @ExcelColumn(name = "Importo", index = 1)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    @ExcelSubtotal(
        dataConsolidateFunction = DataConsolidateFunction.SUM,
        excelCellLayout = @ExcelCellLayout(
            horizontalAlignment = HorizontalAlignment.RIGHT,
            font = @ExcelFont(bold = true)
        )
    )
    private Double amount;

    // costruttore, getter, setter...
}

// Classe foglio
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
public class SalarySheet extends SheetData<SalaryRow> {
    public SalarySheet(String sheetName) { super(sheetName); }
}

// Test / utilizzo
@SpringBootTest
@EnableExcelGenerator
public class SalaryTest {

    @Autowired
    private GenerateExcel generateExcel;

    @Test
    public void testSalary() throws Exception {
        SalarySheet sheet = new SalarySheet("salary");
        sheet.addRows(new SalaryRow("Italia",    "Roma",       "A", 32.0));
        sheet.addRows(new SalaryRow("Italia",    "Roma",       "B", 25.5));
        sheet.addRows(new SalaryRow("Italia",    "Milano",     "A", 21.0));
        sheet.addRows(new SalaryRow("Inghilterra","Londra",    "A", 31.0));
        sheet.addRows(new SalaryRow("Inghilterra","Manchester","A",  8.0));

        ReportExcel report = new ReportExcel("test", List.of(sheet));
        byte[] bytes = generateExcel.createFileXlsx(report);
        SpreadsheetUtils.writeToFile("/mnt/report/", report.getTitle(), ".xlsx", bytes);
    }
}
```

### Dati da Query — UtenteSheet (JPA)

```java
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
@ExcelQuery("SELECT id_utente, nome, cognome, data_nascita, image, path, abilitato " +
            "FROM utente WHERE cognome IN (:cognome) ORDER BY cognome, nome")
public class UtenteSheet extends QuerySheetData<UtenteRow> {
    public UtenteSheet(String sheetName) { super(sheetName); }
}

// Utilizzo
UtenteSheet sheet = new UtenteSheet("Utenti");
sheet.getMapParameters().put("cognome", Arrays.asList("Rossi", "Bianchi"));

ReportExcel report = new ReportExcel("report", List.of(sheet));
byte[] bytes = generateExcel.createFileXlsx(report);
```

### Foglio con Grafico

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

## Proprietà Spring

| Proprietà                              | Descrizione                                                        |
|----------------------------------------|--------------------------------------------------------------------|
| `com.bld.commons.check.annotation`    | Abilita la validazione delle annotazioni all'avvio (`true`/`false`) |
| `com.bld.commons.date.format`         | Formato data globale (usato con `ColumnDateFormat.PARAMETER`)      |
| `com.bld.commons.multiple.datasource` | Abilita il supporto a datasource multipli (`true`/`false`)         |

```yaml
com.bld.commons:
  check.annotation: true
  date.format: dd/MM/yyyy
  multiple.datasource: false
```

---

## Supporto Formati

| Funzionalità               | XLS (HSSF) | XLSX (XSSF) | SXSSF (Big Data) |
|----------------------------|:----------:|:-----------:|:----------------:|
| Dati statici               | si         | si          | si               |
| Dati da query JPA          | si         | si          | si               |
| Subtotali                  | si         | si          | no               |
| Grafici                    | no         | si          | no               |
| Tabelle pivot              | no         | si          | no               |
| Formattazione condizionale | si         | si          | no               |
| Merge righe                | si         | si          | no               |
| Immagini                   | si         | si          | no               |
| Super intestazioni         | si         | si          | no               |
