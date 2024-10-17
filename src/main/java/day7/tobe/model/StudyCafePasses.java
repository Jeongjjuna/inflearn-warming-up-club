package day7.tobe.model;

import java.util.List;

public class StudyCafePasses {

    private final List<StudyCafePass> studyCafePasses;

    public StudyCafePasses(List<StudyCafePass> studyCafePasses) {
        this.studyCafePasses = studyCafePasses;
    }

    public List<StudyCafePass> selectCafePassesBy(StudyCafePassType userSelectedPassType) {
        return studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.isSameType(userSelectedPassType))
                .toList();
    }
}
