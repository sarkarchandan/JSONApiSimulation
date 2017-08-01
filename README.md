# RESTful Api Simulation
This project has simulated a RESTful API which exposes data persisted in backend database serialised in JSON format. We often consume multiple public APIs offered by different providers in our applications. Thus the motivation of this project is to create a simulation to persist some mock data in a backend database and exposing the data as a RESTful endpoint. The frameworks have been used are:

- [Java EE Specification](https://javaee.github.io)
- [Docker](https://www.docker.com)
- [PostgreSQL](https://www.postgresql.org)

A very simple schema has been defined to represent the data. It pretends a collection of faculties in a university and the collection of courses offered by each faculty.

It has a backend interface for adding the Faculties to the database and another subsequent interface for adding the courses to each faculty. One faculty naturally can accommodate many courses.

It used linux container technology with docker to make the project portable. Hence in order to execute the project one needs just a native installation of docker platform itself and docker-compose framework. While executing, this project creates two docker containers (one for hosting the PostgreSQL database and another for hosting the web application inside [glassfish](https://javaee.github.io/glassfish/) application server). 

---

### Build and Configuration
A native installation of [Docker](https://www.docker.com) and Docker Compose framework is required for executing the project.

````
Clone the Repository: git clone git@github.com:sarkarchandan/JSONApiSimulation.git
````
For this project the port 8888 has been used as default. We have mapped the port 8080 inside the container with the port 8888 on host machine. It can be changed simply by editing the docker-compose.yml file at the the following place:

````
ports:
      - "8888:8080"
````

````
Execution: docker-compose up
````
It's that simple !!!

First execution of the project will take a little time since docker will download the required dependencies and cache them for re-use in subsequent executions.

---

### Demonstration
```http://localhost:8888/server/add_faculty``` 
on any browser will open an interface for adding the faculty groups which will also list down the currently available faculty groups in the database (initially blank).

<img src="https://cloud.githubusercontent.com/assets/19269229/26532124/6ba4499e-43f9-11e7-80db-7cb020eaf490.png" width="300" height="120">

```http://localhost:8888/server/add_course``` 
on any browser will open an interface for adding the courses to all available faculty groups which will also list down the currently available courses in the database (initially blank).

<img src="https://cloud.githubusercontent.com/assets/19269229/26532146/9859015a-43f9-11e7-8c84-56ab9afcd30c.png" width="300" height="150">

```http://localhost:8888/server/endpoint/rest/faculties``` will mimic a RESTful Api to expose the data currently stored in the PostgreSQL database serialised in JSON format.

```
{
"facultyGroupAbbreviation": "DSG",
"facultyGroupId": 1,
"facultyGroupName": "Distributed Systems Group",
"getFacultyGroupHead": "Professor Dr. Guido Wirtz",
"simpleCourseList": [
  {
"courseAbbreviation": "DSG-IDistrSys",
"courseFacultyGroup": "DSG",
"courseId": 5,
"courseName": "Introduction to Distributed Systems",
"termCourseOfferedIn": "Summer"
},
  {
"courseAbbreviation": "DSG-SOA-M",
"courseFacultyGroup": "DSG",
"courseId": 6,
"courseName": "Service-Oriented Architecture and WebServices",
"termCourseOfferedIn": "Summer"
},
  {
"courseAbbreviation": "DSG-DSAM-M",
"courseFacultyGroup": "DSG",
"courseId": 7,
"courseName": "Distributed Systems Architecture & Middleware",
"termCourseOfferedIn": "Winter"
}
```

:octocat:




