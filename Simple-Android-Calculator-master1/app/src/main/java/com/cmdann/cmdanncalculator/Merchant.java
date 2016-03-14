package com.cmdann.cmdanncalculator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import net.glxn.qrgen.javase.QRCode;

public class Merchant extends Activity {

    private TextView qrStringView;
    private String qrString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        qrStringView = (TextView) findViewById(R.id.qrString);

        Intent intent = getIntent();
        qrString = intent.getStringExtra("qrString");

        qrStringView.setText(qrString);
        generateQRImage(qrString);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_qr, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void generateQRImage(String qrString) {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = null;

        try {
            bitMatrix = writer.encode(qrString, BarcodeFormat.QR_CODE, 900, 900);

        } catch (WriterException e) {
            e.printStackTrace();
        }

        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);

            }
        }

        ImageView qr_image = (ImageView) findViewById(R.id.imageView2);
        qr_image.setImageBitmap(bmp);



    }
}
