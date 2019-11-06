package fluffyhairedkid.dicemasterscompanion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Walker on 1/18/2016.
 */
public class DnD extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnd);

        Button allDnD = (Button) findViewById(R.id.btAllDnD);
        Button BFF = (Button) findViewById(R.id.btBFF);
        Button FUS = (Button) findViewById(R.id.btFUS);
        Button ToA = (Button) findViewById(R.id.btToA);
        Button TIW = (Button) findViewById(R.id.btTIW);
        Button AIW = (Button) findViewById(R.id.btAIW);
        Button ZHN = (Button) findViewById(R.id.btZHN);
        Button promosDnD = (Button) findViewById(R.id.btPromosDnD);

        allDnD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DnD.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where Universe='Dungeons & Dragons'");
                startActivity(intent);
            }
        });

        BFF.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DnD.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='BFF'");
                startActivity(intent);
            }
        });

        FUS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DnD.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='FUS'");
                startActivity(intent);
            }
        });

        ToA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DnD.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='ToA'");
                startActivity(intent);
            }
        });

        TIW.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DnD.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='TIW'");
                startActivity(intent);
            }
        });

        AIW.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DnD.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='AIW'");
                startActivity(intent);
            }
        });

        ZHN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DnD.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='ZHN'");
                startActivity(intent);
            }
        });

        promosDnD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DnD.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where Universe='Dungeons & Dragons' and Rarity in('gp','halt')");
                startActivity(intent);
            }
        });
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
