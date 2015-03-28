package module;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import libs.CharsCoding;

@Entity
@Table(name="course_info")
public class CourseInfo extends CharsCoding implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 50514364L;

	public CourseInfo() {}
	
	@Id
	@Column(name="id", nullable=false, length=10)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="course_id", nullable=false, length=10)
	private Integer course;
	
	@Column(name="group_name", nullable=false, length=100)
	private String group_name;
	
	@Column(name="credits", nullable=false, length=10)
	private Integer credits;
	
	@Column(name="auditor_hours", nullable=false, length=10)
	private Integer auditor_hours;
	
	@Column(name="lectures_hours", nullable=false, length=10)
	private Integer lectures_hours;
	
	@Column(name="labs_hours", nullable=false, length=10)
	private Integer labs_hours;
	
	@Column(name="practics_hours", nullable=false, length=10)
	private Integer practics_hours;
	
	@Column(name="seminar_hours", nullable=false, length=10)
	private Integer seminar_hours;
	
	@Column(name="consultation_hours", nullable=false, length=10)
	private Integer consultation_hours;
	
	@Column(name="control_hours", nullable=false, length=10)
	private Integer control_hours;
	
	@Column(name="path_of_stady", nullable=false, length=10)
	private Integer path_of_stady;
	
	@Column(name="date_created", nullable=false, length=50)
	private String created;
	
	@Column(name="date_changed", nullable=false, length=50)
	private String change;
	
	@Column(name="coursovy", nullable=false, length=2)
	private Integer coursovy;

	public Integer getCourse() {
		return course;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCourse(Integer course) {
		this.course = course;
	}

	public String getGroup_name() {
		return this.decoding(group_name);
	}

	public void setGroup_name(String group_name) {
		this.group_name = this.encoding(group_name);
	}

	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	public Integer getAuditor_hours() {
		return auditor_hours;
	}

	public void setAuditor_hours(Integer auditor_hours) {
		this.auditor_hours = auditor_hours;
	}

	public Integer getLectures_hours() {
		return lectures_hours;
	}

	public void setLectures_hours(Integer lectures_hours) {
		this.lectures_hours = lectures_hours;
	}
	
	public Integer getFullLabsHours() {
		//-------------------------
		return labs_hours;
	}

	public Integer getLabs_hours() {
		return labs_hours;
	}

	public void setLabs_hours(Integer labs_hours) {
		this.labs_hours = labs_hours;
	}

	public Integer getPractics_hours() {
		return practics_hours;
	}

	public void setPractics_hours(Integer practics_hours) {
		this.practics_hours = practics_hours;
	}

	public Integer getSeminar_hours() {
		return seminar_hours;
	}

	public void setSeminar_hours(Integer seminar_hours) {
		this.seminar_hours = seminar_hours;
	}

	public Integer getConsultation_hours() {
		return consultation_hours;
	}

	public void setConsultation_hours(Integer consultation_hours) {
		this.consultation_hours = consultation_hours;
	}

	public Integer getControl_hours() {
		return control_hours;
	}

	public void setControl_hours(Integer control_hours) {
		this.control_hours = control_hours;
	}

	public Integer getPath_of_stady() {
		return path_of_stady;
	}

	public void setPath_of_stady(Integer path_of_stady) {
		this.path_of_stady = path_of_stady;
	}

	
	public Integer getId() {
		return id;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public void setChange(String change) {
		this.change = change;
	}
	
	public Integer getCoursovy() {
		return coursovy;
	}

	public void setCoursovy(Integer coursovy) {
		this.coursovy = coursovy;
	}
	
	public static CourseInfo plus(CourseInfo start, CourseInfo pCourseInfo){
		start.setId(pCourseInfo.getId());
		start.setCourse(pCourseInfo.getCourse());
		start.setGroup_name(pCourseInfo.getGroup_name());
		start.setPath_of_stady(pCourseInfo.getPath_of_stady());
		
		start.setAuditor_hours(pCourseInfo.getAuditor_hours() + start.getAuditor_hours());
		start.setConsultation_hours(pCourseInfo.getConsultation_hours() + start.getConsultation_hours());
		start.setControl_hours(pCourseInfo.getControl_hours() + start.getControl_hours());
		start.setCredits(pCourseInfo.getCredits() + start.getCredits());
		start.setLabs_hours(pCourseInfo.getLabs_hours() + start.getLabs_hours());
		start.setLectures_hours(pCourseInfo.getLectures_hours() + start.getLectures_hours());
		start.setPractics_hours(pCourseInfo.getPractics_hours() + start.getPractics_hours());
		start.setSeminar_hours(pCourseInfo.getSeminar_hours() + start.getSeminar_hours());
		start.setCoursovy(pCourseInfo.getCoursovy() + start.getCoursovy());
		return start;
	}
	
	public static CourseInfo plus(CourseInfo start, List<CourseInfo> pCourseInfo_list){
		int count = pCourseInfo_list.size();
		for(int i = 0; i < count; ++i) {
			CourseInfo pCourseInfo = pCourseInfo_list.get(i);
			
			start.setId(pCourseInfo.getId());
			start.setCourse(pCourseInfo.getCourse());
			start.setGroup_name(pCourseInfo.getGroup_name());
			start.setPath_of_stady(pCourseInfo.getPath_of_stady());
			start.setAuditor_hours(pCourseInfo.getAuditor_hours() + (start.getAuditor_hours() == null ? 0 : start.getAuditor_hours()));
			start.setConsultation_hours(pCourseInfo.getConsultation_hours() + (start.getConsultation_hours() == null ? 0 : start.getConsultation_hours() ));
			start.setControl_hours(pCourseInfo.getControl_hours() + (start.getControl_hours() == null ? 0 : start.getControl_hours() ));
			start.setCredits(pCourseInfo.getCredits() + (start.getCredits() == null ? 0 : start.getCredits()));
			start.setLabs_hours(pCourseInfo.getLabs_hours() + (start.getLabs_hours() == null ? 0 : start.getLabs_hours() ));
			start.setLectures_hours(pCourseInfo.getLectures_hours() + (start.getLectures_hours() == null ? 0 : start.getLectures_hours() ));
			start.setPractics_hours(pCourseInfo.getPractics_hours() + (start.getPractics_hours() == null ? 0 : start.getPractics_hours() ));
			start.setSeminar_hours(pCourseInfo.getSeminar_hours() + (start.getSeminar_hours() == null ? 0 : start.getSeminar_hours() ));
			start.setCoursovy(pCourseInfo.getCoursovy() + (start.getCoursovy() == null ? 0 : start.getCoursovy() ));
		}
		return start;
	}
	

}
