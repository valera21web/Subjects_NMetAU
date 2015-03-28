package view;

import init.Main;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import dao.DyplomDAO;
import module.Dyplom;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.UIManager;
import java.awt.Font;

public class editDyplom extends JPanel {

	private static final long serialVersionUID = 1L;
	private Main frame;
	private Dyplom dyplom;
	private DyplomDAO dyplom_dao = new DyplomDAO();
	private int activeYear;
	private boolean newDyplom = false;
	private JButton btSave;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JLabel lblError;
	private JTextField input_full;
	private JLabel label_1;
	private JLabel lblMe;
	private JLabel lblMb;
	private JLabel label_2;
	private JLabel lbln;
	private JTextField input_4_me;
	private JTextField input_4_mv;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel label_6;
	private JLabel label_7;
	private JTextField input_5_me_job;
	private JTextField input_5_mv_job;
	private JTextField input_5_me_proect;
	private JTextField input_5_mv_proect;
	private JLabel label_5;
	private JLabel label_3;
	private JLabel label_4;
	private JTextField input_5_me;
	private JTextField input_5_mv;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel label_8;
	private JTextField input_4_mv_v;
	private JLabel label_9;
	private JTextField input_4_me_v;
	private JLabel label_10;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_8;
	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public editDyplom(Main pFrame) {
		int year = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
		int mount = Integer.parseInt((new SimpleDateFormat("MM")).format(new Date()));
		int pYear = year - (mount < 7 ? 1 : 0 );
		start(pFrame, pYear);
	}
	
	public editDyplom(Main pFrame, int pYear) {
		start(pFrame, pYear);
	}
	
