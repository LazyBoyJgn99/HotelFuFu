package top.jglo.hotel.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ResizeImg {
    //改变图片大小
    //resizeImage(is, os, 10, "png");
    public OutputStream resizeImage(InputStream is, OutputStream os, String format) throws IOException {
        BufferedImage prevImage = ImageIO.read(is);
        int newWidth = 512;
        int newHeight = 512;
        BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.createGraphics();
        graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
        ImageIO.write(image, format, os);
        os.flush();
        is.close();
        os.close();
        return os;
    }
}
