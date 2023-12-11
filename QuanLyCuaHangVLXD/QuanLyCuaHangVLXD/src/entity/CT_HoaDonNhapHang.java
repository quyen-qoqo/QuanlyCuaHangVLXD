package entity;

public class CT_HoaDonNhapHang {
    private HoaDonNhapHang hoaDonNhapHang;
    private VatLieu vatLieu;
    private int soLuong;
    private double donGia;

    public CT_HoaDonNhapHang() {
        // TODO Auto-generated constructor stub
    }

    public CT_HoaDonNhapHang(int soLuong, double donGia) {
        super();
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public HoaDonNhapHang getHoaDonNhapHang() {
        return hoaDonNhapHang;
    }

    public void setHoaDonNhapHang(HoaDonNhapHang hoaDonNhapHang) {
        this.hoaDonNhapHang = hoaDonNhapHang;
    }

    public VatLieu getVatLieu() {
        return vatLieu;
    }

    public void setVatLieu(VatLieu vatLieu) {
        this.vatLieu = vatLieu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "CT_HoaDonNhapHang{" +
                "hoaDonNhapHang=" + hoaDonNhapHang +
                ", vatLieu=" + vatLieu +
                ", soLuong=" + soLuong +
                ", donGia=" + donGia +
                '}';
    }
}
