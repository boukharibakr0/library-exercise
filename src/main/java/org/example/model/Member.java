package org.example.model;

import java.time.LocalDate;

public class Member {

    private String id;
    private String name;
    private String email;
    private LocalDate membershipDate;

    public Member(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.membershipDate = LocalDate.now();
    }

    public String getId()                   { return id; }
    public String getName()                 { return name; }
    public String getEmail()                { return email; }
    public LocalDate getMembershipDate()    { return membershipDate; }

    public void setEmail(String email)      { this.email = email; }

    @Override
    public String toString() {
        return String.format("Member{id='%s', name='%s', email='%s'}", id, name, email);
    }
}
