package com.example.administrator.demotest;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hengheng on 2016/9/2.
 * 二维码操作类
 */
public class ZXingUtils {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;

    /**
     * 生成二维码
     * @param content
     * @return
     */
    public static Bitmap encode(String content){
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            //实现二维码的编码
            BitMatrix matrix =
                    writer.encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            //把Matrix转换为一位数组像素
            int[] pixels = new int[WIDTH * HEIGHT];
            //外层控制行，内层控制列
            for(int y = 0;y < HEIGHT;y++){
                for(int x = 0;x < WIDTH;x++){
                    if(matrix.get(x,y)){
                        pixels[y * WIDTH + x] = 0xff000000;
                    }else{
                        pixels[y * WIDTH + x] = 0xffffffff;
                    }
                }
            }
            //生成二维码图片
            Bitmap bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels,0,WIDTH,0,0,WIDTH,HEIGHT);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析二维码图片
     * @param bmp
     * @return
     */
    public static Result decode(Bitmap bmp){
        MultiFormatReader reader = new MultiFormatReader();
        //将Bitmap转为像素数组
        int[] pixels = new int[WIDTH * HEIGHT];
        bmp.getPixels(pixels,0,WIDTH,0,0,WIDTH,HEIGHT);
        //将像素数组转换为BinaryBitmap
        BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(new RGBLuminanceSource(WIDTH, HEIGHT, pixels)));
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
        try {
            //解析图片
            Result result = reader.decode(binaryBitmap, hints);
            return result;
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
