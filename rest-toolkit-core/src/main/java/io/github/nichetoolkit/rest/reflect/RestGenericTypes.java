package io.github.nichetoolkit.rest.reflect;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <code>RestGenericTypes</code>
 * <p>The rest generic types class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class RestGenericTypes {

    /**
     * <code>resolveFieldType</code>
     * <p>The resolve field type method.</p>
     * @param field   {@link java.lang.reflect.Field} <p>The field parameter is <code>Field</code> type.</p>
     * @param srcType {@link java.lang.reflect.Type} <p>The src type parameter is <code>Type</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>The resolve field type return object is <code>Type</code> type.</p>
     * @see java.lang.reflect.Field
     * @see java.lang.reflect.Type
     */
    public static Type resolveFieldType(Field field, Type srcType) {
        Type fieldType = field.getGenericType();
        Class<?> declaringClass = field.getDeclaringClass();
        return resolveType(fieldType, srcType, declaringClass);
    }

    /**
     * <code>resolveSuperclassTypes</code>
     * <p>The resolve superclass types method.</p>
     * @param srcType {@link java.lang.Class} <p>The src type parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>The resolve superclass types return object is <code>Type</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.reflect.Type
     */
    public static Type[] resolveSuperclassTypes(Class<?> srcType) {
        Type type = srcType.getGenericSuperclass();
        List<Type> result = new ArrayList<>();
        if (type instanceof Class) {
            result.addAll(Arrays.asList(resolveSuperclassTypes((Class<?>) type)));
        } else if (type instanceof ParameterizedType) {
            Collections.addAll(result, ((ParameterizedType) type).getActualTypeArguments());
        }
        return result.toArray(new Type[]{});
    }

    /**
     * <code>resolveInterfaceTypes</code>
     * <p>The resolve interface types method.</p>
     * @param srcType {@link java.lang.Class} <p>The src type parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>The resolve interface types return object is <code>Type</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.reflect.Type
     */
    public static Type[] resolveInterfaceTypes(Class<?> srcType) {
        Type[] types = srcType.getGenericInterfaces();
        List<Type> result = new ArrayList<>();
        for (Type type : types) {
            if (type instanceof Class) {
                result.addAll(Arrays.asList(resolveInterfaceTypes((Class<?>) type)));
            } else if (type instanceof ParameterizedType) {
                Collections.addAll(result, ((ParameterizedType) type).getActualTypeArguments());
            }
        }
        return result.toArray(new Type[]{});
    }

    /**
     * <code>resolveMethodTypes</code>
     * <p>The resolve method types method.</p>
     * @param method  {@link java.lang.reflect.Method} <p>The method parameter is <code>Method</code> type.</p>
     * @param srcType {@link java.lang.reflect.Type} <p>The src type parameter is <code>Type</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>The resolve method types return object is <code>Type</code> type.</p>
     * @see java.lang.reflect.Method
     * @see java.lang.reflect.Type
     */
    public static Type[] resolveMethodTypes(Method method, Type srcType) {
        Class<?> declaringClass = method.getDeclaringClass();
        TypeVariable<? extends Class<?>>[] typeParameters = declaringClass.getTypeParameters();
        Type[] result = new Type[typeParameters.length];
        for (int i = 0; i < typeParameters.length; i++) {
            result[i] = resolveType(typeParameters[i], srcType, declaringClass);
        }
        return result;
    }

    /**
     * <code>resolveFieldClass</code>
     * <p>The resolve field class method.</p>
     * @param field   {@link java.lang.reflect.Field} <p>The field parameter is <code>Field</code> type.</p>
     * @param srcType {@link java.lang.reflect.Type} <p>The src type parameter is <code>Type</code> type.</p>
     * @return {@link java.lang.Class} <p>The resolve field class return object is <code>Class</code> type.</p>
     * @see java.lang.reflect.Field
     * @see java.lang.reflect.Type
     * @see java.lang.Class
     */
    public static Class<?> resolveFieldClass(Field field, Type srcType) {
        Type fieldType = field.getGenericType();
        Class<?> declaringClass = field.getDeclaringClass();
        Type type = resolveType(fieldType, srcType, declaringClass);
        return resolveClass(type);
    }

    /**
     * <code>resolveClass</code>
     * <p>The resolve class method.</p>
     * @param type {@link java.lang.reflect.Type} <p>The type parameter is <code>Type</code> type.</p>
     * @return {@link java.lang.Class} <p>The resolve class return object is <code>Class</code> type.</p>
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
     * <p>The resolve return type method.</p>
     * @param method  {@link java.lang.reflect.Method} <p>The method parameter is <code>Method</code> type.</p>
     * @param srcType {@link java.lang.reflect.Type} <p>The src type parameter is <code>Type</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>The resolve return type return object is <code>Type</code> type.</p>
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
     * <p>The resolve param types method.</p>
     * @param method  {@link java.lang.reflect.Method} <p>The method parameter is <code>Method</code> type.</p>
     * @param srcType {@link java.lang.reflect.Type} <p>The src type parameter is <code>Type</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>The resolve param types return object is <code>Type</code> type.</p>
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
     * <p>The resolve type method.</p>
     * @param type           {@link java.lang.reflect.Type} <p>The type parameter is <code>Type</code> type.</p>
     * @param srcType        {@link java.lang.reflect.Type} <p>The src type parameter is <code>Type</code> type.</p>
     * @param declaringClass {@link java.lang.Class} <p>The declaring class parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>The resolve type return object is <code>Type</code> type.</p>
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
     * <p>The resolve generic array type method.</p>
     * @param genericArrayType {@link java.lang.reflect.GenericArrayType} <p>The generic array type parameter is <code>GenericArrayType</code> type.</p>
     * @param srcType          {@link java.lang.reflect.Type} <p>The src type parameter is <code>Type</code> type.</p>
     * @param declaringClass   {@link java.lang.Class} <p>The declaring class parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>The resolve generic array type return object is <code>Type</code> type.</p>
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
     * <p>The resolve parameterized type method.</p>
     * @param parameterizedType {@link java.lang.reflect.ParameterizedType} <p>The parameterized type parameter is <code>ParameterizedType</code> type.</p>
     * @param srcType           {@link java.lang.reflect.Type} <p>The src type parameter is <code>Type</code> type.</p>
     * @param declaringClass    {@link java.lang.Class} <p>The declaring class parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.reflect.ParameterizedType} <p>The resolve parameterized type return object is <code>ParameterizedType</code> type.</p>
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
     * <p>The resolve wildcard type method.</p>
     * @param wildcardType   {@link java.lang.reflect.WildcardType} <p>The wildcard type parameter is <code>WildcardType</code> type.</p>
     * @param srcType        {@link java.lang.reflect.Type} <p>The src type parameter is <code>Type</code> type.</p>
     * @param declaringClass {@link java.lang.Class} <p>The declaring class parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>The resolve wildcard type return object is <code>Type</code> type.</p>
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
     * <p>The resolve wildcard type bounds method.</p>
     * @param bounds         {@link java.lang.reflect.Type} <p>The bounds parameter is <code>Type</code> type.</p>
     * @param srcType        {@link java.lang.reflect.Type} <p>The src type parameter is <code>Type</code> type.</p>
     * @param declaringClass {@link java.lang.Class} <p>The declaring class parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>The resolve wildcard type bounds return object is <code>Type</code> type.</p>
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
     * <p>The resolve type var method.</p>
     * @param typeVar        {@link java.lang.reflect.TypeVariable} <p>The type var parameter is <code>TypeVariable</code> type.</p>
     * @param srcType        {@link java.lang.reflect.Type} <p>The src type parameter is <code>Type</code> type.</p>
     * @param declaringClass {@link java.lang.Class} <p>The declaring class parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>The resolve type var return object is <code>Type</code> type.</p>
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
     * <p>The scan super types method.</p>
     * @param typeVar        {@link java.lang.reflect.TypeVariable} <p>The type var parameter is <code>TypeVariable</code> type.</p>
     * @param srcType        {@link java.lang.reflect.Type} <p>The src type parameter is <code>Type</code> type.</p>
     * @param declaringClass {@link java.lang.Class} <p>The declaring class parameter is <code>Class</code> type.</p>
     * @param clazz          {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param superclass     {@link java.lang.reflect.Type} <p>The superclass parameter is <code>Type</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>The scan super types return object is <code>Type</code> type.</p>
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
     * <p>The translate parent type vars method.</p>
     * @param srcType    {@link java.lang.reflect.ParameterizedType} <p>The src type parameter is <code>ParameterizedType</code> type.</p>
     * @param srcClass   {@link java.lang.Class} <p>The src class parameter is <code>Class</code> type.</p>
     * @param parentType {@link java.lang.reflect.ParameterizedType} <p>The parent type parameter is <code>ParameterizedType</code> type.</p>
     * @return {@link java.lang.reflect.ParameterizedType} <p>The translate parent type vars return object is <code>ParameterizedType</code> type.</p>
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
