package id.zulvani.math.basic.arithmetic;

public enum Operator {
    ADD("+"), SUB("-"), MUL("*");
    private final String sign;

    Operator(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
