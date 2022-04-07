package bld.report.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bld.read.report.excel.domain.RowSheetRead;
import bld.read.report.excel.domain.SheetRead;
import bld.report.controller.entity.ReadAutoreLibriRow;
import bld.report.controller.input.ReadExcelModel;
import bld.report.controller.input.ReadSheetModel;

@RestController
@RequestMapping("/excel")
public class ExcelController {

	@PostMapping(path = "/sheet-read", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void readSheet(@RequestBody ReadSheetModel readSheetModel) {
		if (readSheetModel.getReadAutoreLibriSheet() != null)
			for (ReadAutoreLibriRow row : readSheetModel.getReadAutoreLibriSheet().getListRowSheet())
				System.out.println(row);
	}
	
	
	@PostMapping(path = "/excel-read", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void readExcel(@RequestBody ReadExcelModel readExcelModel) {
		for(SheetRead<? extends RowSheetRead>sheet:readExcelModel.getExcelRead().getListSheetRead()) {
			System.out.println("--------------------------"+sheet.getSheetName()+"--------------------------");
			for(RowSheetRead row:sheet.getListRowSheet())
				System.out.println(row);
		}
			
	}
}
