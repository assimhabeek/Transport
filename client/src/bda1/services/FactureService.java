package bda1.services;

import bda1.model.Facture;

public class FactureService extends BaseService<Facture> {

    public FactureService() {
        super("/facture", Facture.class);
    }
}
