package dao;

import connection.MyConnection;
import entity.CT_HoaDonBanHang;
import entity.HoaDonBanHang;
import entity.VatLieu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CT_HDBH_DAO {
    private Connection con;

    public CT_HDBH_DAO() {
        con = MyConnection.getInstance().getConnection();
    }

    public boolean addCTHDBH(CT_HoaDonBanHang cthd) {
        try {
            PreparedStatement hdAdd = con.prepareStatement("INSERT INTO CT_HoaDonBanHang ([MAHDBH],[MAVL],[SOLUONG],[DONGIA]) " +
                    "VALUES(?,?,?,?)");
            hdAdd.setString(1, cthd.getHoaDonBanHang().getMaHDBH());
            hdAdd.setString(2, cthd.getVatLieu().getMaVatLieu());
            hdAdd.setInt(3, cthd.getSoLuong());
            hdAdd.setDouble(4, cthd.getDonGia());
            int n = hdAdd.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public CT_HoaDonBanHang TimKiemMaVL(String ma){
        CT_HoaDonBanHang ct = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CT_HOADONBANHANG where MAVL = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                ct = new CT_HoaDonBanHang(rs.getInt(3),rs.getDouble(4));
                HoaDonBanHang_DAO hdDao = new HoaDonBanHang_DAO();
                VatLieu_DAO vatLieu_dao = new VatLieu_DAO();
                HoaDonBanHang hd = hdDao.TimKiemMa(rs.getString(1));
                VatLieu vatLieu = vatLieu_dao.TimKiemMa(rs.getString(2));
                ct.setHoaDonBanHang(hd);
                ct.setVatLieu(vatLieu);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ct;
    }
    public List<CT_HoaDonBanHang> TimKiemHD(String ma){
        List<CT_HoaDonBanHang> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CT_HOADONBANHANG where MAHDBH = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                CT_HoaDonBanHang ct = new CT_HoaDonBanHang(rs.getInt(3),rs.getDouble(4));
                HoaDonBanHang_DAO hdDao = new HoaDonBanHang_DAO();
                VatLieu_DAO vatLieu_dao = new VatLieu_DAO();
                HoaDonBanHang hd = hdDao.TimKiemMa(rs.getString(1));
                VatLieu vatLieu = vatLieu_dao.TimKiemMa(rs.getString(2));
                ct.setHoaDonBanHang(hd);
                ct.setVatLieu(vatLieu);
                ls.add(ct);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }

    public List<VatLieu> TimKiemVatLieuByMaHD(String ma){
        List<CT_HoaDonBanHang> ls = new ArrayList<>();
        List<VatLieu> listSanPham = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CT_HOADONBANHANG where MAHDBH = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                CT_HoaDonBanHang ct = new CT_HoaDonBanHang(rs.getInt(3),rs.getDouble(4));
                HoaDonBanHang_DAO hdDao = new HoaDonBanHang_DAO();
                VatLieu_DAO vatLieu_dao = new VatLieu_DAO();
                HoaDonBanHang hd = hdDao.TimKiemMa(rs.getString(1));
                VatLieu vatLieu = vatLieu_dao.TimKiemMa(rs.getString(2));
                ct.setHoaDonBanHang(hd);
                ct.setVatLieu(vatLieu);
                ls.add(ct);
                listSanPham.add(vatLieu);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listSanPham;
    }
    public CT_HoaDonBanHang TimKiemMaHD(String ma,String maVL){
        CT_HoaDonBanHang ct = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CT_HOADONBANHANG where MAHDBH = ? AND MAVL = ?");
            stmt.setString(1,ma);
            stmt.setString(2,maVL);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                ct = new CT_HoaDonBanHang(rs.getInt(3),rs.getDouble(4));
                HoaDonBanHang_DAO hdDao = new HoaDonBanHang_DAO();
                VatLieu_DAO vatLieu_dao = new VatLieu_DAO();
                HoaDonBanHang hd = hdDao.TimKiemMa(rs.getString(1));
                VatLieu vatLieu = vatLieu_dao.TimKiemMa(rs.getString(2));
                ct.setHoaDonBanHang(hd);
                ct.setVatLieu(vatLieu);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ct;
    }
    public boolean updateSoLuong(String maHD,String maVL,int sl) {
        String sql = "update CT_HOADONBANHANG set SOLUONG = ? where MAHDBH = ? and MAVL = ?";
        try {
            System.out.println("OK1");
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,sl);
            stmt.setString(2,maHD);
            stmt.setString(3,maVL);

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

}
