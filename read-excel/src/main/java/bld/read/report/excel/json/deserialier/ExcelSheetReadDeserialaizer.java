/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
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

/**
 * The Class ExcelSheetReadDeserialaizer.
 */
@SuppressWarnings("serial")
public class ExcelSheetReadDeserialaizer extends StdDeserializer<SheetRead<? extends RowSheetRead>>  implements ContextualDeserializer{

	/** The json sheet read. */
	private JsonSheetRead jsonSheetRead;
	
	/** The sheet read class. */
	private Class<? extends SheetRead<? extends RowSheetRead>> sheetReadClass;
	
	
	/**
	 * Instantiates a new excel sheet read deserialaizer.
	 */
	public ExcelSheetReadDeserialaizer() {
		super(SheetRead.class);
	}	



	/**
	 * Instantiates a new excel sheet read deserialaizer.
	 *
	 * @param vc the vc
	 */
	protected ExcelSheetReadDeserialaizer(Class<SheetRead<? extends RowSheetRead>> vc) {
		super(vc);
		this.sheetReadClass=vc;
	}

	
	
	/**
	 * Instantiates a new excel sheet read deserialaizer.
	 *
	 * @param vc    the vc
	 * @param sheet the sheet
	 */
	public ExcelSheetReadDeserialaizer(Class<? extends SheetRead<? extends RowSheetRead>> vc, JsonSheetRead sheet) {
		super(vc);
		this.sheetReadClass=vc;
		this.jsonSheetRead = sheet;
	}



	/** The Constant MIME_TYPE_XLS. */
	private static final String MIME_TYPE_XLS = "application/vnd.ms-excel";

	/** The Constant MIME_TYPE_XLSX. */
	private static final String MIME_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	/**
	 * Deserialize.
	 *
	 * @param p    the p
	 * @param ctxt the ctxt
	 * @return the sheet read<? extends row sheet read>
	 * @throws IOException      Signals that an I/O exception has occurred.
	 * @throws JacksonException the jackson exception
	 */
	@Override
	public SheetRead<? extends RowSheetRead> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		String file = p.getText();
		String partSeparator = ",";
		String[] propsFile = file.split(partSeparator);
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

	/**
	 * Creates the contextual.
	 *
	 * @param ctxt     the ctxt
	 * @param property the property
	 * @return the json deserializer
	 * @throws JsonMappingException the json mapping exception
	 */
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
