
package gestion_reservas_hotel;

public class Usuario {
    private String nombre;
    private String cedula;
    private boolean activo;
    private String observacion;
    
    public Usuario () {
        this.nombre = null;
        this.cedula = "0000000000";
        this.activo = true;
        this.observacion = "Cuenta creada:" + DateFormat.formatoFecha();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean setCedula(String cedula) {
        boolean validador = false;
        if (cedula.matches("\\d+") && cedula.length() == 10) {
            this.cedula = cedula;
            validador = true;
        } else {
           System.out.println("Cedula no valida.");
        }
        return validador;
    }
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion += " # " + observacion;
    }
    
    public void mostrarUsuario() {
        String confirmacion = (isActivo())? "Y": "N";
        System.out.println("-------------------------");
        System.out.printf("\tNombre: %s\n", getNombre());
        System.out.printf("\tCedula: %s\n", getCedula());
        System.out.printf("\tCuenta activa: %s\n", confirmacion);
        System.out.printf("Notas referentes al usuario:\n%s\n", getObservacion());
        System.out.println("-------------------------");
    }
}
