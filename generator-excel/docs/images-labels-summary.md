# Images, Labels & Summary

---

## `@ExcelImage`

Embeds an image in a cell. Applied to `RowSheet` fields of type `byte[]` (raw image bytes) or `String` (file-system path to the image).

| Attribute      | Type     | Default | Description |
|----------------|----------|---------|-------------|
| `resizeHeight` | `double` | `1.0`   | Vertical resize factor (1.0 = original height) |
| `resizeWidth`  | `double` | `1.0`   | Horizontal resize factor (1.0 = original width) |

```java
// Embed from raw bytes
@ExcelColumn(name = "Photo", index = 4)
@ExcelImage
private byte[] photo;

// Embed from file path, resized to 70% height and 60% width
@ExcelColumn(name = "Logo", index = 5)
@ExcelImage(resizeHeight = 0.7, resizeWidth = 0.6)
private String logoPath;
```

Use `@ExcelRowHeight` on the class to ensure the row is tall enough to display the image:

```java
@ExcelRowHeight(height = 5)
public class ProductRow implements RowSheet {

    @ExcelColumn(name = "Photo", index = 3)
    @ExcelImage
    private byte[] photo;
}
```

---

## `@ExcelLabel`

Writes a free-text label cell **above the data area** in a `SheetData` sheet. Applied to a `String` **field** of the sheet class (not the row class). The field value at runtime becomes the label text.

| Attribute     | Type              | Default | Description |
|---------------|-------------------|---------|-------------|
| `labelLayout` | `ExcelCellLayout` | Orange-ish bold font on yellow background | Style for the label cell |
| `columnMerge` | `int`             | `1`     | Number of columns the label cell spans |

```java
@ExcelSheetLayout
@ExcelHeaderLayout
public class AuthorSheet extends QuerySheetData<AuthorRow> {

    @ExcelLabel(columnMerge = 4)
    private String reportTitle;

    public AuthorSheet(String sheetName, String reportTitle) {
        super(sheetName);
        this.reportTitle = reportTitle;
    }

    // getter / setter ...
}

// Usage
AuthorSheet sheet = new AuthorSheet("Authors", "Annual Book Report — 2024");
```

Custom label style:

```java
@ExcelLabel(
    columnMerge = 5,
    labelLayout = @ExcelCellLayout(
        font          = @ExcelFont(bold = true, size = 14),
        rgbFont       = @ExcelRgbColor(red = (byte)255, green = (byte)255, blue = (byte)255),
        rgbForeground = @ExcelRgbColor(red = (byte)31, green = (byte)73, blue = (byte)125),
        horizontalAlignment = HorizontalAlignment.CENTER
    )
)
private String reportTitle;
```

---

## `@ExcelSummary`

Configures a **summary sheet** — a two-column key/value table where each row corresponds to a field of the `SheetSummary` subclass. Applied at **class level** on `SheetSummary`.

| Attribute          | Type              | Default | Description |
|--------------------|-------------------|---------|-------------|
| `title`            | `String`          | —       | **Required.** Table title written above the data |
| `titleCellFormulta`| `boolean`         | `false` | `true` if `title` is an Excel formula |
| `comment`          | `String`          | `""`    | Optional comment on the title cell |
| `layout`           | `ExcelCellLayout` | default | Style for the first-column (key) cells |
| `widthColumn1`     | `int`             | `5`     | Width of the key column in centimetres |
| `widthColumn2`     | `int`             | `5`     | Width of the value column in centimetres |

`SheetSummary` fields annotated with `@ExcelColumn` become rows in the table. The `name` of `@ExcelColumn` is the key label; the field value is the value.

```java
@ExcelSheetLayout(startRow = 1, startColumn = 1)
@ExcelSummary(
    title        = "Publisher Info",
    widthColumn1 = 8,
    widthColumn2 = 12,
    layout       = @ExcelCellLayout(font = @ExcelFont(bold = true))
)
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
public class PublisherSummary extends SheetSummary {

    @ExcelColumn(name = "Name",          index = 1)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private String name;

    @ExcelColumn(name = "Founded",       index = 2)
    @ExcelDate(ColumnDateFormat.YYYY_MM_DD)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private Calendar founded;

    @ExcelColumn(name = "City",          index = 3)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    @ExcelRowHeight(height = 3)
    private String city;

    public PublisherSummary(String sheetName, String name, Calendar founded, String city) {
        super(sheetName);
        this.name    = name;
        this.founded = founded;
        this.city    = city;
    }
}
```

`SheetSummary` can also contain `@ExcelFunctionRows` to add formula rows that reference data from other sheets:

```java
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn   = @ExcelColumn(index = 7, name = "Total book revenue"),
        excelFunction = @ExcelFunction(
            function     = "sum(${Books.priceRowStart}:${Books.priceRowEnd})",
            nameFunction = "totalRevenue"
        )
    )
})
public class PublisherSummary extends SheetSummary { ... }
```
