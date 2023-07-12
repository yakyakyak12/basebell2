import Dao.StadiumDao;
import Dao.TeamDao;
import Model.Stadium;
import Model.Team;
import Service.StadiumService;
import Service.TeamService;
import db.DBConnection;
import dto.TeamRespDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("어떤 기능을 요청하시겠습니까?");
        String input = sc.nextLine();
        String[] response = new String[input.length()];
        String[] rss = new String[input.length()];
        //배열이 가변적일 거 같은데 리스트로 쓰면 복잡해서 일단 input.length로 설정해봄...

        if(!input.contains("?")){
            input = input;
        } else if (input.contains("?")) {
            response[0] = Arrays.toString(input.split("\\?"));
            //response[0]은 사용자의 요구
            rss = response[1].split("=");
            //rss[홀수]는 사용자의 입력사항 ex) 야구장이름 = 잠실야구장

        }
        //이 코드를 도저히 깔끔하게 못 만들겠어가지고... 나중에 따로 설명드릴게요...ㅠ


        try {
            if (input.equals("팀목록")) {
                TeamService teamService = new TeamService();
                teamService.팀목록();
                List<TeamRespDTO> dtos = TeamRespDTO.dtos();
            } else if (response[0].equals("야구장등록")) {
                StadiumService stadiumService = new StadiumService();
                stadiumService.야구장등록(rss[1]);
                TeamService teamService = new TeamService();
                teamService.팀목록();

            }

        }catch (Exception e) {
            System.err.println("예외 발생: " + e.getMessage());
        }


    }
}


