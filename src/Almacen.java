import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Almacen {


    private Random rnd = new Random();
    private ArrayList<Producto>  products = new ArrayList<>();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final ReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = reentrantReadWriteLock.readLock();
    private final Lock writeLock = reentrantReadWriteLock.writeLock();

    public Almacen () {
        generateList();
    }

    public ArrayList<Producto> generateList() {
        int num;
        for (int i = 0; i < 5; i++) { //La lista tendrá 5 productos al principio
            num = rnd.nextInt(3)+1;
            Producto newProduct = new Producto(num);
            products.add(newProduct);
        }

        return products;
    }

    public int getStock(int id) {
        readLock.lock();
        try {
            return consultStock(id);
        } finally {
            readLock.unlock();
        }
    }

    public void addProduct(int id) {
        writeLock.lock();
        try {
            products.add(new Producto(id));
        } finally {
            writeLock.unlock();
        }
    }

    public int consultStock(int id) {
        int cont = 0;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                cont++;
            }
        }

        System.out.printf("%s - Stock of Product %s: %d\n",
                LocalTime.now().format(dateTimeFormatter), id, cont);
        return cont;
    }


}
