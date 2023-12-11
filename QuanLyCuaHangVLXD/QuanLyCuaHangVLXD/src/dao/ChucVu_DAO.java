package dao;

import connection.MyConnection;
import entity.ChucVu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChucVu_DAO {
    private Connection con;

    public ChucVu_DAO() {
        con = MyConnection.getInstance().getConnection();
    }
    public ResultSet getResultSet(String StoreName)throws Exception {
        ResultSet rs = null;
        try {
            String callStore;
            callStore = "{Call " + StoreName +"}";
            CallableStatement cs = this.con.prepareCall(callStore);
            cs.executeQuery();
            rs = cs.getResultSet();
        } catch (Exception e) {
            throw new Exception("Error get Store " + e.getMessage());
        }
        return rs;
    }
    public List<ChucVu> getLS() {
        List<ChucVu> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_CV");
            while(rs.next()){
                ChucVu cv = new ChucVu(rs.getString(1),rs.getString(2),rs.getDouble(3));
                ds.add(cv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public boolean addChucVu(ChucVu chucVu) {
        try {
            PreparedStatement nvAdd = con.prepareStatement("INSERT INTO CHUCVU ([TENCV],[HESOLUONG]) VALUES(?,?)");
            nvAdd.setString(1,chucVu.getTenChucVu());
            nvAdd.setDouble(2,chucVu.getHeSoLuong());
            int n = nvAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteCV(String maCV) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from CHUCVU where MACV = ?");
            stmt.setString(1, maCV);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateChucVu(String maChucVu, ChucVu chucVu) {
        String sql = "update CHUCVU set TENCV = ?, HESOLUONG = ? WHERE MACV = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,chucVu.getTenChucVu());
            stmt.setDouble(2,chucVu.getHeSoLuong());
            stmt.setString(3,chucVu.getMaChucVu());

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public ChucVu TimKiemTen(String ten){
        ChucVu cv = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from ChucVu where TENCV = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                cv = new ChucVu(rs.getString(1), rs.getString(2), rs.getDouble(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cv;
    }
    public ChucVu TimKiemMa(String ma){
        ChucVu cv = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from ChucVu where MACV = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                cv = new ChucVu(rs.getString(1), rs.getString(2), rs.getDouble(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cv;
    }
}
