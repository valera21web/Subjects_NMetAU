package module;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import libs.Cript;
import module.Users;


@Entity
@Table(name="users")
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String keyForCript = "practice_for_nmetau";

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="name")
	private String name;

	@Column(name="password")
	private String password;
	
	@Column(name="date_last_login")
	private String date_last_login;
	
	@Column(name="level")
	private int level;
	
	public Users() {
		name = null;
		password = null;
	}
	
	public Users(String pName, String pPassword) {
		setName(pName);
		setPassword(pPassword);
		setLevel(1);
	}
	
	public Users(String pName, String pPassword, int pLevel) {
		setName(pName);
		setPassword(pPassword);
		setLevel(pLevel);
	}
	
	public String getDate_last_login() {
		return date_last_login;
	}

	public void setDate_last_login(String date_last_login) {
		this.date_last_login = date_last_login;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Cript.cache(password, keyForCript);
	}
	
	
	
	
}
