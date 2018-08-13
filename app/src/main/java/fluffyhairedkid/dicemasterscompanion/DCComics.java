package fluffyhairedkid.dicemasterscompanion;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class DCComics extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dc_comics);

        Button allDC = (Button) findViewById(R.id.btAllDC);
        Button jl = (Button) findViewById(R.id.btJL);
        Button wol = (Button) findViewById(R.id.btWoL);
        Button wf = (Button) findViewById(R.id.btWF);
        Button gatf = (Button) findViewById(R.id.btGATF);
        Button bm = (Button) findViewById(R.id.btBM);
        Button sww = (Button) findViewById(R.id.btSWW);
        Button promosDC = (Button) findViewById(R.id.btPromosDC);

        allDC.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DCComics.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where Universe='DC Comics'");
                startActivity(intent);
            }
        });

        jl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DCComics.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='JL'");
                startActivity(intent);
            }
        });

        wol.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DCComics.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='WoL'");
                startActivity(intent);
            }
        });

        wf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DCComics.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='WF'");
                startActivity(intent);
            }
        });

        gatf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DCComics.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='GATF'");
                startActivity(intent);
            }
        });

        bm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DCComics.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='BM'");
                startActivity(intent);
            }
        });

        sww.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DCComics.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='SWW'");
                startActivity(intent);
            }
        });

        promosDC.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DCComics.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where Universe='DC Comics' and Rarity in('gp','halt')");
                startActivity(intent);
            }
        });
        /*
        allDC.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(DCComics.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where Universe='DC Comics'");

                Intent intent = new Intent(DCComics.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });

        jl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(DCComics.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where CardSet='JL'");

                Intent intent = new Intent(DCComics.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);

            }
        });

        wol.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ListBuilder cardList = new ListBuilder(DCComics.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where CardSet='WoL'");
                Intent intent = new Intent(DCComics.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });

        wf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ListBuilder cardList = new ListBuilder(DCComics.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where CardSet='WF'");
                Intent intent = new Intent(DCComics.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });

        gatf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ListBuilder cardList = new ListBuilder(DCComics.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where CardSet='GATF'");
                Intent intent = new Intent(DCComics.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });

        promosDC.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ListBuilder cardList = new ListBuilder(DCComics.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where Universe='DC Comics' and Rarity in('gp','halt')");

                Intent intent = new Intent(DCComics.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });
        */
    }
}
