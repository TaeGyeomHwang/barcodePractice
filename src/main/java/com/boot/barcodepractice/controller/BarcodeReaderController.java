package com.boot.barcodepractice.controller;

import com.boot.barcodepractice.entity.Barcode;
import com.boot.barcodepractice.service.BarcodeReaderService;
import com.boot.barcodepractice.service.BarcodeService;
import com.google.zxing.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BarcodeReaderController {

    private final BarcodeReaderService barcodeReaderService;
    private final BarcodeService barcodeService;

    @PostMapping("/readBarcode")
    public ResponseEntity<String> readBarcode(@RequestParam("file") MultipartFile file) {
        try {
            String barcodeText = barcodeReaderService.readBarcode(file);
            return ResponseEntity.ok(barcodeText);
        } catch (IOException | NotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/addBarcode")
    public ResponseEntity<Barcode> createBarcode(@RequestBody Barcode barcode) {
        System.out.println("입력한 값 = " + barcode.getName());
        Barcode savedBarcode = barcodeService.saveBarcode(barcode);
        return new ResponseEntity<>(savedBarcode, HttpStatus.CREATED);
    }

    @GetMapping("/getBarcodes")
    public List<Barcode> getAllBarcodes() {
        return barcodeService.findAll();
    }
}
