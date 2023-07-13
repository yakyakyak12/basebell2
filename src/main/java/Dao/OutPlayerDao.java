package Dao;

import Model.OutPlayer;
import Model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // 퇴출 선수 삭제
    public void deleteOutPlayer(String outPlayerId) throws SQLException {
        String query = "delete from out_player_tb where out_player_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, outPlayerId);
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<OutPlayer> findAll(){
        List<OutPlayer> outPlayerList = new ArrayList<>();
        String sql = "select * from Out_player_tb order by out_player_created_at asc;";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                OutPlayer outPlayer = new OutPlayer(
                        rs.getInt("out_player_id"),
                        rs.getInt("player_id"),
                        rs.getString("out_player_reason"),
                        rs.getTimestamp("out_player_created_at")

                );
                outPlayerList.add(outPlayer);

            }
            System.out.println(outPlayerList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return outPlayerList;

    }
}