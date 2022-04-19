package bld.report.controller.input;

import bld.report.controller.entity.ReadAutoreLibriSheet;
import bld.report.controller.entity.ReadGenereSheet;

public class ReadSheetsModel {

	private ReadAutoreLibriSheet autoreLibri;

	private ReadGenereSheet genere;

	public ReadAutoreLibriSheet getAutoreLibri() {
		return autoreLibri;
	}

	public void setAutoreLibri(ReadAutoreLibriSheet autoreLibri) {
		this.autoreLibri = autoreLibri;
	}

	public ReadGenereSheet getGenere() {
		return genere;
	}

	public void setGenere(ReadGenereSheet genere) {
		this.genere = genere;
	}

}
