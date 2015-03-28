package view;

import init.Main;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import module.Users;
import dao.UsersDAO;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class showListUsers extends JPanel {


	private static final long serialVersionUID = -1082670606284528417L;
	private Main frame;
	private JList<Item> list;
	private JTextField inputLogin;
	private JButton btnSave;
	private JComboBox<Item> selectLevel; 
	private Users userActive;
	private JLabel inpetError;


	public showListUsers(Main pFrame) {
		setFrame(pFrame);
		
	}


	public Main getFrame() {
		return frame;
	}


	public void setFrame(Main frame) {
		this.frame = frame;
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		
		UsersDAO users_dao = new UsersDAO();
		List<Users> users_list = users_dao.getAll();
		int course_count = users_list.size();
		Vector<Item> model = new Vector<Item>();
        for(int j = 0; j < course_count; ++j) {
        	Users user = users_list.get(j);
        	if(!user.getName().equals("varel")) {
        		model.addElement(new Item(user.getId(), user.getName()));
        	}
		}
		
		list = new JList<Item>(model);
		list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                	int user = list.getSelectedValue().toInteger();
                	Users user_info = new Users();
                	if(user > 0) {
                		UsersDAO users_dao_ = new UsersDAO();
                		user_info = users_dao_.get(user);
                		btnSave.setEnabled(true);
                	} else {
                		btnSave.setEnabled(false);
                	}
                	userActive = user_info;
                	setValueInfo(user_info);
                }
            }
        });
		
		JLabel lblNewLabel = new JLabel("Логин: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label = new JLabel("Доступ: ");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		Vector<Item> model1 = new Vector<Item>();
		model1.addElement(new Item(0, "Доступ"));
		model1.addElement(new Item(1, "Пользователь"));
		model1.addElement(new Item(2, "Частичный доступ"));
		model1.addElement(new Item(3, "Редактор"));
		model1.addElement(new Item(4, "Администратор"));
		model1.addElement(new Item(5, "СуперАдмин"));
		selectLevel = new JComboBox<Item>(model1);
		if(frame.userLogin.getLevel() < 5) {
			selectLevel.setEnabled(false);
		}
		
		inputLogin = new JTextField();
		inputLogin.setColumns(10);
		
		btnSave = new JButton("Сохранить");
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int user = userActive.getId();
            	if(user > 0) {
            		inpetError.setText("");
            		UsersDAO users_dao_ = new UsersDAO();
            		String login = inputLogin.getText();
            		if(login.length() >= 3) {
            			if(login.equals(userActive.getName()) || !users_dao_.hasUser(login)) {
            				int level = selectLevel.getSelectedIndex();
            				if(level > 0) {
            					userActive.setName(login);
            					userActive.setLevel(level);
            					users_dao_.update(userActive);
            				} else {
            					inpetError.setText("Выберите права пользователя");
            				}
            			} else {
            				inpetError.setText("Этот логин нельзя использовать");
            			}
            		} else {
            			inpetError.setText("Логин слишком короткий");
            		}
            	}
			}
		});
		
		inpetError = new JLabel("");
		
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(inpetError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(inputLogin))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSave)
								.addComponent(selectLevel, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(selectLevel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnSave)
					.addGap(24)
					.addComponent(inpetError, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(117, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(list, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
					.addGap(10))
		);
		panel.setLayout(gl_panel);
	}
	
	private void setValueInfo(Users userInfo) {
		inputLogin.setText(userInfo.getName() != null ? userInfo.getName() : "");
		selectLevel.setSelectedIndex(userInfo.getLevel() > 0 ? userInfo.getLevel() : 0);
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
