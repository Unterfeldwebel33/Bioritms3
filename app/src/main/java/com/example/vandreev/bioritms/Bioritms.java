package com.example.vandreev.bioritms;
//import android.graphics.Point;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

//import android.support.v7.app.AppCompatActivity;


public class Bioritms extends AppCompatActivity {   //AppCompatActivity  Activity
    private static final int NOTIFY_ID = 101;
    public static ArrayList<Map<String, Object>> data;
    static SimpleAdapter sAdapter;
    static int gv_select_index = -1;

    // имена атрибутов для Map
    final String ATR_FIO_TEXT = "text";
    final String ATR_DATE_TXT = "date";
    final String ATR_NAME_IMG = "image";
    public int gv_newVal;
    String[] mSign1 = new String[]{"Овен", "Телец"};
    Map<String, Object> m;
    //*************************
    Integer[] mImage = {R.drawable.aries, R.drawable.taurus, R.drawable.gemini, R.drawable.cancer, R.drawable.lion, R.drawable.virgo, R.drawable.libra, R.drawable.scorpio, R.drawable.sagittarius, R.drawable.capricorn, R.drawable.aquarius, R.drawable.pisces};
    String[] mSign = new String[]{"Овен", "Телец", "Близнецы", "Рак", "Лев", "Дева", "Весы", "Скорпион", "Стрелец", "Козерог", "Водолей", "Рыбы"};
    String[] mDate = {"21 марта - 20 апреля", "21 апреля - 20 мая", "21 мая - 21 июня", "22 июня - 22 июля", "23 июля - 23 августа", "24 августа - 23 сентября", "24 сентября - 23 октября", "24 октября - 22 ноября", "23 ноября - 21 декабря", "22 декабря - 20 января", "21 января - 20 февраля", "21 февраля - 20 марта"};
    int DIALOG_DATE = 1;
//**********  Дата
    int myYear = 2015;  // =   cal.get(Calendar.YEAR)  ;//  2015 $// util/Date/getYear() $  // 2011$
    int myMonth = 02;   // =   cal.get(Calendar.MONTH) ;// 02;
    int myDay = 03;     // =   cal.get(Calendar.DAY_OF_MONTH) ;// 03; // Calendar.DAY_OF_MONTH ;//
    Calendar cal;
    Calendar gv_cal_person;
    int gv_days_from_birthday;
    //*************
    Button gv_button_now;
    TextView gv_tV_information;
    TextView gv_id_date_birth;
    ImageView imageView_Source, imageView_Dest;
    //   String[] data1 = {"one", "two", "three", "four", "five"};
    NumberPicker np;
    myAdapter1 mAdapter1;
    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            cal.set(year, monthOfYear, dayOfMonth);
            set_date_variable();
            set_date_text();
        }
    };


    private NotificationManager myNotifyMgr;
    private Bitmap bitmap_Source, bitmap_Dest;

    @Override
    protected void onStart() {
        super.onStart();
        Log("MainActivity: onStart()");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log("MainActivity: onReStart()");
        StartAnimation();
    }

    private void Log(String str) {
        String TAG = "Bio";
        Log.d(TAG, str);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bioritms);  //activity_bioritms
        Log("MainActivity: onCreate()");
//*******************************************************************
        data = new ArrayList<Map<String, Object>>();
        m = new HashMap<String, Object>();
        m.put(ATR_FIO_TEXT, "Андреев Влад ");
        m.put(ATR_NAME_IMG, R.drawable.man);
        m.put(ATR_DATE_TXT, "1976-06-15");
        data.add(m);
        m = new HashMap<String, Object>();
        m.put(ATR_FIO_TEXT, "sometext2 ");
        m.put(ATR_NAME_IMG, R.drawable.girl);
        m.put(ATR_DATE_TXT, "2015-12-12");
        data.add(m);

// массив имен атрибутов, из которых будут читаться данные
        String[] from = {ATR_FIO_TEXT, ATR_NAME_IMG, ATR_DATE_TXT};
// массив ID View-компонентов, в которые будут вставлять данные
        int[] to = {R.id.Sign, R.id.Image, R.id.Date};
//********************************************************************************

        gv_cal_person = Calendar.getInstance();
        cal = Calendar.getInstance();
        gv_cal_person.set(1976, 06 - 1, 15);


        gv_button_now = (Button) findViewById(R.id.b_Now);
        gv_tV_information = (TextView) findViewById(R.id.tV_information_);
        gv_id_date_birth = (TextView) findViewById(R.id.id_date_birth);
        gv_id_date_birth.setText(String.format("%tF", gv_cal_person));  // YYYY-MM-DD

//******************
        set_date_variable();
        set_date_text();
//******************
        imageView_Dest = (ImageView) findViewById(R.id.imageView);

        Display display = getWindowManager().getDefaultDisplay();
        // @SuppressWarnings("deprecation")
        //  int    width = display.getWidth();

        gv_newVal = 7;
        imageView_Dest.setImageBitmap(processingBitmap2(gv_newVal));

