USE java_quanlysanpham;

-- Tạo bảng lưu trữ thông tin sản phẩm
CREATE TABLE Sanpham (
    Masanpham INT PRIMARY KEY,
    Tensanpham VARCHAR(255) NOT NULL,
    Giaban DECIMAL(10, 2) NOT NULL,
);
-- Thêm dữ liệu vào bảng Sanpham
INSERT INTO Sanpham (Masanpham, Tensanpham, Giaban) VALUES
(1, 'Sản phẩm A', 100.50),
(2, 'Sản phẩm B', 75.25),
(3, 'Sản phẩm C', 150.00),
(4, 'Sản phẩm D', 200.75);
