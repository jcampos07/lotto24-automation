# lotto24-automation

## Instructions

Dear Reviewer,  
here you can find my detailed instructions about how to build and run these projects.
I have created two frameworks, one of them in Java + Selenium + Maven + Log4J + TestNG + Extend Report for the UI testing, additionally, you are going to find a couple of Test Cases (located in the same path thant README.MD) to verify the wikipedia scenario and the tesla page, I decide to write them because as part of my qa process, I always have a Test Case before automating, that is the process we should follow.
The other one in Python + behave + request + Allure report to automate the API scenarios. I have decided to use two different languages and technologies in order to show I am flexible to work on different tools, I consider this is important for a Senior QA Automation Engineer.

Lets start with the RestApi project.

### Framework Design for RestApi:
This framework has been created in Python, behave, request library and Allure report. I created this framework using those technologies because python has better performance and it is faster than Java.
In addition, I use behave (similar to cucumber) because when we test RestApi it is difficult for non technical people to understand what we are testing, using behave which is based on gherkin language, it helps us to show them what its being tested.
The structure is simple, lets explain it:

- **config** (request.py class that encapsulates the functions like put, get, delete, post. These functions are going to be used for the classes under steps folder)
- **data** (Store json files we need in the tests, for example, we have new_post.json file that contains the information we need to the creation of a new post test)
- **feature** (Use gherkin language, we have a .feature for each endpoint we are going to test, in each .feature file we have several scenarios)
	- **steps** (each .feature file should have a python class where we implement the function for each step (Given, when, then), here we develop the logic to test a scenario)
- **utils**	(Functions that are helpful for the project, for example write a value in a file)

Another important point I would like to explain is that as you can see in the project, each folder under "steps" contains two python files, one of them implements the functions we need to test according to the description we give in the .feature file, the second class with name posts_functions for example has the logic we need to validate an scenario (compare json response, get specific values from a complex json file).
I have decided to do it in that way because I like to have the logic in another file to make the code clearer, additionally, the functions in posts_functions can be reused for other scenarios that belong to the same feature.

 
## Run API Project:

### Prerequisite: 

**Please checkout/clone the project in your computer.**

##### Windows machine:
* If you do not have Python 3.7.3 in your machine, download it from -> https://www.python.org/downloads/release/python-373/.
* Install the .exe in your machine
* Go to Enviroment variables and add the following System Variables (in case they are not set): C:\Users\{YOUR_USERNAME}\AppData\Local\Programs\Python\Python37\Scripts and C:\Users\{YOUR_USERNAME}\AppData\Local\Programs\Python\Python37
* Once the system variables are set, open a cmd.
* Move to the folder name: "lotto24_api" using cd command.
* Enter the following command to install the necessary libraries -> pip install -r requirements.txt
* Wait until the libraries be downloaded

##### Linux machine:
* If you do not have Python 3.7.3 in your machine, open a command line
* Enter the following commands: 
	```shell
	sudo apt update
	```
	```shell
	sudo apt install software-properties-common
	```
	```shell
	sudo add-apt-repository ppa:deadsnakes/ppa
	```
	```shell
	sudo apt install python3.7
	```
	python3.7 --version -> version should be displayed if everything went well
* Move to the folder name: "lotto24_api" using cd command.
* Enter the following command to install the necessary libraries:
	```shell
	pip install -r requirements.txt
	```
* Wait until the libraries be downloaded

##### Mac machine:
* If you do not have Python 3.7.3 in your machine, open a command line
* Enter the following commands:
	```shell
	/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
	```
	```shell
	brew install python3
	```
* Move to the folder name: "lotto24_api" using cd command.
* Enter the following command to install the necessary libraries:
	```shell
	pip install -r requirements.txt
	```
* Wait until the libraries be downloaded
	
**I have a windows machine, hopefully these steps work for linux and Mac since I could not test them**

### Running the api project:

In order to run the api project, we would need to open the console, move to the folder called: "lotto24_api", then we only need to enter the following command:
	
