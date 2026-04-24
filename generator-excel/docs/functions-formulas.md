# Functions & Formulas

Annotations for appending formula rows below the data area. Formulas can reference columns by name using `${fieldName}` placeholders that are resolved to real cell addresses at generation time.

---

## `@ExcelFunctionRows`

Container annotation applied at **class level** on a `RowSheet`. Holds two independent lists of formula rows.

| Attribute             | Type                     | Default | Description |
|-----------------------|--------------------------|---------|-------------|
| `excelFunctions`      | `ExcelFunctionRow[]`     | `{}`    | Simple formula rows appended below the last data row |
| `excelFunctionMerges` | `ExcelFunctionMergeRow[]`| `{}`    | Formula rows whose cells span merged groups |

---

## `@ExcelFunctionRow`

Configures a single formula row appended below all data rows.

| Attribute               | Type                   | Default             | Description |
|-------------------------|------------------------|---------------------|-------------|
| `excelColumn`           | `ExcelColumn`          | —                   | **Required.** Column header and position |
| `excelFunction`         | `ExcelFunction`        | —                   | **Required.** Formula definition |
| `excelCellsLayout`      | `ExcelCellLayout`      | right-aligned, precision 2 | Style for the result cell |
| `excelColumnWidth`      | `ExcelColumnWidth`     | 5 cm                | Column width |
| `excelHeaderCellLayout` | `ExcelHeaderCellLayout`| default header style| Style for the header cell of this column |
| `excelSubtotal`         | `ExcelSubtotal`        | `enable=false`      | Subtotal appended below this formula column |
| `excelNumberFormat`     | `ExcelNumberFormat`    | `""` (default)      | Number format for the result cell |

---

## `@ExcelFunctionMergeRow`

Like `@ExcelFunctionRow`, but merges consecutive cells in the column that share the same value in a reference field, then applies the formula over each merged group.

| Attribute               | Type                   | Default             | Description |
|-------------------------|------------------------|---------------------|-------------|
| `excelColumn`           | `ExcelColumn`          | —                   | **Required.** Column header and position |
| `excelFunction`         | `ExcelFunction`        | —                   | **Required.** Formula definition |
| `excelMergeRow`         | `ExcelMergeRow`        | —                   | **Required.** Field(s) that determine merge boundaries |
| `excelCellsLayout`      | `ExcelCellLayout`      | right-aligned, precision 2 | Style for the merged result cell |
| `excelColumnWidth`      | `ExcelColumnWidth`     | 5 cm                | Column width |
| `excelHeaderCellLayout` | `ExcelHeaderCellLayout`| default header style| Style for the header cell |
| `excelSubtotal`         | `ExcelSubtotal`        | `enable=false`      | Subtotal for this merged formula column |
| `excelNumberFormat`     | `ExcelNumberFormat`    | `""` (default)      | Number format applied to each merged result cell |

---

## `@ExcelFunction`

Defines the Excel formula and its identifier.

| Attribute       | Type                  | Default       | Description |
|-----------------|-----------------------|---------------|-------------|
| `function`      | `String`              | —             | **Required.** Formula with `${fieldName}` placeholders |
| `nameFunction`  | `String`              | —             | **Required.** Unique name used to reference this formula's result cells in other formulas |
| `anotherTable`  | `boolean`             | `true`        | `false` if the formula references columns from the same table |
| `alias`         | `ExcelFormulaAlias[]` | `{}`          | Named aliases for complex cell references |
| `onSubTotalRow` | `ExcelFunctionSubTotal`| `value=false`| Grand-total row appended below all data rows |

### Placeholder syntax

| Placeholder                               | Resolves to |
|-------------------------------------------|-------------|
| `${fieldName}`                            | Current-row cell for that field |
| `${fieldNameRowStart}:${fieldNameRowEnd}` | Full column range (first to last data row) |
| `${fieldName[start]}:${fieldName[end]}`   | Same (alternative syntax) |
| `${fieldName[start+N]}:${fieldName[end-M]}`| Column range with row offset |
| `${fieldName.field-value[start]}`         | The value of the first cell in that column |
| `${SheetName.fieldRowStart}:${SheetName.fieldRowEnd}` | Cross-sheet column range |

---

## `@ExcelFunctionSubTotal`

Controls whether a **grand-total row** is appended at the very bottom of a formula column. Configured via `@ExcelFunction.onSubTotalRow`.

| Attribute         | Type              | Default          | Description |
|-------------------|-------------------|------------------|-------------|
| `value`           | `boolean`         | `false`          | `true` to append the grand-total row |
| `excelCellLayout` | `ExcelCellLayout` | right-aligned, bold | Style applied to the grand-total cell |

