package pro.abned.labs.barcode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

abstract public class BarcodeWriter {
    private final String path;
    private final WriterType writerType;

    protected BarcodeWriter(String fullPath, WriterType writerType) {
        this.path = fullPath;
        this.writerType = writerType;
    }

    protected BarcodeWriter(String fullPath) {
        this(fullPath, WriterType.PNG);
    }

    final public void writeEan13(String input) {
        BufferedImage image = _writeEan13(input);
        _writeToFile(image);
    }

    private void _writeToFile(BufferedImage image) {
        File outputfile = new File(path);
        try {
            ImageIO.write(image, writerType.name().toLowerCase(), outputfile);
        } catch (IOException e) {
            throw new IllegalArgumentException("barcode.write.failed#" + e.getMessage());
        }
    }

    abstract protected BufferedImage _writeEan13(String ean13);
}
