# Changelog

All notable changes to **dev-excel** are documented in this file.

The project publishes to Maven Central under the group `com.github.bld-commons.excel`.  
Main modules: `generator-excel`, `read-excel`, `common-spreadsheet`.

---

## [Unreleased]

### 5.1.3 — 2026-03-30
**Stack:** Java 17 · Spring Boot 3.5.10 · Apache POI 5.5.1

- Upgrade Spring Boot to 3.5.10 and Apache POI to 5.5.1
- Added Italian README (`README.it.md`) for `common-spreadsheet`, `read-excel` and `generator-excel`
- Improved Javadoc on secondary annotations, `MapSheetRead` and utility classes
- Updated license file
- Minor formatter and code-style fixes

### 5.1.2 — 2026-03-18
**Stack:** Java 17 · Spring Boot 3.5.x · Apache POI 5.5.x

- Code formatter applied across all modules
- Internal version bump and POM alignment

### 5.1.1 — 2026-03-11
**Stack:** Java 17 · Spring Boot 3.5.x · Apache POI 5.5.x

- Excel and `read-excel` processing improvements
- Scope and dependency management refactoring
- Nexus publishing configuration updated
- Build and GitHub Actions pipeline updates

### 5.1.0 — 2026-01-27
**Stack:** Java 17 · Spring Boot 3.5.x · Apache POI 5.5.x

- New functional features in the generator
- Semgrep static analysis CI integration (GitHub Actions)
- General update of Spring Boot and Apache POI dependencies

---

## Released

### [5.0.5] — 2025-05-28
**Stack:** Java 17 · Spring Boot 3.3.4 · Apache POI 5.4.1

- **Java 17** — minimum required JVM raised from 11 to 17
- Upgrade Apache POI to **5.4.1**
- RGB colour support in cell/row styling
- Excel layout condition (`conditionexcellayout`) improvements
- Chart enhancements
- Value and salary report examples
- Dependency and POM alignment

---

### [5.0.4] — 2025-03-30
**Stack:** Java 11 · Spring Boot 3.3.x · Apache POI 5.3.x

- New features and POM version management
- Internal version bump

### [5.0.3] — 2025-03-30
**Stack:** Java 11 · Spring Boot 3.3.x · Apache POI 5.3.x

- Maven Central publishing configuration (`mvn`) updates
- Version alignment between modules

---

### [5.0.2] — 2024-12-02
**Stack:** Java 17 · Spring Boot 3.3.4 · Apache POI 5.3.0

- Upgrade Apache POI to **5.3.0**
- Data validation support (ported from 4.2.x branch)
- Issue and bug fixes
- Repository and `.gitignore` cleanup

### [5.0.1] — 2024-09-26
**Stack:** Java 11 · Spring Boot 3.3.x · Apache POI 5.2.3

- Spring Boot version update
- Maven repackage plugin configuration
- Javadoc/comment improvements

### [5.0.0] — 2024-05-03
**Stack:** Java 11 · Spring Boot 3.2.5 · Apache POI 5.2.3

- **Major release** — migration to **Spring Boot 3.x** (Jakarta EE namespace)
- Requires Java 11 minimum
- POM restructured for the new Spring Boot 3 dependency tree

---

### [4.2.2] — 2025-05-28
**Stack:** Java 8 · Spring Boot 2.7.18 · Apache POI 5.4.1  
_(legacy branch — Java 8 / Spring Boot 2.x long-term support)_

- Upgrade Apache POI to **5.4.1**
- RGB colour support in cell and row styling
- Excel layout condition (`conditionexcellayout`) support
- Function and value handling improvements
- Excel generation enhancements

### [4.2.1] — 2024-12-02
**Stack:** Java 8 · Spring Boot 2.7.18 · Apache POI 5.2.3

- Data validation annotation support
- `.gitignore` and repository cleanup
- Bug fixes

### [4.2.0] — 2024-05-03
**Stack:** Java 8 · Spring Boot 2.7.7 · Apache POI 5.2.3

- Excel workbook/sheet locking (protection) support

---

### [4.1.6] — 2024-03-19
**Stack:** Java 8 · Spring Boot 2.7.x · Apache POI 5.2.3

- Hidden column support
- Version management updates

### [4.1.5] — 2023-12-05
**Stack:** Java 8 · Spring Boot 2.7.x · Apache POI 5.2.3

- Issue and bug fixes
- Maven publishing configuration updates
- Number/numeric handling improvements
- Excel generation refinements

### [4.1.4] — 2023-11-27
**Stack:** Java 8 · Spring Boot 2.7.x · Apache POI 5.2.3

- POM updates
- Excel improvements
- Subtotal handling refinements

