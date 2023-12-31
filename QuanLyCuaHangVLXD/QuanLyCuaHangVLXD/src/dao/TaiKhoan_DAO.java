package dao;

import connection.MyConnection;
import entity.TaiKhoan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoan_DAO {
    private Connection con;

    public TaiKhoan_DAO() {
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
    public List<TaiKhoan> getLS() {
        List<TaiKhoan> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_TK");
            while(rs.next()){
                TaiKhoan lt =new TaiKhoan(rs.getString(1),rs.getString(2), rs.getString(3));
                NhanVien_DAO nhanVien_dao = new NhanVien_DAO();
                lt.setNhanVien(nhanVien_dao.TimKiemMa(rs.getString(4)));
                ds.add(lt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public boolean addTaiKhoan(TaiKhoan taiKhoan) {
        try {
            PreparedStatement nvAdd = con.prepareStatement("INSERT INTO TAIKHOAN ([TENDN],[MATKHAU],[MANV]) VALUES(?,?,?)");
            nvAdd.setString(1,taiKhoan.getTenDangNhap());
            nvAdd.setString(2,taiKhoan.getMatKhau());
            nvAdd.setString(3,taiKhoan.getNhanVien().getMaNV());
            int n = nvAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteTK(String maTK) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from TAIKHOAN where MATK = ?");
            stmt.setString(1, maTK);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateTaiKhoan(String maTK, TaiKhoan taiKhoan) {
        String sql = "update TAIKHOAN set TENDN = ?, MATKHAU = ? ,MANV = ? WHERE MATK = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,taiKhoan.getTenDangNhap());
            stmt.setString(2,taiKhoan.getMatKhau());
            stmt.setString(3,taiKhoan.getNhanVien().getMaNV());
            stmt.setString(4,taiKhoan.getMaTaiKhoan());

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public TaiKhoan TimKiemMa(String tenDN, String matKhau){
        TaiKhoan lt = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from TAIKHOAN where TENDN = ? and MATKHAU = ?");
            stmt.setString(1,tenDN);
            stmt.setString(2,matKhau);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                lt = new TaiKhoan(rs.getString(1),rs.getString(2), rs.getString(3));

                NhanVien_DAO nhanVien_dao = new NhanVien_DAO();
                lt.setNhanVien(nhanVien_dao.TimKiemMa(rs.getString(4)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lt;
    }
}
