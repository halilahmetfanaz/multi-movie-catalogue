package multimoviedb;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class MainMoviePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel tableModel;
	
	public void fillTable(String user) throws SQLException {
		tableModel.setRowCount(0);
		
		MovieDataContex ctx = new MovieDataContex();
		
		for (MovieRecord m : ctx.getMovieDetails(user)) {
			tableModel.addRow(new Object[] {m.getId(), m.getTitle(), m.getReleaseYear(), m.getGenre(), m.getDirector(), m.getRating(), m.isFavourite()});
			
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMoviePage frame = new MainMoviePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MainMoviePage() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMovies = new JLabel("Movie Catalogue");
		lblMovies.setBounds(21, 20, 99, 18);
		contentPane.add(lblMovies);
		
		
		
		
		JButton btnAddMovie = new JButton("Add Movie");
		btnAddMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				AddMovies a = new AddMovies();
				a.setVisible(true);
			}
		});
		btnAddMovie.setBounds(261, 218, 110, 20);
		contentPane.add(btnAddMovie);
		
		JComboBox cbUser = new JComboBox();
		cbUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if(cbUser.getSelectedItem() != null) {
						fillTable(cbUser.getSelectedItem().toString());
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
					// TODO: handle exception
				}
			}
		});
		cbUser.setBounds(198, 19, 128, 20);
		contentPane.add(cbUser);
		cbUser.addItem("Ahmet Fanaz");
		cbUser.addItem("Mushab Budak");
		cbUser.addItem("Yusuf Gungor");
		
		JButton btnDeleteMovie = new JButton("Delete Movie");
		btnDeleteMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() ==-1) {
					JOptionPane.showMessageDialog(contentPane, "Chose a movie!");
					return;
				}
				
				try {
					
					MovieDataContex ctx = new MovieDataContex();
					int selectedRow = table.getSelectedRow();
					int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
					
					ctx.deleteMovie(id);
					
					fillTable(cbUser.getSelectedItem().toString());
					
				} catch (SQLException e1) {
					e1.printStackTrace();
					// TODO: handle exception
				}
		
			}});
		
		btnDeleteMovie.setBounds(21, 218, 110, 20);
		contentPane.add(btnDeleteMovie);
		
		JButton btnFavourite = new JButton("Show Favourites");
		btnFavourite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ShowFavourites af = new ShowFavourites();
				af.setVisible(true);
			}
		});
		btnFavourite.setBounds(381, 218, 117, 20);
		contentPane.add(btnFavourite);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 48, 477, 147);
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
		tableModel.addColumn("Favourite");

		
		JLabel lblRecentUser = new JLabel("User: ");
		lblRecentUser.setBounds(157, 23, 44, 12);
		contentPane.add(lblRecentUser);
		
		JButton btnEdit = new JButton("Edit Movie");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() ==-1) {
					JOptionPane.showMessageDialog(contentPane, "Choose a movie!");
					return;
				}
				
				int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				
				EditMovie em = new EditMovie(id);
				em.setVisible(true);
			}
		});
		btnEdit.setBounds(141, 218, 110, 20);
		contentPane.add(btnEdit);
		
		

	}
}
