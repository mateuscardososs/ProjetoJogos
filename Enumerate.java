public class Enumerate {
    public enum HabilidadeCarta {
        AMEDRONTAR,
        ATROPELAR,
        UNIR,
        VOAR,
        INVISIBILIDADE,
        FOGO
    }

    public enum RaridadeDasCartas {
        COMUM("Comum", 0.80),
        INCOMUM("Incomum", 0.15),
        RARA("Rara", 0.04),
        MUITO_RARA("Muito rara", 0.008),
        ÉPICA("Épica", 0.002);

        private final String name;
        private final double dropProbability;

        private RaridadeDasCartas(String name, double dropProbability) {
            this.name = name;
            this.dropProbability = dropProbability;
        }

        public String getName() {
            return name;
        }

        public double getDropProbability() {
            return dropProbability;
        }

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