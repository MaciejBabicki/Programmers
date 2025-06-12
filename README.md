## General Info

Programmers is a web application designed to assist in managing data about programmers. It connects to github API, search throught the the repositories and fetchs the data. It allows to add, edit, and delete programmer profiles, as well as browse their details. 
I have created this project thinking about two applications:
1) Application to find the programming-trainer for private lessons
2) Application to find an employee for a company

## Key Features:

- **Add Programmer:** Users can add new programmer profiles by providing relevant details such as name, surname, skills, experience, etc.

- **Edit Profiles:** Existing programmer profiles can be edited to update information. Users can modify programmer details, add new skills, update experience, etc.

- **Delete Profiles:** The application enables the removal of unnecessary programmer profiles from the database.

- **Browse Data:** Users can browse available programmer profiles, checking their skills, experience, and other information.

- **Authentication and Authorization:** Access to the application is secured using Spring Security, requiring users to authenticate through login.

## Technologies:

**Backend:** Java, SpringBoot, Hibernate, Security

**Testing:** JUnit, Mockito, MockMvc

**Frontend:** JavaScript, React.js, HTML, CSS, vite.js, bootstrap 

**Databese:** MySQL

**Security**: Spring Security


![Programmers](https://github.com/MaciejBabicki/Programmers/assets/123827748/c271de49-bc31-4677-a039-b11874ff387e)

## System Requirements:

- Java 8 or higher
- MySQL (installed locally or available as a remote service)

## Setup
**Clone the Repository**

Clone the repository or download the source code:
```
git clone <https://github.com/MaciejBabicki/Programmers.git>
```
**Configure Your Environment**

Ensure you have Java installed, version 8 or newer.

**Download External Libraries**

This project uses external libraries such as Spring Boot and Hibernate. Ensure that you have Maven installed, then navigate to the project directory and run:
```
mvn clean install
```
This command will download all required dependencies and build the project.

**Set Up the Database**

The application uses MySQL database. You need to set up a MySQL server and create a database for the application. Update the `application.properties` file in `src/main/resources` directory with your database configuration.

**Run the Application**

Once the database is set up and the project is built, you can run the application using the following command:
```
mvn spring-boot:run
```
This will start the Spring Boot application.

**Access the Application**

Once the application is running, you can access it at http://localhost:8080 in your web browser.

**Unit Tests**

The project includes unit tests. You can run them using Maven:
```
mvn test
```
