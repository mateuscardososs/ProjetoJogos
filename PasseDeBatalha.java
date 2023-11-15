public class PasseDeBatalha {
    public static void main(String[] args) {
       
        PasseComum passeComum = new PasseComum();
        passeComum.registrarVitoria();
        passeComum.entregarPremiacao();
        passeComum.finalizarPasse();

        System.out.println();

        PassePremium passePremium = new PassePremium();
        passePremium.registrarVitoria();
        passePremium.entregarPremiacao();
        passePremium.resetarPasse();
    }
}
