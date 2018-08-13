package fluffyhairedkid.dicemasterscompanion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

public class CardCollector extends Activity {

    SQLDataSource datasource;

    public static List<String> groupList = new ArrayList<String>();
    List<String> childList;
    List<Integer> intChild;
    Map<String, List<String>> cardCollection;
    Map<String, List<String>> cardsOwnedCollection;
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
    ArrayList<Map<String, String>> masterList;
    ExpandableListAdapter expListAdapter;
    String viewType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_collector);

        datasource = new SQLDataSource(this);
        datasource.open();

        Bundle extras = getIntent().getExtras();
        viewType = extras.getString("view");

        setExpandableListView();

        Button searchCards = (Button) findViewById(R.id.btSearch);

        searchCards.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CardCollector.this, Search.class);
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

                Intent intent = new Intent(CardCollector.this, CardViewer.class);
                intent.putExtra("group", groupPosition);
                intent.putExtra("child", childPosition);
                startActivity(intent);
                return true;
            }

        });
    }

    private void createGroupList() {
        groupList.clear();

        String currentHero;
        String tempHero = "";
        for (Map<String, String> hero : masterList) {
            currentHero = hero.get("hero");
            if (!currentHero.equals(tempHero)) {
                groupList.add(currentHero);
            }
            tempHero = currentHero;
        }
    }

    private void createCollection(String key) {

        if (key.equals("card")) {
            cardCollection = new LinkedHashMap<String, List<String>>();
        } else if (key.equals("numOwned")) {
            cardsOwnedCollection = new LinkedHashMap<String, List<String>>();
        }

        String current;
        String temp = "";

        for (Map<String, String> hero : masterList) {

            current = hero.get("hero");
            if (current.equals(temp)) {
                childList.add(hero.get(key));
            } else {
                if (temp.equals("")) {
                    childList = new ArrayList<String>();
                    childList.add(hero.get(key));
                } else {
                    if (key.equals("card")) {
                        cardCollection.put(temp, childList);
                    } else if (key.equals("numOwned")) {
                        cardsOwnedCollection.put(temp, childList);
                    }

                    childList = new ArrayList<String>();
                    childList.add(hero.get(key));

                }
            }
            temp = current;
        }
        if (key.equals("card")) {
            cardCollection.put(temp, childList);
        } else if (key.equals("numOwned")) {
            cardsOwnedCollection.put(temp, childList);
        }
    }

    private void createDrawableCollection(String key) {

        if (key.equals("rarity")) {
            rarityCollection = new LinkedHashMap<String, List<Integer>>();
        } else if (key.equals("cost")) {
            costCollection = new LinkedHashMap<String, List<Integer>>();
        } else if (key.equals("affiliationOne")) {
            affiliationOneCollection = new LinkedHashMap<String, List<Integer>>();
        } else if (key.equals("affiliationTwo")) {
            affiliationTwoCollection = new LinkedHashMap<String, List<Integer>>();
        } else if (key.equals("energy")) {
            energyCollection = new LinkedHashMap<String, List<Integer>>();
        } else if (key.equals("image")) {
            imageCollection = new LinkedHashMap<String, List<Integer>>();
        }

        String current;
        String temp = "";

        for (Map<String, String> hero : masterList) {

            current = hero.get("hero");
            if (current.equals(temp)) {
                intChild.add(getResources().getIdentifier(hero.get(key),
                        "drawable", getPackageName()));
            } else {
                if (temp.equals("")) {
                    intChild = new ArrayList<Integer>();
                    intChild.add(getResources().getIdentifier(hero.get(key),
                            "drawable", getPackageName()));
                } else {
                    if (key.equals("rarity")) {
                        rarityCollection.put(temp, intChild);
                    } else if (key.equals("cost")) {
                        costCollection.put(temp, intChild);
                    } else if (key.equals("affiliationOne")) {
                        affiliationOneCollection.put(temp, intChild);
                    } else if (key.equals("affiliationTwo")) {
                        affiliationTwoCollection.put(temp, intChild);
                    } else if (key.equals("energy")) {
                        energyCollection.put(temp, intChild);
                    } else if (key.equals("image")) {
                        imageCollection.put(temp, intChild);
                    }

                    intChild = new ArrayList<Integer>();
                    intChild.add(getResources().getIdentifier(hero.get(key),
                            "drawable", getPackageName()));

                }
            }
            temp = current;

        }

        if (key.equals("rarity")) {
            rarityCollection.put(temp, intChild);
        } else if (key.equals("cost")) {
            costCollection.put(temp, intChild);
        } else if (key.equals("affiliationOne")) {
            affiliationOneCollection.put(temp, intChild);
        } else if (key.equals("affiliationTwo")) {
            affiliationTwoCollection.put(temp, intChild);
        } else if (key.equals("energy")) {
            energyCollection.put(temp, intChild);
        } else if (key.equals("image")) {
            imageCollection.put(temp, intChild);
        }

    }

    private void createDieImageCollection() {

        dieImageCollection = new LinkedHashMap<String, Integer>();
        String current;
        String temp = "";
        int dieImage = 0;

        for (Map<String, String> hero : masterList) {

            current = hero.get("hero");
            if (!current.equals(temp)) {
                if (temp.equals("")) {
                    dieImage = getResources().getIdentifier(hero.get("die"),
                            "drawable", getPackageName());
                } else {
                    dieImageCollection.put(temp, dieImage);
                    dieImage = getResources().getIdentifier(hero.get("die"),
                            "drawable", getPackageName());
                }
            }

            temp = current;

        }
        dieImageCollection.put(temp, dieImage);
    }

    private void createDiceOwnedCollection() {

        diceOwnedCollection = new LinkedHashMap<String, Integer>();
        String current;
        String temp = "";
        int diceOwned = 0;

        for (Map<String, String> hero : masterList) {

            current = hero.get("hero");
            if (!current.equals(temp)) {
                if (temp.equals("")) {
                    diceOwned = Integer.valueOf(hero.get("diceOwned"));
                } else {
                    diceOwnedCollection.put(temp, diceOwned);
                    diceOwned = Integer.valueOf(hero.get("diceOwned"));
                }
            }

            temp = current;

        }
        diceOwnedCollection.put(temp, diceOwned);
    }

    private void createSetCollection(String key) {
        if (key.equals("set")) {
            setCollection = new LinkedHashMap<String, String>();
        } else if (key.equals("tblName")) {
            tblNameCollection = new LinkedHashMap<String, String>();
        }

        String current;
        String temp = "";
        String text = "";

        for (Map<String, String> hero : masterList) {

            current = hero.get("hero");
            if (!current.equals(temp)) {
                if (temp.equals("")) {
                    text = hero.get(key);
                } else {
                    if (key.equals("set")) {
                        setCollection.put(temp, text);
                        text = hero.get(key);
                    } else if (key.equals("tblName")) {
                        tblNameCollection.put(temp, text);
                        text = hero.get(key);
                    }
                }
            }

            temp = current;

        }
        if (key.equals("set")) {
            setCollection.put(temp, text);
        } else if (key.equals("tblName")) {
            tblNameCollection.put(temp, text);
        }
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
            editor.commit();
            expListAdapter.notifyDataSetChanged();
        }
    }

    public void setExpandableListView() {
        masterList = MainActivity.masterList;

        createGroupList();

        createCollection("card");
        createCollection("umOwned");
        createSetCollection("set");
        createSetCollection("tblName");
        createDieImageCollection();
        createDiceOwnedCollection();
        createDrawableCollection("rarity");
        createDrawableCollection("cost");
        createDrawableCollection("energy");
        createDrawableCollection("affiliationOne");
        createDrawableCollection("affiliationTwo");
        createDrawableCollection("image");


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
                    cardsOwnedCollection, cardsOwnedCollection, teamDiceCollection, setCollection,
                    tblNameCollection);

        } else {
            expListAdapter = new ExpandableListAdapter(this, viewType,
                    groupList, cardCollection, rarityCollection,
                    costCollection, affiliationOneCollection,
                    affiliationTwoCollection, energyCollection, dieImageCollection,
                    cardsOwnedCollection, cardsOwnedCollection, diceOwnedCollection, setCollection,
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
            ListBuilder cardList = new ListBuilder(CardCollector.this,
                    getPackageName());
            MainActivity.masterList = cardList
                    .buildList(datasource
                            .sqlGetTeamList(MainActivity.selectedTeam));
            setExpandableListView();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        datasource.close();
    }
}