package Controller;

import Model.Sanpham;
import View.ConnectSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sanpham_Controller {
    public  static DefaultTableModel LaydulieutuDB(){
        DefaultTableModel model = new DefaultTableModel();
        try {
            Connection conn = ConnectSQL.connectDB();
            Statement stmt = conn.createStatement();
            String query = "Select * from Sanpham";
            ResultSet rs = stmt.executeQuery(query);

            model.addColumn("Mã Sản Phẩm");
            model.addColumn("Tên Sản Phẩm");
            model.addColumn("Giá bán");

            while (rs.next()){
                int masp = rs.getInt("Masanpham");
                String tensp = rs.getString("Tensanpham");
                Double giaban = rs.getDouble("Giaban");
                model.addRow(new Object[]{masp, tensp, giaban});
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return model;
    }

    public static void ThemDulieuvaoDB(Integer Masanpham, String Tensanpham, double Giaban) {
        try {
            Connection conn = ConnectSQL.connectDB();
            String query = "INSERT INTO Sanpham (Masanpham, Tensanpham, Giaban) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Masanpham);
            pstmt.setString(2, Tensanpham);
            pstmt.setDouble(3, Giaban);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void CapnhatDulieutrongDB(int Masanpham, String Tensanpham, double Giaban) {
        try {
            Connection conn = ConnectSQL.connectDB();
            String query = "UPDATE Sanpham SET Tensanpham = ?, Giaban = ? WHERE Masanpham = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Tensanpham);
            pstmt.setDouble(2, Giaban);
            pstmt.setInt(3, Masanpham);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void XoadulieutuDB(int Masanpham) {
        try {
            Connection conn = ConnectSQL.connectDB();
            String query = "DELETE FROM Sanpham WHERE Masanpham = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Masanpham);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean KiemTraMasanphamTonTai(int Masanpham) {
        try {
            Connection conn = ConnectSQL.connectDB();
            String query = "SELECT COUNT(*) AS count FROM Sanpham WHERE Masanpham = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Masanpham);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt("count");
            rs.close();
            pstmt.close();
            conn.close();
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static DefaultTableModel TimKiemDulieu(String masanpham, String tensanpham, String giaban) {
        DefaultTableModel model = new DefaultTableModel();
        try {
            Connection conn = ConnectSQL.connectDB();
            String query = "SELECT * FROM Sanpham WHERE Masanpham LIKE ? AND Tensanpham LIKE ? AND Giaban LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + masanpham + "%");
            pstmt.setString(2, "%" + tensanpham + "%");
            pstmt.setString(3, "%" + giaban + "%");
            ResultSet rs = pstmt.executeQuery();

            model.addColumn("Mã Sản Phẩm");
            model.addColumn("Tên Sản Phẩm");
            model.addColumn("Giá Bán");

            while (rs.next()) {
                int id = rs.getInt("Masanpham");
                String name = rs.getString("Tensanpham");
                double price = rs.getDouble("Giaban");
                model.addRow(new Object[]{id, name, price});
            }
            pstmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
        return model;
    }


}

