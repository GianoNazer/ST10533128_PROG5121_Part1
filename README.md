
# ChatApp Part 1 + Part 2

## Student Information
- Name: Giano Nazer
- Student Number: ST10533128
- Module: PROG5121

## Overview
This project is a simple **Java Login and Registration system** with **JUnit testing**.  
It demonstrates user authentication, input validation, and unit testing principles.

The system allows a user to:
- Register with a username, password, and phone number
- Validate input details
- Log in using registered credentials
- Receive login status messages

## Project Structure
-src/
-main/java/Main/
-Login.java
  --MainApp.java
  --Message

-test/java/Main/
--LoginTest.java
--MessageTest.java

### Registration
- Validates username, password, and phone number
- Stores user details if valid
- Returns success or error messages

### Login
- Checks if entered credentials match registered details
- Returns login success or failure status

#### Username
- Must contain an underscore `_`
- Must be **5 characters or less**

#### Password
- Minimum **8 characters**
- Must contain:
  - At least one uppercase letter
  - At least one number
  - At least one special character

#### Phone Number
- Must start with **+27**
- Must follow valid international format
- 
## Part 2: Messaging System

### Send, Store, and Discard Messages
Users can send messages to recipients with a maximum limit of 250 characters. Multiple messages can be sent in one session. After a message is created, the user can choose to store or discard it.

### Message Tracking
The system keeps track of messages that are sent and stored (excluding discarded ones). It displays key information such as:
- Message ID  
- Message Hash  
- Total Messages Sent  

## How to Run the Program

1. Open the project in NetBeans or any Java IDE  
2. Compile all Java files  
3. Run `mainapp.java`  
4. Register a new user  
5. Log into the application  

---

## Video Demonstration

Part 2 walkthrough:  
https://www.youtube.com/watch?v=GtaccUdUdO4
