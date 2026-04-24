# Columns & Cells

Annotations that control how individual fields are mapped to Excel columns, how cells are styled, and how consecutive cells are merged.

---

## `@ExcelColumn`

Maps a `RowSheet` field to an Excel column. **Required** on every field that should appear in the output.

| Attribute | Type      | Default | Description |
|-----------|-----------|---------|-------------|
| `name`    | `String`  | —       | Column header label. Supports Spring property placeholders (`${key}`) |
| `index`   | `double`  | —       | Column position. Decimal values (e.g. `7.1`, `7.2`) allow inserting sub-columns between two integer positions |
| `comment` | `String`  | `""`    | Tooltip comment shown on the header cell |
| `ignore`  | `boolean` | `false` | `true` to exclude the column from the output |

```java
@ExcelColumn(name = "Employee ID", index = 1)
private Integer id;

@ExcelColumn(name = "${col.name.label}", index = 2)   // i18n via Spring properties
private String name;

@ExcelColumn(name = "Internal", index = 3, ignore = true)  // excluded from output
private String internalCode;

// Sub-column between 7 and 8 — useful for computed/formula columns
@ExcelColumn(name = "Sub-total A", index = 7.1)
private Double subTotalA;
```

---

## `@ExcelCellLayout`

Full cell style definition. Applied to `RowSheet` fields.

| Attribute             | Type                  | Default               | Description |
|-----------------------|-----------------------|-----------------------|-------------|
| `border`              | `ExcelBorder`         | THIN on all sides     | Cell border |
| `horizontalAlignment` | `HorizontalAlignment` | `LEFT`                | Horizontal text alignment |
| `verticalAlignment`   | `VerticalAlignment`   | `CENTER`              | Vertical text alignment |
| `font`                | `ExcelFont`           | CALIBRI, size 11      | Font |
| `wrap`                | `boolean`             | `true`                | Wrap text inside the cell |
| `rgbFont`             | `ExcelRgbColor[]`     | black `(0,0,0)`       | Font colour (one value = solid; two values = alternating rows) |
| `rgbForeground`       | `ExcelRgbColor[]`     | white `(255,255,255)` | Background colour (one value = solid; two values = alternating rows) |
| `fillPatternType`     | `FillPatternType`     | `SOLID_FOREGROUND`    | Fill pattern |
| `precision`           | `int`                 | `-1` (no restriction) | Decimal places for numeric values |
| `percent`             | `boolean`             | `false`               | Format number as percentage |
| `locked`              | `boolean`             | `false`               | Prevent cell editing (effective when sheet is protected) |
| `autoSizeColumn`      | `boolean`             | `false`               | Auto-fit column width to content |

```java
// Right-aligned, 2 decimal places
@ExcelColumn(name = "Salary", index = 4)
@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
private Double salary;

// Alternating row colours
@ExcelColumn(name = "Name", index = 2)
@ExcelCellLayout(
    rgbForeground = {
        @ExcelRgbColor(red = (byte)255, green = (byte)255, blue = (byte)255),
        @ExcelRgbColor(red = (byte)198, green = (byte)224, blue = (byte)180)
    }
)
private String name;

// Auto-size column width
@ExcelColumn(name = "Description", index = 5)
@ExcelCellLayout(autoSizeColumn = true)
private String description;
```

---

## `@ExcelFont`

Configures the font inside `@ExcelCellLayout` or `@ExcelHeaderCellLayout`.

| Attribute   | Type            | Default   | Description |
|-------------|-----------------|-----------|-------------|
| `font`      | `FontType`      | `CALIBRI` | Font family |
| `bold`      | `boolean`       | `false`   | Bold text |
| `italic`    | `boolean`       | `false`   | Italic text |
| `underline` | `UnderlineType` | `NONE`    | Underline style |
| `size`      | `short`         | `11`      | Font size in points |

```java
@ExcelCellLayout(font = @ExcelFont(bold = true, size = 12, font = FontType.ARIAL))
private String title;
```

---

## `@ExcelBorder`

