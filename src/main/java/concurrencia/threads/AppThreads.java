package concurrencia.threads;

/*Crear dos clases que se ejecuten como threads y cuya tarea sea escribir 10 veces por la salida
estándar su nombre junto con el tiempo en milisegundos transcurridos desde la última vez que
hicieron la escritura y el número de escrituras que ha realizado. Cada vez que el thread escriba un
mensaje se dormirá un tiempo aleatorio entre 0 y 2 segundos. Una de las clases debe heredar de
Thread y la otra implementar la interfaz Runnable.
Implementar un programa que arranque dos threads de cada uno de los tipos anteriores. Cuando los
dos threads hayan finalizado, este programa escribirá en la salida estándar: “Se acabó”.
*/

public class AppThreads {
    public static void main(String[] args) throws InterruptedException {
        ThreadClass threadClass = new ThreadClass("thread class");
        Thread thread = new Thread(new RunnableClass(), "runnable class");
        threadClass.start();
        thread.start();

        threadClass.join();
        thread.join();

        System.out.println("Se acabó");
    }
}
