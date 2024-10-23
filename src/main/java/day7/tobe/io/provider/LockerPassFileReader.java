package day7.tobe.io.provider;

import day7.tobe.model.pass.locker.StudyCafeLockerPass;
import day7.tobe.model.pass.locker.StudyCafeLockerPasses;
import day7.tobe.model.pass.seat.StudyCafePassType;
import day7.tobe.provider.LockerPassProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LockerPassFileReader implements LockerPassProvider {

    private static final String LOCKER_LIST_CSV_PATH = "src/main/resources/studycafe/locker.csv";

    @Override
    public StudyCafeLockerPasses readLockerPasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOCKER_LIST_CSV_PATH));
            List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);

                StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(studyCafePassType, duration, price);
                lockerPasses.add(lockerPass);
            }

            return new StudyCafeLockerPasses(lockerPasses);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }
}
