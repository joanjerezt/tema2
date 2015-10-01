package concurrencia.sincronismo;

/* Concurrencia. Sincronismo.
Implementar una clase Buffer con un atributo privado vector de caracteres de tamaño 32 (char[]).
La clase proporciona un método de lectura que devuelve el primer carácter no leído del vector,
dejando la posición que ocupaba el carácter libre para escritura. Para poder realizar la lectura en el
vector debe haber al menos un carácter legible. Asimismo, proporciona otro método de escritura que
escribe un carácter en la primera posición libre del vector. Para poder realizar la escritura en el
vector debe haber al menos una posición libre en el vector.
Por otro lado tendremos dos clases que se ejecutan como thread. La primera es un thread productor,
que escribe todos los caracteres de una cadena acabada en '\n' en una instancia de la clase Buffer, y
la segunda es un thread consumidor que lee los caracteres que se escriben en el buffer en el mismo
orden en el que fueron escritos y cuando el carácter es '\n' escribe por la salida estándar la cadena
que ha leído.
Implementar un programa que compruebe el correcto funcionamiento de los requisitos pedidos.
*/

public class AppSynchronization {
    public static void main(String[] args) {
        String line = "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor.\n";
        Buffer buffer = new Buffer();
        Thread producer = new Thread(new Producer(buffer, line), "producer");
        Thread consumer = new Thread(new Consumer(buffer), "consumer");

        consumer.start();
        producer.start();
    }
}
