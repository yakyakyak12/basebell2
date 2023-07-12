package Dao;

import Model.Team;
import lombok.Getter;
import db.DBConnection;
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
        public void createTeam(int teamId, int stadiumId, String teamName, Timestamp teamCreatedAt) throws SQLException {
            String query = "INSERT INTO team_tb (team_id, stadium_id, team_name, team_created_at) VALUES (?, ?, ?, now())";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, teamId);
                statement.setInt(2, stadiumId);
                statement.setString(3, teamName);
                statement.executeUpdate();
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


