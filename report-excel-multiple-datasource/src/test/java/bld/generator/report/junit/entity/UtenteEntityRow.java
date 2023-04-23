package bld.generator.report.junit.entity;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;

public class UtenteEntityRow implements RowSheet {
	
	@ExcelColumn(name = "Id", index = 0)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private String id; 
	@ExcelColumn(name = "Nome", index = 2)
	@ExcelCellLayout
	private String firstName; 
	@ExcelColumn(name = "Cognome", index = 1)
	@ExcelCellLayout
	private String lastName;
	@ExcelColumn(name = "email", index = 3)
	@ExcelCellLayout
	private String email;


	public UtenteEntityRow(String id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public UtenteEntityRow() {
	}


	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}





	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	
	
	

}
