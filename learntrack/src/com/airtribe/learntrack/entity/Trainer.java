package com.airtribe.learntrack.entity;
//The class Trainer created to demonstrate we gained--->
//1.No duplication 2.super() constructor chaining 3.Polymorphism via method overriding through Inheritance
public class Trainer extends Person {

    private String specialization;

    public Trainer() {
        super();
    }

    public Trainer(int id, String firstName, String lastName, String email, String specialization) {
        super(id, firstName, lastName, email);
        this.specialization = specialization;
    }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    @Override
    public String getDisplayName() {
        return "[Trainer] " + getFirstName() + " " + getLastName();
    }

    @Override
    public String toString() {
        return super.toString() + " | Specialization: " + specialization;
    }
}
