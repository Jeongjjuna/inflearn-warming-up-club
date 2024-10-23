package day7.tobe;

import day7.tobe.io.IoHandler;
import day7.tobe.io.provider.LockerPassFileReader;
import day7.tobe.io.provider.SeatPassFileReader;

public class StudyCafeApplication {

    public static void main(String[] args) {

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
                new IoHandler(),
                new SeatPassFileReader(),
                new LockerPassFileReader()
        );
        studyCafePassMachine.run();
    }

}
