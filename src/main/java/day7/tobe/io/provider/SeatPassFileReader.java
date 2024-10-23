package day7.tobe.io.provider;

import day7.tobe.model.pass.seat.StudyCafePassType;
import day7.tobe.model.pass.seat.StudyCafeSeatPass;
import day7.tobe.model.pass.seat.StudyCafeSeatPasses;
import day7.tobe.provider.SeatPassProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SeatPassFileReader implements SeatPassProvider {

    private static final String PASS_LIST_CSV_PATH = "src/main/resources/studycafe/pass-list.csv";

    @Override
    public StudyCafeSeatPasses readStudyCafePasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(PASS_LIST_CSV_PATH));
            List<StudyCafeSeatPass> studyCafeSeatPasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                double discountRate = Double.parseDouble(values[3]);

                StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(studyCafePassType, duration, price, discountRate);
                studyCafeSeatPasses.add(studyCafeSeatPass);
            }

            return StudyCafeSeatPasses.of(studyCafeSeatPasses);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }
}
