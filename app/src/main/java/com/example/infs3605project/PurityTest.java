package com.example.infs3605project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PurityTest extends AppCompatActivity {

    List<TestQuestion> dataList = new ArrayList<>();
    Users userLogin = new Users();

    ListView listView;
    MyAdapter myAdapter;
    TextView scoreTextView;
    Button submit, homeBtn;

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
                int count = 0;
                for (TestQuestion data : dataList) {
                    if (data.isChecked()) {
                        count++;
                    }
                }

                userLogin.setPurityScore(count);
                scoreTextView.setText("" + count + "/" + dataList.size());
                Toast.makeText(view.getContext(), "Total Score: " + count + "/" + dataList.size(), Toast.LENGTH_SHORT).show();
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

        dataListInsert("1. I know what a phishing attack is (can go into more specifics eg. spear phishing, vhishing)");
        dataListInsert("2. I’ve never feel victim to an cyber scam/attack");
        dataListInsert("3. I’ve used 2-step authentication where possible");
        dataListInsert("4. I’ve used 3-step authentication where possible");
        dataListInsert("5. I would let strangers use my phone (for whatever reason)");
        dataListInsert("6. I would let my acquaintances use my phone");
        dataListInsert("7. I use the same password for multiple accounts");
        dataListInsert("8. I use the same password for all accounts");
        dataListInsert("9. My passwords are considered strong by most standards(unique combinations)");
        dataListInsert("10. I lock all my devices with a password/authentication measure");
        dataListInsert("11. I lock all apps that have personal data with an authentication measure");
        dataListInsert("12. I read the terms & conditions before any agreement");
        dataListInsert("13. I check a company’s privacy policy before engaging in any activity");
        dataListInsert("14. I think twice before clicking any links/content within an email");
        dataListInsert("15. I open any email that is sent to me without checking who it’s from");
        dataListInsert("16. I filter out/block spam email as soon as possible");
        dataListInsert("17. I have used public wifi networks that don’t require authentication");
        dataListInsert("18. I will use public wifi networks when outside if possible");
        dataListInsert("19. I have used a VPN for security purposes");
        dataListInsert("20. I regularly use a VPN for security purposes");
        dataListInsert("21. I use separate devices for work/personal use");
        dataListInsert("22. I always update my device’s security software when possible");
        dataListInsert("23. I don’t mind sharing personal information over social media channels");
        dataListInsert("24. I regularly change my passwords where there is personal information");
        dataListInsert("25. I have clicked on unknown links before in an email");
        dataListInsert("26. I research what I am downloading before installing an application");
        dataListInsert("27. I regularly back-up my critical data");
        dataListInsert("28. I click no when websites ask me to remember password");
        dataListInsert("29. I delete sensitive material when there is no use for it anymore");
        dataListInsert("30. I often run anti-malware/anti-virus software tests");
        dataListInsert("31. I use network firewall for every application");
        dataListInsert("32. I avoid all pop-ups that appear");

        
        userLogin.setFirstName("tom");
    }

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

                    int count = 0;
                    for (TestQuestion data : dataList) {
                        if (data.isChecked()) {
                            count++;
                        }
                    }
                    scoreTextView.setText("" + count);
                }
            });

            return convertView;
        }
    }
}