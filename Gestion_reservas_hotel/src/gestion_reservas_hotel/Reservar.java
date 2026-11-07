package gestion_reservas_hotel;

public interface Reservar {
    public abstract void reservarHabitacion(int habitacion, int dias);
    
    public abstract void cancelarHabitacion(int habitacion, String razon);
}
