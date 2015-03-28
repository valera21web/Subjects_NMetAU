package view;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;

import dao.TeacherDAO;
import module.Teacher;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addTeacher extends JPanel {

	private static final long serialVersionUID = -1503486521394849552L;
	private JTextField textField;
	private JButton button;
	private JTextField inputKoef;


	public addTeacher() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ф.И.О. преподователя: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(0, 72, 156, 24);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("Добавить нового преподователя");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(101, 11, 239, 26);
		panel.add(label);
		
		button = new JButton("Добавить");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Teacher teacher = new Teacher();
				TeacherDAO teacherDAO = new TeacherDAO();
				String teacher_name = textField.getText();
				if(teacher_name.length() > 7) {
					String kf = inputKoef.getText();
					kf = kf.replace(",", ".");
					double koef = Double.parseDouble(kf);
					if(koef > 0) {
						teacher.setName(teacher_name);
						teacher.setKoef(koef);
						teacherDAO.save(teacher);
						textField.setText("");
						JOptionPane.showConfirmDialog(null, "Преподователь сохранен", "Сохранение", JOptionPane.PLAIN_MESSAGE);
					} else {
						JOptionPane.showConfirmDialog(null, "Некоректный коефициент преподователя[>0]", "Ошибка", JOptionPane.PLAIN_MESSAGE);
					}
				} else {
					JOptionPane.showConfirmDialog(null, "Короткое имя преподователя[>7]", "Ошибка", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		button.setBounds(266, 129, 110, 23);
		panel.add(button);
		
		textField = new JTextField();
		textField.setBounds(157, 74, 219, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		inputKoef = new JTextField("1");
		inputKoef.setBounds(157, 107, 86, 20);
		panel.add(inputKoef);
		inputKoef.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Коефициент нагрузки: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 108, 137, 19);
		panel.add(lblNewLabel_1);

	}
}
