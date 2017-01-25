package action;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;

import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class CreateqrAction {
	private static String charset="utf-8";//定义字符编码
	private static String QrName="jpg";//定义二维码的类型
	private static int QrSize=300;//定义二维码大size
	private static final int WIDTH = 60;//定义内嵌图片的宽度
    private static final int HEIGHT = 60;//定义内嵌图片的高度
	/**
	 * 	生成二维码函数createQrimage()
	 * @throws Exception 
	 */
	public static BufferedImage crateQrimage(String content,String imgPath,
			   boolean needCompress) throws Exception{
		Hashtable<EncodeHintType, Object> hash = new Hashtable<EncodeHintType, Object>();
		hash.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//越高存储越小,容错率越高
		hash.put(EncodeHintType.CHARACTER_SET, charset);
		BitMatrix bitMatrix= new MultiFormatWriter().encode(content,
			    BarcodeFormat.QR_CODE, QrSize, QrSize, hash);//生成010101比特矩阵
		int width=bitMatrix.getWidth();//获取比特矩阵的宽度
		int height=bitMatrix.getHeight();//获取比特矩阵的高度
		BufferedImage Qrimage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);//创建二维码图片对象，大小是width X heigth像素大
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Qrimage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000: 0xFFFFFFFF);//对图片每一个像素点进行描色
			}
		}
		
		if (imgPath == null || "".equals(imgPath)) {//如果要插入的图片的路径为空，直接返回二维码图片
			   return Qrimage;
	    }
	    
		CreateqrAction.insertImage(Qrimage, imgPath, needCompress);//调用insertImage函数插入图片到二维码中
	    return Qrimage;	
	}
    
	/**
	 * 插入内嵌图片
	 * @param source
	 * @param imgPath
	 * @param needCompress
	 * @throws Exception
	 */
	 private static void insertImage(BufferedImage source, String imgPath,
	   boolean needCompress) throws Exception {
	  File file = new File(imgPath);
	  if (!file.exists()) {
	   System.err.println(""+imgPath+"路径不存在！");
	   return;
	  }
	  Image src = ImageIO.read(new File(imgPath));
	  int width = src.getWidth(null);//获取原宽度
	  int height = src.getHeight(null);//获取原高度
	  
	  if (needCompress) { //比较要插入的图片的宽度是否大于设定的WIDTH=60像素宽
	   if (width > WIDTH) {
	    width = WIDTH;
	   }
	   if (height > HEIGHT) {//比较要插入的图片的高度是否大于设定的HEIGTH=60像素宽
	    height = HEIGHT;
	   }
	   Image image = src.getScaledInstance(width, height,
	     Image.SCALE_SMOOTH);//把image对象的getScaledInstance方法把图片缩小到heightXwidth像素大小
	   BufferedImage tag = new BufferedImage(width, height,
	     BufferedImage.TYPE_INT_RGB);//创建一个透明色的BufferedImage对象
	   Graphics g = tag.getGraphics();
	   g.drawImage(image, 0, 0, null); //绘制指定图像中当前可用的image图像，图像的左上角位于该图形上下文坐标（0,0）的 (x, y)
	   g.dispose();
	   src = image;
	  }
	  //
	  Graphics2D graph = source.createGraphics();
	  int x = (QrSize - width) / 2;
	  int y = (QrSize - height) / 2;
	  graph.drawImage(src, x, y, width, height, null);//将需要插入的图片内嵌在二维码中坐标为（x,y）的地方
	  Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
	  graph.setStroke(new BasicStroke(3f));
	  graph.draw(shape);
	  graph.dispose();
	 }
	  
	  
	 /**
	  * 对二维码图像进行编码，生成二维码
	  * @param content
	  * @param imgPath
	  * @param destPath
	  * @param needCompress
	  * @throws Exception
	  */
	 public static void encode(String content, String imgPath, String destPath,
	   boolean needCompress,String qrName) throws Exception {
	  BufferedImage image = CreateqrAction.crateQrimage(content, imgPath,
	  needCompress);
	  mkdirs(destPath);
	  String file = qrName+".jpg";
	  System.out.print(new File(destPath+"/"+file));
	  ImageIO.write(image, QrName, new File(destPath+"/"+file));
	 }
	 
	 
	 
	 /**
	  * 
	  * @param destPath
	  */
	 public static void mkdirs(String destPath) {
	  File file =new File(destPath);    
	  //
	  if (!file.exists() && !file.isDirectory()) {
	   file.mkdirs();
	  }
	 }
	 
}  

