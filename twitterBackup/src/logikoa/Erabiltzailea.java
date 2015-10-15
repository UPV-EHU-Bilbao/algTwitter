package logikoa;

public class Erabiltzailea {

	private String userId;
	/*private String passId;*/
	private String pin;
	private static Erabiltzailea mErab = null;
	
	private Erabiltzailea(){
		
	}
	public static synchronized Erabiltzailea getErab(){
		if(mErab==null){
			mErab = new Erabiltzailea();
		}
		return mErab;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPin() {
		return pin;
	}
	public void setPassId(String passId) {
		this.pin = passId;
	}
	
}
