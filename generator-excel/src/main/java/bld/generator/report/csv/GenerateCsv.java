package bld.generator.report.csv;

import java.io.IOException;

public interface GenerateCsv {

	public <T extends RowCsv> byte[] generateCsv(CsvSheet<T> csvSheet) throws IOException;
	
}
