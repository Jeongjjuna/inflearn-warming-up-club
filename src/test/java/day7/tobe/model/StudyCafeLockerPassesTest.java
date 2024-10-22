package day7.tobe.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("StudyCafePasses 테스트")
class StudyCafeLockerPassesTest {

    @DisplayName("스터디 카페 이용권을 통해 사물함 이용권을 찾을 수 있다.")
    @Test
    void findBy() {
        // given
        List<StudyCafeLockerPass> studyCafeLockerPasses = List.of(
                StudyCafeLockerPass.of(StudyCafePassType.FIXED, 10, 2000),
                StudyCafeLockerPass.of(StudyCafePassType.FIXED, 20, 1500),
                StudyCafeLockerPass.of(StudyCafePassType.HOURLY, 10, 1000)
        );
        StudyCafeLockerPasses allLockerPasses = new StudyCafeLockerPasses(studyCafeLockerPasses);
        StudyCafePass pass = StudyCafePass.of(StudyCafePassType.FIXED, 10, 1000, 5);

        // when
        Optional<StudyCafeLockerPass> lockerPass = allLockerPasses.findBy(pass);


        // then
        assertThat(lockerPass.isPresent()).isTrue();
    }
}