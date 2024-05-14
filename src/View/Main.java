package View;

import Controller.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    public static void main(String[] args) {
        JTable Bang_Sanpham;
        JLabel lb_Masannpham;
        JLabel lb_Tensanpham;
        JLabel lb_Giaban;
        JTextField txt_Masanpham;
        JTextField txt_Tensanpham;
        JTextField txt_Giaban;
        JButton btn_Them;
        JButton btn_Capnhat;
        JButton btn_Xoa;
        JButton btn_Timkiem;
        JButton btn_Dong;
        JButton btn_Huy;
        JButton btn_Tailai;

        JFrame jframe = new JFrame();
        jframe.setTitle("Quan ly san pham");
        jframe.setSize(800, 600);
        jframe.setLocation(400, 100);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,3,3,3);

        //panle input
        JPanel panel_input = new JPanel(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        lb_Masannpham = new JLabel("Mã sản phẩm");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 30;
        gbc.weightx = 1.0;
        panel_input.add(lb_Masannpham, gbc);
        txt_Masanpham = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel_input.add(txt_Masanpham, gbc);
        lb_Tensanpham = new JLabel("Tên sản phẩm");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel_input.add(lb_Tensanpham, gbc);
        txt_Tensanpham = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel_input.add(txt_Tensanpham, gbc);
        lb_Giaban = new JLabel("Giá bán");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel_input.add(lb_Giaban, gbc);
        txt_Giaban = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel_input.add(txt_Giaban, gbc);

        //panel chuc nang
        JPanel panel_chucnang = new JPanel(new GridBagLayout());
        btn_Them = new JButton("Thêm");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panel_chucnang.add(btn_Them, gbc);
        btn_Capnhat = new JButton("Cập nhật");
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel_chucnang.add(btn_Capnhat, gbc);
        btn_Xoa = new JButton("Xóa");
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel_chucnang.add(btn_Xoa, gbc);
        btn_Timkiem = new JButton("Tìm kiếm");
        gbc.gridx = 3;
        gbc.gridy = 0;
        panel_chucnang.add(btn_Timkiem, gbc);
        btn_Dong = new JButton("Đóng");
        gbc.gridx = 4;
        gbc.gridy = 0;
        panel_chucnang.add(btn_Dong, gbc);
        btn_Huy = new JButton("Hủy");
        gbc.gridx = 5;
        gbc.gridy = 0;
        panel_chucnang.add(btn_Huy, gbc);
        btn_Tailai = new JButton("Tải lại");
        gbc.gridx = 6;
        gbc.gridy = 0;
        panel_chucnang.add(btn_Tailai, gbc);

        //
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jframe.add(panel_input, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        jframe.add(panel_chucnang, gbc);

        //table
        Bang_Sanpham = new JTable();
        Bang_Sanpham.setDefaultEditor(Object.class, null);
        DefaultTableModel model = Sanpham_Controller.LaydulieutuDB();
        Bang_Sanpham.setModel(model);

        JScrollPane scrollPane = new JScrollPane(Bang_Sanpham);
        //

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        jframe.add(scrollPane, gbc);

        //
        Bang_Sanpham.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = Bang_Sanpham.getSelectedRow();
                if (row != -1){
                    String masp = Bang_Sanpham.getValueAt(row, 0).toString();
                    String tensp = Bang_Sanpham.getValueAt(row, 1).toString();
                    String giaban = Bang_Sanpham.getValueAt(row, 2).toString();

                    txt_Masanpham.setText(masp);
                    txt_Tensanpham.setText(tensp);
                    txt_Giaban.setText(giaban);
                }
            }
        });
        // Sự kiện cho nút Thêm
        btn_Them.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer masanpham = null;
                    if (!txt_Masanpham.getText().isEmpty()) {
                        masanpham = Integer.parseInt(txt_Masanpham.getText());
                    }
                    String tensanpham = txt_Tensanpham.getText();
                    double giaban = Double.parseDouble(txt_Giaban.getText());

                    if (masanpham == null || tensanpham.isEmpty() || giaban == 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
                        return; // Không thực hiện thêm nếu dữ liệu thiếu
                    }

                    // Kiểm tra xem Masanpham đã tồn tại chưa
                    if (Sanpham_Controller.KiemTraMasanphamTonTai(masanpham)) {
                        JOptionPane.showMessageDialog(null, "Mã sản phẩm đã tồn tại.");
                        return; // Không thực hiện thêm nếu mã sản phẩm đã tồn tại
                    }

                    // Thêm dữ liệu vào cơ sở dữ liệu
                    Sanpham_Controller.ThemDulieuvaoDB(masanpham, tensanpham, giaban);
                    // Cập nhật bảng hiển thị dữ liệu
                    Bang_Sanpham.setModel(Sanpham_Controller.LaydulieutuDB());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Mã sản phẩm và giá bán phải là số.");
                }
            }
        });

        // Sự kiện cho nút Cập nhật
        btn_Capnhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer masanpham = null;
                    if (!txt_Masanpham.getText().isEmpty()) {
                        masanpham = Integer.parseInt(txt_Masanpham.getText());
                    }
                    String tensanpham = txt_Tensanpham.getText();
                    double giaban = Double.parseDouble(txt_Giaban.getText());

                    if (masanpham == null || tensanpham.isEmpty() || giaban == 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
                        return; // Không thực hiện cập nhật nếu dữ liệu thiếu
                    }

                    // Kiểm tra xem Masanpham có tồn tại không
                    if (!Sanpham_Controller.KiemTraMasanphamTonTai(masanpham)) {
                        JOptionPane.showMessageDialog(null, "Mã sản phẩm không tồn tại.");
                        return; // Không thực hiện cập nhật nếu mã sản phẩm không tồn tại
                    }

                    // Cập nhật dữ liệu trong cơ sở dữ liệu
                    Sanpham_Controller.CapnhatDulieutrongDB(masanpham, tensanpham, giaban);
                    // Cập nhật bảng hiển thị dữ liệu
                    Bang_Sanpham.setModel(Sanpham_Controller.LaydulieutuDB());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Mã sản phẩm và giá bán phải là số.");
                }
            }
        });

