package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.error.lack.AccessibleLackError;
import io.github.nichetoolkit.rest.error.lack.FieldLackError;
import io.github.nichetoolkit.rest.error.lack.InstanceLackError;
import sun.reflect.ConstructorAccessor;
import sun.reflect.FieldAccessor;
import sun.reflect.ReflectionFactory;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * <p>DefaultEnum</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
class DefaultEnum {

    private static final ReflectionFactory REFLECTION_FACTORY = ReflectionFactory.getReflectionFactory();

    private static final String MODIFIERS = "modifiers";
    private static final String CONSTANT_DIRECTORY = "enumConstantDirectory";
    private static final String CONSTANTS = "enumConstants";
    private static final String VALUES = "$VALUES";

    private static void access(Field field, Object target, Object value) {
        /* make the field accessible */
        field.setAccessible(true);
        try {
            Field modifiersField = Field.class.getDeclaredField(MODIFIERS);
            modifiersField.setAccessible(true);
            int modifiers = modifiersField.getInt(field);
            /* blank out the final bit in the modifiers int */
            modifiers &= ~Modifier.FINAL;
            modifiersField.setInt(field, modifiers);
            FieldAccessor fieldAccessor = REFLECTION_FACTORY.newFieldAccessor(field, false);
            fieldAccessor.set(target, value);
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            throw new AccessibleLackError(exception);
        }
    }

    private static void reset(Class<?> clazz, String... fieldNames) {
        Field[] declaredFields = Class.class.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(field -> {
            String name = field.getName();
            Arrays.stream(fieldNames).forEach(fieldName -> {
                if (name.contains(fieldName)) {
                    AccessibleObject.setAccessible(new Field[]{field}, true);
                    access(field, clazz, null);
                }
            });
        });
    }

    private static ConstructorAccessor construct(Class<?> clazz, Class<?>[] additionalParameterTypes) {
        Class<?>[] parameterTypes = new Class[additionalParameterTypes.length + 2];
        parameterTypes[0] = String.class;
        parameterTypes[1] = int.class;
        System.arraycopy(additionalParameterTypes, 0, parameterTypes, 2, additionalParameterTypes.length);
        Constructor<?> declaredConstructor;
        try {
            declaredConstructor = clazz.getDeclaredConstructor(parameterTypes);
        } catch (NoSuchMethodException exception) {
            throw new AccessibleLackError(exception);
        }
        return REFLECTION_FACTORY.newConstructorAccessor(declaredConstructor);
    }

    private static Object instance(Class<?> clazz, String value, int ordinal, Class<?>[] types, Object[] values) {
        Object[] params = new Object[values.length + 2];
        params[0] = value;
        params[1] = ordinal;
        System.arraycopy(values, 0, params, 2, values.length);
        ConstructorAccessor constructor = construct(clazz, types);
        Object instance;
        try {
            instance = constructor.newInstance(params);
        } catch (InstantiationException | InvocationTargetException exception) {
            throw new InstanceLackError(exception);
        }
        return clazz.cast(instance);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Enum<?>> T value(Class<T> clazz, String name, Class<?>[] paramClasses, Object[] paramValues) {
        Field[] declaredFields = clazz.getDeclaredFields();
        Optional<Field> optionalField = Arrays.stream(declaredFields)
                .filter(field -> field.getName().contains(VALUES)).findFirst();
        optionalField.orElseThrow(FieldLackError::new);
        Field valuesField = optionalField.get();
        AccessibleObject.setAccessible(new Field[]{valuesField}, true);
        try {
            T[] copyValues = (T[]) valuesField.get(clazz);
            List<T> values = new ArrayList<>(Arrays.asList(copyValues));
            T newValue = (T) instance(clazz, name, values.size(), paramClasses, paramValues);
            values.add(newValue);
            Object object = values.toArray((T[]) Array.newInstance(clazz, 0));
            access(valuesField, null, object);
            reset(clazz, CONSTANT_DIRECTORY, CONSTANTS);
            return newValue;
        } catch (IllegalAccessException exception) {
            throw new AccessibleLackError(exception);
        }
    }
}
