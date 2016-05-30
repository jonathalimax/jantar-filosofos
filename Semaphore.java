package br.com.jantarfilosofos;

/**
 *
 * @author Jonatha
 */
public class Semaphore {

    protected int cont;

    public Semaphore() {
        this.cont = 0;
    }

    public Semaphore(int value) {
        this.cont = value;
    }

    public synchronized void decrementar() {
        while (this.cont == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {

                System.out.println("ERROR>" + ex.getMessage());
            }
        }

        this.cont--;
    }

    public synchronized void incrementar() {
        this.cont++;
        notify();
    }
}
