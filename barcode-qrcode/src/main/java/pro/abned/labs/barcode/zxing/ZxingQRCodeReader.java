package pro.abned.labs.barcode.zxing;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import pro.abned.labs.barcode.QRCodeReader;

import java.awt.image.BufferedImage;

public class ZxingQRCodeReader extends QRCodeReader {
    @Override
    protected Result _readFromImage(BufferedImage image) {
        try {
            final BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            return new MultiFormatReader().decode(bitmap);
        } catch (NotFoundException e) {
            throw new IllegalArgumentException("qrcode.read.not_found#" + e.getMessage());
        }
    }
}
