package com.example.administrator.demotest;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class TakePhoto extends AppCompatActivity {
    private ImageView mImage;
    private Bitmap bitmap;
    private String photoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        mImage= (ImageView) findViewById(R.id.image);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.take_photo_btn:
                Intent intents = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intents,1000);
                break;
            case R.id.choose_photo_btn:
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,1100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
            Bundle bundle=data.getExtras();
            bitmap = (Bitmap)bundle.get("data");
            mImage.setImageBitmap(bitmap);
        }else if(requestCode==1100){
            //data中自带有返回的uri
            Uri uri = data.getData();
            //获取照片路径
            String[] filePathColumn={MediaStore.Audio.Media.DATA};
            Cursor cursor=getContentResolver().query(uri,filePathColumn,null,null,null);
            cursor.moveToFirst();
            photoPath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            cursor.close();
            //有了照片路径，之后就是压缩图片，和之前没有什么区别
            bitmap = BitmapFactory.decodeFile(photoPath);
            mImage.setImageBitmap(bitmap);
        }
    }
}
