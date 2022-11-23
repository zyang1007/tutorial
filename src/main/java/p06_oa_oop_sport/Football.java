package p06_oa_oop_sport;

import java.util.Arrays;

public class Football implements Sport {
    private int[] playerIDs;

    public Football() {
        this.playerIDs = new int[11];
        Arrays.fill(this.playerIDs, 1);
        System.out.println("A new football team has been formed!");
    }

    @Override
    public void retirePlayer(int id) {
        playerIDs[id - 1] = -1;  // [id - 1] -> zero-indexed array, sets the id to  -1
        System.out.println("Player has already retired!");
    }

    public void playerTransfer(int fee, int id) {
        if (playerIDs[id - 1] == 1) {
            System.out.println("Player with id{" + id + "} has been transferred with fee $" + fee);
        } else {
            System.out.println("Player has already retired!");
        }
    }
}
