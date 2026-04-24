# Advanced & Utilities

Miscellaneous annotations for controlling generation behaviour, sheet covers, and startup validation.

---

## `@ExcelClearRows`

Marker annotation. Instructs the generator to delete all existing data rows from the sheet before writing new content. Applied at **class level** on a `RowSheet`.

Useful when the same sheet template is reused across multiple generation cycles and stale data from a previous run must not be carried over.

```java
@ExcelClearRows
public class DailyReportRow implements RowSheet {

    @ExcelColumn(name = "Date",   index = 1)
    @ExcelDate(ColumnDateFormat.DD_MM_YYYY)
    private Calendar date;

    @ExcelColumn(name = "Amount", index = 2)
    @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
    private Double amount;

    // getters / setters ...
}
```

---

## `@ExcelSelectCell`

Selects a specific cell in a **cover sheet** at generation time. Applied to a **field** of a `ReportExcel`-level class (the root container, not a sheet class).

| Attribute       | Type     | Description |
|-----------------|----------|-------------|
| `cellReference` | `String` | **Required.** Excel cell address to select (e.g. `"B3"`, `"A1"`) |

```java
public class MyReport extends ReportExcel {

    @ExcelSelectCell(cellReference = "B2")
    private String coverTitle;

    // ...
}
```

---

## `@IgnoreCheck`

Marker annotation that tells the generator to skip annotation validation for the annotated class or field during startup checks (controlled by the `com.bld.commons.check.annotation` property).

Applied at **class level** or **field level**.

```java
// Skip validation for this entire class
@IgnoreCheck
public class LegacyRow implements RowSheet { ... }

// Skip validation for a single field
public class PartialRow implements RowSheet {

    @ExcelColumn(name = "Code", index = 1)
    @IgnoreCheck
    private String legacyCode;
}
```

This annotation is typically used during migration or when a class intentionally deviates from the expected annotation constraints.
