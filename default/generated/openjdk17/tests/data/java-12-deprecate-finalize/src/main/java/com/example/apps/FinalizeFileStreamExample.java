import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.File;
import java.util.zip.ZipFile;
import java.util.zip.Inflater;
import java.util.zip.Deflater;
import java.util.Enumeration;
import java.util.zip.ZipEntry;

public class FinalizeFileStreamExample {

    static class MyInputStream extends FileInputStream {
        public MyInputStream(String name) throws IOException {
            super(name);
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalizing FileInputStream");
            super.finalize(); // Deprecated
        }
    }

    static class MyOutputStream extends FileOutputStream {
        public MyOutputStream(String name) throws IOException {
            super(name);
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalizing FileOutputStream");
            super.finalize(); // Deprecated
        }
    }

    static class MyZipFile extends ZipFile {
        public MyZipFile(File file) throws Exception {
            super(file);
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalizing ZipFile");
            super.finalize(); // ❌ Deprecated and removed
        }
    }

    static class MyInflater extends Inflater {
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalizing Inflater");
            super.finalize(); // ❌ Deprecated and removed
        }
    }

    static class MyDeflater extends Deflater {
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalizing Deflater");
            super.finalize(); // ❌ Deprecated and removed
        }
    }

    public static void main(String[] args) throws Exception {
        MyInputStream in = new MyInputStream("input.txt");
        MyOutputStream out = new MyOutputStream("output.txt");

        // simulate usage
        in.read();
        out.write(65);

        in = null;
        out = null;

        System.gc(); // Request GC
        Thread.sleep(1000); // Give time for finalizers to run


        MyZipFile zip = new MyZipFile(new File("sample.zip"));
        MyInflater inflater = new MyInflater();
        MyDeflater deflater = new MyDeflater();

        // Simulate usage
        Enumeration<? extends ZipEntry> entries = zip.entries();
        while (entries.hasMoreElements()) {
            System.out.println(entries.nextElement().getName());
        }

        // Trigger GC to observe finalization (only in older JDKs)
        zip = null;
        inflater = null;
        deflater = null;

        System.gc();
        Thread.sleep(1000);
    }
}
