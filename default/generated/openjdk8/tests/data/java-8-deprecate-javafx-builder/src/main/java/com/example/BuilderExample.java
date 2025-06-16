import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;

public class BuilderExample {

    public static void main(String[] args) {
        // Create a JavaFX application
        javafx.application.Application.launch(MyApp.class, args);
    }

    public static class MyApp extends javafx.application.Application {
        @Override
        public void start(javafx.stage.Stage primaryStage) {
            // Create a Button using the Builder pattern
            javafx.scene.control.Button button = new javafx.scene.control.ButtonBuilder()
                    .text("Click Me")
                    .onAction(event -> System.out.println("Button Clicked!"))
                    .build();

            // Set up the scene and stage
            javafx.scene.Scene scene = new javafx.scene.Scene(new javafx.scene.layout.StackPane(button), 300, 200);
            primaryStage.setTitle("JavaFX Builder Example");
            primaryStage.setScene(scene);
            primaryStage.show();

            Label label = LabelBuilder.create()
            .text("Hello, World!")
            .build(); // ✅ returns a Label (which extends Node)

            StackPane pane = StackPaneBuilder.create()
            .children(label)  // .children(Node...)
            .build();
        }
    }
}