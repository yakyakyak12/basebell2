package Service;


import Dao.StadiumDao;
import Model.Stadium;
import db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

//구현해야할 기능 1. 야구장 등록 2. 전체 야구장 목록 보기
public class StadiumService {

    public void 야구장등록(String stadiumName) throws SQLException {
        Connection connection = DBConnection.getInstance();
        StadiumDao stadiumDao = new StadiumDao(connection);
        stadiumDao.createStadium(stadiumName);

    }
}
