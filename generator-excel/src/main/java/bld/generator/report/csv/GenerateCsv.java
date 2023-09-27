package bld.generator.report.csv;


/**
 * The Interface GenerateCsv.
 */
public interface GenerateCsv {

	/**
	 * Generate csv.
	 *
	 * @param <T> the generic type
	 * @param csvSheet the csv sheet
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	public <T extends CsvRow> byte[] generateCsv(CsvData<T> csvSheet) throws Exception;
	
}
