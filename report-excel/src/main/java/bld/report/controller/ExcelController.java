package bld.report.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bld.read.report.excel.domain.RowSheetRead;
import bld.report.controller.input.ReadExcelModel;
import bld.report.controller.input.ReadSheetModel;

@RestController
@RequestMapping("/excel")
public class ExcelController {

	@PostMapping(path = "/sheet-read", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void readSheet(@RequestBody @Valid ReadSheetModel readSheetModel) {
		if (readSheetModel.getReadAutoreLibriSheet() != null)
			readObj(readSheetModel.getReadAutoreLibriSheet().getListRowSheet());
	}
	
	
	@PostMapping(path = "/excel-read", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void readExcel(@RequestBody @Valid ReadExcelModel readExcelModel) {
		
		readObj(readExcelModel.getReadSheetsModel().getAutoreLibri().getListRowSheet());
		readObj(readExcelModel.getReadSheetsModel().getGenere().getListRowSheet());
			
	}
	
	
	private void readObj(List<? extends RowSheetRead> rows) {
		for(RowSheetRead row:rows)
			System.out.println(row);
	}
	
}
