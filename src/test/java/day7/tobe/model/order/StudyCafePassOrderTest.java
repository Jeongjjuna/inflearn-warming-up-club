package day7.tobe.model.order;

import day7.tobe.model.pass.locker.StudyCafeLockerPass;
import day7.tobe.model.pass.seat.StudyCafePassType;
import day7.tobe.model.pass.seat.StudyCafeSeatPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 테스트를 위한 전체 이용권 초기 데이터를 관리해주면 좋을 것 같다.
 * TODO : 테스트를 위한 데이터 만들기
 */
@DisplayName("[StudyCafePassOrder] 단위테스트")
class StudyCafePassOrderTest {


    @DisplayName("주문서의 전체 가격 정보를 알 수 있다.")
    @Test
    void 주문서_전체_가격정보를_알수있다() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
                StudyCafePassType.FIXED,
                4,
                250000,
                0.1
        );
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(
                StudyCafePassType.FIXED,
                4,
                10000
        );
        StudyCafePassOrder passOrder = StudyCafePassOrder.of(seatPass, lockerPass);

        // when
        int totalPrice = passOrder.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(235000);
    }

}