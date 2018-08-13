package fluffyhairedkid.dicemasterscompanion;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TeamBuilder extends Activity {

    SQLDataSource datasource;
    int tempCards = 0;
    int tempTeams = 1;
    String whereCriteria = " where NumOwned>0 ";
    String basicCriteria = " where NumOwned>0 ";

    ArrayList<String> teamList = new ArrayList<String>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_builder);

        datasource = new SQLDataSource(this);
        datasource.open();

        listView = (ListView) findViewById(R.id.lvTeamList);
        Button add = (Button) findViewById(R.id.btAdd);
        Button delete = (Button) findViewById(R.id.btDelete);
        Button show = (Button) findViewById(R.id.btShow);
        Button random = (Button) findViewById(R.id.btRandom);

        setListView();

        add.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.etNewTeam);

                boolean checkName = datasource.sqlCheckTeamName(edit.getText()
                        .toString());
                if (checkName) {
                    AlertDialog alertDialog = new AlertDialog.Builder(
                            TeamBuilder.this).create();
                    alertDialog
                            .setMessage("Team name already exists, please choose a new one.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                } else {

                    MainActivity.selectedTeam = edit.getText().toString();

                    datasource.sqlAddTeam(MainActivity.selectedTeam);
                    edit.setText("");
                    setListView();
                    edit.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
                }

            }

        });

        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listView.getCheckedItemPosition() != -1) {
                    final String item = teamList.get(listView
                            .getCheckedItemPosition());

                    AlertDialog.Builder deleteAlert = new AlertDialog.Builder(
                            TeamBuilder.this);
                    deleteAlert
                            .setMessage("Are you sure you want to delete this team?");
                    deleteAlert.setCancelable(true);
                    deleteAlert.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();

                                    datasource.sqlDeleteTeam(item);
                                    setListView();
                                }
                            });
                    deleteAlert.setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert = deleteAlert.create();

                    alert.show();

                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(
                            TeamBuilder.this).create();
                    alertDialog.setMessage("You must select a team!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

            }

        });

        show.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listView.getCheckedItemPosition() != -1) {
                    //ListBuilder cardList = new ListBuilder(TeamBuilder.this,getPackageName());

                    MainActivity.selectedTeam = teamList.get(listView.getCheckedItemPosition());

                    //MainActivity.masterList = cardList.buildList(datasource.sqlGetTeamList(MainActivity.selectedTeam));

                    //Intent intent = new Intent(TeamBuilder.this,CardCollector.class);
                    Intent intent = new Intent(TeamBuilder.this, CardCollectorNEW.class);
                    intent.putExtra("view", "team viewer");
                    intent.putExtra("whereCriteria", datasource.sqlGetTeamList(MainActivity.selectedTeam));
                    startActivity(intent);
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(TeamBuilder.this).create();
                    alertDialog.setMessage("You must select a team!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

            }

        });

        random.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(TeamBuilder.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                tempCards = 0;
                tempTeams = 1;
                whereCriteria = " where NumOwned>0 ";
                basicCriteria = " where NumOwned>0 ";

                dialog.setContentView(R.layout.randomizer_dialog);

                final TextView cardsText = (TextView) dialog
                        .findViewById(R.id.tvCardsCount);
                final TextView teamsText = (TextView) dialog
                        .findViewById(R.id.tvTeamsCount);
                Button cardsMinus = (Button) dialog
                        .findViewById(R.id.btCardsMinus);
                Button cardsPlus = (Button) dialog
                        .findViewById(R.id.btCardsPlus);
                Button teamsMinus = (Button) dialog
                        .findViewById(R.id.btTeamsMinus);
                Button teamsPlus = (Button) dialog
                        .findViewById(R.id.btTeamsPlus);
                Button cancel = (Button) dialog
                        .findViewById(R.id.btCancelCreate);
                Button searchCriteria = (Button) dialog
                        .findViewById(R.id.btSearchCriteria);
                Button create = (Button) dialog.findViewById(R.id.btCreate);
                final CheckBox basics = (CheckBox) dialog
                        .findViewById(R.id.cbBasics);


                basics.setChecked(true);


                cardsMinus.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (tempCards > 0) {
                            tempCards = tempCards - 1;
                            cardsText.setText(String.valueOf(tempCards));
                        }
                    }
                });
                cardsPlus.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        tempCards++;
                        cardsText.setText(String.valueOf(tempCards));
                    }
                });
                teamsMinus.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (tempTeams > 1) {
                            tempTeams = tempTeams - 1;
                            teamsText.setText(String.valueOf(tempTeams));
                        }
                    }
                });
                teamsPlus.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        tempTeams++;
                        teamsText.setText(String.valueOf(tempTeams));
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });
                searchCriteria.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(TeamBuilder.this, Search.class);
                        i.putExtra("view", "randomizer");
                        startActivityForResult(i, 1);
                    }
                });
                create.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        int tempTeamNum = 1;
                        datasource.open();


                        while (tempTeams > 0) {
                            String teamName = "Random Team "
                                    + String.valueOf(tempTeamNum);
                            boolean check = datasource
                                    .sqlCheckTeamName(teamName);
                            if (check) {
                                tempTeamNum++;
                            } else {
                                datasource.sqlAddTeam(teamName);
                                datasource.sqlRandomTeam(teamName, tempCards,
                                        basics.isChecked(),
                                        whereCriteria, basicCriteria);
                                tempTeams = tempTeams - 1;
                            }
                        }
                        setListView();
                        dialog.dismiss();
                        Toast.makeText(TeamBuilder.this, "Team(s) created!",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.setTitle("Randomizer");
                dialog.show();
            }

        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {

                final String oldTeamName = teamList.get(pos);

                final Dialog dialog = new Dialog(TeamBuilder.this);

                dialog.setContentView(R.layout.input_dialog);
                dialog.setTitle("Change team name");
                final EditText edit = (EditText) dialog
                        .findViewById(R.id.etTeamName);

                Button ok = (Button) dialog.findViewById(R.id.btOK);
                ok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        datasource.sqlUpdateTeamName(oldTeamName, edit.getText().toString());
                        dialog.dismiss();
                        setListView();
                        Toast.makeText(TeamBuilder.this, "Team renamed!",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();

                return onLongListItemClick(v, pos, id);
            }
        });
    }

    protected boolean onLongListItemClick(View v, int pos, long id) {
        return true;
    }

    public void setListView() {
        teamList = datasource.sqlGetTeams();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, teamList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        datasource.open();
        setListView();
    }

    @Override
    public void onPause() {
        super.onPause();
        datasource.close();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (1): {
                if (resultCode == TeamBuilder.RESULT_OK) {
                    whereCriteria = data.getStringExtra("cardCriteria");
                    basicCriteria = data.getStringExtra("basicCriteria");
                    Toast.makeText(TeamBuilder.this, "Criteria set",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

}
