package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entities.Category;

public class DataCategory {
    public Category get(int categoryID) {
        Category category = null;
        try(Connection conn = ConnectorBuilder.getConnector()) {
            PreparedStatement stmtCategory = conn.prepareStatement("select * from categories where categoryID = ?");
            stmtCategory.setInt(1, categoryID);
            ResultSet rs = stmtCategory.executeQuery();
            if (rs.next() && rs != null) {
                category = new Category(rs);
            }
            rs.close();
            stmtCategory.close();
            conn.close();
        } catch (Exception e) {
            // TODO: Implementar logger
        }
        return category;
    }

    public boolean save(Category category) {
        try (Connection conn = ConnectorBuilder.getConnector()){
            PreparedStatement stmt = conn.prepareStatement(
                "insert into categories(`desc`,name) values(?,?)"
            );
            stmt.setString(1, category.getDesc());
            stmt.setString(2, category.getName());
            stmt.executeUpdate();
        } catch (Exception e) {
            // TODO Implementar Logger
            return false;
        }
        return true;
    }

	public ArrayList<Category> getAll() {
        ArrayList<Category> categories = new ArrayList<Category>();
        try(Connection conn = ConnectorBuilder.getConnector()){
            PreparedStatement stmt = conn.prepareStatement(
                "select * from categories"
            );
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                categories.add(new Category(rs));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
		return categories;
	}
    // TODO: Esto capaz que sirva
	// public ArrayList<Category> getBatch(ArrayList<Integer> categoriesID) {
    //     ArrayList<Category> categories = new ArrayList<Category>();
    //     for(Integer categoryID: categoriesID){
    //         categories.add(this.get(categoryID));
    //     }
    //     return categories;
	// }
}