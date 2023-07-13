package Service;

import Dao.PlayerDao;
import Dao.StadiumDao;
import Dao.TeamDao;
import Model.Player;
import Model.Stadium;
import db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//구현해야할 기능 1. 선수 등록 2. 팀별 선수 목록
public class PlayerService {
    public void 선수등록(String input) {
        Connection connection = DBConnection.getInstance();
        PlayerDao playerDao = new PlayerDao(connection);

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
        try {
            playerDao.createPlayer(teamId, name, position);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void 선수목록(String input) {
        Connection connection = DBConnection.getInstance();
        PlayerDao playerDao = new PlayerDao(connection);

        String[] pParams = input.substring(input.indexOf('?') + 1).split("&");
        Integer stadiumId = 0;

        try {
            for (String param : pParams) {
                String[] keyValue = param.split("=");
                String key = keyValue[0];
                String value = keyValue[1];

                if (key.equals("teamId")) {
                    stadiumId = Integer.valueOf(value);
                }
                playerDao.findAll(stadiumId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


//        System.out.println(stadiumList);
}


