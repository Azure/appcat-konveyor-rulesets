// Main.java
package com.example.reflection;

import com.example.reflection.interfaces.BaseInterface;
import com.example.reflection.interfaces.ChildInterface;
import com.example.reflection.interfaces.MyInterface;

import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) {
        BaseInterface base = new ChildInterface() {
            @Override
            public void display() {
                System.out.println("Display from BaseInterface");
            }
        };

        // Using reflection to get methods
        try {
            // Get methods from BaseInterface
            Method[] baseMethods = BaseInterface.class.getMethods();
            System.out.println("Methods in BaseInterface:");
            for (Method method : baseMethods) {
                System.out.println(method.getName());
            }

            // Get methods from ChildInterface
            Method[] childMethods = ChildInterface.class.getMethods();
            System.out.println("\nMethods in ChildInterface:");
            for (Method method : childMethods) {
                System.out.println(method.getName());
            }

            // Get specific method using getMethod
            Method displayMethod = ChildInterface.class.getMethod("display");
            System.out.println("\nRetrieved method: " + displayMethod.getName());

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Class<?> proxyClass = Proxy.getProxyClass(
            HiddenInterface.class.getClassLoader(),
            HiddenInterface.class
        );

        Constructor<?> ctor = proxyClass.getConstructor(InvocationHandler.class);

        // This will fail in Java 8+ with IllegalAccessException
        Object proxy = ctor.newInstance((InvocationHandler) (proxy1, method, args1) -> {
            System.out.println("Hello from proxy!");
            return null;
        });

        ((HiddenInterface) proxy).sayHello();

        try {
            MyInterface proxy = (MyInterface) Proxy.newProxyInstance(
                MyInterface.class.getClassLoader(),
                new Class<?>[]{MyInterface.class},
                null // Intentionally passing null handler
            );

            proxy.doSomething(); // Would throw NPE at runtime in Java 7
        } catch (NullPointerException e) {
            System.out.println("Caught expected NullPointerException: " + e.getMessage());
        }
    }
}

