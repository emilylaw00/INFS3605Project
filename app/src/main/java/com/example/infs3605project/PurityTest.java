package com.example.infs3605project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurityTest extends AppCompatActivity {

    List<TestQuestion> dataList = new ArrayList<>();
    Users userLogin = new Users();

    ListView listView;
    MyAdapter myAdapter;
    TextView scoreTextView;
    Button submit, homeBtn;
    String userID;

    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purity_test);

        initData();

        listView = findViewById(R.id.listView);
        scoreTextView = findViewById(R.id.score);
        submit = findViewById(R.id.submit);
        homeBtn = findViewById(R.id.homeBtn);

        scoreTextView.setText("20");

        myAdapter = new MyAdapter(dataList, scoreTextView);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TestQuestion data = myAdapter.getItem(position);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = 20;
                for (TestQuestion data : dataList) {
                    if (data.isChecked()) {
                        count--; //decrementing score
                    }
                }

                userLogin.setPurityScore(count);
                scoreTextView.setText("" + count + "/" + dataList.size());
                Toast.makeText(view.getContext(), "Total Score: " + count + "/" + dataList.size(), Toast.LENGTH_SHORT).show();

                //store the score in the DB
                userID = fAuth.getCurrentUser().getUid(); //getting user ID of currently registered user
                DocumentReference documentReference = fStore.collection("Purity Score").document(userID);

                //create a map
                Map<String, Object> purity_score = new HashMap<>();
                purity_score.put("Score", Integer.toString(count));
                documentReference.set(purity_score).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("SAVED", "onSuccess: user score is saved for " + userID);
                    }
                }) .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("SAVED", "onFailure: " + e.toString());
                    }
                });

                //send intent to the score activity
                startActivity(new Intent(getApplicationContext(), PurityScore.class));

            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    void initData() {

        dataListInsert("1. I use the same password for most of my accounts");
        dataListInsert("2. I click on interesting spam emails");
        dataListInsert("3. I do not use a multi-factor authentication");
        dataListInsert("4. I have not set up a disaster recovery / incident response plan");
        dataListInsert("5. I do not use email protection softwares");
        dataListInsert("6. I use a password that is easy to remember");
        dataListInsert("7. I never log out of my accounts");
        dataListInsert("8. I do not keep up to date with cyber security news");
        dataListInsert("9. I often think that cyber security attacks cannot happen to me");
        dataListInsert("10. I treat cyber security as a one-time project");
        dataListInsert("11. I click on links sent to me from unknown phone numbers");
        dataListInsert("12. I pick up phones with no caller ID");
        dataListInsert("13. I do not check the sender email address before clicking on an email addressed to me");
        dataListInsert("14. I do not update my device’s security software when possible");
        dataListInsert("15. I do not mind sharing personal information over social media channels");
        dataListInsert("16. I use a separate device for work and personal use");
        dataListInsert("17. I have never used a VPN for security purposes before");
        dataListInsert("18. I click yes when websites ask me to remember my sensitive information");
        dataListInsert("19. If I picked up a USB from the ground, I would insert it into my computer out of pure curiosity");
        dataListInsert("20. I do not use a VPN when accessing public wifi networks");

        userLogin.setFirstName("tom");

    }

    //insert the question into the purity test page
    public void dataListInsert(String question) {
        TestQuestion data = new TestQuestion();
        data.setQuestion(question);
        dataList.add(data);
    }

    private class MyAdapter extends BaseAdapter {
        TextView scoreTextView;
        List<TestQuestion> dataList;

        public MyAdapter(List<TestQuestion> dataList, TextView scoreTextView) {
            this.dataList = dataList;
            this.scoreTextView = scoreTextView;
        }

        @Override
        public int getCount() {
            return dataList != null ? dataList.size() : 0;
        }

        @Override
        public TestQuestion getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.purity_list_item, container, false);
            }

            final TestQuestion data = dataList.get(position);

            TextView name = convertView.findViewById(R.id.name);
            final CheckBox checkBox = convertView.findViewById(R.id.checkBox);

            name.setText(data.getQuestion());
            checkBox.setChecked(data.isChecked());

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkBox.setChecked(!data.isChecked());
                    data.setChecked(checkBox.isChecked());

                    int count = 20;
                    for (TestQuestion data : dataList) {
                        if (data.isChecked()) {
                            count--;
                        }
                    }
                    scoreTextView.setText("" + count);
                }
            });

            return convertView;
        }
    }
}