package com.example.vandreev.bioritms;

/**
 * Created by v.andreev on 12.11.2015.
 */


        import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public MySimpleArrayAdapter(Context context, String[] values) {
        super(context, R.layout.activity_biorythms_list, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_biorythms_list, parent, false);
     //    TextView textView = (TextView) rowView.findViewById(R.id.label);
    //    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
       // textView.setText(values[position]);
        // Изменение иконки для Windows и iPhone
        String s = values[position];
//        if (s.startsWith("Windows7") || s.startsWith("iPhone")
//                || s.startsWith("Solaris")) {
//            imageView.setImageResource(R.drawable.no);
//        } else {
//            imageView.setImageResource(R.drawable.ok);
//        }

        return rowView;
    }
}
