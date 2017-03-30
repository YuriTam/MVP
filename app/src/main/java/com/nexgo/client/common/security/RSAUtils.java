package com.nexgo.client.common.security;

import android.util.Log;

import com.nexgo.client.common.utils.ByteUtils;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import static com.nexgo.client.common.utils.ByteUtils.hexString2ByteArray;

/**
 * RSA工具类
 */
public class RSAUtils {

    /**
     * 公钥加密
     *
     * @param data 加密数据
     * @param publicKey 密钥公钥
     * @return 返回加密内容
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, RSAPublicKey publicKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 模长
        int key_len = publicKey.getModulus().bitLength() / 8;
        // 加密数据长度 <= 模长-11
        String[] datas = splitString(data, key_len - 11);
        String mi = "";
        //如果明文长度大于模长-11则要分组加密
        for (String s : datas) {
            mi += bcd2Str(cipher.doFinal(s.getBytes()));
        }
        return mi;
    }

    /**
     * 公钥加密
     *
     * @param data 加密数据
     * @param publicKey 密钥公钥
     * @return 返回加密内容
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, RSAPublicKey publicKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 模长
        int key_len = publicKey.getModulus().bitLength() / 8;
        // 加密数据长度 <= 模长-11
        byte[][] datas = splitArray(data, key_len - 11);

        byte[] mi = null;
        //如果明文长度大于模长-11则要分组加密
        for (byte[] s : datas) {
//            mi.put(cipher.doFinal(s));
            mi = ByteUtils.mergeByteArray(mi,cipher.doFinal(s));
        }
        return mi;
    }
    /**
     * 私钥解密
     *
     * @param data 解密数据
     * @param privateKey 解密私钥
     * @return 返回解密内容
     * @throws Exception
     */
    public static String decryptByPrivateKey(String data, RSAPrivateKey privateKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        //模长
        int key_len = privateKey.getModulus().bitLength() / 8;
        byte[] bytes = data.getBytes();
        byte[] bcd = ASCII_To_BCD(bytes, bytes.length);
        //如果密文长度大于模长则要分组解密
        String ming = "";
        byte[][] arrays = splitArray(bcd, key_len);
        for (byte[] arr : arrays) {
            ming += new String(cipher.doFinal(arr));
        }
        return ming;
    }

    /**
     * 私钥解密
     *
     * @param data 解密数据
     * @param privateKey 解密私钥
     * @return 返回解密内容
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, RSAPrivateKey privateKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        //模长
        int key_len = privateKey.getModulus().bitLength() / 8;
        //如果密文长度大于模长则要分组解密
        byte[] ming = null;
        byte[][] arrays = splitArray(data, key_len);
        for (byte[] arr : arrays) {
            byte[] aa = cipher.doFinal(arr);
            ming = ByteUtils.mergeByteArray(ming,cipher.doFinal(arr));
        }

        //去掉后面的0
        int i;
        for (i = 0; i < ming.length; i++){
            if (ming[i] == 0){
                break;
            }
        }
        byte[] res = new byte[i];
        System.arraycopy(ming,0,res,0,i);
        return res;
    }

    /**
     * ASCII码转BCD码
     */
    public static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {
        byte[] bcd = new byte[asc_len / 2];
        int j = 0;
        for (int i = 0; i < (asc_len + 1) / 2; i++) {
            bcd[i] = asc_to_bcd(ascii[j++]);
            bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
        }
        return bcd;
    }

    public static byte asc_to_bcd(byte asc) {
        byte bcd;

        if ((asc >= '0') && (asc <= '9'))
            bcd = (byte) (asc - '0');
        else if ((asc >= 'A') && (asc <= 'F'))
            bcd = (byte) (asc - 'A' + 10);
        else if ((asc >= 'a') && (asc <= 'f'))
            bcd = (byte) (asc - 'a' + 10);
        else
            bcd = (byte) (asc - 48);
        return bcd;
    }

    /**
     * BCD转字符串
     */
    public static String bcd2Str(byte[] bytes) {
        char temp[] = new char[bytes.length * 2], val;

        for (int i = 0; i < bytes.length; i++) {
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

            val = (char) (bytes[i] & 0x0f);
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
        }
        return new String(temp);
    }

