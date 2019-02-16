# MySmartUSC

Google requires all *developers* to have a special client ID. 
Please provide us with your andriod studio SHA-1 code in order for us to issue you a credentials file. 

Please run the following in terminal: 

	keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android

We need to the SHA-1 code, which looks something like this:

	 SHA1: 12:C7:4E:CB:24:EC:FF:CC:F1:AE:AF:D5:D1:CE:8D:08:FC:02:D7:CE

We run our app on Nexus 5 API 24 Google Play Version 14.3.66.

We will then supply you with a credentials file that you can place in Android Studio to run the app in development mode.


-----------------------

For whitebox testing: 

Use the following credentials to log in into the app *before* running the tests: 

username: basxgum@gmail.com
password: ahmad0o0o

Run the white box tests after logging in successfully. This will ensure the credentials are saved so that the tests don't manually log in everytime. 

------------------------

Changes made for MySmartUSC application for project 2.6 Scrum 3:

1. In Settings, added functionality, so if there are no user-specified important emails, then the message: "There are no important emails to display." is displayed.
2. In Settings, added functionality, so if there are no user-specified urgents keywords, then the message: "There are no urgent keywords to display." is displayed.
3. In Settings, added functionality, so if there are no user-specified favorite keywords, then the message: "There are no favorite keywords to display." is displayed.
4. In Filter by Important Emails, added back button from Filter by Important Emails to Inbox.
5. In Filter by Urgent Keywords, added back button from Filter by Urgent Keywords to Inbox.
6. In Filter by Favorite Keywords, added back button from Filter by Favorite Keywords to Inbox.
7. Blackbox Tests for Filter by Important Emails, Urgent Keywords, and Favorite Keywords.
8. Fixed layout error issue for Filter by Urgent Keywords.
9. Whitebox Tests for new Java classes added in Sprint 2: ImportantEmailUnitTest, UrgentKeyWordUnitTest, and FavoriteKeyWordUnitTest. 
10. Added spinner when fetching emails in Inbox.
