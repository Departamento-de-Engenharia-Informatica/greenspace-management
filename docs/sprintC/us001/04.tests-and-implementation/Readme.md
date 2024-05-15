# US001 - Register a SKill

## 4. Tests 

**Test 1:** Check that you can't add a skill with a null name.

    @Test
    public void testAddSkillWithNameNull() {
        // Test AC2: Each skill entry must include a name
        // Attempt to add a skill with null name
        skillRepository.addSkill(null);

        // Ensure no skill is added with null name
        assertEquals(0, skillRepository.getAllSkills().size());
    }
	

**Test 2:** Check that it is not possible to add a skill with a name containing special characters. 

    @Test
    public void testAddSkillWithSpecialCharacters() {
        // Test AC3: Special characters or algarisms should not be allowed in the skill name
        // Attempt to add a skill with special characters in the name
        skillRepository.addSkill("Java!");

        // Ensure no skill is added with special characters in the name
        assertEquals(0, skillRepository.getAllSkills().size());
    }

**Test 3:** Check if you can add a skill.
        

        @Test
        public void testAddSkillWithNameNull() {
        // Test AC2: Each skill entry must include a name
        // Attempt to add a skill with null name
        skillRepository.addSkill(null);
        // Ensure no skill is added with null name
        assertEquals(0, skillRepository.getAllSkills().size());
        }


**Test 4:** Check if you can edit a skill.

    @Test
    public void testUpdateSkill() {
        // Add a skill to the repository
        skillRepository.addSkill("Java");

        // Update the skill's name
        skillRepository.updateSkill(1, "JavaScript"); // Updating the skill at index 1 (Java) to JavaScript

        // Ensure the skill is updated successfully
        assertEquals("JavaScript", skillRepository.getAllSkills().get(0).getSkillName());
    }

**Test 5:** Check if you can remove a skill.

        @Test
        public void testRemoveSkill() {
        // Add some skills to the repository
        skillRepository.addSkill("Java");
        skillRepository.addSkill("Python");
        // Get the list of skills before removal
        List<Skill> beforeRemoval = skillRepository.getAllSkills();
        // Remove a skill from the repository
        skillRepository.removeSkill(2); // Removing the skill at index 2 (Python)

        // Get the list of skills after removal
        List<Skill> afterRemoval = skillRepository.getAllSkills();

        // Ensure the size of the list decreases by 1 after removal
        assertEquals(beforeRemoval.size() - 1, afterRemoval.size());

        // Ensure the removed skill is no longer present in the list
        assertFalse(afterRemoval.contains(new Skill("Python")));
        }

## 5. Construction (Implementation)


### Class RegisterSkillController

```java
public void addSkill(String skillName) {
    SkillRepository skillRepository = repositories.getSkillRepository();
    skillRepository.addSkill(skillName);
}

public void updateSkill(int skillId, String newSkillName) {
    SkillRepository skillRepository = repositories.getSkillRepository();
    skillRepository.updateSkill(skillId, newSkillName);
}

public void removeSkill(int skillId) {
    SkillRepository skillRepository = repositories.getSkillRepository();
    skillRepository.removeSkill(skillId);
}

```

### Class SkillRepository

```java
public void addSkill(String skillName) {
    if (skillName == null || !skillName.matches("[a-zA-Z ]+")) {
        System.out.println("Special characters or digits are not allowed in the skill name.");
    } else {
        Skill skill = new Skill(skillName);
        skills.add(skill);
    }
}

public void removeSkill(int skillId) {
    Skill skillToRemove = findSkillById(skillId);
    if (skillToRemove != null) {
        skills.remove(skillToRemove);
    }
}

public void updateSkill(int skillId, String newSkillName) {
    Skill skillToUpdate = findSkillById(skillId);
    if (skillToUpdate != null) {
        skillToUpdate.setSkillName(newSkillName);
    }
}

```
### Class Skill
```java
public class Skill {
    private static int nextId = 1;
    private int id;
    private String skillName;

    public Skill(String skillName) {
        this.id = nextId++;
        this.skillName = skillName;
    }

    public int getId() {
        return id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}

```

### Class RegisterSkillUI
```java
public void addSkill() {
    System.out.print("\nEnter skill name: ");
    String skillName = scanner.nextLine();
    skillRepository.addSkill(skillName);
    System.out.println("Skill added successfully.");
}

public void editSkill() {
    System.out.print("\nEnter skill number to edit: ");
    int skillNumber = Integer.parseInt(scanner.nextLine());

    List<Skill> skills = skillRepository.getAllSkills();

    if (skillNumber >= 1 && skillNumber <= skills.size()) {
        Skill selectedSkill = skills.get(skillNumber - 1);
        System.out.println("\nSelected Skill: " + selectedSkill.getSkillName());
        System.out.print("Enter new skill name: ");
        String newSkillName = scanner.nextLine();
        skillRepository.updateSkill(selectedSkill.getId(), newSkillName);
        System.out.println("Skill updated successfully.");
    } else {
        System.out.println("Invalid skill number. Please try again.");
    }
}

public void removeSkill() {
    System.out.print("\nEnter skill number to remove: ");
    int skillNumber = Integer.parseInt(scanner.nextLine());

    List<Skill> skills = skillRepository.getAllSkills();

    if (skillNumber >= 1 && skillNumber <= skills.size()) {
        Skill selectedSkill = skills.get(skillNumber - 1);
        skillRepository.removeSkill(selectedSkill.getId());
        System.out.println("Skill removed successfully.");
    } else {
        System.out.println("Invalid skill number. Please try again.");
    }
}

```


## 6. Integration and Demo 

* A new option on the HRM menu options was added.

* For demo purposes some skills are bootstrapped while system starts.


## 7. Observations

n/a