package view;

import init.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import libs.Search;
import module.Course;
import module.Subject;
import module.Teacher;
import dao.CourseDAO;
import dao.SubjectDAO;
import dao.TeacherDAO;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;


public class showListTeacher extends JPanel {

	private static final long serialVersionUID = 6754292479071004695L;
	private JTable tableCourses;
	private JList<Item> listTeachers;
	private JButton buttonSort;
	private JComboBox<Item> comboBoxYers;
	private JCheckBox checkBox_1;
	private JCheckBox checkBox_2;
	private JCheckBox checkBox_3;
	private JCheckBox checkBox_4;
	private JComboBox<Item> comboBoxSubjects;

	/**
	 * Create the panel.
	 */
	public showListTeacher(Main pFrame) {
		init();
		showSortPanel(false);
		events();
		initSortPanel();
    	reWriteTable();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void init() {
		TeacherDAO teacher_dao = new TeacherDAO();
		List<Teacher> teacher_list = teacher_dao.getAll();
		int course_count = teacher_list.size();

		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		//body = tmp_body;
		DefaultTableModel modelTable = new DefaultTableModel();
		modelTable.addColumn("Предмет");
		modelTable.addColumn("Группы");
		modelTable.addColumn("Часы");
		modelTable.addColumn("Студентов");
		modelTable.addColumn("Семестры");
		modelTable.addColumn("Год");
		
		tableCourses = new JTable(modelTable);
		tableCourses.setEnabled(false);
		tableCourses.setRowSelectionAllowed(false);
		scrollPane.setViewportView(tableCourses);
		add(panel);
		
		Vector<Object> model = new Vector<Object>();
        for(int j = 0; j < course_count; ++j) {
			Teacher teacher = teacher_list.get(j);
			CourseDAO course_dao = new CourseDAO();
    		List<Course> course_list = course_dao.getByTeacher(teacher.getId());
			model.addElement(new Item(teacher.getId(), teacher.getName()+ "[" + teacher.getKoef() + "]"  + "(" + course_list.size() + ")"));
		}
		listTeachers = new JList(model); 
		listTeachers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if(course_count > 0) { listTeachers.setSelectedIndex(0); }
		
		JPanel panel_1 = new JPanel();
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(listTeachers, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 682, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
						.addComponent(listTeachers, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
					.addGap(20))
		);
		
		JLabel label = new JLabel("Год: ");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		comboBoxYers = new JComboBox<Item>();
		JLabel label_1 = new JLabel("Предмет: ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		comboBoxSubjects = new JComboBox<Item>();
		JLabel label_2 = new JLabel("Четверть: ");
		checkBox_1 = new JCheckBox("1-ая ");
		checkBox_1.setSelected(true);
		checkBox_2 = new JCheckBox("2-ая ");
		checkBox_2.setSelected(true);
		checkBox_3 = new JCheckBox("3-ая ");
		checkBox_3.setSelected(true);
		checkBox_4 = new JCheckBox("4-ая");
		checkBox_4.setSelected(true);
		buttonSort = new JButton("Сортировка");
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(comboBoxSubjects, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
							.addGap(18))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(comboBoxYers, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addGap(9)))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(checkBox_1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(checkBox_2, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(checkBox_3, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(checkBox_4, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
						.addComponent(buttonSort, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
					.addGap(122))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxYers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkBox_1)
						.addComponent(checkBox_2)
						.addComponent(checkBox_3)
						.addComponent(checkBox_4)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxSubjects, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonSort))
					.addGap(7))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
	}
	
	private void events() {
		
		buttonSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSortPanel(false);
				int year = ((Item) comboBoxYers.getSelectedItem()).toInteger();
				int subjects = ((Item) comboBoxSubjects.getSelectedItem()).toInteger();
				reWriteTable(year, subjects, checkBox_1.isSelected(), checkBox_2.isSelected(), checkBox_3.isSelected(), checkBox_4.isSelected());
				showSortPanel(true);
			}
		});
		
		listTeachers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                	initSortPanel();
                	reWriteTable();
                }
            }
        });
	}
	
	private void reWriteTable() {
		reWriteTable(-1, -1, true, true, true, true);
	}
	
	private void reWriteTable(int pYear, int pSubject, 
			boolean path_1, boolean path_2, boolean path_3, boolean path_4) {
		
		DefaultTableModel myTableModel = (DefaultTableModel) tableCourses.getModel();
		if (myTableModel.getRowCount() > 0) {
		    for (int i = myTableModel.getRowCount() - 1; i > -1; i--) {
		        myTableModel.removeRow(i);
		    }
		}
		
		int teacher = listTeachers.getSelectedValue().toInteger();
		List<Course> course_list = (new CourseDAO()).getByTeacherYearSubject(teacher, pYear, pSubject);
		int course_count = course_list.size();
		
    	for(int y = 0; y < course_count; ++y) {
    		Course course = course_list.get(y);
    		if((path_1 && course.getStady_in_path_1() == 1) || (path_2 && course.getStady_in_path_2() == 1)
    				|| (path_3 && course.getStady_in_path_3() == 1) || (path_4 && course.getStady_in_path_4() == 1)) {
		  		String pathStaty = 
	    				(course.getStady_in_path_1() == 1 ? " 1" : "") + 
	    				(course.getStady_in_path_2() == 1 ? " 2" : "") + 
	    				(course.getStady_in_path_3() == 1 ? " 3" : "") + 
	    				(course.getStady_in_path_4() == 1 ? " 4" : "")
	    		;

		  		String subject = (new SubjectDAO()).get(course.getSubject()).getName();
	    		myTableModel.addRow(new Object[] {
	    				subject,
	    				course.getGroups(),
	    				""+course.getHours(),
	    				course.getAllCountUsers(),
	    				pathStaty,
	    				course.getYear() + "-" + (course.getYear() + 1)
	    		});
    		}
    	}
	}
	
	private void initSortPanel() {
		int teacher = listTeachers.getSelectedValue().toInteger();
		if(teacher > 0) {
			showSortPanel(true);
			List<Integer> yearsFroList = (new CourseDAO()).getYearsByTeacher(teacher);
			Vector<Item> aModel = new Vector<Item>();
			aModel.addElement(new Item(0, "Select year"));
			if(yearsFroList != null) {
				for(Integer year: yearsFroList) {
					aModel.addElement(new Item(year, year + "-" + (year + 1)));
				}
			}
			comboBoxYers.setModel(new DefaultComboBoxModel<Item>(aModel));
			/*
			 * 
			 */
			
			List<Course> listSublects_ = (new CourseDAO()).getSubjectsByTeacher(teacher);
			List<Integer> listSublects = new ArrayList<Integer>();
			for(Course t: listSublects_) {
				listSublects.add(t.getSubject());
			}
			List<Subject> subjects = (new SubjectDAO()).getAll();
			Vector<Item> bModel = new Vector<Item>();
			bModel.addElement(new Item(0, "Select subject"));
			if(subjects != null && listSublects != null) {
				for(Subject subject : subjects) {
					if(Search.InList(subject.getId(), listSublects)) {
						bModel.addElement(new Item(subject.getId(), subject.getName()));
					}
				}
			}
			comboBoxSubjects.setModel(new DefaultComboBoxModel<Item>(bModel));
			
		} else
			showSortPanel(false);		
	}
	
	private void showSortPanel(boolean show) {
		comboBoxYers.setEnabled(show);
		comboBoxSubjects.setEnabled(show);
		checkBox_1.setEnabled(show);
		checkBox_2.setEnabled(show);
		checkBox_3.setEnabled(show);
		checkBox_4.setEnabled(show);
		buttonSort.setEnabled(show);
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
