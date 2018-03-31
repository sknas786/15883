package shiksha.teambitcoders.com.shiksha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Subham Lenka on 28-03-2018.
 */

public class MyAdapterMenuForStudent extends BaseAdapter {
    private final List<Item> mItems = new ArrayList<Item>();
    private final LayoutInflater mInflater;

    public MyAdapterMenuForStudent(Context context) {
        mInflater = LayoutInflater.from(context);

        mItems.add(new Item("College info",       R.drawable.route));
        mItems.add(new Item("Timetable",   R.drawable.marksheet));
        mItems.add(new Item("Doubt", R.drawable.video_call));
        mItems.add(new Item("Community chat",      R.drawable.message));
        mItems.add(new Item("Soft skill",     R.drawable.softskill));
        mItems.add(new Item("Events",     R.drawable.events));
        mItems.add(new Item("Daily Notice",     R.drawable.csyllabus));
        mItems.add(new Item("Grievance",     R.drawable.grievance));
        mItems.add(new Item("Virtual Assistant",     R.drawable.assistanta));
        mItems.add(new Item("Marksheet",     R.drawable.cmarksheet));
        mItems.add(new Item("Attendance Report",     R.drawable.rep));
        mItems.add(new Item("Log out",     R.drawable.logout));
//        mItems.add(new Item("classes",     R.drawable.degree));
//        mItems.add(new Item("Timetable",     R.drawable.degree));


    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }


//        Typeface   =Typeface.createFromAsset(mInflater.getContext().getAssets(),
//                "fonts/opificio_regular.ttf");
        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);

//        name.setTypeface(face);

        Item item = getItem(i);

        picture.setImageResource(item.drawableId);
        name.setText(item.name);

        return v;
    }

    private static class Item {
        public final String name;
        public final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}