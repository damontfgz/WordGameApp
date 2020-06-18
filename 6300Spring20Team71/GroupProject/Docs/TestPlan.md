# Test Plan

**Author**: Team71

## 1 Testing Strategy

### 1.1 Overall strategy

*White-box testing is adopted for unit level testing. To make sure the main
functions for each function in different classes are correct and all edge cases
are covered, it requires the knowledge of how each operation is developed. So
all white box testings are developed in parallel with each function unit.*

*Black-box testing is adopted for both integration and system level, as at
system level we are more focusing on the functions and input/output, which will
be developed according to requirement/specifications provided and all possible
use cases.*

*Regression-testing will be done in the later stages of the development, mainly
after the initial version is finished(unit level testing is done) and start
integration/system level testing. During higher level testing, if any
modification is required at unit and integrations level, regression test need to
be performed at the specific modified blocks.*

*Each member will be reasonable for the development of certain units and
corresponding unit level white-box testing. And they will also be reasonable for
related regression testing later on.*

### 1.2 Test Selection

*At unit level, test cases will be generated for each possible statement to
ensure statement coverage for white-box testing. This ensures all statements for
each function unit are verified at early stages.*

*State Transition testing will be adopted for Black-box testing at integration
and system level. First based on required specifications and use cases,
reasonable partitions are identified and divided. For example, at integration
level, in the Board class, see if generateLetter() functions and reset()
functions are working together properly. At system level, if playGame() can
properly generate board and letter based on player changeSetting() through
GameSetting class. State transition testing helps to check if operations in each
specific state are correctly reflected in other related states.*

### 1.3 Adequacy Criterion

*Statement coverage is adopted for this project to make sure each statement in
the function unit is verified. This should ensure over 90% of low level
functions are covered.*

*At a higher level, test cases are generated based on the understanding of
specifications and use cases, so that possible combinations of function and
interaction between classes are captured and verified.*

### 1.4 Bug Tracking

*Google sheet will be used for tracking bugs and enhancement requests. Every bug
and enhancement will be filled in with new col with necessary information,
example are given below. And Bug have higher priority over enhancements*

*Bugs:*

| *Description*                                    | *Due* | *Status* | *Assigned* |
|--------------------------------------------------|-------|----------|------------|
| *Default duration setting wrong*                 |       | *N*      | *Ahmed*     |
| *changeBoardSize() cannot save customized value* |       |  *Y*        | *Vipul*          |

*Enhancement:*

| *Description*                   | *Due* | *Status* | *Assigned* |
|---------------------------------|-------|----------|------------|
| *main page color change to red* |       | *N*      | *Feng*     |

### 1.5 Technology

*For now, JUnit tests will be used for manual testing with test cases provided
below for unit, integration and system level testing. Later on depending on the
schedule, automated testing might also be applied.*

## 2 Test Cases

