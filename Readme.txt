# antcolony
Homework assignment for Data Structures and Algorithms in Java.

Some items were provided by instructor. This assignment is not meant to be copied and turned in as your own work but to see a solution. Utilize what I have done and make it better. There is always room for improvement.

Grading Requirements:

Simulation Events
	Keeps track of time (10 turns/day)
	Pheromone decreases by half each day
	Simulation ends properly
	Simulation capable of running in continuous mode or turn-by-turn mode
Output
	GUI is used (either the one I provided or one you built on your own). If you built your own GUI, it clearly indicates 		what is going on in the simulation.
Colony Environment
	A 27 x 27 grid is provided for the ants to move around in and perform their required duties. Any data structure used to model this grid is acceptable.
	The environment has some way to store food, pheromone, and ants.
General Ant Properties
	Ants have appropriate life spans, and die when life spans are over
	Ants perform one action per turn
Queen Ant
	Queen consumes 1 unit of food per turn
	Queen does not move from her original location
	Queen hatches one new ant each day
	Frequencies of each new ant type are correct
Scout Ants
	Capable of moving in all directions; movement not impeded by closed nodes
	Open closed nodes
	Newly opened nodes have appropriate chance of containing food
Forager Ants
	Maintains movement history
	Capable of random movement, when appropriate
	Move toward highest pheromone concentration when not carrying food
	Move toward queen when carrying food
	Deposit pheromone appropriately when carrying food
	No pheromone deposited when searching for food
	Capable of picking up food when in 	node with a food source
	Capable of transporting food
	Drop food when in queen's node
Soldier Ants
	Move randomly when not in node with a Bala ant
	Attack Bala when in node with one or more Bala ants
	Frequency of hits/frequency of misses is approximately 50/50
Bala Ants
	Move randomly when not in node with a non-Bala ant
	Attack non-Bala when in node with one or more non-Bala ants
	Frequency of hits/frequency of misses 	is approximately 50/50
	Bala ants enter colony only at 	periphery

  
