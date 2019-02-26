package logic;

import java.sql.SQLException;

import data.DataReview;
import entities.Review;
import logic.exceptions.ReviewException;
import utils.Utils;

/**
 * ControllerReview
 */
public class ControllerReview {
    private final DataReview dataReview;

    public ControllerReview(){
        dataReview = new DataReview();
    }
    public void save(Review review) throws SQLException, ReviewException {
        validar(review);
        review.setReviewDate(Utils.getCurrentTime());
        dataReview.save(review);
    }

    private void validar(Review review) throws ReviewException {
        if(Utils.isNullOrEmpty(review.getTitle())){
            throw new ReviewException("El titulo no puede estar vacio");
        }
        if(Utils.isNullOrEmpty(review.getDesc())){
            throw new ReviewException("La descripcion no puede ser vacia");
        }
    }
}