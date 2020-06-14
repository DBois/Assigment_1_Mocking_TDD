package dk.cphbusiness.banking.backend.utility;

import dk.cphbusiness.banking.backend.models.RealMovement;

import java.util.ArrayList;
import java.util.List;
import static dk.cphbusiness.banking.contract.MovementManager.*;

public class MovementAssembler {

    public static MovementDetail createMovementDetail(RealMovement movement){
        return new MovementDetail(movement.getId(),
                movement.getTime(),
                movement.getAmount(),
                movement.getTarget(),
                movement.getSource());
    }

    public static List<MovementDetail> createMovementDetails(List<RealMovement> movement){
        var summaries = new ArrayList<MovementDetail>();
        movement.forEach(mvmnt -> summaries.add(createMovementDetail(mvmnt)));
        return summaries;
    }
}
