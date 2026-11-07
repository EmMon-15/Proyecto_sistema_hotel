package gestion_reservas_hotel;

import java.util.ArrayList;

public class Gestion_reservas_hotel {

    // Pide un texto hasta que cumpla con el minimo y el maximo de caracteres.
    private static String pedirTexto(String mensaje, int min, int max) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = Input.str(min, max);
        } while (texto == null);
        return texto;
    }
    
    private static String pedirNombre() {
        return pedirTexto("Ingresa el nombre: ", 1, 40);
    }

    public static void main(String[] args) {
        Personal admin = new Personal(0);
        admin.setNombre("Administrador");
        admin.setCedula("1000000000");
        GestionUsuarios.agregarPersonal(admin);
        
        for (int i = 0; i < 10; i++) {
            GestionReservas.crearHabitacion();
        }
        for (Habitacion h : GestionReservas.getListaHabitacion()) {
            h.ingresarTipo("Estandar", "Habitacion con cama doble y bano privado");
        }
        
        int seleccion;
        int seleccion0;
        int seleccion1;
        int seleccion2;
        int seleccion3;
        int seleccion4;
        int seleccion5;
        int seleccionP;
        int seleccionP1;
        int seleccionP2;
        int seleccionP3;
        int seleccionP4;
        int seleccionP5;
        String campo;
        
        do {
            
            seleccion = 0;
            System.out.println("\n=== SISTEMA DE RESERVAS DEL HOTEL ===");
            System.out.println("\tIngrese tipo de usuario:\n<1> Usuario cliente.\n<2> Usuario personal.\n<3> Salir del programa.");
            seleccion = Input.ent();

            switch (seleccion) {
                case 3: 
                    
                    System.out.println("-- Gracias por usar el sistema. Hasta luego!");
                    break;
                    
                case 1:
                    
                    do {
                        seleccion0 = 0;
                        System.out.println("\tIngrese opcion:\n<1> Iniciar sesion.\n<2> Crear cuenta.\n<3> Regresar.");
                        seleccion0 = Input.ent();
                        
                        switch (seleccion0) {
                            case 3:
                                break;
                            case 1:
                                
                                Cliente actual;
                                campo = null;
                                System.out.print("Ingrese su cedula: ");
                                campo = Input.str();
                                
                                if (GestionUsuarios.getCliente(campo) != null && GestionUsuarios.getCliente(campo).isActivo()) {

                                    actual = GestionUsuarios.getCliente(campo);
                                    GestionReservas.actualizarReservas();
                                    System.out.printf("\n===== Bienvenido, %s =====\n", actual.getNombre());

                                    do {

                                        seleccion1 = 0;
                                        System.out.println("\tIngrese opcion:\n<1> Ver habitaciones disponibles.\n<2> Ver mis reservas.\n<3> Cancelar reservas.\n<4> Ver informacion de cuenta.\n<5> Regresar.");
                                        seleccion1 = Input.ent();

                                        switch (seleccion1) {
                                            case 5:
                                                break;
                                            case 1:

                                                GestionReservas.actualizarReservas();
                                                GestionReservas.mostrarHabitacionesClientes(GestionReservas.getListaHabitacionLibre());
                                                seleccion2 = 0;
                                                System.out.print("Elija el numero de habitacion (elija 0 para regresar): ");
                                                seleccion2 = Input.ent();

                                                if (seleccion2 != 0 && GestionReservas.disponibilidad(seleccion2)) {
                                                    int habitacion = seleccion2;
                                                    
                                                    seleccion3 = 0;
                                                    System.out.print("Escriba la cantidad de dias: ");
                                                    seleccion3 = Input.ent();

                                                    actual.reservarHabitacion(habitacion, seleccion3);
                                                }

                                                break;
                                            case 2:

                                                GestionReservas.mostrarRegistrosActivos(actual.getRegistrosCliente());

                                                break;
                                            case 3:

                                                seleccion4 = 0;
                                                System.out.print("Elija el numero de habitacion a cancelar (elija 0 para regresar): ");
                                                seleccion4 = Input.ent();

                                                if (seleccion4 != 0) {
                                                    actual.cancelarHabitacion(seleccion4, null);
                                                }

                                                break;
                                            case 4:

                                                do {
                                                    seleccion5 = 0;
                                                    System.out.println("\tIngrese opcion:\n<1> Visualizar perfil.\n<2> Visualizar todos los registros de reservas.\n<3> Desactivar cuenta.\n<4> Regresar.");
                                                    seleccion5 = Input.ent();

                                                    switch (seleccion5) {
                                                        case 4:
                                                            break;
                                                        case 1:
                                                            actual.mostrarUsuario();
                                                            break;
                                                        case 2:
                                                            GestionReservas.mostrarTodosRegistros(actual.getRegistrosCliente());
                                                            break;
                                                        case 3:
                                                            campo = null;
                                                            System.out.print("Escribe tu numero de cedula para confirmar: ");
                                                            campo = Input.str();

                                                            if (campo.equals(actual.getCedula())) {
                                                                actual.setActivo(false);
                                                                campo = null;
                                                                System.out.print("Escriba una razon del por que: ");
                                                                campo = Input.str();
                                                                actual.setObservacion(campo);

                                                                GestionReservas.actualizarReservas();
                                                                System.out.println("-- Cuenta desactivada.");
                                                            } else {
                                                                System.out.println("-- No es posible hacer dicha accion.");
                                                            }

                                                            break;
                                                        default:
                                                            System.out.println("-- Respuesta invalida, ingrese nuevamente.");
                                                            break;
                                                    }

                                                } while (seleccion5 != 4 && actual.isActivo());

                                                break;
                                            default:
                                                System.out.println("-- Respuesta invalida, ingrese nuevamente.");
                                                break;
                                        }

                                    } while (seleccion1 != 5 && actual.isActivo());

                                } else {
                                    System.out.println("-- La cuenta no es valida.");
                                }
                                break;
                                
                            case 2:
                                
                                campo = null;
                                System.out.print("Ingrese su cedula: ");
                                campo = Input.str();
                                
                                if (campo.matches("\\d+") && campo.length() == 10 && !campo.equals(Usuario.SIN_CEDULA)) {
                                    if(GestionUsuarios.getUsuario(campo) == null) {
                                    
                                        String cedula = campo;
                                        String nombre = pedirNombre();

                                        Cliente nuevoCliente = new Cliente();
                                        nuevoCliente.setCedula(cedula);
                                        nuevoCliente.setNombre(nombre);

                                        GestionUsuarios.agregarCliente(nuevoCliente);
                                        System.out.println("-- Cuenta creada.");
                                    } else {
                                        System.out.println("-- No valido.");
                                    }
                                } else {
                                    System.out.println("-- Formato no valido.");
                                }
                                
                                break;
                            default:
                                System.out.println("-- Respuesta invalida, ingrese nuevamente.");
                                break;
                        }
                    } while (seleccion0 != 3);
                        
                    break;
                case 2:
                    
                    Personal actualP;
                    campo = null;
                    System.out.print("Ingrese su cedula: ");
                    campo = Input.str();
                    
                    actualP = GestionUsuarios.getPersonal(campo);
                    
                    if (actualP == null) {
                        System.out.println("-- La cedula no pertenece a ningun usuario personal.");
                    } else if (!actualP.isActivo()) {
                        System.out.println("-- La cuenta se encuentra desactivada.");
                    } else {
                        
                        GestionReservas.actualizarReservas();
                        System.out.printf("\n===== Bienvenido, %s =====\n", actualP.getNombre());
                        
                        do {
                            
                            seleccionP = 0;
                            System.out.println("\tIngrese opcion:\n<1> Habitaciones.\n<2> Clientes.\n<3> Empleados.\n<4> Reservas.\n<5> Regresar.");
                            seleccionP = Input.ent();
                            
                            switch (seleccionP) {
                                case 5:
                                    break;
                                case 1:
                                    
                                    do {
                                        seleccionP1 = 0;
                                        System.out.println("\tIngrese opcion:\n<1> Ver lista de habitaciones.\n<2> Ver una habitacion.\n<3> Crear habitacion.\n<4> Eliminar habitacion.\n<5> Cancelar reserva de una habitacion.\n<6> Regresar.");
                                        seleccionP1 = Input.ent();
                                        
                                        switch (seleccionP1) {
                                            case 6:
                                                break;
                                            case 1:
                                                GestionReservas.actualizarReservas();
                                                GestionReservas.mostrarHabitacionesEmpleados(GestionReservas.getListaHabitacion());
                                                break;
                                            case 2:
                                                seleccion2 = 0;
                                                System.out.print("Elija el numero de habitacion (elija 0 para regresar): ");
                                                seleccion2 = Input.ent();
                                                
                                                if (seleccion2 != 0) {
                                                    GestionReservas.actualizarReservas();
                                                    Habitacion elegida = GestionReservas.getHabitacion(seleccion2, GestionReservas.getListaHabitacion());
                                                    
                                                    if (elegida != null) {
                                                        elegida.mostrarTodaHabitacion();
                                                    } else {
                                                        System.out.println("-- Habitacion seleccionada no es valida.");
                                                    }
                                                }
                                                break;
                                            case 3:
                                                if (actualP.getNivel() <= 2) {
                                                    GestionReservas.crearHabitacion();
                                                    ArrayList<Habitacion> todas = GestionReservas.getListaHabitacion();
                                                    Habitacion nueva = todas.get(todas.size() - 1);
                                                    
                                                    String tipo = pedirTexto("Escriba el tipo de habitacion: ", 1, 40);
                                                    String desc = pedirTexto("Escriba la descripcion: ", 1, 100);
                                                    nueva.ingresarTipo(tipo, desc);
                                                    
                                                    System.out.printf("-- Habitacion #%d creada.\n", nueva.getNumHabitacion());
                                                } else {
                                                    System.out.println("-- No se poseen los permisos necesarios.");
                                                }
                                                break;
                                            case 4:
                                                if (actualP.getNivel() <= 2) {
                                                    seleccion2 = 0;
                                                    System.out.print("Elija el numero de habitacion a eliminar (elija 0 para regresar): ");
                                                    seleccion2 = Input.ent();
                                                    
                                                    if (seleccion2 != 0) {
                                                        GestionReservas.borrarHabitacion(seleccion2);
                                                    }
                                                } else {
                                                    System.out.println("-- No se poseen los permisos necesarios.");
                                                }
                                                break;
                                            case 5:
                                                seleccion4 = 0;
                                                System.out.print("Elija el numero de habitacion a cancelar (elija 0 para regresar): ");
                                                seleccion4 = Input.ent();
                                                
                                                if (seleccion4 != 0) {
                                                    campo = pedirTexto("Escriba una razon del por que: ", 1, 100);
                                                    actualP.cancelarHabitacion(seleccion4, campo);
                                                }
                                                break;
                                            default:
                                                System.out.println("-- Respuesta invalida, ingrese nuevamente.");
                                                break;
                                        }
                                        
                                    } while (seleccionP1 != 6);
                                    
                                    break;
                                case 2:
                                    
                                    do {
                                        seleccionP2 = 0;
                                        System.out.println("\tIngrese opcion:\n<1> Ver lista de clientes.\n<2> Ver un cliente.\n<3> Crear cliente.\n<4> Desactivar o reactivar cliente.\n<5> Regresar.");
                                        seleccionP2 = Input.ent();
                                        
                                        switch (seleccionP2) {
                                            case 5:
                                                break;
                                            case 1:
                                                GestionUsuarios.mostrarListaUsuarios(GestionUsuarios.getTodoCliente());
                                                break;
                                            case 2:
                                                campo = null;
                                                System.out.print("Ingrese la cedula del cliente: ");
                                                campo = Input.str();
                                                
                                                Cliente revisado = GestionUsuarios.getCliente(campo);
                                                if (revisado != null) {
                                                    revisado.mostrarFicha();
                                                    GestionReservas.mostrarTodosRegistrosPersonal(revisado.getRegistrosCliente(), actualP);
                                                } else {
                                                    System.out.println("-- La cedula ingresada no pertenece a un cliente.");
                                                }
                                                break;
                                            case 3:
                                                if (actualP.getNivel() <= 2) {
                                                    campo = null;
                                                    System.out.print("Ingrese la cedula del cliente: ");
                                                    campo = Input.str();
                                                    
                                                    if (campo.matches("\\d+") && campo.length() == 10 && !campo.equals(Usuario.SIN_CEDULA) && GestionUsuarios.getUsuario(campo) == null) {
                                                        String cedula = campo;
                                                        String nombre = pedirNombre();
                                                        
                                                        GestionUsuarios.crearCliente(actualP, cedula, nombre);
                                                    } else {
                                                        System.out.println("-- Cedula no valida o ya registrada.");
                                                    }
                                                } else {
                                                    System.out.println("-- No se poseen los permisos necesarios.");
                                                }
                                                break;
                                            case 4:
                                                campo = null;
                                                System.out.print("Ingrese la cedula del cliente: ");
                                                campo = Input.str();
                                                
                                                Cliente gestionado = GestionUsuarios.getCliente(campo);
                                                if (gestionado == null) {
                                                    System.out.println("-- La cedula ingresada no pertenece a un cliente.");
                                                } else if (!GestionUsuarios.puedeGestionar(actualP, gestionado)) {
                                                    System.out.println("-- No se poseen los permisos necesarios.");
                                                } else {
                                                    String accion = (gestionado.isActivo())? "desactivar" : "reactivar";
                                                    String razon = pedirTexto("Escriba una razon para " + accion + " la cuenta: ", 1, 100);
                                                    GestionUsuarios.cambiarEstadoUsuario(actualP, gestionado.getCedula(), razon);
                                                }
                                                break;
                                            default:
                                                System.out.println("-- Respuesta invalida, ingrese nuevamente.");
                                                break;
                                        }
                                        
                                    } while (seleccionP2 != 5);
                                    
                                    break;
                                case 3:
                                    
                                    do {
                                        seleccionP3 = 0;
                                        System.out.println("\tIngrese opcion:\n<1> Ver lista de empleados.\n<2> Ver lista de todos los usuarios.\n<3> Ver un empleado o usuario.\n<4> Crear empleado.\n<5> Regresar.");
                                        seleccionP3 = Input.ent();
                                        
                                        switch (seleccionP3) {
                                            case 5:
                                                break;
                                            case 1:
                                                GestionUsuarios.mostrarTodosUsuarios(actualP, GestionUsuarios.getTodoPersonal());
                                                break;
                                            case 2:
                                                GestionUsuarios.mostrarTodosUsuarios(actualP, GestionUsuarios.getTodoUsuario());
                                                break;
                                            case 3:
                                                campo = null;
                                                System.out.print("Ingrese la cedula del empleado o usuario: ");
                                                campo = Input.str();
                                                
                                                Usuario seleccionado = GestionUsuarios.getUsuario(campo);
                                                if (seleccionado == null) {
                                                    System.out.println("-- La cedula ingresada no existe en el sistema.");
                                                } else {
                                                    seleccionado.mostrarFicha();
                                                    
                                                    if (GestionUsuarios.puedeGestionar(actualP, seleccionado)) {
                                                        do {
                                                            seleccionP5 = 0;
                                                            String accion = (seleccionado.isActivo())? "Desactivar" : "Reactivar";
                                                            System.out.printf("\tIngrese opcion:\n<1> %s cuenta.\n<2> Cambiar nivel.\n<3> Regresar.\n", accion);
                                                            seleccionP5 = Input.ent();
                                                            
                                                            switch (seleccionP5) {
                                                                case 3:
                                                                    break;
                                                                case 1:
                                                                    String razon = pedirTexto("Escriba una razon del por que: ", 1, 100);
                                                                    GestionUsuarios.cambiarEstadoUsuario(actualP, seleccionado.getCedula(), razon);
                                                                    seleccionado.mostrarFicha();
                                                                    break;
                                                                case 2:
                                                                    if (seleccionado instanceof Personal) {
                                                                        System.out.printf("Ingrese el nuevo nivel (entre %d y %d): ", actualP.getNivel() + 1, Usuario.NIVEL_MAXIMO_PERSONAL);
                                                                        int nuevoNivel = Input.ent();
                                                                        
                                                                        GestionUsuarios.cambiarNivelPersonal(actualP, seleccionado.getCedula(), nuevoNivel);
                                                                        seleccionado.mostrarFicha();
                                                                    } else {
                                                                        System.out.println("-- Solo se puede cambiar el nivel de un empleado.");
                                                                    }
                                                                    break;
                                                                default:
                                                                    System.out.println("-- Respuesta invalida, ingrese nuevamente.");
                                                                    break;
                                                            }
                                                            
                                                        } while (seleccionP5 != 3);
                                                    }
                                                }
                                                break;
                                            case 4:
                                                if (actualP.getNivel() < Usuario.NIVEL_MAXIMO_PERSONAL) {
                                                    campo = null;
                                                    System.out.print("Ingrese la cedula del empleado: ");
                                                    campo = Input.str();
                                                    
                                                    if (campo.matches("\\d+") && campo.length() == 10 && !campo.equals(Usuario.SIN_CEDULA) && GestionUsuarios.getUsuario(campo) == null) {
                                                        String cedula = campo;
                                                        String nombre = pedirNombre();
                                                        
                                                        System.out.printf("Ingrese el nivel del empleado (entre %d y %d): ", actualP.getNivel() + 1, Usuario.NIVEL_MAXIMO_PERSONAL);
                                                        int nivelNuevo = Input.ent();
                                                        
                                                        GestionUsuarios.crearPersonal(actualP, cedula, nombre, nivelNuevo);
                                                    } else {
                                                        System.out.println("-- Cedula no valida o ya registrada.");
                                                    }
                                                } else {
                                                    System.out.println("-- No se poseen los permisos necesarios.");
                                                }
                                                break;
                                            default:
                                                System.out.println("-- Respuesta invalida, ingrese nuevamente.");
                                                break;
                                        }
                                        
                                    } while (seleccionP3 != 5);
                                    
                                    break;
                                case 4:
                                    
                                    do {
                                        seleccionP4 = 0;
                                        System.out.println("\tIngrese opcion:\n<1> Ver reservas activas.\n<2> Ver todos los registros.\n<3> Ver una reserva.\n<4> Cancelar reserva de una habitacion.\n<5> Regresar.");
                                        seleccionP4 = Input.ent();
                                        
                                        switch (seleccionP4) {
                                            case 5:
                                                break;
                                            case 1:
                                                GestionReservas.actualizarReservas();
                                                GestionReservas.mostrarRegistrosActivosPersonal(GestionReservas.getListaRegistro(), actualP);
                                                break;
                                            case 2:
                                                GestionReservas.actualizarReservas();
                                                GestionReservas.mostrarTodosRegistrosPersonal(GestionReservas.getListaRegistro(), actualP);
                                                break;
                                            case 3:
                                                seleccion2 = 0;
                                                System.out.print("Elija el numero de registro (elija 0 para regresar): ");
                                                seleccion2 = Input.ent();
                                                
                                                if (seleccion2 != 0) {
                                                    GestionReservas.actualizarReservas();
                                                    Registro encontrado = GestionReservas.getRegistro(seleccion2, GestionReservas.getListaRegistro());
                                                    
                                                    if (encontrado != null) {
                                                        encontrado.mostrarRegistroPersonal(actualP);
                                                    } else {
                                                        System.out.println("-- Registro seleccionado no valido.");
                                                    }
                                                }
                                                break;
                                            case 4:
                                                seleccion4 = 0;
                                                System.out.print("Elija el numero de habitacion a cancelar (elija 0 para regresar): ");
                                                seleccion4 = Input.ent();
                                                
                                                if (seleccion4 != 0) {
                                                    campo = pedirTexto("Escriba una razon del por que: ", 1, 100);
                                                    actualP.cancelarHabitacion(seleccion4, campo);
                                                }
                                                break;
                                            default:
                                                System.out.println("-- Respuesta invalida, ingrese nuevamente.");
                                                break;
                                        }
                                        
                                    } while (seleccionP4 != 5);
                                    
                                    break;
                                default:
                                    System.out.println("-- Respuesta invalida, ingrese nuevamente.");
                                    break;
                            }
                            
                        } while (seleccionP != 5 && actualP.isActivo());
                    }
                    
                    break;
                default:
                    System.out.println("-- Respuesta invalida, ingrese nuevamente.");
                    break;
            }
        } while (seleccion != 3);
        
        Input.cerrarScanner();
    }
}
