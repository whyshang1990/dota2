package com.why.dota2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MatchInfoDTO implements Serializable {
    private static final long serialVersionUID = 4640831241510105506L;

    @JsonProperty("match_id")
    private Long matchId;
    @JsonProperty("match_seq_num")
    private Long matchSeqNum;
    @JsonProperty("start_time")
    private Long startTime;
    /*
    大厅匹配模式
    -1 - Invalid  无效的
    0 - Public matchmaking 公共匹配
    1 - Practise  练习模式
    2 - Tournament  锦标赛
    3 - Tutorial  教程
    4 - Co-op with bots.
    5 - Team match
    6 - Solo Queue  Solo
    7 - Ranked Matchmaking  天梯
    8 - 1v1 Solo Mid  中单solo
     */
    @JsonProperty("lobby_type")
    private Integer lobbyType;
    @JsonProperty("radiant_team_id")
    private Integer radiantTeamId;
    @JsonProperty("dire_team_id")
    private Integer direTeamId;
    private List<PlayerDTO> players;
}
