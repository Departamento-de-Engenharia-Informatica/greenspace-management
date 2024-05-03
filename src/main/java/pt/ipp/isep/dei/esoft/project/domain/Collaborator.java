package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Collaborator {
    private String name;
    private LocalDate birthdayDate;
    private LocalDate admissionDate;
    private String address;
    private String phoneNumber;
    private String email;
    private int taxpayerNumber;
    private long BINumber;
    private String job;

    public Collaborator(String name, LocalDate birthdayDate, LocalDate admissionDate,
                        String address, String phoneNumber, String email,
                        int taxpayerNumber, long BINumber, String job) throws IllegalArgumentException {
        validateInputs(name, birthdayDate, admissionDate, address, phoneNumber, email, taxpayerNumber, BINumber, job);
        this.name = name;
        this.birthdayDate = birthdayDate;
        this.admissionDate = admissionDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.taxpayerNumber = taxpayerNumber;
        this.BINumber = BINumber;
        this.job = job;
    }

    private void validateInputs(String name, LocalDate birthdayDate, LocalDate admissionDate,
                                String address, String phoneNumber, String email,
                                int taxpayerNumber, long BINumber, String job) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Name must be provided.");
            return; // Exit method, allowing the user to correct the input
        }

        if (birthdayDate == null) {
            System.out.println("Birthday date must be provided.");
            return;
        }

        if (admissionDate == null) {
            System.out.println("Admission date must be provided.");
            return;
        }

        if (address == null || address.trim().isEmpty()) {
            System.out.println("Address must be provided.");
            return;
        }

        if (phoneNumber == null || !phoneNumber.matches("\\d{9}")) {
            System.out.println("Phone number must be 9 digits.");
            return;
        }

        if (email == null || !email.contains("@")) {
            System.out.println("Email must be provided and contain '@'.");
            return;
        }

        if (taxpayerNumber <= 0 || String.valueOf(taxpayerNumber).length() != 9) {
            System.out.println("Taxpayer number must be a positive integer of 9 digits.");
            return;
        }

        if (String.valueOf(BINumber).length() != 8) {
            System.out.println("BI number must be an integer of 8 digits.");
            return;
        }

        if (job == null || job.trim().isEmpty()) {
            System.out.println("Job must be provided.");
            return;
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collaborator that = (Collaborator) o;
        return taxpayerNumber == that.taxpayerNumber &&
                BINumber == that.BINumber &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthdayDate, that.birthdayDate) &&
                Objects.equals(admissionDate, that.admissionDate) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(email, that.email) &&
                Objects.equals(job, that.job);
    }

    @Override


    public Collaborator clone() {
        return new Collaborator(name, birthdayDate, admissionDate, address, phoneNumber, email, taxpayerNumber, BINumber,job);
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdayDate() {
        return birthdayDate;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getTaxpayerNumber() {
        return taxpayerNumber;
    }

    public long getBINumber() {
        return BINumber;
    }

    public String getJob() {
        return job;
    }

}
