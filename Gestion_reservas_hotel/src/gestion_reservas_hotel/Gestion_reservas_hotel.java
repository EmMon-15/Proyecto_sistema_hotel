
package gestion_reservas_hotel;

public class Gestion_reservas_hotel {

    public static void main(String[] args) {
        
        int seleccion;
        
        System.out.printf("%40s\n", "Habitaciones existentes");
        
        do {
            System.out.println("\n=== SISTEMA DE RESERVAS DEL HOTEL ===");
            System.out.println("Ingrese tipo de usuario:\n<1> Usuario cliente.\n<2> Usuario personal.\n<3> Salir del programa.");
            seleccion = Input.ent();

            switch (seleccion) {
                case 3: 
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                    break;
                case 1:
                    
                    break;
                case 2:
                    
                    break;
            }
        } while (seleccion != 3);
    }
}

