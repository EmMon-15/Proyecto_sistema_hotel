
package gestion_reservas_hotel;

public class Usuario {
    private String nombre;
    private String cedula;
    
    public Usuario () {
        setNombre();
        setCedula();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setNombre() {
        this.nombre = Input.str(40, true);
    }

    public boolean setCedula() {
        boolean validado = true;
        do {
            String cedula = Input.str(10, true);
            if (cedula.matches("\\d+") && cedula.length() == 10) {
                this.cedula = cedula;
                return validado;
            } else {
                System.out.println("Formato no valido.");
                System.out.println("Inserte un numero de cedula valido o Inserte <0> para volver.");
            }
        } while (!cedula.equals("0"));
        return !validado;
    }
    
}
