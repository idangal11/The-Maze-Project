# The-Maze-Project
This project was done as part of the "Advanced Topics in Programming" course. In this project I create a maze, a matrix of zeros and ones,
so that one represents a wall in the maze and one represents a path through which you can pass.
In addition, I created a class that generates a maze by "Recursive division method",
one of the "Maze generation algorithm" as you can see here-https://en.wikipedia.org/wiki/Maze_generation_algorithm.  
  
In the project there are three search methods - Breadth-first search, Depth-first search and Best-first search.
With the help of these search algorithms it is possible to look for ways to solve the maze.
So we can search for these algorithms in the game, the maze, i used the adapter design pattern.
Given additional games, I will be able to adapt the above search methods for it with the help of this design pattern.
  
  
In The Final part i created a pair of servers that provide service to a multitude of clients, using threads. The first server role to generate
mazes on demand. The second server's role is to solve mazes. When a client connects to a server that creates mazes
It will send him the parameters to create the maze and will receive back a maze object. When a client connects to the solver server, 
the server will send him an existing maze and receive back from the server a solution to the maze. to shorten the time
The communication will have to compress the information that passes between them before sending. The receiving party will open the
The compression and will benefit from the information.

## Main Packages
#### MazeGenerators:
* Maze class- start and goal position, two-dimensional matrix of integers.
* MyMazeGnerator- generates a maze by "Recursive division method".
* SimpleMazeGenerator- generate a maze by putting randomly walls. 
#### Search:
* Classes of Search Algorithms- Breadth-first search, Depth-first search and Best-first search.
* SearchableMaze- the adapter class. with help of this class we can search in given game (like maze).
* Solution class- track the path and return a solution.
#### IO
* MyCompressorOutputStream class- unique way to compress maze data.
* MyDecompressorInputStream class- unique way to decompress maze data.
#### Server
* ServerStrategyGenerateMaze class- generate maze after recieve parameters from client.
* ServerStrategySolveSearchProblem class- return solution according to the three search algorithm.
#### Client
* Client class- connect to server with IP and Port after creating a socket.

