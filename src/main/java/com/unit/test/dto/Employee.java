package com.unit.test.dto;

/**
 * Employee DTO
 */
public class Employee {

    private String firstName;
    private String lastName;
    private long age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Employee) {
            Employee e = (Employee) obj;

            if(e.getFirstName() != null) {
                return this.firstName.equalsIgnoreCase(e.getFirstName()) ? true : false;
            }
            else {
                return true;
            }
        }

        return false;
    }
}
