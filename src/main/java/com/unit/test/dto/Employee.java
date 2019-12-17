package com.unit.test.dto;

/**
 * Employee DTO
 */
public class Employee extends AbstractBaseDTO<Employee>{

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

    /**
     * This is not good implementation of equals method.
     * Quick hack is done just to demo how DTO testing
     * is performed.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Employee) {
            Employee e = (Employee) obj;

            if (e.getFirstName() != null) {
                return this.firstName.equalsIgnoreCase(e.getFirstName()) ? true : false;
            } else {
                return true;
            }
        }

        return false;
    }

    /**
     * Populate Method
     */
    @Override
    public Employee populate(Employee e){
        this.setFirstName(e.getFirstName());
        this.setLastName(e.getLastName());
        this.setAge(e.getAge());

        return this;
    }
}