| Test case | Purpose | Test steps                                                                                   | Expected result                                                                               | Actual Result                                                                                                           | Pass/Fail |
|-----------|---------|----------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|-----------|
| checkApplicationMenu() | Test that application menu is displayed correctly. | Start the application by running MainActivity class. | The UI should display text boxes for 'Play', 'Settings' and 'View Stats' options. | The UI displays text boxes for 'Play', 'Settings' and 'View Stats' options. | Pass
| checkGamePlay()        | Test that the play button starts the game                                                    | In the main menu, click the 'PLAY' button.               | The UI should display the following: 1. Board of letters for the game. 2. Reset Button 3. Exit button 4. Input text for the user to enter the word 5. A button to play the input word. | The UI displayed the following: 1. Board of letters for the game. 2. Reset Button 3. Exit button 4. Input text for the user to enter the word 5. A button to play the input word. | Pass
| checkGameReset() | Test that the user can reset the board i.e. change the letters in the board. | In the Game View, click on the 'RESET' button. | The grid of letters displayed in the board should change. | The grid of letters in the board changed | Pass                            
| checkGameExit() |  Test that the user can exit the game before the timer runs out. | In the Game View, click on the 'EXIT' button | The UI should render a different screen that displays the total score for the game and a 'BACK' button. | The UI rendered the total score for the game and a 'BACK' button. | Pass
| checkEmptyWordPlayed() | Test that the user cannot enter empty words for the game | In the Game View, leave the input text box empty and click on 'GO' button | The UI should show a red exclamation icon next to the text box to indicate error. | The UI displayed a red exclamation icon next to the text box| Pass
| checkInvalidWordPlayed() | Test that when user enters invalid word (words with letters not positioned next to each other in the board vertically, horizontally or diagonally), a red exclamation icon is displayed next to the text box to indicate error. | In the Game View, enter letters that are not next to each other in the text box and hit 'GO' | A red exclamation mark is displayed next to the input text box. | A red exclamation mark is displayed next to the text box. | Pass
| checkNonPresentLettersPlayed() | Test that when user enters invalid word (words with letters not contained in the board), a red exclamation icon is displayed next to the text box to indicate error. | In the Game View, enter letters not displayed in the board in the text box and hit 'GO' | A red exclamation mark is displayed next to the input text box. | A red exclamation mark is displayed next to the text box. | Pass
| checkRepeatedLettersPlayed() | Test that the game does not allow the user to enter a previously played word. | In the Game View, enter word that has already been played in the text box and hit 'GO' | A red exclamation mark is displayed next to the input text box. | A red exclamation mark is displayed next to the text box. | Pass
| checkGameScoreIsCorrect() | Test that the game score is incremented correctly for valid words | In the Game View, enter a valid word and hit go. Enter another valid word and hit go. Exit game. | The score should be the sum of the length of the two valid words. | The score is the sum of the length of the two valid words | Pass
| checkResetPenalty() | Test that the game score is penalized (-5) for game reset | In the Game View, enter a valid word in the input text and hit 'GO', hit 'RESET' button, hit 'EXIT' | The game score should be equal to the length of the first valid word minus the penalty (-5) | The game score is equal to the length of the first valid word minus the penalty (-5) | Pass
| checkBackButtonInScoreView() | Test that the user can go back to the main menu after viewing the game score. | In Game View, hit 'EXIT'. In the Game Score View, hit 'BACK' | The 'BACK' button should render the main menu. | The 'BACK' button rendered the main menu. | Pass
| checkWordStatCount() | Test that the word stat gets updated correctly. | In Game View, enter 3 valid words. Go to main menu. Click on 'VIEW STATS'. | The View Stats page should show the words entered and their count of 1. | The View Stats page shows the words entered and their count of 1. | Pass
| checkViewStatsOption() | Test that the 'VIEW STAT' BUTTON works correctly. | In main menu, click on 'VIEW STATS'. | The UI should show 'GAME STATS' and 'WORD STATS' | The UI shows 'GAME STATS' and 'WORD STATS' | Pass
| checkBackButtonInStatsView() | Test that the user can come back to the main menu from the Stats View. | In the Statistics View, click on the 'BACK BUTTON' | The main menu should be displayed| The main menu is displayed | Pass
| checkSettingsOption() | Test that the user can view settings of the game. | In main menu, click on the 'SETTINGS' button. | The UI should show the following: 'Word Weight', 'Board Size' set to default value 4 and 'Game Time'. | The UI shows the following: 'Word Weight', 'Board Size' set to default value 4 and 'Game Time'. | Pass
| checkWordWeightOption() | Test that the user can view the settings for letter weights | In Settings menu, click on the 'Word Weight' | The UI should display the letters with their respective weights | The UI displays the letters with their respective weights | Pass
| checkBackButtonInSettingsView() | Test that the user can come back to the main menu from the Settings View. | In the Settings View, click on the 'BACK BUTTON' | The main menu should be displayed | The main menu is displayed | Pass
| checkBackButtonInWordWeightSettingsView() | Test that the user can come back to the Settings menu from the Word Weight Settings View. | In the Word Weight Settings View, click on the 'BACK BUTTON' | The Settings menu should be displayed | The Settings menu is displayed | Pass
| testDefaultBoard() | Test that when the player starts the game, the board is initialized with letters | Create Player with default settings and start game | 1. Board is not null 2. Board size = 4 3. Board's grid matrix only has single letters, except 'qu' | 1. Board is not null 2. Board size = 4 3. Board's grid matrix only has single letters, except 'qu' | Pass
| testDefaultSetting() | Test that when the player is created, GameSetting has default setting. | Create the Player object. | 1. GameSetting board size = 4 2. GameSetting game duration is 3 3. GameSetting letter weights is an array of size 26 and default value 1. | 1. GameSetting board size = 4 2. GameSetting game duration is 3 3. GameSetting letter weights is an array of size 26 and default value 1. | Pass
| testDefaultGame() | Test that when the player starts the game, game is created with default settings | Create a Player object and start application | 1. Game highest score = 0 2. Game total score = 0 3. Game num of resets = 0 4. Game num of words = 0 5. Game top score word is null 6. Game board is not null | 1. Game highest score = 0 2. Game total score = 0 3. Game num of resets = 0 4. Game num of words = 0 5. Game top score word is null 6. Game board is not null | Pass
| testValidWordHorizontal() | Test that when the player enters a word that is present in the board horizontally, it is considered as a valid word. | Create a Player object, start application and call playGame with a valid horizontal word in the board. | playGame method returns true | playGame method returns true | Pass
| testValidWordVertical() | Test that when the player enters a word that is present in the board vertically, it is considered as a valid word. | Create a Player object, start application and call playGame with a valid vertical word in the board. | playGame method returns true | playGame method returns true | Pass
| testValidWordDiagonal() | Test that when the player enters a word that is present in the board diagonally, it is considered as a valid word. | Create a Player object, start application and call playGame with a valid diagonal word in the board. | playGame method returns true | playGame method returns true | Pass
| testInvalidWordRepeated() | Test that when the player enters the same word repeatedly, it is considered as an invalid word | Create a Player object, start application and call playGame with a valid diagonal word in the board. Call playGame again with the same word.| playGame method returns true in the first call and false the second time. | playGame method returns true the first time and false the second time | Pass
| testInvalidWordSingleLetter() | Test that when the player enters single letter word, it is considered as an invalid word | Create a Player object, start application and call playGame with single letter word.| playGame method returns false. | playGame method returns false | Pass
| testInvalidWordEmpty() | Test that when the player enters empty word, it is considered as an invalid word | Create a Player object, start application and call playGame with an empty word.| playGame method returns false. | playGame method returns false | Pass
| testInvalidWordEmpty() | Test that when the player enters empty word, it is considered as an invalid word | Create a Player object, start application and call playGame with an empty word.| playGame method returns false. | playGame method returns false | Pass
| testInvalidWordNonAlphabets() | Test that when the player enters word with only numbers, it is considered as an invalid word | Create a Player object, start application and call playGame with "12".| playGame method returns false. | playGame method returns false | Pass
| testInvalidWordNotPresent() | Test that when the player enters letters not ordered correctly, it is considered as an invalid word | Create a Player object, start application and call playGame with "qq".| playGame method returns false. | playGame method returns false | Pass
| testGameScoreIncrementedCorrectly() | Test that the game score is incremented correctly during the game. | Create a Player object, start the game and enter 4 valid words. | The current game score should be equal to the sum of the length of the words played. | The current game score is equal to the sum of the length of the words played. | Pass
| testGameScorePenalized() | Test that the game score is penalized correctly for rerolls during the game. | Create a Player object, start the game and enter 4 valid words and call reroll | The current game score should be equal to the sum of the length of the words played minus 5 (PENALTY). | The current game score is equal to the sum of the length of the words played minus 5 (PENALTY). | Pass
| testGameStatCreatedOnExit() | Test the the GameStat is saved correctly when we exit the Game. | Create a Player object, start the game and enter 4 valid words and call exit | 1. The Player should have 1 GameStat object created. 2. The final score of the GameStat should be equal to the sum of the length of the words played, 3. The highest score for the GameStat should be equal to the maximum length of the words played, 4. Number of resets for the GameStat should be 0, 5. Number of words played should be 4, 6. Top score word for the GameStat should be the word with the longest length, 7. The duration of the GameStat should be 3, 8. The board size of the GameStat should be 4. | 1. The Player has 1 GameStat object created. 2. The final score of the GameStat is equal to the sum of the length of the words played, 3. The highest score for the GameStat is equal to the maximum length of the words played, 4. Number of resets for the GameStat is 0, 5. Number of words played is 4, 6. Top score word for the GameStat is the word with the longest length, 7. The duration of the GameStat is 3, 8. The board size of the GameStat is 4. | Pass
| checkBoardCreation1 | Test that the array size is equal to the size passed while creating the board. | Create board by passing default setting | Board size = 4 | Board size = 4 | Pass
| checkBoardCreation2 | Test that the array size is equal to the size passed while creating the board. | Change BoardSize in setting to 8, then create board by passing modified setting | Board size = 8 | Board size = 8 | Pass
| checkBoardCreation1 | Test that the array size is equal to the size passed while creating the board. | Create board by passing default setting | Board size = 4 | Board size = 4 | Pass
| checkBoardCreation2 | Test that the array size is equal to the size passed while creating the board. | Change BoardSize in setting to 8, then create board by passing modified setting | Board size = 8 | Board size = 8 | Pass
| checkVowel1 | Test that Vowels are generated according the default weight in setting | Pass default setting to board and generate 500 vowels, count the num of each vowel generated | Num should be within +/-20% of 100 | Count of each vowel is within +/- 20% of 100 | Pass
| checkVowel2 | Test that Vowels are generated according the modified weight in setting | Change weight of 'A' to 5, pass modified setting to board and generate 500 vowels, count the num of each vowel generated | num of 'A' should be within +/-20% of 270 | A num = 286 | Pass
| checkVowelNum1 | Test that num of Vowels is 1/5 of the board | create board of default size, check the numer of Vowels | num of vowels = 4 | num of vowels = 4 | Pass
| checkVowelNum2 | Test that num of Vowels is 1/5 of the board | create board of 8 * 8 size, check the number of vowels | num of vowels = 13 | num of vowels = 13 | Pass
| checkReset1 | Test that weight for each letter still remain unchange after board.reset() | create board of default size, reset board, check vowel ratios | Num should be within +/-20% of 100 | Num of each vowel is within +/-20% of 100 | Pass
| checkReset2 | Test that weight for each letter still remain unchange after board.reset() | set weight of 'A' to 5, create board of default size, sreset board, check vowel ratios | num of 'A' should be within +/-20% of 270 | A num = 265 | Pass
| checkWord1 | Test that word need to be 2 and more letter long | pick one letter from board anc do board.checkWord() | return false | return False | Pass
| checkWord2 | Test that single word cannot reuse letter from same location | append letter from board for 3 times, do board.checkWord() | return false | return false | Pass
| checkWord3 | Test that single word cannot be applied twice before resetting the board | choose a valid from board and do board.checkWrod() twice | first check return true, second check return false | first check return true, second check return false | Pass
| checkWord4 | Test that single word can be applied again after resetting baord | check the dedup HashSet before and after board.reset() | do board.checkWord() and check set.size() = 1,  reset board and check set.size()= 0 | set.size() = 1 before reset, set.size() = 0 after reset | Pass
| checkWord5 | Test valid word need to adjacent letter on the board | pick seperate letters from board and do board.checkWord() | return false | return false | Pass
| checkGameCreation | Test the game can be created properly | check all the variables are initialized to default value | highestScore = 0, numOfReset = 0, numOfWord = 0, highestScore = 0, topScoreWrod = null | the same as expected result | Pass
| checkGameStart | Test that game and board class are integrated correctly | check all elements within board of Game before and after game.start() | game.grid are null before game.start and are filled after game.start() | the same as expected | Pass
| checkTopScoreWord | Test topScoredWord is updated properly | check topScored Word at beginning, processInput(shortWord), check topSocred word, processInput(longWord), check topScoredWord again | get null, shortWord, then longWrod | the same as expected | Pass
| checkHighestScore | Test HighestScore is updated properly | check HighestScore at beginning, processInput(shortWord), check HighestScore, processInput(longWord), check HighestScore again | get 0, shortWord.length(), then longWrod.length() | the same as expected | Pass
| checkNumOfWord | Test NumOfWord is updated properly | check NumOfWord at beginning, processInput(shortWord), check NumOfWord, processInput(longWord), check NumOfWord again | get 0, get 1, get 2 | the same as expected | Pass
| checkNumOfResets | Test NumOfResets is updated properly | check NumOfResets at beginning, processInput(shortWord), check NumOfResets, processInput(longWord), check NumOfResets again | get 0, get 1, get 2 | the same as expected | Pass
| checkTotalScore1 | Test TotalScore is updated properly game.processInput() | check TotalScore at beginning, processInput(shortWord), check TotalScore, processInput(longWord), check TotalScore again | get 0, shortWord.length(), then longWrod.length()+ shortWord.length() | the same as expected | Pass
| checkTotalScore2 | Test TotalScore is updated properly with reset() | check TotalScore at beginning, do game.reset(), check TotalScore | get 0, then -5 | the same as expected | Pass
| testGameStats() | verify when a game finish, a GameStat class is created with necesary information recorded.| Create a Player and start application | Call ProcessInput for 2 words one with 4 letters and another with 3 letters | Reset Board | Exit Game | Expected FinalScore : 2 | Expected Num Of Reset : 1 | Expected Num Of Words : 2 | Expected TopScoredWord : 4 letter word | Actual FinalScore : 2 | Actual Num Of Reset : 1 | Actual Num Of Words : 2 | Actual TopScoredWord : 4 letter word | Pass
| testGameStatsWithSetting() | verify when a game finish, a GameStat class is created with necesary information recorded | Create a Player and start application | Call ProcessInput for 2 words one with 4 letters and another with 3 letters | Reset Board | Exit Game | Expected Duration : 3 | Expected BoardSize : 4 | Expected TopScoredWord : 4 letter word | Actual Duration : 3 | Actual BoardSize : 4 | Actual TopScoredWord : 4 letter word | Pass
| testDefaultSettings() | Initialize GameSetting class and check default settings  | Duration : 3 | BoardSize : 4 | Weights : 1 | Pass
| testchangeSizeSuccess() | Initialize GameSetting class and change BoardSize to 8 | Expected BoardSize : 8| Actual BoardSize : 8 | the same as expected | Pass
| testchangeSizeFail() | change BoardSize to 'c' | InvalidArgumentException | InvalidArgumentException | the same as expected |Pass
| testDurationSuccess() |  change Game Duration to 5 | Expected Duration : 5 | Actual Duration : 5 | the same as expected |Pass
| testDurationFail() | Change Game Duration to 'c' | Expected Exception : InvalidArgumentException | Actual Exception : InvalidArgumentException | the same as expected | Pass
| testchangeWeightSuccess() | change word Weight to 3 | Expected weight : 3 | Actual weight : 3 | the same as expected |Pass
| testchangeWeightFail() | change word weight to 'c' | Expected Exception : InvalidArgumentException | Actual Exception : InvalidArgumentException | the same as expected | Pass
