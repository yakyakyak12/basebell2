package Model;

import lombok.*;

import java.sql.Timestamp;

@ToString
@Getter
@Setter
public class OutPlayer {
    private Integer outPlayerId;
    private Integer playerId;
    private String outReason;
    private Timestamp outCreatedAt;

    @Builder
    public OutPlayer(Integer outPlayerId, Integer PlayerId, String outPlayerReason, Timestamp outPlayerCreatedAt) {
        this.outPlayerId = outPlayerId;
        this.playerId = PlayerId;
        this.outReason = outPlayerReason;
        this.outCreatedAt = outPlayerCreatedAt;
    }


}