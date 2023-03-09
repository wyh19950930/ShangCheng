package com.jymj.zhglxt.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.jymj.zhglxt.api.ApiConstants;
import com.jymj.zhglxt.ui.activity.FirstActivity;
import com.setsuna.common.commonutils.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/12/28 15:47
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private String code;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //对象传递给 IWXAPI 接口的 handleIntent 方法
//        ApiConstant.wx_api.handleIntent(getIntent(), this);
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                Log.i("WXTest", "onResp OK");
                if (resp instanceof SendAuth.Resp) {
                    SendAuth.Resp newResp = (SendAuth.Resp) resp;
                    //获取微信传回的code
                    code = newResp.code;
                    //！！！到这我们就获得code值了，可以进行下面自己服务器的操作了
                    //拿到用户信息并保存
                    getAccessToken(code);
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Log.i("WXTest", "onResp ERR_USER_CANCEL ");
                //发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Log.i("WXTest", "onResp ERR_AUTH_DENIED");
                //发送被拒绝
                break;
            default:
                Log.i("WXTest", "onResp default errCode " + resp.errCode);
                //发送返回
                break;
        }
        finish();
    }

    private void getAccessToken(String code) {

/**
 *        access_token:接口调用凭证
 *        appid：应用唯一标识，在微信开放平台提交应用审核通过后获得。
 *        secret：应用密钥AppSecret，在微信开放平台提交应用审核通过后获得。
 *        code：填写第一步获取的code参数。
 *        grant_type：填authorization_code。
 */
        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token")
                .append("?appid=")
                .append(ApiConstants.WXAppID)
                .append("&secret=")
                .append(ApiConstants.WXAppSecret)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(loginUrl.toString())
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure1", call.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseInfo = response.body().string();
                String access = null;
                String openid = null;
                //用json去解析返回来的access和token值
                try {
                    JSONObject jsonObject = new JSONObject(responseInfo);
                    access = jsonObject.getString("access_token");
                    openid = jsonObject.getString("openid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getUserInfo(access, openid);
            }
        });
    }

    private void getUserInfo(String access, String openid) {
        String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access + "&openid=" + openid;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(getUserInfoUrl)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure2", call.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseInfo = response.body().string();
                Log.e("onResponse2", responseInfo);
                //用SharedPreference来缓存字符串
                SharedPreferences.Editor editor = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
                editor.putString("responseInfo", responseInfo);
                editor.commit();
                ToastUtils.showShort("登陆成功");
                Intent intent = new Intent(WXEntryActivity.this, FirstActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
