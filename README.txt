Phoenix
Milestone 1
Project Authors: Lina El Sadek, Brandon Marino, Martin Massie, Osama BuHamad
=======

Two programs have been written to model specific board games.  Both AI implementations randomly find a move to perform.

Tic-Tac-Toe
  Board game which is played on a board of the size 3 rows by 3 columns.  Two players are involved, an 'X' player
  and 'O' player. The Objective of the game is for a player to place their piece in a straight line across any
  part of the map.  If neither player can complete that challenge, the game is a draw.
  
Othello
  Board game which is ordinarily played on a board of size 8 rows by 8 columns. This implementation lowers the 
  size of the board to 4 rows by 4 columns for logistical purposes.  Two players are involved with each game, a
  'Black' player and a 'White' player.This implementation uses a 'B' to represent a black tile and a 'W' to
  represent a white tile.  Each player starts with half of the available tiles.  The game consists of each player
  trying to flank their opponent's pieces of lines of pieces.  A flank is achieved when one opponent successfully
  encapsulates the other player's piece of chain of pieces with two of their own.  Each player can only place one
  piece at a time.  The game ends when either both players can no longer make a legal move or both players run out
  of available pieces.

TO RUN
  Either open a command window, cd into the folder holding the respective .jar file (they are available in the
  main 'phoenix' folder), once in the folder, simply execute the command 'java -jar project-name.jar'.  
  The other option is to create a new project in eclipse with the classes 'board', 'move' and 'game' then copy
  paste the code into those classes and run the project.

Diagrams
  The UML diagrams for Tic-Tac-Toe and Othello can be found in their respective project root folders.
  ie: Phoenix-master.zip/phoenix-master/Othello or Phoenix-master.zip/phoenix-master/Tic Tac toe