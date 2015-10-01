package excepciones.io.texto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AverageFileCalculator {
    public final static double average(String file) throws FileParsingException, BigNumberException {
        double contador = 0d;
        double suma = 0d;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = reader.readLine()) != null) {
                try {
                    int numero = Integer.parseInt(line);
                    if (numero > 1000)
                        throw new BigNumberException("Número més gran de 1000 a la línia " + (int) (contador+1));
                    suma = numero + suma;
                    contador++;
                } catch (NumberFormatException e) {
                    throw new FileParsingException(e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new FileParsingException(e.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("No puc tancar el fitxer");
            }
        }

        return suma / contador;
    }
}