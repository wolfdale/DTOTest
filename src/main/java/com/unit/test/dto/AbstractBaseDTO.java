package com.unit.test.dto;

public abstract class AbstractBaseDTO<T extends AbstractBaseDTO> {
    /**
     * This is a custom method in Mother of all DTO classes.
     * @param source
     * @return
     */
    public abstract T populate(T source);
}
