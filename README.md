HelloFresh Web-Test
=====


##### Configurator:

* Run tests in parallel mode

```java
mvn clean test -P Parallel
```

* Run tests for different browsers/OS by configuring

```java
mvn clean test -D browser=chrome -D os=windows
```

```java
mvn clean test -D browser=firefox -D os=windows
```

```java
mvn clean test -D browser=chrome -D os=linux
```

```java
mvn clean test -D browser=firefox -D os=linux
```

* Run tests for different environments(urls) by configuring/by command-line.

```java
mvn clean test -D url=http://dev.automationpractice.com/index.php
```

```java
mvn clean test -D url=http://qa.automationpractice.com/index.php
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

##### Generation human readable report

##### Generating random values for insignificant test data, for example, for new user

##### WebDriver factory

##### Encapsulation layers like test data, logic of tests, actions on web pages 






















