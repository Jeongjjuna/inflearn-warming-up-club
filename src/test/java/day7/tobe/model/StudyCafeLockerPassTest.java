package day7.tobe.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("StudyCafeLockerPass 테스트")
class StudyCafeLockerPassTest {

    @DisplayName("사물함 이용권의 가격을 알 수 있다.")
    @Test
    void getStudyCafeLockerPassPrice() {
        // given
        StudyCafeLockerPass lockerPasses = StudyCafeLockerPass.of(
          StudyCafePassType.FIXED, 10, 1000
        );

        // when
        int lockerPassesPrice = lockerPasses.getPrice();

        // then
        assertThat(lockerPassesPrice).isEqualTo(1000);
    }

    @DisplayName("사물함 이용권의 타입이 일치하는지 알 수 있다.")
    void isSamePassType() {
        // given
        StudyCafeLockerPass lockerPasses = StudyCafeLockerPass.of(
                StudyCafePassType.FIXED, 10, 1000
        );

        // when
        boolean isSameType = lockerPasses.isSameType(StudyCafePassType.FIXED);

        // then
        assertThat(isSameType).isTrue();
    }

    @DisplayName("사물함 이용권의 기간이 일치하는지 알 수 있다.")
    @Test
    void isSameDuration() {
        // given
        StudyCafeLockerPass lockerPasses = StudyCafeLockerPass.of(
                StudyCafePassType.FIXED, 10, 1000
        );

        // when
        boolean isSameType = lockerPasses.isSameDuration(10);

        // then
        assertThat(isSameType).isTrue();
    }
}