public class Enumerate{
    public enum raridadedascartas{
        COMUM("Comum", 0.80),
        INCOMUM("Incomum", 0.15),
        RARA("Rara", 0.04),
        MUITO_RARA("Muito rara", 0.008),
        ÉPICA("Épica", 0.002);

        private final String name;
        private final double dropProbability;
    
        private raridadedascartas(String name, double dropProbability) {
            this.name = name;
            this.dropProbability = dropProbability;
        }
    
        public String getName() {
            return name;
        }
    
        public double getDropProbability() {
            return dropProbability;
        }
    
        public static raridadedascartas getRandomRarity() {
            double randomValue = Math.random();
            double cumulativeProbability = 0.0;
            
            for (raridadedascartas rarity : raridadedascartas.values()) {
                cumulativeProbability += rarity.getDropProbability();
                if (randomValue <= cumulativeProbability) {
                    return rarity;
                }
            }
            
            // Default to common if for some reason no rarity was selected
            return COMUM;
        }
    }
}
