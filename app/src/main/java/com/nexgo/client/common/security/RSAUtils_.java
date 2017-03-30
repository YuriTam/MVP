package com.nexgo.client.common.security;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import static com.nexgo.client.common.utils.ByteUtils.hexString2ByteArray;

public class RSAUtils_ {
	
	/**
	 * 根据参数获得RSA公钥
	 * @param ASNIFormat 公钥Str
	 * @return RSA公钥
	 * @throws Exception
	 */
	public static RSAPublicKey getPublicKey(String ASNIFormat){
		try {
			byte[] keyBytes = hexString2ByteArray(ASNIFormat);
			ASN1Sequence sequence = ASN1Sequence.getInstance(keyBytes);
			ASN1Integer modulus = ASN1Integer.getInstance(sequence.getObjectAt(0));
			ASN1Integer exponent = ASN1Integer.getInstance(sequence.getObjectAt(1));

			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus.getPositiveValue(), exponent.getPositiveValue());
			RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
			return publicKey;
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();  
	         return null;  
		}
	}
	
	/**
	 * RSA公钥加密
	 * @param PubKey RSA公钥
	 * @param input  待加密的数据
	 * @return
	 * @throws NoSuchAlgorithmException   
     * @throws NoSuchPaddingException   
     * @throws InvalidKeyException   
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
	 */
	public static String encrpt(RSAPublicKey PubKey,String input)
	{
		String enData;
      
        byte[] BCD2  = input.getBytes();
        byte[] cData = new byte[30];
        AscToBcd(cData, BCD2, 60);
        System.out.println("cData:" + bytesToHexString(cData));
        try {
        	/**进行RSA加密**/
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, PubKey);
			byte[] cipherText1 = cipher.doFinal(cData);
			enData = bytesToHexString(cipherText1);
			
		    System.out.println("\ncipher------:");
		    System.out.println("length:"+cipherText1.length);
		    System.out.println("cipher: " + enData);
		    return enData;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	
		/**  
	    * 把字节数组转换成16进制字符串  
	    * @param bArray  
	    * @return  
	    */   
		public static final String bytesToHexString(byte[] bArray) {   
		    StringBuffer sb = new StringBuffer(bArray.length);   
		    String sTemp;   
		    for (int i = 0; i < bArray.length; i++) {   
		     sTemp = Integer.toHexString(0xFF & bArray[i]);   
		     if (sTemp.length() < 2)   
		      sb.append(0);   
		     sb.append(sTemp.toUpperCase());   
		    }   
		    return sb.toString();   
		}  
		
		public static void AscToBcd(byte[] sBcdBuf, byte[] sAscBuf, int iAscLen) {
			int i, j;

			j = 0;

			for (i = 0; i < (iAscLen + 1) / 2; i++) {
				sBcdBuf[i] = (byte) (aasc_to_bcd(sAscBuf[j++]) << 4);
				if (j >= iAscLen)
					sBcdBuf[i] |= 0x00;
				else sBcdBuf[i] |= aasc_to_bcd(sAscBuf[j++]);
			}
		}
		
		public static byte aasc_to_bcd(byte ucAsc) {
			byte ucBcd;

			if (ucAsc >= '0' && ucAsc <= '9')
				ucBcd = (byte) (ucAsc - '0');
			else if (ucAsc >= 'A' && ucAsc <= 'F')
				ucBcd = (byte) (ucAsc - 'A' + 10);
			else if (ucAsc >= 'a' && ucAsc <= 'f')
				ucBcd = (byte) (ucAsc - 'a' + 10);
			else if (ucAsc > 0x39 && ucAsc <= 0x3f)
				ucBcd = (byte) (ucAsc - '0');
			else ucBcd = 0x0f;

			return ucBcd;
		}
}
