package day7.tobe.model.pass.seat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[StudyCafePassType] 단위테스트")
class StudyCafePassTypeTest {


    @DisplayName("사물함 이용권을 이용할 수 있는지 알 수 있다.")
    @Test
    void 사물함_이용권을_이용할수있는지_알수있다() {
        // given
        StudyCafePassType fixed = StudyCafePassType.FIXED;

        // when
        boolean isLockerType = fixed.isLockerType();

        // then
        assertThat(isLockerType).isTrue();
    }

}