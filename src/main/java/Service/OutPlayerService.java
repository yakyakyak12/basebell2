package Service;

import Dao.OutPlayerDao;
import Dao.PlayerDao;
import Dao.TeamDao;
import db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

//구현해야할 기능 1. 퇴출 선수 등록 2. 퇴출 선수 목록
public class OutPlayerService {
    public void 퇴출등록(Integer playerId, String reason){
        Connection connection = DBConnection.getInstance();
        OutPlayerDao outPlayerDao = new OutPlayerDao(connection);
        PlayerDao playerDao = new PlayerDao(connection);
        try {
            outPlayerDao.createOutPlayer(playerId, reason);
            playerDao.updatePlayer(playerId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
