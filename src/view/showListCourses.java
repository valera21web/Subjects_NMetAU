package view;

import init.Main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

import libs.XMLSettings;
import module.Course;
import module.CourseInfo;
import module.Subject;
import dao.CourseDAO;
import dao.CourseInfoDAO;
import dao.SubjectDAO;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JComboBox;

public class showListCourses extends JPanel {

	private static final long serialVersionUID = 6754292479071004695L;
	private Main frame;
	private int left;
	private int result_year;
	private JTable table_1;
	private String[][] body_1;
	private String[][] body_2;
	private String[] header;
	private JTable table_2;
	private JComboBox<Item> yearsList;
	private JLabel label;

	/**
	 * Create the panel.
	 */
	public showListCourses(Main pFrame, int pLeft) {
		frame = pFrame;
		left = pLeft;
		
		try {
			initContent();
		} catch(Exception e) {
			JOptionPane.showConfirmDialog(null, e, "ERROR", JOptionPane.PLAIN_MESSAGE);
		}
		
		try {
			initEvents();
		} catch(Exception e) {
			JOptionPane.showConfirmDialog(null, e, "ERROR2", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private void initContent() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		header = new String[] {
				"Учебный год",
				"Дисциплина",
				"Кредиты",
				"Курс",
				"Колл. студентов",
				"Группы",
				"Колл. потоков",
				"Лекций",
				"Консультации",
				"Лабораторные",
				"Практические",
				"Семинары",
				"Контроль",
				"Курсовые",
				"Всего"
		};
		
		SubjectDAO subject_dao = new SubjectDAO();
		CourseDAO course_dao = new CourseDAO();
		int year = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
		int mount = Integer.parseInt((new SimpleDateFormat("MM")).format(new Date()));
		result_year = 0;
		if(left == 0) {
			result_year = year - (mount >= 7 ? 0 : 1);
		} else if(left > 0){
			result_year = year + (mount >= 7 ? 1 : 0) + (left - 1);
		} else if(left < 0) {
			result_year = year - (mount >= 7 ? 1 : 2) - (left + 1);
		}
		List<Course> course_list = course_dao.getAll("WHERE year = " + Integer.toString(result_year));
		int course_count = course_list.size();
		
		CourseInfoDAO course_info_dao = new CourseInfoDAO();
		Map<String, Integer> save_result = new HashMap<String, Integer>();
		
		int j;
		int index;
		int count_students;
		int count_stream;
		int labs_hours;
		String years_groups = "";
		body_1 = new String[0][];
		body_2 = new String[0][];
		for(int ij = 0; ij < 2;++ij) {
			save_result.put("credits", 0);
			save_result.put("count_student", 0);
			save_result.put("count_stream", 0);
			save_result.put("lectures_hours", 0);
			save_result.put("consultation_hours", 0);
			save_result.put("labs_hours", 0);
			save_result.put("practics_hours", 0);
			save_result.put("seminar_hours", 0);
			save_result.put("control_hours", 0);
			save_result.put("coursovy", 0);
			save_result.put("full", 0);
			
			index = 0;
			count_students = 0;
			count_stream = 0;
			labs_hours = 0;
			String[][] body_tmp = new String[course_count+1][];
			String where = "";
			if(ij == 0) {
				where = " AND (path_of_stady = 1 OR path_of_stady = 2)";
			} else if(ij == 1){
				where = " AND (path_of_stady = 3 OR path_of_stady = 4)";
			}
			
			for(j = 0; j < course_count; ++j) {
				Course course_now = course_list.get(j);
				String subject_name = "";
				Subject sb = subject_dao.get(course_now.getSubject());
				if(sb != null) {
					subject_name = sb.getName();
				}
				List<CourseInfo> course_info_list = course_info_dao.getAll("where course_id = " + course_now.getId() + where);
				if(course_info_list.isEmpty()) {
					int tmp_count_array = body_tmp.length;
					String[][] _body = body_tmp;
					body_tmp = new String[tmp_count_array-1][];

					for(int t = 0; t < tmp_count_array - 1; ++t) {
						body_tmp[t] = _body[t];
					}
					continue;
				}
				CourseInfo course_info_result = new CourseInfo();
				try {
					course_info_result = CourseInfo.plus(course_info_result, course_info_list);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showConfirmDialog(null, e, "course_info_result", JOptionPane.PLAIN_MESSAGE);
				}
				
				try {
					count_students = course_now.getAllCountUsers();		
				} catch (Exception e) {
					JOptionPane.showConfirmDialog(null, e, "course_info_result 179", JOptionPane.PLAIN_MESSAGE);
				}
				
				try {
					count_stream = course_now.getCountStream();				
				} catch (Exception e) {
					JOptionPane.showConfirmDialog(null, e, "course_info_result 185", JOptionPane.PLAIN_MESSAGE);
				}
				
				try {
					labs_hours = course_now.getLabsHour(course_info_result.getLabs_hours());
				} catch (Exception e) {
					JOptionPane.showConfirmDialog(null, e, "course_info_result 191", JOptionPane.PLAIN_MESSAGE);
				}
				
				try {
					years_groups = Integer.toString(course_now.getYearGroup());
				} catch (Exception e) {
					JOptionPane.showConfirmDialog(null, e, "course_info_result 197", JOptionPane.PLAIN_MESSAGE);
				}
				
				XMLSettings settings;
				int count_coursovy_hours = 0;
				try {
					settings = new XMLSettings();
					int koef_cursovy = 0;
					switch (course_info_result.getCoursovy()) {
						case 1:
							koef_cursovy = settings.getVariableInt("coursovy_job");
							break;
							
						case 2:
							koef_cursovy = settings.getVariableInt("coursovy_project");
							break;
					}
					count_coursovy_hours = course_now.getAllCountUsers() * koef_cursovy ;
				} catch (Exception e) {
					JOptionPane.showConfirmDialog(null, e, "new XMLSettings() 198", JOptionPane.PLAIN_MESSAGE);
				}
				
				save_result.put("credits", save_result.get("credits") + course_info_result.getCredits());
				save_result.put("count_student", save_result.get("count_student") + count_students);
				save_result.put("count_stream", save_result.get("count_stream") + count_stream); // потоков
				save_result.put("lectures_hours", save_result.get("lectures_hours") + course_info_result.getLectures_hours());
				save_result.put("consultation_hours", save_result.get("consultation_hours") + course_info_result.getConsultation_hours());
				save_result.put("labs_hours", save_result.get("labs_hours") + labs_hours);
				save_result.put("practics_hours", save_result.get("practics_hours") + course_info_result.getPractics_hours());
				save_result.put("seminar_hours", save_result.get("seminar_hours") + course_info_result.getSeminar_hours());
				save_result.put("control_hours", save_result.get("control_hours") + course_info_result.getControl_hours());
				save_result.put("coursovy", save_result.get("coursovy") + count_coursovy_hours);
				save_result.put("full", save_result.get("full") + course_now.getHours());
				
				body_tmp[index] = new String[] {
					Integer.toString(course_now.getYear()) + " - " + Integer.toString(course_now.getYear() + 1),
					subject_name,
					Integer.toString(course_info_result.getCredits()),
					years_groups,
					Integer.toString(count_students),
					course_now.getGroups(),
					Integer.toString(count_stream),
					Integer.toString(course_info_result.getLectures_hours()),
					Integer.toString(course_info_result.getConsultation_hours()),
					Integer.toString(labs_hours),
					Integer.toString(course_info_result.getPractics_hours()),
					Integer.toString(course_info_result.getSeminar_hours()),
					Integer.toString(course_info_result.getControl_hours()),
					Integer.toString(count_coursovy_hours),
					Integer.toString(course_now.getHours())
				};
				++index;
			}
			body_tmp[index] = new String[] {
				"Всего",
				"",
				Integer.toString(save_result.get("credits")),
				"",
				"",
				"",
				Integer.toString(save_result.get("count_stream")),
				Integer.toString(save_result.get("lectures_hours")),
				Integer.toString(save_result.get("consultation_hours")),
				Integer.toString(save_result.get("labs_hours")),
				Integer.toString(save_result.get("practics_hours")),
				Integer.toString(save_result.get("seminar_hours")),
				Integer.toString(save_result.get("control_hours")),
				Integer.toString(save_result.get("coursovy")),
				Integer.toString(save_result.get("full"))
			};
			
			if(ij == 0) {
				body_1 = new String[body_tmp.length][];
				body_1 = body_tmp;
			} else if(ij == 1){
				body_2 = new String[body_tmp.length][];
				body_2 = body_tmp;
			}
		}
		
		table_1 = new JTable(body_1, header);
		table_1.setEnabled(false);
		table_1.setRowSelectionAllowed(false);
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		label = new JLabel("Первое полугодие");
		
		JLabel label_1 = new JLabel("Второе полугодие");
		
		JLabel label_2 = new JLabel("Год: ");
		label_2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		Vector<Item> model = new Vector<Item>();
		CourseDAO course_dao_years = new CourseDAO();
		List<Integer> years = course_dao_years.getYears();
		int index_select = 0, t = 0;
		for(Integer year_i : years) {
			index_select = year_i == result_year ? t : index_select; 
    		model.addElement(new Item(year_i, year_i + "-" + (year_i + 1)));
    		++t;
		}
		yearsList = new JComboBox<Item>(model);
		if(index_select > 0) 
			yearsList.setSelectedIndex(index_select);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(yearsList, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(140, Short.MAX_VALUE))
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addContainerGap(346, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_1)
					.addContainerGap(347, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(yearsList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
		);

		table_2 = new JTable(body_2, header);
		scrollPane_2.setViewportView(table_2);
		setLayout(groupLayout);
	}

	private void initEvents() {
		yearsList.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		JComboBox<?> comboBox = (JComboBox<?>) e.getSource();
	                Item item = (Item) comboBox.getSelectedItem();
	                int newYear = item.getId();
		    		int nowYear = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
		    		int nowMount = Integer.parseInt((new SimpleDateFormat("MM")).format(new Date()));
		    		int left = newYear - (nowYear - (nowMount < 7 ? 1 : 0));
		    	
		    		frame.changePanel(new showListCourses(frame, left));
		    	} catch(Exception e1) {
		    		JOptionPane.showConfirmDialog(null, e, "initEvents 344", JOptionPane.PLAIN_MESSAGE);
		    	}
		    	
		    }
		});
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
