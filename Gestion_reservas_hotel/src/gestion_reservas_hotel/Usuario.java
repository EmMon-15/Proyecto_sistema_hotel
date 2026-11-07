package gestion_reservas_hotel;

public class Usuario {
    public static final String SIN_CEDULA = "0000000000";
    public static final int NIVEL_MAXIMO_PERSONAL = 3;
    
    private String nombre;
    private String cedula;
    private boolean activo;
    private String observacion;
    private int nivel;
    
    public Usuario (int nivel) {
        this.nombre = null;
        this.cedula = SIN_CEDULA;
        this.activo = true;
        this.observacion = "Cuenta creada:" + DateFormat.formatoFecha();
        this.nivel = nivel;
    }
    
    public int getNivel() {
        return this.nivel;
    }

    // Solo un usuario de mayor jerarquia puede cambiar el nivel, y solo a uno de menor jerarquia que el suyo.
    public void setNivel(Personal admin, int nuevoNivel) {
        if (admin.getNivel() >= this.nivel) {
            System.out.println("No se poseen los permisos necesarios.");
            return;
        }
        if (nuevoNivel <= admin.getNivel() || nuevoNivel > NIVEL_MAXIMO_PERSONAL) {
            System.out.printf("El nuevo nivel debe estar entre %d y %d.\n", admin.getNivel() + 1, NIVEL_MAXIMO_PERSONAL);
            return;
        }
        this.nivel = nuevoNivel;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getCedula() {
        return this.cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(String cedula) {
        if (cedula != null && cedula.matches("\\d+") && cedula.length() == 10) {
            this.cedula = cedula;
        } else {
           System.out.println("Cedula no valida.");
        }
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
    
    // Datos adicionales que cada tipo de usuario agrega a su ficha.
    protected void mostrarDetalleExtra() {
    }
    
    // Ficha completa (con notas) para el personal.
    public void mostrarFicha() {
        String confirmacion = (isActivo())? "Y": "N";
        System.out.println("-------------------------");
        System.out.printf("\tNombre: %s\n", getNombre());
        System.out.printf("\tCedula: %s\n", getCedula());
        System.out.printf("\tNivel de acceso: %d\n", getNivel());
        System.out.printf("\tCuenta activa: %s\n", confirmacion);
        mostrarDetalleExtra();
        System.out.printf("Notas referentes al usuario:\n%s\n", getObservacion());
        System.out.println("-------------------------");
    }
    
    // Una sola linea para mostrar en listas.
    public void mostrarResumen() {
        String confirmacion = (isActivo())? "Y": "N";
        System.out.printf("%s | Cedula: %s | Nivel: %d | Cuenta activa: %s\n", getNombre(), getCedula(), getNivel(), confirmacion);
    }
}
