
package multimoviedb;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.JCheckBox;

public class AddMovies extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtGenre;
	private JTextField txtDirector;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMovies frame = new AddMovies();
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
	
	
	public AddMovies() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 363, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(26, 45, 51, 12);
		contentPane.add(lblTitle);
		
		JLabel lblReleaseYear = new JLabel("Release Year");
		lblReleaseYear.setBounds(26, 70, 83, 12);
		contentPane.add(lblReleaseYear);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setBounds(26, 95, 51, 12);
		contentPane.add(lblGenre);
		
		JLabel lblDirector = new JLabel("Director");
		lblDirector.setBounds(26, 117, 51, 12);
		contentPane.add(lblDirector);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(110, 42, 96, 18);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		JComboBox cbYear = new JComboBox();
		cbYear.setBounds(110, 66, 96, 20);
		contentPane.add(cbYear);
		for(int i=1960; i<=2026;i++) {
			cbYear.addItem(i);
		}
		
		txtGenre = new JTextField();
		txtGenre.setBounds(110, 92, 96, 18);
		contentPane.add(txtGenre);
		txtGenre.setColumns(10);
		
		txtDirector = new JTextField();
		txtDirector.setBounds(110, 114, 96, 18);
		contentPane.add(txtDirector);
		txtDirector.setColumns(10);
		
		
		
		JLabel lblRating = new JLabel("Rating");
		lblRating.setBounds(26, 150, 44, 12);
		contentPane.add(lblRating);
		
		JSlider sliderRating = new JSlider();
		sliderRating.setMinorTickSpacing(1);
		sliderRating.setValue(0);
		sliderRating.setMajorTickSpacing(1);
		sliderRating.setMaximum(5);
		sliderRating.setPaintTicks(true);
		sliderRating.setPaintLabels(true);
		sliderRating.setBounds(110, 135, 109, 44);
		contentPane.add(sliderRating);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(26, 193, 44, 12);
		contentPane.add(lblUser);
		
		JCheckBox chbxFavourite = new JCheckBox("Favourite");
		chbxFavourite.setBounds(110, 217, 96, 20);
		contentPane.add(chbxFavourite);
		
		JComboBox cbUser = new JComboBox();
		cbUser.setBounds(110, 189, 109, 20);
		contentPane.add(cbUser);
		cbUser.addItem("Ahmet Fanaz");
		cbUser.addItem("Mushab Budak");
		cbUser.addItem("Yusuf Gungor");
		
		JButton btnAddCatalogue = new JButton("Add Movie to Catalogue");
		btnAddCatalogue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MovieRecord m = new MovieRecord();
				m.setTitle(txtTitle.getText());
				m.setReleaseYear(Integer.parseInt(cbYear.getSelectedItem().toString()));
				m.setGenre(txtGenre.getText());
				m.setDirector(txtDirector.getText());
				m.setRating(sliderRating.getValue());
				m.setFavourite(chbxFavourite.isSelected());
				m.setUserFullName(cbUser.getSelectedItem().toString());
				
				MovieDataContex ctx = new MovieDataContex();
				
				try {
					
					ctx.addMovie(m);
					txtTitle.setText("");
					cbYear.setSelectedIndex(0);
					txtGenre.setText("");
					txtDirector.setText("");
					sliderRating.setValue(0);
					chbxFavourite.setSelected(false);
					cbUser.setSelectedIndex(0);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
					// TODO: handle exception
				}
				
				
			}
		});
		

		btnAddCatalogue.setBounds(26, 252, 180, 20);
		contentPane.add(btnAddCatalogue);
		
		
	}
}
