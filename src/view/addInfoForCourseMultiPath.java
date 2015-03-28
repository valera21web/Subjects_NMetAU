package view;

import init.Main;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import dao.CourseInfoDAO;
import libs.XMLSettings;
import module.Course;
import module.CourseInfo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JEditorPane;

import java.awt.Font;
import java.math.BigDecimal;

import javax.swing.JTable;
import javax.swing.JRadioButton;

public class addInfoForCourseMultiPath extends JPanel {

	private Main frame;
	private static final long serialVersionUID = 1L;
	private XMLSettings settingsRead;
	private Course course;
	private int active_path_for_stady = 0;
	private int index_path = 0;
	private int coursovy = 0;
	private Integer[] path_arr = null;
	private Integer[]  hours_by_path = null;
	
	private JTextField input_full_hours;
	private JTextField input_credits;
	private JTextField input_auditor_hours;
	private JCheckBox checkBox_credits;
	private JCheckBox checkBox_auditor_hours;
	
	
	private boolean visible_credits = false;
	private boolean visible_auditors_hours = false;
	private boolean visible_lectures = false;
	private boolean visible_labs = false;
	private boolean visible_practics = false;
	private boolean visible_seminars = false;
	private boolean visible_consultation = false;
	private boolean visible_control = false;
	
	private int full_hours;
	private String group_name;
	
	private JPanel panel;
	private JLabel lblNewLabel_2;
	private JTextField input_lectures;
	private JCheckBox checkBox_lectures;
	private JLabel label_3;
	private JTextField input_lab;
	private JTextField input_practics;
	private JLabel label_4;
	private JTextField input_seminar;
	private JLabel label_5;
	private JCheckBox checkBox_lab;
	private JCheckBox checkBox_pactics;
	private JCheckBox checkBox_seminar;
	private JLabel label_6;
	private JTextField input_control;
	private JCheckBox checkBox_control;
	private JLabel label_7;
	private JTextField input_consultation;
	private JCheckBox checkBox_consultation;
	private JButton btnSave;
	private JEditorPane error;
	private JLabel lblStady_path_1;
	private JTable table;
	private JLabel label_2;
	private JRadioButton radioCoursovy_none;
	private JRadioButton radioCoursovy_job;
	private JRadioButton radioCoursovy_project;

	public addInfoForCourseMultiPath(Main pFrame, Course pCourse, Integer[] pPath_arr, int pIndexPath,Integer[] pHoursByPath) {
		course = pCourse;
		frame = pFrame;
		index_path = pIndexPath;
		path_arr = pPath_arr;
		hours_by_path = pHoursByPath;
		
		try {
			settingsRead = new XMLSettings();
		} catch (Exception e) { 
			e.printStackTrace(); 
			JOptionPane.showConfirmDialog(null, "Проблема с файлом настроек с константами. \r\n[settings.xml]", "Ошибка", JOptionPane.PLAIN_MESSAGE);
		}
		
		initComponents();
		afterInit();
		createdEventes();
	}
	
