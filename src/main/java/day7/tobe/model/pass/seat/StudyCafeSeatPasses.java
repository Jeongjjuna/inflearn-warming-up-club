package day7.tobe.model.pass.seat;

import java.util.List;

public class StudyCafeSeatPasses {

    private final List<StudyCafeSeatPass> seatPasses;

    private StudyCafeSeatPasses(List<StudyCafeSeatPass> seatPasses) {
        this.seatPasses = seatPasses;
    }

    public static StudyCafeSeatPasses of(List<StudyCafeSeatPass> studyCafeSeatPasses) {
        return new StudyCafeSeatPasses(studyCafeSeatPasses);
    }

    public List<StudyCafeSeatPass> findPassBy(StudyCafePassType passType) {
        return seatPasses.stream()
                .filter(studyCafePass -> studyCafePass.isSameType(passType))
                .toList();
    }
}
