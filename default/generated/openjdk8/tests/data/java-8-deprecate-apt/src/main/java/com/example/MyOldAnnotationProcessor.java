// File: MyOldAnnotationProcessor.java
import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;
import java.util.*;

public class MyOldAnnotationProcessor implements AnnotationProcessorFactory {

    @Override
    public Collection<String> supportedAnnotationTypes() {
        return Collections.singleton("MyOldAnnotation");
    }

    @Override
    public Collection<String> supportedOptions() {
        return Collections.emptySet();
    }

    @Override
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment env) {
        return new AnnotationProcessor() {
            @Override
            public void process() {
                for (TypeDeclaration type : env.getSpecifiedTypeDeclarations()) {
                    MyOldAnnotation annotation = type.getAnnotation(MyOldAnnotation.class);
                    if (annotation != null) {
                        System.out.println("APT found annotation on class: " + type.getSimpleName() +
                                " with value: " + annotation.value());
                    }
                }
            }
        };
    }
}
