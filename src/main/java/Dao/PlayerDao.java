package Dao;

import Model.Player;
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
            int result = statement.executeUpdate();
            if(result == 1){
                System.out.println("성공");
            }else{
                System.out.println("실패");
            }
        }catch (SQLException e){
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

    // 플레이어 업데이트
    public void updatePlayer(Integer playerId) throws SQLException {
        String query = "UPDATE player_tb SET team_id = null WHERE player_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, playerId);
            statement.executeUpdate();
        }
    }

    // 선수 명단 조회
    public List<Player> findAll(){
        List<Player> playerList = new ArrayList<>();
        String sql = "select * from player_tb order by team_id asc;";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
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

            }
            System.out.println(playerList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return playerList;

    }
}












// 플레이어 상세보기
//    public Player getPlayerByTeamName(String teamName) throws SQLException {
//        String query = "SELECT * FROM team_tb WHERE team_name = ?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1,teamName);
//            try (ResultSet rs = statement.executeQuery()) {
//                if (rs.next()) {
//                    return buildTeamFromResultSet(rs);
//                }
//            }
//        }
//        return null; // Team not found
//    }
//
//    // 전체 팀 조회
//    public List<Team> getAllTeams() throws SQLException {
//        List<Team> teams = new ArrayList<>();
//        String query = "SELECT * FROM team_tb";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            try(ResultSet resultSet = statement.executeQuery()){
//                while (resultSet.next()) {
//                    Team team = buildTeamFromResultSet(resultSet);
//                    teams.add(team);
//                }
//            }
//        }
//        return teams;
//    }
//
//    // 팀 이름 수정 - 쓸 일이 없을 거 같은데 혹시 몰라서 일단 살려두겠습니다
//    public void updateTeam(String teamName, String newTeamName) throws SQLException {
//        String query = "UPDATE team_tb SET teamName = ?  WHERE teamName = ?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, newTeamName);
//            statement.setString(2, teamName);
//            statement.executeUpdate();
//        }
//    }
//
//
//
//    private Player buildTeamFromResultSet(ResultSet resultSet) throws SQLException {
//        int teamId = resultSet.getInt("team_id");
//        int stadiumId = resultSet.getInt("stadium_id");
//        String teamName = resultSet.getString("team_name");
//        Timestamp teamCreatedAt = resultSet.getTimestamp("team_created_at");
//
//        return Player.builder()
//                .teamId(teamId)
//                .stadiumId(stadiumId)
//                .teamName(teamName)
//                .teamCreatedAt(teamCreatedAt)
//                .build();
//    }
//}
//
//
//
//
//
