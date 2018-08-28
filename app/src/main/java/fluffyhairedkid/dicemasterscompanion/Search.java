package fluffyhairedkid.dicemasterscompanion;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Search extends Activity {

    String viewType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        final SQLDataSource datasource;
        datasource = new SQLDataSource(this);

        Bundle extras = getIntent().getExtras();
        viewType = extras.getString("view");

        final CheckBox uniMarvel = (CheckBox) findViewById(R.id.cb20);
        final CheckBox uniYGO = (CheckBox) findViewById(R.id.cb21);
        final CheckBox uniDnD = (CheckBox) findViewById(R.id.cb22);
        final CheckBox uniDC = (CheckBox) findViewById(R.id.cb23);
        final CheckBox uniTMNT = (CheckBox) findViewById(R.id.cb86);
        final CheckBox uniW40K = (CheckBox) findViewById(R.id.cb115);
        final CheckBox setAvX = (CheckBox) findViewById(R.id.cb15);
        final CheckBox setUX = (CheckBox) findViewById(R.id.cb16);
        final CheckBox setS1 = (CheckBox) findViewById(R.id.cb17);
        final CheckBox setBFF = (CheckBox) findViewById(R.id.cb18);
        final CheckBox setJL = (CheckBox) findViewById(R.id.cb19);
        final CheckBox setAoU = (CheckBox) findViewById(R.id.cb27);
        final CheckBox setWoL = (CheckBox) findViewById(R.id.cb34);
        final CheckBox setASM = (CheckBox) findViewById(R.id.cb68);
        final CheckBox setFUS = (CheckBox) findViewById(R.id.cb72);
        final CheckBox setWF = (CheckBox) findViewById(R.id.cb76);
        final CheckBox setCW = (CheckBox) findViewById(R.id.cb80);
        final CheckBox setTMNT = (CheckBox) findViewById(R.id.cb81);
        final CheckBox setGATF = (CheckBox) findViewById(R.id.cb87);
        final CheckBox setDS = (CheckBox) findViewById(R.id.cb88);
        final CheckBox setDP = (CheckBox) findViewById(R.id.cb92);
        final CheckBox setHHS = (CheckBox) findViewById(R.id.cb93);
        final CheckBox setIMWM = (CheckBox) findViewById(R.id.cb94);
        final CheckBox setTD = (CheckBox) findViewById(R.id.cb97);
        final CheckBox setBM = (CheckBox) findViewById(R.id.cb99);
        final CheckBox setSWW = (CheckBox) findViewById(R.id.cb100);
        final CheckBox setSMMC = (CheckBox) findViewById(R.id.cb102);
        final CheckBox setGOTG = (CheckBox) findViewById(R.id.cb103);
        final CheckBox setToA = (CheckBox) findViewById(R.id.cb101);
        final CheckBox setXMFC = (CheckBox) findViewById(R.id.cb104);
        final CheckBox setTMT = (CheckBox) findViewById(R.id.cb107);
        final CheckBox setAI = (CheckBox) findViewById(R.id.cb108);
        final CheckBox setKI = (CheckBox) findViewById(R.id.cb109);
        final CheckBox setJLL = (CheckBox) findViewById(R.id.cb110);
        final CheckBox setHQ = (CheckBox) findViewById(R.id.cb111);
        final CheckBox setBFU = (CheckBox) findViewById(R.id.cb112);
        final CheckBox setORK = (CheckBox) findViewById(R.id.cb113);
        final CheckBox setSW = (CheckBox) findViewById(R.id.cb114);
        final CheckBox costOne = (CheckBox) findViewById(R.id.cb1);
        final CheckBox costTwo = (CheckBox) findViewById(R.id.cb2);
        final CheckBox costThree = (CheckBox) findViewById(R.id.cb3);
        final CheckBox costFour = (CheckBox) findViewById(R.id.cb4);
        final CheckBox costFive = (CheckBox) findViewById(R.id.cb5);
        final CheckBox costSix = (CheckBox) findViewById(R.id.cb6);
        final CheckBox costSeven = (CheckBox) findViewById(R.id.cb7);
        final CheckBox costEight = (CheckBox) findViewById(R.id.cb8);
        final CheckBox costNine = (CheckBox) findViewById(R.id.cb9);
        final CheckBox costTen = (CheckBox) findViewById(R.id.cb10);
        final CheckBox engNone = (CheckBox) findViewById(R.id.cb24);
        final CheckBox engBolt = (CheckBox) findViewById(R.id.cb11);
        final CheckBox engFist = (CheckBox) findViewById(R.id.cb12);
        final CheckBox engShield = (CheckBox) findViewById(R.id.cb13);
        final CheckBox engMask = (CheckBox) findViewById(R.id.cb14);
        final CheckBox owned = (CheckBox) findViewById(R.id.cb25);
        final CheckBox notOwned = (CheckBox) findViewById(R.id.cb26);
        final CheckBox starter = (CheckBox) findViewById(R.id.cb33);
        final CheckBox common = (CheckBox) findViewById(R.id.cb28);
        final CheckBox uncommon = (CheckBox) findViewById(R.id.cb29);
        final CheckBox rare = (CheckBox) findViewById(R.id.cb30);
        final CheckBox superrare = (CheckBox) findViewById(R.id.cb31);
        final CheckBox promo = (CheckBox) findViewById(R.id.cb32);
        final CheckBox avengers = (CheckBox) findViewById(R.id.cb35);
        final CheckBox fanfour = (CheckBox) findViewById(R.id.cb36);
        final CheckBox hydra = (CheckBox) findViewById(R.id.cb37);
        final CheckBox xmen = (CheckBox) findViewById(R.id.cb38);
        final CheckBox force = (CheckBox) findViewById(R.id.cb71);
        final CheckBox thunderbolts = (CheckBox) findViewById(R.id.cb79);
        final CheckBox mknight = (CheckBox) findViewById(R.id.cb82);
        final CheckBox guardians = (CheckBox) findViewById(R.id.cb39);
        final CheckBox starkorg = (CheckBox) findViewById(R.id.cb95);
        final CheckBox shield = (CheckBox) findViewById(R.id.cb40);
        final CheckBox zombie = (CheckBox) findViewById(R.id.cb41);
        final CheckBox sinister = (CheckBox) findViewById(R.id.cb69);
        final CheckBox spider = (CheckBox) findViewById(R.id.cb70);
        final CheckBox newWarriors = (CheckBox) findViewById(R.id.cb78);
        final CheckBox magic = (CheckBox) findViewById(R.id.cb85);
        final CheckBox deadpoolorg = (CheckBox) findViewById(R.id.cb90);
        final CheckBox inhumans = (CheckBox) findViewById(R.id.cb91);
        final CheckBox defenders = (CheckBox) findViewById(R.id.cb96);
        final CheckBox magnetoorg = (CheckBox) findViewById(R.id.cb105);
        final CheckBox exiles = (CheckBox) findViewById(R.id.cb106);
        final CheckBox egypt = (CheckBox) findViewById(R.id.cb42);
        final CheckBox foot = (CheckBox) findViewById(R.id.cb43);
        final CheckBox turtle = (CheckBox) findViewById(R.id.cb77);
        final CheckBox monster = (CheckBox) findViewById(R.id.cb44);
        final CheckBox emerald = (CheckBox) findViewById(R.id.cb45);
        final CheckBox harpers = (CheckBox) findViewById(R.id.cb46);
        final CheckBox alliance = (CheckBox) findViewById(R.id.cb47);
        final CheckBox gauntlet = (CheckBox) findViewById(R.id.cb48);
        final CheckBox zhentarim = (CheckBox) findViewById(R.id.cb49);
        final CheckBox good = (CheckBox) findViewById(R.id.cb50);
        final CheckBox neutral = (CheckBox) findViewById(R.id.cb51);
        final CheckBox evil = (CheckBox) findViewById(R.id.cb52);
        final CheckBox league = (CheckBox) findViewById(R.id.cb53);
        final CheckBox society = (CheckBox) findViewById(R.id.cb54);
        final CheckBox legion = (CheckBox) findViewById(R.id.cb55);
        final CheckBox villain = (CheckBox) findViewById(R.id.cb56);
        final CheckBox noaffil = (CheckBox) findViewById(R.id.cb57);
        final CheckBox titans = (CheckBox) findViewById(R.id.cb58);
        final CheckBox iceCream = (CheckBox) findViewById(R.id.cb59);
        final CheckBox crime = (CheckBox) findViewById(R.id.cb73);
        final CheckBox superman = (CheckBox) findViewById(R.id.cb74);
        final CheckBox batman = (CheckBox) findViewById(R.id.cb75);
        final CheckBox red = (CheckBox) findViewById(R.id.cb60);
        final CheckBox blue = (CheckBox) findViewById(R.id.cb61);
        final CheckBox orange = (CheckBox) findViewById(R.id.cb62);
        final CheckBox indigo = (CheckBox) findViewById(R.id.cb63);
        final CheckBox sinestro = (CheckBox) findViewById(R.id.cb64);
        final CheckBox star = (CheckBox) findViewById(R.id.cb65);
        final CheckBox green = (CheckBox) findViewById(R.id.cb66);
        final CheckBox black = (CheckBox) findViewById(R.id.cb67);
        final CheckBox white = (CheckBox) findViewById(R.id.cb83);
        final CheckBox greenarrow = (CheckBox) findViewById(R.id.cb84);
        final CheckBox target = (CheckBox) findViewById(R.id.cb89);
        final CheckBox newGods = (CheckBox) findViewById(R.id.cb98);
        final CheckBox imperium = (CheckBox) findViewById(R.id.cb116);
        final CheckBox ultramarines = (CheckBox) findViewById(R.id.cb117);
        final CheckBox chaos = (CheckBox) findViewById(R.id.cb118);
        final CheckBox deathguard = (CheckBox) findViewById(R.id.cb119);
        final CheckBox orks = (CheckBox) findViewById(R.id.cb120);
        final CheckBox spacewolves = (CheckBox) findViewById(R.id.cb121);


        final EditText editSearch = (EditText) findViewById(R.id.etSearchText);

        Button search = (Button) findViewById(R.id.btSearch);
        Button clear = (Button) findViewById(R.id.btClear);

        if (viewType.equals("randomizer")) {
            search.setText(String.valueOf("Set Criteria"));
            owned.setChecked(true);
        }

        search.setOnClickListener(new View.OnClickListener() {

                                      @Override
                                      public void onClick(View v) {

                                          //ListBuilder cardList = new ListBuilder(Search.this,getPackageName());
                                          String criteria = "";
                                          String basicCriteria = "";
                                          String searchCriteria = "";
                                          String uniCriteria = "";
                                          String collectionCriteria = "";
                                          String setCriteria = "";
                                          String costCriteria = "";
                                          String energyCriteria = "";
                                          String rarityCriteria = "";
                                          String affilCriteria = "";
                                          String noAffilCriteria = "";

                                          searchCriteria = editSearch.getText().toString();
                                          searchCriteria = searchCriteria.replace("'", "''");

                                          if (!"".equals(searchCriteria)) {
                                              if (searchCriteria.substring(searchCriteria.length() - 1)
                                                      .equals(" ")) {
                                                  searchCriteria = searchCriteria.substring(0,
                                                          searchCriteria.length() - 1);
                                              }
                                              searchCriteria = "(CharName like '%" + searchCriteria
                                                      + "%' or CardName like '%" + searchCriteria + "%')";
                                          }

                                          if (uniMarvel.isChecked()) {
                                              if (uniCriteria.equals("")) {
                                                  uniCriteria = uniCriteria + "'Marvel'";
                                              } else {
                                                  uniCriteria = uniCriteria + ",'Marvel'";
                                              }

                                          }
                                          if (uniYGO.isChecked()) {
                                              if (uniCriteria.equals("")) {
                                                  uniCriteria = uniCriteria + "'Yu-Gi-Oh!'";
                                              } else {
                                                  uniCriteria = uniCriteria + ",'Yu-Gi-Oh!'";
                                              }

                                          }
                                          if (uniDnD.isChecked()) {
                                              if (uniCriteria.equals("")) {
                                                  uniCriteria = uniCriteria + "'Dungeons & Dragons'";
                                              } else {
                                                  uniCriteria = uniCriteria + ",'Dungeons & Dragons'";
                                              }
                                          }
                                          if (uniDC.isChecked()) {
                                              if (uniCriteria.equals("")) {
                                                  uniCriteria = uniCriteria + "'DC Comics'";
                                              } else {
                                                  uniCriteria = uniCriteria + ",'DC Comics'";
                                              }
                                          }
                                          if (uniTMNT.isChecked()) {
                                              if (uniCriteria.equals("")) {
                                                  uniCriteria = uniCriteria + "'TMNT'";
                                              } else {
                                                  uniCriteria = uniCriteria + ",'TMNT'";
                                              }
                                          }
                                          if (uniW40K.isChecked()) {
                                              if (uniCriteria.equals("")) {
                                                  uniCriteria = uniCriteria + "'W40K'";
                                              } else {
                                                  uniCriteria = uniCriteria + ",'W40K'";
                                              }
                                          }

                                          if (!"".equals(uniCriteria)) {
                                              uniCriteria = "Universe in(" + uniCriteria + ")";
                                          }

                                          if (starter.isChecked()) {
                                              if (rarityCriteria.equals("")) {
                                                  rarityCriteria = rarityCriteria + "'as'";
                                              } else {
                                                  rarityCriteria = rarityCriteria + ",'as'";
                                              }

                                          }
                                          if (common.isChecked()) {
                                              if (rarityCriteria.equals("")) {
                                                  rarityCriteria = rarityCriteria + "'bc'";
                                              } else {
                                                  rarityCriteria = rarityCriteria + ",'bc'";
                                              }

                                          }
                                          if (uncommon.isChecked()) {
                                              if (rarityCriteria.equals("")) {
                                                  rarityCriteria = rarityCriteria + "'cu'";
                                              } else {
                                                  rarityCriteria = rarityCriteria + ",'cu'";
                                              }

                                          }
                                          if (rare.isChecked()) {
                                              if (rarityCriteria.equals("")) {
                                                  rarityCriteria = rarityCriteria + "'dr'";
                                              } else {
                                                  rarityCriteria = rarityCriteria + ",'dr'";
                                              }

                                          }
                                          if (superrare.isChecked()) {
                                              if (rarityCriteria.equals("")) {
                                                  rarityCriteria = rarityCriteria + "'esr'";
                                              } else {
                                                  rarityCriteria = rarityCriteria + ",'esr'";
                                              }

                                          }

                                          if (promo.isChecked()) {
                                              if (rarityCriteria.equals("")) {
                                                  rarityCriteria = rarityCriteria + "'fz','gp','halt'";
                                              } else {
                                                  rarityCriteria = rarityCriteria + ",'fz','gp','halt'";
                                              }

                                          }

                                          if (!"".equals(rarityCriteria)) {
                                              rarityCriteria = "Rarity in(" + rarityCriteria + ")";
                                          }

                                          if (setAvX.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'AVX'";
                                              } else {
                                                  setCriteria = setCriteria + ",'AVX'";
                                              }

                                          }
                                          if (setUX.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'UX'";
                                              } else {
                                                  setCriteria = setCriteria + ",'UX'";
                                              }

                                          }
                                          if (setS1.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'S1'";
                                              } else {
                                                  setCriteria = setCriteria + ",'S1'";
                                              }

                                          }
                                          if (setBFF.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'BFF'";
                                              } else {
                                                  setCriteria = setCriteria + ",'BFF'";
                                              }

                                          }
                                          if (setJL.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'JL'";
                                              } else {
                                                  setCriteria = setCriteria + ",'JL'";
                                              }

                                          }
                                          if (setAoU.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'AoU'";
                                              } else {
                                                  setCriteria = setCriteria + ",'AoU'";
                                              }

                                          }
                                          if (setWoL.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'WoL'";
                                              } else {
                                                  setCriteria = setCriteria + ",'WoL'";
                                              }

                                          }
                                          if (setASM.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'ASM'";
                                              } else {
                                                  setCriteria = setCriteria + ",'ASM'";
                                              }

                                          }
                                          if (setFUS.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'FUS'";
                                              } else {
                                                  setCriteria = setCriteria + ",'FUS'";
                                              }

                                          }
                                          if (setWF.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'WF'";
                                              } else {
                                                  setCriteria = setCriteria + ",'WF'";
                                              }

                                          }
                                          if (setCW.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'CW'";
                                              } else {
                                                  setCriteria = setCriteria + ",'CW'";
                                              }

                                          }
                                          if (setTMNT.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'TMNT'";
                                              } else {
                                                  setCriteria = setCriteria + ",'TMNT'";
                                              }
                                          }
                                          if (setGATF.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'GATF'";
                                              } else {
                                                  setCriteria = setCriteria + ",'GATF'";
                                              }
                                          }
                                          if (setDS.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'DS'";
                                              } else {
                                                  setCriteria = setCriteria + ",'DS'";
                                              }
                                          }
                                          if (setDP.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'DP'";
                                              } else {
                                                  setCriteria = setCriteria + ",'DP'";
                                              }
                                          }
                                          if (setHHS.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'HHS'";
                                              } else {
                                                  setCriteria = setCriteria + ",'HHS'";
                                              }
                                          }
                                          if (setIMWM.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'IMWM'";
                                              } else {
                                                  setCriteria = setCriteria + ",'IMWM'";
                                              }
                                          }
                                          if (setTD.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'TD'";
                                              } else {
                                                  setCriteria = setCriteria + ",'TD'";
                                              }
                                          }
                                          if (setBM.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'BM'";
                                              } else {
                                                  setCriteria = setCriteria + ",'BM'";
                                              }
                                          }
                                          if (setSWW.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'SWW'";
                                              } else {
                                                  setCriteria = setCriteria + ",'SWW'";
                                              }
                                          }
                                          if (setToA.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'ToA'";
                                              } else {
                                                  setCriteria = setCriteria + ",'ToA'";
                                              }
                                          }
                                          if (setXMFC.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'XMFC'";
                                              } else {
                                                  setCriteria = setCriteria + ",'XMFC'";
                                              }
                                          }
                                          if (setSMMC.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'SMMC'";
                                              } else {
                                                  setCriteria = setCriteria + ",'SMMC'";
                                              }
                                          }
                                          if (setGOTG.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'GotG'";
                                              } else {
                                                  setCriteria = setCriteria + ",'GotG'";
                                              }
                                          }
                                          if (setTMT.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'TMT'";
                                              } else {
                                                  setCriteria = setCriteria + ",'TMT'";
                                              }
                                          }
                                          if (setAI.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'AI'";
                                              } else {
                                                  setCriteria = setCriteria + ",'AI'";
                                              }
                                          }
                                          if (setKI.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'KI'";
                                              } else {
                                                  setCriteria = setCriteria + ",'KI'";
                                              }
                                          }
                                          if (setJLL.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'JLL'";
                                              } else {
                                                  setCriteria = setCriteria + ",'JLL'";
                                              }
                                          }
                                          if (setHQ.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'HQ'";
                                              } else {
                                                  setCriteria = setCriteria + ",'HQ'";
                                              }
                                          }
                                          if (setBFU.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'BFU'";
                                              } else {
                                                  setCriteria = setCriteria + ",'BFU'";
                                              }
                                          }
                                          if (setORK.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'ORK'";
                                              } else {
                                                  setCriteria = setCriteria + ",'ORK'";
                                              }
                                          }
                                          if (setSW.isChecked()) {
                                              if (setCriteria.equals("")) {
                                                  setCriteria = setCriteria + "'SW'";
                                              } else {
                                                  setCriteria = setCriteria + ",'SW'";
                                              }
                                          }
                                          if (!"".equals(setCriteria)) {
                                              setCriteria = "CardSet in(" + setCriteria + ")";
                                          }

                                          if (costOne.isChecked()) {
                                              if (costCriteria.equals("")) {
                                                  costCriteria = costCriteria + "'one'";
                                              } else {
                                                  costCriteria = costCriteria + ",'one'";
                                              }

                                          }
                                          if (costTwo.isChecked()) {
                                              if (costCriteria.equals("")) {
                                                  costCriteria = costCriteria + "'two'";
                                              } else {
                                                  costCriteria = costCriteria + ",'two'";
                                              }
                                          }
                                          if (costThree.isChecked()) {
                                              if (costCriteria.equals("")) {
                                                  costCriteria = costCriteria + "'three'";
                                              } else {
                                                  costCriteria = costCriteria + ",'three'";
                                              }
                                          }
                                          if (costFour.isChecked()) {
                                              if (costCriteria.equals("")) {
                                                  costCriteria = costCriteria + "'four'";
                                              } else {
                                                  costCriteria = costCriteria + ",'four'";
                                              }
                                          }
                                          if (costFive.isChecked()) {
                                              if (costCriteria.equals("")) {
                                                  costCriteria = costCriteria + "'five'";
                                              } else {
                                                  costCriteria = costCriteria + ",'five'";
                                              }
                                          }
                                          if (costSix.isChecked()) {
                                              if (costCriteria.equals("")) {
                                                  costCriteria = costCriteria + "'six'";
                                              } else {
                                                  costCriteria = costCriteria + ",'six'";
                                              }
                                          }
                                          if (costSeven.isChecked()) {
                                              if (costCriteria.equals("")) {
                                                  costCriteria = costCriteria + "'seven'";
                                              } else {
                                                  costCriteria = costCriteria + ",'seven'";
                                              }
                                          }
                                          if (costEight.isChecked()) {
                                              if (costCriteria.equals("")) {
                                                  costCriteria = costCriteria + "'eight'";
                                              } else {
                                                  costCriteria = costCriteria + ",'eight'";
                                              }
                                          }

                                          if (costNine.isChecked()) {
                                              if (costCriteria.equals("")) {
                                                  costCriteria = costCriteria + "'nine'";
                                              } else {
                                                  costCriteria
                                                          = costCriteria + ",'nine'";
                                              }
                                          }

                                          if (costTen.isChecked()) {
                                              if (costCriteria.equals("")) {
                                                  costCriteria = costCriteria + "'ten'";
                                              } else {
                                                  costCriteria = costCriteria + ",'ten'";
                                              }
                                          }

                                          if (!"".equals(costCriteria)) {
                                              costCriteria = "Cost in(" + costCriteria + ")";
                                          }

                                          if (engNone.isChecked()) {
                                              if (energyCriteria.equals("")) {
                                                  energyCriteria = energyCriteria + "''";
                                              } else {
                                                  energyCriteria = energyCriteria + ",''";
                                              }
                                          }
                                          if (engBolt.isChecked()) {
                                              if (energyCriteria.equals("")) {
                                                  energyCriteria = energyCriteria + "'lightning','bf','bm','bs','a'";
                                              } else {
                                                  energyCriteria = energyCriteria + ",'lightning','bf','bm','bs','a'";
                                              }
                                          }
                                          if (engFist.isChecked()) {
                                              if (energyCriteria.equals("")) {
                                                  energyCriteria = energyCriteria + "'fist','bf','fm','fs','a'";
                                              } else {
                                                  energyCriteria = energyCriteria + ",'fist','bf','fm','fs','a'";
                                              }
                                          }
                                          if (engShield.isChecked()) {
                                              if (energyCriteria.equals("")) {
                                                  energyCriteria = energyCriteria + "'shield','fs','ms','bs','a'";
                                              } else {
                                                  energyCriteria = energyCriteria + ",'shield','fs','ms','bs','a'";
                                              }
                                          }
                                          if (engMask.isChecked()) {
                                              if (energyCriteria.equals("")) {
                                                  energyCriteria = energyCriteria + "'mask','fm','ms','bm','a'";
                                              } else {
                                                  energyCriteria = energyCriteria + ",'mask','fm','ms','bm','a'";
                                              }
                                          }

                                          if (!"".equals(energyCriteria)) {
                                              energyCriteria = "Energy in(" + energyCriteria + ")";
                                          }

                                          if (owned.isChecked()) {
                                              collectionCriteria = "(NumOwned>0 or NumFoilsOwned>0)";
                                              //if (collectionCriteria.equals("")) {
                                              //    collectionCriteria = collectionCriteria + ">0";
                                              //}
                                          }

                                          if (notOwned.isChecked()) {
                                              if (collectionCriteria.equals("")) {
                                                  //collectionCriteria = collectionCriteria + "=0";
                                                  collectionCriteria = "(NumOwned=0 and NumFoilsOwned<=0)";
                                              } else {
                                                  collectionCriteria = "";
                                              }
                                          }

                                          //if (!"".equals(collectionCriteria)) {
                                          //    collectionCriteria = "(NumOwned" + collectionCriteria + ")";
                                          //}
                                          // Affiliation section
                                          if (avengers.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'avengers'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'avengers'";
                                              }
                                          }
                                          if (fanfour.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'fanfour'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'fanfour'";
                                              }
                                          }
                                          if (hydra.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'hydra'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'hydra'";
                                              }
                                          }
                                          if (xmen.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'xmen'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'xmen'";
                                              }
                                          }
                                          if (force.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'force'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'force'";
                                              }
                                          }
                                          if (thunderbolts.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'thunderbolts'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'thunderbolts'";
                                              }
                                          }
                                          if (mknight.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'mknight'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'mknight'";
                                              }
                                          }
                                          if (guardians.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'guardians'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'guardians'";
                                              }
                                          }
                                          if (starkorg.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'starkorg'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'starkorg'";
                                              }
                                          }
                                          if (shield.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'shieldorg'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'shieldorg'";
                                              }
                                          }
                                          if (zombie.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'zombieorg'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'zombieorg'";
                                              }
                                          }
                                          if (sinister.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'sinistersix'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'sinistersix'";
                                              }
                                          }
                                          if (spider.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'spiderfriends'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'spiderfriends'";
                                              }
                                          }
                                          if (newWarriors.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'newwarriors'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'newwarriors'";
                                              }
                                          }
                                          if (magic.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'magicorg'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'magicorg'";
                                              }
                                          }
                                          if (deadpoolorg.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'deadpoolorg'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'deadpoolorg'";
                                              }
                                          }
                                          if (inhumans.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'inhumans'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'inhumans'";
                                              }
                                          }
                                          if (defenders.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'defendersorg'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'defendersorg'";
                                              }
                                          }
                                          if (magnetoorg.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'magnetoorg'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'magnetoorg'";
                                              }
                                          }
                                          if (exiles.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'exiles'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'exiles'";
                                              }
                                          }
                                          if (egypt.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'egypt'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'egypt'";
                                              }
                                          }
                                          if (foot.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'foot'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'foot'";
                                              }
                                          }
                                          if (turtle.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'turtle'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'turtle'";
                                              }
                                          }
                                          if (monster.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'monster'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'monster'";
                                              }
                                          }
                                          if (emerald.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'emeraldenclave'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'emeraldenclave'";
                                              }
                                          }
                                          if (harpers.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'harpers'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'harpers'";
                                              }
                                          }
                                          if (alliance.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'lordsalliance'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'lordsalliance'";
                                              }
                                          }

                                          if (gauntlet.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'orderofthegauntlet'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'orderofthegauntlet'";
                                              }
                                          }
                                          if (zhentarim.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'zhentarim'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'zhentarim'";
                                              }
                                          }
                                          if (good.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'good'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'good'";
                                              }
                                          }
                                          if (neutral.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'neutral'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'neutral'";
                                              }
                                          }
                                          if (evil.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'evil'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'evil'";
                                              }
                                          }
                                          if (league.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'league'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'league'";
                                              }
                                          }
                                          if (society.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'society'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'society'";
                                              }
                                          }
                                          if (legion.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'legion'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'legion'";
                                              }
                                          }
                                          if (villain.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'villain'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'villain'";
                                              }
                                          }
                                          if (titans.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'titans'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'titans'";
                                              }
                                          }
                                          if (iceCream.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'icecream'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'icecream'";
                                              }
                                          }
                                          if (crime.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'crime'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'crime'";
                                              }
                                          }
                                          if (superman.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'supermanorg'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'supermanorg'";
                                              }
                                          }
                                          if (batman.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'batmanorg'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'batmanorg'";
                                              }
                                          }
                                          if (red.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'redlanterncorps'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'redlanterncorps'";
                                              }
                                          }
                                          if (blue.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'bluelanterncorps'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'bluelanterncorps'";
                                              }
                                          }
                                          if (orange.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'orangelanterncorps'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'orangelantercorps'";
                                              }
                                          }
                                          if (indigo.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'indigotribeorg'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'indigotribeorg'";
                                              }
                                          }
                                          if (sinestro.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'sinestrocorps'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'sinestrocorps'";
                                              }
                                          }
                                          if (star.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'starsapphirecorps'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'starsapphirecorps'";
                                              }
                                          }
                                          if (green.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'greenlanterncorps'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'greenlanterncorps'";
                                              }
                                          }
                                          if (black.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'blacklanterncorps'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'blacklanterncorps'";
                                              }
                                          }
                                          if (white.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'whitelanterncorps'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'whitelanterncorps'";
                                              }
                                          }
                                          if (greenarrow.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'greenarroworg'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'greenarroworg'";
                                              }
                                          }
                                          if (target.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'targetorg'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'targetorg'";
                                              }
                                          }
                                          if (newGods.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'newgods'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'newgods'";
                                              }
                                          }
                                          if (imperium.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'imperium'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'imperium'";
                                              }
                                          }
                                          if (ultramarines.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'ultramarines'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'ultramarines'";
                                              }
                                          }
                                          if (chaos.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'chaos'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'chaos'";
                                              }
                                          }
                                          if (deathguard.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'deathguard'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'deathguard'";
                                              }
                                          }
                                          if (orks.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'orks'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'orks'";
                                              }
                                          }
                                          if (spacewolves.isChecked()) {
                                              if (affilCriteria.equals("")) {
                                                  affilCriteria = affilCriteria + "'spacewolves'";
                                              } else {
                                                  affilCriteria = affilCriteria + ",'spacewolves'";
                                              }
                                          }

                                          if (noaffil.isChecked())

                                          {
                                              if (affilCriteria.equals("")) {
                                                  noAffilCriteria = "'','blank'";
                                              } else {
                                                  noAffilCriteria = ",'','blank'";
                                              }
                                          }

                                          if (!"".

                                                  equals(affilCriteria)

                                                  )

                                          {
                                              affilCriteria = "(AffiliationOne in(" + affilCriteria
                                                      + noAffilCriteria + ") or AffiliationTwo in("
                                                      + affilCriteria + "))";
                                          } else if (!"".

                                                  equals(noAffilCriteria)

                                                  )

                                          {
                                              affilCriteria = "(AffiliationOne in(" + noAffilCriteria
                                                      + "))";
                                          }

                                          criteria = "where ";

                                          if (!"".

                                                  equals(searchCriteria)

                                                  )

                                          {
                                              if (criteria.substring(criteria.length() - 1).equals(")")) {
                                                  criteria = criteria + " and " + searchCriteria;
                                              } else {
                                                  criteria = criteria + searchCriteria;
                                              }
                                          }

                                          if (!"".

                                                  equals(uniCriteria)

                                                  )

                                          {
                                              if (criteria.substring(criteria.length() - 1).equals(")")) {
                                                  criteria = criteria + " and " + uniCriteria;
                                              } else {
                                                  criteria = criteria + uniCriteria;
                                              }
                                          }

                                          if (!"".

                                                  equals(rarityCriteria)

                                                  )

                                          {
                                              if (criteria.substring(criteria.length() - 1).equals(")")) {
                                                  criteria = criteria + " and " + rarityCriteria;
                                              } else {
                                                  criteria = criteria + rarityCriteria;
                                              }
                                          }

                                          if (!"".

                                                  equals(setCriteria)

                                                  )

                                          {
                                              if (criteria.substring(criteria.length() - 1).equals(")")) {
                                                  criteria = criteria + " and " + setCriteria;
                                              } else {
                                                  criteria = criteria + setCriteria;
                                              }
                                          }

                                          if (!"".

                                                  equals(costCriteria)

                                                  )

                                          {
                                              if (criteria.substring(criteria.length() - 1).equals(")")) {
                                                  criteria = criteria + " and " + costCriteria;
                                              } else {
                                                  criteria = criteria + costCriteria;
                                              }
                                          }

                                          if (!"".

                                                  equals(energyCriteria)

                                                  )

                                          {
                                              if (criteria.substring(criteria.length() - 1).equals(")")) {
                                                  criteria = criteria + " and " + energyCriteria;
                                              } else {
                                                  criteria = criteria + energyCriteria;
                                              }
                                          }

                                          if (!"".

                                                  equals(collectionCriteria)

                                                  )

                                          {
                                              if (criteria.substring(criteria.length() - 1).equals(")")) {
                                                  criteria = criteria + " and " + collectionCriteria;
                                              } else {
                                                  criteria = criteria + collectionCriteria;
                                              }
                                          }

                                          if (!"".

                                                  equals(affilCriteria)

                                                  )

                                          {
                                              if (criteria.substring(criteria.length() - 1).equals(")")) {
                                                  criteria = criteria + " and " + affilCriteria;
                                              } else {
                                                  criteria = criteria + affilCriteria;
                                              }
                                          }

                                          if (criteria.equals("where "))

                                          {
                                              criteria = "";
                                          }

                                          basicCriteria = "where ";

                                          if (!"".

                                                  equals(searchCriteria)

                                                  )

                                          {
                                              if (basicCriteria.substring(basicCriteria.length() - 1)
                                                      .equals(")")) {
                                                  basicCriteria = basicCriteria + " and "
                                                          + searchCriteria;
                                              } else {
                                                  basicCriteria = basicCriteria + searchCriteria;
                                              }
                                          }

                                          if (!"".

                                                  equals(uniCriteria)

                                                  )

                                          {
                                              if (basicCriteria.substring(basicCriteria.length() - 1)
                                                      .equals(")")) {
                                                  basicCriteria = basicCriteria + " and " + uniCriteria;
                                              } else {
                                                  basicCriteria = basicCriteria + uniCriteria;
                                              }
                                          }
                /*
                 * if (!"".equals(rarityCriteria)) { if
				 * (basicCriteria.substring(basicCriteria.length() -
				 * 1).equals(")")) { basicCriteria = basicCriteria + " and " +
				 * rarityCriteria; } else { basicCriteria = basicCriteria +
				 * rarityCriteria; } }
				 */

                                          if (!"".

                                                  equals(setCriteria)

                                                  )

                                          {
                                              if (basicCriteria.substring(basicCriteria.length() - 1)
                                                      .equals(")")) {
                                                  basicCriteria = basicCriteria + " and " + setCriteria;
                                              } else {
                                                  basicCriteria = basicCriteria + setCriteria;
                                              }
                                          }

                                          if (!"".

                                                  equals(costCriteria)

                                                  )

                                          {
                                              if (basicCriteria.substring(basicCriteria.length() - 1)
                                                      .equals(")")) {
                                                  basicCriteria = basicCriteria + " and " + costCriteria;
                                              } else {
                                                  basicCriteria = basicCriteria + costCriteria;
                                              }
                                          }
                /*
                 * if (!"".equals(energyCriteria)) { if
				 * (basicCriteria.substring(basicCriteria.length() -
				 * 1).equals(")")) { basicCriteria = basicCriteria + " and " +
				 * energyCriteria; } else { basicCriteria = basicCriteria +
				 * energyCriteria; } }
				 */
                                          if (!"".equals(collectionCriteria)) {
                                              if (basicCriteria.substring(basicCriteria.length() - 1)
                                                      .equals(")")) {
                                                  basicCriteria = basicCriteria + " and "
                                                          + collectionCriteria;
                                              } else {
                                                  basicCriteria = basicCriteria + collectionCriteria;
                                              }
                                          }
                /*
                 * if (!"".equals(affilCriteria)) { if
				 * (basicCriteria.substring(basicCriteria.length() -
				 * 1).equals(")")) { basicCriteria = basicCriteria + " and " +
				 * affilCriteria; } else { basicCriteria = basicCriteria +
				 * affilCriteria; } }
				 */

                                          if (basicCriteria.equals("where ")) {
                                              basicCriteria = "";
                                          }

                                          List<String> groupList;
                                          datasource.open();
                                          groupList = datasource.sqlGetCharList(criteria, "case when DieImage='basic' then 0 else 1 end, CharName, CardSet, Rarity, CardName");

                                          //MainActivity.masterList = cardList.buildList(criteria);

                                          if (groupList.isEmpty()) {
                                              AlertDialog alertDialog = new AlertDialog.Builder(
                                                      Search.this).create();
                                              alertDialog.setMessage("No results returned.");
                                              alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                                      new DialogInterface.OnClickListener() {
                                                          public void onClick(DialogInterface dialog,
                                                                              int which) {
                                                              dialog.dismiss();
                                                          }
                                                      });
                                              alertDialog.show();
                                          } else {
                                              if (viewType.equals("randomizer")) {
                                                  Intent resultIntent = new Intent();
                                                  resultIntent.putExtra("cardCriteria", criteria);
                                                  resultIntent.putExtra("basicCriteria", basicCriteria);
                                                  setResult(Search.RESULT_OK, resultIntent);
                                                  finish();
                                              } else {
                                                  //Intent intent = new Intent(Search.this,CardCollector.class);
                                                  Intent intent = new Intent(Search.this, CardCollectorNEW.class);
                                                  if (viewType.equals("team viewer")) {
                                                      intent.putExtra("view", "team builder");
                                                  } else {
                                                      intent.putExtra("view", viewType);
                                                  }
                                                  intent.putExtra("whereCriteria", criteria);
                                                  startActivity(intent);
                                              }
                                          }
                                      }
                                  }

        );

        clear.setOnClickListener(new View.OnClickListener() {

                                     @Override
                                     public void onClick(View v) {
                                         uniMarvel.setChecked(false);
                                         uniYGO.setChecked(false);
                                         uniDnD.setChecked(false);
                                         uniDC.setChecked(false);
                                         uniTMNT.setChecked(false);
                                         owned.setChecked(false);
                                         notOwned.setChecked(false);
                                         setAvX.setChecked(false);
                                         setUX.setChecked(false);
                                         setS1.setChecked(false);
                                         setBFF.setChecked(false);
                                         setJL.setChecked(false);
                                         setAoU.setChecked(false);
                                         setWoL.setChecked(false);
                                         setASM.setChecked(false);
                                         setFUS.setChecked(false);
                                         setWF.setChecked(false);
                                         setCW.setChecked(false);
                                         setTMNT.setChecked(false);
                                         setGATF.setChecked(false);
                                         setDS.setChecked(false);
                                         setDP.setChecked(false);
                                         setHHS.setChecked(false);
                                         setIMWM.setChecked(false);
                                         setTD.setChecked(false);
                                         setBM.setChecked(false);
                                         setSWW.setChecked(false);
                                         setSMMC.setChecked(false);
                                         setGOTG.setChecked(false);
                                         setToA.setChecked(false);
                                         setXMFC.setChecked(false);
                                         setTMT.setChecked(false);
                                         setAI.setChecked(false);
                                         setKI.setChecked(false);
                                         setJLL.setChecked(false);
                                         setHQ.setChecked(false);
                                         costOne.setChecked(false);
                                         costTwo.setChecked(false);
                                         costThree.setChecked(false);
                                         costFour.setChecked(false);
                                         costFive.setChecked(false);
                                         costSix.setChecked(false);
                                         costSeven.setChecked(false);
                                         costEight.setChecked(false);
                                         costNine.setChecked(false);
                                         costTen.setChecked(false);
                                         engNone.setChecked(false);
                                         engBolt.setChecked(false);
                                         engFist.setChecked(false);
                                         engShield.setChecked(false);
                                         engMask.setChecked(false);
                                         starter.setChecked(false);
                                         common.setChecked(false);
                                         uncommon.setChecked(false);
                                         rare.setChecked(false);
                                         superrare.setChecked(false);
                                         promo.setChecked(false);
                                         avengers.setChecked(false);
                                         fanfour.setChecked(false);
                                         hydra.setChecked(false);
                                         xmen.setChecked(false);
                                         force.setChecked(false);
                                         thunderbolts.setChecked(false);
                                         mknight.setChecked(false);
                                         guardians.setChecked(false);
                                         starkorg.setChecked(false);
                                         shield.setChecked(false);
                                         zombie.setChecked(false);
                                         sinister.setChecked(false);
                                         spider.setChecked(false);
                                         newWarriors.setChecked(false);
                                         magic.setChecked(false);
                                         deadpoolorg.setChecked(false);
                                         inhumans.setChecked(false);
                                         defenders.setChecked(false);
                                         magnetoorg.setChecked(false);
                                         exiles.setChecked(false);
                                         egypt.setChecked(false);
                                         foot.setChecked(false);
                                         turtle.setChecked(false);
                                         monster.setChecked(false);
                                         emerald.setChecked(false);
                                         harpers.setChecked(false);
                                         alliance.setChecked(false);
                                         gauntlet.setChecked(false);
                                         zhentarim.setChecked(false);
                                         good.setChecked(false);
                                         neutral.setChecked(false);
                                         evil.setChecked(false);
                                         league.setChecked(false);
                                         society.setChecked(false);
                                         legion.setChecked(false);
                                         villain.setChecked(false);
                                         noaffil.setChecked(false);
                                         titans.setChecked(false);
                                         iceCream.setChecked(false);
                                         crime.setChecked(false);
                                         superman.setChecked(false);
                                         batman.setChecked(false);
                                         red.setChecked(false);
                                         blue.setChecked(false);
                                         orange.setChecked(false);
                                         indigo.setChecked(false);
                                         sinestro.setChecked(false);
                                         star.setChecked(false);
                                         green.setChecked(false);
                                         black.setChecked(false);
                                         white.setChecked(false);
                                         greenarrow.setChecked(false);
                                         target.setChecked(false);
                                         newGods.setChecked(false);

                                         editSearch.setText("");
                                         editSearch.clearFocus();
                                     }
                                 }

        );

    }

}
