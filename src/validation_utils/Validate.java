package ValidationUtils;

@FunctionalInterface
interface Validate<T> {
    boolean isValid(T obj);

}
