import com.sun.media.sound.WaveFileReader;

public class MediaSoundExample {
    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());

        try {
            WaveFileReader reader = new WaveFileReader();
            System.out.println("Successfully accessed com.sun.media.sound.WaveFileReader");
        } catch (SecurityException e) {
            System.err.println("Access denied by SecurityManager: " + e);
        } catch (Throwable t) {
            System.err.println("Other error: " + t);
        }
    }
}
