package Dao;

import Model.Player;
import dto.OutPlayerRespDTO;
import dto.PositionRespDTO;
import dto.TeamRespDTO;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PlayerDao {
    public Connection connection;

    public PlayerDao(Connection connection) {
        this.connection = connection;
    }

    // 생성 , 수정 , 삭제,
    // 플레이어 생성
    public void createPlayer(Integer teamId, String player_name, String playerPosition) {
        String query = "insert into player_tb (team_id, player_name, player_position, player_created_at) value(?, ?, ?, now())";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, teamId);
            statement.setString(2, player_name);
            statement.setString(3, playerPosition);
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // 플레이어 삭제
    public void deletePlayer(String playerName) {
        String query = "delete from player_tb where player_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, playerName);
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 선수 명단 조회

//    public List<OutPlayerRespDTO> findPlayerWithOutPlayer() throws SQLException{
//
//        List<OutPlayerRespDTO> dtos = new ArrayList<>();
//        String sql = "select pt.player_id, pt.player_name, pt.player_position," +
//                "opt.out_player_reason oreason, opt.out_player_created_at otime " +
//                "from out_player_tb opt " +
//                "right outer join player_tb pt on pt.player_id = opt.player_id";
//
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            try(ResultSet rs = statement.executeQuery()){
//
//                while (rs.next()) {
//
//                    OutPlayerRespDTO dto = OutPlayerRespDTO.builder()
//                            .pId(rs.getInt(1))
//                            .pName(rs.getString(2))
//                            .pPosition(rs.getString(3))
//                            .outReason(rs.getString(4))
//                            .outTimestamp(rs.getTimestamp(5))
//                            .build();
//                    dtos.add(dto);
//                }
//            }
    public List<Player> findAll(Integer stadiumId){
        List<Player> playerList = new ArrayList<>();
        String sql = "select player_id, player_name, player_position, player_created_at from player_tb where team_id = ? order by player_id asc;";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, stadiumId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Player player = new Player(

                        rs.getInt("player_id"),
                        rs.getInt("team_id"),
                        rs.getString("player_name"),
                        rs.getString("player_position"),
                        rs.getTimestamp("player_created_at")
                );
                playerList.add(player);
                System.out.println("=======================");
                System.out.println("Player ID: " + player.getPlayerId());
                System.out.println("Player Name: " + player.getPlayerName());
                System.out.println("Player Position: " + player.getPlayerPosition());
                System.out.println("Player Created At: " + player.getPlayerCreatedAt());

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return playerList;

    }


    //포지션별 팀 야구 선수
    public List<PositionRespDTO> findPlayerByPosition() throws SQLException{
        List<PositionRespDTO> dtos = new ArrayList<>();
        String sql = "select " +
                "pt2.player_position as 포지션," +
                "if(pt.team_id=1, pt.player_name, null) 삼성," +
                "if(pt.team_id=2, pt.player_name, null) 롯데," +
                "if(pt.team_id=3, pt.player_name, null) 광주 " +
                "from player_tb pt " +
                "join player_tb pt2 on pt.player_id = pt2.player_id";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                PositionRespDTO dto = PositionRespDTO.builder()
                        .position(rs.getString(1))
                        .samSung(rs.getString(2))
                        .lotte(rs.getString(3))
                        .gwangJu(rs.getString(4))
                        .build();
                dtos.add(dto);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dtos;

    }
}

