/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Student
 */
public class Message {

    // =========================
    // Fields
    // =========================
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    private static List<String> sentMessages = new ArrayList<>();
private static List<String> disregardedMessages = new ArrayList<>();
private static List<String> storedMessages = new ArrayList<>();
private static List<String> messageHashes = new ArrayList<>();
private static List<String> messageIDs = new ArrayList<>();

    private static int totalMessages = 0;

    // =========================
    // Constructor
    // =========================
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;

        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();

        totalMessages++;
    }

    // =========================
    // Generate 10-digit ID
    // =========================
    private String generateMessageID() {
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int digit = (int)(Math.random() * 10);
            id.append(digit);
        }

        return id.toString();
    }

    // =========================
    // Validate ID
    // =========================
    public boolean checkMessageID() {
        return messageID.length() == 10;
    }

    // =========================
    // Recipient Validation
    // =========================
    public String checkRecipientCell() {
        if (recipient.startsWith("+27") && recipient.length() <= 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // =========================
    // Message Length Validation
    // =========================
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int over = messageText.length() - 250;
            return "Message exceeds 250 characters by " + over + "; please reduce the size.";
        }
    }

    // =========================
    // Message Hash
    // =========================
    public String createMessageHash() {
        String idPart = messageID.substring(0, 2);

        String[] words = messageText.split(" ");

        String firstWord = words[0];
        String middleWord;

    if (words.length >= 2) {
        middleWord = words[words.length - 2];
    } else {
        middleWord = words[0];
    }

    String hash = idPart + ":" + messageNumber + ":" + firstWord + middleWord;

    return hash.toUpperCase();
    }

    // =========================
    // Send / Disregard / Store
    // =========================
    public String sentMessage(int option) {

        if (!checkMessageID()) {
            return "Message ID is invalid.";
        }

        if (!checkRecipientCell().equals("Cell phone number successfully captured.")) {
            return checkRecipientCell();
        }

        if (!checkMessageLength().equals("Message ready to send.")) {
            return checkMessageLength();
        }

        switch (option) {
            case 1:
                return "Message successfully sent.";

            case 2:
                return "Press 0 to delete the message.";

            case 3:
                storeMessage();
                return "Message successfully stored.";

            default:
                return "Invalid option.";
        }
    }
    // =========================
// Send Message (DEFAULT)
// =========================
public String sentMessage() {
    return sentMessage(1);
}

    // =========================
    // PRINT MESSAGE DETAILS
    // =========================
    public void printMessages() {
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + messageHash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + messageText);
    }

    // =========================
    // Total messages
    // =========================
    public static int returnTotalMessages() {
        return totalMessages;
    }

    // =========================
    // Store Message
    // =========================
    public void storeMessage() {
        System.out.println("Message stored.");

        JSONObject obj = new JSONObject();

        obj.put("messageID", this.messageID);
        obj.put("messageNumber", this.messageNumber);
        obj.put("recipient", this.recipient);
        obj.put("messageText", this.messageText);
        obj.put("messageHash", this.messageHash);

        try (FileWriter fw = new FileWriter("messages.json", true)) {
            fw.write(obj.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }
    
    
}
    

