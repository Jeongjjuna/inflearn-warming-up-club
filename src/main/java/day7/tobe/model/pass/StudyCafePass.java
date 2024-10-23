package day7.tobe.model.pass;

import day7.tobe.model.pass.seat.StudyCafePassType;

public interface StudyCafePass {

    StudyCafePassType getPassType();

    int getDuration();

    int getPrice();
}
