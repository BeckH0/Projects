# Project

# Fantasy Football Game

While in England, I have come to learn that Fantasy Premier League (FPL) is a very common and well liked game, so I wanted to recreate this.

This program reads in a file containing the league table and then allows the user to change and alter the table in whatever way they need.

_Note: This program uses a Python class._

## Methods to edit the league
- Add a player
- Remove a player
- Add to a player's points tally
- Remove from a player's points tally
- Sort the table from most points to greatest
- Compare two players' point tallies
- Display which place a competitor is in

## How It Works

Any time the league admin wants to edit the table, the program reads in a file containing each member and their point tally that week and then runs whatever function the user called. It edits/reads the table in whatever way it needs to, and then prints out a new table every time. Each player is on seperate line (see 'league_table.txt'). This will look like:

'player1', 52 <br>
'player2', 58 <br>
'player3', 46 <br>

It is assumed that each line *is* in this format.

## How It Runs

Already written in the file is every function and the command line calls, however, in order to use the function you must first uncomment it, as well as the correct sys.argv[] statements. Once this is done, you can then call the program in command line.

### Example - 

If you want to add a player to the league, you would first uncomment the 'add_player' function line and the correct sys.argv[] statements at the bottom of the file, and then run the command with your command line prompt looking something like this <br>
*python3 fantasy_league.py league_table.txt 'player_name' 'player_points'*

