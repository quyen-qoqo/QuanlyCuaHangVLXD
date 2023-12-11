package dao;

import connection.MyConnection;
import entity.LoaiVatLieu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoaiVatLieu_DAO {
    private Connection con;

    public LoaiVatLieu_DAO() {
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
    public List<LoaiVatLieu> getLS() {
        List<LoaiVatLieu> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_LVL");
            while(rs.next()){
                LoaiVatLieu lt =new LoaiVatLieu(rs.getString(1),rs.getString(2));
                ds.add(lt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public boolean addLoaiVatLieu(LoaiVatLieu loaiSanPham) {
        try {
            PreparedStatement nvAdd = con.prepareStatement("INSERT INTO LOAIVATLIEU ([TENLOAI]) VALUES(?)");
            nvAdd.setString(1,loaiSanPham.getTenLoai());
            int n = nvAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteLVL(String maLoai) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from LOAIVATLIEU where MALOAI = ?");
            stmt.setString(1, maLoai);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateLoaiVatLieu(String maLoai, LoaiVatLieu loaiVatLieu) {
        String sql = "update LOAIVATLIEU set MaLoai = ? ,TENLOAI = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,loaiVatLieu.getMaLoai());
            stmt.setString(2,loaiVatLieu.getTenLoai());

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public LoaiVatLieu TimKiemMa(String ma){
        LoaiVatLieu lt = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LOAIVATLIEU where MALOAI = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                lt = new LoaiVatLieu(rs.getString(1),rs.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lt;
    }
    public LoaiVatLieu TimKiemTen(String ten){
        LoaiVatLieu lt = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LOAIVATLIEU where TENLOAI = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                lt = new LoaiVatLieu(rs.getString(1),rs.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lt;
    }
}
