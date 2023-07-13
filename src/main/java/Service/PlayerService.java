package Service;

import Dao.PlayerDao;
import Dao.TeamDao;
import db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

//구현해야할 기능 1. 선수 등록 2. 팀별 선수 목록
public class PlayerService {
    public void 선수등록(Integer teamId, String teamName, String position){
        Connection connection = DBConnection.getInstance();
        PlayerDao playerDao = new PlayerDao(connection);
        try {
            playerDao.createPlayer(teamId, teamName, position);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
