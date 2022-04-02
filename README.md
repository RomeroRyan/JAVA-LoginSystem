# JAVA Login-System
- Ryan Romero         r.romero.softwaredev@gmail.com

# Project Description
A java login system with GUI. Users can create an account then login to be greeted with a randomly selected messaged based on calender date and time of day (good morning, happy birthday, happy new years, etc). Users also can edit and update their account information onced logged in. All changes are saved immediately and between runtimes of the software. This was my final project in my Java Programming class, displaying what I learn in that class.

# Youtube Example Video
[![Watch the video](https://img.youtube.com/vi/h6_kHkv53fo/maxresdefault.jpg)](https://youtu.be/h6_kHkv53fo)

# How it Works (Summary)
When user creates an account, a .txt file named after the user's desired USERNAME is created in a pre-determine folder ('this' folder essentially functions as a "database"). If a file already exist with the desired USERNAME, then the username is deem taken; user must choose a new username. The user's information is all turn to string and saved into the .txt file. 

Upon logging in, the program searches for a .txt file with the username inputted. If a file exist with the given username, it then compares the password the user input with the one saved in the file. If a txt file was found and passwords match, login was successful. If no file exist, username is incorrect. If file exist but passwords do not match, password is incorrect.

# How to Run
Before running the Java file, a variable named "**pathName**" needs to be updated to a desired folder location in order for the code to function properly. This folder location is used to save newly created accounts and search for accounts when a user trys to login.
- Run LoginMain.java


# Ways to Improve
1. Currently the software uses folder and files as a primative database. Even with the file permissions beintg altered, this offers limited security. bcrypt can be use to include encryption/decryption techniques to further improve the security of files. However, implementing encryption/decryption/database will require an overhaul of the code.
2. If we stay with the same "primative database" system implemented, it be best to optimize the amount of spaced used by having all profiles saved into a single .txt file instead of creating multiple .txt files for every user. This will condense the amount of files created. (Will also need to change code to search for appropriate accounts within the single txt file)
3. Currently there is **NO** restrictions to passwords (no length restriction or strong/weak analysis). This can be achieve by write code to enforce user-created-passwords to have characteristics of strong passwords. (EX: atleast 1 captical char, 1 number char, etc)
4. Switching to SQL for the database.
