package com.detector.processor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThresholdDTO {

    private Integer id;
    private Integer maxValue;
    private Integer dailyLimit;

    public ThresholdDTO() {
    }

}
