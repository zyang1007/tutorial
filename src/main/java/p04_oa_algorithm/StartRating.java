package p04_oa_algorithm;

public class StartRating {

    private static String solver(String s) {
        float n = Float.parseFloat(s);
        int max = 5;
        StringBuilder sb = new StringBuilder();

        while (n >= 1) {
            sb.append("full ");
            n--;
            max--;
        }

        if (n > 0) {
            sb.append("half ");
            max--;
        }

        while (max > 0) {
            sb.append("empty ");
            max--;
        }

        return sb.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(solver("5"));
        System.out.println(solver("3.8"));
        System.out.println(solver("0.7"));
        System.out.println(solver("0.38"));
        System.out.println(solver("0"));
    }
}
