package bld.generator.report.csv.impl;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.stereotype.Component;

import bld.generator.report.comparator.CsvColumnComparator;
import bld.generator.report.csv.CsvHeader;
import bld.generator.report.csv.CsvSheet;
import bld.generator.report.csv.GenerateCsv;
import bld.generator.report.csv.RowCsv;
import bld.generator.report.csv.annotation.CsvColumn;
import bld.generator.report.exception.CsvGeneratorException;
import bld.generator.report.utils.ExcelUtils;

@Component
public class GenerateCsvImpl implements GenerateCsv {

	@Override
	public <T extends RowCsv> byte[] generateCsv(CsvSheet<T> csvSheet) throws IOException {
		byte[] csv=null;
		if(CollectionUtils.isNotEmpty(csvSheet.getRows())) {
			List<CsvHeader>headers=getHeader(csvSheet.getRowClass());
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			CSVPrinter csvPrinter=new CSVPrinter(new OutputStreamWriter(baos), csvSheet.getFormat());
			for(CsvHeader header:headers) {
				
			}
		}else 
			throw new CsvGeneratorException("The csv list cannot be empty");
		return csv;
	}
	
	private <T extends RowCsv> List<CsvHeader> getHeader(Class<T> classRow){
		List<CsvHeader>headers=new ArrayList<>();
		Set<Field> fields = ExcelUtils.getListField(classRow);
		for(Field field:fields) {
			CsvColumn column = field.getAnnotation(CsvColumn.class);
			if (column != null) {
				headers.add(new CsvHeader(column.name(), column.index()));
			}
		}
		Collections.sort(headers, new CsvColumnComparator());
		return headers;
	}

}
