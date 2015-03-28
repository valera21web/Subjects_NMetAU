package init;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

import objects.ValTable;
public class PreView extends JFrame {

	private static final long serialVersionUID = -4969707291654391415L;
	private JPanel contentPane;
	private PreView framePreView;
	private JScrollPane scrollPane;
	private JTable table;


	/**
	 * Create the frame.
	 */
	public PreView(ValTable pPrintTable) {
		init(pPrintTable);
	}
	
	private void init(ValTable pPrintTable) {
        this.framePreView = this;
        this.framePreView.setVisible(true);
        this.framePreView.setSize(800, 600);
        this.framePreView.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		
		String[] head = pPrintTable.toArrayHead();
		String[][] body = pPrintTable.toArrayBody();

        this.scrollPane = new JScrollPane();
        this.contentPane.add(this.scrollPane, BorderLayout.CENTER);

        this.table = new JTable(body, head);
        this.scrollPane.setViewportView(table);
	}
}
