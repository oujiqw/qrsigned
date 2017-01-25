package action;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class GetqrAction extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 获取前端页面传过来设置二维码的数据
	 * @param request
	 * @param response
	 */
    public void getContent(HttpServletRequest request, HttpServletResponse response){
    	String name = request.getParameter("name");//获取姓名
    	String sex = request.getParameter("sex");//获取性别
		String telphone = request.getParameter("telphone");//获取电话号码
		String company = request.getParameter("company");//获取公司
		String position = request.getParameter("position");//获取职位
		StringBuffer content = new StringBuffer("Qr");
		content.append("Name").append(name).append("\n");
		content.append("sex").append(sex).append("\n");
		content.append("telphone").append(telphone).append("\n");
		content.append("position").append(position).append("\n");
		content.append("company").append(company).append("\n");
		response.setContentType("image/jpeg");
		String fileName = name + ".jpg"; 
		try {
			 response.setHeader("Content-Disposition", "attachment;"  
		                + " filename="  
		                + new String(fileName.getBytes(), "utf-8")); 
			 OutputStream os = response.getOutputStream();  
			 String root = request.getSession().getServletContext().getRealPath("/");
			/* QRCodeUtil.encode(content.toString(),root+"/logo/logo.png", os,true);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
   /**
    * 
    * 测试方法，请忽视
    */
    public void saveQrImage(){
    	try {
            
    	     String content = "120605181003;sdgfsdgfg";
    	     String path = "F:/windows8Desktop/New";
    	     
    	     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
    	     
    	     Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
    	     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
    	     BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hints);
    	     File file1 = new File(path,"xxx.jpg");
    	     Path pathOther = file1.toPath();
    	     MatrixToImageWriter.writeToPath(bitMatrix, "jpg",pathOther);
    	     
    	 } catch (Exception e) {
    	     e.printStackTrace();
    	 }
    }
}
