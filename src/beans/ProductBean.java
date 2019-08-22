package beans;

public class ProductBean {

	private Long id;
	private String name;
	private Double quant;
	private Double price;
	private Long category_id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getQuant() {
		return quant;
	}
	public void setQuant(Double quant) {
		this.quant = quant;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPriceTemp() {
		return Double.toString(price).replace('.',',');
	}
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

}
