/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import Main.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    // =========================
    // MESSAGE LENGTH TESTS
    // =========================

    @Test
    public void testMessageLengthValid() {
        Message msg = new Message(1, "+27831234567", "Hello world");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthInvalid() {
        String longMessage = "a".repeat(300);

        Message msg = new Message(1, "+27831234567", longMessage);

        assertTrue(msg.checkMessageLength().contains("Message exceeds 250 characters by"));
    }

    // =========================
    // RECIPIENT TESTS
    // =========================

    @Test
    public void testValidRecipient() {
        Message msg = new Message(1, "+27831234567", "Hello");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testInvalidRecipient() {
        Message msg = new Message(1, "08575975889", "Hello");

        assertEquals(
            "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
            msg.checkRecipientCell()
        );
    }

    // =========================
    // MESSAGE HASH TEST
    // =========================

    @Test
    public void testMessageHash() {
        Message msg = new Message(0, "+27831234567", "Hi Keegan did you receive the payment");

        assertEquals("00:0:HIRECEIVE", msg.createMessageHash());
    }

    // =========================
    // MESSAGE ID TEST
    // =========================

    @Test
    public void testMessageIDLength() {
        Message msg = new Message(1, "+27831234567", "Hello");
        assertTrue(msg.checkMessageID());
    }

    // =========================
    // SENT MESSAGE TESTS
    // =========================

    @Test
    public void testMessageSent() {
        Message msg = new Message(1, "+27831234567", "Hello");

        assertEquals("Message sent successfully.", msg.sentMessage(1));
    }

    @Test
    public void testMessageStored() {
        Message msg = new Message(1, "+27831234567", "Hello");

        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }

    @Test
    public void testMessageDisregard() {
        Message msg = new Message(1, "+27831234567", "Hello");

        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
    }

    // =========================
    // OVERLOAD SUPPORT TEST (optional safety)
    // =========================

    @Test
    public void testDefaultSend() {
        Message msg = new Message(1, "+27831234567", "Hello");

        assertEquals("Message sent successfully.", msg.sentMessage());
    }
}
