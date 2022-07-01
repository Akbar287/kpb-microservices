package com.kpb.fileuploadservice.web;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@RequestMapping("/file/api/pubers")
public class PubersPicture {

    @PostMapping(value = "/kategori/{oldImage}", produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
    public ResponseEntity<?> uploadKategori (@RequestParam("image") MultipartFile file, @PathVariable("oldImage") String oldImage) {
        String directoryProfile = System.getProperty("user.dir") + "/file-upload-service/images/transaksi/kategori/";
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
    @GetMapping(value = "/kategori/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> showFile(@PathVariable("name") String name) throws IOException {
        String directoryProfile = System.getProperty("user.dir") + "/file-upload-service/images/transaksi/kategori/";
        Path fileNamePath = Paths.get(directoryProfile, name);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(fileNamePath));
        return ResponseEntity.ok().contentLength(fileNamePath.toFile().length()).contentType(MediaType.IMAGE_PNG).body(resource);
    }

    @PostMapping(value = "/jenis/{oldImage}", produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
    public ResponseEntity<?> uploadJenis (@RequestParam("image") MultipartFile file, @PathVariable("oldImage") String oldImage) {
        String directoryProfile = System.getProperty("user.dir") + "/file-upload-service/images/transaksi/jenis/";
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
    @GetMapping(value = "/jenis/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> showFileJenis(@PathVariable("name") String name) throws IOException {
        String directoryProfile = System.getProperty("user.dir") + "/file-upload-service/images/transaksi/jenis/";
        Path fileNamePath = Paths.get(directoryProfile, name);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(fileNamePath));
        return ResponseEntity.ok().contentLength(fileNamePath.toFile().length()).contentType(MediaType.IMAGE_PNG).body(resource);
    }

    @PostMapping(value = "/produk/{oldImage}", produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
    public ResponseEntity<?> uploadProduk (@RequestParam("image") MultipartFile file, @PathVariable("oldImage") String oldImage) {
        String directoryProfile = System.getProperty("user.dir") + "/file-upload-service/images/transaksi/produk/";
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
    @GetMapping(value = "/produk/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> showFileProduk(@PathVariable("name") String name) throws IOException {
        String directoryProfile = System.getProperty("user.dir") + "/file-upload-service/images/transaksi/produk/";
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
