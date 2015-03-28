package module;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import libs.CharsCoding;
import libs.XMLSettings;

@Entity
@Table(name="dyplom")
public class Dyplom extends CharsCoding implements Serializable {

	private static final long serialVersionUID = 1782448911625141403L;
	private static double koef_d_4_hours = 9.25;
	private static double koef_v_4_hours = 19.5;
	private static double koef_d_5_spec_hours_job = 20.5;
	private static double koef_d_5_spec_hours_proect = 19;
	private static double koef_d_5_mag_hours_me = 21.1;
	private static double koef_d_5_mag_hours_mv = 29.5;
	private static XMLSettings settingsRead;
	
	public Dyplom() {
		try {
			settingsRead = new XMLSettings();
			koef_d_4_hours = settingsRead.getVariableDouble("dyplom_d_4_hours");
			koef_v_4_hours = settingsRead.getVariableDouble("dyplom_v_4_hours");
			koef_d_5_spec_hours_job = settingsRead.getVariableDouble("dyplom_d_5_spec_hours_job");
			koef_d_5_spec_hours_proect = settingsRead.getVariableDouble("dyplom_d_5_spec_hours_proect");
			koef_d_5_mag_hours_me = settingsRead.getVariableDouble("dyplom_d_5_mag_hours_me");
			koef_d_5_mag_hours_mv = settingsRead.getVariableDouble("dyplom_d_5_mag_hours_mv");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Id
	@Column(name="id", nullable=false, length=10)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="hours_d_4_me", nullable=false, length=10)
	private double hours_d_4_me = 0;
	
	@Column(name="hours_d_4_mv", nullable=false, length=10)
	private double hours_d_4_mv = 0;
	
	@Column(name="hours_d_5_sp_job_me", nullable=false, length=10)
	private double hours_d_5_sp_job_me = 0;
	
	@Column(name="hours_d_5_sp_job_mv", nullable=false, length=10)
	private double hours_d_5_sp_job_mv = 0;
	
	@Column(name="hours_d_5_sp_proect_me", nullable=false, length=10)
	private double hours_d_5_sp_proect_me = 0;
	
	@Column(name="hours_d_5_sp_proect_mv", nullable=false, length=10)
	private double hours_d_5_sp_proect_mv = 0;
	
	@Column(name="hours_d_5_mag_me", nullable=false, length=10)
	private double hours_d_5_mag_me = 0;
	
	@Column(name="hours_d_5_mag_mv", nullable=false, length=10)
	private double hours_d_5_mag_mv = 0;
	
	@Column(name="hours_v_4_me", nullable=false, length=10)
	private double hours_v_4_me = 0;
	
	@Column(name="hours_v_4_mv", nullable=false, length=10)
	private double hours_v_4_mv = 0;
	
	@Column(name="hours_full", nullable=false, length=10)
	private double hours_full = 0;
	
	@Column(name="year", nullable=false, length=10, unique=true)
	private Integer year = 0;
	
	@Column(name="date_created", nullable=false, length=50)
	private String created;
	
	@Column(name="date_changed", nullable=false, length=50)
	private String change;
	
	
	public double getHours_d_4_me() {
		return hours_d_4_me / koef_d_4_hours;
	}

	public void setHours_d_4_me(double hours_d_4_me) {
		this.hours_d_4_me = hours_d_4_me * koef_d_4_hours;
	}

	public double getHours_d_4_mv() {
		return hours_d_4_mv / koef_d_4_hours;
	}

	public void setHours_d_4_mv(double hours_d_4_mv) {
		this.hours_d_4_mv = hours_d_4_mv * koef_d_4_hours;
	}

	public double getHours_d_5_sp_job_me() {
		return hours_d_5_sp_job_me / koef_d_5_spec_hours_job ;
	}

	public void setHours_d_5_sp_job_me(double hours_d_5_sp_job_me) {
		this.hours_d_5_sp_job_me = hours_d_5_sp_job_me * koef_d_5_spec_hours_job;
	}

	public double getHours_d_5_sp_job_mv() {
		return hours_d_5_sp_job_mv / koef_d_5_spec_hours_job;
	}

	public void setHours_d_5_sp_job_mv(double hours_d_5_sp_job_mv) {
		this.hours_d_5_sp_job_mv = hours_d_5_sp_job_mv * koef_d_5_spec_hours_job;
	}

	public double getHours_d_5_sp_proect_me() {
		return hours_d_5_sp_proect_me / koef_d_5_spec_hours_proect;
	}

	public void setHours_d_5_sp_proect_me(double hours_d_5_sp_proect_me) {
		this.hours_d_5_sp_proect_me = hours_d_5_sp_proect_me * koef_d_5_spec_hours_proect;
	}

	public double getHours_d_5_sp_proect_mv() {
		return hours_d_5_sp_proect_mv / koef_d_5_spec_hours_proect;
	}

	public void setHours_d_5_sp_proect_mv(double hours_d_5_sp_proect_mv) {
		this.hours_d_5_sp_proect_mv = hours_d_5_sp_proect_mv * koef_d_5_spec_hours_proect;
	}

	public double getHours_d_5_mag_me() {
		return hours_d_5_mag_me / koef_d_5_mag_hours_me;
	}

	public void setHours_d_5_mag_me(double hours_d_5_mag_me) {
		this.hours_d_5_mag_me = hours_d_5_mag_me * koef_d_5_mag_hours_me;
	}

	public double getHours_d_5_mag_mv() {
		return hours_d_5_mag_mv / koef_d_5_mag_hours_mv;
	}

	public void setHours_d_5_mag_mv(double hours_d_5_mag_mv) {
		this.hours_d_5_mag_mv = hours_d_5_mag_mv * koef_d_5_mag_hours_mv;
	}

	public double getHours_v_4_me() {
		return hours_v_4_me / koef_v_4_hours;
	}

	public void setHours_v_4_me(double hours_v_4_me) {
		this.hours_v_4_me = hours_v_4_me * koef_v_4_hours;
	}

	public double getHours_v_4_mv() {
		return hours_v_4_mv;
	}

	public void setHours_v_4_mv(double hours_v_4_mv) {
		this.hours_v_4_mv = hours_v_4_mv;
	}

	public double getHours_full() {
		return hours_full;
	}

	public void setHours_full() {
		this.hours_full = 
				this.hours_d_4_me + this.hours_d_4_mv +
				this.hours_v_4_me + this.hours_v_4_mv +
				this.hours_d_5_mag_me + this.hours_d_5_mag_mv +
				this.hours_d_5_sp_job_me + this.hours_d_5_sp_job_mv +
				this.hours_d_5_sp_proect_me + this.hours_d_5_sp_proect_mv;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public Integer getId() {
		return id;
	}
	
}
