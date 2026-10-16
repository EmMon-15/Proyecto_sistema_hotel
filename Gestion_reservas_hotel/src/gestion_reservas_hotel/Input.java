
package gestion_reservas_hotel;
import java.util.Scanner;

public class Input {
    static Scanner sc = new Scanner(System.in);
    
    public static String str(){
        String cadena = sc.nextLine();
        return cadena;
    }
    
    public static String str(int max){
        String cadena;
        do {
            cadena = sc.nextLine();
            if (cadena.length() <= max) {
                return cadena;
            } else {
                System.out.printf("Cantidad de caracteres introducidos ha superado el limite (%d caracter/es).", max);
                System.out.print(max);
            }
        } while (true);
    }
}
