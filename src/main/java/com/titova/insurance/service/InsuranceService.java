package com.titova.insurance.service;

import com.titova.insurance.model.Insurance;
import java.util.List;

public interface InsuranceService {
    
    void createInsurance(Insurance insurance);

    Insurance readInsurance(int insuranceId);

    void updateInsurance(Insurance insurance);

    void deleteInsurance(Insurance insurance);

    List getAllInsurances();
    
    Insurance getInsuranceByName(String name);
}
