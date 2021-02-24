import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//fifo pizza delivery
public class Algorithm1 extends Algorithm {
    protected Algorithm1(Input input) {
        super(input);
    }

    @Override
    Output run() {

        List<Output.Delivery> deliveries = new ArrayList<>(10_000);

        int pizzaIndex = 0;

        for (int i = pizzaIndex; i < input.numTwoPersonTeams; i++) {
            boolean noMorePizzasForTeam = pizzaIndex > input.numPizzas - 1 - 1;
            if (noMorePizzasForTeam) break;
            List<Integer> pizzaIds = Arrays.asList(pizzaIndex, pizzaIndex+1);
            deliveries.add(new Output.Delivery(2, pizzaIds));
            pizzaIndex += 2;
        }
        for (int i = 0; i < input.numThreePersonTeams; i++) {
            boolean noMorePizzasForTeam = pizzaIndex > input.numPizzas - 1 - 2;
            if (noMorePizzasForTeam) break;
            List<Integer> pizzaIds = Arrays.asList(pizzaIndex, pizzaIndex+1, pizzaIndex+2);
            deliveries.add(new Output.Delivery(3, pizzaIds));
            pizzaIndex += 3;
        }
        for (int i = 0; i < input.numFourPersonTeams; i++) {
            boolean noMorePizzasForTeam = pizzaIndex > input.numPizzas - 1 - 3;
            if (noMorePizzasForTeam) break;
            List<Integer> pizzaIds = Arrays.asList(pizzaIndex, pizzaIndex+1, pizzaIndex+2, pizzaIndex+3);
            deliveries.add(new Output.Delivery(4, pizzaIds));
            pizzaIndex += 4;
        }

        return new Output(deliveries);
    }
}
