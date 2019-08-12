package bld.generator.report.excel;

import java.util.Map;

import javax.validation.constraints.Size;

public abstract class SheetFunctionTotal<T extends RowSheet> extends SheetData<T> {
	
	private Integer calRowStart;
	
	private Integer calRowEnd;
	
	

	@Override
	public void setMapFieldColumn(Map<String, Integer> mapFieldColumn) {
		super.setMapFieldColumn(mapFieldColumn);
		
	}

	public SheetFunctionTotal(@Size(max = 30) String nameSheet) {
		super(nameSheet);
	}

	public Integer getCalRowStart() {
		return calRowStart;
	}

	public void setCalRowStart(Integer calRowStart) {
		this.calRowStart = calRowStart;
	}

	public Integer getCalRowEnd() {
		return calRowEnd;
	}

	public void setCalRowEnd(Integer calRowEnd) {
		this.calRowEnd = calRowEnd;
	}

	
	
}
