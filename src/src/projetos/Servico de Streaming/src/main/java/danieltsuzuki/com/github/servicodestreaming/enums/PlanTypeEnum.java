package danieltsuzuki.com.github.servicodestreaming.enums;

public enum PlanTypeEnum {
    BASIC(0),
    STANDARD(2),
    PREMIUM(4);

    private final int maxLinkedAccounts;

    PlanTypeEnum(int maxLinkedAccounts) {
        this.maxLinkedAccounts = maxLinkedAccounts;
    }

    public int getMaxLinkedAccounts() {
        return maxLinkedAccounts;
    }

}
