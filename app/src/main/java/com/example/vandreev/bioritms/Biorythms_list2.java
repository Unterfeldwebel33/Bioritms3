package com.example.vandreev.bioritms;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by v.andreev on 12.11.2015.
 */
//public class Biorythms_list2 {
//}


//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


public class Biorythms_list2 extends ListActivity {  //AppCompatActivity  ListActivity
    String[] mSign = new String[]{"Овен", "Телец", "Близнецы", "Рак", "Лев", "Дева", "Весы", "Скорпион", "Стрелец", "Козерог", "Водолей", "Рыбы"};
    String[] mDate = new String[]{"21 марта - 20 апреля", "21 апреля - 20 мая", "21 мая - 21 июня", "22 июня - 22 июля", "23 июля - 23 августа", "24 августа - 23 сентября", "24 сентября - 23 октября", "24 октября - 22 ноября", "23 ноября - 21 декабря", "22 декабря - 20 января", "21 января - 20 февраля", "21 февраля - 20 марта"};
 //   Integer[] mImage = new Integer[]{Integer.valueOf(2130837505), Integer.valueOf(2130837516), Integer.valueOf(2130837509), Integer.valueOf(2130837507), Integer.valueOf(2130837512), Integer.valueOf(2130837517), Integer.valueOf(2130837511), Integer.valueOf(2130837515), Integer.valueOf(2130837514), Integer.valueOf(2130837508), Integer.valueOf(2130837504), Integer.valueOf(2130837513)};
    Integer[] mImage  = {R.drawable.aries, R.drawable.taurus, R.drawable.gemini, R.drawable.cancer, R.drawable.lion, R.drawable.virgo, R.drawable.libra, R.drawable.scorpio, R.drawable.sagittarius, R.drawable.capricorn, R.drawable.aquarius, R.drawable.pisces};

    String[] mHoro = new String[]{"aries", "taurus", "gemini", "cancer", "leo", "virgo", "libra", "scorpio", "sagittarius", "capricorn", "aquarius", "pisces"};
    myAdapter mAdapter;
    private static final int NOTIFY_ID = 101;
    private NotificationManager myNotifyMgr;

    public Biorythms_list2() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // this.setContentView(2130903042);
        setContentView(R.layout.activity_biorythms_list);
        mAdapter = new  myAdapter(this);
        setListAdapter(mAdapter);
        myNotifyMgr = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        // Всплывающее уведомление
        LayoutInflater mInflater = getLayoutInflater();
        View mLayout = mInflater.inflate(R.layout.toast, null);

        ImageView image = (ImageView)mLayout.findViewById(R.id.Image);
        image.setImageResource(mImage[position]);

        TextView sign = (TextView)mLayout.findViewById(R.id.Sign);
        sign.setText(mSign[position]);

        TextView date = (TextView)mLayout.findViewById(R.id.Date);
        date.setText(mDate[position]);

        Toast toast = new Toast (getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);

        toast.setView(mLayout);
        toast.show();


        // Уведомление в строке состояния
        CharSequence Title = "Выбран элемент " + mSign[position];
        int icon = mImage[position];

        Intent notificationIntent = new Intent (this, Biorythms_list2.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        RemoteViews mRemoteView = new RemoteViews(getPackageName(), R.layout.notify);
        mRemoteView.setImageViewResource(R.id.Image, mImage[position]);
        mRemoteView.setTextViewText(R.id.Sign, mSign[position]);
        mRemoteView.setTextViewText(R.id.Date, mDate[position]);

        Notification notification = new Notification(icon, Title, System.currentTimeMillis());
        notification.contentIntent = contentIntent;
        notification.contentView = mRemoteView;

        myNotifyMgr.notify(NOTIFY_ID, notification);
    }

    public class myAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;

        public myAdapter (Context ctx) {
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

            ImageView image = (ImageView)convertView.findViewById(R.id.Image);
            image.setImageResource(mImage[position]);

            TextView sign = (TextView)convertView.findViewById(R.id.Sign);
            sign.setText(mSign[position]);

            TextView date = (TextView)convertView.findViewById(R.id.Date);
            date.setText(mDate[position]);
            return convertView;
        }
    }
}
