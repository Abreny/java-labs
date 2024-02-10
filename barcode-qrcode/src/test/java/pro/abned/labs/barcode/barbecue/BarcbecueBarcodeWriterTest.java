package pro.abned.labs.barcode.barbecue;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BarcbecueBarcodeWriterTest {
    private static String getFilename() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyMMddHHmmssS");
        return simpleDateFormat.format(new Date());
    }

    @Test
    void testWriteToFileEan13() {
        final String filename = getFilename();
        File file = new File("target", "generated-codes");
        if (!file.isDirectory()) {
            assertTrue(file.mkdirs());
        }
        String fullPath = String.format("%s%s%s.png", file.getAbsolutePath(), File.separator, filename);
        BarbecueBarcodeWriter barcodeWriter = new BarbecueBarcodeWriter(fullPath);
        barcodeWriter.writeEan13("123456789012"); // ean13 is a 12 length numeric strings
        assertTrue(new File(fullPath).isFile());
    }
}
