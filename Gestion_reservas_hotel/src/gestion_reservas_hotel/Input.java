
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
                    System.out.printf("Cantidad de caracteres introducidos ha superado el limite (%d caracter/es).", limite);
                }
            } else {
                if (cadena.length() >= limite) {
                    return cadena;
                } else {
                    System.out.printf("Cantidad de caracteres introducidos debe ser mayor que %d caracter/es.", limite);
                }
            }
        } while (true);
    }
    
    public static String str(int min, int max){
        String cadena;
        cadena = sc.nextLine();
        if (cadena.length() < min) {
            System.out.printf("Cantidad de caracteres introducidos no supera el minimo (%d caracter/es).", min);
        } else if (cadena.length() > max) {
            System.out.printf("Cantidad de caracteres introducidos ha superado el maximo (%d caracter/es).", max);
        } else {
            return cadena;
        }
        return null;
    }
    
    public static int ent() {
        String strNumero;
        int entero;
        
        strNumero = sc.nextLine();
        try {
            entero = Integer.parseInt(strNumero);
            return entero;
        } catch (NumberFormatException e) {
            System.out.print("Formato incorrecto, necesita introducir un numero entero: ");
        }
        return 0;
    }
    
    public static double dec() {
        String strNumero;
        double decimal;
        strNumero = sc.nextLine();
        try {
            decimal = Double.parseDouble(strNumero);
            return decimal;
        } catch (NumberFormatException e) {
            System.out.print("Formato incorrecto, necesita introducir un numero decimal: ");
        }
         return 0;
    }
    // Usar al final del programa.
    public static void cerrarScanner() {
        sc.close();
    }
}
