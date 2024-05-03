package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Collaborator {
    private String name;
    private String birthdayDate;
    private String admissionDate;
    private String address;
    private String phoneNumber;
    private String email;
    private String taxpayerNumber;
    private String BINumber;
    private String job;

    public Collaborator(String name, String birthdayDate, String admissionDate,
                        String address, String phoneNumber, String email,
                        String taxpayerNumber, String BINumber, String job) {
        validateInputs(name, birthdayDate, admissionDate, address, phoneNumber, email, taxpayerNumber, BINumber,job);
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

    private void validateInputs(String name, String birthdayDate, String admissionDate,
                                String address, String phoneNumber, String email,
                                String taxpayerNumber, String BINumber, String job) {
        // Validate inputs here, for example, ensure none are null or empty
        // Add more validation rules as needed
        if  (
                name == null || name.isEmpty() ||
                birthdayDate == null || birthdayDate.isEmpty() ||
                admissionDate == null || admissionDate.isEmpty() ||
                address == null || address.isEmpty() ||
                phoneNumber == null || phoneNumber.length() != 9 || !phoneNumber.matches("\\d+") ||
                email == null ||  !email.contains("@") || // Check if email contains '@'
                taxpayerNumber == null ||  taxpayerNumber.length() != 9 || !taxpayerNumber.matches("\\d+") ||
                BINumber == null ||  BINumber.length() != 8 || !BINumber.matches("\\d+") ||
                job == null || job.isEmpty()
            )
        {
            throw new IllegalArgumentException("All fields must be provided.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collaborator that = (Collaborator) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(birthdayDate, that.birthdayDate) &&
                Objects.equals(admissionDate, that.admissionDate) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(email, that.email) &&
                Objects.equals(taxpayerNumber, that.taxpayerNumber) &&
                Objects.equals(BINumber, that.BINumber) &&
                Objects.equals(job, that.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthdayDate, admissionDate, address, phoneNumber, email, taxpayerNumber, BINumber,job);
    }

    public Collaborator clone() {
        return new Collaborator(name, birthdayDate, admissionDate, address, phoneNumber, email, taxpayerNumber, BINumber,job);
    }

    public String getName() {
        return name;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public String getAdmissionDate() {
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

    public String getTaxpayerNumber() {
        return taxpayerNumber;
    }

    public String getBINumber() {
        return BINumber;
    }

    public String getJob() {
        return job;
    }
}
