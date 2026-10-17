package gestion_reservas_hotel;
public class Creditos {
    String cedula;
    private int saldoPuntos;
    int puntosGanado = 0;
    double montoPorPuntos = 1.0;
    public Creditos(int saldoPuntos, String cedula, int puntosGanado){
        this.cedula = cedula;
        this.saldoPuntos = saldoPuntos;
        this.puntosGanado = puntosGanado;
    }
    //Acumular Puntos
    public void acumular_Puntos(double montoGastado){
        if (montoGastado>0){
            int puntosGanado = (int) (montoGastado / montoPorPuntos);
            this.saldoPuntos += puntosGanado;
            System.out.println("Usted ha acumulado "+ puntosGanado + "puntos");
        }
    }
    //Canje de puntos
    public boolean canjearPuntos(int puntos){
        if (puntos<= 0 ){
            System.out.println("Usted no tiene puntos");
            return false;
        }
        if (this.saldoPuntos >= puntos){
            this.saldoPuntos =- puntos;
            System.out.println("Canje exitoso de "+ puntos +"puntos");
            return true;
        }else{
            System.out.println("Saldo insuficiente");
            return false;
        }
    }
}