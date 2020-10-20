package bld.generator.report.db1.persistence.domain;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "utente")
public class Utente {
    @Column(name = "nome")
    @NotNull
    private String nome;
    @Column(name = "cognome")
    @NotNull
    private String cognome;
    @Column(name = "data_nascita")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date dataNascita;
    @Column(name = "image")
    private byte[] image;
    @Column(name = "path")
    private String path;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public Date getDataNascita() {
        return dataNascita;
    }
    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="SEQ_utente") @SequenceGenerator(name="SEQ_utente", sequenceName="SEQ_utente", allocationSize=1)
    @Column(name = "id_utente")
    private Integer idUtente;
    public Integer getIdUtente() {
        return this.idUtente;
    }
    public void setIdUtente(Integer id) {
        this.idUtente = id;
    }
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
    
}
