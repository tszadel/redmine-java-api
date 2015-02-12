package com.taskadapter.redmineapi.internal;

public class Optional<T> {
    private T instance;
    private boolean valuePresent;

    public void set(T value) {
        instance = value;
        valuePresent = true;
    }

    public T get() {
        if (!valuePresent) {
            throw new IllegalStateException("Object value is not set.");
        }
        return instance;
    }

    public T orNull() {
        return instance;
    }

    public boolean isPresent() {
        return valuePresent;
    }

    public boolean isPresentAndNotNull() {
        return valuePresent && instance != null;
    }

    public void remove() {
        instance = null;
        valuePresent = false;
    }
}
