HelloFresh Web-Test
=====

##### Configurator:

* Run tests in parallel mode : by default it'll run on chrome browser and windows

```java
mvn clean test -P Parallel
```

* Run tests for different browsers/OS by configuring

#### Prerequisite - To run on Linux OS - src/test/resources/drivers
```java
chmod +x geckodriver  
```
```java
chmod +x chromedriver
```

```java
mvn clean test -P Parallel -Dbrowser=chrome -Dos=windows
```

```java
mvn clean test -P Parallel -Dbrowser=firefox -Dos=windows
```

```java
mvn clean test -P Parallel -Dbrowser=chrome -Dos=linux
```

```java
mvn clean test -P Parallel -Dbrowser=firefox -Dos=linux
```

* Run tests for different environments(urls) by configuring/by command-line.

```java
mvn clean test -P Parallel -Durl=http://dev.automationpractice.com/index.php
```

```java
mvn clean test -P Parallel -Durl=http://qa.automationpractice.com/index.php
```

* Reading test data from file Refer data.json under src/test/resources

```java
"NewOrder": [
		"btn_submit:click",
		"btn_checkout:click",
		"btn_proceed_checkout:click",
		"btn_process_address:click",
		"rbn_accept_term:click",
		"btn_process_carrier:click",
		"btn_pay_by_bankwire:click",
		"btn_confirm_order:click"
		],
```

##### Logging

* Using log4j for logging 
* log4j.properties file under "src/test/resources"
* Sample output will be under "log" folder
* Output will be on Console,File.txt and .html file

##### Taking screenshot on failed tests 
* Screenshot will get attached in report if any test case fails  

##### Generation human readable report 
* Integrated framework with Extent Reports
* Ouput report has two tabs at left top corner to view the report in sequential format or graph format

##### Generating random values for insignificant test data, for example, for new user
All data is stored is data.json in project directory

##### WebDriver factory
* Currently framework supports Chrome(77 version),Firefox,IE and Edge browser 

##### Encapsulation layers like test data, logic of tests, actions on web pages 
* Framework supports PageFactory Design pattern so that each web page in a application there is a corresponding page class which contains the web elements of that page and methods to perform action on those web elements





















