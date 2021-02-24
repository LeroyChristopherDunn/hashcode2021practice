import java.util.Collections;
import java.util.List;

public class Output {
    public final List<Delivery> deliveries;

    public Output(List<Delivery> deliveries) {
        this.deliveries = Collections.unmodifiableList(deliveries);
    }

    public static class Delivery{
        public final int teamSize;
        public final List<Integer> pizzaIds;

        public Delivery(int teamSize, List<Integer> pizzaIds) {
            this.teamSize = teamSize;
            this.pizzaIds = pizzaIds;
        }
    }


}
