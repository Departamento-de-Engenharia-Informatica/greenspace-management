# US001 - As a Human Resources Manager (HRM), I want to register skills that a collaborator may have.



## 1. Requirements Engineering

### 1.1. User Story Description

User Story 1 focuses on optimizing the skill registration process within MusgoSublime (MS) for HR managers. This enhancement aims to streamline collaborator skill management, allowing HR managers to allocate tasks more effectively based on individual competencies. By improving this aspect of the system, MS can enhance project planning and execution efficiency, leading to increased productivity and resource utilization.


### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Each task is characterized by having a unique reference per organization, a designation, an informal and a technical description, an estimated duration and cost, as well as a task category. 

>	As long as it is not published, access to the task is exclusive to the employees of the respective organization. 

**From the client clarifications:**

> **Question:** Which is the unit of measurement used to estimate duration?
>
> **Answer:** Duration is estimated in days.

> **Question:** Monetary data is expressed in any particular currency?
>
> **Answer:** Monetary data (e.g. estimated cost of a task) is indicated in POT (virtual currency internal to the platform).

### 1.3. Acceptance Criteria

* **AC1:** The system must allow the HRM to register skills for a collaborator.
* **AC2:** Each skill entry must include a name and description.
* **AC3:** The system must ensure that skills are unique for each collaborator.
* **AC4:** The HRM should be able to view and modify the skills of each collaborator.
### 1.4. Found out Dependencies

* There is a dependency on "US003 - Create a task category" as there must be at least one task category to classify the task being created.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a reference
    * a designation 
    * an informal description
    * a technical description
    * an estimated duration
    * an estimated cost
	
* Selected data:
    * a task category 

**Output Data:**

* List of existing task categories
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)



![System Sequence Diagram - Alternative One](C:\Users\João Vieira\Desktop\greenspace-management\docs\sprintA\us001\01.requirements-engineering\puml\us01-system-sequence-diagram.puml)




### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.