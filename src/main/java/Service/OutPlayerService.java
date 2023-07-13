package Service;

import Dao.OutPlayerDao;
import dto.OutPlayerRespDTO;
import dto.TeamRespDTO;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
//구현해야할 기능 1. 퇴출 선수 등록 2. 퇴출 선수 목록
public class OutPlayerService {

    private Connection connection;
    private OutPlayerDao outPlayerDao;


    public OutPlayerService(Connection connection) {
        this.connection = connection;
        outPlayerDao = new OutPlayerDao(connection);
    }

    public void 퇴출목록() throws SQLException {


       List<OutPlayerRespDTO> outList = outPlayerDao.findPlayerWithOutPlayer();
        System.out.println(outList);
    }

    public void 퇴출등록(int playerId, String reason){
        OutPlayerDao outPlayerDao = new OutPlayerDao(connection);
        try {
            outPlayerDao.createOutPlayer(playerId, reason);
        } catch (Exception e) {
            e.printStackTrace();
        }




}}
