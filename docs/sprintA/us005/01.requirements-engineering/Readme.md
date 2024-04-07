# US005 - Generate a Team


## 1. Requirements Engineering

### 1.1. User Story Description

As a HRM, I want to generate a team proposal automatically.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> (HRM) manages the human resources and defines the teams according to the needs of the projects in progress and the skills of the employees.

**From the client clarifications:**

> **Question:** What are the input data to automatically generate a team?
>
> **Answer:** the max size of the team (for instance 4)
and the skill needed: 4 tree pruner and 1 light vehicle driver
meaning that one team member have 2 skills.

> **Question:** What should the output of the automation be? (should it just store the team proposal or show it to the customer?)  Will the team proposal be a document about all the instructions of each team member/worker?
>
> **Answer:** The systems provide team proposals and HRM can accept of refuse the proposals. In the future (not in this sprint) HRM may decide to edit the team.

### 1.3. Acceptance Criteria


* **AC1:**  When creating a task with an existing reference, the system must reject
  such operation.
* **AC2:**  The maximum team size and the set of skills need to be supplied by
  the HRM.
* **AC3:**  The intended skill set must be provided by the HRM.



### 1.4. Found out Dependencies

* It depends on US04 and US05, as it takes collaborator with certain professions and skills for a team to be generated.

### 1.5 Input and Output Data

**Input Data:**

* Typed data: 
    * numberColaborators 
    * skils

* Selected data:
    * generate a team.

**Output Data:**

* List of teams.
* In(Sucess) of the operation: By sending employee details, the system confirms whether the operation was successful or unsuccessful, providing feedback


### 1.6. System Sequence Diagram (SSD)



![System Sequence Diagram - Alternative One](svg/us005-system-sequence-diagram-alternative-one-System_Sequence_Diagram__SSD___US005.svg)


### 1.7 Other Relevant Remarks


- Consideration should be given to potential future iterations where HRM may need the ability to edit generated team proposals, though this functionality is not within the scope of the current sprint.

- It's essential to validate input data, including the number of collaborators and required skills, to ensure accurate team proposal generation.

- Depending on system architecture, considerations should be made for scalability and performance optimization when handling large datasets or frequent team generation requests.

- Any potential security considerations, such as access controls and data encryption, should be addressed to protect sensitive HRM and employee information.



