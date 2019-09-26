HelloFresh Web-Test
=====


[![release](http://github-release-version.herokuapp.com/github/yandex-qatools/ashot/release.svg?style=flat)](https://github.com/yandex-qatools/ashot/releases/latest) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/ru.yandex.qatools.ashot/ashot/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/ru.yandex.qatools.ashot/ashot)

##### Configurator:

* Run tests in parallel mode

```mvn clean test -P Parallel 
```

* Run tests for different browsers/OS by configuring

```mvn clean test -Dbrowser=chrome -Dos=windows
```

```mvn clean test -Dbrowser=firefox -Dos=windows
```

```mvn clean test -Dbrowser=chrome -Dos=linux
```

```mvn clean test -Dbrowser=firefox -Dos=linux
```

* Run tests for different environments(urls) by configuring/by command-line.

```mvn clean test -Durl=http://dev.automationpractice.com/index.php
```

```mvn clean test -Durl=http://qa.automationpractice.com/index.php
```

* Reading test data from file ,for example, the name of dress, size and color in the checkout test. 
Refer data.json under src/test/resources

```	"NewOrder": [
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




##### Capturing the WebElement

One can take a screenshot of particular WebElement(s). Just specify the element(s).
```java
WebElement myWebElement = webDriver.findElement(By.cssSelector("#my_element"));
new AShot()
  .takeScreenshot(webDriver, myWebElement);
```
 
As noted earlier, aShot will find an element's size and position and crop the original image. WebDriver API provides a method to find the WebElement's coordinates but different WebDriver implementations behave differently. The most general approach to find coordinates is to use jQuery, so aShot uses jQuery by default. But some drivers have problems with Javascript execution such as OperaDriver. In this case there is another way to find the WebElement's coordinates.
```java
new AShot()
  .coordsProvider(new WebDriverCoordsProvider()) //find coordinates with WebDriver API
  .takeScreenshot(webDriver, myWebElement);
```
Feel free to implement your own CoordsProvider. Pull requests are welcome.

##### Prettifying the screenshot

So, let's take a simple screenshot of the weather snippet at Yandex.com.

```java
new AShot()
  .takeScreenshot(webDriver, yandexWeatherElement);
```
Here is the result.
![simple weather snippet](/doc/img/def_crop.png)
 
```DefaultCropper``` is used by default. Can we do better? Yes, we can.
 
```java
new AShot()
  .withCropper(new IndentCropper()           // set custom cropper with indentation
                 .addIndentFilter(blur()))   // add filter for indented areas
  .takeScreenshot(driver, yandexWeatherElement);
```
  
![indent blur weather snippet](/doc/img/weather_indent_blur.png)
This screenshot provides more information about position relative other elements and blurs indent in order to focus on the WebElement.  
  
##### Screenshot comparison
```.takeScreenshot()``` returns a ```Screenshot``` object which contains an image and data for comparison. One can ignore some WebElements from comparison.

```java
Screenshot myScreenshot = new AShot()
  .addIgnoredElement(By.cssSelector("#weather .blinking_element")) // ignored element(s)
  .takeScreenshot(driver, yandexWeatherElement);
```

Use ```ImageDiffer``` to find a difference between two images.

```java
ImageDiff diff = new ImageDiffer().makeDiff(myScreenshot, anotherScreenshot);
BufferedImage diffImage = diff.getMarkedImage(); // comparison result with marked differences
```

##### Several elements comparison
`(since 1.2)`  
Sometimes one needs to take a screenshot of several independent elements. In this case aShot computes complex comparison area.
```java
List<WebElement> elements = webDriver.findElements(By.cssSelector("#my_element, #popup"));
new AShot()
  .withCropper(new IndentCropper() 
                 .addIndentFilter(blur()))
  .takeScreenshot(webDriver, elements);
```
Here is the result.
![complex comparison area](/doc/img/complex_elements.png)

One can see only specified elements (the header and the popup) are focused and will be compared if needed.

##### Ignoring of pixels with predefined color
You can set the color of pixels which should be excluded from comparison of screenshots.
```java
ImageDiffer imageDifferWithIgnored = new ImageDiffer().withIgnoredColor(Color.MAGENTA);
ImageDiff diff = imageDifferWithIgnored.makeDiff(templateWithSomeMagentaPixels, actualScreenshot);
assertFalse(diff.hasDiff());
```
Any pixels in template with color MAGENTA (255, 0, 255 in RGB) will be ignored during comparison.
