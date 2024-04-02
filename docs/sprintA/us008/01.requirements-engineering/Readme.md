# US008 - List the vehicles needing the check up 


## 1. Requirements Engineering

### 1.1. User Story Description

As a Fleet Manager, I want to view a list of vehicles that are due for a check-up so that I can efficiently schedule and prioritize maintenance tasks.

### 1.2. Customer Specifications and Clarifications 

**From the client clarifications:**

> **Question:** What information will appear on the final list regarding the vehicle,besides the needing for check-up?
>
> **Answer:** Data that allow to identify the vehicle like Plate, brand and modle, as well as, the data that allowed to select/insert te vehicle in the list, number of kms, frequecny of checkup and the last checkup.

> **Question:** Are there acceptance criteria when asking for the list?
>
> **Answer:** The list must contain all vehicles that have already exceeded the number of km required for the inspection or those that are close to it. 
>
>For example: a vehicle that made the checkup at 23500 and has a checkup frequency of 10000km.
> 
> **a)** If it currently has 33600 (exceeded) or
> 
> **b)** 33480 (there is a difference minor than 5% of the number of kms of the checkup frequency).



### 1.3. Acceptance Criteria

* **AC1:** The system should accurately determine which vehicles are due for a check-up based on their maintenance/check-up frequency and current mileage.
* **AC2:** The generated list should include relevant details such as plate, brand, model, current KM, maintenance/check-up frequency, and last check-up date.

### 1.4. Found out Dependencies

* There is a dependency on "US006 - Register a Vehicle" as a vehicle must exist before being listed for a check up.
### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * None required (system retrieves data internally).

**Output Data:**

* List of vehicles needing check-up.

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - Alternative One](svg/us008-system-sequence-diagram.svg)

