package gestion_reservas_hotel;
import java.util.Scanner;

public class Input {
    static Scanner sc = new Scanner(System.in);
    
    public static String str(){
        String cadena = sc.nextLine();
        return cadena;
    }
    
    public static String str(int limite, boolean maximo){
        String cadena;
        do {
            cadena = sc.nextLine();
            if (maximo) {
                if (cadena.length() <= limite) {
                    return cadena;
                } else {
                    System.out.printf("Cantidad de caracteres introducidos ha superado el limite (%d caracter/es).\n", limite);
                }
            } else {
                if (cadena.length() >= limite) {
                    return cadena;
                } else {
                    System.out.printf("Cantidad de caracteres introducidos debe ser mayor que %d caracter/es.\n", limite);
                }
            }
        } while (true);
    }
    
    // Devuelve null si el texto no cumple con el minimo o el maximo.
    public static String str(int min, int max){
        String cadena;
        cadena = sc.nextLine();
        if (cadena.length() < min) {
            System.out.printf("Cantidad de caracteres introducidos no supera el minimo (%d caracter/es).\n", min);
        } else if (cadena.length() > max) {
            System.out.printf("Cantidad de caracteres introducidos ha superado el maximo (%d caracter/es).\n", max);
        } else {
            return cadena;
        }
        return null;
    }
    
    // Devuelve -1 si el formato es incorrecto (0 se usa como opcion "regresar").
    public static int ent() {
        String strNumero;
        int entero;
        
        strNumero = sc.nextLine();
        try {
            entero = Integer.parseInt(strNumero.trim());
            return entero;
        } catch (NumberFormatException e) {
            System.out.println("Formato incorrecto, necesita introducir un numero entero.");
        }
        return -1;
    }
    
    public static double dec() {
        String strNumero;
        double decimal;
        strNumero = sc.nextLine();
        try {
            decimal = Double.parseDouble(strNumero.trim());
            return decimal;
        } catch (NumberFormatException e) {
            System.out.println("Formato incorrecto, necesita introducir un numero decimal.");
        }
        return -1;
    }
    // Usar al final del programa.
    public static void cerrarScanner() {
        sc.close();
    }
}
