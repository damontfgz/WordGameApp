**Requirements Assignment 5**

ahusain9

2/7/2020

1.  When the application is started, the player may choose to (1) Play a word
    game, (2) View statistics, or (3) Adjust the game settings.

>   **Design Explanation:** To realize this requirement, I have added a
>   ‘currentPlayer’ method to the Application class to track the signed in
>   player, and then the operations can perform are viewMainScreen or start Game
>   or may choose to exit the application. So, I have added these methods to
>   track Players and all the activities.

Application starts with current player

![](media/75421c623eda1e1e6fe6d3061220c7b8.png)

>   **Design Explanation:** Player can start the Game. Player may choose to
>   playGame or viewStatistics or adjust Game settings. So, I have added these
>   properties to the work with the Player in this class.

![](media/a1398b80f585420c9bf0a238b0f26ff7.png)

1.  When choosing to *adjust the game settings,* the player (1) may choose for
    the game to end after a certain *number of minutes,* from 1 to 5, defaulting
    to 3, (2) may adjust the *size of the square board,* between 4(x4) and
    8(x8)*,* defaulting to 4, and (3) may adjust the *weights* of the letters of
    the alphabet between 1 and 5, defaulting to 1.

>   **Design Explanation**: This requires a separate class to represent game
>   settings and allow all the operations through its methods. So, I have added
>   a class Game Setting and the methods to change all the settings. I have also
>   added a variable “GameTime”, which can be changed by player through
>   changeGameTime method.

>   Here is the range and default values for each item settings which will be
>   part of the implementation.

![](media/eb26b7830772a90d950601b91c4777c0.png)

1.  When choosing to *play a word game,* a player will:

    1.  Be shown a ‘board’ of randomly generated letters.

    2.  Be shown a timer counting down the *number of minutes* available for the
        game, as set in the settings.

    3.  Start with 0 points, which is not required to be displayed during the
        game.

    4.  Until the game timer counts to zero and the game ends*:*

        1.  Enter a unique word made up of two or more letters on the board. The
            word must contain only letters from the board that are each adjacent
            to the next (horizontally, vertically, or diagonally) and a single
            letter on the board may not be used twice. The word does not need to
            be checked against a dictionary (for simplicity, we will assume the
            player enters only real words that are spelled correctly).

>   or

1.  Choose to re-roll the board at a cost of 5 points. The board will be
    re-created in the same way it is generated at the start of each game,
    replacing each letter. The timer will not be reset or paused. The player’s
    score may go into negative values.

>   or

1.  Choose to exit the game early.

2.  At the end of the game, when the timer has run out or the player chooses to
    exit, the final score for the game will be displayed.

3.  Each word will score 1 point per letter (‘Qu’ will count as 2 letters), and
    the cost for each board reset will be subtracted.

4.  After the player views the score, they will continue

>   **Design Explanation:** Here an additional class is required to represent
>   all activities happening during the game play. So, I have added one more
>   class Game here.

>   When the Game starts, it will display a board which can be handled by method
>   “DisplayBoard”. The game also needs a separate class Board which is designed
>   as part of next requirement. I have added a variable “Timer “which can be
>   changed through method “maintainTimer”. Similarly, a variable “Score” (which
>   is initially zero value) can be changed via method “calculateScore”.

>   When a player selects letters and play words, this will be handled in the
>   method “processBoard”. I have added one more method here “ResetBoard”, which
>   will manage the game when player selects to reroll the board. It will also
>   manage the points for every reset. Finally, when Game timer is over, or
>   player selects to exit the game it can be handled in a separate method
>   “ExitGame” which will take player to mainScreen of Application. Please note
>   that details of all methods described above will be part of this
>   implementation.

![](media/2957d6792f4363e4d1f2b3c198b8dc6f.png)

