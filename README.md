# SuperDuperDrive
SuperDuperDrive is a Spring Boot project developed during the Java Web Developer Nanodegree

What will be implemented:
1. Back-end with Spring Boot
2. Front-end with Thymeleaf
3. Application tests with Selenium

Features:
* Simple file storage
* Note management
* Credentials management

## Back-end

1. Managing user access with Spring Security:
* Unauthorized users can only access the login and signup pages
* Create a security configuration class which extends the WebSecurityConfigurerAdapter class from Spring
* Override the default login page with our own login page
* Implement a custom AuthenticationProvider which is responsible for authorizing logins by matching credentials against the ones stored in the database

2. Handling front-end calls with controllers:
* Use Spring MVC to identify the templates served for different requests and populate the view model with the data
* Determine the error messages needed for each case
* Locate all controllers in a package called "controller"
* Place all the logic in services which will be called by the controllers. Usa a "service" package

3. Making calls to the database using MyBattis Mappers
* Design Java classes to match the data in the database and place this classes in a "model" or "entity" package
* Implement MyBatis mappers interfaces to connect the model classes with the database
* Write SQL queries required by the functionality of the application (CRUD). Place these classes in the "mapper" package 

## Front-end

1. Login Page
* Everyone can access this page
* Show login errors, like invalid username/password

2. Signup Page
* Everyone can access this page
* Check if the username already exists and if it does show error messages
* Store the user's password securely

3. Home Page
* It should have a logout button
* All three main functionalities of the application should be presented in this page: files, notes and credentials
  * Files: 
    * Upload and see uploaded files
    * View/download/delete files
    * Display errors related with the possible actions
    * It's not allowed to upload files with the same name
  * Notes: 
    * Create notes and see created notes
    * Edit or delete notes
  * Credentials:
    * Store credentials for specific websites and see stored credentials (display the password encrypted)
    * View/edit or delete credentials

## Testing

1. Tests for signup, login and restricted unauthorized access
* Test that verifies that an authorized user can only access the login and signup pages
* Test that signs up a new user, logs in, access the home page, logs out and checks if the homepage is no more accesible

2. Tests for note creation, viewing, editing and deleting
* Test that creates a note and verifies it is displayed
* Test that edits an existing note and verifies that the notes are displayed
* Test that deletes a note and verify that the note is no longer displayed

3. Tests for credential creation, viewing, editing and deleting
* Test that creates a set of credentials, verifies that they are displayed and verifies that the displayed password are encrypted
* Test that views and existing set of credentials, verifies that the viewable password in encrypted, edits the credential and verifies that the credentials are no longer displayed
* Test that deletes an existing sets of credentials and verifies that they are no longer displayed










