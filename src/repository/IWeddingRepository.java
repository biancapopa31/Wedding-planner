package repository;

public interface IWeddingRepository {

    model.Wedding getWedding();

    void updateWedding(model.Wedding wedding);

}
