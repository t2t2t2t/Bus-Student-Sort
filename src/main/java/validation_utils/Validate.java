package validation_utils;

@FunctionalInterface
public
interface Validate<T> {
    boolean isValid(T obj);

}
