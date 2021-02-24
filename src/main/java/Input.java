import java.util.*;

public class Input {
    public final int numPizzas;
    public final int numTwoPersonTeams;
    public final int numThreePersonTeams;
    public final int numFourPersonTeams;
    public final List<Pizza> pizzas;

    public final HashSet<String> ingredientSet;

    public static class Pizza{
        public final int pizzaId;
        public final int numIngredients;
        public final List<String> ingredients;

        public Pizza(int pizzaId, int numIngredients, List<String> ingredients) {
            this.pizzaId = pizzaId;
            this.numIngredients = numIngredients;
            this.ingredients = Collections.unmodifiableList(ingredients);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pizza pizza = (Pizza) o;
            return pizzaId == pizza.pizzaId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(pizzaId);
        }
    }

    public Input(int numPizzas, int numTwoPersonTeams, int numThreePersonTeams, int numFourPersonTeams, List<Pizza> pizzas) {
        this.numPizzas = numPizzas;
        this.numTwoPersonTeams = numTwoPersonTeams;
        this.numThreePersonTeams = numThreePersonTeams;
        this.numFourPersonTeams = numFourPersonTeams;
        this.pizzas = Collections.unmodifiableList(pizzas);

        HashSet<String> ingredientSet = new HashSet<>(numPizzas * 10);
        pizzas.forEach(pizza -> ingredientSet.addAll(pizza.ingredients));
        this.ingredientSet = ingredientSet;
    }
}

