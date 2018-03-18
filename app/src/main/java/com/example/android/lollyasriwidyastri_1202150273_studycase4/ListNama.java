package com.example.android.lollyasriwidyastri_1202150273_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ListNama extends AppCompatActivity {

    //deklarasi atribut yang akan di gunakan
    private ListView ListMahasiswa;
    private Button StartAsyncTask;
    private ProgressBar ProgressBar;
    private String[] mahasiswaArray = {
            "Lolly Asri", "Dwi Vitra", "Zuleyka Sevenia", "Hamzi Athari", "Hadif Alzam", "Azrah Sakira", "Haziqa", "Miza", "Iklimah"
            , "Nurul Hrp", "Nia Miranda", "Aulia Huda", "Ivana Tyora", "Mba Ipah", "Ipan"}; //data array yang akan ditampilkan
    private AddItemToListView mAddItemToListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama);
        //mengambil atribut yang akan digunakan
        ListMahasiswa = findViewById(R.id.listMahasiswa);
        ProgressBar = findViewById(R.id.progressBar);
        StartAsyncTask = findViewById(R.id.buttonMulai);

        ListMahasiswa.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

        StartAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //process adapter with asyncTask
                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute();
            }
        });
    }

    public class AddItemToListView extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> mAdapter;
        private int counter = 1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListNama.this);

        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) ListMahasiswa.getAdapter(); //casting suggestion
            //isi progress dialog
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data"); //loading data mengambil list nama dari array
            mProgressDialog.setCancelable(false); //cancel loading
            mProgressDialog.setMessage("Please wait....");
            mProgressDialog.setProgress(0);
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mAddItemToListView.cancel(true);
                    ProgressBar.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            mProgressDialog.show(); //menampilkan hasil loading data mengambil list nama
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (String item : mahasiswaArray) {
                publishProgress(item); //progress mengambil list nama
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (isCancelled()) { //kondisi jika progress di cancel
                    mAddItemToListView.cancel(true);
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);

            Integer current_status = (int) ((counter / (float) mahasiswaArray.length) * 100);
            ProgressBar.setProgress(current_status);

            //set progress only working for horizontal loading
            mProgressDialog.setProgress(current_status);

            //set message will not working when using horizontal loading
            mProgressDialog.setMessage(String.valueOf(current_status + "%"));
            counter++;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //hide progressbar
            ProgressBar.setVisibility(View.GONE);

            //remove progress dialog
            mProgressDialog.dismiss();
            ListMahasiswa.setVisibility(View.VISIBLE);
        }
    }
}
