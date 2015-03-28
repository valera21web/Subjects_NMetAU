package view;

import init.Main;

import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import module.Course;
import module.Subject;
import dao.CourseDAO;
import dao.SubjectDAO;
import dao.TeacherDAO;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.BorderLayout;

import javax.swing.JList;

public class showListSubject extends JPanel {

	private static final long serialVersionUID = 6754292479071004695L;
	private JTable tableCourse;
	private JList<Item> listSubgects;

	/**
	 * Create the panel.
	 */
	public showListSubject(Main pFrame) {
		init();
		events();
		reWriteTable();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void init() {
setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		//body = tmp_body;
		DefaultTableModel modelTable = new DefaultTableModel();
		modelTable.addColumn("Преподватель");
		modelTable.addColumn("Группы");
		modelTable.addColumn("Часы");
		modelTable.addColumn("Студентов");
		modelTable.addColumn("Семестры");
		modelTable.addColumn("Год");
		
		tableCourse = new JTable(modelTable);
		tableCourse.setEnabled(false);
		tableCourse.setRowSelectionAllowed(false);
		scrollPane.setViewportView(tableCourse);
		add(panel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
						.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		SubjectDAO subject_dao = new SubjectDAO();
		List<Subject> subject_list = subject_dao.getAll();
		int course_count = subject_list.size();
		
		Vector<Object> model = new Vector<Object>();
        for(int j = 0; j < course_count; ++j) {
			Subject subject = subject_list.get(j);
			CourseDAO course_dao = new CourseDAO();
    		List<Course> course_list = course_dao.getSybject(subject.getId());
			model.addElement(new Item(subject.getId(), subject.getName() + "(" + course_list.size() + ")"));
		}
		
		listSubgects = new JList(model);
		scrollPane_1.setViewportView(listSubgects);
		if(course_count > 0){ listSubgects.setSelectedIndex(0);}
		panel.setLayout(gl_panel);
	}
	
	private void events() {
		//listTeachers
		listSubgects.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                	reWriteTable();
                }
            }
        });
	}
	
	
	private void reWriteTable() {
		DefaultTableModel myTableModel = (DefaultTableModel) tableCourse.getModel();
		if (myTableModel.getRowCount() > 0) {
		    for (int i = myTableModel.getRowCount() - 1; i > -1; i--) {
		        myTableModel.removeRow(i);
		    }
		}
		
		int subject = listSubgects.getSelectedValue().toInteger();
		CourseDAO course_dao = new CourseDAO();
		List<Course> course_list = course_dao.getSybject(subject);
		int course_count = course_list.size();
		
    	for(int y = 0; y < course_count; ++y) {
    		Course course = course_list.get(y);
    		String pathStaty = 
    				(course.getStady_in_path_1() == 1 ? " 1" : "") + 
    				(course.getStady_in_path_2() == 1 ? " 2" : "") + 
    				(course.getStady_in_path_3() == 1 ? " 3" : "") + 
    				(course.getStady_in_path_4() == 1 ? " 4" : "")
    		;
    		String teach = (new TeacherDAO()).get(course.getTeacher()).getName();
    		myTableModel.addRow(new Object[] {
    				teach,
    				course.getGroups(),
    				""+course.getHours(),
    				course.getAllCountUsers(),
    				pathStaty,
    				course.getYear() + "-" + (course.getYear() + 1)
    		});
    	}
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
