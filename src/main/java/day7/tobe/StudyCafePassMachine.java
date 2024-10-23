package day7.tobe;


import day7.tobe.exception.AppException;
import day7.tobe.io.IoHandler;
import day7.tobe.io.StudyCafeFileHandler;
import day7.tobe.model.pass.locker.StudyCafeLockerPass;
import day7.tobe.model.pass.locker.StudyCafeLockerPasses;
import day7.tobe.model.pass.seat.StudyCafePassType;
import day7.tobe.model.pass.seat.StudyCafeSeatPass;
import day7.tobe.model.pass.seat.StudyCafeSeatPasses;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final IoHandler ioHandler;
    private final StudyCafeFileHandler studyCafeFileHandler;

    public StudyCafePassMachine(
            IoHandler ioHandler,
            StudyCafeFileHandler studyCafeFileHandler
    ) {
        this.ioHandler = ioHandler;
        this.studyCafeFileHandler = studyCafeFileHandler;
    }

    public void run() {
        try {
            ioHandler.showWelcomeMessage();

            ioHandler.showAnnouncement();

            StudyCafeSeatPass selectedPass = selectPass();
            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);

            optionalLockerPass.ifPresentOrElse(
                    lockerPass -> ioHandler.showPassOrderSummary(selectedPass, lockerPass),
                    () -> ioHandler.showPassOrderSummary(selectedPass)
            );

        } catch (AppException e) {
            ioHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            ioHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    // 패스권을 선택한다.
    private StudyCafeSeatPass selectPass() {
        StudyCafePassType passType = ioHandler.askStudyCafePassTypeSelecting();
        List<StudyCafeSeatPass> passCandidates = findPassCandidatesBy(passType);

        return ioHandler.askStudyCafePassSelecting(passCandidates);
    }

    private List<StudyCafeSeatPass> findPassCandidatesBy(StudyCafePassType userSelectingPassType) {
        StudyCafeSeatPasses allSeatPasses = studyCafeFileHandler.readStudyCafePasses();
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
        return ioHandler.askDoseUseLockerSelecting(lockerPass);

    }

    private Optional<StudyCafeLockerPass> findLockerPassCandidateBy(StudyCafeSeatPass pass) {
        StudyCafeLockerPasses allLockerPasses = studyCafeFileHandler.readLockerPasses();
        return allLockerPasses.findLockerPassBy(pass);
    }

}
