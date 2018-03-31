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
 * Created by Subham Lenka on 31-03-2018.
 */

public class MyAdapterForFaculty  extends BaseAdapter {
    private final List<MyAdapterForFaculty.Item> mItems = new ArrayList<MyAdapterForFaculty.Item>();
    private final LayoutInflater mInflater;

    public MyAdapterForFaculty(Context context) {
        mInflater = LayoutInflater.from(context);

        mItems.add(new MyAdapterForFaculty.Item("Marksheet",       R.drawable.cmarksheet));
        mItems.add(new MyAdapterForFaculty.Item("Create Event",   R.drawable.events));
        mItems.add(new MyAdapterForFaculty.Item("Notice", R.drawable.rep));
        mItems.add(new MyAdapterForFaculty.Item("Energised Materials", R.drawable.csyllabus));
        mItems.add(new MyAdapterForFaculty.Item("Logout",     R.drawable.logout));
//        mItems.add(new MyAdapterForFaculty.Item("Community chat",      R.drawable.message));
//        mItems.add(new MyAdapterForFaculty.Item("Soft skill",     R.drawable.softskill));
//        mItems.add(new MyAdapterForFaculty.Item("Events",     R.drawable.events));
//        mItems.add(new MyAdapterForFaculty.Item("Energised material",     R.drawable.rep));
//        mItems.add(new MyAdapterForFaculty.Item("Grievance",     R.drawable.grievance));
//        mItems.add(new MyAdapterForFaculty.Item("Virtual Assistant",     R.drawable.assistanta));
//        mItems.add(new MyAdapterForFaculty.Item("Marksheet",     R.drawable.cmarksheet));
//        mItems.add(new MyAdapterForFaculty.Item("Log out",     R.drawable.logout));
//        mItems.add(new Item("classes",     R.drawable.degree));
//        mItems.add(new Item("Timetable",     R.drawable.degree));


    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public MyAdapterForFaculty.Item getItem(int i) {
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

        MyAdapterForFaculty.Item item = getItem(i);

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