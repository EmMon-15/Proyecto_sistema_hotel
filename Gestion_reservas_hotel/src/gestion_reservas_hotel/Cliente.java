package gestion_reservas_hotel;
import java.util.ArrayList;

public class Cliente extends Usuario implements Reservar {
    private static final double PRECIO_NOCHE = 50;
    
    private Creditos misCreditos;
    private ArrayList<Registro> registroUsuario = new ArrayList<>(); 
    
    public Cliente() {
        super(4);
        this.misCreditos = new Creditos(0, getCedula(), 0);
    }
    
    // Se actualiza la cedula de los creditos cuando se asigna la cedula real.
    @Override
    public void setCedula(String cedula) {
        super.setCedula(cedula);
        this.misCreditos = new Creditos(misCreditos.getSaldoPuntos(), getCedula(), 0);
    }
    
    public void agregarRegistro(Registro nuevoRegistro) {
        registroUsuario.add(nuevoRegistro);
    }

    public Creditos getMisCreditos() {
        return misCreditos;
    }

    public void setMisCreditos(Creditos misCreditos) {
        this.misCreditos = misCreditos;
    }

    @Override
    public void reservarHabitacion(int habitacion, int dias) {
        if (dias > 0) {
            String razon = "Reservar Habitacion";
            if (GestionReservas.disponibilidad(habitacion)) {
                Registro nuevaReserva = new Registro(getCedula(), getCedula(), razon, habitacion, dias);
                agregarRegistro(nuevaReserva);
                GestionReservas.guardarRegistro(nuevaReserva);
                GestionReservas.reservar(nuevaReserva);
                
                misCreditos.acumular_Puntos(dias * PRECIO_NOCHE);
            }
        } else {
            System.out.println("-- La cantidad de días debe de ser mayor o igual a 1.");
        }
    }
    
    @Override
    public void cancelarHabitacion(int habitacionSeleccionada, String razon) {
        String motivo = (razon != null)? razon : "Cancelada por el cliente";
        for (Registro registro: registroUsuario) {
            if (registro.getEstadoReserva() && registro.getNumHabitacion() == habitacionSeleccionada) {
                GestionReservas.cancelarRegistro(registro, motivo);
                misCreditos.restarPuntos((int) (registro.getDias() * PRECIO_NOCHE));
                return;
            }
        }
        System.out.println("-- No tienes una reserva activa en esa habitacion.");
    }
    
    @Override
    public void mostrarUsuario() {
        System.out.println("-------------------------");
        System.out.printf("\tNombre: %s\n", super.getNombre());
        System.out.printf("\tCedula: %s\n", super.getCedula());
        System.out.printf("\tCreditos: %d\n", misCreditos.getSaldoPuntos() );
        System.out.println("-------------------------");
    }
    
    @Override
    protected void mostrarDetalleExtra() {
        System.out.printf("\tCreditos: %d\n", misCreditos.getSaldoPuntos());
    }
    
    public ArrayList<Registro> getRegistrosCliente() {
        ArrayList<Registro> copia = new ArrayList<>(registroUsuario);
        return copia;
    }
}
