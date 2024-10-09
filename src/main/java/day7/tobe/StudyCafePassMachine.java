package day7.tobe;


import day7.tobe.exception.AppException;
import day7.tobe.io.InputHandler;
import day7.tobe.io.OutputHandler;
import day7.tobe.io.StudyCafeFileHandler;
import day7.tobe.model.StudyCafeLockerPass;
import day7.tobe.model.StudyCafePass;
import day7.tobe.model.StudyCafePassType;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final List<StudyCafePass> allPasses;
    private final List<StudyCafeLockerPass> allLockerPasses;
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final StudyCafeFileHandler studyCafeFileHandler;

    public StudyCafePassMachine(
            InputHandler inputHandler,
            OutputHandler outputHandler,
            StudyCafeFileHandler studyCafeFileHandler
    ) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.studyCafeFileHandler = studyCafeFileHandler;
        this.allPasses = getAllPasses();
        this.allLockerPasses = getAllLockPasses();
    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            StudyCafePass selectedPass = selectPass();
            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPassBy(selectedPass);

            optionalLockerPass.ifPresentOrElse(
                    lockerPass -> outputHandler.showPassOrderSummary(selectedPass, lockerPass),
                    () -> outputHandler.showPassOrderSummary(selectedPass)
            );

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePass selectPass() {
        outputHandler.askPassTypeSelection();
        StudyCafePassType userSelectedStudyCafePassType = inputHandler.getPassTypeSelectingUserAction();

        List<StudyCafePass> candidatePasses = getCandidatePasses(allPasses, userSelectedStudyCafePassType);

        outputHandler.showPassListForSelection(candidatePasses);
        return inputHandler.getSelectPass(candidatePasses);
    }

    private Optional<StudyCafeLockerPass> selectLockerPassBy(StudyCafePass selectedPass) {
        if (selectedPass.cannotUseLocker()) {
            return Optional.empty();
        }

        Optional<StudyCafeLockerPass> candidateLockerPasses = findCandidateLockerPassesBy(selectedPass);

        return candidateLockerPasses.filter(this::isSelectedLockerPass);
    }

    private boolean isSelectedLockerPass(StudyCafeLockerPass lockerPass) {
        outputHandler.askLockerPass(lockerPass);
        return inputHandler.getLockerSelection();
    }

    private Optional<StudyCafeLockerPass> findCandidateLockerPassesBy(StudyCafePass pass) {
        return allLockerPasses.stream()
                .filter(pass::isSameDurationType)
                .findFirst();
    }

    private List<StudyCafePass> getCandidatePasses(List<StudyCafePass> studyCafePasses, StudyCafePassType userSelectedPassType) {
        return studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.isSameType(userSelectedPassType))
                .toList();
    }

    private List<StudyCafePass> getAllPasses() {
        return studyCafeFileHandler.readStudyCafePasses();
    }

    private List<StudyCafeLockerPass> getAllLockPasses() {
        return studyCafeFileHandler.readLockerPasses();
    }

}
