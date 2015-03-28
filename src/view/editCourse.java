package view;

import init.Main;

import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import libs.XMLSettings;
import module.Course;
import module.CourseInfo;
import module.Subject;
import module.Teacher;
import dao.CourseDAO;
import dao.CourseInfoDAO;
import dao.SubjectDAO;
import dao.TeacherDAO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JRadioButton;

@SuppressWarnings("unused")
public class editCourse extends JPanel {

	private static final long serialVersionUID = 6752479071004695L;
	private Main frame;
	private XMLSettings settingsRead;
	private Course courseActive = null;
	private CourseInfo courseInfoActive = null;
	private List<CourseInfo> coursesInfoActive = null;
	private JPanel panel;
	private JComboBox<Item> comboBox_subject;
	private JComboBox<Item> comboBox_year;
	private JComboBox<Item> comboBox_groups;
	private JComboBox<Item> comboBox_edit_subject;
	private JComboBox<Item> comboBox_edit_teacher;
	private JTextField textField_edit_groups;
	private JTextField textField_edit_year1;
	private JTextField textField_edit_year2;
	private JComboBox<Item> comboBox_edit_type;
	private JButton button_save_course;
	private JTextField textField_edit_credits;
	private JTextField textField_edit_auditor;
	private JTextField textField_edit_lection;
	private JTextField textField_edit_laborator;
	private JTextField textField_edit_control;
	private JTextField textField_edit_consultation;
	private JTextField textField_edit_seminar;
	private JTextField textField_edit_practics;
	private JButton button_save_course_info;
	private JButton button_path_1;
	
	private List<Item> list_subjects = null;
	private List<Item> list_teachers = null;
	private List<Item> list_types = null;
	private JButton button_path_2;
	private JButton button_path_3;
	private JButton button_path_4;
	private JButton buttonDeleteCourse;
	private JButton buttonDeleteCourseInfo;
	private JLabel label_17;
	private JRadioButton radioCoursovy_none;
	private JRadioButton radioCoursovy_job;
	private JRadioButton radioCoursovy_project;

	private int coursovy = 0;
	/**
	 * Create the panel.
	 */
	public editCourse(Main pFrame) {
		init();
		try {
			settingsRead = new XMLSettings();
		} catch (Exception e) { JOptionPane.showConfirmDialog(null, e, "ERROR1", JOptionPane.PLAIN_MESSAGE); }
		
		try {
			events();
		} catch (Exception e) {  JOptionPane.showConfirmDialog(null, e, "ERROR2", JOptionPane.PLAIN_MESSAGE); }
		
		try {
			setEnableCourse(false);
		} catch (Exception e) {  JOptionPane.showConfirmDialog(null, e, "ERROR3", JOptionPane.PLAIN_MESSAGE); }
		
		try {
			setEnableCourseInfo(false);
		} catch (Exception e) {  JOptionPane.showConfirmDialog(null, e, "ERROR4", JOptionPane.PLAIN_MESSAGE); }
	}
	
