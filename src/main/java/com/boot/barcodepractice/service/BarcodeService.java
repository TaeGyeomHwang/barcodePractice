package com.boot.barcodepractice.service;

import com.boot.barcodepractice.entity.Barcode;
import com.boot.barcodepractice.repository.BarcodeRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BarcodeService {

    private final BarcodeRepository barcodeRepository;

    public byte[] generateBarcode(String text) throws Exception {
        int width = 300;
        int height = 150;
        int textHeight = 30;

        // 바코드 생성
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
        BufferedImage barcodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        // 텍스트가 들어갈 공간을 가진 새로운 이미지 생성
        BufferedImage combinedImage = new BufferedImage(width, height + textHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = combinedImage.createGraphics();

        // 바코드 그리기
        g2d.drawImage(barcodeImage, 0, 0, null);

        // 바코드 아래에 텍스트 그리기
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        g2d.drawString(text, (width - textWidth) / 2, height + fontMetrics.getAscent());

        g2d.dispose();

        // 이미지를 byte 배열로 변환
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(combinedImage, "png", baos);
        return baos.toByteArray();
    }

    public List<byte[]> generateQRCodes(List<String> texts) throws Exception {
        List<byte[]> qrCodes = new ArrayList<>();
        for (String text : texts) {
            qrCodes.add(generateBarcode(text));
        }
        return qrCodes;
    }

    public Barcode saveBarcode(Barcode barcode) {
        return barcodeRepository.save(barcode);
    }

    public List<Barcode> findAll() {
        return barcodeRepository.findAll();
    }
}
