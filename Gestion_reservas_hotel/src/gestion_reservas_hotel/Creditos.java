package gestion_reservas_hotel;

public class Creditos {
    private String cedula;
    private int saldoPuntos;
    private int puntosGanado = 0;
    private double montoPorPuntos = 1.0;
    
    public Creditos(int saldoPuntos, String cedula, int puntosGanado){
        this.cedula = cedula;
        this.saldoPuntos = saldoPuntos;
        this.puntosGanado = puntosGanado;
    }
    
    public int getSaldoPuntos(){
        return saldoPuntos;
    }
    
    public String getCedula() {
        return cedula;
    }
    
    //Acumular Puntos
    public void acumular_Puntos(double montoGastado){
        if (montoGastado > 0){
            int puntos = (int) (montoGastado / montoPorPuntos);
            this.saldoPuntos += puntos;
            this.puntosGanado += puntos;
            System.out.println("-- Usted ha acumulado " + puntos + " puntos.");
        }
    }
    
    //Canje de puntos
    public boolean canjearPuntos(int puntos){
        if (puntos <= 0){
            System.out.println("-- La cantidad de puntos debe ser mayor a 0.");
            return false;
        }
        if (this.saldoPuntos >= puntos){
            this.saldoPuntos -= puntos;
            System.out.println("-- Canje exitoso de " + puntos + " puntos.");
            return true;
        } else {
            System.out.println("-- Saldo insuficiente.");
            return false;
        }
    }
    
    //Revertir puntos (cancelaciones)
    public void restarPuntos(int puntos) {
        if (puntos > 0) {
            this.saldoPuntos = Math.max(0, this.saldoPuntos - puntos);
            System.out.println("-- Se han descontado " + puntos + " puntos por la cancelacion.");
        }
    }
}
