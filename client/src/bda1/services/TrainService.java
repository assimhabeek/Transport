package bda1.services;

import bda1.model.Train;

public class TrainService extends BaseService<Train> {

    public TrainService() {
        super("/train", Train.class);
    }
}
