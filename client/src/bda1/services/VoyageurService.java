package bda1.services;

import bda1.model.Voyageur;

public class VoyageurService extends BaseService<Voyageur> {

    public VoyageurService() {
        super("/voyageur", Voyageur.class);
    }
}
