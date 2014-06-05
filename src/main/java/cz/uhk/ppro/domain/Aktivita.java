package cz.uhk.ppro.domain;


import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import cz.uhk.ppro.dao.FotogalerieDAO;
import cz.uhk.ppro.dao.KomentarDAO;

@Entity
@Table(name="aktivita")
public class Aktivita {

	@Id
	@GeneratedValue
	private int id;
	@DateTimeFormat(iso=DateTimeFormat.ISO.NONE)
	@Past(message="Nesprávné datum")
	private Date datum;
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date dobaTrvani;
	private String text;
	@NotEmpty
	private String typ;
	@NotNull
	private double kalorie;
	@NotNull
	private double rychlost;
	@NotNull
	@Min(value=0,message="Záporná hodnota") 
	@Digits(integer=1000000000, fraction=2, message="Hodnota musí být číslo s nejvíce dvěmi desetinými čísli")
	private double vzdalenost;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@OneToMany(mappedBy="aktivita")
	@Cascade(org.hibernate.annotations.CascadeType.DELETE)
	private Collection<Komentar> komentare;
	@OneToMany(mappedBy="aktivita")
	@Cascade(org.hibernate.annotations.CascadeType.DELETE)
	private Collection<Foto> fotogalerie;
	
	public Aktivita() {
		// TODO Auto-generated constructor stub
	}

	public Aktivita(Date datum, Date dobaTrvani,
			String text, String typ, double vzdalenost,double kalorie,double rychlost,Collection<Komentar> komentare,User user,Collection<Foto> fotogalerie) {
		super();
		this.datum = datum;
		this.dobaTrvani = dobaTrvani;
		this.text = text;
		this.typ = typ;
		this.vzdalenost = vzdalenost;
		this.kalorie = kalorie;
		this.rychlost = rychlost;
		this.komentare = komentare;
		this.user = user;
		this.fotogalerie = fotogalerie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Date getDobaTrvani() {
		return dobaTrvani;
	}

	public void setDobaTrvani(Date dobaTrvani) {
		this.dobaTrvani = dobaTrvani;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public double getVzdalenost() {
		return vzdalenost;
	}

	public void setVzdalenost(double vzdalenost) {
		this.vzdalenost = vzdalenost;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getKalorie() {
		return kalorie;
	}
	public void setKalorie(double kalorie) {
		this.kalorie = kalorie;
	}
	public double getRychlost() {
		return rychlost;
	}
	public void setRychlost(double rychlost) {
		this.rychlost = rychlost;
	}
	public Collection<Komentar> getKomentare() {
		/*Hibernate.initialize(komentare);*/
		
		KomentarDAO dao = new KomentarDAO();
		this.komentare = dao.getAll(id);
		
		return komentare;
	}
	public void setKomentare(Collection<Komentar> komentare) {
		this.komentare = komentare;
	}
	public Collection<Foto> getFotogalerie() {
		FotogalerieDAO dao = new FotogalerieDAO();
		this.fotogalerie = dao.getAll(id);
		return fotogalerie;
	}
	public void setFotogalerie(Collection<Foto> fotogalerie) {
		this.fotogalerie = fotogalerie;
	}
}
