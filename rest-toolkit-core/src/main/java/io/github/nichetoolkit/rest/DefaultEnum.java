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
 * <code>DefaultEnum</code>
 * <p>The type default enum class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
class DefaultEnum {

    /**
     * <code>REFLECTION_FACTORY</code>
     * {@link sun.reflect.ReflectionFactory} <p>the constant <code>REFLECTION_FACTORY</code> field.</p>
     * @see sun.reflect.ReflectionFactory
     */
    private static final ReflectionFactory REFLECTION_FACTORY = ReflectionFactory.getReflectionFactory();

    /**
     * <code>MODIFIERS</code>
     * {@link java.lang.String} <p>the constant <code>MODIFIERS</code> field.</p>
     * @see java.lang.String
     */
    private static final String MODIFIERS = "modifiers";
    /**
     * <code>CONSTANT_DIRECTORY</code>
     * {@link java.lang.String} <p>the constant <code>CONSTANT_DIRECTORY</code> field.</p>
     * @see java.lang.String
     */
    private static final String CONSTANT_DIRECTORY = "enumConstantDirectory";
    /**
     * <code>CONSTANTS</code>
     * {@link java.lang.String} <p>the constant <code>CONSTANTS</code> field.</p>
     * @see java.lang.String
     */
    private static final String CONSTANTS = "enumConstants";
    /**
     * <code>VALUES</code>
     * {@link java.lang.String} <p>the constant <code>VALUES</code> field.</p>
     * @see java.lang.String
     */
    private static final String VALUES = "$VALUES";

    /**
     * <code>access</code>
     * <p>the method.</p>
     * @param field  {@link java.lang.reflect.Field} <p>the field parameter is <code>Field</code> type.</p>
     * @param target {@link java.lang.Object} <p>the target parameter is <code>Object</code> type.</p>
     * @param value  {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @see java.lang.reflect.Field
     * @see java.lang.Object
     */
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

    /**
     * <code>reset</code>
     * <p>the method.</p>
     * @param clazz      {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param fieldNames {@link java.lang.String} <p>the field names parameter is <code>String</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.String
     */
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

    /**
     * <code>construct</code>
     * <p>the method.</p>
     * @param clazz                    {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param additionalParameterTypes {@link java.lang.Class} <p>the additional parameter types parameter is <code>Class</code> type.</p>
     * @return {@link sun.reflect.ConstructorAccessor} <p>the return object is <code>ConstructorAccessor</code> type.</p>
     * @see java.lang.Class
     * @see sun.reflect.ConstructorAccessor
     */
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

    /**
     * <code>instance</code>
     * <p>the method.</p>
     * @param clazz   {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param value   {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @param ordinal int <p>the ordinal parameter is <code>int</code> type.</p>
     * @param types   {@link java.lang.Class} <p>the types parameter is <code>Class</code> type.</p>
     * @param values  {@link java.lang.Object} <p>the values parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Object} <p>the return object is <code>Object</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.String
     * @see java.lang.Object
     */
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

    /**
     * <code>value</code>
     * <p>the method.</p>
     * @param <T>          {@link java.lang.Enum} <p>the generic parameter is <code>Enum</code> type.</p>
     * @param clazz        {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param name         {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param paramClasses {@link java.lang.Class} <p>the param classes parameter is <code>Class</code> type.</p>
     * @param paramValues  {@link java.lang.Object} <p>the param values parameter is <code>Object</code> type.</p>
     * @return T <p>the return object is <code>T</code> type.</p>
     * @see java.lang.Enum
     * @see java.lang.Class
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.SuppressWarnings
     */
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
