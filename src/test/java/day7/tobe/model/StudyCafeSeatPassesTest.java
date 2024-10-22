package day7.tobe.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("StudyCafePasses 테스트")
class StudyCafeSeatPassesTest {

    @DisplayName("사용자가 선택한 타입의 스터디 카페 이용권들을 가져올 수 있다.")
    @Test
    void selectCafePassesBy() {
        // given
        List<StudyCafeSeatPass> studyCafeSeatPasses = List.of(
                StudyCafeSeatPass.of(StudyCafePassType.FIXED, 10, 1000, 5),
                StudyCafeSeatPass.of(StudyCafePassType.FIXED, 20, 1500, 5),
                StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 10, 1000, 5)
        );
        StudyCafeSeatPasses totalStudyCafeSeatPasses = StudyCafeSeatPasses.of(studyCafeSeatPasses);

        // when
        List<StudyCafeSeatPass> selectedCafePasses = totalStudyCafeSeatPasses.selectCafePassesBy(StudyCafePassType.FIXED);

        // then
        assertThat(selectedCafePasses.size()).isEqualTo(2);
    }
}