package ui;

import entity.NhanVien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GD_NhanVien extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnCneter;
    JLabel lblGiaoVien,lblTenGV,lblThanhVien,lblSV1,lbl1,lblSV2,lbl2,lblSV3;
    NhanVien nv;
    public GD_NhanVien(){

        doShow();
    }
    public void doShow(){
        setSize(1200,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Quản Lý Mua Bán Đồ Bảo Hộ");

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        //Menu chương trình
//        JPanel pnNorth = new JPanel();
        Font ftmn = new Font("arial",Font.BOLD,16);
        JMenuBar menuBar = new JMenuBar();

        JMenu mnTrangChu = new JMenu("Trang Chủ");
        mnTrangChu.setIcon(new ImageIcon(getClass().getResource("/images/house1.png")));
        mnTrangChu.setFont(ftmn);
        mnTrangChu.isSelected();


        JMenu mnHeThong = new JMenu("Hệ Thống");
        JMenuItem mnThoat, mnDangXuat, mnDoiMatKhau;
        mnHeThong.setIcon(new ImageIcon(getClass().getResource("/images/system1.png")));
        mnHeThong.setFont(ftmn);
        mnThoat = new JMenuItem("Thoát");
        mnHeThong.add(mnThoat);
        mnDangXuat = new JMenuItem("Đăng Xuất");
        mnHeThong.add(mnDangXuat);
        mnDoiMatKhau = new JMenuItem("Đổi Mật Khẩu");
        mnHeThong.add(mnDoiMatKhau);
        mnThoat.setPreferredSize(new Dimension(150,30));
        mnDangXuat.setPreferredSize(new Dimension(150,30));
        mnDoiMatKhau.setPreferredSize(new Dimension(150,30));

        JMenu mnQuanLy = new JMenu("Quản Lý");
        mnQuanLy.setIcon(new ImageIcon(getClass().getResource("/images/quanly1.png")));
        mnQuanLy.setFont(ftmn);
        JMenuItem mnSanPham,mnKhachHang,mnNhanVien,mnNhaCC,mnLoaiSanPham, mnChatLieu, mnMauSac, mnChucVu;
//        mnQuanLy.add(mnSanPham = new JMenuItem("Sản Phẩm"));
        mnQuanLy.add(mnKhachHang = new JMenuItem("Khách Hàng"));
//        mnQuanLy.add(mnNhanVien = new JMenuItem("Nhân Viên"));
//        mnQuanLy.add(mnNhaCC = new JMenuItem("Nhà Cung Cấp"));
//        mnQuanLy.add(mnLoaiSanPham = new JMenuItem("Loại Sản Phẩm"));
//        mnQuanLy.add(mnChatLieu = new JMenuItem("Chất Liệu"));
//        mnQuanLy.add(mnMauSac = new JMenuItem("Màu Sắc"));
//        mnQuanLy.add(mnChucVu = new JMenuItem("Chức Vụ"));

//        mnSanPham.setPreferredSize(new Dimension(150,30));
        mnKhachHang.setPreferredSize(new Dimension(150,30));
//        mnNhaCC.setPreferredSize(new Dimension(150,30));
//        mnNhanVien.setPreferredSize(new Dimension(150,30));
//        mnLoaiSanPham.setPreferredSize(new Dimension(150,30));
//        mnChatLieu.setPreferredSize(new Dimension(150,30));
//        mnMauSac.setPreferredSize(new Dimension(150,30));
//        mnChucVu.setPreferredSize(new Dimension(150,30));

        JMenu mnXuLy = new JMenu("Xử Lý");
        mnXuLy.setIcon(new ImageIcon(getClass().getResource("/images/no-touch1.png")));
        mnXuLy.setFont(ftmn);
        JMenuItem mnBan,mnNhap;
        mnXuLy.add(mnBan = new JMenuItem("Bán Đồ Bảo Hộ"));
        mnXuLy.add(mnNhap = new JMenuItem("Nhập Đồ Bảo Hộ"));
        mnNhap.setPreferredSize(new Dimension(150,30));
        mnBan.setPreferredSize(new Dimension(150,30));


        JMenu mnThongKe = new JMenu("Thống Kê");
        JMenuItem mnThongke1,mnThongke2;
//        mnThongKe.add(mnThongke1 = new JMenuItem("Sản Phẩm Bán Trong Ngày"));
//        mnThongKe.add(mnThongke2 = new JMenuItem("Sản Phẩm Còn Trong Cửa Hàng"));
//        mnThongke1.setPreferredSize(new Dimension(200,30));
//        mnThongke2.setPreferredSize(new Dimension(200,30));
        mnThongKe.setIcon(new ImageIcon(getClass().getResource("/images/stats1.png")));
        mnThongKe.setFont(ftmn);


        JMenu mnTimKiem = new JMenu("Tìm Kiếm");
        mnTimKiem.setIcon(new ImageIcon(getClass().getResource("/images/loupe1.png")));
        mnTimKiem.setFont(ftmn);
        JMenuItem mnTimSanPham,mnTimKH,mnTimNV,mnTimNCC;
        mnTimKiem.add(mnTimSanPham = new JMenuItem("Sản Phẩm"));
        mnTimKiem.add(mnTimKH = new JMenuItem("Khách Hàng"));
        mnTimKiem.add(mnTimNV = new JMenuItem("Nhân Viên"));
        mnTimKiem.add(mnTimNCC = new JMenuItem("Nhà Cung Cấp"));

        mnTimSanPham.setPreferredSize(new Dimension(150,30));
        mnTimKH.setPreferredSize(new Dimension(150,30));
        mnTimNCC.setPreferredSize(new Dimension(150,30));
        mnTimNV.setPreferredSize(new Dimension(150,30));


        JMenu mnTaiKhoan = new JMenu("Tài Khoản");
        mnTaiKhoan.setIcon(new ImageIcon(getClass().getResource("/images/information1.png")));
        mnTaiKhoan.setFont(ftmn);


        menuBar.add(mnTrangChu);
        menuBar.add(mnHeThong);
        menuBar.add(mnQuanLy);
        menuBar.add(mnXuLy);
        menuBar.add(mnThongKe);
        menuBar.add(mnTimKiem);
//        menuBar.add(mnTaiKhoan);

        //PnCneter
        pnCneter = new JPanel();
        JPanel pnCenterN = new JPanel();
        JPanel pnCenterC = new JPanel();
        JPanel pnCenterS = new JPanel();
        pnCneter.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ MUA BÁO ĐỒ BẢO HỘ");
        lblTieuDe.setFont(new Font("arial",Font.BOLD,24));
        lblTieuDe.setForeground(Color.RED);
        pnCenterN.setPreferredSize(new Dimension(1000,60));

        JLabel lbImage = new JLabel();
        lbImage.setIcon(new ImageIcon(getClass().getResource("/images/banner_baoho.png")));

        pnCenterN.add(lblTieuDe);
        pnCenterC.add(lbImage);
        pnCneter.add(pnCenterN,BorderLayout.NORTH);
        pnCneter.add(pnCenterC,BorderLayout.CENTER);

        //pnSouth
        JPanel pnSouth = new JPanel();

        Box b,b1,b2,b3,b4;
        b = Box.createVerticalBox();
        b.add(b1 = Box.createHorizontalBox());
        b1.add(Box.createHorizontalStrut(200));
        b1.add(lblGiaoVien = new JLabel("Giáo Viên Hướng Dẫn: "));
        b1.add(Box.createHorizontalStrut(30));
        b1.add(lblTenGV = new JLabel("Nguyễn Văn ABC"));
        b.add(Box.createVerticalStrut(30));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(Box.createHorizontalStrut(200));
        b2.add(lblThanhVien = new JLabel("Thành Viên Nhóm: "));
        b2.add(Box.createHorizontalStrut(30));
        b2.add(lblSV1 = new JLabel("Nguyễn Văn A"));
        b.add(Box.createVerticalStrut(20));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(Box.createHorizontalStrut(200));
        b3.add(lbl1 = new JLabel("                               "));
        b3.add(Box.createHorizontalStrut(30));
        b3.add(lblSV2 = new JLabel("Nguyễn Văn B"));
        b.add(Box.createVerticalStrut(20));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(Box.createHorizontalStrut(200));
        b4.add(lbl2 = new JLabel("                               "));
        b4.add(Box.createHorizontalStrut(30));
        b4.add(lblSV3 = new JLabel("Nguyễn Văn C"));
        b.add(Box.createVerticalStrut(90));

        lblThanhVien.setPreferredSize(lblGiaoVien.getPreferredSize());

        Font ft = new Font("arial",Font.BOLD,17);
        lblGiaoVien.setFont(ft);
        lblTenGV.setFont(ft);
        lblThanhVien.setFont(ft);
        lbl1.setFont(ft);
        lbl2.setFont(ft);
        lblSV1.setFont(ft);
        lblSV2.setFont(ft);
        lblSV3.setFont(ft);

        pnCenterS.add(b);
        pnCneter.add(pnCenterS,BorderLayout.SOUTH);
        Font ft1 = new Font("arial",Font.ITALIC,16);
        JLabel lbs;
        pnSouth.add(lbs = new JLabel("Nhóm xx:                             Chương trình quản lý mua bán đồ bảo hộ"));
        lbs.setFont(ft1);
        pnSouth.setBackground(Color.decode("#00bcd4"));
        pnSouth.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        pnSouth.setPreferredSize(new Dimension(0,50));
        cp.add(menuBar,BorderLayout.NORTH);
        cp.add(pnCneter,BorderLayout.CENTER);
        cp.add(pnSouth,BorderLayout.SOUTH);

        mnKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KhachHang_Form kh = new KhachHang_Form();
                pnCneter.removeAll();
                pnCneter.add(kh);
                validate();
            }
        });
