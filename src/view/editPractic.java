package view;

import init.Main;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;

import dao.PracticeDAO;
import module.Practice;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.awt.Font;

public class editPractic extends JPanel {

	private static final long serialVersionUID = 1L;
	private Main frame;
	private Practice practice;
	private PracticeDAO practice_dao = new PracticeDAO();
	private int activeYear;
	private boolean newPractice = false;
	
	private JTextField input_1_me;
	private JTextField input_1_mv;
	private JTextField input_1_other;
	private JTextField input_3_me;
	private JTextField input_3_mv;
	private JTextField input_4_me;
	private JTextField input_4_mv;
	private JTextField input_5_me_s;
	private JTextField input_5_mv_s;
	private JTextField input_5_me_m;
	private JTextField input_5_mv_m;
	private JButton btSave;
	private JComboBox<Item> comboBox;
	private JLabel lblError;
	private JTextField input_full;
	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public editPractic(Main pFrame) {
		int year = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
		int mount = Integer.parseInt((new SimpleDateFormat("MM")).format(new Date()));
		int pYear = year - (mount < 7 ? 1 : 0 );
		start(pFrame, pYear);
	}
	
	public editPractic(Main pFrame, int pYear) {
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

	private void initComponent() {
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("CheckBox.background"));
		
		JLabel label = new JLabel("Год: ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		practice_dao = new PracticeDAO();
		Practice tmp = practice_dao.getByYear(activeYear);
		if(tmp == null) {
			newPractice = true;
			tmp = new Practice();
			tmp.setYear(activeYear);
		}
		practice = tmp;
		
		List<Integer> years = practice_dao.getYears();
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
			
		comboBox = new JComboBox<Item>(model);
		comboBox.setSelectedIndex(index_select);
		
		JLabel label_1 = new JLabel("1-ый курс");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_2 = new JLabel("МЕ(часов): ");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_1_me = new JTextField();
		input_1_me.setColumns(10);
		
		input_1_mv = new JTextField();
		input_1_mv.setColumns(10);
		
		JLabel label_3 = new JLabel("МВ(часов): ");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_1_other = new JTextField();
		input_1_other.setColumns(10);
		
		JLabel label_4 = new JLabel("Кол. других групп: ");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_5 = new JLabel("3-ый курс");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_6 = new JLabel("МЕ(часов): ");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_3_me = new JTextField();
		input_3_me.setEnabled(false);
		input_3_me.setColumns(10);
		
		JLabel label_7 = new JLabel("МВ(часов): ");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_3_mv = new JTextField();
		input_3_mv.setEnabled(false);
		input_3_mv.setColumns(10);
		
		JLabel label_8 = new JLabel("4-ый курс");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_9 = new JLabel("МЕ(студентов): ");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_4_me = new JTextField();
		input_4_me.setColumns(10);
		
		JLabel label_10 = new JLabel("МВ(студентов): ");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_4_mv = new JTextField();
		input_4_mv.setColumns(10);
		
		btSave = new JButton("Сохранить");
		
		JLabel label_11 = new JLabel("5-ый курс");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_12 = new JLabel("Специалист");
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_13 = new JLabel("Магистр");
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_14 = new JLabel("МЕ(студентов): ");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_14.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_15 = new JLabel("МВ(студентов): ");
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_15.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_5_me_s = new JTextField();
		input_5_me_s.setColumns(10);
		
		input_5_mv_s = new JTextField();
		input_5_mv_s.setColumns(10);
		
		input_5_me_m = new JTextField();
		input_5_me_m.setColumns(10);
		
		input_5_mv_m = new JTextField();
		input_5_mv_m.setColumns(10);
		
		lblError = new JLabel("");
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblError.setForeground(Color.RED);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_16 = new JLabel("Общее количество часов: ");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_16.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_full = new JTextField();
		input_full.setEnabled(false);
		input_full.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(33)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(label, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
													.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
													.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
													.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(label_14, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
										.addComponent(label_15, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(18)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(input_1_other, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
												.addComponent(input_1_me, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
												.addComponent(input_5_mv_s, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
												.addComponent(input_5_me_s, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
												.addComponent(input_3_me, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
												.addComponent(input_4_me, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_panel.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(input_full, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
											.addComponent(label_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(label_7, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(input_1_mv, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addComponent(input_3_mv, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addComponent(input_4_mv, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
								.addComponent(input_5_mv_m, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(input_5_me_m, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblError, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btSave)))
					.addContainerGap(48, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(173)
					.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addGap(83)
					.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(66, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addGap(14)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_2)
								.addComponent(input_1_me, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_3)
								.addComponent(input_1_mv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_4)
								.addComponent(input_1_other, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_6)
								.addComponent(input_3_me, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(input_3_mv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_7))
							.addGap(16)
							.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_9)
								.addComponent(input_4_me, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_10)
								.addComponent(input_4_mv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(34)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(38))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(input_5_me_s, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_14)
								.addComponent(input_5_me_m, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(input_5_mv_s, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_15)
								.addComponent(input_5_mv_m, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(31)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblError, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(btSave)
									.addComponent(input_full, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(54)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 520, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
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
	                Practice tmp = practice_dao.getByYear(activeYear);
	        		if(tmp == null) {
	        			newPractice = true;
	        			tmp = new Practice();
	        			tmp.setYear(activeYear);
	        		}
	        		practice = tmp;
		    		setValuesPractice();
		    	} catch(Exception e1) {}
		    	
		    }
		});
		
		
		btSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(valitForm()) {
					getUpdateValue();
					if(newPractice) {
						practice_dao.save(practice);
						newPractice = false;
					} else {
						practice_dao.update(practice);
					}
				} else {
					lblError.setText("Проверте данные");
				}
			}
		});
	}

	private void setValuesPractice() {
		setValue(input_1_me, practice.getHours_1_me());
		setValue(input_1_mv, practice.getHours_1_mv());
		setValue(input_1_other, practice.getHours_1_other());
		setValue(input_3_me, practice.getHours_3_me());
		setValue(input_3_mv, practice.getHours_3_mv());
		setValue(input_4_me, practice.getHours_4_me());
		setValue(input_4_mv, practice.getHours_4_mv());
		setValue(input_5_me_s, practice.getHours_5_me_s());
		setValue(input_5_mv_s, practice.getHours_5_mv_s());
		setValue(input_5_me_m, practice.getHours_5_me_m());
		setValue(input_5_mv_m, practice.getHours_5_mv_m());
		setValue(input_full, practice.getHours_full());
	}
	
	private void setValue(JTextField input, int value) {
		value = value > 0 ? value : 0;
		input.setText(""+ value);
	}
	
	private void getUpdateValue() {
		practice.setHours_1_me(getValue(input_1_me));
		practice.setHours_1_mv(getValue(input_1_mv));
		practice.setHours_1_other(getValue(input_1_other));
		practice.setHours_3_me(getValue(input_3_me));
		practice.setHours_3_me(getValue(input_3_mv));
		practice.setHours_4_me(getValue(input_4_me));
		practice.setHours_4_mv(getValue(input_4_mv));
		practice.setHours_5_me_s(getValue(input_5_me_s));
		practice.setHours_5_mv_s(getValue(input_5_mv_s));
		practice.setHours_5_me_m(getValue(input_5_me_m));
		practice.setHours_5_mv_m(getValue(input_5_mv_m));
		practice.setHours_full();
	}
	
	private int getValue(JTextField input) {
		int value = 0;
		try {
			value = Integer.parseInt(input.getText());
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
					valitInpit(input_1_me) && valitInpit(input_1_mv)  && valitInpit(input_1_other)
					&& valitInpit(input_3_me)  && valitInpit(input_3_mv)
					&& valitInpit(input_4_me)  && valitInpit(input_4_mv)
					&& valitInpit(input_5_me_s)  && valitInpit(input_5_mv_s)
					&& valitInpit(input_5_me_m)  && valitInpit(input_5_mv_m)
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
