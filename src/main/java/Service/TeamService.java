package Service;

import Dao.TeamDao;
import Model.Stadium;
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

    private Connection connection;
    private TeamDao teamDao;

    public TeamService(Connection connection) {
        this.connection = connection;
        teamDao = new TeamDao(connection);
    }



    public void 팀등록(Integer stadiumId, String teamName) {
        try {
            teamDao.createTeam(stadiumId, teamName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void 팀목록() throws SQLException {
        List<TeamRespDTO> teamList = teamDao.findAll();
        System.out.println(teamList);

    }
}









