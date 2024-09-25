package bld.report.controller.input;

import jakarta.validation.Valid;

import com.bld.read.report.excel.json.annotation.JsonSheet;
import com.fasterxml.jackson.annotation.JsonProperty;

import bld.report.controller.entity.ReadAutoreLibriSheet;

public class ReadSheetModel {

	@Valid
	@JsonProperty("excel")
	@JsonSheet(fieldName = "readAutoreLibriSheet", name = "Libri d'autore")
	private ReadAutoreLibriSheet readAutoreLibriSheet;

	public ReadAutoreLibriSheet getReadAutoreLibriSheet() {
		return readAutoreLibriSheet;
	}

	public void setReadAutoreLibriSheet(ReadAutoreLibriSheet readAutoreLibriSheet) {
		this.readAutoreLibriSheet = readAutoreLibriSheet;
	}

}