// Sự kiện cho nút Xóa
        btn_Xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer masanpham = null;
                    if (!txt_Masanpham.getText().isEmpty()) {
                        masanpham = Integer.parseInt(txt_Masanpham.getText());
                    }

                    // Kiểm tra xem Masanpham có tồn tại không
                    if (masanpham == null || !Sanpham_Controller.KiemTraMasanphamTonTai(masanpham)) {
                        JOptionPane.showMessageDialog(null, "Mã sản phẩm không tồn tại.");
                        return; // Không thực hiện xóa nếu mã sản phẩm không tồn tại
                    }
                    Sanpham_Controller.XoadulieutuDB(masanpham);
                    // Cập nhật bảng hiển thị dữ liệu
                    Bang_Sanpham.setModel(Sanpham_Controller.LaydulieutuDB());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Mã sản phẩm phải là một số nguyên.");
                }
            }
        });

        // Sự kiện cho nút Hủy
        btn_Huy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt_Masanpham.setText("");
                txt_Tensanpham.setText("");
                txt_Giaban.setText("");
            }
        });

        // Sự kiện cho nút Đóng
        btn_Dong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Sự kiện cho nút Tìm kiếm
        btn_Timkiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maSanpham = txt_Masanpham.getText();
                String tenSanpham = txt_Tensanpham.getText();
                Double giaBan = null;
                try {
                    giaBan = Double.parseDouble(txt_Giaban.getText());
                } catch (NumberFormatException ex) {
                    // Nếu không phải là số, đặt giá trị giaBan là NaN
                    giaBan = Double.NaN;
                }
                if (!maSanpham.isEmpty() || !tenSanpham.isEmpty() || !Double.isNaN(giaBan)) {
                    Bang_Sanpham.setModel(Sanpham_Controller.TimKiemDulieu(maSanpham, tenSanpham, String.valueOf(giaBan)));
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập từ khóa để tìm kiếm.");
                }
            }
        });

        // Sự kiện cho nút Tải lại
        btn_Tailai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bang_Sanpham.setModel(Sanpham_Controller.LaydulieutuDB());
                txt_Masanpham.setText("");
                txt_Tensanpham.setText("");
                txt_Giaban.setText("");
            }
        });


        jframe.setVisible(true);

    }
}