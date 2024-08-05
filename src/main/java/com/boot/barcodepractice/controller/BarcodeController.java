package com.boot.barcodepractice.controller;

import com.boot.barcodepractice.service.BarcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/addBarcode")
    public String addBarcode() {
        return "barcode/addBarcode";
    }


    @PostMapping("/generateQRCodes")
    @ResponseBody
    public ResponseEntity<?> generateQRCodes(@RequestBody List<String> barcodes) {
        try {
            List<byte[]> qrCodes = barcodeService.generateQRCodes(barcodes);
            return ResponseEntity.ok(qrCodes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error generating QR codes");
        }
    }

    @GetMapping("/displayQRCodes")
    public String displayQRCodes(Model model, @RequestParam("qrCodes") List<String> qrCodes) {
        model.addAttribute("qrCodes", qrCodes);
        return "barcode/displayQRCodes";
    }
}