	private void init() {
		setLayout(new BorderLayout(0, 0));
		
		Vector<Item> model_select = new Vector<Item>();
		model_select.addElement(new Item(0, " Select"));
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		
		JPanel panel_4 = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(75, Short.MAX_VALUE))
		);
		
		JLabel label_16 = new JLabel("Четверти: ");
		label_16.setHorizontalAlignment(SwingConstants.RIGHT);
		
		button_path_1 = new JButton("1-ая");
		button_path_1.setEnabled(false);
		
		button_path_2 = new JButton("2-ая");
		button_path_2.setEnabled(false);
		
		button_path_3 = new JButton("3-ая");
		button_path_3.setEnabled(false);
		
		button_path_4 = new JButton("4-ая");
		button_path_4.setEnabled(false);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_path_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_path_2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_path_3, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_path_4, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(413, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_path_1)
						.addComponent(button_path_2)
						.addComponent(button_path_3)
						.addComponent(button_path_4))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
		JLabel label_9 = new JLabel("Кредитов: ");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lbls = new JLabel("Аудиторных: ");
		lbls.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_11 = new JLabel("Лекций: ");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_12 = new JLabel("Лаб. робот: ");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		
		button_save_course_info = new JButton("Сохранить");
		button_save_course_info.setEnabled(false);
		
		textField_edit_credits = new JTextField();
		textField_edit_credits.setColumns(10);
		
		textField_edit_auditor = new JTextField();
		textField_edit_auditor.setColumns(10);
		
		textField_edit_lection = new JTextField();
		textField_edit_lection.setColumns(10);
		
		textField_edit_laborator = new JTextField();
		textField_edit_laborator.setColumns(10);
		
		JLabel label_10 = new JLabel("Контроль: ");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textField_edit_control = new JTextField();
		textField_edit_control.setColumns(10);
		
		textField_edit_consultation = new JTextField();
		textField_edit_consultation.setColumns(10);
		
		JLabel label_13 = new JLabel("Консультации: ");
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_14 = new JLabel("Семинары: ");
		label_14.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textField_edit_seminar = new JTextField();
		textField_edit_seminar.setColumns(10);
		
		textField_edit_practics = new JTextField();
		textField_edit_practics.setColumns(10);
		
		JLabel label_15 = new JLabel("Практических: ");
		label_15.setHorizontalAlignment(SwingConstants.RIGHT);
		
		buttonDeleteCourseInfo = new JButton("Удалить");
		buttonDeleteCourseInfo.setBackground(Color.RED);
		buttonDeleteCourseInfo.setEnabled(false);
		
		label_17 = new JLabel("Курсовые: ");
		label_17.setHorizontalAlignment(SwingConstants.RIGHT);
		
		radioCoursovy_none = new JRadioButton("Нет");
		
		radioCoursovy_job = new JRadioButton("Робота");
		
		radioCoursovy_project = new JRadioButton("Проэкт");
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_edit_credits, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lbls, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_edit_auditor, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_edit_lection, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_edit_laborator, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(label_17, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(radioCoursovy_none, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(radioCoursovy_job, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addComponent(radioCoursovy_project, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(91)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(textField_edit_practics, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(textField_edit_seminar, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(textField_edit_consultation, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(textField_edit_control, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(47)
							.addComponent(buttonDeleteCourseInfo, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(button_save_course_info, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(209, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addGap(3)
									.addComponent(textField_edit_practics, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(6)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addGap(3)
									.addComponent(textField_edit_seminar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(6)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addGap(3)
									.addComponent(textField_edit_consultation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(6)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addGap(3)
									.addComponent(textField_edit_control, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_edit_credits, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbls, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_edit_auditor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_edit_lection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_edit_laborator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_17, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(radioCoursovy_none))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(radioCoursovy_job)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(radioCoursovy_project))
						.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
							.addComponent(buttonDeleteCourseInfo, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addComponent(button_save_course_info, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
					.addGap(54))
		);
		panel_3.setLayout(gl_panel_3);
		
		JLabel label_3 = new JLabel("Предмет: ");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_4 = new JLabel("Преподователь: ");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);

		comboBox_edit_subject = new JComboBox<Item>(model_select);
		comboBox_edit_subject.setSelectedIndex(0);
		
		JLabel label_5 = new JLabel("Группы: ");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		
		comboBox_edit_teacher = new JComboBox<Item>(model_select);
		comboBox_edit_teacher.setSelectedIndex(0);
		
		textField_edit_groups = new JTextField();
		textField_edit_groups.setColumns(10);
		
		comboBox_edit_type = new JComboBox<Item>(model_select);
		comboBox_edit_type.setSelectedIndex(0);
		
		JLabel label_6 = new JLabel("Тип обучения: ");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_7 = new JLabel("Год: ");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textField_edit_year1 = new JTextField();
		textField_edit_year1.setColumns(10);
		
		textField_edit_year2 = new JTextField();
		textField_edit_year2.setEditable(false);
		textField_edit_year2.setColumns(10);
		
		JLabel label_8 = new JLabel(" - ");
		
		button_save_course = new JButton("Сохранить");
		button_save_course.setEnabled(false);
		
		buttonDeleteCourse = new JButton("Удалить");
		buttonDeleteCourse.setBackground(Color.RED);
		buttonDeleteCourse.setEnabled(false);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(label_5, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_4, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_edit_groups, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGap(182)
									.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(comboBox_edit_type, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
								.addComponent(comboBox_edit_teacher, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBox_edit_subject, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
									.addComponent(buttonDeleteCourse, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
									.addGap(25)
									.addComponent(button_save_course, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_edit_year1, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_8)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_edit_year2, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)))
					.addGap(206))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_edit_subject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_edit_teacher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_edit_groups, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_edit_year1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_8)
						.addComponent(textField_edit_year2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_edit_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(button_save_course, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonDeleteCourse, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel label_1 = new JLabel("Год: ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_2 = new JLabel("Группы: ");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		Vector<Item> modelS = new Vector<Item>();
		modelS.addElement(new Item(0, "Выберите предмет"));
        SubjectDAO subject_dao = new SubjectDAO();
		List<Subject> subject_list = subject_dao.getAll();
		int course_count = subject_list.size();
		for(int j = 0; j < course_count; ++j) {
			Subject course_now = subject_list.get(j);
    		modelS.addElement(new Item(course_now.getId(), course_now.getName()));
		}
		comboBox_subject = new JComboBox<Item>(modelS);
		comboBox_subject.setSelectedIndex(0);
		
		Vector<Item> modelY = new Vector<Item>();
		modelY.addElement(new Item(0, "Выберите год обучения"));
		comboBox_year = new JComboBox<Item>(modelY);
		comboBox_year.setSelectedIndex(0);
		comboBox_year.setEnabled(false);
		
		Vector<Item> modelG = new Vector<Item>();
		modelG.addElement(new Item(0, "Выберите поток"));
		comboBox_groups = new JComboBox<Item>(modelG);
		comboBox_groups.setSelectedIndex(0);
		comboBox_groups.setEnabled(false);
		
		JLabel label = new JLabel("Предмет: ");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(label_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
								.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_subject, GroupLayout.PREFERRED_SIZE, 439, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_year, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_groups, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(222, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_subject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_year, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_groups, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
	}
	
	private void events() {
		comboBox_subject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int subjects = ((Item) comboBox_subject.getSelectedItem()).toInteger();
	        	
        		if(subjects != 0) {
        			Vector<Item> modelY = new Vector<Item>();
        			modelY.addElement(new Item(0, "Выберите год обучения"));
                    
                    CourseDAO course_dao = new CourseDAO();
            		List<Integer> years_list = course_dao.getYearsBySybject(subjects); 
            		for(Integer yaer: years_list) {
            			modelY.addElement(new Item(yaer, yaer + " - " + (yaer + 1)));
            		}
            		
            		comboBox_year.setModel(new DefaultComboBoxModel<Item>(modelY));
            		comboBox_year.setSelectedIndex(0);
            		comboBox_year.setEnabled(true);
        		} else {
        			comboBox_year.setEnabled(false);
        			comboBox_year.setSelectedIndex(0);
        			comboBox_groups.setEnabled(false);
        			comboBox_groups.setSelectedIndex(0);
        		}
	        }
	    });
		
		comboBox_year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int subject = ((Item) comboBox_subject.getSelectedItem()).toInteger();
				int year = ((Item) comboBox_year.getSelectedItem()).toInteger();
	        	
        		if(year != 0) {
        			Vector<Item> modelY = new Vector<Item>();
        			modelY.addElement(new Item(0, "Выберите поток"));
                    
                    CourseDAO course_dao = new CourseDAO();
            		List<Course> course_list = course_dao.getByYearSubject(subject, year); 
            		for(Course course: course_list) {
            			modelY.addElement(new Item(course.getId(), course.getGroups()));
            		}
            		
            		comboBox_groups.setModel(new DefaultComboBoxModel<Item>(modelY));
            		comboBox_groups.setSelectedIndex(0);
            		comboBox_groups.setEnabled(true);
        		} else {
        			comboBox_groups.setSelectedIndex(0);
        			comboBox_groups.setEnabled(false);
        		}
	        }
	    });
		
		comboBox_groups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int course_id = ((Item) comboBox_groups.getSelectedItem()).toInteger();
	        	
        		if(course_id != 0) {
        			Course course = (new CourseDAO()).get(course_id);
        			setValueCourse(course);
        			
        			List<CourseInfo> courseInfo = (new CourseInfoDAO()).getByCourse(course_id);
        			setCoursesInfo(courseInfo);
        			
        			setEnableCourse(true);
        			setEnableCourseInfo(true);
        			button_save_course.setEnabled(true);
        			button_save_course_info.setEnabled(true);
        			buttonDeleteCourse.setEnabled(true);
        			buttonDeleteCourseInfo.setEnabled(true);
        		} else {
        			setEnableCourse(false);
        			setEnableCourseInfo(false);
        			button_save_course.setEnabled(false);
        			button_save_course_info.setEnabled(false);
        			buttonDeleteCourse.setEnabled(false);
        			buttonDeleteCourseInfo.setEnabled(false);
        			setEmptyCourse();
        			setEmptyCourseInfo();
        		}
	        }
	    });
		
		button_save_course.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Course course = getCourseActive();
				
				int subject_id = ((Item) comboBox_edit_subject.getSelectedItem()).toInteger();
				int teacher_id = ((Item) comboBox_edit_teacher.getSelectedItem()).toInteger();
				int type_id = ((Item) comboBox_edit_type.getSelectedItem()).toInteger();
				String groups = textField_edit_groups.getText();
				
				
				course.setSubject(subject_id);
				course.setTeacher(teacher_id);
				String yesr_ = textField_edit_year1.getText();
				if(!yesr_.isEmpty()) {
					try {
						int year = Integer.parseInt(yesr_);
						course.setYear(year);
					} catch(Exception e1) {System.out.println(e1);}
				}
				course.setGroups(groups);
				switch (type_id) {
				case 1:
					course.setForm_of_training(settingsRead.getVariable("konsultation_d_str").charAt(0));
					course.setForm_of_training_key((float)settingsRead.getVariableDouble("konsultation_d"));
					break;
					
				case 2:
					course.setForm_of_training(settingsRead.getVariable("konsultation_v_str").charAt(0));
					course.setForm_of_training_key((float)settingsRead.getVariableDouble("konsultation_v"));
					break;
					
				case 3:
					course.setForm_of_training(settingsRead.getVariable("konsultation_z_str").charAt(0));
					course.setForm_of_training_key((float)settingsRead.getVariableDouble("konsultation_z"));
					break;

				default:
					break;
				}
				(new CourseDAO()).update(course);
			}
		});
		
		button_save_course_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseInfo courseInfo = getCourseInfoActive();
				String input = "";
				//
				input = textField_edit_auditor.getText();
				if(!input.isEmpty()) {
					try{
						courseInfo.setAuditor_hours(Integer.parseInt(input));
					} catch(Exception e1) {System.out.println(e1);}
				}
				//
				input = textField_edit_consultation.getText();
				if(!input.isEmpty()) {
					try{
						courseInfo.setConsultation_hours(Integer.parseInt(input));
					} catch(Exception e1) {System.out.println(e1);}
				}
				//
				input = textField_edit_control.getText();
				if(!input.isEmpty()) {
					try{
						courseInfo.setControl_hours(Integer.parseInt(input));
					} catch(Exception e1) {System.out.println(e1);}
				}
				//
				input = textField_edit_credits.getText();
				if(!input.isEmpty()) {
					try{
						courseInfo.setCredits(Integer.parseInt(input));
					} catch(Exception e1) {System.out.println(e1);}
				}
				//
				input = textField_edit_laborator.getText();
				if(!input.isEmpty()) {
					try{
						courseInfo.setLabs_hours(Integer.parseInt(input));
					} catch(Exception e1) {System.out.println(e1);}
				}
				//
				input = textField_edit_lection.getText();
				if(!input.isEmpty()) {
					try{
						courseInfo.setLectures_hours(Integer.parseInt(input));
					} catch(Exception e1) {System.out.println(e1);}
				}
				//
				input = textField_edit_practics.getText();
				if(!input.isEmpty()) {
					try{
						courseInfo.setPractics_hours(Integer.parseInt(input));
					} catch(Exception e1) {System.out.println(e1);}
				}
				//
				input = textField_edit_seminar.getText();
				if(!input.isEmpty()) {
					try{
						courseInfo.setSeminar_hours(Integer.parseInt(input));
					} catch(Exception e1) {System.out.println(e1);}
				}
				
				courseInfo.setCoursovy(coursovy);
				(new CourseInfoDAO()).update(courseInfo);
			}
		});
		
		buttonDeleteCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int answerYes_No = JOptionPane.showConfirmDialog(null, 
						"Вы действительно хотите удалить полностью весь курс?", 
						"Удаление курса", JOptionPane.YES_NO_OPTION);
				if(answerYes_No == JOptionPane.YES_OPTION) {
					try{
						Course course = getCourseActive();
						int id_course = course.getId();
						CourseInfoDAO courseInfoDAO = new CourseInfoDAO();
						List<CourseInfo> courseInfos = courseInfoDAO.getByCourse(id_course);
						for(CourseInfo courseInfo: courseInfos) {
							(new CourseInfoDAO()).delete(courseInfo);
						}
						(new CourseDAO()).delete(course);
						int a = JOptionPane.showConfirmDialog(null, 
								"Курс успешно удален", 
								"Удаление курса", JOptionPane.PLAIN_MESSAGE);
					} catch(Exception ex) {
						JOptionPane.showConfirmDialog(null, 
								"Курс не был удален или удален частично из за ошибки!\r\nПерезагрузите эту страничку и если курс не удален удалите.", 
								"Удаление курса", JOptionPane.PLAIN_MESSAGE);
					}
					
					try {
						comboBox_subject.setSelectedIndex(0);
					} catch(Exception ex) {
						JOptionPane.showConfirmDialog(null, ex.toString(), "ERROR", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});
		
		buttonDeleteCourseInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int answerYes_No = JOptionPane.showConfirmDialog(null, 
						"Вы действительно хотите удалить курс в данной четверти?", 
						"Удаление курса данной четверти", JOptionPane.YES_NO_OPTION);
				if(answerYes_No == JOptionPane.YES_OPTION) {
					try {
						Course course = getCourseActive();
						CourseInfo courseInfo = getCourseInfoActive();

						switch (courseInfo.getPath_of_stady()) { 
							case 1:
								course.setStady_in_path_1(0);
								break;
							case 2:
								course.setStady_in_path_2(0);
								break;
							case 3:
								course.setStady_in_path_3(0);
								break;
							case 4:
								course.setStady_in_path_4(0);
								break;
						}
						(new CourseInfoDAO()).delete(courseInfo);
						(new CourseDAO()).update(course);
						
						int a = JOptionPane.showConfirmDialog(null, 
								"Четверть данного курса успешно удалена", 
								"Удаление четверти курса", JOptionPane.PLAIN_MESSAGE);
					} catch(Exception ex) {
						JOptionPane.showConfirmDialog(null, 
								"Четверть данного курса не удалена по возможности ошибки", 
								"Удаление четверти курса", JOptionPane.PLAIN_MESSAGE);
					}
					
					try {
						int pos = comboBox_groups.getSelectedIndex();
						comboBox_groups.setSelectedIndex(0);
						comboBox_groups.setSelectedIndex(pos);
					} catch(Exception ex) {
						JOptionPane.showConfirmDialog(null, ex.toString(), "ERROR", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});
		
		button_path_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectPathStadyInfo(1);
			}
		});
		
		button_path_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectPathStadyInfo(2);
			}
		});
		
		button_path_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectPathStadyInfo(3);
			}
		});
		
		button_path_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectPathStadyInfo(4);
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
	
	private void setEnableCourse(boolean show) {
		comboBox_edit_subject.setEnabled(show);
		comboBox_edit_teacher.setEnabled(show);
		textField_edit_year1.setEnabled(show);
		textField_edit_groups.setEnabled(show);
		comboBox_edit_type.setEnabled(show);
	}
	
	private void setEnableCourseInfo(boolean show) {
		textField_edit_credits.setEnabled(show);
		textField_edit_lection.setEnabled(show);
		textField_edit_auditor.setEnabled(show);
		textField_edit_laborator.setEnabled(show);
		textField_edit_practics.setEnabled(show);
		textField_edit_seminar.setEnabled(show);
		textField_edit_consultation.setEnabled(show);
		textField_edit_control.setEnabled(show);
	}
	
	private void setEmptyCourse() {
		setCourseActive(null);
		comboBox_edit_subject.setSelectedIndex(0);
		comboBox_edit_teacher.setSelectedIndex(0);
		textField_edit_year1.setText("");
		textField_edit_year2.setText("");
		textField_edit_groups.setText("");
		comboBox_edit_type.setSelectedIndex(0);
	}
	
	private void setEmptyCourseInfo() {
		try {
			setCourseInfoActive(null);
			radioCoursovy_none.setSelected(true);
			radioCoursovy_job.setSelected(false);
			radioCoursovy_project.setSelected(false);
			button_path_1.setEnabled(false);
			button_path_3.setEnabled(false);
			button_path_4.setEnabled(false);
			button_path_4.setEnabled(false);
			button_path_1.setBackground(new Color(240,240,240));
			button_path_2.setBackground(new Color(240,240,240));
			button_path_3.setBackground(new Color(240,240,240));
			button_path_4.setBackground(new Color(240,240,240));
			
			textField_edit_credits.setText("");
			textField_edit_lection.setText("");
			textField_edit_auditor.setText("");
			textField_edit_laborator.setText("");
			textField_edit_practics.setText("");
			textField_edit_seminar.setText("");
			textField_edit_consultation.setText("");
			textField_edit_control.setText("");
		} catch(Exception e) {
			 JOptionPane.showConfirmDialog(null, e, "setEmptyCourseInfo", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	private void setValueCourse(Course course) {
		setCourseActive(course);
		
		button_path_1.setEnabled(course.getStady_in_path_1() == 1 ? true : false);
		button_path_2.setEnabled(course.getStady_in_path_2() == 1 ? true : false);
		button_path_3.setEnabled(course.getStady_in_path_3() == 1 ? true : false);
		button_path_4.setEnabled(course.getStady_in_path_4() == 1 ? true : false);
		
		Vector<Item> modelS_ = new Vector<Item>();
        SubjectDAO subject_dao = new SubjectDAO();
		List<Subject> subject_list = subject_dao.getAll();
		int course_count = subject_list.size();
		int ind = 0;
		for(int j = 0; j < course_count; ++j) {
			Subject course_now = subject_list.get(j);
			modelS_.addElement(new Item(course_now.getId(), course_now.getName()));
			ind = (course_now.getId() == course.getSubject()) ? j : ind ;
		}
		comboBox_edit_subject.setModel(new DefaultComboBoxModel<Item>(modelS_));
		comboBox_edit_subject.setSelectedIndex(ind);
		// -------------------------------------------------
		Vector<Item> modelT_ = new Vector<Item>();
        TeacherDAO teacherDAO = new TeacherDAO();
		List<Teacher> teList = teacherDAO.getAll();
		int course_count1 = teList.size();
		ind = 0;
		for(int j = 0; j < course_count1; ++j) {
			Teacher teacher = teList.get(j);
			modelT_.addElement(new Item(teacher.getId(), teacher.getName()));
			ind = (teacher.getId() == course.getTeacher()) ? j : ind ;
		}
		comboBox_edit_teacher.setModel(new DefaultComboBoxModel<Item>(modelT_));
		comboBox_edit_teacher.setSelectedIndex(ind);
		// --------------------------------------------------------
		textField_edit_year1.setText(course.getYear().toString());
		textField_edit_year1.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e){ warn(); }
			  public void removeUpdate(DocumentEvent e) { warn(); }
			  public void insertUpdate(DocumentEvent e) { warn(); }
			  public void warn() { 
				  try {
					  String s = textField_edit_year1.getText();
					  if(!s.isEmpty()) {
						  int y1 =  Integer.parseInt(textField_edit_year1.getText());
						  textField_edit_year2.setText((y1 + 1) + "");
					  }
				  } catch(Error e) { System.out.println(e);}
			  }
			});
		// --------------------------------------------------------
		textField_edit_year2.setText((course.getYear() + 1) + "");
		// --------------------------------------------------------
		textField_edit_groups.setText(course.getGroups());
		// --------------------------------------------------------
		Vector<Item> modelP = new Vector<Item>();
		modelP.addElement(new Item(1, "Дневное"));
		modelP.addElement(new Item(2, "Вечернее"));
		modelP.addElement(new Item(3, "Заочное"));
		comboBox_edit_type.setModel(new DefaultComboBoxModel<Item>(modelP));
		ind = 0;
		switch (course.getForm_of_training()) {
			case 'd':
				ind = 0;
				break;
				
			case 'v':
				ind = 1;
				break;
				
			case 'z':
				ind = 2;
				break;
				
			default:
				ind = 0;
				break;
		}
		comboBox_edit_type.setSelectedIndex(ind);
		// --------------------------------------------------------
		
	}
	
	private void setCoursesInfo(List<CourseInfo> courseInfos) {
		try {
			setCoursesInfoActive(courseInfos);
			if(courseInfos.size() > 0) {
				selectPathStadyInfo(courseInfos.get(0));
			}
		} catch(Exception e) {
			 JOptionPane.showConfirmDialog(null, e, "setCoursesInfo 1028", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	private void selectPathStadyInfo(int path_) {
		try {
			List<CourseInfo> courseInfos = getCoursesInfoActive();
			int path = 0;
			int i = 0;
			for(CourseInfo courseInfo : courseInfos) {
				path = (courseInfo.getPath_of_stady() == path_) ? i : path;
				++i;
			}
			selectPathStadyInfo(courseInfos.get(path));
		} catch(Exception e) {
			 JOptionPane.showConfirmDialog(null, e, "selectPathStadyInfo 1043", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	private void selectPathStadyInfo(CourseInfo courseInfo) {	
		try {
			setCourseInfoActive(courseInfo);
			setValueCourseInfo(courseInfo);
			button_path_1.setBackground(new Color(240,240,240));
			button_path_2.setBackground(new Color(240,240,240));
			button_path_3.setBackground(new Color(240,240,240));
			button_path_4.setBackground(new Color(240,240,240));
			
			switch (courseInfo.getPath_of_stady()) {
			case 1: button_path_1.setBackground(new Color(255,0,0));
				break;
			case 2: button_path_2.setBackground(new Color(255,0,0));
				break;
			case 3: button_path_3.setBackground(new Color(255,0,0));
				break;
			case 4: button_path_4.setBackground(new Color(255,0,0));
				break;
			}
		} catch(Exception e) {
			 JOptionPane.showConfirmDialog(null, e, "selectPathStadyInfo 1067", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	private void setValueCourseInfo(CourseInfo courseInfo) {
		try {
			textField_edit_credits.setText(courseInfo.getCredits().toString());
			textField_edit_lection.setText(courseInfo.getLectures_hours().toString());
			textField_edit_auditor.setText(courseInfo.getAuditor_hours().toString());
			textField_edit_laborator.setText(courseInfo.getLabs_hours().toString());
			textField_edit_practics.setText(courseInfo.getPractics_hours().toString());
			textField_edit_seminar.setText(courseInfo.getSeminar_hours().toString());
			textField_edit_consultation.setText(courseInfo.getConsultation_hours().toString());
			textField_edit_control.setText(courseInfo.getControl_hours().toString());
			coursovy = courseInfo.getCoursovy();
			
			radioCoursovy_none.setSelected(false);
			radioCoursovy_job.setSelected(false);
			radioCoursovy_project.setSelected(false);
			switch (coursovy) {
			case 0:
				radioCoursovy_none.setSelected(true);
				break;
				
			case 1:
				radioCoursovy_job.setSelected(true);
				break;
				
			case 2:
				radioCoursovy_project.setSelected(true);
				break;
	
			default:
				coursovy = 0;
				radioCoursovy_none.setSelected(true);
				break;
			}
		} catch(Exception e) {
			 JOptionPane.showConfirmDialog(null, e, "setValueCourseInfo CourseInfo 1105", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public Course getCourseActive() {
		return courseActive;
	}

	public void setCourseActive(Course courseActive_) {
		this.courseActive = courseActive_;
	}
	
	public CourseInfo getCourseInfoActive() {
		return courseInfoActive;
	}

	public void setCourseInfoActive(CourseInfo courseInfoActive_) {
		this.courseInfoActive = courseInfoActive_;
	}
	
	public List<CourseInfo> getCoursesInfoActive() {
		return coursesInfoActive;
	}

	public void setCoursesInfoActive(List<CourseInfo> courseInfoActive_) {
		this.coursesInfoActive = courseInfoActive_;
	}


	class Item {
        private int id;
        private String description;

        public Item(int id, String description) {
            this.id = id;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public String toString() {
            return description;
        }
        
        public int toInteger() {
            return id;
        }
    }
}
