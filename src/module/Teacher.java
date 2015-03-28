package module;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import libs.CharsCoding;
import module.Teacher;


@Entity
@Table(name="teachers")
public class Teacher extends CharsCoding  implements Serializable {

	private static final long serialVersionUID = 4242343L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="koef")
	private double koef;
	
	public Teacher() {
		name = null;
	}
	
	public double getKoef() {
		return koef;
	}

	public void setKoef(double koef) {
		this.koef = koef;
	}

	public Teacher(String pName) {
		name = this.encoding(pName);
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return this.decoding(name);
	}

	public void setName(String name) {
		this.name = this.encoding(name);
	}
}
