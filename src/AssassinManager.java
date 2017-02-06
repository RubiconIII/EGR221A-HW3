import java.util.*;

/**
 * Created by curti_000 on 1/31/2017.
 */

/** Manages the pool of killers and graveyard of dead
participants in assassin game. Also allows the user
to decide who should be killed, noting the winner.*/

public class AssassinManager {

    private AssassinNode poolFront;
    private AssassinNode gYardFront;


    /* names cannot be null AND the list is not empty, throws IllegalArgumentException
     creates linked nodes of the players using the user chosen text*/
    public AssassinManager(List<String> names) {
        if (names == null || names.size() == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = names.size() - 1; i >= 0; i--) {
            poolFront = new AssassinNode(names.get(i), poolFront);
        }
        gYardFront = null;
    }

    // Prints to who each player is stalking in the kill pool
    public void printKillRing() {
        AssassinNode current = poolFront;
        while (current.next != null) {
            System.out.println("  " + current.name + " is stalking " + current.next.name);
            current = current.next;
        }
        System.out.println("  " + current.name + " is stalking " + poolFront.name);
    }

    //Prints who killed each player
    public void printGraveyard() {
        AssassinNode current = gYardFront;
        while (current != null) {
            System.out.println("  " + current.name + " was killed by " + current.killer);
            current = current.next;
        }
    }

    // pre: returns true if (name) is still in the kill pool. post: else false
    public boolean killRingContains(String name) {
        AssassinNode current = poolFront;
        return contains(name, current);
    }

    // pre: returns true if name is in the gYardFront pool. post: else false
    public boolean graveyardContains(String name) {
        AssassinNode current = gYardFront;
        return contains(name, current);
    }

    // returns true if there is only one player remains. else false
    public boolean isGameOver() {
        return poolFront.next == null;
    }

    // returns winner if game is over else null (shouldn't be reached)
    public String winner() {
        if (isGameOver()) {
            return poolFront.name;
        } else {
            return null;
        }
    }

    /* if game is over, throws IllegalStateException
    if player is not in kill pool throws IllegalArgumentException
    kills the passed in name passed in and transitions player to graveyard.*/
    public void kill(String name) {
        if (isGameOver()) {
            throw new IllegalStateException();
        } else if (!killRingContains(name)) {
            throw new IllegalArgumentException();
        }

        AssassinNode current = poolFront;

        /* If person to be killed is front of kill pool, kill the player and set killer as last in the kill pool.
         Victim sent to the graveyard.
         Else, search for player
         set killer as player before victim. Send victim to the graveyard.*/
        if (poolFront.name.equalsIgnoreCase(name)) {
            gYardFront = new AssassinNode(poolFront.name, gYardFront);
            while (current.next != null) {
                current = current.next;
            }
            gYardFront.killer = current.name;
            poolFront = poolFront.next;
        } else {
            while (!current.next.name.equalsIgnoreCase(name)) {
                current = current.next;
            }
            gYardFront = new AssassinNode(current.next.name, gYardFront);
            gYardFront.killer = current.name;
            current.next = current.next.next;
        }
    }

    // will return true if name is within is contained within current. else false
    private boolean contains(String name, AssassinNode current) {
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return true;
            } else {
                current = current.next;
            }
        }
        return false;
    }

    /**
     * Each AssassinNode object represents a single node in a linked list
     * for a game of Assassin.
     */
   private static class AssassinNode {
        public final String name;  // this person's name
        public String killer;      // name of who killed this person (null if alive)
        public AssassinNode next;  // next node in the list (null if none)

        /**
         * Constructs a new node to store the given name and no next node.
         */
        public AssassinNode(String name) {
            this(name, null);
        }

        /**
         * Constructs a new node to store the given name and a reference
         * to the given next node.
         */
        public AssassinNode(String name, AssassinNode next) {
            this.name = name;
            this.killer = null;
            this.next = next;
        }
    }
}
