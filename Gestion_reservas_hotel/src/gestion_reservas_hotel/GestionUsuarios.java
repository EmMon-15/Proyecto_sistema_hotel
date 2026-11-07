package gestion_reservas_hotel;
import java.util.ArrayList;

public class GestionUsuarios {
    private static ArrayList<Usuario> datosUsuario = new ArrayList<>();
    private static ArrayList<Cliente> datosCliente = new ArrayList<>();
    private static ArrayList<Personal> datosPersonal = new ArrayList<>();
    
    private static boolean cedulaDisponible(String cedula) {
        if (cedula == null || !cedula.matches("\\d{10}") || cedula.equals(Usuario.SIN_CEDULA)) {
            System.out.println("Cedula no valida.");
            return false;
        }
        if (getUsuario(cedula) != null) {
            System.out.println("La cedula ya se encuentra registrada.");
            return false;
        }
        return true;
    }
    
    public static void crearUsuario(Personal personal, String cedula) {
        if (personal.getNivel() <= 1) {
            if (cedulaDisponible(cedula)) {
                Usuario usuarioNuevo = new Usuario(4);
                usuarioNuevo.setCedula(cedula);
                datosUsuario.add(usuarioNuevo);
            }
        } else {
            System.out.println("No se poseen los permisos suficientes");
        }
    }
    
    public static void crearCliente(Personal personal, String cedula, String nombre) {
        if (personal.getNivel() <= 2) {
            if (cedulaDisponible(cedula)) {
                Cliente clienteNuevo = new Cliente();
                clienteNuevo.setCedula(cedula);
                clienteNuevo.setNombre(nombre);
                agregarCliente(clienteNuevo);
                System.out.println("-- Cuenta creada.");
            }
        } else {
            System.out.println("No se poseen los permisos suficientes");
        }
    }
    
