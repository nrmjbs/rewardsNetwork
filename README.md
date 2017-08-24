# Rewards Network - Getting Dressed Application

- This is a standalone version of Getting Dressed application. The requirements are in the end.

- Its built using technology stack below
	* Java 8 (with functional programming/lambda, Objects API)
	* Mockito and Junit for unit testing
	* Apache Commons Lang3 for String and Array utils
	* GIT (Source Repository/SCM)
	* STS (IDE from Spring)


- Source Code
	* Java classes are in the package com.rewardsnetwork under src/main/java folder
	* Test classes are in the package com.rewardsnetwork under src/test/java folder


- Setup Steps
	* Download/Clone the source from the repository @ https://github.com/nrmjbs/rewardsNetwork ; branch: master
	* Setup the IDE with JRE 1.8
	* Import existing maven project in the IDE


- Steps to Run
	* Run GettingDressedClient as a standalone application
	* Feed the desired input via command line
	* Validate output against expectation


- SPECIAL Notes
	* SOLID design patterns are used where required.
		* Single responsibility is assigned to a class so that other classes and their functionality is not modified/impacted when the behavior in a class is modified.
		* Design patterns of the likes of Factory, Builder, Template, Singleton are in use
		* Singletons are stateless for reuse
		* A class is open for extension using has-a relationship.
		* Coding to interfaces is in practice through out the program.
		* Parent reference can be substituted for a child (Liskov Substitution Principle)at any time without breaking the compilation unit.


- Assumptions


- Known Issues and Solutions
	* Components own the creation of dependencies, so replacing them with newer implemenation will require code change. Use of DI (dependency injection) framework should be able to resolve this.


- Requirements
	* Problem:
		You are currently in your house wearing your PJ’s. You must get fully dressed and leave the house.
		Your challenge is to programmatically process a list of getting dressed commands, enforce related rules, and display 		appropriate output.
		* Inputs:
			* Temperature type (one of the following)
				- HOT
				- COLD
			* Comma separated list of numeric commands
				- Command Description		Hot Response	Cold Response
					- 1	Put on footwear		“sandals”		“boots”
					- 2	Put on headwear		“sunglasses”	“hat”
					- 3	Put on socks		fail			“socks”
					- 4	Put on shirt		“shirt”			“shirt”
					- 5	Put on jacket		fail			“jacket”
					- 6	Put on pants		“shorts”		“pants”
					- 7	Leave house			“leaving house”	“leaving house”
					- 8	Take off pajamas	“Removing PJs”	“Removing PJs”
		* Rules:
			- You start in the house with your PJ’s on
			- Pajamas must be taken off before anything else can be put on
			- Only 1 piece of each type of clothing may be put on
			- You cannot put on socks when it is hot
			- You cannot put on a jacket when it is hot
			- Socks must be put on before footwear
			- Pants must be put on before footwear
			- The shirt must be put on before the headwear or jacket
			- You cannot leave the house until all items of clothing are on (except socks and a jacket when it’s hot)
			- If an invalid command is issued, respond with “fail” and stop processing commands
		* Examples
			- Success
				* Input: HOT 8, 6, 4, 2, 1, 7
				* Output: Removing PJs, shorts, shirt, sunglasses, sandals, leaving house
				* Input: COLD 8, 6, 3, 4, 2, 5, 1, 7
				* Output: Removing PJs, pants, socks, shirt, hat, jacket, boots, leaving house
			- Failure
				* Input: 	HOT 8, 6, 6
				* Output: 	Removing PJs, shorts, fail
				* Input: 	HOT 8, 6, 3
				* Output: 	Removing PJs, shorts, fail
				* Input: 	COLD 8, 6, 3, 4, 2, 5, 7
				* Output: 	Removing PJs, pants, socks, shirt, hat, jacket, fail
				* Input: 	COLD 6
				* Output: 	fail

