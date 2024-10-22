package day7.tobe.model;

import java.util.List;
import java.util.Optional;

public class StudyCafeLockerPasses {

    private final List<StudyCafeLockerPass> studyCafeLockerPasses;

    public StudyCafeLockerPasses(List<StudyCafeLockerPass> studyCafeLockerPasses) {
        this.studyCafeLockerPasses = studyCafeLockerPasses;
    }

    public Optional<StudyCafeLockerPass> findBy(StudyCafeSeatPass pass) {
        return studyCafeLockerPasses.stream()
                .filter(pass::isSameDurationAndSameType)
                .findFirst();
    }
}
