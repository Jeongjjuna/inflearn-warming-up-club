package day7.tobe;

import day7.tobe.io.InputHandler;
import day7.tobe.io.OutputHandler;
import day7.tobe.io.StudyCafeFileHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {

        InputHandler inputHandler = new InputHandler();
        OutputHandler outputHandler = new OutputHandler();
        StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
                inputHandler,
                outputHandler,
                studyCafeFileHandler
        );

        studyCafePassMachine.run();
    }

}