	private void start(Main pFrame, int pYear) {
		activeYear = pYear;
		setBackground(UIManager.getColor("CheckBox.background"));
		setFrame(pFrame);
		initComponent();
		createEvent();
		setValuesPractice();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initComponent() {
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("CheckBox.background"));
		
		dyplom_dao = new DyplomDAO();
		Dyplom tmp = dyplom_dao.getByYear(activeYear);
		if(tmp == null) {
			newDyplom = true;
			tmp = new Dyplom();
			tmp.setYear(activeYear);
		}
		dyplom = tmp;
		
		List<Integer> years = dyplom_dao.getYears();
		Vector<Item> model = new Vector<Item>();
		int index_select = 0, t = 0;
		boolean yearNow = false;
		boolean yearNext = false;
		for(Integer year_i : years) {
			yearNow = year_i == activeYear ? true : yearNow;
			yearNext = year_i == activeYear + 1 ? true : yearNext;
			index_select = year_i == activeYear ? t : index_select;
    		model.addElement(new Item(year_i, year_i + "-" + (year_i + 1)));
    		++t;
		}
		if(!yearNow) {
			model.addElement(new Item(activeYear, (activeYear) + "-" + (activeYear + 1)));
			index_select = t;
			++t;
		}
			
		if(!yearNext) {
			model.addElement(new Item(activeYear + 1, (activeYear + 1) + "-" + (activeYear + 2)));
			++t;
		}
		
		panel_1 = new JPanel();
		
		panel_2 = new JPanel();
		
		panel_3 = new JPanel();
		
		panel_4 = new JPanel();
		
		panel_5 = new JPanel();
		panel_5.setBackground(Color.DARK_GRAY);
		
		panel_6 = new JPanel();
		panel_6.setBackground(Color.DARK_GRAY);
		
		panel_8 = new JPanel();
		panel_8.setBackground(Color.DARK_GRAY);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(10)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addGap(49))
		);
		
		label_8 = new JLabel("Заочное обучение");
		label_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		
		input_4_mv_v = new JTextField();
		input_4_mv_v.setColumns(10);
		
		label_9 = new JLabel("Бакалавров MB: ");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_4_me_v = new JTextField();
		input_4_me_v.setColumns(10);
		
		label_10 = new JLabel("Бакалавров ME: ");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(input_4_me_v, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(input_4_mv_v, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(94, Short.MAX_VALUE))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(158)
					.addComponent(label_8, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
					.addGap(128))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
							.addComponent(input_4_me_v, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(input_4_mv_v, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
		JLabel label_16 = new JLabel("Общее количество часов: ");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_16.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_full = new JTextField();
		input_full.setEnabled(false);
		input_full.setColumns(10);
		
		btSave = new JButton("Сохранить");
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(input_full, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(73)
					.addComponent(btSave)
					.addContainerGap(92, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(input_full, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btSave))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		label_1 = new JLabel("Дневное обучение");
		label_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblMe = new JLabel("Бакалавров ME: ");
		lblMe.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMe.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_4_me = new JTextField();
		input_4_me.setColumns(10);
		
		lblMb = new JLabel("Бакалавров MB: ");
		lblMb.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMb.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_4_mv = new JTextField();
		input_4_mv.setColumns(10);
		
		lbln = new JLabel("Дипломная робота");
		lbln.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbln.setHorizontalAlignment(SwingConstants.LEFT);
		
		label_2 = new JLabel("Специалисты ");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		label_6 = new JLabel("МЕ: ");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		
		label_7 = new JLabel("МВ: ");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_5_me_job = new JTextField();
		input_5_me_job.setColumns(10);
		
		input_5_mv_job = new JTextField();
		input_5_mv_job.setColumns(10);
		
		input_5_me_proect = new JTextField();
		input_5_me_proect.setColumns(10);
		
		input_5_mv_proect = new JTextField();
		input_5_mv_proect.setColumns(10);
		
		label_5 = new JLabel("Дипломный проект");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		
		label_3 = new JLabel("Магистров МЕ: ");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		label_4 = new JLabel("Магистров МВ: ");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_5_me = new JTextField();
		input_5_me.setColumns(10);
		
		input_5_mv = new JTextField();
		input_5_mv.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(label_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblMe, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addComponent(input_4_me, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
									.addComponent(input_5_me_job, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addComponent(input_5_me, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addComponent(input_5_mv_job, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGap(42)
									.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addComponent(input_5_me_proect, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
										.addComponent(input_5_mv, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
										.addComponent(input_5_mv_proect, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGap(25)
									.addComponent(lblMb, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(input_4_mv, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGap(29)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_2.createSequentialGroup()
											.addGap(1)
											.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
										.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbln, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addGap(58)
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(106)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 285, Short.MAX_VALUE)
					.addGap(84))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMe, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(input_4_me, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMb, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(input_4_mv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(input_5_me_proect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(input_5_mv_proect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(input_5_mv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbln, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(input_5_me_job, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(input_5_mv_job, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(input_5_me, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel label = new JLabel("Год: ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		comboBox = new JComboBox(model);
		comboBox.setSelectedIndex(index_select);
		
		lblError = new JLabel("");
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblError.setForeground(Color.RED);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(50)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addComponent(lblError, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblError, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(label)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 482, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(54, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	private void createEvent() {
		
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		JComboBox<?> comboBox = (JComboBox<?>) e.getSource();
	                Item item = (Item) comboBox.getSelectedItem();
	                activeYear = item.getId();
	                Dyplom tmp = dyplom_dao.getByYear(activeYear);
	        		if(tmp == null) {
	        			newDyplom = true;
	        			tmp = new Dyplom();
	        			tmp.setYear(activeYear);
	        		}
	        		dyplom = tmp;
		    		setValuesPractice();
		    	} catch(Exception e1) {}
		    	
		    }
		});
		
		
		btSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(valitForm()) {
					getUpdateValue();
					if(newDyplom) {
						dyplom_dao.save(dyplom);
						newDyplom = false;
					} else {
						dyplom_dao.update(dyplom);
					}
				} else {
					lblError.setText("Проверте данные");
				}
			}
		});
	}

	private void setValuesPractice() {
		setValue(input_4_me, dyplom.getHours_d_4_me());
		setValue(input_4_mv, dyplom.getHours_d_4_mv());
		setValue(input_4_me_v, dyplom.getHours_v_4_me());
		setValue(input_4_mv_v, dyplom.getHours_v_4_mv());
		setValue(input_5_me_job, dyplom.getHours_d_5_sp_job_me());
		setValue(input_5_mv_job, dyplom.getHours_d_5_sp_job_mv());
		setValue(input_5_me_proect, dyplom.getHours_d_5_sp_proect_me());
		setValue(input_5_mv_proect, dyplom.getHours_d_5_sp_proect_mv());
		setValue(input_5_me, dyplom.getHours_d_5_mag_me());
		setValue(input_5_mv, dyplom.getHours_d_5_mag_mv());
		setValue(input_full, dyplom.getHours_full());
	}
	
	private void getUpdateValue() {
		dyplom.setHours_d_4_me(getValue(input_4_me));
		dyplom.setHours_d_4_mv(getValue(input_4_mv));
		dyplom.setHours_v_4_me(getValue(input_4_me_v));
		dyplom.setHours_v_4_mv(getValue(input_4_mv_v));
		dyplom.setHours_d_5_sp_job_me(getValue(input_5_me_job));
		dyplom.setHours_d_5_sp_job_mv(getValue(input_5_mv_job));
		dyplom.setHours_d_5_sp_proect_me(getValue(input_5_me_proect));
		dyplom.setHours_d_5_sp_proect_mv(getValue(input_5_mv_proect));
		dyplom.setHours_d_5_mag_me(getValue(input_5_me));
		dyplom.setHours_d_5_mag_mv(getValue(input_5_mv));
		dyplom.setHours_full();
	}
	
	private void setValue(JTextField input, double value) {
		value = value > 0 ? value : 0;
		input.setText(""+value);
	}
	
	@SuppressWarnings("unused")
	private void setValue(JTextField input, int value) {
		value = value > 0 ? value : 0;
		input.setText(""+value);
	}
	
	
	private double getValue(JTextField input) {
		double value = 0;
		try {
			value = Double.parseDouble(input.getText());
		} catch(Exception e3) {}
		return value > 0 ? value : 0;
	}
	
	private boolean valitInpit(JTextField input) {
		return getValue(input) > 0 ? true : false;
	}
	
	private boolean valitForm() {
		boolean result = false;
		try {
			if(
					valitInpit(input_4_me) && valitInpit(input_4_mv)  
					&& valitInpit(input_4_me_v) && valitInpit(input_4_mv_v)
					&& valitInpit(input_5_me_job) && valitInpit(input_5_mv_job)
					&& valitInpit(input_5_me_proect) && valitInpit(input_5_mv_proect)
					&& valitInpit(input_5_me) && valitInpit(input_5_mv)
				) {
				result = true;
			}
		} catch(Exception e2) {}
		return result;
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
    }
}