//        mnNhanVien.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                NhanVien_Form nv = new NhanVien_Form();
//                pnCneter.removeAll();
//                pnCneter.add(nv);
//                validate();
//            }
//        });
//        mnNhaCC.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                NhaCC_Form ncc = new NhaCC_Form();
//                pnCneter.removeAll();
//                pnCneter.add(ncc);
//                validate();
//            }
//        });
//
//        mnLoaiSanPham.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                LoaiSanPham_Form loaiSanPham_form = new LoaiSanPham_Form();
//                pnCneter.removeAll();
//                pnCneter.add(loaiSanPham_form);
//                validate();
//            }
//        });
//        mnChatLieu.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ChatLieu_Form chatLieu_form = new ChatLieu_Form();
//                pnCneter.removeAll();
//                pnCneter.add(chatLieu_form);
//                validate();
//            }
//        });
//        mnMauSac.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                MauSac_Form mauSac_form = new MauSac_Form();
//                pnCneter.removeAll();
//                pnCneter.add(mauSac_form);
//                validate();
//            }
//        });
//        mnSanPham.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                SanPham_Form thuoc = new SanPham_Form();
//                thuoc.doShow();
//                pnCneter.removeAll();
//                pnCneter.add(thuoc);
//                validate();
//            }
//        });
        mnBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BanHang_Form banHang = new BanHang_Form();
                pnCneter.removeAll();
                banHang.nv = nv;
                banHang.bh = banHang;
                pnCneter.add(banHang);
                banHang.doShow();
                validate();
            }
        });
        mnNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhapHang_Form nhapHang = new NhapHang_Form();
                pnCneter.removeAll();
                nhapHang.nv = nv;
                nhapHang.nh = nhapHang;
                pnCneter.add(nhapHang);
                nhapHang.doShow();
                validate();
            }
        });
        mnTimKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemKH_Form timKH = new TimKiemKH_Form();
                pnCneter.removeAll();
                pnCneter.add(timKH);
                timKH.doShow();
                validate();
            }
        });
        mnTimNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemNV_Form timNV = new TimKiemNV_Form();
                pnCneter.removeAll();
                pnCneter.add(timNV);
                validate();
            }
        });
        mnTimNCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemNCC_Form timNCC = new TimKiemNCC_Form();
                pnCneter.removeAll();
                pnCneter.add(timNCC);
                validate();
            }
        });
        mnTimSanPham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemVatLieu_Form timThuoc = new TimKiemVatLieu_Form();
                pnCneter.removeAll();
                pnCneter.add(timThuoc);
                validate();
            }
        });
        mnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lc = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn thoát chương trình không?","Xác nhận",JOptionPane.YES_NO_OPTION);
                if(lc == JOptionPane.YES_OPTION)
                    setVisible(false);
            }
        });
        mnTrangChu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TrangChu_Form trangChu_form = new TrangChu_Form();
                pnCneter.removeAll();
                pnCneter.add(trangChu_form);
                validate();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        mnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        mnDangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                DangNhap_Form dangNhap_form = new DangNhap_Form();
                dangNhap_form.setVisible(true);
            }
        });

        mnDoiMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

