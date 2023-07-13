package Service;

import Dao.OutPlayerDao;
import Dao.PlayerDao;
import Dao.TeamDao;
import db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

//구현해야할 기능 1. 퇴출 선수 등록 2. 퇴출 선수 목록
public class OutPlayerService {
    public void 퇴출등록(String input){
        Connection connection = DBConnection.getInstance();
        OutPlayerDao outPlayerDao = new OutPlayerDao(connection);
        PlayerDao playerDao = new PlayerDao(connection);

        String[] tParams = input.substring(input.indexOf('?') + 1).split("&");
        Integer playerId = 0;
        String reason = null;

        for (String param : tParams) {
            String[] keyValue = param.split("=");
            String key = keyValue[0];
            String value = keyValue[1];

            if (key.equals("playerId")) {
                playerId = Integer.parseInt(value);
            } else if (key.equals("reason")) {
                reason = value;
            }
        }
        try {
            outPlayerDao.createOutPlayer(playerId, reason);
            playerDao.updatePlayer(playerId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