1.  Whenever the board is generated, or re-generated it will meet the following
    criteria:

    1.  The board will consist of a square full of letters. The square should
        have the number of letters, both horizontally and vertically, indicated
        by the *size of the square board* from the game settings (4x4, 5x5, 6x6,
        7x7, or 8x8).

    2.  ⅕ (rounded up) of the letters will be vowels (a,e,i,o,u). ⅘ will be
        consonants.

    3.  The letter Q will be displayed as ‘Qu’ (so that Q never appears alone).

    4.  The location and letters should be randomly selected from a distribution
        of letters reflecting the *weights* of letters from the settings. A
        letter with a weight of 5 should be 5 times as likely to be chosen as a
        letter with a weight of 1 (assuming both are consonants or both are
        vowels). In this way, more common letters can be set to appear more
        often.

    5.  A letter may appear on the board more than once.

>   **Design Explanation:** Here we have another class “Board “which handles all
>   the activities of a square board.

>   I have added variable “Letter “which represents, Letter displayed on a
>   board. Variable “size” represents size of the board. For example, a size of
>   4 means it will be 4x4 square board. I also have a weight of character
>   variable which will be considered while displaying board to the player and
>   can be changed through Game settings.

>   As the name suggests, ResetBoardCount will be used to maintain the count,
>   how many times board has been reset. I have also added a variable
>   “wordWithCount” which track the count as to how many times this word is used
>   in all the games. For example if word “table” is used 4 times till the time
>   , it will add entry it this map as (“table”,4).If this word is used next
>   time , it will simply add one more in the count and change the map entry for
>   this as (“table,5) .It’s not game specific. So, it should be a static
>   variable maintained across all the games.

>   SquareBoard has only 2 activities such as display and reset the board. These
>   functions can be maintained through 2 separate methods. “displayBoard” and
>   “resetBoard”.

![](media/41212b1765cd56e8eb84e83e845ff1ab.png)

1.  When choosing to view statistics, the player may view (1) *game score
    statistics*, or (2) *word statistics*.

>   **Design Explanation:** Here I have added an Interface “Statistics” which
>   will be implemented by 2 classes “Game Statistic” and “Word Statistics”.

![](media/5383d25d1faa4e707435d290bf2bdd8b.png)

1.  For game score statistics, the player will view the list of scores, in
    descending order by final game score, displaying:

>   The final game scores

>   The number of times the board was reset

>   The number of words entered in the game

The player may select any of the game scores from this list to view the settings
for that game’s *board size, number of minutes,* and the *highest scoring word*
played in the game (if multiple words score an equal number of points, the first
played will be displayed)*.*

**Design Explanation:** I have added a class “Game Statistics” which will handle
all the statistics related to game. I also have added a method “viewScore” which
will display list of scores in descending order Number of times board was reset
will be handled by method “viewTotalNoBoardReset”. Similarly, the number of
words entered will be handled by method “TotalNoOfWords”. Every game will have a
link to see all settings such as board size, noOfMinutes and highest scoring
word played. These activities are handled in the method
“viewSettingsForEachScore” which will be called inside method “viewScore” when
player selects the game. Details of all these methods are not shown in design
since it will be handled in the implementation.

![](media/1c50997510760630bbde27b7a3c5ccf9.png)

1.  For the word statistics, the player will view the list of words used,
    starting from the most frequently played, displaying:

>   The word

>   The number of times the word has been played, across all games

>   **Explanation:** I have added here an another class “Word Statistics” to
>   represent all the statistics related to word which will implement the
>   interface “Statistics”.

>   This class has only one method “viewPlayedWord” which will display word and
>   counts (no of times this word has been played across all games).

![](media/91662379b0f2c65700787e92ae0350a2.png)

Below diagram represents statistics interface with 2 implementation classes.

![](media/ea594699624210fcf18b2ff5ff0d374a.png)

1.  The user interface must be intuitive and responsive.

**Explanation:** This depends on GUI implementation and not included in the
design.

1.  The performance of the game should be such that students do not experience
    any considerable lag between their actions and the response of the
    application.

**Explanation:** This depends on the actual implementation of game application.
Not a design part.

**Final diagram**: shows classes with all the operations and relations among
them.

>   Player starts application which has Game.

>   Game has SqaureBoard and Game Settings.

>   Player can Play Game. Player can play many Games.

>   Player can change Settings.

>   Player can view statistics which can be Game or Word Statistics.

![](media/c0ef7d18e716f45d4c6e1ae5fa4dc28b.png)

1.  For simplicity, you may assume there is a *single system* running the
    application.
