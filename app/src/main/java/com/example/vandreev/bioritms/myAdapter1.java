package com.example.vandreev.bioritms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by v.andreev on 12.11.2015.
 */

public class myAdapter1 extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private final String[] mSign;
    private final String[] mDate;
    private final Integer[] mImage;
//    public
//    String[] mSign = new String[]{"Овен", "Телец", "Близнецы", "Рак", "Лев", "Дева", "Весы", "Скорпион", "Стрелец", "Козерог", "Водолей", "Рыбы"};
//    String[] mDate = {"21 марта - 20 апреля", "21 апреля - 20 мая", "21 мая - 21 июня", "22 июня - 22 июля", "23 июля - 23 августа", "24 августа - 23 сентября", "24 сентября - 23 октября", "24 октября - 22 ноября", "23 ноября - 21 декабря", "22 декабря - 20 января", "21 января - 20 февраля", "21 февраля - 20 марта"};
//    Integer[] mImage = {R.drawable.aries, R.drawable.taurus, R.drawable.gemini, R.drawable.cancer, R.drawable.lion, R.drawable.virgo, R.drawable.libra, R.drawable.scorpio, R.drawable.sagittarius, R.drawable.capricorn, R.drawable.aquarius, R.drawable.pisces};


    public myAdapter1(Context ctx, String[] mSign, String[] mDate, Integer[] mImage) {
        this.mSign = mSign;
        this.mDate = mDate;
        this.mImage = mImage;
        mLayoutInflater = LayoutInflater.from(ctx);
    }

    public int getCount () {
        return mSign.length;
    }

    public Object getItem (int position) {
        return position;
    }

    public long getItemId (int position) {
        return position;
    }

    public String getString (int position) {
        return mSign[position] + " (" + mDate[position] + ")";
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = mLayoutInflater.inflate(R.layout.item, null);

        TextView sign = (TextView)convertView.findViewById(R.id.Sign);
        sign.setText(mSign[position]);

        TextView date = (TextView)convertView.findViewById(R.id.Date);
        date.setText(mDate[position]);


        ImageView image = (ImageView)convertView.findViewById(R.id.Image);
        image.setImageResource(mImage[position]);

        return convertView;
    }


//    public void onListItemClick (ListView parent, View v, int position, long id) {
//        Toast.makeText(this.getApplicationContext(), mSign[position], Toast.LENGTH_SHORT).show();
//
//    }

}