```shell
behave
```
That command will show the result in the console. 
In order to see an Allure report with the results, please execute the following commands:

```shell
behave -f allure_behave.formatter:AllureFormatter -o results/jsonplaceholder --tags=@rest -D tag=rest
```
```shell
allure generate results/jsonplaceholder -o reports/jsonplaceholder --clean
```
```shell
allure open reports/jsonplaceholder
```
I found several bugs in the endpoints I tested, you are going to find attached a file in the repository called Bugs.md where you can see them in detail.


### Framework Design for Front End:
This framework has been created in Selenium, Java, Maven, TestNG, Log4J and Extend Report. I chose those tools because most of the framework for Front End testing are based on them. Also, on internet we can find help quickly when a problem comes up since the community is big.
I created it based on Behavior Driver Development methodology (BDD), as we can see in the Search.java test, I run the search test two times using data providers, the first time with the search criteria you requested in the test, the second time, with a search I set, this shows this framework can be use for this methodology.
Beside, I decided to create two different testNG files, this because both pages (Tesla and wikipedia) belong to a different domains, so, it is not good to have both tests in the same file, the best approach is to have them in a different xml.

The project structure is the following:

- **java/bot** (Encapsulates the selenium api functions, here we have the sendKeys, click, the explicit waits, etc. We have custom functions to improve the functionality that selenium offers)
- **java/datastructures** (Domain classes that map the json information in a object, this object is from where we get the information for each test scenario)
- **java/pageobjects** (Page objects are handled in that package)
- **java/steps** (These classes are controllers between the tests and Page objects, they comunicate both, here we have the logic of each test, when we need a specific function of a page object, it comunicates with the class and call the function. I created this classes in order to have only impulses from the test class, the steps files contains the logic and when we need to interact with a page, we just call the function for the Page object)
- **java/utilities** (Classes that are useful for the framework, for example read a json file)
- **resources** (Here we have the json files with the information we use for testing. Besides, the data providers are located here)
- **tests/config** (This is an important folder, here we have all the classes we need to start a driver with its configurations, here we have the classes to create the reports when the execution ends)

Related to the scenario to test for the wikipedia page, I felt that we need to increase the coverage,so, I extended the search test, I added a steps to compare each title in the table of contents has a section with the same name in the article. Here I use a Java 8 feature (lambda) in order to show I have worked with this nice feature.

## Run Selenium Project:

### Prerequisite: 

**Please checkout/clone the project in your computer.**

* Have Maven install in your machine
* Have JDK 8 or higher installed

### Running the Selenium project:

In order to run the selenium project, we need to open a console, then move to the project: lotto24-automation/lotto-automation using cd command.
Once we are in that path, we just need to enter this command:

	```shell
	mvn test -DsuiteXmlFile=wikipedia.xml -Dbrowser=Chrome -Dmode=UI -Dtag=search
	```
Here we can noticed, I have configure the browser to be passed as parameter, so, the ones we can send are the following:
	
	Firefox -> Firefox browser
	Chome -> Chrome browser
	
Additionally, we can run the tests in headless mode, for this me would need to modify the mode parameter like this:
	
	```shell
	mvn test -DsuiteXmlFile=wikipedia.xml -Dbrowser=Chrome -Dmode=headless -Dtag=search
	```
The above commands are going to execute the wikipedia test only, if you want to test the tesla page, you need to execute the following command:
	
	```shell
	mvn test -DsuiteXmlFile=tesla.xml -Dbrowser=Chrome -Dmode=UI -Dtag=tesla
	```
We also can configure the browser to run in Firefox modifying the -Dbrowser=Firefox parameter in the previous step.

Once the automation has finished, we could go to reports folder, there you can see the extend report with the summary of the test execution.
This helps to have a nice way to see the results.

Basically, that is the way to run both projects, hopefully everything goes well and you like both implementations, thank you very much for this opportunity, I am very happy to be part of this process, looking forward to hear good news from you soon.
Any feedback you have, please let me know.

Best Regards,
Jason.
