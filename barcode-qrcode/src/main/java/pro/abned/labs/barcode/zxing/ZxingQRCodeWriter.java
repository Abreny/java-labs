package pro.abned.labs.barcode.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import pro.abned.labs.barcode.WriterType;

import java.awt.image.BufferedImage;

public class ZxingQRCodeWriter extends pro.abned.labs.barcode.QRCodeWriter {
    public ZxingQRCodeWriter(String fullPath, WriterType writerType) {
        super(fullPath, writerType);
    }

    public ZxingQRCodeWriter(String fullPath) {
        this(fullPath, WriterType.PNG);
    }

    @Override
    protected BufferedImage _writeQrcode(String ean13) {
        try {
            com.google.zxing.qrcode.QRCodeWriter barcodeWriter = new com.google.zxing.qrcode.QRCodeWriter();
            BitMatrix bitMatrix = barcodeWriter.encode(ean13, BarcodeFormat.QR_CODE, 200, 200);

            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (Exception e) {
            throw new IllegalArgumentException("barcode.generator.failed#" + e.getMessage());
        }
    }
}
