package com.microservicios.clinica_veter_branch_service.web.controller;

import com.microservicios.clinica_veter_branch_service.common.exception.ApplicationException;
import com.microservicios.clinica_veter_branch_service.domain.model.Branch;
import com.microservicios.clinica_veter_branch_service.domain.service.BranchService;
import com.microservicios.clinica_veter_branch_service.web.controller.ifc.BranchAPI;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/branch")
public class BranchController implements BranchAPI {

    private static final Logger log = LoggerFactory.getLogger(BranchController.class);
    private final HttpServletRequest request;
    private final BranchService branchService;

    public BranchController(HttpServletRequest request, BranchService branchService) {
        this.request = request;
        this.branchService = branchService;
    }

    @Override
    public ResponseEntity<List<Branch>> getAll() {
        try {
            return new ResponseEntity<List<Branch>>(branchService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<List<Branch>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Branch> getId(Integer id) throws ApplicationException {
        String accept = request.getHeader("Accept");
        if (accept!= null && accept.contains("application/json")){
            return ResponseEntity.ok(branchService.getId(id));
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @Override
    public ResponseEntity<Branch> agg(Branch branch) throws ApplicationException {
        Branch branch1 = branchService.save(branch);
        try {
            if (branch1 != null){
                return ResponseEntity.ok(branch1);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @Override
    public ResponseEntity<Branch> update(Branch branch) throws ApplicationException {
        Branch branch1 = branchService.update(branch);
        if (branch1 != null){
            return ResponseEntity.ok(branch1);
        }
        return ResponseEntity.notFound().build();
    }


    @Override
    public ResponseEntity<String> delete(int idBranch) throws ApplicationException {
        Boolean branch1 = branchService.delete(idBranch);
        if (branch1){
            return ResponseEntity.ok("Branch successfully removed");
        }
        return ResponseEntity.badRequest().build();
    }
}
