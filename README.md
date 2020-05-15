# RideshareBackend API

*Rideshare* is a web application for matching Revature associates and employees with carpool drivers. Think Uber, only for Revature associates. *Rideshare*, originally known under the now-deprecated name _Rideforce_, currently acts as a training tool for graduating batches to gain real life experience with both the technological and organizational challenges of managing and updating legacy code. As it is, *Rideshare* implements an API for authentication and authorization, messaging, and user/driver matching. The development process is unique in that it is continually inherited as legacy code worked on in short bursts by developers with approximately the same knowledge-base and experience level. If you are reading this in preparation for the next sprint, you'll know what that means. 

Consequently, organizational knowledge behind this product is not always readibly accessible when tackling the code base for the first time. Still, we hope that this document can help orient new batches as to what previous design patterns and technolodies we chose, and perhaps even why these were beneficial or detrimental. 

## Features of this version

The most current implementation of this build included adding the following features:
 * Users should not be able to see contact information of other users
 * Riders should be able to send an email through the drivers page to request rides from drivers
 * Drivers should be able to confirm or deny requests, which would respectively send or not send
 * Users must verify email upon account creation or updating email
 * GoogleMapsAPI implementation refactoring
 * Client-side form validation refactored
 * Server-side form validation with JSR 303
 * Documentation and logging
 * Bug fixes
 * 80% unit test coverage on controller and service class methods

## New technologies included

In addition to the tech stack used to implement previous iterations of this project, a few new technologies were included to help meet the challenges of the above user stories. The original project uses an MVC design pattern built as a Java project running the Spring framework, persisted to a postgresql db running on an RDS instance, and displayed on the view layer through an implementation of Angular 2. This overall architecture remains the same, although two relatively distinct new technologies were introduced, namely a refactored implementation of the GoogleMapsAPI and introduction of the Thymeleaf templating system as a means of generating email messages. 

You can find excellent documentation and tutorials on the GoogleMapsAPI at its [official site](https://developers.google.com/maps/documentation).

While the documentation for Thymeleaf on its [official site](https://www.thymeleaf.org/documentation.html) is comprehensive, a number of our users found the tutorials to have minor drawbacks due to the relatively long time since its last update. Nonetheless, a number of other high quality tutorials can be found with a simple web search. 

A note on the GoogleMapsAPI. Obtaining permissions and API keys to deploy and test the project may be tricky to navigate and we recommend including this as one of the first items of business for any new batches picking up this project. 

## Internal documentation

Where possible, we have tried to leave a breadcrumb trail throughout the project, particularly in Java docs. These were fully reviewed to ensure that comments accurately reflected the code. We hope and encourage you to do the same. Congratulations for making it this far and we encourage you to make this project your own and turn *Rideshare* into the highest-caliber application it can be. 
