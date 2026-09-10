package multimoviedb;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditMovie extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int movieID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditMovie frame = new EditMovie(0);
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
	public EditMovie(int id) {
		this.movieID = id;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 251, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MovieDataContex ctx = new MovieDataContex();
		MovieRecord movie = null;
		
		try {
			movie = ctx.getMovieByID(movieID);
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		JLabel lblMovieName = new JLabel("");
		lblMovieName.setBounds(33, 31, 148, 22);
		contentPane.add(lblMovieName);
		
		JSlider slider = new JSlider();
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(1);
		slider.setMaximum(5);
		slider.setBounds(10, 77, 200, 36);
		contentPane.add(slider);
		
		JCheckBox chbxFavourite = new JCheckBox("Favourite");
		chbxFavourite.setBounds(10, 146, 123, 30);
		contentPane.add(chbxFavourite);
		
		
		
		if(movie != null) {
			lblMovieName.setText(movie.getTitle());
			slider.setValue(movie.getRating());
			chbxFavourite.setSelected(movie.isFavourite());
		}
		
		JButton lblEdit = new JButton("Edit Movie");
		lblEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					MovieDataContex ctx = new MovieDataContex();
					
					ctx.updateMovie(slider.getValue(), chbxFavourite.isSelected(), id);
					
					dispose();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
					// TODO: handle exception
				}
				
			}
		});
		lblEdit.setBounds(33, 197, 148, 20);
		contentPane.add(lblEdit);
	}

}
