import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OutputWriter {

    public final String inputFileName;
    public final Output output;

    public OutputWriter(String inputFileName, Output output) {
        this.output = output;
        this.inputFileName = inputFileName;
    }

    public void write() throws IOException{

        PrintWriter out = new PrintWriter(new FileWriter("./outputdata/" + inputFileName + ".out"));

        out.println(output.deliveries.size());

        for (Output.Delivery delivery : output.deliveries) {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(delivery.teamSize);
            for (Integer pizzaId : delivery.pizzaIds) {
                stringBuilder.append(" ").append(pizzaId);
            }
            out.println(stringBuilder.toString());
        }

        out.close();
    }

}
