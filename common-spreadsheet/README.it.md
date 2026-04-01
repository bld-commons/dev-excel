# common-spreadsheet

Libreria di fondamenta condivisa per il progetto dev-excel. Fornisce annotazioni core, utility ed eccezioni utilizzate da entrambi i moduli `generator-excel` e `read-excel`.

## Dipendenza Maven

```xml
<dependency>
    <groupId>com.github.bld-commons</groupId>
    <artifactId>common-spreadsheet</artifactId>
    <version>5.1.3</version>
</dependency>
```

---

## Annotazioni

### Annotazioni Excel — `com.bld.common.spreadsheet.excel.annotation`

#### `@ExcelDate`

Applicata a campi di tipo `Date` o `Calendar` per definire il formato data usato in scrittura o lettura di una cella.

| Attributo | Tipo               | Default          | Descrizione                |
|-----------|--------------------|------------------|----------------------------|
| `value`   | `ColumnDateFormat` | `DD_MM_YYYY`     | Il pattern del formato data |

```java
@ExcelDate(value = ColumnDateFormat.DD_MM_YYYY_HH_MM_SS)
private Date createdAt;
```

---

#### `@ExcelBooleanText`

Applicata a campi `Boolean` per definire le etichette di testo che rappresentano i valori `true` e `false` nella cella.

| Attributo | Tipo     | Default | Descrizione                          |
|-----------|----------|---------|--------------------------------------|
| `enable`  | `String` | `"Yes"` | Testo visualizzato quando `true`     |
| `disable` | `String` | `"No"`  | Testo visualizzato quando `false`    |

```java
@ExcelBooleanText(enable = "Abilitato", disable = "Disabilitato")
private Boolean active;
```

---

### Annotazioni CSV — `com.bld.common.spreadsheet.csv.annotation`

#### `@CsvDate`

Applicata a campi `Date` o `Calendar` per definire la formattazione data nell'output CSV.

| Attributo   | Tipo               | Default           | Descrizione                              |
|-------------|--------------------|--------------------|------------------------------------------|
| `value`     | `ColumnDateFormat` | `DD_MM_YYYY`       | Pattern del formato data                 |
| `separator` | `String`           | `"/"`              | Separatore tra le parti della data       |
| `timezone`  | `String`           | `"Europe/Rome"`    | Timezone per la conversione della data   |

---

#### `@CsvSettings`

Annotazione a livello di classe applicata a una classe `CsvRow` per configurare il formato CSV.

| Attributo           | Tipo       | Default    | Descrizione                                            |
|---------------------|------------|------------|--------------------------------------------------------|
| `delimiter`         | `char`     | `','`      | Delimitatore di campo                                  |
| `quoteChar`         | `char`     | `'"'`      | Carattere di escape/citazione                          |
| `ignoreEmptyLines`  | `boolean`  | `true`     | Salta le righe vuote                                   |
| `recordSeparator`   | `String`   | `"\r\n"`   | Sequenza di fine riga                                  |
| `skipHeaderRecord`  | `boolean`  | —          | Salta la prima riga (intestazione) in fase di lettura  |
| `trim`              | `boolean`  | `true`     | Rimuove gli spazi bianchi dai valori                   |
| `ignoreColumns`     | `String[]` | `{}`       | Nomi di colonne da ignorare durante l'elaborazione     |
| `parallel`          | `boolean`  | `false`    | Abilita l'elaborazione CSV parallela                   |

```java
@CsvSettings(delimiter = ';', skipHeaderRecord = true)
public class MyImportRow implements CsvRow { ... }
```

---

## Costanti — `com.bld.common.spreadsheet.constant`

### `ColumnDateFormat`

Enum dei pattern di formato data disponibili.

| Costante                    | Pattern                          |
|-----------------------------|----------------------------------|
| `DD_MM_YYYY`                | `dd/MM/yyyy`                     |
| `DD_MM_YYYY_HH_MM_SS`       | `dd/MM/yyyy HH:mm:ss`            |
| `YYYY_MM_DD`                | `yyyy/mm/dd`                     |
| `YYYY_MM_DD_HH_MM_SS`       | `yyyy/MM/dd HH:mm:ss`            |
| `HH_MM_SS`                  | `HH:mm:ss`                       |
| `HH_MM`                     | `HH:mm`                          |
| `PARAMETER`                 | `${com.bld.commons.date.format}` |

Il valore `PARAMETER` risolve il formato dalla property Spring `com.bld.commons.date.format`, permettendo di definire un formato predefinito globale tramite `application.yml`.

---

## Utility — `com.bld.common.spreadsheet.utils`

### `SpreadsheetUtils`

Spring `@Component` che fornisce metodi statici per la reflection su annotazioni e l'introspezione dei campi.

| Metodo | Descrizione |
|--------|-------------|
| `getAnnotation(Class<?>, Class<A>)` | Restituisce un'annotazione da una classe; lancia `SpreadsheetException` se assente |
| `getAnnotation(Field, Class<A>)` | Restituisce un'annotazione da un campo |
| `reflectionAnnotation(T entity, K annotation)` | Copia i valori degli attributi dell'annotazione sui campi dell'oggetto tramite reflection |
| `getListField(Class<?>)` | Restituisce tutti i campi dichiarati dell'intera gerarchia di classe come `Set` |
| `getMapField(Class<?>)` | Restituisce tutti i campi dichiarati come `Map<String, Field>` |
| `writeToFile(String path, String name, String ext, byte[] data)` | Scrive un array di byte su file |

Costante: `SHEET_NAME_SIZE = 31` (lunghezza massima del nome foglio in Excel).

### `CsvUtils`

| Metodo | Descrizione |
|--------|-------------|
| `getCsvFormat(CsvSettings, String... headers)` | Costruisce un `CSVFormat` di Apache Commons CSV da un'annotazione `@CsvSettings` |

### `ExcelUtils`

| Metodo | Descrizione |
|--------|-------------|
| `coordinateCalculation(int row, int col, boolean blockCol, boolean blockRow)` | Costruisce l'indirizzo di una cella Excel (es. `$A$1`) |
| `widthColumn(int width)` | Converte una larghezza colonna in unità Apache POI |
| `rowHeight(int height)` | Converte un'altezza riga in unità Apache POI |
| `evaluate(String expression, String param, Number value)` | Valuta un'espressione matematica |

---

## Eccezioni — `com.bld.common.spreadsheet.exception`

| Classe                    | Descrizione                                                    |
|---------------------------|----------------------------------------------------------------|
| `SpreadsheetException`    | Eccezione base per tutti gli errori della libreria spreadsheet |
| `ExcelGeneratorException` | Lanciata quando la generazione Excel fallisce                  |
| `CsvGeneratorException`   | Lanciata quando la generazione CSV fallisce                    |

Tutte le eccezioni estendono `RuntimeException` e supportano i costruttori standard: predefinito, messaggio, causa, messaggio+causa.

---

## Proprietà Spring

| Proprietà                       | Descrizione                                                              |
|---------------------------------|--------------------------------------------------------------------------|
| `com.bld.commons.date.format`   | Formato data globale quando si usa `ColumnDateFormat.PARAMETER`          |

```yaml
com.bld.commons:
  date.format: dd/MM/yyyy
```
