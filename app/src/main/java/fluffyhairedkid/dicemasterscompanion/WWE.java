package fluffyhairedkid.dicemasterscompanion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Trubie on 2/12/2020.
 */
public class WWE extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wwe);

        Button allWWE = (Button) findViewById(R.id.btAllWWE);
        Button WWE = (Button) findViewById(R.id.btWWE);
        Button BIT = (Button) findViewById(R.id.btBIT);
        Button TAG = (Button) findViewById(R.id.btTAG);
        //Button promosWWE = (Button) findViewById(R.id.btPromosWWE);

        allWWE.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WWE.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where Universe='WWE'");
                startActivity(intent);
            }
        });

        WWE.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WWE.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='WWE'");
                startActivity(intent);
            }
        });

        BIT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WWE.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='BIT'");
                startActivity(intent);
            }
        });

        TAG.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WWE.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='TAG'");
                startActivity(intent);
            }
        });

/*
        promosWWE.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WWE.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where Universe='WWE' and Rarity in('gp','halt')");
                startActivity(intent);
            }
        });
*/
/*
        allDnD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(DnD.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where Universe='Dungeons & Dragons'");

                Intent intent = new Intent(DnD.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });

        BFF.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(DnD.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where CardSet='BFF'");

                Intent intent = new Intent(DnD.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);

            }
        });

        FUS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(DnD.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where CardSet='FUS'");

                Intent intent = new Intent(DnD.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });


        promosDnD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(DnD.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where Universe='Dungeons & Dragons' and Rarity in('gp','halt')");

                Intent intent = new Intent(DnD.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });
    */
    }

}
