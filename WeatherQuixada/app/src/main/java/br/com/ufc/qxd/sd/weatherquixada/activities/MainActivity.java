package br.com.ufc.qxd.sd.weatherquixada.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;

import br.com.ufc.qxd.sd.weatherquixada.R;
import br.com.ufc.qxd.sd.weatherquixada.entities.Currently;
import de.timroes.axmlrpc.XMLRPCCallback;
import de.timroes.axmlrpc.XMLRPCClient;
import de.timroes.axmlrpc.XMLRPCException;
import de.timroes.axmlrpc.XMLRPCServerException;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLinearLayoutContent;
    private TextView mTextViewCloud;
    private TextView mTextViewCo;
    private TextView mTextViewHumidity;
    private TextView mTextViewPreciptation;
    private TextView mTextViewPressure;
    private TextView mTextViewWindSpeed;

    private TextView mTextViewSummary;
    private TextView mTextViewTemperature;

    private Currently mCurrently;
    private ProgressDialog mProgressDialog;
    private AlertDialog.Builder mBuilder;

    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        loadData();
    }

    private void initComponents(){
        mLinearLayoutContent = (LinearLayout) findViewById(R.id.lnl_content);
        mTextViewTemperature = (TextView) findViewById(R.id.txt_temperature);
        mTextViewSummary = (TextView) findViewById(R.id.txt_summary);
        mTextViewCloud = (TextView) findViewById(R.id.txt_cloud_cover);
        mTextViewCo = (TextView) findViewById(R.id.txt_ozone);
        mTextViewHumidity = (TextView) findViewById(R.id.txt_humidity);
        mTextViewPreciptation = (TextView) findViewById(R.id.txt_precip);
        mTextViewPressure = (TextView) findViewById(R.id.txt_pressure);
        mTextViewWindSpeed = (TextView) findViewById(R.id.txt_wind_speed);
    }

    private void loadData(){
        createDialog();
        XMLRPCCallback listener = new XMLRPCCallback() {
            public void onResponse(long id, Object result) {
                Gson gson = new Gson();
                Log.d("Currently", String.valueOf(result));
                mCurrently = gson.fromJson(String.valueOf(result), Currently.class);
                refreshViews();
                mProgressDialog.dismiss();
            }
            public void onError(long id, XMLRPCException error) {
                mProgressDialog.dismiss();
                createAlertDialog("Falha de Rede");
                error.printStackTrace();
            }
            public void onServerError(long id, XMLRPCServerException error) {
                mProgressDialog.dismiss();
                createAlertDialog("Falha de Rede");
                error.printStackTrace();
            }
        };
        XMLRPCClient client = null;
        try {
            client = new XMLRPCClient(new URL("http://192.168.0.102:8000"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        long id = client.callAsync(listener, "currently");
    }

    public void refreshViews(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
                if (myBitmap != null && !myBitmap.isRecycled()) {
                    Palette palette = Palette.from(myBitmap).generate();
                    mLinearLayoutContent.setBackgroundColor(palette.getMutedColor(0x000000));
                }
                mTextViewTemperature.setText(String.valueOf(mCurrently.getTemperature()));
                mTextViewSummary.setText(mCurrently.getSummary());
                mTextViewCloud.setText(String.valueOf(mCurrently.getCloudCover() * 100));
                mTextViewCo.setText(String.valueOf(mCurrently.getOzone()));
                mTextViewHumidity.setText(String.valueOf(mCurrently.getHumidity() * 100));
                mTextViewPreciptation.setText(String.valueOf(mCurrently.getPrecipProbability() * 100));
                mTextViewPressure.setText(String.valueOf(mCurrently.getPressure()));
                mTextViewWindSpeed.setText(String.valueOf(mCurrently.getWindSpeed()));
            }
        });
    }

    public void createDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("WeatherQuixadá");
        mProgressDialog.setMessage("Recebendo Dados...");
        mProgressDialog.show();
    }


    public void createAlertDialog(final String message){
        final Activity activity = this;
        handler.post(new Runnable() {
            @Override
            public void run() {
                mBuilder = new AlertDialog.Builder(activity);
                mBuilder.setMessage(message)
                        .setPositiveButton("OK", null)
                        .show();
            }
        });
    }

}
