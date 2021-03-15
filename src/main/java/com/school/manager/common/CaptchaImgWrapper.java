package com.school.manager.common;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CaptchaImgWrapper {
	
	private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };
	private static Random random = new Random();
	

	public static BufferedImage generatePic(Integer width, Integer height, String captcha) {
		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D gd = (Graphics2D) buffImg.getGraphics();
		
		int margin = width/10; //字体间隔 5px
		
		int xx = (width -margin) / captcha.length();// 用于定位文字和文字大小的变量(左右留边10)
		int red = 0, green = 0, blue = 0;
		// 创建字体，字体的大小 = （每个字符占大小 - 2*margin）*2 +5 
		int fontSize = (xx - margin) * 2 + 5;
		
		Font font = new Font("Fixedsys", Font.BOLD + Font.ITALIC, fontSize);
		// 设置字体。
		gd.setFont(font);
		// 将图像填充为浅灰色
		gd.setColor(Color.LIGHT_GRAY);
		gd.fillRect(0, 0, width, height);
		/*// 画边框。
		gd.setColor(Color.BLACK);
		gd.drawRect(0, 0, width - 1, height - 1);*/
		// 随机产生codeCount数字的验证码。
		
		for (int i = 0; i < captcha.length(); i++) {
			// 得到随机产生的验证码数字。
			String code = String.valueOf(captcha.toCharArray()[i]);
			// String code="J";
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red = random.nextInt(150);
			green = random.nextInt(150);
			blue = random.nextInt(150);

			// 用随机产生的颜色将验证码绘制到图像中。
			Color color = new Color(red, green, blue);
			gd.setColor(color);
			// shear(gd,(i + 1) * xx, (height/2)+5);
			int jiaodu = random.nextInt(60) - 30;// 角度正负30度
			double hudu = jiaodu * Math.PI / 180;// 弧度
			gd.rotate(hudu, i * xx + margin, (height+fontSize)/2 );//设置弧度偏移量
			gd.drawString(code, i * xx + margin, (height+fontSize)/2);
			gd.rotate(-hudu, i * xx + margin, (height+fontSize)/2);//重置偏移量
		}

		// 随机产生30条干扰线，使图象中的认证码不易被其它程序探测到。
		gd.setColor(Color.BLACK);
		for (int i = 0; i < 30; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}
		return buffImg;
	}
	
	
	/**
	 * 生成一个map集合 code为生成的验证码 codePic为生成的验证码BufferedImage对象
	 * 
	 * @param width
	 *            图片宽
	 * @param height
	 *            图片高
	 * @param codeCount
	 *            验证码个数
	 * @return
	 */
	public static Map<String, Object> generateCodeAndPic(Integer width, Integer height, Integer codeCount) {
		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// Graphics2D gd = buffImg.createGraphics();
		Graphics2D gd = (Graphics2D) buffImg.getGraphics();
		// Graphics gd = buffImg.getGraphics();
		int xx = width / codeCount;// 用于定位文字和文字大小的变量
		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;
		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Fixedsys", Font.BOLD + Font.ITALIC, xx + 5);
		// 设置字体。
		gd.setFont(font);
		// 将图像填充为白色
		gd.setColor(Color.LIGHT_GRAY);
		gd.fillRect(0, 0, width, height);
		/*// 画边框。
		gd.setColor(Color.BLACK);
		gd.drawRect(0, 0, width - 1, height - 1);*/
		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字。
			String code = String.valueOf(codeSequence[random.nextInt(31)]);
			// String code="J";
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red = random.nextInt(150);
			green = random.nextInt(150);
			blue = random.nextInt(150);

			// 用随机产生的颜色将验证码绘制到图像中。
			Color color = new Color(red, green, blue);
			gd.setColor(color);
			// shear(gd,(i + 1) * xx, (height/2)+5);
			int jiaodu = random.nextInt(60) - 40;// 角度正负30度
			double hudu = jiaodu * Math.PI / 180;// 弧度
			if (i == 0) {// 第一个字符
				gd.rotate(hudu, width / codeCount - 10, (height / 2) + 10);
				gd.drawString(code, width / codeCount - 10, (height / 2) + 10);
				gd.rotate(-hudu, width / codeCount - 10, (height / 2) + 10);
			} else {
				gd.rotate(hudu, i * xx, (height / 2) + 10);
				gd.drawString(code, i * xx, (height / 2) + 10);
				gd.rotate(-hudu, i * xx, (height / 2) + 10);
			}
			// 将产生的四个随机数组合在一起。
			randomCode.append(code);
		}

		// 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
		gd.setColor(Color.BLACK);
		for (int i = 0; i < 30; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		// 存放验证码
		map.put("code", randomCode.toString());
		// 存放生成的验证码BufferedImage对象
		map.put("codePic", buffImg);
		return map;
	}
	
	
	/**
	 * 随机生成4位验证码的方法
	 * @param codeCount  
	 * @return
	 */
	public static String generateCaptch(Integer codeCount) {
		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字。
			String code = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
			// 将产生的四个随机数组合在一起。
			randomCode.append(code);
		}
		return randomCode.toString();
	}
	
	
	/**
	 * 验证码扭曲方法 未使用
	 */
	public static void shear(Graphics g, int w1, int h1) {
		shearX(g, w1, h1);
		shearY(g, w1, h1);
	}

	private static void shearX(Graphics g, int w1, int h1) {
		int period = random.nextInt(2);
		boolean borderGap = true;
		int frames = 1;
		int phase = random.nextInt(2);

		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				// g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}

	}

	private static void shearY(Graphics g, int w1, int h1) {
		int period = random.nextInt(40) + 2; // 50;
		boolean borderGap = true;
		int frames = 15;
		int phase = 5;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(i, 0, 1, h1, 0, 10);
			System.out.println((int) d);
			if (borderGap) {
				// g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}

		}

	}
	

}
