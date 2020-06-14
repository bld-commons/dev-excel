# dev-excel
In this project there are 2 libraries used to speed up and easy the generation and reading of excel files through the library apache-poi and are:

  1)  generator-excel
  2)  read-excel
 
 
The main classes to start developing the generator-excel library are:

  1)  ReportExcel is the entity from which the excel file will be generated.
  2)  BaseSheet is the class that represents the Excel sheet, it is extended from:
        - SheetData
        - SheetSummary
        - MergeSheet
  3)  RowSheet is the entity that reprents the row  of the sheet
  4)  On the package "bld.generator.report.excel.annotation" there are the annotations to configure the sheet layout, the most important annotations are:
        - ExcelHeaderLayout
        - ExcelSheetLayout
        - ExcelColumn
        - ExcelCellLayout
        
It generates sheet whit:
  1)  Functions(sum by column and row).
  2)  Charts.
  3)  It merge cells between rows when they are equals.
  4)  To get rows list through the annotation ExcelQuery and the QuerySheetData type entity.
        
For all the documnetation of the generator-excel library, to click on the following link:

  - https://javadoc.io/doc/com.github.bld-commons.excel/generator-excel/latest/index.html



The main classes to start developing the read-excel library are:
  1)  ExcelRead is the object that represents the excel file.
  2)  SheetRead is the object that represents the excel sheet.
  3)  RowSheetRead is used to create an object that represents the row of the sheet.
  4)  On the package "bld.read.report.excel.annotation" there are the annotations to map the entities with the sheets an columns of the excel file

For all the documnetation of the read-excel library, to click on the following link:

  - https://javadoc.io/doc/com.github.bld-commons.excel/read-excel/latest/index.html
