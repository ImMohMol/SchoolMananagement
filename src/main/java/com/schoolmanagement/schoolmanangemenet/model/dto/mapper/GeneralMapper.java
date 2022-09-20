package com.schoolmanagement.schoolmanangemenet.model.dto.mapper;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class GeneralMapper {
    public static <TSource, TDestination> TDestination convert (TSource source, Type destinationType) {
        try {
            TDestination result = (TDestination) Class.forName(destinationType.getTypeName());
            List<Method> sourceAccessorMethods = createAccessorMethods(source, true);
            List<Method> destinationAccessorMethods = createAccessorMethods(result, false);
            invokeMethods(sourceAccessorMethods, destinationAccessorMethods, source, result);
            return result;
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            // TODO: throw new exception here to handle bad things in converting models to dto and dto to models!
            return null;
        }
    }

    private static void invokeMethods (List<Method> sourceMethods, List<Method> destinationMethods, Object source,
                                       Object destination) throws InvocationTargetException, IllegalAccessException {
        for (Method handler : destinationMethods) {
            for (Method sourceAccessorMethod : sourceMethods) {
                if (sourceAccessorMethod.getName().substring(3).equals(handler.getName().substring(3))) {
                    handler.invoke(destination, sourceAccessorMethod.invoke(source));
                    break;
                }
            }
        }
    }

    private static List<Method> createAccessorMethods (Object object, boolean isCreatingGetterMethods) {
        Field[] fields = object.getClass().getDeclaredFields();
        Method[] methods = object.getClass().getDeclaredMethods();
        List<Method> result = new ArrayList<>();
        for (Field field : fields)
            addMethodToTheMethods(methods, result, createMethodName(field, isCreatingGetterMethods));
        return result;
    }

    private static StringBuilder createMethodName (Field field, boolean isCreatingGetterMethods) {
        StringBuilder fieldNameHandler = new StringBuilder(field.getName());
        fieldNameHandler.replace(0, 0, String.valueOf(Character.toUpperCase(fieldNameHandler.charAt(0))));
        fieldNameHandler.insert(0, isCreatingGetterMethods ? "get" : "set");
        return fieldNameHandler;
    }

    private static void addMethodToTheMethods (Method[] methods, List<Method> result, StringBuilder methodNameHandler) {
        for (Method method : methods) {
            if (method.getName().equals(methodNameHandler.toString())) {
                result.add(method);
                break;
            }
        }
    }
}
