package bld.generator.report.excel.data;

public class InfoChart {

	private String title;
	
	private String function;
	
	private int firstRow;
	
	private int lastRow;
	

	public InfoChart(String title,String function, int firstRow) {
		super();
		this.title=title;
		this.function = function;
		this.firstRow = firstRow;
		this.lastRow=firstRow;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getLastRow() {
		return lastRow;
	}

	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
