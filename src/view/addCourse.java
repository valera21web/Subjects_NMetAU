package view;

import init.Main;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JCheckBox;
import org.hibernate.HibernateException;
import dao.CourseDAO;
import dao.SubjectDAO;
import libs.XMLSettings;
import module.Course;
import module.Subject;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class addCourse extends JPanel {

	private static final long serialVersionUID = 1L;
	private XMLSettings settingsRead;
	private boolean visible_years = false;
	private int year = 0;
	private String form_of_stady;
	private float form_of_stady_key;
	private JTextField input_groups;
	private JTextField input_year_to;
	private JButton buttonSave;
	private JPanel panel;
	private JTextField input_year_from;
	private JLabel label_error_name_subject;
	private JLabel label_error_group;
	private JLabel label_error_years;
	private JCheckBox checkbox_years;
	private JLabel label_for_name_subject;
	private JLabel label_for_groups;
	private JLabel label_for_years;
	private JLabel label_for_full_hours;
	private JTextField input_full_hours;
	private JLabel label_error_full_hours;
	private Main frame;
	private JRadioButton radioButton_d;
	private JRadioButton radioButton_v;
	private JRadioButton radioButton_z;
	private JLabel label_2;
	private JCheckBox staty_in_path_4;
	private JCheckBox staty_in_path_3;
	private JCheckBox staty_in_path_2;
	private JCheckBox staty_in_path_1;
	private JComboBox<Item> selectboxSubject;
	private JTextField input_new_group;
	private JTextField input_group_count;
	private JButton btnAdd;
	private JCheckBox chckbxChange;

	/**
	 * Create the panel.
	 */
	public addCourse(Main pFrame) {
		frame = pFrame;
		beforeInit();
		initContent();
		afterInitContent();
		createdEvents();
	}
	

	private void beforeInit() {
		Date date = new Date();
		SimpleDateFormat simpleDateformat=new SimpleDateFormat("yyyy");
		year = Integer.parseInt(simpleDateformat.format(date));
		
		try {
			settingsRead = new XMLSettings();
			form_of_stady = settingsRead.getVariable("konsultation_d_str");
			form_of_stady_key =  (float) settingsRead.getVariableDouble("konsultation_d");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, "Проблема с файлом настроек с константами. \r\n[settings.xml]", "Ошибка", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	
	private void initContent() {
		setLayout(new BorderLayout(0, 0));
		panel = new JPanel();
		
		JLabel label = new JLabel("\u0414\u043E\u0431\u0430\u0432\u043B\u0435\u043D\u0438\u0435 \u043D\u043E\u0432\u043E\u0433\u043E \u043A\u0443\u0440\u0441\u0430");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.ITALIC, 18));
		
		buttonSave = new JButton("\u0414\u0430\u043B\u044C\u0448\u0435");
		buttonSave.setFont(new Font("Arial", Font.PLAIN, 14));
		
		label_for_name_subject = new JLabel("\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435 \u043F\u0440\u0435\u0434\u043C\u0435\u0442\u0430: ");
		label_for_name_subject.setHorizontalAlignment(SwingConstants.RIGHT);
		label_for_name_subject.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		
		input_groups = new JTextField();
		input_groups.setEnabled(false);
		input_groups.setColumns(10);
		
		label_for_groups = new JLabel("\u0413\u0440\u0443\u043F\u043F\u044B: ");
		label_for_groups.setHorizontalAlignment(SwingConstants.RIGHT);
		label_for_groups.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		
		input_year_from = new JTextField();
		input_year_from.setColumns(10);
		
		input_year_to = new JTextField();
		input_year_to.setColumns(10);
		
		label_for_years = new JLabel("\u0413\u043E\u0434:");
		label_for_years.setBackground(new Color(119, 136, 153));
		label_for_years.setHorizontalAlignment(SwingConstants.RIGHT);
		label_for_years.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		
		JLabel label_4 = new JLabel("/");
		
		label_error_name_subject = new JLabel("");
		label_error_name_subject.setFont(new Font("Arial", Font.ITALIC, 12));
		
		label_error_group = new JLabel("");
		label_error_group.setFont(new Font("Arial", Font.ITALIC, 12));
		
		label_error_years = new JLabel("");
		label_error_years.setFont(new Font("Arial", Font.ITALIC, 12));
		
		checkbox_years = new JCheckBox("\u0438\u0437\u043C\u0435\u043D\u0438\u0442\u044C");
		
		label_for_full_hours = new JLabel("\u041F\u043E\u043B\u043D\u043E\u0435 \u043A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0447\u0430\u0441\u043E\u0432: ");
		label_for_full_hours.setHorizontalAlignment(SwingConstants.RIGHT);
		label_for_full_hours.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		
		input_full_hours = new JTextField();
		input_full_hours.setColumns(10);
		
		label_error_full_hours = new JLabel("");
		label_error_full_hours.setFont(new Font("Arial", Font.ITALIC, 12));
		
		radioButton_d = new JRadioButton("Дневная");
		radioButton_d.setSelected(true);
		
		radioButton_v = new JRadioButton("Вечерная");
		
		radioButton_z = new JRadioButton("Заочная");
		
		JLabel label_1 = new JLabel("Форма обучения:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		label_1.setBackground(new Color(119, 136, 153));
		
		label_2 = new JLabel("Преподаеться в :");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		label_2.setBackground(new Color(119, 136, 153));
		
		staty_in_path_1 = new JCheckBox("1-ой четверти");
		
		staty_in_path_2 = new JCheckBox("2-ой четверти");
		
		staty_in_path_3 = new JCheckBox("3-ой четверти");
		
		staty_in_path_4 = new JCheckBox("4-ой четверти");
		
		selectboxSubject = new JComboBox<Item>();
		
		input_new_group = new JTextField();
		input_new_group.setColumns(10);
		
		input_group_count = new JTextField();
		input_group_count.setColumns(10);
		
		btnAdd = new JButton("Add");
		
		JLabel label_3 = new JLabel("Имя группы: ");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_5 = new JLabel("Кол. студентов: ");
		
		chckbxChange = new JCheckBox("change");
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_for_groups, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(input_new_group, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(label_5)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(input_group_count, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnAdd))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(input_groups, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(chckbxChange))
								.addComponent(label_error_group, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE))
							.addGap(137))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_for_name_subject, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_error_name_subject, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
								.addComponent(selectboxSubject, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(staty_in_path_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
						.addComponent(staty_in_path_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(staty_in_path_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(staty_in_path_4, Alignment.LEADING))
					.addContainerGap(362, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_for_full_hours, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(input_full_hours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label_error_full_hours, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(240))
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(label_for_years, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_error_years, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(input_year_from, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(input_year_to, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(checkbox_years))
						.addComponent(radioButton_z, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(radioButton_v, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(radioButton_d, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(297, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(321)
					.addComponent(buttonSave)
					.addContainerGap(286, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(56)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_for_name_subject, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(selectboxSubject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_error_name_subject, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_for_groups, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(input_groups, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxChange))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(input_new_group, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5)
						.addComponent(btnAdd)
						.addComponent(input_group_count, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(1)
					.addComponent(label_error_group, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_for_full_hours, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(input_full_hours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_error_full_hours, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(input_year_from, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_for_years, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(input_year_to, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkbox_years))
					.addGap(5)
					.addComponent(label_error_years, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(radioButton_d)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(radioButton_v)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(radioButton_z)))
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(staty_in_path_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(staty_in_path_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(staty_in_path_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(staty_in_path_4)
					.addGap(21)
					.addComponent(buttonSave)
					.addContainerGap(129, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		add(panel, BorderLayout.CENTER);
	}
	
	private void afterInitContent() {
		input_year_from.setEnabled(false);
		input_year_to.setEnabled(false);
		input_year_from.setText(Integer.toString(year));
		input_year_to.setText(Integer.toString(year + 1));
		
		List<Subject> subjects = (new SubjectDAO()).getAll();
		
		Vector<Item> aModel = new Vector<Item>();
		aModel.addElement(new Item(0, "Выбрать предмет"));
		for(Subject subject: subjects) {
			aModel.addElement(new Item(subject.getId(), subject.getName()));
		}
		selectboxSubject.setModel(new DefaultComboBoxModel<Item>(aModel));
		selectboxSubject.setSelectedIndex(0);
	}
	
	private void createdEvents() {
		
		
		radioButton_d.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radioButton_d.isSelected()) {
					form_of_stady = settingsRead.getVariable("konsultation_d_str");
					form_of_stady_key = (float) settingsRead.getVariableDouble("konsultation_d");;
				} else {
					radioButton_d.setSelected(true);
				}
				radioButton_v.setSelected(false);
				radioButton_z.setSelected(false);
			}
		});
		
		radioButton_v.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radioButton_v.isSelected()) {
					form_of_stady = settingsRead.getVariable("konsultation_v_str");
					form_of_stady_key = (float) settingsRead.getVariableDouble("konsultation_v");;
				} else {
					radioButton_v.setSelected(true);
				}
				radioButton_d.setSelected(false);
				radioButton_z.setSelected(false);
			}
		});
		
		radioButton_z.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radioButton_z.isSelected()) {
					form_of_stady = settingsRead.getVariable("konsultation_z_str");
					form_of_stady_key = (float) settingsRead.getVariableDouble("konsultation_z");;
				} else {
					radioButton_z.setSelected(true);
				}
				radioButton_d.setSelected(false);
				radioButton_v.setSelected(false);
			}
		});
		
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean is_valid = true; 
				
				label_error_name_subject.setText("");
				label_error_group.setText("");
				label_error_years.setText("");
				label_error_full_hours.setText("");
				label_for_name_subject.setForeground(new Color(0, 0, 0));
				label_for_groups.setForeground(new Color(0, 0, 0));
				label_for_years.setForeground(new Color(0, 0, 0));
				label_for_full_hours.setForeground(new Color(0, 0, 0));
		        
				int subject_id = 0;
				String groups = "";
				int full_hours = 0;
				int year_from = 0;
				int year_to = 0;
				
				
				try {
					subject_id = ((Item) selectboxSubject.getSelectedItem()).toInteger();
					if(subject_id == 0) {
						label_error_name_subject.setText("Not correct subject name.");
						label_for_name_subject.setForeground(new Color(255, 0, 0));
						is_valid = false;
					}
				} catch(NumberFormatException e) {
					label_error_group.setText("Not correct subject name.");
					label_for_name_subject.setForeground(new Color(255, 0, 0));
					is_valid = false;
				}
				
				try {
					groups = input_groups.getText();
					if(groups.length() < 5) {
						label_error_group.setText("Not correct groups.");
						label_for_groups.setForeground(new Color(255, 0, 0));
						is_valid = false;
					}
				} catch(NumberFormatException e) {
					label_error_group.setText("Not correct groups.");
					label_for_groups.setForeground(new Color(255, 0, 0));
					is_valid = false;
				}
				
				try {
					full_hours = Integer.parseInt(input_full_hours.getText());
					if(full_hours <= 0) {
						label_error_full_hours.setText("Not correct hours.");
						label_for_full_hours.setForeground(new Color(255, 0, 0));
						is_valid = false;
					}
				} catch(NumberFormatException e) {
					label_error_full_hours.setText("Not correct hours.");
					label_for_full_hours.setForeground(new Color(255, 0, 0));
					is_valid = false;
				}
				
				try {
					year_from = Integer.parseInt(input_year_from.getText());
					year_to = Integer.parseInt(input_year_to.getText());
					if(year_from < 2010 || year_from > 2100 || year_to < 2010 || year_to > 2100 || (year_to - year_from != 1)) {
						label_error_years.setText("Not correct Years.");
						label_for_years.setForeground(new Color(255, 0, 0));
						is_valid = false;
					}
				} catch(NumberFormatException e) {
					label_error_years.setText("Not correct Years. [2010-2100]");
					label_for_years.setForeground(new Color(255, 0, 0));
					is_valid = false;
				}
				
				if(is_valid) {
					buttonSave.setEnabled(false);			
					try {
						Course course = new Course();
						
						String[] groups_list = groups.split(";");
						groups = "";
						String count_user_in_groups = "";
						String tmp_str = "";
						Integer tmp_int = 0;
						int count_list = groups_list.length;
						int tmp_stady_in_path = 0;
						int count_stady_in_path = 0;
						
						
						try {
							for(int i = 0; i < count_list; ++i) {
								tmp_str = groups_list[i];
								tmp_int = tmp_str.indexOf("(");
								groups += tmp_str.substring(0, tmp_int);
								count_user_in_groups += tmp_str.substring(tmp_int+1, tmp_str.indexOf(")"));
								
								if((i + 1) != count_list) {
									groups += ";";
									count_user_in_groups += ";";
								}	
							}
						} catch(HibernateException he) {
							JOptionPane.showConfirmDialog(null, "Ошибка при сохранении курса", "Ошибка", JOptionPane.PLAIN_MESSAGE);
						}
												
						course.setSubject(subject_id);
						course.setCount(count_list);
						course.setGroups(groups);
						course.setCount_users_in_groups(count_user_in_groups);
						course.setHours(full_hours);
						course.setYear(year_from);
						course.setForm_of_training(form_of_stady.charAt(0));
						course.setForm_of_training_key(form_of_stady_key);
						
						tmp_stady_in_path = staty_in_path_1.isSelected() ? 1 : 0;
						count_stady_in_path += tmp_stady_in_path;
						course.setStady_in_path_1(tmp_stady_in_path);
						
						tmp_stady_in_path = staty_in_path_2.isSelected() ? 1 : 0;
						count_stady_in_path += tmp_stady_in_path;
						course.setStady_in_path_2(tmp_stady_in_path);
						
						tmp_stady_in_path = staty_in_path_3.isSelected() ? 1 : 0;
						count_stady_in_path += tmp_stady_in_path;
						course.setStady_in_path_3(tmp_stady_in_path);
						
						tmp_stady_in_path = staty_in_path_4.isSelected() ? 1 : 0;
						count_stady_in_path += tmp_stady_in_path;
						course.setStady_in_path_4(tmp_stady_in_path);
						
						Integer[] path_arr = new Integer[count_stady_in_path];
						int tmp_index = 0;
						if(course.getStady_in_path_1() == 1) { 
							path_arr[tmp_index] = 1;
							++tmp_index;
						}
						if(course.getStady_in_path_2() == 1) { 
							path_arr[tmp_index] = 2;
							++tmp_index;
						}
						if(course.getStady_in_path_3() == 1) { 
							path_arr[tmp_index] = 3;
							++tmp_index;
						}
						if(course.getStady_in_path_4() == 1) { 
							path_arr[tmp_index] = 4;
							++tmp_index;
						}
						
						if(count_stady_in_path == 0) {
							JOptionPane.showConfirmDialog(null, "Не выбрана четверть для курса", "Ошибка", JOptionPane.PLAIN_MESSAGE);
						} else {
							int course_id = 0;
							try {
								course_id = (new CourseDAO()).save(course);
							} catch(HibernateException he) {
								JOptionPane.showConfirmDialog(null, "Ошибка при сохранении курса", "Ошибка", JOptionPane.PLAIN_MESSAGE);
							}
							if(course_id > 0) {
								try {
									if(count_stady_in_path == 1) {
										frame.changePanel(new addInfoForCourseOnePath(frame, course_id));
									} else {
										frame.changePanel(new splitFullHoursOnPath(frame, course, path_arr));
									}	
								} catch(HibernateException he) {
									JOptionPane.showConfirmDialog(null, "Ошибка при открытия окна информации курса", "Ошибка", JOptionPane.PLAIN_MESSAGE);
								}
							}
						}
					} catch(HibernateException he) {
						JOptionPane.showConfirmDialog(null, "Ошибка при сохранении", "Ошибка", JOptionPane.PLAIN_MESSAGE);
					}
					buttonSave.setEnabled(true);
				} else {
					JOptionPane.showConfirmDialog(null, "Форма заполнена не верно", "Ошибка", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		
		checkbox_years.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visible_years = visible_years ? false : true;
				if(visible_years) {
					input_year_to.setEnabled(true);
					input_year_from.setEnabled(true);
				} else {
					input_year_to.setEnabled(false);
					input_year_from.setEnabled(false);
				}
				panel.update(getGraphics());
			}
		});

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int count_users = 0;
				int valid = 1;
				if(input_new_group.getText().length() >= 4) {
					try {
						count_users = Integer.parseInt(input_group_count.getText());
						if(count_users == 0) {
							valid = 0;
						}
					} catch(Exception e) {
						valid = 0;
					} finally {
						if(valid == 1) {
							input_groups.setText(input_groups.getText() + input_new_group.getText() + "(" + count_users + ");");
							input_new_group.setText("");
							input_group_count.setText("");
						}
					}
				}
			}
		});
		
		chckbxChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxChange.isSelected()) {
					input_groups.setEnabled(true);
				} else {
					input_groups.setEnabled(false);
				}
				panel.update(getGraphics());
			}
		});
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
