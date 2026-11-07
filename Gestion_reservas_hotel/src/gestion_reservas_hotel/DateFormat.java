package gestion_reservas_hotel;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class DateFormat {
    private static final String PATRON = " yyyy/MM/dd - HH:mm:ss";
    
    public static String formatoFecha() {
        return formatoFecha(Calendar.getInstance());
    }
    
    public static String formatoFecha(Calendar fecha) {
        return new SimpleDateFormat(PATRON).format(fecha.getTime());
    }
    
    public static boolean compararFecha(Calendar fecha) {
        Calendar fechaDeHoy = Calendar.getInstance();
        return fechaDeHoy.before(fecha);
    }    
}
