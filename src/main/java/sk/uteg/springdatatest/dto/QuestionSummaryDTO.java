package sk.uteg.springdatatest.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionSummaryDTO {
    private String text;
    private String type;
    private Double averageRating;
    private List<OptionCountDTO> optionCounts;
}