//        mnChucVu.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ChucVu_Form chucVu_form = new ChucVu_Form();
//                pnCneter.removeAll();
//                pnCneter.add(chucVu_form);
//                validate();
//            }
//        });

        mnTaiKhoan.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TaiKhoan_Form taiKhoan_form = new TaiKhoan_Form();
                pnCneter.removeAll();
                pnCneter.add(taiKhoan_form);
                validate();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

//        mnThongke1.addActionListener(new ActionListener() {
//        	private JasperPrint jprint;
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				try {
//					Class.forName("net.sourceforge.jtds.jdbc.Driver");
//					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyQuayThuoc;instance=SQLEXPRESS;user=sa;password=hung");
////					Connection con = MyConnection.getInstance().getConnection();
//					String sql = "SELECT *\r\n" +
//							"FROM [dbo].[Thuoc] t WHERE t.MAT IN (SELECT ct.MAT FROM \r\n" +
//							"[dbo].[CT_HoaDonBanHang] ct JOIN [dbo].[HoaDonBanHang] \r\n" +
//							"hd ON ct.MaHDBH = hd.MaHDBH WHERE DAY(hd.NGAYLAPHD) = DAY(GETDATE()))";
//					JasperDesign jdesign = JRXmlLoader.load("D:\\PTUD_ChuongTrinhQuanLyQuayThuoc\\PTUD_QuanLyQuayThuoc\\src\\Report\\ThongKeHangBan.jrxml");
//					JRDesignQuery updateQuery = new JRDesignQuery();
//					updateQuery.setText(sql);
//					jdesign.setQuery(updateQuery);
//
//					Map<String, Object> parameters = new HashMap<String, Object>();
//					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
//					JasperPrint jpasperPrint = JasperFillManager.fillReport(jreport, parameters,con);
//
//					JasperViewer.viewReport(jpasperPrint,false);
//				}catch (Exception e2) {
//					// TODO: handle exception
//					e2.printStackTrace();
//					JOptionPane.showMessageDialog(null, e2);
//				}
//
//			}
//		});
//        mnThongke2.addActionListener(new ActionListener() {
//        	private JasperPrint jprint;
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				try {
//					Class.forName("net.sourceforge.jtds.jdbc.Driver");
//					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyQuayThuoc;instance=SQLEXPRESS;user=sa;password=hung");
////					Connection con = MyConnection.getInstance().getConnection();
//					String sql = "SELECT t.MAT,t.TENTHUOC,lt.TENLOAI,t.CONGDUNG,t.DONVITINH,t.SOLUONG,t.GIABAN,t.GIABAN*t.SOLUONG AS THANHTIEN\r\n" +
//							"FROM [dbo].[Thuoc] t JOIN [dbo].[LoaiThuoc] lt ON t.MALOAI = lt.MALOAI";
//					JasperDesign jdesign = JRXmlLoader.load("D:\\PTUD_ChuongTrinhQuanLyQuayThuoc\\PTUD_QuanLyQuayThuoc\\src\\Report\\ThongKeThuocCoTrongKho.jrxml");
//					JRDesignQuery updateQuery = new JRDesignQuery();
//					updateQuery.setText(sql);
//					jdesign.setQuery(updateQuery);
//
//					Map<String, Object> parameters = new HashMap<String, Object>();
//					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
//					JasperPrint jpasperPrint = JasperFillManager.fillReport(jreport, parameters,con);
//
//					JasperViewer.viewReport(jpasperPrint,false);
//				}catch (Exception e2) {
//					// TODO: handle exception
//					e2.printStackTrace();
//					JOptionPane.showMessageDialog(null, e2);
//				}
//
//			}
//		});
    }

    public static void main(String[] args) {
        new GD_Chinh().setVisible(true);
    }
}
