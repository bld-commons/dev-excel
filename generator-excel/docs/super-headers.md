# Super Headers

Annotations for adding a merged header row **above** the regular column header row, grouping multiple columns under a common label.

Not supported on `SheetDynamicData` subclasses.

---

## `@ExcelSuperHeaders`

Applied at **class level** on a `SheetData` subclass. Contains one or more `@ExcelSuperHeader` rows.

| Attribute      | Type                | Default | Description |
|----------------|---------------------|---------|-------------|
| `superHeaders` | `ExcelSuperHeader[]`| `{}`    | One entry per super-header row (top-to-bottom order) |

---

## `@ExcelSuperHeader`

Defines a single super-header row. It is an element of `@ExcelSuperHeaders.superHeaders`.

| Attribute      | Type                   | Default | Description |
|----------------|------------------------|---------|-------------|
| `headerGroups` | `ExcelSuperHeaderCell[]`| `{}`   | Cells in this row, left to right |
| `rowHeight`    | `short`                | `2`     | Height of this super-header row in centimetres |

---

## `@ExcelSuperHeaderCell`

Defines a single cell within a super-header row.

| Attribute              | Type                  | Default              | Description |
|------------------------|-----------------------|----------------------|-------------|
| `columnName`           | `String`              | —                    | **Required.** Label text for the cell |
| `columnRange`          | `String`              | —                    | **Required.** Horizontal span expressed as `${firstField}:${lastField}` using field names |
| `excelHeaderCellLayout`| `ExcelHeaderCellLayout`| default header style | Style applied to this super-header cell |

The `columnRange` uses field names (matching `@ExcelColumn` on the `RowSheet` class) to determine which columns the cell should span.

---

## Example

```java
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
@ExcelSuperHeaders(
    superHeaders = @ExcelSuperHeader(
        rowHeight    = 3,
        headerGroups = {
            @ExcelSuperHeaderCell(
                columnName  = "Personal Info",
                columnRange = "${name}:${birthDate}"
            ),
            @ExcelSuperHeaderCell(
                columnName  = "Book Details",
                columnRange = "${genre}:${price}",
                excelHeaderCellLayout = @ExcelHeaderCellLayout(
                    rgbForeground = @ExcelRgbColor(red = (byte)31, green = (byte)73, blue = (byte)125),
                    rgbFont       = @ExcelRgbColor(red = (byte)255, green = (byte)255, blue = (byte)255),
                    font          = @ExcelFont(bold = true, size = 12)
                )
            )
        }
    )
)
public class AuthorBookSheet extends QuerySheetData<AuthorBookRow> {
    public AuthorBookSheet(String name) { super(name); }
}
```

The `RowSheet` fields define the column positions that `columnRange` references:

```java
public class AuthorBookRow implements RowSheet {

    @ExcelColumn(name = "Name",       index = 1) private String name;
    @ExcelColumn(name = "Surname",    index = 2) private String surname;
    @ExcelColumn(name = "Birth Date", index = 3) private String birthDate;

    @ExcelColumn(name = "Genre",  index = 4) private String genre;
    @ExcelColumn(name = "Title",  index = 5) private String title;
    @ExcelColumn(name = "Price",  index = 6) private Double price;
}
```

Result: a row above the normal header with two merged cells — "Personal Info" spanning columns 1–3 and "Book Details" spanning columns 4–6.

### Combined with `@ExcelAreaBorder`

Super headers can be included in area borders by setting `includeSuperHeader = true` on `@ExcelAreaBorder`:

```java
@ExcelSheetLayout(areaBorder = {
    @ExcelAreaBorder(
        areaRange        = "${name[header]}:${price[end]}",
        border           = @ExcelBorder(top = BorderStyle.MEDIUM, bottom = BorderStyle.MEDIUM,
                                        left = BorderStyle.MEDIUM, right = BorderStyle.MEDIUM),
        includeSuperHeader = true
    )
})
@ExcelSuperHeaders(superHeaders = @ExcelSuperHeader(
    headerGroups = {
        @ExcelSuperHeaderCell(columnName = "Personal Info", columnRange = "${name}:${birthDate}"),
        @ExcelSuperHeaderCell(columnName = "Book Details",  columnRange = "${genre}:${price}")
    }
))
public class AuthorBookSheet extends QuerySheetData<AuthorBookRow> { ... }
```
