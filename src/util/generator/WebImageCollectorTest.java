package util.generator;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Tester for WebImageCollector, displays
 * search on JFrame.
 * 
 * @author maddiebriere
 *
 */

public class WebImageCollectorTest {
	
    public static void main(String avg[]) throws IOException
    {
        new WebImageCollectorTest();
    }

    public WebImageCollectorTest() throws IOException
    {
        BufferedImage img= WebImageCollector.findAndSaveRandomIcon(new Random(), "bomb");
        ImageIcon icon=new ImageIcon(img);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(200,300);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
