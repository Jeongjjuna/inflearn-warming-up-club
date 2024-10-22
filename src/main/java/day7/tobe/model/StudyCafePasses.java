package day7.tobe.model;

import java.util.List;

public class StudyCafePasses {

    private final List<StudyCafePass> studyCafePasses;

    private StudyCafePasses(List<StudyCafePass> studyCafePasses) {
        this.studyCafePasses = studyCafePasses;
    }

    public static StudyCafePasses of(List<StudyCafePass> studyCafePasses) {
        return new StudyCafePasses(studyCafePasses);
    }

    public List<StudyCafePass> selectCafePassesBy(StudyCafePassType userSelectedPassType) {
        return studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.isSameType(userSelectedPassType))
                .toList();
    }
}
