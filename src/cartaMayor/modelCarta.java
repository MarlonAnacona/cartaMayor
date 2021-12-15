package cartaMayor;

import java.util.Random;

public class modelCarta
{

    private carta cartaJugador = new carta();
    private carta cartaMaquina = new carta();
    private int paloJugador,paloMaquina,paloGanador;
    private int estado;
    private String[] estadoToString = new String[2];
    private String paloGanadorToString,jugadorGanador;
    private int[] caras = new int[2];
    Random aleatorio= new Random();

    public modelCarta() {
    }

    public void calcularTiroJugador() {
        this.caras[0] = this.cartaJugador.getCara();

        this.paloJugador=aleatorio.nextInt(4)+1;

    }

    public void calcularTiroMaquina() {
        this.caras[1] = this.cartaMaquina.getCara();
        this.paloMaquina=aleatorio.nextInt(4)+1;

        //equals cards
        if(this.caras[0] == this.caras[1] &&this.paloJugador==this.paloMaquina)
            calcularTiroMaquina();
    }

    public  int getPaloJugador(){
        return paloJugador;
}


    public  int getPaloMaquina(){
    return paloMaquina;
}

    public void determinarJuego() {
        if (this.caras[0]>this.caras[1]) {
            //win player
            this.estado = 1;
        }else {

            if (this.caras[0] < this.caras[1]) {
                //win machine
                this.estado = 2;
            } else {
                if(this.caras[0] == this.caras[1]) {
                //equality
                 this.estado=3;
                 if(this.paloJugador>this.paloMaquina){
                     paloGanador=paloJugador;
                     if(paloGanador==4){
                         paloGanadorToString="Oro";
                     }
                     if(paloGanador==3){
                         paloGanadorToString="Copas";
                     }
                     if(paloGanador==2){
                         paloGanadorToString="Espadas";
                     }
                     if(paloGanador==1){
                         paloGanadorToString="Bastos";
                     }
                     jugadorGanador="El usuario";
                 }else{
                     if(this.paloJugador<this.paloMaquina){
                         paloGanador=paloMaquina;
                         if(paloGanador==4){
                             paloGanadorToString="Oro";
                         }
                         if(paloGanador==3){
                             paloGanadorToString="Copas";
                         }
                         if(paloGanador==2){
                             paloGanadorToString="Espadas";
                         }
                         if(paloGanador==1){
                             paloGanadorToString="Bastos";
                         }
                         jugadorGanador="La maquina";
                     }
                 }

                }

            }

        }
    }







    public String[] getEstadoToString() {
        switch(this.estado) {
            case 1:
        //win
                this.estadoToString[0] = "Has ganado!";

                break;
            case 2:
                //win
                this.estadoToString[0] = "Has perdido";

                break;
            case 3:
                //
                this.estadoToString[0] = "Sacaron igual numero, el  palo mayor fue el de: "+ paloGanadorToString+
                        "\n Por lo tanto el ganador es: "+ jugadorGanador;
            break;
        }

        return this.estadoToString;
    }

    public int[] getCaras() {
        return this.caras;
    }
}
