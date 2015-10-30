package Encriptation;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
 
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
public class AESEncriptacion {
	 
	 private AESkey aesKey;
	 private final String ALGORITMO = "AES";
	 private final int LONGITUD = 128;
	 private final String CODIFICACION = "UTF-8";
	 
	 public AESEncriptacion(AESkey aesKey) throws Exception {  
	  this.aesKey = aesKey;
	 }
	   
	 public AESkey generaKey() throws Exception {
	   KeyGenerator kgen = KeyGenerator.getInstance(ALGORITMO);
	   kgen.init(LONGITUD);
	   SecretKey skey = kgen.generateKey(); 
	   AESkey aesKey = new AESkey();
	   aesKey.setEncoded(HexToString(skey.getEncoded()));
	   return aesKey;
	 }
	  
	 public String encripta(String cadena) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
	 IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {  
	    byte[] raw = StringToHex(aesKey.getEncoded());
	    SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITMO);
	    Cipher cipher = Cipher.getInstance(ALGORITMO);
	    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	    byte[] encrypted = cipher.doFinal(cadena.getBytes(CODIFICACION));
	    String encriptado = HexToString(encrypted);
	    return encriptado;
	 }
	  
	 private String HexToString(byte[] arregloEncriptado) {
	   String textoEncriptado = "";
	   for (int i = 0; i < arregloEncriptado.length; i++) {
	     int aux = arregloEncriptado[i] & 0xff;
	     if (aux < 16) {
	      textoEncriptado = textoEncriptado.concat("0");
	     }
	     textoEncriptado = textoEncriptado.concat(Integer.toHexString(aux));
	   }
	   return textoEncriptado;
	 }
	  
	 private byte[] StringToHex(String encriptado) {
	   byte[] enBytes = new byte[encriptado.length() / 2];
	   for (int i = 0; i < enBytes.length; i++) {
	     int index = i * 2;
	     String aux = encriptado.substring(index, index + 2);
	     int v = Integer.parseInt(aux, 16);
	     enBytes[i] = (byte) v;
	   }
	   return enBytes;
	 }
	  
	 public String desencriptar(String encriptado) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
	   UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException {
	   byte[] raw = StringToHex(aesKey.getEncoded());
	   SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITMO);
	   Cipher cipher = Cipher.getInstance(ALGORITMO);  
	   cipher.init(Cipher.DECRYPT_MODE, skeySpec);
	   byte[] original = cipher.doFinal(StringToHex(encriptado));
	   String originalString = new String(original);
	   return originalString;
	 }
	  
	 public static void main(String args[]) throws Exception {
	   AESkey aesKey = new AESkey();
	      
	   AESEncriptacion tmp = new AESEncriptacion(aesKey);
	   aesKey = tmp.generaKey();
	     
	   AESEncriptacion ejemplo = new AESEncriptacion(aesKey);
	    
	   String encriptado = ejemplo.encripta("123456789012345678");
	   String desencriptado = ejemplo.desencriptar(encriptado);
	   System.out.println(encriptado);
	   System.out.println(desencriptado);
	   System.out.println("***************************************************");
	   encriptado = ejemplo.encripta("esto es un segundo ejemplo");
	   desencriptado = ejemplo.desencriptar(encriptado);
	   System.out.println(encriptado);
	   System.out.println(desencriptado);
	 }
	
}
