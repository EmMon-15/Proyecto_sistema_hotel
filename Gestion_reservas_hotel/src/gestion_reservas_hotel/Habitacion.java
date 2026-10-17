
package gestion_reservas_hotel;

public class Habitacion {
    private static int numLlamadas = 0;
    private int numHabitacion;
    private String[] cualidades;
    private boolean ocupado;
    
    public Habitacion() {
        this.numLlamadas ++;
        this.numHabitacion = numLlamadas;
    }
    
    public Boolean getEstadoHabitacion() {
        return this.ocupado;
    }
    
    private void reservarHabitacion() {
        this.ocupado = true;
    }
    
    public void cancelarHabitacion() {
        this.ocupado = false;
    }
    
    public void integrarCualidades(String[] cualidades) {
        int contador = 0;
        for (String etiqueta: cualidades) {
            this.cualidades[contador] = etiqueta;
            contador++;
        }
    }
}
