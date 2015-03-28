package module;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import libs.CharsCoding;


@Entity
@Table(name="subject")
public class Subject extends CharsCoding implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Subject() {
		this.name = null;
		this.created = null;
		this.change = null;
	}
	
	public Subject(Integer pId, String pName, String pCreated) {
		this.name = this.encoding(pName);
		this.created = this.encoding(pCreated);
		this.change = this.encoding(pCreated);
	}
	
	
	@Id
	@Column(name="id", nullable=false, length=10)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name", nullable=false, length=250)
	private String name;

	@Column(name="date_created", nullable=false, length=50)
	private String created;
	
	@Column(name="date_changed", nullable=false, length=50)
	private String change; 
	
	public Integer getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = this.encoding(name);
	}
	
	public String getName() {
		return this.decoding(this.name);
	}
	
	public void setCreated(String created) {
		this.created = created;
	}

	public void setChange(String change) {
		this.change = change;
	}

}
