package dk.cphbusiness.banking.backend.models;

import java.util.Date;

public class RealClock implements  Clock {
    @Override
    public long getTime() {
        // Returns current time in ms
        return new Date().getTime();
    }
}
