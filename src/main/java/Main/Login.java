package Main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
public class Login {
    //Declaring Variables
    String username;
    String password;
    String phonenumber;
    
    //Username Validation
    public boolean checkUsername(String username) {
        return username.contains("_")&& username.length() <=5;
    }
     //Passowrd Validation
     public boolean checkPasswordComplexity(String password) {
         boolean hasCapital = false;
         boolean hasNumber = false;
         boolean hasSpecial = false;
         
         for(int i =0; i < password.length(); i++){
             char c = password.charAt(i);
             
             if(Character.isUpperCase(c)){
                 hasCapital = true;
             } else if (Character.isDigit(c)){
                 hasNumber = true;
             } else if(!Character.isLetterOrDigit(c)) {
                 hasSpecial = true;
             }
         }
         return password.length() >= 8 && hasCapital && hasSpecial;
     }
     
     //Cellphone Validation
     public boolean checkCellPhoneNumber(String phone) {
       return phone.startsWith("+27") && phone.length()<=12;
     }
    
     //Regitering of the User
     public String registerUser(String username,String password,String phoneNumber){
         
         if(!checkUsername(username)){
             return "Username is not correctly formatted;please ensure that your username contains an underscore and is no more than five characters in length";     
         }
         
         if(!checkPasswordComplexity(password)){
             return "Password is not correctly formatted;please ensure that the password contains atleast eight characters,a capital letter,a number, and a special character";            
         }
         
         if(!checkCellPhoneNumber(phoneNumber)){
             return "Cell phone number incorrectly formatted or does not contain international code";
         }
         
         this.username= username;
         this.password= password;
         this.phonenumber= phoneNumber;
         
         return"User registered successfully"; 
         }
    
     //Login Feature
     //Login of the user
     public boolean loginUser(String username,String password){
         return this.username.equals(username) && this.password.equals(password);
     }
    //Login Status
     public String returnLoginStatus(boolean success) {
         if(success) {
             return "Welcome" +" "+ username +" "+ "it is great to see you again" ;
             } else {
               return "Username or password incorrect,please try again.";
         }
     }
     
     
}
