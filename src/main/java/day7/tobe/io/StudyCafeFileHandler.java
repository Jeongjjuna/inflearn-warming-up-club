package day7.tobe.io;

import day7.tobe.model.StudyCafeLockerPass;
import day7.tobe.model.StudyCafeLockerPasses;
import day7.tobe.model.StudyCafePass;
import day7.tobe.model.StudyCafePassType;
import day7.tobe.model.StudyCafePasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafeFileHandler {

    public StudyCafePasses readStudyCafePasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/studycafe/pass-list.csv"));
            List<StudyCafePass> studyCafePasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                double discountRate = Double.parseDouble(values[3]);

                StudyCafePass studyCafePass = StudyCafePass.of(studyCafePassType, duration, price, discountRate);
                studyCafePasses.add(studyCafePass);
            }

            return new StudyCafePasses(studyCafePasses);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

    public StudyCafeLockerPasses readLockerPasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/studycafe/locker.csv"));
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
