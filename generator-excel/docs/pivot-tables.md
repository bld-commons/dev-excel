# Pivot Tables

Annotations for generating a pivot table from a sheet's data. Supported only for **XLSX (XSSF)** format.

The pivot table is placed to the right of the data area, starting at the column set by `@ExcelPivot.startColumn`.

---

## `@ExcelPivot`

Applied at **class level** on a `SheetData` subclass. Enables pivot table generation for that sheet.

| Attribute     | Type  | Default | Description |
|---------------|-------|---------|-------------|
| `startColumn` | `int` | `0`     | Column index (0-based) where the pivot table starts in the sheet |

---

## `@ExcelPivotRow`

Applied to `RowSheet` **fields**. Marks the field as a **row** dimension in the pivot table.

| Attribute | Type     | Description |
|-----------|----------|-------------|
| `order`   | `double` | Position of this row dimension in the pivot table (lower = higher in the hierarchy) |

---

## `@ExcelPivotColumn`

Applied to `RowSheet` **fields**. Marks the field as a **column** dimension in the pivot table.

| Attribute | Type     | Description |
|-----------|----------|-------------|
| `order`   | `double` | Position of this column dimension |

---

## `@ExcelPivotColumnFunction`

Applied to `RowSheet` **fields**. Marks the field as a **value** area in the pivot table, with one or more aggregation functions.

| Attribute                 | Type                       | Description |
|---------------------------|----------------------------|-------------|
| `order`                   | `double`                   | Position of this value column in the pivot table |
| `dataConsolidateFunction` | `DataConsolidateFunction[]`| One or more aggregation functions (`SUM`, `AVERAGE`, `COUNT`, …). One value column is generated for each function |

---

## `@ExcelPivotFilter`

Applied to `RowSheet` **fields**. Marks the field as a **filter** (report filter) in the pivot table. No additional attributes.

---

## Example

Sheet class enabling the pivot table:

```java
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.5, bottom = 1.5, left = 1.5, right = 1.5)
@ExcelQuery(
    "SELECT a.id_author, a.surname, a.name, b.title, b.genre, s.year, s.price " +
    "FROM author a JOIN book b ON a.id = b.author_id JOIN price_history s ON b.id = s.book_id"
)
@ExcelPivot(startColumn = 12)
public class BookSheet extends QuerySheetData<BookRow> {
    public BookSheet(String name) { super(name); }
}
```

Row class with pivot roles assigned per field:

```java
public class BookRow implements RowSheet {

    // Report filter
    @ExcelColumn(name = "Author ID", index = 0)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    @ExcelPivotFilter
    private Integer idAuthor;

    // Row dimensions (hierarchy: surname → name → title)
    @ExcelColumn(name = "Surname", index = 1)
    @ExcelCellLayout
    @ExcelPivotRow(order = 0)
    private String surname;

    @ExcelColumn(name = "Name", index = 2)
    @ExcelCellLayout
    @ExcelPivotRow(order = 2)
    private String name;

    @ExcelColumn(name = "Title", index = 3)
    @ExcelCellLayout
    @ExcelPivotRow(order = 3)
    private String title;

    // Column dimension
    @ExcelColumn(name = "Year", index = 4)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
    @ExcelPivotColumn(order = 0)
    private Integer year;

    // Value area — two aggregates (SUM and AVERAGE) for the same field
    @ExcelColumn(name = "Price", index = 5)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
    @ExcelPivotColumnFunction(
        order = 0,
        dataConsolidateFunction = {
            DataConsolidateFunction.SUM,
            DataConsolidateFunction.AVERAGE
        }
    )
    private Double price;

    // getters / setters ...
}
```

The resulting pivot table will have:
- **Report filter**: Author ID
- **Row labels**: Surname → Name → Title
- **Column labels**: Year
- **Values**: Sum of Price, Average of Price
