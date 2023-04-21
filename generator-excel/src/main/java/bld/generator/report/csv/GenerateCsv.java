package bld.generator.report.csv;

public interface GenerateCsv {

	public <T extends CsvRow> byte[] generateCsv(CsvData<T> csvSheet) throws Exception;
	
}
