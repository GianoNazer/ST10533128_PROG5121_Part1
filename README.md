# 📱 Chat Application – Part 3

## 👤 Student Information

- **Name:** Giano Nazer 
- **Student Number:** ST10533128
- **Module:** PROG5121  


##  Overview

This project is Part 3 of a Java Chat Application. It expands the system by adding message storage, searching, deletion, and reporting features. The focus of this part is to manage messages more effectively after they are created.

##  Message Storage

- Messages can be stored during runtime  
- Stored messages are accessible through the **Stored Messages Menu**  
- The system can display the **longest stored message** entered by the user  


##  Message Searching

Users can search for messages using:

- Message ID  
- Recipient cellphone number  

All matching results are displayed based on the selected search option.

---

##  Message Deletion

- Messages are deleted using a unique **Message Hash**  
- Once deleted, the message is removed from all tracking arrays  
- This ensures data stays updated and consistent  

## Message Reporting

The application can generate a full message report containing:

- Message Hash  
- Recipient Number  
- Message Content  

This allows users to review all sent and stored messages in one place.

## How to Run the Program


1. Open the project in NetBeans (or any Java IDE)
2. Compile all Java files
3. Run MainApp.java
4. Register a new user
5. Log in with your credentials
6. Use the menu to:
   - Send messages
   - Store messages
   - Search messages
   - Delete messages
   - View reports
