package danieltsuzuki.com.github.servicodestreaming.enums;

public enum PerfilEnum {
    KID(13),
    TEEN(18),
    ADULT(100);

    int ageLimit;

    PerfilEnum(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public static PerfilEnum definePerfilByAge(int age) {
        for (PerfilEnum perfil : PerfilEnum.values()) {
            if (age < perfil.ageLimit) {
                return perfil;
            }
        }
        return ADULT; // Default to ADULT if age exceeds all limits
    }

    public static PerfilEnum definePerfilByAge(int age, boolean allowAdultContent) {
        for (PerfilEnum perfil : PerfilEnum.values()) {
            if (allowAdultContent && age >= KID.getAgeLimit() && age < TEEN.getAgeLimit()) {
                return ADULT;
            }
            if (age < perfil.ageLimit) {
                return perfil;
            }
        }
        return ADULT; // Default to ADULT if age exceeds all limits
    }

    public int getAgeLimit() {
        return ageLimit;
    }

}
