import Dao.TeamDao;
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
    public static void main(String[] args) {

        // 생성자
        Connection connection = DBConnection.getInstance();
        StadiumService stadiumService = new StadiumService();
        TeamService teamService = new TeamService();
        PlayerService playerService = new PlayerService();
        OutPlayerService outPlayerService = new OutPlayerService();

        // 사용자로부터 입력 받고 값을 담음
        Scanner sc = new Scanner(System.in);
        System.out.println("어떤 기능을 요청하시겠습니까?");
        String input = sc.nextLine();


        // 목록보기
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

        if(input.startsWith("선수목록")){
           playerService.선수목록(input);
        }

//         ==============================================================================
        // 야구장등록
        if (input.startsWith("야구장등록")) {
            try {
                stadiumService.야구장등록(input);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        // 팀등록
        if (input.startsWith("팀등록")) {
            teamService.팀등록(input);
        }
        // 선수등록
        if (input.startsWith("선수등록")) {
            playerService.선수등록(input);
      }
        // 퇴출등록
        if (input.startsWith("퇴출등록")) {
            outPlayerService.퇴출등록(input);

    }
}}








