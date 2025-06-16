import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class JavaFXAppletExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        HostServices hostServices = getHostServices();

        // Deprecated and removed in newer JDKs
        JSObject webContext = (JSObject) hostServices.getWebContext();
        webContext.call("alert", "Hello from JavaFX Applet!");
    }
}
