package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlConnect {
    Connection conn = null;
    public static Connection ConnectDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/tvshowdb","root","");
            JOptionPane.showMessageDialog(null, "ConnectionEstablished");
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
            System.out.println(list);
        }catch(Exception e){

        }
        return list;
    }

}
