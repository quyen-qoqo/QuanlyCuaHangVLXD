package ui;

import dao.ChucVu_DAO;
import dao.TaiKhoan_DAO;
import entity.ChucVu;
import entity.TaiKhoan;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
public class DangNhap_Form extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1197763560953830596L;
	JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblTenDangNhap,lblMatKhau;
    JTextField txtTenDN;
    JPasswordField tfMatKhau;
    JCheckBox cbxHienThi;
    JButton btnDangNhap,btnThoat;
    private JComboBox<String> cbcChucVu;

    public DangNhap_Form(){
        doShow();
    }
    public void doShow(){
        setSize(600,270);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Đăng Nhập Hệ Thống");

        //pnNorth
        pnNorth = new JPanel();
        JLabel lblTieuDe = new JLabel("ĐĂNG NHẬP");
        lblTieuDe.setForeground(Color.RED);
        lblTieuDe.setFont(new Font("arial",Font.BOLD,20));
        pnNorth.add(lblTieuDe);

        //pnCenter
        pnCenter = new JPanel();
        Box b,b1,b2,b3,b4;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(350,120));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblTenDangNhap = new JLabel("Tên Đăng Nhập: "));
        b1.add(Box.createHorizontalStrut(30));
        b1.add(txtTenDN = new JTextField());
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblMatKhau = new JLabel("Mật Khẩu: "));
        b2.add(Box.createHorizontalStrut(30));
        b2.add(tfMatKhau = new JPasswordField());
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        JLabel lblQuyen;
        b3.add(lblQuyen = new JLabel("Chức Vụ: "));
        b3.add(Box.createHorizontalStrut(30));
        cbcChucVu = new JComboBox<>();
        ChucVu_DAO chucVu_dao = new ChucVu_DAO();
        for (ChucVu chucVu: chucVu_dao.getLS()) {
            cbcChucVu.addItem(chucVu.getTenChucVu());
        }
        cbcChucVu.setPreferredSize(new Dimension(220,20));
        b3.add(cbcChucVu);
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(Box.createHorizontalStrut(26));
        b4.add(cbxHienThi = new JCheckBox("Hiển Thị Mật Khẩu"));
        b.add(Box.createVerticalStrut(10));


        lblMatKhau.setPreferredSize(lblTenDangNhap.getPreferredSize());
        lblQuyen.setPreferredSize(lblTenDangNhap.getPreferredSize());

        pnCenter.add(b);

        //pnSouth
        pnSouth = new JPanel();
        Box bc = Box.createHorizontalBox();
        bc.add(btnDangNhap = new JButton("Đăng Nhập"));
        bc.add(Box.createHorizontalStrut(90));
        bc.add(btnThoat = new JButton("Thoát"));

        pnSouth.add(bc);
        pnSouth.setPreferredSize(new Dimension(0,70));

        cbxHienThi.addActionListener(e -> {
            System.out.println(String.valueOf(tfMatKhau.getPassword()));
            tfMatKhau.setText(String.valueOf(tfMatKhau.getPassword()));
        });
        TaiKhoan_DAO taiKhoan_dao = new TaiKhoan_DAO();
        btnDangNhap.addActionListener(e -> {
            TaiKhoan taiKhoan = taiKhoan_dao.TimKiemMa(txtTenDN.getText().trim(), String.valueOf(tfMatKhau.getPassword()).trim());
            if(taiKhoan != null)
            {
                String chucVu = taiKhoan.getNhanVien().getChucVu().getTenChucVu();
                if (chucVu.equals(Objects.requireNonNull(cbcChucVu.getSelectedItem()).toString())) {
                    JOptionPane.showMessageDialog(null,"Đăng Nhập Hệ Thống Thành Công Với Quyền " +cbcChucVu.getSelectedItem().toString());
                    if (chucVu.equals("Admin")) {
                        GD_Chinh gd = new GD_Chinh();
                        gd.nv = taiKhoan.getNhanVien();
                        gd.setVisible(true);
                        setVisible(false);
                    } else {
                        GD_NhanVien gd = new GD_NhanVien();
                        gd.nv = taiKhoan.getNhanVien();
                        gd.setVisible(true);
                        setVisible(false);
                    }

                } else {
                    JOptionPane.showMessageDialog(null,"Bạn Không Thể Đăng Nhập Với Quyền " +cbcChucVu.getSelectedItem().toString());
                }

            }else{
                JOptionPane.showMessageDialog(null,"Sai Tên Đăng Nhập Hoặc Mật Khẩu!");
            }
        });

        this.setLayout(new BorderLayout());
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new DangNhap_Form().setVisible(true);
    }
}
