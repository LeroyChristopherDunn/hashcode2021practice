import java.util.*;
import java.util.stream.Collectors;

//fifo pizza delivery
// - starting with longer teams
// - pizzas ordered by num ingredients
public class Algorithm3 extends Algorithm {
    protected Algorithm3(Input input) {
        super(input);
    }

    @Override
    Output run() {

        List<Output.Delivery> deliveries = new ArrayList<>(10_000);

        ArrayList<Input.Pizza> sortedPizzas = new ArrayList<>(input.pizzas);
        sortedPizzas.sort(Comparator.comparingInt(o -> o.numIngredients));
        Collections.reverse(sortedPizzas);
        LinkedHashSet<Input.Pizza> pizzas = new LinkedHashSet<>(sortedPizzas);

        int pizzaIndex = 0;

        for (int i = 0; i < input.numFourPersonTeams; i++) {
            boolean noMorePizzasForTeam = pizzas.size() < 4;
            if (noMorePizzasForTeam) break;
            List<Input.Pizza> selectedPizzas = getBestPizzas(pizzas, 4);
            pizzas.removeAll(selectedPizzas);
            List<Integer> selectedPizzaIds = selectedPizzas.stream()
                    .map(pizza -> pizza.pizzaId)
                    .collect(Collectors.toList());
            deliveries.add(new Output.Delivery(4, selectedPizzaIds));
        }
        for (int i = 0; i < input.numThreePersonTeams; i++) {
            boolean noMorePizzasForTeam = pizzas.size() < 3;
            if (noMorePizzasForTeam) break;
            List<Input.Pizza> selectedPizzas = getBestPizzas(pizzas, 3);
            pizzas.removeAll(selectedPizzas);
            List<Integer> selectedPizzaIds = selectedPizzas.stream()
                    .map(pizza -> pizza.pizzaId)
                    .collect(Collectors.toList());
            deliveries.add(new Output.Delivery(3, selectedPizzaIds));
        }
        for (int i = pizzaIndex; i < input.numTwoPersonTeams; i++) {
            boolean noMorePizzasForTeam = pizzas.size() < 2;
            if (noMorePizzasForTeam) break;
            List<Input.Pizza> selectedPizzas = getBestPizzas(pizzas, 2);
            pizzas.removeAll(selectedPizzas);
            List<Integer> selectedPizzaIds = selectedPizzas.stream()
                    .map(pizza -> pizza.pizzaId)
                    .collect(Collectors.toList());
            deliveries.add(new Output.Delivery(2, selectedPizzaIds));
        }

        return new Output(deliveries);
    }

    private List<Input.Pizza> getBestPizzas(LinkedHashSet<Input.Pizza> pizzas, int teamSize){
        return pizzas.stream().limit(teamSize).collect(Collectors.toList());
    }
}
