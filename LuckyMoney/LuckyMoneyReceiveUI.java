package com.example.luckymoneyui;
import android.os.Bundle; import android.widget.Toast;
import androidx.annotation.Nullable; import androidx.appcompat.app.AppCompatActivity; import androidx.recyclerview.widget.GridLayoutManager; import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar; import java.util.*;
public class LuckyMoneyReceiveUI extends AppCompatActivity implements EnvelopeAdapter.OnEnvelopeOpenListener{
  private RecyclerView recycler;
  @Override protected void onCreate(@Nullable Bundle savedInstanceState){ super.onCreate(savedInstanceState); setContentView(R.layout.activity_receive);
    MaterialToolbar toolbar=findViewById(R.id.toolbar); toolbar.setTitle("LuckyMoney Receive"); toolbar.setOnClickListener(v-> Toast.makeText(this,"Toolbar",Toast.LENGTH_SHORT).show());
    recycler=findViewById(R.id.recycler); recycler.setLayoutManager(new GridLayoutManager(this,3));
    List<EnvelopeItem> items=new ArrayList<>(); for(int i=0;i<12;i++){ items.add(new EnvelopeItem(false,0)); }
    recycler.setAdapter(new EnvelopeAdapter(items,this));
  }
  @Override public void onEnvelopeOpened(int position,int valueCents){ Toast.makeText(this,"Envelope "+(position+1)+" = R$ "+String.format("%.2f", valueCents/100.0),Toast.LENGTH_SHORT).show(); }
}