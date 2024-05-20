# US022 - Add a new entry in the agenda


## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I want to add a new entry in the Agenda.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	• Green Spaces Manager (GSM) - the person responsible for managing
the green spaces in charge of the organization
> 
> • The Agenda is a crucial mechanism for planning the week’s work. Each entry
in the Agenda defines a task (that was previously included in the to-do list).
A team will carry out that task in a green space at a certain time interval
on a specific date. Comparatively analyzing the Agenda entries and the
pending tasks (to-do list) allows you to evaluate the work still to be done,
the busyness of the week, and the work performed by a team in a green space
at a determined time interval and on a specific date.
> 
> The Agenda is made
up of entries that relate to a task (which was previously in the To-Do List),
the team that will carry out the task, the vehicles/equipment assigned to
the task, expected duration, and the status (Planned, Postponed, Canceled,
Done).


**From the client clarifications:**

> **Question:**
> When a to-do list entry is added to the agenda, what should its state be?


> **Answer:**
Maybe "planned".


> **Question:**
Is it mandatory for the program to have multiple GSM? If so, should each green space be associated with a responsible GSM? In other words, can only the GSM assigned to a specific green space register a new entry for the to-do list?



> **Answer:**
The program can have multiple GSM.
Yes.
Yes.
>

> **Question:**
Regarding the US022, when we are registering an entry to the agenda, are the three inputs (selecting an existing task from the to-do list, starting date, and finishing date) sufficient?
> 

> **Answer:** The starting date will be enough because the task already has the predicted duration.
>

> **Question:**
We also know that an Agenda entry has a target date, but is this target date supposed to be inputted upon transferring a task from the to-do list to the agenda, or is it supposed to be inputted upon creating the task in the to-do list?

> **Answer:**
To-do list entries doesn't have dates!

> **Question:** Can I add an entry that has a time period that already have an existing entry in the Agenda?

> **Answer:** Yes, because:
a) there are many parks to manage
b) different tasks can be executed at same time in the same park.

> **Question:**
When the GSM plans a task (that was previously in To-Do) into the Agenda, what aditional data/information does he need to input when planning?

> **Answer:**
The starting date for the task.
Later the GSM will be able to add the Team and vehicles (if required).


> **Question:**
We also know that an Agenda entry has a target date, but is this target date supposed to be inputted upon transferring a task from the to-do list to the agenda, or is it supposed to be inputted upon creating the task in the to-do list?

> **Answer:**
To-do list entries doesn't have dates!

### 1.3. Acceptance Criteria

 **AC1:** All required fields must be filled in.

 **AC2:** As a GSM i should be able to add a new entry in the agenda.

 **AC3:** The new entry must be associated with a green space managed by the GSM.

 **AC4:** The new entry must exist in the To-Do list.

### 1.4. Found out Dependencies

• US20 - To choose the greenspace.

• US21 - The entry for the agenda needs to be in the To-Do List.

### 1.5 Input and Output Data

**Input Data:**
     --

**Output Data:**
--

### 1.6. System Sequence Diagram (SSD)


![System Sequence Diagram - Alternative One](svg/us002-system-sequence-diagram.svg)



