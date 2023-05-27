package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;

public class MySqlConnect {
    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/tvshowdb","root","");
           // JOptionPane.showMessageDialog(null, "ConnectionEstablished");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public static ObservableList<TvShowList> getDataShows() {
        Connection conn = ConnectDb();
        ObservableList<TvShowList> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select title, year, runtime, rating, genre, text from list");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TvShowList(rs.getString("title"), rs.getString("year"),
                        rs.getString("runtime"), rs.getString("rating"), rs.getString("genre"), rs.getString("text")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<TvShowList> getWantToWatchData(String user) {
        Connection conn = ConnectDb();
        ObservableList<TvShowList> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select title, year, runtime, rating, genre, text from want_to_watch where user = ?");
            ps.setString(1, user);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TvShowList(rs.getString("title"), rs.getString("year"),
                        rs.getString("runtime"), rs.getString("rating"), rs.getString("genre"), rs.getString("text")));
            }
        } catch (Exception e) {
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

    public String addToWantToWatch(String user, String MovieToSearch) {
        try {
            Connection conn = ConnectDb(); // Establish a connection to the database

            String query = "SELECT * FROM want_to_watch WHERE title = ? and user= ?";

            // Create a PreparedStatement object
            PreparedStatement ps = conn.prepareStatement(query);

            // Set the parameter value for the ID
            ps.setString(1, MovieToSearch);
            ps.setString(2, user);


            // Execute the SELECT query
            ResultSet rs = ps.executeQuery();

            // Check if any result is returned
            boolean idExists = rs.next();

            if (idExists) {
                return MovieToSearch +  " was already added!";
            } else {

                PreparedStatement ps3 = conn.prepareStatement("select * from list where title = ?");
                ps3.setString(1, MovieToSearch);

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

                    String selectIdQuery = "SELECT MAX(id) FROM want_to_watch";
                    Statement statement = conn.createStatement();
                    ResultSet rs2 = statement.executeQuery(selectIdQuery);
                    int lastInsertedId = 0;
                    if (rs2.next()) {
                        lastInsertedId = rs2.getInt(1);
                    }

                    // Define the SQL query to insert the row into the destination table
                    PreparedStatement ps2 = conn.prepareStatement("INSERT INTO want_to_watch (id, title, year, runtime, rating, genre, text, user) VALUES (?,?,?,?,?,?,?,?)");
                    // Execute the insert query
                    ps2.setInt(1, lastInsertedId + 1);
                    ps2.setString(2, rs_title);
                    ps2.setString(3, rs_year);
                    ps2.setString(4, rs_runtime);
                    ps2.setString(5, rs_rating);
                    ps2.setString(6, rs_genre);
                    ps2.setString(7, rs_text);
                    ps2.setString(8, user);
                    ps2.executeUpdate();
                }

            }

            return MovieToSearch + " added!";
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }

    public String getActiveSession() {
        Connection conn = ConnectDb();
        try {
            PreparedStatement ps = conn.prepareStatement("Select username from active_session");
            ResultSet resultSet = ps.executeQuery();
            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve the column values from the result set
                return resultSet.getString("username");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setActiveSession(String username) {
        Connection conn = ConnectDb();
        String query = "TRUNCATE TABLE active_session";

        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO active_session (id, username) VALUES (?, ?)");
            ps.setInt(1, 1);
            ps.setString(2, username);
            ps.executeUpdate();
            //JOptionPane.showMessageDialog(null, "User information inserted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getProfilePic(String username){
        Connection conn = ConnectDb();
        try {
            PreparedStatement ps = conn.prepareStatement("Select profilePic from user_table where user = ?");
            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();
            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve the column values from the result set
                return resultSet.getInt("profilePic");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
    public String getFavouriteQuote(String username){
        Connection conn = ConnectDb();
        try {
            PreparedStatement ps = conn.prepareStatement("Select favQuote from user_table where user = ?");
            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();
            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve the column values from the result set
                return resultSet.getString("favQuote");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "too cool for quotes";
    }
    public static ObservableList<TvShowList> getWatchedData(String user) {
        Connection conn = ConnectDb();
        ObservableList<TvShowList> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select title, year, runtime, rating, genre, text from watched where user = ?");
            ps.setString(1, user);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TvShowList(rs.getString("title"), rs.getString("year"),
                        rs.getString("runtime"), rs.getString("rating"), rs.getString("genre"), rs.getString("text")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public String addToWatched(String user, String MovieToSearch) {
        try {
            Connection conn = ConnectDb(); // Establish a connection to the database

            String query = "SELECT * FROM watched WHERE title = ? and user= ?";

            // Create a PreparedStatement object
            PreparedStatement ps = conn.prepareStatement(query);

            // Set the parameter value for the ID
            ps.setString(1, MovieToSearch);
            ps.setString(2, user);


            // Execute the SELECT query
            ResultSet rs = ps.executeQuery();

            // Check if any result is returned
            boolean idExists = rs.next();

            if (idExists) {
                return MovieToSearch +  " was already added!";
            } else {

                PreparedStatement ps3 = conn.prepareStatement("select * from list where title = ?");
                ps3.setString(1, MovieToSearch);

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

                    String selectIdQuery = "SELECT MAX(id) FROM watched";
                    Statement statement = conn.createStatement();
                    ResultSet rs2 = statement.executeQuery(selectIdQuery);
                    int lastInsertedId = 0;
                    if (rs2.next()) {
                        lastInsertedId = rs2.getInt(1);
                    }

                    // Define the SQL query to insert the row into the destination table
                    PreparedStatement ps2 = conn.prepareStatement("INSERT INTO watched (id, title, year, runtime, rating, genre, text, user) VALUES (?,?,?,?,?,?,?,?)");
                    // Execute the insert query
                    ps2.setInt(1, lastInsertedId + 1);
                    ps2.setString(2, rs_title);
                    ps2.setString(3, rs_year);
                    ps2.setString(4, rs_runtime);
                    ps2.setString(5, rs_rating);
                    ps2.setString(6, rs_genre);
                    ps2.setString(7, rs_text);
                    ps2.setString(8, user);
                    ps2.executeUpdate();
                }

            }

            return MovieToSearch + " added!";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String addCustomTvShowToWatched(String user, String customTitle, String customYear, String customRuntime, String customRating, String customGenre, String customDescription){
        try {
            Connection conn = ConnectDb(); // Establish a connection to the database

            if(customTitle.equals(""))
                return "The title Field can not be null!";

            String query = "SELECT * FROM watched WHERE title = ? and user= ?";

            // Create a PreparedStatement object
            PreparedStatement ps = conn.prepareStatement(query);

            // Set the parameter value for the ID
            ps.setString(1, customTitle);
            ps.setString(2, user);
            // Execute the SELECT query
            ResultSet rs = ps.executeQuery();

            // Check if any result is returned
            boolean idExists = rs.next();

            if (idExists) {
                return customTitle +  " is already in list!";
            } else {

                    String selectIdQuery = "SELECT MAX(id) FROM watched";
                    Statement statement = conn.createStatement();
                    ResultSet rs2 = statement.executeQuery(selectIdQuery);
                    int lastInsertedId = 0;
                    if (rs2.next()) {
                        lastInsertedId = rs2.getInt(1);
                    }

                    // Define the SQL query to insert the row into the destination table
                    PreparedStatement ps2 = conn.prepareStatement("INSERT INTO watched (id, title, year, runtime, rating, genre, text, user) VALUES (?,?,?,?,?,?,?,?)");
                    // Execute the insert query
                    ps2.setInt(1, lastInsertedId + 1);
                    ps2.setString(2, customTitle);
                    ps2.setString(3, customYear);
                    ps2.setString(4, customRuntime);
                    ps2.setString(5, customRating);
                    ps2.setString(6, customGenre);
                    ps2.setString(7, customDescription);
                    ps2.setString(8, user);
                    ps2.executeUpdate();
                }
            return customTitle + " added!";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String addCustomTvShowToWantToWatch(String user, String customTitle, String customYear, String customRuntime, String customRating, String customGenre, String customDescription){
        try {
            Connection conn = ConnectDb(); // Establish a connection to the database

            if(customTitle.equals(""))
                return "The title Field can not be null!";

            String query = "SELECT * FROM want_to_watch WHERE title = ? and user= ?";

            // Create a PreparedStatement object
            PreparedStatement ps = conn.prepareStatement(query);

            // Set the parameter value for the ID
            ps.setString(1, customTitle);
            ps.setString(2, user);
            // Execute the SELECT query
            ResultSet rs = ps.executeQuery();

            // Check if any result is returned
            boolean idExists = rs.next();

            if (idExists) {
                return customTitle +  " is already in list!";
            } else {

                String selectIdQuery = "SELECT MAX(id) FROM want_to_watch";
                Statement statement = conn.createStatement();
                ResultSet rs2 = statement.executeQuery(selectIdQuery);
                int lastInsertedId = 0;
                if (rs2.next()) {
                    lastInsertedId = rs2.getInt(1);
                }

                // Define the SQL query to insert the row into the destination table
                PreparedStatement ps2 = conn.prepareStatement("INSERT INTO want_to_watch (id, title, year, runtime, rating, genre, text, user) VALUES (?,?,?,?,?,?,?,?)");
                // Execute the insert query
                ps2.setInt(1, lastInsertedId + 1);
                ps2.setString(2, customTitle);
                ps2.setString(3, customYear);
                ps2.setString(4, customRuntime);
                ps2.setString(5, customRating);
                ps2.setString(6, customGenre);
                ps2.setString(7, customDescription);
                ps2.setString(8, user);
                ps2.executeUpdate();
            }
            return customTitle + " added!";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String removeFromWatched(String user, String MovieToSearch) {
        try {
            Connection conn = ConnectDb(); // Establish a connection to the database

            String query = "DELETE FROM watched WHERE title = ? AND user = ?";

            // Create a PreparedStatement object
            PreparedStatement ps = conn.prepareStatement(query);

            // Set the parameter value for the title and user
            ps.setString(1, MovieToSearch);
            ps.setString(2, user);

            // Execute the statement to delete the row
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return MovieToSearch + " removed!";
            } else {
                return "No matching rows found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String removeFromWantToWatch(String user, String MovieToSearch) {
        try {
            Connection conn = ConnectDb(); // Establish a connection to the database

            String query = "DELETE FROM want_to_watch WHERE title = ? AND user = ?";

            // Create a PreparedStatement object
            PreparedStatement ps = conn.prepareStatement(query);

            // Set the parameter value for the title and user
            ps.setString(1, MovieToSearch);
            ps.setString(2, user);

            // Execute the statement to delete the row
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return MovieToSearch + " removed!";
            } else {
                return "No matching rows found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}