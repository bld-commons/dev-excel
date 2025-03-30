package bld.report.read.junit.entity;

import java.util.Date;

import com.bld.common.spreadsheet.csv.annotation.CsvDate;
import com.bld.common.spreadsheet.csv.annotation.CsvSettings;
import com.bld.read.report.excel.annotation.ExcelReadColumn;
import com.bld.read.report.excel.domain.RowSheetRead;


@CsvSettings(delimiter = ';',skipHeaderRecord = true)
public class UserCsvRow implements RowSheetRead {

	@ExcelReadColumn(value="name")
	private String name;
	
	@ExcelReadColumn(value="email")
	private String email;
	
	@ExcelReadColumn(value="phone")
	private String phone;	
	
	@ExcelReadColumn(value="country")
	private String country;	
	
	@ExcelReadColumn(value="birth date")
	@CsvDate(separator = "-")
	private Date date;
	
	@ExcelReadColumn(value="id")
	private Integer id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserCsvRow [name=" + name + ", email=" + email + ", phone=" + phone + ", country=" + country + ", date=" + date + ", id=" + id + "]";
	}

	
}
