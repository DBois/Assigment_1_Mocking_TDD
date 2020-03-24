package dk.cphbusiness.banking.backend.contract;

import dk.cphbusiness.banking.backend.doubles.ClockDummy;
import dk.cphbusiness.banking.backend.models.*;
import dk.cphbusiness.banking.contract.MovementManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovementManagerDummy implements MovementManager {

    List<Movement> movements;
    Map<String, Account> accounts;


    public MovementManagerDummy() {

        var bank = new RealBank("12345678", "Nordea");
        var cpr1 = "1008956666";
        var cpr2 = "0101010001";

        var adam = new RealCustomer(cpr1, "Adam", bank);
        var emil = new RealCustomer(cpr2, "Emil", bank);

        var clock = new ClockDummy();
        var source = new RealAccount(bank, adam, "0123456789", clock);
        var target = new RealAccount(bank, emil, "9876543210", clock);


        var mvmt1 = new RealMovement(1, clock.getTime(), 10000L, source, target);
        var mvmt2 = new RealMovement(2, clock.getTime(), 10000L, source, target);


        movements = new ArrayList<>() {{
            add(mvmt1);
            add(mvmt2);
        }};

        var sourceMovements = source.getMovements();
        sourceMovements.add(mvmt1);
        sourceMovements.add(mvmt2);

        accounts = new HashMap<>() {{
            put(source.getNumber(), source);
            put(target.getNumber(), target);
        }};

    }

    @Override
    public List<MovementDetail> getMovements(String accountNumber) {
        var acc = accounts.get(accountNumber);
        return MovementAssembler.createMovementDetails(acc.getMovements());
    }
}
