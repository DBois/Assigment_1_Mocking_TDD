package dk.cphbusiness.banking.backend.utility;

import dk.cphbusiness.banking.backend.models.Movement;
import dk.cphbusiness.banking.backend.models.RealMovement;

import java.util.ArrayList;
import java.util.List;
import static dk.cphbusiness.banking.contract.MovementManager.*;
import static dk.cphbusiness.banking.backend.utility.AccountAssembler.*;

public class MovementAssembler {

    public static MovementDetail createMovementDetail(RealMovement movement){
        return new MovementDetail(movement.getId(),
                movement.getTime(),
                movement.getAmount(),
                createAccountSummary(movement.getTarget()),
                createAccountSummary(movement.getSource()));
    }

    public static List<MovementDetail> createMovementDetails(List<RealMovement> movement){
        var summaries = new ArrayList<MovementDetail>();
        movement.forEach(mvmnt -> summaries.add(createMovementDetail(mvmnt)));
        return summaries;
    }
}
