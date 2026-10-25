
package gestion_reservas_hotel;

public class Empleado extends Usuario {
    
    private int nivel;
    
    public Empleado(){
        super();
        
    }
    
    public void setNivel() {
        do {
            int nivel;
        } while (nivel >= 0 && nivel <= 4);
    }
}
