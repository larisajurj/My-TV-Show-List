package java.utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.EncryptPassword;

public class EncryptPasswordTest {
    EncryptPassword encryptor;
    @BeforeEach
    public void createClass() {
        encryptor = new EncryptPassword();
    }
    @Test
    public void testEncryptWithPassword1() {

        // Test case 1: Encrypting "password1"
        String password1 = "password1";
        String expectedHash1 = "8edcab3b3f475960e90204e3b00b8adb347ad91a624700b3d223ef3879d0f1f9ba67994e9b7014f877ad58192cff0a8933ca09a6c7e29b4bd388bbe9f896b316";
        String actualHash1 = encryptor.encrypt(password1);
        Assertions.assertEquals(expectedHash1, actualHash1, "Hashes for password1 do not match.");

    }

    @Test
    public void testEncryptWithPassword2() {
        EncryptPassword encryptor = new EncryptPassword();
        String password = "hello123";
        String expectedHash = "700608834416bc542bbf6c6289d33c38d0cb383b4b210292de11e8c6b9e22baaa5eddd54d1850da5b8ad82179184550e52d0f1eb9f1b4e9bcd5e99a96b929f84";
        String actualHash = encryptor.encrypt(password);
        Assertions.assertEquals(expectedHash, actualHash, "Hashes for password2 do not match.");
    }

    @Test
    public void testEncryptWithNullPassword() {
        EncryptPassword encryptor = new EncryptPassword();
        String password = null;
        Assertions.assertThrows(NullPointerException.class, () -> {
            encryptor.encrypt(password);
        }, "Expected NullPointerException when encrypting null password.");
    }
}
