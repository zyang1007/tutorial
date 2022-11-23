package p06_oa_oop_sport;

import java.util.Arrays;

public class Cricket implements Sport{
    private int[] playerIDs;

    public Cricket() {
        this.playerIDs = new int[11];
        Arrays.fill(playerIDs, 1);
        System.out.println("A new cricket team has been formed!");
    }

    @Override
    public void retirePlayer(int id) {
        playerIDs[id - 1] = -1;
        System.out.println("Player has already retired!");
    }
}
