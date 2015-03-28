package module;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import libs.CharsCoding;
import libs.XMLSettings;

@Entity
@Table(name="course")
public class Course extends CharsCoding implements Serializable {

	private static final long serialVersionUID = 1782148919625141401L;
	private static XMLSettings settingsRead;
	
	public Course() {
		try {
			settingsRead = new XMLSettings();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Id
	@Column(name="id", nullable=false, length=10)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="subject_id", nullable=false, length=10)
	private Integer subject;
	
	@Column(name="teacher_id", nullable=false, length=10)
	private Integer teacher = 0;

	@Column(name="count_groups", nullable=false, length=10)
	private Integer count;
	
	@Column(name="count_users_in_groups", nullable=false, length=100)
	private String count_users_in_groups;

	@Column(name="groups", nullable=false, length=100)
	private String groups;
	
	@Column(name="full_hours", nullable=false, length=10)
	private Integer hours;
	
	@Column(name="year", nullable=false, length=10)
	private Integer year;
	
	@Column(name="form_of_training", nullable=false, length=2)
	private char form_of_training;

	@Column(name="form_of_training_key", nullable=false, length=10)
	private float form_of_training_key;
	
	@Column(name="stady_in_path_1", nullable=false, length=2)
	private Integer stady_in_path_1;
	
	@Column(name="stady_in_path_2", nullable=false, length=2)
	private Integer stady_in_path_2;
	
	@Column(name="stady_in_path_3", nullable=false, length=2)
	private Integer stady_in_path_3;
	
	@Column(name="stady_in_path_4", nullable=false, length=2)
	private Integer stady_in_path_4;

	@Column(name="date_created", nullable=false, length=50)
	private String created;
	
	@Column(name="date_changed", nullable=false, length=50)
	private String change;	
	
	public Integer getId() {
		return id;
	}


	public Integer getAllCountUsers() {
		int count_users = 0;
		String users = getCount_users_in_groups();
		if(users.indexOf(";") != -1) {
			String[] counts_users = users.split(";");
			for(String item : counts_users) {
				count_users += Integer.parseInt(item);
			}
		} else {
			count_users = Integer.parseInt(users);
		}
		return count_users;
	}
	
	public Integer getCountStream() {
		if(settingsRead != null) {
			int count_users = settingsRead.getVariableInt(XMLSettings.VARS_COUNT, "count_users_for_streem");
			int count_students = getAllCountUsers();	
			return count_students > count_users ? (int)((count_students + (count_users - count_students % count_users))/ count_users) : 1;
		} else {
			return 1;
		}
	}
	
	public Integer getLabsHour(int housOnGroup) {
		int count_users = 18;
		if(settingsRead != null) {
			 count_users = settingsRead.getVariableInt(XMLSettings.VARS_COUNT, "count_users_for_streem");
		}
		int labs_hours = 0;
		String[] count_students_list = getCount_users_in_groups().split(";");
		for(int u = 0; u < count_students_list.length ;++u) {
			labs_hours += (Integer.parseInt(count_students_list[u]) > count_users ? 2 : 1) * housOnGroup;
		}
		return labs_hours;
	}
	
	public Integer getYearGroup() {
		String[] groups = getGroups().split(";");
		int year = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
		int mount = Integer.parseInt((new SimpleDateFormat("MM")).format(new Date()));
		int year_group = 0;
		
		if(groups.length > 0) {
			try {
				int int_start = groups[0].indexOf("-");
				year_group = Integer.parseInt(
					groups[0].substring(int_start + 1, int_start + 3)
				);
			} catch(Exception e) {}
	
		}
		return (year - (2000 + year_group) + (mount >= 9 ? 1 : 0));
	}
	
	public String getCount_users_in_groups() {
		return count_users_in_groups;
	}

	public void setCount_users_in_groups(String count_users_in_groups) {
		this.count_users_in_groups = count_users_in_groups;
	}
	
	public Integer getSubject() {
		return subject;
	}

	public void setSubject(Integer subject) {
		this.subject = subject;
	}
	
	public Integer getTeacher() {
		return teacher;
	}

	public void setTeacher(Integer pTeacher) {
		this.teacher = pTeacher;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getGroups() {
		return this.decoding(groups);
	}

	public void setGroups(String groups) {
		this.groups = this.encoding(groups);
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	public char getForm_of_training() {
		return form_of_training;
	}

	public void setForm_of_training(char form_of_training) {
		this.form_of_training = form_of_training;
	}

	public float getForm_of_training_key() {
		return form_of_training_key;
	}

	public void setForm_of_training_key(float form_of_training_key) {
		this.form_of_training_key = form_of_training_key;
	}
	
	public Integer getStady_in_path_1() {
		return stady_in_path_1;
	}

	public void setStady_in_path_1(Integer stady_in_path_1) {
		this.stady_in_path_1 = stady_in_path_1;
	}

	public Integer getStady_in_path_2() {
		return stady_in_path_2;
	}

	public void setStady_in_path_2(Integer stady_in_path_2) {
		this.stady_in_path_2 = stady_in_path_2;
	}

	public Integer getStady_in_path_3() {
		return stady_in_path_3;
	}

	public void setStady_in_path_3(Integer stady_in_path_3) {
		this.stady_in_path_3 = stady_in_path_3;
	}

	public Integer getStady_in_path_4() {
		return stady_in_path_4;
	}

	public void setStady_in_path_4(Integer stady_in_path_4) {
		this.stady_in_path_4 = stady_in_path_4;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public void setChange(String change) {
		this.change = change;
	} 
	
	public String toString() {
		return "ID:"+this.id+"; "+"Subject:"+subject + "; Teacher:" +this.teacher + "; Year:" +this.year;
	}
	

}
