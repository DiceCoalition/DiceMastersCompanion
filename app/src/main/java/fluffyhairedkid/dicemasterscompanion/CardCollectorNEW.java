package fluffyhairedkid.dicemasterscompanion;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CardCollectorNEW extends Activity {

    SQLDataSource datasource;

    public static List<String> groupList = new ArrayList<String>();
    //List<String> childList;
    //List<Integer> intChild;
    Map<String, List<String>> cardCollection;
    public static Map<String, List<String>> cardImageNames;
    Map<String, List<String>> cardsOwnedCollection;
    Map<String, List<String>> foilsOwnedCollection;
    Map<String, String> setCollection;
    Map<String, String> tblNameCollection;
    Map<String, List<Integer>> rarityCollection;
    Map<String, List<Integer>> costCollection;
    Map<String, List<Integer>> affiliationOneCollection;
    Map<String, List<Integer>> affiliationTwoCollection;
    Map<String, List<Integer>> energyCollection;
    public static Map<String, List<Integer>> imageCollection;
    Map<String, Integer> dieImageCollection;
    Map<String, Integer> diceOwnedCollection;
    HashMap<String, Integer> teamDiceCollection;
    ExpandableListView expListView;
    //ArrayList<Map<String, String>> masterList;
    ExpandableListAdapter expListAdapter;
    String viewType = "";
    String whereCriteria = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_collector);

        SharedPreferences prefs = getSharedPreferences("MyPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("addOrRemove", 0);
        editor.apply();

        datasource = new SQLDataSource(this);
        datasource.open();

        Bundle extras = getIntent().getExtras();
        viewType = extras.getString("view");
        whereCriteria = extras.getString("whereCriteria");

        setExpandableListView();

        Button searchCards = (Button) findViewById(R.id.btSearch);

        searchCards.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CardCollectorNEW.this, Search.class);
                intent.putExtra("view", viewType);
                startActivity(intent);

            }

        });

        if (!"team viewer".equals(viewType)) {
            searchCards.setVisibility(View.GONE);
        }

        expListView.setOnChildClickListener(new OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                Intent intent = new Intent(CardCollectorNEW.this, CardViewer.class);
                intent.putExtra("group", groupPosition);
                intent.putExtra("child", childPosition);
                startActivity(intent);
                return true;
            }

        });

    }

    public void assignListAdapter(ExpandableListAdapter listAdapter) {
        expListAdapter = listAdapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all_cards, menu);
        SharedPreferences prefs = getSharedPreferences("MyPrefs", 0);
        if (prefs.getLong("tieDiceToCards", 0) == 1) {
            menu.findItem(R.id.tieDice).setChecked(true);
        }
        if (prefs.getLong("addOrRemove", 0) == 1) {
            menu.findItem(R.id.massAdd).setChecked(true);
        } else if (prefs.getLong("addOrRemove", 0) == -1) {
            menu.findItem(R.id.massRemove).setChecked(true);
        }
        if (viewType.equals("team builder") || viewType.equals(("team viewer"))) {
            menu.getItem(2).setVisible(false);
            menu.getItem(3).setVisible(false);
            menu.getItem(4).setVisible(false);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.collapse:
                collapseList();
                return true;
            case R.id.expand:
                expandList();
                return true;
            case R.id.tieDice:
                boolean checked = item.isChecked();
                if (checked) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                tieDice(checked);
                return true;
            case R.id.massAdd:
                boolean addChecked = item.isChecked();
                if (addChecked) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                massAdd(addChecked);
                return true;
            case R.id.massRemove:
                boolean removeChecked = item.isChecked();
                if (removeChecked) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                massRemove(removeChecked);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void collapseList() {
        int count = expListAdapter.getGroupCount();
        for (int position = 1; position <= count; position++)
            expListView.collapseGroup(position - 1);
        MainActivity.collapseSetting = 1;
    }

    public void expandList() {
        int count = expListAdapter.getGroupCount();
        for (int position = 1; position <= count; position++)
            expListView.expandGroup(position - 1);
        MainActivity.collapseSetting = 0;
    }

    public void tieDice(boolean checked) {
        SharedPreferences prefs = getSharedPreferences("MyPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        if (!checked) {
            editor.putLong("tieDiceToCards", 1);
            editor.apply();
            expListAdapter.notifyDataSetChanged();
        } else {
            editor.putLong("tieDiceToCards", 0);
            editor.apply();
            expListAdapter.notifyDataSetChanged();
        }
    }

    public void massAdd(boolean checked) {
        SharedPreferences prefs = getSharedPreferences("MyPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        if (!checked) {
            editor.putLong("addOrRemove", 1);
            editor.apply();
            expListAdapter.notifyDataSetChanged();
        } else {
            editor.putLong("addOrRemove", 0);
            editor.apply();
            expListAdapter.notifyDataSetChanged();
        }
        invalidateOptionsMenu();
    }

    public void massRemove(boolean checked) {
        SharedPreferences prefs = getSharedPreferences("MyPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        if (!checked) {
            editor.putLong("addOrRemove", -1);
            editor.apply();
            expListAdapter.notifyDataSetChanged();
        } else {
            editor.putLong("addOrRemove", 0);
            editor.apply();
            expListAdapter.notifyDataSetChanged();
        }
        invalidateOptionsMenu();
    }

    public void setExpandableListView() {
        String orderCriteria = "case when DieImage='basic' then 0 else 1 end, CharName, CardSet, Rarity, CardName";

        groupList = datasource.sqlGetCharList(whereCriteria, orderCriteria);
        cardCollection = datasource.sqlGetCardsList("CardName", whereCriteria, orderCriteria);
        cardsOwnedCollection = datasource.sqlGetCardsList("NumOwned", whereCriteria, orderCriteria);
        foilsOwnedCollection = datasource.sqlGetCardsList("NumFoilsOwned", whereCriteria, orderCriteria);
        rarityCollection = datasource.sqlGetImagesList(this, "Rarity", whereCriteria, orderCriteria);
        costCollection = datasource.sqlGetImagesList(this, "Cost", whereCriteria, orderCriteria);
        energyCollection = datasource.sqlGetImagesList(this, "Energy", whereCriteria, orderCriteria);
        affiliationOneCollection = datasource.sqlGetImagesList(this, "AffiliationOne", whereCriteria, orderCriteria);
        affiliationTwoCollection = datasource.sqlGetImagesList(this, "AffiliationTwo", whereCriteria, orderCriteria);
        imageCollection = datasource.sqlGetImagesList(this, "CardImage", whereCriteria, orderCriteria);
        dieImageCollection = datasource.sqlGetCharAttribList(this, "DieImage", whereCriteria, orderCriteria);
        diceOwnedCollection = datasource.sqlGetCharAttribList(this, "max(DiceOwned)", whereCriteria, orderCriteria);
        setCollection = datasource.sqlGetTableInfoList("CardSet", whereCriteria, orderCriteria);
        tblNameCollection = datasource.sqlGetTableInfoList("tblCards.CharName", whereCriteria, orderCriteria);
        cardImageNames = datasource.sqlGetCardsList("CardImage", whereCriteria, orderCriteria);
        expListView = (ExpandableListView) findViewById(R.id.elvCardList);
        final ExpandableListAdapter expListAdapter;

        if (viewType.equals("team viewer")) {
            teamDiceCollection = datasource.sqlGetTeamDice(
                    "where TeamName='"
                            + MainActivity.selectedTeam.replace("'", "''")
                            + "'", "CharName");
            expListAdapter = new ExpandableListAdapter(this, viewType,
                    groupList, cardCollection, rarityCollection,
                    costCollection, affiliationOneCollection,
                    affiliationTwoCollection, energyCollection, dieImageCollection,
                    cardsOwnedCollection, foilsOwnedCollection, teamDiceCollection, setCollection,
                    tblNameCollection);
        } else {
            expListAdapter = new ExpandableListAdapter(this, viewType,
                    groupList, cardCollection, rarityCollection,
                    costCollection, affiliationOneCollection,
                    affiliationTwoCollection, energyCollection, dieImageCollection,
                    cardsOwnedCollection, foilsOwnedCollection, diceOwnedCollection, setCollection,
                    tblNameCollection);
        }
        expListView.setAdapter(expListAdapter);
        assignListAdapter(expListAdapter);
        if (MainActivity.collapseSetting == 0) {
            expandList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        datasource.open();
        if (viewType.equals("team viewer")) {
            //ListBuilder cardList = new ListBuilder(CardCollectorNEW.this,getPackageName());
            //MainActivity.masterList = cardList.buildList(datasource.sqlGetTeamList(MainActivity.selectedTeam));
            whereCriteria = datasource.sqlGetTeamList(MainActivity.selectedTeam);
            setExpandableListView();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        datasource.close();
    }
}