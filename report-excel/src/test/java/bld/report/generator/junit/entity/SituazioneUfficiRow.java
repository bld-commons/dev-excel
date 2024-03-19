package bld.report.generator.junit.entity;

import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelFont;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelFunctionRow;
import bld.generator.report.excel.annotation.ExcelFunctionRows;
import bld.generator.report.excel.annotation.ExcelFunctionSubTotal;
import bld.generator.report.excel.annotation.ExcelNumberFormat;
import bld.generator.report.excel.annotation.ExcelSubtotal;
import bld.generator.report.excel.annotation.ExcelSubtotals;

@ExcelFunctionRows(
		excelFunctions = @ExcelFunctionRow(
		excelColumn = @ExcelColumn(index = 3, name = "% Scop. Giuridica"), 
		excelFunction = @ExcelFunction(function = "${presenzaGiuridica}/${organico}", nameFunction = "scoperturaGiuridica",
										onSubTotalRow = @ExcelFunctionSubTotal(value=true,
												excelCellLayout=@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,font = @ExcelFont(bold = true))
										)
		
		),excelNumberFormat = @ExcelNumberFormat("0.00%")
		
				))
@ExcelSubtotals(sumForGroup = { "tipoContratto", "comparto", "area" }, labelTotalGroup = "Totale Complessivo")
public class SituazioneUfficiRow implements RowSheet {

	@ExcelColumn(index = 0.3, name = "Area")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.LEFT)
	private String area;

	@ExcelColumn(index = 0.1, name = "Tipo Contratto")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.LEFT)
	private String tipoContratto;

	@ExcelColumn(index = 0.2, name = "Comparto")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.LEFT)
	private String comparto;

	@ExcelColumn(index = 0.5, name = "Profilo Professionale")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.LEFT)
	private String profiloProfessionale;

	@ExcelColumn(index = 1, name = "Organico")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	@ExcelSubtotal(dataConsolidateFunction = DataConsolidateFunction.SUM)
	private BigDecimal organico;

	@ExcelColumn(index = 2, name = "Presenza Giuridica")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	@ExcelSubtotal(dataConsolidateFunction = DataConsolidateFunction.SUM)
	private BigDecimal presenzaGiuridica;

	@ExcelColumn(index = 4, name = "Distacchi/Comandi In")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	@ExcelSubtotal(dataConsolidateFunction = DataConsolidateFunction.SUM)
	private BigDecimal distacchiComandiIn;

	@ExcelColumn(index = 4.5, name = "Distacchi/Comandi Out")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	@ExcelSubtotal(dataConsolidateFunction = DataConsolidateFunction.SUM)
	private BigDecimal distacchiComandiOut;

	@ExcelColumn(index = 5, name = "Presenza Effettiva")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	@ExcelSubtotal(dataConsolidateFunction = DataConsolidateFunction.SUM)
	private BigDecimal presenzaEffettiva;

	@ExcelColumn(index = 6, name = "% Scop. Effettiva")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	@ExcelSubtotal(dataConsolidateFunction = DataConsolidateFunction.SUM)
	private BigDecimal scoperturaEffettiva;

	public SituazioneUfficiRow(String tipoContratto, String comparto, String area, String profiloProfessionale, BigDecimal organico, BigDecimal presenzaGiuridica, BigDecimal distacchiComandiIn,
			BigDecimal distacchiComandiOut, BigDecimal presenzaEffettiva, BigDecimal scoperturaEffettiva) {
		this.tipoContratto = tipoContratto;
		this.comparto = comparto;
		this.area = area;
		this.profiloProfessionale = profiloProfessionale;
		this.organico = organico;
		this.presenzaGiuridica = presenzaGiuridica;
		this.distacchiComandiIn = distacchiComandiIn;
		this.distacchiComandiOut = distacchiComandiOut;
		this.presenzaEffettiva = presenzaEffettiva;
		this.scoperturaEffettiva = scoperturaEffettiva;
	}

	public SituazioneUfficiRow() {
	}

	public String getProfiloProfessionale() {
		return profiloProfessionale;
	}

	public void setProfiloProfessionale(String profiloProfessionale) {
		this.profiloProfessionale = profiloProfessionale;
	}

	public BigDecimal getOrganico() {
		return organico;
	}

	public void setOrganico(BigDecimal organico) {
		this.organico = organico;
	}

	public BigDecimal getPresenzaGiuridica() {
		return presenzaGiuridica;
	}

	public void setPresenzaGiuridica(BigDecimal presenzaGiuridica) {
		this.presenzaGiuridica = presenzaGiuridica;
	}



	public BigDecimal getDistacchiComandiIn() {
		return distacchiComandiIn;
	}

	public void setDistacchiComandiIn(BigDecimal distacchiComandiIn) {
		this.distacchiComandiIn = distacchiComandiIn;
	}

	public BigDecimal getPresenzaEffettiva() {
		return presenzaEffettiva;
	}

	public void setPresenzaEffettiva(BigDecimal presenzaEffettiva) {
		this.presenzaEffettiva = presenzaEffettiva;
	}

	public BigDecimal getScoperturaEffettiva() {
		return scoperturaEffettiva;
	}

	public void setScoperturaEffettiva(BigDecimal scoperturaEffettiva) {
		this.scoperturaEffettiva = scoperturaEffettiva;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public BigDecimal getDistacchiComandiOut() {
		return distacchiComandiOut;
	}

	public void setDistacchiComandiOut(BigDecimal distacchiComandiOut) {
		this.distacchiComandiOut = distacchiComandiOut;
	}

	public String getTipoContratto() {
		return tipoContratto;
	}

	public void setTipoContratto(String tipoContratto) {
		this.tipoContratto = tipoContratto;
	}

	public String getComparto() {
		return comparto;
	}

	public void setComparto(String comparto) {
		this.comparto = comparto;
	}

}