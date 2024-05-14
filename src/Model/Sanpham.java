package Model;

public class Sanpham {
    private Integer Id;
    private String Tensanpham;
    private Double Giaban;
    public Sanpham(){

    }
    public Sanpham(Integer id, String tensanpham, Double giaban){
        this.Id = id;
        this.Tensanpham = tensanpham;
        this.Giaban = giaban;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public String getTensanpham() {
        return Tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.Tensanpham = tensanpham;
    }

    public Double getGiaban() {
        return Giaban;
    }

    public void setGiaban(Double giaban) {
        this.Giaban = giaban;
    }
}
