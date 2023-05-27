package utilities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MySqlConnectTest {
    private static MySqlConnect mySqlConnect;

    @BeforeAll
    public static void setUp() {
        mySqlConnect = new MySqlConnect();
    }

    @Test
    public void testConnectDb() {
        assertNotNull(MySqlConnect.ConnectDb());
    }

    @Test
    public void testGetDataShows() {
        assertNotNull(mySqlConnect.getDataShows());
    }

    @Test
    public void testGetWantToWatchData() {
        assertNotNull(mySqlConnect.getWantToWatchData("user"));
    }

    @Test
    public void testInsertUserInfo() {
        Random random = new Random();
        mySqlConnect.insertUserInfo(random.toString(), "password", "quote", "1");
        // Assert the insertion by checking if the username exists in the user_table
        assertTrue(mySqlConnect.checkUsername("username"));
    }

    @Test
    public void testCheckUsername() {
        assertFalse(mySqlConnect.checkUsername("nonexistentUser"));
    }

    @Test
    public void testCheckPassword() {
        assertFalse(mySqlConnect.checkPassword("incorrectPassword", "username"));
    }
}