## Assignment 5 - Design Information

**1. When the application is started, the player may choose to (1) Play a word game, (2) View statistics, or (3) Adjust the game settings.**
>This part is handled entirely by the GUI and is not represented in my design. However, my implementation does show the backend. 
>When ‘Play a word game’ is selected, the `Player` class is initialized that in turn sets up other required classes such as `Settings` and `Game` to start the game. The `Game` class initializes the `Board` class using the integer `boardSize` and integer array `weights` from the `Settings` class. The `Board` creates a 2D char array that hold the letters to be displayed in the GUI, which is sent back to the GUI for displaying the board to the user.

>When `View Statistics` is selected, the UI (implementation not shown) then shows two options to choose from – ‘Word Statistics’ and `Game Statistics` which will be discussed in detail later.

>When `Adjust the game settings` is selected, (UI implementation not shown) options to set game duration, size of square board and weights of the letters are shown.

**2. When choosing to adjust the game settings, the player (1) may choose for the game to end after a certain number of minutes, from 1 to 5, defaulting to 3, (2) may adjust the size of the square board, between 4(x4) and 8(x8), defaulting to 4, and (3) may adjust the weights of the letters of the alphabet between 1 and 5, defaulting to 1.** 

>To implement this I added 3 private attributes in the `Settings` class. They are : `gameDuration` which is an integer initialized with a default value 3, `boardSize` which is an integer initialized with a default value 4 and `weights` which is an integer array of size 26 initialized with a default values of 1. All range validation is handled by the GUI.


**3. When choosing to play a word game, a player will:**
>**a. Be shown a ‘board’ of randomly generated letters.**
As discussed above in 1., when the player chooses to play a game the `Board` class creates an array of randomly generated 2D letters and returns it to the GUI. 

>**b. Be shown a timer counting down the number of minutes available for the game, as set in the settings.**
This part will mostly be implemented by the GUI. The number of minutes for a game has been stored as an integer `gameDuration` in the class `Settings`. When the player starts the game, the UI starts the timer and uses the `gameDuration` from the `Settings` class to limit the player’s game time. 

>**c. Start with 0 points, which is not required to be displayed during the game.**
The points are stored in the `Game` class as a private attribute `totalScore` of type integer, which will be initialized as 0. This `totalScore` will be updated during the game.

>**d. Until the game timer counts to zero and the game ends:**  

>**i. Enter a unique word made up of two or more letters on the board. The word must contain only letters from the board that are each adjacent to the next (horizontally, vertically, or diagonally) and a single letter on the board may not be used twice. The word does not need to be checked against a dictionary (for simplicity, we will assume the player enters only real words that are spelled correctly).**
>The user can enter the word using the GUI which is then sent to the `Player` class. The `Player` class in turn sends this word to the `Game` class. The `Game` class validates this word using the `validateWord()` method from the `Board` class. The `validateWord()`  accepts a String (word entered by the player) as an input argument and returns a `Boolean` which will be true for a valid word and false otherwise. Upon validation, for a valid word, the `Board` also marks the corresponding letters in the board as `*` which prevents the players from playing the same word again. If the word is invalid, this method will not change anything. 
>In the `Game` class, for a valid word, we increment the integer `totalScore` and the integer `numWords` (number of words played) by 1. The `Game` class also has an attribute `highestScoreWord` and `currentHighestScore` which represents the word with the highest score played by the player in this game and the corresponding score for that word respectively. After validation, if the word entered by the user passes validation, it also checks if this word has a higher score than `currentHighestScore` in which case it updates the `highestScoreWord` to this word. This also ensures that if two words have the same score, we consider the word that was played first. If the word is invalid, we do not update anything. 
>In the Player class, with every word played we also update the attribute `wordCountStatistics` which is a Map keyed by the word and value that represents the number of times the word has been played. If the word is valid, we check to see if the Player has played that word before and exists in the map. If the word has been played before, we fetch the count for that word and increment by 1. If the word is being played for the first time, we simply put the word and the count of 1 for that word in this map and return to the GUI.  
>Based on the Integer value that is returned to the GUI, we either display an error message to indicate an invalid input or display the updated board, which can now have a few letters replaced by * to prevent users from using the same letters.

>**ii. Choose to re-roll the board at a cost of 5 points. The board will be re-created in the same way it is generated at the start of each game, replacing each letter. The timer will not be reset or paused.  The player’s score may go into negative values.**
>The `Game` class has a method reset which calls the `reset` method in the Board class. The `reset` method reinitializes the 2D char array and returns a new board. On resetting the board, the `Game` class deducts 5 points from the `totalScore` which could potentially lead to negative scores for that game. We also store `5` as an attribute `RESET_PENALTY` with a constant value in the `Game` class. In all this we let the timer in the UI continue, without pausing or resetting it. Also, any ‘*’ for previously played letter is cleared and the board is again reset with new letters. 

>**iii. Choose to exit the game early.**
>When the player chooses to exit the game early, it will first create the `GameScoreStatistic` using some of the attributes from the `Game` and `Settings` class and stores the `GameScoreStatistic` in the attribute `gameScoreStatistics`. It then calls the `end()` method of the `Game` class. Inside the `end()` method in the `Game` class we clear all fields, that is rest score, count attributes to their initial values. After the exit, the UI takes the player back to the main menu.

