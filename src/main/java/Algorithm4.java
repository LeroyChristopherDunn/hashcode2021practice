import java.util.*;
import java.util.stream.Collectors;

//fifo pizza delivery
// - starting with longer teams
// - pizzas ordered by num ingredients
// - pizzas selected with best score within search range
public class Algorithm4 extends Algorithm {
    protected Algorithm4(Input input) {
        super(input);
    }

    private final int searchRange = 10;

    @Override
    Output run() {

        List<Output.Delivery> deliveries = new ArrayList<>(10_000);

        ArrayList<Input.Pizza> sortedPizzas = new ArrayList<>(input.pizzas);
        sortedPizzas.sort(Comparator.comparingInt(o -> o.numIngredients));
        Collections.reverse(sortedPizzas);
        LinkedHashSet<Input.Pizza> pizzas = new LinkedHashSet<>(sortedPizzas);

        for (int i = 0; i < input.numFourPersonTeams; i++) {

            boolean noMorePizzasForTeam = pizzas.size() < 4;
            if (noMorePizzasForTeam) break;

            List<Input.Pizza> selectedPizzas = getBestPizzasTeam4(pizzas);
            pizzas.removeAll(selectedPizzas);

            List<Integer> selectedPizzaIds = getPizzaIds(selectedPizzas);
            deliveries.add(new Output.Delivery(4, selectedPizzaIds));
        }
        for (int i = 0; i < input.numThreePersonTeams; i++) {

            boolean noMorePizzasForTeam = pizzas.size() < 3;
            if (noMorePizzasForTeam) break;

            List<Input.Pizza> selectedPizzas = getBestPizzasTeam3(pizzas);
            pizzas.removeAll(selectedPizzas);

            List<Integer> selectedPizzaIds = getPizzaIds(selectedPizzas);
            deliveries.add(new Output.Delivery(3, selectedPizzaIds));
        }
        for (int i = 0; i < input.numTwoPersonTeams; i++) {

            boolean noMorePizzasForTeam = pizzas.size() < 2;
            if (noMorePizzasForTeam) break;

            List<Input.Pizza> selectedPizzas = getBestPizzasTeam2(pizzas);
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

    private List<Input.Pizza> getBestPizzasTeam4(LinkedHashSet<Input.Pizza> pizzas){
        List<Input.Pizza> searchPizzas = pizzas.stream()
                .limit(searchRange)
                .collect(Collectors.toList());

        int bestScore = 0;
        List<Integer> bestPizzas = searchPizzas.subList(0, 4).stream()
                .map(pizza -> pizza.pizzaId)
                .collect(Collectors.toList());

        for (int i = 0; i < searchPizzas.size(); i++) {
            for (int j = i+1; j < searchPizzas.size(); j++) {
                for (int k = j+1; k < searchPizzas.size(); k++) {
                    for (int l = k+1; l < searchPizzas.size(); l++) {
                        HashSet<String> ingredientSet = new HashSet<>();
                        ingredientSet.addAll(searchPizzas.get(i).ingredients);
                        ingredientSet.addAll(searchPizzas.get(j).ingredients);
                        ingredientSet.addAll(searchPizzas.get(k).ingredients);
                        ingredientSet.addAll(searchPizzas.get(l).ingredients);
                        int score = ingredientSet.size();
                        if (score > bestScore){
                            bestScore = score;
                            bestPizzas = Arrays.asList(i, j, k, l);
                        }
                    }
                }
            }
        }

        return bestPizzas.stream()
                .map(searchPizzas::get)
                .collect(Collectors.toList());
    }

    private List<Input.Pizza> getBestPizzasTeam3(LinkedHashSet<Input.Pizza> pizzas){
        List<Input.Pizza> searchPizzas = pizzas.stream()
                .limit(searchRange)
                .collect(Collectors.toList());

        int bestScore = 0;
        List<Integer> bestPizzas = searchPizzas.subList(0, 3).stream()
                .map(pizza -> pizza.pizzaId)
                .collect(Collectors.toList());

        for (int i = 0; i < searchPizzas.size(); i++) {
            for (int j = i+1; j < searchPizzas.size(); j++) {
                for (int k = j+1; k < searchPizzas.size(); k++) {
                    HashSet<String> ingredientSet = new HashSet<>();
                    ingredientSet.addAll(searchPizzas.get(i).ingredients);
                    ingredientSet.addAll(searchPizzas.get(j).ingredients);
                    ingredientSet.addAll(searchPizzas.get(k).ingredients);
                    int score = ingredientSet.size();
                    if (score > bestScore){
                        bestScore = score;
                        bestPizzas = Arrays.asList(i, j, k);
                    }
                }
            }
        }

        return bestPizzas.stream()
                .map(searchPizzas::get)
                .collect(Collectors.toList());
    }

    private List<Input.Pizza> getBestPizzasTeam2(LinkedHashSet<Input.Pizza> pizzas){
        List<Input.Pizza> searchPizzas = pizzas.stream()
                .limit(searchRange)
                .collect(Collectors.toList());

        int bestScore = 0;
        List<Integer> bestPizzas = searchPizzas.subList(0, 2).stream()
                .map(pizza -> pizza.pizzaId)
                .collect(Collectors.toList());

        for (int i = 0; i < searchPizzas.size(); i++) {
            for (int j = i+1; j < searchPizzas.size(); j++) {
                HashSet<String> ingredientSet = new HashSet<>();
                ingredientSet.addAll(searchPizzas.get(i).ingredients);
                ingredientSet.addAll(searchPizzas.get(j).ingredients);
                int score = ingredientSet.size();
                if (score > bestScore){
                    bestScore = score;
                    bestPizzas = Arrays.asList(i, j);
                }
            }
        }

        return bestPizzas.stream()
                .map(searchPizzas::get)
                .collect(Collectors.toList());
    }
}
