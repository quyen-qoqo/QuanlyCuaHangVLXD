package ui;

import com.toedter.calendar.JDateChooser;
import dao.CT_HDBH_DAO;
import dao.HoaDonBanHang_DAO;
import dao.VatLieu_DAO;
import entity.*;
import table_model.CT_HDBH_TableModel;
import table_model.VatLieu_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BanHang_Form extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5430744024866983777L;
	JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblTienThuoc,lblTienNhan,lblTienThue,lblTienThoi,lblTongTien,lblTim,
            lblKhachHang,lblMaHD,lblST,lblNgayLap,lblNhanVien,lblDiaChi,lblGioiTinh;
    JTextField txttienThuoc,txtTienNhan,txtThue,txtTienThoi,txtTong,txtTim,
            txtKhachHang,txtSDT,txtMaHD,txtNhanVien,txtDiaChi;
    JButton btnHoaDonMoi,btnIn,btnThoat,btnTim,btnKhachHang;
    JComboBox cbcTim,cbcGT;
    JDateChooser NgayLap;
    String mahd = "";
    NhanVien nv;
    KhachHang kh;
    BanHang_Form bh;
    private String txtTongC;

    public BanHang_Form(){

    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("LẬP HÓA ĐƠN BÁN HÀNG");
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
        b2.add(btnKhachHang = new JButton("Khách Hàng"));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblKhachHang = new JLabel("Khách Hàng: "));
        b3.add(txtKhachHang = new JTextField());
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblGioiTinh = new JLabel("Giới Tính: "));
        b3.add(cbcGT = new JComboBox<>());
        cbcGT.addItem("Nam");
        cbcGT.addItem("Nữ");
        cbcGT.setPreferredSize(new Dimension(100,20));
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
        lblKhachHang.setPreferredSize(lblNhanVien.getPreferredSize());
        lblST.setPreferredSize(lblNhanVien.getPreferredSize());

        JPanel pnSanPham = new JPanel();
        pnSanPham.setBorder(new TitledBorder("Danh Sách Vật Liệu"));
        pnSanPham.setLayout(new BorderLayout());
        JPanel pnSanPham_N = new JPanel();
        JPanel pnSanPham_C = new JPanel();
        Box t = Box.createHorizontalBox();
        t.setPreferredSize(new Dimension(400,25));
        t.add(lblTim = new JLabel("Tìm VL Theo: "));
        t.add(cbcTim = new JComboBox<>());
        cbcTim.setPreferredSize(new Dimension(100,20));
        t.add(Box.createHorizontalStrut(10));
        t.add(txtTim = new JTextField());
        t.add(btnTim = new JButton("Tìm Vật Liệu"));

        VatLieu_DAO vatLieu_dao = new VatLieu_DAO();
        VatLieu_TableModel model = new VatLieu_TableModel(vatLieu_dao.getLS());
        JTable table = new JTable();
        table.setModel(model);

        JScrollPane sc = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(600,170));
        pnSanPham_N.add(t);
        pnSanPham_C.add(sc);

        pnSanPham.add(pnSanPham_N,BorderLayout.NORTH);
        pnSanPham.add(pnSanPham_C,BorderLayout.CENTER);
        pnThongTin.add(b);

        pnNorth.add(pnSanPham,BorderLayout.EAST);
        pnNorth.add(pnThongTin,BorderLayout.CENTER);
        txtMaHD.setEditable(false);
        if(nv != null){
            txtNhanVien.setEditable(false);
            txtNhanVien.setText(nv.getTenNV());
        }

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setBorder(new TitledBorder("Danh Sách Vật Liệu Đã Chọn"));
        List<CT_HoaDonBanHang> ls1 = new ArrayList<>();
        CT_HDBH_TableModel model1 = new CT_HDBH_TableModel(ls1);
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
        s1.add(lblTienThuoc = new JLabel("Tổng Tiền VL: "));
        s1.add(txttienThuoc = new JTextField());
        txttienThuoc.setEditable(false);
        s1.add(Box.createHorizontalStrut(50));
        s1.add(lblTienNhan = new JLabel("Tiền Nhận: "));
        s1.add(txtTienNhan = new JTextField());
        s.add(Box.createVerticalStrut(7));

        s.add(s2 = Box.createHorizontalBox());
        s2.add(lblTienThue = new JLabel("Thuế GTGT: "));
        s2.add(txtThue = new JTextField());
        txtThue.setEditable(false);
        s2.add(Box.createHorizontalStrut(55));
        s2.add(lblTienThoi = new JLabel("Tiền Trả Lại: "));
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

        lblTienNhan.setPreferredSize(lblTienThuoc.getPreferredSize());
        lblTienThoi.setPreferredSize(lblTienThuoc.getPreferredSize());
        lblTienThue.setPreferredSize(lblTienThuoc.getPreferredSize());
        lblTongTien.setPreferredSize(lblTienThuoc.getPreferredSize());


        List<CT_HoaDonBanHang> list = new ArrayList<>();
        table.addMouseListener(new MouseListener() {
            double tongTien = 0;
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();

                if(r != -1){
                    if(kh != null) {
                        int sl = Integer.parseInt(JOptionPane.showInputDialog(null, "Nhập số lượng vật liệu muốn mua: ",
                                "Nhập số lượng", JOptionPane.INFORMATION_MESSAGE));
                        HoaDonBanHang_DAO hdDao = new HoaDonBanHang_DAO();
                        HoaDonBanHang hd = null;
                        try {
                            hd = hdDao.TimKiemMa(hdDao.getMa());
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        if (sl > 0) {
                        	if(sl <= vatLieu_dao.TimKiemMa(table.getValueAt(r, 0).toString()).getSoLuong()) {
                                try {
                                    mahd = hdDao.getMa();
                                    VatLieu t = vatLieu_dao.TimKiemMa(table.getValueAt(r, 0).toString());
                                    CT_HoaDonBanHang ct = new CT_HoaDonBanHang(sl, t.getGiaBan());
                                    assert hd != null;
                                    hd.setMaHDBH(mahd);
                                    ct.setHoaDonBanHang(hd);
                                    ct.setVatLieu(t);
                                    CT_HDBH_DAO ctDao = new CT_HDBH_DAO();
                                    if(ctDao.TimKiemMaHD(mahd,t.getMaVatLieu()) != null){
                                        if (vatLieu_dao.updateSoLuong(table.getValueAt(r, 0).toString(), t.getSoLuong() - sl)) {
                                            table.setModel(new VatLieu_TableModel(vatLieu_dao.getLS()));
                                            ctDao.updateSoLuong(mahd,t.getMaVatLieu(),ctDao.TimKiemMaVL(t.getMaVatLieu()).getSoLuong()+sl);
                                            table1.setModel(new CT_HDBH_TableModel(ctDao.TimKiemHD(mahd)));
                                            tongTien += (double) ctDao.TimKiemMaVL(t.getMaVatLieu()).getSoLuong() * ct.getDonGia();
                                            txttienThuoc.setText(String.valueOf(tongTien));
                                            txtThue.setText("5%");
                                            txtTong.setText(String.valueOf(tongTien + tongTien * 0.05));
                                            hdDao.updateTongtien(mahd, tongTien + tongTien * 0.05);
                                        }
                                    }else {
                                        if (ctDao.addCTHDBH(ct)) {
                                            if (vatLieu_dao.updateSoLuong(table.getValueAt(r, 0).toString(), t.getSoLuong() - sl)) {
                                                table.setModel(new VatLieu_TableModel(vatLieu_dao.getLS()));
                                                list.add(ct);
                                                table1.setModel(new CT_HDBH_TableModel(list));
                                                tongTien += (double) ct.getSoLuong() * ct.getDonGia();
                                                txttienThuoc.setText(String.valueOf(tongTien));
                                                txtThue.setText("5%");
                                                txtTong.setText(String.valueOf(tongTien + tongTien * 0.05));
                                                hdDao.updateTongtien(mahd, tongTien + tongTien * 0.05);
                                            }
                                        }
                                    }
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }

                        	 }else {
                             	JOptionPane.showMessageDialog(null, "Không đủ số lượng!");
                             }
                        } else {
                            JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Bạn chưa chọn khách hàng!");
                    }
                }
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


        btnKhachHang.addActionListener(e -> {
            DS_KhachHang_Form ds = new DS_KhachHang_Form();
            ds.setVisible(true);
            ds.banhang = bh;
        });
        txtTienNhan.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

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
                if (!txtTienNhan.getText().trim().isEmpty() ) {
                    if (Double.parseDouble(txtTienNhan.getText().trim()) < Double.parseDouble(txtTong.getText().trim())) {
                        JOptionPane.showMessageDialog(null, "Số tiền nhận phải lớn hơn số tiền háo đơn");
                    } else {
                        txtTongC = txtTong.getText().trim();
                        txtTienThoi.setText(String.valueOf(Double.parseDouble(txtTienNhan.getText().trim()) - Double.parseDouble(txtTong.getText().trim())));
                    }
                }
            }
        });
        btnHoaDonMoi.addActionListener(e -> {
            txttienThuoc.setText("");
            txtTong.setText("");
            txtKhachHang.setText("");
            txtDiaChi.setText("");
            txtSDT.setText("");
            List<CT_HoaDonBanHang> ls = new ArrayList<>();
            table1.setModel(new CT_HDBH_TableModel(ls));
        });

        btnIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HoaDonBanHang_Form hd = new HoaDonBanHang_Form();
                hd.maHD = mahd;
                hd.tienChu = numberToString(new BigDecimal(txtTongC));
                hd.tong = Double.parseDouble(txtTong.getText());
                hd.tienKhach = Double.parseDouble(txtTienNhan.getText());
                hd.doShow();
//                        setVisible(false);
                hd.setVisible(true);
            }
        });
        this.setLayout(new BorderLayout());
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }

    public static String numberToString(BigDecimal number) {
        String sNumber = number.toString();
        // Tao mot bien tra ve
        String sReturn = "";
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Lat nguoc chuoi nay lai
        String sNumber1 = "";
        for (int i = iLen - 1; i >= 0; i--) {
            sNumber1 += sNumber.charAt(i);
        }
        // Tao mot vong lap de doc so
        // Tao mot bien nho vi tri cua sNumber
        int iRe = 0;
        do {
            // Tao mot bien cat tam
            String sCut = "";
            if (iLen > 3) {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + 3);
                sReturn = Read(sCut, iRe) + sReturn;
                iRe++;
                iLen -= 3;
            } else {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + iLen);
                sReturn = Read(sCut, iRe) + sReturn;
                break;
            }
        } while (true);
        if(sReturn.length() > 1){
            sReturn = sReturn.substring(0,1).toUpperCase() + sReturn.substring(1);
        }
        sReturn = sReturn + "đồng";

        // xu ly lan cuoi voi 220 000 tỷ 000 000 000 000 000 HUTTV ADDED 3 OCT
        if(sNumber.length()>12)
        {
            // tren gia tri can xu ly, kiem tra xem don vi TY co = 000 khong
            int begin = sNumber.length()-9;
            String donviTy = sNumber.substring(begin-3, (begin-3)+3);
            if(donviTy.equals("000"))
            {
                sReturn = sReturn.replaceFirst("ngàn", "ngàn tỷ");
            }
        }


        return sReturn;
    }

    // Khoi tao ham Read
    public static String Read(String sNumber, int iPo) {
        // Tao mot bien tra ve
        String sReturn = "";
        // Tao mot bien so
        String sPo[] = { "", "ngàn" + " ",
                "triệu" + " ", "tỷ" + " ",  "ngàn" + " "};
        String sSo[] = { "không" + " ", "một" + " ",
                "hai" + " ", "ba" + " ",
                "bốn" + " ", "năm" + " ",
                "sáu" + " ", "bảy" + " ",
                "tám" + " ", "chín" + " " };
        String sDonvi[] = { "", "mươi" + " ",
                "trăm" + " " };
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Tao mot bien nho vi tri doc
        int iRe = 0;
        // Tao mot vong lap de doc so
        for (int i = 0; i < iLen; i++) {
            String sTemp = "" + sNumber.charAt(i);
            int iTemp = Integer.parseInt(sTemp);
            // Tao mot bien ket qua
            String sRead = "";
            // Kiem tra xem so nhan vao co = 0 hay ko
            if (iTemp == 0) {
                switch (iRe) {
                    case 0:
                        break;// Khong lam gi ca
                    case 1: {
                        if (Integer.parseInt("" + sNumber.charAt(0)) != 0) {
                            sRead = "lẻ" + " ";
                        }
                        break;
                    }
                    case 2: {
                        if (Integer.parseInt("" + sNumber.charAt(0)) != 0
                                && Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                            sRead = "không trăm" + " ";
                        }
                        break;
                    }
                }
            } else if (iTemp == 1) {
                switch (iRe) {
                    case 1:
                        sRead = "mười" + " ";
                        break;
                    default:
                        sRead = "một" + " " + sDonvi[iRe];
                        break;
                }
            } else if (iTemp == 5) {
                switch (iRe) {
                    case 0: {
                        if(sNumber.length() <= 1){
                            sRead = "năm" + " ";
                        }
                        else if (Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                            sRead = "lăm" + " ";
                        } else
                            sRead = "năm" + " ";
                        break;
                    }
                    default:
                        sRead = sSo[iTemp] + sDonvi[iRe];
                }
            } else {
                sRead = sSo[iTemp] + sDonvi[iRe];
            }

            sReturn = sRead + sReturn;
            iRe++;
        }
        if (sReturn.length() > 0) {
            sReturn += sPo[iPo];
        }
        // xu ly lan cuoi voi 220 000 tỷ 000 000 000 000 000
        if(sNumber.length()>12)
        {
            // tren gia tri can xu ly, kiem tra xem don vi TY co = 000 khong
            System.out.println(sNumber.substring(11, 8));
        }
        return sReturn;
    }

    public static String getRomanNumerals(int Int) {
        LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
        roman_numerals.put("M", 1000);
        roman_numerals.put("CM", 900);
        roman_numerals.put("D", 500);
        roman_numerals.put("CD", 400);
        roman_numerals.put("C", 100);
        roman_numerals.put("XC", 90);
        roman_numerals.put("L", 50);
        roman_numerals.put("XL", 40);
        roman_numerals.put("X", 10);
        roman_numerals.put("IX", 9);
        roman_numerals.put("V", 5);
        roman_numerals.put("IV", 4);
        roman_numerals.put("I", 1);
        String res = "";
        for(Map.Entry<String, Integer> entry : roman_numerals.entrySet()){
            int matches = Int/entry.getValue();
            res += repeat(entry.getKey(), matches);
            Int = Int % entry.getValue();
        }
        return res;
    }
    public static String repeat(String s, int n) {
        if(s == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

}
