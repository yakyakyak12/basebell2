package Dao;

import Model.Team;
import dto.TeamRespDTO;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TeamDao {
    private Connection connection;

    public TeamDao(Connection connection) {
        this.connection = connection;
    }

    // 팀 생성
    public void createTeam(int stadiumId, String teamName) throws SQLException {
        String query = "INSERT INTO team_tb (stadium_id, team_name, team_created_at) VALUES (?, ?, now())";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stadiumId);
            statement.setString(2, teamName);
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

    // 팀 상세보기
    public Team getTeamByTeamName(String teamName) throws SQLException {
        String query = "SELECT * FROM team_tb WHERE team_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,teamName);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return buildTeamFromResultSet(rs);
                }
            }
        }
        return null; // Team not found
    }

    // 전체 팀 조회
    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        String query = "SELECT * FROM team_tb";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    Team team = buildTeamFromResultSet(resultSet);
                    teams.add(team);
                }
            }
        }
        return teams;
    }

    // 팀 이름 수정 - 쓸 일이 없을 거 같은데 혹시 몰라서 일단 살려두겠습니다
    public void updateTeam(String teamName, String newTeamName) throws SQLException {
        String query = "UPDATE team_tb SET team_name = ?  WHERE team_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newTeamName);
            statement.setString(2, teamName);
            statement.executeUpdate();
        }
    }

    // 팀 삭제
    public void deleteTeam(String teamName) throws SQLException {
        String query = "DELETE FROM team_tb WHERE team_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, teamName);
            statement.executeUpdate();
        }
    }

    //팀 전체 목록 조회
    public List<TeamRespDTO> findAll() throws SQLException {
        List<TeamRespDTO> dtos = new ArrayList<>();
        String sql = "select *\n" +
                "from team_tb tt \n" +
                "inner join stadium_tb st on tt.stadium_id = st.stadium_id \n";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try(ResultSet rs = statement.executeQuery()){
                while (rs.next()) {
                    TeamRespDTO dto = TeamRespDTO.builder()
                            .teamId(rs.getInt("team_id"))
                            .teamName(rs.getString("team_name"))
                            .teamCreatedAt(rs.getTimestamp("team_created_at"))
                            .sId(rs.getInt("stadium_id"))
                            .sName(rs.getString("stadium_name"))
                            .sCreatedAt(rs.getTimestamp("stadium_created_at"))
                            .build();
                    dtos.add(dto);
                }
            }
        }
        return dtos;
    }

    private Team buildTeamFromResultSet(ResultSet resultSet) throws SQLException {
        int teamId = resultSet.getInt("team_id");
        int stadiumId = resultSet.getInt("stadium_id");
        String teamName = resultSet.getString("team_name");
        Timestamp teamCreatedAt = resultSet.getTimestamp("team_created_at");

        return Team.builder()
                .teamId(teamId)
                .stadiumId(stadiumId)
                .teamName(teamName)
                .teamCreatedAt(teamCreatedAt)
                .build();
    }
}