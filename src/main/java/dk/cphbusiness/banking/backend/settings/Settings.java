package dk.cphbusiness.banking.backend.settings;

public class Settings {
    public enum Environment {PRODUCTION, DEVELOPMENT};
    public static Environment env = Environment.DEVELOPMENT;

    public final static String DB_NAME = (env == Environment.DEVELOPMENT) ? "test" : "test_bank";
}
