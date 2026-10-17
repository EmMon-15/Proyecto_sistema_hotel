
package gestion_reservas_hotel;

import java.util.Scanner;

public class Gestion_reservas_hotel {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();
        int seleccion;
        do {
            System.out.println("\n=== SISTEMA DE RESERVAS DEL HOTEL ===");
            System.out.println("Ingrese tipo de usuario:\n<1> Usuario cliente.\n<2> Usuario personal.\n<3> Salir del programa.");
            seleccion = sc.nextInt(); sc.nextLine();

            switch (seleccion) {
                case 3: 
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                    break;
                case 1:
                    Cliente cliente = new Cliente(hotel, sc);
                    cliente.mostrarMenu();
                    break;
                case 2:
                    
                    break;
            }
        } while (seleccion != 3);
    }
}
class Hotel {

    public void mostrarHabitaciones(boolean soloDisponibles) {
        if (soloDisponibles) {
            System.out.println("Habitaciones disponibles: (pendiente)");
        } else {
            System.out.println("Todas las habitaciones: (pendiente)");
        }
    }
}
