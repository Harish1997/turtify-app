package rexzen.turtify;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harishananth on 09/06/17.
 */

public class theapp extends AppCompatActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.theapp);
        List<abtdata> maindat = fill_with_data();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.abtturtlerecy);
        abtView_Adapter adapter = new abtView_Adapter(maindat,this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
    }

    public List<abtdata> fill_with_data() {

        List<abtdata> data = new ArrayList<>();

        data.add(new abtdata("Taxonomy", "The genus name is derived from the Greek words lepidos, meaning scale, and chelys, which translates to turtle. This could possibly be a reference to the supernumerary costal scute counts characteristic of this genus.The etymology of the English vernacular name \"olive\" is somewhat easier to resolve, as its carapace is olive green in color.However, the origin of \"ridley\" is still somewhat unclear, perhaps derived from \"riddle\".", R.drawable.oliveone1));
        //data.add(new data("Bus Schedule", "Anyone can drive a car,It takes someone special to drive a college bus", R.drawable.svcebus));
        data.add(new abtdata("Description", "Growing to about 2 feet in length, the Olive ridley gets its name from its olive-colored carapace, which is heart-shaped and rounded. Males and females grow to the same size; however, females have a slightly more rounded carapace as compared to the male.The heart-shaped carapace is characterized by four pairs of pore-bearing inframarginal scutes on the bridge, two pairs of prefrontals, and up to 9 lateral scutes per side. Olive ridleys are unique in that they can have variable and asymmetrical lateral scute 6 to 8 counts ranging from five to 9 plates on each side, with six to eight being most commonly observed.Each side of the carapace has 12–14 marginal scutes.", R.drawable.olive1));
        data.add(new abtdata("Distribution", "The olive ridley turtle has a circumtropical distribution, living in tropical and warm waters of the Pacific and Indian Oceans from India, Arabia, Japan, and Micronesia south to southern Africa, Australia, and New Zealand. In the Atlantic Ocean, it has been observed off the western coast of Africa and the coasts of northern Brazil, Suriname, Guyana, French Guiana, and Venezuela. Additionally, the olive ridley has been recorded in the Caribbean Sea as far north as Puerto Rico.", R.drawable.olive2));
        data.add(new abtdata("Nesting Grounds", "Olive ridley turtles are best known for their behavior of synchronized nesting in mass numbers, termed arribadas.Females return to the same beach from where they first hatched, to lay their eggs. They lay their eggs in conical nests about one and a half feet deep which they laboriously dig with their hind flippers.In the Indian Ocean, the majority of olive ridleys nest in two or three large groups near Gahirmatha in Odisha. The coast of Odisha in India is the largest mass nesting site for the olive ridley, followed by the coasts of Mexico and Costa Rica.In 1991, over 600,000 turtles nested along the coast of Odisha in one week. Nesting occurs elsewhere along the Coromandel Coast and Sri Lanka, but in scattered locations. However, olive ridleys are considered a rarity in most areas of the Indian Ocean.", R.drawable.locations));
        data.add(new abtdata("Reproduction", "Mating is often assumed to occur in the vicinity of nesting beaches, but copulating pairs have been reported over 1,000 km from the nearest beach. Research from Costa Rica revealed the number of copulating pairs observed near the beach could not be responsible for the fertilization of the tens of thousands of gravid females, so a significant amount of mating is believed to have occurred elsewhere at other times of the year.", R.drawable.olive3));
        //data.add(new data("Be a developer", "Innovation distinguishes between a leader and a follower", R.drawable.bedev));
        return data;
    }

}
