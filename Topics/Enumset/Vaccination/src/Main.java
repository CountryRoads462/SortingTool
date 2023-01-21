import java.util.EnumSet;

public class Main {

    public enum Age {
        AGE_0_TO_10, AGE_11_TO_17, AGE_18_TO_45, AGE_46_TO_60, AGE_60_ABOVE
    }

    public static void main(String[] args) {

        EnumSet<Age> notAllowedAgeGroup = EnumSet.of(Age.AGE_0_TO_10, Age.AGE_11_TO_17);

        EnumSet<Age> allowedAgeGroup = EnumSet.of(Age.AGE_18_TO_45, Age.AGE_46_TO_60, Age.AGE_60_ABOVE);

        System.out.println(allowedAgeGroup);
    }

}