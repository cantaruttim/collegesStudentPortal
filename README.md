# Student Portal

The main objective of this system is to manage grades and modules
that a student has being enrolled. Also, we have an goal to automate
the academic process and the student as well as the secretary experience
during the course.

All the system will be implemented using a No Relational Database MongoDB
because we can easily manipulate students' and academic's information.

## Already Implemented
- Create Student functionality 
- Create User student 
  - user login default
  - user password default
  - first login implemented (must change default password)
- EmailSender where students can receive user login and password
- JWT with tokenization of student
- Roles & Permissions**

## 🛠️ Technologies & Tools

- Java 21
- Spring Boot
- Spring Security
  - JWT
  - Password Cryptography (BCrypt)
- Spring Email

## Modeling 

- Roles and Permissions: It represents the roles and permissions that who is using the system can do
- Create Student: It represents the forms that the secretary will fill in order to register a student.
At the end of this process, an email will be sent to the student containing the `user login` and `user password`
- User: It represents the entity that will be created when a student is created. At the first login the user *must* 
change his default password to another password.

- defaultPassword
- defaultRegistrationNumber
- EmailSender


- Login


- Build Activity By Theacher
- ActivityForms (Intensive and Master)
- Coorp



- Grades

## Benefits 
- Build a system where the registration number of a student is created automatically, as well as, a default password is created automatically. Those two benefits are new to portal student.
- Security is our main goal where, so the student only will be authorized to access the his portal after change the default password on his first access.

- Student's grades are calculated automatically as business rules.

## Roles & Permissions
- The roles and Permissions were implemented on this system and during the Student creation, this student
extends Roles and Permissions that specify what they can do
- To create a Student, must be logged on as ADMIN Role

## Tests
All the tests will be found on `scripts` folder