import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;
import java.util.Calendar;
//import java.util.Scanner;
public class LoginJFrames extends JFrame implements ActionListener, ItemListener {
/**********************************************
 * Random Variables needed for Program
 **********************************************/
	Font titleFont = new Font("Arial", Font.BOLD, 20);
	Font bigFont = new Font("Arial", Font.BOLD, 15);
	Font smallFont = new Font("Arial", Font.BOLD, 12);
	Font basicFont = new Font("Arial", Font.PLAIN, 12);
	File myFile;
	String randomTemp;
	CardLayout myCardLayout = new CardLayout();
	 
	//***NOTE: String to the pathfile of where "database" folder is (create a "database" folder and copy/paste the pathway here)
	String pathName = "/home/student/Desktop/Coding Projects/Java/Project1_java/database/";
	
	//Hold user info whenever successful login
	String 	userFirstName = "<FirstName>";
	String 	userLastName = "<LastName>";
	String 	userUsername = "bob123";
	String 	userPassword = "password123";
	int 	userMonthBday = 00;
	int 	userDayBday = 00;
	
	//Hold variables during changes made in "Edit Account" window,
	//Doesnt mess with original values in case user chooses not to execute changes to profile or if error occures
	int		holderMonth;
	int		holderDay;
	String	holderUsername;
	String	holderPassword;
	String	holderFirst;
	String	holderLast;
	
	//calender variables
	Calendar theDate;
	int hour;
	int day;
	int month;		//Jan=1, l Feb=2, etc
	
/**********************************************
 * CARD PANELS
 **********************************************/
	JPanel WelcomePanel = new JPanel();
			JPanel WelcomePanel_North = new JPanel();
			JPanel WelcomePanel_South = new JPanel();
			JPanel WelcomePanel_Center = new JPanel();
	JPanel AccountPanel = new JPanel();
			JPanel AccountPanel_North = new JPanel();
			JPanel AccountPanel_Center = new JPanel();
			JPanel AccountPanel_South = new JPanel();
	JPanel AccessPanel = new JPanel();
			JPanel AccessPanel_North = new JPanel();
			JPanel AccessPanel_Center = new JPanel();
			JPanel AccessPanel_South = new JPanel();
	JPanel EditPanel = new JPanel();
			JPanel EditPanel_North = new JPanel();
			JPanel EditPanel_Center = new JPanel();
			JPanel EditPanel_South = new JPanel();
			
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  Welcome's objects
	 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	JLabel 		WELCOME_headerMsg = new JLabel("Welcome");
	JLabel 		WELCOME_usernameMsg = new JLabel("username:");
	JLabel 		WELCOME_passwordMsg = new JLabel("password:");
	JTextField 	WELCOME_usernameTField = new JTextField(20);
	JTextField 	WELCOME_passwordTField = new JTextField(20);
	JButton 	WELCOME_loginButton = new JButton("Login");
	JButton 	WELCOME_createButton = new JButton("New? Create Account");
	JLabel 		WELCOME_errorMsg = new JLabel("");
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  Account's objects
	 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	JLabel 		ACCOUNT_headerMsg = new JLabel("Create Account");
	JLabel 		ACCOUNT_usernameMsg = new JLabel("username:");
	JLabel 		ACCOUNT_passwordMsg = new JLabel("password:");
	JLabel 		ACCOUNT_nameLastMsg = new JLabel("last name:");
	JLabel 		ACCOUNT_nameFirstMsg = new JLabel("first name:");
	JLabel		ACCOUNT_birthdayMsg = new JLabel("birthday:");
	JLabel 		ACCOUNT_errorMsg = new JLabel("");

	JTextField 	ACCOUNT_usernameTField = new JTextField(20);
	JTextField 	ACCOUNT_nameFirstTField = new JTextField(20);
	JTextField 	ACCOUNT_nameLastTField = new JTextField(20);
	JTextField 	ACCOUNT_passwordTField = new JTextField(20);
	JButton 	ACCOUNT_signupButton = new JButton("Sign Up");
	JButton		ACCOUNT_goBackButton = new JButton("Back");

