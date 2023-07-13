package dto;

import lombok.Builder;
import lombok.ToString;

import java.sql.Timestamp;

@Builder @ToString
public class OutPlayerRespDTO {

    //선수
    private Integer pId;
    private String pName;
    private String pPosition;
    //퇴출선수
    private String outReason;
    private Timestamp outTimestamp;


}
