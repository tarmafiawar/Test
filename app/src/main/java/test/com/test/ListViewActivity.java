package test.com.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.com.model.Row;


public class ListViewActivity extends Activity {

    private List<Row> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        list = new ArrayList<Row>();
        Row row = null;
        for(int i=0; i<100;i++){
            row = new Row();
            row.setKey("key"+i);
            row.setValue("value"+i);

            list.add(row);
        }

        ListView listView = (ListView)findViewById(R.id.listView);

        listView.setAdapter(new TableAdapter());


/*
        List<String> stringList = new ArrayList<String>();
        Map<String,?> stringMap = new HashMap<String, String>();
        int[] intArray = {0};
        String[] stringArray = {"0"};*/

       // ListAdapter lvAdapt = new SimpleAdapter(getApplicationContext(),stringMap,R.layout.listiview_item,stringArray,intArray);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listview_item, menu);
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

    class TableAdapter extends BaseAdapter{

        LayoutInflater inflater;

        public TableAdapter(){
            inflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = null;
            Holder holder = null;
            TextView keyTextView = null;
            TextView valueTextView = null;

            if(convertView == null){
                row = inflater.inflate(R.layout.listview_item,parent,false);
                keyTextView = (TextView) row.findViewById(R.id.tv_key);
                valueTextView = (TextView) row.findViewById(R.id.tv_value);

                holder = new Holder();
                holder.keyTextView = keyTextView;
                holder.valueTextView = valueTextView;

                row.setTag(holder);
            }else{
                row = convertView;
                holder = (Holder)row.getTag();
            }

            holder.keyTextView.setText(list.get(position).getKey());
            holder.valueTextView.setText(list.get(position).getValue());

            return row;
        }

        class Holder{
            TextView keyTextView = null;
            TextView valueTextView = null;
        }
    }
}
