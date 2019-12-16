package com.unit.test.dtotest;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.Assert.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractDTOTest<T> {

    public static final Pattern ACCESSOR_NAME_REGEX = Pattern.compile("(?:get|is)([a-zA-Z0-9]+)");

    protected final Class<T> dtoClass;
    protected final Map<String, Method[]> fields;
    protected final Constructor<T> copyConstructor;

    public AbstractDTOTest(Class<T> dtoClass) {
        if (dtoClass == null) {
            // No DTO class provided
        }

        this.dtoClass = dtoClass;
        this.fields = new HashMap<>();

        //Scan for accessor/Mutator in dto class.
        for (Method mth : this.dtoClass.getMethods()) {
            try {
                Matcher matcher = ACCESSOR_NAME_REGEX.matcher(mth.getName());

                if (matcher.matches() && mth.getParameterTypes().length == 0) {
                    String fieldName = matcher.group(1);
                    Method mutator = this.dtoClass.getMethod("set" + fieldName, mth.getReturnType());
                    fields.put(fieldName, new Method[]{mth, mutator});
                }

            } catch (NoSuchMethodException exp) {
                //Do nothing

            }
        }

        // Check if we have copy constructor for this dto
        Constructor constructor = null;
        try {
            constructor = this.dtoClass.getConstructor(this.dtoClass);
        } catch (NoSuchMethodException e) {
            // Do Nothing
        }

        this.copyConstructor = constructor;
    }

    protected abstract Object getInputValueForMutator(String field);

    protected abstract Object getOutputValueForAccessor(String field, Object input);

    /**
     * This will act as a source method to provide all fields in
     * DTO.
     */
    public Stream<Object> getFieldNames(){
        return Stream.of(this.fields.keySet().toArray());
    }

    /**
     * Use to generate a brand new object for a class via java reflection
     */
    public T getInstance() {
        try {
            return this.dtoClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            // Runtime exception
            throw new RuntimeException("Unable to init object");
        }
    }

    /**
     * Method to test object equality
     */
    @ParameterizedTest
    @MethodSource("getFieldNames")
    public void testForEquality(String field) throws Exception {
        Method [] method = this.fields.get(field);

        T ob1 = this.getInstance();
        T ob2 = this.getInstance();

        assertTrue(ob1.equals(ob2));

        Object input1 = this.getInputValueForMutator(field);
        method[1].invoke(ob1, input1);

        Object input2 = this.getInputValueForMutator(field);
        method[1].invoke(ob1, input2);

        assertTrue(ob1.equals(ob2));

    }


}
