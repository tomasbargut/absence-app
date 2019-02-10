package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}