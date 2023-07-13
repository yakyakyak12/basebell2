import Dao.TeamDao;
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
    public static void main(String[] args) {


        Connection connection = DBConnection.getInstance();
        StadiumService stadiumService = new StadiumService();
        TeamService teamService = new TeamService();
        PlayerService playerService = new PlayerService();
        Scanner sc = new Scanner(System.in);
        System.out.println("어떤 기능을 요청하시겠습니까?");
        String input = sc.nextLine();

        if (input.startsWith("야구장목록")) {
            stadiumService.야구장목록();
        } else if (input.equals("팀목록")) {
            try {
                List<TeamRespDTO> dtos = TeamService.팀목록();
                System.out.println(dtos);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (input.startsWith("야구장등록")) {
            String[] sParams = input.substring(input.indexOf('?') + 1).split("&");
            String name = null;
            for (String param : sParams) {
                String[] keyValue = param.split("=");
                String key = keyValue[0];
                String value = keyValue[1];

                if (key.equals("name")) {
                    name = value;

                }
            }
            try {
                stadiumService.야구장등록(name);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (input.startsWith("팀등록")) {
            String[] tParams = input.substring(input.indexOf('?') + 1).split("&");
            Integer stadiumId = 0;
            String name = null;

            for (String param : tParams) {
                String[] keyValue = param.split("=");
                String key = keyValue[0];
                String value = keyValue[1];

                if (key.equals("stadiumId")) {
                    stadiumId = Integer.parseInt(value);
                } else if (key.equals("name")) {
                    name = value;
                }
            }

            teamService.팀등록(stadiumId, name);
        }
        if (input.startsWith("선수등록")) {
            String[] pParams = input.substring(input.indexOf('?') + 1).split("&");
            Integer teamId = 0;
            String name = null;
            String position = null;

            for (String param : pParams) {
                String[] keyValue = param.split("=");
                String key = keyValue[0];
                String value = keyValue[1];

                if (key.equals("teamId")) {
                    teamId = Integer.parseInt(value);
                } else if (key.equals("name")) {
                    name = value;
                } else if (key.equals("position")) {
                    position = value;
                }
            }

            playerService.선수등록(teamId, name, position);
        }

    }
}








