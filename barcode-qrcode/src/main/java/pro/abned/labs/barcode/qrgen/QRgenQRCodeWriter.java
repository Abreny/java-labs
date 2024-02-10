package pro.abned.labs.barcode.qrgen;

import net.glxn.qrgen.javase.QRCode;
import pro.abned.labs.barcode.WriterType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class QRgenQRCodeWriter extends pro.abned.labs.barcode.QRCodeWriter {
    public QRgenQRCodeWriter(String fullPath, WriterType writerType) {
        super(fullPath, writerType);
    }

    public QRgenQRCodeWriter(String fullPath) {
        this(fullPath, WriterType.PNG);
    }

    @Override
    protected BufferedImage _writeQrcode(String input) {
        try {
            ByteArrayOutputStream stream = QRCode
                    .from(input)
                    .withSize(200, 200)
                    .stream();
            ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

            return ImageIO.read(bis);
        } catch (Exception e) {
            throw new IllegalArgumentException("barcode.generator.failed#" + e.getMessage());
        }
    }
}
