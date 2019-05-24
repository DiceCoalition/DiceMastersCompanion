package fluffyhairedkid.dicemasterscompanion;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import android.widget.ToggleButton;

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

				final ToggleButton xmfStarter = (ToggleButton) dialog.findViewById(R.id.tbXMFStarter);
                final ToggleButton xfoStarter = (ToggleButton) dialog.findViewById(R.id.tbXFOStarter);
                final ToggleButton dxmtStarter = (ToggleButton) dialog.findViewById(R.id.tbDXMStarter);
                final ToggleButton jusStarter = (ToggleButton) dialog.findViewById(R.id.tbJUSStarter);
                final ToggleButton doomStarter = (ToggleButton) dialog.findViewById(R.id.tbDOOMStarter);
                final ToggleButton mystStarter = (ToggleButton) dialog.findViewById(R.id.tbMYSTStarter);
                final ToggleButton bfuStarter = (ToggleButton) dialog.findViewById(R.id.tbBFUStarter);
                final ToggleButton orkStarter = (ToggleButton) dialog.findViewById(R.id.tbORKStarter);
                final ToggleButton swStarter = (ToggleButton) dialog.findViewById(R.id.tbSWStarter);
                final ToggleButton aiStarter = (ToggleButton) dialog.findViewById(R.id.tbAIStarter);
                final ToggleButton kiStarter = (ToggleButton) dialog.findViewById(R.id.tbKIStarter);
                final ToggleButton jllStarter = (ToggleButton) dialog.findViewById(R.id.tbJLLStarter);
                final ToggleButton hqStarter = (ToggleButton) dialog.findViewById(R.id.tbHQStarter);
                final ToggleButton smmcStarter = (ToggleButton) dialog.findViewById(R.id.tbSMMCStarter);
                final ToggleButton swwStarter = (ToggleButton) dialog.findViewById(R.id.tbSWWStarter);
                final ToggleButton tdStarter = (ToggleButton) dialog.findViewById(R.id.tbTDStarter);
                final ToggleButton imwmStarter = (ToggleButton) dialog.findViewById(R.id.tbIMWMStarter);
                final ToggleButton hhsStarter = (ToggleButton) dialog.findViewById(R.id.tbHHSStarter);
                final ToggleButton dsStarter = (ToggleButton) dialog.findViewById(R.id.tbDSStarter);
                final ToggleButton tmntStarter = (ToggleButton) dialog.findViewById(R.id.tbTMNTStarter);
                final ToggleButton cwStarter = (ToggleButton) dialog.findViewById(R.id.tbCWStarter);
                final ToggleButton wfStarter = (ToggleButton) dialog.findViewById(R.id.tbWFStarter);
                final ToggleButton fusStarter = (ToggleButton) dialog.findViewById(R.id.tbFUSStarter);
                final ToggleButton asmStarter = (ToggleButton) dialog.findViewById(R.id.tbASMStarter);
                final ToggleButton wolStarter = (ToggleButton) dialog.findViewById(R.id.tbWoLStarter);
                final ToggleButton aouStarter = (ToggleButton) dialog.findViewById(R.id.tbAoUStarter);
                final ToggleButton jlStarter = (ToggleButton) dialog.findViewById(R.id.tbJLStarter);
                final ToggleButton bffStarter = (ToggleButton) dialog.findViewById(R.id.tbBFFStarter);
                final ToggleButton s1Starter = (ToggleButton) dialog.findViewById(R.id.tbS1Starter);
                final ToggleButton uxStarter = (ToggleButton) dialog.findViewById(R.id.tbUXStarter);
                final ToggleButton avxStarter = (ToggleButton) dialog.findViewById(R.id.tbAvXStarter);

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
                        
						 if (xmfStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'XMF'";
                            } else {
                                starterCriteria = starterCriteria + ",'XMF'";
                            }
                        }if (xfoStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'XFO'";
                            } else {
                                starterCriteria = starterCriteria + ",'XFO'";
                            }
                        }if (dxmStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'DXM'";
                            } else {
                                starterCriteria = starterCriteria + ",'DXM'";
                            }
                        if (jusStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'JUS'";
                            } else {
                                starterCriteria = starterCriteria + ",'JUS'";
                            }
                        }if (doomStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'DOOM'";
                            } else {
                                starterCriteria = starterCriteria + ",'DOOM'";
                            }
                        }if (mystStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'MYST'";
                            } else {
                                starterCriteria = starterCriteria + ",'MYST'";
                            }
                        }if (bfuStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'BFU'";
                            } else {
                                starterCriteria = starterCriteria + ",'BFU'";
                            }
                        }if (orkStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'ORK'";
                            } else {
                                starterCriteria = starterCriteria + ",'ORK'";
                            }
                        }if (swStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'SW'";
                            } else {
                                starterCriteria = starterCriteria + ",'SW'";
                            }
                        }if (aiStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'AI'";
                            } else {
                                starterCriteria = starterCriteria + ",'AI'";
                            }
                        }if (kiStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'KI'";
                            } else {
                                starterCriteria = starterCriteria + ",'KI'";
                            }
                        }if (jllStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'JLL'";
                            } else {
                                starterCriteria = starterCriteria + ",'JLL'";
                            }
                        }if (hqStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'HQ'";
                            } else {
                                starterCriteria = starterCriteria + ",'HQ'";
                            }
                        }if (smmcStarter.isChecked()) {
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
