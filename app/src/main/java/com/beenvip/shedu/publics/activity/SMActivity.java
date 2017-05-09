package com.beenvip.shedu.publics.activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseActivity;
import com.beenvip.shedu.utils.LalaLog;
import com.bumptech.glide.Glide;

/**
 * Created by ZH on 2017/4/6.
 * 497239511@qq.com
 */

public class SMActivity extends BaseActivity implements View.OnClickListener{
    private ImageView idCard_zm;
    private ImageView idCard_fm;
    private Bitmap bitmap;
    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        idCard_zm.setOnClickListener(this);
        idCard_fm.setOnClickListener(this);
    }

    @Override
    protected boolean isShowToolBar() {
        return true;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_smyz);
        setTitle("实名认证");
        idCard_zm=findView(R.id.idcard_zm);
        idCard_fm=findView(R.id.idcard_fm);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.idcard_zm:
                Intent intent1 =new Intent(Intent.ACTION_PICK,null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                break;
            case R.id.idcard_fm:
                Intent intent2 =new Intent(Intent.ACTION_PICK,null);
                intent2.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent2, 2);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            LalaLog.i("uri",uri.toString());
            ContentResolver cr = this.getContentResolver();
                /* 将Bitmap设定到ImageView */
                if (requestCode==1){
//                    使用Bitmap
//                    try {
//                        if (bitmap!=null) bitmap.recycle();
//                        bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
//
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    idCard_zm.setImageBitmap(bitmap);
                    Glide.with(this).load(uri).into(idCard_zm); //使用uri
                }else if (requestCode==2){
                    Glide.with(this).load(uri).into(idCard_fm);
                }
            }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
