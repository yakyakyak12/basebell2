package Dao;


import Model.Stadium;
import Model.Team;
import lombok.Builder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//public class Stadium {
//    private Integer stadiumId;
//    private String stadiumName;
//    private Timestamp stadiumCreatedAt;

@Builder
public class StadiumDao {
    private Connection connection;

    public StadiumDao(Connection connection) {
        this.connection = connection;
    }

    // 야구장 생성
    public void createStadium(int stadiumId, String stadiumName, Timestamp stadiumCreatedAt) throws SQLException {
        String query = "INSERT INTO stadium_tb (stadium_id, stadium_name, stadium_created_at) VALUES (?, ?, now())";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stadiumId);
            statement.setString(2, stadiumName);
            statement.executeUpdate();
        }
    }

    // 야구장 상세보기
    public Stadium getStadiumByStadiumName(String stadiumName) throws SQLException {
        String query = "SELECT * FROM stadium_tb WHERE stadium_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,stadiumName);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return buildStadiumFromResultSet(rs);
                }
            }
        }
        return null; // Team not found
    }

    // 전체 야구장 조회
    public List<Stadium> getAllStadiums() throws SQLException {
        List<Stadium> stadiums = new ArrayList<>();
        String query = "SELECT * FROM stadium_tb";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    Stadium stadium = buildStadiumFromResultSet(resultSet);
                    stadiums.add(stadium);
                }
            }
        }
        return stadiums;
    }

    // 야구장 이름 수정
    public void updateStadium(String stadiumName, String newStadiumName) throws SQLException {
        String query = "UPDATE stadium_tb SET stadium_name = ?  WHERE stadium_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newStadiumName);
            statement.setString(2, stadiumName);
            statement.executeUpdate();
        }
    }

    // 야구장 삭제
    public void deleteStadium(String stadiumName) throws SQLException {
        String query = "DELETE FROM stadium_tb WHERE stadium_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, stadiumName);
            statement.executeUpdate();
        }
    }

    private Stadium buildStadiumFromResultSet(ResultSet resultSet) throws SQLException {
        int stadiumId = resultSet.getInt("stadium_id");
        String stadiumName = resultSet.getString("stadium_name");
        Timestamp stadiumCreatedAt = resultSet.getTimestamp("stadium_created_at");

        return Stadium.builder()
                .stadiumId(stadiumId)
                .stadiumName(stadiumName)
                .stadiumCreatedAt(stadiumCreatedAt)
                .build();
    }

}
