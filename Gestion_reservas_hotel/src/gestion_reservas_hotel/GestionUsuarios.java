
package gestion_reservas_hotel;
import java.util.ArrayList;

public class GestionUsuarios {
    private static ArrayList<Usuario> datosUsuario = new ArrayList<>();
    private static ArrayList<Cliente> datosCliente = new ArrayList<>();
    private static ArrayList<Personal> datosPersonal = new ArrayList<>();
    
    public void crearUsuario(Personal personal, String cedula) {
        int nivel = personal.getNivel();
        if (nivel <= 1) {
            Usuario usuarioNuevo = new Usuario();
            usuarioNuevo.setCedula(cedula);
            datosUsuario.add(usuarioNuevo);
        } else {
            System.out.println("No se poseen los permisos suficientes");
        }
    }
    
    public void crearCliente(Personal personal, String cedula) {
        int nivel = personal.getNivel();
        if (nivel <= 2) {
            Cliente clienteNuevo = new Cliente();
            clienteNuevo.setCedula(cedula);
            agregarCliente(clienteNuevo);
        } else {
            System.out.println("No se poseen los permisos suficientes");
        }
    }
    
    public void crearPersonal(Personal personal, String cedula) {
        int nivel = personal.getNivel();
        if (nivel <= 1) {
            Personal personalNuevo = new Personal();
            personalNuevo.setCedula(cedula);
            agregarPersonal(personalNuevo);
        } else {
            System.out.println("No se poseen los permisos suficientes");
        }
    }
    
    public static void agregarCliente(Cliente nuevoCliente) {
        datosUsuario.add(nuevoCliente);
        datosCliente.add(nuevoCliente);
    }
    
    public static void agregarPersonal(Personal nuevoPersonal) {
        datosUsuario.add(nuevoPersonal);
        datosPersonal.add(nuevoPersonal);
    }
    
    public static int compararCedula(String cedula) {
        int indicePersona = -1; //-1 Si no existe; si existe, será un valor >= 0;
        
        for (Usuario usuarioRevisado: datosUsuario) {
            if (cedula.equals(usuarioRevisado.getCedula())) {
                indicePersona = datosUsuario.indexOf(cedula);
            }
        }
        return indicePersona;
    }
    
    public static void gestionarCuentaAdmin(Personal personal, String cedula, String observacion) {
        if (personal.getNivel() <= 1 && personal.isActivo()) {
            int indiceCedula = compararCedula(cedula);
            if (indiceCedula >= 0) {
                if (observacion != null) {
                    Usuario cuentaGestionada = datosUsuario.get(indiceCedula);
                    cuentaGestionada.setObservacion(observacion + DateFormat.formatoFecha());
                    cuentaGestionada.setActivo(!cuentaGestionada.isActivo());
                } else {
                    System.out.println("Es necesario colocar una razon/nota.");
                }
            } else {
                System.out.println("La cedula ingresada no existe en el sistema.");
            }
        } else {
            System.out.println("No se posee los permisos necesarios.");
        }
    }
    
    public static void mostrarDatosUsuarios(String cedulaBuscada) {
        int indiceUsuario = compararCedula(cedulaBuscada);
        if (indiceUsuario != -1) {
            Usuario usuarioEncontrado = datosUsuario.get(indiceUsuario);
            usuarioEncontrado.mostrarUsuario();
        } else {
            System.out.println("La cedula ingresada no existe en el sistema.");
        }
    }
}
