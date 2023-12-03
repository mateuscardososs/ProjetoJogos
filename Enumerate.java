public class Enumerate {
    // Enumeração de habilidades das cartas
    public enum HabilidadeCarta {
        AMEDRONTAR,
        ATROPELAR,
        UNIR,
        VOAR,
        INVISIBILIDADE,
        FOGO
    }
    // Enumeração de raridades das cartas
    public enum RaridadeDasCartas {
        COMUM("Comum", 0.80),
        INCOMUM("Incomum", 0.15),
        RARA("Rara", 0.04),
        MUITO_RARA("Muito rara", 0.008),
        ÉPICA("Épica", 0.002);
        // Atributos da enumeração de raridades
        private final String name;
        private final double dropProbability;
         // Construtor privado
        private RaridadeDasCartas(String name, double dropProbability) {
            this.name = name;
            this.dropProbability = dropProbability;
        }
        // Método para obter o nome da raridade
        public String getName() {
            return name;
        }
       // Método para obter a probabilidade de drop da raridade
        public double getDropProbability() {
            return dropProbability;
        }
        // Método para obter uma raridade aleatória
        public static RaridadeDasCartas getRandomRarity() {
            double randomValue = Math.random();
            double cumulativeProbability = 0.0;

            for (RaridadeDasCartas rarity : RaridadeDasCartas.values()) {
                cumulativeProbability += rarity.getDropProbability();
                if (randomValue <= cumulativeProbability) {
                    return rarity;
                }
            }

            return COMUM;
        }
    }
}