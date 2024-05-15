## US004- Assign Skills to a Colaborator3. Design - User Story Realization

## 3. Design - User Story Realization

### 3.1. Rationale

**The rationale grounds on the SSD interactions and the identified input/output data.**

| Interaction ID | Question: Which class is responsible for...      | Answer                                | Justification (with patterns)                       |
| :------------- | :----------------------------------------------- | :------------------------------------ | :-------------------------------------------------- |
| Step 1         | Interacting with the HRM?                        | RegisterSkillToCollaboratorUI         | UI, interacts with user.                            |
| Step 2         | Knowing all skills in the system?                | SkillRepository                       | Pure Fabrication, IE.                               |
| Step 2         | Knowing all collaborators in the system          | EmployeeRepository                    | Pure Fabrication, IE.                               |
| Step 3         | interacting with the provided collaborator id    | RegisterSkillToCollaboratorController | Controller, mediates input from user to the system. |
| Step 4         | showing available skills to the HRM              | RegisterSkillToCollaboratorUI         | UI, interacts with user.                            |
| Step 5         | registering the skill                            | Colaborator                           | IE, has its own skills.                             |
| Step 6         | Show HRM the collaborator and registered skills | registerSkillUI                       | UI, interacts with user                             |
|                |                                                  |                                       |                                                     |

### Systematization

According to the taken rationale, the conceptual classes promoted to software classes are:

* Collaborator [Employee]
* Skill

Other software classes (i.e. Pure Fabrication) identified:

* RegisterSkillToCollaboratorUI
* RegisterSkillToCollaboratorController
* SkillRepository
* EmployeeRepository

## 3.2. Sequence Diagram (SD)

_In this section, it is suggested to present an UML dynamic view representing the sequence of interactions between software objects that allows to fulfill the requirements._

![US04-SD](docs/sprintA/us004/03.design/svg/us004-sequence-diagram.svg)

## 3.3. Class Diagram (CD)

_In this section, it is suggested to present an UML static view representing the main related software classes that are involved in fulfilling the requirements as well as their relations, attributes and methods._

![US004-CD](us004-sequence-diagram.svg)
