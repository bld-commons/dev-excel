# generator-excel

Libreria Spring Boot per la generazione di file Excel (XLS, XLSX, SXSSF) e CSV da oggetti Java annotati.
Supporta dati statici, dati da query JPA, grafici, tabelle pivot, immagini, subtotali, formattazione condizionale e molto altro.

## Dipendenza Maven

```xml
<dependency>
    <groupId>com.github.bld-commons</groupId>
    <artifactId>generator-excel</artifactId>
    <version>5.2.0</version>
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

#### Protezione Foglio — `@ExcelLocked` e `LockedSheet`

La protezione di un foglio può essere attivata in due modi alternativi.

**1. `@ExcelLocked` — password statica o da property Spring**

Annotazione da applicare direttamente sulla classe del foglio (non più come attributo di `@ExcelSheetLayout`). Il foglio viene bloccato; se `value` risolve a una stringa non vuota viene impostata una password, altrimenti il foglio è bloccato senza password.

| Attributo | Tipo     | Default                            | Descrizione |
|-----------|----------|------------------------------------|-------------|
| `value`   | `String` | `${bld.commons.sheet.password:}` | Password (supporta placeholder Spring) |

```java
// Password fissa
@ExcelSheetLayout
@ExcelLocked("myPassword")
public class MySheet extends SheetData<MyRow> { ... }

// Password da property Spring
@ExcelSheetLayout
@ExcelLocked("${my.sheet.password}")
public class MySheet extends SheetData<MyRow> { ... }

// Lock senza password (usa la property globale, di default vuota)
@ExcelSheetLayout
@ExcelLocked
public class MySheet extends SheetData<MyRow> { ... }
```

**2. `LockedSheet` — password dinamica a runtime**

Implementa l'interfaccia `LockedSheet` sulla classe del foglio per restituire la password a runtime. Se `password()` restituisce `null` o una stringa vuota il foglio è bloccato senza password.

```java
@ExcelSheetLayout
public class DateSheet extends SheetData<DateRow> implements LockedSheet {

    private final String sheetPassword;

    public DateSheet(String sheetName, String sheetPassword) {
        super(sheetName);
        this.sheetPassword = sheetPassword;
    }

    @Override
    public String password() {
        return sheetPassword;
    }
}
```

> Entrambe le modalità sono compatibili con `SheetData`, `SheetSummary` e `MergeSheet`.

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

Unisce celle consecutive in una colonna quando il valore del campo annotato non cambia tra una riga e la successiva.

| Attributo        | Tipo       | Default | Descrizione                                                                 |
|------------------|------------|---------|-----------------------------------------------------------------------------|
| `referenceField` | `String[]` | `{}`    | Nomi dei campi (o `nameFunction`) usati come condizione di interruzione del merge |

**Comportamento**

| Configurazione | Effetto |
|---|---|
| `@ExcelMergeRow` (senza parametri) | Merge basato sul valore della cella stessa; si interrompe quando il valore cambia. Valido solo sulla prima colonna. |
| `@ExcelMergeRow(referenceField = "campo")` | Merge interrotto quando il campo `campo` cambia nella riga corrente rispetto alla precedente. |
| `@ExcelMergeRow(referenceField = {"campo1","campo2"})` | Merge interrotto quando uno qualsiasi dei campi elencati cambia. |

Ogni valore in `referenceField` deve corrispondere a:
- un nome di campo Java della classe `RowSheet`, oppure
- un `nameFunction` di una colonna `@ExcelFunction` / `ExtraColumnAnnotation`.

Un valore blank o non corrispondente ad alcun campo fa lanciare una `ExcelGeneratorException` a runtime.

> `@ExcelMergeRow` funziona solo se `notMerge = false` in `@ExcelSheetLayout` (valore di default).

```java
// Merge basato sul valore della cella — solo prima colonna
@ExcelColumn(name = "Matricola", index = 1)
@ExcelMergeRow
private Integer matricola;

// Merge interrotto quando cambia "matricola"
@ExcelColumn(name = "Nome", index = 2)
@ExcelMergeRow(referenceField = "matricola")
private String nome;

