package dongzhong.okhttputiltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import dongzhong.okhttputil.OkHttpUtil;
import dongzhong.okhttputil.callback.Callback;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private Button getAsyncButton;
    private Button getSyncButton;

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_async_button:
                OkHttpUtil.getInstance()
                        .get()
                        .url("http://www.baidu.com")
                        .build()
                        .execute(new Callback() {
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
                            public void onResponse(Call call, final Response response) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            textView.setText(response.body().string());
                                        }
                                        catch (IOException e) {
                                            textView.setText(e.getMessage());
                                        }
                                    }
                                });
                            }
                        });
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
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        textView.setText(response.body().string());
                                    }
                                    catch (IOException e) {
                                        textView.setText(e.getMessage());
                                    }
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
            default:
                break;
        }
    }
}
