## Project Title - STUDIBOOK

### <h3> Project Objective </h3> <a name="objective"></a>
 -	The purpose of this application is to have all the android projects that the students are working on at one place.
 - 	The projects are categorized based on their domains which makes it easier for the students to search and refer to the android projects in the previous semesters.
 
### <h3>  Team members </h3> 
Jessica Salome </br>
Sajida Mohammad  </br>
Sreeja Kumbhum </br>
Thaarani Yerramsetty </br>

<p>
 Thaarani Yerramsetty S547062 </br>
 1. Instructor Login page.</br>
 2. Help and support page also and</br>
 3. Enter project details page also.</p>
 
 <p>
 Sreeja Kumbhum S547055 </br>
 1. Pie chart. </br>
 2. About Page. </br>

<p>
 Sajida Mohammad S546648 </br>
 1. Search bar and fetched the projects when clicked on search bar. </br>
 2. Navigation bar which redirects to the respective pages. </br>
</p>

 <p>
 Jessica Merugu S547054 </br>
 1. Project details. </br>
 2. Added details of project, its description and team member details. </br>
<p><br>
 
### Application information
The application purpose is to have all the android projects of the students who worked on the differernt domains. This helps students to refer previous android projects for reference, code and reference regarding projects can be founfd in  GITHUB repository. A menu bar is developed to switch between pages for simple navigation. For each ongoing semester, only instructors (Professor/GA) may add or amend the information regarding the students' projects. This application could be utilized for a project for the students future trimesters.<br>
#### <h4> Activities:<br></h4>
##### Launcher Screen: <br>
This screen consists of a Menu which has 5 sub categories
 - Home - Displays a search screen
 - About Us - Provides basic information
 - Add on Projects - Required to add the projects
 - Graphical Prospect - Statistical Information
 - Help & Support - For further assistantce
 ##### Search Screen: <br>
 The Home panel on this screen allows students to search for different projects by domain or project name. Details on a certain project are displayed when the user clicks on the project's links. I nformation about the project, the GitHub link, the team members (where applicable), and  Upon clicking on a certain team member, his profile and contact information would open. Students who participated in the project's communication channels (emails would be posted) as well as the semester in which the project is created.<br>
##### About us screen:
 This screen provides the basic information related to the application.<br>
##### Add on Projects:
 Only professors have access to this screen to enter details about new projects for ongoing semesters. A popup is displayed where the instructor can enter student data to add team members. And the Plus icon might be used to add multiple team members.<br>
##### Graphical Prospect: 
 This screen displays the statistical information of various domain related projects.<br>
##### Help & Support:
 This screen will display details regarding the graduate assistant for the current semester. From the same screen, students can also contact the GA via phone or email.<br>
 
### Application Usage Guidelines:<br>
<p>Every student can use this application as a reference or to know the how many projects have been worked on on each domain which provides them an idea and this can be perfectly represented as a graphical representation the pie chart.</br>
In this Instructor and GA will have login access for edit, delete, insert, update the data of the student and related project too.</br></p>

##### Search: <br>
- A Student can search the project with the related keyword of the particular domain or with the title of the project. 
- With the related keyword it will display the number of projects available under it. </br>

##### Categories: <br>
- In this we have created different general categories where most of the project falls under any of the categories mentioned. 
- If a project does not relate to any domain it will be placed under miscelleanous domain.
- As the project list will be displayed with all the related projects from that particular domain. </br>

##### Information about a project: <br>
- This is a student view. where he can just gather or have a look at the infromation , he can not have access for the modifications.
- A project will be displayed information about description of the project, year, batch, team member details, github link, each team members personal information too like their S.Id, mail address. Each of the mailing address is functional.</br>

##### Adding a project: <br>
- This is only accessible to the instructor and the related GA of that semester. 
- They can add a project by login with the details provided. Once they have login in, they can fill the all the required details of the project such as title, description of the project, batch, year, category, github link, members of the project team. 
- In the section team members, they have to enter the student name, S.Id, Mail address and publish the project.
- To add a new member every time they have to click "add new" button.
- When they have filled the whole required information about a project then they can hit "Continue" button.</br>

##### Modification a project: <br>
- This is only accessible to the instructor and the related GA of that semester. 
- They can modify a project only when they have login in, they can update, delete, create, modify the project details/information such as title, description of the project, batch, year, category, github link, members of the project team. </br>

##### Graphical Represention:  <br>
- when the project is added in the above section it will be updated in the data base and it will reflect under the particular domain instantly. 
- It shows the number of projects present in the each domain. The representation of the projects can be displayed in the form of percentages. 
- Visual representation of data in a pie chart is done as a fractional part of a whole. 
- when we add all slices together it should represent the cent percentage. 
- Each slice defines the category and the percentage of each slice hold.</br>

##### Help & Support: <br>
- In this information related to the current semester GA and instructor will be present whom students can contact if they face any issue. </br>

#### Test Credentials for Login: 
UserName: Instructor <br>
Password: nwmissouri

#### Supported Devices: 
 - API level 22 to API level 31 <br>
 - Google play store supported Emulators is required to run the application

#### Advantages:
- User-Friendly.</br>
- Handy to the students for both code and for references. </br>
- All android projects references together on the single application.</br>
- Provides percentages of each domain which helps students to chose the project on the domains which are not worked on it.</br>
- Persistent Data.</br>
- We used Firebase Database to store and retrieve the data.</br>
- Pie chart provides a better comparison of data for the students. And effective mode of communication.</br>

#### API/Libraries:
###### Google Firebase:</br>
 com.google.firebase:firebase-database:20.0 (This can be used when Android API is 9 or above)</br>
###### Pie chart functionality:</br>
 com.github.blackfizz:eazegraph:1.2.5 </br>
 com.nineoldandroids:library:2.4.0</br>
 
#### Technical Stack</br>
###### Android : xml, java</br>

#### Problems Faced:
- Finding the projects with the keywords as both categories shared same word.</br>
- Issues with the piechart.</br>
- Issues in displaying the percentages for the number of projects stored in the data base.</br>
- It's also possible that this application will cause problems with loading.</br>
- Issues in the search functionality.</br></p>

