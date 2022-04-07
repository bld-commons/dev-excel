package bld.read.report.excel.json.deserialier;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import bld.generator.report.utils.ExcelUtils;
import bld.read.report.excel.ReadExcel;
import bld.read.report.excel.domain.ExcelRead;
import bld.read.report.excel.json.annotation.JsonExcel;
import bld.read.report.excel.json.annotation.JsonSheet;

@SuppressWarnings("serial")
public class ExcelReadDeserialaizer extends StdDeserializer<ExcelRead>  implements ContextualDeserializer{

	private JsonSheet[] sheets;
	
	protected ExcelReadDeserialaizer(Class<?> vc) {
		super(vc);
	}

	private static final String MIME_TYPE_XLS = "application/vnd.ms-excel";

	private static final String MIME_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	@Override
	public ExcelRead deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		String file = p.getText();
		String partSeparator = ",";
		String[] propsFile = file.split(partSeparator);
		if (!propsFile[0].contains(MIME_TYPE_XLS) && propsFile[0].contains(MIME_TYPE_XLSX))
			throw new IOException("The file type is not valid");
		file = propsFile[1];
		byte[] excelByteArray = Base64.getDecoder().decode(file);
		ReadExcel readExcel=ExcelUtils.getApplicationContext().getBean(ReadExcel.class);
		
		ExcelRead excelRead=null;
		try {
			excelRead=new ExcelRead();
			excelRead.setReportExcel(excelByteArray);
			for(JsonSheet sheet:sheets)
				excelRead.addSheetConvertion(sheet.sheetClass(), sheet.name());
			readExcel.convertExcelToEntity(excelRead);
		} catch (Exception e) {
			throw new IOException(e);
		}
		
		return excelRead;
	}

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
		JsonExcel jsonExcel = property.getAnnotation(JsonExcel.class);
		this.sheets=jsonExcel.value();
		return this;
	}
	
	

}
