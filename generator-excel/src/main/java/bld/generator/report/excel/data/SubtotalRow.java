package bld.generator.report.excel.data;

public class SubtotalRow {

	
	private int emptyRow;
	
	private String label;
	
	public SubtotalRow() {
	}
	
	public SubtotalRow(int emptyRow) {
		super();
		this.emptyRow = emptyRow;
	}


	public SubtotalRow(int emptyRow, String label) {
		super();
		this.emptyRow = emptyRow;
		this.label = label;
	}

	public int getEmptyRow() {
		return emptyRow;
	}

	public String getLabel() {
		return label;
	}

	public void setEmptyRow(int emptyRow) {
		this.emptyRow = emptyRow;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	

}
