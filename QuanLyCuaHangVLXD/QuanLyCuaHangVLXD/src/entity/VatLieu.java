package entity;

public class VatLieu {
    private String maVatLieu;
    private String tenVatLieu;
    private LoaiVatLieu loaiVatLieu;
    private float trongLuong;
    private String donViTinh;
    private String xuatXu;
    private int soLuong;
    private double giaBan;

    public VatLieu() {
        // TODO Auto-generated constructor stub
    }

    public VatLieu(String maVatLieu, String tenSanPham, float trongLuong, String donViTinh, String xuatXu, int soLuong, double giaBan) {
        this.maVatLieu = maVatLieu;
        this.tenVatLieu = tenSanPham;
        this.trongLuong = trongLuong;
        this.donViTinh = donViTinh;
        this.xuatXu = xuatXu;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
    }

    public String getMaVatLieu() {
        return maVatLieu;
    }

    public void setMaVatLieu(String maVatLieu) {
        this.maVatLieu = maVatLieu;
    }

    public String getTenVatLieu() {
        return tenVatLieu;
    }

    public void setTenVatLieu(String tenVatLieu) {
        this.tenVatLieu = tenVatLieu;
    }

    public float getTrongLuong() {
        return trongLuong;
    }

    public void setTrongLuong(float trongLuong) {
        this.trongLuong = trongLuong;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public LoaiVatLieu getLoaiVatLieu() {
        return loaiVatLieu;
    }

    public void setLoaiVatLieu(LoaiVatLieu loaiVatLieu) {
        this.loaiVatLieu = loaiVatLieu;
    }
}
