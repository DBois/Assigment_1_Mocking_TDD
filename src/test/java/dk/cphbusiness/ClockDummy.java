package dk.cphbusiness;

import dk.cphbusiness.banking.Clock;

public class ClockDummy implements Clock {
    @Override
    public long getTime() {
        return 0;
    }
}
