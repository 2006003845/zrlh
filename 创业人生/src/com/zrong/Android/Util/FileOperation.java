package com.zrong.Android.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

/**
 * 功能: 对SD卡上文件的读写操作 创建人: 杨征 创建时间：13 Nov 2010
 */
public class FileOperation {
	private static String rootPath = Environment.getExternalStorageDirectory()
			.getAbsolutePath();

	/**
	 * 功能: 构造方法，获取SD卡的主存储目录 创建人: 杨征
	 * 
	 */
	public FileOperation() {
		this.rootPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
	}

	/**
	 * 功能: 在SD卡上创建文件目录 创建人: 杨征
	 * 
	 * 
	 * @param filePath
	 *            :文件目录
	 * @return
	 */
	public static File createFileDirToSDCart(String filePath) {
		File file = new File(rootPath + filePath);
		file.mkdir();
		return file;
	}

	/**
	 * 功能: 创建一个新的文件对象 创建人: 杨征
	 * 
	 * 
	 * @param filePath
	 *            ：文件的路径
	 * @param fileName
	 *            ：文件名称
	 * @return
	 * @throws IOException
	 */
	public static File createFileToSDCard(String filePath, String fileName)
			throws IOException {
		File file = new File(rootPath + filePath, fileName);
		if (file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	/**
	 * 功能: 获取文件 创建人: 杨征
	 * 
	 * 
	 * @param filePath
	 *            ：文件的路径
	 * @param fileName
	 *            ：文件名称
	 * @return
	 */
	public static File getFileFromSDCard(String filePath, String fileName) 
	{
		File file = new File(filePath, fileName);
		return file;
	}

	/**
	 * 功能: 删除SD卡上文件 创建人: 杨征
	 * 
	 * 
	 * @param filePath
	 *            ：文件的路径
	 * @param fileName
	 *            ：文件名称
	 * @return
	 * @throws IOException
	 */
	public static boolean deleteFileFromSDCard(String filePath, String fileName)
			throws IOException {
		File file = new File(rootPath + filePath, fileName);
		return file.delete();
	}

	/**
	 * 功能: 判断文件是否已经存在 创建人: 杨征
	 * 
	 * 
	 * @param filePath
	 *            :文件路径
	 * @param fileName
	 *            ：文件名称
	 * @return
	 */
	public static Boolean isFileExist(String filePath, String fileName) {
		File file = new File(rootPath + filePath + fileName);
		return file.exists();
	}

	/**
	 * 功能: 判断文件夹是否已经存在 创建人: 杨征
	 * 
	 * 
	 * @param filePath
	 *            ：文件夹在ＳＤ卡上路径
	 * @return
	 */
	public static Boolean isDirExist(String filePath) {
		File file = new File(rootPath + filePath);
		return file.exists();
	}

	/**
	 * 功能: 写文件到SD卡 创建人: 杨征
	 * 
	 * 
	 * @param filePath
	 *            ：文件在SD卡上的路径
	 * @param fileName
	 *            ：文件名称
	 * @param inputStream
	 *            ：网上下载的文件流
	 * @return
	 */
	public static File writeToSDCardFromInputStream(String filePath,
			String fileName, InputStream inputStream) {
		File file = null;
		OutputStream outputStream = null;
		try {
			// 如果文件夹不存在，就创建文件夹
			if (!isDirExist(filePath)) {
				createFileDirToSDCart(filePath);
			}
			// 创建文件
			file = createFileToSDCard(filePath, fileName);
			outputStream = new FileOutputStream(file);
			// 每次写入４Ｋ
			byte buffer[] = new byte[4 * 1024];
			int temp;
			while ((temp = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, temp);
			}
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}
}