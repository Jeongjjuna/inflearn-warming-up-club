package day7.tobe.io;

import day7.tobe.model.order.StudyCafePassOrder;
import day7.tobe.model.pass.locker.StudyCafeLockerPass;
import day7.tobe.model.pass.seat.StudyCafePassType;
import day7.tobe.model.pass.seat.StudyCafeSeatPass;

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

    public StudyCafeSeatPass askStudyCafePassSelecting(List<StudyCafeSeatPass> candidatePasses) {
        outputHandler.showPassListForSelection(candidatePasses);
        return inputHandler.getSelectPass(candidatePasses);
    }

    public boolean askUseLockerSelecting(StudyCafeLockerPass lockerPass) {
        outputHandler.askLockerPass(lockerPass);
        return inputHandler.getLockerSelection();
    }

    public void showPassOrderSummary(StudyCafePassOrder passOrder) {
        outputHandler.showPassOrderSummary(passOrder);
    }

    public void showSimpleMessage(String message) {
        outputHandler.showSimpleMessage(message);
    }
}
