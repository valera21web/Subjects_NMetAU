package view;

import init.Main;
import java.util.List;
import java.util.Vector;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import module.Subject;
import dao.SubjectDAO;
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

public class editSubject extends JPanel {

	private static final long serialVersionUID = 6754292479004695L;
	private JList<Item> listSubjects;
	private JTextField inputName;
	private JButton button;
	private Subject subjectActive = null;
	private JLabel inputError;
	private JButton buttonDel;
	private Main frame;

	/**
	 * Create the panel.
	 */
	public editSubject(Main pFrame) {
		frame = pFrame;
		init();
		events();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void init() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		
		SubjectDAO subjects_dao = new SubjectDAO();
		List<Subject> subjects_list = subjects_dao.getAll();
		Vector<Object> model = new Vector<Object>();
        for(Subject subject : subjects_list) {
			model.addElement(new Item(subject.getId(), subject.getName()));
		}
		listSubjects = new JList(model); 
		listSubjects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if(model.size() > 0) { listSubjects.setSelectedIndex(0); }
		
		JLabel lblNewLabel = new JLabel("Название: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		inputName = new JTextField();
		inputName.setColumns(10);
		
		button = new JButton("Сохранить");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(subjectActive != null) {
					String name = inputName.getText();
					if(name.length() > 7) {
						subjectActive.setName(name);
						SubjectDAO dao = new SubjectDAO();
						dao.update(subjectActive);
					} else {
						inputError.setText("Ошибка");
					}
				}
			}
		});
		
		inputError = new JLabel("");
		inputError.setForeground(Color.BLACK);
		
		buttonDel = new JButton("Удалить");
		buttonDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubjectDAO dao = new SubjectDAO();
				dao.delete(subjectActive);
				frame.changePanel(new editSubject(frame), "редактировать предмет");
			}
		});
		buttonDel.setBackground(Color.RED);
		buttonDel.setForeground(Color.WHITE);
		buttonDel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(listSubjects, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(inputName, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(inputError, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addComponent(button))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(buttonDel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(62)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(buttonDel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(inputError, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(143, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(listSubjects, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
					.addGap(20))
		);
		panel.setLayout(gl_panel);
	}
	
	private void events() {
		//listTeachers
		listSubjects.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
            		int subject = listSubjects.getSelectedValue().toInteger();
            		if(subject > 0) {
            			SubjectDAO dao = new SubjectDAO();
            			subjectActive = dao.get(subject);
            		} else {
            			subjectActive = null;
            		}
            		setValues();
                }
            }
        });
	}
	
	private void setValues() {
		if(subjectActive != null) {
			inputName.setText(subjectActive.getName());
		} else {
			inputName.setText("");
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
