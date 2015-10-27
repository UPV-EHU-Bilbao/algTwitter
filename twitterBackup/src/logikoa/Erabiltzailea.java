package logikoa;
//Erabiltzailearen user id gordeko da
public class Erabiltzailea {

	private String userId;
	
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
	
}
