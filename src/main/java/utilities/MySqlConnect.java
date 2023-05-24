package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;

public class MySqlConnect {
    Connection conn = null;
    public static Connection ConnectDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/tvshowdb","root","");
           // JOptionPane.showMessageDialog(null, "ConnectionEstablished");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }
    public static ObservableList<TvShowList> getDataShows(){
        Connection conn = ConnectDb();
        ObservableList<TvShowList> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = conn.prepareStatement("select title, year, runtime, rating, genre, text from list");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new TvShowList(rs.getString("title"), rs.getString("year"),
                        rs.getString("runtime"), rs.getString("rating"), rs.getString("genre"), rs.getString("text")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public static ObservableList<TvShowList> getWantToWatchData(String user){
        Connection conn = ConnectDb();
        ObservableList<TvShowList> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = conn.prepareStatement("select title, year, runtime, rating, genre, text from want_to_watch where user = ?");
            ps.setString(1, user);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new TvShowList(rs.getString("title"), rs.getString("year"),
                        rs.getString("runtime"), rs.getString("rating"), rs.getString("genre"), rs.getString("text")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public static void insertUserInfo(String username, String password, String favoriteQuote) {
        Connection conn = ConnectDb();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO user_table (user, password, favQuote, profilePic) VALUES (?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, favoriteQuote);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "User information inserted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<UserNameList> getUserInfo() {
        Connection conn = ConnectDb();
        ObservableList<UserNameList> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT user, password, favQuote, profilePic FROM user_table");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new UserNameList(rs.getString("user"), rs.getString("password"), rs.getString("favQuote"), rs.getInt("profilePic")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    /*
    public void addToWantToWatch(String user, Integer MovieID){
        try {
            Connection conn = ConnectDb(); // Establish a connection to the database
            String MovieToSearch;
            // Create a PreparedStatement object
            PreparedStatement ps0 = conn.prepareStatement("SELECT title FROM list WHERE id = ?");
            ps0.setInt(1, MovieID);
            ResultSet rss = ps0.executeQuery();

            if (rss.next()) {
                // Retrieve the title value from the ResultSet
                MovieToSearch = rss.getString("title");
                String query = "SELECT title FROM your_table_name WHERE title = ?";

                // Create a PreparedStatement object
                PreparedStatement ps = conn.prepareStatement(query);

                // Set the parameter value for the ID
                ps.setString(1, MovieToSearch);

                // Execute the SELECT query
                ResultSet rs = ps.executeQuery();

                // Check if any result is returned
                boolean idExists = rs.next();

                if (idExists) {
                    System.out.println(MovieToSearch + "was already added!");
                } else {
                    System.out.println("ID does not exist in the table");

                    PreparedStatement ps3 = conn.prepareStatement("select title, year, runtime, rating, genre, text from list where id = ?");
                    ps3.setInt(1, MovieID);

                    ResultSet resultSet = ps3.executeQuery();
                    // Iterate over the result set
                    while (resultSet.next()) {
                        // Retrieve the column values from the result set
                        String rs_title = resultSet.getString("title");
                        String rs_year = resultSet.getString("year");
                        String rs_runtime = resultSet.getString("runtime");
                        String rs_rating = resultSet.getString("rating");
                        String rs_genre = resultSet.getString("genre");
                        String rs_text = resultSet.getString("text");// ...

                        String selectIdQuery = "SELECT LAST_INSERT_ID()";
                        Statement statement = conn.createStatement();
                        ResultSet rs2 = statement.executeQuery(selectIdQuery);
                        int lastInsertedId = 0;
                        if (rs2.next()) {
                            lastInsertedId = rs2.getInt(1);
                        }

                        // Define the SQL query to insert the row into the destination table
                        PreparedStatement ps2 = conn.prepareStatement("INSERT INTO want_to_watch (id, title, year, runtime, rating, genre, text, user) VALUES (?,?,?,?,?,?,?,?)");
                        // Execute the insert query
                        ps2.setInt(1, lastInsertedId+1);
                        ps2.setString(2,rs_title);
                        ps2.setString(3,rs_year);
                        ps2.setString(4,rs_runtime);
                        ps2.setString(5,rs_rating);
                        ps2.setString(6,rs_genre);
                        ps2.setString(7,rs_text);
                        ps2.setString(8,user);
                        ps2.executeUpdate();
                    }

                }

            }

                System.out.println("Row inserted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
}