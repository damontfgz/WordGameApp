_1. When the application is started, the player may choose to (1) Play a word game, (2) View statistics, or (3) Adjust the game settings._  
   * An `Application` class is created with `startApplication()` operation. Classes related to game, statistics and setting are explained below
   * `setting()`, `playGame()` and `showStat()` are created different options player choose.

_2. When choosing to adjust the game settings, the player (1) may choose for the game to end after a certain number of minutes, from 1 to 5, defaulting to 3, (2) may adjust the size of the square board, between 4(x4) and 8(x8), defaulting to 4, and (3) may adjust the weights of the letters of the alphabet between 1 and 5, defaulting to 1._
   * A `Setting` class is created with `boardSize`, `time` and `alphabet` attributes.
   * `boardSize` is set to 4 by default, player can call `changeSize()` to choose prefer size
   * `time` is set to 3 by default, player can call `changeTime()` to choose prefer time.
   * `alphabet` is use to hold letter to weight mapping, with default value 1 for all letters. Call `changeWeight(string, int)` to set weight for specific letter.
    
_3. When choosing to play a word game, a player will:_
   * `WordGame` class is created with `playGame()` operation. `palyGame()` is called to start word game.
   `playGame()` take `boardSize`, `time` and `alphabet` from `Setting` and initialize corresponding attributes.
   
     _a. Be shown a ‘board’ of randomly generated letters._
     * `WordGame` have a attribute `board: string[][] ` with either 4*4 as default size, or chosen size set by player. This `board` will be filled with randomly generated letters 
   
     _b. Be shown a timer counting down the number of minutes available for the game, as set in the settings._
     * A utility class `Minute` is created to record timing of the game
     * `WordGame` class have a attribute `time: Minute`, which set to initial value set by player.
   
     _c. Start with 0 points, which is not required to be displayed during the game._
     * `WordGame` class have a attribute `socre: int`, which has initial value of 0. Score will be updated eagerly every time a correct word is entered or reroll() operations is called.
   
     _d. Until the game timer counts to zero and the game ends:_
    
      _i. Enter a unique word made up of two or more letters on the board.  The word must contain only letters from the board that are each adjacent to the next (horizontally, vertically, or diagonally) and a single letter on the board may not be used twice.  The word does not need to be checked against a dictionary (for simplicity, we will assume the player enters only real words that are spelled correctly). or_
     * `WordGame` have a attribute `word: string` which hold the entered word from player. 
     * `WordGame` also have a operations `checkWord()` which check whether the word enter follows the game rule or if the word is valid. If valid, then corresponding score will be updated to the `score` attribute.
   
     _ii. Choose to re-roll the board at a cost of 5 points.  The board will be re-created in the same way it is generated at the start of each game, replacing each letter.  The timer will not be reset or paused.  The player’s score may go into negative values.
or_
      * `WordGame` have a operation `reroll()` which will fill `board:string[][]` with different letters and update `socre`.
   
     _iii. Choose to exit the game early._
     * `WordGame` has a `exit()` operation that can exit before game ends.  
   
     _e. At the end of the game, when the timer has run out or the player chooses to exit, the final score for the game will be displayed._
    
     * `exit()` will be called when `time` reach 0 or player ends the game. `exit()` will stop the `time` from counting down, as player may exit before time is used up. 
     * Also `exit()` will display final score and time used by player through GUI.
  
     _f. Each word will score 1 point per letter (‘Qu’ will count as 2 letters), and the cost for each board reset will be subtracted._
      * Mentioned previously, this function is included in the `checkWord()` and `reroll()` operations.
  
     _g. After the player views the score, they will continue back to the main menu._
     * `WordGame` have `backToMenue()` operation that return to the welcome page of the application

