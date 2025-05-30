package com.example.filesystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.annotation.Resource;

/**
 * File handling utility that demonstrates file system access patterns
 * in a Java EE application using Resource injection.
 */
public class FileHandler {

    @Resource(name = "uploadPath")
    private String uploadDir;
    
    @Resource(name = "tempDirectory")
    private String tempDir;
    
    @Resource(name = "documentFolderPath")
    private String documentFolder;
    
    @Resource(name = "imageStorageFolder")
    private String imageFolder;

    /**
     * Saves file content to the configured upload directory
     */
    public String saveFile(String filename, byte[] content) throws IOException {
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        File file = new File(directory, filename);
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(content);
        }
        
        return file.getAbsolutePath();
    }
    
    /**
     * Creates a temporary file and returns its path
     */
    public String createTempFile(String prefix, String suffix, byte[] content) throws IOException {
        File tempDirectory = new File(tempDir);
        if (!tempDirectory.exists()) {
            tempDirectory.mkdirs();
        }
        
        String uniqueFileName = prefix + "_" + UUID.randomUUID().toString() + suffix;
        File tempFile = new File(tempDirectory, uniqueFileName);
        
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            out.write(content);
        }
        
        return tempFile.getAbsolutePath();
    }
    
    /**
     * Reads file content from the document folder
     */
    public byte[] readDocument(String documentName) throws IOException {
        Path documentPath = Paths.get(documentFolder, documentName);
        return Files.readAllBytes(documentPath);
    }
    
    /**
     * Saves an image to the configured image folder
     */
    public void saveImage(String imageName, byte[] imageData) throws IOException {
        Path imagePath = Paths.get(imageFolder, imageName);
        Files.createDirectories(imagePath.getParent());
        Files.write(imagePath, imageData);
    }
    
    /**
     * Deletes a file from the upload directory
     */
    public boolean deleteFile(String filename) {
        File file = new File(uploadDir, filename);
        return file.delete();
    }
    
    /**
     * Lists all files in the document folder
     */
    public String[] listDocuments() {
        File dir = new File(documentFolder);
        return dir.exists() ? dir.list() : new String[0];
    }
} 