
package gestion_reservas_hotel;

public class Empleado {
    private String nombre;
    private String cedula;
    private boolean acceso;
    
    public Empleado(){
        this.nombre = null;
        this.cedula = null;
        acceso = true;
    }
    
    public void setNombre() {
        this.nombre = Input.str(40);
    }
    
    public void setCedula(String cedula) {
        this.cedula = Input.str(10, 10);
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public String getCedula() {
        return this.cedula;
    }
    
    
}