### [4.1.3] — 2023-11-24
**Stack:** Java 8 · Spring Boot 2.7.x · Apache POI 5.2.3

- Version alignment fixes

### [4.1.2] — 2023-11-23
**Stack:** Java 8 · Spring Boot 2.7.x · Apache POI 5.2.3

- Excel generation improvements
- Report module fixes

### [4.1.1] — 2023-09-27
**Stack:** Java 8 · Spring Boot 2.7.x · Apache POI 5.2.3

- Report generation improvements
- POM dependency updates

### [4.1.0] — 2023-04-23
**Stack:** Java 8 · Spring Boot 2.7.5 · Apache POI 5.2.3

- Upgrade Spring Boot to **2.7.5** and Apache POI to **5.2.3**
- CSV export support
- Auto-size column feature
- Config and report generation improvements

---

### [4.0.9] — 2022-09-14
**Stack:** Java 8 · Spring Boot 2.3.4 · Apache POI 5.0.0

- Dropdown improvements
- Sheet mapping (`@MapSheetRead`) enhancements

### [4.0.8] — 2022-06-23
**Stack:** Java 8 · Spring Boot 2.3.4 · Apache POI 5.0.0

- Chart improvements
- Formula handling fixes
- Read-excel enhancements
- Box/message cell support

### [4.0.7] — 2022-04-20
**Stack:** Java 8 · Spring Boot 2.3.4 · Apache POI 5.0.0

- Excel generation fixes

### [4.0.6] — 2022-04-19
**Stack:** Java 8 · Spring Boot 2.3.4 · Apache POI 5.0.0

- Read-excel module improvements
- Cell string reading enhancements
- Enable annotation refinements

### [4.0.5] — 2022-03-29
**Stack:** Java 8 · Spring Boot 2.3.4 · Apache POI 5.0.0

- Boolean cell type support

### [4.0.4] — 2022-03-08
**Stack:** Java 8 · Spring Boot 2.3.4 · Apache POI 5.0.0

- Excel generation improvements
- Version management

### [4.0.3] — 2021-12-09
**Stack:** Java 8 · Spring Boot 2.3.4 · Apache POI 5.0.0

- NullPointerException fixes
- Version and POM alignment

### [4.0.2] — 2021-09-08
**Stack:** Java 8 · Spring Boot 2.3.4 · Apache POI 5.0.0

- Label improvements
- Release configuration fixes

### [4.0.1] — 2021-08-11
**Stack:** Java 11 · Spring Boot 2.3.4 · Apache POI 5.0.0

- Chart rendering improvements
- **Java 11** target introduced in this patch
- Javadoc/comment additions

### [4.0.0] — 2021-07-14
**Stack:** Java 8 · Spring Boot 2.3.4 · Apache POI 5.0.0

- **Major release** — upgrade to **Apache POI 5.0.0**
- New **`read-excel`** module for reading `.xlsx` files
- CSV file reading support
- Chart and image support improvements
- `@EnableComponentScan` / enable-annotation support
- Utility and function array enhancements

---

### [3.1.3] — 2021-03-03
**Stack:** Java 8 · Spring Boot 2.3.4 · Apache POI 4.1.2

- Chart support (line, bar, pie)
- Chart title configuration
- Subtotal refinements

### [3.1.2] — 2020-11-26
**Stack:** Java 8 · Spring Boot 2.3.4 · Apache POI 4.1.2

- **Big data / large dataset** streaming support
- Formula generation improvements
- Attachment/file embedding support
- Logger integration
- Documentation and configuration improvements

### [3.1.1] — 2020-10-29
**Stack:** Java 8 · Spring Boot 2.3.4 · Apache POI 4.1.2

- Subtotal function improvements
- Cell comment refinements
- Test class cleanup

### [3.1.0] — 2020-10-29
**Stack:** Java 8 · Spring Boot 2.3.4 · Apache POI 4.1.2

- Subtotal / aggregate row support
- Dropdown in summary sheets
- Image and comment cell support
- Datasource instance component management
- Header cell configuration

---

### [3.0.3] — 2020-10-12
**Stack:** Java 8 · Spring Boot 2.3.0 · Apache POI 4.1.2

- **Multiple datasource** support
- Datasource resource management
- Component and documentation improvements

### [3.0.2] — 2020-09-24
**Stack:** Java 8 · Spring Boot 2.3.0 · Apache POI 4.1.2

- **Dropdown** / data-validation support
- Date parsing error fix

### [3.0.1.1] — 2020-08-28
**Stack:** Java 8 · Spring Boot 2.3.0 · Apache POI 4.1.2

