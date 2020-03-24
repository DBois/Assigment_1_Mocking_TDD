package dk.cphbusiness.banking.backend;

import dk.cphbusiness.banking.backend.models.Clock;

public class ClockDummy implements Clock {
    @Override
    public long getTime() {
        return 0;
    }
}
