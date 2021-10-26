# IbmWhiteBoard

By Oscar Niles . 

This is my solution to the Whiteboard exercise that was given to me. I am using an SQLite database, as it was easy to implement. I started off this exercise trying to use a spring boot framework, but (sadly quite late) realized that I would not be able to complete both a working hosted frontend for the information as well as working backend. Therefor I pivoted my solution to be interfaced with through the console. 


When the program is started, a database is created in the workplace. There are 2 tables in the database. One that logs users and one that logs content.  It can then populated with log in information for the team members (username: "Team1" pw:""), Bertha((username: "Bert1" pw:"bestBert")), who is a user, Sam (username: "Sam1 pw:"superSam") who is a mod and an admin account (username: "admin" pw:"admin").

I am an electrical engineer, not a computer scientist, so Im sorry if Im not giving you enough information to compile the project. IMO, it should be as easy as running:

javac -classpath .app/App.java

If it does not work please reach out to me. I am quite new to these things.

Functionality: 

STEP 1: type resetDb in commandline to populate the new database with the standard users. 

There are now multiple commands that can be called. They are listed below: 

Login: initiates the log in process. Once logged in, the content of the whiteboard is displayed in the terminal. 

Help: Gives information over all available commands.

change Password: allows a logged in user to change password

write a note: Allows you to write a new note on the whiteboard.

delete a note: Depending on account level, lets you delete either none, your own or all posts. 

add user: Allows the mods and admins to add more private accounts. 

reset db: Populates db with above mentioned users.

log out: logs you out of the account

quit: closes the program





