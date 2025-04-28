import java.lang.Class;

public class DefaultMethodDemo {
    public void demonstrate() throws Exception {
        // Create an instance of the ChildInterface
        ChildInterface child = new ChildInterface() {
            @Override
            public void display() {
                System.out.println("Display from ChildInterface");
            }
        };

        // Get the Class object for the ChildInterface
        Class<?> clazz = child.getClass();

        // Retrieve methods using Class.getMethod and Class.getMethods
        System.out.println("Using Class.getMethod:");
        try {
            // This will retrieve the display method from BaseInterface
            System.out.println("Method: " + clazz.getMethod("display").getName());
        } catch (NoSuchMethodException e) {
            System.out.println("Method not found: display");
        }

        try {
            // This will throw an exception because default methods are not visible
            System.out.println("Method: " + clazz.getMethod("show").getName());
        } catch (NoSuchMethodException e) {
            System.out.println("Method not found: show");
        }

        System.out.println("\nUsing Class.getMethods:");
        // This will retrieve all public methods, including inherited ones
        for (java.lang.reflect.Method method : clazz.getMethods()) {
            System.out.println("Method: " + method.getName());
        }
    }
}