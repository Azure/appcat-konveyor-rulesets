// File: MyOldAnnotation.java
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyOldAnnotation {
    String value();
}
