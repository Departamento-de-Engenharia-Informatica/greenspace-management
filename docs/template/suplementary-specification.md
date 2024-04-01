

# Supplementary Specification (FURPS+)

## Functionality Especifica funcionalidades que são comuns em vários EUA/UC e, portanto, não são
## capturado nos cenários do usuário

_Specifies functionalities that:  
&nbsp; &nbsp; (i) are common across several US/UC;  
&nbsp; &nbsp; (ii) are not related to US/UC, namely: Audit, Reporting and Security._

(i) Business rules validation must be respected when recording and updating data.  
(ii) The class structure must be designed to allow easy maintenance and the addition of new features, following the best OO practices.

## Usability user interface  avalia a interface

_Evaluates the user interface. It has several subcategories, among them: error prevention; interface aesthetics and design; help and documentation; consistency and standards._

The application’s graphical interface is to be developed in JavaFX 11. All those who wish to use the application must be authenticated with a password of seven alphanumeric characters, including three capital letters and two digits. The application documentation must be in English language.

## Reliability  Refere-se à integridade, conformidade e interoperabilidade do software

_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

The development team must implement unit tests for all methods, except for methods that implement Input/Output operations. The unit tests should be implemented using the JUnit 5 framework. The JaCoCo plugin should be used to generate the coverage report.

## Performance Avalia o desempenho do software (ou seja, tempos de execução, uso, disponibilidade)

_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

(fill in here )

## Supportability  Diz respeito à estabilidade, manutenabilidade, compatibilidade, configurabilidade, instalação, escalabilidade

_The supportability requirements gather several characteristics, such as: testability, adaptability, maintainability, compatibility, configurability, installability, scalability and more._

During the system development, the team must: (i) adopt best practices for identifying requirements, and for OO software analysis and design; (ii) adopt recognized coding standards (e.g., CamelCase); (iii) use Javadoc to generate useful documentation for Java code. All the images/figures produced during the software development process should be recorded in SVG format. The application should use object serialization to ensure data persistence between two runs of the application.

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

(fill in here )

### Implementation Constraints

_Specifies or constraints the code or construction of a system such as: mandatory standards/patterns, implementation languages, database integrity, resource limits, operating system._

The application must be developed in Java language using the IntelliJ IDE or NetBeans.

### Interface Constraints

_Specifies or constraints the features inherent to the interaction of the system being developed with other external systems._

(fill in here )

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

(fill in here ) 

