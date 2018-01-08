package com.zzh.demo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FtpUtils {

	private FTPClient ftp;
	private boolean isConnected;

	// timeout 默认120秒
	private static final int DEFAULTTIMEOUTSECOND = 120;

	// 连接timeout 默认30秒
	private static final int CONNECTTIMEOUTSECOND = 30;

	// 数据超时 默认300秒
	private static final int DATATIMEOUTSECOND = 300;

	private static final Logger LOGGER = LoggerFactory.getLogger(FtpUtils.class);

	/**
	 * 按照默认超时设置
	 */
	public FtpUtils() {
		ftp = new FTPClient();
		isConnected = false;

		ftp.setDefaultTimeout(DEFAULTTIMEOUTSECOND * 1000);
		ftp.setConnectTimeout(CONNECTTIMEOUTSECOND * 1000);
		ftp.setDataTimeout(DATATIMEOUTSECOND * 1000);
	}

	/**
	 * 按照用户指定超时设置
	 * 
	 * @param defaultTimeoutSecond
	 *            timeout
	 * @param connectTimeoutSecond
	 *            连接timeout
	 * @param dataTimeoutSecond
	 *            数据timeout
	 */
	public FtpUtils(int defaultTimeoutSecond, int connectTimeoutSecond, int dataTimeoutSecond) {
		ftp = new FTPClient();
		isConnected = false;

		ftp.setDefaultTimeout(defaultTimeoutSecond * 1000);
		ftp.setConnectTimeout(connectTimeoutSecond * 1000);
		ftp.setDataTimeout(dataTimeoutSecond * 1000);
	}

	/**
	 * 使用21端口，二进制链接
	 * 
	 * @param host
	 *            host IP
	 * @param user
	 *            用户名
	 * @param password
	 *            密码
	 * 
	 * @throws IOException
	 */
	public void connect(String host, String user, String password) throws IOException {
		this.connect(host, 21, user, password, false);
	}

	/**
	 * 自定义连接
	 * 
	 * @param host
	 *            host IP
	 * @param port
	 *            端口
	 * @param user
	 *            用户名
	 * @param password
	 *            密码
	 * @param isTextMode
	 *            二进制模式
	 * @throws IOException
	 */
	public void connect(String host, int port, String user, String password, boolean isTextMode) throws IOException {
		// 连接服务器
		try {
			ftp.connect(host, port);
		} catch (UnknownHostException ex) {
			LOGGER.error("{}", ex);
			throw new IOException("无法连接服务器： " + host + "！");
		}

		// 连接结果验证
		int reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			disconnect();
			throw new IOException("连接异常： " + host + "！");
		}

		// 用户登录
		if (!ftp.login(user, password)) {
			isConnected = false;
			disconnect();
			throw new IOException("用户认证错误： '" + user + "'");
		} else {
			isConnected = true;
		}

		// 传输文件模式
		if (isTextMode) {
			ftp.setFileType(FTP.ASCII_FILE_TYPE); // ACSII
		} else {
			ftp.setFileType(FTP.BINARY_FILE_TYPE); // Binary
		}
	}

	/**
	 * 
	 * 上传文件到服务器
	 * 
	 * @param ftpFileName
	 *            目标文件名
	 * @param localFile
	 *            本地文件名
	 * @throws IOException
	 *             IO异常
	 */
	public void upload(String ftpFileName, File localFile) {
		try (InputStream in = new BufferedInputStream(new FileInputStream(localFile))) {
			// 文件存在Check
			if (!localFile.exists()) {
				throw new IOException("文件： " + localFile.getAbsolutePath() + "不存在！");
			}

			upload(ftpFileName, in);
		} catch (IOException e) {
			LOGGER.error("{}", e);
		}
	}

	/**
	 * 上传数据流到服务器
	 * 
	 * @param ftpFileName
	 *            目标文件名
	 * @param in
	 *            输入流
	 * @throws IOException
	 */
	public void upload(String ftpFileName, InputStream in) throws IOException {
		// passive模式穿越防火墙.
		ftp.enterLocalPassiveMode();

		// 上传
		if (!ftp.storeFile(ftpFileName, in)) {
			throw new IOException("文件：上传路径（" + ftpFileName + "）上传失败！");
		}
	}

	/**
	 * 
	 * @param ftpFileName
	 *            目标文件名
	 * @param localFile
	 *            本地文件名
	 * @throws IOException
	 */
	public void download(String ftpFileName, File localFile) {

		try (OutputStream out = new BufferedOutputStream(new FileOutputStream(localFile))) {
			this.download(ftpFileName, out);
		} catch (IOException e) {
			LOGGER.error("{}", e);
		}
	}

	/**
	 * 
	 * 从服务器下载文件
	 * 
	 * @param ftpFileName
	 *            目标文件名
	 * @param out
	 *            输出流
	 * @throws IOException
	 */
	public void download(String ftpFileName, OutputStream out) throws IOException {
		// passive模式穿越防火墙
		ftp.enterLocalPassiveMode();

		// 取得文件信息
		FTPFile[] fileInfoArray = ftp.listFiles(ftpFileName);
		if (fileInfoArray == null) {
			throw new FileNotFoundException("文件： " + ftpFileName + " 不存在！");
		}

		// 取得文件大小
		FTPFile fileInfo = fileInfoArray[0];
		long size = fileInfo.getSize();
		if (size > Integer.MAX_VALUE) {
			throw new IOException("文件： " + ftpFileName + " 过大！");
		}

		// 下载开始
		if (!ftp.retrieveFile(ftpFileName, out)) {
			throw new IOException("文件： " + ftpFileName + " 下载失败！");
		}

		out.flush();
	}

	/**
	 * 
	 * 删除指定文件
	 * 
	 * @param ftpFileName
	 * @throws IOException
	 */
	public void delete(String ftpFileName) throws IOException {
		if (!ftp.deleteFile(ftpFileName)) {
			throw new IOException("无法删除FTP服务器上的文件： " + ftpFileName + "！");
		}
	}

	/**
	 * 
	 * 列出指定路径下的所有文件
	 * 
	 * @param filePath
	 *            指定路径
	 * @return 文件列表
	 * @throws IOException
	 */
	public List<String> list(String filePath) throws IOException {
		List<String> fileList = new ArrayList<>();

		// passive模式穿越防火墙
		ftp.enterLocalPassiveMode();

		FTPFile[] ftpFiles = ftp.listFiles(filePath);
		int size = (ftpFiles == null) ? 0 : ftpFiles.length;
		for (int i = 0; i < size; i++) {
			FTPFile ftpFile = ftpFiles[i];
			if (ftpFile.isFile()) {
				fileList.add(ftpFile.getName());
			}
		}

		return fileList;
	}

	/**
	 * 发送FTP命令
	 * 
	 * @param args
	 *            命令内容
	 * @throws IOException
	 */
	public void changeWorkingDirectory(String args) {
		if (ftp.isConnected()) {
			try {
				if (!ftp.changeWorkingDirectory(args) && !ftp.makeDirectory(args)) {
					LOGGER.info("FTP 服务器创建文件夹路径失败！！");
				} else {
					ftp.changeWorkingDirectory(args);
				}
			} catch (IOException ex) {
				LOGGER.error("{}", ex);
			}
		}
	}

	/**
	 * 切断FTP服务器连接
	 * 
	 * @throws IOException
	 * 
	 */
	public void disconnect() {

		if (ftp.isConnected()) {
			try {
				ftp.logout();
				ftp.disconnect();
				isConnected = false;
			} catch (IOException ex) {
				LOGGER.error("{}", ex);
			}
		}
	}

	/**
	 * 
	 * 连接状态确认
	 * 
	 * @return true, if connected
	 */
	public boolean isConnected() {
		return isConnected;
	}

}