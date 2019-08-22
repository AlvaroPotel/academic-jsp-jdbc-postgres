package beans;

public class UserBean {

	private Long id;
	private String login;
	private String name;
	private String password;
	private String phone;
	private String zip;
	private String street;
	private String neighborhood;
	private String city;
	private String states;
	private String ibge;
	private String gender;
	private String perfil;
	
	private String picBase64;
	private String miniPicBase64;
	private String contentType;
	private String tempPicUser;
	
	private String pdfBase64;
	private String contentTypePdf;
	
	private boolean updatePic = true;
	private boolean updatePdf = true;
	
	private boolean active;
	
	public boolean isUpdatePic() {
		return updatePic;
	}

	public void setUpdatePic(boolean updatePic) {
		this.updatePic = updatePic;
	}

	public boolean isUpdatePdf() {
		return updatePdf;
	}

	public void setUpdatePdf(boolean updatePdf) {
		this.updatePdf = updatePdf;
	}

	public void setTempPicUser(String tempPicUser) {
		this.tempPicUser = tempPicUser;
	}

	public String getPdfBase64() {
		return pdfBase64;
	}

	public void setPdfBase64(String pdfBase64) {
		this.pdfBase64 = pdfBase64;
	}

	public String getContentTypePdf() {
		return contentTypePdf;
	}

	public void setContentTypePdf(String contentTypePdf) {
		this.contentTypePdf = contentTypePdf;
	}

	public String getTempPicUser() {
		
		tempPicUser = "data:" + contentType + ";base64," + picBase64;
		
		return tempPicUser;
	}

	public String getPicBase64() {
		return picBase64;
	}

	public void setPicBase64(String picBase64) {
		this.picBase64 = picBase64;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMiniPicBase64() {
		return miniPicBase64;
	}

	public void setMiniPicBase64(String miniPicBase64) {
		this.miniPicBase64 = miniPicBase64;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	
	
}
