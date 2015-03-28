package init;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import module.Users;
import view.CalculateValues;
import view.Export;
import view.addTeacher;
import view.addTeacherForCourse;
import view.addUser;
import view.editCourse;
import view.editDyplom;
import view.editPractic;
import view.addCourse;
import view.addSubject;
import view.editSettings;
import view.editSubject;
import view.editTeacher;
import view.showListCourses;
import view.showListSubject;
import view.showListTeacher;
import view.showListUsers;


public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String applicationName = "Расчет нагрузки для кафедры ПМ и ЗМ";
	private JPanel contentPane;
	private JMenuItem mntmAbout;
	private JMenuItem mntmNewSubject;
	private static Main frame;
	private JMenuItem mntmReLogin;
	private JMenuItem mntmExport;
	private JMenuItem mntmNewCource;
	private JMenuItem mntmNewTeacherForCource;
	private JMenuItem mntmAddNewTeacher;
	private JMenuItem mntmShowListCourseByThis;
	private JMenuItem mntmShowListSubjects;
	private JMenuItem mntmShowListTeacher;
	private JMenuItem mntmEditTeacher;
	private JMenuItem mntmEditSubject;
	private JMenuItem mntmEditCourse;
	private JMenu mnFile;
	private JMenu menu_1;
	private JMenu menu_2;
	private JMenu menu_3;
	private JMenu menu_4;
	private JMenu menu_5;
	private JMenuItem mntmPractica;
	private JMenuItem mntmDyplom;
	private JMenuItem mntmNewUser;
	private JMenuItem mntmShowListUsers;
	public Users userLogin;
	private JMenuItem menuСalculation;
	private JMenuItem mntmEditSettings;


	/**
	 * Create the frame.
	 */
	public Main(Users pUser) {
        frame = this;
        this.userLogin = pUser;
		frame.setVisible(true);
		frame.setSize(1280, 960);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setTitle(this.applicationName);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setFont(new Font("Arial", Font.PLAIN, 14));
		initComponents();
		createdEvents();
	}
	
	static void addTree(File file, Collection<File> all) {
	    File[] children = file.listFiles();
	    if (children != null) {
	        for (File child : children) {
	            all.add(child);
	            addTree(child, all);
	        }
	    }
	}
	
	private void initComponents() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		if(this.userLogin.getLevel() >= 1) {
            this.mnFile = new JMenu("Главная");
			menuBar.add(this.mnFile);
			
			if(this.userLogin.getLevel() >= 3) {
                this.menu_1 = new JMenu("Добавить");
				menuBar.add(this.menu_1);
			}
			
			if(this.userLogin.getLevel() >= 4) {
                this.menu_2 = new JMenu("Редактировать");
				menuBar.add(this.menu_2);
			}
			
			if(this.userLogin.getLevel() >= 2) {
                this.menu_3 = new JMenu("Список");
				menuBar.add(this.menu_3);
			}
			
			if(this.userLogin.getLevel() >= 3) {
                this.menu_4 = new JMenu("Доп часы");
				menuBar.add(this.menu_4);
			}

            this.menu_5 = new JMenu("Помощь");
			menuBar.add(this.menu_5);
		}
		
		
		if(this.userLogin.getLevel() >= 1) {
			if(this.userLogin.getLevel() >= 3) {
                this.menuСalculation = new JMenuItem("Расчет нагрузки");
                this.mnFile.add(this.menuСalculation);
			}
            this.mntmExport = new JMenuItem("Экспорт в EXCEL");
            this.mnFile.add(this.mntmExport);
			
			if(this.userLogin.getLevel() >= 5) {
                this.mntmEditSettings = new JMenuItem("Настройки");
                this.mnFile.add(this.mntmEditSettings);
			}

            this.mntmReLogin = new JMenuItem("Выход");
            this.mnFile.add(this.mntmReLogin);
		}
		
		if(this.userLogin.getLevel() >= 3) {

            this.mntmNewSubject = new JMenuItem("Добавить предмет");
            this.menu_1.add(this.mntmNewSubject);

            this.mntmAddNewTeacher = new JMenuItem("Добавить преподователя");
            this.menu_1.add(this.mntmAddNewTeacher);

            this.mntmNewTeacherForCource = new JMenuItem("Добавить преподователя курсу");
            this.menu_1.add(this.mntmNewTeacherForCource);

            this.mntmNewCource = new JMenuItem("Добавить курс");
            this.menu_1.add(this.mntmNewCource);
			
			if(this.userLogin.getLevel() >= 5) {
                this.mntmNewUser = new JMenuItem("Добавить пользователя");
                this.menu_1.add(this.mntmNewUser);
			}
		}
		
		if(this.userLogin.getLevel() >= 4) {
            this.mntmEditSubject = new JMenuItem("Редактировать предмет");
            this.menu_2.add(this.mntmEditSubject);

            this.mntmEditTeacher = new JMenuItem("Редактировать преподователя");
            this.menu_2.add(this.mntmEditTeacher);

            this.mntmEditCourse = new JMenuItem("Редактировать курс");
            this.menu_2.add(this.mntmEditCourse);

            this.mntmShowListUsers = new JMenuItem("Список пользователей");
            this.menu_2.add(this.mntmShowListUsers);
			
		}
		
		if(this.userLogin.getLevel() >= 2) {
            this.mntmShowListSubjects = new JMenuItem("Список предметов");
            this.menu_3.add(this.mntmShowListSubjects);

            this.mntmShowListTeacher = new JMenuItem("Список преподователей");
            this.menu_3.add(this.mntmShowListTeacher);

            this.mntmShowListCourseByThis = new JMenuItem("Список курсов");
            this.menu_3.add(this.mntmShowListCourseByThis);
		}
		
		if(this.userLogin.getLevel() >= 3) {
            this.mntmPractica = new JMenuItem("Практика");
            this.menu_4.add(this.mntmPractica);

            this.mntmDyplom = new JMenuItem("Дипломы");
            this.menu_4.add(this.mntmDyplom);
		}

        this.mntmAbout = new JMenuItem("Информация");
        this.menu_5.add(this.mntmAbout);

        this.contentPane = new JPanel();
        this.contentPane.setBackground(SystemColor.inactiveCaption);
        this.contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 432, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 238, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void createdEvents() {

		if(this.userLogin.getLevel() >= 1) {
            this.mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showConfirmDialog(null, "Приложение создано для кафедры \n\"Покрытий, композиционных материалов и защиты металлов\". \n\n\n\nРазработчик Штувбейный Валерий\nEmail:valera21web@gmail.com", "Нагрузка на преподователя @valera21web", JOptionPane.PLAIN_MESSAGE);
				}
			});

            this.mntmReLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Login();
					frame.dispose();
					remove(frame);
				}
			});

            this.mntmExport.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					changePanel(new Export(frame), "");
				}
			});	
		}
		
		if(this.userLogin.getLevel() >= 2) {
            this.mntmShowListCourseByThis.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					changePanel(new showListCourses(frame, 0), "список курсов");
				}
			});

            this.mntmShowListSubjects.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					changePanel(new showListSubject(frame), "список предметов");
				}
			});

            this.mntmShowListTeacher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					changePanel(new showListTeacher(frame), "список преподователей");
				}
			});
		}
		
		if(this.userLogin.getLevel() >= 3) {
            this.menuСalculation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					changePanel(new CalculateValues(frame), "расчет нагрузки");
				}
			});

            this.mntmAddNewTeacher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					changePanel(new addTeacher(), "новый преподователь");
				}
			});

            this.mntmNewSubject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changePanel(new addSubject(), "новый предмет");
				}
			});

            this.mntmNewCource.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					changePanel(new addCourse(frame), "новый курс");
				}
			});

            this.mntmNewTeacherForCource.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					changePanel(new addTeacherForCourse(frame), "добавление преподователя курсу");
				}
			});

            this.mntmPractica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					changePanel(new editPractic(frame), "нагрузка по практикам");
				}
			});

            this.mntmDyplom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					changePanel(new editDyplom(frame), "нагрузка по дипломам");
				}
			});
		
		}
		
		if(this.userLogin.getLevel() >= 4) {
            this.mntmEditSubject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					changePanel(new editSubject(frame), "редактировать предмет");
				}
			});

            this.mntmEditTeacher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					changePanel(new editTeacher(frame), "редактировать преподователя");
				}
			});

            this.mntmEditCourse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					changePanel(new editCourse(frame), "редактировать курс");
				}
			});

            this.mntmShowListUsers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					changePanel(new showListUsers(frame), "спискок пользователей");
				}
			});
			
		}
		
		if(this.userLogin.getLevel() >= 5) {
            this.mntmNewUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					changePanel(new addUser(frame), "добавить пользователя");
				}
			});

            this.mntmEditSettings.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					changePanel(new editSettings(frame), "системные настройки");
				}
			});
		}		
	}
	
	public void changePanel(JPanel panel) {
        this.changePanel(panel, "");
	}
	
	public void changePanel(JPanel panel, String title) {
		frame.getContentPane().setLayout(new BorderLayout());
		getContentPane().removeAll();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		title = applicationName + (title.length() > 0 ? " - " + title : "");
		frame.setTitle(title);
		getContentPane().doLayout();
		update(getGraphics());
		panel.revalidate();
	}
	
}
