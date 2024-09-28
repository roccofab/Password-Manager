# Password Manager System
Password Management System using java SE. This application allows you to create accounts, store domains and their associated passwords for each account.
User data are stored in Credentials.xml and Domain-Passwords associated to User are stored in Domains.xml.
The login password is hashed with the SHA-256 algorithm.
XML files are structured using Properties class.

## Prerequisites
Java, Java swing, Files Operations, OOP, MVC architecture...

## Enviroment and Requirement
- Programming Language: Java SE, Java Swing
- IDE: Intellij IDEA, Visual Studio Code, NetBeans, Eclipse...
- Created in Windows 11 HOME environment

## Usage
First Download the zip and then navigate to the following directory in the prompt where the PasswordManagementSystem.jar is saved:
- AccountsManagementSystem\out\artifacts\PasswordManagementSystem_jar
- Run the following command: java -jar PasswordManagementSystem.jar


## About Files
- Credentials.xml: Contains User Data(Username and Password).
- Domains.xml: Contains Domains name and password about specific user(<entry key = "username.domainName">password</entry>).

## How this project works
This project uses MVC software design pattern. The classes in the Model package contains the logic to retrieve data from XML files
and store data to XML files: The login class contains methods to save and retrieve a user's login data and Domainsdata class contains
methods to save and retrieve domain-password from Domains.xml about a specific user who is identified by the username.
The appController class acts as the main controller of the application, managing the logic between the model components and the graphical interface.


When you launch the application, you will first see the home window with three options:
- Register(Open Registration Window)
- Login(Open Login Window)
- Close(Close Application)
<p align="center">
<img src="https://github.com/roccofab/Password-Manager/blob/master/screenshots/RegisterWindow.png" width="40%">
</p>

## Register Window
When you register a new user with a username that already exists, you will get an error because the Domain-Password data recovery process
in Domains.xml is based on the user's username. If Registration is successful Main Window will be displayed.
<p align="center">
<img src="https://github.com/roccofab/Password-Manager/blob/master/screenshots/RegisterWindow.png" width="35%">
</p>

## Login Window
If Login is successful, then MainWindow will be displayed.
<p align="center">
<img src="https://github.com/roccofab/Password-Manager/blob/master/screenshots/LoginWindow.png" width="30%">
</p>

## Main Window
After logging in, you will reach the main window where all the name of domains and their associated passwords are stored in a list of objects.
- List of objects(JList): When you log in, the domain names and associated passwords are loaded from Domains.xml.
- Add Button: This button let open an Add new Password Window, the Main Window will be disabled until you finish add a new password.
- Close Button: Button to return to the Home Window
<p align="center">
<img src="https://github.com/roccofab/Password-Manager/blob/master/screenshots/MainWindow.png" width="30%">
</p>

## Add New Password Window
<p align="center">
<img src = "https://github.com/roccofab/Password-Manager/blob/master/screenshots/AddPasswordWindow.png" width="30%">
</p>

# Task Ideas
- Fix bugs and issues
- Modify the UI.
- Investigate how multiple accounts can be registered with the same username.
- Add features for backup user data or domain-password data.
- Add a feature that allow user to change their login password.
- Add a feature that allow user to change his password of a domain.
- Add a sort feature for the domains.
- Modify data storage: instead of using XML files, use a database to store all the info about users and their domains-password.
- Add safer security features.