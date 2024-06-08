# testexercise_bank1


# UI Automation Example Project
> Example project developed to perform automated tests on the website [The-Internet](https://TODO123.12/)
>> To collaborate with The Internet: https://github.com/tbogdel


## How to use:

- [Installation](#installation)
- [Examples](#examples)
- [Technologies](#technologies)
- [Patterns](#patterns)
- [Dependencies](#dependencies)
- [Reports](#reports)

---

## Installation
- Put ChromeDriver to directory:
```
	C:\Program Files\WebDriver\chromedriver.exe
```

- Clone this repository to your local machine using the command below:
```
	$ git clone https://github.com/tbogdel...TODO
```

---

### Execution

> Access project root

```
	$ cd /sample
```
> Execute the command to run all tests in the project

```
	$ mvn clean test
```
> Execute the command to run only one test class in the project

```
	$ mvn clean test -Dtest=<TODO TEST>
```

---
## Technologies:
- Selenium WebDriver
- Java
- Maven

---

### Dependencies
* *[selenium](https://www.selenium.dev/)*
* *[testng](https://testng.org/)*
* *[extentreports](http://www.extentreports.com/)*
* *[webdrivermanager](https://github.com/bonigarcia/webdrivermanager)*
* *[lombok](https://projectlombok.org/)*

---

## Reports
* To view report of test, access the file: */target/report/test_execution.html*
