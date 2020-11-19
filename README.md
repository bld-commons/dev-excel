# dev-excel
In this project 2 libraries were created in order to speed up and make easier the generation and reading of excel files through the library apache-poi.
The 2 libraries are:

  1)  generator-excel
  2)  read-excel
 
 
The main classes to start developing the generator-excel library are:

  1)  ReportExcel is the entity from which the excel file will be generated.
  2)  BaseSheet is the class that represents the Excel sheet, it is extended from:
        - SheetData
        - SheetSummary
        - MergeSheet
  3)  RowSheet is the entity that reprents the row  of the sheet
  4)  In the package "bld.generator.report.excel.annotation" there are the annotations to configure the sheet layout, the most important annotations are:
        - ExcelHeaderLayout
        - ExcelSheetLayout
        - ExcelColumn
        - ExcelCellLayout
        
It generates sheets with the following functionalities:
  1)  Functions(sum by column and row).
  2)  Charts.
  3)  It merges cells between rows when they are equals if the option "notMerge" is disabled in the annotation "ExcelSheetLayout".
  4)  To get rows list through a query by the annotation "ExcelQuery" and the "QuerySheetData" entity type.
        
For the complete documnetation of the generator-excel library, click on the following link:

  - https://javadoc.io/doc/com.github.bld-commons.excel/generator-excel/latest/index.html



The main classes to start developing the read-excel library are:
  1)  ExcelRead is the object that represents the excel file.
  2)  SheetRead is the object that represents the excel sheet.
  3)  RowSheetRead is impletened for creating an object that represents the rows of the sheet.
  4)  In the package "bld.read.report.excel.annotation" there are the annotations to map the entities with the sheets an columns of the excel file.

For the complete documnetation of the read-excel library, click on the following link:

  - https://javadoc.io/doc/com.github.bld-commons.excel/read-excel/latest/index.html
  
  
   Below is a summary table with the features of the functions for generating
 excel files.<br>
  
  <table style="width:50%; border: 1px solid #666;">
  <tr>
  <th style="width: 23%; border: 1px solid #666; text-align: center;"></th>
  <th style="width: 23%; border: 1px solid #666; text-align: center;">HSSF</th>
  <th style="width: 23%; border: 1px solid #666; text-align: center;">XSSF</th>
  <th style="width: 23%; border: 1px solid #666; text-align: center;">SXSSF</th>
  </tr>
  <tr>
  <td style="width: 23%; border: 1px solid #666;">Function</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">createFileXls</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">createFileXlsx</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">createBigDataFileXlsx</td>
  </tr>
  <tr>
  <td style="width: 23%; border: 1px solid #666;">CPU and memory efficiency</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Varies</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">varies</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Good</td>
  </tr>
  <tr>
  <td style="width: 23%; border: 1px solid #666;">Read Files</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">No</td>
  </tr>
  <tr>
  <td style="width: 23%; border: 1px solid #666;">Write Files</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  </tr>
  <tr>
  <td style="width: 23%; border: 1px solid #666;">Create sheets/rows/cells</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  </tr>
  <tr>
  <td style="width: 23%; border: 1px solid #666;">Delete sheets/rows/cells</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">No</td>
  </tr>
  <tr>
  <td style="width: 23%; border: 1px solid #666;">Styling cells</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  </tr>
  <tr>
  <td style="width: 23%; border: 1px solid #666;">Shift rows</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">No</td>
  </tr>
  <tr>
  <td style="width: 23%; border: 1px solid #666;">Cloning sheets</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">No</td>
  </tr>
  <tr>
  <td style="width: 23%; border: 1px solid #666;">Formula evaluation</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">No</td>
  </tr>
  <tr>
  <td style="width: 23%; border: 1px solid #666;">Cell comments</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">No</td>
  </tr>
  <tr>
  <td style="width: 23%; border: 1px solid #666;">Picture</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
  <td style="width: 23%; border: 1px solid #666;text-align: center;">No</td>
  </tr>
  </table>
  