    // El nuevo empleado debe ser de menor jerarquia que quien lo crea.
    public static void crearPersonal(Personal personal, String cedula, String nombre, int nuevoNivel) {
        if (personal.getNivel() < Usuario.NIVEL_MAXIMO_PERSONAL && nuevoNivel > personal.getNivel() && nuevoNivel <= Usuario.NIVEL_MAXIMO_PERSONAL) {
            if (cedulaDisponible(cedula)) {
                Personal personalNuevo = new Personal(nuevoNivel);
                personalNuevo.setCedula(cedula);
                personalNuevo.setNombre(nombre);
                agregarPersonal(personalNuevo);
                System.out.println("-- Cuenta creada.");
            }
        } else {
            System.out.println("No se poseen los permisos suficientes o el nivel no es valido.");
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
    
    // Empleados: solo los de menor jerarquia. Clientes: personal de nivel 2 o superior.
    public static boolean puedeGestionar(Personal personal, Usuario objetivo) {
        if (objetivo == null || !personal.isActivo()) {
            return false;
        }
        if (objetivo instanceof Personal) {
            return personal.getNivel() < objetivo.getNivel();
        }
        return personal.getNivel() <= 2;
    }
    
    // Desactiva la cuenta si esta activa, o la reactiva si esta inactiva.
    public static void cambiarEstadoUsuario(Personal personal, String cedula, String razon) {
        Usuario usuario = getUsuario(cedula);
        if (usuario == null) {
            System.out.println("La cedula ingresada no existe en el sistema.");
            return;
        }
        if (!puedeGestionar(personal, usuario)) {
            System.out.println("No se posee los permisos necesarios.");
            return;
        }
        if (razon == null || razon.trim().isEmpty()) {
            System.out.println("Es necesario colocar una razon/nota.");
            return;
        }
        String accion = (usuario.isActivo())? "Cuenta desactivada: " : "Cuenta reactivada: ";
        usuario.setObservacion(accion + razon + " (por " + personal.getNombre() + ")" + DateFormat.formatoFecha());
        usuario.setActivo(!usuario.isActivo());
        System.out.println((usuario.isActivo())? "-- Cuenta reactivada." : "-- Cuenta desactivada.");
        GestionReservas.actualizarReservas();
    }
    
    public static void cambiarNivelPersonal(Personal personal, String cedula, int nuevoNivel) {
        Personal objetivo = getPersonal(cedula);
        if (objetivo == null) {
            System.out.println("La cedula ingresada no pertenece a un empleado.");
            return;
        }
        if (!puedeGestionar(personal, objetivo)) {
            System.out.println("No se posee los permisos necesarios.");
            return;
        }
        int anterior = objetivo.getNivel();
        objetivo.setNivel(personal, nuevoNivel);
        if (objetivo.getNivel() != anterior) {
            objetivo.setObservacion("Nivel cambiado de " + anterior + " a " + objetivo.getNivel() + " por " + personal.getNombre() + DateFormat.formatoFecha());
            System.out.println("-- Nivel actualizado.");
        }
    }
    
    public static void mostrarListaUsuarios(ArrayList<? extends Usuario> usuarios) {
        System.out.println("--- LISTA ---");
        if (usuarios.isEmpty()) {
            System.out.println("-- No hay usuarios para mostrar.");
        }
        int indice = 1;
        for (Usuario usuario : usuarios) {
            System.out.printf("%d. ", indice);
            usuario.mostrarResumen();
            indice ++;
        }
    }
    
    public static void mostrarDatosUsuarios(String cedulaBuscada) {
        Usuario usuarioEncontrado = getUsuario(cedulaBuscada);
        if (usuarioEncontrado != null && usuarioEncontrado.getNivel() == 4) {
            usuarioEncontrado.mostrarUsuario();
        } else {
            System.out.println("La cedula ingresada no existe en el sistema.");
        }
    }
    
    public static void mostrarDatosUsuario(String cedulaBuscada, Personal personal) {
        Usuario usuarioEncontrado = getUsuario(cedulaBuscada);
        if (usuarioEncontrado != null) {
            if (usuarioEncontrado.getNivel() > personal.getNivel()) {
                usuarioEncontrado.mostrarUsuario();
            } else {
                System.out.println("[Usuario protegido]");
            }
        } else {
            System.out.println("La cedula ingresada no existe en el sistema.");
        }
    }
    
    public static ArrayList<Usuario> getTodoUsuario() {
        ArrayList<Usuario> copia = new ArrayList<>(datosUsuario);
        return copia;
    }
    
    public static Usuario getUsuario(String cedula) {
        for (Usuario usuario : datosUsuario) {
            if (cedula.equals(usuario.getCedula())) {
                return usuario;
            }
        }
        return null;
    }
    
    public static ArrayList<Cliente> getTodoCliente() {
        ArrayList<Cliente> copia = new ArrayList<>(datosCliente);
        return copia;
    }
    
    public static Cliente getCliente(String cedula) {
        for (Cliente cliente : datosCliente) {
            if (cedula.equals(cliente.getCedula())) {
                return cliente;
            }
        }
        return null;
    }
    
    public static ArrayList<Personal> getTodoPersonal() {
        ArrayList<Personal> copia = new ArrayList<>(datosPersonal);
        return copia;
    }
    
    public static Personal getPersonal(String cedula) {
        for (Personal personal : datosPersonal) {
            if (cedula.equals(personal.getCedula())) {
                return personal;
            }
        }
        return null;
    }
    
    public static void mostrarTodosUsuarios(Personal personal, ArrayList<? extends Usuario> usuarios) {
        if (personal.getNivel() <= 3) {
            int indice = 0;
            for (Usuario usuario: usuarios) {
                System.out.printf("Usuario no. %d ==============\n", indice);
                mostrarDatosUsuario(usuario.getCedula(), personal);
                indice ++;
            }
        } else {
            System.out.println("No se poseen los permisos suficientes");
        }
    }
}
