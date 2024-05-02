# US006 - Create a Task 

## 3. Design - User Story Realization 

### 3.1. Rationale



**Rationale for User Story 1:**

| Interaction ID | Question: Which class is responsible for... | Answer               | Justification (with patterns)                                                                                 |
|:-------------  |:--------------------- |:---------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		 |	... interacting with the actor? | RegisterSkillUI      | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		 |	... coordinating the US? | RegisterSkillController | Controller                                                                                                    |
| 			  		 |	... instantiating a new Skill? | SkillRepository     | Creator (Rule 1): in the DM, SkillRepository is responsible for managing Skills.                                |
| 			  		 | ... knowing the user using the system?  | UserSession          | Infrastructure Entity: manages user sessions and authentication.                                              |
| 			  		 |							 | SkillRepository      | Entity: represents the skills and their management.                                                           |
| Step 2  		 |							 |                      |                                                                                                               |
| Step 3  		 |	... saving the inputted skill? | SkillRepository     | Entity: manages the storage and retrieval of skills.                                                          |
| Step 4  		 |	... retrieving the list of skills to display? | SkillRepository     | Entity: provides access to the list of skills.                                                                |
| Step 5  		 |	... removing the selected skill? | SkillRepository     | Entity: responsible for deleting skills from the repository.                                                  |
| Step 6  		 |							 |                      |                                                                                                               |              
| Step 7  		 |	... validating the new skill (format validation)? | SkillRepository     | Entity: validates the format of the skill name according to business rules.                                    | 
| 			  		 |	... validating the new skill (uniqueness validation)? | SkillRepository     | Entity: ensures that the skill name is unique within the repository.                                           | 
| 			  		 |	... saving the new skill? | SkillRepository     | Entity: adds the new skill to the repository.                                                                 | 
| Step 8  		 |	... informing operation success?| RegisterSkillUI      | Interface Entity: responsible for user interaction and feedback.                                                | 


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Employee
* Skill

Other software classes (i.e. Pure Fabrication) identified: 

* RegisterSkillUI
* RegisterSkillController


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