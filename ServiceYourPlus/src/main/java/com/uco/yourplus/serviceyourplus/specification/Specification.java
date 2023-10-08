package com.uco.yourplus.serviceyourplus.specification;

public interface Specification<T> {
    void isSatisfied(T data);
}
