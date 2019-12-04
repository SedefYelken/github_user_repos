package com.sedef.yelken.github.mvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sedef.yelken.github.mvvm.databinding.ActivityMainBinding;
import com.sedef.yelken.github.mvvm.model.remote.GitHubRepoResponse;
import com.sedef.yelken.github.mvvm.viewmodel.Adapter.RecyclerViewAdapter;
import com.sedef.yelken.github.mvvm.viewmodel.RecyclerViewModel;


public class MainActivity extends AppCompatActivity{

    static final String TAG = "onrepolistener";
    RecyclerViewModel viewModel;

    private Button sumbit;

    private EditText userName;

    private View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //    Toast.makeText(getApplicationContext(), ((EditText) findViewById(R.id.user)).getText().toString(), Toast.LENGTH_LONG).show();

            Toast.makeText(getApplicationContext(), "as", Toast.LENGTH_LONG).show();

            // loadData(((EditText) findViewById(R.id.user)).getText().toString());
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(RecyclerViewModel.class);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setAdapter(viewModel.adapter);//!!!!!!!!!!!!!!

        loadData(null);
        setupListClick();

        Button button = (Button) findViewById(R.id.btn_sumbit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), ((EditText) findViewById(R.id.user_name)).getText().toString(), Toast.LENGTH_LONG).show();

                loadData(((EditText) findViewById(R.id.user_name)).getText().toString());
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void loadData(String userName) {
        viewModel.fethData(userName).observe(this, response -> {
            viewModel.setRepositories(response);
        });
    }

    public void onRepoClick(int position) {
        Log.d(TAG, "onRepoClick clicked");
        Intent intent = new Intent(this, RepoViewDetailsActivity.class);
    }

    private void setupListClick() {
        viewModel.getSelected().observe(this, new Observer<GitHubRepoResponse>() {
            @Override
            public void onChanged(GitHubRepoResponse repo) {
                if (repo != null) {
                    Toast.makeText(MainActivity.this, "You selected a " +repo.getName(), Toast.LENGTH_LONG).show();
                    changeActivity(repo);
                }
            }
        });


    }

    public void changeActivity(GitHubRepoResponse repo){
        Intent intent = new Intent(this,RepoViewDetailsActivity.class);
        intent.putExtra("USER_NAME", repo.getOwner().getUserName());
        intent.putExtra("OPEN_ISSUE", repo.getOpenIssuesCount());
        intent.putExtra("AVATAR_URL", repo.getOwner().getAvatarUrl());
        startActivity(intent);
    }
}

