package dongzhong.okhttputiltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import dongzhong.okhttputil.OkHttpUtil;
import dongzhong.okhttputil.callback.Callback;
import dongzhong.okhttputiltest.util.FileUtil;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "test";

    private TextView textView;
    private Button getAsyncButton;
    private Button getSyncButton;
    private Button postStringAsyncButton;
    private Button postStringSyncButton;
    private Button postFormAsyncButton;
    private Button postFormSyncButton;
    private Button postFileAsyncButton;
    private Button postFileSyncButton;

    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.textview_mainactivity);
        getAsyncButton = (Button) findViewById(R.id.get_async_button);
        getAsyncButton.setOnClickListener(this);
        getSyncButton = (Button) findViewById(R.id.get_sync_button);
        getSyncButton.setOnClickListener(this);
        postStringAsyncButton = (Button) findViewById(R.id.post_string_async_button);
        postStringAsyncButton.setOnClickListener(this);
        postStringSyncButton = (Button) findViewById(R.id.post_string_sync_button);
        postStringSyncButton.setOnClickListener(this);
        postFormAsyncButton = (Button) findViewById(R.id.post_form_async_button);
        postFormAsyncButton.setOnClickListener(this);
        postFormSyncButton = (Button) findViewById(R.id.post_form_sync_button);
        postFormSyncButton.setOnClickListener(this);
        postFileAsyncButton = (Button) findViewById(R.id.post_file_async_button);
        postFileAsyncButton.setOnClickListener(this);
        postFileSyncButton = (Button) findViewById(R.id.post_file_sync_button);
        postFileSyncButton.setOnClickListener(this);
        cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_async_button:
                OkHttpUtil.getInstance()
                        .get()
                        .url("http://www.baidu.com")
                        .build()
                        .execute(callback);
                break;
            case R.id.get_sync_button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final Response response = OkHttpUtil.getInstance()
                                    .get()
                                    .url("http://www.baidu.com")
                                    .build()
                                    .execute();
                            final String resultString = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(resultString);
                                }
                            });
                        }
                        catch (final IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(e.getMessage());
                                }
                            });
                        }
                    }
                }).start();
                break;
            case R.id.post_string_async_button:
                OkHttpUtil.getInstance()
                        .postString()
                        .url("http://www.baidu.com")
                        .content("测试测试测试")
                        .build()
                        .execute(callback);
                break;
            case R.id.post_string_sync_button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final Response response = OkHttpUtil.getInstance()
                                    .postString()
                                    .url("http://www.baidu.com")
                                    .content("测试测试测试")
                                    .build()
                                    .execute();
                            final String resultString = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(resultString);
                                }
                            });
                        }
                        catch (final IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(e.getMessage());
                                }
                            });
                        }
                    }
                }).start();
                break;
            case R.id.post_form_async_button:
                OkHttpUtil.getInstance()
                        .postForm()
                        .tag(TAG)
                        .url("https://nlp.flwrobot.com/robot/ner/add.do")
                        .addParam("sr_session", "")
                        .addParam("ner_key", "name:location")
                        .addParam("ner_value", "卧室:窗帘")
                        .build()
                        .execute(callback);
                break;
            case R.id.post_form_sync_button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final Response response = OkHttpUtil.getInstance()
                                    .postForm()
                                    .url("https://nlp.flwrobot.com/robot/ner/add.do")
                                    .addParam("sr_session", "")
                                    .addParam("ner_key", "name:location")
                                    .addParam("ner_value", "卧室:窗帘")
                                    .build()
                                    .execute();
                            final String resultString = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(resultString);
                                }
                            });
                        }
                        catch (final IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(e.getMessage());
                                }
                            });
                        }
                    }
                }).start();
                break;
            case R.id.post_file_async_button:
                OkHttpUtil.getInstance().postFile()
                        .url("http://www.baidu.com")
                        .file(FileUtil.uploadFile)
                        .build()
                        .execute(callback);
                break;
            case R.id.post_file_sync_button:
                break;
            case R.id.cancel_button:
                OkHttpUtil.getInstance()
                        .cancelTag(TAG);
                break;
            default:
                break;
        }
    }

    private Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText("请求错误");
                }
            });
        }

        @Override
        public void onResponse(Call call, Response response) {
            String responseString = "";
            try {
                responseString = response.body().string();
            }
            catch (Exception e) {
                responseString = e.getMessage();
            }
            final String result = responseString;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(result);
                }
            });
        }

        @Override
        public void onProgress(float progress, long total, int id) {
            Toast.makeText(MainActivity.this, "Progress: " + progress, Toast.LENGTH_SHORT);
        }
    };
}
