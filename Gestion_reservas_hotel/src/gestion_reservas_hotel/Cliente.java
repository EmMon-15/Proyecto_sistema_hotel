package gestion_reservas_hotel;

import java.util.Scanner;

public class Cliente {
    Scanner sc = new Scanner(System.in);
    private String cedula;
    private String nombre;

    private void registrar() {
        System.out.println("\n--- REGISTRO DE CLIENTE ---");

        System.out.print("Ingrese su cédula (10 dígitos): ");
        cedula = sc.nextLine();
        while (cedula.length() != 10) {
            System.out.println("Cédula inválida, debe tener 10 dígitos.");
            System.out.print("Ingrese su cédula (10 dígitos): ");
            cedula = sc.nextLine();
        }

        System.out.print("Ingrese su nombre: ");
        nombre = sc.nextLine();
        while (nombre=="") {
            System.out.println("El nombre no puede estar vacío.");
            System.out.print("Ingrese su nombre: ");
            nombre = sc.nextLine();
        }

        System.out.println("¡Bienvenido/a, " + nombre + "!");
    }

    public void mostrarMenu() {
        registrar();

        int opcion;

        do {
            System.out.println("\n--- MENÚ CLIENTE ---");
            System.out.println("<1> Ver habitaciones disponibles");
            System.out.println("<2> Hacer una reserva");
            System.out.println("<3> Ver mis reservas");
            System.out.println("<4> Cancelar una reserva");
            System.out.println("<0> Volver");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("Habitaciones disponibles: (pendiente)");
                    break;
                case 2:
                    System.out.println("Hacer reserva (pendiente)");
                    break;
                case 3:
                    System.out.println("Mis reservas (pendiente)");
                    break;
                case 4:
                    System.out.println("Cancelar reserva (pendiente)");
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcion != 0);
    }
}
