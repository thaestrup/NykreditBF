package dk.skov.nykredit.bf;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aogj on 10-07-15.
 *
 * prettymuch only used by newGameDiv.jsp
 *
 * We need to make sure the playersReadyListList is updated before presenting any data.
 */
public class Util {

    public static int INTENSE_LEVEL = 5;

    public static List<List<String>> playersReadyListList = new ArrayList<List<String>>();


    public static String t1Intense;
    public static String t1TeamDiff;

    public static String t2Intense;
    public static String t2TeamDiff;

    public static int t1r1;
    public static int t1r2;
    public static int t1b1;
    public static int t1b2;

    public static int t2r1;
    public static int t2r2;
    public static int t2b1;
    public static int t2b2;

    public static void updateModel(HttpServletRequest request){

        if (request.getParameter("newGame") != null) {
            Util.playersReadyListList = DBHandler.genericSelect("SELECT name FROM tbl_players where playerReady = 1 ORDER BY RAND() LIMIT 8");
        }
        ArrayList<String> dummy = new ArrayList<String>();
        dummy.add("");

        for (int i = Util.playersReadyListList.size(); i < 8; i++) {
            Util.playersReadyListList.add(dummy);
        }


        //----------



        if (request.getParameter("t1BlueWinner") != null) {
            t1Intense = request.getParameter("t1Intense");
            System.out.println("t1Intense=" + t1Intense);
            DBHandler.fightWon(request.getParameter("t1r1"), request.getParameter("t1r2"), request.getParameter("t1b1"), request.getParameter("t1b2"), "blue", (t1Intense.equals("t1b") ? 2 : 1), 1);
        }

        if (request.getParameter("t1RedWinner") != null) {
            t1Intense = request.getParameter("t1Intense");
            System.out.println("t1Intense=" + t1Intense);
            DBHandler.fightWon(request.getParameter("t1r1"), request.getParameter("t1r2"), request.getParameter("t1b1"), request.getParameter("t1b2"), "red", (t1Intense.equals("t1r") ? 2 : 1), 1);
        }

        if (request.getParameter("t2BlueWinner") != null) {
            t2Intense = request.getParameter("t2Intense");
            System.out.println("t2Intense=" + t2Intense);
            DBHandler.fightWon(request.getParameter("t2r1"), request.getParameter("t2r2"), request.getParameter("t2b1"), request.getParameter("t2b2"), "blue", (t2Intense.equals("t2b") ? 2 : 1), 2);
        }

        if (request.getParameter("t2RedWinner") != null) {
            t2Intense = request.getParameter("t2Intense");
            System.out.println("t2Intense=" + t2Intense);
            DBHandler.fightWon(request.getParameter("t2r1"), request.getParameter("t2r2"), request.getParameter("t2b1"), request.getParameter("t2b2"), "red", (t2Intense.equals("t2r") ? 2 : 1), 2);
        }


        //-----------



        System.out.println(Util.playersReadyListList);

        //table 1
        t1r1 = DBHandler.getTablePlayerPoints(0);
        t1r2 = DBHandler.getTablePlayerPoints(2);
        t1b1 = DBHandler.getTablePlayerPoints(1);
        t1b2 = DBHandler.getTablePlayerPoints(3);
        int t1TeamDiffInt = (t1r1 + t1r2) - (t1b1 + t1b2);
        t1TeamDiff = String.valueOf(t1TeamDiffInt).replaceAll("-", "");

        t1Intense = "";
        if (t1TeamDiffInt >= Util.INTENSE_LEVEL) {
            t1TeamDiff += " !!!!!<font color=red>INTENSE</font> " + Util.INTENSE_LEVEL + " points diff exceeded!!!!! Winning blue team takes 2 points!";
            t1Intense = "t1b";
        } else if (t1TeamDiffInt <= Util.INTENSE_LEVEL * -1) {
            t1TeamDiff += " !!!!!<font color=red>INTENSE</font> " + Util.INTENSE_LEVEL + " points diff exceeded!!!!! Winning red team takes 2 points!";
            t1Intense = "t1r";
        }


        //table 2
        t2r1 = DBHandler.getTablePlayerPoints(4);
        t2r2 = DBHandler.getTablePlayerPoints(6);
        t2b1 = DBHandler.getTablePlayerPoints(5);
        t2b2 = DBHandler.getTablePlayerPoints(7);
        int t2TeamDiffInt = (t2r1 + t2r2) - (t2b1 + t2b2);
        t2TeamDiff = String.valueOf(t2TeamDiffInt).replaceAll("-", "");

        t2Intense = "";
        if (t2TeamDiffInt >= Util.INTENSE_LEVEL) {
            t2TeamDiff += "<font color=red>INTENSE</font> !!!!! " + Util.INTENSE_LEVEL + " points diff exceeded!!!!! Winning blue team takes 2 points!";
            t2Intense = "t2b";
        } else if (t2TeamDiffInt <= Util.INTENSE_LEVEL * -1) {
            t2TeamDiff += "<font color=red>INTENSE</font> !!!!! " + Util.INTENSE_LEVEL + " points diff exceeded!!!!! Winning red team takes 2 points!";
            t2Intense = "t2r";
        }


    }
}
