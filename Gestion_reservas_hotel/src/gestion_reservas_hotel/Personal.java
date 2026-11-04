
package gestion_reservas_hotel;

public class Personal extends Usuario implements Reservar {
    int nivel;
    
    public Personal() {
        super();
        this.nivel = 0;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(Personal admin, int nuevoNivel) {
        if (admin.getNivel() <= 1) {
            if (nuevoNivel >= 1) {
                this.nivel = nuevoNivel;
            } else {
                System.out.println("El nuevo nivel debe de ser mayor a 0.");
            }
        } else {
            System.out.print("No se poseen los permisos necesarios.");
        }
    }
    
    @Override
    public Registro reservarHabitacion(int habitacion, int dias) {
        if (dias > 0) {
            String razon = "Reservar Habitacion";
            if (GestionReservas.disponibilidad(habitacion)) {
                Registro nuevaReserva = new Registro("0000000000", getCedula(), razon, habitacion, dias);
                GestionReservas.guardarRegistro(nuevaReserva);
                return nuevaReserva;
            }
        } else {
            
        }
        return null;
    }
    
    public Registro reservarHabitacion(String cedulaCliente, int habitacion, int dias) {
        if (dias > 0) {
            String razon = "Reservar Habitacion";
            if (GestionReservas.disponibilidad(habitacion)) {
                Registro nuevaReserva = new Registro(cedulaCliente, getCedula(), razon, habitacion, dias);
                GestionReservas.guardarRegistro(nuevaReserva);
                return nuevaReserva;
            }
        } else {
            System.out.println("La cantidad de días debe de ser mayor o igual a 1.");
        }
        return null;
    }
    
    @Override
    public int cancelarHabitacion(int habitacion, String razon) {
        if (nivel <= 2) {
            if (!GestionReservas.disponibilidad(habitacion)) {
                return habitacion;
            } else {
                System.out.println("La habitacion no esta ocupada.");
            }
        } else {
            System.out.println("No se poseen los permisos necesarios.");
        }
        return -1;
    }
    
    @Override
    public void mostrarUsuario() {
        String confirmacion = (super.isActivo())? "Y": "N";
        System.out.println("-------------------------");
        System.out.printf("\tNombre: %s\n", super.getNombre());
        System.out.printf("\tCedula: %s\n", super.getCedula());
        System.out.printf("\tNivel de acceso: %d\n", getNivel());
        System.out.printf("\tCuenta activa: %s\n", confirmacion);
        System.out.printf("Notas referentes al Personal:\n%s\n", super.getObservacion());
        System.out.println("-------------------------");
    }
}
