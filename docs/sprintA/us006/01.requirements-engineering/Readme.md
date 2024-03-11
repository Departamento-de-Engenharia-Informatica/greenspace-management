# US006 - Create a Task 


## 1. Requirements Engineering

### 1.1. User Story Description

As a Fleet Manager (FM), I wish to register a vehicle including its details and maintenance schedule for efficient fleet management.
### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> Each vehicle entry should include information such as Brand, Model, Type, Tare, Gross Weight, Current Km, Register Date, Acquisition Date, and Maintenance/Checkup Frequency (in Kms).

> Maintenance/check-up data should be recorded for each vehicle. 

> The frequency of maintenance/check-up should be specified in kilometers.

**From the client clarifications:**
??????????????????

> **Question:** Which is the unit of measurement used to estimate duration?
>
> **Answer:** Duration is estimated in days.

> **Question:** Monetary data is expressed in any particular currency?
>
> **Answer:** Monetary data (e.g. estimated cost of a task) is indicated in POT (virtual currency internal to the platform).

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in for vehicle registration.
* **AC2:** The vehicle registration form should allow the user to input essential details such as Brand, Model, Type, etc.
* **AC3:** The maintenance/check-up frequency field should only accept positive integer values representing kilometers.
* **AC4:** Upon successful registration, the system should confirm the addition of the vehicle.
* **AC5:** The maintenance/check-up schedule should be updated automatically based on the current mileage of the vehicle.

### 1.4. Found out Dependencies

* NO DEPENDENCIES YET

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * Reference/ID
    * Model 
    * Type
    * Tare
    * Gross Weight
    * Current Km
    * Register Date
    * Acquisition Date
    * Maintenance/Checkup Frequency (in Kms)
    * Last maintenance date
    * Next maintenance date
  

* Selected data:
    * a task category 

**Output Data:**

* List of existing task categories.
* Confirmation message upon successful registration.
* List of vehicles with their details.


### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us006-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.