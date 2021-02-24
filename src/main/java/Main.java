import java.io.IOException;

public class Main {

    private final String[] inputFiles = {
            "a_example",
            "b_little_bit_of_everything.in",
            "c_many_ingredients.in",
            "d_many_pizzas.in",
            "e_many_teams.in",
    };

    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    public void run() throws IOException {
        for (String inputFile : inputFiles) {
            Input input = new InputReader("./inputdata/" + inputFile).read();
            Algorithm algorithm = new Algorithm10(input);
            Output output = algorithm.run();
            new OutputWriter(inputFile, output).write();
        }
    }

    public void readerTest() throws IOException {
        for (String inputFile : inputFiles) {
            Input input = new InputReader("./inputdata/" + inputFile).read();

            System.out.println();
            System.out.println("num pizzas: " + input.numPizzas);
            System.out.println("num tpt: " + input.numTwoPersonTeams);
            System.out.println("num tpt: " + input.numThreePersonTeams);
            System.out.println("num fpt: " + input.numFourPersonTeams);

            if (input.numPizzas != input.pizzas.size())
                System.out.println("pizza size error");

            for (Input.Pizza pizza : input.pizzas) {
                if (pizza.numIngredients != pizza.ingredients.size())
                    System.out.println("num ingredient error");
                if (inputFile.equals("./inputdata/a_example"))
                    System.out.println(pizza.ingredients);
            }
        }
    }


}
