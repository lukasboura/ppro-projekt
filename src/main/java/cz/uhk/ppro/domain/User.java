package cz.uhk.ppro.domain;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.engine.internal.Cascade;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import cz.uhk.ppro.dao.AktivitaDAO;
import cz.uhk.ppro.dao.PlanDAO;
import cz.uhk.ppro.service.HibernateUtil;


/**
 * User domain
 */


@Entity
@Table( name = "user")
public class User {

	@Id
	@GeneratedValue
	private int id;
	@NotEmpty
	@Pattern(regexp="[a-zA-Zá-žÁ-Ž]+",message="Jméno musí obsahovat pouze písmena", flags={Flag.CASE_INSENSITIVE})
	@Length(min=1,max=20,message="Jméno musí mít 1 až 20 znaků")
	private String firstName;
	@NotEmpty
	@Pattern(regexp="[a-zA-Zá-žÁ-Ž]+",message="Příjmení musí obsahovat pouze písmena", flags={Flag.CASE_INSENSITIVE})
	@Length(min=1,max=20,message="Příjmení musí mít 1 až 20 znaků")
	private String lastName;
	@Column(unique=true)
	@NotEmpty
	@Length(min=5,max=10,message="Username musí mít 5 až 10 znaků")
	private String username;
	@NotEmpty
	@Length(min=5,message="Heslo musí alespoň 5 znaků") 
	private String password;
	@NotEmpty
	private String confirmpassword;
	@NotEmpty
	@Email
	private String email;
	
	private String bio;
	@Min(value=0,message="Záporná hodnota") 
	@Digits(integer=500, fraction=1, message="Hodnota musí být číslo s nejvíce jedním desetiným číslem")
	private double hmotnost;
	@Min(value=0,message="Záporná hodnota") 
	private int vek;
	
	@Lob
	@Column(columnDefinition="mediumblob")
	private byte[] photo;
	
	@OneToMany(mappedBy="user")
	private Collection<Aktivita> aktivity;
	@OneToMany(mappedBy="user")
	private Collection<Komentar> komentare;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="pritel",
	 joinColumns=@JoinColumn(name="user_id"),
	 inverseJoinColumns=@JoinColumn(name="pritel_id")
	)
	private Collection<User> pratele;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="pritel",
	 joinColumns=@JoinColumn(name="pritel_id"),
	 inverseJoinColumns=@JoinColumn(name="user_id")
	)
	private Collection<User> pritelem;
	@OneToMany(mappedBy="user")
	private Collection<PolozkaTreninkovehoPlanu> treninkovyplan;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String firstName, String lastName, String username,
			String password, String confirmpassword, String email, String bio,
			double hmotnost, int vek,byte[] photo, Collection<Aktivita> aktivity,Collection<User> pratele,Collection<User> pritelem,Collection<PolozkaTreninkovehoPlanu> treninkovyplan) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.confirmpassword = confirmpassword;
		this.email = email;
		this.bio = bio;
		this.hmotnost = hmotnost;
		this.vek = vek;
		this.photo=photo;
		this.aktivity = aktivity;
		this.pratele = pratele;
		this.pritelem = pritelem;
		this.treninkovyplan = treninkovyplan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public double getHmotnost() {
		return hmotnost;
	}

	public void setHmotnost(double hmotnost) {
		this.hmotnost = hmotnost;
	}

	public int getVek() {
		return vek;
	}

	public void setVek(int vek) {
		this.vek = vek;
	}
	
	public Collection<Aktivita> getAktivity() {
		
		
		
		AktivitaDAO dao = new AktivitaDAO();
		Collection<Aktivita> aktivity = dao.getAllAktivitById(id);
		 
		return aktivity;
	}

	public void setAktivity(Collection<Aktivita> aktivity) {
		this.aktivity = aktivity;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public Collection<User> getPratele() {
		return pratele;
		
	}
	public void setPratele(Collection<User> pratele) {
		this.pratele = pratele;
	}
	public Collection<User> getPritelem() {
		return pritelem;
	}
	public void setPritelem(Collection<User> pritelem) {
		this.pritelem = pritelem;
	}
	public Collection<PolozkaTreninkovehoPlanu> getTreninkovyplan() {
		
		PlanDAO dao = new PlanDAO();
		
		this.treninkovyplan = dao.getAllPolozkyByUserId(id);
		
		return treninkovyplan;
	}
	public void setTreninkovyplan(
			Collection<PolozkaTreninkovehoPlanu> treninkovyplan) {
		this.treninkovyplan = treninkovyplan;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
