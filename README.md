# Firebase20
Firebase Authentication and Updation on Real-time Databse

1. DEPENDENCIES

Project Level Build.gradle Depedencies:-

dependencies {

        //Add the below one to the existing one in the new line
        
        classpath 'com.google.gms:google-services:4.0.1'
        
        
    }
    
App Level Build.gradle Dependencies:-

dependencies {

      //Add these to the existing dependencies
    implementation 'com.google.firebase:firebase-core:16.0.1'             //firebase core
    
    implementation 'com.google.firebase:firebase-auth:16.0.1'             //firebase authentication
    
    implementation 'com.google.firebase:firebase-database:16.0.1'         //firebase database
    
    }
 
 
 2. CODE FOR REGISTER AND LOGIN
 
 The code of Registration(MainActivity) is given along with the code for Login(LoginActivity).
 
 Both of them works on the FirebaseAuth library.
 
 
 
 3. CONFIGURING THE REAL-TIME Database in Firebase
 
 Open the Firebase Console https://console.firebase.google.com
 
 click on 'Database' which is just below the 'Authentication' tab.
 
 Now, create a database under it using 'create database' and select 'test mode'
 
 Your dont have to enter any fields.
 
 After this, You will see a database control panel appeared to your screen.
 
 Now, on the Top where 'Database' is wriiten, there must be something like 'Cloud Firestore'. Click on it to change it to Real-Time Database.
 
 After this, do the coding part.
 
 
 
 4. REAL-TIME DATABASE
 
 The java file UpdateInformation is a class defined to store information in the real-time database with a specified format.
 The Activity file (ProfileActivity) is the actvity file in java that uses the UpdateInformation class to send the information in a format 
 to the Real-time Database.
 

 

5. Run the App
Register - MainActivity
Login - LoginActivity
Logged In Page with database hands on - ProfileActivity

If you like it, Leave a star on the repository - https://github.com/Uraj14/Firebase20

Regards,

Ujjawal Raj


