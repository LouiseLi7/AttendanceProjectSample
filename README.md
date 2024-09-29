### This is a course/section/student registration and attendance recording program, including the usage of Java, Gradle, Socket, Google APIs, MySQL, Docker, Junit Test, GUI(JavaFX), Multi-Threading, MVC Architecture. Please see 'E3 documents' for detailed explanations of the project. 


## Prerequisite
### Database Setup
1. installation of mysql
2. adding a new user named 'java' and setting the password as 'password'
3. adding a new database named 'javabase'
### JDK 17
### JavaFx 


## Installtion
If you are opening the GitLab page, you can download the zip file or git clone the whole repository.

## Run
1. add/update/remove students and professors by running './gradlew run-admin' and follow instructions 
2. add/update/remove courses,sections,and lectures by running './gradlew run-courseManagement' and follow instructions 
3. enroll and unenroll students by running './gradlew run-courseManagement' and follow instructions 
4. If you want to log in as a student or a professor, please run './gradlew run-server' first and then './gradlew run-client' for one client

## Notes
However, due to the limitation of the GITHUB, you have to replace your Gmail Address to yours and get the credential through Google Gmail API in credentials.json inside the 'shared/src/main/resources'. 
