**Individual Designs**

**Design1: Feng**

![alt text](https://github.gatech.edu/gt-omscs-se-2020spring/6300Spring20Team71/blob/master/GroupProject/Design-Team/Design_Feng.jpeg)

**Pros**

**Encapsulation:** Correctly identify necessary classes from the requirements and make sure they are independent as possible. When the application starts, each Application class corresponds to one Setting class and WordStat class. Setting can be changed per player’s preference. When a WordGame is created, it just takes necessary information from Setting to generate board and letters, then operates on its own. 


**Information Hiding:** Assigned the attributes with suitable access modifier and provide getter to access this information. This helps to ensure attributes won’t be accidently changed. For example, boardSize and time attributes in Setting class have setBoardSize() and setTime() to change them. And WordStat class have update() and checkWordStat() operations.


**Testability:** Testability is considered in the early design phase regarding attribute and operations in each class. I tried to separate operations inside a class to be less dependent, so that the logic for different operations at their lower level and help to reduce debug time in the integration phase. For example, insteading of creating changeSetting(boardSize, time, letterWeight) together as a single function, I create changeSize(), changeTime() and changeWeight(String, Integer) as three separate functions. This make sure logic for each function is easy enough to be tested in easy design phase.

**Extensibility/Flexibility:** Better extensibility is achieved by separating WordStat as class and GameStat out as individual classes. This is also done by having game settings independent as a single class. For example, new statistics need to be added to the game in the future, we can just create new classes as necessary. Whereas, if the GameStat is designed as an attribute of Application class, we have to modify Application class to add new statistics or other requirements.



**Cons**

**Missing input and return types:** Some input parameters and return types are missing. For example, changeSetting() and changeTime() function should have input arguments as changeSize(int) and changeTime(Minute)

**Static attributes:** it should be better to have static information as attributes for the class, instead of embedding inside operations. For example, PENALTY: 5 could be an attribute of WordGame class. If we want to change the penalty in the future, we can just change

**Relation between class:** Some of the relations between classes are not correctly recognized. WordGame and setting should not be dashed arrow.

**Optimizations for functions:** Some for the operations should be more intuitive. For example, the changeWeight function in Setting class, instead of changing the letter weight for one letter every time, it could be better to take in the whole array in one time. Make the operation easier.















**Design2: Ahmed**

![alt text](https://github.gatech.edu/gt-omscs-se-2020spring/6300Spring20Team71/blob/master/GroupProject/Design-Team/Design_ahusain9.jpeg)

**Pros**

**Modularity:**  I have two separate classes to manage statistics for word and score.

Implementation will be easy to manage. I had used total eight classes to accomplish the board game functionality. Added required classes and the attributes. The design was interfaced with the stats class to display the results. In this designing I had added an application class to keep the record of game scores.



**Attributes:** Added proper attributes with identified variables to store and use the variables.



**Operations:** We have application class used to interaction between Game and Player class.

It can be used to maintain score across all the games for all the players.

**Relations:** The design was properly related with relationship arrows and their properties to show the prototype of the design.



**Cons**

**Interface:** Turned out that the interface was not necessary to accomplish the goals of the project

**Attributes:** There were additional attributes which were not required and removed during the group discussions.

**Optimizations for functions:** Application class may not be required as this functionality can be assumed to be taken by the Androids system GUI.  The GameSetting class can be simplified with fewer attributes and classes.





















**Design 3: Vipul**

![alt text](https://github.gatech.edu/gt-omscs-se-2020spring/6300Spring20Team71/blob/master/GroupProject/Design-Team/Design_Vipul.JPG)

**Pros**

**Modularity:** I&#39;ve made the classes very modular with minimal dependencies between them. For e.g. Board requires a size to create the array as well as the letter weights in order to generate the elements (letters) of the board. Both size and letter weights are attributes of the Settings class but instead of passing Settings to Board, which would have introduced dependency between board and class, I only pass these two attributes to Board via Game class. This way we only enforce dependency from Game to Settings.

**Separation of concerns:** Based on the attributes of the individual classes I have added different methods to the appropriate class. For e.g. I could have put the validateWord method in the Game class, but validateWord method requires no attributes of the Game class and solely operates on the Board class&#39; attributes and thus this way the Game class can simply call the validateMethod of the board class when it receives an input from the Player and does not need to bother with the validation logic which only resides in the Board class.

**Frontend (UI) and Backend (this design) distributing work:** Backend does not take more than the required burden - it shares tasks with the UI where the UI can simply handle things on its own. For example, the UI can get the clock setting from the Settings class and handle the countdown time display on its own.

**Single controller:** Player is the main controller class in my design. This class starts the Game class and feeds the input to the game. This allows us to minimize dependencies between classes. As per the requirements, we only need Game Score stats for the main menu, therefore Game class need not know about the GameScoreStatistics. Also, GameScoreStatitics is a combination of Game and Settings attributes. Since player has reference to both these classes, it can directly create the GameScoreStatistic for every Game when the game ends using the attributes from the Game and the Settings.

**Performance optimization:** While assigning the data types, I have taken into consideration the performance of the code. For e.g. instead of returning the entire Game object to the UI, I only return the 2-dimensional char array for the UI to display. This saves the amount of data we transfer back, and forth which may be further optimized during implementation.





**Cons**

**Missing attributes:** I have missed some attributes in the class while making an assumption that the class has that attribute. For e.g. I haven&#39;t stored the attribute Settings for the Player class but do have the method changeSettings in this class.

**Future considerations:** This design does not take future software changeability into account. For e.g. if we were to add one a different type of game, we would have to add a separate new class. Instead, we could have accounted this into our design today. For instance, I could have created an Interface called Game and created a separated class called BoardGame that implements the interface. Tomorrow, when we have a different game, we could have simply implemented this interface making our code loosely coupled and easy to modify.

**Scalability:** In the course of this design, I haven&#39;t really put much thought into the scalability of the game which might require significant changes considering memory, network etc. bottlenecks.

**UML Diagram:** I could have been more specific in my UML diagram while putting relations between classes.

**Security implications:** The design does not take any of the security aspects into consideration















**8 (b) Team Design**

![alt text](https://github.gatech.edu/gt-omscs-se-2020spring/6300Spring20Team71/blob/master/GroupProject/Design-Team/Design_Team.jpeg)

**Comparisons:**

**Team Design vs Design 1 (Feng)**

Following are the commonalities and differences between my design and team-design classes

1. Player class:
   1. Common: naming is different as my design named this controller class as Application; necessary operations are both created
   2. Difference: final design as Game, Board and stat mapping as the attribute
2. WordStat class:
   1. Common: Mapping relation between word and wordCount are both create in my design and final design
   2. Difference: final design has WordStat as an attribute of Player class for the ease of implementation.
3. Board class:
   1. Common: Board size attribute and generateLetter() operations are both created.
   2. Difference: final design also creates a Board Class, which is better for future modification if any game rules change in the future. Separate checkWord() and processInput() two functions also make future modification easier, for example extending to other languages

**Team Design vs Design 2 (Ahmed)**

Following are the commonalities and differences between my design and team-design classes

1. Player class:
   1. Common: Two methods are common ViewStat, PlayGame.
   2. Difference: We redesigned classes with more methods to accommodate the functionality of the Player class.
2. GameSetting:
   1. Common: The methods are the same as my original design.
   2. Difference: Additional attributes are added to this class.
3. Game:
   1. Common: This class has been redesigned to accommodate modified functionalities. My original design had extra classes but were reduced in the final design.
   2. Difference: attributes and methods were changed and modified
4. Board:
   1. Common: This class has been changed to modify the attributes and methods
   2. Difference: Reduced attributes and added extra methods
5. GameStat:
   1. Common: My original design interfaces with the Statistics class but in the final designed we modified to incorporate both word and game stats into one class
   2. Difference: method name changes.

**Team Design vs Design 3 (Vipul)**

Following are the commonalities and differences between my design and team-design classes

1. Player class:
   1. Common: The attributes and methods are the same as my original design.
   2. Difference: None
2. GameSetting: This class is similar
   1. Common: The attributes and methods are the same as my original design.
   2. Difference: Only name change, this class has been renamed from Setting in my original design to GameSetting.
3. Game:
   1. Common: This class is similar to the Game class in my original design.
   2. Difference: name changes, e.g. numWords renamed to numOfWords.
4. Board:
   1. Common: This class is similar to my class in original design
   2. Difference: None
5. GameStat:
   1. Common: all the attributes are the same:
   2. Difference: method name changes.

**Overall design**

This design preserves most of the ideas where the Player class is the main controller of the application. It has a reference to the Settings class, Game class, GameStat and a Map that stores the word statistics, using the word as the key and the frequency of the word as the value. The Player only controls the game and need not know about the Board class. The Game class in turn controls the Board class. This way we minimize the dependencies between the classes.

We also tried to keep this design as simple as possible, minimizing the number of entities we have in the UML diagram, therefore, we are not using any interfaces in this design, e.g. Interfaces for Game and make BoardGame implement it, interface Statistics and have WordStat and GameStat implement it. Since GameScoreStat and WordStat have nothing in common between them we think it also does not make much sense to make them child classes of a more common parent class like Statistics.

This design is also efficient with respect to UI and the backend responsibilities. For e.g. for displaying the timer, the Game class does not have to have any special logic for the timer. Instead, since the Settings class is storing the game duration and has a getter class for the attribute, the UI can simply fetch that and implement the logic for the displaying the timer. When the time ends it can just end the Game.

We&#39;ve also appropriately chosen the data types, method input arguments and return types. For e.g. when the player starts the game, instead of returning the entire Game object we only return the 2-dimensional char arrays containing letters the UI needs to display to the user.

We&#39;ve also taken into consideration the user experience with our design. For instance, the input arguments to the changeGameSettings method allows the user to modify all the attributes in a single call. This helps the UI to allow the user to make the required changes and update the settings with a single click, instead of having to click the Update for every single attribute.





**Summary:**

We had a very well working and collaborative group where we were freely able to ask questions, discuss ideas, critique each other and brainstorm. Ultimately, we had a hybrid design where we each contributed to and felt confident in. Here is the overview of things that stood out during the process:

1. **Words go a long way:** As we went over each other&#39;s UML by ourselves first before jointly discussing in order to have some time for immersion, we found that sometimes we did not fully grasp the design intent. This was either due to fully grasping the other&#39;s intent and sometimes due to omissions in the actual design. Filling this design gap was critical while building the final Team Design.
2. **Many times, there were no right or wrong answer:** Our approaches on certain aspects varied while all achieving the same goals. For e.g., we discussed whether to create a separate WordStatistics class just as we did for the GameStat or simply create a Map\&lt;\&gt; for it. We agreed to store it in a Map\&lt;\&gt; since it was the most logical thing to do considering that beyond displaying the WordStats, there was no other functionality involved. We decided to push the most robust design even at the cost of a little more complexity.
3. **UI handles many aspects:** We are light on display functions - because the backend, which is basically this design we have created, simply &#39;returns&#39; requisite data when the front end calls it. Then it is the UI&#39;s job to display in whatever manner we have programmed it to.
4. **Effective use of Class for robustness:** We leveraged the concept of Object-Oriented Programming for the development of this design. The entire functionality could have been achieved by something achieved with Procedural Programming but that is neither modular nor robust. Thus, we broke down the design into various Classes whose attributes and closely tied to each class it is under. We ended up with five classes: GameStat, Player, Game, GameSetting and Board.
5. **Cleaning up UML:** Between our designs we realized that we could improve the UML by adding more detail to it (perhaps this was one reason where we had difficulty in understanding each other&#39;s designs). We were more specific with our relationship between classes, we properly indicated private and public attributes and functions and provided appropriate data type and return type.
6. **Teamwork:** Our approach was to parse out work in a logical manner after having a joint session. We also understood what we had to do prior to each meeting (with voice chat and video sharing). This helped us make effective use of our time when we were all available.