HelloFresh Web-Test
=====


##### Configurator:

* Run tests in parallel mode

```java
mvn clean test -P Parallel
```

* Run tests for different browsers/OS by configuring

```java
mvn clean test -Dbrowser=chrome -Dos=windows
```

```java
mvn clean test -Dbrowser=firefox -Dos=windows
```

```java
mvn clean test -Dbrowser=chrome -Dos=linux
```

```java
mvn clean test -Dbrowser=firefox -Dos=linux
```

* Run tests for different environments(urls) by configuring/by command-line.

```java
mvn clean test -Durl=http://dev.automationpractice.com/index.php
```

```java
mvn clean test -Durl=http://qa.automationpractice.com/index.php
```

* Reading test data from file ,for example, the name of dress, size and color in the checkout test. 
Refer data.json under src/test/resources

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

