/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
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
    
public static List<String> sentMessages = new ArrayList<>();
public static List<String> disregardedMessages = new ArrayList<>();
public static List<String> storedMessages = new ArrayList<>();
public static List<String> messageHashes = new ArrayList<>();
public static List<String> messageIDs = new ArrayList<>();
public static List<String> recipients = new ArrayList<>();

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
                sentMessages.add(messageText);
                messageHashes.add(messageHash);
                messageIDs.add(messageID);
                recipients.add(recipient);
                return "Message successfully sent.";

            case 2:
                disregardedMessages.add(messageText);
                messageIDs.add(messageID);
                return "Press 0 to delete the message.";

            case 3:
                storedMessages.add(messageText);
                messageHashes.add(messageHash);
                messageIDs.add(messageID);
                recipients.add(recipient);
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
    // Store to JSON file
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
    
    // =========================
    // Stored messgages Sub menu
    // =========================
    public static void displayStoredMessages() {
        for (String msg : storedMessages) {
            System.out.println(msg);
        }
    }

    public static String displayLongestMessage() {
    String longest = "";

    for (String msg : storedMessages) {
        if (msg.length() > longest.length()) {
            longest = msg;
        }
    }

    return longest;
}

    public static String searchByMessageID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {

        if (messageIDs.get(i).equals(id)) {
            return storedMessages.get(i);
        }
    }

    return "Message not found.";
}

    public static String searchByRecipient(String recipient) {

    StringBuilder results = new StringBuilder();

    for (int i = 0; i < recipients.size(); i++) {

        if (recipients.get(i).equals(recipient)) {

            results.append(storedMessages.get(i)).append("\n");
        }
    }

    if (results.length() == 0) {
        return "No messages found for recipient.";
    }

    return results.toString();
}

    public static String deleteByHash(String hash) {

    for (int i = 0; i < messageHashes.size(); i++) {

        if (messageHashes.get(i).equals(hash)) {

            String deletedMessage = storedMessages.get(i);

            messageHashes.remove(i);
            storedMessages.remove(i);
            messageIDs.remove(i);
            recipients.remove(i);

            return "Message: " + deletedMessage + " successfully deleted.";
        }
    }

    return "Hash not found.";
}

    public static String fullReport() {
        StringBuilder report = new StringBuilder();

    report.append("=== MESSAGE REPORT ===\n");

    int size = Math.min(
        Math.min(sentMessages.size(), messageHashes.size()),
        recipients.size()
    );

    for (int i = 0; i < size; i++) {

        report.append("Message Hash: ").append(messageHashes.get(i)).append("\n");
        report.append("Recipient: ").append(recipients.get(i)).append("\n");
        report.append("Message: ").append(sentMessages.get(i)).append("\n");
        report.append("----------------------\n");
    }

    return report.toString();
}
    public static void loadStoredMessages() {

    // Attribution: org.json library
    // https://mvnrepository.com/artifact/org.json/json

    try (BufferedReader br = new BufferedReader(new FileReader("messages.json"))) {

        String line;

        while ((line = br.readLine()) != null) {

            JSONObject obj = new JSONObject(line);

            storedMessages.add(obj.getString("messageText"));
            messageIDs.add(obj.getString("messageID"));
            messageHashes.add(obj.getString("messageHash"));
            recipients.add(obj.getString("recipient"));
        }

    } catch (IOException e) {
        System.out.println("No stored messages file found. Starting fresh.");
    }
}
}
    

