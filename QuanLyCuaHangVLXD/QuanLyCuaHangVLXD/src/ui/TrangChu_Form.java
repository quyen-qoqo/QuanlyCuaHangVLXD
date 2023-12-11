package ui;

import javax.swing.*;
import java.awt.*;

public class TrangChu_Form extends JPanel {
    protected JLabel lblGiaoVien, lblTenGV, lblThanhVien, lblSV1, lbl1, lblSV2, lbl2, lblSV3;
    public TrangChu_Form() {
        doShow();
    }

    public void doShow() {
        JPanel pnCenterN = new JPanel();
        JPanel pnCenterC = new JPanel();
        JPanel pnCenterS = new JPanel();

        JLabel lblTieuDe = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ MUA BÁO ĐỒ BẢO HỘ");
        lblTieuDe.setFont(new Font("arial",Font.BOLD,24));
        lblTieuDe.setForeground(Color.RED);
        pnCenterN.setPreferredSize(new Dimension(1000,60));

        JLabel lbImage = new JLabel();
        lbImage.setIcon(new ImageIcon(getClass().getResource("/images/banner_baoho.png")));

        pnCenterN.add(lblTieuDe);
        pnCenterC.add(lbImage);

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

        this.setLayout(new BorderLayout());
        this.add(pnCenterN, BorderLayout.NORTH);
        this.add(pnCenterC, BorderLayout.CENTER);
        this.add(pnCenterS, BorderLayout.SOUTH);
    }
}