Defines a cell border. Used inside `@ExcelCellLayout`, `@ExcelHeaderCellLayout`, and `@ExcelAreaBorder`.

| Attribute | Type          | Default | Description   |
|-----------|---------------|---------|---------------|
| `top`     | `BorderStyle` | `NONE`  | Top border    |
| `bottom`  | `BorderStyle` | `NONE`  | Bottom border |
| `left`    | `BorderStyle` | `NONE`  | Left border   |
| `right`   | `BorderStyle` | `NONE`  | Right border  |

```java
@ExcelCellLayout(
    border = @ExcelBorder(
        top    = BorderStyle.THIN,
        bottom = BorderStyle.THIN,
        left   = BorderStyle.THIN,
        right  = BorderStyle.THIN
    )
)
private String field;
```

---

## `@ExcelRgbColor`

Defines an RGB colour used for font or background. Used inside `@ExcelCellLayout` and `@ExcelHeaderCellLayout`.

| Attribute | Type   | Default | Description       |
|-----------|--------|---------|-------------------|
| `red`     | `byte` | `255`   | Red channel (0–255)  |
| `green`   | `byte` | `255`   | Green channel (0–255)|
| `blue`    | `byte` | `255`   | Blue channel (0–255) |

Java `byte` is signed (-128 to 127), so values above 127 must be cast: `(byte)200`.

```java
// Red font on yellow background
@ExcelCellLayout(
    rgbFont       = @ExcelRgbColor(red = (byte)255, green = 0, blue = 0),
    rgbForeground = @ExcelRgbColor(red = (byte)255, green = (byte)255, blue = 0)
)
private String alert;
```

---

## `@ExcelColumnWidth`

Sets a fixed column width (in centimetres). Applied to `RowSheet` fields. Can also appear inside `@ExcelFunctionRow` and `@ExcelFunctionMergeRow` to control formula-column width.

| Attribute | Type  | Default | Description              |
|-----------|-------|---------|--------------------------|
| `width`   | `int` | `5`     | Column width in centimetres |

```java
@ExcelColumn(name = "Price", index = 4)
@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
@ExcelColumnWidth(width = 10)
private Double price;
```

Inside a formula column:

```java
@ExcelFunctionRow(
    excelColumn      = @ExcelColumn(index = 9, name = "Total"),
    excelFunction    = @ExcelFunction(function = "sum(${price},${tax})", nameFunction = "total"),
    excelColumnWidth = @ExcelColumnWidth(width = 7)
)
```

---

## `@ExcelNumberFormat`

Applies a custom Excel number format code to a cell. Applied to `RowSheet` fields or inside `@ExcelFunctionRow`.

| Attribute | Type     | Default | Description              |
|-----------|----------|---------|--------------------------|
| `value`   | `String` | `""`    | Excel format code (e.g. `"#,##0.00"`, `"0.00%"`) |

```java
@ExcelColumn(name = "Amount", index = 3)
@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
@ExcelNumberFormat("#,##0.00 €")
private Double amount;
```

---

## `@ExcelMergeRow`

Merges consecutive cells in a column when a reference field has the same value across adjacent rows.

| Attribute        | Type       | Default | Description |
|------------------|------------|---------|-------------|
| `referenceField` | `String[]` | `{}`    | Field name(s) that define the merge grouping. When multiple fields are listed, cells merge only when **all** listed fields are equal in adjacent rows. An empty array merges all rows unconditionally. |

```java
// Merge "Name" cells while "authorId" is the same
@ExcelColumn(name = "Name", index = 2)
@ExcelCellLayout
@ExcelMergeRow(referenceField = "authorId")
private String name;

// Merge unconditionally (all rows become one merged block)
@ExcelColumn(name = "Department", index = 1)
@ExcelCellLayout
@ExcelMergeRow
private String department;

// Merge only when BOTH genre AND authorId are equal
@ExcelColumn(name = "Genre", index = 3)
@ExcelMergeRow(referenceField = {"genre", "authorId"})
private String genre;
```
