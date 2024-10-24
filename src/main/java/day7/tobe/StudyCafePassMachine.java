package day7.tobe;

import day7.tobe.exception.AppException;
import day7.tobe.io.IoHandler;
import day7.tobe.model.order.StudyCafePassOrder;
import day7.tobe.model.pass.locker.StudyCafeLockerPass;
import day7.tobe.model.pass.locker.StudyCafeLockerPasses;
import day7.tobe.model.pass.seat.StudyCafePassType;
import day7.tobe.model.pass.seat.StudyCafeSeatPass;
import day7.tobe.model.pass.seat.StudyCafeSeatPasses;
import day7.tobe.provider.LockerPassProvider;
import day7.tobe.provider.SeatPassProvider;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final IoHandler ioHandler;
    private final SeatPassProvider seatPassProvider;
    private final LockerPassProvider lockerPassProvider;

    public StudyCafePassMachine(
            IoHandler ioHandler,
            SeatPassProvider seatPassProvider,
            LockerPassProvider lockerPassProvider
    ) {
        this.ioHandler = ioHandler;
        this.seatPassProvider = seatPassProvider;
        this.lockerPassProvider = lockerPassProvider;
    }

    public void run() {
        try {
            ioHandler.showWelcomeMessage();

            ioHandler.showAnnouncement();

            StudyCafeSeatPass selectedPass = selectPass();
            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);

            StudyCafePassOrder passOrder = StudyCafePassOrder.of(
                    selectedPass,
                    optionalLockerPass.orElse(null)
            );
            ioHandler.showPassOrderSummary(passOrder);

        } catch (AppException e) {
            ioHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            ioHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafeSeatPass selectPass() {
        StudyCafePassType passType = ioHandler.askStudyCafePassTypeSelecting();
        List<StudyCafeSeatPass> passCandidates = findPassCandidatesBy(passType);

        return ioHandler.askStudyCafePassSelecting(passCandidates);
    }

    private List<StudyCafeSeatPass> findPassCandidatesBy(StudyCafePassType userSelectingPassType) {
        StudyCafeSeatPasses allSeatPasses = seatPassProvider.readStudyCafePasses();
        return allSeatPasses.findPassBy(userSelectingPassType);
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafeSeatPass selectedPass) {
        if (selectedPass.cannotUseLocker()) {
            return Optional.empty();
        }

        Optional<StudyCafeLockerPass> lockerPassCandidates = findLockerPassCandidateBy(selectedPass);

        return lockerPassCandidates.filter(this::isSelectedLockerPass);
    }

    private boolean isSelectedLockerPass(StudyCafeLockerPass lockerPass) {
        return ioHandler.askUseLockerSelecting(lockerPass);

    }

    private Optional<StudyCafeLockerPass> findLockerPassCandidateBy(StudyCafeSeatPass pass) {
        StudyCafeLockerPasses allLockerPasses = lockerPassProvider.readLockerPasses();
        return allLockerPasses.findLockerPassBy(pass);
    }

}
