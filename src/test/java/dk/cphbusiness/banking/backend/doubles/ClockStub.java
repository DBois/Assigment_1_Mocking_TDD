package dk.cphbusiness.banking.backend.doubles;

import dk.cphbusiness.banking.backend.models.Clock;

public class ClockStub implements Clock {
    @Override
    public long getTime() {
        return 1585812373273L;
    }
}
