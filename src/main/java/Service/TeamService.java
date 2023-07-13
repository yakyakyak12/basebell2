package Service;

import Dao.TeamDao;
import db.DBConnection;
import lombok.AllArgsConstructor;
import dto.TeamRespDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
//구현해야할 기능 1. 팀 등록 2. 전체 팀 목록
public class TeamService {



    public void 팀등록(String input){
        Connection connection = DBConnection.getInstance();
        TeamDao teamDao = new TeamDao(connection);
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
        try {
        teamDao.createTeam(stadiumId, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




    public static List<TeamRespDTO> 팀목록() throws SQLException {
        Connection connection = DBConnection.getInstance();
        List<TeamRespDTO> dtos = new ArrayList<>();
        String sql = "select *\n" +
                "from team_tb tt \n" +
                "inner join stadium_tb st on tt.stadium_id = st.stadium_id \n";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try(ResultSet rs = statement.executeQuery()){
                while (rs.next()) {
                    TeamRespDTO dto = TeamRespDTO.builder()
                            .teamId(rs.getInt("team_id"))
                            .teamName(rs.getString("team_name"))
                            .teamCreatedAt(rs.getTimestamp("team_created_at"))
                            .sId(rs.getInt("stadium_id"))
                            .sName(rs.getString("stadium_name"))
                            .sCreatedAt(rs.getTimestamp("stadium_created_at"))
                            .build();
                    dtos.add(dto);
                }
            }
        }
        return dtos;


    }}











