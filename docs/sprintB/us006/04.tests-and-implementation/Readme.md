# US006 - Register a Vehicle 

## 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the Vehicle class with null values. 

	@Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        Vehicle instance = new Vehicle(null, null, null, 0, 0, 0, null, null, 0, null, null);
    }
	

**Test 2:** The maintenance/check-up frequency field should only accept positive integer values representing kilometers. - AC3. 

	@Test(expected = IllegalArgumentException.class)
    public void ensureMaintenanceCheckupFrequencyIsPositive() {
        Vehicle instance = new Vehicle("Plate123", "ModelX", "TypeY", 2000, 1500, 10000, "2022-01-01", "2021-12-01", -100, "2022-03-15", "2022-05-15");
    }


## 5. Construction (Implementation)

### Class CreateTaskController 

```java
private Vehicle createVehicle(String plateID, String model, String type, int tare, int grossWeight,
        int currentKm, String registerDate, String acquisitionDate,
        int checkupFrequency, String lastMaintenance, String nextMaintenance) {

        Vehicle newVehicle = new Vehicle(plateID, model, type, tare, grossWeight, currentKm,
        registerDate, acquisitionDate, checkupFrequency, lastMaintenance, nextMaintenance);


        return newVehicle;
        }
```

### Class Organization

```java
public Optional<Vehicle> createVehicle(String plateID, String model, String type, int tare, int grossWeight,
        int currentKm, String registerDate, String acquisitionDate,
        int checkupFrequency, String lastMaintenance, String nextMaintenance) {

        Vehicle vehicle = new Vehicle(plateID, model, type, tare, grossWeight, currentKm,
        registerDate, acquisitionDate, checkupFrequency, lastMaintenance, nextMaintenance);

        return Optional.of(vehicle);
        }
```


## 6. Integration and Demo 

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a