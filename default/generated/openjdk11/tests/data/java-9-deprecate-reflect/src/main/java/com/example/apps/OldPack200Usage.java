import sun.reflect.Reflection.*;

public class OldPack200Usage {
    public static void main(String[] args) {
        Class<?> caller = sun.reflect.Reflection.getCallerClass(2);
    }
}
