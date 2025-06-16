public class Main extends javafx.application.Application {
    @Override
    public void start(javafx.stage.Stage primaryStage) {
        primaryStage.setTitle("JavaFX Builder Example");
        BuilderExample builderExample = new BuilderExample();
        javafx.scene.Scene scene = new javafx.scene.Scene(builderExample.createUI(), 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}