package day7.tobe.model.pass.locker;

import day7.tobe.model.pass.seat.StudyCafePassType;
import day7.tobe.model.pass.seat.StudyCafeSeatPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("StudyCafePasses 테스트")
class StudyCafeLockerPassesTest {

    @DisplayName("스터디 카페 이용권을 통해 사물함 이용권을 찾을 수 있다.")
    @Test
    void findLockerPassBy() {
        // given
        List<StudyCafeLockerPass> studyCafeLockerPasses = List.of(
                StudyCafeLockerPass.of(StudyCafePassType.FIXED, 10, 2000),
                StudyCafeLockerPass.of(StudyCafePassType.FIXED, 20, 1500),
                StudyCafeLockerPass.of(StudyCafePassType.HOURLY, 10, 1000)
        );
        StudyCafeLockerPasses allLockerPasses = new StudyCafeLockerPasses(studyCafeLockerPasses);
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 10, 1000, 5);

        // when
        Optional<StudyCafeLockerPass> lockerPass = allLockerPasses.findLockerPassBy(pass);


        // then
        assertThat(lockerPass.isPresent()).isTrue();
    }
}