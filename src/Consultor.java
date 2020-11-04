import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Consultor implements Runnable {

    private Almacen almacen;
    private int id;
    private int num;
    private Random rnd = new Random();

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    public Consultor(Almacen almacen, int id) {
        this.almacen = almacen;
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                num = rnd.nextInt(3)+1; //va a consultar el producto con el id de este random
                System.out.printf("%s - %s consulting stock of Product %d...\n",
                        LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName(), num);
                almacen.getStock(num);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.printf("%s - %s has been interrupted while consulting Product %d\n",
                        LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName(), num);
            }
        }
    }

}
