import com.sun.tracing.ProviderFactory;
import com.sun.tracing.dtrace.DTraceProviderFactory;

public class TracingExample {
    interface MyProvider extends com.sun.tracing.Provider {
        void traceEvent(String message);
    }

    public static void main(String[] args) {
        ProviderFactory factory = new DTraceProviderFactory();
        MyProvider provider = factory.createProvider(MyProvider.class);
        provider.traceEvent("Tracing a message");
    }
}
