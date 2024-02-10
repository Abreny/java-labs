package pro.abned.labs.barcode.barbecue;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import pro.abned.labs.barcode.BarcodeWriter;
import pro.abned.labs.barcode.WriterType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BarbecueBarcodeWriter extends BarcodeWriter {
    private static final Font BARCODE_TEXT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);

    public BarbecueBarcodeWriter(String fullPath, WriterType writerType) {
        super(fullPath, writerType);
    }

    public BarbecueBarcodeWriter(String fullPath) {
        this(fullPath, WriterType.PNG);
    }

    /**
     * <a href="https://barbecue.sourceforge.net/apidocs/net/sourceforge/barbecue/BarcodeFactory.html">All factory method</a>
     *
     * @param input The given ean
     */
    @Override
    protected BufferedImage _writeEan13(String input) {
        try {
            Barcode barcode = BarcodeFactory.createEAN13(input);
            barcode.setFont(BARCODE_TEXT_FONT);

            return BarcodeImageHandler.getImage(barcode);
        } catch (Exception e) {
            throw new IllegalArgumentException("barcode.generator.failed#" + e.getMessage());
        }
    }
}
