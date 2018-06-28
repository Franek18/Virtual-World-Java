# Virtual-World-Java
Implementation of Virtual World game in Java.
It's a turned-base game in which we can add organisms to the world by clicking on a board game.
Organisms divide on two type: animals and plants.
There are two main difference beetwen them.
  1. To proliferate animals must meet on a board and be the same type. Plants can proliferate independently.
  2. Plants can't move to another pole, animals have a free hand in that case.
Organisms classes have two common methods: action and collision which they inherit from a main class called Organism.
Every organism has a unique skill which allowed it to affect on a game and it's implemented in action or collision method.
The special type of animal is Human which is controll by player who determinate the direction of his move with arrow keys
and activate his speciall ability.
