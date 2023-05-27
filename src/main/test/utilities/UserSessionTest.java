package utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserSessionTest {

    private UserSession userSession;

    @BeforeEach
    public void setUp() {
        userSession = UserSession.getInstance();
    }

    @Test
    public void testGetInstance() {
        UserSession instance1 = UserSession.getInstance();
        UserSession instance2 = UserSession.getInstance();

        assertNotNull(instance1);
        assertNotNull(instance2);
        assertSame(instance1, instance2);
    }

    @Test
    public void testSetAndGetUsername() {
        String username = "john.doe";
        userSession.setUsername(username);

        assertEquals(username, userSession.getUsername());
    }
}