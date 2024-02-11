package pro.abned.labs.barcode.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import org.junit.jupiter.api.Test;
import pro.abned.labs.barcode.QRCodeWriter;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZxingQRCodeReaderTest {
    private static String getFilename() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyMMddHHmmssS");
        return simpleDateFormat.format(new Date());
    }

    @Test
    void testReadFromStream() {
        InputStream in = getClass().getClassLoader().getResourceAsStream("examples/qr_code_1.png");
        ZxingQRCodeReader reader = new ZxingQRCodeReader();
        Result result = reader.readFromStream(in);
        assertEquals(BarcodeFormat.QR_CODE, result.getBarcodeFormat());
        assertEquals("1234567890ABCDEF", result.getText());
    }

    @Test
    void testReadISBNFromStream() {
        InputStream in = getClass().getClassLoader().getResourceAsStream("examples/isbn_1.gif");
        ZxingQRCodeReader reader = new ZxingQRCodeReader();
        Result result = reader.readFromStream(in);
        assertEquals(BarcodeFormat.EAN_13, result.getBarcodeFormat());
        assertEquals("9780123456786", result.getText());
    }

    @Test
    void testReadBarcodeFromStream() {
        InputStream in = getClass().getClassLoader().getResourceAsStream("examples/barcode_1.png");
        ZxingQRCodeReader reader = new ZxingQRCodeReader();
        Result result = reader.readFromStream(in);
        assertEquals(BarcodeFormat.CODE_128, result.getBarcodeFormat());
        assertEquals("00123456789101112133", result.getText());
    }

    @Test
    void testReadGenerateBarcode() {
        final String filename = getFilename();
        File file = new File("target", "generated-codes");
        if (!file.isDirectory()) {
            assertTrue(file.mkdirs());
        }
        String fullPath = String.format("%s%s%s.png", file.getAbsolutePath(), File.separator, filename);
        QRCodeWriter writer = new ZxingQRCodeWriter(fullPath);
        writer.write("Z123.R123.C124");
        ZxingQRCodeReader reader = new ZxingQRCodeReader();
        Result result = reader.readFromFile(fullPath);
        assertEquals(BarcodeFormat.QR_CODE, result.getBarcodeFormat());
        assertEquals("Z123.R123.C124", result.getText());
    }
}
