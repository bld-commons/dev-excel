package bld.report.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bld.report.controller.entity.ReadAutoreLibriRow;
import bld.report.controller.input.ReadExcelModel;

@RestController
@RequestMapping("/excel")
public class ExcelController {

	@PostMapping(path = "/read", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void readExcel(@RequestBody ReadExcelModel readExcelModel) {
		if (readExcelModel.getReadAutoreLibriSheet() != null)
			for (ReadAutoreLibriRow row : readExcelModel.getReadAutoreLibriSheet().getListRowSheet())
				System.out.println(row);
	}
}
