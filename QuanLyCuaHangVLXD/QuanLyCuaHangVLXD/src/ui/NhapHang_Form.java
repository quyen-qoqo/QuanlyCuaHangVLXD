package ui;

import com.toedter.calendar.JDateChooser;
import dao.*;
import entity.*;
import table_model.CT_HDNH_TableModel;
import table_model.VatLieu_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NhapHang_Form extends JPanel {
    JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblTienSanPham,lblTienNhan,lblTienThue,lblTienThoi,lblTongTien,
            lblMaHD,lblST,lblNgayLap,lblNhanVien,lblDiaChi,lblNhaCC,lblEmail,
            lblMa,lblTen, lblLoaiSanPham,lblXuatXu,lblSoLuong,lblGiaBan,lblDonViTinh;
    JTextField txttTienSanPham,txtTienNhan,txtThue,txtTienThoi,txtTong
            ,txtSDT,txtMaHD,txtNhanVien,txtDiaChi,txtNhaCC,txtMail
            ,txtXuatXu,txtSoLuong,txtGia,txtMa,txtTen, txtTrongLuong;
    JButton btnHoaDonMoi,btnIn,btnThoat,btnNhaCC, btnNhapSanPham;
    JComboBox cbcLoai,cbcDonVi;
    JDateChooser NgayLap,NgayHetHan;
    String mahd = "";
    NhanVien nv;
    NhaCungCap ncc;
    NhapHang_Form nh;
    private JLabel lblTrongLuong;

    public NhapHang_Form(){

    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnNorth_C = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("LẬP HÓA ĐƠN NHẬP HÀNG");
        lblTieuDe.setFont(new Font("arial", Font.BOLD,18));
        lblTieuDe.setForeground(Color.RED);
        pnTieuDe.add(lblTieuDe);
        pnNorth.add(pnTieuDe,BorderLayout.NORTH);

        JPanel pnThongTin = new JPanel();
        pnThongTin.setBorder(new TitledBorder("Thông Tin Hóa Đơn"));
        Box b,b1,b2,b3,b4;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(500,210));
        b.add(Box.createVerticalStrut(30));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMaHD = new JLabel("Mã HD:"));
        b1.add(txtMaHD = new JTextField());
        txtMaHD.setEditable(false);
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblNgayLap = new JLabel("Ngày Lập HD:"));
        NgayLap = new JDateChooser();
        NgayLap.setDateFormatString("yyyy-MM-dd");
        try {
            Date date = Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date);
            NgayLap.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        b1.add(NgayLap);
        b.add(Box.createVerticalStrut(10));

        Box b11;
        b.add(b11 = Box.createHorizontalBox());
        b11.add(lblNhanVien = new JLabel("Nhân Viên Lập HD: "));
        b11.add(txtNhanVien = new JTextField());
        b.add(Box.createVerticalStrut(10));
        b.add(b2 = Box.createHorizontalBox());
        b2.add(btnNhaCC = new JButton("Nhà Cung Cấp"));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblNhaCC = new JLabel("Nhà Cung Cấp: "));
        b3.add(txtNhaCC = new JTextField());
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblEmail = new JLabel("Email: "));
        b3.add(txtMail = new JTextField());

        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(lblST = new JLabel("Điện Thoại: "));
        b4.add(txtSDT = new JTextField());
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblDiaChi = new JLabel("Địa Chỉ: "));
        b4.add(txtDiaChi = new JTextField());
        b.add(Box.createVerticalStrut(10));

        lblMaHD.setPreferredSize(lblNhanVien.getPreferredSize());
        lblNgayLap.setPreferredSize(lblNhanVien.getPreferredSize());
        lblNhaCC.setPreferredSize(lblNhanVien.getPreferredSize());
        lblST.setPreferredSize(lblNhanVien.getPreferredSize());

        JPanel pnVatLieu = new JPanel();
        pnVatLieu.setBorder(new TitledBorder("Nhập Vật Liệu"));
        pnVatLieu.setLayout(new BorderLayout());
        Box bb,bb1,bb2,bb3,bb4,bb5,bb6,bb7;
        bb = Box.createVerticalBox();
        bb.setPreferredSize(new Dimension(650,170));

        bb.add(bb1 = Box.createHorizontalBox());
        bb1.add(lblMa = new JLabel("Mã Vật Liệu: "));
        bb1.add(txtMa = new JTextField(30));
        txtMa.setEditable(false);
        bb1.add(Box.createHorizontalStrut(20));
        bb1.add(lblTen = new JLabel("Tên Vật Liệu:    "));
        bb1.add(txtTen = new JTextField(30));
        bb.add(Box.createVerticalStrut(10));

        bb.add(bb2 = Box.createHorizontalBox());
        bb2.add(lblLoaiSanPham = new JLabel("Loại Vật Liệu: "));
        cbcLoai = new JComboBox<>();
        LoaiVatLieu_DAO lDao = new LoaiVatLieu_DAO();
        for (LoaiVatLieu l: lDao.getLS()) {
            cbcLoai.addItem(l.getTenLoai());
        }
        cbcLoai.setPreferredSize(new Dimension(140,20));
        bb2.add(cbcLoai);
        bb2.add(Box.createHorizontalStrut(20));
        bb2.add(lblXuatXu = new JLabel("Xuất Xứ:    "));
        bb2.add(txtXuatXu = new JTextField(30));
        bb.add(Box.createVerticalStrut(10));

        bb.add(bb4 = Box.createHorizontalBox());
        bb4.add(lblSoLuong = new JLabel("Số Lượng: "));
        bb4.add(txtSoLuong = new JTextField(30));
        bb4.add(Box.createHorizontalStrut(20));
        bb4.add(lblGiaBan = new JLabel("Giá Bán:    "));
        bb4.add(txtGia = new JTextField(30));
        bb.add(Box.createVerticalStrut(10));

        bb.add(bb5 = Box.createHorizontalBox());
        bb5.add(lblDonViTinh = new JLabel("Đơn Vị Tính: "));
        cbcDonVi = new JComboBox<>();
        cbcDonVi = new JComboBox<>();
        cbcDonVi.addItem("Bộ");
        cbcDonVi.addItem("Thùng");
        cbcDonVi.setPreferredSize(new Dimension(260,20));
        bb5.add(cbcDonVi);
        bb5.add(Box.createHorizontalStrut(20));
        bb5.add(lblTrongLuong = new JLabel("Trọng Lượng: "));
        bb5.add(txtTrongLuong = new JTextField(30));
        bb5.add(Box.createVerticalStrut(10));

        bb.add(bb7 = Box.createHorizontalBox());
        bb7.add(btnNhapSanPham = new JButton("Nhập Vật Liệu"));

        lblMa.setPreferredSize(lblLoaiSanPham.getPreferredSize());
        lblTen.setPreferredSize(lblLoaiSanPham.getPreferredSize());
        lblDonViTinh.setPreferredSize(lblLoaiSanPham.getPreferredSize());
        lblXuatXu.setPreferredSize(lblLoaiSanPham.getPreferredSize());
        lblSoLuong.setPreferredSize(lblLoaiSanPham.getPreferredSize());
        lblTrongLuong.setPreferredSize(lblLoaiSanPham.getPreferredSize());
        lblGiaBan.setPreferredSize(lblLoaiSanPham.getPreferredSize());

        pnVatLieu.add(bb);
        pnThongTin.add(b);

        pnNorth.add(pnVatLieu,BorderLayout.EAST);
        pnNorth.add(pnThongTin,BorderLayout.CENTER);

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setBorder(new TitledBorder("Danh Sách Vật Liệu Đã Nhập"));
        List<VatLieu> ls1 = new ArrayList<>();
        VatLieu_TableModel model1 = new VatLieu_TableModel(ls1);
        JTable table1 = new JTable();
        table1.setModel(model1);
        JScrollPane sc1 = new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc1.setPreferredSize(new Dimension(850,140));

        pnCenter.add(sc1);

        //pnSouth
        pnSouth = new JPanel();
        pnSouth.setLayout(new BorderLayout());
        Box s,s1,s2,s3;
        s = Box.createVerticalBox();
        s.setPreferredSize(new Dimension(1000,100));
        s.add(s1 = Box.createHorizontalBox());
        s1.add(lblTienSanPham = new JLabel("Tổng Tiền Vật Liệu: "));
        s1.add(txttTienSanPham = new JTextField());
        txttTienSanPham.setEditable(false);
        s1.add(Box.createHorizontalStrut(50));
        s1.add(lblTienNhan = new JLabel("          "));
        s1.add(txtTienNhan = new JTextField());
        txtTienNhan.setEditable(false);
        s.add(Box.createVerticalStrut(7));

        s.add(s2 = Box.createHorizontalBox());
        s2.add(lblTienThue = new JLabel("Thuế GTGT: "));
        s2.add(txtThue = new JTextField());
        txtThue.setEditable(false);
        s2.add(Box.createHorizontalStrut(50));
        s2.add(lblTienThoi = new JLabel("           "));
        s2.add(txtTienThoi = new JTextField());
        txtTienThoi.setEditable(false);
        s.add(Box.createVerticalStrut(10));

        s.add(s3 = Box.createHorizontalBox());
        s3.add(lblTongTien = new JLabel("Tổng Tiền HD: "));
        s3.add(txtTong = new JTextField());
        txtTong.setEditable(false);
        s3.add(Box.createHorizontalStrut(50));
        s3.add(btnHoaDonMoi = new JButton("Tạo Hóa Đơn Mới"));
        s.add(Box.createVerticalStrut(7));

        Box d;
        d = Box.createVerticalBox();
        d.setPreferredSize(new Dimension(100,30));
        d.add(btnIn = new JButton("In HD"));
        d.add(Box.createVerticalStrut(15));
        d.add(btnThoat = new JButton("Thoát"));

        pnSouth.add(s,BorderLayout.WEST);
        pnSouth.add(d,BorderLayout.EAST);
        pnSouth.setBorder(new TitledBorder("Chi tiết hóa đơn"));

        lblTienNhan.setPreferredSize(lblTienSanPham.getPreferredSize());
        lblTienThoi.setPreferredSize(lblTienSanPham.getPreferredSize());
        lblTienThue.setPreferredSize(lblTienSanPham.getPreferredSize());
        lblTongTien.setPreferredSize(lblTienSanPham.getPreferredSize());

        cbcLoai.setPreferredSize(new Dimension(260,20));
        cbcDonVi.setPreferredSize(new Dimension(225,20));

        if(nv != null){
            txtNhanVien.setEditable(false);
            txtNhanVien.setText(nv.getTenNV());
        }
        btnNhaCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DS_NhaCungCap_Form ds = new DS_NhaCungCap_Form();
                ds.setVisible(true);
                ds.nhapHang = nh;
            }
        });
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        LoaiVatLieu_DAO loaiVatLieu_dao = new LoaiVatLieu_DAO();
        VatLieu_DAO vatLieu_dao = new VatLieu_DAO();
        CT_HDNH_DAO ctDao = new CT_HDNH_DAO();
        HoaDonNhapHang_DAO hdDao = new HoaDonNhapHang_DAO();

        btnNhapSanPham.addActionListener(new ActionListener() {
            double tongTien = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                String dateTime = (String) formatter.format(NgayHetHan.getDate());
                if(!txtTen.getText().trim().equals("")){
                    VatLieu vatLieu = new VatLieu(txtMa.getText(), txtTen.getText(), Float.parseFloat(txtTrongLuong.getText().trim()), cbcDonVi.getSelectedItem().toString(),
                            txtXuatXu.getText(), Integer.parseInt(txtSoLuong.getText().trim()), Double.parseDouble(txtGia.getText().trim()));
                    LoaiVatLieu loaiVatLieu = loaiVatLieu_dao.TimKiemTen(cbcLoai.getSelectedItem().toString());
                    vatLieu.setLoaiVatLieu(loaiVatLieu);
                    if(vatLieu_dao.addVatLieu(vatLieu)){
                        try {
                            mahd = hdDao.getMa();
                            String maSanPham = vatLieu_dao.getMa();
                            VatLieu vl = vatLieu_dao.TimKiemMa(maSanPham);
                            vl.setLoaiVatLieu(loaiVatLieu);
                            CT_HoaDonNhapHang ct = new CT_HoaDonNhapHang(Integer.parseInt(txtSoLuong.getText().trim()),Double.parseDouble(txtGia.getText().trim()));

                            HoaDonNhapHang hd = hdDao.TimKiemMa(mahd);
                            ct.setHoaDonNhapHang(hd);
                            ct.setVatLieu(vl);
                            System.out.println(vl);
                            System.out.println(hd);
                            System.out.println(ct);
                            if(ctDao.addCTHDNH(ct)){
                                table1.setModel(new CT_HDNH_TableModel(ctDao.TimKiemHD(mahd)));
                                tongTien += (double) ct.getSoLuong() * ct.getDonGia();
                                txttTienSanPham.setText(String.valueOf(tongTien));
                                txtThue.setText("5%");
                                txtTong.setText(String.valueOf(tongTien + tongTien * 0.05));
                                hdDao.updateTongtien(mahd, tongTien + tongTien * 0.05);
                                clearText();
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Bạn chưa nhập tên vật liệu!");
                }
            }
        });

        btnHoaDonMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txttTienSanPham.setText("");
                txtTong.setText("");
                txtNhaCC.setText("");
                txtDiaChi.setText("");
                txtSDT.setText("");
                txtMail.setText("");
                List<CT_HoaDonNhapHang> ls = new ArrayList<>();
                table1.setModel(new CT_HDNH_TableModel(ls));
            }
        });
        btnIn.addActionListener(new ActionListener() {
//        	private JasperPrint jprint;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyQuayThuoc;instance=SQLEXPRESS;user=sa;password=hung");
//					Connection con = MyConnection.getInstance().getConnection();
					String sql = "SELECT hd.MaHDNH,hd.NGAYLAPHD,nv.TENNV,ncc.TENNHACC,ncc.SODT,ncc.EMAIL,ncc.DIACHI,t.TENTHUOC,t.CONGDUNG,t.DONVITINH,t.GIABAN,t.NGAYHETHAN,\r\n" + 
							"ct.SOLUONG,t.THANHPHAN,lt.TENLOAI\r\n" + 
							"FROM [dbo].[HoaDonNhapHang] hd JOIN [dbo].[CT_HoaDonNhapHang] ct  ON hd.MaHDNH = ct.MaHDNH\r\n" + 
							"JOIN [dbo].[NhanVien] nv ON hd.MANV = nv.MANV\r\n" + 
							"JOIN [dbo].[NhaCungCap] ncc ON hd.MANHACC = ncc.MANHACC\r\n" + 
							"JOIN [dbo].[Thuoc] t ON ct.MAT = t.MAT\r\n" + 
							"JOIN [dbo].[LoaiThuoc] lt ON t.MALOAI = lt.MALOAI\r\n" + 
							"WHERE hd.MaHDNH = "+"'"+mahd+"'";
//					JasperDesign jdesign = JRXmlLoader.load("D:\\PTUD_ChuongTrinhQuanLyQuayThuoc\\PTUD_QuanLyQuayThuoc\\src\\Report\\HoaDonNhapHang.jrxml");
//					JRDesignQuery updateQuery = new JRDesignQuery();
//					updateQuery.setText(sql);
//					jdesign.setQuery(updateQuery);
//
//					Map<String, Object> parameters = new HashMap<String, Object>();
//					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
//					JasperPrint jpasperPrint = JasperFillManager.fillReport(jreport, parameters,con);
//
//					JasperViewer.viewReport(jpasperPrint,false);
				}catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2);
				}

			}
		});
        this.setLayout(new BorderLayout());
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }
    public void clearText(){
        txtMa.setText("");
        txtTen.setText("");
        txtGia.setText("");
        txtSoLuong.setText("");
        txtXuatXu.setText("");
        cbcDonVi.setSelectedIndex(0);
        cbcLoai.setSelectedIndex(0);
        txtTen.requestFocus();
    }
}
