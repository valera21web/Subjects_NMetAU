package view;

import init.Main;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import module.Users;
import dao.UsersDAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.JComboBox;

public class addUser extends JPanel {


	private static final long serialVersionUID = -8705982844263315056L;
	private JTextField inputLogin;
	private JPasswordField inputPassword;
	private JPasswordField inputForwardPassword;
	private JLabel lblError;
	private JComboBox<Item> selectLevel;
	private Main frame;
	/**
	 * Create the panel.
	 */
	public addUser(Main pFrame)  {
		setFrame(pFrame);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("Добавить пользователя");
		
		JLabel label_1 = new JLabel("Логин: ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_2 = new JLabel("Пароль: ");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_3 = new JLabel("Повторить пароль: ");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		inputLogin = new JTextField();
		inputLogin.setColumns(10);
		
		JLabel label_4 = new JLabel("Логин только из англ символов");
		
		JLabel label_5 = new JLabel("Пароль должен быть не менее 8 символов");
		
		JButton btnNewButton = new JButton("Добавить");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String login = inputLogin.getText();
				String password = new String(inputPassword.getPassword());
				String passwordForward = new String(inputForwardPassword.getPassword());
				Integer level = ((Item) selectLevel.getSelectedItem()).toInteger();
				UsersDAO users_dao = new UsersDAO();
				if(login.length() >= 3) {
					if(!users_dao.hasUser(login)) {
						if(password.equals(passwordForward)) {
							if(password.length() >= 8) {
								if(level > 0) {
									Users user = new Users(login, password, level);
									users_dao.save(user);
									frame.changePanel(new addUser(frame));
									JOptionPane.showConfirmDialog(null, "Пользователь сохранен", "Сохранение", JOptionPane.PLAIN_MESSAGE);
								} else {
									JOptionPane.showConfirmDialog(null, "Не выбраны права даступа!", "Ошибка", JOptionPane.PLAIN_MESSAGE);
								}
							} else {
								JOptionPane.showConfirmDialog(null, "Пароль слишком короткий!", "Ошибка", JOptionPane.PLAIN_MESSAGE);
							}
						} else {
							JOptionPane.showConfirmDialog(null, "Пароли не совпадают", "Ошибка", JOptionPane.PLAIN_MESSAGE);
						}
					} else {
						JOptionPane.showConfirmDialog(null, "Такой пользователь существует!", "Ошибка", JOptionPane.PLAIN_MESSAGE);
					}
				} else {
					JOptionPane.showConfirmDialog(null, "Имя пользователя короткое!", "Ошибка", JOptionPane.PLAIN_MESSAGE);
				}				
			}
		});
		
		inputPassword = new JPasswordField();
		inputForwardPassword = new JPasswordField();
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_6 = new JLabel("Уровень доступа: ");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		Vector<Item> model = new Vector<Item>();
		model.addElement(new Item(0, "Доступ"));
		model.addElement(new Item(1, "Пользователь"));
		model.addElement(new Item(2, "Частичный доступ"));
		model.addElement(new Item(3, "Редактор"));
		model.addElement(new Item(4, "Администратор"));
		model.addElement(new Item(5, "СуперАдмин"));
		
		selectLevel = new JComboBox<Item>(model);
		selectLevel.setSelectedIndex(0);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(159)
							.addComponent(label))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(inputForwardPassword, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
								.addComponent(inputLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(inputPassword, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addComponent(selectLevel, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(54)
							.addComponent(lblError, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(154)
							.addComponent(btnNewButton)))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addComponent(label)
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputForwardPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(selectLevel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblError, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

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
