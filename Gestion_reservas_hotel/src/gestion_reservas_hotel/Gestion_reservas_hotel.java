
package gestion_reservas_hotel;

import java.util.Scanner;

public class Gestion_reservas_hotel {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int seleccion;
        do {
            System.out.println("Ingrese tipo de usuario:\n<0> Salir del programa.\n<1> Usuario cliente.\n<2> Usuario personal.");
            seleccion = sc.nextInt(); sc.nextLine();

            switch (seleccion) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
            }
        } while (seleccion != 0);
    }
}