// Merge interrotto quando cambia "matricola" o "cognome"
@ExcelColumn(name = "Genere", index = 5)
@ExcelMergeRow(referenceField = {"matricola", "cognome"})
private String genere;
```

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

Le righe con formule vengono aggiunte sotto l'area dati annotando la classe `RowSheet` con `@ExcelFunctionRows`.
Ogni riga formula è configurata con `@ExcelFunctionRow` (riga semplice) oppure `@ExcelFunctionMergeRow` (celle unite + formula).
La formula Excel vera e propria è definita all'interno di `@ExcelFunction`.

È anche possibile aggiungere una **colonna formula calcolata interamente tramite annotazione**, senza alcun valore corrispondente nell'entità riga. I segnaposto `${nomeCampo}` nella stringa della formula vengono risolti negli indirizzi Excel reali durante la generazione:

```java
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn = @ExcelColumn(index = 9, name = "Tassazione"),
        excelCellsLayout = @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2),
        excelFunction = @ExcelFunction(
            function = "IF(${stipendio}<=28000,${stipendio}*0.23,IF(${stipendio}<=50000,${stipendio}*0.35,${stipendio}*0.43))",
            nameFunction = "tassazione"
        )
    )
})
public class EmployeeRow implements RowSheet, CsvRow { ... }
```

La formula applica un'aliquota fiscale progressiva a ogni riga: stipendio ≤ 28.000 → 23%, 28.001–50.000 → 35%, > 50.000 → 43%. Il valore `nameFunction` (`"tassazione"`) identifica univocamente la colonna calcolata e può essere referenziato in `@ExcelMergeRow(referenceField = ...)` o in altre formule.

La stessa classe riga può implementare sia `RowSheet` (per la generazione Excel) che `CsvRow` (per la generazione CSV), permettendo di usare lo stesso oggetto dati con entrambi i generatori.

---

#### `@ExcelFunctionRows`

Annotazione contenitore applicata a **livello di classe** su un `RowSheet`. Contiene due liste indipendenti:

| Attributo              | Tipo                     | Default | Descrizione                                        |
|------------------------|--------------------------|---------|----------------------------------------------------|
| `excelFunctions`       | `ExcelFunctionRow[]`     | `{}`    | Righe formula semplici aggiunte sotto i dati       |
| `excelFunctionMerges`  | `ExcelFunctionMergeRow[]`| `{}`    | Righe formula con celle unite per gruppi           |

Le due liste sono elaborate indipendentemente e possono coesistere sulla stessa classe.

---

#### `@ExcelFunctionRow`

Configura una singola riga formula. È un parametro di `@ExcelFunctionRows.excelFunctions`.

| Attributo               | Tipo                    | Default                                    | Descrizione                                              |
|-------------------------|-------------------------|--------------------------------------------|----------------------------------------------------------|
| `excelColumn`           | `ExcelColumn`           | —                                          | **Obbligatorio.** Intestazione e posizione della colonna |
| `excelFunction`         | `ExcelFunction`         | —                                          | **Obbligatorio.** Definizione della formula              |
| `excelCellsLayout`      | `ExcelCellLayout`       | allineamento destra, precisione 2          | Stile della cella risultato                              |
| `excelColumnWidth`      | `ExcelColumnWidth`      | larghezza predefinita                      | Larghezza della colonna                                  |
| `excelHeaderCellLayout` | `ExcelHeaderCellLayout` | stile intestazione predefinito             | Stile della cella di intestazione di questa colonna      |
| `excelSubtotal`         | `ExcelSubtotal`         | `enable=false`, funzione=SUM               | Subtotale per questa colonna formula (disabilitato di default) |
| `excelNumberFormat`     | `ExcelNumberFormat`     | `""` (eredita il formato predefinito)      | Formato numerico applicato alla cella risultato          |

---

#### `@ExcelFunctionMergeRow`

Come `@ExcelFunctionRow`, ma unisce celle consecutive nella colonna che condividono lo stesso valore nel campo di riferimento, poi applica la formula per ogni gruppo unito. È un parametro di `@ExcelFunctionRows.excelFunctionMerges`.

| Attributo               | Tipo                    | Default                                    | Descrizione                                                         |
|-------------------------|-------------------------|--------------------------------------------|---------------------------------------------------------------------|
| `excelColumn`           | `ExcelColumn`           | —                                          | **Obbligatorio.** Intestazione e posizione della colonna            |
| `excelFunction`         | `ExcelFunction`         | —                                          | **Obbligatorio.** Definizione della formula                         |
| `excelMergeRow`         | `ExcelMergeRow`         | —                                          | **Obbligatorio.** Campo/i che determinano i confini di unione       |
| `excelCellsLayout`      | `ExcelCellLayout`       | allineamento destra, precisione 2          | Stile della cella risultato unita                                   |
| `excelColumnWidth`      | `ExcelColumnWidth`      | larghezza predefinita                      | Larghezza della colonna                                             |
| `excelHeaderCellLayout` | `ExcelHeaderCellLayout` | stile intestazione predefinito             | Stile della cella di intestazione                                   |
| `excelSubtotal`         | `ExcelSubtotal`         | `enable=false`, funzione=SUM               | Subtotale per questa colonna formula unita                          |
| `excelNumberFormat`     | `ExcelNumberFormat`     | `""` (eredita il formato predefinito)      | Formato numerico applicato a ogni cella risultato unita             |

---

#### `@ExcelFunction`

Definisce la formula Excel e il suo identificatore. Usato dentro `@ExcelFunctionRow` e `@ExcelFunctionMergeRow`.

| Attributo        | Tipo                     | Default       | Descrizione                                                                   |
|------------------|--------------------------|---------------|-------------------------------------------------------------------------------|
| `function`       | `String`                 | —             | **Obbligatorio.** Formula Excel con segnaposto `${fieldName}`                 |
| `nameFunction`   | `String`                 | —             | **Obbligatorio.** Nome univoco usato per riferirsi alle celle risultato       |
| `anotherTable`   | `boolean`                | `true`        | `true` se la formula fa riferimento a colonne di un altro foglio/tabella      |
| `alias`          | `ExcelFormulaAlias[]`    | `{}`          | Alias di intervallo celle usati nella formula                                 |
| `onSubTotalRow`  | `ExcelFunctionSubTotal`  | `value=false` | Configurazione della riga totale complessivo in fondo ai dati                 |

##### Sintassi dei segnaposto nelle formule

La stringa `function` supporta i seguenti segnaposto, risolti in indirizzi Excel reali in fase di generazione:

| Segnaposto                                            | Risolto in                                            | Caso d'uso                                      |
|-------------------------------------------------------|-------------------------------------------------------|-------------------------------------------------|
| `${fieldName}`                                        | La cella nella riga corrente per quel campo           | Formula di riga (somma su colonne)              |
| `${fieldNameRowStart}:${fieldNameRowEnd}`             | Prima e ultima cella di quella colonna nel range dati | Aggregato di colonna (somma, media, …)          |
| `${fieldName[start]}:${fieldName[end]}`               | Come sopra (sintassi alternativa)                     | Aggregato di colonna                            |
| `${fieldName[start+N]}:${fieldName[end-M]}`           | Range di colonna con offset di riga                   | Escludere righe iniziali/finali dal range       |
| `${fieldName.field-value[start]}`                     | Il valore della prima cella di quella colonna         | Riferirsi alla prima cella come costante        |
| `${SheetName.fieldRowStart}:${SheetName.fieldRowEnd}` | Range di colonna su un altro foglio                   | Formula che fa riferimento a un altro foglio    |

Con `anotherTable=false` la formula agisce sulla stessa tabella; i range `${fieldRowStart}:${fieldRowEnd}` sono risolti relativamente al blocco dati del foglio corrente.

---

#### `@ExcelFunctionSubTotal`

Controlla se viene aggiunta una **riga totale complessivo** in fondo alla colonna formula (sotto tutte le righe dati). Si configura tramite l'attributo `onSubTotalRow` di `@ExcelFunction`.

| Attributo         | Tipo              | Default                              | Descrizione                                      |
|-------------------|-------------------|--------------------------------------|--------------------------------------------------|
| `value`           | `boolean`         | `false`                              | `true` per aggiungere la riga totale complessivo |
| `excelCellLayout` | `ExcelCellLayout` | allineamento destra, font grassetto  | Stile applicato alla cella totale complessivo    |

---

#### `@ExcelFormulaAlias`

Definisce un **alias nominato** per una cella o un intervallo di celle, in modo che l'alias possa essere usato come segnaposto nelle stringhe formula. Utile per riferimenti cross-sheet o quando la sintassi del nome campo non è sufficiente.

| Attributo     | Tipo      | Default | Descrizione                                                                                   |
|---------------|-----------|---------|-----------------------------------------------------------------------------------------------|
| `alias`       | `String`  | —       | **Obbligatorio.** Il nome segnaposto usato nelle espressioni `${alias}`                       |
| `coordinate`  | `String`  | —       | **Obbligatorio.** Coordinata target — nome campo con `[start]`/`[end]` opzionali o sintassi cross-sheet `SheetName.field[start]` |
| `sheet`       | `String`  | `""`    | Nome del foglio per riferimenti cross-sheet (vuoto per il foglio corrente)                   |
| `blockColumn` | `boolean` | `false` | Produce un riferimento assoluto alla colonna (`$A1`)                                         |
| `blockRow`    | `boolean` | `false` | Produce un riferimento assoluto alla riga (`A$1`)                                            |

---

#### Esempi — Funzioni e Formule

**1. Formula di riga (somma di due colonne nella stessa riga)**

```java
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn  = @ExcelColumn(index = 9, name = "Prezzo Totale"),
        excelFunction = @ExcelFunction(
            function     = "sum(${prezzo},${supplemento})",
            nameFunction = "prezzoTotale"
        ),
        excelCellsLayout = @ExcelCellLayout(
            horizontalAlignment = HorizontalAlignment.RIGHT,
            precision = 2,
            locked = true
        ),
        excelColumnWidth = @ExcelColumnWidth(width = 7)
    )
})
public class LibroRow implements RowSheet { ... }
```

**2. Aggregato di colonna (somma di tutte le righe di una colonna)**

```java
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn  = @ExcelColumn(index = 8, name = "Totale Prezzo"),
        excelFunction = @ExcelFunction(
            function     = "sum(${prezzoRowStart}:${prezzoRowEnd})",
            nameFunction = "totalePrezzo"
        )
    )
})
public class TotaleRow implements RowSheet { ... }
```

**3. Aggregato condizionale (SUMIF)**

```java
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn  = @ExcelColumn(index = 2, name = "Totale per Matricola"),
        excelFunction = @ExcelFunction(
            function     = "sumif(${matricolaRowStart}:${matricolaRowEnd},${totMatricola},${prezzoRowStart}:${prezzoRowEnd})",
            nameFunction = "totalePerMatricola"
        )
    )
})
public class TotaleAutoreLibriRow implements RowSheet {
    @ExcelColumn(name = "Matricola", index = 1)
    private Integer totMatricola;
}
```

**4. Aggregato di colonna con offset di riga**

```java
// Esclude la prima e l'ultima riga del range dati
@ExcelFunction(
    function     = "sum(${prezzo[start+1]}:${prezzo[end-1]})",
    nameFunction = "totaleTroncato"
)
```

**5. Formula percentuale con formato numerico e riga totale complessivo**

```java
@ExcelFunctionRows(
    excelFunctions = @ExcelFunctionRow(
        excelColumn  = @ExcelColumn(index = 3, name = "% Scop. Giuridica"),
        excelFunction = @ExcelFunction(
            function     = "${presenzaGiuridica}/${organico}",
            nameFunction = "scoperturaGiuridica",
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
public class SituazioneUfficiRow implements RowSheet { ... }
```

**6. Formula unita (subtotale per gruppo)**

```java
@ExcelFunctionRows(
    excelFunctionMerges = {
        @ExcelFunctionMergeRow(
            excelColumn   = @ExcelColumn(index = 7.1, name = "Prezzo Totale per Autore"),
            excelMergeRow = @ExcelMergeRow(referenceField = "matricola"),
            excelFunction = @ExcelFunction(
                function     = "sum(${prezzoRowStart}:${prezzoRowEnd})",
                nameFunction = "prezzoTotalePerAutore",
                anotherTable = false
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
public class LibroRow implements RowSheet { ... }
```

**7. Confini di unione multi-campo**

Il campo `referenceField` di `@ExcelMergeRow` dentro `@ExcelFunctionMergeRow` accetta un array; le celle vengono unite solo quando **tutti** i campi elencati sono uguali:

```java
@ExcelFunctionMergeRow(
    excelColumn   = @ExcelColumn(index = 7.3, name = "Prezzo Totale per Genere"),
    excelMergeRow = @ExcelMergeRow(referenceField = {"genere", "matricola"}),
    excelFunction = @ExcelFunction(
        function     = "sum(${prezzoRowStart}:${prezzoRowEnd})",
        nameFunction = "prezzoTotalePerGenere",
        anotherTable = false
    )
)
```

**8. Riferimento cross-sheet tramite alias**

```java
@ExcelFunctionRow(
    excelColumn  = @ExcelColumn(index = 10, name = "Data Test"),
    excelFunction = @ExcelFunction(
        function     = "${Test Date.dataA}",
        nameFunction = "dataTest"
    )
)
```

---

### Validazione Dati

---

#### `@ExcelDataValidation`

Applica una **regola di validazione basata su formula** alla colonna di un campo. Se la formula restituisce `FALSE` per il valore inserito, Excel mostra un dialogo di errore. Si applica direttamente sul campo.

| Attributo  | Tipo                  | Default                                                    | Descrizione                                                           |
|------------|-----------------------|------------------------------------------------------------|-----------------------------------------------------------------------|
| `value`    | `String`              | —                                                          | **Obbligatorio.** Formula di validazione; usa `${fieldName}` per riferirsi alla cella corrente |
| `alias`    | `ExcelFormulaAlias[]` | `{}`                                                       | Alias per riferimenti cella usati nella formula                       |
| `errorBox` | `ExcelBoxMessage`     | STOP, titolo "Error", messaggio "The value is not valid"   | Dialogo mostrato quando la validazione fallisce                       |

Attributi di `@ExcelBoxMessage`:

| Attributo   | Tipo       | Default | Descrizione                                        |
|-------------|------------|---------|----------------------------------------------------|
| `show`      | `boolean`  | `true`  | Se mostrare il dialogo di errore                   |
| `boxStyle`  | `BoxStyle` | —       | `STOP`, `WARNING` o `INFORMATION`                  |
| `title`     | `String`   | —       | Titolo del dialogo                                 |
| `message`   | `String`   | —       | Testo del messaggio                                |

```java
// Valida che il campo contenga una data reale (non un numero inserito manualmente)
@ExcelColumn(name = "Data di Nascita", index = 4)
@ExcelDate(value = ColumnDateFormat.YYYY_MM_DD)
@ExcelDataValidation(
    "AND(ISNUMBER(${dataDiNascita});${dataDiNascita}=DATE(YEAR(${dataDiNascita});MONTH(${dataDiNascita});DAY(${dataDiNascita})))"
)
private Calendar dataDiNascita;
```

---

#### `@ExcelDropDown`

Crea una **lista a discesa** in una cella referenziando un range da un altro foglio o tabella nello stesso workbook. Si applica direttamente sul campo.

| Attributo               | Tipo                  | Default                                                  | Descrizione                                                            |
|-------------------------|-----------------------|----------------------------------------------------------|------------------------------------------------------------------------|
| `areaRange`             | `String`              | —                                                        | **Obbligatorio.** Espressione di range celle (usa segnaposto `${alias}`) |
| `suppressDropDownArrow` | `boolean`             | `true`                                                   | `false` per mostrare la freccia a discesa nella cella                  |
| `alias`                 | `ExcelFormulaAlias[]` | `{}`                                                     | Alias che mappano i segnaposto agli indirizzi reali delle celle         |
| `errorBox`              | `ExcelBoxMessage`     | STOP, titolo "Error", messaggio "The value is not valid" | Dialogo mostrato quando viene inserito un valore non valido            |

##### Sintassi di `areaRange`

Il valore `areaRange` è un'espressione di range Excel. I segnaposto `${aliasName}` vengono risolti tramite l'array `alias`.

Sono supportati due stili di riferimento:

| Stile                    | Esempio                                                       | Descrizione                                               |
|--------------------------|---------------------------------------------------------------|-----------------------------------------------------------|
| Basato su alias          | `${genereStart}:${genereEnd}`                                 | Gli alias definiti in `alias` vengono risolti in indirizzi celle |
| Risolto automaticamente  | `${SheetName.fieldRowStart}:${SheetName.fieldRowEnd}`         | Risolto automaticamente dal range dati di un altro foglio |

```java
// Stile 1 — alias espliciti che puntano a un altro foglio
@ExcelColumn(name = "Genere", index = 5)
@ExcelDropDown(
    areaRange = "${genereStart}:${genereEnd}",
    alias = {
        @ExcelFormulaAlias(alias = "genereStart", coordinate = "genere[start]", sheet = "Genere"),
        @ExcelFormulaAlias(alias = "genereEnd",   coordinate = "genere[end]",   sheet = "Genere")
    }
)
private String genere;

// Stile 2 — range cross-sheet risolto automaticamente (nessun alias esplicito necessario)
@ExcelColumn(name = "Genere", index = 5)
@ExcelDropDown(
    areaRange = "${Genere.genereRowStart}:${Genere.genereRowEnd}",
    suppressDropDownArrow = true
)
private String desGenere;
```

---

#### `IntegerDropDown` / `CharacterDropDown` — lista di valori inline

Per elenchi statici semplici che non fanno riferimento a un altro foglio, usa i tipi wrapper `IntegerDropDown` e `CharacterDropDown`. Dichiara il campo con uno di questi tipi e inizializzalo con la lista dei valori ammessi:

```java
// Dropdown intero — valori 0, 1, 2
@ExcelColumn(name = "Opzione", index = 8)
@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
private IntegerDropDown option;

// Dropdown carattere — valori A, B, C
@ExcelColumn(name = "Opzione Char", index = 9)
@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
private CharacterDropDown optionChar;

// Nel costruttore
this.option     = new IntegerDropDown(null, Arrays.asList(0, 1, 2));
this.optionChar = new CharacterDropDown(null, Arrays.asList('A', 'B', 'C'));
```

Non è necessaria alcuna annotazione `@ExcelDropDown` — il tipo stesso segnala che deve essere generata una lista a discesa.

---

## Colonne Dinamiche

Le **colonne dinamiche** permettono di aggiungere colonne al foglio a runtime, senza definirle con annotazioni nel `RowSheet`. Sono utili quando il numero o il tipo di colonne è noto solo durante l'esecuzione (es. anni, parametri variabili).

### `DynamicRowSheet`

Classe astratta che estende `RowSheet`. I valori delle colonne dinamiche vengono memorizzati in una mappa interna `mapValue` indicizzata per chiave stringa.

```java
public class AutoreLibriRowDynamic extends DynamicRowSheet {

    @ExcelColumn(name = "Nome", index = 2)
    @ExcelCellLayout
    @ExcelMergeRow(referenceField = "matricola")
    private String nome;

    // campi statici normali ...

    public AutoreLibriRowDynamic(...) {
        super();
        // ...
    }
}

// Aggiunta di valori dinamici per riga
AutoreLibriRowDynamic row = new AutoreLibriRowDynamic(...);
row.addValue("anno1", 23.4);
row.addValue("anno2", 30.12);
row.addValue("data",  new Date());
```

### `SheetDynamicData<T extends DynamicRowSheet>`

Classe astratta di foglio che accetta colonne dinamiche. Le colonne sono configurate tramite `addExtraColumnAnnotation`.

```java
@ExcelSheetLayout
@ExcelHeaderLayout
public class AutoreLibriSheetDynamic extends SheetDynamicData<AutoreLibriRowDynamic> {
    public AutoreLibriSheetDynamic(String sheetName) { super(sheetName); }
}
```

### `addExtraColumnAnnotation(String key, Consumer<ExtraColumnAnnotation>)`

Aggiunge e configura una colonna dinamica tramite lambda. La `key` deve corrispondere alla chiave usata in `DynamicRowSheet.addValue(...)`.

`ExtraColumnAnnotation` supporta le stesse annotazioni di un campo `RowSheet` statico:

| Setter | Descrizione |
|--------|-------------|
| `setExcelColumn(Consumer<ExcelColumnImpl>)` | **Obbligatorio.** Nome, indice, ignore della colonna |
| `setExcelCellLayout(Consumer<ExcelCellLayoutImpl>)` | **Obbligatorio.** Stile cella |
| `setExcelDate(Consumer<ExcelDateImpl>)` | Formato data |
| `setExcelFunction(Consumer<ExcelFunctionImpl>)` | Formula Excel calcolata per riga |
| `setExcelSubtotal(Consumer<ExcelSubtotalImpl>)` | Subtotale della colonna |
| `setExcelMergeRow(Consumer<ExcelMergeRowImpl>)` | Merge di celle consecutive |
| `setExcelHeaderCellLayout(Consumer<ExcelHeaderCellLayoutImpl>)` | Stile intestazione colonna |
| `setExcelColumnWidth(Consumer<ExcelColumnWidthImpl>)` | Larghezza colonna |
| `setExcelDataValidation(Consumer<ExcelDataValidationImpl>)` | Validazione cella |
| `setExcelDropDown(Consumer<ExcelDropDownImpl>)` | Lista a discesa |
| `setExcelSubtotal(Consumer<ExcelSubtotalImpl>)` | Subtotale |
| `setExcelBooleanText(Consumer<ExcelBooleanTextImpl>)` | Testo booleano |
| `setExcelNumberFormat(Consumer<ExcelNumberFormatImpl>)` | Formato numero personalizzato |
| `setExcelImage(Consumer<ExcelImageImpl>)` | Immagine nella cella |

### Esempio completo

```java
// 1. Classe riga con campi statici + valori dinamici
public class AutoreLibriRowDynamic extends DynamicRowSheet {

    @ExcelColumn(name = "Matricola", index = 1)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    @ExcelMergeRow
    private Integer matricola;

    @ExcelColumn(name = "Nome", index = 2)
    @ExcelCellLayout
    @ExcelMergeRow(referenceField = "matricola")
    private String nome;

    // altri campi statici ...
}

// 2. Classe foglio dinamico
@ExcelSheetLayout
@ExcelHeaderLayout
public class AutoreLibriSheetDynamic extends SheetDynamicData<AutoreLibriRowDynamic> {
    public AutoreLibriSheetDynamic(String sheetName) { super(sheetName); }
}

// 3. Configurazione colonne e generazione
AutoreLibriSheetDynamic sheet = new AutoreLibriSheetDynamic("Libri d'autore");

Consumer<ExcelCellLayoutImpl> doubleCellLayout = l -> {
    l.setWrap(true);
    l.setVerticalAlignment(VerticalAlignment.CENTER);
    l.setPrecision(2);
    l.setHorizontalAlignment(HorizontalAlignment.RIGHT);
    l.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
    l.addRgbForeground(r -> { r.setRed((byte) 255); r.setGreen((byte) 255); r.setBlue((byte) 255); });
    l.addRgbFont(r -> {});
    l.setBorder(b -> {
        b.setLeft(BorderStyle.THIN); b.setTop(BorderStyle.THIN);
        b.setRight(BorderStyle.THIN); b.setBottom(BorderStyle.THIN);
    });
};

// Colonna con valore dalla mapValue
sheet.addExtraColumnAnnotation("anno1", a -> {
    a.setExcelColumn(c -> { c.setName("2015"); c.setIndex(20); });
    a.setExcelCellLayout(doubleCellLayout);
});

// Colonna formula con subtotale e merge
sheet.addExtraColumnAnnotation("totalePrezzoAnniAutore", a -> {
    a.setExcelColumn(c -> { c.setName("Totale anni per Autore"); c.setIndex(22); });
    a.setExcelCellLayout(doubleCellLayout);
    a.setExcelFunction(f -> {
        f.setFunction("sum(${totalePrezzoAnniRowStart}:${totalePrezzoAnniRowEnd})");
        f.setNameFunction("totalePrezzoAnniAutore");
        f.setAnotherTable(false);
    });
    a.setExcelMergeRow(m -> m.setReferenceField("matricola"));
    a.setExcelColumnWidth(cw -> cw.setWidth(10));
});

// Dati di riga
AutoreLibriRowDynamic row = new AutoreLibriRowDynamic("Mario", "Rossi", 1, 25.5, 3.0);
row.addValue("anno1", 23.4);
sheet.addRows(row);

byte[] bytes = generateExcel.createFileXlsx(new ReportExcel("report", List.of(sheet)));
```

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
| `bld.commons.sheet.password`          | Password globale di default per i fogli con `@ExcelLocked` senza valore esplicito (default: `""` — lock senza password) |

```yaml
com.bld.commons:
  check.annotation: true
  date.format: dd/MM/yyyy
  multiple.datasource: false
bld.commons.sheet.password: myGlobalPassword
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
