package com.example.ad340app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static com.example.ad340app.profileFragment.AGE;
import static com.example.ad340app.profileFragment.DESCRIP;
import static com.example.ad340app.profileFragment.JOB;
import static com.example.ad340app.profileFragment.NAME;

public class Activity2 extends AppCompatActivity implements Listener{
    String username, check, name, job, description, age;
    FragmentManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        manager = getSupportFragmentManager();

        if (b != null) {

            if (b.containsKey(Constants.KEY_NAME)) {
                name = b.getString(Constants.KEY_NAME);
            }

            if (b.containsKey(Constants.KEY_AGE)) {
                age = b.getString(Constants.KEY_AGE);
            }

            if (b.containsKey(Constants.KEY_DESCRIPTION)) {
                description = b.getString(Constants.KEY_DESCRIPTION);
            }

            if (b.containsKey(Constants.KEY_JOB_NAME)) {
                job = b.getString(Constants.KEY_JOB_NAME);
            }
        }


        Bundle bundle = new Bundle();
        bundle.putString(NAME, name);
        bundle.putString(AGE,age);
        bundle.putString(JOB,job);
        bundle.putString(DESCRIP,description);

        profileFragment fragment = new profileFragment();
        fragment.setArguments(bundle);

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment, "fragA");
        transaction.commit();
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.newmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.navProfile:
                Bundle bundle = new Bundle();
                bundle.putString(NAME, name);
                bundle.putString(AGE,age);
                bundle.putString(JOB,job);
                bundle.putString(DESCRIP,description);
                profileFragment fragment = new profileFragment();
                fragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;
            case R.id.navMatches:
                Toast.makeText(this, "Matches selected", Toast.LENGTH_SHORT).show();

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MatchesFragment()).commit();
                break;
            case R.id.navSettings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return  true;
    }

    @Override
    public void matchesLikeToast(String k) {
        Toast.makeText(this,String.format("Liked " + k ),Toast.LENGTH_LONG).show();
    }

    public void finish(View view) {
        finish();
    }
}