//        Point size = new Point();
//        Display display1 = getWindowManager().getDefaultDisplay();
//        display1.getRealSize(size);


//*******************************************
        np = (NumberPicker) findViewById(R.id.numberPicker);
        // np.setLayoutParams(params);
        np.setMinValue(5);
        np.setMaxValue(30);
        np.setWrapSelectorWheel(true);
        np.setValue(7);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // TODO Auto-generated method stub
                gv_newVal = newVal;
                imageView_Dest.setImageBitmap(processingBitmap2(gv_newVal));
            }
        });
//***************************************************

// адаптер
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // создаем адаптер
        sAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        spinner.setAdapter(sAdapter);

        // registerForContextMenu(imageView_Dest);
// заголовок
        spinner.setPrompt("Person Name");
// выделяем элемент
       // (gv_select_index >=0 )  ?gv_select_index =gv_select_index    gv_select_index = -1;
        if (!data.isEmpty()) {
          //  gv_select_index = 0;
            spinner.setSelection(gv_select_index,true);
        }

//********************************************
        StartAnimation();
//****************************************

// устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
// показываем позиция нажатого элемента
                gv_select_index = position;

                Map<String, Object> m;
                m = new HashMap<String, Object>();
                m = Bioritms.data.get(position);
                String str = (String) m.get("date");

                gv_cal_person.set(Integer.parseInt(str.substring(0,4)), Integer.parseInt(str.substring(5,7)) - 1, Integer.parseInt(str.substring(8,10)) );
                set_date_variable();
                set_date_text();
                imageView_Dest.setImageBitmap(processingBitmap2(gv_newVal));
               // Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


    }

    private void StartAnimation() {
        Animation anim = null;
        anim = AnimationUtils.loadAnimation(this, R.anim.myalpha);
// запускаем анимацию для компонента tv
        //ImageView.startAnimation(anim);
        findViewById(R.id.b_day_minus).startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        findViewById(R.id.imageView).startAnimation(anim);
    }

    private int get_days_from_birthday_(Calendar cal_, Calendar gv_cal_person_) {
        long difference = cal_.getTime().getTime() - gv_cal_person_.getTime().getTime();
        return (int) (difference / (long) (24 * 60 * 60 * 1000));

        // return 0;
    }

    private void set_date_text() {
        gv_button_now.setText(myDay + "." + (myMonth + 1) + "." + myYear);
        //  gv_button_now.setText(String.format("%tF", cal));
        gv_button_now.refreshDrawableState();
    }

    private void set_date_variable() {
        myYear = cal.get(cal.YEAR);          // 2015 $// util/Date/getYear() $  // 2011$
        myMonth = Integer.parseInt(String.format("%tm", cal)) - 1;  //cal.get(cal.MONTH );        // 02;
        myDay = cal.get(cal.DAY_OF_MONTH);  // 03;   // Calendar.DAY_OF_MONTH ;//
        gv_days_from_birthday = get_days_from_birthday_(cal, gv_cal_person);
        gv_tV_information.setText("Прошло " + gv_days_from_birthday + "Дней");
    }


    ///////////////////////////////
    //***************   Меню

    private Bitmap processingBitmap2(int day_pluss_) {
//        Bitmap dest = Bitmap.createBitmap(
//                src.getWidth(), src.getHeight(), src.getConfig());

        Point size = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        //  @SuppressWarnings("deprecation");


        display.getRealSize(size);
        int width = display.getWidth();
        //  @SuppressWarnings("deprecation")
        // int  width  = src.getWidth();
        int height = size.y;// display.getHeight() 100 ; // src.getHeight();
        Bitmap dest;
        dest = Bitmap.createBitmap(width, (int) ((int) height / 2.5f), Bitmap.Config.RGB_565);

        Paint paint = new Paint();
        //     RadialGradient shader = new RadialGradient((width - radius), 0, fullRadius, colors, positions, Shader.TileMode.CLAMP);
        //     paint.setShader(shader);

        Canvas canvas = new Canvas(dest);
        canvas.drawColor(0xffffff00); // Желтій
        //canvas.drawRect((float) 0, (float) 0, (float) width/2  , (float) height / 2.5f, paint);
        //canvas.drawBitmap(src, 0, 0, paint );  //null
        // canvas.drawRect(0, 0, width, height, paint);


//        physical: \sin(2\pi t/23),
//        emotional: \sin(2\pi t/28),
//        intellectual: \sin(2\pi t/33),
        paint.setColor(0xff110000);
        paint.setStrokeWidth(1);
        Biorythms_draw Biorythms_draw = new Biorythms_draw();
        Biorythms_draw.set_default(height, width, day_pluss_, gv_days_from_birthday, canvas, paint);


        Biorythms_draw.DrawCalendar(myYear, myMonth, myDay);

        paint.setStrokeWidth(3);
        float lv_bio_day = 23.0f;       // physical:
        Biorythms_draw.DrawBioLine(lv_bio_day, 0xffFF00FF);
        lv_bio_day = 33.0f; //intellectual
        Biorythms_draw.DrawBioLine(lv_bio_day, Color.BLUE);
        lv_bio_day = 28.0f; // emotional:
        Biorythms_draw.DrawBioLine(lv_bio_day, Color.GREEN);

        return dest;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*
        Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bioritms, menu);
        return true;
        menu.add("menu1");
        У этого метода 4 параметра на вход:
        - groupId - идентификатор группы, частью которой является пункт меню
        - itemId - ID пункта меню
        - order - для задания последовательности показа пунктов меню
        - title - текст, который будет отображен
        */

        menu.add(0, 1, 0, "add");
        menu.add(0, 2, 0, "edit");
        menu.add(0, 3, 3, "delete");
        menu.add(1, 4, 1, "copy");
        menu.add(1, 5, 2, "paste");
        menu.add(1, 6, 4, "exit");
        return super.onCreateOptionsMenu(menu);

    }

    // обновление меню
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
// TODO Auto-generated method stub
// пункты меню с ID группы = 1 видны, если в CheckBox стоит галка
        //   menu.setGroupVisible(1, chb.isChecked());
        return super.onPrepareOptionsMenu(menu);
    }

