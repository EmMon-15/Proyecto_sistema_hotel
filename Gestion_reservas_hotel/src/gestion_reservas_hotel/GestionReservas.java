package gestion_reservas_hotel;
import java.util.ArrayList;

public class GestionReservas {
    private static ArrayList<Registro> procesosRegistro = new ArrayList<>();
    private static ArrayList<Habitacion> datosHabitacion = new ArrayList<>();
    
    private static Habitacion buscarHabitacion(int num) {
        for (Habitacion habitacion : datosHabitacion) {
            if (habitacion.getNumHabitacion() == num) {
                return habitacion;
            }
        }
        return null;
    }
    
    public static void crearHabitacion() {
        Habitacion.aumentarHabitacion();
        Habitacion nuevaHabitacion = new Habitacion();
        guardarHabitacion(nuevaHabitacion);
    }
    
    public static void borrarHabitacion(int habitacion) {
        Habitacion aBorrar = buscarHabitacion(habitacion);
        if (aBorrar == null) {
            System.out.println("-- Habitacion no valida.");
            return;
        }
        for (Registro registro : getListaRegistro()) {
            if (habitacion == registro.getNumHabitacion() && registro.getEstadoReserva()) {
                cancelarRegistro(registro, "Habitacion eliminada.");
            }
        }
        datosHabitacion.remove(aBorrar);
        System.out.println("-- Habitacion eliminada.");
    }
    
    public static void actualizarReservas() {
        for (Registro registro : getListaRegistro()) {
            if (registro.haExpirado()) {
                cancelarRegistro(registro, "Reserva expirada o cuenta desactivada.");
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
        Habitacion habitacion = buscarHabitacion(nuevoRegistro.getNumHabitacion());
        if (habitacion != null && habitacion.getEstadoHabitacion()) {
            habitacion.reservarEstaHabitacion();
            habitacion.setCedulaOcupante(nuevoRegistro.getCedulaRegistrado());
            System.out.println("-- Habitacion reservada.");
        }
    }
    
    // Cancela un registro especifico y libera su habitacion.
    public static void cancelarRegistro(Registro registro, String razon) {
        if (!registro.getEstadoReserva()) {
            return;
        }
        Habitacion habitacion = buscarHabitacion(registro.getNumHabitacion());
        if (habitacion != null) {
            habitacion.cancelarEstaHabitacion();
            habitacion.setCedulaOcupante(Usuario.SIN_CEDULA);
        }
        registro.setNota(razon);
        registro.inhabilitarReserva();
        System.out.println("-- Habitacion cancelada.");
    }
    
    // Cancela la reserva activa de una habitacion dentro de la lista de registros dada.
    public static void cancelar(int habitacion, String razon, ArrayList<Registro> registros) {
        if (buscarHabitacion(habitacion) == null) {
            System.out.println("-- Habitacion no valida.");
            return;
        }
        for (Registro registro : registros) {
            if (habitacion == registro.getNumHabitacion() && registro.getEstadoReserva()) {
                cancelarRegistro(registro, razon);
                return;
            }
        }
    }
    
    public static boolean disponibilidad(int habitacion) {
        Habitacion encontrada = buscarHabitacion(habitacion);
        if (encontrada == null) {
            System.out.println("-- Habitacion seleccionada no es valida.");
            return false;
        }
        if (!encontrada.getEstadoHabitacion()) {
            System.out.println("-- La habitacion esta ocupada.");
            return false;
        }
        return true;
    }
    
    public static Habitacion getHabitacion(int numHabitacion, ArrayList<Habitacion> habitaciones) {
        for (Habitacion habitacionSeleccionada: habitaciones) {
            if (numHabitacion == habitacionSeleccionada.getNumHabitacion()) {
                return habitacionSeleccionada;
            }
        }
        return null;
    }
    
    public static Registro getRegistro(int numRegistro, ArrayList<Registro> registros) {
        for (Registro registroSeleccionado: registros) {
            if (numRegistro == registroSeleccionado.getNumRegistro()) {
                return registroSeleccionado;
            }
        }
        return null;
    }
    
    public static ArrayList<Habitacion> getListaHabitacion() {
        ArrayList<Habitacion> copia = new ArrayList<>(datosHabitacion);
        return copia;
    }
    
    public static ArrayList<Habitacion> getListaHabitacionLibre() {
        ArrayList<Habitacion> libres = new ArrayList<>();
        for (Habitacion habitacion : datosHabitacion) {
            if (habitacion.getEstadoHabitacion()) {
                libres.add(habitacion);
            }
        }
        return libres;
    }
    
    public static ArrayList<Registro> getListaRegistro() {
        ArrayList<Registro> copia = new ArrayList<>(procesosRegistro);
        return copia;
    }
    //Mostrar habitaciones filtrados para Clientes
    public static void mostrarHabitacionesClientes(ArrayList<Habitacion> habitaciones) {
        System.out.println("--- LISTA DE HABITACIONES ---");
        if (habitaciones.isEmpty()) {
            System.out.println("-- No hay habitaciones para mostrar.");
        }
        for (Habitacion habitacion: habitaciones) {
            habitacion.mostrarHabitacion();
        }
    }
    //Mostrar habitaciones para Empleados
    public static void mostrarHabitacionesEmpleados(ArrayList<Habitacion> habitaciones) {
        System.out.println("--- LISTA DE TODAS LAS HABITACIONES Y SUS USUARIOS ---");
        if (habitaciones.isEmpty()) {
            System.out.println("-- No hay habitaciones para mostrar.");
        }
        for (Habitacion habitacion : habitaciones) {
            habitacion.mostrarTodaHabitacion();
        }
    }
    //Registros de reservas guardados para clientes y personal.
    public static void mostrarTodosRegistros(ArrayList<Registro> registros) {
        System.out.println("--- LISTA DE TODOS LOS REGISTROS ---");
        if (registros.isEmpty()) {
            System.out.println("-- No hay registros existentes.");
        }
        for (Registro registro : registros) {
            registro.mostrarRegistro();
        }
    }
    
    public static void mostrarRegistrosActivos(ArrayList<Registro> registros) {
        int contador = 0;
        System.out.println("--- LISTA DE RESERVAS ---");
        for (Registro registro : registros) {
            if (registro.getEstadoReserva()) {
                contador ++;
                registro.mostrarRegistro();
            }
        }
        if (contador == 0) {
            System.out.println("-- No hay reservas activas.");
        }
    }
    
    public static void mostrarTodosRegistrosPersonal(ArrayList<Registro> registros, Personal personal) {
        System.out.println("--- LISTA DE TODOS LOS REGISTROS ---");
        if (registros.isEmpty()) {
            System.out.println("-- No hay registros existentes.");
        }
        for (Registro registro : registros) {
            registro.mostrarRegistroPersonal(personal);
        }
    }
    
    public static void mostrarRegistrosActivosPersonal(ArrayList<Registro> registros, Personal personal) {
        int contador = 0;
        System.out.println("--- LISTA DE RESERVAS ---");
        for (Registro registro : registros) {
            if (registro.getEstadoReserva()) {
                contador ++;
                registro.mostrarRegistroPersonal(personal);
            }
        }
        if (contador == 0) {
            System.out.println("-- No hay reservas activas.");
        }
    }
}
