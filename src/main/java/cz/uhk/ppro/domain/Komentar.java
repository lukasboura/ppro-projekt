package cz.uhk.ppro.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.annotation.Order;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Komentar {

	@Id
	@GeneratedValue
	private int id;
	@NotEmpty
	private String text;
	@DateTimeFormat(iso=DateTimeFormat.ISO.NONE)
	private Date datum;
	@ManyToOne
	@JoinColumn(name="aktivita_id")
	private Aktivita aktivita;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	public Komentar() {
		// TODO Auto-generated constructor stub
	}
	public Komentar(String text, Aktivita aktivita, User user,Date datum) {
		super();
		this.text = text;
		this.aktivita = aktivita;
		this.user = user;
		this.datum = datum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
	
}
