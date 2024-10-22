package day7.tobe.model;

public class StudyCafeSeatPass {

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

    public int getPrice() {
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public String display() {
        if (passType == StudyCafePassType.HOURLY) {
            return String.format("%s시간권 - %d원", duration, price);
        }
        if (passType == StudyCafePassType.WEEKLY) {
            return String.format("%s주권 - %d원", duration, price);
        }
        if (passType == StudyCafePassType.FIXED) {
            return String.format("%s주권 - %d원", duration, price);
        }
        return "";
    }

    public boolean cannotUseLocker() {
        return passType == StudyCafePassType.FIXED;
    }

    public boolean isSameType(StudyCafePassType userSelectedPassType) {
        return userSelectedPassType == passType;
    }

    public boolean isSameDurationAndSameType(StudyCafeLockerPass lockerPass) {
        return isSameDuration(lockerPass) && isSamePassType(lockerPass);
    }

    private boolean isSameDuration(StudyCafeLockerPass lockerPass) {
        return lockerPass.isSameDuration(this.duration);
    }

    private boolean isSamePassType(StudyCafeLockerPass lockerPass) {
        return lockerPass.isSameType(this.passType);
    }

}
