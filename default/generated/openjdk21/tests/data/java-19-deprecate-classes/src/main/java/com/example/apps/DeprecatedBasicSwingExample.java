import javax.swing.plaf.basic.BasicMenuItemUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import javax.swing.plaf.basic.BasicDirectoryModel;
import javax.swing.plaf.basic.BasicToolBarUI;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListDataEvent;

public class DeprecatedBasicSwingExample {
    public static void main(String[] args) {
        // BasicMenuItemUI.MouseInputHandler
        BasicMenuItemUI.MouseInputHandler mouseInputHandler = new BasicMenuItemUI().new MouseInputHandler();
        System.out.println("Created BasicMenuItemUI.MouseInputHandler: " + mouseInputHandler);

        // BasicScrollPaneUI.HSBChangeListener
        BasicScrollPaneUI.HSBChangeListener hsbChangeListener = new BasicScrollPaneUI().new HSBChangeListener();
        hsbChangeListener.stateChanged(new ChangeEvent(new Object()));
        System.out.println("Created BasicScrollPaneUI.HSBChangeListener: " + hsbChangeListener);

        // BasicScrollPaneUI.PropertyChangeHandler
        BasicScrollPaneUI.PropertyChangeHandler propertyChangeHandler = new BasicScrollPaneUI().new PropertyChangeHandler();
        propertyChangeHandler.propertyChange(new PropertyChangeEvent(new Object(), "property", null, null));
        System.out.println("Created BasicScrollPaneUI.PropertyChangeHandler: " + propertyChangeHandler);

        // BasicScrollPaneUI.ViewportChangeHandler
        BasicScrollPaneUI.ViewportChangeHandler viewportChangeHandler = new BasicScrollPaneUI().new ViewportChangeHandler();
        viewportChangeHandler.stateChanged(new ChangeEvent(new Object()));
        System.out.println("Created BasicScrollPaneUI.ViewportChangeHandler: " + viewportChangeHandler);

        // BasicScrollPaneUI.VSBChangeListener
        BasicScrollPaneUI.VSBChangeListener vsbChangeListener = new BasicScrollPaneUI().new VSBChangeListener();
        vsbChangeListener.stateChanged(new ChangeEvent(new Object()));
        System.out.println("Created BasicScrollPaneUI.VSBChangeListener: " + vsbChangeListener);

        // BasicDirectoryModel method calls (using dummy JList)
        JList<String> list = new JList<>();
        BasicDirectoryModel model = new BasicDirectoryModel(null, null, null);
        model.intervalAdded(new ListDataEvent(list, ListDataEvent.INTERVAL_ADDED, 0, 1));
        model.intervalRemoved(new ListDataEvent(list, ListDataEvent.INTERVAL_REMOVED, 0, 1));
        java.io.File fileA = new java.io.File("a");
        java.io.File fileB = new java.io.File("b");
        model.lt(fileA, fileB);
        System.out.println("Called BasicDirectoryModel methods: intervalAdded, intervalRemoved, lt");

        // BasicToolBarUI.createFloatingFrame
        BasicToolBarUI toolBarUI = new BasicToolBarUI();
        JToolBar toolBar = new JToolBar();
        JFrame frame = new JFrame();
        toolBarUI.createFloatingFrame(toolBar);
        System.out.println("Called BasicToolBarUI.createFloatingFrame");
    }
}