	//BDAY OBJs
	String[] Bday_Month = {"Month" ,"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	String[] Bday_Day = {"Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
			"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	JComboBox ACCOUNT_Month = new JComboBox(Bday_Month);
	JComboBox ACCOUNT_Day = new JComboBox(Bday_Day);

	/*~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  Edit's object
	 ~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	JLabel 		EDIT_headerMsg = new JLabel("Edit Account");
	JLabel 		EDIT_usernameMsg = new JLabel("username:");
	JLabel 		EDIT_passwordMsg = new JLabel("password:");
	JLabel 		EDIT_nameLastMsg = new JLabel("last name:");
	JLabel 		EDIT_nameFirstMsg = new JLabel("first name:");
	JLabel		EDIT_birthdayMsg = new JLabel("birthday:");
	JLabel 		EDIT_errorMsg = new JLabel("");
	
	JTextField 	EDIT_usernameTField = new JTextField(20);
	JTextField 	EDIT_nameFirstTField = new JTextField(20);
	JTextField 	EDIT_nameLastTField = new JTextField(20);
	JTextField 	EDIT_passwordTField = new JTextField(20);
	JButton 	EDIT_updateButton = new JButton("Update");
	JButton		EDIT_goBackButton = new JButton("Back");
	
	//BDAY OBJs
	String[] BdMonth = {"Month" ,"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	String[] BdDay = {"Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
			"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	JComboBox EDIT_Month = new JComboBox(BdMonth);
	JComboBox EDIT_Day = new JComboBox(BdDay);
	
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~
	 *	Access's objects
	 ~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	JLabel 		ACCESS_headerMsg = new JLabel("Hello, "+ userFirstName);
	JLabel  	ACCESS_subMsg = new JLabel("Sample");
	JButton 	ACCESS_edit = new JButton("Edit Account");
	JButton 	ACCESS_tempButton = new JButton("Log out");		

	
/**************************************************************************************																							  *
 *		  		CONSTRUCTOR 														  *																								  *
 ***************************************************************************************/
	public LoginJFrames() {
		super("Login Project");
		//welcome = 300, 230
		//Account = 300, 330 
		setBounds(700, 300, 300, 230);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(myCardLayout);

		theDate = Calendar.getInstance();
		
		/*~~~~~~~~~~~~~~~~~~~~~~
		 * Create Welcome Panel
		 ~~~~~~~~~~~~~~~~~~~~~~~*/
		WelcomePanel.setLayout(new BorderLayout());
		WelcomePanel.add(WelcomePanel_North, BorderLayout.NORTH);
		WelcomePanel.add(WelcomePanel_South, BorderLayout.SOUTH);
		WelcomePanel.add(WelcomePanel_Center, BorderLayout.CENTER);
		WelcomePanel_South.setLayout(new FlowLayout());
		JLabel welcomeEmptySpace = new JLabel("       ");
		
		WELCOME_headerMsg.setFont(titleFont);
		WELCOME_createButton.setFont(basicFont);
		WELCOME_loginButton.setFont(smallFont);
		
		WelcomePanel_North.add(WELCOME_headerMsg);
		WelcomePanel_Center.add(WELCOME_usernameMsg);
		WelcomePanel_Center.add(WELCOME_usernameTField);
		WelcomePanel_Center.add(WELCOME_passwordMsg);
		WelcomePanel_Center.add(WELCOME_passwordTField);
		WelcomePanel_Center.add(WELCOME_errorMsg);
		WelcomePanel_South.add(WELCOME_loginButton);
		WelcomePanel_South.add(welcomeEmptySpace);
		WelcomePanel_South.add(WELCOME_createButton);
		
		/*~~~~~~~~~~~~~~~~~~~~~~~
		 *	Create Account Panel
		 ~~~~~~~~~~~~~~~~~~~~~~~~*/
		AccountPanel.setLayout(new BorderLayout());
		AccountPanel.add(AccountPanel_North, BorderLayout.NORTH);
		AccountPanel.add(AccountPanel_South, BorderLayout.SOUTH);
		AccountPanel.add(AccountPanel_Center, BorderLayout.CENTER);
		JLabel accountEmptySpace = new JLabel("           ");

		ACCOUNT_headerMsg.setFont(titleFont);
		ACCOUNT_goBackButton.setFont(smallFont);
		ACCOUNT_signupButton.setFont(smallFont);
		
		AccountPanel_North.add(ACCOUNT_headerMsg);
		AccountPanel_South.add(ACCOUNT_goBackButton);
		AccountPanel_South.add(ACCOUNT_signupButton);
		AccountPanel_Center.add(ACCOUNT_usernameMsg);
		AccountPanel_Center.add(ACCOUNT_usernameTField);
		AccountPanel_Center.add(ACCOUNT_passwordMsg);
		AccountPanel_Center.add(ACCOUNT_passwordTField);
		AccountPanel_Center.add(ACCOUNT_nameFirstMsg);
		AccountPanel_Center.add(ACCOUNT_nameFirstTField);
		AccountPanel_Center.add(ACCOUNT_nameLastMsg);
		AccountPanel_Center.add(ACCOUNT_nameLastTField);
		AccountPanel_Center.add(ACCOUNT_birthdayMsg);
		AccountPanel_Center.add(ACCOUNT_Month);
		AccountPanel_Center.add(ACCOUNT_Day);
		AccountPanel_Center.add(accountEmptySpace);
		AccountPanel_Center.add(ACCOUNT_errorMsg);

		/*~~~~~~~~~~~~~~~~~~~~~~~
		 *	Create Edit Pannel
		 ~~~~~~~~~~~~~~~~~~~~~~~~*/
		EditPanel.setLayout(new BorderLayout());
		EditPanel.add(EditPanel_North, BorderLayout.NORTH);
		EditPanel.add(EditPanel_South, BorderLayout.SOUTH);
		EditPanel.add(EditPanel_Center, BorderLayout.CENTER);
		JLabel editEmptySpace = new JLabel("           ");
		
		EDIT_headerMsg.setFont(titleFont);
		
		EditPanel_North.add(EDIT_headerMsg);
		EditPanel_South.add(EDIT_goBackButton);
		EditPanel_South.add(EDIT_updateButton);
		EditPanel_Center.add(EDIT_usernameMsg);
		EditPanel_Center.add(EDIT_usernameTField);
		EditPanel_Center.add(EDIT_passwordMsg);
		EditPanel_Center.add(EDIT_passwordTField);
		EditPanel_Center.add(EDIT_nameFirstMsg);
		EditPanel_Center.add(EDIT_nameFirstTField);
		EditPanel_Center.add(EDIT_nameLastMsg);
		EditPanel_Center.add(EDIT_nameLastTField);
		EditPanel_Center.add(EDIT_birthdayMsg);
		EditPanel_Center.add(EDIT_Month);
		EditPanel_Center.add(EDIT_Day);
		EditPanel_Center.add(editEmptySpace);
		EditPanel_Center.add(EDIT_errorMsg);
		
		/*~~~~~~~~~~~~~~~~~~~~~~~
		 *	Create Access Pannel
		 ~~~~~~~~~~~~~~~~~~~~~~~~*/
		AccessPanel.setLayout(new BorderLayout());
		AccessPanel.add(AccessPanel_Center, BorderLayout.CENTER);
		AccessPanel.add(AccessPanel_South, BorderLayout.SOUTH);
		AccessPanel.add(AccessPanel_North, BorderLayout.NORTH);
		
		ACCESS_headerMsg.setFont(bigFont);
		ACCESS_subMsg.setFont(basicFont);
		
		AccessPanel_Center.setLayout(new BorderLayout());
		
		AccessPanel_North.add(ACCESS_headerMsg);
		AccessPanel_Center.add(ACCESS_subMsg);
		AccessPanel_South.add(ACCESS_edit);
		AccessPanel_South.add(ACCESS_tempButton);
		
/*************************************************************************************																					  *
 *		  		Add Panels to Deck											         *																								  *
 *************************************************************************************/
		add(WelcomePanel, "Welcome");
		add(AccountPanel, "Account");
		add(EditPanel, "Edit");
		add(AccessPanel, "Access");
		
		/*~~~~~~~~~~~~~~~~~~~~~~
		 *	Add Listeners
	 	~~~~~~~~~~~~~~~~~~~~~~~~*/
		WELCOME_loginButton.addActionListener(this);
		WELCOME_createButton.addActionListener(this);
		ACCOUNT_signupButton.addActionListener(this);
		ACCOUNT_goBackButton.addActionListener(this);
		EDIT_goBackButton.addActionListener(this);
		EDIT_updateButton.addActionListener(this);
		ACCESS_edit.addActionListener(this);
		ACCESS_tempButton.addActionListener(this);
		
		ACCOUNT_Month.addItemListener(this);
		ACCOUNT_Day.addItemListener(this);
		EDIT_Month.addItemListener(this);
		EDIT_Day.addItemListener(this);
		
		
	} // end of constructor
	
	/*~~~~~~~~~~~~~~~~~~~~
	 * get Greeting Msg
	 ~~~~~~~~~~~~~~~~~~~~~*/
	void getGreeting(int day, int month, int hour) {
		String myText = "<html>If you see this message, an error has occured.</html>";
		
		
		//generate random num between 1 and 3 (this random Int use to generate different messages)
		int RandomNum = (int)(Math.random()*3) +1;
		
		
		//EVENT 1: Birthday Message
		if((month == userMonthBday) && (day == userDayBday)) {
			ACCESS_headerMsg.setText("Happy Birthday, " + userFirstName + "!");
			if(RandomNum ==1) {
				myText = "<html>Birthdays are good for your health. Studies have shown that people who have more birthdays live longer! WOW!</html>";
			}else if(RandomNum == 2) {
				myText = "<html>The secret of staying young is to live honest, eat healthy, and lie about your age.</html>";
			}else {
				myText = "<html>You're not as young as you used to be. But you're not as old as you're going to be.</html>";
			}
			ACCESS_subMsg.setText(myText);
		}
		
		//EVENT 2: Holiday Messages 
		else if((month == 1) && (day == 1)) {
			ACCESS_headerMsg.setText("Happy New Year, " + userFirstName+ "!");
			if(RandomNum ==1) {
				myText = "<html>Stay commited to your goals this year, but stay flexible in your approach.</html>";
			}else if(RandomNum == 2) {
				myText = "<html>What the new year brings to you will depend a great deal on what you bring to the new year. </html>";
			}else {
				myText = "<html>-New Year Msg 3-</html>";
			}
			ACCESS_subMsg.setText(myText);
		}
		else if((month == 7) && (day ==4)) {
			ACCESS_headerMsg.setText("Happy Independence Day");
			if(RandomNum ==1) {
				myText = "<html>Fun Fact: Thomas Jefferson drafted the Declaration of Independence. But did you know he would coincidentally died on July forth? 1826.</html>";
			}else if(RandomNum == 2) {
				myText = "<html>Keep on pursueing your American Dream! Anything can be achieve with hardwork.</html>";
			}else {
				myText = "<html>America was build on courage, imagination, and an unbeatable determination to get the job done. Strive to do your best always!</html>";
			}
			ACCESS_subMsg.setText(myText);
		}
		else if((month == 12) && (day ==24)) {
			ACCESS_headerMsg.setText("It's Christmas Eve!");
			if(RandomNum ==1) {
				myText = "<html>Hope you done all your holiday shopping. Now time to sit back and enjoy your evening.</html>";
			}else if(RandomNum == 2) {
				myText = "<html>Spread the holiday cheer today! It's as simple as wishing someone a happy holidays with a smile.</html>";
			}else {
				myText = "<html>-Christmas Eve msg 3-</html>";
			}
			ACCESS_subMsg.setText(myText);
		}
		else if((month == 12) && (day ==25)) {
			ACCESS_headerMsg.setText("Merry Christmas, " + userFirstName+ "!");
			if(RandomNum ==1) {
				myText = "<html>Enjoy yourself, pour a cup of coffee and spread some holiday cheer!</html>";
			}else if(RandomNum == 2) {
				myText = "<html>Dont forget the true meaning of Christmas. You know, the birth of Santa?</html>";
			}else {
				myText = "<html>Remember that period of time where Santa would drink Coca-Cola with his cookies?</html>";
			}
			ACCESS_subMsg.setText(myText);
		}
		else if((month ==10) && (day ==31)) {
			ACCESS_headerMsg.setText("Happy Halloween, " + userFirstName+ "!");
			if(RandomNum ==1) {
				myText = "<html>Have a spook-tacular, boo-tiful, fang-tastic Halloween!</html>";
			}else if(RandomNum == 2) {
				myText = "<html>Don't let any vampires suck the fun out of your night. Have a frightful day!</html>";
			}else {
				myText = "<html>Finally the day you look less ugly then usual. Have a festive Halloween. </html>";
			}
			ACCESS_subMsg.setText(myText);
		}
		//checks if it's the 4th Thursday of November (thanksgiving)
		else if(  (month == 11)  &&  (theDate.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY)  &&  (theDate.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 4)  ) {
			ACCESS_headerMsg.setText("Happy Thanksgiving, " + userFirstName+ "!");
			if(RandomNum ==1) {
				myText = "<html>Remember to eat ur vegetables today! I recommend carrot cake, zucchini bread, and pumpkin pie.</html>";
			}else if(RandomNum == 2) {
				myText = "<html>An optimist is a person who starts a new diet on Thanksgiving Day.</html>";
			}else {
				myText = "<html>Imagine, there are people traveling thousands of miles to be with people they only see once a year. And then discover once a year is way too often. Spend your day with those you love!</html>";
			}
			ACCESS_subMsg.setText(myText);
		}
		
		
		//Standard Message
		else if((hour > 0) && (hour < 12)) {
			ACCESS_headerMsg.setText("Good Morning, " + userFirstName);
			if(RandomNum ==1) {
				myText = "<html>Do not worry about what the rest of the week brings, because your day has just begun.</html>";
			}else if(RandomNum == 2) {
				myText = "<html>Nothing beats a cup of coffee to jumpstart up your day.</html>";
			}else {
				myText = "<html>Every new morning is a brand new day. Forget the past and live the present.</html>";
			}
			ACCESS_subMsg.setText(myText);
		}
		else if((hour >= 12) && (hour < 17)) {
			ACCESS_headerMsg.setText("Good Afternoon, " + userFirstName);
			if(RandomNum ==1) {
				myText = "<html>Take a momment to enjoy a small break. Breathe in the warmth and enjoy your day.</html>";
			}else if(RandomNum == 2) {
				myText = "<html>The biggest motivation is your own thoughts, so think big and inspire yourself! </html>";
			}else {
				myText = "<html>Smile bright like the afternoon sun and spread inspriation and confidences to everyone you meet.</html>";
			}
			ACCESS_subMsg.setText(myText);
		}
		else if((hour >= 17)&& (hour < 21)) {
			ACCESS_headerMsg.setText("Good Evening, " + userFirstName);
			if(RandomNum ==1) {
				myText = "<html>Evenings are the best time to relax and reflect on your day so kick back and enjoy a cup of coffee or tea. </html>";
			}else if(RandomNum == 2) {
				myText = "<html>What a nice night for an evening...</html>";
			}else {
				myText = "<html>It's that time of day where the soft amber lights glow throughout our homes.</html>";
			}
			ACCESS_subMsg.setText(myText);
		}
		else if(hour >=21) {
			ACCESS_headerMsg.setText("It's late, " + userFirstName + "...");
			if(RandomNum ==1) {
				myText = "<html>Please get some rest soon. Try to get a full night's sleep.</html>";
			}else if(RandomNum == 2) {
				myText = "<html>Is everything alright? Having trouble sleeping? That's ok.</html>";
			}else {
				myText = "<html>Never give up on your dreams! Keep on sleeping!</html>";
			}
			ACCESS_subMsg.setText(myText);
		}
		else {
			//ERROR Msg to system, dump memory
			ACCESS_subMsg.setText(myText);
			System.out.println("something wrong");
			System.out.println("Hours:" + hour + " Days:" + day +" Month:" +month);
		}
	} //end of getGreeting()
	

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == ACCOUNT_Month) {
			userMonthBday = ACCOUNT_Month.getSelectedIndex();
		}
		else if(e.getSource() == ACCOUNT_Day) {
			userDayBday = ACCOUNT_Day.getSelectedIndex();
		}
		else if(e.getSource() == EDIT_Month) {
			holderMonth = EDIT_Month.getSelectedIndex();
		}
		else if(e.getSource() == EDIT_Day) {
			holderDay = EDIT_Day.getSelectedIndex();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		/**********************************************************
		 * Note: each comment explain: current panel --> new panel 
		 * 		 based on which button was click
		 **********************************************************/
		
		/*~~~~~~~~~~~~~~~~~~~~~
		 * Welcome --> Access
		 ~~~~~~~~~~~~~~~~~~~~~~*/
		if(source == WELCOME_loginButton){
			//get user input username and password
			userUsername = WELCOME_usernameTField.getText();
			userPassword = WELCOME_passwordTField.getText();
			
			//searches for textfile matching username user typed
			randomTemp = pathName + userUsername + ".txt";
			myFile = new File(randomTemp);
			
			if(myFile.exists()) {
				BufferedReader reader;
				try {
						reader = new BufferedReader(new FileReader(randomTemp));
						String fileLine = reader.readLine();
						String[] fileLineArray = fileLine.split(" ");
						
						if(userPassword.contains(fileLineArray[1])) {
								//Accounts: "username password firstName LastName Month(num) Day(num)"
								userFirstName = fileLineArray[2];
								userLastName = fileLineArray[3];
								userMonthBday = Integer.parseInt(fileLineArray[4]);
								userDayBday =Integer.parseInt(fileLineArray[5]);
								reader.close();
								
								//gets date before switching to Access Panel
								theDate = Calendar.getInstance();
								hour = theDate.get(Calendar.HOUR_OF_DAY);
								day = theDate.get(Calendar.DAY_OF_MONTH);
								month = theDate.get(Calendar.MONTH) +1;		//Jan=1, Feb=2, etc
								getGreeting(day, month, hour);
								
								//switches to Access Panel
								setBounds(700, 300, 300, 250);
								myCardLayout.show(getContentPane(), "Access");
						}else
							{WELCOME_errorMsg.setText("ERROR: Incorrect Password");}
						
				}catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}else 
				{WELCOME_errorMsg.setText("ERROR: Account not found");}
			
		}
		
		/*~~~~~~~~~~~~~~~~~~~~~~
		 * Welcome --> Account
		 ~~~~~~~~~~~~~~~~~~~~~~~*/
		else if(source == WELCOME_createButton){
			//resize Window
			setBounds(700, 300, 300, 330);
			
			//clears textfield before switching to panel
			ACCOUNT_usernameTField.setText("");
			ACCOUNT_passwordTField.setText("");
			ACCOUNT_nameFirstTField.setText("");
			ACCOUNT_nameLastTField.setText("");
			ACCOUNT_errorMsg.setText("");
			myCardLayout.show(getContentPane(), "Account");
		}
		
		/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
		 * Account --> Welcome(back)
		 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		else if(source == ACCOUNT_goBackButton){
			setBounds(700, 300, 300, 230);
			//clears textfield before switching to panel
			WELCOME_usernameTField.setText("");
			WELCOME_passwordTField.setText("");
			WELCOME_errorMsg.setText("");
			myCardLayout.show(getContentPane(), "Welcome");
		}
		
		/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		 * Account --> Welcome(signup)
		 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		else if(source == ACCOUNT_signupButton){
				userUsername = ACCOUNT_usernameTField.getText();
				userPassword = ACCOUNT_passwordTField.getText();
				userFirstName = ACCOUNT_nameFirstTField.getText();
				userLastName = ACCOUNT_nameLastTField.getText();
				//userMonthBday + userDayBday updated with itemStateChanged: Consider them updated 
				
				//Creates a file using pathname and username
				randomTemp = pathName + userUsername + ".txt";
				myFile = new File(randomTemp);
				
				try {
						//check if username is taken. if not, allows account to be created
						//if false, file with existing username exist, does not create account
						if(myFile.createNewFile()) {
								FileWriter writer = new FileWriter(myFile);
								
								//create a line of string (the profile)
								randomTemp = userUsername+" "+userPassword+" "+userFirstName+" "+userLastName+" "+userMonthBday+" "+userDayBday;
								writer.write(randomTemp);
								writer.close();
								
								//"secure" the file
								myFile.setReadOnly();
								
								//switch to Welcome panel
								WELCOME_usernameTField.setText("");
								WELCOME_passwordTField.setText("");
								WELCOME_errorMsg.setText("");
								setBounds(700, 300, 300, 230);
								myCardLayout.show(getContentPane(), "Welcome");
						}
						else 
							{ACCOUNT_errorMsg.setText("Error: Username already taken");}
				}catch (IOException e1) {
					e1.printStackTrace();
				}
		}

		
		/*~~~~~~~~~~~~~~~~~~~
		 * Edit --> Access
		 ~~~~~~~~~~~~~~~~~~~~*/
		else if(source == EDIT_updateButton) {
			//"holder" varaiables temporary hold user inputs
			holderUsername = EDIT_usernameTField.getText();
			holderPassword = EDIT_passwordTField.getText();
			holderFirst = EDIT_nameFirstTField.getText();
			holderLast = EDIT_nameLastTField.getText();
			//holderMonth + holderDay updated with itemStateChanged: Consider them updated
			
			//boolean ment to check if username is change, if so, extra steps required(check if username avaliable, erase old account, create new account
			boolean checkUsername = false;
			
			//holds the location of old file (incase username is changed during "edit profile")
			File oldFile = new File(pathName + userUsername + ".txt");
			
			//if any TextField is left empty, simply override with old info
				if(holderUsername.isEmpty()) {
					holderUsername = userUsername;
					//username isnt change. extra steps not required
					checkUsername = true;
				}
				if(holderPassword.isEmpty()) {
					holderPassword = userPassword;
				}
				if(holderFirst.isEmpty()) {
					holderFirst = userFirstName;
				}
				if(holderLast.isEmpty()) {
					holderLast = userLastName;
				}
				if(holderMonth == 0) {
					holderMonth = userMonthBday;
				}
				if(holderDay == 0) {
					holderDay = userDayBday;
				}
			
			
			//if checkUsername is true, username was NOT change, updates account on the OLD FILE
			if(checkUsername == true) {
				
				//will write into old file, must allow it to be writeabale again
				oldFile.setWritable(true);
				
				//save new info into main variables
				userUsername = holderUsername;
				userPassword = holderPassword;
				userFirstName = holderFirst;
				userLastName = holderLast;
				userMonthBday = holderMonth;
				userDayBday = holderDay;
				
				
				//write into old file with new info
				try {
					
					FileWriter writer = new FileWriter(oldFile, false);
					randomTemp = userUsername+" "+userPassword+" "+userFirstName+" "+userLastName+" "+userMonthBday+" "+userDayBday;
					writer.write(randomTemp);
					writer.close();
					
					//"secure" the file once more
					oldFile.setReadOnly();
					
					//update messages before switching to Access Panel
					theDate = Calendar.getInstance();
					hour = theDate.get(Calendar.HOUR_OF_DAY);
					day = theDate.get(Calendar.DAY_OF_MONTH);
					month = theDate.get(Calendar.MONTH) +1;		//Jan=1, Feb=2, etc
					getGreeting(day, month, hour);
					
					//go back to "Access" panel
					setBounds(700, 300, 300, 250);
					myCardLayout.show(getContentPane(), "Access");
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			//ELSE: username is changed, check if username avaliable, create new file, erase old file and update account
			else if (checkUsername == false) {
				
				//TODO: ***NOTE: code will run into error if user input the same username they originally had before "edit account"
				
				//open a file with new username
				randomTemp = pathName + holderUsername + ".txt";
				File myFile = new File(randomTemp);
				
				//check if username is avaliable
				try {
					if(myFile.createNewFile()) {
						//SUCCESS: username is avaliable, can now create new profile
						
						//save info to main variables
						userUsername = holderUsername;
						userPassword = holderPassword;
						userFirstName = holderFirst;
						userLastName = holderLast;
						userMonthBday = holderMonth;
						userDayBday = holderDay;
						
						//delete old file
						oldFile.delete();
						
						//write into the new file
						FileWriter writer = new FileWriter(myFile);
						randomTemp = userUsername+" "+userPassword+" "+userFirstName+" "+userLastName+" "+userMonthBday+" "+userDayBday;
						writer.write(randomTemp);
						writer.close();
						
						//"secure" the file
						myFile.setReadOnly();
						
						//update messages before switching to Access Panel
						theDate = Calendar.getInstance();
						hour = theDate.get(Calendar.HOUR_OF_DAY);
						day = theDate.get(Calendar.DAY_OF_MONTH);
						month = theDate.get(Calendar.MONTH) +1;		//Jan=1, Feb=2, etc
						getGreeting(day, month, hour);
						
						//go back to "Access" panel
						setBounds(700, 300, 300, 250);
						myCardLayout.show(getContentPane(), "Access");
					}
					//else, ERROR: username is taken
					else
						{EDIT_errorMsg.setText("Error: Username already taken");}
				}catch (IOException e1) 
					{e1.printStackTrace();}
				
			}
		}//END OF EDIT_updateButton
		
//------------------------------------------------------------
//------------------------------------------------------------
//------------------------------------------------------------
		
		/*~~~~~~~~~~~~~~~~~~~~
		 * Edit --> Access #2
		 ~~~~~~~~~~~~~~~~~~~~~*/
		else if (source == EDIT_goBackButton) {
			setBounds(700, 300, 300, 250);
			myCardLayout.show(getContentPane(), "Access");
		}
		
		/*~~~~~~~~~~~~~~~~~~
		 * Access --> Edit
		 ~~~~~~~~~~~~~~~~~~~*/
		else if(source == ACCESS_edit){
			EDIT_errorMsg.setText("");
			setBounds(700, 300, 300, 330);
			myCardLayout.show(getContentPane(), "Edit");
		}
		
		/*~~~~~~~~~~~~~~~~~~~~~
		 * Login --> NOTHIN
		 ~~~~~~~~~~~~~~~~~~~~~~*/
		else if(source == ACCESS_tempButton){
			
			//RESETS EVERYTHING!
			userUsername = "";
			userPassword = "";
			userFirstName = "";
			userLastName = "";
			userMonthBday = 00;
			userDayBday = 00;
			randomTemp = "";
			
			holderMonth = 00;
			holderDay = 00;
			holderUsername = "";
			holderPassword = "";
			holderFirst = "";
			holderLast = "";
			
			//reset Welcome Panel
			WELCOME_usernameTField.setText("");
			WELCOME_passwordTField.setText("");
			WELCOME_errorMsg.setText("");
			
			//returns back to Welcome Panel
			setBounds(700, 300, 300, 230);
			myCardLayout.show(getContentPane(), "Welcome");
		}
		
		

	}
}
