package view;

import init.Main;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

import libs.XMLSettings;
import module.Course;
import module.CourseInfo;
import module.Dyplom;
import module.Practice;
import module.Teacher;
import dao.CourseDAO;
import dao.CourseInfoDAO;
import dao.DyplomDAO;
import dao.PracticeDAO;
import dao.TeacherDAO;

import javax.swing.JList;

public class CalculateValues extends JPanel {

	private static final long serialVersionUID = 6991588252632823799L;
	private Main frame;
	private int activeYear = 0;
	private int yearNow = 0;
	
	private JTextField inputFull;
	private JTextField inputLection;
	private JTextField inputPractic;
	private JTextField inputDyplom;
	private JComboBox<Item> comboBox;
	private JButton button;
	private JList<Object> list;

	public CalculateValues(Main pFrame) {
		setFrame(pFrame);
		int year = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
		int mount = Integer.parseInt((new SimpleDateFormat("MM")).format(new Date()));
		setYearNow(year - (mount < 7 ? 1 : 0 ));
		init();
	}

	private void init() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		
		JPanel panel_1 = new JPanel();
		
		JLabel label = new JLabel("Год: ");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		Vector<Item> model = new Vector<Item>();
		model.addElement(new Item(0, "Выберите год"));
		model.addElement(new Item(yearNow - 1, (yearNow - 1) + "-" + yearNow));
		model.addElement(new Item(yearNow, yearNow + "-" + (yearNow + 1)));
		model.addElement(new Item(yearNow + 1, (yearNow + 1) + "-" + (yearNow + 2)));
		comboBox = new JComboBox<Item>(model);
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		JComboBox<?> comboBox = (JComboBox<?>) e.getSource();
	                int id = ((Item) comboBox.getSelectedItem()).getId();
	                if(id > 0) {
	                	activeYear = id;
	                } else {
	                	activeYear = 0;
	                }
		    	} catch(Exception e1) {}
		    	
		    }
		});
		
		button = new JButton("Расчитать");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(activeYear > 0) {
					comboBox.setEnabled(false);
					button.setEnabled(false);
		    		setValuesCalculate(calculateValues());
		    		comboBox.setEnabled(true);
		    		button.setEnabled(true);
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(button)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel label_1 = new JLabel("Общая нагрузка за год: ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_2 = new JLabel("Лекционая нагрузка: ");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_3 = new JLabel("Нагрузка по практике: ");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_4 = new JLabel("Нагрузка по диплому: ");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_5 = new JLabel("Нагрузка на преподователя: ");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		
		inputFull = new JTextField();
		inputFull.setEnabled(false);
		inputFull.setColumns(10);
		
		inputLection = new JTextField();
		inputLection.setEnabled(false);
		inputLection.setColumns(10);
		
		inputPractic = new JTextField();
		inputPractic.setEnabled(false);
		inputPractic.setColumns(10);
		
		inputDyplom = new JTextField();
		inputDyplom.setEnabled(false);
		inputDyplom.setColumns(10);
		
		list = new JList<Object>();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(inputFull, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(inputLection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(inputPractic, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(inputDyplom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(32)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(108, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputFull, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputLection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputPractic, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputDyplom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(list, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
	}
	
	
	
	
	public Main getFrame() {
		return frame;
	}

	public void setFrame(Main frame) {
		this.frame = frame;
	}
	
	private Calculate calculateValues() {
		Calculate calc = new Calculate();
		if(activeYear > 0) {
			DyplomDAO duplom_dao = new DyplomDAO();
			Dyplom dyplom = duplom_dao.getByYear(activeYear);
			if(dyplom != null) {
				double dyplo = dyplom.getHours_full();
				dyplo = dyplo > 0 ? dyplo : 0;
				calc.setHoursDuplome((int)dyplo);
			}
			
			
			PracticeDAO practice_dao = new PracticeDAO();
			Practice practice = practice_dao.getByYear(activeYear);
			if(practice != null) {
				int pract = practice.getHours_full();
				pract = pract > 0 ? pract : 0;
				calc.setHoursPractice(pract);
			}
			
			List<Course> courses = (new CourseDAO()).getByYear(activeYear);
			int lection_hours = 0;
			if(courses.size() > 0) {
				for(Course course : courses) {
					CourseInfoDAO courseInfoDAO = new CourseInfoDAO();
					List<CourseInfo> coursesInfo = courseInfoDAO.getByCourse(course.getId());
					if(coursesInfo.size() > 0) {
						for(CourseInfo courseInfo : coursesInfo) {
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
								count_coursovy_hours = course.getAllCountUsers() + koef_cursovy ;
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							
							lection_hours += courseInfo.getLectures_hours() + course.getLabsHour(courseInfo.getLabs_hours())
									+ courseInfo.getPractics_hours() + courseInfo.getSeminar_hours()
									+ courseInfo.getConsultation_hours() + courseInfo.getControl_hours() + count_coursovy_hours;
						}
					}
				}
			}
			calc.setHoursLections(lection_hours);
		}
		return calc;
	}
	
	private void setValuesCalculate(Calculate calculate) {
		Vector<Object> model = new Vector<Object>();
		if(calculate.getHoursFull() > 0) {
			inputFull.setText("" + calculate.getHoursFull());
			inputDyplom.setText("" + calculate.getHoursDuplome());
			inputLection.setText("" + calculate.getHoursLections());
			inputPractic.setText("" + calculate.getHoursPractice());
			
			TeacherDAO teacher_dao = new TeacherDAO();
			List<Teacher> teacher_list = teacher_dao.getAll();
			double full_koef = 0;
			for(Teacher teacher : teacher_list) {
				full_koef += teacher.getKoef();
			}
			
			for(Teacher teacher : teacher_list) {
				double hours_teacher = (teacher.getKoef() * calculate.getHoursFull()) / full_koef;
				BigDecimal control_hours_d = new BigDecimal(Double.toString(hours_teacher));
				control_hours_d = control_hours_d.setScale(2, BigDecimal.ROUND_HALF_UP);
				model.addElement(new Item(
						teacher.getId(), 
						teacher.getName()+ " - " + control_hours_d.doubleValue() + " часов"));
			}
	        list.setListData(model);
	        
		} else {
			inputFull.setText("0");
			inputDyplom.setText("0");
			inputLection.setText("0");
			inputPractic.setText("0");
			list.setListData(model);
		}
	}
	
	
	
	public int getYearNow() {
		return yearNow;
	}

	public void setYearNow(int yearNow) {
		this.yearNow = yearNow;
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
	
	class Calculate {
		
		private int year = 0;
		private int hoursPractice = 0;
		private int hoursDuplome = 0;
		private int hoursLections = 0;
		

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public int getHoursPractice() {
			return hoursPractice;
		}

		public void setHoursPractice(int hoursPractice) {
			this.hoursPractice = hoursPractice;
		}

		public int getHoursDuplome() {
			return hoursDuplome;
		}

		public void setHoursDuplome(int hoursDuplome) {
			this.hoursDuplome = hoursDuplome;
		}

		public int getHoursFull() {
			return hoursDuplome + hoursPractice + hoursLections;
		}

		public int getHoursLections() {
			return hoursLections;
		}

		public void setHoursLections(int hoursLections) {
			this.hoursLections = hoursLections;
		}
	}
	
	
}
