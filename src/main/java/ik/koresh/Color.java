package ik.koresh;

public enum Color {

    ANSI_RESET ("\u001B[0m"),
    WHITE("\u001B[0;107m"),
    RED ("\u001B[41m"),
    BLACK("\u001B[0;100m"),
    GREEN("\u001B[0;102m"),
    YELLOW("\u001B[43m"),
    CYAN("\u001B[46m"),
    PURPLE("\u001B[45m"),
    BLUE("\u001B[44m");

    public final String stringColor;

    Color(String stringColor) {
        this.stringColor = stringColor;
    }
}
