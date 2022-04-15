package com.mdstailor.tailoringbackend.profile;

import com.mdstailor.tailoringbackend.customer.entity.Customer;
import com.mdstailor.tailoringbackend.customer.repository.CustomerRepository;
import com.mdstailor.tailoringbackend.exceptions.CustomerNotFoundException.CustomerNotFoundException;
import com.mdstailor.tailoringbackend.order.entity.Order;
import com.mdstailor.tailoringbackend.profile.entity.ImageModel;
import com.mdstailor.tailoringbackend.profile.repository.ImageRepository;
import com.mdstailor.tailoringbackend.profile.service.FileUploadHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageUploadController {

    private final ImageRepository imageRepository;
    private final CustomerRepository customerRepository;


    @Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File is Empty");
            }
            boolean f = fileUploadHelper.uploadFile(file);
            if (f) {
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while uploading file");

    }

    @PostMapping("/uploadImages")
    public ResponseEntity<String> uploadImage(@ModelAttribute ImageModel imageModel, @RequestParam("file") MultipartFile file, Customer customer) {
        try {

            Long id = 1l;
            Customer customers = this.customerRepository.getById(id);
            if(file.isEmpty()){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File is Empty");
            }else {

               File saveFile = new ClassPathResource("static/image").getFile();
               Path path  = Paths.get(saveFile.getAbsolutePath()+ File.separator+ file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image uploaded");
            }
            imageModel.setCustomer(customer);
            this.customerRepository.save(customers);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

