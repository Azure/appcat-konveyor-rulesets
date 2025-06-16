import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeAnonymousClassDemo {
    public static void main(String[] args) throws Exception {
        // Obtain Unsafe instance
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        // Dummy class bytes (empty class)
        byte[] classBytes = new byte[] {
            (byte)0xCA, (byte)0xFE, (byte)0xBA, (byte)0xBE, // magic number
            // ... rest of a valid class file (not shown here for brevity)
        };

        Class<?> anonClass = unsafe.defineAnonymousClass(UnsafeAnonymousClassDemo.class, classBytes, null);
        System.out.println("Anonymous class defined: " + anonClass);
    }
}
