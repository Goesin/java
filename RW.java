package day0421;

import java.io.*;

public class RW {
	public static void main(String[] args) throws Exception {
		//写入路径
		File file = new File("F:\\Helloworld");
		//(写入路径的File对象, 输出路径)
		getFile(file, "G:\\a");
	}

	public static void getFile(File file, String outpath) throws Exception {
		//循环遍历file的文件及文件夹
		for (File f : file.listFiles()) {
			//如果是文件：
			if (f.isFile()) {
				File ftt = new File(outpath);
				//判断该路径是否存在，如果不存在则创建文件夹
				if (!ftt.exists()) {
					ftt.mkdirs();
				}

				// 读写文件
				FileInputStream fis = new FileInputStream(f);
				System.out.println("file:" + outpath + File.separator + f.getName());
				FileOutputStream fos = new FileOutputStream(outpath + File.separator + f.getName());
				int len = 0;
				byte b[] = new byte[1024];
				while ((len = fis.read(b)) != -1) {
					fos.write(b, 0, len);
				}
				fos.close();
				fis.close();
			} 
			
			//如果是文件夹：
			else if (f.isDirectory()) {
				System.out.println("dir:" + outpath + File.separator + f.getName());
				//为防止出现空文件夹而进行创建
				File fi=new File(outpath + File.separator + f.getName());
				fi.mkdir();//如果没有空文件夹，那么这一步没用
				//递归调用
				getFile(f, outpath + File.separator + f.getName());
			}
		}

	}
}
