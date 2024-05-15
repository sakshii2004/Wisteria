# Wisteria - A Productivity Application
_A desktop application that will be your ultimate companion for boosting productivity, organizing your life and fostering positive habits._


The Wisteria flower, known for its cascading clusters of fragrant blooms, symbolizes longevity, devotion and reflection. It's a vine that climbs and thrives, constantly reaching for new heights. 


Just like the Wisteria flower, our application aims to help you flourish on your productivity journey. Our aim is to provide a comprehensive set of features to help you manage your tasks, set goals and and engage in deep reflection through journaling, all designed to increase your efficiency and achieve your aspirations whether they may be short or long-term. Streamline your workflow, prioritize tasks and cultivate a healthier work-life balance with intuitive features designed to empower your daily routines.

## Features
- User Management: The Login and Register features allow you to secure your data and personalize your experience. The MyAccount section also allows you to check your account details as well as update passwords if needed.
- Home Page: Get the summary of tasks on your to-do list as well as track your productivity for the day, all in one page!
- Time Management: A built-in timer that helps you stay focused and track your progress on specific tasks.
- Calendar Integration: Wisteria seamlessly integrates with your monthly calendar, giving you a holistic view of your commitments and deadlines.
- Goal Setting: Define your monthly goals and track your progress towards achieving them.
- Daily To-Do List: Create and manage your daily to-do lists, prioritizing tasks and ensuring nothing falls through the cracks.
- Journaling: Write journal entries to reflect on your progress, challenges and learnings.

## Functionalities
All the different functionalities are separated into different packages with each package containing the FXML file responsible for the aesthetic front-end, a controller class, a database class which talks to the database for the controller class and other necessary class files. If a feature has multiple UIs, then the package contains more than one FXML file and controller class. 

An overview of all the packages: 

- **application**: contains the main.java which is the entry point of the application. It renders the login page.
- **authentication**: contains java classes responsible for the register and login functionalities.
- **base**: contains the base java classes like DB.java, SceneController.java that other classes extend. [See class diagram](#Class-Diagram)
- **calender**: contains the java classes responsible for the calendar functionality.
- **dailytask**: contains the java classes responsible for the daily to-do list functionality: add daily task, view task, delete task etc.
- **home**: contains the java classes responsible for rendering the homepage.
- **journal**: contains the java classes responsible for the journal aspect of the application: adding journal entries, viewing journal entries etc.
- **monthlytask**: contains the java classes responsible for setting, viewing, deleting monthly goals.
- **myaccount**: contains the java classes that enable to user to view their account details, as well as, update their passwords. Also incorporates a [custom exception](#Custom-Exception).
- **tasks**: package containing the classes that are the blueprint of all task objects, be it monthly or daily.
- **timer**: contains the java classes responsible for timer functionality: set the timer while you do the task and the timer runs in the background.
- **utility**: contains the miscellenous java classes that aid in various other classes.

#### Custom Exception:
- **MyAccountException**: Custom exception thrown when any error occurs while fetching the account details for My Account page.

## Class Diagram
![CDW1](https://github.com/sakshii2004/Wisteria/assets/124381306/15468301-0684-4ce4-ac3f-3d5466d75b29)

## Database Schema
![Untitled design (6)](https://github.com/sakshii2004/Wisteria/assets/124381306/d5438f02-b978-41d1-a6f9-fd90147db8bb)

## Homepage of the Application
The homepage is customized for each user. It displays a personalized welcome message, the tasks pertaining to that day for the user and the minutes the user has been productive on that day.

![image](https://github.com/sakshii2004/Wisteria/assets/124381306/e951fbd1-acfa-411a-9eb9-9bab193fb37c)

## Link to Project Demo
https://www.kapwing.com/videos/66437dbc58ef0e615cf5538c

## Know more about Wisteria
https://www.canva.com/design/DAGBFNm5OcA/r6FdkeXvofQnh1rI-H2a6g/view?utm_content=DAGBFNm5OcA&utm_campaign=designshare&utm_medium=link&utm_source=editor
