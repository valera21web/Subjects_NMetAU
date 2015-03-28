package view;

import init.Main;

import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;

import module.Teacher;
import dao.TeacherDAO;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class editTeacher extends JPanel {

	private static final long serialVersionUID = 6754292479071004695L;
	private JList<Item> listTeachers;
	private JTextField inputName;
	private JTextField inputKoef;
	private JButton button;
	private Teacher teacherActive = null;
	private JLabel inputError;
	private JButton buttonDel;
	private Main frame;

	/**
	 * Create the panel.
	 */
	public editTeacher(Main pFrame) {
		frame = pFrame;
		init();
		events();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void init() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		
		TeacherDAO teacher_dao = new TeacherDAO();
		List<Teacher> teacher_list = teacher_dao.getAll();
		Vector<Object> model = new Vector<Object>();
        for(Teacher teacher : teacher_list) {
			model.addElement(new Item(teacher.getId(), teacher.getName()));
		}
		listTeachers = new JList(model); 
		listTeachers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if(model.size() > 0) { listTeachers.setSelectedIndex(0);}
		
		JLabel lblNewLabel = new JLabel("ФИО: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label = new JLabel("Коефициент нагрузки: ");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		inputName = new JTextField();
		inputName.setColumns(10);
		
		inputKoef = new JTextField();
		inputKoef.setColumns(10);
		
		button = new JButton("Сохранить");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(teacherActive != null) {
					String name = inputName.getText();
					String kf = inputKoef.getText();
					kf.replace(",", ".");
					double koef = Double.parseDouble(kf);
					if(koef > 0 && name.length() > 7) {
						teacherActive.setKoef(koef);
						teacherActive.setName(name);
						TeacherDAO dao = new TeacherDAO();
						dao.update(teacherActive);
					} else {
						inputError.setText("Ошибка");
					}
				}
			}
		});
		
		inputError = new JLabel("");
		inputError.setForeground(Color.BLACK);
		
		buttonDel = new JButton("Удалить");
		buttonDel.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonDel.setForeground(Color.WHITE);
		buttonDel.setBackground(Color.RED);
		buttonDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherDAO dao = new TeacherDAO();
				dao.delete(teacherActive);
				frame.changePanel(new editTeacher(frame), "редактировать преподователя");
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(listTeachers, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(inputKoef, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addComponent(inputError, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(button)
								.addGap(18)
								.addComponent(buttonDel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(inputName, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputKoef, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonDel)
						.addComponent(button))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(inputError, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(113, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(listTeachers, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
					.addGap(20))
		);
		panel.setLayout(gl_panel);
	}
	
	private void events() {
		//listTeachers
		listTeachers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
	                int id = listTeachers.getSelectedValue().toInteger();
            		int teacher = id;
            		if(teacher > 0) {
            			TeacherDAO dao = new TeacherDAO();
            			teacherActive = dao.get(teacher);
            		} else {
            			teacherActive = null;
            		}
            		setValues();
                }
            }
        });
	}
	
	private void setValues() {
		if(teacherActive != null) {
			inputName.setText(teacherActive.getName());
			inputKoef.setText(""+teacherActive.getKoef());
		} else {
			inputName.setText("");
			inputKoef.setText("");
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
