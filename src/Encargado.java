import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Encargado implements Runnable {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private Almacen almacen;
    private int num;
    private Random rnd = new Random();

    public Encargado(Almacen almacen) {
        this.almacen = almacen;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                num = rnd.nextInt(3)+1; //va a añadir un nuevo producto con el id de este random
                System.out.printf("%s - %s is adding a new Product %d\n",
                        LocalTime.now().format(dateTimeFormatter),Thread.currentThread().getName(), num);
                almacen.addProduct(num);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.printf("%s - %s has been interrupted while adding Product %d\n",
                        LocalTime.now().format(dateTimeFormatter),Thread.currentThread().getName(), num);
            }
        }

    }

}
