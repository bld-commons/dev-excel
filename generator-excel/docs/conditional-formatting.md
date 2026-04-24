# Conditional Formatting

Annotations for applying a cell style when an Excel formula condition evaluates to `TRUE`.

---

## `@ExcelConditionCellLayouts`

Container annotation applied at **class level** on a `RowSheet`. Holds one or more `@ExcelConditionCellLayout` rules.

| Attribute | Type                        | Description |
|-----------|-----------------------------|-------------|
| `value`   | `ExcelConditionCellLayout[]`| One or more conditional formatting rules |

---

## `@ExcelConditionCellLayout`

Defines a single conditional formatting rule.

| Attribute         | Type              | Description |
|-------------------|-------------------|-------------|
| `columns`         | `String[]`        | Names of the columns (matching `@ExcelColumn.name`) to apply the formatting to |
| `condition`       | `String`          | Excel formula that evaluates to `TRUE`/`FALSE`. Use `${fieldName[start]}` to reference the first cell of a column |
| `excelCellLayout` | `ExcelCellLayout` | Style applied when `condition` is `TRUE` |

### Placeholder syntax in `condition`

| Placeholder            | Resolves to                                      |
|------------------------|--------------------------------------------------|
| `${fieldName[start]}`  | Address of the first data cell of that column    |
| `${fieldName}`         | Address of the current row's cell for that field |

---

## Example

Highlight rows whose `state` column contains the word "total" (subtotal rows) with a red font on a blue background:

```java
@ExcelConditionCellLayouts(
    @ExcelConditionCellLayout(
        columns   = {"state", "city", "district", "amount"},
        condition = "ISNUMBER(SEARCH(\"total\", LOWER(${state[start]})))",
        excelCellLayout = @ExcelCellLayout(
            rgbFont       = @ExcelRgbColor(red = (byte)255, green = 0, blue = 0),
            rgbForeground = @ExcelRgbColor(red = 0, green = 0, blue = (byte)255)
        )
    )
)
public class SalaryRow implements RowSheet { ... }
```

Multiple independent rules on the same sheet:

```java
@ExcelConditionCellLayouts({
    @ExcelConditionCellLayout(
        columns   = {"amount"},
        condition = "${amount[start]}<0",
        excelCellLayout = @ExcelCellLayout(
            rgbFont = @ExcelRgbColor(red = (byte)255, green = 0, blue = 0)
        )
    ),
    @ExcelConditionCellLayout(
        columns   = {"amount"},
        condition = "${amount[start]}>10000",
        excelCellLayout = @ExcelCellLayout(
            rgbFont       = @ExcelRgbColor(red = 0, green = (byte)128, blue = 0),
            font          = @ExcelFont(bold = true)
        )
    )
})
public class SalaryRow implements RowSheet { ... }
```
