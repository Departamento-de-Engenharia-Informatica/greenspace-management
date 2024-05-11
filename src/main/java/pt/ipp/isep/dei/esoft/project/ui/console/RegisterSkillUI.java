package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;
import java.util.Scanner;

public class RegisterSkillUI implements Runnable{
    private final Scanner scanner;
    private final SkillRepository skillRepository;

    public RegisterSkillUI(SkillRepository skillRepository) {
        this.scanner = new Scanner(System.in);
        this.skillRepository = skillRepository;
    }

    public void run() {
        while (true) {
            System.out.println("\n--- Skill Management System ---");
            displaySkills();

            System.out.println("\nChoose an option:");
            System.out.println("1. Add Skill");
            System.out.println("2. Edit Skill");
            System.out.println("3. Remove Skill");
            System.out.println("4. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addSkill();
                    break;
                case 2:
                    editSkill();
                    break;
                case 3:
                    removeSkill();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displaySkills() {
        List<Skill> skills = skillRepository.getAllSkills();
        System.out.println("\n--- Skills List ---");
        for (int i = 0; i < skills.size(); i++) {
            System.out.println((i + 1) + ". " + skills.get(i).getSkillName());
        }
    }

    private void addSkill() {
        System.out.print("\nEnter skill name: ");
        String skillName = scanner.nextLine();
        skillRepository.addSkill(skillName);
        System.out.println("Skill added successfully.");
    }

    private void editSkill() {
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

    private void removeSkill() {
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

    public static void main(String[] args) {
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        RegisterSkillUI registerSkillUI = new RegisterSkillUI(skillRepository);
        registerSkillUI.run();
    }
}
