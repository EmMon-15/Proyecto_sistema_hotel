
package gestion_reservas_hotel;
import java.util.Calendar;

public class Registro {
    
    private static int numRegistro = 0;
    private int idRegistro;
    
    private String cedulaRegistrado;
    private String cedulaProcesante;
    private String nota;
    private int numHabitacion;
    private Calendar fechaRegistro;
    private Calendar fechaMaxima;
    private boolean estadoReserva;
    
    public Registro(String registrado, String procesante, String nota, int habitacionReservada, int diasInstancia) {
        numRegistro ++;
        idRegistro = numRegistro;
        cedulaRegistrado = registrado;
        cedulaProcesante = procesante;
        numHabitacion = habitacionReservada;
        fechaRegistro = Calendar.getInstance();
        this.nota = nota + DateFormat.formatoFecha(fechaRegistro);
        fechaMaxima = (Calendar) fechaRegistro.clone();
        fechaMaxima.add(Calendar.DAY_OF_MONTH, diasInstancia);
        fechaMaxima.set(Calendar.HOUR_OF_DAY, 9); fechaMaxima.set(Calendar.MINUTE, 0); fechaMaxima.set(Calendar.SECOND, 0); fechaMaxima.set(Calendar.MILLISECOND, 0);   
        estadoReserva = true;
        //no olvidar añadir un actualizador en la base de datos para cambiar todos los estados de reserva
    }
    
    public int getNumRegistro() {
        return numRegistro;
    }

    public String getCedulaRegistrado() {
        return cedulaRegistrado;
    }

    public String getCedulaProcesante() {
        return cedulaProcesante;
    }

    public void setNota(String nota) {
        this.nota += "| " + nota + DateFormat.formatoFecha();
    }
    
    public String getNota() {
        return nota;
    }

    public int getNumHabitacion() {
        return numHabitacion;
    }

    public Calendar getFechaRegistro() {
        return fechaRegistro;
    }

    public Calendar getFechaMaxima() {
        return fechaMaxima;
    }
    
    public boolean getEstadoReserva() {
        return this.estadoReserva;
    }
    
    public void inhabilitarReserva() {
        estadoReserva = false;
    }
    
    public void actualizarEstado() {
        if (!DateFormat.compararFecha(fechaMaxima) && getEstadoReserva()) {
            estadoReserva = false;
        }
    }
    
    public void mostrarRegistro() {
        System.out.printf("Registro no.%d ------------------------------------\n", getNumRegistro());
        System.out.printf("\tCedula de usuario registrado: %s\n", getCedulaRegistrado());
        System.out.printf("\tHabitacion reservada: %d\n", getNumHabitacion());
        System.out.printf("\tFecha de registro:%s\n", DateFormat.formatoFecha(getFechaRegistro()));
        System.out.printf("\tFecha ", );
    }
}
