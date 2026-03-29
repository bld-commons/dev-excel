# report-excel-multiple-datasource

Integration test module for `generator-excel` and `read-excel` with **multiple JPA datasources**.
Demonstrates how to generate a single Excel file with sheets sourced from different databases,
each identified by a dedicated `EntityManager`.

> This module is **not published** to Maven Central. It exists only to test and demonstrate library features.

---

## Dependencies

```xml
<dependency>
    <groupId>com.github.bld-commons</groupId>
    <artifactId>generator-excel</artifactId>
    <version>5.1.3</version>
</dependency>
<dependency>
    <groupId>com.github.bld-commons</groupId>
    <artifactId>read-excel</artifactId>
    <version>5.1.3</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>
```

---

## Multiple Datasource Setup

### Enable in Spring configuration

```yaml
# application.yml
bld:
  commons:
    multiple.datasource: true   # activates the second datasource bean
    check.annotation: true
    date.format: dd/MM/yyyy
    resource.cover.path: classpath:/excel/Copertina.xlsx
    report.excel:
      title: A12
      date: A18

# Primary datasource (DB1 — PostgreSQL port 5432)
postgres:
  datasource:
    jdbc-url: jdbc:postgresql://localhost:5432/excel_db
    username: ${EXCEL_USER_DB}
    password: Pa$$word87!
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate.ddl-auto: none
    database-platform: org.hibernate.dialect.PostgreSQLDialect

# Secondary datasource (DB2 — PostgreSQL port 5433)
postgres2:
  datasource:
    jdbc-url: jdbc:postgresql://localhost:5433/keyc9
    username: ${EXCEL_USER_DB2}
    password: ${EXCEL_PASSWORD_DB2}
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate.ddl-auto: none
    database-platform: org.hibernate.dialect.PostgreSQLDialect
```

### `Db1DatabaseConfiguration` — Primary datasource

```java
@Configuration
public class Db1DatabaseConfiguration extends MultipleDatabaseConfiguration {

    public static final String DB1_ENTITY_MANAGER         = "postgresEntityManager";
    public static final String DB1_ENTITY_MANAGER_FACTORY = "postgresEntityManagerFactory";
    public static final String DB1_TRANSACTION_MANAGER    = "postgresTransactionManager";
    public static final String JDBC_TEMPLATE              = "postgresJdbcTemplate";
    public static final String PACKAGE_SUP = "bld.generator.report.db1.persistence.domain";

    @Primary @Bean
    public DataSource postgresDataSource() { ... }

    @Primary @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory() { ... }

    @Primary @Bean
    public PlatformTransactionManager postgresTransactionManager() { ... }

    @Bean
    public NamedParameterJdbcTemplate postgresJdbcTemplate() { ... }
}
```

### `Db2DatabaseConfiguration` — Secondary datasource

```java
@Configuration
@ConditionalOnProperty("com.bld.commons.multiple.datasource")
public class Db2DatabaseConfiguration extends MultipleDatabaseConfiguration {

    public static final String DB2_ENTITY_MANAGER         = "postgres2EntityManager";
    public static final String DB2_ENTITY_MANAGER_FACTORY = "postgres2EntityManagerFactory";
    public static final String DB2_TRANSACTION_MANAGER    = "postgres2TransactionManager";
    public static final String JDBC_TEMPLATE              = "postgres2JdbcTemplate";
    public static final String PACKAGE_SUP = "bld.generator.report.db2.persistence.domain";

    @Bean
    public DataSource postgres2DataSource() { ... }

    @Bean
    public LocalContainerEntityManagerFactoryBean postgres2EntityManagerFactory() { ... }

    @Bean
    public PlatformTransactionManager postgres2TransactionManager() { ... }

    @Bean
    public NamedParameterJdbcTemplate postgres2JdbcTemplate() { ... }
}
```

---

## Sheet Entities

Each sheet declares which `EntityManager` to use via `entityManager` in `@ExcelQuery`.

### `UtenteSheet` — from DB1 (primary datasource)

```java
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
@ExcelQuery(
    value = "select new bld.generator.report.junit.entity.UtenteRow(" +
            "u.idUtente, u.nome, u.cognome, u.dataNascita, u.image, u.path) " +
            "from Utente u where u.cognome = :cognome order by u.cognome, u.nome",
    nativeQuery = false,
    entityManager = Db1DatabaseConfiguration.DB1_ENTITY_MANAGER   // "postgresEntityManager"
)
public class UtenteSheet extends QuerySheetData<UtenteRow> {
    public UtenteSheet(String sheetName) { super(sheetName); }
}
```

### `UtenteEntitySheet` — from DB2 (secondary datasource)

```java
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
@ExcelQuery(
    value = "select new bld.generator.report.junit.entity.UtenteEntityRow(" +
            "u.id, u.firstName, u.lastName, u.email) " +
            "from UserEntity u order by u.lastName, u.firstName",
    nativeQuery = false,
    entityManager = Db2DatabaseConfiguration.DB2_ENTITY_MANAGER   // "postgres2EntityManager"
)
public class UtenteEntitySheet extends QuerySheetData<UtenteEntityRow> {
    public UtenteEntitySheet(String sheetName) { super(sheetName); }
}
```

