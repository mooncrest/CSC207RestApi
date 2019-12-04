# CSC207RestApi
A RestApi developed for an android game developed in CSC207

Design structure will be added soon as a PDF.

# Current Status:
designing this whole api is quite a big task ontop of other course work and my portion of the CSC207 android app and request connection. Sadly did not have enough time to implement all the features desired so the code is left partially complete and designed to be able to implement new features with minimal refactoring. Pivoted from package by features for microsystems due to the small size of project and no plan to expand.

# First attempt Accomplishments:
* Allows future android app extension in activities to follow single responsibility principle easier. 
* Uses a json file to store user, leaderboard and token data. 
* Handles http requests from volley calls in CSC207 android app (Link posted soon).
  * Accept score posting.
  * Accept user time played and stage posting.
  * Gets leaderboard from json database.
  * Registers user.
* Uses dependency injection to allow modularity in code and allow swapping of services or DAO easily. 
* Really basic authentication system implemented partially features of oAauth2.

# Dependencies used:
* SpringBoot
* Jackson 

# Future extensions:
* Plan on incorporating a mongoDB database should require little refactoring due to dependency injection on the DAO objects for services.
* Email verification system to send emails should be a simple extension of the user service.  
  * Code for sending email is already finished
  * Require code for setting up api for confirmation
* Proper oAuth2 implementation currently does a really rough framework for oauth but should be implemented easily by changing services through dependency injection on the user serivces.
* Proper Response code and Error handling 
  * Proper http response messages for detailed errors and many improper response code.
  * Currently some methods return integers that should be documented and used later for proper error responses.
* Salted and Peppered hash for passwords
* Implement CQRS into the database

# Requirements:
Java 1.8 depedencies should be set up through maven

# Installation Guide:
1. Clone the file anywhere you wish
2. open the file and click on pom.xml
3. run the api it should be working and on port 8080

# Rough Documentation URL = localhost:8080 
<Post> api/leaderboard/testing/{game}
gets the game with name {game}
<Get> api/leaderboard/testing
gets all the leaderboards
<Post> api/leaderboard/testing
posts a specific leaderboard. Body Structure docs will be added soon.
<Post> api/tokens/user
gets the user of this token if its valid. Body Structure docs will be added soon.
<Post> api/tokens/login 
returns an auth token if valid. Body Structure docs will be added soon.
<Post> api/tokens/score/{game}
posts the score created by the user. Body Structure docs will be added soon.
<Put> api/tokens/stage/{stage}
puts the stage completed into the user data. Body Structure docs will be added soon.
<Put> api/tokens/timeplayed/{duration}
puts this users time played into the user data. Body Structure docs will be added soon.
<Post> api/tokens/testing 
deletes all the tokens in the DB. Body Structure docs will be added soon.
<Post> api/users/register 
Tries to add this user to the database. Body Structure docs will be added soon.
<Get> api/users/testing
gets all the users in this database.
