package bld.generator.report.csv;

public class CsvHeader {

	private String name;

	private Double index;

	public CsvHeader(String name, Double index) {
		super();
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public Double getIndex() {
		return index;
	}
}
