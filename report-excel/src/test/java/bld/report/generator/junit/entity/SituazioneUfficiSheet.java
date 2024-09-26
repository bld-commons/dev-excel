package bld.report.generator.junit.entity;

import jakarta.validation.constraints.Size;

import com.bld.generator.report.excel.SheetData;
import com.bld.generator.report.excel.annotation.ExcelHeaderLayout;
import com.bld.generator.report.excel.annotation.ExcelMarginSheet;
import com.bld.generator.report.excel.annotation.ExcelSheetLayout;

@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
public class SituazioneUfficiSheet extends SheetData<SituazioneUfficiRow> { //implements FunctionsTotal<TotaleSituazioneUfficiSheet>{

    //private TotaleSituazioneUfficiSheet sheetFunctionsTotal;

    public SituazioneUfficiSheet(@Size(max = 31) String sheetName) {
        super(sheetName);
        //this.sheetFunctionsTotal = new TotaleSituazioneUfficiSheet();
    }

    // @Override
    // public TotaleSituazioneUfficiSheet getSheetFunctionsTotal() {
    //     return sheetFunctionsTotal;
    // }

    // @Override
    // public void setSheetFunctionsTotal(TotaleSituazioneUfficiSheet sheetFunctionsTotal) {
    //     this.sheetFunctionsTotal = sheetFunctionsTotal;
    // }
}