
package gestion_reservas_hotel;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class DateFormat {
    static private SimpleDateFormat formato = new SimpleDateFormat(" yyyy/MM/dd - HH:mm:ss");
    private Calendar diaSistema;
    
    public static void actualizarDia(Calendar diaSistema) {
        diaSistema = Calendar.getInstance();
    }
    
    public static String formatoFecha() {
        Calendar fecha = Calendar.getInstance();
        String fechaFormateada = formato.format(fecha.getTime());
        return fechaFormateada;
    }
    
    public static String formatoFecha(Calendar fecha) {
        String fechaFormateada = formato.format(fecha.getTime());
        return fechaFormateada;
    }
    
    public static boolean compararFecha(Calendar fecha) {
        Calendar fechaDeHoy = Calendar.getInstance();
        boolean aunHayTiempo = fechaDeHoy.before(fecha);
        return aunHayTiempo;
    }    
}
