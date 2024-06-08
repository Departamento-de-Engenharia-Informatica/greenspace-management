# US002 - Create a new job

## 3. Design - User Story Realization 

### 3.1. Rationale

| Interaction ID                                                | Question: Which class is responsible for...                     | Answer               | Justification (with patterns)                                                                                      |
|:--------------------------------------------------------------|:----------------------------------------------------------------|:---------------------|:-------------------------------------------------------------------------------------------------------------------|
| Step 1  		                                                    | 	... interacting with the actor?                                | AssignTeamToAgendaUI | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.      |
| Step 2 - Requests data (agenda entry, team proposal)		        | 	... displaying the form for the actor to select the data						 | AssignTeamToAgendaUI | IE: is responsible for user interactions.                                                                          |
| Step 3 - Selects requested data (agenda entry, team proposal) | 	... temporarily keeping the inputted data                      | AssignTeamToAgendaUI | IE: is responsible for temporarily keeping the typed data until the actor confirms it                           |
| Step 4 - Shows all data and requests confirmation	            | 	... displaying all information before submitting               | AssignTeamToAgendaUI | IE: Temporarily shows inputted that until actor confirms it                                                         |
| Step 5 - Confirm data		                                       | 	... new agenda entry?                                                  | AgendaController     | IE: Responsible for coordinating the agenda entry creation process, interacts with both UI and repository.|
| Step 6 - Assign team to agenda entry                          | 	... creating new agenda entry?                          | Agenda               | IE: is responsible for user interactions.                                                                          |  
|Step 7 - Display operation success                       | 	... informing operation success?                        | AssignTeamToAgendaUI            | IE: is responsible for user interactions.                                                                        |  


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Agenda


Other software classes (i.e. Pure Fabrication) identified: 

* AssignTeamToAgendaUI  
* AgendaController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us002-sequence-diagram-full.svg)

[//]: # (### Split Diagrams)

[//]: # ()
[//]: # (The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.)

[//]: # ()
[//]: # (It uses Interaction Occurrence &#40;a.k.a. Interaction Use&#41;.)

[//]: # ()
[//]: # (![Sequence Diagram - split]&#40;svg/us006-sequence-diagram-split.svg&#41;)

[//]: # ()
[//]: # (**Get Task Category List Partial SD**)

[//]: # ()
[//]: # (![Sequence Diagram - Partial - Get Task Category List]&#40;svg/us006-sequence-diagram-partial-get-task-category-list.svg&#41;)

[//]: # ()
[//]: # (**Get Task Category Object**)

[//]: # ()
[//]: # (![Sequence Diagram - Partial - Get Task Category Object]&#40;svg/us006-sequence-diagram-partial-get-task-category.svg&#41;)

[//]: # ()
[//]: # (**Get Employee**)

[//]: # ()
[//]: # (![Sequence Diagram - Partial - Get Employee]&#40;svg/us006-sequence-diagram-partial-get-employee.svg&#41;)

[//]: # ()
[//]: # (**Create Task**)

[//]: # ()
[//]: # (![Sequence Diagram - Partial - Create Task]&#40;svg/us006-sequence-diagram-partial-create-task.svg&#41;)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us002-class-diagram.svg)