>**e. At the end of the game, when the timer has run out or the player chooses to exit, the final score for the game will be displayed.**
>When either the user exits or the timer runs out, we save the `GameScoreStatistic` for that game in the `Player` class' attribute `gameScoreStatistics` which is a map with the gameId: Integer as the key and the GameScoreStatistic as the value. The score of the game can be fetched from the `Game` class attribute `totalScore` and displayed by the GUI.

>**f. Each word will score 1 point per letter (‘Qu’ will count as 2 letters), and the cost for each board reset will be subtracted.**
>This logic can be implemented in the `Game` class which maintains the score of the game. For a valid input word, it’s score is the length of the word. This will ensure that we always attribute 2 points to ‘Qu’.

>**g. After the player views the score, they will continue back to the main menu.**
The final score is displayed using `totalScore` value in the Game class and then the UI handles the implementation part where after viewing the player can continue back to the main menu.

**4. Whenever the board is generated, or re-generated it will meet the following criteria:**
>**a. The board will consist of a square full of letters. The square should have the number of letters, both horizontally and vertically, indicated by the size of the square board from the game settings (4x4, 5x5, 6x6, 7x7, or 8x8)**
>The `Board` class has a 2D array of char that holds the letters that need to be displayed to the user. The size of this array is determined by the size parameter that is passed on by the `Game` class during the `Board` construction.

>**b. ⅕ (rounded up) of the letters will be vowels (a,e,i,o,u). ⅘ will be consonants.**
>This will be implemented by the logic I will add to the private function `generateLetters()` in the `Board` class where `1/5` x board size’ squares (rounded up) will be filled by only picking letters from the list ‘a,e,i,o,u’. The rest of the board will only be filled by consonants. 

>**c. The letter Q will be displayed as ‘Qu’ (so that Q never appears alone)**
>This can be enforced in the GUI, such that every occurrence of the letter `Q` in the 2D char array returned to the GUI will be substituted by `Qu`.

>**d. The location and particular letters should be randomly selected from a distribution of letters reflecting the weights of letters from the settings. A letter with a weight of 5 should be 5 times as likely to be chosen as a letter with a weight of 1 (assuming both are consonants or both are vowels). In this way, more common letters can be set to appear more often.**
>The weights of the letter is passed by the `Game` class to the class `Board` during the construction. This is an array of integers, where the value at an index represents the weight of the letter at that index. For e.g. the weight of letter `a` will be stored in 0th index, `b` in 1st index etc. Once we have the set of vowels and consonants from step c above, we use those with their corresponding weights in our probability function to ensure that we perform weighted random selection.

>**e. A letter may appear on the board more than once.**
>The step d above itself ensures that letters with higher weights are selected more often and we do not restrict the number of times letters are picked by chance.


**5. When choosing to view statistics, the player may view (1) game score statistics, or (2) word statistics.**
>This is handled by the UI which is not shown. The backend after selection is discussed below.

**6. For game score statistics, the player will view the list of scores, in descending order by final game score, displaying:**
>**1. The final game score**
**2. The number of times the board was reset**
**3. The number of words entered in the game**
**The player may select any of the game scores from this list to view the settings for that game’s board size, number of minutes, and the highest scoring word played in the game (if multiple words score an equal number of points, the first played will be displayed).**

>When a game ends, the `Player` class creates the `GameScoreStatistic` from `totalScore`, `numResets`, `numWords`, `highestScore` attributes from the `Game` class as well as the `boardSize` and `gameDuration` attributes from the `Settings` class and adds that `GameScoreStatistic` to a map `gameScoreStatistics` keyed by a randomly generated Integer key. This `gameScoreStatistics` will be displayed in the descending order of the `totalScore`. We can achieve this by storing the stats in some sorted collection such that any modification of this collection will always preserve the ordering. When the user clicks on one of those scores, the UI can display the other information like boardSize, number of minutes and highest scoring word using the key of this GameScoreStatistic. It can simply fetch the object in question from the map `gameScoreStatistics` using the key and get the other required attributes from that object. 

**7. For the word statistics, the player will view the list of words used, starting from the most frequently played, displaying:**
>**1. The word**  

>See below.  

>**2. The number of times the word has been played, across all games**  

>Both (i) and (ii) are handled by a map that stores ALL entered and VALID words. This map is stored as an attribute `wordStatistics` of the `Player` class. For every valid word played, we update the map either incrementing the count for an already played word or storing the word and the count of 1 for every new word. We can achieve this by using some sorted collection for the attribute `wordStatistics`, such that any modification of this collection will always preserve the ordering.


**8. The user interface must be intuitive and responsive.**
>Not represented in my design as it will be handled by the GUI implementation. 

**9. The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application**.
>By using the correct data structures and efficient algorithms in the implementation, we will ensure that there is no excess overhead in running the game. As an example, after a board is initialized, during the gameplay, the board should not be repeatedly generated (unless `reset()` is called). Also, for displaying stats by the descending order of their count/score we can use a suitable data structure that guarantees preserving the sort order instead of sorting it right before the display since if the size of the collection is very large, the sorting could lead to a bad performance.  

**10. For simplicity, you may assume there is a single system running the application.**
>The application has been designed considering a single system. For a mutli-system design, there could potentailly be changes for better performance and eliminate bottleneck.