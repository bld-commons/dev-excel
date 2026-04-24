# Sheet Layout & Structure

Annotations that control the overall structure of a sheet: orientation, margins, header style, frozen panes, borders, and sheet protection.

---

## `@ExcelSheetLayout`

Applied at **class level** on `SheetData` or `SheetSummary` subclasses. Configures the sheet's global layout options.

| Attribute      | Type               | Default  | Description |
|----------------|--------------------|----------|-------------|
| `landscape`    | `boolean`          | `true`   | Landscape page orientation |
| `notMerge`     | `boolean`          | `true`   | `false` to allow merged cells across columns |
| `sortAndFilter`| `boolean`          | `true`   | Show sort/filter controls on the header row |
| `startColumn`  | `int`              | `0`      | First column index where data starts (0-based) |
| `startRow`     | `int`              | `0`      | First row index where data starts (0-based) |
| `order`        | `int`              | `-1`     | Position of this sheet in the workbook tab order |
| `showHeader`   | `boolean`          | `true`   | Write the header row; `false` to skip it |
| `groupRow`     | `boolean`          | `false`  | Enable row grouping |
| `groupColumn`  | `boolean`          | `false`  | Enable column grouping |
| `scale`        | `short`            | `100`    | Print scale percentage |
| `hidden`       | `boolean`          | `false`  | Hide the sheet tab |
| `locked`       | `ExcelLocked`      | unlocked | Sheet protection — see [`@ExcelLocked`](#excellocked) |
| `areaBorder`   | `ExcelAreaBorder[]`| `{}`     | One or more cell-area borders — see [`@ExcelAreaBorder`](#excelareaorder) |

```java
@ExcelSheetLayout(
    landscape    = true,
    sortAndFilter = true,
    startRow     = 1,
    startColumn  = 1,
    showHeader   = true,
    scale        = 90
)
@ExcelHeaderLayout
public class EmployeeSheet extends SheetData<EmployeeRow> {
    public EmployeeSheet(String name) { super(name); }
}
```

---

## `@ExcelHeaderLayout`

Applied at **class level** on `SheetData`. Configures the visual style of the header row.

| Attribute              | Type                 | Default | Description |
|------------------------|----------------------|---------|-------------|
| `rowHeight`            | `short`              | `2`     | Header row height in centimetres |
| `excelHeaderCellLayout`| `ExcelHeaderCellLayout` | (see below) | Default style applied to all header cells |

```java
@ExcelSheetLayout
@ExcelHeaderLayout(
    rowHeight = 3,
    excelHeaderCellLayout = @ExcelHeaderCellLayout(
        rgbForeground = @ExcelRgbColor(red = (byte)31, green = (byte)73, blue = (byte)125),
        rgbFont       = @ExcelRgbColor(red = (byte)255, green = (byte)255, blue = (byte)255),
        font          = @ExcelFont(bold = true)
    )
)
public class ReportSheet extends SheetData<ReportRow> { ... }
```

---

## `@ExcelHeaderCellLayout`

Defines the style of individual header cells. Can appear in two places:
- As the `excelHeaderCellLayout` attribute of `@ExcelHeaderLayout` — applies to all header cells globally.
- Directly on a `RowSheet` **field** — overrides the global style for that specific column header.

| Attribute            | Type                  | Default                       |
|----------------------|-----------------------|-------------------------------|
| `border`             | `ExcelBorder`         | THIN on all sides             |
| `wrap`               | `boolean`             | `true`                        |
| `rgbFont`            | `ExcelRgbColor`       | `(0, 97, 0)` — dark green     |
| `rgbForeground`      | `ExcelRgbColor`       | `(198, 239, 206)` — light green |
| `font`               | `ExcelFont`           | CALIBRI bold                  |
| `horizontalAlignment`| `HorizontalAlignment` | `CENTER`                      |
| `verticalAlignment`  | `VerticalAlignment`   | `CENTER`                      |
| `fillPatternType`    | `FillPatternType`     | `SOLID_FOREGROUND`            |
| `rotation`           | `int`                 | `0`                           |
| `locked`             | `boolean`             | `false`                       |

```java
// Override the header style for a single column
@ExcelColumn(name = "Price", index = 4)
@ExcelHeaderCellLayout(
    rgbForeground = @ExcelRgbColor(red = (byte)255, green = 0, blue = 0),
    rgbFont       = @ExcelRgbColor(red = (byte)255, green = (byte)255, blue = (byte)255)
)
@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
private Double price;
```

---

## `@ExcelMarginSheet`

Sets the page margins (in centimetres). Applied at class level on `SheetData` or `SheetSummary`.

| Attribute | Type     | Default | Description |
|-----------|----------|---------|-------------|
| `top`     | `double` | `1.0`   | Top margin    |
| `bottom`  | `double` | `1.0`   | Bottom margin |
| `left`    | `double` | `1.0`   | Left margin   |
| `right`   | `double` | `1.0`   | Right margin  |

```java
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
public class SalarySheet extends SheetData<SalaryRow> { ... }
```

---

## `@ExcelFreezePane`

Freezes rows and/or columns. Applied at class level on `SheetData`.

| Attribute     | Type  | Default | Description                     |
|---------------|-------|---------|---------------------------------|
| `columnFreez` | `int` | `0`     | Number of columns to freeze     |
| `rowFreez`    | `int` | `0`     | Number of rows to freeze        |

```java
// Freeze the first 2 columns and the header row
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelFreezePane(columnFreez = 2, rowFreez = 1)
public class EmployeeSheet extends SheetData<EmployeeRow> { ... }
```

---

## `@ExcelAreaBorder`

Draws a border around a rectangular area of cells. Used as a value inside `@ExcelSheetLayout.areaBorder`.

The `areaRange` string uses the same `${fieldName[...]}` placeholder syntax as formulas:
- `${field[header]}` — the header cell of that column
- `${field[start]}` / `${fieldRowStart}` — first data cell of that column
- `${field[end]}` / `${fieldRowEnd}` — last data cell of that column

| Attribute            | Type          | Default | Description |
|----------------------|---------------|---------|-------------|
| `areaRange`          | `String`      | —       | **Required.** Cell range using `${field[...]}` placeholders |
| `border`             | `ExcelBorder` | —       | **Required.** Border style to apply |
| `includeSuperHeader` | `boolean`     | `false` | Extend the area to include the super-header row if present |

```java
@ExcelSheetLayout(areaBorder = {
    @ExcelAreaBorder(
        areaRange = "${name[header]}:${salary[end]}",
        border    = @ExcelBorder(
            top    = BorderStyle.MEDIUM,
            bottom = BorderStyle.MEDIUM,
            left   = BorderStyle.MEDIUM,
            right  = BorderStyle.MEDIUM
        )
    ),
    @ExcelAreaBorder(
        areaRange        = "${department[header]}:${department[end]}",
        border           = @ExcelBorder(top = BorderStyle.MEDIUM_DASHED, bottom = BorderStyle.MEDIUM_DASHED,
                                        left = BorderStyle.MEDIUM_DASHED, right = BorderStyle.MEDIUM_DASHED),
        includeSuperHeader = true
    )
})
public class EmployeeSheet extends SheetData<EmployeeRow> { ... }
```

---

## `@ExcelLocked`

Protects the sheet with a password. Not used as a standalone annotation — it is the value of `@ExcelSheetLayout.locked`.

| Attribute | Type      | Default | Description |
|-----------|-----------|---------|-------------|
| `value`   | `String`  | `${bld.commons.sheet.password:}` | Password (supports Spring property placeholder) |
| `locked`  | `boolean` | `true`  | `false` to disable protection (default in `@ExcelSheetLayout`) |

```java
// Sheet protected by a password read from Spring config
@ExcelSheetLayout(locked = @ExcelLocked(value = "${my.sheet.password}", locked = true))
public class ProtectedSheet extends SheetData<ProtectedRow> { ... }
```

The password can also be set in `application.yml`:
```yaml
my.sheet.password: s3cr3t
```

---

## `@ExcelRowHeight`

Sets a fixed row height (in centimetres).

- Applied at **class level** on a `RowSheet` — applies the height to every data row.
- Applied at **field level** inside a `SheetSummary` — applies the height to the row that contains that field.

| Attribute | Type    | Default | Description                          |
|-----------|---------|---------|--------------------------------------|
| `height`  | `short` | `-1`    | Row height in centimetres; `-1` = auto |

```java
// All data rows will be 1.5 cm tall
@ExcelRowHeight(height = 2)
public class ImageRow implements RowSheet {

    @ExcelColumn(name = "Photo", index = 1)
    @ExcelImage
    private byte[] photo;

    // ...
}

// Inside a SheetSummary — only the row containing "city" gets height 5
public class CompanySummary extends SheetSummary {

    @ExcelColumn(name = "City", index = 3)
    @ExcelRowHeight(height = 5)
    private String city;
}
```
