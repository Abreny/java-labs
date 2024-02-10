package pro.abned.labs.barcode.barcode4j;

import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import pro.abned.labs.barcode.BarcodeWriter;
import pro.abned.labs.barcode.WriterType;

import java.awt.image.BufferedImage;

public class Barcode4JBarcodeWriter extends BarcodeWriter {
    public Barcode4JBarcodeWriter(String fullPath, WriterType writerType) {
        super(fullPath, writerType);
    }

    public Barcode4JBarcodeWriter(String fullPath) {
        this(fullPath, WriterType.PNG);
    }

    @Override
    protected BufferedImage _writeEan13(String ean13) {
        try {
            EAN13Bean barcodeGenerator = new EAN13Bean();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);

            barcodeGenerator.generateBarcode(canvas, ean13);
            return canvas.getBufferedImage();
        } catch (Exception e) {
            throw new IllegalArgumentException("barcode.generator.failed#" + e.getMessage());
        }
    }
}
