package dk.cphbusiness.banking.backend.facade;

import dk.cphbusiness.banking.backend.datalayer.DAO;
import dk.cphbusiness.banking.backend.exceptions.RestException;
import dk.cphbusiness.banking.backend.models.RealMovement;
import dk.cphbusiness.banking.backend.utility.MovementAssembler;
import dk.cphbusiness.banking.contract.MovementManager;

import java.util.List;

public class MovementFacade implements MovementManager {

    private DAO DAO;

    public MovementFacade() {
        this.DAO = new DAO();
    }

    public List<MovementDetail> getMovements(String accountNumber) throws Exception {
        List<RealMovement> movements;
        try {
            movements = DAO.getMovements(accountNumber);
            if (movements == null) throw new RestException("No movements found for the given account", 404);
            return MovementAssembler.createMovementDetails(movements);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
