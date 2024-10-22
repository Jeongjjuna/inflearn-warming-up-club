package day7.tobe.model;

import java.util.List;

public class StudyCafeSeatPasses {

    private final List<StudyCafeSeatPass> studyCafeSeatPasses;

    private StudyCafeSeatPasses(List<StudyCafeSeatPass> studyCafeSeatPasses) {
        this.studyCafeSeatPasses = studyCafeSeatPasses;
    }

    public static StudyCafeSeatPasses of(List<StudyCafeSeatPass> studyCafeSeatPasses) {
        return new StudyCafeSeatPasses(studyCafeSeatPasses);
    }

    public List<StudyCafeSeatPass> selectCafePassesBy(StudyCafePassType userSelectedPassType) {
        return studyCafeSeatPasses.stream()
                .filter(studyCafePass -> studyCafePass.isSameType(userSelectedPassType))
                .toList();
    }
}
