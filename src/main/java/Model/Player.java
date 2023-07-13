package Model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
public class Player {
    private Integer playerId;
    private Integer teamId;
    private String playerName;
    private String playerPosition;
    private Timestamp playerCreatedAt;

    @Builder
    public Player(Integer playerId, String playerName, String playerPosition, Timestamp playerCreatedAt) {
        this.playerId = playerId;
        this.teamId = teamId;
        this.playerName = playerName;
        this.playerPosition = playerPosition;
        this.playerCreatedAt = playerCreatedAt;
    }
}