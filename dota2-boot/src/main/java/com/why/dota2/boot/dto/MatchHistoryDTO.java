package com.why.dota2.boot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MatchHistoryDTO implements Serializable {
    private static final long serialVersionUID = -2057006463305024419L;

    private int status;
    @JsonProperty("num_results")
    private int numResults;
    @JsonProperty("total_results")
    private int totalResults;
    @JsonProperty("results_remaining")
    private int resultsRemaining;
    private List<MatchInfoDTO> matches;
}
