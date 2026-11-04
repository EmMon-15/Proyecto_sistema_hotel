
package gestion_reservas_hotel;

public interface Reservar {
    public abstract Registro reservarHabitacion(int habitacion, int dia);
    
    public abstract int cancelarHabitacion(int habitacion, String razon);
}
