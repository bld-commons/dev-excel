# common-spreadsheet

Shared foundation library for the dev-excel project. Provides core annotations, utilities, and exceptions used by both `generator-excel` and `read-excel`.

## Maven Dependency

```xml
<dependency>
    <groupId>com.github.bld-commons</groupId>
    <artifactId>common-spreadsheet</artifactId>
    <version>5.1.3</version>
</dependency>
```

---

## Annotations

### Excel Annotations — `com.bld.common.spreadsheet.excel.annotation`

#### `@ExcelDate`

Applied to `Date` or `Calendar` fields to define the date format used when writing or reading a cell.

| Attribute | Type               | Default          | Description              |
|-----------|--------------------|------------------|--------------------------|
| `value`   | `ColumnDateFormat` | `DD_MM_YYYY`     | The date format pattern  |

```java
@ExcelDate(value = ColumnDateFormat.DD_MM_YYYY_HH_MM_SS)
private Date createdAt;
```

---

#### `@ExcelBooleanText`

Applied to `Boolean` fields to define the text labels used to represent `true` and `false` values in a cell.

| Attribute | Type     | Default | Description                 |
|-----------|----------|---------|-----------------------------|
| `enable`  | `String` | `"Yes"` | Text displayed when `true`  |
| `disable` | `String` | `"No"`  | Text displayed when `false` |

```java
@ExcelBooleanText(enable = "Enable", disable = "Disable")
private Boolean active;
```

---

### CSV Annotations — `com.bld.common.spreadsheet.csv.annotation`

#### `@CsvDate`

Applied to `Date` or `Calendar` fields to define date formatting in CSV output.

| Attribute   | Type               | Default           | Description                         |
|-------------|--------------------|--------------------|-------------------------------------|
| `value`     | `ColumnDateFormat` | `DD_MM_YYYY`       | Date format pattern                 |
| `separator` | `String`           | `"/"`              | Separator between date parts        |
| `timezone`  | `String`           | `"Europe/Rome"`    | Timezone for date conversion        |

---

#### `@CsvSettings`

Type-level annotation applied to a `CsvRow` class to configure the CSV format.

| Attribute           | Type       | Default    | Description                                      |
|---------------------|------------|------------|--------------------------------------------------|
| `delimiter`         | `char`     | `','`      | Field delimiter                                  |
| `quoteChar`         | `char`     | `'"'`      | Quote character                                  |
| `ignoreEmptyLines`  | `boolean`  | `true`     | Skip empty lines                                 |
| `recordSeparator`   | `String`   | `"\r\n"`   | Line ending sequence                             |
| `skipHeaderRecord`  | `boolean`  | —          | Skip the first row when reading                  |
| `trim`              | `boolean`  | `true`     | Trim whitespace from values                      |
| `ignoreColumns`     | `String[]` | `{}`       | Column names to ignore during processing         |
| `parallel`          | `boolean`  | `false`    | Enable parallel CSV processing                   |

```java
@CsvSettings(delimiter = ';', skipHeaderRecord = true)
public class MyImportRow implements CsvRow { ... }
```

---

## Constants — `com.bld.common.spreadsheet.constant`

### `ColumnDateFormat`

Enum of available date format patterns.

| Constant                    | Pattern                    |
|-----------------------------|----------------------------|
| `DD_MM_YYYY`                | `dd/MM/yyyy`               |
| `DD_MM_YYYY_HH_MM_SS`       | `dd/MM/yyyy HH:mm:ss`      |
| `YYYY_MM_DD`                | `yyyy/mm/dd`               |
| `YYYY_MM_DD_HH_MM_SS`       | `yyyy/MM/dd HH:mm:ss`      |
| `HH_MM_SS`                  | `HH:mm:ss`                 |
| `HH_MM`                     | `HH:mm`                    |
| `PARAMETER`                 | `${com.bld.commons.date.format}` |

The `PARAMETER` value resolves the format from the Spring environment property `com.bld.commons.date.format`, allowing a global default format via `application.yml`.

---

## Utilities — `com.bld.common.spreadsheet.utils`

### `SpreadsheetUtils`

Spring `@Component` providing static helpers for annotation reflection and field introspection.

| Method | Description |
|--------|-------------|
| `getAnnotation(Class<?>, Class<A>)` | Get annotation from a class; throws `SpreadsheetException` if absent |
| `getAnnotation(Field, Class<A>)` | Get annotation from a field |
| `reflectionAnnotation(T entity, K annotation)` | Copy annotation attribute values to an object's fields via reflection |
| `getListField(Class<?>)` | Return all declared fields from the full class hierarchy as a `Set` |
| `getMapField(Class<?>)` | Return all declared fields as a `Map<String, Field>` |
| `writeToFile(String path, String name, String ext, byte[] data)` | Write byte array to a file |

Constant: `SHEET_NAME_SIZE = 31` (Excel's maximum sheet name length).

### `CsvUtils`

| Method | Description |
|--------|-------------|
| `getCsvFormat(CsvSettings, String... headers)` | Build an Apache Commons CSV `CSVFormat` from a `@CsvSettings` annotation |

### `ExcelUtils`

| Method | Description |
|--------|-------------|
| `coordinateCalculation(int row, int col, boolean blockCol, boolean blockRow)` | Build an Excel cell address string (e.g. `$A$1`) |
| `widthColumn(int width)` | Convert a width value to Apache POI units |
| `rowHeight(int height)` | Convert a height value to Apache POI units |
| `evaluate(String expression, String param, Number value)` | Evaluate a mathematical expression |

---

## Exceptions — `com.bld.common.spreadsheet.exception`

| Class                    | Description                                             |
|--------------------------|---------------------------------------------------------|
| `SpreadsheetException`   | Base exception for all spreadsheet library errors       |
| `ExcelGeneratorException`| Thrown when Excel generation fails                      |
| `CsvGeneratorException`  | Thrown when CSV generation fails                        |

All exceptions extend `RuntimeException` and support the standard constructors: default, message, cause, message+cause.

---

## Spring Property

| Property                        | Description                                        |
|---------------------------------|----------------------------------------------------|
| `com.bld.commons.date.format`   | Global date format when `ColumnDateFormat.PARAMETER` is used |

```yaml
com.bld.commons:
  date.format: dd/MM/yyyy
```