- Header cell style improvements
- Area/border styling (`areaBorder`)
- Property and query annotation refinements

### [3.0.1] — 2020-08-27
**Stack:** Java 8 · Spring Boot 2.3.0 · Apache POI 4.1.2

- Row colouring support
- Pivot table support
- Documentation improvements

### [3.0.0] — 2020-07-12
**Stack:** Java 8 · Spring Boot 2.3.0 · Apache POI 4.1.2

- Upgrade Spring Boot to **2.3.0** and Apache POI to **4.1.2**
- RGB colour model support
- JPA integration for data source queries
- `startRow` configuration
- Lombok dependency removed
- Extensive Javadoc and README updates

---

### [2.0.5] — 2020-02-26
**Stack:** Java 8 · Spring Boot 2.1.6 · Apache POI 4.1.1

- Scope management improvements
- Statistics fixes

### [2.0.4] — 2020-01-18
**Stack:** Java 8 · Spring Boot 2.1.6 · Apache POI 4.1.1

- Extended statistics (stat) aggregations

### [2.0.3] — 2020-01-10
**Stack:** Java 8 · Spring Boot 2.1.6 · Apache POI 4.1.1

- Statistics improvements
- Excel generation fixes

### [2.0.2] — 2020-01-08
**Stack:** Java 8 · Spring Boot 2.1.6 · Apache POI 4.1.1

- Bug fix: anomaly in report generation
- Report module improvements

### [2.0.1] — 2020-01-04
**Stack:** Java 8 · Spring Boot 2.1.6 · Apache POI 4.1.1

- Excel generation improvements
- Statistics support (`stat`)
- New version utilities

### [2.0.0] — 2019-12-29
**Stack:** Java 8 · Spring Boot 2.1.6 · Apache POI 4.1.1

- **Major release** — architecture refactoring
- Apache POI upgraded to **4.1.1**
- Deployment and branch strategy update

---

### [1.1.5] — 2019-12-28
**Stack:** Java 8 · Spring Boot 2.1.6 · Apache POI 4.1.0

- Statistics / aggregation support
- Excel generation fixes

### [1.1.4] — 2019-12-27
**Stack:** Java 8 · Spring Boot 2.1.6 · Apache POI 4.1.0

- Excel generation improvements

### [1.1.3] — 2019-12-18
**Stack:** Java 8 · Spring Boot 2.1.6 · Apache POI 4.1.0

- Resource management improvements
- Cell comment support

### [1.1.2] — 2019-11-13
**Stack:** Java 8 · Spring Boot 2.1.6 · Apache POI 4.1.0

- **Ignore columns** feature (`@IgnoreColumns`)
- Auto-documentation improvements

### [1.1.1] — 2019-11-09
**Stack:** Java 8 · Spring Boot 2.1.6 · Apache POI 4.1.0

- Read Excel initial support
- Generator and report improvements
- Version management

### [1.1.0] — 2019-10-27
**Stack:** Java 8 · Spring Boot 2.1.6 · Apache POI 4.1.0

- Label and index support
- Maven Central publishing setup
- Documentation and Javadoc improvements
- Snapshot version management

---

### [1.0.3] — 2019-09-20
**Stack:** Java 8 · Spring Boot 2.1.6 · Apache POI 4.1.0

- Excel and POM improvements
- Version alignment

### [1.0.2] — 2019-09-20
**Stack:** Java 8 · Spring Boot 2.1.6 · Apache POI 4.1.0

- Column configuration
- Total/sum cell calculation
- POM and dependency updates

### [1.0.1] — 2019-08-12
**Stack:** Java 8 · Spring Boot 2.1.6 · Apache POI 4.1.0

- Base report generation
- Initial project setup and `.gitignore`

### [1.0.0] — 2019-08-10
**Stack:** Java 8 · Spring Boot 2.1.6 · Apache POI 4.1.0

- First public release on Maven Central
- Annotation-driven Excel generation framework
- Core `generator-excel` module

---

