package bld.report.controller.input;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import bld.read.report.excel.domain.ExcelRead;
import bld.read.report.excel.json.annotation.JsonExcel;
import bld.read.report.excel.json.annotation.JsonSheet;
import bld.report.controller.entity.ReadAutoreLibriSheet;
import bld.report.controller.entity.ReadGenereSheet;

public class ReadExcelModel {

	@JsonProperty("excel")
	@JsonExcel({
		@JsonSheet(name = "Libri d'autore", sheetClass = ReadAutoreLibriSheet.class),
		@JsonSheet(name = "Genere", sheetClass = ReadGenereSheet.class)
	})
	@Valid
	private ExcelRead excelRead;

	public ExcelRead getExcelRead() {
		return excelRead;
	}

	public void setExcelRead(ExcelRead excelRead) {
		this.excelRead = excelRead;
	}

	
	
	
	
}
