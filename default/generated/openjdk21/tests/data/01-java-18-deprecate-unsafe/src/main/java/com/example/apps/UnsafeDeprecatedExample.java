import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeDeprecatedExample {

    static class InstanceClass {
        int instanceValue = 10;
    }

    static class StaticClass {
        static int staticValue = 42;
    }

    public static void main(String[] args) throws Exception {
        // Access Unsafe instance
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        // ===== 1. objectFieldOffset =====
        Field instanceField = InstanceClass.class.getDeclaredField("instanceValue");
        long instanceOffset = unsafe.objectFieldOffset(instanceField);
        InstanceClass instanceObj = new InstanceClass();
        int instanceFieldValue = unsafe.getInt(instanceObj, instanceOffset);
        System.out.println("objectFieldOffset - value: " + instanceFieldValue);

        // ===== 2. staticFieldOffset and 3. staticFieldBase =====
        Field staticField = StaticClass.class.getDeclaredField("staticValue");
        Object staticBase = unsafe.staticFieldBase(staticField);
        long staticOffset = unsafe.staticFieldOffset(staticField);
        int staticFieldValue = unsafe.getInt(staticBase, staticOffset);
        System.out.println("staticFieldBase + staticFieldOffset - value: " + staticFieldValue);
    }
}
