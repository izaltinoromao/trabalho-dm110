package br.inatel.dm110.enums;

public enum Operation {
    CREATE("create"),
    UPDATE("update"),
    DELETE("delete"),
    GET("get");

    private final String operation;

    private Operation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
