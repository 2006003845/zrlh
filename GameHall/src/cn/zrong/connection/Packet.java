package cn.zrong.connection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class Packet {

	public static final String TAG = "Packet";

	/** value����ֵ */
	private static final int MAX = 0xFFFF;
	/** ˳���� */
	private static int value = 0;
	/** ΨһId */
	private long uniqueId;

	/** ������ */
	private short type;

	private long playId;

	protected byte[] data;

	public byte[] getData() {
		return data;
	}

	public static long getUnique() {
		try {
			return System.currentTimeMillis() << 20 | value;
		} finally {
			value = value++ < MAX ? value : 0;
		}
	}

	/**
	 * ����֪����ݹ���һ����ݰ�,<br>
	 * dataδͬ��ʹ��ʱ��Ҫ�������,<br>
	 * typeδͬ��ʹ��ʱ��Ҫ�������<br>
	 * 
	 * @param type
	 *            ����
	 * @param data
	 *            ��ݶ�
	 */
	public Packet(short type, byte[] data) {
		if (data.length > Short.MAX_VALUE)
			throw new ArrayIndexOutOfBoundsException("packet data to long");
		this.type = type;
		this.data = data;
		uniqueId = getUnique();
	}

	// public abstract Object getData();
}
