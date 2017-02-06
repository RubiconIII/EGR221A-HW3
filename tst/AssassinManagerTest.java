import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by curti_000 on 2/1/2017.
 */
public class AssassinManagerTest {

    public List<String> listMakerHelper() {
        List<String> list = new ArrayList<>();
        list.add("Adam");
        list.add("Billy");
        list.add("Chris");
        list.add("Doug");
        list.add("Eric");
        list.add("Frank");

        return list;
    }

    @Test
    public void constructorTest() {
        List<String> list = listMakerHelper();
        AssassinManager manager = new AssassinManager(list);

        manager.printKillRing();
    }

    @Test
    public void constructorNegativeTest() {
        try {
            List<String> fail = new ArrayList<>();
            AssassinManager manager = new AssassinManager(fail);
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }
    }

        @Test
        public void isGameOverTest () {
            List<String> onePlayer = new ArrayList<>();
            onePlayer.add("Adam");
            AssassinManager manager = new AssassinManager(onePlayer);
            Assert.assertTrue(manager.isGameOver());
        }

        @Test
        public void winnerTest () {
            List<String> winMan = new ArrayList<>();
            winMan.add("Adam");

            AssassinManager manager = new AssassinManager(winMan);
            Assert.assertTrue(manager.winner().equals("Adam"));
        }

        @Test
        public void killRingContainsTest () {
            List<String> list = listMakerHelper();
            AssassinManager manager = new AssassinManager(list);

            Assert.assertTrue(manager.killRingContains("adam"));
            Assert.assertTrue(manager.killRingContains("BillY"));
            Assert.assertTrue(manager.killRingContains("CHRIS"));
            Assert.assertTrue(manager.killRingContains("doug"));
            Assert.assertTrue(manager.killRingContains("ErIc"));
            Assert.assertTrue(manager.killRingContains("FRANK"));
            Assert.assertTrue(manager.killRingContains("aDAM"));
        }

        @Test
        public void killRingContainsNegativeTest() {
            List<String> list = listMakerHelper();
            AssassinManager manager = new AssassinManager(list);

            Assert.assertFalse(manager.killRingContains("aaron"));
            Assert.assertFalse(manager.killRingContains("bob"));
            Assert.assertFalse(manager.killRingContains("carl"));
            Assert.assertFalse(manager.killRingContains("Dan"));
            Assert.assertFalse(manager.killRingContains("EWAN"));
            Assert.assertFalse(manager.killRingContains("FerB"));
        }
    @Test
    public void killTest () {
        List<String> list = listMakerHelper();
        AssassinManager testMan = new AssassinManager(list);

        testMan.kill("Adam");
        Assert.assertFalse(testMan.killRingContains("aDam"));

        testMan.kill("Billy");
        Assert.assertFalse(testMan.killRingContains("BILLY"));

        testMan.kill("Chris");
        Assert.assertFalse(testMan.killRingContains("chris"));

        testMan.kill("Doug");
        Assert.assertFalse(testMan.killRingContains("DOUG"));

        testMan.kill("Eric");
        Assert.assertFalse(testMan.killRingContains("ERIC"));

    }

        @Test
        public void killNegativeTest () {
            try {
                List<String> list1 = new ArrayList<>();
                list1.add("Adam");
                AssassinManager manTest = new AssassinManager(list1);
                manTest.kill("a");
                Assert.fail();
            } catch (IllegalStateException e) {
            }
        }


        @Test
        public void graveyardContainsTest () {
            List<String> list = listMakerHelper();
            AssassinManager testMan = new AssassinManager(list);

            testMan.printKillRing();
            testMan.kill("adam");
            testMan.printGraveyard();
            Assert.assertTrue(testMan.graveyardContains("adam"));

        }

        @Test
        public void graveyardContainsNegativeTest() {
            List<String> list = listMakerHelper();

            AssassinManager manager = new AssassinManager(list);
            manager.kill("Adam");
            Assert.assertFalse(manager.graveyardContains("chris"));

            manager.kill("billy");
            Assert.assertFalse(manager.graveyardContains("doug"));
        }


    }