The two sheets use different `EntityManager` beans, transparently routing each query to the correct database.

---

## Test Class — `ReportTestMultipleDataSource`

### `test()` — Multi-datasource Excel generation

```java
@SpringBootTest
@ComponentScan(basePackages = {"com.bld.generator", "com.bld.read"})
@EnableTransactionManagement
public class ReportTestMultipleDataSource {

    @Autowired private GenerateExcel generateExcel;
    @Autowired private ReadExcel     readExcel;

    @Test
    public void test() throws Exception {
        List<BaseSheet> sheets = new ArrayList<>();

        // DB1 — Users from primary database
        UtenteSheet utenteSheet = new UtenteSheet("Utente");
        utenteSheet.getMapParameters().put("cognome", "Rossi");
        sheets.add(utenteSheet);

        // DB2 — Users from secondary database
        UtenteEntitySheet utenteEntitySheet = new UtenteEntitySheet("Utente Entity");
        sheets.add(utenteEntitySheet);

        // Shared sheets (queried via default EntityManager)
        sheets.add(new GenereSheet("Genere"));

        AutoreLibriSheet autoreLibriSheet = new AutoreLibriSheet("Libri d'autore", "Test label");
        TotaleAutoreLibriSheet totale = new TotaleAutoreLibriSheet();
        totale.addRows(new TotaleAutoreLibriRow("Totale"));
        autoreLibriSheet.setSheetFunctionsTotal(totale);
        sheets.add(autoreLibriSheet);

        ReportExcel excel = new ReportExcel("Mondadori Multiple Datasource", sheets);
        byte[] bytes = generateExcel.createFileXlsx(excel);
        SpreadsheetUtils.writeToFile("/mnt/report/", excel.getTitle(), ".xlsx", bytes);
    }
```

### `testRead()` — Read the generated file back

```java
    @Test
    public void testRead() throws Exception {
        byte[] report = IOUtils.toByteArray(
            new FileInputStream("/mnt/report/Mondadori-Dynamic.xlsx")
        );

        ExcelRead excelRead = new ExcelRead();
        excelRead.setReportExcel(report);
        excelRead.setExcelType(ExcelType.XLSX);
        excelRead.getListSheetRead().add(new ReadAutoreLibriSheet("Libri d'autore"));
        excelRead.getListSheetRead().add(new ReadGenereSheet("Genere"));
        excelRead = readExcel.convertExcelToEntity(excelRead);

        ReadAutoreLibriSheet sheet = excelRead.getSheet(ReadAutoreLibriSheet.class, "Libri d'autore");
        for (ReadAutoreLibriRow row : sheet.getListRowSheet())
            System.out.println(row);

        ReadGenereSheet genereSheet = excelRead.getSheet(ReadGenereSheet.class, "Genere");
        for (ReadGenereRow row : genereSheet.getListRowSheet())
            System.out.println(row);
    }
}
```

---

## How Multiple Datasource Works

1. Set `bld.commons.multiple.datasource: true` in `application.yml` to activate secondary datasource beans.
2. Define each datasource with its own `@Configuration` class extending `MultipleDatabaseConfiguration`.
3. Mark the primary datasource beans with `@Primary`.
4. Use `@ConditionalOnProperty("com.bld.commons.multiple.datasource")` on the secondary configuration to keep it optional.
5. In each `@ExcelQuery`, set `entityManager` to the bean name constant from the corresponding configuration class.
6. The library will route the query to the correct `EntityManager` at generation time.

---

## Project Structure

```
report-excel-multiple-datasource/
├── src/
│   ├── main/java/bld/generator/report/
│   │   ├── ReportExcelApplication.java
│   │   ├── config/
│   │   │   ├── Db1DatabaseConfiguration.java   (primary — port 5432)
│   │   │   └── Db2DatabaseConfiguration.java   (secondary — port 5433)
│   │   ├── db1/persistence/
│   │   │   ├── domain/   (Utente, UtenteRepository)
│   │   │   └── service/  (UtenteService, UtenteServiceImpl)
│   │   └── db2/persistence/
│   │       ├── domain/   (UserEntity, UserEntityRepository)
│   │       └── service/  (UserEntityService, UserEntityServiceImpl)
│   └── test/java/bld/generator/report/
│       ├── junit/ReportTestMultipleDataSource.java
│       └── junit/entity/
│           ├── UtenteSheet.java / UtenteRow.java        (DB1)
│           ├── UtenteEntitySheet.java / UtenteEntityRow.java  (DB2)
│           ├── AutoreLibriSheet.java / AutoreLibriRow.java
│           ├── GenereSheet.java / GenereRow.java
│           └── TotaleAutoreLibriSheet.java / TotaleAutoreLibriRow.java
└── src/test/resources/
    └── application.yml
```
