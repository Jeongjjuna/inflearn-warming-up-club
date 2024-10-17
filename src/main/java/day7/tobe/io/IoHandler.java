package day7.tobe.io;

import day7.tobe.model.StudyCafeLockerPass;
import day7.tobe.model.StudyCafePass;
import day7.tobe.model.StudyCafePassType;

import java.util.List;

public class IoHandler {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public IoHandler(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public void showWelcomeMessage() {
        outputHandler.showWelcomeMessage();
    }

    public void showAnnouncement() {
        outputHandler.showAnnouncement();
    }

    public StudyCafePassType askStudyCafePassTypeSelecting() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    public StudyCafePass askStudyCafePassSelecting(List<StudyCafePass> candidatePasses) {
        outputHandler.showPassListForSelection(candidatePasses);
        return inputHandler.getSelectPass(candidatePasses);
    }

    public boolean askDoseUseLockerSelecting(StudyCafeLockerPass lockerPass) {
        outputHandler.askLockerPass(lockerPass);
        return inputHandler.getLockerSelection();
    }

    public void showPassOrderSummary(StudyCafePass selectedPass) {
        outputHandler.showPassOrderSummary(selectedPass);
    }

    public void showPassOrderSummary(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
        outputHandler.showPassOrderSummary(selectedPass, lockerPass);
    }

    public void showSimpleMessage(String message) {
        outputHandler.showSimpleMessage(message);
    }
}
