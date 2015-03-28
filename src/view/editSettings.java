package view;

import init.Main;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.JButton;

import libs.XMLSettings;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class editSettings extends JPanel {

	private static final long serialVersionUID = 7014375881881018052L;
	private Main frame;
	private JTextField textFieldValue;
	private JLabel lblName;
	private JEditorPane editorDescription;
	private JList<Item> list;
	private String[][] settings_list;
	private XMLSettings settingsRead;
	private JButton btnNewButton;
	
	public editSettings(Main pFrame) {
		setFrame(pFrame);
		try { settingsRead = new XMLSettings();
		} catch (Exception e) { e.printStackTrace(); }
		initArray();
		init();
		events();
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void init() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		
		
		Vector<Object> model = new Vector<Object>();
        for(String settings[] : settings_list) {
			model.addElement(new Item(Integer.parseInt(settings[0]), settings[1]));
		}
		list = new JList(model);
		
		lblName = new JLabel("");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Значение: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldValue = new JTextField();
		textFieldValue.setColumns(10);
		
		editorDescription = new JEditorPane();
		editorDescription.setEditable(false);
		
		btnNewButton = new JButton("Сохранить");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.LEADING, gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(10)
								.addComponent(editorDescription, 0, 0, Short.MAX_VALUE))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textFieldValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnNewButton))))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(list, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(29)
							.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton))
							.addGap(18)
							.addComponent(editorDescription, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		
	}
	
	private void events() {
		list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
            		setValues(list.getSelectedValue().toInteger());
                }
            }
        });
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateSetting();
			}
		});
		
		textFieldValue.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) { 
					updateSetting();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {}
			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
	}
	
	private void updateSetting() {
		int id = 0;
		try{
			id = list.getSelectedValue().toInteger();
		} catch(Exception e) {
			JOptionPane.showConfirmDialog(null, "Вы не выбрали редактируемый параметр!!!", "Error", JOptionPane.PLAIN_MESSAGE);
		}
		if(id > 0) {
			String[] var = settings_list[id-1];
			String val = textFieldValue.getText().replaceAll(",", ".");
			if(val.matches("^([0-9]{1,}|[0-9]{1,}.[0-9]{1,})$")) {
				if(settingsRead.reWriteValue(var[2], var[3], val))  {
					JOptionPane.showConfirmDialog(null, "Переменая сохранена удачно.", "Изменение параметров", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showConfirmDialog(null, "Переменая не сохранена!", "Изменение параметров", JOptionPane.PLAIN_MESSAGE);
				}
			} else {
				JOptionPane.showConfirmDialog(null, "Переменая не сохранена!\r\nНе коректное новое значение.", "Изменение параметров", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	
	private void setValues(int id) {
		String[] var = settings_list[id-1];
		lblName.setText(var[1]);
		textFieldValue.setText(settingsRead.getVariable(var[2], var[3]));
		editorDescription.setText(var[4]);
	}
	
	
	private void initArray() {
		settings_list = new String[][] {
				{"1", "Консультации(Дневное)", "koef", "konsultation_d", "Часов консультация на одно студента дневного обучения"},
				{"2", "Консультации(Вечерние)", "koef", "konsultation_v", "Часов консультация на одно студента вечернего обучения"},
				{"3", "Консультации(Заочное)", "koef", "konsultation_z", "Часов консультация на одно студента заочного обучения"},
				{"4", "Контроль", "koef", "kontrol", "Коефициент для часов контроля"},
				{"5", "Курсовую роботу", "koef", "coursovy_job", "Коефициент часов на студента за курсовую роботу"},
				{"6", "Курсовой проэкт", "koef", "coursovy_project", "Коефициент часов на студента за курсовой проэкт"},
				{"7", "Диплом 4 курс(Дневное)", "koef", "dyplom_d_4_hours", "Часов на студента выполняющего дипломную роботу 4-ого курса дневного обучения"},
				{"8", "Диплом 4 курс(Вечернее)", "koef", "dyplom_v_4_hours", "Часов на студента выполняющего дипломную роботу 4-ого курса вечернего обучения"},
				{"9", "Диплом 5 курс специалист(Робота)", "koef", "dyplom_d_5_spec_hours_job", "Часов на студент выполняющего дипломную роботу 5-ого курса, специалиста"},
				{"10", "Диплом 5 курс специалист(Проект)", "koef", "dyplom_d_5_spec_hours_proect", "Часов на студент выполняющего дипломный проект 5-ого курса, специалиста"},
				{"11", "Диплом 5 курс магистр(МЕ)", "koef", "dyplom_d_5_mag_hours_me", "Часов на студент выполняющего дипломную роботу 5-ого курса, магистра МЕ"},
				{"12", "Диплом 5 курс магистр(МВ)", "koef", "dyplom_d_5_mag_hours_mv", "Часов на студент выполняющего дипломную роботу 5-ого курса, магистра МВ"},
				{"13", "Практика 1 курс", "koef", "practice_1_hoursOthersGroups", "Часов за проведение практики с другой кафедры первого курса, за группу"},
				{"14", "Практика 3 курс", "koef", "practice_3_hoursForGroups", "Часов за проведение практики на 3-ем курсе"},
				{"15", "Практика 4 курс", "koef", "practice_4_hoursForGroups", "Часов за проведение практики на 4-ом курсе за студента"},
				{"16", "Практика 5 курс специалист", "koef", "practice_5_hoursForGroupsSpecialist", "Часов за проведение практики на 5-ом курсе за студента специалиста"},
				{"17", "Практика 5 курс магистр(МЕ)", "koef", "practice_5_hoursForGroupsMVMagistr", "Часов за проведение практики на 5-ом курсе за студента магистра МЕ"},
				{"18", "Практика 5 курс магистр(МВ)", "koef", "practice_5_hoursForGroupsMEMagistr", "Часов за проведение практики на 5-ом курсе за студента магистра МВ"},
				{"19", "Часов на один кредит", "count", "hours_on_kredit", "Часов необходимых на один кредит из обещего количества часов на предмет"},
				{"20", "Аудиторных часов на кредит", "count", "hours_auditor_on_kredit", "Аудиторных часов на один кредит"},
				{"21", "Студентов на потоке", "count", "count_users_for_streem", "Максимальное количество студентов на одном потоке"},
				{"22", "Студентов для группы(лаб.)", "count", "count_users_for_labs", "Максимальное количество студентов в подгруппе для проведения лабораторных робот"},
			};
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
	
	public Main getFrame() {
		return frame;
	}

	public void setFrame(Main frame) {
		this.frame = frame;
	}
}