    /**
     * 拆分字符串
     */
    public static String[] splitString(String string, int len) {
        int x = string.length() / len;
        int y = string.length() % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }
        String[] strings = new String[x + z];
        String str = "";
        for (int i = 0; i < x + z; i++) {
            if (i == x + z - 1 && y != 0) {
                str = string.substring(i * len, i * len + y);
            } else {
                str = string.substring(i * len, i * len + len);
            }
            strings[i] = str;
        }
        return strings;
    }

    /**
     * 拆分数组
     */
    public static byte[][] splitArray(byte[] data, int len) {
        int x = data.length / len;
        int y = data.length % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }
        byte[][] arrays = new byte[x + z][];
        byte[] arr;
        for (int i = 0; i < x + z; i++) {
            arr = new byte[len];
            if (i == x + z - 1 && y != 0) {
                System.arraycopy(data, i * len, arr, 0, y);
            } else {
                System.arraycopy(data, i * len, arr, 0, len);
            }
            arrays[i] = arr;
        }
        return arrays;
    }

    public static RSAPublicKey getPublicKey(String ASNIFormat) {
        try {
            byte[] keyBytes = hexString2ByteArray(ASNIFormat);
            ASN1Sequence sequence = ASN1Sequence.getInstance(keyBytes);
            ASN1Integer modulus = ASN1Integer.getInstance(sequence.getObjectAt(0));
            ASN1Integer exponent = ASN1Integer.getInstance(sequence.getObjectAt(1));
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus.getPositiveValue(), exponent.getPositiveValue());
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) factory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static RSAPrivateKey getPrivateKey(String ASNIFormat) {
        try {
            byte[] keyBytes = hexString2ByteArray(ASNIFormat);
            ASN1Sequence sequence = ASN1Sequence.getInstance(keyBytes);
            ASN1Integer modulus = ASN1Integer.getInstance(sequence.getObjectAt(1));
            ASN1Integer exponent = ASN1Integer.getInstance(sequence.getObjectAt(3));
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus.getPositiveValue(), exponent.getPositiveValue());
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) factory.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static RSAPublicKey getPublicKey(byte[] keyBytes) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return (RSAPublicKey) publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 通过私钥byte[]将公钥还原，适用于RSA算法
    public static RSAPrivateKey getPrivateKey(byte[] keyBytes) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return (RSAPrivateKey) privateKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        final String PUBLICKEY = "30818902818100C5AFBE6C8AA4D86D0EA93691A08AB149093A13E32525D9DBD771AD63F6CDE33C9C1BC6E55DF8119F1B51C3840F85EB4F114E0D43C81A858738927522A70AACA7E74BC5927935F2048EEC134E1B53593602D1B71E28BEF2AFDB2F828BAC66D363E1C97B37D0D37BD662174E33F39D9BE85091669C67EA3AE50608BEDE72C5E6690203010001";
        final String PRIVATEKEY = "3082025C02010002818100C5AFBE6C8AA4D86D0EA93691A08AB149093A13E32525D9DBD771AD63F6CDE33C9C1BC6E55DF8119F1B51C3840F85EB4F114E0D43C81A858738927522A70AACA7E74BC5927935F2048EEC134E1B53593602D1B71E28BEF2AFDB2F828BAC66D363E1C97B37D0D37BD662174E33F39D9BE85091669C67EA3AE50608BEDE72C5E669020301000102818100AE2AE44EA4E80F762610976F3E4E735BB1B1658C1E5276DAE5B9D5D5D8C0149075C9533709CF615718AEB4DF48FDE80F4563CAF408FCB4E029CB7F400483B710B9DB84791B3FCB8738D231521079D94660EBA0133A1CD371771BA5A4AA7AD8369D4B67E40F9A1049DB57F2E2B992A840CCEF4572E812A4D10E27689A86061EC1024100EF933A39821D0E4EFA3564CEAA9D2B3B75CB0CBE1E0B4C4B34D09E3772F53C9FEFCC8B3A639DFA2FEDCD67CE480345FC354A452B6421582C74BF83CA11996A9D024100D33D54CAFB8FF72B2905BBBA0B9FD809156593BBAACADB964725F5D4ECA3130FAEF12808AC317218E5D16B211B25D8A1156848ADBB474B5FE612CEFEF651CB3D02403370F17797B09579EFCFB72D8904E65B86176B4D073675D3502BDB4D1CA9FBB27BD2F7B239E199EEE0A8D23940CD3321CC6F7E1AAB0D5DD96DD8117C39FB2F21024049C85354AEF78322CD2A567ECE4E9F9BD4A06D3090A05880AE0B414984FA14CF56909F26205708FE4F4F8D76BBCFC551C6736D675951B6AF2162927095C2CE1502405854A37F5126E94681D1FDB8DCA376C70F9FC5D60DCB7F3C7ED326E3348396B0B02B020963599E4DEB2A3EF53A3F04C89BA3E4A1F8DBF74E9939124F2AF84018";

        byte[] data = ByteUtils.hexString2ByteArray(PUBLICKEY);
        System.out.print(String.valueOf(data));

        RSAPublicKey publicKey = getPublicKey(PUBLICKEY);
        RSAPrivateKey privateKey = getPrivateKey(PRIVATEKEY);
        if (publicKey == null || privateKey == null) {
            System.out.println("error");
            return;
        }

        final String orgText = "just test";
        byte[] orgArray = new byte[]{0x6A,0x75,0x73,0x74,0x20,0x74,0x65,0x73,0x74};
        String encryptText = encryptByPublicKey(orgText, publicKey);
        String plainText = decryptByPrivateKey(encryptText, privateKey);
        Log.d("RSAUtils_","encryptText="+encryptText);
        Log.d("RSAUtils_","plainText="+plainText);
        byte[] encryptArray = encryptByPublicKey(orgArray, publicKey);
        byte[] plainArray = decryptByPrivateKey(encryptArray, privateKey);
        Log.d("RSAUtils_","encryptText="+ByteUtils.byteArray2HexStringWithSpace(encryptArray));
        Log.d("RSAUtils_","plainText="+ByteUtils.byteArray2HexStringWithSpace(plainArray));
    }
}