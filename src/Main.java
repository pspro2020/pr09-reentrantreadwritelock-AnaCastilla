public class Main {

    public static void main(String[] args) throws InterruptedException {

        Almacen almacen = new Almacen();
        Thread[] consultThreads= new Thread[3];
        Thread addProduct = new Thread(new Encargado(almacen), "Encargado");

        for (int i = 0; i < 3; i++) {
            consultThreads[i] = new Thread(new Consultor(almacen, i+1), "Consultor " + (i+1));
        }

        for (int i = 0; i < 3; i++) {
            consultThreads[i].start();
        }

        addProduct.start();

        Thread.sleep(5000);

        for (int i = 0; i < 3; i++) {
            consultThreads[i].interrupt();
        }

        addProduct.interrupt();
    }
}
