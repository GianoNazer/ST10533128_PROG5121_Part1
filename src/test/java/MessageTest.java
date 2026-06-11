/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import Main.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {
    
    // =========================
    // TEST DATA SETUP
    // =========================
    @BeforeEach
    public void setup() {

        // Clear arrays before each test 
        Message.sentMessages.clear();
        Message.storedMessages.clear();
        Message.disregardedMessages.clear();
        Message.messageHashes.clear();
        Message.messageIDs.clear();
         Message.recipients.clear();

        // =========================
        // MESSAGE 1 (SENT)
        // =========================
        Message m1 = new Message(1, "+27834557896", "Did you get the cake?");
        m1.sentMessage(1);

        // =========================
        // MESSAGE 2 (SENT)
        // =========================
        Message m2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        m2.sentMessage(1);

        // =========================
        // MESSAGE 3 (DISREGARDED)
        // =========================
        Message m3 = new Message(3, "+27834484567", "Test message");
        m3.sentMessage(2);

        // =========================
        // MESSAGE 4 (SENT)
        // =========================
        Message m4 = new Message(4, "0838884567", "It is dinner time!");
        m4.sentMessage(1);

        // =========================
        // MESSAGE 5 (SENT)
        // =========================
        Message m5 = new Message(5, "+27838884567", "Ok, I am leaving without you.");
        m5.sentMessage(1);
    }

    // =========================
    // TEST 1
    // =========================
    @Test
    public void testSentMessagesArray_correctlyPopulated() {

        assertTrue(Message.sentMessages.contains("Did you get the cake?"));
        assertTrue(Message.sentMessages.contains("It is dinner time!"));
    }

    // =========================
    // TEST 2
    // =========================
    @Test
    public void testDisplayLongestMessage_returnsCorrectMessage() {

        String result = Message.displayLongestMessage();

        assertEquals(
            "Where are you? You are late! I have asked you to be on time.",
            result
        );
    }

    // =========================
    // TEST 3
    // =========================
    @Test
    public void testSearchByMessageID_returnsCorrectMessage() {

        String id = Message.messageIDs.get(3); // message 4

        String result = Message.searchByMessageID(id);

        assertEquals("It is dinner time!", result);
    }

    // =========================
    // TEST 4
    // =========================
    @Test
    public void testSearchByRecipient_returnsAllMatchingMessages() {

        String result = Message.searchByRecipient("+27838884567");

        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    // =========================
    // TEST 5
    // =========================
    @Test
    public void testDeleteByHash_removesCorrectMessage() {

        String hash = Message.messageHashes.get(1); // message 2

        String result = Message.deleteByHash(hash);

        assertEquals(
            "Message: Where are you? You are late! I have asked you to be on time. successfully deleted.",
            result
        );
    }

    // =========================
    // TEST 6
    // =========================
    @Test
    public void testDisplayReport_containsRequiredFields() {

        String report = Message.fullReport();

        assertTrue(report.contains("Message Hash"));
        assertTrue(report.contains("Recipient"));
        assertTrue(report.contains("Did you get the cake?"));
    }


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
