package com.boot.barcodepractice.controller;

import com.boot.barcodepractice.service.BarcodeReaderService;
import com.google.zxing.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class BarcodeReaderController {

    private final BarcodeReaderService barcodeReaderService;

    public BarcodeReaderController(BarcodeReaderService barcodeReaderService) {
        this.barcodeReaderService = barcodeReaderService;
    }

    @PostMapping("/readBarcode")
    public ResponseEntity<String> readBarcode(@RequestParam("file") MultipartFile file) {
        try {
            String barcodeText = barcodeReaderService.readBarcode(file);
            return ResponseEntity.ok(barcodeText);
        } catch (IOException | NotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
