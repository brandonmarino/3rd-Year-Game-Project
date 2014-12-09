
Phoenix
Milestone 4
Project Authors: Lina El Sadek, Brandon Marino, Martin Massie, Osama BuHamad
=======
==============================================================================================================================
                                                  Milestone 4
==============================================================================================================================

Overview on MileStone 4...
We have created several features to our Othello game. Those features include: Save, load, undo, and redo. In addition to that,
we have created tests classes for everything that was missing testing. Finally, we attempted the bonus which is porting to an
Android App.


Undo Redo Design Decision...


Save Load Design Decision...
the save and load features in the project were done by making the some of the classes serializable such as Boards ,Game, and PlayerType so the object of that class can be stored as sequence of bytes with their information 
to save the file, it must be serialaized to a file by using ObjectOutputStream method that is provided by importing seralizable class in the project. In our project serializeToFile method was 
created in Util class and it uses writeObject to write the object to output file. 
to load a file, the saved file must be deserialiazed by calling ObjectInputStream which has a method called readObject that will read the desired information from the file. This is implemented in Util class as well in 
the deserializeFromFile method and it reads the file name. 
-When any of the games is executed, the options are 1-New Game 2-Load Game. To load a game, a file must existed before and if  there is a file, the program is going to ask for the file bath and it can be entered as follows:
C://User/Documents/Game/saved.txt 
Then the game will start from the saved point. Every time the player makes a move, a number of options will appear along with the available moves. If save was enter, then the program will ask for the path of the file, an example is:
C://User/Documents/Game/save.txt 
and the game will continue running. 


JUnit Design Decision...
Used Black Box technique as well as rigorous testing cases to ensure our program behaved the way it is supposed to. Moreover, invalid
test cases were included.
Also, we have included error handling especially to our board classes as they are more error prone to out of bound indexing issues.


Improvements Made Since Milestone 3...
- Fixed ReadMe file
- Fixed UML Diagrams - Added additional classes
- JUnit testing includes error handling
- Added missing tests
- Added comments as to what each test does
- Removed duplicate code between Minimax and MinimaxPlayerType

Reasons Why some issues were not resolved...
- Cannot remove current player and game state from Board Class as it requires changing all classes and tests. This was supposed to be
mentioned earlier.
- Some inherited methods were not tested as they were tested in main Class


Team Members Responsible for Deliverables....
1) JUnit ....... Lina
2) Save/Load  ....... Osama
3) Undo/Redo  ...... Brandon 
4) JUnit ..... Martin
5) UML Diagrams .... Osama

TO RUN
  Either open a command window, cd into the folder holding the respective .jar file (they are available in the
  main 'phoenix' folder), once in the folder, simply execute the command 'java -jar PhoenixMileStone3.jar' or 'java -jar Checkers.jar'
  or 'java -jar Othello.jar' or 'java -jar OthelloGui.jar' or 'java -jar TicTacToe.jar'.  The other option is to create a new project in 
  eclipse with the packages 'Boards', 'Games', 'minimax', 'PlayerTypes', and 'common', 'strategies', ' alternative', and 'GUI'  then create 
  empty classes with the names of each class found in each package and copy paste the code into those classes and run the project.

Diagrams
  All UML diagrams can be found in the respective project root folder.
  ie: Phoenix-master.zip/phoenix-master/MileStone3UML
==============================================================================================================================
                                                  Milestone 3
==============================================================================================================================
Third board game chosen is Checkers

Background on Checkers...
It is a 2 player board game. The game starts with each player having 12 discs of different colors (e.g player1 has black discs and player2 has red discs).
The board itself is made up of 64 squares. That is, it is an 8x8 board. Usually, the board consists of 32 white squares and 32 black squares alternating
between each color. Each player places their pieces on the 12 dark squares closest to them. Black starts the game first, alternating moves between players
every turn. Finally, discs are allowed to move on black squares only. Therefore, pieces always move diagonally. Discs are only allowed to move forward.
When a disc jumps over the opponent's dis, the other disc is captured and is taken out of the board. Only when a disc has reached the furthest row will
it be able to move back and forth. When the opponent's pieces has been captured, that's when the player wins the game.

Overall Design Decision...
Our design is focused around creating an abstract class in a package and creating other classes that inherits from it. This increases
readability, decreases chances of bugs, and is generally easier to improve and add more features to it.

Strategy Design Decision...
Two strategies were implemented: Random, and Minimax. The random strategy generates random moves with no evaluation to whether it was
a good or bad move. On the other hand, minimax, evaluates all possible moves, and picks the best move that would result in making the 
computer win. In this milestone, miniax has been improved on to suit Othello, Tic Tac Toe, and Checkers.
In milestone 3, a third strategy was implemented. It can be found in Strategies.Alternatives package. This strategy works by looking at all its
possible moves and instead of picking the best move, it picks the move that causes the biggest problem to the opponent. That is, it tries to block the
opponent's best move. In addition to that, it has been designed to accomodate cases where there is nothing to obstruct. In that case, instead of using
a random move, it will use minimax.

