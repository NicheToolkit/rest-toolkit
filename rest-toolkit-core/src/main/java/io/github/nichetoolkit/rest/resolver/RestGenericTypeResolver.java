package io.github.nichetoolkit.rest.resolver;

import java.lang.reflect.*;

/**
 * <code>RestGenericTypeResolver</code>
 * <p>The type rest generic type resolver class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class RestGenericTypeResolver {

    /**
     * <code>resolveType</code>
     * <p>the type method.</p>
     * @param field   {@link java.lang.reflect.Field} <p>the field parameter is <code>Field</code> type.</p>
     * @param srcType {@link java.lang.reflect.Type} <p>the src type parameter is <code>Type</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>the type return object is <code>Type</code> type.</p>
     * @see java.lang.reflect.Field
     * @see java.lang.reflect.Type
     */
    public static Type resolveType(Field field, Type srcType) {
        Type fieldType = field.getGenericType();
        Class<?> declaringClass = field.getDeclaringClass();
        return resolveType(fieldType, srcType, declaringClass);
    }

    /**
     * <code>resolveClass</code>
     * <p>the class method.</p>
     * @param field   {@link java.lang.reflect.Field} <p>the field parameter is <code>Field</code> type.</p>
     * @param srcType {@link java.lang.reflect.Type} <p>the src type parameter is <code>Type</code> type.</p>
     * @return {@link java.lang.Class} <p>the class return object is <code>Class</code> type.</p>
     * @see java.lang.reflect.Field
     * @see java.lang.reflect.Type
     * @see java.lang.Class
     */
    public static Class<?> resolveClass(Field field, Type srcType) {
        Type fieldType = field.getGenericType();
        Class<?> declaringClass = field.getDeclaringClass();
        Type type = resolveType(fieldType, srcType, declaringClass);
        return resolveClass(type);
    }

    /**
     * <code>resolveClass</code>
     * <p>the class method.</p>
     * @param type {@link java.lang.reflect.Type} <p>the type parameter is <code>Type</code> type.</p>
     * @return {@link java.lang.Class} <p>the class return object is <code>Class</code> type.</p>
     * @see java.lang.reflect.Type
     * @see java.lang.Class
     */
    public static Class<?> resolveClass(Type type) {
        if (type instanceof Class) {
            return (Class<?>) type;
        } else if (type instanceof ParameterizedType) {
            return (Class<?>) ((ParameterizedType) type).getRawType();
        } else if (type instanceof TypeVariable<?>) {
            Type[] bounds = ((TypeVariable<?>) type).getBounds();
            return (Class<?>) bounds[0];
        } else if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            if (componentType instanceof Class) {
                return Array.newInstance((Class<?>) componentType, 0).getClass();
            } else {
                Class<?> componentClass = resolveClass(componentType);
                return Array.newInstance(componentClass, 0).getClass();
            }
        }
        return Object.class;
    }

    /**
     * <code>resolveReturnType</code>
     * <p>the return type method.</p>
     * @param method  {@link java.lang.reflect.Method} <p>the method parameter is <code>Method</code> type.</p>
     * @param srcType {@link java.lang.reflect.Type} <p>the src type parameter is <code>Type</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>the return type return object is <code>Type</code> type.</p>
     * @see java.lang.reflect.Method
     * @see java.lang.reflect.Type
     */
    public static Type resolveReturnType(Method method, Type srcType) {
        Type returnType = method.getGenericReturnType();
        Class<?> declaringClass = method.getDeclaringClass();
        return resolveType(returnType, srcType, declaringClass);
    }

    /**
     * <code>resolveParamTypes</code>
     * <p>the param types method.</p>
     * @param method  {@link java.lang.reflect.Method} <p>the method parameter is <code>Method</code> type.</p>
     * @param srcType {@link java.lang.reflect.Type} <p>the src type parameter is <code>Type</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>the param types return object is <code>Type</code> type.</p>
     * @see java.lang.reflect.Method
     * @see java.lang.reflect.Type
     */
    public static Type[] resolveParamTypes(Method method, Type srcType) {
        Type[] paramTypes = method.getGenericParameterTypes();
        Class<?> declaringClass = method.getDeclaringClass();
        Type[] result = new Type[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            result[i] = resolveType(paramTypes[i], srcType, declaringClass);
        }
        return result;
    }

    /**
     * <code>resolveType</code>
     * <p>the type method.</p>
     * @param type           {@link java.lang.reflect.Type} <p>the type parameter is <code>Type</code> type.</p>
     * @param srcType        {@link java.lang.reflect.Type} <p>the src type parameter is <code>Type</code> type.</p>
     * @param declaringClass {@link java.lang.Class} <p>the declaring class parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>the type return object is <code>Type</code> type.</p>
     * @see java.lang.reflect.Type
     * @see java.lang.Class
     */
    public static Type resolveType(Type type, Type srcType, Class<?> declaringClass) {
        if (type instanceof TypeVariable) {
            return resolveTypeVar((TypeVariable<?>) type, srcType, declaringClass);
        } else if (type instanceof ParameterizedType) {
            return resolveParameterizedType((ParameterizedType) type, srcType, declaringClass);
        } else if (type instanceof GenericArrayType) {
            return resolveGenericArrayType((GenericArrayType) type, srcType, declaringClass);
        } else {
            return type;
        }
    }

    /**
     * <code>resolveGenericArrayType</code>
     * <p>the generic array type method.</p>
     * @param genericArrayType {@link java.lang.reflect.GenericArrayType} <p>the generic array type parameter is <code>GenericArrayType</code> type.</p>
     * @param srcType          {@link java.lang.reflect.Type} <p>the src type parameter is <code>Type</code> type.</p>
     * @param declaringClass   {@link java.lang.Class} <p>the declaring class parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>the generic array type return object is <code>Type</code> type.</p>
     * @see java.lang.reflect.GenericArrayType
     * @see java.lang.reflect.Type
     * @see java.lang.Class
     */
    private static Type resolveGenericArrayType(GenericArrayType genericArrayType, Type srcType, Class<?> declaringClass) {
        Type componentType = genericArrayType.getGenericComponentType();
        Type resolvedComponentType = null;
        if (componentType instanceof TypeVariable) {
            resolvedComponentType = resolveTypeVar((TypeVariable<?>) componentType, srcType, declaringClass);
        } else if (componentType instanceof GenericArrayType) {
            resolvedComponentType = resolveGenericArrayType((GenericArrayType) componentType, srcType, declaringClass);
        } else if (componentType instanceof ParameterizedType) {
            resolvedComponentType = resolveParameterizedType((ParameterizedType) componentType, srcType, declaringClass);
        }
        if (resolvedComponentType instanceof Class) {
            return Array.newInstance((Class<?>) resolvedComponentType, 0).getClass();
        } else {
            return new DefaultGenericArrayType(resolvedComponentType);
        }
    }

    /**
     * <code>resolveParameterizedType</code>
     * <p>the parameterized type method.</p>
     * @param parameterizedType {@link java.lang.reflect.ParameterizedType} <p>the parameterized type parameter is <code>ParameterizedType</code> type.</p>
     * @param srcType           {@link java.lang.reflect.Type} <p>the src type parameter is <code>Type</code> type.</p>
     * @param declaringClass    {@link java.lang.Class} <p>the declaring class parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.reflect.ParameterizedType} <p>the parameterized type return object is <code>ParameterizedType</code> type.</p>
     * @see java.lang.reflect.ParameterizedType
     * @see java.lang.reflect.Type
     * @see java.lang.Class
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    private static ParameterizedType resolveParameterizedType(ParameterizedType parameterizedType, Type srcType, Class<?> declaringClass) {
        Class<?> rawType = (Class<?>) parameterizedType.getRawType();
        Type[] typeArgs = parameterizedType.getActualTypeArguments();
        Type[] args = new Type[typeArgs.length];
        for (int i = 0; i < typeArgs.length; i++) {
            if (typeArgs[i] instanceof TypeVariable) {
                args[i] = resolveTypeVar((TypeVariable<?>) typeArgs[i], srcType, declaringClass);
            } else if (typeArgs[i] instanceof ParameterizedType) {
                args[i] = resolveParameterizedType((ParameterizedType) typeArgs[i], srcType, declaringClass);
            } else if (typeArgs[i] instanceof WildcardType) {
                args[i] = resolveWildcardType((WildcardType) typeArgs[i], srcType, declaringClass);
            } else {
                args[i] = typeArgs[i];
            }
        }
        return new DefaultParameterizedType(rawType, null, args);
    }

    /**
     * <code>resolveWildcardType</code>
     * <p>the wildcard type method.</p>
     * @param wildcardType   {@link java.lang.reflect.WildcardType} <p>the wildcard type parameter is <code>WildcardType</code> type.</p>
     * @param srcType        {@link java.lang.reflect.Type} <p>the src type parameter is <code>Type</code> type.</p>
     * @param declaringClass {@link java.lang.Class} <p>the declaring class parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>the wildcard type return object is <code>Type</code> type.</p>
     * @see java.lang.reflect.WildcardType
     * @see java.lang.reflect.Type
     * @see java.lang.Class
     */
    private static Type resolveWildcardType(WildcardType wildcardType, Type srcType, Class<?> declaringClass) {
        Type[] lowerBounds = resolveWildcardTypeBounds(wildcardType.getLowerBounds(), srcType, declaringClass);
        Type[] upperBounds = resolveWildcardTypeBounds(wildcardType.getUpperBounds(), srcType, declaringClass);
        return new DefaultWildcardType(lowerBounds, upperBounds);
    }

    /**
     * <code>resolveWildcardTypeBounds</code>
     * <p>the wildcard type bounds method.</p>
     * @param bounds         {@link java.lang.reflect.Type} <p>the bounds parameter is <code>Type</code> type.</p>
     * @param srcType        {@link java.lang.reflect.Type} <p>the src type parameter is <code>Type</code> type.</p>
     * @param declaringClass {@link java.lang.Class} <p>the declaring class parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>the wildcard type bounds return object is <code>Type</code> type.</p>
     * @see java.lang.reflect.Type
     * @see java.lang.Class
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    private static Type[] resolveWildcardTypeBounds(Type[] bounds, Type srcType, Class<?> declaringClass) {
        Type[] result = new Type[bounds.length];
        for (int i = 0; i < bounds.length; i++) {
            if (bounds[i] instanceof TypeVariable) {
                result[i] = resolveTypeVar((TypeVariable<?>) bounds[i], srcType, declaringClass);
            } else if (bounds[i] instanceof ParameterizedType) {
                result[i] = resolveParameterizedType((ParameterizedType) bounds[i], srcType, declaringClass);
            } else if (bounds[i] instanceof WildcardType) {
                result[i] = resolveWildcardType((WildcardType) bounds[i], srcType, declaringClass);
            } else {
                result[i] = bounds[i];
            }
        }
        return result;
    }

    /**
     * <code>resolveTypeVar</code>
     * <p>the type var method.</p>
     * @param typeVar        {@link java.lang.reflect.TypeVariable} <p>the type var parameter is <code>TypeVariable</code> type.</p>
     * @param srcType        {@link java.lang.reflect.Type} <p>the src type parameter is <code>Type</code> type.</p>
     * @param declaringClass {@link java.lang.Class} <p>the declaring class parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>the type var return object is <code>Type</code> type.</p>
     * @see java.lang.reflect.TypeVariable
     * @see java.lang.Class
     */
    private static Type resolveTypeVar(TypeVariable<?> typeVar, Type srcType, Class<?> declaringClass) {
        Type result;
        Class<?> clazz;
        if (srcType instanceof Class) {
            clazz = (Class<?>) srcType;
        } else if (srcType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) srcType;
            clazz = (Class<?>) parameterizedType.getRawType();
        } else {
            throw new IllegalArgumentException("The 2nd arg must be Class or ParameterizedType, but was: " + srcType.getClass());
        }

        if (clazz == declaringClass) {
            Type[] bounds = typeVar.getBounds();
            if (bounds.length > 0) {
                return bounds[0];
            }
            return Object.class;
        }

        Type superclass = clazz.getGenericSuperclass();
        result = scanSuperTypes(typeVar, srcType, declaringClass, clazz, superclass);
        if (result != null) {
            return result;
        }

        Type[] superInterfaces = clazz.getGenericInterfaces();
        for (Type superInterface : superInterfaces) {
            result = scanSuperTypes(typeVar, srcType, declaringClass, clazz, superInterface);
            if (result != null) {
                return result;
            }
        }
        return Object.class;
    }

    /**
     * <code>scanSuperTypes</code>
     * <p>the super types method.</p>
     * @param typeVar        {@link java.lang.reflect.TypeVariable} <p>the type var parameter is <code>TypeVariable</code> type.</p>
     * @param srcType        {@link java.lang.reflect.Type} <p>the src type parameter is <code>Type</code> type.</p>
     * @param declaringClass {@link java.lang.Class} <p>the declaring class parameter is <code>Class</code> type.</p>
     * @param clazz          {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param superclass     {@link java.lang.reflect.Type} <p>the superclass parameter is <code>Type</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>the super types return object is <code>Type</code> type.</p>
     * @see java.lang.reflect.TypeVariable
     * @see java.lang.Class
     */
    private static Type scanSuperTypes(TypeVariable<?> typeVar, Type srcType, Class<?> declaringClass, Class<?> clazz, Type superclass) {
        if (superclass instanceof ParameterizedType) {
            ParameterizedType parentAsType = (ParameterizedType) superclass;
            Class<?> parentAsClass = (Class<?>) parentAsType.getRawType();
            TypeVariable<?>[] parentTypeVars = parentAsClass.getTypeParameters();
            if (srcType instanceof ParameterizedType) {
                parentAsType = translateParentTypeVars((ParameterizedType) srcType, clazz, parentAsType);
            }
            if (declaringClass == parentAsClass) {
                for (int i = 0; i < parentTypeVars.length; i++) {
                    if (typeVar.equals(parentTypeVars[i])) {
                        return parentAsType.getActualTypeArguments()[i];
                    }
                }
            }
            if (declaringClass.isAssignableFrom(parentAsClass)) {
                return resolveTypeVar(typeVar, parentAsType, declaringClass);
            }
        } else if (superclass instanceof Class && declaringClass.isAssignableFrom((Class<?>) superclass)) {
            return resolveTypeVar(typeVar, superclass, declaringClass);
        }
        return null;
    }

    /**
     * <code>translateParentTypeVars</code>
     * <p>the parent type vars method.</p>
     * @param srcType    {@link java.lang.reflect.ParameterizedType} <p>the src type parameter is <code>ParameterizedType</code> type.</p>
     * @param srcClass   {@link java.lang.Class} <p>the src class parameter is <code>Class</code> type.</p>
     * @param parentType {@link java.lang.reflect.ParameterizedType} <p>the parent type parameter is <code>ParameterizedType</code> type.</p>
     * @return {@link java.lang.reflect.ParameterizedType} <p>the parent type vars return object is <code>ParameterizedType</code> type.</p>
     * @see java.lang.reflect.ParameterizedType
     * @see java.lang.Class
     */
    private static ParameterizedType translateParentTypeVars(ParameterizedType srcType, Class<?> srcClass, ParameterizedType parentType) {
        Type[] parentTypeArgs = parentType.getActualTypeArguments();
        Type[] srcTypeArgs = srcType.getActualTypeArguments();
        TypeVariable<?>[] srcTypeVars = srcClass.getTypeParameters();
        Type[] newParentArgs = new Type[parentTypeArgs.length];
        boolean noChange = true;
        for (int i = 0; i < parentTypeArgs.length; i++) {
            if (parentTypeArgs[i] instanceof TypeVariable) {
                for (int j = 0; j < srcTypeVars.length; j++) {
                    if (srcTypeVars[j].equals(parentTypeArgs[i])) {
                        noChange = false;
                        newParentArgs[i] = srcTypeArgs[j];
                    }
                }
            } else {
                newParentArgs[i] = parentTypeArgs[i];
            }
        }
        return noChange ? parentType : new DefaultParameterizedType((Class<?>) parentType.getRawType(), null, newParentArgs);
    }

}
