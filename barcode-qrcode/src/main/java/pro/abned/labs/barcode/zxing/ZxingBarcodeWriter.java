package pro.abned.labs.barcode.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;
import pro.abned.labs.barcode.BarcodeWriter;
import pro.abned.labs.barcode.WriterType;

import java.awt.image.BufferedImage;

public class ZxingBarcodeWriter extends BarcodeWriter {
    public ZxingBarcodeWriter(String fullPath, WriterType writerType) {
        super(fullPath, writerType);
    }

    public ZxingBarcodeWriter(String fullPath) {
        this(fullPath, WriterType.PNG);
    }

    @Override
    protected BufferedImage _writeEan13(String ean13) {
        try {
            EAN13Writer barcodeWriter = new EAN13Writer();
            BitMatrix bitMatrix = barcodeWriter.encode(ean13, BarcodeFormat.EAN_13, 300, 150);

            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (Exception e) {
            throw new IllegalArgumentException("barcode.generator.failed#" + e.getMessage());
        }
    }
}
