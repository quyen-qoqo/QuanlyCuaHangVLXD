package dao;

import connection.MyConnection;
import entity.LoaiVatLieu;
import entity.VatLieu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VatLieu_DAO {
    private Connection con;

    public VatLieu_DAO() {
        con = MyConnection.getInstance().getConnection();
    }

    public ResultSet getResultSet(String StoreName) throws Exception {
        ResultSet rs = null;
        try {
            String callStore;
            callStore = "{Call " + StoreName + "}";
            CallableStatement cs = this.con.prepareCall(callStore);
            cs.executeQuery();
            rs = cs.getResultSet();
        } catch (Exception e) {
            throw new Exception("Error get Store " + e.getMessage());
        }
        return rs;
    }

    public List<VatLieu> getLS() {
        List<VatLieu> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_VL");
            while (rs.next()) {
                VatLieu vatLieu = new VatLieu(rs.getString(1), rs.getString(3), rs.getFloat(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7),rs.getDouble(8));

                LoaiVatLieu_DAO loaiVatLieu_dao =new LoaiVatLieu_DAO();
                LoaiVatLieu loaiVatLieu = loaiVatLieu_dao.TimKiemMa(rs.getString(2));
                vatLieu.setLoaiVatLieu(loaiVatLieu);

                ds.add(vatLieu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public boolean addVatLieu(VatLieu vatLieu) {
        try {
            PreparedStatement sanphamAdd = con.prepareStatement("INSERT INTO VatLieu ([MALOAI],[TENVATLIEU],[TRONGLUONG]," +
                    "[DONVITINH],[XUATXU],[SOLUONG],[GIABAN]) VALUES(?,?,?,?,?,?,?)");
            sanphamAdd.setString(1, vatLieu.getLoaiVatLieu().getMaLoai());
            sanphamAdd.setString(2, vatLieu.getTenVatLieu());
            sanphamAdd.setFloat(3, vatLieu.getTrongLuong());
            sanphamAdd.setString(4, vatLieu.getDonViTinh());
            sanphamAdd.setString(5, vatLieu.getXuatXu());
            sanphamAdd.setInt(6, vatLieu.getSoLuong());
            sanphamAdd.setDouble(7, vatLieu.getGiaBan());

            int n = sanphamAdd.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteVatLieu(String maVatLieu) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from VatLieu where MAVL = ?");
            stmt.setString(1, maVatLieu);
            int n = stmt.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateVatLieu(VatLieu vatLieu) {
        String sql = "update VatLieu set [MALOAI] = ?,[TENVATLIEU] = ?," +
                "[TRONGLUONG] = ?,[DONVITINH] = ?,[XUATXU] = ?,[SOLUONG] = ?,[GIABAN] = ?  where MAVL = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, vatLieu.getLoaiVatLieu().getMaLoai());
            stmt.setString(2, vatLieu.getTenVatLieu());
            stmt.setFloat(3, vatLieu.getTrongLuong());
            stmt.setString(4, vatLieu.getDonViTinh());
            stmt.setString(5, vatLieu.getXuatXu());
            stmt.setInt(6, vatLieu.getSoLuong());
            stmt.setDouble(7, vatLieu.getGiaBan());

            int n = stmt.executeUpdate();
            if (n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public VatLieu TimKiemMa(String ma) {
        VatLieu vatLieu = null;
        try {
            PreparedStatement stmt = con.prepareStatement("select * from VatLieu where MAVL = ?");
            stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vatLieu = new VatLieu(rs.getString(1), rs.getString(3), rs.getFloat(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7),rs.getDouble(8));

                LoaiVatLieu_DAO loaiVatLieu_dao =new LoaiVatLieu_DAO();
                LoaiVatLieu loaiVatLieu = loaiVatLieu_dao.TimKiemMa(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vatLieu;
    }

    public VatLieu TimKiemTen(String ten) {
        VatLieu vatLieu = null;
        try {
            PreparedStatement stmt = con.prepareStatement("select * from VATLIEU where TENVATLIEU = ?");
            stmt.setString(1, ten);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vatLieu = new VatLieu(rs.getString(1), rs.getString(3), rs.getFloat(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7),rs.getDouble(8));

                LoaiVatLieu_DAO loaiVatLieu_dao =new LoaiVatLieu_DAO();
                LoaiVatLieu loaiVatLieu = loaiVatLieu_dao.TimKiemMa(rs.getString(2));
                vatLieu.setLoaiVatLieu(loaiVatLieu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vatLieu;
    }
    public List<VatLieu> TimKiemMaL(String maL) {
        List<VatLieu> ls =new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from VATLIEU where MALOAI = ?");
            stmt.setString(1, maL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                VatLieu vatLieu = new VatLieu(rs.getString(1), rs.getString(3), rs.getFloat(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7),rs.getDouble(8));

                LoaiVatLieu_DAO loaiVatLieu_dao =new LoaiVatLieu_DAO();
                LoaiVatLieu loaiVatLieu = loaiVatLieu_dao.TimKiemMa(rs.getString(2));
                vatLieu.setLoaiVatLieu(loaiVatLieu);

                ls.add(vatLieu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }
    public boolean updateSoLuong(String maVL,int soLuong) {
        String sql = "update VATLIEU set SOLUONG = ? where MAVL = ?";
        try {
            System.out.println("OK1");
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,soLuong);
            stmt.setString(2,maVL);

            int n = stmt.executeUpdate();
            System.out.println("OK2");
            if(n > 0){
                System.out.println("OK3");
                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public String getMa() throws SQLException {
        String ma = "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("SELECT MAX([MAVL]) FROM [dbo].[VatLieu]");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            ma = rs.getString(1);
        }
        return ma;
    }
}
