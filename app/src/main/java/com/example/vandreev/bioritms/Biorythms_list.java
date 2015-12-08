package com.example.vandreev.bioritms;


import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Biorythms_list extends Activity {  //  AppCompatActivity  ListActivity Activity
    private static final int CM_DELETE_ID = 1;
    //private int gv_select_index;
    final String ATR_FIO_TEXT = "text";
    final String ATR_DATE_TXT = "date";
    final String ATR_NAME_IMG = "image";
    //myAdapter mAdapter;
    private static final int NOTIFY_ID = 101;
    LinearLayout id_list_change_data_LinearLayout;
    LinearLayout id_buttons_save_LinearLayout;
    LinearLayout id_footer4list_LinearLayout;
    DatePicker   id_fio_DatePicker;
    EditText id_fio;
    ListView lv;
    Button id_del_list_button;
    Button id_change_list;
    ToggleButton id_choose_sex;
    private NotificationManager myNotifyMgr;
    ;

    public Biorythms_list() {
        ArrayList<Map<String, Object>> data;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biorythms_list);
//Bioritms.m;
        id_list_change_data_LinearLayout = (LinearLayout) findViewById(R.id.id_list_change_data);
        id_buttons_save_LinearLayout = (LinearLayout) findViewById(R.id.id_buttons_save);
        id_footer4list_LinearLayout = (LinearLayout) findViewById(R.id.footer4list);
        id_fio = (EditText) findViewById(R.id.id_fio);
        id_fio_DatePicker = (DatePicker) findViewById(R.id.fio_DatePicker) ;

        id_del_list_button = (Button) findViewById(R.id.id_del_list);
        id_change_list = (Button)findViewById(R.id.id_change_list);
        id_choose_sex = (ToggleButton) findViewById(R.id.choose_sex );

        id_list_change_data_LinearLayout.setVisibility(View.INVISIBLE);


        myNotifyMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

         lv = (ListView) findViewById(R.id.list_users_);
        registerForContextMenu(lv);
       // regis
        lv.setAdapter(Bioritms.sAdapter);




        // устанавливаем обработчик нажатия
       lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view,
                                      int position, long id) {
// показываем позиция нажатого элемента
               Bioritms.gv_select_index = position;
               // Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onNothingSelected(AdapterView<?> arg0) {
           }
       });



                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        //  Log.d(LOG_TAG, "itemClick: position = " + position + ", id = " + id);
                        //    Toast.makeText(getApplicationContext(), mAdapter.getString(position), Toast.LENGTH_SHORT).show();
                        Bioritms.gv_select_index = position;
                        Map<String, Object> m;
                        m = new HashMap<String, Object>();
                        m = Bioritms.data.get(position);
                        int img_i = (int) m.get("image");
                        Bioritms.gv_select_index = position;
                        LayoutInflater mInflater = getLayoutInflater();
                        View mLayout = mInflater.inflate(R.layout.toast, null);

                        ImageView image = (ImageView) mLayout.findViewById(R.id.Image);
                        image.setImageResource(img_i);  //mImage[position]

                        TextView sign = (TextView) mLayout.findViewById(R.id.Sign);
                        // sign.setText(mSign[position]);
                        sign.setText(m.get("text").toString());

                        TextView date = (TextView) mLayout.findViewById(R.id.Date);
                        // date.setText(mDate[position]);
                        date.setText((String) m.get("date"));
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_LONG);

                        toast.setView(mLayout);
                        toast.show();


