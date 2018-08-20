# Maharajah and the Sepoys

This program was created as part of an evaluation of a bachelor course in engineering (at the 2014). Implement the board game "Maharajah and the Sepoys". It provides a user interface that allows you to play a game of "Maharajah and the Sepoys" between two people.

For example, the following image shown a virtual board of this game:

![Example of board of the Maharajah and the Sepoys](https://github.com/ediloaz/Maharajah-and-the-Sepoys/blob/master/settings/board.png)


## About of the *Maharajah and the Sepoys*

### History
It is believed that chess originated in eastern India during the Gupta empire in the 6th century, through its predecessor chaturanga, which had pieces to represent infantry, cavalry, elephants and chariots (equivalent to the current pieces pawn, knight, bishop and tower ). Then the game was adopted by the Persians around the year 600, and they were in charge of introducing it to Europe and Russia, later evolving to its current form and spreading its use all over the world.

Multiple variations have arisen from the game, based on the modification of the board (different number of squares or shape), addition of different pieces or modification of the rules. One of them is known as the **Maharajah and the Sepoys**, in which there are different armies for white and black. Black has complete training with the standard pieces, which are called sepoys. White's army consists only of a special piece called Maharaja, which can move as queen or as a horse. If Black's player does not make mistakes, he should always win, but if not, the Maharaja can break Black's lines and win.


### Rules
Except for the initial layout of the pieces and the movement of the Maharaja, the rules of the game are almost identical to conventional chess. In this variant there is no promotion of pawns (change the piece for another when it reaches the last row), nor the castling (special movement between the king and the tower).


## A mini-User's Manual

### Keys to use

#### For the Maharajah

![cursor-left](https://github.com/q2apro/keyboard-keys-speedflips/blob/master/single-keys-blank/200dpi/cursor-left.png)  ![cursor-right](https://github.com/q2apro/keyboard-keys-speedflips/blob/master/single-keys-blank/200dpi/cursor-right.png)  ![cursor-up](https://github.com/q2apro/keyboard-keys-speedflips/blob/master/single-keys-blank/200dpi/cursor-up.png)  ![cursor-down](https://github.com/q2apro/keyboard-keys-speedflips/blob/master/single-keys-blank/200dpi/cursor-down.png) 

And **Enter key** to select.

#### For the Sepoys
![a](https://github.com/q2apro/keyboard-keys-speedflips/blob/master/single-keys-blank/200dpi/a.png)  ![w](https://github.com/q2apro/keyboard-keys-speedflips/blob/master/single-keys-blank/200dpi/w.png)  ![s](https://github.com/q2apro/keyboard-keys-speedflips/blob/master/single-keys-blank/200dpi/s.png)  ![d](https://github.com/q2apro/keyboard-keys-speedflips/blob/master/single-keys-blank/200dpi/d.png) 
 
And **Space bar** to select.

#### Both users






The program only receive a parameter as input, the N. On windows you must open GHCI (consulting [prerequisites](https://github.com/ediloaz/n-queens-problem-HASKELL#prerequisites)) and on Linux you must open terminal and press *ghci*.

When GHCI is running you need write the following commands:
```
Prelude> :cd C:/codes/
Prelude> :load "haskell.hs"
Main> :run main
N:
```

In this point the user must define the parameter N. In the terminal on Linux it looks like this:

![Screenshot of an input](https://github.com/ediloaz/n-queens-problem-HASKELL/blob/master/settings/input.png)


## Output

Is shown from the terminal (or from GHCI on windows), and an example of 4 queens it looks like this:

![Screenshot of an output](https://github.com/ediloaz/n-queens-problem-HASKELL/blob/master/settings/output.png)

The approximate times of this program running (calculations and prints) on a conventional computer are close to those shown in the following table:

| N | miliseconds | 
| :---         |     :---:      | 
| `N=5`   | 292     | 
| `N=6`   | 356     | 
| `N=7`   | 417     | 
| `N=8`   | 819     | 
| `N=9`   | 3481     | 

In a smooth line chart:

![Screenshot of an output](https://github.com/ediloaz/n-queens-problem-HASKELL/blob/master/settings/graphic.png)


## Prerequisites

### Steps to install haskell on Linux
 1. Install haskell from terminal with the command: 
 ```
sudo apt-get install haskell-platform
```
 2. Install GHC (Glasgow Haskell Compiler) on [GHC for linux](https://www.haskell.org/ghc/)

### Steps to install haskell on Windows
 1. Install GHC (Glasgow Haskell Compiler) on [GHC for windows](https://www.haskell.org/ghc/) 


## Running

Is necessary Glasgow Haskell Compiler, is both an interpreter and native-code compiler that runs on most platforms. In [input](https://github.com/ediloaz/n-queens-problem-HASKELL#prerequisites) was showed how running.

## Built With

* [Sublime text](https://www.sublimetext.com/3)


## Authors

* **Edisson López** - *Developer* - [ediloaz](https://github.com/ediloaz)
* **Joel Barrantes** - *Developer* - [JoelBarrantes](https://github.com/JoelBarrantes)


See also the list of [repositories](https://github.com/ediloaz?tab=repositories) who I participated/created.
