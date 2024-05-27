# US006 - Create a Task 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for... | Answer                      | Justification (with patterns)                                                                                              |
|:-------------  |:--------------------- |:---------------------|:---------------------------------------------------------------------------------------------------------------------------|
| Step 1  		 | ... interacting with the actor?            | RegisterGreenSpaceUI        | Pure Fabrication: no existing class in the Domain Model directly interacts with the actor.                                  |
| Step 2  		 | ... prompting for required fields?         | RegisterGreenSpaceUI        | Controller (High Cohesion, Low Coupling): RegisterGreenSpaceUI interacts with the user to gather necessary information.     |
| Step 3  		 | ... providing green space details?         | RegisterGreenSpaceUI        | Controller (High Cohesion, Low Coupling): RegisterGreenSpaceUI collects and passes green space details to the system.       |
| Step 4  		 | ... validating details and creating green space object? | GreenSpace            | Creator (High Cohesion): GreenSpace is responsible for creating the appropriate green space object based on the provided details.  |
| Step 5  		 | ... storing green space in repository?     | GreenSpaceRepository        | Pure Fabrication (Low Coupling): GreenSpaceRepository handles storing green space objects, reducing coupling with other classes. |
| Step 6  		 | ... confirming successful green space registration? | RegisterGreenSpaceUI  | Controller (Polymorphism, Protected Variations): RegisterGreenSpaceUI confirms the successful registration to the actor.       |
### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* GreenSpace
* GreenSpaceRepository

Other software classes (i.e. Pure Fabrication) identified: 

* RegisterGreenSpaceUI
* RegisterGreenSpaceController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us006-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us006-sequence-diagram-split.svg)

**Get Task Category List Partial SD**

![Sequence Diagram - Partial - Get Task Category List](svg/us006-sequence-diagram-partial-get-task-category-list.svg)

**Get Task Category Object**

![Sequence Diagram - Partial - Get Task Category Object](svg/us006-sequence-diagram-partial-get-task-category.svg)

**Get Employee**

![Sequence Diagram - Partial - Get Employee](svg/us006-sequence-diagram-partial-get-employee.svg)

**Create Task**

![Sequence Diagram - Partial - Create Task](svg/us006-sequence-diagram-partial-create-task.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us006-class-diagram.svg)