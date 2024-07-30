package com.boot.barcodepractice.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProcessName {
    MCT_1("MCT 1"),
    MCT_2("MCT 2"),
    MCT_3("MCT 3"),
    CNC_1("CNC 1"),
    CNC_2("CNC 2"),
    LPS("LPS"),
    ROBOT("로봇 자동화"),
    ASSEMBLE("조립장"),
    MEASURING_ROOM("측정실"),
    HOBBING("호빙기"),
    MILLING_RADIAL_POLISHING("밀링/연마/레디알");

    private final String description;
}
