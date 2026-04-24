# Data Validation & Dropdowns

Annotations and types for constraining cell input: formula-based validation rules and dropdown lists.

---

## `@ExcelDataValidation`

Applies a formula-based validation rule to a field's column. If the formula evaluates to `FALSE` for an entered value, Excel shows an error dialog. Applied directly on a `RowSheet` **field**.

| Attribute  | Type                  | Default | Description |
|------------|-----------------------|---------|-------------|
| `value`    | `String`              | —       | **Required.** Excel validation formula; use `${fieldName}` to reference the current cell |
| `alias`    | `ExcelFormulaAlias[]` | `{}`    | Aliases for cell references inside the formula |
| `errorBox` | `ExcelBoxMessage`     | STOP, "Error", "The value is not valid" | Error dialog shown when validation fails |

### `@ExcelBoxMessage` attributes

| Attribute  | Type       | Default | Description |
|------------|------------|---------|-------------|
| `show`     | `boolean`  | `true`  | Whether to show the error dialog |
| `boxStyle` | `BoxStyle` | `STOP`  | `STOP`, `WARNING`, or `INFORMATION` |
| `title`    | `String`   | `"Error"` | Dialog title |
| `message`  | `String`   | `"The value is not valid"` | Dialog message body |

```java
// Ensure the value is a real Excel date (not a plain number)
@ExcelColumn(name = "Birth Date", index = 4)
@ExcelDate(value = ColumnDateFormat.YYYY_MM_DD)
@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
@ExcelDataValidation(
    "AND(ISNUMBER(${birthDate});${birthDate}=DATE(YEAR(${birthDate});MONTH(${birthDate});DAY(${birthDate})))"
)
private Calendar birthDate;

// Custom error message
@ExcelColumn(name = "Score", index = 5)
@ExcelDataValidation(
    value    = "AND(${score}>=0;${score}<=100)",
    errorBox = @ExcelBoxMessage(
        boxStyle = BoxStyle.WARNING,
        title    = "Invalid score",
        message  = "Score must be between 0 and 100."
    )
)
private Integer score;
```

---

## `@ExcelDropDown`

Creates a dropdown list in a cell by referencing a range from another sheet in the same workbook. Applied directly on a `RowSheet` **field**.

| Attribute               | Type                  | Default | Description |
|-------------------------|-----------------------|---------|-------------|
| `areaRange`             | `String`              | —       | **Required.** Cell range formula (use `${alias}` or auto-resolved syntax) |
| `suppressDropDownArrow` | `boolean`             | `true`  | `false` to show the dropdown arrow in the cell |
| `alias`                 | `ExcelFormulaAlias[]` | `{}`    | Aliases mapping placeholder names to actual cell addresses |
| `errorBox`              | `ExcelBoxMessage`     | STOP, "Error", "The value is not valid" | Error dialog for invalid entries |

### `areaRange` syntax

Two styles are supported:

| Style          | Example | Description |
|----------------|---------|-------------|
| Alias-based    | `${genreStart}:${genreEnd}` | Explicit aliases defined in `alias` array |
| Auto-resolved  | `${Genre.genreRowStart}:${Genre.genreRowEnd}` | Resolved automatically from another sheet's data range |

```java
// Style 1 — explicit aliases pointing to another sheet
@ExcelColumn(name = "Genre", index = 5)
@ExcelCellLayout
@ExcelDropDown(
    areaRange = "${genreStart}:${genreEnd}",
    alias = {
        @ExcelFormulaAlias(alias = "genreStart", coordinate = "genre[start]", sheet = "Genre"),
        @ExcelFormulaAlias(alias = "genreEnd",   coordinate = "genre[end]",   sheet = "Genre")
    }
)
private String genre;

// Style 2 — auto-resolved cross-sheet range (no explicit aliases needed)
@ExcelColumn(name = "Genre", index = 5)
@ExcelDropDown(
    areaRange              = "${Genre.genreRowStart}:${Genre.genreRowEnd}",
    suppressDropDownArrow  = true
)
private String genre;
```

---

## `IntegerDropDown` / `CharacterDropDown` — inline value list

For simple static lists that do not reference another sheet, use the wrapper types `IntegerDropDown` and `CharacterDropDown`. Declare the field with one of these types and initialise it in the constructor with the list of allowed values. No `@ExcelDropDown` annotation is needed — the type itself triggers dropdown generation.

```java
public class BookRow implements RowSheet {

    // Integer dropdown — values 0, 1, 2
    @ExcelColumn(name = "Option", index = 8)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private IntegerDropDown option;

    // Character dropdown — values A, B, C
    @ExcelColumn(name = "Category", index = 9)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    private CharacterDropDown category;

    public BookRow() {
        this.option   = new IntegerDropDown(null, Arrays.asList(0, 1, 2));
        this.category = new CharacterDropDown(null, Arrays.asList('A', 'B', 'C'));
    }
}
```

The first constructor argument is the selected value (`null` = no pre-selection); the second is the list of allowed values.
