public class Secret {

	/**
	 * 加密
	 * 
	 * @param buf
	 */
	public static void encriptXor(byte[] buf) {

		byte encriptKey = 118;
		byte key;
		System.out.println("加密过程 ：\r\n");
		for (int i = 0; i < buf.length; i++) {
			key = (byte) (buf[i] ^ encriptKey);
			System.out.println(i + "=" + buf[i] + " Xor " + encriptKey + " = "
					+ key);
			buf[i] = key;
			encriptKey = key;
		}
	}

	/**
	 * 解密
	 * 
	 * @param buf
	 */
	public static byte[] decriptXor(byte[] buf) {

		byte encriptKey = 118;
		byte nextencriptKey;
		System.out.println("解密过程 ：\r\n");
		for (int i = 0; i < buf.length; i++) {
			nextencriptKey = buf[i];
			buf[i] = (byte) (nextencriptKey ^ encriptKey);
			encriptKey = nextencriptKey;
		}
		return buf;
	}

	public static String decriptXor(String objStr) {
		byte[] buf = decriptString(objStr);
		byte encriptKey = 118;
		byte nextencriptKey;
		System.out.println("解密过程 ：\r\n");
		for (int i = 0; i < buf.length; i++) {
			nextencriptKey = buf[i];
			buf[i] = (byte) (nextencriptKey ^ encriptKey);
			encriptKey = nextencriptKey;
		}
		return new String(buf);
	}

	public static String encriptString(byte[] a) {
		String ret = "";
		if (a == null)
			return null;
		for (int i = 0; i < a.length; i++) {
			ret += encriptString(a[i]);
		}

		return ret;
	}

	public static byte[] decriptString(String a) {

		if (a == null)
			return null;
		byte[] c = a.getBytes();
		byte[] d = new byte[c.length / 2];

		for (int i = 0; i < d.length; i++) {
			d[i] = decriptString((char) c[i * 2], (char) c[i * 2 + 1]);
		}
		return d;
	}

	public static String encriptString(byte i) {

		char a = (char) (i / 12 + 77);
		char b = (char) (i % 12 + 77);

		// System.out.println("char a = "+a+" b ="+b);

		return new String() + a + b;
	}

	public static byte decriptString(char m, char n) {

		int a = (int) (m - 77);
		int b = (int) (n - 77);

		// byte c = (byte)(a*12+b);

		// System.out.println("char a = "+a+" b ="+b);

		return (byte) (a * 12 + b);
	}

	public static void test() throws Exception {
		String source = "荣誉abcdef12234567@#$%^&*()'\"";

		System.out.println("\r加密前文字：\r\n" + source);
		System.out.println("\r加密前文字长度：\r\n" + source.length());
		System.out.println("\r16进制：\r\n" + toHexString(source));
		byte[] encodedData = source.getBytes();
		encriptXor(encodedData);
		String r = encriptString(encodedData);
		System.out.println("加密后文字：\r\n" + r);
		byte[] decodedData = decriptString(r);
		decriptXor(decodedData);
		String target = new String(decodedData);
		System.out.println("\r\n解密后文字: \r\n" + target);
	}

	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	public static String toHexString(byte[] buf) {
		String str = new String(buf);
		for (int i = 0; i < str.length(); i++) {
			int ch = (int) str.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

}
