# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:  
&nbsp; &nbsp; (i) are common across several US/UC;  
&nbsp; &nbsp; (ii) are not related to US/UC, namely: Audit, Reporting and Security._

Validation of business rules during data registration and update.

Implementation of a class structure designed for easy maintenance and addition of new functionalities following Object-Oriented best practices.

## Usability

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

Authentication requirement for users with a password of seven alphanumeric characters, including three capital letters and two digits.

Development of the graphical interface using JavaFX 11, ensuring user-friendly interactions.

Support for the English language in the application and all project artifacts.

## Reliability

_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

Adoption of best practices for requirement identification, OO software analysis, and design to ensure reliability.

Adherence to recognized coding standards like CamelCase.

Usage of Javadoc to generate comprehensive documentation for Java code.

## Performance

_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

Utilization of object serialization for data persistence between application runs, enhancing performance.

Implementation of unit tests for all methods (except those involving Input/Output operations) using the JUnit 5 framework to ensure performance and stability.

Use of the JaCoCo plugin to generate coverage reports for effective performance monitoring.

## Supportability

_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._

Adoption of a development approach that allows for easy maintenance and addition of new features.

Requirement for unit tests for method implementations, contributing to long-term supportability.

Recording of all images/figures produced during software development in SVG format for future reference and support.


## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

Development of the application in Java language using either IntelliJ IDE or NetBeans.

Graphical interface development using JavaFX 11.

Authentication requirement for users with specific password criteria.

### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

Utilization of object serialization for data persistence.

Requirement for unit tests using JUnit 5 framework.

Usage of the JaCoCo plugin for coverage report generation.

### Interface Constraints

_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

Adoption of the JavaFX 11 framework for graphical interface development.

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

Record of all images/figures in SVG format during software development.