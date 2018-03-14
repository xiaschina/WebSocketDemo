package com.xias.demo.websocket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xias.demo.websocket.util.WebSocketClient;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnWebSocketConnectListener{

    private List<String> list = new ArrayList<>();
    private MyAdapter myAdapter;
    private WebSocketClient webSocketClient;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        editText = findViewById(R.id.edit_text);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editText.getText().toString()))
                    return;
                if(webSocketClient.isOpen()) {
                    webSocketClient.send(editText.getText().toString());
                    editText.setText("");
                }else{
                    showToast("Connect is shut!");
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        String address = "ws://172.24.42.12:8888";//服务器地址
        try {
            URI uri = new URI(address);
            webSocketClient = new com.xias.demo.websocket.util.WebSocketClient(uri, this);
            webSocketClient.connect();
        }catch (Exception e){
            showToast(e.getMessage());
        }
    }

    @Override
    public void onOpen(String msg) {
        showToast(msg);
    }

    @Override
    public void onMessage(String msg) {
        if(!TextUtils.isEmpty(msg)) {
            list.add(msg);
            myAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClose(String msg) {
        showToast(msg);
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    private void showToast(String msg){
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_recycler_view_item, null);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tv;

            public ViewHolder(View itemView) {
                super(itemView);
                this.tv = itemView.findViewById(R.id.tv);
            }
        }
    }
}
