package day7.tobe.model;

import day7.tobe.model.pass.locker.StudyCafeLockerPass;
import day7.tobe.model.pass.seat.StudyCafePassType;
import day7.tobe.model.pass.seat.StudyCafeSeatPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("StudyCafePass 테스트")
class StudyCafeSeatPassTest {

    @DisplayName("카페 이용권의 가격을 알 수 있다.")
    @Test
    void getStudyCafePassPrice() {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(
                StudyCafePassType.FIXED, 10, 1000, 10
        );

        // when
        int passPrice = pass.getPrice();

        // then
        assertThat(passPrice).isEqualTo(1000);
    }

    @DisplayName("카페 이용권의 할인 비율을 알 수 있다.")
    @Test
    void getStudyCafePassDiscountRate() {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(
                StudyCafePassType.FIXED, 10, 1000, 10
        );

        // when
        double passDiscountRate = pass.getDiscountRate();

        // then
        assertThat(passDiscountRate).isEqualTo(10);
    }

    @DisplayName("사물함을 이용할 수 있다.")
    @Test
    void cannotUseLocker() {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(
                StudyCafePassType.FIXED, 10, 1000, 10
        );

        // when
        boolean cannotUseLocker = pass.cannotUseLocker();

        // then
        assertThat(cannotUseLocker).isTrue();
    }

    @DisplayName("사물함을 이용할 수 있다.")
    @Test
    void canUseLocker() {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(
                StudyCafePassType.HOURLY, 10, 1000, 10
        );

        // when
        boolean cannotUseLocker = pass.cannotUseLocker();

        // then
        assertThat(cannotUseLocker).isFalse();
    }

    @DisplayName("어떤 타입의 이용권인지 알 수 있다.")
    @Test
    void isSameType() {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(
                StudyCafePassType.HOURLY, 10, 1000, 10
        );

        // when
        boolean isSameType = pass.isSameType(StudyCafePassType.HOURLY);

        // then
        assertThat(isSameType).isTrue();
    }

    @DisplayName("어떤 타입의 이용권인지 알 수 있다.")
    @Test
    void isDifferentType() {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(
                StudyCafePassType.HOURLY, 10, 1000, 10
        );

        // when
        boolean isSameType = pass.isSameType(StudyCafePassType.FIXED);

        // then
        assertThat(isSameType).isFalse();
    }

    @DisplayName("카페 이용권과 사물함 이용권이 같은 기간, 같은 타입을 가지는지 확인할 수 있다.")
    @Test
    void isSameDurationAndSameType() {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(
                StudyCafePassType.HOURLY, 10, 1000, 10
        );
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(
                StudyCafePassType.HOURLY, 10, 2000
        );

        // when
        boolean isSameDurationAndSameType = pass.isSameDurationAndSameType(lockerPass);

        // then
        assertThat(isSameDurationAndSameType).isTrue();
    }

    @DisplayName("카페 이용권과 사물함 이용권이 다른 기간을 가지는지 알 수 있다.")
    @Test
    void isDifferentDurationAndSameType() {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(
                StudyCafePassType.HOURLY, 10, 1000, 10
        );
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(
                StudyCafePassType.HOURLY, 20, 2000
        );

        // when
        boolean isSameDurationAndSameType = pass.isSameDurationAndSameType(lockerPass);

        // then
        assertThat(isSameDurationAndSameType).isFalse();
    }

    @DisplayName("카페 이용권과 사물함 이용권이 다른 기간을 가지는지 알 수 있다.")
    @Test
    void isSameDurationAndDifferentType() {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(
                StudyCafePassType.HOURLY, 10, 1000, 10
        );
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(
                StudyCafePassType.WEEKLY, 10, 2000
        );

        // when
        boolean isSameDurationAndSameType = pass.isSameDurationAndSameType(lockerPass);

        // then
        assertThat(isSameDurationAndSameType).isFalse();
    }
}