//******************

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

    public void click_2(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.b_Now:
                // showDialog(DIALOG_DATE);


                // final int REQUEST_CODE_COLOR = 1;
                final int REQUEST_CODE_ALIGN = 2;
                intent = new Intent(this, Biorythms_list.class);
                intent.putExtra("Select_id", 1);                //intent.putExtra("fname", (Parcelable) m);
                //   intent.putExtra("fname2", 1);
                startActivityForResult(intent, REQUEST_CODE_ALIGN);

                // startActivity(intent);
                /// mSign.
                break;
            case R.id.b_day_minus:
                cal.add(cal.DATE, -1);
                break;
            case R.id.b_day_plus:


//                // Уведомление в строке состояния
//                CharSequence Title = "Выбран элемент " ;//+ mSign[position];
//                int icon =    mImage[1];
//               Intent notificationIntent = new Intent(this, Bioritms.class);
//                PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//                RemoteViews mRemoteView = new RemoteViews(getPackageName(), R.layout.notify);
//                mRemoteView.setImageViewResource(R.id.Image, R.drawable.test_sm);
//                mRemoteView.setTextViewText(R.id.Sign, mSign[1]);
//                mRemoteView.setTextViewText(R.id.Date, mDate[1]);
//                Notification notification = null;
//                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
//                }
//               else{
//
//             Notification.Builder builder = new Notification.Builder(getApplicationContext())
//                                             .setContentIntent(contentIntent)
//                                             .setSmallIcon(R.mipmap.ic_launcher)
//                                             .setContentTitle(Title)
//                                             .setContent(mRemoteView);
//
//             notification = builder.build();
//                }
//                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                mNotificationManager.notify(NOTIFY_ID, notification);


//*************************************
//                Intent intent;
//                // final int REQUEST_CODE_COLOR = 1;
//                final int REQUEST_CODE_ALIGN = 2;
//                intent = new Intent(this, Biorythms_list.class);
//                startActivityForResult(intent, REQUEST_CODE_ALIGN);
//                startActivity(intent);
//*********************************************
                cal.add(cal.DATE, +1);


                break;

        }


        set_date_variable();
        set_date_text();
        // gv_days_from_birthday = get_days_from_birthday_(cal, gv_cal_person);
        // gv_tV_information.setText("Прошло " + gv_days_from_birthday + "Дней");
        imageView_Dest.setImageBitmap(processingBitmap2(gv_newVal));

    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    //    @SuppressWarnings("deprecation")
//    @SuppressLint("NewApi")
//    private void sendNotification(String message) {
//
//    }
    public class myAdapter1 extends BaseAdapter {
        private LayoutInflater mLayoutInflater;

        public myAdapter1(Context ctx) {
            mLayoutInflater = LayoutInflater.from(ctx);
        }

        public int getCount() {
            return mSign1.length;
            //return m.size()-1;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public String getString(int position) {
            return mSign[position] + " (" + mDate[position] + ")";
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = mLayoutInflater.inflate(R.layout.item, null);

            TextView sign = (TextView) convertView.findViewById(R.id.Sign);
            sign.setText(mSign[position]);
            // sign.setText(m.
            TextView date = (TextView) convertView.findViewById(R.id.Date);
            date.setText(mDate[position]);
            // date.setText("q");
//
//
            ImageView image = (ImageView) convertView.findViewById(R.id.Image);
            image.setImageResource(mImage[position]);
            // image.setImageResource(mImage[1]);

            return convertView;
        }


    }


}