---

## `@ExcelFormulaAlias`

Defines a named alias for a cell or range, usable as `${aliasName}` inside a formula string.

| Attribute     | Type      | Default | Description |
|---------------|-----------|---------|-------------|
| `alias`       | `String`  | —       | **Required.** Placeholder name |
| `coordinate`  | `String`  | —       | **Required.** Target — field name with optional `[start]`/`[end]`, or cross-sheet syntax `SheetName.field[start]` |
| `sheet`       | `String`  | `""`    | Sheet name for cross-sheet references |
| `blockColumn` | `boolean` | `false` | Absolute column reference (`$A1`) |
| `blockRow`    | `boolean` | `false` | Absolute row reference (`A$1`) |

---

## Examples

**1. Row formula — sum two columns on the same row**

```java
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn      = @ExcelColumn(index = 9, name = "Total Price"),
        excelFunction    = @ExcelFunction(
            function     = "sum(${price},${supplement})",
            nameFunction = "totalPrice"
        ),
        excelCellsLayout = @ExcelCellLayout(
            horizontalAlignment = HorizontalAlignment.RIGHT,
            precision = 2
        ),
        excelColumnWidth = @ExcelColumnWidth(width = 7)
    )
})
public class BookRow implements RowSheet { ... }
```

**2. Column aggregate — sum all rows of a column**

```java
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn   = @ExcelColumn(index = 8, name = "Total"),
        excelFunction = @ExcelFunction(
            function     = "sum(${priceRowStart}:${priceRowEnd})",
            nameFunction = "grandTotal"
        )
    )
})
public class TotalsRow implements RowSheet { ... }
```

**3. Percentage formula with number format and grand-total row**

```java
@ExcelFunctionRows(
    excelFunctions = @ExcelFunctionRow(
        excelColumn   = @ExcelColumn(index = 3, name = "% Coverage"),
        excelFunction = @ExcelFunction(
            function     = "${presence}/${headcount}",
            nameFunction = "coverageRate",
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
public class StaffRow implements RowSheet { ... }
```

**4. Merged formula — subtotal per group**

```java
@ExcelFunctionRows(
    excelFunctionMerges = {
        @ExcelFunctionMergeRow(
            excelColumn   = @ExcelColumn(index = 7.1, name = "Total per Author"),
            excelMergeRow = @ExcelMergeRow(referenceField = "authorId"),
            excelFunction = @ExcelFunction(
                function     = "sum(${priceRowStart}:${priceRowEnd})",
                nameFunction = "totalPerAuthor",
                anotherTable = false
            ),
            excelCellsLayout = @ExcelCellLayout(
                horizontalAlignment = HorizontalAlignment.RIGHT,
                precision = 2
            ),
            excelSubtotal = @ExcelSubtotal(
                enable = true,
                dataConsolidateFunction = DataConsolidateFunction.SUM,
                excelCellLayout = @ExcelCellLayout(
                    horizontalAlignment = HorizontalAlignment.RIGHT,
                    precision = 2,
                    font = @ExcelFont(bold = true)
                )
            )
        )
    }
)
public class BookRow implements RowSheet { ... }
```

**5. Cross-sheet reference using `@ExcelFormulaAlias`**

```java
@ExcelFunctionRow(
    excelColumn   = @ExcelColumn(index = 5, name = "Total Books"),
    excelFunction = @ExcelFunction(
        function     = "sum(${booksStart}:${booksEnd})",
        nameFunction = "totalBooks",
        alias = {
            @ExcelFormulaAlias(alias = "booksStart", coordinate = "quantity[start]", sheet = "Books"),
            @ExcelFormulaAlias(alias = "booksEnd",   coordinate = "quantity[end]",   sheet = "Books")
        }
    )
)
```

**6. Column range with row offset**

```java
// Exclude the first and last data rows from the sum range
@ExcelFunction(
    function     = "sum(${price[start+1]}:${price[end-1]})",
    nameFunction = "trimmedTotal"
)
```

**7. Multi-field merge boundaries**

```java
@ExcelFunctionMergeRow(
    excelColumn   = @ExcelColumn(index = 7.3, name = "Total per Genre"),
    excelMergeRow = @ExcelMergeRow(referenceField = {"genre", "authorId"}),
    excelFunction = @ExcelFunction(
        function     = "sum(${priceRowStart}:${priceRowEnd})",
        nameFunction = "totalPerGenre",
        anotherTable = false
    )
)
```
