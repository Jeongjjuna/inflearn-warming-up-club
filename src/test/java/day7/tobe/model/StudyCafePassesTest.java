package day7.tobe.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("StudyCafePasses 테스트")
class StudyCafePassesTest {

    @DisplayName("사용자가 선택한 타입의 스터디 카페 이용권들을 가져올 수 있다.")
    @Test
    void selectCafePassesBy() {
        // given
        List<StudyCafePass> studyCafePasses = List.of(
                StudyCafePass.of(StudyCafePassType.FIXED, 10, 1000, 5),
                StudyCafePass.of(StudyCafePassType.FIXED, 20, 1500, 5),
                StudyCafePass.of(StudyCafePassType.HOURLY, 10, 1000, 5)
        );
        StudyCafePasses totalStudyCafePasses = StudyCafePasses.of(studyCafePasses);

        // when
        List<StudyCafePass> selectedCafePasses = totalStudyCafePasses.selectCafePassesBy(StudyCafePassType.FIXED);

        // then
        assertThat(selectedCafePasses.size()).isEqualTo(2);
    }
}