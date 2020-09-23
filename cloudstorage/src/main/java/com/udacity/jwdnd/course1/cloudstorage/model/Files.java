package com.udacity.jwdnd.course1.cloudstorage.model;

import java.io.InputStream;

public class Files {
    private Integer fileId;
    private String fileName;
    private String contentType;
    private Long fileSize;
    private Integer userId;
    private InputStream fileData;

    public Files(Integer fileId, String fileName, String contentType, Long fileSize, Integer userId, InputStream fileData)
    {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userId = userId;
        this.fileData = fileData;
    }
    // Getters and Setters
    public Integer getFileId() { return fileId; }
    public void setFileId(Integer fileId) { this.fileId = fileId; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }

    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public InputStream getFileData() { return fileData; }
    public void setFileData(InputStream fileData) { this.fileData = fileData; }

}
