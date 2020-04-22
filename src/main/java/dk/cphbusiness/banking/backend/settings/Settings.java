package dk.cphbusiness.banking.backend.settings;

public class Settings {
    private enum Environment {PRODUCTION, DEVELOPMENT};
    static Environment env = Environment.PRODUCTION;

    public final static String DB_NAME = (env == Environment.DEVELOPMENT) ? "test" : "test_bank";
}
