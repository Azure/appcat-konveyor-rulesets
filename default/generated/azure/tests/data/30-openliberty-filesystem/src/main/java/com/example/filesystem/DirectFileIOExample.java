package com.example.filesystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * Example class demonstrating various direct file I/O operations
 * that would need to be migrated to cloud storage on Azure.
 */
public class DirectFileIOExample {

    /**
     * Reads text from a file using java.io API
     */
    public String readTextFileJavaIO(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    
    /**
     * Writes text to a file using java.io API
     */
    public void writeTextFileJavaIO(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }
    
    /**
     * Reads binary data from a file using java.io API
     */
    public byte[] readBinaryFileJavaIO(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] data = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(data);
        }
        return data;
    }
    
    /**
     * Writes binary data to a file using java.io API
     */
    public void writeBinaryFileJavaIO(String filePath, byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(data);
        }
    }
    
    /**
     * Reads a file using NIO channels
     */
    public ByteBuffer readFileWithChannel(String filePath) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filePath, "r");
             FileChannel channel = file.getChannel()) {
            
            ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
            channel.read(buffer);
            buffer.flip();
            return buffer;
        }
    }
    
    /**
     * Writes data using NIO channels
     */
    public void writeFileWithChannel(String filePath, ByteBuffer buffer) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filePath, "rw");
             FileChannel channel = file.getChannel()) {
            
            buffer.rewind();
            channel.write(buffer);
        }
    }
    
    /**
     * Reads all bytes from a file using java.nio.file API
     */
    public byte[] readAllBytesNIO(String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }
    
    /**
     * Writes all bytes to a file using java.nio.file API
     */
    public void writeAllBytesNIO(String filePath, byte[] data) throws IOException {
        Files.write(Paths.get(filePath), data);
    }
    
    /**
     * Copies a file from source to destination
     */
    public void copyFile(String sourcePath, String destPath) throws IOException {
        Files.copy(Paths.get(sourcePath), Paths.get(destPath), StandardCopyOption.REPLACE_EXISTING);
    }
    
    /**
     * Moves a file from source to destination
     */
    public void moveFile(String sourcePath, String destPath) throws IOException {
        Files.move(Paths.get(sourcePath), Paths.get(destPath), StandardCopyOption.REPLACE_EXISTING);
    }
    
    /**
     * Lists files in a directory
     */
    public Stream<Path> listFiles(String directoryPath) throws IOException {
        return Files.list(Paths.get(directoryPath));
    }
    
    /**
     * Creates directory structure
     */
    public void createDirectories(String directoryPath) throws IOException {
        Files.createDirectories(Paths.get(directoryPath));
    }
    
    /**
     * Deletes a file
     */
    public void deleteFile(String filePath) throws IOException {
        Files.delete(Paths.get(filePath));
    }
} 