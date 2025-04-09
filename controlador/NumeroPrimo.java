package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NumeroPrimo {
    private int[] numeros;
    private int[] numerosPrimos;
    private int cantidadNumeros;
    private int cantidadPrimos;

    public NumeroPrimo() {
        // No inicializamos los arreglos hasta saber el tamaño exacto
        this.cantidadNumeros = 0;
        this.cantidadPrimos = 0;
    }

    public boolean cargarDatos(String rutaArchivo) {
        try {
            // Primero contamos cuántas líneas tiene el archivo
            long lineasTotales = Files.lines(Paths.get(rutaArchivo)).count();
            this.numeros = new int[(int) lineasTotales];
            
            // Ahora leemos y guardamos cada número
            try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
                String linea;
                cantidadNumeros = 0;
                
                while ((linea = br.readLine()) != null) {
                    try {
                        numeros[cantidadNumeros] = Integer.parseInt(linea.trim());
                        cantidadNumeros++;
                    } catch (NumberFormatException e) {
                        System.err.println("Error al convertir a número: " + linea);
                    }
                }
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return false;
        }
    }

    private boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void procesarNumerosPrimos() {
        // Primero contamos cuántos números primos hay
        cantidadPrimos = 0;
        for (int i = 0; i < cantidadNumeros; i++) {
            if (esPrimo(numeros[i])) {
                cantidadPrimos++;
            }
        }
        
        // Creamos el arreglo con el tamaño exacto
        numerosPrimos = new int[cantidadPrimos];
        
        // Llenamos el arreglo con los números primos
        int indice = 0;
        for (int i = 0; i < cantidadNumeros; i++) {
            if (esPrimo(numeros[i])) {
                numerosPrimos[indice] = numeros[i];
                indice++;
            }
        }
    }

    public int getCantidadPrimos() {
        return cantidadPrimos;
    }
    
    public int getCantidadNumeros() {
        return cantidadNumeros;
    }
    
    public int[] getNumerosPrimos() {
        return numerosPrimos;
    }
}