# The-Maze-Project
This project was done as part of the "Advanced Topics in Programming" course. In this project I create a maze, a matrix of zeros and ones,
so that one represents a wall in the maze and one represents a path through which you can pass.
In addition, I created a class that generates a maze by "Recursive division method",
one of the "Maze generation algorithm" as you can see here-https://en.wikipedia.org/wiki/Maze_generation_algorithm.  
  
In the project there are three search methods - Breadth-first search, Depth-first search and Best-first search.
With the help of these search algorithms it is possible to look for ways to solve the maze.
So we can search for these algorithms in the game, the maze, i used the adapter design pattern.
Given additional games, I will be able to adapt the above search methods for it with the help of this design pattern.
  
  
The selection of the search algorithms will be done at runtime according to the bridge design pattern.
After implementing a maze and ways to solve it, it will be possible to compress the data of the maze and transfer it from the client to the server,
so that the server will solve the maze according to the above search methods, and then return a solution to the client.

