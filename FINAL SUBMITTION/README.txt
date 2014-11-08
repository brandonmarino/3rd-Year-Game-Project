Phoenix
Milestone 2
Project Authors: Lina El Sadek, Brandon Marino, Martin Massie, Osama BuHamad
=======

Two programs have been written to model specific board games.  Both AI implementations randomly find a move to perform.

Overall Design Decision ... 
Diving the classes the way we did (i.e. board, game, and player) decreases coupling, inhances readability, easier to debug,
and results in fewer bugs in general.

Minimax Design Decesion....
In addition to minimax, alpha beta prunning algorithm was used, which decreases the number of positions on board that are evaluated
by the board. This way, the program will have a faster response time and is less error prone by simplifying decreasing the overall
calculations the program has to do. In simple terms, alpha beta prunning basically eliminates a position immediately if it finds a 
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

TO RUN
  Either open a command window, cd into the folder holding the respective .jar file (they are available in the
  main 'phoenix' folder), once in the folder, simply execute the command 'java -jar project-name.jar'.  
  The other option is to create a new project in eclipse, then close eclipse and copy and paste our project root
  'Games' into the new project root you created. Ensure overwrite the .settings folder, .classpath and .project 
  files.
  For Othello-Prompt, run the Main in src.Games.Othello
  For TicTacToe-Prompt, run the Main in src.Games.TicTacToe
  For Othello-GUI, run the Main in src.GUI.OthelloController

Diagrams
  The UML diagrams for Tic-Tac-Toe and Othello can be found in their respective project root folders.
  ie: Phoenix-master.zip/phoenix-master/Othello or Phoenix-master.zip/phoenix-master/Tic Tac toe
