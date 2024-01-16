# Math Basic

## Overview
This repository contain simple collections of algorithms to represent math basic game.

### Arithmetic

Generate row x column metric. Each cell will be filled by simple arithmetic calculation and numeric as answer, 
for example: the question is: 10 x 2, and then for the answer = 20 

```agsl
13 + 3 = 16;  7 + 24 = 31;   1 + 4 = 5;    5 + 3 = 8;     1 + 8 = 9;   16 + 6 = 22;
 
21 + 0 = 21;  15 + 5 = 20;  11 + 16 = 27;  9 + 20 = 29;  11 + 2 = 13;  10 + 9 = 19;
 
7 + 24 = 31;  11 + 2 = 13;   8 + 22 = 30; 24 + 0 = 24;    2 + 5 = 7;   13 + 3 = 16;
 
10 + 9 = 19;  24 + 24 = 48;  1 + 4 = 5;    1 + 16 = 17;   8 + 22 = 30;  2 + 1 = 3;
 
5 + 3 = 8;    16 + 6 = 22;  24 + 0 = 24;  24 + 24 = 48;  11 + 16 = 27;  2 + 1 = 3;
 
1 + 8 = 9;    9 + 20 = 29;  15 + 5 = 20;  21 + 0 = 21;    2 + 5 = 7;    1 + 16 = 17; 
```
User need to match the question vs answer, for example for the above metric, cell[0,0] = cell[2,5]

#### Get started
Go to Game.java under arithmetic package and you will see 2 method:
- generate, used to generate metric based on following required parameters
  - r : int, number of rows
  - c : int, number of column
  - min : int, question minimum number
  - max : int, question maximum number
  - operator : Operator, arithmetic operator
- answer, validate user answer based on metric index
  - q : Point, question index
  - a : Point, answer index