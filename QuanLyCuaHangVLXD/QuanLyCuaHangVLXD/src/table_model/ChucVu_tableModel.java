package table_model;

import entity.ChucVu;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ChucVu_tableModel extends AbstractTableModel {

    private List<ChucVu> ds;
    String [] headers = {"STT","Mã Chức Vụ", "Tên Chức Vụ", "Hệ Số Lương"};

    public ChucVu_tableModel(List<ChucVu> ds){
        super();
        this.ds = ds;
    }
    public String getColumnName(int column){
        return headers[column];
    }
    @Override
    public int getRowCount() {
        return ds.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ChucVu cv = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return rowIndex+1;
            case 1:
                return cv.getMaChucVu();
            case 2:
                return cv.getTenChucVu();
            case 3:
                return cv.getHeSoLuong();
            default:
                return cv;
        }
    }
}

