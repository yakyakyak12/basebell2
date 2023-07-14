package Dao;

import Model.OutPlayer;
import Model.Player;
import dto.OutPlayerRespDTO;
import dto.TeamRespDTO;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OutPlayerDao {
    public Connection connection;

    public OutPlayerDao(Connection connection) {
        this.connection = connection;
    }

    // 퇴출 선수 등록
    public void createOutPlayer(Integer playerId, String outPlayerReason) {
        String query = "insert into out_player_tb(player_id, out_player_reason, out_player_created_at) values(?,?, now())";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, playerId);
            statement.setString(2, outPlayerReason);
            int result = statement.executeUpdate();
            if(result == 1){
                System.out.println("성공");
            }else{
                System.out.println("실패");
            }
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 퇴출 선수 삭제
    public void deleteOutPlayer(String outPlayerId) throws SQLException {
        String query = "delete from out_player_tb where out_player_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, outPlayerId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public List<OutPlayer> findAll() {
        List<OutPlayer> outPlayerList = new ArrayList<>();
        String sql = "select * from out_player_tb order by out_player_created_at asc";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                OutPlayer outPlayer = new OutPlayer(
                        rs.getInt("out_player_id"),
                        rs.getInt("player_id"),
                        rs.getString("out_player_reason"),
                        rs.getTimestamp("out_player_created_at")

                );
                outPlayerList.add(outPlayer);

            }
            System.out.println(outPlayerList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outPlayerList;

    }

    //선수 퇴출 목록
    public List<OutPlayerRespDTO> findPlayerWithOutPlayer() throws SQLException{

        List<OutPlayerRespDTO> dtos = new ArrayList<>();
        String sql = "select pt.player_id, pt.player_name, pt.player_position," +
                "opt.out_player_reason oreason, opt.out_player_created_at otime " +
                "from out_player_tb opt " +
                "right outer join player_tb pt on pt.player_id = opt.player_id";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            try(ResultSet rs = statement.executeQuery()){

                while (rs.next()) {

                    OutPlayerRespDTO dto = OutPlayerRespDTO.builder()
                            .pId(rs.getInt(1))
                            .pName(rs.getString(2))
                            .pPosition(rs.getString(3))
                            .outReason(rs.getString(4))
                            .outTimestamp(rs.getTimestamp(5))
                            .build();
                    dtos.add(dto);
                }
            }

        }
        return dtos;

    }
}
