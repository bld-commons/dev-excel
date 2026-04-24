# Subtotals

Annotations for grouping rows and inserting subtotal rows automatically.

---

## `@ExcelSubtotals`

Applied at **class level** on a `RowSheet`. Enables automatic subtotal rows inserted after each group of rows that share the same value in the grouping fields.

| Attribute          | Type       | Default | Description |
|--------------------|------------|---------|-------------|
| `labelTotalGroup`  | `String`   | —       | Text prefix for each subtotal row label |
| `endLabel`         | `String`   | `""`    | Text appended to the closing subtotal row |
| `sumForGroup`      | `String[]` | `{}`    | Fields that define the grouping hierarchy (ordered from coarsest to finest) |

---

## `@ExcelSubtotal`

Applied to individual `RowSheet` **fields** to mark which numeric columns should be aggregated in the subtotal row.

| Attribute                 | Type                      | Default       | Description |
|---------------------------|---------------------------|---------------|-------------|
| `dataConsolidateFunction` | `DataConsolidateFunction` | `SUM`         | Aggregation function (`SUM`, `AVERAGE`, `COUNT`, etc.) |
| `excelCellLayout`         | `ExcelCellLayout`         | default style | Style applied to the subtotal cell |
| `enable`                  | `boolean`                 | `true`        | `false` to disable the subtotal for this field |

---

## Example

```java
@ExcelSubtotals(
    labelTotalGroup = "Total",
    endLabel        = "total",
    sumForGroup     = {"state", "city"}   // group first by state, then by city
)
public class SalaryRow implements RowSheet {

    @ExcelColumn(name = "State", index = 0)
    @ExcelCellLayout
    private String state;

    @ExcelColumn(name = "City", index = 1)
    @ExcelCellLayout
    private String city;

    @ExcelColumn(name = "District", index = 2)
    @ExcelCellLayout
    private String district;

    @ExcelColumn(name = "Amount", index = 3)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    @ExcelSubtotal(
        dataConsolidateFunction = DataConsolidateFunction.SUM,
        excelCellLayout = @ExcelCellLayout(
            horizontalAlignment = HorizontalAlignment.RIGHT,
            font = @ExcelFont(bold = true)
        )
    )
    private Double amount;

    // constructor, getters, setters ...
}
```

The generator will insert a subtotal row after each city group and a higher-level subtotal after each state group. The `Amount` column will be summed; text columns (`State`, `City`, `District`) will have the label produced from `labelTotalGroup + " " + groupValue`.
