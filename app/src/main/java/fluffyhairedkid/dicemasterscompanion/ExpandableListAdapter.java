package fluffyhairedkid.dicemasterscompanion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    SQLDataSource datasource;

    private Activity context;
    private Map<String, List<String>> cardCollection;
    private Map<String, List<String>> cardsOwnedCollection;
    private Map<String, List<String>> foilsOwnedCollection;
    private Map<String, String> setCollection;
    private Map<String, String> tblNameCollection;
    private Map<String, List<Integer>> rarityCollection;
    private Map<String, List<Integer>> costCollection;
    private Map<String, List<Integer>> affiliationOneCollection;
    private Map<String, List<Integer>> affiliationTwoCollection;
    private Map<String, List<Integer>> energyCollection;
    private Map<String, Integer> dieImageCollection;
    private Map<String, Integer> diceOwnedCollection;
    private List<String> cards;
    private int tempInt;
    private int foilsInt;
    private String viewType;


    public ExpandableListAdapter(Activity context, String viewType,
                                 List<String> cards,
                                 Map<String, List<String>> cardCollection,
                                 Map<String, List<Integer>> rarityCollection,
                                 Map<String, List<Integer>> costCollection,
                                 Map<String, List<Integer>> affiliationOneCollection,
                                 Map<String, List<Integer>> affiliationTwoCollection,
                                 Map<String, List<Integer>> energyCollection,
                                 Map<String, Integer> dieImageCollection,
                                 Map<String, List<String>> cardsOwnedCollection,
                                 Map<String, List<String>> foilsOwnedCollection,
                                 Map<String, Integer> diceOwnedCollection,
                                 Map<String, String> setCollection,
                                 Map<String, String> tblNameCollection) {
        this.context = context;
        this.viewType = viewType;
        this.cardCollection = cardCollection;
        this.cardsOwnedCollection = cardsOwnedCollection;
        this.foilsOwnedCollection = foilsOwnedCollection;
        this.setCollection = setCollection;
        this.tblNameCollection = tblNameCollection;
        this.rarityCollection = rarityCollection;
        this.costCollection = costCollection;
        this.affiliationOneCollection = affiliationOneCollection;
        this.affiliationTwoCollection = affiliationTwoCollection;
        this.energyCollection = energyCollection;
        this.dieImageCollection = dieImageCollection;
        this.diceOwnedCollection = diceOwnedCollection;
        this.cards = cards;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return cardCollection.get(cards.get(groupPosition)).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        datasource = new SQLDataSource(context);

        final String card = (String) getChild(groupPosition, childPosition);
        final int rarity = rarityCollection.get(
                cards.get(groupPosition)).get(childPosition);
        final int cost = costCollection.get(cards.get(groupPosition))
                .get(childPosition);
        final int affiliationone = affiliationOneCollection.get(
                cards.get(groupPosition)).get(childPosition);
        final int affiliationtwo = affiliationTwoCollection.get(
                cards.get(groupPosition)).get(childPosition);
        final int energy = energyCollection.get(
                cards.get(groupPosition)).get(childPosition);
        tempInt = Integer.valueOf((cardsOwnedCollection.get(cards
                .get(groupPosition)).get(childPosition)));
        foilsInt = Integer.valueOf((foilsOwnedCollection.get(cards
                .get(groupPosition)).get(childPosition)));

        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_row, null);
        }

        final Button numCards = (Button) convertView
                .findViewById(R.id.btCardOwned);
        final Button numFoils = (Button) convertView
                .findViewById(R.id.btFoilOwned);

        numFoils.setVisibility(View.VISIBLE);
        if (viewType.equals("team builder")) {
            numCards.setText(String.valueOf("Add"));
            numFoils.setVisibility(View.GONE);
        } else if (viewType.equals("team viewer")) {
            numCards.setText(String.valueOf("Del"));
            numFoils.setVisibility(View.GONE);
        } else {
            if(tempInt >= 0) {
                numCards.setText(String.valueOf(tempInt));
            }else{
                //Hiding the button caused layout issues
                numCards.setVisibility(View.INVISIBLE);
                //RelativeLayout.LayoutParams rel_bottone = new RelativeLayout.LayoutParams(0, 30);
                //numCards.setLayoutParams(rel_bottone);
                //numCards.setWidth(0);
            }
            if (foilsInt >= 0) {
                numFoils.setText(String.valueOf(foilsInt));
            } else {
                numFoils.setVisibility(View.GONE);
            }
        }

        TextView item = (TextView) convertView.findViewById(R.id.tvCardName);
        ImageView rarityImage = (ImageView) convertView
                .findViewById(R.id.ivRarity);
        ImageView costImage = (ImageView) convertView.findViewById(R.id.ivCost);
        ImageView affilOneImage = (ImageView) convertView
                .findViewById(R.id.ivAffilOne);
        ImageView affilTwoImage = (ImageView) convertView
                .findViewById(R.id.ivAffilTwo);
        ImageView energyImage = (ImageView) convertView
                .findViewById(R.id.ivEnergy);

        numCards.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {

                int tempInt = Integer.valueOf(numCards.getText().toString());
                if (tempInt >= 0) {
                    int newc = tempInt + 1;
                    updateCards(groupPosition, childPosition, card, newc, newc);
                    numCards.setText(String.valueOf(newc));
                }
                return true;
            }
        });
        numCards.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                String charName = cards.get(groupPosition);
                final SharedPreferences prefs = context.getSharedPreferences("MyPrefs", 0);

                if (viewType.equals("team builder")) {
                    datasource.open();
                    datasource.sqlAddTeamCard(MainActivity.selectedTeam,
                            tblNameCollection.get(charName), card,
                            setCollection.get(charName));
                    datasource.close();

                    Toast.makeText(context, "Card added to team",
                            Toast.LENGTH_SHORT).show();

                } else if (viewType.equals("team viewer")) {
                    datasource.open();
                    datasource.sqlDeleteTeamCard(MainActivity.selectedTeam,
                            tblNameCollection.get(charName), card,
                            setCollection.get(charName));
                    datasource.close();

                    Toast.makeText(context, "Card removed from team",
                            Toast.LENGTH_SHORT).show();

                    List<String> tempList = cardCollection.get(charName);
                    tempList.remove(childPosition);
                    if (tempList.size() > 0) {
                        cardCollection.put(charName, tempList);
                    } else {
                        cards.remove(groupPosition);
                    }

                    notifyDataSetChanged();
                } else {
                    if (prefs.getLong("addOrRemove", 0) == 1) {
                        tempInt = Integer.valueOf(numCards.getText().toString()) + 1;
                        int newDice = diceOwnedCollection.get(cards.get(groupPosition)) + 1;
                        numCards.setText(String.valueOf(tempInt));
                        updateCards(groupPosition, childPosition, card, tempInt, newDice);
                    } else if (prefs.getLong("addOrRemove", 0) == -1) {
                        tempInt = Integer.valueOf(numCards.getText().toString()) - 1;
                        if (tempInt >= 0) {
                            int newDice = diceOwnedCollection.get(cards.get(groupPosition)) - 1;
                            if (newDice < 0) {
                                newDice = 0;
                            }
                            numCards.setText(String.valueOf(tempInt));
                            updateCards(groupPosition, childPosition, card, tempInt, newDice);
                        }
                    } else {
                        dialog.setContentView(R.layout.custom_dialog);
                        dialog.setTitle(card);

                        final TextView text = (TextView) dialog
                                .findViewById(R.id.tvCount);
                        tempInt = Integer.valueOf(numCards.getText().toString());
                        final int origInt = tempInt;
                        text.setText(String.valueOf(tempInt));

                        Button minus = (Button) dialog.findViewById(R.id.btMinus);
                        minus.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                if (tempInt > 0) {
                                    tempInt = tempInt - 1;
                                }
                                text.setText(String.valueOf(tempInt));
                            }
                        });

                        Button plus = (Button) dialog.findViewById(R.id.btPlus);
                        plus.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                tempInt++;
                                text.setText(String.valueOf(tempInt));
                            }
                        });

                        Button save = (Button) dialog.findViewById(R.id.btSave);
                        save.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                //if (prefs.getLong("tieDiceToCards", 0) == 1) {
                                int currentDice = diceOwnedCollection.get(cards.get(groupPosition));
                                int diceChange = (tempInt - origInt);
                                int newDice = 0;

                                if (currentDice + diceChange > 0) {
                                    newDice = currentDice + diceChange;
                                }
                                /*
                                    diceOwnedCollection.put(cards.get(groupPosition),
                                            newDice);
                                    datasource.open();
                                    datasource.sqlUpdateNumDice(newDice,
                                            tblNameCollection.get(cards
                                                    .get(groupPosition)), setCollection
                                                    .get(cards.get(groupPosition)));
                                    datasource.close();
                                    notifyDataSetChanged();
                                }*/

                                numCards.setText(String.valueOf(tempInt));
                                updateCards(groupPosition, childPosition, card, tempInt, newDice);
                                /*List<String> tempList = new ArrayList<String>();
                                tempList = cardsOwnedCollection.get(cards
                                        .get(groupPosition));
                                tempList.remove(childPosition);
                                tempList.add(childPosition, String.valueOf(tempInt));
                                cardsOwnedCollection.put(cards.get(groupPosition),
                                        tempList);
                                datasource.open();
                                datasource
                                        .sqlUpdateNumCards(tempInt,
                                                tblNameCollection.get(cards
                                                        .get(groupPosition)), card,
                                                setCollection.get(cards
                                                        .get(groupPosition)));
                                datasource.close();*/
                                dialog.dismiss();
                            }
                        });

                        Button cancel = (Button) dialog.findViewById(R.id.btCancel);
                        cancel.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        dialog.show();
                    }
                }
            }
        });

        numFoils.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                int tempIntF = Integer.valueOf(numFoils.getText().toString());
                if (tempIntF >= 0) {
                    int newF = tempIntF + 1;
                    updateFoils(groupPosition, childPosition, card, newF, newF);
                    numFoils.setText(String.valueOf(newF));
                }
                return true;
            }
        });
        numFoils.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final SharedPreferences prefs = context.getSharedPreferences("MyPrefs", 0);

                if (prefs.getLong("addOrRemove", 0) == 1) {
                    tempInt = Integer.valueOf(numFoils.getText().toString()) + 1;
                    int newDice = diceOwnedCollection.get(cards.get(groupPosition)) + 1;
                    numFoils.setText(String.valueOf(tempInt));
                    updateFoils(groupPosition, childPosition, card, tempInt, newDice);
                } else if (prefs.getLong("addOrRemove", 0) == -1) {
                    tempInt = Integer.valueOf(numFoils.getText().toString()) - 1;
                    if (tempInt >= 0) {
                        int newDice = diceOwnedCollection.get(cards.get(groupPosition)) - 1;
                        if (newDice < 0) {
                            newDice = 0;
                        }
                        numFoils.setText(String.valueOf(tempInt));
                        updateFoils(groupPosition, childPosition, card, tempInt, newDice);
                    }
                } else {
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.custom_dialog);
                    dialog.setTitle(card + "");

                    final TextView text = (TextView) dialog
                            .findViewById(R.id.tvCount);
                    tempInt = Integer.valueOf(numFoils.getText().toString());
                    final int origInt = tempInt;
                    text.setText(String.valueOf(tempInt));

                    Button minus = (Button) dialog.findViewById(R.id.btMinus);
                    minus.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if (tempInt > 0) {
                                tempInt = tempInt - 1;
                            }
                            text.setText(String.valueOf(tempInt));
                        }
                    });

                    Button plus = (Button) dialog.findViewById(R.id.btPlus);
                    plus.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            tempInt++;
                            text.setText(String.valueOf(tempInt));
                        }
                    });

                    Button save = (Button) dialog.findViewById(R.id.btSave);
                    save.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            //if (prefs.getLong("tieDiceToCards", 0) == 1) {
                            int currentDice = diceOwnedCollection.get(cards.get(groupPosition));
                            int diceChange = (tempInt - origInt);
                            int newDice = 0;

                            if (currentDice + diceChange > 0) {
                                newDice = currentDice + diceChange;
                            }
                             /*   updateDice(groupPosition, newDice);

                            diceOwnedCollection.put(cards.get(groupPosition),
                                    newDice);
                            datasource.open();
                            datasource.sqlUpdateNumDice(newDice,
                                    tblNameCollection.get(cards
                                            .get(groupPosition)), setCollection
                                            .get(cards.get(groupPosition)));
                            datasource.close();

                                notifyDataSetChanged();*/


                            numFoils.setText(String.valueOf(tempInt));
                            updateFoils(groupPosition, childPosition, card, tempInt, newDice);
                            /*List<String> tempList;
                            tempList = foilsOwnedCollection.get(cards
                                    .get(groupPosition));
                            tempList.remove(childPosition);
                            tempList.add(childPosition, String.valueOf(tempInt));
                            foilsOwnedCollection.put(cards.get(groupPosition),
                                    tempList);
                            datasource.open();
                            datasource
                                    .sqlUpdateNumFoils(tempInt,
                                            tblNameCollection.get(cards
                                                    .get(groupPosition)), card,
                                            setCollection.get(cards
                                                    .get(groupPosition)));
                            datasource.close();*/
                            dialog.dismiss();

                        }
                    });

                    Button cancel = (Button) dialog.findViewById(R.id.btCancel);
                    cancel.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            }
        });

        item.setText(card);
        rarityImage.setImageResource(rarity);
        costImage.setImageResource(cost);
        affilOneImage.setImageResource(affiliationone);
        affilTwoImage.setImageResource(affiliationtwo);
        energyImage.setImageResource(energy);

        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return cardCollection.get(cards.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return cards.get(groupPosition);
    }

    public int getGroupCount() {
        return cards.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        datasource = new SQLDataSource(context);

        String cardName = (String) getGroup(groupPosition);
        cardName = cardName.replace("(S1)", "(YGO)");
        cardName = cardName.replace("(UX)", "(UXM)");
        cardName = cardName.replace("(GATF)", "(GAF)");
        cardName = cardName.replace("(DS)", "(DrS)");
        cardName = cardName.replace("(IMWM)", "(IMW)");
        cardName = cardName.replace("(TD)", "(Def)");
        cardName = cardName.replace("(BM)", "(BAT)");
        cardName = cardName.replace("(SMMC)", "(SMC)");
        cardName = cardName.replace("(XFMC)", "(XFC)");
        cardName = cardName.replace("(ToA)", "(TOA)");
        cardName = cardName.replace("(TMT)", "(THOR)");

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.parent_row, null);
        }

        final Button numDice = (Button) convertView
                .findViewById(R.id.btDiceOwned);
        TextView item = (TextView) convertView.findViewById(R.id.tvHeroName);
        ImageView image = (ImageView) convertView.findViewById(R.id.ivDieImage);
        //TODO: NULL REFERENCE EXCEPTION BEING THROWN HERE FOR SOME USERS!
        //diceOwnedCollection null?
        //cards null?
        if (null == cards || cards.isEmpty() || null ==  diceOwnedCollection ||  diceOwnedCollection.isEmpty()) {
            return convertView;
        }
        tempInt = diceOwnedCollection.get(cards.get(groupPosition));


        if (tblNameCollection.get(cards.get(groupPosition)).equals(
                "Basic Action Card")) {
            numDice.setVisibility(View.INVISIBLE);
        } else {
            numDice.setVisibility(View.VISIBLE);
        }

        numDice.setText(String.valueOf(tempInt));
        image.setImageResource(dieImageCollection.get(cards.get(groupPosition)));
        item.setTypeface(null, Typeface.BOLD);
        item.setText(cardName);

        numDice.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                int tempIntF = Integer.valueOf(numDice.getText().toString());
                if (tempIntF >= 0) {
                    int newF = tempIntF + 1;
                    updateDice(groupPosition, newF);
                    numDice.setText(String.valueOf(newF));
                }
                return true;
            }
        });
        numDice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences prefs = context.getSharedPreferences("MyPrefs", 0);

                if (prefs.getLong("addOrRemove", 0) == 1) {
                    tempInt = Integer.valueOf(numDice.getText().toString()) + 1;
                    numDice.setText(String.valueOf(tempInt));
                    updateDice(groupPosition, tempInt);
                } else if (prefs.getLong("addOrRemove", 0) == -1) {
                    tempInt = Integer.valueOf(numDice.getText().toString()) - 1;
                    if (tempInt >= 0) {
                        numDice.setText(String.valueOf(tempInt));
                        updateDice(groupPosition, tempInt);
                    }
                } else {
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.custom_dialog);
                    dialog.setTitle(cards.get(groupPosition));

                    final TextView text = (TextView) dialog
                            .findViewById(R.id.tvCount);
                    tempInt = Integer.valueOf(numDice.getText().toString());
                    text.setText(String.valueOf(tempInt));

                    Button minus = (Button) dialog.findViewById(R.id.btMinus);
                    minus.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            if (tempInt > 0) {
                                tempInt = tempInt - 1;
                            }
                            text.setText(String.valueOf(tempInt));
                        }
                    });

                    Button plus = (Button) dialog.findViewById(R.id.btPlus);
                    plus.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            tempInt = tempInt + 1;
                            text.setText(String.valueOf(tempInt));
                        }
                    });

                    Button save = (Button) dialog.findViewById(R.id.btSave);
                    save.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            numDice.setText(String.valueOf(tempInt));
                            updateDice(groupPosition, tempInt);
                        /*diceOwnedCollection.put(cards.get(groupPosition),
                                tempInt);
                        if (viewType.equals("team viewer")) {
                            datasource.open();
                            datasource.sqlUpdateTeamDice(tempInt,
                                    tblNameCollection.get(cards
                                            .get(groupPosition)), setCollection
                                            .get(cards.get(groupPosition)),
                                    MainActivity.selectedTeam);
                            datasource.close();
                        } else {
                            datasource.open();
                            datasource.sqlUpdateNumDice(tempInt,
                                    tblNameCollection.get(cards
                                            .get(groupPosition)), setCollection
                                            .get(cards.get(groupPosition)));
                            datasource.close();
                        }*/
                            dialog.dismiss();
                        }
                    });

                    Button cancel = (Button) dialog.findViewById(R.id.btCancel);
                    cancel.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            }
        });

        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void updateDice(int groupPosition, int number) {
        diceOwnedCollection.put(cards.get(groupPosition),
                number);
        if (viewType.equals("team viewer")) {
            datasource.open();
            datasource.sqlUpdateTeamDice(number,
                    tblNameCollection.get(cards
                            .get(groupPosition)), setCollection
                            .get(cards.get(groupPosition)),
                    MainActivity.selectedTeam);
            datasource.close();
        } else {
            datasource.open();
            datasource.sqlUpdateNumDice(number,
                    tblNameCollection.get(cards
                            .get(groupPosition)), setCollection
                            .get(cards.get(groupPosition)));
            datasource.close();
        }
    }

    public void updateCards(int groupPosition, int childPosition, String card, int number, int dieChange) {
        SharedPreferences prefs = context.getSharedPreferences("MyPrefs", 0);

        if (prefs.getLong("tieDiceToCards", 0) == 1) {
            updateDice(groupPosition, dieChange);
            notifyDataSetChanged();
        }

        List<String> tempList = new ArrayList<String>();
        tempList = cardsOwnedCollection.get(cards
                .get(groupPosition));
        tempList.remove(childPosition);
        tempList.add(childPosition, String.valueOf(number));
        cardsOwnedCollection.put(cards.get(groupPosition),
                tempList);
        datasource.open();
        datasource
                .sqlUpdateNumCards(number,
                        tblNameCollection.get(cards
                                .get(groupPosition)), card,
                        setCollection.get(cards
                                .get(groupPosition)));
        datasource.close();
    }

    public void updateFoils(int groupPosition, int childPosition, String card, int number, int dieChange) {
        SharedPreferences prefs = context.getSharedPreferences("MyPrefs", 0);

        if (prefs.getLong("tieDiceToCards", 0) == 1) {
            updateDice(groupPosition, dieChange);
            notifyDataSetChanged();
        }

        List<String> tempList;
        tempList = foilsOwnedCollection.get(cards
                .get(groupPosition));
        tempList.remove(childPosition);
        tempList.add(childPosition, String.valueOf(number));
        foilsOwnedCollection.put(cards.get(groupPosition),
                tempList);
        datasource.open();
        datasource
                .sqlUpdateNumFoils(number,
                        tblNameCollection.get(cards
                                .get(groupPosition)), card,
                        setCollection.get(cards
                                .get(groupPosition)));
        datasource.close();
    }

}
