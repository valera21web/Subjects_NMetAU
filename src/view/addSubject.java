package view;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import dao.SubjectDAO;
import module.Subject;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.LayoutStyle.ComponentPlacement;

public class addSubject extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField input_name;
	private JButton btnSave;

	/**
	 * Create the panel.
	 */
	public addSubject() {
		initComponent();
		createEvent();
	}

	private void initComponent() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("\u0414\u043E\u0431\u0430\u0432\u043B\u0435\u043D\u0438\u0435 \u043D\u043E\u0432\u043E\u0433\u043E \u043F\u0440\u0435\u0434\u043C\u0435\u0442\u0430");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_1 = new JLabel("Название предмета: ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_name = new JTextField();
		input_name.setColumns(10);
		
		btnSave = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(138)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnSave)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(input_name, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(64, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(31)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(input_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addComponent(btnSave)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
	
	private void createEvent() {
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		input_name.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyChar() == KeyEvent.VK_ENTER){
                	save();
                }       
            }
			@Override public void keyReleased(KeyEvent arg0) {}
			@Override public void keyTyped(KeyEvent arg0) {}
		});
	}
	
	private void save() {
		Subject subj = new Subject();
		String name = input_name.getText();
		if(name.length() > 0) {
			btnSave.setEnabled(false);
			subj.setName(name);
			(new SubjectDAO()).save(subj);
			
			input_name.setText("");
			JOptionPane.showConfirmDialog(null, "Предмет сохранен", "Сохранение", JOptionPane.PLAIN_MESSAGE);
			btnSave.setEnabled(true);
		}
	}
}
