
package gestion_reservas_hotel;
import java.util.ArrayList;

public class Cliente extends Usuario implements Reservar {
    private double creditos;
    private ArrayList<Registro> registroUsuario = new ArrayList<>(); 
    
    public Cliente() {
        super();
        this.creditos = 0;
    }
    
    public void agregarRegistro(Registro nuevoRegistro) {
        registroUsuario.add(nuevoRegistro);
    }

    public double getCreditos() {
        return creditos;
    }

    public void setCreditos(double creditos) {
        this.creditos = creditos;
    }

    @Override
    public Registro reservarHabitacion(int habitacion, int dias) {
        if (dias > 0) {
            String razon = "Reservar Habitacion";
            if (GestionReservas.disponibilidad(habitacion)) {
                Registro nuevaReserva = new Registro(getCedula(), getCedula(), razon, habitacion, dias);
                agregarRegistro(nuevaReserva);
                return nuevaReserva;
            }
        } else {
            System.out.println("La cantidad de días debe de ser mayor o igual a 1.");
        }
        return null;
    }
    
    @Override
    public int cancelarHabitacion(int habitacionSeleccionada, String razon) {
        for (Registro registroUsuario: registroUsuario) {
            if (!registroUsuario.getEstadoReserva() && registroUsuario.getNumHabitacion() == habitacionSeleccionada) {
                registroUsuario.setNota(razon);
                registroUsuario.inhabilitarReserva();
                return habitacionSeleccionada;
            } else {
                System.out.print("Habitacion no valida.");
            }
        }
        return -1;
    }
    
    @Override
    public void mostrarUsuario() {
        System.out.println("-------------------------");
        System.out.printf("\tNombre: %s\n", super.getNombre());
        System.out.printf("\tCedula: %s\n", super.getCedula());
        System.out.printf("\tCreditos: %2f\n", getCreditos());
        System.out.println("-------------------------");
    }
}
