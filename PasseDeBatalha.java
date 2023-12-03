public class PasseDeBatalha {
    public static void main(String[] args) {
       // Instanciar e usar o PasseComum
        PasseComum passeComum = new PasseComum();
        passeComum.registrarVitoria();
        passeComum.entregarPremiacao();
        passeComum.finalizarPasse();

        System.out.println();
        
       // Instanciar e usar o PassePremium
        PassePremium passePremium = new PassePremium();
        passePremium.registrarVitoria();
        passePremium.entregarPremiacao();
        passePremium.resetarPasse();
    }
}
