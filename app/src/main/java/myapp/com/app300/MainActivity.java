package myapp.com.app300;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView gridView;
    ImageAdapter imageAdapter;
    ArrayList<Image> images;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start();


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Just wait a minute!", Toast.LENGTH_SHORT).show();
                Bitmap bmap= BitmapFactory.decodeResource(getResources(),images.get(position).getId());
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

                try {
                    wallpaperManager.setBitmap(bmap);
                    Toast.makeText(MainActivity.this, "Wallpaper Changed Successfully", Toast.LENGTH_LONG).show();
                }

                catch (IOException e) {

                    Toast.makeText(MainActivity.this, "An Error Occured", Toast.LENGTH_LONG).show();
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery,100);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100){
            Uri imageUri= data.getData();


            Bitmap bmap = null;
            try {
                bmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }


            WallpaperManager  wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
            try {
                wallpaperManager.setBitmap(bmap);
                Toast.makeText(this, "Wallpaper Changed Successfully", Toast.LENGTH_SHORT).show();
            }

            catch (IOException e) {

                Toast.makeText(this, "An Error Occured", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void start() {
        gridView=findViewById(R.id.gd);
        images= new ArrayList<>();

        Image a=new Image("WALLPAPER 1",R.drawable.a);
        Image b=new Image("WALLPAPER 2",R.drawable.b);
        Image c=new Image("WALLPAPER 3",R.drawable.c);
        Image d=new Image("WALLPAPER 4",R.drawable.d);
        Image e=new Image("WALLPAPER 5",R.drawable.e);
        Image g=new Image("WALLPAPER 6",R.drawable.g);

        images.add(a);
        images.add(b);
        images.add(c);
        images.add(d);
        images.add(e);
        images.add(g);
        button=findViewById(R.id.btntv);

        imageAdapter=new ImageAdapter(this,R.layout.layout_image,images);
        gridView.setAdapter(imageAdapter);
    }
}