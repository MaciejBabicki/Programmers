## General Info

Programmers is a web application designed to assist in managing data about programmers. It connects to github API, search throught the the repositories and fetchs the data. When user find repositories created by concrete programmers then is able to create records to local database.  It allows to add, edit, and delete programmer profiles, as well as browse their details. 


## Key Features:

- **CRUD:** Users can create, read, ubdate, delete programmer profiles by providing most necessary details.
  [Programmers](http://ec2-3-126-250-109.eu-central-1.compute.amazonaws.com:81/Programmers/)
  
- **Public API:** Application also gives public Api which fetch data from GITHUB API and filters it by title, technology (type your tittle+technology in the end of the url).
  [API](http://ec2-3-126-250-109.eu-central-1.compute.amazonaws.com:8080/repos/Wheater+java)
  
- **Browse Data:** Users can browse available programmer profiles, checking their skills, experience, and other information.

## Technologies:

**Backend:** Java, SpringBoot, Hibernate, Security

**Testing:** JUnit, Mockito, MockMvc

**Frontend:** JavaScript, React.js, HTML, CSS, vite.js, bootstrap 

**Databese:** MySQL

**Security**: Spring Security






