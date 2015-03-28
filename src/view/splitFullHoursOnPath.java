package view;

import init.Main;

import javax.swing.JPanel;
import module.Course;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class splitFullHoursOnPath extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8003311216042614342L;
	private Main frame;
	private Course course;
	private Integer[] path_arr = null;
	private JTextField input_path_1;
	private JTextField input_path_2;
	private JTextField input_path_3;
	private JTextField input_path_4;
	private JButton button;
	private JTextField input_full_hours;

	/**
	 * Create the panel.
	 */
	public splitFullHoursOnPath(Main pFrame, Course pCourse, Integer[] pPath_arr) {
		course = pCourse;
		frame = pFrame;
		path_arr = pPath_arr;
		
		initComponents();
		afterInit();
		createdEventes();
	}
	
	private void initComponents() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("Распределение часов по четвертям");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("1-ая четверть: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_path_1 = new JTextField();
		input_path_1.setEnabled(false);
		input_path_1.setColumns(10);
		
		input_path_2 = new JTextField();
		input_path_2.setEnabled(false);
		input_path_2.setColumns(10);
		
		JLabel label_1 = new JLabel("2-ая четверть: ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_path_3 = new JTextField();
		input_path_3.setEnabled(false);
		input_path_3.setColumns(10);
		
		JLabel label_2 = new JLabel("3-ая четверть: ");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_path_4 = new JTextField();
		input_path_4.setEnabled(false);
		input_path_4.setColumns(10);
		
		JLabel label_3 = new JLabel("4-ая четверть: ");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		button = new JButton("Продолжить");
		
		JLabel lblNewLabel_1 = new JLabel("Всего часов: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		input_full_hours = new JTextField();
		input_full_hours.setEnabled(false);
		input_full_hours.setColumns(10);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(input_path_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(input_path_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(input_path_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(178)
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(input_path_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(input_full_hours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(88)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(65, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(input_full_hours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(input_path_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(input_path_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(input_path_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(input_path_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(38))
		);
		panel.setLayout(gl_panel);
	}
	
	@SuppressWarnings("deprecation")
	private void afterInit() {
		int full_hours = course.getHours();
		input_full_hours.setText(Integer.toString(full_hours));
		switch(path_arr.length) {
			case 2:
				int hour = full_hours / 2;
				for(int r = 0; r < 2; ++r) {
					switch(path_arr[r]) {
						case 1:
							input_path_1.setText(Integer.toString(hour));
							input_path_1.enable();
							break;
						case 2:
							input_path_2.setText(Integer.toString(hour));
							input_path_2.enable();
							break;
						case 3:
							input_path_3.setText(Integer.toString(hour));
							input_path_3.enable();
							break;
						case 4:
							input_path_4.setText(Integer.toString(hour));
							input_path_4.enable();
							break;
					}
				}
				break;
			
			case 3:
				int hour1 = 0;
				if(full_hours % 3 == 0) {
					hour1 = full_hours / 3;
				} else {
					hour1 = (int) (full_hours / 3) + (full_hours % 3);
				}
				
				for(int r = 0; r < 3; ++r) {
					switch(path_arr[r]) {
						case 1:
							input_path_1.setText(Integer.toString(hour1));
							input_path_1.enable();
							break;
						case 2:
							input_path_2.setText(Integer.toString(hour1));
							input_path_2.enable();
							break;
						case 3:
							input_path_3.setText(Integer.toString(hour1));
							input_path_3.enable();
							break;
						case 4:
							input_path_4.setText(Integer.toString(hour1));
							input_path_4.enable();
							break;
					}
					if(r == 0 && (full_hours % 3 != 0)) {
						hour1 -= (full_hours % 3);
					}
				}
				break;
				
			case 4:
				int hour2 = 0;
				if(full_hours % 4 == 0) {
					hour2 = full_hours / 4;
				} else {
					hour2 = (int) (full_hours / 4) + (full_hours % 4);
				}
				for(int r = 0; r < 4; ++r) {
					switch(path_arr[r]) {
						case 1:
							input_path_1.setText(Integer.toString(hour2));
							input_path_1.enable();
							break;
						case 2:
							input_path_2.setText(Integer.toString(hour2));
							input_path_2.enable();
							break;
						case 3:
							input_path_3.setText(Integer.toString(hour2));
							input_path_3.enable();
							break;
						case 4:
							input_path_4.setText(Integer.toString(hour2));
							input_path_4.enable();
							break;
					}
					if(r == 0 && (full_hours % 4 != 0)) {
						hour2 -= (full_hours % 4);
					}
				}
				break;
				
		}		
	}

	private void createdEventes() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer[] hours_by_path = new Integer[path_arr.length];
				
				for(int r = 0; r < path_arr.length; ++r) {
					switch(path_arr[r]) {
						case 1:
							hours_by_path[r] = Integer.parseInt(input_path_1.getText());
							break;
						case 2:
							hours_by_path[r] = Integer.parseInt(input_path_2.getText());
							break;
						case 3:
							hours_by_path[r] = Integer.parseInt(input_path_3.getText());
							break;
						case 4:
							hours_by_path[r] = Integer.parseInt(input_path_4.getText());
							break;
					}
				}
				
				int sum_hours = 0;
				for(int k = 0; k < path_arr.length; ++k) {
					sum_hours += hours_by_path[k];
				}
				if(sum_hours == course.getHours()) {
					frame.changePanel(new addInfoForCourseMultiPath(frame, course, path_arr, 0, hours_by_path));
				} else {
					System.out.print("ERROR hours");
				}
			}
		});
	}
}