[Unreleased]: https://github.com/bld-commons/dev-excel/compare/5.0.5...HEAD
[5.0.5]: https://github.com/bld-commons/dev-excel/compare/5.0.4...5.0.5
[5.0.4]: https://github.com/bld-commons/dev-excel/compare/5.0.3...5.0.4
[5.0.3]: https://github.com/bld-commons/dev-excel/compare/5.0.2...5.0.3
[5.0.2]: https://github.com/bld-commons/dev-excel/compare/5.0.1...5.0.2
[5.0.1]: https://github.com/bld-commons/dev-excel/compare/5.0.0...5.0.1
[5.0.0]: https://github.com/bld-commons/dev-excel/compare/4.2.2...5.0.0
[4.2.2]: https://github.com/bld-commons/dev-excel/compare/4.2.1...4.2.2
[4.2.1]: https://github.com/bld-commons/dev-excel/compare/4.2.0...4.2.1
[4.2.0]: https://github.com/bld-commons/dev-excel/compare/4.1.6...4.2.0
[4.1.6]: https://github.com/bld-commons/dev-excel/compare/4.1.5...4.1.6
[4.1.5]: https://github.com/bld-commons/dev-excel/compare/4.1.4...4.1.5
[4.1.4]: https://github.com/bld-commons/dev-excel/compare/4.1.3...4.1.4
[4.1.3]: https://github.com/bld-commons/dev-excel/compare/4.1.2...4.1.3
[4.1.2]: https://github.com/bld-commons/dev-excel/compare/4.1.1...4.1.2
[4.1.1]: https://github.com/bld-commons/dev-excel/compare/4.1.0...4.1.1
[4.1.0]: https://github.com/bld-commons/dev-excel/compare/4.0.9...4.1.0
[4.0.9]: https://github.com/bld-commons/dev-excel/compare/4.0.8...4.0.9
[4.0.8]: https://github.com/bld-commons/dev-excel/compare/4.0.7...4.0.8
[4.0.7]: https://github.com/bld-commons/dev-excel/compare/4.0.6...4.0.7
[4.0.6]: https://github.com/bld-commons/dev-excel/compare/4.0.5...4.0.6
[4.0.5]: https://github.com/bld-commons/dev-excel/compare/4.0.4...4.0.5
[4.0.4]: https://github.com/bld-commons/dev-excel/compare/4.0.3...4.0.4
[4.0.3]: https://github.com/bld-commons/dev-excel/compare/4.0.2...4.0.3
[4.0.2]: https://github.com/bld-commons/dev-excel/compare/4.0.1...4.0.2
[4.0.1]: https://github.com/bld-commons/dev-excel/compare/4.0.0...4.0.1
[4.0.0]: https://github.com/bld-commons/dev-excel/compare/3.1.3...4.0.0
[3.1.3]: https://github.com/bld-commons/dev-excel/compare/3.1.2...3.1.3
[3.1.2]: https://github.com/bld-commons/dev-excel/compare/3.1.1...3.1.2
[3.1.1]: https://github.com/bld-commons/dev-excel/compare/3.1.0...3.1.1
[3.1.0]: https://github.com/bld-commons/dev-excel/compare/3.0.3...3.1.0
[3.0.3]: https://github.com/bld-commons/dev-excel/compare/3.0.2...3.0.3
[3.0.2]: https://github.com/bld-commons/dev-excel/compare/3.0.1.1...3.0.2
[3.0.1.1]: https://github.com/bld-commons/dev-excel/compare/3.0.1...3.0.1.1
[3.0.1]: https://github.com/bld-commons/dev-excel/compare/3.0.0...3.0.1
[3.0.0]: https://github.com/bld-commons/dev-excel/compare/2.0.5...3.0.0
[2.0.5]: https://github.com/bld-commons/dev-excel/compare/2.0.4...2.0.5
[2.0.4]: https://github.com/bld-commons/dev-excel/compare/2.0.3...2.0.4
[2.0.3]: https://github.com/bld-commons/dev-excel/compare/2.0.2...2.0.3
[2.0.2]: https://github.com/bld-commons/dev-excel/compare/2.0.1...2.0.2
[2.0.1]: https://github.com/bld-commons/dev-excel/compare/2.0.0...2.0.1
[2.0.0]: https://github.com/bld-commons/dev-excel/compare/1.1.5...2.0.0
[1.1.5]: https://github.com/bld-commons/dev-excel/compare/1.1.4...1.1.5
[1.1.4]: https://github.com/bld-commons/dev-excel/compare/1.1.3...1.1.4
[1.1.3]: https://github.com/bld-commons/dev-excel/compare/1.1.2...1.1.3
[1.1.2]: https://github.com/bld-commons/dev-excel/compare/1.1.1...1.1.2
[1.1.1]: https://github.com/bld-commons/dev-excel/compare/1.1.0...1.1.1
[1.1.0]: https://github.com/bld-commons/dev-excel/compare/1.0.3...1.1.0
[1.0.3]: https://github.com/bld-commons/dev-excel/compare/1.0.2...1.0.3
[1.0.2]: https://github.com/bld-commons/dev-excel/compare/v1.0.1...1.0.2
[1.0.1]: https://github.com/bld-commons/dev-excel/compare/v1.0.0...v1.0.1
[1.0.0]: https://github.com/bld-commons/dev-excel/releases/tag/v1.0.0
