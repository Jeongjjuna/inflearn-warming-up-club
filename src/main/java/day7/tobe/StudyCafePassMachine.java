package day7.tobe;


import day7.tobe.exception.AppException;
import day7.tobe.io.IoHandler;
import day7.tobe.io.StudyCafeFileHandler;
import day7.tobe.model.StudyCafeLockerPass;
import day7.tobe.model.StudyCafeLockerPasses;
import day7.tobe.model.StudyCafePass;
import day7.tobe.model.StudyCafePassType;
import day7.tobe.model.StudyCafePasses;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final IoHandler ioHandler;
    private final StudyCafeFileHandler studyCafeFileHandler;
    private final StudyCafePasses allPasses;
    private final StudyCafeLockerPasses allLockerPasses;

    public StudyCafePassMachine(
            IoHandler ioHandler,
            StudyCafeFileHandler studyCafeFileHandler
    ) {
        this.ioHandler = ioHandler;
        this.studyCafeFileHandler = studyCafeFileHandler;
        this.allPasses = getAllPasses();
        this.allLockerPasses = getAllLockPasses();
    }

    public void run() {
        try {
            ioHandler.showWelcomeMessage();

            ioHandler.showAnnouncement();

            StudyCafePass selectedPass = selectPass();
            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPassBy(selectedPass);

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

    private StudyCafePass selectPass() {
        StudyCafePassType userSelectingPassType = ioHandler.askStudyCafePassTypeSelecting();

        List<StudyCafePass> candidatePasses = getCandidatePasses(allPasses, userSelectingPassType);

        return ioHandler.askStudyCafePassSelecting(candidatePasses);
    }

    private Optional<StudyCafeLockerPass> selectLockerPassBy(StudyCafePass selectedPass) {
        if (selectedPass.cannotUseLocker()) {
            return Optional.empty();
        }

        Optional<StudyCafeLockerPass> candidateLockerPasses = findCandidateLockerPassesBy(selectedPass);

        return candidateLockerPasses.filter(this::isSelectedLockerPass);
    }

    private boolean isSelectedLockerPass(StudyCafeLockerPass lockerPass) {
        return ioHandler.askDoseUseLockerSelecting(lockerPass);

    }

    private Optional<StudyCafeLockerPass> findCandidateLockerPassesBy(StudyCafePass pass) {
        return allLockerPasses.findBy(pass);
    }

    private List<StudyCafePass> getCandidatePasses(StudyCafePasses studyCafePasses, StudyCafePassType userSelectedPassType) {
        return studyCafePasses.selectCafePassesBy(userSelectedPassType);
    }

    private StudyCafePasses getAllPasses() {
        return studyCafeFileHandler.readStudyCafePasses();
    }

    private StudyCafeLockerPasses getAllLockPasses() {
        return studyCafeFileHandler.readLockerPasses();
    }

}
