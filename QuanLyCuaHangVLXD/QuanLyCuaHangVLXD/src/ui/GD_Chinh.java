package ui;

import entity.NhanVien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GD_Chinh extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pnCneter;
    NhanVien nv;
    public GD_Chinh(){

        doShow();
    }
    public void doShow() {
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Quản Lý Cửa Hàng Vật Liệu Xây Dựng");

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        //Menu chương trình
        Font ftmn = new Font("arial", Font.BOLD, 16);
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
        mnThoat.setPreferredSize(new Dimension(150, 30));
        mnDangXuat.setPreferredSize(new Dimension(150, 30));
        mnDoiMatKhau.setPreferredSize(new Dimension(150, 30));

        JMenu mnQuanLy = new JMenu("Quản Lý");
        mnQuanLy.setIcon(new ImageIcon(getClass().getResource("/images/quanly1.png")));
        mnQuanLy.setFont(ftmn);
        JMenuItem mnVatLieu, mnKhachHang, mnNhanVien, mnNhaCC, mnLoaiVatLieu, mnChucVu;
        mnQuanLy.add(mnVatLieu = new JMenuItem("Vật Liệu"));
        mnQuanLy.add(mnKhachHang = new JMenuItem("Khách Hàng"));
        mnQuanLy.add(mnNhanVien = new JMenuItem("Nhân Viên"));
        mnQuanLy.add(mnNhaCC = new JMenuItem("Nhà Cung Cấp"));
        mnQuanLy.add(mnLoaiVatLieu = new JMenuItem("Loại Vật Liệu"));
        mnQuanLy.add(mnChucVu = new JMenuItem("Chức Vụ"));

        mnVatLieu.setPreferredSize(new Dimension(150, 30));
        mnKhachHang.setPreferredSize(new Dimension(150, 30));
        mnNhaCC.setPreferredSize(new Dimension(150, 30));
        mnNhanVien.setPreferredSize(new Dimension(150, 30));
        mnLoaiVatLieu.setPreferredSize(new Dimension(150, 30));
        mnChucVu.setPreferredSize(new Dimension(150, 30));

        JMenu mnXuLy = new JMenu("Xử Lý");
        mnXuLy.setIcon(new ImageIcon(getClass().getResource("/images/no-touch1.png")));
        mnXuLy.setFont(ftmn);
        JMenuItem mnBan, mnNhap;
        mnXuLy.add(mnBan = new JMenuItem("Bán Vật Liệu Xây Dựng"));
        mnXuLy.add(mnNhap = new JMenuItem("Nhập Vật Liệu Xây Dựng"));
        mnNhap.setPreferredSize(new Dimension(150, 30));
        mnBan.setPreferredSize(new Dimension(150, 30));


        JMenu mnTimKiem = new JMenu("Tìm Kiếm");
        mnTimKiem.setIcon(new ImageIcon(getClass().getResource("/images/loupe1.png")));
        mnTimKiem.setFont(ftmn);
        JMenuItem mnTimVL, mnTimKH, mnTimNV, mnTimNCC;
        mnTimKiem.add(mnTimVL = new JMenuItem("Vật Liệu"));
        mnTimKiem.add(mnTimKH = new JMenuItem("Khách Hàng"));
        mnTimKiem.add(mnTimNV = new JMenuItem("Nhân Viên"));
        mnTimKiem.add(mnTimNCC = new JMenuItem("Nhà Cung Cấp"));

        mnTimVL.setPreferredSize(new Dimension(150, 30));
        mnTimKH.setPreferredSize(new Dimension(150, 30));
        mnTimNCC.setPreferredSize(new Dimension(150, 30));
        mnTimNV.setPreferredSize(new Dimension(150, 30));


        JMenu mnTaiKhoan = new JMenu("Tài Khoản");
        mnTaiKhoan.setIcon(new ImageIcon(getClass().getResource("/images/information1.png")));
        mnTaiKhoan.setFont(ftmn);


        menuBar.add(mnTrangChu);
        menuBar.add(mnHeThong);
        menuBar.add(mnQuanLy);
        menuBar.add(mnXuLy);
        //menuBar.add(mnThongKe);
        menuBar.add(mnTimKiem);
        menuBar.add(mnTaiKhoan);

        //PnCneter
        pnCneter = new JPanel();
        JPanel pnCenterN = new JPanel();
        JPanel pnCenterC = new JPanel();
        JPanel pnCenterS = new JPanel();
        pnCneter.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ CỬA HÀNG VẬT LIỆU XÂY DỰNG");
        lblTieuDe.setFont(new Font("arial", Font.BOLD, 24));
        lblTieuDe.setForeground(Color.RED);
        pnCenterN.setPreferredSize(new Dimension(1000, 60));

        JLabel lbImage = new JLabel();
        lbImage.setIcon(new ImageIcon(getClass().getResource("/images/banner_vlxd.jpg")));

        pnCenterN.add(lblTieuDe);
        pnCenterC.add(lbImage);
        pnCneter.add(pnCenterN, BorderLayout.NORTH);
        pnCneter.add(pnCenterC, BorderLayout.CENTER);

        //pnSouth
        JPanel pnSouth = new JPanel();

        Font ft = new Font("arial", Font.BOLD, 17);
        pnCneter.add(pnCenterS, BorderLayout.SOUTH);
        Font ft1 = new Font("arial", Font.ITALIC, 16);
        JLabel lbs;
        pnSouth.add(lbs = new JLabel("Nhóm xx:                             Chương trình quản lý cửa hàng vật liệu xây dựng"));
        lbs.setFont(ft1);
        pnSouth.setBackground(Color.decode("#00bcd4"));
        pnSouth.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        pnSouth.setPreferredSize(new Dimension(0, 50));
        cp.add(menuBar, BorderLayout.NORTH);
        cp.add(pnCneter, BorderLayout.CENTER);
        cp.add(pnSouth, BorderLayout.SOUTH);

        mnKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KhachHang_Form kh = new KhachHang_Form();
                pnCneter.removeAll();
                pnCneter.add(kh);
                validate();
            }
        });
        mnNhanVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhanVien_Form nv = new NhanVien_Form();
                pnCneter.removeAll();
                pnCneter.add(nv);
                validate();
            }
        });
        mnNhaCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhaCC_Form ncc = new NhaCC_Form();
                pnCneter.removeAll();
                pnCneter.add(ncc);
                validate();
            }
        });

        mnLoaiVatLieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoaiVatLieu_Form loaiVatLieu_form = new LoaiVatLieu_Form();
                pnCneter.removeAll();
                pnCneter.add(loaiVatLieu_form);
                validate();
            }
        });
        mnVatLieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VatLieu_Form thuoc = new VatLieu_Form();
                thuoc.doShow();
                pnCneter.removeAll();
                pnCneter.add(thuoc);
                validate();
            }
        });
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
        mnTimVL.addActionListener(new ActionListener() {
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
                int lc = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thoát chương trình không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (lc == JOptionPane.YES_OPTION)
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

        mnChucVu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChucVu_Form chucVu_form = new ChucVu_Form();
                pnCneter.removeAll();
                pnCneter.add(chucVu_form);
                validate();
            }
        });

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
    }
}
