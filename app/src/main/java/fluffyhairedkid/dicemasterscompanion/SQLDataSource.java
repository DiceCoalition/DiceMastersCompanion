package fluffyhairedkid.dicemasterscompanion;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static android.R.attr.value;

public class SQLDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public SQLDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    void close() {
        dbHelper.close();
    }

    ArrayList<String> sqlGetCharList(String whereCriteria, String orderCriteria) {
        ArrayList<String> data = new ArrayList<String>();

        Cursor cursor = database
                .rawQuery(
                        "select case when B.Ct>1 then tblCards.CharName||' ('||tblCards.CardSet||')' else tblCards.CharName end as CharName from tblCards inner join (select CharName, count(distinct CardSet) as Ct from tblCards "
                                + whereCriteria
                                + " group by CharName) as B on tblCards.CharName=B.CharName "
                                + whereCriteria.replace("CharName",
                                "tblCards.CharName")
                                + "group by case when B.Ct>1 then tblCards.CharName||' ('||tblCards.CardSet||')' else tblCards.CharName end order by "
                                + orderCriteria, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            data.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();

        return data;
    }

    HashMap<String, List<String>> sqlGetCardsList(String fieldName, String whereCriteria, String orderCriteria) {
        LinkedHashMap<String, List<String>> data = new LinkedHashMap<String, List<String>>();
        ArrayList<String> tempList = new ArrayList<String>();
        String tempChar = "";

        Cursor cursor = database
                .rawQuery(
                        "select case when B.Ct>1 then tblCards.CharName||' ('||tblCards.CardSet||')' else tblCards.CharName end as CharName, " + fieldName + " from tblCards inner join (select CharName, count(distinct CardSet) as Ct from tblCards "
                                + whereCriteria
                                + " group by CharName) as B on tblCards.CharName=B.CharName "
                                + whereCriteria.replace("CharName",
                                "tblCards.CharName")
                                + " order by "
                                + orderCriteria, null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            tempChar = cursor.getString(0);
            while (!cursor.isAfterLast()) {
                if (cursor.getString(0).equals(tempChar)) {
                    tempList.add(cursor.getString(1));
                } else {
                    data.put(tempChar, tempList);
                    tempChar = cursor.getString(0);
                    tempList = new ArrayList<String>();
                    tempList.add(cursor.getString(1));
                }
                cursor.moveToNext();
            }
            data.put(tempChar, tempList);
            cursor.close();
        }
        return data;
    }

    HashMap<String, List<Integer>> sqlGetImagesList(Context context, String fieldName, String whereCriteria, String orderCriteria) {
        LinkedHashMap<String, List<Integer>> data = new LinkedHashMap<String, List<Integer>>();
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        String tempChar = "";

        Cursor cursor = database
                .rawQuery(
                        "select case when B.Ct>1 then tblCards.CharName||' ('||tblCards.CardSet||')' else tblCards.CharName end as CharName, " + fieldName + " from tblCards inner join (select CharName, count(distinct CardSet) as Ct from tblCards "
                                + whereCriteria
                                + " group by CharName) as B on tblCards.CharName=B.CharName "
                                + whereCriteria.replace("CharName",
                                "tblCards.CharName")
                                + " order by "
                                + orderCriteria, null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            tempChar = cursor.getString(0);
            while (!cursor.isAfterLast()) {
                if (cursor.getString(0).equals(tempChar)) {
                    tempList.add(context.getResources().getIdentifier(cursor.getString(1), "drawable", context.getPackageName()));
                } else {
                    data.put(tempChar, tempList);
                    tempChar = cursor.getString(0);
                    tempList = new ArrayList<Integer>();
                    tempList.add(context.getResources().getIdentifier(cursor.getString(1), "drawable", context.getPackageName()));
                }
                cursor.moveToNext();
            }
            data.put(tempChar, tempList);
            cursor.close();
        }
        return data;
    }

    HashMap<String, Integer> sqlGetCharAttribList(Context context, String fieldName, String whereCriteria, String orderCriteria) {
        LinkedHashMap<String, Integer> data = new LinkedHashMap<String, Integer>();

        Cursor cursor = database
                .rawQuery(
                        "select case when B.Ct>1 then tblCards.CharName||' ('||tblCards.CardSet||')' else tblCards.CharName end as CharName, " + fieldName + " from tblCards inner join (select CharName, count(distinct CardSet) as Ct from tblCards "
                                + whereCriteria
                                + " group by CharName) as B on tblCards.CharName=B.CharName "
                                + whereCriteria.replace("CharName",
                                "tblCards.CharName")
                                + "group by case when B.Ct>1 then tblCards.CharName||' ('||tblCards.CardSet||')' else tblCards.CharName end order by "
                                + orderCriteria, null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                if(fieldName.equals("DieImage")){
                    data.put(cursor.getString(0), context.getResources().getIdentifier(cursor.getString(1), "drawable", context.getPackageName()));
                } else {
                    data.put(cursor.getString(0), cursor.getInt(1));
                }
                cursor.moveToNext();
            }
            cursor.close();
        }
        return data;
    }

    HashMap<String, String> sqlGetTableInfoList(String fieldName, String whereCriteria, String orderCriteria) {
        LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();

        Cursor cursor = database
                .rawQuery(
                        "select case when B.Ct>1 then tblCards.CharName||' ('||tblCards.CardSet||')' else tblCards.CharName end as CharName, " + fieldName + " from tblCards inner join (select CharName, count(distinct CardSet) as Ct from tblCards "
                                + whereCriteria
                                + " group by CharName) as B on tblCards.CharName=B.CharName "
                                + whereCriteria.replace("CharName",
                                "tblCards.CharName")
                                + "group by case when B.Ct>1 then tblCards.CharName||' ('||tblCards.CardSet||')' else tblCards.CharName end, " + fieldName + " order by "
                                + orderCriteria, null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                data.put(cursor.getString(0), cursor.getString(1));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return data;
    }

    ArrayList<ArrayList<String>> sqlGetCardList(Context context,
                                                       String whereCriteria, String orderCriteria) {
        ArrayList<ArrayList<String>> cardArray = new ArrayList<ArrayList<String>>();
        ArrayList<String> setName = new ArrayList<String>();
        ArrayList<String> charName = new ArrayList<String>();
        ArrayList<String> cardName = new ArrayList<String>();
        ArrayList<String> dieImage = new ArrayList<String>();
        ArrayList<String> cardImage = new ArrayList<String>();
        ArrayList<String> cost = new ArrayList<String>();
        ArrayList<String> affiliationone = new ArrayList<String>();
        ArrayList<String> affiliationtwo = new ArrayList<String>();
        ArrayList<String> energy = new ArrayList<String>();
        ArrayList<String> rarity = new ArrayList<String>();
        ArrayList<String> diceOwned = new ArrayList<String>();
        ArrayList<String> cardsOwned = new ArrayList<String>();
        ArrayList<String> tblName = new ArrayList<String>();

        Cursor cursor = database
                .rawQuery(
                        "select CardSet, case when B.Ct>1 then tblCards.CharName||' ('||tblCards.CardSet||')' else tblCards.CharName end as CharName, CardName, DieImage, CardImage, Cost, AffiliationOne, AffiliationTwo, Energy, Rarity, NumOwned, DiceOwned, tblCards.CharName as TblName from tblCards inner join (select CharName, count(distinct CardSet) as Ct from tblCards "
                                + whereCriteria
                                + " group by CharName) as B on tblCards.CharName=B.CharName "
                                + whereCriteria.replace("CharName",
                                "tblCards.CharName")
                                + " order by "
                                + orderCriteria, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            setName.add(cursor.getString(0));
            charName.add(cursor.getString(1));
            cardName.add(cursor.getString(2));
            dieImage.add(cursor.getString(3));
            cardImage.add(cursor.getString(4));
            cost.add(cursor.getString(5));
            affiliationone.add(cursor.getString(6));
            affiliationtwo.add(cursor.getString(7));
            energy.add(cursor.getString(8));
            rarity.add(cursor.getString(9));
            diceOwned.add(cursor.getString(10));
            cardsOwned.add(cursor.getString(11));
            tblName.add(cursor.getString(12));
            cursor.moveToNext();
        }
        cursor.close();

        cardArray.add(setName);
        cardArray.add(charName);
        cardArray.add(cardName);
        cardArray.add(dieImage);
        cardArray.add(cardImage);
        cardArray.add(cost);
        cardArray.add(affiliationone);
        cardArray.add(affiliationtwo);
        cardArray.add(energy);
        cardArray.add(rarity);
        cardArray.add(diceOwned);
        cardArray.add(cardsOwned);
        cardArray.add(tblName);

        return cardArray;
    }

    void sqlUpdateNumDice(int numDice, String charName, String setName) {

        charName = charName.replace("'", "''");
        database.execSQL("update tblCards set DiceOwned=" + numDice
                + " where CharName='" + charName + "' and CardSet='" + setName
                + "'");
    }

    void sqlUpdateNumCards(int numCards, String charName,
                                  String cardName, String setName) {

        charName = charName.replace("'", "''");
        cardName = cardName.replace("'", "''");
        database.execSQL("update tblCards set NumOwned=" + numCards
                + " where CharName='" + charName + "' and CardName='"
                + cardName + "' and CardSet='" + setName + "'");
    }

    void sqlUpdateNumFoils(int numCards, String charName,
                           String cardName, String setName) {

        charName = charName.replace("'", "''");
        cardName = cardName.replace("'", "''");
        database.execSQL("update tblCards set NumFoilsOwned=" + numCards
                + " where CharName='" + charName + "' and CardName='"
                + cardName + "' and CardSet='" + setName + "'");
    }

    ArrayList<String> sqlGetTeams() {
        ArrayList<String> teams = new ArrayList<String>();

        Cursor cursor = database.rawQuery(
                "select TeamName from tblTeams group by TeamName", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            teams.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();

        return teams;
    }

    String sqlGetTeamList(String teamName) {
        String whereCriteria = "where _id in(";

        teamName = teamName.replace("'", "''");
        Cursor cursor = database
                .rawQuery(
                        "select _id from tblCards inner join tblTeams on tblCards.CardSet=tblTeams.CardSet and tblCards.CharName=tblTeams.CharName and tblCards.CardName=tblTeams.CardName where TeamName='"
                                + teamName + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            whereCriteria = whereCriteria + cursor.getString(0) + ",";
            cursor.moveToNext();
        }

        if (whereCriteria.substring(whereCriteria.length() - 1).equals(",")) {
            whereCriteria = whereCriteria.substring(0,
                    whereCriteria.length() - 1) + ")";
        } else {
            whereCriteria = whereCriteria + ")";
        }

        cursor.close();

        return whereCriteria;
    }

    void sqlAddTeam(String teamName) {

        teamName = teamName.replace("'", "''");
        database.execSQL("insert into tblTeams select '" + teamName
                + "','','','',0");
    }

    void sqlDeleteTeam(String teamName) {
        teamName = teamName.replace("'", "''");
        database.execSQL("delete from tblTeams where TeamName='" + teamName
                + "'");
    }

    void sqlAddTeamCard(String teamName, String charName,
                               String cardName, String setName) {
        teamName = teamName.replace("'", "''");
        cardName = cardName.replace("'", "''");
        charName = charName.replace("'", "''");
        database.execSQL("insert into tblTeams select '" + teamName + "','"
                + setName + "','" + charName + "','" + cardName + "',0");
    }

    void sqlDeleteTeamCard(String teamName, String charName,
                                  String cardName, String setName) {
        teamName = teamName.replace("'", "''");
        cardName = cardName.replace("'", "''");
        charName = charName.replace("'", "''");
        database.execSQL("delete from tblTeams where TeamName='" + teamName
                + "' and CardSet='" + setName + "' and CharName='" + charName
                + "' and CardName='" + cardName + "'");
    }

    boolean sqlCheckTeamName(String teamName) {
        boolean check = false;
        String name = "";

        Cursor cursor = database.rawQuery(
                "select TeamName from tblTeams where TeamName='"
                        + teamName.replace("'", "''") + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            name = cursor.getString(0);
            cursor.moveToNext();
        }
        cursor.close();

        if (teamName.equals(name)) {
            check = true;
        }

        return check;
    }

    HashMap<String, Integer> sqlGetTeamDice(String whereCriteria,
                                                   String orderCriteria) {

        HashMap<String, Integer> item = new HashMap<String, Integer>();

        Cursor cursor = database
                .rawQuery(
                        "select case when B.Ct>1 then tblTeams.CharName||' ('||tblTeams.CardSet||')' else tblTeams.CharName end as CharName, NumDice, tblTeams.CharName as TblName from tblTeams inner join (select CharName, count(distinct CardSet) as Ct from tblTeams "
                                + whereCriteria
                                + " group by CharName) as B on tblTeams.CharName=B.CharName "
                                + whereCriteria.replace("CharName",
                                "tblTeams.CharName")
                                + " order by "
                                + orderCriteria, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            item.put(cursor.getString(0), cursor.getInt(1));
            cursor.moveToNext();
        }
        cursor.close();

        return item;
    }

    void sqlUpdateTeamDice(int numDice, String charName, String setName,
                                  String teamName) {

        charName = charName.replace("'", "''");
        teamName = teamName.replace("'", "''");
        database.execSQL("update tblTeams set NumDice=" + numDice
                + " where CharName='" + charName + "' and CardSet='" + setName
                + "' and TeamName='" + teamName + "'");
    }

    void sqlRandomTeam(String teamName, int numCards, boolean basics,
                              String whereCriteria, String basicCriteria) {

        // String addCriteria = "";
        List<String> charList = new ArrayList<String>();
        boolean charCheck = false;

        if (whereCriteria.equals("")) {
            whereCriteria = "where NumOwned>=0 ";
        }
        if (basicCriteria.equals("")) {
            basicCriteria = "where NumOwned>=0 ";
        }
        // if (owned) {
        // addCriteria = "and NumOwned>0 ";
        // }

        Cursor cursor = database
                .rawQuery(
                        "select CardSet, CharName, CardName from tblCards "
                                // + addCriteria
                                + whereCriteria
                                + "and CharName<>'Basic Action Card' order by random()",
                        null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            for (int i = 0; i < charList.size(); i++) {
                if (charList.get(i).equals(cursor.getString(1))) {
                    charCheck = true;
                    i = charList.size();
                }
            }

            if (!charCheck) {
                charList.add(cursor.getString(1));
                // WHY DOESN'T THIS WORK?
                // sqlAddTeamCard(teamName, cursor.getString(0),
                // cursor.getString(1), cursor.getString(2));
                database.execSQL("insert into tblTeams select '" + teamName
                        + "','" + cursor.getString(0) + "','"
                        + cursor.getString(1).replace("'", "''") + "','"
                        + cursor.getString(2).replace("'", "''") + "',0");

            }

            if (charList.size() == numCards) {
                cursor.moveToLast();
            }
            cursor.moveToNext();
            charCheck = false;
        }
        cursor.close();

        if (basics) {
            Cursor cursor2 = database
                    .rawQuery(
                            "select CardSet, CharName, CardName from tblCards "
                                    // + addCriteria
                                    + basicCriteria
                                    + "and CharName='Basic Action Card' order by random() limit 2",
                            null);
            cursor2.moveToFirst();
            while (!cursor2.isAfterLast()) {
                // WHY DOESN'T THIS WORK?
                // sqlAddTeamCard(teamName, cursor2.getString(0),
                // cursor2.getString(1), cursor2.getString(2));
                database.execSQL("insert into tblTeams select '" + teamName
                        + "','" + cursor2.getString(0) + "','"
                        + cursor2.getString(1) + "','"
                        + cursor2.getString(2).replace("'", "''") + "',0");
                cursor2.moveToNext();
            }
            cursor2.close();
        }

    }

    void sqlUpdateTeamName(String oldName, String newName) {
        oldName = oldName.replace("'", "''");
        newName = newName.replace("'", "''");
        database.execSQL("update tblTeams set TeamName='" + newName
                + "' where TeamName='" + oldName + "'");
    }

    void sqlAddStarter(String criteria) {
        database.execSQL("update tblCards set NumOwned=NumOwned+1 where Rarity='as' and CardName not like '%(Alt)' and CardName not like '%(Foil)' and CardSet in(" + criteria + ")");

        Cursor cursor = database
                .rawQuery("select CardSet, CharName, sum(1) from tblCards where Rarity='as' and CardName not like '%(Alt)' and CardName not like '%(Foil)' and CardSet in(" + criteria + ") group by CardSet, CharName", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            database.execSQL("update tblCards set DiceOwned=DiceOwned+case when CardSet='S1' then 1 when CardSet in('TMNT','HHS','AI') then 3 else 2 end where CardSet='" + cursor.getString(0).replace("'", "''") +
                    "' and CharName='" + cursor.getString(1).replace("'", "''") + "'");
            cursor.moveToNext();
        }
        cursor.close();
    }

    void sqlResetCards() {
        database.execSQL("update tblCards set NumOwned=0, DiceOwned=0");
        database.execSQL("update tblCards set NumFoilsOwned=0 where NumFoilsOwned > -1");
    }

    void sqlExportData(Context context) {
        Cursor cursor = database.rawQuery("select CardSet, CharName, CardName, NumOwned, NumFoilsOwned, DiceOwned from tblCards", null);
        FileOutputStream outputStream;


        if (!cursor.moveToFirst()) {
            Toast.makeText(context, "Huh?",
                    Toast.LENGTH_LONG).show();
        }
        cursor.moveToFirst();

        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "Transition Zone.csv");
        try {
            outputStream = new FileOutputStream(file);

            outputStream.write("Set".getBytes());
            outputStream.write(",".getBytes());
            outputStream.write("Character".getBytes());
            outputStream.write(",".getBytes());
            outputStream.write("Card Name".getBytes());
            outputStream.write(",".getBytes());
            outputStream.write("Cards Owned".getBytes());
            outputStream.write(",".getBytes());
            outputStream.write("Foils Owned".getBytes());
            outputStream.write(",".getBytes());
            outputStream.write("Dice Owned".getBytes());
            outputStream.write("\n".getBytes());

            do {

                outputStream.write(cursor.getString(0).getBytes());
                outputStream.write(",".getBytes());
                outputStream.write("\"".getBytes());
                outputStream.write(cursor.getString(1).getBytes());
                outputStream.write("\",\"".getBytes());
                outputStream.write(cursor.getString(2).getBytes());
                outputStream.write("\",".getBytes());
                outputStream.write(cursor.getString(3).getBytes());
                outputStream.write(",".getBytes());
                outputStream.write(cursor.getString(4).getBytes());
                outputStream.write(",".getBytes());
                outputStream.write(cursor.getString(5).getBytes());
                outputStream.write("\n".getBytes());


            } while (cursor.moveToNext());

            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {

            e.printStackTrace();
        }


        cursor.close();


        MediaScannerConnection.scanFile(context, new String[] {file.toString()}, null, null);
    }

    void sqlImportData(Context context) {

        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "Transition Zone.csv");
        FileInputStream is;


        try {
            is = new FileInputStream(file);

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            try {

                String line;
                int lineNum = 0;
                while ((line = reader.readLine()) != null) {
                    if (lineNum > 0) {
                        String[] RowData = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                        database.execSQL("update tblCards set NumOwned=" + RowData[3] + ", DiceOwned=" + RowData[5] + ", NumFoilsOwned=" + RowData[4] + " where CardSet='" + RowData[0].replace("'", "''").replace("\"", "") +
                                "' and CharName='" + RowData[1].replace("'", "''").replace("\"", "") + "' and CardName='" + RowData[2].replace("'", "''").replace("\"", "") + "'");
                    }
                    lineNum++;
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    void sqlBackup() {
        database.execSQL("delete from tblBackup");
        database.execSQL("insert into tblBackup select CardSet, CharName, CardName, NumOwned, DiceOwned, NumFoilsOwned from tblCards ");
    }

    void sqlRestore() {
        database.execSQL("update tblCards set DiceOwned=(select DiceOwned from tblBackup where CardSet=tblCards.CardSet and CharName=tblCards.CharName and CardName=tblCards.CardName), NumOwned=(select NumOwned from tblBackup where CardSet=tblCards.CardSet and CharName=tblCards.CharName and CardName=tblCards.CardName), NumFoilsOwned=(select NumFoilsOwned from tblBackup where CardSet=tblCards.CardSet and CharName=tblCards.CharName and CardName=tblCards.CardName) where exists (select DiceOwned from tblBackup where CardSet=tblCards.CardSet and CharName=tblCards.CharName and CardName=tblCards.CardName)");
        ;
    }
}
