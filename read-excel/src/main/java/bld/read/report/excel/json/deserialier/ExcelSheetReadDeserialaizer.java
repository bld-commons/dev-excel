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
import bld.read.report.excel.constant.ExcelType;
import bld.read.report.excel.domain.ExcelRead;
import bld.read.report.excel.domain.RowSheetRead;
import bld.read.report.excel.domain.SheetRead;
import bld.read.report.excel.json.annotation.JsonSheetRead;

@SuppressWarnings("serial")
public class ExcelSheetReadDeserialaizer extends StdDeserializer<SheetRead<? extends RowSheetRead>>  implements ContextualDeserializer{

	private JsonSheetRead jsonSheetRead;
	
	private Class<? extends SheetRead<? extends RowSheetRead>> sheetReadClass;
	
	
	public ExcelSheetReadDeserialaizer() {
		super(SheetRead.class);
	}	



	protected ExcelSheetReadDeserialaizer(Class<SheetRead<? extends RowSheetRead>> vc) {
		super(vc);
		this.sheetReadClass=vc;
	}

	
	
	public ExcelSheetReadDeserialaizer(Class<? extends SheetRead<? extends RowSheetRead>> vc, JsonSheetRead sheet) {
		super(vc);
		this.sheetReadClass=vc;
		this.jsonSheetRead = sheet;
	}



	private static final String MIME_TYPE_XLS = "application/vnd.ms-excel";

	private static final String MIME_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	@Override
	public SheetRead<? extends RowSheetRead> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		String file = p.getText();
		String partSeparator = ",";
		String[] propsFile = file.split(partSeparator);
		boolean value=false;
		ExcelType excelType=ExcelType.XLS;
		if (!propsFile[0].contains(MIME_TYPE_XLS) && !propsFile[0].contains(MIME_TYPE_XLSX))
			throw new IOException("The file type is not valid");
		if(propsFile[0].contains(MIME_TYPE_XLSX))
			excelType=ExcelType.XLSX;
		file = file.substring(file.indexOf(partSeparator)+1);
		byte[] excelByteArray = Base64.getDecoder().decode(file);
		ReadExcel readExcel=ExcelUtils.getApplicationContext().getBean(ReadExcel.class);
		
		ExcelRead excelRead=null;
		try {
			excelRead=new ExcelRead();
			excelRead.setExcelType(excelType);
			excelRead.setReportExcel(excelByteArray);
			excelRead.addSheetConvertion(this.sheetReadClass, this.jsonSheetRead.value());
			readExcel.convertExcelToEntity(excelRead);
		} catch (Exception e) {
			
			throw new IOException(e);
		}
		
		
		return excelRead.getSheet(this.sheetReadClass, this.jsonSheetRead.value());
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
		this.jsonSheetRead = property.getAnnotation(JsonSheetRead.class);
		if (property.getType() != null && property.getType().getRawClass() != null)
			return new ExcelSheetReadDeserialaizer((Class<? extends SheetRead<? extends RowSheetRead>>) property.getType().getRawClass(), this.jsonSheetRead);
		else
			return this;
	}
	
	

}
