package com.ezwash.backend.resource.business;

import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.business.Report;
import com.ezwash.backend.domain.model.interactions.CardUsers;

import javax.swing.plaf.nimbus.State;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class SaveContractResource {

    @NotNull
    private Date date;

    public Date getDate() {
        return date;
    }

    public SaveContractResource setDate(Date date) {
        this.date = date;
        return this;
    }




}
