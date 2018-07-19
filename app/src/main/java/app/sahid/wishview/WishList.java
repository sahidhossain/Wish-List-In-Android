package app.sahid.wishview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WishList extends AppCompatActivity {

    private Button btn2;
    private ListView listview;
    private EditText et;
    ArrayList<Item> al;
    BaseAdapter ba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        intitall();
    }

    private void intitall() {
        btn2=findViewById(R.id.btn2);
        listview=findViewById(R.id.listview);
        et=findViewById(R.id.edittext);
        al=new ArrayList<Item>();
        ba=new BaseAdapter() {
            @Override
            public int getCount() {
                return al.size();
            }

            @Override
            public Object getItem(int i) {
                return al.get(i);
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if(view==null)
                {
                    LayoutInflater inflater= (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
                    view=inflater.inflate(R.layout.sample,viewGroup,false);
                }
                TextView txt1=view.findViewById(R.id.text1);
                TextView txt2=view.findViewById(R.id.text2);
                txt1.setText(al.get(i).getName());
                Date date1=al.get(i).getDate();
                txt2.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(date1));
                return view;
            }
        };
        listview.setAdapter(ba);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st=et.getText().toString();
                Date dt=new Date();
                Item item=new Item(st,dt);
                al.add(item);
                ba.notifyDataSetChanged();
                et.setText("");
                InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromInputMethod(et.getWindowToken(),0);
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                al.remove(i);
                ba.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"Remove Succcessfully",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
