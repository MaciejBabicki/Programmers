## General Info

Programmers is a web application designed to assist in managing data about programmers. It connects to github API, search throught the the repositories and fetchs the data. When user find repositories created by concrete programmers then is able to manipulate records to local database. I host backend and also frontend of this application by AWS EC2. 


## Key Features:

- **CRUD:** Users can create, read, ubdate, delete programmer profiles by providing most necessary details.
  [Programmers](https://maciejcreatessoft.com/)

- **Filter Repositories:** Users can search GitHub repositories with exact title and technology
  [Filter repositories](http://ec2-3-126-250-109.eu-central-1.compute.amazonaws.com:81/Programmers/repos)
  
- **Public API:** Application also gives public Api which fetch data from GITHUB API and filters it by title, technology (type your tittle+technology in the end of the url).
  [API](http://ec2-3-126-250-109.eu-central-1.compute.amazonaws.com:8080/repos/Wheater+java)
  
- **Browse Data:** Users can browse available programmer profiles, checking their skills, experience, and other information.

## Technologies:

**Backend:** Java, SpringBoot, Hibernate, Security

**Frontend:** JavaScript, React.js, HTML, CSS, vite.js, bootstrap 

**Databese:** MySQL 

**Deployment**: AWS EC2 (Linux)

**Security**: Spring Security

**Testing:** JUnit, Mockito, MockMvc






