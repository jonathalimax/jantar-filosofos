package br.com.jantarfilosofos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Jonatha
 */
public class Design extends JPanel implements Runnable {

    String text = "STATUS";
    Thread sketch;

    private BufferedImage img[];
    public static Semaphore mutex = new Semaphore(1);
    public static Semaphore[] semaphores = new Semaphore[5];
    public static int[] status = new int[5];
    static Philosopher[] philosophers = new Philosopher[5];

    public Design(BufferedImage img[]) {
        this.setFocusable(true);
        this.setSize(680, 620);
        this.setBackground(Color.white);
        this.init();
        this.img = img;
    }

    public void init() {
        for (int i = 0; i < status.length; i++) {
            status[i] = 0;
        }
        if (sketch == null) {
            sketch = new Thread(this);
            sketch.start();
        }

        // Define a prioridade principal para este atual Thread
        Thread.currentThread().setPriority(1);

        // Inicializa todos filósofos
        philosophers[0] = new Philosopher("Filosofo 1", 0);
        philosophers[1] = new Philosopher("Filosofo 2", 1);
        philosophers[2] = new Philosopher("Filosofo 3", 2);
        philosophers[3] = new Philosopher("Filosofo 4", 3);
        philosophers[4] = new Philosopher("Filosofo 5", 4);

        // Inicializa todos semáforos
        semaphores[0] = new Semaphore(0);
        semaphores[1] = new Semaphore(0);
        semaphores[2] = new Semaphore(0);
        semaphores[3] = new Semaphore(0);
        semaphores[4] = new Semaphore(0);

        // Inicia a execução de todos filósofos
        philosophers[0].start();
        philosophers[1].start();
        philosophers[2].start();
        philosophers[3].start();
        philosophers[4].start();
    }

    // Método para desenhar os objetos
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g.create();

        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        // Cria um circulo
        g.drawOval(175, 140, 330, 300);

        //Desenhando filosofos
        g2.drawImage(img[0], 50, 230, null);
        g2.drawImage(img[1], 190, 440, null);
        g2.drawImage(img[2], 400, 440, null);
        g2.drawImage(img[3], 520, 230, null);
        g2.drawImage(img[4], 285, 15, null);

        //Desenhando pratos
        g2.drawImage(img[5], 180, 235, null);
        g2.drawImage(img[5], 230, 340, null);
        g2.drawImage(img[5], 370, 335, null);
        g2.drawImage(img[5], 405, 230, null);
        g2.drawImage(img[5], 295, 130, null);

        //Desenhando hashis
        g2.drawImage(img[6], 240, 205, null);
        g2.drawImage(img[7], 230, 330, null);
        g2.drawImage(img[8], 340, 370, null);
        g2.drawImage(img[9], 390, 205, null);
        g2.drawImage(img[10], 410, 320, null);

        for (int i = 0; i < 5; i++) {
            if (status[i] == 0) {
                g.setColor(Color.gray);
                text = "PENSANDO";
            }
            if (status[i] == 1) {
                g.setColor(Color.RED);
                text = "FAMINTO";
            }
            if (status[i] == 2) {
                g.setColor(Color.GREEN);
                text = "COMENDO";
            }

            g.drawString(text, (int) (200D - 250D * Math.cos(1.2566370614359172D * (double) i)) + 100, (int) (200D - 200D * Math.sin(1.2566370614359172D * (double) i)) + 120);
        }

        // ATIVA A SINCRONIA
        Toolkit.getDefaultToolkit()
                .sync();
        // PAUSA
        g.dispose();
    }

    public void run() {
        do {
            repaint();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException ex) {
                System.out.println("ERROR>" + ex.getMessage());
            }
        } while (true);
    }

    public void animacaoGarfo(int numGarfo1, int numGarfo2) {

    }
}
