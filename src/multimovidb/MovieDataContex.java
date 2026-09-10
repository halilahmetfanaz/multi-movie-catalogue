package multimoviedb;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import multimoviedb.MovieRecord;

public class MovieDataContex {

	/**
	 * Connection details are read from db.properties (not committed to
	 * version control). Copy db.properties.example to db.properties and
	 * fill in your own local MySQL credentials before running the app.
	 */
	public Connection getConnected() throws SQLException {
		Properties props = new Properties();

		try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
			if (input == null) {
				throw new SQLException(
					"db.properties not found. Copy db.properties.example to db.properties "
					+ "and fill in your database credentials.");
			}
			props.load(input);
		} catch (IOException e) {
			throw new SQLException("Failed to read db.properties", e);
		}

		String url = props.getProperty("db.url");
		String user = props.getProperty("db.user");
		String password = props.getProperty("db.password");

		return DriverManager.getConnection(url, user, password);
	}

	public ArrayList<MovieRecord> getMovieDetails(String user) throws SQLException{
		ArrayList<MovieRecord> temp = new ArrayList<>();
		String query = "select * from user_movies where userFullname = ?";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setString(1, user);
		
		ResultSet rs  = ps.executeQuery();
		
		while(rs.next()) {
			MovieRecord record = new MovieRecord();
			
			record.setId(rs.getInt(1));
			record.setTitle(rs.getString(2));
			record.setReleaseYear(rs.getInt(3));
			record.setGenre(rs.getString(4));
			record.setDirector(rs.getString(5));
			record.setRating(rs.getInt(6));
			record.setFavourite(rs.getBoolean(7));
			record.setUserFullName(rs.getString(8));
			
			temp.add(record);
		}
		
		return temp;
		
	}
	
	public void addMovie(MovieRecord m) throws SQLException {
		String query = "insert into user_movies(title, releaseYear, genre, director, rating, favourite, userFullname) values (?,?,?,?,?,?,?)";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setString(1, m.getTitle());
		ps.setInt(2, m.getReleaseYear());
		ps.setString(3, m.getGenre());
		ps.setString(4, m.getDirector());
		ps.setInt(5, m.getRating());
		ps.setBoolean(6, m.isFavourite());
		ps.setString(7, m.getUserFullName());
		
		ps.executeUpdate();
		
	}
	
	public void deleteMovie(int id) throws SQLException {
		String query = "delete from user_movies where id =?";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();
		
	}
	
	public void updateMovie(int rating, boolean favourite, int id) throws SQLException {
		String query = "update user_movies set rating = ?, favourite = ? where id = ?";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setInt(1, rating);
		ps.setBoolean(2, favourite);
		ps.setInt(3, id);
		ps.executeUpdate();
	}
	
	public ArrayList<MovieRecord> getByFavourite(String user) throws SQLException{
		ArrayList<MovieRecord> temp = new ArrayList<>();
		String query = "select * from user_movies where userFullname = ? and favourite = 1";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setString(1, user);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			MovieRecord record = new MovieRecord();
			
			record.setId(rs.getInt(1));
			record.setTitle(rs.getString(2));
			record.setReleaseYear(rs.getInt(3));
			record.setGenre(rs.getString(4));
			record.setDirector(rs.getString(5));
			record.setRating(rs.getInt(6));
			record.setFavourite(rs.getBoolean(7));
			record.setUserFullName(rs.getString(8));
			
			temp.add(record);
		}
		return temp;
		
	}
	
	public MovieRecord getMovieByID(int id) throws SQLException {
		String query = "select * from user_movies where id = ?";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setInt(1, id);	
		
		ResultSet rs = ps.executeQuery();
		
		MovieRecord m = new MovieRecord();
		
		if(rs.next()) {
			m.setTitle(rs.getString(2));
			m.setRating(rs.getInt(6));
			m.setFavourite(rs.getBoolean(7));
			
		}
		
		return m;
	}
	
}
