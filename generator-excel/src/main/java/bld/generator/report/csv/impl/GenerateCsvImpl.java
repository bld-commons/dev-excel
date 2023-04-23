package bld.generator.report.csv.impl;

import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bld.common.spreadsheet.csv.annotation.CsvSettings;
import bld.common.spreadsheet.exception.CsvGeneratorException;
import bld.common.spreadsheet.utils.CsvUtils;
import bld.common.spreadsheet.utils.SpreadsheetUtils;
import bld.generator.report.comparator.CsvColumnComparator;
import bld.generator.report.csv.CsvData;
import bld.generator.report.csv.CsvHeader;
import bld.generator.report.csv.CsvRow;
import bld.generator.report.csv.GenerateCsv;
import bld.generator.report.csv.QueryCsvData;
import bld.generator.report.csv.annotation.CsvColumn;
import bld.generator.report.csv.query.CsvQueryComponent;

@Component
public class GenerateCsvImpl implements GenerateCsv {

	@Autowired(required = false)
	private CsvQueryComponent csvQueryComponent;
	
	@Override
	public <T extends CsvRow> byte[] generateCsv(CsvData<T> csvData) throws Exception {
		byte[] csv = null;
		if(this.csvQueryComponent!=null && csvData instanceof QueryCsvData)
			this.csvQueryComponent.executeQuery((QueryCsvData<T>)csvData);
		if (CollectionUtils.isNotEmpty(csvData.getRows())) {
			CsvSettings csvSettings = SpreadsheetUtils.getAnnotation(csvData.getClass(), CsvSettings.class);
			List<CsvHeader> headers = getHeader(csvData.getRowClass());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final int size = headers.size();
			String[] header = new String[size];
			for (int i = 0; i < headers.size(); i++)
				header[i] = headers.get(i).getName();
			CSVFormat csvFormat = CsvUtils.getCsvFormat(csvSettings,header);
			CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(baos), csvFormat);
			Object[] row=null;
			SimpleDateFormat sdf = null;
			for (T csvRow : csvData.getRows()) {
				row = new Object[size];
				for (int i = 0; i < headers.size(); i++) {
					CsvHeader csvHeader = headers.get(i);
					Object value = PropertyUtils.getProperty(csvRow, csvHeader.getField().getName());
					if (value!=null && csvHeader.getDateFormat() != null) {
						String format = csvHeader.getDateFormat().value().getValue();
						format = format.replace("/", csvHeader.getDateFormat().separator());
						sdf = new SimpleDateFormat(format);
						if (value instanceof Date)
							value = sdf.format((Date) value);
						else if(value instanceof Calendar)
							value=sdf.format(((Calendar)value).getTime());
						else if(value instanceof Timestamp)
							value=sdf.format(((Timestamp)value).getTimestamp());
					}
					row[i]=value;
				}
				csvPrinter.printRecord(row);
				
			}
			csvPrinter.flush();
			csv=baos.toByteArray();
			csvPrinter.close();
		} else
			throw new CsvGeneratorException("The csv list cannot be empty");
		return csv;
	}

	private <T extends CsvRow> List<CsvHeader> getHeader(Class<T> classRow) {
		List<CsvHeader> headers = new ArrayList<>();
		Set<Field> fields = SpreadsheetUtils.getListField(classRow);
		for (Field field : fields) {
			CsvColumn column = field.getAnnotation(CsvColumn.class);
			if (column != null) {
				headers.add(new CsvHeader(field));
			}
		}
		Collections.sort(headers, new CsvColumnComparator());
		return headers;
	}

}
