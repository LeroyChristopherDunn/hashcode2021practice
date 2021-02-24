public abstract class Algorithm {
    final Input input;

    protected Algorithm(Input input) {
        this.input = input;
    }

    abstract Output run();
}
