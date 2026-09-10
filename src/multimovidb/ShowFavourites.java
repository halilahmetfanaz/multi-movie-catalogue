package multimoviedb;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowFavourites extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel tableModel;
	
	public void fillTable(String user) throws SQLException {
		tableModel.setRowCount(0);
		
		MovieDataContex ctx = new MovieDataContex();
		
		for(MovieRecord m : ctx.getByFavourite(user)) {
			tableModel.addRow( new Object[] { m.getId(), m.getTitle(), m.getReleaseYear(), m.getGenre(), m.getDirector(), m.getRating()});
			
		}
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowFavourites frame = new ShowFavourites();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShowFavourites() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox cbUser = new JComboBox();
		cbUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cbUser.getSelectedItem() != null) {
					try {
						fillTable(cbUser.getSelectedItem().toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			}
		});
		cbUser.setBounds(20, 18, 104, 20);
		contentPane.add(cbUser);
		cbUser.addItem("Ahmet Fanaz");
		cbUser.addItem("Mushab Budak");
		cbUser.addItem("Yusuf Gungor");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 48, 339, 185);
		contentPane.add(scrollPane);
		
		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		tableModel.addColumn("ID");
		tableModel.addColumn("Title");
		tableModel.addColumn("Year");
		tableModel.addColumn("Genre");
		tableModel.addColumn("Director");
		tableModel.addColumn("Rating");
	}

}
