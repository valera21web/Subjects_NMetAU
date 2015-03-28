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
@Table(name="practice")
public class Practice extends CharsCoding implements Serializable {

	private static final long serialVersionUID = 1782148919625141401L;
	private static int koef_1_hoursOthersGroups = 2;
	private static int koef_3_hoursForGroups = 120;
	private static int koef_4_hoursForGroups = 2;
	private static int koef_5_hoursForGroupsSpecialist = 4;
	private static int koef_5_hoursForGroupsMVMagistr = 4;
	private static int koef_5_hoursForGroupsMEMagistr = 2;
	private static XMLSettings settingsRead;
	public Practice() {
		try {
			settingsRead = new XMLSettings();
			koef_1_hoursOthersGroups = settingsRead.getVariableInt("practice_1_hoursOthersGroups");
			koef_3_hoursForGroups = settingsRead.getVariableInt("practice_3_hoursForGroups");
			koef_4_hoursForGroups = settingsRead.getVariableInt("practice_4_hoursForGroups");
			koef_5_hoursForGroupsSpecialist = settingsRead.getVariableInt("practice_5_hoursForGroupsSpecialist");
			koef_5_hoursForGroupsMVMagistr = settingsRead.getVariableInt("practice_5_hoursForGroupsMVMagistr");
			koef_5_hoursForGroupsMEMagistr = settingsRead.getVariableInt("practice_5_hoursForGroupsMEMagistr");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Id
	@Column(name="id", nullable=false, length=10)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="hours_1_me", nullable=false, length=10)
	private Integer hours_1_me = 0;
	
	@Column(name="hours_1_mv", nullable=false, length=10)
	private Integer hours_1_mv = 0;
	
	@Column(name="hours_1_other", nullable=false, length=10)
	private Integer hours_1_other = 0;
	
	@Column(name="hours_3_me", nullable=false, length=10)
	private Integer hours_3_me = koef_3_hoursForGroups;
	
	@Column(name="hours_3_mv", nullable=false, length=10)
	private Integer hours_3_mv = koef_3_hoursForGroups;
	
	@Column(name="hours_4_me", nullable=false, length=10)
	private Integer hours_4_me = 0;
	
	@Column(name="hours_4_mv", nullable=false, length=10)
	private Integer hours_4_mv = 0;
	
	@Column(name="hours_5_me_s", nullable=false, length=10)
	private Integer hours_5_me_s = 0;
	
	@Column(name="hours_5_mv_s", nullable=false, length=10)
	private Integer hours_5_mv_s = 0;
	
	@Column(name="hours_5_me_m", nullable=false, length=10)
	private Integer hours_5_me_m = 0;
	
	@Column(name="hours_5_mv_m", nullable=false, length=10)
	private Integer hours_5_mv_m = 0;
	
	@Column(name="hours_full", nullable=false, length=10)
	private Integer hours_full = 0;
	
	@Column(name="year", nullable=false, length=10, unique=true)
	private Integer year = 0;
	
	@Column(name="date_created", nullable=false, length=50)
	private String created;
	
	@Column(name="date_changed", nullable=false, length=50)
	private String change;
	
	
	public Integer getHours_1_me() {
		return hours_1_me;
	}

	public void setHours_1_me(Integer hours_1_me) {
		this.hours_1_me = hours_1_me;
	}

	public Integer getHours_1_mv() {
		return hours_1_mv;
	}

	public void setHours_1_mv(Integer hours_1_mv) {
		this.hours_1_mv = hours_1_mv;
	}

	public Integer getHours_1_other() {
		return hours_1_other / koef_1_hoursOthersGroups;
	}

	public void setHours_1_other(Integer hours_1_other) {
		this.hours_1_other = hours_1_other * koef_1_hoursOthersGroups;
	}

	public Integer getHours_3_me() {
		return hours_3_me;
	}

	public void setHours_3_me(Integer hours_3_me) {
		this.hours_3_me = hours_3_me;
	}

	public Integer getHours_3_mv() {
		return hours_3_mv;
	}

	public void setHours_3_mv(Integer hours_3_mv) {
		this.hours_3_mv = hours_3_mv;
	}

	public Integer getHours_4_me() {
		return hours_4_me / koef_4_hoursForGroups;
	}

	public void setHours_4_me(Integer hours_4_me) {
		this.hours_4_me = hours_4_me *koef_4_hoursForGroups;
	}

	public Integer getHours_4_mv() {
		return hours_4_mv / koef_4_hoursForGroups;
	}

	public void setHours_4_mv(Integer hours_4_mv) {
		this.hours_4_mv = hours_4_mv * koef_4_hoursForGroups;
	}

	public Integer getHours_5_me_s() {
		return hours_5_me_s / koef_5_hoursForGroupsSpecialist;
	}

	public void setHours_5_me_s(Integer hours_5_me_s) {
		this.hours_5_me_s = hours_5_me_s * koef_5_hoursForGroupsSpecialist;
	}

	public Integer getHours_5_mv_s() {
		return hours_5_mv_s / koef_5_hoursForGroupsSpecialist;
	}

	public void setHours_5_mv_s(Integer hours_5_mv_s) {
		this.hours_5_mv_s = hours_5_mv_s * koef_5_hoursForGroupsSpecialist;
	}

	public Integer getHours_5_me_m() {
		return hours_5_me_m / koef_5_hoursForGroupsMEMagistr;
	}

	public void setHours_5_me_m(Integer hours_5_me_m) {
		this.hours_5_me_m = hours_5_me_m * koef_5_hoursForGroupsMEMagistr;
	}

	public Integer getHours_5_mv_m() {
		return hours_5_mv_m / koef_5_hoursForGroupsMVMagistr;
	}

	public void setHours_5_mv_m(Integer hours_5_mv_m) {
		this.hours_5_mv_m = hours_5_mv_m * koef_5_hoursForGroupsMVMagistr;
	}

	public Integer getHours_full() {
		return hours_full;
	}

	public void setHours_full() {
		this.hours_full = 
				this.hours_1_me + this.hours_1_mv + this.hours_1_other
				+ this.hours_3_me + this.hours_3_mv 
				+ this.hours_4_me + this.hours_4_mv
				+ this.hours_5_me_m + this.hours_5_me_s
				+ this.hours_5_mv_m + this.hours_5_mv_s;
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
