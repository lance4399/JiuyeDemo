package cn.jiuye.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;

public class VerifyCode {
	private int width=70;
	private int height = 30;
	private Random r=new Random();
	private String[] fontNames={"宋体","华文楷体","黑体","微软雅黑","楷体_GB2312"};
	private String codes="23456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRTUVWXYZ";
	private Color bgColor=new Color(255,255,255);//白色背景
	private String text;//用于保存图片的文本
	
	//随机的颜色
	private Color randomColor(){
		int red=r.nextInt(150);//150表示与白色有较明显的区分
		int green=r.nextInt(150);
		int blue=r.nextInt(150);
		return new Color(red,green,blue);
	}
	//生成随机的字体
	private Font randomFont(){
		int index=r.nextInt(fontNames.length);//
		String fontName=fontNames[index];
		int style=r.nextInt(4);//生成随机的样式 ：0(无样式)  1(斜体)  2(粗体)  3(斜体+粗体)  
		int size=r.nextInt(5)+24;//生成随机字号：24-28
		return new Font(fontName,style,size);
	}
	
	//生成随机的一位验证码
	private char randomChar() {
		int index=r.nextInt(codes.length());
		return codes.charAt(index);
	}
	//画出矩形
		private BufferedImage createImage() {
			BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
			Graphics2D g2=(Graphics2D)image.getGraphics();//拿到画笔
			g2.setColor(this.bgColor);
			g2.fillRect(0, 0, width, height);
			return image;
		}
		//画干扰线
		private void drawLine(BufferedImage image) {
			int num =3;
			Graphics2D g2=(Graphics2D)image.getGraphics();
			for(int i =0;i<num;i++){
				int x1=r.nextInt(width);
				int y1=r.nextInt(height);
				int x2=r.nextInt(width);
				int y2=r.nextInt(height);
				g2.setStroke(new BasicStroke(1.5F));
				g2.setColor(Color.BLUE);
				g2.drawLine(x1, y1, x2, y2);
			}
		}
		
		public String getText(){
			return text;
		}	
	//调用这个方法得到验证码
	public BufferedImage getImage(){
		BufferedImage image=createImage();
		Graphics2D g2=(Graphics2D)image.getGraphics();
		StringBuilder sb=new StringBuilder();
		//向图片中画4个字符
		for (int i=0;i<4;i++){
			String s=randomChar()+"";//随机生成一个字符，并加上一个空格
			sb.append(s); //长度
			float x=i*1.0F*width/4;//设置当前字符的x轴坐标
			g2.setFont(randomFont());
			g2.setColor(randomColor());
			g2.drawString(s, x, height-5);
		}
		this.text=sb.toString();//把生成的字符串赋给了this.text
		drawLine(image);//添加干扰线
		return image;
	
	}

		//保存图片到指定的输出流
	public static void output (BufferedImage image,OutputStream out) throws IOException{
		ImageIO.write(image,"JPEG",out);
	}
}
