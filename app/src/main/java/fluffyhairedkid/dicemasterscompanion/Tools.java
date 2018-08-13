package fluffyhairedkid.dicemasterscompanion;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by Walker on 2/21/2016.
 */
public class Tools extends Activity {

    SQLDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools);

        Button teamBuilder = (Button) findViewById(R.id.btTeamBuilder);
        Button lifeCounter = (Button) findViewById(R.id.btLifeCounter);
        Button bulkAdd = (Button) findViewById(R.id.btBulkAdd);
        Button dataManagement = (Button) findViewById(R.id.btDataManagement);

        teamBuilder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(Tools.this, TeamBuilder.class));
            }
        });

        lifeCounter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(Tools.this, LifeCounter.class));

            }
        });

        bulkAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(Tools.this);
                dialog.setContentView(R.layout.mass_add);
                dialog.setTitle("Bulk Add");


                Button add = (Button) dialog.findViewById(R.id.btAddCards);
                Button cancel = (Button) dialog.findViewById(R.id.btCancelAdd);

                final CheckBox smmcStarter = (CheckBox) dialog.findViewById(R.id.cbSMMCStarter);
                final CheckBox swwStarter = (CheckBox) dialog.findViewById(R.id.cbSWWStarter);
                final CheckBox tdStarter = (CheckBox) dialog.findViewById(R.id.cbTDStarter);
                final CheckBox imwmStarter = (CheckBox) dialog.findViewById(R.id.cbIMWMStarter);
                final CheckBox hhsStarter = (CheckBox) dialog.findViewById(R.id.cbHHSStarter);
                final CheckBox dsStarter = (CheckBox) dialog.findViewById(R.id.cbDSStarter);
                final CheckBox tmntStarter = (CheckBox) dialog.findViewById(R.id.cbTMNTStarter);
                final CheckBox cwStarter = (CheckBox) dialog.findViewById(R.id.cbCWStarter);
                final CheckBox wfStarter = (CheckBox) dialog.findViewById(R.id.cbWFStarter);
                final CheckBox fusStarter = (CheckBox) dialog.findViewById(R.id.cbFUSStarter);
                final CheckBox asmStarter = (CheckBox) dialog.findViewById(R.id.cbASMStarter);
                final CheckBox wolStarter = (CheckBox) dialog.findViewById(R.id.cbWoLStarter);
                final CheckBox aouStarter = (CheckBox) dialog.findViewById(R.id.cbAoUStarter);
                final CheckBox jlStarter = (CheckBox) dialog.findViewById(R.id.cbJLStarter);
                final CheckBox bffStarter = (CheckBox) dialog.findViewById(R.id.cbBFFStarter);
                final CheckBox s1Starter = (CheckBox) dialog.findViewById(R.id.cbS1Starter);
                final CheckBox uxStarter = (CheckBox) dialog.findViewById(R.id.cbUXStarter);
                final CheckBox avxStarter = (CheckBox) dialog.findViewById(R.id.cbAvXStarter);

                datasource = new SQLDataSource(Tools.this);

                cancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });

                add.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        String starterCriteria = "";
                        String fullCriteria = "";

                        if (smmcStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'SMMC'";
                            } else {
                                starterCriteria = starterCriteria + ",'SMMC'";
                            }
                        }if (swwStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'SWW'";
                            } else {
                                starterCriteria = starterCriteria + ",'SWW'";
                            }
                        }
                        if (tdStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'TD'";
                            } else {
                                starterCriteria = starterCriteria + ",'TD'";
                            }
                        }
                        if (imwmStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'IMWM'";
                            } else {
                                starterCriteria = starterCriteria + ",'IMWM'";
                            }
                        }
                        if (hhsStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'HHS'";
                            } else {
                                starterCriteria = starterCriteria + ",'HHS'";
                            }
                        }
                        if (dsStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'DS'";
                            } else {
                                starterCriteria = starterCriteria + ",'DS'";
                            }
                        }
                        if (tmntStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'TMNT'";
                            } else {
                                starterCriteria = starterCriteria + ",'TMNT'";
                            }
                        }
                        if (cwStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'CW'";
                            } else {
                                starterCriteria = starterCriteria + ",'CW'";
                            }
                        }
                        if (wfStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'WF'";
                            } else {
                                starterCriteria = starterCriteria + ",'WF'";
                            }
                        }
                        if (fusStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'FUS'";
                            } else {
                                starterCriteria = starterCriteria + ",'FUS'";
                            }
                        }
                        if (asmStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'ASM'";
                            } else {
                                starterCriteria = starterCriteria + ",'ASM'";
                            }
                        }
                        if (wolStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'WoL'";
                            } else {
                                starterCriteria = starterCriteria + ",'WoL'";
                            }
                        }
                        if (aouStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'AoU'";
                            } else {
                                starterCriteria = starterCriteria + ",'AoU'";
                            }
                        }
                        if (jlStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'JL'";
                            } else {
                                starterCriteria = starterCriteria + ",'JL'";
                            }
                        }
                        if (bffStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'BFF'";
                            } else {
                                starterCriteria = starterCriteria + ",'BFF'";
                            }
                        }
                        if (s1Starter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'S1'";
                            } else {
                                starterCriteria = starterCriteria + ",'S1'";
                            }
                        }
                        if (uxStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'UX'";
                            } else {
                                starterCriteria = starterCriteria + ",'UX'";
                            }
                        }
                        if (avxStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'AVX'";
                            } else {
                                starterCriteria = starterCriteria + ",'AVX'";
                            }
                        }

                        datasource.open();
                        datasource.sqlAddStarter(starterCriteria);
                        datasource.close();

                        dialog.dismiss();
                        Toast.makeText(Tools.this, "Cards added!",
                                Toast.LENGTH_SHORT).show();

                    }
                });

                dialog.show();
            }
        });

        dataManagement.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(Tools.this, DataManagement.class));

            }
        });

    }
}
