package bld.report.read.junit.entity;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.util.Calendar;

import com.bld.read.report.excel.annotation.ExcelReadSheet;
import com.bld.read.report.excel.domain.SheetRead;

import jakarta.validation.constraints.Size;

@ExcelReadSheet
public class DataMeteoSheet extends SheetRead<DataMeteoRow>{

	private int currentYear;
	
	
	public DataMeteoSheet(@Size(max = 31) String sheetName) {
		super(sheetName);
		Calendar cal=Calendar.getInstance();
		this.currentYear=cal.get(Calendar.YEAR);
	}

	@Override
	protected boolean filtered(DataMeteoRow t) {
		return t.getYear()<this.currentYear;
	}

}
