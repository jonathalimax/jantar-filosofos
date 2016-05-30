package br.com.jantarfilosofos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author Jonatha
 */
public class JantarDosFilosofos extends JFrame {
    
    private static BufferedImage img;
    
    public JantarDosFilosofos(BufferedImage img[]) {
        add(new Design(img));
        setTitle("Jantar dos Filósofos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(680, 620);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        try {
            BufferedImage img[] = new BufferedImage[11];
            img[0] = ImageIO.read(JantarDosFilosofos.class.getResourceAsStream("fi1.png"));
            img[1] = ImageIO.read(JantarDosFilosofos.class.getResourceAsStream("fi2.png"));
            img[2] = ImageIO.read(JantarDosFilosofos.class.getResourceAsStream("fi3.png"));
            img[3] = ImageIO.read(JantarDosFilosofos.class.getResourceAsStream("fi4.png"));
            img[4] = ImageIO.read(JantarDosFilosofos.class.getResourceAsStream("fi5.png"));
            img[5] = ImageIO.read(JantarDosFilosofos.class.getResourceAsStream("spageti.png"));
            img[6] = ImageIO.read(JantarDosFilosofos.class.getResourceAsStream("hashi1.png"));
            img[7] = ImageIO.read(JantarDosFilosofos.class.getResourceAsStream("hashi2.png"));
            img[8] = ImageIO.read(JantarDosFilosofos.class.getResourceAsStream("hashi3.png"));
            img[9] = ImageIO.read(JantarDosFilosofos.class.getResourceAsStream("hashi2.png"));
            img[10] = ImageIO.read(JantarDosFilosofos.class.getResourceAsStream("hashi1.png"));
            new JantarDosFilosofos(img);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }
}
