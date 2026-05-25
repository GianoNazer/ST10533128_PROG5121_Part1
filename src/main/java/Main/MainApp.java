/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args){

        // Scanner allows the user to enter the information
        Scanner input = new Scanner(System.in);

        // Create an object of the Login class
        Login login = new Login();

        // =========================
        // REGISTRATION SECTION
        // =========================
        System.out.println("=== USER REGISTRATION ===");

        System.out.println("Enter a username:");
        String username = input.nextLine();

        System.out.println("Enter a password:");
        String password = input.nextLine();

        System.out.println("Enter phone number (+27):");
        String phoneNumber = input.nextLine();

        // Register user
        String registrationMessage = login.registerUser(username, password, phoneNumber);
        System.out.println(registrationMessage);

        // =========================
        // LOGIN SECTION
        // =========================
        System.out.println("=== USER LOGIN ===");

        System.out.println("Enter username:");
        String loginUsername = input.nextLine();

        System.out.println("Enter password:");
        String loginPassword = input.nextLine();

        // Call loginUser to check details
        boolean loggedIn = login.loginUser(loginUsername, loginPassword);

        // Print correct login message
        String loginMessage = login.returnLoginStatus(loggedIn);
        System.out.println(loginMessage);
        
        
        
        
        //PART 2: Messaging
       if (loggedIn) {

    System.out.println("Welcome to ChatApp.");

    boolean running = true;

    while (running) {

        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1) Send Messages");
        System.out.println("2) Show recently sent messages");
        System.out.println("3) Quit");
        System.out.print("Choose an option: ");

        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {

            case 1:
                // Ask how many messages to send
                System.out.print("How many messages would you like to send? ");
                int numMessages = input.nextInt();
                input.nextLine();

                // FOR LOOP RUNS MESSAGES X AMOUNT TIMES
                for (int i = 0; i < numMessages; i++) {

    int messageNumber = i + 1;

    System.out.println("\n--- Message " + messageNumber + " ---");

    System.out.print("Enter recipient number: ");
    String recipient = input.nextLine();

    System.out.print("Enter your message (max 250 chars): ");
    String messageText = input.nextLine();

    Message msg = new Message(messageNumber, recipient, messageText);

    // Ask user what to do with message
    System.out.println("What would you like to do with this message?");
    System.out.println("1) Send Message");
    System.out.println("2) Disregard Message");
    System.out.println("3) Store Message to send later");
    System.out.print("Choose option: ");

    int option = input.nextInt();
    input.nextLine();

    String result = msg.sentMessage(option);
    System.out.println(result);

    if (result.equals("Message successfully sent.") ||
        result.equals("Message successfully stored.")) {

        msg.printMessages();
    }
}

break;

case 2:
    System.out.println("Total messages sent: " + Message.returnTotalMessages());
    break;

case 3:
    System.out.println("Exiting ChatApp...");
    running = false;
    break;

default:
    System.out.println("Invalid option. Please choose 1, 2, or 3.");
}

} 

} 

else {
    System.out.println("Login failed. Exiting application.");
}
    }
}
                