package me.fistpump.partialstub;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.ExceptionMethod;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Constructor;

/**
 * PartialStub is a utility for instantiating partially implemented interfaces in the form of abstract classes. Any
 * methods not implemented throw an {@link AbstractMethodError}
 */
public final class PartialStub {
    private PartialStub() {}

    /**
     * Creates an instance of a partially implemented class. Any methods not implemented throw an
     * {@link AbstractMethodError}
     *
     * @param partiallyImplementedAbstractClass the partially implemented class to instantiate
     * @param <T> the partially implemented class type
     * @return an instance of the partially implemented class
     */
    public static <T> T create(Class<? extends T> partiallyImplementedAbstractClass) {
        Class<? extends T> loaded = new ByteBuddy()
                .subclass(partiallyImplementedAbstractClass)
                .method(ElementMatchers.isAbstract())
                .intercept(ExceptionMethod.throwing(AbstractMethodError.class, "Method is not implemented on abstract class"))
                .make()
                .load(PartialStub.class.getClassLoader())
                .getLoaded();
        Constructor<? extends T> constructor = getConstructor(loaded);
        try {
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error instantiating " + partiallyImplementedAbstractClass.getName(), e);
        }
    }

    private static <T> Constructor<? extends T> getConstructor(Class<? extends T> loaded) {
        try {
            return loaded.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(loaded + " needs a default no-arg constructor");
        }
    }
}
