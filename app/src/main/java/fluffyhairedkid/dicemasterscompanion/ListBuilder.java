package fluffyhairedkid.dicemasterscompanion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;

public class ListBuilder extends Activity {

	SQLDataSource datasource;
	
	
	private Activity context;
	private String packageName;

	public ListBuilder(Activity context, String packageName) {
		this.context = context;
		this.packageName = packageName;
	}

	private HashMap<String, String> putData(String set, String hero,
			String card, String die, String image, String cost,
			String affiliationOne, String affiliationTwo, String energy,
			String rarity, String numOwned, String diceOwned, String tblName) {
		HashMap<String, String> item = new HashMap<String, String>();
		item.put("set", set);
		item.put("hero", hero);
		item.put("card", card);
		item.put(
				"die",
				Integer.toString(context.getResources().getIdentifier(die,
						"drawable", packageName)));
		item.put(
				"image",
				Integer.toString(context.getResources().getIdentifier(image,
						"drawable", packageName)));
		item.put(
				"cost",
				Integer.toString(context.getResources().getIdentifier(cost,
						"drawable", packageName)));
		item.put(
				"affiliationOne",
				Integer.toString(context.getResources().getIdentifier(
						affiliationOne, "drawable", packageName)));
		item.put(
				"affiliationTwo",
				Integer.toString(context.getResources().getIdentifier(
						affiliationTwo, "drawable", packageName)));
		item.put(
				"energy",
				Integer.toString(context.getResources().getIdentifier(energy,
						"drawable", packageName)));
		item.put(
				"rarity",
				Integer.toString(context.getResources().getIdentifier(rarity,
						"drawable", packageName)));
		item.put("numOwned", numOwned);
		item.put("diceOwned", diceOwned);
		item.put("tblName", tblName);

		return item;
	}

	public ArrayList<Map<String, String>> buildList(String whereCriteria) {
		ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
		ArrayList<ArrayList<String>> query = new ArrayList<ArrayList<String>>();
		ArrayList<String> setName = new ArrayList<String>();
		ArrayList<String> charName = new ArrayList<String>();
		ArrayList<String> cardName = new ArrayList<String>();
		ArrayList<String> dieImage = new ArrayList<String>();
		ArrayList<String> cardImage = new ArrayList<String>();
		ArrayList<String> cost = new ArrayList<String>();
		ArrayList<String> affiliationOne = new ArrayList<String>();
		ArrayList<String> affiliationTwo = new ArrayList<String>();
		ArrayList<String> energy = new ArrayList<String>();
		ArrayList<String> rarity = new ArrayList<String>();
		ArrayList<String> numOwned = new ArrayList<String>();
		ArrayList<String> diceOwned = new ArrayList<String>();
		ArrayList<String> tblName = new ArrayList<String>();

		datasource = new SQLDataSource(context);
		datasource.open();
		query = datasource
				.sqlGetCardList(this, whereCriteria,
						"case when DieImage='basic' then 0 else 1 end, CharName, CardSet, Rarity, CardName");
		datasource.close();

		setName = query.get(0);
		charName = query.get(1);
		cardName = query.get(2);
		dieImage = query.get(3);
		cardImage = query.get(4);
		cost = query.get(5);
		affiliationOne = query.get(6);
		affiliationTwo = query.get(7);
		energy = query.get(8);
		rarity = query.get(9);
		numOwned = query.get(10);
		diceOwned = query.get(11);
		tblName = query.get(12);

		int count = charName.size();
		for (int position = 1; position <= count; position++)
			list.add(putData(setName.get(position - 1),
					charName.get(position - 1), cardName.get(position - 1),
					dieImage.get(position - 1), cardImage.get(position - 1),
					cost.get(position - 1), affiliationOne.get(position - 1),
					affiliationTwo.get(position - 1), energy.get(position - 1),
					rarity.get(position - 1), numOwned.get(position - 1),
					diceOwned.get(position - 1), tblName.get(position - 1)));

		return list;
	}
}
