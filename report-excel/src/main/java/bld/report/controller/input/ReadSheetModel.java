package bld.report.controller.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import bld.read.report.excel.json.annotation.JsonSheetRead;
import bld.report.controller.entity.ReadAutoreLibriSheet;

public class ReadSheetModel {

	@JsonProperty("excel")
	@JsonSheetRead("Libri d'autore")
	private ReadAutoreLibriSheet readAutoreLibriSheet;

	public ReadAutoreLibriSheet getReadAutoreLibriSheet() {
		return readAutoreLibriSheet;
	}

	public void setReadAutoreLibriSheet(ReadAutoreLibriSheet readAutoreLibriSheet) {
		this.readAutoreLibriSheet = readAutoreLibriSheet;
	}
	
	
	
	
	
}
