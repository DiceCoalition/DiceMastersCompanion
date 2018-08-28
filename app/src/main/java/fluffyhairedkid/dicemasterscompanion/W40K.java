package fluffyhairedkid.dicemasterscompanion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Trubie on 8/19/2018.
 */
public class W40K extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.w40k);

        Button allW40K = (Button) findViewById(R.id.btAllW40K);
        Button BFU = (Button) findViewById(R.id.btBFU);
        Button ORK = (Button) findViewById(R.id.btORK);
        Button SW = (Button) findViewById(R.id.btSW);
        //Button promosDnD = (Button) findViewById(R.id.btPromosDnD);

        allW40K.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(W40K.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where Universe='W40K'");
                startActivity(intent);
            }
        });

        BFU.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(W40K.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='BFU'");
                startActivity(intent);
            }
        });

        ORK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(W40K.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='ORK'");
                startActivity(intent);
            }
        });

        SW.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(W40K.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='SW'");
                startActivity(intent);
            }
        });

        /*
        promosDnD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(W40K.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where Universe='Dungeons & Dragons' and Rarity in('gp','halt')");
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
