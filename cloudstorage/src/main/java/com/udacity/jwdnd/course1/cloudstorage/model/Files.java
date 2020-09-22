package com.udacity.jwdnd.course1.cloudstorage.model;

import java.io.InputStream;

public class Files {
    private Integer filedId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private Integer userId;
    private InputStream fileData;

    // Getters and Setters
    public Integer getFiledId() { return filedId; }
    public void setFiledId(Integer filedId) { this.filedId = filedId; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }

    public String getFileSize() { return fileSize; }
    public void setFileSize(String fileSize) { this.fileSize = fileSize; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public InputStream getFileData() { return fileData; }
    public void setFileData(InputStream fileData) { this.fileData = fileData; }

}