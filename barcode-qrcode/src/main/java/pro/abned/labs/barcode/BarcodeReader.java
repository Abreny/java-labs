package pro.abned.labs.barcode;

import com.google.zxing.Result;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public abstract class BarcodeReader {
    abstract protected Result _readFromImage(BufferedImage image);

    final public Result readFromFile(String path) {
        return _readFromImage(_readImageFromFile(path));
    }

    final public Result readFromStream(InputStream input) {
        return _readFromImage(_readImageFromFile(input));
    }

    protected BufferedImage _readImageFromFile(String path) {
        File imageFile = new File(path);
        try {
            return ImageIO.read(imageFile);
        } catch (IOException e) {
            throw new IllegalArgumentException("barcode.file_to_image.failed#" + e.getMessage());
        }
    }

    protected BufferedImage _readImageFromFile(InputStream in) {
        try {
            return ImageIO.read(in);
        } catch (IOException e) {
            throw new IllegalArgumentException("barcode.file_to_image.failed#" + e.getMessage());
        }
    }
}
