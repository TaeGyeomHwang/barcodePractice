package com.boot.barcodepractice.controller;

import com.boot.barcodepractice.service.BarcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BarcodeController {

    private final BarcodeService barcodeService;

    @GetMapping("/")
    public String inputBarcode() {
        return "barcode/barcodeInput";
    }

    @GetMapping("/generateBarcode")
    public ResponseEntity<ByteArrayResource> generateBarcode(@RequestParam String text) {
        try {
            byte[] barcode = barcodeService.generateBarcode(text);

            ByteArrayResource resource = new ByteArrayResource(barcode);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=barcode.png")
                    .contentType(MediaType.IMAGE_PNG)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
