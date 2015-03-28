package view;

import init.Main;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;

import libs.Search;
import module.Course;
import module.Subject;
import module.Teacher;
import dao.CourseDAO;
import dao.SubjectDAO;
import dao.TeacherDAO;

public class addTeacherForCourse extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1526105327294198202L;
	private Main frame;
	private JComboBox<Item> selectListSubject;
	private JComboBox<Object> selectListYears;
	private JComboBox<Object> selectListCourse;
	private JComboBox<String> selectListTeacher;
	private JButton button;
	
	private int mSubject = 0;
	private int mYear = 0;
	private int mCourse = 0;
	private int mTeacher = 0;
	
	public addTeacherForCourse(Main pFrame) {
		setFrame(pFrame);
		initComponents();
		createdEvents();
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponents() { 
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		
		JLabel label = new JLabel("Предмет: ");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_1 = new JLabel("Год: ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_2 = new JLabel("Курс: ");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_3 = new JLabel("Преподователь: ");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_4 = new JLabel("Добавить преподователя курсу");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);

		
		Vector<Item> model = new Vector<Item>();
        model.addElement(new Item(0, "Выберите предмет"));
        SubjectDAO subject_dao = new SubjectDAO();
		List<Subject> subject_list = subject_dao.getAll();
		for(Subject course_now: subject_list) {
    		List<Course> course_list = (new CourseDAO()).getSybjectWithoutTeacher(course_now.getId());
    		model.addElement(new Item(course_now.getId(), course_now.getName() + "(" + course_list.size() + ")"));
		}
		selectListSubject = new JComboBox<Item>(model);
		selectListSubject.setSelectedIndex(0);
		
		model = new Vector<Item>();
	    model.addElement(new Item(0, "Выберите год обучения"));
		selectListYears = new JComboBox(model);
		selectListYears.setEnabled(false);
		
		model = new Vector<Item>();
	    model.addElement(new Item(0, "Выберите курс"));
		selectListCourse = new JComboBox(model);
		selectListCourse.setEnabled(false);
		
		model = new Vector<Item>();
        model.addElement(new Item(0, "Выберите преподователя"));
        TeacherDAO teacher_dao = new TeacherDAO();
		List<Teacher> teacher_list = teacher_dao.getAll();
		for(Teacher teacher: teacher_list) {
			model.addElement(new Item(teacher.getId(), teacher.getName()));
		}
		selectListTeacher = new JComboBox(model);
		selectListTeacher.setEnabled(false);
		
		button = new JButton("Добавть");
		button.setEnabled(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(69)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(14)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(selectListTeacher, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addComponent(selectListYears, 0, 141, Short.MAX_VALUE)
						.addComponent(label_4, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
						.addComponent(selectListSubject, 0, 176, Short.MAX_VALUE)
						.addComponent(selectListCourse, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(302, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(selectListSubject, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(selectListYears, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(selectListCourse, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(selectListTeacher, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(92))
		);
		panel.setLayout(gl_panel);
		add(panel);
	}
	
	private void createdEvents() { 
		
		selectListSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	JComboBox<?> comboBox = (JComboBox<?>)e.getSource();
                Item item = (Item)comboBox.getSelectedItem();
                mSubject = item.getId();
                selectListCourse.setEnabled(false);
        		selectListTeacher.setEnabled(false);
        		if(mSubject != 0) {
        			Vector<Object> model = new Vector<Object>();
                    model.addElement(new Item(0, "Выберите год обучения"));
                    
                    CourseDAO course_dao = new CourseDAO();
            		List<Course> course_list = course_dao.getByTeacher(0);
            		int course_count = course_list.size();
            		int j;
            		List<Integer> years = new ArrayList<Integer>();
            		 
            		for(j = 0; j < course_count; ++j) {
            			Course course_now = course_list.get(j);
            			int yaer = course_now.getYear();
            			if(mSubject == course_now.getSubject() && !Search.InList(yaer, years)) {
            				years.add(yaer);
            				model.addElement(new Item(yaer, yaer + " - " + (yaer + 1)));
            			}
            		}
            		selectListYears.setModel(new DefaultComboBoxModel<Object>(model));
            		selectListYears.setSelectedIndex(0);
            		selectListYears.setEnabled(true);
        		} else {
        			selectListYears.setEnabled(false);
        			selectListYears.setSelectedIndex(0);
        			selectListTeacher.setSelectedIndex(0);
        		}
	        }
	    });
		
		selectListYears.addActionListener(new ActionListener(){ 
	        public void actionPerformed(ActionEvent e) {
	        	JComboBox<?> comboBox = (JComboBox<?>)e.getSource();
                Item item = (Item)comboBox.getSelectedItem();
                mYear = item.getId();
                if(mYear != 0) {
                	Vector<Object> model = new Vector<Object>();
                    model.addElement(new Item(0, "Выберите курс"));
                    
                    CourseDAO course_dao = new CourseDAO();
            		List<Course> course_list = course_dao.getByTeacher(0);
            		int course_count = course_list.size();
            		int j;
            		
            		for(j = 0; j < course_count; ++j) {
            			Course course_now = course_list.get(j);
            			if(mSubject == course_now.getSubject() && mYear == course_now.getYear()) {
            				model.addElement(new Item(course_now.getId(), course_now.getGroups()));
            			}
            		}
            		selectListCourse.setModel(new DefaultComboBoxModel<Object>(model));
            		selectListCourse.setSelectedIndex(0);
            		selectListCourse.setEnabled(true);
                } else {
                	selectListCourse.setEnabled(false);
                	selectListCourse.setSelectedIndex(0);
                	selectListTeacher.setEnabled(false);
                	button.setEnabled(false);
                }
	        }
	    });
		
		selectListCourse.addActionListener(new ActionListener(){ 
	        public void actionPerformed(ActionEvent e) {
	        	JComboBox<?> comboBox = (JComboBox<?>)e.getSource();
	        	if(comboBox != null) {
	        		Item item = (Item)comboBox.getSelectedItem();
	        		mCourse = item.getId();
	        		if(mCourse == 0) {
	        			selectListTeacher.setEnabled(false);
	        		} else {
	        			selectListTeacher.setEnabled(true);
	        		}
	        	}
	        }
	    });
		
		
		selectListTeacher.addActionListener(new ActionListener(){ 
	        public void actionPerformed(ActionEvent e) {
	        	JComboBox<?> comboBox = (JComboBox<?>)e.getSource();
                Item item = (Item)comboBox.getSelectedItem();
                mTeacher = item.getId();
                if(mTeacher == 0) {
                	button.setEnabled(false);
                } else {
                	button.setEnabled(true);
                }
	        }
	    });
		
		button.addActionListener(new ActionListener(){ 
	        public void actionPerformed(ActionEvent e) {
	        	if(mTeacher != 0 && mCourse != 0) {
	        		CourseDAO course_dao = new CourseDAO();
	        		Course pCourse = course_dao.get(mCourse);
	        		pCourse.setTeacher(mTeacher);
	        		course_dao.update(pCourse);
	        		frame.changePanel(new addTeacherForCourse(frame));
	        	}
            }
        });
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
