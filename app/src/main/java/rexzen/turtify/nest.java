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

public class nest extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nest);
        List<abtdata> maindat = fill_with_data();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.nestrecy);
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

        data.add(new abtdata("Ecosystem Threats", "Known predators of olive ridley eggs include raccoons, coyotes, feral dogs and pigs, opossums, coatimundi, caimans, ghost crabs, and the sunbeam snake. Hatchlings are preyed upon as they travel across the beach to the water by vultures, frigate birds, crabs, raccoons, coyotes, iguanas, and snakes. \n\nIn the water, hatchling predators most likely include oceanic fishes, sharks, and crocodiles. Adults have relatively few known predators, other than sharks, and killer whales are responsible for occasional attacks. \n\nOn land, nesting females may be attacked by jaguars. It is notable that the jaguar is the only cat with a strong enough bite to penetrate a sea turtle's shell, thought to be an evolutionary adaption from the Holocene extinction event. ", R.drawable.olivec1));
        data.add(new abtdata("Fishing Nets and Pollution ", "Other major threats include mortality associated with boat collisions, and incidental takes in fisheries. Trawling, gill nets, ghost nests, longline fishing, and pot fishing, have significantly affected olive ridley populations, as well as other species of marine turtles. Between 1993 and 2003, more than 100,000 olive ridley turtles were reported dead in Odisha, India from fishery-related practices. In addition, entanglement and ingestion of marine debris is listed as a major threat for this species. \n\nCoastal development, natural disasters, climate change, and other sources of beach erosion have also been cited as potential threats to nesting grounds. Additionally, coastal development also threatens newly hatched turtles through the effects of light pollution. Hatchlings which use light cues to orient themselves to the sea are now misled into moving towards land, and die from dehydration or exhaustion, or are killed on roads." , R.drawable.olivec2));
        data.add(new abtdata("Egg loss from Arribadas", "However, the greatest single cause of olive ridley egg loss results from arribadas, in which the density of nesting females is so high, previously laid nests are inadvertently dug up and destroyed by other nesting females. \n\nIn some cases, nests become cross-contaminated by bacteria or pathogens of rotting nests. For example, in Playa Nancite, Costa Rica, only 0.2% of the 11.5 million eggs produced in a single arribada successfully hatched. \n\nAlthough some of this loss resulted from predation and high tides, the majority was attributed to conspecifics unintentionally destroying existing nests. The extent to which arribadas contribute to the population status of olive ridleys has created debate among scientists. \n\nMany believe the massive reproductive output of these nesting events is critical to maintaining populations, while others maintain the traditional arribada beaches fall far short of their reproductive potential and are most likely not sustaining population levels. In some areas, this debate eventually led to legalizing egg collection.", R.drawable.olivec3));
        data.add(new abtdata("Economic importance", "Historically, the olive ridley has been exploited for food, bait, oil, leather, and fertilizer. The meat is not considered a delicacy; the egg, however, is esteemed everywhere. Egg collection is illegal in most of the countries where olive ridleys nest, but these laws are rarely enforced. \n\nHarvesting eggs has the potential to contribute to local economies, so the unique practice of allowing a sustainable (legal) egg harvest has been attempted in several localities. Numerous case studies have been conducted in regions of arribadas beaches to investigate and understand the socioeconomic, cultural, and political issues of egg collection. \n\nOf these, the legal egg harvest at Ostional, Costa Rica, has been viewed by many as both biologically sustainable and economically viable. Since egg collection became legal in 1987, local villagers have been able to harvest and sell around three million eggs annually. They are permitted to collect eggs during the first 36 hours of the nesting period, as many of these eggs would be destroyed by later nesting females. Over 27 million eggs are left unharvested, and villagers have played a large role in protecting these nests from predators, thereby increasing hatching success.", R.drawable.olivec4));
        data.add(new abtdata("Conservation status", "The olive ridley is classified as vulnerable according to the International Union for Conservation of Nature and Natural Resources (IUCN), and is listed in Appendix I of CITES. These listings were largely responsible for halting the large scale commercial exploitation and trade of olive ridley skins. \n\nThe Convention on Migratory Species and the Inter-American Convention for the Protection and Conservation of Sea Turtles have also provided olive ridleys with protection, leading to increased conservation and management for this marine turtle. \n\nNational listings for this species range from Endangered to Threatened, yet enforcing these sanctions on a global scale has been unsuccessful for the most part. Conservation successes for the olive ridley have relied on well-coordinated national programs in combination with local communities and nongovernment organizations, which focused primarily on public outreach and education. \n\nArribada management has also played a critical role in conserving olive ridleys. Lastly, enforcing the use of turtle excluder devices in the shrimp trawling industry has also proved effective in some areas. Globally, the olive ridley continues to receive less conservation attention than its close relative, the Kemp's ridley (L. kempii). Also, many schools arrange trips for students to carry out the conservation project, especially in India.", R.drawable.olivec5));
        data.add(new abtdata("Saving the Olive Ridley", " For 40 years groups of Chennai residents have walked the beaches every night to protect the increasingly fragile Olive Ridley turtles. the turtle walks of Chennai inspired several generations of astonished encounters with nature. While other groups, including the forest department, organise them too, the oldest and most consistent group of turtle walkers is the Students’ Sea Turtle Conservation Network (SSTCN)." +
                "\n\n" +
                "Staffed entirely by volunteers, the SSTCN was founded 29 years ago and is now run by Arun Venkatraman, whose other projects include a reforestation project called The Forest Way and the Marudam Farm School, both in Tiruvannamalai." +"\n\n"+
                "\n" +
                "When the Olive Ridley hatchlings, nurtured by SSTCN, are released into the sea, they are set down a few metres before the waterline. The belief is that as they cross that final stretch on their own -- they are geo-mapping the location into their memories. This has been the way of sea turtles for millions of years." +

                "\n\n"+"Little is known about the lives of turtles underwater, but this much has long been understood: Where a turtle hatches is where she comes back to nest, a dozen or so years later. In the nearly three decades of the SSTCN’s work, Venkatraman estimates that 2,50,000 eggs have been transferred from the beaches to the hatcheries, of which 2,00,000 hatchlings have been released into the sea.", R.drawable.olivecc6));

        return data;
    }
}
