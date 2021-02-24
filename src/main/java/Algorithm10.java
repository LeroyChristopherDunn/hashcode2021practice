import java.util.*;
import java.util.stream.Collectors;

//fifo pizza delivery
// - starting with shorter teams
// - pizzas ordered by num ingredients
// - pizzas selected with best score within search range
// - score with least ingredient overlap - weighted
// - incremental score building
public class Algorithm10 extends Algorithm {
    protected Algorithm10(Input input) {
        super(input);
    }

    private final int searchRange = 10000;

    @Override
    Output run() {

        Random random = new Random(0);
        List<Output.Delivery> deliveries = new ArrayList<>(10_000);

        ArrayList<Input.Pizza> sortedPizzas = new ArrayList<>(input.pizzas);
        sortedPizzas.sort(Comparator.comparingInt(o -> o.numIngredients));
        Collections.reverse(sortedPizzas);
        LinkedHashSet<Input.Pizza> pizzas = new LinkedHashSet<>(sortedPizzas);

        for (int i = 0; i < input.numFourPersonTeams; i++) {

            boolean noMorePizzasForTeam = pizzas.size() < 4;
            if (noMorePizzasForTeam) break;

            List<Input.Pizza> selectedPizzas = getBestPizzas(pizzas, 4);
            pizzas.removeAll(selectedPizzas);

            List<Integer> selectedPizzaIds = getPizzaIds(selectedPizzas);
            deliveries.add(new Output.Delivery(4, selectedPizzaIds));
        }
        for (int i = 0; i < input.numThreePersonTeams; i++) {

            boolean noMorePizzasForTeam = pizzas.size() < 3;
            if (noMorePizzasForTeam) break;

            List<Input.Pizza> selectedPizzas = getBestPizzas(pizzas, 3);
            pizzas.removeAll(selectedPizzas);

            List<Integer> selectedPizzaIds = getPizzaIds(selectedPizzas);
            deliveries.add(new Output.Delivery(3, selectedPizzaIds));
        }
        for (int i = 0; i < input.numTwoPersonTeams; i++) {

            boolean noMorePizzasForTeam = pizzas.size() < 2;
            if (noMorePizzasForTeam) break;

            List<Input.Pizza> selectedPizzas = getBestPizzas(pizzas, 2);
            pizzas.removeAll(selectedPizzas);

            List<Integer> selectedPizzaIds = getPizzaIds(selectedPizzas);
            deliveries.add(new Output.Delivery(2, selectedPizzaIds));
        }

        return new Output(deliveries);
    }

    private List<Integer> getPizzaIds(List<Input.Pizza> pizzas){
        return pizzas.stream()
                .map(pizza -> pizza.pizzaId)
                .collect(Collectors.toList());
    }

    private List<Input.Pizza> getBestPizzas(LinkedHashSet<Input.Pizza> pizzas, int teamSize){

        List<Input.Pizza> searchPizzas = pizzas.stream()
                .limit(searchRange)
                .collect(Collectors.toList());

        List<Input.Pizza> bestPizzas = new ArrayList<>(10);

        for (int i = 0; i < teamSize; i++) {

            int bestScore = Integer.MIN_VALUE;
            Input.Pizza bestPizza = searchPizzas.get(0);
            HashSet<String> currIngredients = new HashSet<>();
            bestPizzas.forEach(pizza -> currIngredients.addAll(pizza.ingredients));

            for (Input.Pizza searchPizza : searchPizzas) {
                if (bestPizzas.contains(searchPizza)) continue;

                HashSet<String> newIngredients = new HashSet<>(currIngredients);
                newIngredients.addAll(searchPizza.ingredients);
                int numUniqueIngredients = newIngredients.size() - currIngredients.size();

                int score =  (1000 * numUniqueIngredients) - searchPizza.numIngredients;
                if (score > bestScore){
                    bestScore = score;
                    bestPizza = searchPizza;
                }
            }

            bestPizzas.add(bestPizza);
        }

        return bestPizzas;
    }
}
