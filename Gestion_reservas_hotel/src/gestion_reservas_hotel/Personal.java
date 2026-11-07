package gestion_reservas_hotel;

public class Personal extends Usuario implements Reservar {
    private static final double PRECIO_NOCHE = 50;
    
    public Personal(int nivel) {
        super(nivel);
    }
    
    // Reserva sin cliente asociado.
    @Override
    public void reservarHabitacion(int habitacion, int dias) {
        if (dias > 0) {
            String razon = "Reservar Habitacion";
            if (GestionReservas.disponibilidad(habitacion)) {
                Registro nuevaReserva = new Registro(Usuario.SIN_CEDULA, getCedula(), razon, habitacion, dias);
                GestionReservas.guardarRegistro(nuevaReserva);
                GestionReservas.reservar(nuevaReserva);
            }
        } else {
            System.out.println("-- La cantidad de días debe de ser mayor o igual a 1.");
        }
    }
    
    // Reserva a nombre de un cliente existente.
    public void reservarHabitacion(String cedulaCliente, int habitacion, int dias) {
        Cliente cliente = GestionUsuarios.getCliente(cedulaCliente);
        if (cliente == null || !cliente.isActivo()) {
            System.out.println("-- El cliente no existe o esta inactivo.");
            return;
        }
        if (dias <= 0) {
            System.out.println("-- La cantidad de días debe de ser mayor o igual a 1.");
            return;
        }
        if (GestionReservas.disponibilidad(habitacion)) {
            Registro nuevaReserva = new Registro(cedulaCliente, getCedula(), "Reservar Habitacion", habitacion, dias);
            cliente.agregarRegistro(nuevaReserva);
            GestionReservas.guardarRegistro(nuevaReserva);
            GestionReservas.reservar(nuevaReserva);
            cliente.getMisCreditos().acumular_Puntos(dias * PRECIO_NOCHE);
        }
    }
    
    @Override
    public void cancelarHabitacion(int habitacion, String razon) {
        if (getNivel() > 2) {
            System.out.println("-- No se poseen los permisos necesarios.");
            return;
        }
        Habitacion encontrada = GestionReservas.getHabitacion(habitacion, GestionReservas.getListaHabitacion());
        if (encontrada != null && !encontrada.getEstadoHabitacion()) {
            String motivo = (razon != null)? razon : "Cancelada por el personal";
            GestionReservas.cancelar(habitacion, motivo, GestionReservas.getListaRegistro());
        } else {
            System.out.println("-- La habitacion no existe o no esta reservada.");
        }
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
