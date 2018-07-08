package com.adesworld.Activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.adesworld.Adapter.CategoryAdapter;
import com.adesworld.Adapter.DocumentAdapter;
import com.adesworld.Model.Category;
import com.adesworld.Model.CategoryData;
import com.adesworld.Model.Document;
import com.adesworld.Model.DocumentData;
import com.adesworld.R;
import com.adesworld.Utilities.ApiClient;
import com.adesworld.Utilities.ApiInterface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentListActivity extends AppCompatActivity {

    private Context context;
    private ApiInterface apiInterface;
    private Spinner spin_categories;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView rec_Documents;
    private ArrayList<CategoryData> categories;
    private ArrayList<DocumentData> documents;
    private TextView chosenTab;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents_list);
        context = this;
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        coloringStatusBar();
        getViews();
        getCategories();
        assignClickListeners();
        chosenTab.setTextColor(getResources().getColor(R.color.black));
        layoutManager = new LinearLayoutManager(this);
        rec_Documents.setLayoutManager(layoutManager);
    }

    public void coloringStatusBar()
    {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            window.setStatusBarColor(context.getResources().getColor(R.color.actionBar));
        }
    }

    public void getViews()
    {
        chosenTab = (TextView) findViewById(R.id.tv_Documents);
        spin_categories = (Spinner) findViewById(R.id.spin_categories);
        rec_Documents = (RecyclerView) findViewById(R.id.rec_Documents);
    }

    public void assignClickListeners()
    {
        spin_categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getDocuments(categories.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getCategories()
    {
        Call<Category> call_category = apiInterface.getCategories();
        call_category.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                categories = new ArrayList<>(Arrays.asList(response.body().getData()));
                CategoryAdapter adapter = new CategoryAdapter(categories);
                spin_categories.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getDocuments(String categoryId)
    {

        Call<Document> call_documents = apiInterface.getDocuments(categoryId);
        call_documents.enqueue(new Callback<Document>() {
            @Override
            public void onResponse(Call<Document> call, Response<Document> response) {
                documents = new ArrayList<>(Arrays.asList(response.body().getData()));
                DocumentAdapter adapter = new DocumentAdapter(documents);
                rec_Documents.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Document> call, Throwable t) {

            }
        });
    }

}
