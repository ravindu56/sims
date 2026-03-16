package com.ec9590.sims.model;

import java.time.LocalDate;

public class Student {

    private int id;
    private String regNo;
    private String name;
    private String field;
    private LocalDate dob;
    private String contact;
    private String email;

    public Student() {}

    public Student(String regNo, String name, String field,
                   LocalDate dob, String contact, String email) {
        this.regNo   = regNo;
        this.name    = name;
        this.field   = field;
        this.dob     = dob;
        this.contact = contact;
        this.email   = email;
    }

    public int getId()                  { return id; }
    public void setId(int id)           { this.id = id; }

    public String getRegNo()            { return regNo; }
    public void setRegNo(String regNo)  { this.regNo = regNo; }

    public String getName()             { return name; }
    public void setName(String name)    { this.name = name; }

    public String getField()            { return field; }
    public void setField(String field)  { this.field = field; }

    public LocalDate getDob()               { return dob; }
    public void setDob(LocalDate dob)       { this.dob = dob; }

    public String getContact()              { return contact; }
    public void setContact(String contact)  { this.contact = contact; }

    public String getEmail()                { return email; }
    public void setEmail(String email)      { this.email = email; }

    @Override
    public String toString() {
        return "Student{regNo='" + regNo + "', name='" + name +
               "', field='" + field + "', dob=" + dob + "}";
    }
}
