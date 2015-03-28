package view;

import init.Main;
import init.PreView;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import objects.ValRow;
import objects.ValRows;
import objects.ValTable;
import libs.ExportToExcel;
import libs.XMLSettings;
import module.Course;
import module.CourseInfo;
import module.Subject;
import module.Teacher;
import dao.CourseDAO;
import dao.CourseInfoDAO;
import dao.SubjectDAO;
import dao.TeacherDAO;

import java.awt.Color;

public class Export extends JPanel {

	private static final long serialVersionUID = -4674017122260039365L;
	private Main frame;
	private String defaulTextLabelSort = "Сортировать по: ";
	private String defaulTextSelectItem = "Сортировать по: ";
	
	private JComboBox<Item> selectTypeExport;
	private JLabel labelSort_1;
	private JLabel labelSort_2;
	private JLabel labelSort_3;
	private JButton btnExport;
	@SuppressWarnings("rawtypes")
	private JComboBox selectSort_1;
	@SuppressWarnings("rawtypes")
	private JComboBox selectSort_2;
	@SuppressWarnings("rawtypes")
	private JComboBox selectSort_3;
	private JButton btnPreView;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	private JButton btnPrint;
	/**
	 * Create the panel.
	 */
	public Export(Main pFrame) {
		setFrame(pFrame);
		init();
		events();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void init() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		
		JLabel lblExport = new JLabel("Export:");
		lblExport.setHorizontalAlignment(SwingConstants.RIGHT);
		
		Vector<Object> model = new Vector<Object>();
        model.addElement(new Item(0, "Выберите получаемые данные"));
        model.addElement(new Item(1, "Полный список"));
		selectTypeExport = new JComboBox(model);
		selectTypeExport.setSelectedIndex(0);
		
		JLabel label_1 = new JLabel("Дополнительная сортировка");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		labelSort_1 = new JLabel(defaulTextLabelSort);
		labelSort_1.setEnabled(false);
		labelSort_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		labelSort_2 = new JLabel(defaulTextLabelSort);
		labelSort_2.setEnabled(false);
		labelSort_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		labelSort_3 = new JLabel(defaulTextLabelSort);
		labelSort_3.setEnabled(false);
		labelSort_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		btnExport = new JButton("Export");
		
		Vector<Object> model_s1 = new Vector<Object>();
		model_s1.addElement(new Item(0, defaulTextSelectItem));
		selectSort_1 = new JComboBox(model_s1);
		selectSort_1.setEnabled(false);
		
		selectSort_2 = new JComboBox(model_s1);
		selectSort_2.setEnabled(false);
		
		selectSort_3 = new JComboBox(model_s1);
		selectSort_3.setEnabled(false);
		
		btnPreView = new JButton("PreView");
		
		label = new JLabel("");
		label.setBackground(Color.WHITE);
		
		label_2 = new JLabel("");
		label_2.setBackground(Color.WHITE);
		
		label_3 = new JLabel("");
		label_3.setBackground(Color.WHITE);
		
		btnPrint = new JButton("Print");
		btnPrint.setEnabled(false);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
											.addComponent(labelSort_2, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
											.addComponent(labelSort_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
									.addGroup(gl_panel.createSequentialGroup()
										.addGap(18)
										.addComponent(labelSort_1, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
											.addComponent(btnPrint, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
											.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addComponent(btnPreView, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
											.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
									.addComponent(selectSort_1, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(selectSort_3, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(selectSort_2, Alignment.LEADING, 0, 193, Short.MAX_VALUE))))
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(163)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(41)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addGap(51)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(106)
							.addComponent(lblExport, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(selectTypeExport, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(175, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(48)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblExport, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(selectTypeExport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(selectSort_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelSort_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(selectSort_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelSort_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(selectSort_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelSort_3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
							.addGap(32)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPreView, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
							.addGap(36)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(223)
							.addComponent(btnPrint, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(148, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
	
	private void events() {
		//listTeachers
		selectTypeExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int inpType = ((Item)selectTypeExport.getSelectedItem()).toInteger();
				if(inpType > 0) {
					switch(inpType) {
						case 1:
							setSelectsForFull();
							break;
							
						case 2:
							setSelectsForTeacherWithSubject();
							break;
							
						case 3:
							setSelectsForTeacherWithCourses();
							break;
							
						case 4:
							setSelectsForCourses();
							break;
						 
						default:
							setSelectsEmpty();
							break;
					}
				} else {
					setSelectsEmpty();
				}
            }
        });
		
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int inpType = ((Item)selectTypeExport.getSelectedItem()).toInteger();
				if(inpType != 0) {
					ValTable table = getBody(inpType);
					ExportToExcel.export(frame, table.toArrayHead(), table.toArrayBody());
				}
			}
		});
		
		btnPreView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int inpType = ((Item)selectTypeExport.getSelectedItem()).toInteger();
				if(inpType != 0) {
					ValTable table = getBody(inpType);
					new PreView(table);
				}
			}
		});
		
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* int inpType = ((Item)selectTypeExport.getSelectedItem()).toInteger();
				if(inpType != 0 && false) {
					ValTable table = getBody(inpType);
					PrintToPrinter.printSCV(frame, table);
				}	
				*/			
			}
		});
	}
	
	private ValTable getBody(int inpType) {
		//int inpType = ((Item)selectTypeExport.getSelectedItem()).toInteger();
		ValTable table = new ValTable();
		
		switch(inpType) {
			case 1:
				table = getFullList();
				break;
			case 2:
				table = getListTeacherWithSubject();
				break;
			case 3:
				table = getListTeacherWithCourses();
				break;
			case 4:
				table = getListCourses();
				break;
			 
			default:
				break;
		}
		return table;
	}
	
	@SuppressWarnings("unchecked")
	private void setSelectsEmpty() {
		Vector<Object> aModel = new Vector<Object>();
		aModel.addElement(new Item(0, defaulTextSelectItem));
		selectSort_1.setModel(new DefaultComboBoxModel<Object>(aModel));
		selectSort_1.setEnabled(false);
		labelSort_1.setText(defaulTextLabelSort);
		labelSort_1.setEnabled(false);
		
		Vector<Object> bModel = new Vector<Object>();
		bModel.addElement(new Item(0, defaulTextSelectItem));
		selectSort_2.setModel(new DefaultComboBoxModel<Object>(bModel));
		selectSort_2.setEnabled(false);
		labelSort_2.setText(defaulTextLabelSort);
		labelSort_2.setEnabled(false);
		
		Vector<Object> cModel = new Vector<Object>();
		cModel.addElement(new Item(0, defaulTextSelectItem));
		selectSort_3.setModel(new DefaultComboBoxModel<Object>(cModel));
		selectSort_3.setEnabled(false);
		labelSort_3.setText(defaulTextLabelSort);
		labelSort_3.setEnabled(false);
	}
	
	////------
	@SuppressWarnings("unchecked")
	private void setSelectsForFull() {
		SubjectDAO subjectDAO = new SubjectDAO();
		List<Subject> subjects = subjectDAO.getAll();
		Vector<Object> aModel = new Vector<Object>();
		aModel.addElement(new Item(0, defaulTextSelectItem));
		for(Subject subject : subjects) {
			aModel.addElement(new Item(subject.getId(), subject.getName()));
		}
		selectSort_1.setModel(new DefaultComboBoxModel<Object>(aModel));
		selectSort_1.setEnabled(true);
		labelSort_1.setText("Курсы по предмету: ");
		labelSort_1.setEnabled(true);
		
		TeacherDAO teacherDAO = new TeacherDAO();
		List<Teacher> teachers = teacherDAO.getAll();
		Vector<Object> bModel = new Vector<Object>();
		bModel.addElement(new Item(0, defaulTextSelectItem));
		for(Teacher teacher : teachers) {
			bModel.addElement(new Item(teacher.getId(), teacher.getName()));
		}
		selectSort_2.setModel(new DefaultComboBoxModel<Object>(bModel));
		selectSort_2.setEnabled(true);
		labelSort_2.setText("Курсы по предователю: ");
		labelSort_2.setEnabled(true);
		
		CourseDAO courseDAO = new CourseDAO();
		List<Integer> yearsFroList = courseDAO.getYears();
		Vector<Object> cModel = new Vector<Object>();
		cModel.addElement(new Item(0, defaulTextSelectItem));
		for(Integer year: yearsFroList){
			cModel.addElement(new Item(year, year + "-" + (year + 1)));
		}
		selectSort_3.setModel(new DefaultComboBoxModel<Object>(cModel));
		selectSort_3.setEnabled(true);
		labelSort_3.setText("Курсы за год: ");
		labelSort_3.setEnabled(true);
		
		
	}
	
	private ValTable getFullList() {
		int inputSubject = ((Item)selectSort_1.getSelectedItem()).toInteger();
		int inputTeacher = ((Item)selectSort_2.getSelectedItem()).toInteger();
		int inputYear = ((Item)selectSort_3.getSelectedItem()).toInteger();
		
		ValTable table = new ValTable();
		ValRow head = new ValRow();		
		head.set("Преподователь");
		head.set("Предмет");
		head.set("Год");
		head.set("Четверть");
		head.set("Группы");
		head.set("Студентов");
		head.set("Лекции");
		head.set("Лабораторные");
		head.set("Практические");
		head.set("Консультации");
		head.set("Семинары");
		head.set("Контроль");
		head.set("Курсовые");
		head.set("Всего часов");
		head.set("Кредитов");
		
		ValRows body = new ValRows();
		TeacherDAO teacherDAO = new TeacherDAO();
		SubjectDAO subjectDAO = new SubjectDAO();
		CourseDAO courseDAO = new CourseDAO();
		CourseInfoDAO courseInfoDAO = new CourseInfoDAO();
		List<Teacher> teachers = teacherDAO.getAll();
		if(teachers.size() > 0) {
			for(Teacher teacher : teachers) {
				boolean isAddTeacher = false;
				if(inputTeacher != 0 && inputTeacher != teacher.getId()) continue;
				// if sort by Teacher is true 
				List<Course> courses = courseDAO.getByTeacher(teacher.getId());
				String teacherName = teacher.getName();
				if(courses.size() > 0) {
					for(Course course : courses) {
						if(inputYear != 0 && inputYear != course.getYear()) continue;
						// if sort by Year is true 
						Subject subject = subjectDAO.get(course.getSubject());
						if(inputSubject != 0 && inputSubject != subject.getId()) continue;
						// if sort by Subject is true 
						String SubjectName = " ";
						if(subject != null) {
							SubjectName = subject.getName();
						}
						
						List<CourseInfo> courseInfos = courseInfoDAO.getByCourse(course.getId());
						if(courseInfos.size() > 0) {
							for(CourseInfo courseInfo : courseInfos) {
								XMLSettings settings;
								int count_coursovy_hours = 0;
								try {
									settings = new XMLSettings();
									int koef_cursovy = 0;
									switch (courseInfo.getCoursovy()) {
										case 1:
											koef_cursovy = settings.getVariableInt("coursovy_job");
											break;
											
										case 2:
											koef_cursovy = settings.getVariableInt("coursovy_project");
											break;
									}
									count_coursovy_hours = course.getAllCountUsers() * koef_cursovy ;
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								ValRow row = new ValRow();
								row.set(teacherName);
								row.set(SubjectName);
								row.set(course.getYear() + "-" + (course.getYear() + 1));
								row.set(courseInfo.getPath_of_stady());
								row.set(course.getGroups());
								row.set(course.getAllCountUsers());
								row.set(courseInfo.getLectures_hours());
								row.set(course.getLabsHour(courseInfo.getLabs_hours()));
								row.set(courseInfo.getPractics_hours());
								row.set(courseInfo.getConsultation_hours());
								row.set(courseInfo.getSeminar_hours());
								row.set(courseInfo.getControl_hours());
								row.set(count_coursovy_hours);
								row.set(course.getHours());
								row.set(courseInfo.getCredits());
								//--------
								body.set(row);
								teacherName = " ";
								SubjectName = " ";
								isAddTeacher = true;
							}
						}
					}
				}
				if(isAddTeacher)
					body.set(new ValRow());
			}
		}
		
		List<Course> courses = courseDAO.getByTeacher(0);
		if(inputTeacher == 0 && courses.size() > 0) {
			for(Course course : courses) {
				if(inputYear != 0 && inputYear != course.getYear()) continue;
				// if sort by Year is true 
				Subject subject = subjectDAO.get(course.getSubject());
				if(inputSubject != 0 && inputSubject != subject.getId()) continue;
				// if sort by Subject is true 
				String SubjectName = " ";
				if(subject != null) {
					SubjectName = subject.getName();
				}
				
				List<CourseInfo> courseInfos = courseInfoDAO.getByCourse(course.getId());
				if(courseInfos.size() > 0) {
					for(CourseInfo courseInfo : courseInfos) {
						XMLSettings settings;
						int count_coursovy_hours = 0;
						try {
							settings = new XMLSettings();
							int koef_cursovy = 0;
							switch (courseInfo.getCoursovy()) {
								case 1:
									koef_cursovy = settings.getVariableInt("coursovy_job");
									break;
									
								case 2:
									koef_cursovy = settings.getVariableInt("coursovy_project");
									break;
							}
							count_coursovy_hours = course.getAllCountUsers() * koef_cursovy ;
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						ValRow row = new ValRow();
						row.set(" ");
						row.set(SubjectName);
						row.set(course.getYear() + "-" + (course.getYear() + 1));
						row.set(courseInfo.getPath_of_stady());
						row.set(course.getGroups());
						row.set(course.getAllCountUsers());
						row.set(courseInfo.getLectures_hours());
						row.set(course.getLabsHour(courseInfo.getLabs_hours()));
						row.set(courseInfo.getPractics_hours());
						row.set(courseInfo.getConsultation_hours());
						row.set(courseInfo.getSeminar_hours());
						row.set(courseInfo.getControl_hours());
						row.set(count_coursovy_hours);
						row.set(course.getHours());
						row.set(courseInfo.getCredits());
						//--------
						body.set(row);
					}
				}
			}
		}
		
		table.setHead(head);
		table.setBody(body);
		
		return table;
	}
	
	////------
	private void setSelectsForTeacherWithSubject() {
		setSelectsEmpty();
	}
	
	private ValTable getListTeacherWithSubject() {
		ValTable table = new ValTable();
		ValRow head = new ValRow();		
		ValRows body = new ValRows();

		
		table.setHead(head);
		table.setBody(body);
		return table;
	}
	
	////------
	@SuppressWarnings("unchecked")
	private void setSelectsForTeacherWithCourses() {
		setSelectsEmpty();
		CourseDAO courseDAO = new CourseDAO();
		List<Integer> yearsFroList = courseDAO.getYears();
		Vector<Object> aModel = new Vector<Object>();
		aModel.addElement(new Item(0, defaulTextSelectItem));
		for(Integer year: yearsFroList) {
			aModel.addElement(new Item(year, year + "-" + (year + 1)));
		}
		selectSort_1.setModel(new DefaultComboBoxModel<Object>(aModel));
		selectSort_1.setEnabled(true);
		labelSort_1.setText("Курсы за год: ");
		labelSort_1.setEnabled(true);
	}
	
	private ValTable getListTeacherWithCourses() {
		ValTable table = new ValTable();
		ValRow head = new ValRow();		
		ValRows body = new ValRows();

		
		table.setHead(head);
		table.setBody(body);
		return table;
	}
	
	////------
	@SuppressWarnings("unchecked")
	private void setSelectsForCourses() {
		setSelectsEmpty();
		
		SubjectDAO subjectDAO = new SubjectDAO();
		List<Subject> subjects = subjectDAO.getAll();
		Vector<Object> aModel = new Vector<Object>();
		aModel.addElement(new Item(0, defaulTextSelectItem));
		for(Subject subject : subjects) {
			aModel.addElement(new Item(subject.getId(), subject.getName()));
		}
		selectSort_1.setModel(new DefaultComboBoxModel<Object>(aModel));
		selectSort_1.setEnabled(true);
		labelSort_1.setText("Курсы по предмету: ");
		labelSort_1.setEnabled(true);
		
		CourseDAO courseDAO = new CourseDAO();
		List<Integer> yearsFroList = courseDAO.getYears();
		Vector<Object> bModel = new Vector<Object>();
		bModel.addElement(new Item(0, defaulTextSelectItem));
		for(Integer year: yearsFroList) {
			bModel.addElement(new Item(year, year + "-" + (year + 1)));
		}
		selectSort_2.setModel(new DefaultComboBoxModel<Object>(bModel));
		selectSort_2.setEnabled(true);
		labelSort_2.setText("Курсы за год: ");
		labelSort_2.setEnabled(true);
		
		TeacherDAO teacherDAO = new TeacherDAO();
		List<Teacher> teachers = teacherDAO.getAll();
		Vector<Object> cModel = new Vector<Object>();
		cModel.addElement(new Item(0, defaulTextSelectItem));
		for(Teacher teacher : teachers) {
			cModel.addElement(new Item(teacher.getId(), teacher.getName()));
		}
		selectSort_3.setModel(new DefaultComboBoxModel<Object>(cModel));
		selectSort_3.setEnabled(true); 
		labelSort_3.setText("Курсы по предователю: ");
		labelSort_3.setEnabled(true);
	}
	
	private ValTable getListCourses() {
		ValTable rows = new ValTable();
		
		return rows;
	}
	
	
	
	
	
	
	public Main getFrame() {
		return frame;
	}
	public void setFrame(Main frame) {
		this.frame = frame;
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
