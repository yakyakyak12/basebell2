package Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Connection;
import java.sql.Timestamp;

@Getter
@ToString
@Setter
public class Team {
    private Integer teamId;
    private Integer stadiumId;
    private String teamName;
    private Timestamp teamCreatedAt;

    @Builder
    public Team(Integer teamId, Integer stadiumId, String teamName, Timestamp teamCreatedAt) {
        this.stadiumId = stadiumId;
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamCreatedAt = teamCreatedAt;
    }


}