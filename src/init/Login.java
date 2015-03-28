package init;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import org.hibernate.HibernateException;
import module.Users;
import dao.UsersDAO;

import java.awt.Color;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Login extends JFrame {

	private static final long serialVersionUID = 5930711625932262057L;
	private JPanel contentPane;
	private JTextField loginField;
	private JButton btnButtonSign;
	private JPasswordField passwordField;
	private static Login frameLogin;
	private JLabel infoError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		frameLogin = new Login();
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(new Runnable() {
		    public void run() {
		    	try {
		    		(new UsersDAO()).hasUser("_");
		    	} catch(HibernateException e) { 
		    		JOptionPane.showConfirmDialog(null, e, "UsersDAO ERROR", JOptionPane.PLAIN_MESSAGE);
		    	}
		    }
		});
		
		executorService.shutdown();
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		super("Вход в программу");
		frameLogin = this;
		frameLogin.setVisible(true);
		frameLogin.setResizable(false);
		frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLogin.setLocationRelativeTo(null);
		init();
		loginField.requestFocus();
		events();
	}

	private void init() {
		setBounds(100, 100, 450, 300);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

        this.loginField = new JTextField();
        this.loginField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Имя: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel label_1 = new JLabel("Пароль: ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);

        this.btnButtonSign = new JButton("Вход");

        this.passwordField = new JPasswordField();

        this.infoError = new JLabel("");
        this.infoError.setHorizontalAlignment(SwingConstants.RIGHT);
        this.infoError.setForeground(new Color(255, 0, 0));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane
			.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(67).addGroup(gl_contentPane
					.createParallelGroup(Alignment.TRAILING)
					.addComponent(infoError,GroupLayout.PREFERRED_SIZE,230,GroupLayout.PREFERRED_SIZE)
					.addComponent(btnButtonSign,GroupLayout.PREFERRED_SIZE,89,GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel,GroupLayout.PREFERRED_SIZE,79,GroupLayout.PREFERRED_SIZE)
												.addComponent(label_1,GroupLayout.PREFERRED_SIZE,83,GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING,false)
											.addComponent(passwordField,Alignment.TRAILING,GroupLayout.DEFAULT_SIZE,150,Short.MAX_VALUE)
											.addComponent(
													loginField,
													Alignment.TRAILING,
													GroupLayout.PREFERRED_SIZE,
													150,
													GroupLayout.PREFERRED_SIZE))))
			.addContainerGap(130, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(44)
										.addComponent(infoError,
												GroupLayout.PREFERRED_SIZE, 28,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblNewLabel,
																GroupLayout.PREFERRED_SIZE,
																17,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																loginField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(14)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(label_1)
														.addComponent(
																passwordField,
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(btnButtonSign,
												GroupLayout.PREFERRED_SIZE, 20,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(98, Short.MAX_VALUE)));
        this.contentPane.setLayout(gl_contentPane);
	}

	private void events() {
        this.btnButtonSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});

        this.loginField.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					login();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});

        this.passwordField.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					login();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
	}

	private void login() {
		try {
			
			UsersDAO user_dao = new UsersDAO();

			if (user_dao.isLogin(this.loginField.getText(),
					new String(this.passwordField.getPassword()))) {
				Users user_login = user_dao.getUserByName(loginField.getText());
				new Main(user_login);
                this.frameLogin.dispose();
				remove(this.frameLogin);
			} else {
				JOptionPane.showConfirmDialog(null, "Пользователь не найден", "Вход", JOptionPane.PLAIN_MESSAGE);
			}
			
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Невозможно подключится к базе данных", "Ошибка", JOptionPane.PLAIN_MESSAGE);
		}
	}
}
