package edu.escuelaing.arep;

public class MathResponse {
    private String operation;
    private Double input;
    private Double output;

    public MathResponse(String operation, Double input, Double output) {
        this.operation = operation;
        this.input = input;
        this.output = output;
    }
}
