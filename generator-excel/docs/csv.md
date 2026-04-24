# CSV Generation

Annotations and classes for generating CSV files. The CSV pipeline mirrors the Excel pipeline: a container class holds rows, and fields are mapped to columns via annotations.

---

## Core classes

| Class / Interface | Role |
|-------------------|------|
| `CsvData<T extends CsvRow>` | Abstract base for a CSV dataset. Holds the list of rows |
| `CsvRow`           | Marker interface that every CSV row class must implement |
| `QueryCsvData<T>`  | Subclass of `CsvData` for query-driven population via `@CsvQuery` |
| `GenerateCsv`      | Spring bean that generates the CSV bytes |

### `GenerateCsv`

```java
@Autowired
private GenerateCsv generateCsv;

public byte[] export(List<ProductRow> rows) throws Exception {
    ProductData data = new ProductData();
    data.setListRowSheet(rows);
    return generateCsv.generateCsv(data);
}
```

---

## `@CsvColumn`

Maps a `CsvRow` field to a CSV column. Equivalent of `@ExcelColumn` for CSV.

| Attribute | Type     | Description |
|-----------|----------|-------------|
| `name`    | `String` | **Required.** Column header label |
| `index`   | `double` | **Required.** Column position (supports decimal sub-ordering) |

```java
public class ProductRow implements CsvRow {

    @CsvColumn(name = "ID",          index = 1)
    private Integer id;

    @CsvColumn(name = "Name",        index = 2)
    private String name;

    @CsvColumn(name = "Price",       index = 3)
    private Double price;

    @CsvColumn(name = "Launch Date", index = 4)
    private String launchDate;

    // getters / setters ...
}
```

---

## `@CsvQuery`

Applied at **class level** on a `QueryCsvData` subclass. Defines the SQL or JPQL query used to automatically populate the rows. Equivalent of `@ExcelQuery` for CSV.

| Attribute       | Type      | Default | Description |
|-----------------|-----------|---------|-------------|
| `value`         | `String`  | —       | **Required.** SQL or JPQL query string |
| `nativeQuery`   | `boolean` | `true`  | `true` for native SQL, `false` for JPQL |
| `entityManager` | `String`  | `""`    | Bean name of the `EntityManager` (multiple datasource support) |

```java
// Static data — populate rows manually
public class ProductData extends CsvData<ProductRow> { }

// Query-driven — rows populated from the database
@CsvQuery("SELECT id, name, price FROM product WHERE active = true ORDER BY name")
public class ActiveProductData extends QueryCsvData<ProductRow> { }

// JPQL query
@CsvQuery(
    value       = "select new com.example.ProductRow(p.id, p.name, p.price) from Product p where p.active = true",
    nativeQuery = false
)
public class ActiveProductData extends QueryCsvData<ProductRow> { }

// Multiple datasource
@CsvQuery(
    value         = "SELECT id, name FROM product",
    entityManager = SecondaryDatabaseConfig.ENTITY_MANAGER_NAME
)
public class SecondaryProductData extends QueryCsvData<ProductRow> { }
```

---

## Full example

```java
// Row class
public class EmployeeRow implements CsvRow {

    @CsvColumn(name = "ID",         index = 1)
    private Integer id;

    @CsvColumn(name = "First Name", index = 2)
    private String firstName;

    @CsvColumn(name = "Last Name",  index = 3)
    private String lastName;

    @CsvColumn(name = "Department", index = 4)
    private String department;

    @CsvColumn(name = "Salary",     index = 5)
    private Double salary;

    // constructor, getters, setters ...
}

// Static data container
public class EmployeeData extends CsvData<EmployeeRow> { }

// Usage
@Autowired
private GenerateCsv generateCsv;

public byte[] exportEmployees(List<EmployeeRow> employees) throws Exception {
    EmployeeData data = new EmployeeData();
    data.setListRowSheet(employees);
    return generateCsv.generateCsv(data);
}
```
