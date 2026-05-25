

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import Main.Login;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    public LoginTest() {
    }
    
    // Create Login object
    Login login = new Login();

    // =========================
    // USERNAME TESTS
    // =========================

    @Test
    public void testValidUsername() {
        boolean result = login.checkUsername("a_b");
        assertTrue(result);
    }

    @Test
    public void testInvalidUsername() {
        boolean result = login.checkUsername("abcdef");
        assertFalse(result);
    }

    @Test
    public void testUsernameFailMessage() {
        String result = login.registerUser("abcdef", "Password@1", "+27821234567");

        assertEquals(
            "Username is not correctly formatted;please ensure that your username contains an underscore and is no more than five characters in length",
            result
        );
    }

    // =========================
    // PASSWORD TESTS
    // =========================

    @Test
    public void testValidPassword() {
        boolean result = login.checkPasswordComplexity("Password@1");
        assertTrue(result);
    }

    @Test
    public void testInvalidPassword() {
        boolean result = login.checkPasswordComplexity("password");
        assertFalse(result);
    }

    @Test
    public void testPasswordFailMessage() {
        String result = login.registerUser("a_b", "pass", "+27821234567");

        assertEquals(
            "Password is not correctly formatted;please ensure that the password contains atleast eight characters,a capital letter,a number, and a special character",
            result
        );
    }

    // =========================
    // PHONE NUMBER TESTS
    // =========================

    @Test
    public void testValidPhoneNumber() {
        boolean result = login.checkCellPhoneNumber("+27821234567");
        assertTrue(result);
    }

    @Test
    public void testInvalidPhoneNumber() {
        boolean result = login.checkCellPhoneNumber("0821234567");
        assertFalse(result);
    }

    @Test
    public void testPhoneFailMessage() {
        String result = login.registerUser("a_b", "Password@1", "0821234567");

        assertEquals(
            "Cell phone number incorrectly formatted or does not contain international code",
            result
        );
    }

    // =========================
    // REGISTRATION TEST
    // =========================

    @Test
    public void testSuccessfulRegistration() {
        String result = login.registerUser("a_b", "Password@1", "+27821234567");

        assertEquals("User registered successfully", result);
    }

    // =========================
    // LOGIN TESTS
    // =========================

    @Test
    public void testLoginSuccess() {
        login.registerUser("a_b", "Password@1", "+27821234567");

        boolean result = login.loginUser("a_b", "Password@1");
        assertTrue(result);
    }

    @Test
    public void testLoginFailure() {
        login.registerUser("a_b", "Password@1", "+27821234567");

        boolean result = login.loginUser("a_b", "WrongPass");
        assertFalse(result);
    }
}