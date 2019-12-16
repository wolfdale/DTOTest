package com.unit.test.dtotest;

import com.unit.test.dto.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeDTOTest extends AbstractDTOTest<Employee> {

    protected Map<String, Object> values;

    public EmployeeDTOTest(){
        super(Employee.class);

        values  = new HashMap<>();
        this.values.put("FirstName", "Foo");
        this.values.put("LastName", "Bar");
        this.values.put("Age", new Integer("55"));

    }

    @Override
    protected Object getInputValueForMutator(String field) {
        return this.values.get(field);
    }

    @Override
    protected Object getOutputValueForAccessor(String field, Object input) {
        return input;
    }
}
