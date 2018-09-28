package fluffyhairedkid.dicemasterscompanion;

import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.Toast;

public class CardViewer extends Activity {
    int groupPosition = 0;
    int childPosition = 0;
    int maxChar = 0;
    int maxCard = 0;
    List<Integer> heroCards;
    Map<String, List<Integer>> imageCollection;
    List<String> groupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_viewer);
        Bundle extras = getIntent().getExtras();

        groupPosition = extras.getInt("group");
        childPosition = extras.getInt("child");

        //imageCollection = CardCollector.imageCollection;
        //groupList = CardCollector.groupList;
        imageCollection = CardCollectorNEW.imageCollection;
        groupList = CardCollectorNEW.groupList;

        ImageView imgView = (ImageView) findViewById(R.id.ivViewer);

        maxChar = groupList.size() - 1;
        setValues();
        setImageView();

        imgView.setOnTouchListener(new OnSwipeTouchListener(CardViewer.this) {
            public void onSwipeTop() {
                oneCardDown();
            }

            public void onSwipeRight() {
                oneCardUp();
            }

            public void onSwipeLeft() {
                oneCardDown();
            }

            public void onSwipeBottom() {
                oneCardUp();
            }

        });

    }

    public void setImageView() {
        //TODO: Can we download images as needed instead of including them in the initial download?
        ImageView imgView = (ImageView) findViewById(R.id.ivViewer);
        int cardimage = heroCards.get(childPosition);
        // if it will return 0, then the resource you are looking for does not exist
        //imgView.getResources().getIdentifier(cardimage)
        if (cardimage > 0) {
            imgView.setBackgroundResource(0);
            imgView.setImageResource(cardimage);
        } else {
            imgView.setBackgroundResource(this.getResources().getIdentifier("nopic", "drawable", this.getPackageName()));
            imgView.setImageResource(0);
        }
    }

    public void oneCardUp() {
        if (childPosition != 0) {
            childPosition = childPosition - 1;
            setImageView();
        } else {
            if (groupPosition != 0) {
                groupPosition = groupPosition - 1;
                setValues();
                childPosition = maxCard;
                setImageView();
            }
        }
    }

    public void oneCardDown() {
        if (childPosition != maxCard) {
            childPosition++;
            setImageView();
        } else {
            if (groupPosition != maxChar) {
                groupPosition++;
                setValues();
                childPosition = 0;
                setImageView();
            }
        }
    }

    public void setValues() {
        //TODO: IndexOutOfBoundsException being thrown for some users
        if(groupPosition != -1 && groupList.size() > groupPosition && !imageCollection.isEmpty()) {
            heroCards = imageCollection.get(groupList.get(groupPosition));
            maxCard = heroCards.size() - 1;
        }
    }

}
