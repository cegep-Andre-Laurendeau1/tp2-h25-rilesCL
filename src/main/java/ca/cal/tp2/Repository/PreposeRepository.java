package ca.cal.tp2.Repository;
import ca.cal.tp2.modele.Prepose;

import java.sql.*;

public class PreposeRepository extends ParentRepositoryJdbc {

    protected void createTable() throws SQLException {
        String sql = "CREATE TABLE PREPOSE " +
                "(userID INTEGER not NULL, " +
                " name VARCHAR(255), " +
                " email VARCHAR(255), " +
                " phoneNumber VARCHAR(255), " +
                " PRIMARY KEY (userID))";
        stmt.executeUpdate(sql);
    }
    // CREATE
    public Prepose save(Prepose prepose) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "INSERT INTO PREPOSE (userID, name, email, phoneNumber) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, prepose.getUserId());
            pstmt.setString(2, prepose.getNom());
            pstmt.setString(3, prepose.getEmail());
            pstmt.setString(4, prepose.getPhoneNumber());

            pstmt.executeUpdate();
            return prepose;

        } catch (SQLException e) {
            handleException(e);
            return null;
        }
    }
    // READ
    public Prepose getPrepose(int id) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM PREPOSE WHERE userID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Prepose prepose = new Prepose();
                prepose.setUserId(rs.getInt("userID"));
                prepose.setNom(rs.getString("name"));
                prepose.setEmail(rs.getString("email"));
                prepose.setPhoneNumber(rs.getString("phoneNumber"));
                return prepose;
            }
        } catch (SQLException e) {
            handleException(e);
        }
        return null;
    }
}
