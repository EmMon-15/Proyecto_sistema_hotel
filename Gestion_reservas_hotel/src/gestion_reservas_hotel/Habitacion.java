
package gestion_reservas_hotel;

public class Habitacion {
    private static int numLlamadas = 0;
    private int numHabitacion;
    private String tipo;
    private String desc;
    private boolean desocupado;
    private String cedulaOcupante;
    
    public Habitacion() {
        this.numHabitacion = this.numLlamadas;
        this.tipo = null;
        this.desocupado = true;
        cedulaOcupante = "0000000000";
    }
    
    public static void aumentarHabitacion() {
        numLlamadas ++;
    }
    
    public static int getNumLlamadas() {
        return numLlamadas;
    }
    
    public Boolean getEstadoHabitacion() {
        return this.desocupado;
    }
    
    public int getNumHabitacion() {
        return this.numHabitacion;
    }
    
    public void reservarEstaHabitacion() {
        this.desocupado = false;
    }
    
    public void cancelarEstaHabitacion() {
        this.desocupado = true;
    }
    
    public void ingresarTipo(String tipo, String desc) {
        this.tipo = tipo;
        this.desc = desc;
    }
    
    public String getTipo() {
        return this.tipo;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    public void setCedulaOcupante(String cedulaOcupante) {
        this.cedulaOcupante = cedulaOcupante;
    }
    
    public String getCedulaOcupante() {
        return this.cedulaOcupante;
    }
    
    public void mostrarHabitacion() {
        String estado = (getEstadoHabitacion())? "Desocupado" : "Ocupado";
        System.out.printf("Habitacion #%d ----------\n", getNumHabitacion());
        System.out.printf("\tTipo: %s\n", getTipo());
        System.out.printf("\tDescripcion: %s\n", getDesc());
        System.out.printf("\tEstado: %s\n", estado);
        System.out.println("-------------------------");
    }
    
    public void mostrarTodaHabitacion() {
        String estado = (getEstadoHabitacion())? "Desocupado" : "Ocupado";
        System.out.printf("Habitacion #%d ----------\n", getNumHabitacion());
        System.out.printf("\tTipo: %s\n", getTipo());
        System.out.printf("\tDescripcion: %s\n", getDesc());
        System.out.printf("\tEstado: %s\n", estado);
        GestionUsuarios.mostrarDatosUsuarios(cedulaOcupante);
        System.out.println("-------------------------");
    }
}
