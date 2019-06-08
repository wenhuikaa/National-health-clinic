package com.hospital.util;


import java.security.MessageDigest;
import java.util.Random;
/**
 * �����棬��ϢժҪ�㷨
 */
public class MD5Util {
	
	public static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       

        try {
            byte[] btInput = s.getBytes();
            // ���MD5ժҪ�㷨�� MessageDigest ����
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // ʹ��ָ�����ֽڸ���ժҪ
            mdInst.update(btInput);
            // �������
            byte[] md = mdInst.digest();
            // ������ת����ʮ�����Ƶ��ַ�����ʽ
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
	

	/**
	 * ����MD5 add by 2019/1/21
	 */
	public static String saltMD5(String password) {
		String salt = genSalt();
 		password = MD5(password + salt);
 		char[] cs = new char[48];
 		for (int i = 0; i < 48; i += 3) {
 			cs[i] = password.charAt(i / 3 * 2);
 			char c = salt.charAt(i / 3);
 			cs[i + 1] = c;
 			cs[i + 2] = password.charAt(i / 3 * 2 + 1);
 		}
		return new String(cs);
	}


	/**
	 * ���ɹ̶���ֵ
	 * @return
	 */
	private static String genSalt() {
		Random r = new Random();
 		StringBuilder sb = new StringBuilder(16);
 		sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
 		int len = sb.length();
 		if (len < 16) {
 			for (int i = 0; i < 16 - len; i++) {
 				sb.append("0");
 			}
 		}
 		String salt = sb.toString();
		return salt;
	}

	/**
	 * У����ε�MD5
	 */
	public static boolean saltMD5Verify(String password, String md5) {
 		char[] cs1 = new char[32];
		char[] cs2 = new char[16];
		for (int i = 0; i < 48; i += 3) {
			cs1[i / 3 * 2] = md5.charAt(i);
			cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
			cs2[i / 3] = md5.charAt(i + 1);
		}
		String salt = new String(cs2);
		return MD5(password + salt).equals(new String(cs1));
	}
	
	public static void main(String[] args) {
		System.out.println(MD5("123456"));
	}

}
