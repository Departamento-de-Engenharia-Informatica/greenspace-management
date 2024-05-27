# US027 - List all green spaces managed by me


## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I need to list all green spaces managed by me.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	The list of green spaces must be sorted by size in descending order. The sorting algorithm to be used by the application
must be defined through a configuration file. At least two sorting
algorithms should be available.

**From the client clarifications:**

> **Question:**
> "Bom dia, Para a US02, gostaria de esclarecer o seguinte:
É relevante associar uma área ou setor específico a cada Job? (Por exemplo, "Jardineiro" seria inserido no setor de "Manutenção")
Deve-se incluir informações como salário, tipo de contratação (full-time ou part-time), e modalidade de trabalho (presencial, remoto ou híbrido) no Job? Ou essas informações encaixam-se melhor no âmbito do colaborador, ou talvez nem sejam necessárias?
Que outras informações acha necessárias associar ao Job?
Agradeço a atenção dispensada."


> **Answer:** 
"Bom dia,
 não é necessário na medida que não existem US que sugiram que isso possa vir a ser necessário;
 idem;
 para já nenhumas;
> De nada."

> **Question:** 
 "Quais são os dados de entrada para a criação de uma profissão?"

> **Answer:**
"o Nome da profissão:
jardineiro
calceteiro
electricista
condutor
...
"

### 1.3. Acceptance Criteria

* **AC1:** The list of green spaces must be sorted by size in descending order.
* **AC2:** The sorting algorithm to be used by the application must be defined through a configuration file.
* **AC3:** At least two sorting algorithms should be available.
* **AC4:** The system should determine which green spaces are assigned to the logged GSM and list them. 

### 1.4. Found out Dependencies

There is a dependency in US20 as if there isn't Green Spaces a list can't be made.

### 1.5 Input and Output Data

**Output Data:**
List of the managed Green Spaces and their data.

### 1.6. System Sequence Diagram (SSD)


![System Sequence Diagram - Alternative One](svg/us027-system-sequence-diagram-System_Sequence_Diagram__SSD_.svg)



