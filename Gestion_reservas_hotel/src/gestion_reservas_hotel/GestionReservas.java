
package gestion_reservas_hotel;
import java.util.ArrayList;

public class GestionReservas {
    private static ArrayList<Registro> procesosRegistro = new ArrayList<>();
    private static ArrayList<Habitacion> datosHabitacion = new ArrayList<>();
    
    public static void crearHabitacion() {
        Habitacion.aumentarHabitacion();
        Habitacion nuevaHabitacion = new Habitacion();
        guardarHabitacion(nuevaHabitacion);
    }
    
    public static void borrarHabitacion(int habitacion) {
        cancelar(habitacion);
        for (Registro registro: procesosRegistro) {
            if (habitacion == registro.getNumHabitacion()) {
                registro.inhabilitarReserva();
            }
        }
    }
    
    public static void actualizarReservas() {
        for (Registro registro: procesosRegistro) {
            if (registro.getEstadoReserva()) {
                registro.actualizarEstado();
                if (!registro.getEstadoReserva()) {
                    cancelar(registro.getNumHabitacion());
                }
            }
        }
    }
    
    public static void guardarHabitacion(Habitacion nuevaHabitacion) {
        datosHabitacion.add(nuevaHabitacion);
    }
    
    public static void guardarRegistro(Registro nuevoRegistro) {
        procesosRegistro.add(nuevoRegistro);
    }
    
    public static void reservar(Registro nuevoRegistro) {
        int habitacionReservada = nuevoRegistro.getNumHabitacion();
        String cedulaOcupante = nuevoRegistro.getCedulaRegistrado();
        for (Habitacion habitacionRevisada: datosHabitacion) {
            if (habitacionReservada == habitacionRevisada.getNumHabitacion() && habitacionRevisada.getEstadoHabitacion()) {
                habitacionRevisada.reservarEstaHabitacion();
                habitacionRevisada.setCedulaOcupante(cedulaOcupante);
            }
        }
    }
    
    public static void cancelar(int habitacion) {
        if (habitacion > 0) {
            Habitacion aCancelar = getHabitacion(habitacion);
            aCancelar.cancelarEstaHabitacion();
            aCancelar.setCedulaOcupante("0000000000");
        }
    }
    
    public static boolean disponibilidad(int habitacion) {
        if (habitacion <= Habitacion.getNumLlamadas() && Habitacion.getNumLlamadas() > 0) {
            for (Habitacion enSeleccion: datosHabitacion) {
                if (enSeleccion.getEstadoHabitacion() && habitacion == enSeleccion.getNumHabitacion()) {
                    return true;
                }
            }
            System.out.println("La habitacion esta ocupada.");
            return false;
        } else {
            System.out.println("Habitacion seleccionada no es valida.");
        }
        return false;
    }
    
    public static Habitacion getHabitacion(int numHabitacion) {
        Habitacion seleccion;
        if (numHabitacion <= Habitacion.getNumLlamadas() && Habitacion.getNumLlamadas() > 0) {
            for (Habitacion habitacionSeleccionada: datosHabitacion) {
                if (numHabitacion == habitacionSeleccionada.getNumHabitacion()) {
                    seleccion = habitacionSeleccionada;
                    return seleccion;
                }
            }
        } else {
            System.out.println("Habitacion seleccionada no valida");
        }
        return null;
    }
    
    public static Registro getRegistro(int numRegistro) {
        Registro seleccion;
        if (numRegistro <= procesosRegistro.size() && !procesosRegistro.isEmpty()) {
            for (Registro registroSeleccionado: procesosRegistro) {
                if (numRegistro == registroSeleccionado.getNumRegistro()) {
                    seleccion = registroSeleccionado;
                    return seleccion;
                }
            }
        } else {
            System.out.println("Habitacion seleccionada no valida");
        }
        return null;
    }
}
