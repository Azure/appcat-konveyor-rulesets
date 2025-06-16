
import com.sun.java.browser.plugin2.DOM;
import sun.plugin.dom.DOMObject;

public class DeprecatedDOMExample {
    public void manipulateDOM(DOM dom) {
        DOMObject doc = dom.getDocument();
        DOMObject body = (DOMObject) doc.getMember("body");
        body.setMember("innerHTML", "Updated content using deprecated DOM API");
    }
}
