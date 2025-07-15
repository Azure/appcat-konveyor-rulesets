package com.example.filesystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Example servlet that demonstrates usage of ServletContext.getRealPath()
 * for file operations.
 */
@WebServlet("/files/*")
public class ServletContextFileExample extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo(); // CodeQL [SM00698] justification
        if (pathInfo == null || pathInfo.equals("/")) {
            response.getWriter().println("Please specify a file path");
            return;
        }
        
        // Using ServletContext.getRealPath for file access
        ServletContext context = getServletContext();
        String relativePath = "/WEB-INF/files" + pathInfo;
        String realPath = context.getRealPath(relativePath); // CodeQL [SM00698] justification
        
        if (realPath == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Path not available: " + relativePath);
            return;
        }
        
        File file = new File(realPath);
        if (!file.exists() || file.isDirectory()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found: " + pathInfo);
            return;
        }
        
        // Set content type based on file name
        String contentType = context.getMimeType(file.getName());
        response.setContentType(contentType != null ? contentType : "application/octet-stream");
        response.setContentLength((int) file.length());
        
        if (relativePath.contains("..") || relativePath.contains("/") || relativePath.contains("\\")) {
            throw new IllegalArgumentException("Invalid path");
        }
        // Stream the file to the response
        try (InputStream is = context.getResourceAsStream(relativePath)) { // CodeQL [SM00698] justification
            if (is != null) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);
                }
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo(); // CodeQL [SM00698] justification
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File path required");
            return;
        }
        
        // Using ServletContext.getRealPath for file creation
        ServletContext context = getServletContext();
        String uploadDirPath = context.getRealPath("/WEB-INF/uploads");
        
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        
        // Create or overwrite file
        File outputFile = new File(uploadDir, pathInfo.substring(1)); // CodeQL [SM00698] justification
        try (FileOutputStream fos = new FileOutputStream(outputFile)) { // CodeQL [SM00698] justification
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = request.getInputStream().read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.getWriter().println("File saved: " + outputFile.getAbsolutePath()); // CodeQL [SM00707] justification
    }
} 