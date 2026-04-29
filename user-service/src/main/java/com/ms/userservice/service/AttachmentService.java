package com.ms.userservice.service;

import com.ms.userservice.entity.Attachment;
import com.ms.userservice.repository.AttachmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

@Service
public class AttachmentService {

    private static final String UPLOAD_DIR = "uploads/";

    private final AttachmentRepository attachmentRepository;

    public AttachmentService(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    public Attachment upload(Long ownerId, String ownerType, MultipartFile file) {
        if (file == null || file.isEmpty()) return null;

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE);
            String fileUrl = "/uploads/" + fileName;

            attachmentRepository.findByOwnerIdAndOwnerType(ownerId, ownerType)
                    .ifPresent(existing -> {
                        deleteFile(existing.getFileUrl());
                        attachmentRepository.delete(existing);
                    });

            Attachment attachment = new Attachment();
            attachment.setFileName(fileName);
            attachment.setFileUrl(fileUrl);
            attachment.setOwnerId(ownerId);
            attachment.setOwnerType(ownerType);
            return attachmentRepository.save(attachment);

        } catch (IOException e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }

    public void delete(Long ownerId, String ownerType) {
        attachmentRepository.findByOwnerIdAndOwnerType(ownerId, ownerType)
                .ifPresent(attachment -> {
                    deleteFile(attachment.getFileUrl());
                    attachmentRepository.delete(attachment);
                });
    }

    public String getFileUrl(Long ownerId, String ownerType) {
        return attachmentRepository.findByOwnerIdAndOwnerType(ownerId, ownerType)
                .map(Attachment::getFileUrl)
                .orElse(null);
    }

    private void deleteFile(String fileUrl) {
        if (fileUrl == null) return;
        File file = new File(UPLOAD_DIR + fileUrl.replace("/uploads/", ""));
        if (file.exists()) file.delete();
    }
}