JUnit Design Decision...
Used Black Box technique as well as rigorous testing cases to ensure our program behaved the way it is supposed to. Moreover, invalid
test cases were included.


Improvements Made Since Milestone 2...
- Fixed ReadMe file
- Fixed UML Diagrams - Java API classes were missing
- JUnit testing includes invalid numbers
- ArrayIndexOutOfBound issue in Tic Tac Toe
- Initial values in Board class should be constants
- Game.getPlayerInfo() should not need to know if its an othello game or not. Consider refactoring
- Board class should be handling whos won or lost that logic should be handled by the game class
- Missing minimax AI in Tic Tac Toe

Team Members Responsible for Deliverables....
1) JUnit - Othello and TicTacToe Games ....... Lina
2) JUnit - Checkers classes ....... Osama
3) Strategy - Checkers ...... Brandon 
4) Board Game - Checkrs ..... Martin
5) UML Diagrams .... Osama

TO RUN
  Either open a command window, cd into the folder holding the respective .jar file (they are available in the
  main 'phoenix' folder), once in the folder, simply execute the command 'java -jar PhoenixMileStone3.jar' or 'java -jar Checkers.jar'
  or 'java -jar Othello.jar' or 'java -jar OthelloGui.jar' or 'java -jar TicTacToe.jar'.  The other option is to create a new project in 
  eclipse with the packages 'Boards', 'Games', 'minimax', 'PlayerTypes', and 'common', 'strategies', ' alternative', and 'GUI'  then create 
  empty classes with the names of each class found in each package and copy paste the code into those classes and run the project.

Diagrams
  All UML diagrams can be found in the respective project root folder.
  ie: Phoenix-master.zip/phoenix-master/MileStone3UML

==============================================================================================================================
                                                  Milestone 2
==============================================================================================================================
Two programs have been written to model specific board games.  Both AI implementations randomly find a move to perform.

Overall Design Decision ... 
Diving the classes the way we did (i.e. board, game, and player) decreases coupling, inhances readability, easier to debug,
and results in fewer bugs in general.

Minimax Design Decesion....
In addition to minimax, alpha beta prunning algorithm was used, which decreases the number of positions on board that are evaluated
by the board. This way, the program will have a faster response time. In simple terms, alpha beta prunning basically eliminates a position immediately if it finds a 
a better position on the board.

Othello Graphic User Interface (gui) design decisions
Made the gui using the swing framework because of its simplicity and the prior knowledge of it. The gui
shows the Model View Controller (MVC) design pattern to reduce coupling and make further changes much easier 
to impliment. In order to make the MVC pattern work the OthelloFrameView class listens to the a Game class.
The controller sends data from the view to the game when coordinates for a player move is needed. That is 
done through the event model using action listeners in the controller class while this same class waits, looping,
in the player part of the controller class.

JUnit Design Decision
Used Black Box technique as well as rigorous testing cases to ensure our program behaved the way it is supposed to. 

Known Issues in Milestone2...
  Regarding GUI...
    - GUI buttons don't show until you hover the mouse over.
    - Main function in OthelloController only launches Othello and always launches the GUI.
    - Othello keeps playing in the command line.

Imrovements made since Milestone 1....
1) Reduced code duplications between Othello and TicTacToe by creating 3 main abstract classes called Game, Board, and PlayerType. 
Classes related to Othello and TicTacToe inherit from the abstract classes and if necessary, implement certain abstract methods
that are related to specific games.

2) Fixed some bugs that already existed.

3) Added JUnit testing.

4) Made the games playable against users.

5) Created an AI Playing mode through minimax for Othello.

6) Created a GUI for Othello.

Team Members Responsible for Deliverables....
1) JUnit - Tic Tac Toe ....... Lina
2) JUnit - Othello ......... Osama
3) GUI - Othello ....... Martin
4) MiniMax - Othello ...... Brandon and Lina
5) UML Diagrams ..... Lina
6) Reducing redundancy and fixing bugs from MileStone 1 ..... Brandon

==============================================================================================================================
                                                  Milestone 1
==============================================================================================================================
Tic-Tac-Toe
  Board game which is played on a board of the size 3 rows by 3 columns.  Two players are involved, an 'X' player
  and 'O' player. The Objective of the game is for a player to place their piece in a straight line across any
  part of the map.  If neither player can complete that challenge, the game is a draw.
  
Othello
  Board game which is ordinarily played on a board of size 8 rows by 8 columns. Two players are involved with each game, a
  'Black' player and a 'White' player.  This implementation uses a 'B' to represent a black tile and a 'W' to
  represent a white tile in the console.  The GUI uses black and white ellipses.  Each player starts with half of 
  the available tiles.  The game consists of each player trying to flank their opponent's pieces of lines of pieces.  
  A flank is achieved when one opponent successfully encapsulates the other player's piece of chain of pieces with
  two of their own.  Each player can only place one piece at a time.  The game ends when either both players can 
  no longer make a legal move or either players run out of available pieces.
