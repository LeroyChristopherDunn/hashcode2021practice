import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputReader {

    private final String fileName;

    public InputReader(String fileName){
        this.fileName = fileName;
    }

    public Input read() throws IOException{

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        List<Integer> firstLineParams = readLineOfIntegers(bufferedReader);
        int numPizzas = firstLineParams.get(0);
        int numTwoPersonTeams = firstLineParams.get(1);
        int numThreePersonTeams = firstLineParams.get(2);
        int numFourPersonTeams = firstLineParams.get(3);

        List<Input.Pizza> pizzas = new ArrayList<>(numPizzas);
        for (int pizzaId = 0; pizzaId < numPizzas; pizzaId++) {

            String[] pizzaParams = bufferedReader.readLine().split(" ");
            int numPizzaIngredients = Integer.parseInt(pizzaParams[0]);
            List<String> ingredients = Arrays.asList(pizzaParams).subList(1, pizzaParams.length);
            ingredients.sort(String::compareTo);
            Input.Pizza pizza = new Input.Pizza(pizzaId, numPizzaIngredients, ingredients);
            pizzas.add(pizza);
        }

        return new Input(numPizzas, numTwoPersonTeams, numThreePersonTeams, numFourPersonTeams, pizzas);
    }

    private List<Integer> readLineOfIntegers(BufferedReader bufferedReader) throws IOException {
        String firstLine = bufferedReader.readLine();
        return Arrays.stream(firstLine.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    }

}
