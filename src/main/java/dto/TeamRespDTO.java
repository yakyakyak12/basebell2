package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.sql.Timestamp;

@Builder
public class TeamRespDTO {
    //팀
    private Integer teamId;
    private String teamName;
    private Timestamp teamCreatedAt;
    //야구장 -
    private Integer sId;
    private String sName;
    private Timestamp sCreatedAt;


}
