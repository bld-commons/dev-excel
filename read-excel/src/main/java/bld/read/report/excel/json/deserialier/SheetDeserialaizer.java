/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.excel.json.deserialier;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Base64;

import org.apache.commons.beanutils.PropertyUtils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
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
import bld.read.report.excel.json.annotation.JsonSheet;
import bld.read.report.excel.json.annotation.JsonSheets;

/**
 * The Class ExcelReadDeserialaizer.
 */
@SuppressWarnings({"serial","unchecked"})
public class SheetDeserialaizer extends StdDeserializer<Object> implements ContextualDeserializer {

	/** The sheets. */
	private JsonSheet[] sheets;

	/** The obj class. */
	private Class<?> objClass;

	/** The field not null. */
	private boolean fieldNotNull;

	/**
	 * Instantiates a new sheet deserialaizer.
	 */
	public SheetDeserialaizer() {
		super(Object.class);
	}

	/**
	 * Instantiates a new excel read deserialaizer.
	 *
	 * @param objClass     the obj class
	 * @param sheets       the sheets.
	 * @param fieldNotNull the field not null
	 */
	protected SheetDeserialaizer(Class<?> objClass, JsonSheet[] sheets, boolean fieldNotNull) {
		super(objClass);
		this.objClass = objClass;
		this.sheets = sheets;
		this.fieldNotNull = fieldNotNull;
	}

	/** The Constant MIME_TYPE_XLS. */
	private static final String MIME_TYPE_XLS = "application/vnd.ms-excel";

	/** The Constant MIME_TYPE_XLSX. */
	private static final String MIME_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	/**
	 * Deserialize.
	 *
	 * @param jp   the jp
	 * @param ctxt the ctxt
	 * @return the object
	 * @throws IOException      Signals that an I/O exception has occurred.
	 * @throws JacksonException the jackson exception
	 */
	@Override
	public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
		Object obj = null;
		String file = jp.getText();
		String partSeparator = ",";
		String[] propsFile = file.split(partSeparator);
		ExcelType excelType = ExcelType.XLS;
		if (!propsFile[0].contains(MIME_TYPE_XLS) && !propsFile[0].contains(MIME_TYPE_XLSX))
			throw new IOException("The file type is not valid");
		if (propsFile[0].contains(MIME_TYPE_XLSX))
			excelType = ExcelType.XLSX;
		file = file.substring(file.indexOf(partSeparator) + 1);
		byte[] excelByteArray = Base64.getDecoder().decode(file);
		ReadExcel readExcel = ExcelUtils.getApplicationContext().getBean(ReadExcel.class);

		ExcelRead excelRead = null;
		try {

			excelRead = new ExcelRead();
			excelRead.setExcelType(excelType);
			excelRead.setReportExcel(excelByteArray);
			for (JsonSheet sheet : sheets) {
				Class<? extends SheetRead<? extends RowSheetRead>> rowSheetReadClass = null;
				if (this.fieldNotNull) {
					Field field = this.objClass.getDeclaredField(sheet.fieldName());
					rowSheetReadClass = (Class<? extends SheetRead<? extends RowSheetRead>>) field.getType();
				} else
					rowSheetReadClass = (Class<? extends SheetRead<? extends RowSheetRead>>) this.objClass;
				excelRead.addSheetConvertion(rowSheetReadClass, sheet.name(), sheet.keyField());

			}

			readExcel.convertExcelToEntity(excelRead);
			if (this.fieldNotNull)
				obj = getObject(excelRead);
			else
				obj=excelRead.getSheet((Class<? extends SheetRead<? extends RowSheetRead>>) this.objClass, sheets[0].name());

		} catch (Exception e) {
			throw new IOException(e);
		}
		return obj;

	}

	/**
	 * Gets the object.
	 *
	 * @param excelRead the excel read
	 * @return the object
	 * @throws InstantiationException    the instantiation exception
	 * @throws IllegalAccessException    the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException     the no such method exception
	 * @throws NoSuchFieldException      the no such field exception
	 */
	private Object getObject(ExcelRead excelRead) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
		Object obj;
		obj = this.objClass.getDeclaredConstructor().newInstance();
		for (JsonSheet sheet : sheets) {
			Field field = this.objClass.getDeclaredField(sheet.fieldName());
			SheetRead<? extends RowSheetRead> sheetRead = excelRead.getSheet((Class<? extends SheetRead<? extends RowSheetRead>>) field.getType(), sheet.name());
			PropertyUtils.setProperty(obj, sheet.fieldName(), sheetRead);
		}
		return obj;
	}

	/**
	 * Creates the contextual.
	 *
	 * @param ctxt     the ctxt
	 * @param property the property
	 * @return the json deserializer
	 * @throws JsonMappingException the json mapping exception
	 */
	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
		JsonSheet[] sheets = null;
		boolean fieldNotNull = false;
		if (property.getAnnotation(JsonSheets.class) != null) {
			sheets = getJsonSheet(property.getAnnotation(JsonSheets.class).value());
			fieldNotNull = true;
		} else if (property.getAnnotation(JsonSheet.class) != null)
			sheets = getJsonSheet(property.getAnnotation(JsonSheet.class));

		JavaType type = ctxt.getContextualType();
		return new SheetDeserialaizer(type.getRawClass(), sheets, fieldNotNull);
	}

	/**
	 * Gets the json sheet.
	 *
	 * @param sheets the sheets
	 * @return the json sheet
	 */
	private JsonSheet[] getJsonSheet(JsonSheet... sheets) {
		return sheets;
	}

}
