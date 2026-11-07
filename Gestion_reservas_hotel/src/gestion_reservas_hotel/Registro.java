package gestion_reservas_hotel;
import java.util.Calendar;

public class Registro {
    
    private static int numRegistro = 0;
    private int idRegistro;
    
    private String cedulaRegistrado;
    private String cedulaProcesante;
    private String nota;
    private int numHabitacion;
    private int dias;
    private Calendar fechaRegistro;
    private Calendar fechaMaxima;
    private boolean estadoReserva;
    
    public Registro(String registrado, String procesante, String nota, int habitacionReservada, int diasInstancia) {
        numRegistro ++;
        idRegistro = numRegistro;
        cedulaRegistrado = registrado;
        cedulaProcesante = procesante;
        numHabitacion = habitacionReservada;
        dias = diasInstancia;
        fechaRegistro = Calendar.getInstance();
        this.nota = nota + DateFormat.formatoFecha(fechaRegistro);
        fechaMaxima = (Calendar) fechaRegistro.clone();
        fechaMaxima.add(Calendar.DAY_OF_MONTH, diasInstancia);
        fechaMaxima.set(Calendar.HOUR_OF_DAY, 9); fechaMaxima.set(Calendar.MINUTE, 0); fechaMaxima.set(Calendar.SECOND, 0); fechaMaxima.set(Calendar.MILLISECOND, 0);   
        estadoReserva = true;
    }
    
    public int getNumRegistro() {
        return idRegistro;
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
    
    public int getDias() {
        return dias;
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
    
    // Una reserva activa expira si su fecha vencio o si el cliente fue desactivado.
    public boolean haExpirado() {
        if (!estadoReserva) {
            return false;
        }
        if (!DateFormat.compararFecha(fechaMaxima)) {
            return true;
        }
        Cliente cliente = GestionUsuarios.getCliente(cedulaRegistrado);
        return cliente != null && !cliente.isActivo();
    }
    
    public void mostrarRegistro() {
        String registrado = (getEstadoReserva())? "Valido" : "No valido";
        System.out.printf("Registro <%d> ------------------------------------\n", getNumRegistro());
        System.out.printf("\tCedula de usuario registrado: %s\n", getCedulaRegistrado());
        System.out.printf("\tHabitacion reservada: %d\n", getNumHabitacion());
        System.out.printf("\tFecha de reserva:%s\n", DateFormat.formatoFecha(getFechaRegistro()));
        System.out.printf("\tFecha de fin de reserva: %s\n", DateFormat.formatoFecha(getFechaMaxima()));
        System.out.printf("\tEstado de reserva: %s\n", registrado);
        System.out.printf("--------------------------------------------------\n");
    }
    
    private void mostrarUsuarioRegistro(String cedula, Personal personal) {
        if (cedula.equals(Usuario.SIN_CEDULA)) {
            System.out.println("\t\tNinguno");
        } else {
            GestionUsuarios.mostrarDatosUsuario(cedula, personal);
        }
    }
    
    public void mostrarRegistroPersonal(Personal personal) {
        String registrado = (getEstadoReserva())? "Valido" : "No valido";
        System.out.printf("Registro <%d> ------------------------------------\n", getNumRegistro());
        System.out.println("\tUsuario registrado: ");
        mostrarUsuarioRegistro(getCedulaRegistrado(), personal);
        System.out.println("\tCreador del registro: ");
        mostrarUsuarioRegistro(getCedulaProcesante(), personal);
        System.out.printf("\tHabitacion reservada: %d\n", getNumHabitacion());
        System.out.printf("\tFecha de reserva:%s\n", DateFormat.formatoFecha(getFechaRegistro()));
        System.out.printf("\tFecha de fin de reserva: %s\n", DateFormat.formatoFecha(getFechaMaxima()));
        System.out.printf("\tEstado de reserva: %s\n", registrado);
        System.out.printf("\tNotas: %s\n", getNota());
        System.out.printf("--------------------------------------------------\n");
    }
}
