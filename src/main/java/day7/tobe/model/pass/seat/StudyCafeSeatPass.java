package day7.tobe.model.pass.seat;

import day7.tobe.model.pass.StudyCafePass;
import day7.tobe.model.pass.locker.StudyCafeLockerPass;

public class StudyCafeSeatPass implements StudyCafePass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafeSeatPass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafeSeatPass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafeSeatPass(passType, duration, price, discountRate);
    }

    public boolean cannotUseLocker() {
        return passType.isNotLockerType();
    }

    public boolean isSameType(StudyCafePassType userSelectedPassType) {
        return userSelectedPassType == passType;
    }

    public boolean isSameDurationAndSameType(StudyCafeLockerPass lockerPass) {
        return isSameDuration(lockerPass) && isSamePassType(lockerPass);
    }

    public int getDiscountPrice() {
        return (int) (price * discountRate);
    }

    @Override
    public StudyCafePassType getPassType() {
        return passType;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    private boolean isSameDuration(StudyCafeLockerPass lockerPass) {
        return lockerPass.isSameDuration(this.duration);
    }

    private boolean isSamePassType(StudyCafeLockerPass lockerPass) {
        return lockerPass.isSameType(this.passType);
    }

}
