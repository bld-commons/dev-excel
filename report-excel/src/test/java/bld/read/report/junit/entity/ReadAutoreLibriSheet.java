package bld.read.report.junit.entity;

import bld.read.report.excel.annotation.ExcelReadSheet;
import bld.read.report.excel.domain.SheetRead;

@ExcelReadSheet(nameSheet = "Libri d'autore",startRow = 2)
public class ReadAutoreLibriSheet extends SheetRead<ReadAutoreLibriRow>{

	
}
