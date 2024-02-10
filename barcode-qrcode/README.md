# DESCRIPTION
[Barcodes](https://www.baeldung.com/cs/barcodes) encode information such as product numbers, serial numbers, and batch numbers. Also, they enable parties like retailers, manufacturers, and transport providers to track assets through the entire supply chain.

[QR Codes](https://www.baeldung.com/cs/qr-codes) are becoming the most widely recognized 2D barcodes worldwide. The big benefit of the QR code is that we can store large amounts of data in a limited spac

# LIBRARIES
[Barbecue](https://barbecue.sourceforge.net/) is an open-source Java library that supports an extensive set of 1D barcode formats. Also, the barcodes can be output to PNG, GIF, JPEG, and SVG.

[Barcode4j](https://barcode4j.sourceforge.net/) is also an open-source library. In addition, it offers 2D barcode formats – like DataMatrix and PDF417 – and more output formats. The PDF417 format is available in both libraries. But, unlike Barcode4j, Barbecue considers it a linear barcode.

[ZXing](https://github.com/zxing/zxing) (“zebra crossing”) is an open-source, multi-format 1D/2D barcode image processing library implemented in Java, with ports to other languages. This is the main library that supports QR codes in Java.

[QRGen](https://github.com/kenglxn/QRGen) library offers a simple QRCode generation API built on top of ZXing. It provides separate modules for Java and Android.

# EXAMPLE OF USAGE
1. [ ] UPC (universal product code)
2. [ ] EAN (european article number, variation: EAN-13, EAN-8, JAN-13 and ISN)
3. [ ] Code 128 (It can encode all 128 characters of ASCII, and its length is variable)
4. [ ] PDF417 (We might expect to find it on a variety of applications such as travel (boarding passes), identification cards, and inventory management)
5. [ ] QRCode