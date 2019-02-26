package logic;

import java.util.List;

import data.DataCategory;
import entities.Category;
import logic.exceptions.CategoryException;
import utils.Utils;

/**
 * ControllerCategory
 */
public class ControllerCategory {
    private DataCategory dataCategory;

    public ControllerCategory(){
        dataCategory = new DataCategory();
    }
    public boolean save(Category category) throws CategoryException{
        validar(category);
        return dataCategory.save(category);
    }

    public void validar(Category category) throws CategoryException{
        if(Utils.isNullOrEmpty(category.getName())){
            throw new CategoryException("Nombre invalido");
        }
        if(Utils.isNullOrEmpty(category.getDesc())){
            throw new CategoryException("Descripcion invalida");
        }
    }
	public List<Category> getAll() {
        return dataCategory.getAll();
	}
}