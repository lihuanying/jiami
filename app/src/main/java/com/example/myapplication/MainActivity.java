package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.enc.SM2Utils;
import com.example.enc.SM3Util;


public class MainActivity extends AppCompatActivity {

    private EditText mInputET;

    private Button mSM3Btn;
    private Button mSM2Btn;
    private Button mSM2EncodingBtn;
    private TextView mSM3TV;
    private TextView mSM2TV;
    private TextView mSM2EncodingTV;

    private String mContent;

    String privateKey = "cKwtbFCaURkAoCREdYOIKzngWwybP8er4gYz234gOqY=";
    String publicKey = "BNa6GBPM3SPpYXze00+OcoltK08XbeWifpzF1sEbpGR00P3ae5rCw7fWXNCmW6FHVCgciDHoJybVKxPmCJLcqKg=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSM3Btn = findViewById(R.id.sm3_bt);
        mSM3TV = findViewById(R.id.sm3_tv);

        mSM2Btn = findViewById(R.id.sm2_bt);
        mSM2EncodingBtn = findViewById(R.id.sm2_encoding_bt);
        mSM2TV = findViewById(R.id.sm2_tv);
        mSM2EncodingTV = findViewById(R.id.sm2_encoding_tv);


        mInputET = findViewById(R.id.input_et);

        mSM3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mInputET.getText().toString();
                if (!s.isEmpty()) {

                    mSM3TV.setText("SM3加密结果 : " + SM3Util.getSM3(s));
                }
            }

        });


        mSM2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mInputET.getText().toString();
                if (!s.isEmpty()) {
                    mContent = SM2Utils.getEncryption(s, privateKey, publicKey);
                    mSM2TV.setText("SM2加密结果 : " + mContent);
                }

            }
        });

        mSM2EncodingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                LogUtil.e("content : ------ " + content);
                mSM2EncodingTV.setText("SM2解密结果 : " + SM2Utils.getDecrypt(mContent, privateKey, publicKey));

            }
        });
    }


//
//    public void getSM2(){
////        testSM2Provider();
//        testSM2Creator();
//    }
//
//    /**
//     * SM2，秘钥对，是原始文档中提供的
//     * 当，SM2中的，ecc_param，为测试参数时，能够校验成功
//     * 当，SM2中的，ecc_param，为正式参数时，会校验失败【因为，密钥对是错误的】
//     */
//    public void testSM2Provider() {
//        String privateKey = "128B2FA8BD433C6C068C8D803DFF79792A519A55171B1B650C23661D15897263";
//        byte[] privateBytes = HexUtils.decodeHex(privateKey.toCharArray());
//        String publicKey = "040AE4C7798AA0F119471BEE11825BE46202BB79E2A5844495E97C04FF4DF2548A7C0240F88F1CD4E16352A73C17B7F16F07353E53A176D684A9FE0C6BB798E857";
//        byte[] publicBytes = HexUtils.decodeHex(publicKey.toCharArray());
//
//        String source = "qwe";
//        String userId = "";
//
//        testSM2(privateBytes, publicBytes, userId, source);
//    }
//
//
//    /**
//     * SM2，秘钥对，是随机生成的。
//     * 当，SM2中的，ecc_param，为测试参数或正式参数，都能够校验成功
//     */
//    public void testSM2Creator() {
//        SM2Impl.SM2KeyPair keyPair = SM2Utils.createKeyPair();
////        String privateKey = SM2Utils.getPrivateKey(keyPair);
////        if (null == privateKey) {
////            LogUtil.e("keyPair = " +keyPair + ", privateKey = " + privateKey);
////            return;
////        }
//
//        String privateKey = "cKwtbFCaURkAoCREdYOIKzngWwybP8er4gYz234gOqY=";
//        byte[] privateBytes = Base64.decode(privateKey, Base64.NO_WRAP);
//
////        String publicKey = SM2Utils.getPublicKey(keyPair);
//        String publicKey = "BNa6GBPM3SPpYXze00+OcoltK08XbeWifpzF1sEbpGR00P3ae5rCw7fWXNCmW6FHVCgciDHoJybVKxPmCJLcqKg=";
//        byte[] publicBytes = Base64.decode(publicKey, Base64.NO_WRAP);
//
////        LogUtil.v("-------------------------------------------------------");
////        LogUtil.e("privateKey = " + privateKey);
////        LogUtil.e("publicKey = " + publicKey);
//
//        String source = "qwe";
//        String userId = "";
//        testSM2(privateBytes, publicBytes, userId, source);
//
//
//
//    }
//
//    /**
//     * 测试，加密、解密、签名、验签过程
//     */
//    private void testSM2(byte[] privateBytes, byte[] publicBytes, String userId, String source) {
//        try {
//            byte[] cipherBytes = SM2Utils.encrypt(publicBytes, source.getBytes());
////            LogUtil.e("加密：" + Base64.encodeToString(cipherBytes, Base64.NO_WRAP));
//
//            textView1.setText("SM2加密结果 : "+Base64.encodeToString(cipherBytes, Base64.NO_WRAP));
//
//            byte[] plainBytes = SM2Utils.decrypt(privateBytes, cipherBytes);
//            String plainText = null == plainBytes ? null : new String(plainBytes);
////            LogUtil.e("解密：" + plainText + ", result = " + source.equals(plainText));
//
//            textView2.setText("SM2解密结果 : "+plainText + ", result = " + source.equals(plainText));
//
////            byte[] signBytes = SM2Utils.sign(userId.getBytes(), privateBytes, source.getBytes());
////            LogUtil.e("签名：" + Base64.encodeToString(signBytes, Base64.NO_WRAP));
////
////            textView3.setText("SM2签名 : "+Base64.encodeToString(signBytes, Base64.NO_WRAP));
////
////
////            boolean signResult = SM2Utils.verifySign(userId.getBytes(), publicBytes, source.getBytes(), signBytes);
////            LogUtil.e("验签：" + signResult);
////
////            textView4.setText("SM2验签 : "+signResult);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
