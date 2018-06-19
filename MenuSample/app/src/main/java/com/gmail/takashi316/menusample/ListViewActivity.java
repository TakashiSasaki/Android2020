package com.gmail.takashi316.menusample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends Activity {

    private ListView _lvMenu;
    private List<Map<String, Object>> _menuList;

    //マップのキー文字列の配列。変更されることはないのでstatic final。
    private static final String[] FROM = {"name", "price"};

    //ビューIDの配列。変更されることはないのでstatic final。
    private static final int[] TO = {R.id.tvMenuName, R.id.tvMenuPrice};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        //メンバ変数の初期化処理
        this._lvMenu = findViewById(R.id.lvMenu);
        this._menuList = createTeishokuList();

        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
                _menuList, R.layout.row, FROM, TO);
        this._lvMenu.setAdapter(adapter);
        this._lvMenu.setOnItemClickListener(new ListItemClickListener());
    }//onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options_menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }//onCreateOptionsMenu

    private List<Map<String, Object>> createTeishokuList() {
        List menuList = new ArrayList<>();

        Map<String , Object> menu = new HashMap<>();

        menu.put("name", "ハンバーグ定食");
        menu.put("price", 800);
        menu.put("desc", "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>(); //これにより新しいMapオブジェクトを生成
        menu.put("name", "唐揚げ定食");
        menu.put("price", 750);
        menu.put("desc", "サラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        return menuList;
    }//createTeishokuList

    private List<Map<String, Object>> createCurryList() {
        List menuList = new ArrayList<>();

        Map<String , Object> menu = new HashMap<>();

        menu.put("name", "ビーフカレー");
        menu.put("price", 520);
        menu.put("desc", "特選スパイスを効かせた国産ビーフ１００％のカレーです。");
        menuList.add(menu);

        menu = new HashMap<>(); //これにより新しいMapオブジェクトを生成
        menu.put("name", "ポークカレー");
        menu.put("price", 420);
        menu.put("desc", "特選スパイスを効かせた国産ポーク１００％のカレーです。");
        menuList.add(menu);

        return menuList;
    }//createCurryList

    private class ListItemClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Map<String , Object> item = (Map<String , Object>)adapterView.getItemAtPosition(i);
            String menuName = (String)item.get("name");
            Integer menuPrice = (Integer)item.get("price");

            //getApplicationContext の代わりに ListViewActivity.this でもよい
            Intent intent = new Intent(getApplicationContext(), MenuThanksActivity.class);
            intent.putExtra("menuName", menuName);
            intent.putExtra("menuPrice", menuPrice + getString(R.string.tv_menu_unit));
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch(itemId){
            case R.id.menuListOptionTeishoku:
                this._menuList = createTeishokuList();
                break;
            case R.id.menuListOptionCurry:
                this._menuList = createCurryList();
                break;
        }//switch

        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
                this._menuList, R.layout.row, this.FROM, this.TO);

        this._lvMenu.setAdapter(adapter);

        return super.onOptionsItemSelected(item);
    }//onOptionsItemSelected
}//ListViewActivity