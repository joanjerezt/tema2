package excepciones.io.binario;

/*Implementar una clase que tenga tres atributos: uno de tipo entero (int), otro decimal (double) y uno
de tipo carácter (char). Sobre esta clase implementar:
1. un método que tomando como parámetro de entrada el nombre de un fichero escriba en él en
formato binario el valor de los tres atributos en el orden en el que se han enumerado.
2. Un método de clase que tomando como parámetro de entrada el nombre de un fichero que
ha sido generado como salida del método anterior instancie un objeto de la clase y lo
devuelva.
3. Cualquier excepción que se produzca en ambos métodos no se tratará.
Implementar un programa que compruebe el correcto funcionamiento de los dos métodos descritos.
*/

import java.io.IOException;

public class AppIoBinary {
    public static void main(String[] args) throws IOException {
        BinaryIOClass testWrite = new BinaryIOClass(3, 7.5, 'a');
        testWrite.saveToFile(args[0]);
        String nombrefichero = args[0];

        BinaryIOClass testRead = BinaryIOClass.readFromFile(nombrefichero);
        System.out.println("integer = " + testRead.getInteger());
        System.out.println("decimal = " + testRead.getDecimal());
        System.out.println("char = " + testRead.getCharacter());

        // File file = new File(nombrefichero);
        // file.delete();
    }
}
