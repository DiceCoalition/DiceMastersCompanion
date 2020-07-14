package fluffyhairedkid.dicemasterscompanion;

import android.os.Bundle;

import android.widget.TextView;
import android.app.Activity;


import java.util.List;
import java.util.Map;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CollectionStats extends Activity {

    SQLDataSource datasource;
    String ownedCriteria = " where NumOwned>0 ";
    String notOwnedCriteria = " where NumOwned=0 ";
    String marvelCriteria = " and Universe='Marvel'";
    String dcCriteria = " and Universe='DC Comics'";
    String dndCriteria = " and Universe='Dungeons & Dragons'";
    String yugiohCriteria = " and Universe='Yu-Gi-Oh!'";
    String tmntCriteria = " and Universe='TMNT'";
    String w40kCriteria = " and Universe='W40K'";
    String wweCriteria = " and Universe='WWE'";
    static DecimalFormat df = new DecimalFormat("0.0");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String orderCriteria = "case when DieImage='basic' then 0 else 1 end, CharName, CardSet, Rarity, CardName";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_stats);

        datasource = new SQLDataSource(this);
        datasource.open();

        Map<String, List<String>> result = datasource.sqlGetCardsList("_id", ownedCriteria, orderCriteria);
        int ownedNum = 0;
        for(String n : result.keySet()){
            ownedNum += result.get(n).size();
        }
        result = datasource.sqlGetCardsList("_id",notOwnedCriteria , orderCriteria);
        int notOwned = 0;
        for(String n : result.keySet()){
            notOwned += result.get(n).size();
        }
        final TextView dmStats = (TextView) findViewById(R.id.tvDmStats);
        int total = ownedNum+notOwned;
        double percent = (((double)ownedNum)/((double)total))*100.0;
        dmStats.setText(ownedNum + "/" + total + "\n"+df.format(percent)+"%");

        //begin Marvel stats
        result = datasource.sqlGetCardsList("_id", ownedCriteria + marvelCriteria, orderCriteria);
        ownedNum = 0;
        for(String n : result.keySet()){
            ownedNum += result.get(n).size();
        }
        result = datasource.sqlGetCardsList("_id",notOwnedCriteria + marvelCriteria , orderCriteria);
        notOwned = 0;
        for(String n : result.keySet()){
            notOwned += result.get(n).size();
        }
        final TextView marvelStats = (TextView) findViewById(R.id.tvMarvelStats);
        total = ownedNum+notOwned;
        percent = (((double)ownedNum)/((double)total))*100.0;

        marvelStats.setText(ownedNum + "/" + total + "\n"+df.format(percent)+"%");


        //begin DC stats
        result = datasource.sqlGetCardsList("_id", ownedCriteria + dcCriteria, orderCriteria);
        ownedNum = 0;
        for(String n : result.keySet()){
            ownedNum += result.get(n).size();
        }
        result = datasource.sqlGetCardsList("_id",notOwnedCriteria + dcCriteria , orderCriteria);
        notOwned = 0;
        for(String n : result.keySet()){
            notOwned += result.get(n).size();
        }
        final TextView dcStats = (TextView) findViewById(R.id.tvDCStats);
        total = ownedNum+notOwned;
        percent = (((double)ownedNum)/((double)total))*100.0;

        dcStats.setText(ownedNum + "/" + total + "\n"+df.format(percent)+"%");

        //begin D&D stats
        result = datasource.sqlGetCardsList("_id", ownedCriteria + dndCriteria, orderCriteria);
        ownedNum = 0;
        for(String n : result.keySet()){
            ownedNum += result.get(n).size();
        }
        result = datasource.sqlGetCardsList("_id",notOwnedCriteria + dndCriteria , orderCriteria);
        notOwned = 0;
        for(String n : result.keySet()){
            notOwned += result.get(n).size();
        }
        final TextView dndStats = (TextView) findViewById(R.id.tvDnDStats);
        total = ownedNum+notOwned;
        percent = (((double)ownedNum)/((double)total))*100.0;

        dndStats.setText(ownedNum + "/" + total + "\n"+df.format(percent)+"%");

        //begin yugioh stats
        result = datasource.sqlGetCardsList("_id", ownedCriteria + yugiohCriteria, orderCriteria);
        ownedNum = 0;
        for(String n : result.keySet()){
            ownedNum += result.get(n).size();
        }
        result = datasource.sqlGetCardsList("_id",notOwnedCriteria + yugiohCriteria , orderCriteria);
        notOwned = 0;
        for(String n : result.keySet()){
            notOwned += result.get(n).size();
        }
        final TextView yugiohStats = (TextView) findViewById(R.id.tvYuGiOhStats);
        total = ownedNum+notOwned;
        percent = (((double)ownedNum)/((double)total))*100.0;

        yugiohStats.setText(ownedNum + "/" + total + "\n"+df.format(percent)+"%");

        //begin TMNT stats
        result = datasource.sqlGetCardsList("_id", ownedCriteria + tmntCriteria, orderCriteria);
        ownedNum = 0;
        for(String n : result.keySet()){
            ownedNum += result.get(n).size();
        }
        result = datasource.sqlGetCardsList("_id",notOwnedCriteria + tmntCriteria , orderCriteria);
        notOwned = 0;
        for(String n : result.keySet()){
            notOwned += result.get(n).size();
        }
        final TextView tmntStats = (TextView) findViewById(R.id.tvtmntStats);
        total = ownedNum+notOwned;
        percent = (((double)ownedNum)/((double)total))*100.0;

        tmntStats.setText(ownedNum + "/" + total + "\n"+df.format(percent)+"%");

        //begin W40K stats
        result = datasource.sqlGetCardsList("_id", ownedCriteria + w40kCriteria, orderCriteria);
        ownedNum = 0;
        for(String n : result.keySet()){
            ownedNum += result.get(n).size();
        }
        result = datasource.sqlGetCardsList("_id",notOwnedCriteria + w40kCriteria , orderCriteria);
        notOwned = 0;
        for(String n : result.keySet()){
            notOwned += result.get(n).size();
        }
        final TextView w40kStats = (TextView) findViewById(R.id.tvw40kStats);
        total = ownedNum+notOwned;
        percent = (((double)ownedNum)/((double)total))*100.0;

        w40kStats.setText(ownedNum + "/" + total + "\n"+df.format(percent)+"%");

        //begin WWE stats
        result = datasource.sqlGetCardsList("_id", ownedCriteria + wweCriteria, orderCriteria);
        ownedNum = 0;
        for(String n : result.keySet()){
            ownedNum += result.get(n).size();
        }
        result = datasource.sqlGetCardsList("_id",notOwnedCriteria + wweCriteria , orderCriteria);
        notOwned = 0;
        for(String n : result.keySet()){
            notOwned += result.get(n).size();
        }
        final TextView wweStats = (TextView) findViewById(R.id.tvwweStats);
        total = ownedNum+notOwned;
        percent = (((double)ownedNum)/((double)total))*100.0;

        wweStats.setText(ownedNum + "/" + total + "\n"+df.format(percent)+"%");
    }



}
