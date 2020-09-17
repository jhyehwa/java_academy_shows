// 세미
package shoesMart_END;

public class MemberDTO {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String birth;
	private String Setor; // 회원 사는 지역
	private String tel;

	public MemberDTO() {

	}

	public MemberDTO(String id, String pwd, String name, String eamil, String birth, String Sector, String tel, String email, String Setor) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.birth = birth;
		this.Setor = Setor;
		this.tel = tel;

	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirth() {
		return birth;
	}

	public String getSetor() {
		return Setor;
	}

	public void setSetor(String setor) {
		Setor = setor;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
