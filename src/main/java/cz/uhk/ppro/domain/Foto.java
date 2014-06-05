package cz.uhk.ppro.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "foto")
public class Foto {
	
	@Id
	@GeneratedValue
	private int id;
	@Lob
	@Column(columnDefinition="mediumblob")
	private byte[] fotka;
	
	private String text;
	
	@ManyToOne
	@JoinColumn(name="aktivita_id")
	private Aktivita aktivita;
	
	public Foto() {
		// TODO Auto-generated constructor stub
	}

	public Foto(byte[] fotka, String text, Aktivita aktivita) {
		super();
		this.fotka = fotka;
		this.text = text;
		this.aktivita = aktivita;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getFotka() {
		return fotka;
	}

	public void setFotka(byte[] fotka) {
		this.fotka = fotka;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Aktivita getAktivita() {
		return aktivita;
	}

	public void setAktivita(Aktivita aktivita) {
		this.aktivita = aktivita;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Foto other = (Foto) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
