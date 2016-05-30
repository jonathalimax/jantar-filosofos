package br.com.jantarfilosofos;

/**
 *
 * @author Jonatha
 */
public class Philosopher extends Thread {

    private int ID;

    static final int THINKING = 0;
    static final int STARVING = 1;
    static final int EATING = 2;
    static int garfo[] = {1, 1, 1, 1, 1};

    public Philosopher(String name, int ID) {
        super(name);
        this.ID = ID;
    }

    public void Starving() {
        Design.status[this.ID] = 1;
        System.out.println("O Filósofo " + getName() + " está FAMINTO!");
    }

    public void Eat() {
        Design.status[this.ID] = 2;
        System.out.println("O Filósofo " + getName() + " está COMENDO!");
        garfo[NeighborsLeft()] = 0;
        garfo[NeighborsLeft()] = 0;
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException ex) {
            System.out.println("ERROR>" + ex.getMessage());
        }
        garfo[NeighborsLeft()] = 1;
        garfo[NeighborsLeft()] = 1;
    }

    public void Think() {
        Design.status[this.ID] = 0;
        System.out.println("O Filósofo " + getName() + " está PENSANDO!");

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException ex) {
            System.out.println("ERROR>" + ex.getMessage());
        }
    }

    public void PutFork() {
        Design.mutex.decrementar();
        Think();

        Design.philosophers[NeighborsLeft()].TryGetFork();
        Design.philosophers[NeighborsRight()].TryGetFork();

        Design.mutex.incrementar();
    }

    public void GetFork() {
        Design.mutex.decrementar();

        Starving();
        TryGetFork();

        Design.mutex.incrementar();
        Design.semaphores[this.ID].decrementar();
    }

    public void TryGetFork() {
        if (Design.status[this.ID] == 1
                && Design.status[NeighborsLeft()] != 2
                && Design.status[NeighborsRight()] != 2) {
            Eat();
            Design.semaphores[this.ID].incrementar();
        }

    }

    @Override
    public void run() {

        try {
            Think();

            do {
                GetFork();
                Thread.sleep(1000L);
                PutFork();
            } while (true);
        } catch (InterruptedException ex) {
            System.out.println("ERROR>" + ex.getMessage());
        }

    }

    public int NeighborsRight() {
        return (this.ID + 1) % 5;
    }

    public int NeighborsLeft() {
        if (this.ID == 0) {

            return 4;
        } else {

            return (this.ID - 1) % 5;
        }
    }

}
