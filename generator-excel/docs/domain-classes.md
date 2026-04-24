# Domain Classes — Overview

All sheet types extend or implement a common hierarchy.

```
BaseSheet
├── SheetData<T extends RowSheet>                    implements SheetComponent
│   ├── QuerySheetData<T>
│   ├── SheetDynamicData<T extends DynamicRowSheet>  implements DynamicColumn
│   │   └── DynamicChart<T>
│   ├── LoadSheetData<R, S>
│   └── SheetFunctionTotal<T>
│       └── SheetDynamicFunctionTotal<T>             implements DynamicColumn
└── SheetSummary                                     implements SheetComponent

MergeSheet extends BaseSheet    (groups SheetComponent instances on one tab)

RowSheet  (interface)
└── DynamicRowSheet  (abstract class)

FunctionsTotal<T>  (interface — implemented by SheetData subclasses
                    that expose a companion total sheet)
```

---

## Detail pages

| Page | Classes covered |
|------|----------------|
| [sheet-data.md](sheet-data.md) | `RowSheet`, `SheetData`, `SheetSummary`, `MergeSheet`, `FunctionsTotal`, `ExcelHyperlink`, `ExcelAttachment` |
| [dynamic-sheets.md](dynamic-sheets.md) | `DynamicRowSheet`, `SheetDynamicData`, `ExtraColumnAnnotation`, `DynamicChart`, `SheetDynamicFunctionTotal` |
| [query-load-function.md](query-load-function.md) | `QuerySheetData`, `LoadSheetData`, `SheetFunctionTotal` |