//                // Уведомление в строке состояния
//                CharSequence Title = "Выбран элемент " + mSign[position];
//                int icon = mImage[position];
////
//                Intent notificationIntent = new Intent (this, Biorythms_list.class);
//                PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//
//                RemoteViews mRemoteView = new RemoteViews(getPackageName(), R.layout.notify);
//                mRemoteView.setImageViewResource(R.id.Image, mImage[position]);
//                mRemoteView.setTextViewText(R.id.Sign, mSign[position]);
//                mRemoteView.setTextViewText(R.id.Date, mDate[position]);
//
//                Notification notification = new Notification(icon, Title, System.currentTimeMillis());
//                notification.contentIntent = contentIntent;
//                notification.contentView = mRemoteView;
//
//                myNotifyMgr.notify(NOTIFY_ID, notification);
                    }
                });


        //if (Bioritms.gv_select_index != -1)
        lv.setSelection(Bioritms.gv_select_index);
        Bundle extras = getIntent().getExtras();
        int Select_id = extras.getInt("Select_id");
        lv.setSelection(Select_id);
       // lv.refreshDrawableState();

    }

    public void Click_list(View view) {

        switch (view.getId()) {
            case R.id.id_add_list:
                id_list_change_data_LinearLayout.setVisibility(View.VISIBLE);
                id_footer4list_LinearLayout.setVisibility(View.INVISIBLE);
                break;
            case R.id.id_del_list:
                // удаляем Map из коллекции, используя позицию пункта в списке
                Log.d("Bio", String.valueOf(lv.getSelectedItemPosition()));  // FirstVisiblePosition()
                Log.d("Bio", String.valueOf(lv.getCheckedItemPosition()));
                //  Bioritms.data.remove( Bioritms.gv_select_index );  //lv.getSelectedItemPosition()
               // Log.d("Bio", String.valueOf(lv.getFirstVisiblePosition()));
                // уведомляем, что данные изменились
              //  Bioritms.sAdapter.notifyDataSetChanged();
              //  Bioritms.gv_select_index = -1;
                break;
            case R.id.id_save_inList:
                id_list_change_data_LinearLayout.setVisibility(View.INVISIBLE);
                id_footer4list_LinearLayout.setVisibility(View.VISIBLE);
                // создаем новый Map
                Map<String, Object> m;
                m = new HashMap<String, Object>();
                m.put(ATR_FIO_TEXT, id_fio.getText().toString());

                m.put(ATR_NAME_IMG, (id_choose_sex.isChecked())? R.drawable.girl   :R.drawable.man );
                m.put(ATR_DATE_TXT, DatePicker2String(id_fio_DatePicker));  // "01/01/2011"
                // добавляем его в коллекцию
                Bioritms.data.add(m);
                // уведомляем, что данные изменились
                Bioritms.sAdapter.notifyDataSetChanged();
                break;
            case R.id.id_return:
                id_list_change_data_LinearLayout.setVisibility(View.INVISIBLE);
                id_footer4list_LinearLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.id_change_list:
                // cal.add(cal.DATE, -1);
                break;
        }

        id_del_list_button.setVisibility(Bioritms.data.isEmpty() ? View.INVISIBLE : View.VISIBLE);
        id_change_list.setVisibility(Bioritms.data.isEmpty() ? View.INVISIBLE : View.VISIBLE);

    }

    private   String DatePicker2String(DatePicker id_fio_datePicker) {
        String dd ,mm ,yyyy ;
        dd =  id_fio_DatePicker.getDayOfMonth() < 10 ?  "0" + String.valueOf(id_fio_DatePicker.getDayOfMonth()) :    String.valueOf(id_fio_DatePicker.getDayOfMonth())  ;
        mm =  id_fio_DatePicker.getMonth()+1 < 10    ?  "0" + String.valueOf((id_fio_DatePicker.getMonth()+1))  :    String.valueOf((id_fio_DatePicker.getMonth()+1));
     return   id_fio_DatePicker.getYear() + "." + mm + "." +  dd;
    }


    ////******************
@Override
public void onCreateContextMenu(ContextMenu menu, View v,
                                ContextMenu.ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
}

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {
            // получаем инфу о пункте списка
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo)  item.getMenuInfo();
            // удаляем Map из коллекции, используя позицию пункта в списке
            Bioritms.data.remove(acmi.position);
            // уведомляем, что данные изменились
            Bioritms.sAdapter.notifyDataSetChanged();
            id_del_list_button.setVisibility(Bioritms.data.isEmpty() ? View.INVISIBLE : View.VISIBLE);
            id_change_list.setVisibility(Bioritms.data.isEmpty() ? View.INVISIBLE : View.VISIBLE);
            return true;
        }
        return super.onContextItemSelected(item);
    }

}
