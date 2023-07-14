package Service;

import Dao.PlayerDao;
import Dao.TeamDao;
import dto.PositionRespDTO;
import dto.TeamRespDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//구현해야할 기능 1. 선수 등록 2. 팀별 선수 목록
public class PlayerService {
    private Connection connection;


    public PlayerService(Connection connection) {
        this.connection = connection;
    }

    public void 선수등록(int teamId, String name, String position) {
        PlayerDao playerDao = new PlayerDao(connection);
        playerDao.createPlayer(teamId, name, position);
    }
    public void 선수목록(int teamId) {
        PlayerDao playerDao = new PlayerDao(connection);
        playerDao.findAll(teamId);
    }

        public void 포지션별목록() throws SQLException {
        PlayerDao playerDao = new PlayerDao(connection);
        List<PositionRespDTO> postionList = playerDao.findPlayerByPosition();
        System.out.println(postionList);
    }
}