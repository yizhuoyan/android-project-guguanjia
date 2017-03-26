package com.chinasofti.guguanjia.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.application.GGJAplication;
import com.chinasofti.guguanjia.bean.UserBean;
import com.chinasofti.guguanjia.utils.ImageDownloader;
import com.chinasofti.guguanjia.utils.MPoPuWindow;
import com.chinasofti.guguanjia.utils.MPoPuWindow.onGetTypeClckListener;
import com.chinasofti.guguanjia.utils.UrlConfig;
import com.chinasofti.guguanjia.views.TitleView;

import java.io.File;

public class UpdateHeaderImgActivity extends BaseActivity {
    private TitleView titleView;
    private Button update_header_img_btn;

    private ImageView user_header_img;

    private File file;

    private Uri ImgUri;

    private Type type;

    private MPoPuWindow puWindow;

    public enum Type {
        PHONE, CAMERA
    }

    private  UserBean userBean;
    @Override
    public void loadContentView() {
        Intent intent = getIntent();
        if (intent!=null)
            userBean = (UserBean) intent.getSerializableExtra("userBean");
        setContentView(R.layout.activity_update_header_img);

    }

    @Override
    public void initLayoutView() {
        titleView=(TitleView) findViewById(R.id.updatHeaderImgTitleView);
        titleView.setTitle("修改头像");
        titleView.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.finshActivity(UpdateHeaderImgActivity.this);
            }
        });
        titleView.setRightBtnText("完成");
        titleView.setRightButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.finshActivity(UpdateHeaderImgActivity.this);
            }
        });

        user_header_img=(ImageView) findViewById(R.id.user_header_img);

        update_header_img_btn=(Button) findViewById(R.id.update_header_img_btn);
        update_header_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puWindow = new MPoPuWindow(UpdateHeaderImgActivity.this, UpdateHeaderImgActivity.this);
                puWindow.showPopupWindow(findViewById(R.id.activity_update_header_img));
                puWindow.setOnGetTypeClckListener(new onGetTypeClckListener() {

                    @Override
                    public void getType(Type type) {
                        UpdateHeaderImgActivity.this.type = type;
                    }

                    @Override
                    public void getImgUri(Uri ImgUri, File file) {
                        UpdateHeaderImgActivity.this.ImgUri = ImgUri;
                        UpdateHeaderImgActivity.this.file = file;
                    }

                });
            }
        });
    }

    @Override
    public void loadData() {
        //加载头像
        ImageDownloader imageDownloader = new ImageDownloader(UpdateHeaderImgActivity.this);
        imageDownloader.download(UrlConfig.BASEURL+"/static/uploads/heads/1470929079373.jpg", user_header_img, ImageView.ScaleType.FIT_CENTER);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("requestCode", type + "");
        if (requestCode == 1) {
            if (ImgUri != null) {
                puWindow.onPhoto(ImgUri, 300, 300);
            }
        } else if (requestCode == 2) {
            if (data != null) {
                Uri uri = data.getData();
                puWindow.onPhoto(uri, 300, 300);
            }
        } else if (requestCode == 3) {
            if (type == Type.PHONE) {
                if (data != null) {
                    Bundle extras = data.getExtras();
                    Bitmap bitmap = (Bitmap) extras.get("data");
                    if (bitmap != null) {
                        user_header_img.setImageBitmap(bitmap);
                    }
                }
            } else if (type == Type.CAMERA) {
                user_header_img.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
            }
        }
    }

}
