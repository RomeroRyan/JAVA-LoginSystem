# -Java-Login-System (Project 2):
A simple login system where users can create account an account. After doing so, users can login and be greeted with a randomly selected messaged based on calender day and time of day. Users also can edit and update their account. All changes are also saved immediately and between runtimes. The main purpose of this project is to apply what I learn in my Java class to a project done from scratch. It also very malleable for future Java projects that may require data to be saved between runtimes.

## -How-it-Works (summary):
When user creates an account, a .txt file named after the user's desired USERNAME is created in a pre-determine folder (folder essentially functions as a "database"). If a file already exist with the desired USERNAME, username is taken, user is force to choose a new one. The user's information is all turn to string and saved into this file. Upon logging in, the program searches for a .txt file with the username inputted. It then compares the password inputted with the one saved in the file. If a txt file was found and passwords match, log in was successful. 

### -CONTACTS:
    Ryan Romero     r.romero.softwaredev@gmail.com
    LinkedIn:       (TBA)

### -Notes:
1. A variable named **pathName** needs to be updated to location programmer wishes files to be created in order for code to function properly.
2. Currently the program changes .txt files preference to **read only**. To improve security, would want to make it unreadable and unwriteable for all.

### -Ways-to-Improve-Code:
1. bcrypt can be use to include encryption/decryption techniques to further improve the security of it
2. To optimize the amount of spaced used, it might be best if all profiles r saved into a single .txt file
3. Currently there is **NO** restrictions to passwords. Should write code to enforce atleast 1 captical char, 1 number char to all passwords + add a ToolTip to explain said restrictions.
