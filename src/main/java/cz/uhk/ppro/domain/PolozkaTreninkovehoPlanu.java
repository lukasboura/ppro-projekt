package cz.uhk.ppro.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class PolozkaTreninkovehoPlanu {

	@Id
	@GeneratedValue
	private int id;
	@NotEmpty
	private String den;
	@NotEmpty
	private String typAktivity;
	@NotEmpty
	private String ukol;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public PolozkaTreninkovehoPlanu() {
		// TODO Auto-generated constructor stub
	}
	
	public PolozkaTreninkovehoPlanu(String den, String typAktivity, String ukol,User user) {
		super();
		this.den = den;
		this.typAktivity = typAktivity;
		this.ukol = ukol;
		this.user = user;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDen() {
		return den;
	}
	public void setDen(String den) {
		this.den = den;
	}
	public String getTypAktivity() {
		return typAktivity;
	}
	public void setTypAktivity(String typAktivity) {
		this.typAktivity = typAktivity;
	}
	public String getUkol() {
		return ukol;
	}
	public void setUkol(String ukol) {
		this.ukol = ukol;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
