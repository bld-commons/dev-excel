/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.report.generator.junit.entity.EmployeeCsvData
 */
package bld.report.generator.junit.entity;

import com.bld.common.spreadsheet.csv.annotation.CsvSettings;
import com.bld.generator.report.csv.CsvData;

/**
 * CSV data container for {@link EmployeeRow} rows.
 * <p>
 * Configured with a comma delimiter and with header record writing enabled
 * ({@code skipHeaderRecord = false}).
 * </p>
 */
@CsvSettings(skipHeaderRecord = false, delimiter = ',')
public class EmployeeCsvData extends CsvData<EmployeeRow> {

}
