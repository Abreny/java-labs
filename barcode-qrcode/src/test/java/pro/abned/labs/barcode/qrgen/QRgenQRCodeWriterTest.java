package pro.abned.labs.barcode.qrgen;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

class QRgenQRCodeWriterTest {
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
        QRgenQRCodeWriter barcodeWriter = new QRgenQRCodeWriter(fullPath);
        barcodeWriter.write("123456789012"); // ean13 is a 12 length numeric strings
        assertTrue(new File(fullPath).isFile());
    }

    @Test
    void testWriteToFileZoneStr() {
        final String filename = getFilename();
        File file = new File("target", "generated-codes");
        if (!file.isDirectory()) {
            assertTrue(file.mkdirs());
        }
        String fullPath = String.format("%s%s%s.png", file.getAbsolutePath(), File.separator, filename);
        QRgenQRCodeWriter barcodeWriter = new QRgenQRCodeWriter(fullPath);
        barcodeWriter.write("Z123.R123.C124"); // zone.row.col
        assertTrue(new File(fullPath).isFile());
    }
}