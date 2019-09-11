package cn.ifhu.dongjia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        new Handler().postDelayed(() -> {
//            if (MchInfoLogic.isLogin()) {
//                startActivity(new Intent(LaunchActivity.this,MainActivity.class));
//            } else {
//                startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
//            }
            LaunchActivity.this.finish();
        },1000);

//        boolean isRingMost = IrReference.getInstance().getBoolean(Constants.RINGMOST, false);
//        if (isRingMost){
//            AudioUtil.getInstance(LaunchActivity.this).setMediaVolume(100);
//        }
    }

}
