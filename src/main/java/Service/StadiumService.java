package Service;


import Dao.StadiumDao;
import Model.Stadium;
import db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//구현해야할 기능 1. 야구장 등록 2. 전체 야구장 목록 보기
public class StadiumService {


    private Connection connection;
    public StadiumService(Connection connection) {
        this.connection = connection;
    }

    public void 야구장목록(){
        StadiumDao stadiumDao = new StadiumDao(connection);
        List<Stadium> stadiumList = stadiumDao.getAllStadiums();
        System.out.println(stadiumList);
    }

    public void 야구장등록(String stadiumName) throws SQLException {
        StadiumDao stadiumDao = new StadiumDao(connection);
        stadiumDao.createStadium(stadiumName);

    }
}
