## General Info

Programmers is a web application designed to assist in managing data about programmers. It connects to github API, search throught the the repositories and fetchs the data. When user find repositories created by concrete programmers then is able to manipulate records to local database. I host backend and also frontend of this application by AWS EC2. Link to working app: [Programmers](https://maciejcreatessoft.com/)


## Key Features:

- **CRUD:** Users can create, read, ubdate, delete programmer profiles by providing most necessary details.
  

- **Filter Repositories:** Users can search GitHub repositories with exact title and technology
  
- **Public API:** Application also gives public Api which fetch data from GITHUB API and filters it by title, technology (type your tittle+technology in the end of the url).
  
- **Browse Data:** Users can browse available programmer profiles, checking their skills, experience, and other information.

## Technologies:

**Backend:** Java, SpringBoot, Hibernate, Security

**Frontend:** JavaScript, React.js, HTML, CSS, vite.js, bootstrap 

**Databese:** MySQL 

**Deployment**: AWS EC2 (Linux)

**Security**: Spring Security

**Testing:** JUnit, Mockito, MockMvc






