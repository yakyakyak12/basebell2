package dto;

import lombok.Builder;
import lombok.ToString;

@Builder @ToString
public class PositionRespDTO {
    private String position;
    private String samSung;
    private String lotte;
    private String gwangJu;
}
