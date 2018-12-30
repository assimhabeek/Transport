package bda1.services;

import bda1.model.Avion;

public class AvionService extends BaseService<Avion> {

    public AvionService() {
        super("/avion", Avion.class);
    }
}
