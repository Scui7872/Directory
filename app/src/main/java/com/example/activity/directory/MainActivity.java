package com.example.activity.directory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mEtName;
    private EditText mEtPhone;
    private RecyclerView mTvShow;

    private List<Address> addresses;
    private AddressDao addressDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }
    private void initData(){
        addressDao = new AddressDao(this);
        addresses = addressDao.queryAll();
//        addresses = new ArrayList<>();
//        addresses.add(new Address("测试","13001234567"));
    }

    private AddressAdapter adapter;
    private Address currentAddress;
    private void initView() {
        mEtName = findViewById(R.id.et_name);
        mEtPhone = findViewById(R.id.et_phone);
        mTvShow = findViewById(R.id.rv_address);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);

        adapter = new AddressAdapter(addresses) ;
        mTvShow.setLayoutManager(new LinearLayoutManager(this));
        mTvShow.addItemDecoration (new DividerItemDecoration(this, DividerItemDecoration. VERTICAL));
        mTvShow.setAdapter(adapter);
        adapter.setOnItemClickListener(new AddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                currentAddress = addresses.get(position);
                if (currentAddress != null) {
                    mEtName.setText(currentAddress.getName());
                    mEtPhone.setText(currentAddress.getPhone());
                }
            }
        });


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //增加数据
            case R.id.btn_add:
                insert();
                break;
            //查询数据
            case R.id.btn_query:
                query();
                break;
            //修改数据
            case R.id.btn_update:
                update();
                break;
            //删除数据
            case R.id.btn_delete:
                delete();
                break;
        }
    }
    
    private void insert() {
        String name = mEtName.getText().toString();
        String phone = mEtPhone.getText().toString();
        Address address = new Address();
        address.setName(name);
        address.setPhone(phone);
        if(addressDao.insert(address)) {
            query();
        }
    }

    private void query() {
        if (addresses.size() > 0) {
            addresses.clear();
        }
        addresses.addAll(addressDao.queryAll());
        adapter.notifyDataSetChanged();
    }

    private void update() {
        String name = mEtName.getText().toString();
        String phone = mEtPhone.getText().toString();
        if (currentAddress != null) {
            currentAddress.setName(name);
            currentAddress.setPhone(phone);
            if (addressDao.update(currentAddress)) {
                query();
            }
        }
    }

    private void delete() {

    }

}
