import Dao.TeamDao;
import Model.Team;
import Service.OutPlayerService;
import Service.PlayerService;
import db.DBConnection;
import dto.TeamRespDTO;
import Service.StadiumService;
import Service.TeamService;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        System.out.println("어떤 기능을 요청하시겠습니까?");



        //0. 모든 객체 만들기
        Connection connection = DBConnection.getInstance();
        StadiumService stadiumService = new StadiumService(connection);
        TeamService teamService = new TeamService(connection);
        PlayerService playerService = new PlayerService(connection);
        OutPlayerService outPlayerService = new OutPlayerService(connection);



        //1. 파싱
        String input = sc.nextLine();
        String path = "";
        String body = "";

        if(!input.contains("?")){
            path = input;
        } else if(input.contains("?")){
            path = input.split("\\?")[0];
            body = input.split("\\?")[1];

        }

        if(path.equals("야구장등록")){
            String name = body.split("=")[1];
            stadiumService.야구장등록(name);
        } else if (path.equals("야구장목록")) {
            stadiumService.야구장목록();
        } else if (path.equals("팀등록")) {
            String[] body2 = body.split("&");
            int stadiumId = Integer.parseInt(body2[0].split("=")[1]);
            String name = body2[1].split("=")[1];

            teamService.팀등록(stadiumId, name);
        } else if (path.equals("팀목록")) {
            teamService.팀목록();
            
        } else if (path.equals("선수등록")) {
            String[] body2 = body.split("&");
            int teamId = Integer.parseInt(body2[0].split("=")[1]);
            String name = body2[1].split("=")[1];
            String position = body2[2].split("=")[1];

            playerService.선수등록(teamId, name, position);

        } else if (path.equals("선수목록")) {
            int teamId = Integer.parseInt(body.split("=")[1]);

            //playerService.선수목록(teamId);
        } else if (path.equals("퇴출등록")) {
            String[] body2 = body.split("&");
            int playerId = Integer.parseInt(body2[0].split("=")[1]);
            String reason = body2[1].split("=")[1];

            //outPlayerService.퇴출등록(playerId, reason);
        } else if (path.equals("퇴출목록")) {

            outPlayerService.퇴출목록();
        } else if (path.equals("포지션별목록")) {
            playerService.포지션별목록();
        }


    }}