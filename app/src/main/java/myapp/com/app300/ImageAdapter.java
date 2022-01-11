package myapp.com.app300;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ImageAdapter extends ArrayAdapter<Image>
{
    Activity context;
    int id;
    ArrayList<Image> arrayList;

    public ImageAdapter(Activity context, int id, ArrayList<Image> arrayList) {
        super(context, id, arrayList);
        this.context = context;
        this.id = id;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        convertView=layoutInflater.inflate(id,null);

        Image image=arrayList.get(position);

        TextView textView=convertView.findViewById(R.id.txtname);
        ImageView imageView=convertView.findViewById(R.id.imageView);

        textView.setText(image.getName());
        imageView.setImageResource(image.getId());

        return  convertView;
    }
}
