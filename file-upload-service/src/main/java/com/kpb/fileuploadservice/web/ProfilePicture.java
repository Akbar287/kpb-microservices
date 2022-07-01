package com.kpb.fileuploadservice.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@RequestMapping("/file/api/profile")
public class ProfilePicture {

    @PostMapping(value = "/{oldImage}", produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
    public ResponseEntity<?> uploadFile (@RequestParam("image") MultipartFile file, @PathVariable("oldImage") String oldImage) {
        String directoryProfile = System.getProperty("user.dir") + "/file-upload-service/images/profile/";
        if(!Objects.equals(oldImage, "avatar.png")) {
            Path oldFileNamePath = Paths.get(directoryProfile, oldImage);
            File oldFile = new File(String.valueOf(oldFileNamePath));
            if(oldFile.exists()) oldFile.delete();
        }
        makeDirectoryIfNotExist(directoryProfile);
        String name = Objects.requireNonNull(FilenameUtils.getName(file.getOriginalFilename()));
        Long datetime = System.currentTimeMillis();
        name = datetime + "_" + name;
        Path fileNamePath = Paths.get(directoryProfile, name);
        try {
            Files.write(fileNamePath, file.getBytes());
            return new ResponseEntity<>(name, HttpStatus.CREATED);
        } catch (IOException ex) {
            return new ResponseEntity<>("Image is not uploaded", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> showFile(@PathVariable("name") String name) throws IOException {
        String directoryProfile = System.getProperty("user.dir") + "/file-upload-service/images/profile/";
        Path fileNamePath = Paths.get(directoryProfile, name);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(fileNamePath));
        return ResponseEntity.ok().contentLength(fileNamePath.toFile().length()).contentType(MediaType.IMAGE_PNG).body(resource);
    }

    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}