	private void initComponents() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("\u0420\u0430\u0441\u0447\u0435\u0442 \u0434\u043E\u043F\u043E\u043B\u043D\u0438\u0442\u0435\u043B\u044C\u043D\u044B\u0445 \u0434\u0430\u043D\u043D\u044B\u0445");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_1 = new JLabel("\u0412\u0441\u0435\u0433\u043E \u0447\u0430\u0441\u043E\u0432: ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_full_hours = new JTextField();
		input_full_hours.setEnabled(false);
		input_full_hours.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u041A\u0440\u0435\u0434\u0438\u0442\u043E\u0432: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_credits = new JTextField();
		input_credits.setEnabled(false);
		input_credits.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u0410\u0443\u0434\u0438\u0442\u043E\u0440. \u0447\u0430\u0441\u043E\u0432:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_auditor_hours = new JTextField();
		input_auditor_hours.setEnabled(false);
		input_auditor_hours.setColumns(10);
		
		checkBox_credits = new JCheckBox("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C");
		
		checkBox_auditor_hours = new JCheckBox("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C");
		
		lblNewLabel_2 = new JLabel("\u041B\u0435\u043A\u0446\u0438\u0438:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_lectures = new JTextField();
		input_lectures.setEnabled(false);
		input_lectures.setColumns(10);
		
		checkBox_lectures = new JCheckBox("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C");
		
		label_3 = new JLabel("\u041B\u0430\u0431\u043E\u0440\u0430\u0442\u043E\u0440\u043D\u044B\u0435: ");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_lab = new JTextField();
		input_lab.setEnabled(false);
		input_lab.setColumns(10);
		
		input_practics = new JTextField();
		input_practics.setEnabled(false);
		input_practics.setColumns(10);
		
		label_4 = new JLabel("\u041F\u0440\u0430\u043A\u0442\u0438\u0447\u0435\u0441\u043A\u0438\u0435: ");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_seminar = new JTextField();
		input_seminar.setEnabled(false);
		input_seminar.setColumns(10);
		
		label_5 = new JLabel("\u0421\u0435\u043C\u0438\u043D\u0430\u0440: ");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		
		checkBox_lab = new JCheckBox("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C");
		
		checkBox_pactics = new JCheckBox("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C");
		
		checkBox_seminar = new JCheckBox("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C");
		
		label_6 = new JLabel("\u041A\u043E\u043D\u0442\u0440\u043E\u043B\u044C: ");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_control = new JTextField();
		input_control.setText("0");
		input_control.setEnabled(false);
		input_control.setColumns(10);
		
		checkBox_control = new JCheckBox("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C");
		
		label_7 = new JLabel("\u041A\u043E\u043D\u0441\u0443\u043B\u044C\u0442\u0430\u0446\u0438\u0438: ");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_consultation = new JTextField();
		input_consultation.setText("0");
		input_consultation.setEnabled(false);
		input_consultation.setColumns(10);
		
		checkBox_consultation = new JCheckBox("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C");
		
		btnSave = new JButton("Сохранить");
		
		error = new JEditorPane();
		
		lblStady_path_1 = new JLabel("Path");
		lblStady_path_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblStady_path_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		table = new JTable();
		
		label_2 = new JLabel("Курсовые: ");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		radioCoursovy_none = new JRadioButton("Нет");
		radioCoursovy_none.setSelected(true);
		
		radioCoursovy_job = new JRadioButton("Робота");
		
		radioCoursovy_project = new JRadioButton("Проэкт");
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 424, Short.MAX_VALUE)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addGap(114))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(input_seminar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(checkBox_seminar, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(input_practics, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(checkBox_pactics, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(370, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(input_control, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(checkBox_control, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(input_consultation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(checkBox_consultation, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(370, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(64)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(input_full_hours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(412, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(45)
					.addComponent(error, GroupLayout.PREFERRED_SIZE, 495, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(96, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(97)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(input_credits, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(checkBox_credits))
						.addComponent(lblStady_path_1, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(input_auditor_hours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(checkBox_auditor_hours, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(input_lectures, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(checkBox_lectures, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(input_lab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(checkBox_lab, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(362, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(228)
					.addComponent(label, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
					.addGap(220))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(radioCoursovy_project, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
							.addComponent(radioCoursovy_job, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addComponent(radioCoursovy_none, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(326, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(input_full_hours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(18)
									.addComponent(lblStady_path_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addGap(102)
									.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(55)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(67)
											.addComponent(checkBox_lectures)
											.addGap(18)
											.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
												.addComponent(input_lab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(checkBox_lab)))
										.addGroup(gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
												.addComponent(input_credits, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(checkBox_credits))
											.addGap(11)
											.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
												.addComponent(input_auditor_hours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(checkBox_auditor_hours))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
												.addComponent(input_lectures, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
							.addGap(73)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(checkBox_pactics)
									.addComponent(input_practics, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(2)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(input_seminar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(checkBox_seminar))))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(3)
									.addComponent(input_consultation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(2)
									.addComponent(checkBox_consultation)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(3)
									.addComponent(input_control, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(2)
									.addComponent(checkBox_control)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(radioCoursovy_none))
							.addGap(25)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(radioCoursovy_project)
								.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
							.addComponent(error, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(radioCoursovy_job)
							.addGap(121))))
		);
		panel.setLayout(gl_panel);
	}
	
	private void afterInit() {		
		active_path_for_stady = path_arr[index_path];
		lblStady_path_1.setText("Path " + active_path_for_stady);
		group_name = course.getGroups();
		
		full_hours = hours_by_path[index_path];
		int count_credits = 0;
		int auditor_hours = 0;	
		
		try {
			count_credits = full_hours / settingsRead.getVariableInt(XMLSettings.VARS_COUNT, "hours_on_kredit");
		} catch(Exception e) {
			JOptionPane.showConfirmDialog(null, "Проблемма с расчетом кредитов\r\nВозможно коефициент не целое число", "Ошибка", JOptionPane.PLAIN_MESSAGE);
		}
		
		try {
			auditor_hours = count_credits * settingsRead.getVariableInt(XMLSettings.VARS_COUNT, "hours_auditor_on_kredit");
		} catch(Exception e) {
			JOptionPane.showConfirmDialog(null, "Проблемма с расчетом аудиторных часов\r\nВозможно коефициент не целое число", "Ошибка", JOptionPane.PLAIN_MESSAGE);
		}

		input_full_hours.setText(Integer.toString(full_hours));
		input_credits.setText(Integer.toString(count_credits));
		input_auditor_hours.setText(Integer.toString(auditor_hours));
		
		input_lectures.setText(Integer.toString((int) (auditor_hours * 0.5)));
		input_lab.setText(Integer.toString((int)(auditor_hours * 0.25)));
		input_practics.setText(Integer.toString((int)(auditor_hours * 0.25)));
		input_seminar.setText("0");
		
		double key_consultation = course.getForm_of_training_key();
		
		input_consultation.setText(Integer.toString((int)(full_hours * key_consultation)));
		double control_hours = count_credits * course.getAllCountUsers() * 
				settingsRead.getVariableDouble("kontrol");
		BigDecimal control_hours_d = new BigDecimal(Double.toString(control_hours));
		control_hours_d = control_hours_d.setScale(0, BigDecimal.ROUND_HALF_UP);
		input_control.setText(Integer.toString(control_hours_d.intValue()));
	}
	
	private void createdEventes() {
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean valid_form = true;
				btnSave.setEnabled(false);
				int credits = 0;
				int auditor_hours = 0;
				int lectures_hours = 0;
				int labs_hours = 0;
				int practics_hours = 0;
				int seminar_hours = 0;
				int consultation_hours = 0;
				int control_hours = 0;
				error.setText("");
				
				try {
					credits = Integer.parseInt(input_credits.getText());
					if(credits == 0) {
						error.setText(error.getText() + "Error: credits{null}.\n");
						valid_form = false;
					}
				} catch(NumberFormatException er) {
					valid_form = false;
					error.setText(error.getText() + "Error: credits{not correct format}\n");
				}

				try {
					auditor_hours = Integer.parseInt(input_auditor_hours.getText());
					if(auditor_hours == 0) {
						error.setText(error.getText() + "Error: auditor_hours{null}\n");
						valid_form = false;
					}
				} catch(NumberFormatException er) {
					valid_form = false;
					error.setText(error.getText() + "Error: auditor_hours{not correct format}\n");
				}
				
				try {
					lectures_hours = Integer.parseInt(input_lectures.getText());
				} catch(NumberFormatException er) {
					valid_form = false;
					error.setText(error.getText() + "Error: lectures_hours{not correct format}\n");
				}
				
				try {
					labs_hours = Integer.parseInt(input_lab.getText());
				} catch(NumberFormatException er) {
					error.setText(error.getText() + "Error: labs_hours{not correct format}\n");
					valid_form = false;
				}
				
				try {
					practics_hours = Integer.parseInt(input_practics.getText());
				} catch(NumberFormatException er) {
					valid_form = false;
					error.setText(error.getText() + "Error: practics_hours{not correct format}\n");
				}
				
				try {
					seminar_hours = Integer.parseInt(input_seminar.getText());
				} catch(NumberFormatException er) {
					valid_form = false;
					error.setText(error.getText() + "Error: seminar_hours{not correct format}\n");
				}
				
				try {
					consultation_hours = Integer.parseInt(input_consultation.getText());
					if(consultation_hours == 0) {
						error.setText(error.getText() + "Error: consultation_hours{null}\n");
						valid_form = false;
					}
				} catch(NumberFormatException er) {
					valid_form = false;
					error.setText(error.getText() + "Error: consultation_hours{not correct format}\n");
				}
				
				try {
					control_hours = Integer.parseInt(input_control.getText());
				} catch(NumberFormatException er) {
					valid_form = false;
					error.setText(error.getText() + "Error: control_hours{not correct format}\n");
				}
				
				
				if(valid_form) {
					CourseInfo courseInfo = new CourseInfo();
					
					courseInfo.setCourse(course.getId());
					courseInfo.setGroup_name(group_name);
					courseInfo.setCredits(credits);
					courseInfo.setAuditor_hours(auditor_hours);
					courseInfo.setLectures_hours(lectures_hours);
					courseInfo.setLabs_hours(labs_hours);
					courseInfo.setPractics_hours(practics_hours);
					courseInfo.setSeminar_hours(seminar_hours);
					courseInfo.setConsultation_hours(consultation_hours);
					courseInfo.setControl_hours(control_hours);
					courseInfo.setPath_of_stady(active_path_for_stady);
					courseInfo.setCoursovy(coursovy);
					
					(new CourseInfoDAO()).save(courseInfo);
					++index_path;
					if(index_path < path_arr.length) {
						frame.changePanel(new addInfoForCourseMultiPath(frame, course, path_arr, index_path, hours_by_path));
					} else {
						frame.changePanel(new addCourse(frame));
					}
				} else {
					error.setText(error.getText() + "Error not valid form.\n");
					btnSave.setEnabled(true);
				}
			}
		});
		
		checkBox_credits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visible_credits = visible_credits ? false : true;
				if(visible_credits) {
					input_credits.setEnabled(true);
				} else {
					input_credits.setEnabled(false);
				}
				panel.update(getGraphics());
			}
		});
		
		checkBox_auditor_hours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visible_auditors_hours = visible_auditors_hours ? false : true;
				if(visible_auditors_hours) {
					input_auditor_hours.setEnabled(true);
				} else {
					input_auditor_hours.setEnabled(false);
				}
				panel.update(getGraphics());
			}
		});
		
		checkBox_lectures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visible_lectures = visible_lectures ? false : true;
				if(visible_lectures) {
					input_lectures.setEnabled(true);
				} else {
					input_lectures.setEnabled(false);
				}
				panel.update(getGraphics());
			}
		});
		
		checkBox_lab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visible_labs = visible_labs ? false : true;
				if(visible_labs) {
					input_lab.setEnabled(true);
				} else {
					input_lab.setEnabled(false);
				}
				panel.update(getGraphics());
			}
		});
		
		checkBox_pactics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visible_practics = visible_practics ? false : true;
				if(visible_practics) {
					input_practics.setEnabled(true);
				} else {
					input_practics.setEnabled(false);
				}
				panel.update(getGraphics());
			}
		});
		
		checkBox_seminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visible_seminars = visible_seminars ? false : true;
				if(visible_seminars) {
					input_seminar.setEnabled(true);
				} else {
					input_seminar.setEnabled(false);
				}
				panel.update(getGraphics());
			}
		});
		
		checkBox_consultation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visible_consultation = visible_consultation ? false : true;
				if(visible_consultation) {
					input_consultation.setEnabled(true);
				} else {
					input_consultation.setEnabled(false);
				}
				panel.update(getGraphics());
			}
		});
		
		checkBox_control.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visible_control = visible_control ? false : true;
				if(visible_control) {
					input_control.setEnabled(true);
				} else {
					input_control.setEnabled(false);
				}
				panel.update(getGraphics());
			}
		});
		
		radioCoursovy_none.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radioCoursovy_none.isSelected()) {
					coursovy = 0;
				} else {
					radioCoursovy_none.setSelected(true);
				}
				radioCoursovy_job.setSelected(false);
				radioCoursovy_project.setSelected(false);
			}
		});
		
		radioCoursovy_job.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radioCoursovy_job.isSelected()) {
					coursovy = 1;
				} else {
					radioCoursovy_job.setSelected(true);
				}
				radioCoursovy_none.setSelected(false);
				radioCoursovy_project.setSelected(false);
			}
		});
		
		radioCoursovy_project.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radioCoursovy_project.isSelected()) {
					coursovy = 2;
				} else {
					radioCoursovy_project.setSelected(true);
				}
				radioCoursovy_none.setSelected(false);
				radioCoursovy_job.setSelected(false);
			}
		});
	}
}
