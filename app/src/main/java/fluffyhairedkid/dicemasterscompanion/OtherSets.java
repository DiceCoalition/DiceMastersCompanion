package fluffyhairedkid.dicemasterscompanion;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class OtherSets extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_sets);

        Button S1 = (Button) findViewById(R.id.btS1);
        Button TMNT = (Button) findViewById(R.id.btTMNT);
        Button HHS = (Button) findViewById(R.id.btHHS);

        S1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OtherSets.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where Universe='Yu-Gi-Oh!'");
                startActivity(intent);
            }
        });

        TMNT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OtherSets.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='TMNT'");
                startActivity(intent);
            }
        });

        HHS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OtherSets.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='HHS'");
                startActivity(intent);
            }
        });

        /*
        S1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(OtherSets.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where Universe='Yu-Gi-Oh!'");

                Intent intent = new Intent(OtherSets.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });

        TMNT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(OtherSets.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where CardSet='TMNT'");

                Intent intent = new Intent(OtherSets.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });

        HHS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(OtherSets.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where CardSet='HHS'");

                Intent intent = new Intent(OtherSets.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });
        */
    }
}
