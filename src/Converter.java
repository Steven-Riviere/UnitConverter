import java.util.HashMap;
import java.util.Map;

public class Converter {
    private static final Map<String, Double> conversionFactors = new HashMap<>();

    static {
        // Distance
        conversionFactors.put("Kilomètres", 1000.0);
        conversionFactors.put("Mètres", 1.0);
        conversionFactors.put("Pieds", 0.3048);
        conversionFactors.put("Miles", 1609.34);

        // Poids
        conversionFactors.put("Kilogrammes", 1.0);
        conversionFactors.put("Grammes", 0.001);
        conversionFactors.put("Livres", 0.453592);

        // Volume (Cuisine)
        conversionFactors.put("Litres", 1.0);
        conversionFactors.put("Millilitres", 0.001);
        conversionFactors.put("Centilitres", 0.01);
        conversionFactors.put("Cuillères à soupe", 0.015);
        conversionFactors.put("Cuillères à café", 0.005);
    }

    public static double convert(double value, String fromUnit, String toUnit) {
        if (!conversionFactors.containsKey(fromUnit) || !conversionFactors.containsKey(toUnit)) {
            throw new IllegalArgumentException("❌ Conversion non supportée : " + fromUnit + " -> " + toUnit);
        }

        // Convertir en unité de base (ex : tout ramener à Mètres, Kilogrammes, Litres, etc.)
        double baseValue = value * conversionFactors.get(fromUnit);

        // Convertir en unité cible
        return baseValue / conversionFactors.get(toUnit);
    }
}
