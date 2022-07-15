package fluffyhairedkid.dicemasterscompanion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Marvel extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marvel);

        Button allMarvel = (Button) findViewById(R.id.btAllMarvel);
        Button avx = (Button) findViewById(R.id.btAvX);
        Button ux = (Button) findViewById(R.id.btUX);
        Button aou = (Button) findViewById(R.id.btAoU);
        Button asm = (Button) findViewById(R.id.btASM);
        Button cw = (Button) findViewById(R.id.btCW);
        Button ds = (Button) findViewById(R.id.btDS);
        Button dp = (Button) findViewById(R.id.btDP);
        Button imwm = (Button) findViewById(R.id.btIMWM);
        Button td = (Button) findViewById(R.id.btTD);
        Button smmc = (Button) findViewById(R.id.btSMMC);
        Button gotg = (Button) findViewById(R.id.btGOTG);
        Button xmfc = (Button) findViewById(R.id.btXMFC);
        Button tmt = (Button) findViewById(R.id.btTMT);
        Button ai = (Button) findViewById(R.id.btAI);
        Button ki = (Button) findViewById(R.id.btKI);
        Button jll = (Button) findViewById(R.id.btJLL);
		Button xmf = (Button) findViewById(R.id.btXMF);
        Button xfo = (Button) findViewById(R.id.btXFO);
        Button dxm = (Button) findViewById(R.id.btDXM);
        Button ig = (Button) findViewById(R.id.btIG);
        Button dps = (Button) findViewById(R.id.btDPS);
        Button promosMarvel = (Button) findViewById(R.id.btPromosMarvel);

        allMarvel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where Universe='Marvel'");
                startActivity(intent);
            }
        });

        avx.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='AVX'");
                startActivity(intent);
            }
        });

        ux.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='UXM'");
                startActivity(intent);
            }
        });

        aou.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='AoU'");
                startActivity(intent);
            }
        });

        asm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='ASM'");
                startActivity(intent);
            }
        });

        cw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='CW'");
                startActivity(intent);
            }
        });

        ds.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='DS'");
                startActivity(intent);
            }
        });

        dp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='DP'");
                startActivity(intent);
            }
        });

        imwm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='IMWM'");
                startActivity(intent);
            }
        });

        td.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='TD'");
                startActivity(intent);
            }
        });

        smmc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='SMMC'");
                startActivity(intent);
            }
        });

        gotg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='GotG'");
                startActivity(intent);
            }
        });

        xmfc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='XMFC'");
                startActivity(intent);
            }
        });

        tmt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='TMT'");
                startActivity(intent);
            }
        });

        ai.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='AI'");
                startActivity(intent);
            }
        });

        ki.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='KI'");
                startActivity(intent);
            }
        });

        jll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='JLL'");
                startActivity(intent);
            }
        });

		xmf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='XMF'");
                startActivity(intent);
            }
        });

        xfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='XFO'");
                startActivity(intent);
            }
        });

        dxm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='DXM'");
                startActivity(intent);
            }
        });

        ig.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='IG'");
                startActivity(intent);
            }
        });

        dps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where CardSet='DPS'");
                startActivity(intent);
            }
        });

        promosMarvel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marvel.this, CardCollectorNEW.class);
                intent.putExtra("view", "collection");
                intent.putExtra("whereCriteria", "where Universe='Marvel' and Rarity in('gp','halt')");
                startActivity(intent);
            }
        });

        /*
        allMarvel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(Marvel.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where Universe='Marvel'");

                Intent intent = new Intent(Marvel.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });

        avx.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(Marvel.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where CardSet='AVX'");

                Intent intent = new Intent(Marvel.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);

            }
        });

        ux.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(Marvel.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where CardSet='UXM'");

                Intent intent = new Intent(Marvel.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });

        aou.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(Marvel.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where CardSet='AoU'");

                Intent intent = new Intent(Marvel.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });

        asm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(Marvel.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where CardSet='ASM'");

                Intent intent = new Intent(Marvel.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });

        cw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(Marvel.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where CardSet='CW'");

                Intent intent = new Intent(Marvel.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });

        ds.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(Marvel.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where CardSet='DS'");

                Intent intent = new Intent(Marvel.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });

        dp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(Marvel.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where CardSet='DP'");

                Intent intent = new Intent(Marvel.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });

        imwm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(Marvel.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where CardSet='IMWM'");

                Intent intent = new Intent(Marvel.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });

        promosMarvel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListBuilder cardList = new ListBuilder(Marvel.this,
                        getPackageName());
                MainActivity.masterList = cardList
                        .buildList("where Universe='Marvel' and Rarity in('gp','halt')");

                Intent intent = new Intent(Marvel.this, CardCollector.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });
        */
    }
}
