
# ChatApp Part 1

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
src/
├── main/java/Main/
│ ├── Login.java
│ └── MainApp.java
│
├── test/java/Main/
│ └── LoginTest.java

## Features

### Registration
- Validates username, password, and phone number
- Stores user details if valid
- Returns success or error messages

### Login
- Checks if entered credentials match registered details
- Returns login success or failure status

### Validation Rules

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