_4. Whenever the board is generated, or re-generated it will meet the following criteria:_
  * since regarding what to fill in board, there is no difference between first generate or following re-generate, only one operation `reroll()` is created.
    
    _a. The board will consist of a square full of letters.  The square should have the number of letters, both horizontally and vertically, indicated by the size of the square board from the game settings (4x4, 5x5, 6x6, 7x7, or 8x8)._  
    * As mentioned, when `WordGame` class is first created, a `board:string[][]` is initialized with corresponding `boardSize` value. Then `reroll()` is called to fill in letters that satisfied game rules.
    
    _b. ⅕ (rounded up) of the letters will be vowels (a,e,i,o,u). ⅘ will be consonants._
    * this rule will be handled in `reroll()` operation
    
    _c. The letter Q will be displayed as ‘Qu’ (so that Q never appears alone)._  
    * this rule will be handled in `reroll()`, just append `u` every time `Q` is selected
    
    _d. The location and particular letters should be randomly selected from a distribution of letters reflecting the weights of letters from the settings.  A letter with a weight of 5 should be 5 times as likely to be chosen as a letter with a weight of 1 (assuming both are consonants or both are vowels).  In this way, more common letters can be set to appear more often._
    * this rule will be handled in `reroll()` by referring to `alphabet` from `Setting`. 
    
    _e. A letter may appear on the board more than once._
    * handled in `reroll()` operation, do not need to dudup.

_5. When choosing to view statistics, the player may view (1) game score statistics, or (2) word statistics._
  * `GameStat` and `WordStat` classes are created to satisfied this requirements.

_6. For game score statistics, the player will view the list of scores, in descending order by final game score, displaying:_
   *   
      a. The final game score_
        * `GameStat` have `finalScore` attribute to save score when game ends.
      
      _b. The number of times the board was reset_
     * `GameStat` have `resetCount` to represent number of reset used by player.
       
      _c. The number of words entered in the game_
     * `GameStat` have `numOfWord` to represent this value  

_The player may select any of the game scores from this list to view the settings for that game’s board size, number of minutes, and the highest scoring word played in the game (if multiple words score an equal number of points, the first played will be displayed)._
   * `GameStat` have `boardSize`, `usedTime` and `topScoredWord` to represent this information.
   * `GameStat` have `showSetting()` operation to display necessary information

_7. For the word statistics, the player will view the list of words used, starting from the most frequently played, displaying:_
   * `WordStat` class is created to keep tracked all the words played and their corresponding counts. 
   
     _a. The word_
  
      _b. The number of times the word has been played, across all games__
     * `WordStat` have a attribute `wordMap` that maintain mapping relationship between `word` and `word frequency`
     * `showWordStat()` is called if player what the check the word ranking
     * `update(stirng)` is called to update word frequency when a valid word is entered by player. Every time a valid word is entered, if the word has been played before, its corresponding freq++, otherwise a new word with freq = 1 mapping will be created. 

_8. The user interface must be intuitive and responsive._
  * This part is handled in UI design. Player are given bottoms that are necessary and simple to understand.
ex. player hit play button will called `playGame()` operation, which will call `reroll()` `startTimer()` and related sub functions. But only thing player will see is play button.

_9. The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application._
  * To achieve good user experience, we need to pay attention to `checkWordStat()` and `checkGameStat()` operation as they need they may involve most computation as data grows. Although `playGame()` require real time response, it only need to fill in limited space and it take constant time.
The other operation can run behind the scene, which will hide the latency.
  * Mapping in `WordStat` class will be updated eagerly. Meanwhile, the ranking will also be computed eagerly, this can also hide latency as play will not check stat while they are entering words.
This way `checkWordStat()` operation will only need to display the ranking result without much computation.
  * Similarly, a `GameStat` object will be created every time a game is finished. Each `GameStat` will be stored and ranked by their final score eagerly, so that when `checkGameStat()` is called, there will not be much delay.

_10. For simplicity, you may assume there is a single system running the application._
  * Only one game is running on system, so that all data can be save in memory
