<%@ page import="java.util.List" %>
<%@ page import="dk.skov.nykredit.bf.DBHandler" %>


<h4>SCOREBOARD ALL-TIMES</h4>
<table align="left" style="border:2px solid black;border-collapse:collapse">
    <tr>
        <th style="border:1px solid black;">#</th>
        <th style="border:1px solid black;">Name</th>
        <th style="border:1px solid black;">points</th>
        <th style="border:1px solid black;">games played</th>
    </tr>
    <%
        int i = 0;
        String sql = "select * from (SELECT name, sum(points), count(*) FROM `tbl_points` WHERE (DATEDIFF(NOW(), `timestamp`) < 99999) group by name order by sum(points) desc) as mainResult UNION ALL select * from (SELECT \"*SUM*\", sum(points), count(*) FROM `tbl_points` WHERE (DATEDIFF(NOW(), `timestamp`) < 20)) as sumResult";
        for (List<String> playerList : DBHandler.genericSelect(sql)) {
    %>
    <tr>
        <td style="border:1px solid black;"><%=++i%></td>
        <td style="border:1px solid black;"><%=playerList.get(0)%></td>
        <td style="border:1px solid black;"><%=playerList.get(1)%></td>
        <td style="border:1px solid black;"><%=playerList.get(2)%></td>
    </tr>
    <%
        }
    %>
</table>

<br>
<h4>SCOREBOARD ONE ROLLING MONTH</h4>
<table align="left" style="border:2px solid black;border-collapse:collapse">
    <tr>
        <th style="border:1px solid black;">#</th>
        <th style="border:1px solid black;">Name</th>
        <th style="border:1px solid black;">points</th>
        <th style="border:1px solid black;">games played</th>
    </tr>
    <%
        i = 0;
        sql = "select * from (SELECT name, sum(points), count(*) FROM `tbl_points` WHERE (DATEDIFF(NOW(), `timestamp`) < 30) group by name order by sum(points) desc) as mainResult UNION ALL select * from (SELECT \"*SUM*\", sum(points), count(*) FROM `tbl_points` WHERE (DATEDIFF(NOW(), `timestamp`) < 20)) as sumResult";
        for (List<String> playerList : DBHandler.genericSelect(sql)) {
    %>
    <tr>
        <td style="border:1px solid black;"><%=++i%></td>
        <td style="border:1px solid black;"><%=playerList.get(0)%></td>
        <td style="border:1px solid black;"><%=playerList.get(1)%></td>
        <td style="border:1px solid black;"><%=playerList.get(2)%></td>
    </tr>
    <%
        }
    %>
</table>

<br>
<h4>SCOREBOARD ONE ROLLING WEAK</h4>
<table align="left" style="border:2px solid black;border-collapse:collapse">
    <tr>
        <th style="border:1px solid black;">#</th>
        <th style="border:1px solid black;">Name</th>
        <th style="border:1px solid black;">points</th>
        <th style="border:1px solid black;">games played</th>
    </tr>
    <%
        i = 0;
        sql = "select * from (SELECT name, sum(points), count(*) FROM `tbl_points` WHERE (DATEDIFF(NOW(), `timestamp`) < 7) group by name order by sum(points) desc) as mainResult UNION ALL select * from (SELECT \"*SUM*\", sum(points), count(*) FROM `tbl_points` WHERE (DATEDIFF(NOW(), `timestamp`) < 20)) as sumResult";
        for (List<String> playerList : DBHandler.genericSelect(sql)) {
    %>
    <tr>
        <td style="border:1px solid black;"><%=++i%></td>
        <td style="border:1px solid black;"><%=playerList.get(0)%></td>
        <td style="border:1px solid black;"><%=playerList.get(1)%></td>
        <td style="border:1px solid black;"><%=playerList.get(2)%></td>
    </tr>
    <%
        }
    %>
</table>
