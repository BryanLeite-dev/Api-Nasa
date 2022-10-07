package com.example.nasaapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText editTextDateFim, editTextDateInicio;
    Button buttonPesquisar;
    RecyclerView recyclerViewNasa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDateFim = findViewById(R.id.editTextDateFim);
        editTextDateInicio = findViewById(R.id.editTextDateInicio);
        buttonPesquisar = findViewById(R.id.buttonPesquisar);
        recyclerViewNasa = findViewById(R.id.recyclerViewNasa);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/planetary/apod/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PictureService pictureService = retrofit.create(PictureService.class);

        ArrayList<DtoPictureDay> dataset = new ArrayList<>();

        RecyclerView.Adapter adapter = new CustomAdapter(dataset);
        recyclerViewNasa.setAdapter(adapter);

        buttonPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ArrayList<DtoPictureDay>> call =
                        pictureService.getPictureOfDay(editTextDateInicio.getText().toString(),
                                editTextDateFim.getText().toString());


               call.enqueue(new Callback<ArrayList<DtoPictureDay>>() {
                   @Override
                   public void onResponse(Call<ArrayList<DtoPictureDay>> call, Response<ArrayList<DtoPictureDay>> response) {
                       ArrayList<DtoPictureDay> dtoPictureDay = response.body();
                       RecyclerView.Adapter adapter = new CustomAdapter(dtoPictureDay);
                       RecyclerView recyclerViewNasa = findViewById(R.id.recyclerViewNasa);
                       recyclerViewNasa.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
                       recyclerViewNasa.setAdapter(adapter);

                   }

                   @Override
                   public void onFailure(Call<ArrayList<DtoPictureDay>> call, Throwable t) {
                       Toast.makeText(MainActivity.this, "Erro:" + t.toString(), Toast.LENGTH_SHORT).show();
                   }
               });
            }
        });


    }
}