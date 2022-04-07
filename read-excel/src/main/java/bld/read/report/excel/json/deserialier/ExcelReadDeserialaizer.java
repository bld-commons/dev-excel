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
import bld.read.report.excel.json.annotation.JsonExcel;
import bld.read.report.excel.json.annotation.JsonSheet;

/**
 * The Class ExcelReadDeserialaizer.
 */
@SuppressWarnings("serial")
public class ExcelReadDeserialaizer extends StdDeserializer<ExcelRead>  implements ContextualDeserializer{

	/** The sheets. */
	private JsonSheet[] sheets;
	
	private Class<? extends ExcelRead> excelReadClass;
	
	
	public ExcelReadDeserialaizer() {
		super(ExcelRead.class);
		this.excelReadClass=ExcelRead.class;
	}

	/**
	 * Instantiates a new excel read deserialaizer.
	 *
	 * @param vc the vc
	 */
	protected ExcelReadDeserialaizer(Class<? extends ExcelRead> vc) {
		super(vc);
		this.excelReadClass=vc;
	}
	
	

	public ExcelReadDeserialaizer(JsonSheet[] sheets, Class<? extends ExcelRead> excelReadClass) {
		super(excelReadClass);
		this.sheets = sheets;
		this.excelReadClass = excelReadClass;
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
	 * @return the excel read
	 * @throws IOException      Signals that an I/O exception has occurred.
	 * @throws JacksonException the jackson exception
	 */
	@Override
	public ExcelRead deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
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
			excelRead=this.excelReadClass.getDeclaredConstructor().newInstance();
			excelRead.setExcelType(excelType);
			excelRead.setReportExcel(excelByteArray);
			for(JsonSheet sheet:sheets)
				excelRead.addSheetConvertion(sheet.sheetClass(), sheet.name());
			readExcel.convertExcelToEntity(excelRead);
		} catch (Exception e) {
			throw new IOException(e);
		}
		
		return excelRead;
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
		JsonExcel jsonExcel = property.getAnnotation(JsonExcel.class);
		this.sheets=jsonExcel.value();
		if (property.getType() != null && property.getType().getRawClass() != null)
			return new ExcelReadDeserialaizer(this.sheets,(Class<? extends ExcelRead>) property.getType().getRawClass());
		else
			return this;
	}
	
	

}
