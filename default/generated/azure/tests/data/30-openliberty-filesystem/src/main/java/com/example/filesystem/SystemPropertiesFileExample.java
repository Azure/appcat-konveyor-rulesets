package com.example.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Example class demonstrating the use of System properties
 * to obtain file paths for various operations.
 */
public class SystemPropertiesFileExample {

    // File paths from System properties
    private final String baseDir;
    private final String configDir;
    private final String dataDir;
    private final String tempDir;
    private final String logsDir;
    
    public SystemPropertiesFileExample() {
        // Get file paths from system properties with defaults
        this.baseDir = System.getProperty("app.base.dir", "/opt/app");
        this.configDir = System.getProperty("app.config.dir", this.baseDir + "/config");
        this.dataDir = System.getProperty("app.data.dir", this.baseDir + "/data");
        this.tempDir = System.getProperty("app.temp.dir", System.getProperty("java.io.tmpdir"));
        this.logsDir = System.getProperty("app.logs.dir", this.baseDir + "/logs");
        
        // Ensure directories exist
        createDirectoryIfNotExists(this.configDir);
        createDirectoryIfNotExists(this.dataDir);
        createDirectoryIfNotExists(this.tempDir);
        createDirectoryIfNotExists(this.logsDir);
    }
    
    /**
     * Create directory if it doesn't exist
     */
    private void createDirectoryIfNotExists(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
    
    /**
     * Loads application configuration from the config directory
     */
    public Properties loadConfig(String configName) throws IOException {
        Properties props = new Properties();
        File configFile = new File(configDir, configName + ".properties");
        
        if (configFile.exists()) {
            try (FileInputStream fis = new FileInputStream(configFile)) {
                props.load(fis);
            }
        }
        
        return props;
    }
    
    /**
     * Saves configuration to the config directory
     */
    public void saveConfig(String configName, Properties props) throws IOException {
        File configFile = new File(configDir, configName + ".properties");
        
        try (FileOutputStream fos = new FileOutputStream(configFile)) {
            props.store(fos, "Updated configuration");
        }
    }
    
    /**
     * Saves data to the data directory
     */
    public void saveData(String filename, byte[] data) throws IOException {
        Path dataPath = Paths.get(dataDir, filename);
        Files.createDirectories(dataPath.getParent());
        Files.write(dataPath, data);
    }
    
    /**
     * Creates a temporary file in the temp directory
     */
    public File createTempFile(String prefix, String suffix) throws IOException {
        File tempFile = File.createTempFile(prefix, suffix, new File(tempDir));
        return tempFile;
    }
    
    /**
     * Logs a message to a log file in the logs directory
     */
    public void logMessage(String logName, String message) throws IOException {
        File logFile = new File(logsDir, logName + ".log");
        Files.write(
            logFile.toPath(), 
            Arrays.asList(System.currentTimeMillis() + ": " + message), 
            java.nio.file.StandardOpenOption.CREATE, 
            java.nio.file.StandardOpenOption.APPEND
        );
    }
    
    /**
     * Lists files in the data directory
     */
    public List<String> listDataFiles() {
        File dir = new File(dataDir);
        String[] files = dir.list();
        return files != null ? Arrays.asList(files) : Arrays.asList();
    }
} 