# Employee UI
This project is part of a demo to demonistrate Angular UI app and Sprinboot services.

Angular UI app: https://github.com/bhupatikrish/employee-ui

Springboot service: https://github.com/bhupatikrish/employee-service


## Physical architecture diagram
Both Angular UI and Springboot applications are deployed to Amazon web services as per below architecture.


<img src="https://s3.amazonaws.com/github-arch-img/physical-arch.png" height="400px" width="500px"/>

The app can be either accessed via a desktop or mobile web browser.

Angular application is deployed to Amason S3 bucket as a static web application. 

App Url: http://employee-ui.s3-website-us-east-1.amazonaws.com


Springboot aplication is deployed to Elastic Beanstalk as a Java application.

Service Url: http://employee-service.us-east-1.elasticbeanstalk.com/api/employees

## Component sequesnce diagram

<img src="https://s3.amazonaws.com/github-arch-img/component-sequence.jpg" height="400px" width="500px"/>

When the UI application is accessed, the app is donwloaded to the browser. 
Angular application is bootstrapped and based on the route configurations the initial component/ page is loaded.
Angular application calls the backend springboot services asynchronously as needed.

## Employees UI

There are two views for this application. Either view can be switched by selecting the 3 dots on the top right of the screen.

1) "/sidenav": Employees displayed in side navigation and the main area displays employee information (view/ edit/ delete/ add). Users can be searched from search box on the side navigation. This search calls the backend "/search" endpoint to filter employees.

2) "/grid"" Employees are displayed using a grid displayed in the center of the screen. Employee information (view/ edit/ delete/ add) is displayed below the grid. The grid can be filtered using the filter above the grid. This is in-memory filtering using Google meterial data table component. https://material.angular.io/components/table/overview

The app is build using Angular framework' (https://angular.io/) and Google material design (https://material.angular.io).

When the app is bootstrapped and loaded, based on the route configuration, the default route "sidenav" and its corresponding component/ page SidenavDemoComponent is loaded.

Below is high level component diagram.

<img src="https://s3.amazonaws.com/github-arch-img/ui-components.jpg" height="400px" width="500px"/>

<ul>
  <li>AppModule: This is the main app module. It imports modules, components and services used in this application.</li>
  <li>MaterialUiModule:It imports all the material UI components used in this application.</li>
  <li>AppRoutingModule: It defines the routes for this applications.</li>
  <li>AppComponent: This is the main component. It contains router-outlet to host different views.</li>
  <li>SidenavDemoComponent: This is the default view on the application. It imports other components to the main area based on use selections. The main area hide/ show respective component based on the mode (add/ view/ edit).</li>
  <li>GridDemoComponent: This is the grid view on the application. It imports other components below the grid based on use selections. The main area hide/ show respective component based on the mode (add/ view/ edit).</li>
  <li>EmployeeStartComponent: This is the default component on the working area.</li>
  <li>EmployeeDetailComponent: This component is loaded when employee is selected. This component is imported in both views.</li>
  <li>EmployeeEditComponent: This component is loaded when employee is beign edited by selecting "Edit" button on EmployeeDetailComponent component. This component is imported in both views.</li>
  <li>DeleteDialogComponent: This component is loaded when employee is beign deleted by selecting "Delete" button on EmployeeDetailComponent component. This component is imported in both views.</li>
  <li>EmployeeService: This service is being injected into all components. It fetches data by making async http calls to the backend employee service. This service also provide Observables to communicate events between components using observable pattern.</li>
</ul>

## Employee service

This service is developed using Spring Framework. https://projects.spring.io/spring-framework/

<img src="https://s3.amazonaws.com/github-arch-img/service-sequence.jpg" height="400px" width="500px"/>

## Local development environment

<ol>
  <li>Install Nodejs server. https://nodejs.org</li>
  <li>Install Angular CLI. https://cli.angular.io/</li>
  <li>Install Spring tool suite or similar Java IDE. https://spring.io/tools/sts/all</li>
  <li>Install Git. https://git-scm.com/</li>
  <li>run "git clone https://github.com/bhupatikrish/employee-service.git"</li>
  <li>Open Java IDE and import employee-service. Run the project as "Springboot application"</li>
  <li>run "git clone https://github.com/bhupatikrish/employee-ui.git"</li>
  <li>Go to employee-ui folder and run "npm install"</li>
  <li>run "ng serve". Navigate to `http://localhost:4200/`</li>
</ol>


