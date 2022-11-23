package p06_oa_oop_sport;

public class Client {

    public static void main(String[] args) {
        Football football = new Football();
        Cricket cricket = new Cricket();

        cricket.retirePlayer(1);
        football.retirePlayer(2);

        int[] ages = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        cricket.calculateAvgAge(ages);
        football.calculateAvgAge(ages);

        football.playerTransfer(200, 2);
        football.playerTransfer(200, 9);
    }
}
