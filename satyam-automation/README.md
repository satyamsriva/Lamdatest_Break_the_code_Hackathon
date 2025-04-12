**Selenium Automation Tests on LambdaTest**

This repository contains two Selenium-based automation test scripts executed on LambdaTest's cloud grid using RemoteWebDriver and TestNG/JUnit frameworks.

**🔧 Prerequisites**

Java (JDK 8+)

Maven or compatible build tool

Internet access for cloud execution

Valid LambdaTest credentials (Username and Access Key)

🚀 Task 1: herokuappLogin.java

**📄 Description**

This test performs login functionality testing on the Heroku app:

✅ Valid login with correct credentials

🔓 Logout scenario

❌ Invalid login with incorrect credentials

**✅ Tested On**

Browser: Microsoft Edge (dev version)

Platform: Windows 10

**🔐 Credentials Used**

Username: tomsmith

Password: SuperSecretPassword!

**🌐 Test URL**

https://the-internet.herokuapp.com/login

**🧪 Features**

Uses EdgeOptions for browser capability

Suppresses popups and password manager

Verifies success and error messages

Marks test status (pass/fail) on LambdaTest dashboard

⚡ Task 2: herokuappAlert.java

**📄 Description**

This test validates JavaScript alert functionalities on the Heroku app:

✔️ Simple JS Alert (accept)

✔️ Confirm Alert (dismiss)

✔️ Prompt Alert (sendKeys + accept)

**✅ Tested On**

Browser: Chrome (latest)

Platform: Windows 10

**🌐 Test URL**

https://the-internet.herokuapp.com/javascript_alerts

**🧪 Features**

Switches to alerts using driver.switchTo().alert()

Handles JS alerts using accept(), dismiss(), and sendKeys()

Uses static wait to handle alert timing

Marks test status on LambdaTest dashboard

**🛠 How To Run**

1. **Clone the Repository**

git clone <repo-url>
cd <repo-folder>

2. **Set Environment Variables**

export LT_USERNAME="your_username"
export LT_ACCESS_KEY="your_access_key"

💡 Alternatively, hardcoded credentials are already present in the code for demo purposes.

3. **Run the Tests**

**Basic execution:**

mvn test

**Run with TestNG parallel execution XML:**

mvn test "-Dsurefire.suiteXmlFiles=src/test/resources/parallel.xml"

🧪 This will execute tests in parallel across browsers as defined in parallel.xml.

**📁 Project Structure**

.
├── src/
│   └── test/
│       ├── java/
│       │   └── com/qa/satyam/
│       │       ├── herokuappLogin.java
│       │       ├── herokuappLogin1.java
│       │       └── herokuappAlert.java
│       └── resources/
│           └── parallel.xml
├── pom.xml
└── README.md

**🧑‍💻 LambdaTest Test IDs**

herokuappAlertTest: BYVIA-XIVDD-PYEGI-AE1XL

herokuappAlertTest1: UMWSW-P9TEU-WPAYC-GHC35

herokuappLogin1: MVNJC-FE4I9-CL5QQ-CO9GC

herokuappLogin: LSNVU-NZ2OF-2FBQ8-8J9ED


**Author**

Made with ❤️ by Satyam Srivastava.