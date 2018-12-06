import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.util.Random;

public class createImage {

    public createImage(){}
    public int[][] recorder;

    public void ImageTset() throws Exception {

        File f1 = new File("C:\\Users\\liugo\\Desktop\\1.JPG");
        File f2 = new File("C:\\Users\\liugo\\Desktop\\2.JPG");
        File f3 = new File("C:\\Users\\liugo\\Desktop\\3.JPG");
        File f4 = new File("C:\\Users\\liugo\\Desktop\\4.JPG");

        Image src1 = javax.imageio.ImageIO.read(f1);
        Image src2 = javax.imageio.ImageIO.read(f2);
        Image src3 = javax.imageio.ImageIO.read(f3);
        Image src4 = javax.imageio.ImageIO.read(f4);

        int width = 1000;
        int height = 1000;

        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        FileOutputStream out = new FileOutputStream("treasureMap.jpg");

        Graphics g = tag.createGraphics();

        Random rand = new Random();
        recorder = new int[400][2];
        for(int i = 0; i < 400; i++){
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            int imageNo = rand.nextInt(4);
            if(imageNo == 0){
                g.drawImage(src1, x, y, ((BufferedImage) src1).getWidth(null), ((BufferedImage) src1).getHeight(null), null);
            }

            else if(imageNo == 1)
                g.drawImage(src2, x, y, ((BufferedImage) src2).getWidth(null), ((BufferedImage) src2).getHeight(null), null);
            else if(imageNo == 2)
                g.drawImage(src3, x, y, ((BufferedImage) src3).getWidth(null), ((BufferedImage) src3).getHeight(null), null);
            else if(imageNo == 3)
                g.drawImage(src4, x, y, ((BufferedImage) src4).getWidth(null), ((BufferedImage) src4).getHeight(null), null);
        }
        g.dispose();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(tag);
        out.close();
    }

    public static void main(String[] args) {
        try {
            createImage test = new createImage();
            test.ImageTset();
        } catch (Exception e) {
            System.out.print(e);
        }
    }


}
