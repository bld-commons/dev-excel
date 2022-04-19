package bld.report.controller.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import bld.read.report.excel.json.annotation.JsonSheet;
import bld.read.report.excel.json.annotation.JsonSheets;


public class ReadExcelModel {

	
	private String name;
	
	@JsonSheets({
			@JsonSheet(name = "Libri d'autore", fieldName = "autoreLibri"),
			@JsonSheet(name = "Genere", fieldName="genere")
		})
	@JsonProperty("excel")
	private ReadSheetsModel readSheetsModel;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ReadSheetsModel getReadSheetsModel() {
		return readSheetsModel;
	}

	public void setReadSheetsModel(ReadSheetsModel readSheetsModel) {
		this.readSheetsModel = readSheetsModel;
	}
	
	
	
	
}
