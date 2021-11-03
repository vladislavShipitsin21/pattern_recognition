package regex;

public class Regex {
    public static void main(String[] args) {

        String numberReges = "-?[1-9]\\d*";

        String s = "     <=                  3210           ";
        String b = "between -3-4 and -52";

        System.out.println(s.matches("^\\s*(>|<|=|>=|<=)\\s*" + numberReges + "\\s*$"));

        System.out.println(b.matches("^\\s*(BETWEEN|between)\\s" + numberReges + "\\s(AND|and)\\s" + numberReges + "\\s*$"));

    }
}
