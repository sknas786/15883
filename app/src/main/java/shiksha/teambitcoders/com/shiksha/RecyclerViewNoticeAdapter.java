package shiksha.teambitcoders.com.shiksha;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewNoticeAdapter extends RecyclerView.Adapter<RecyclerViewNoticeAdapter.ViewHolder>  {

    Context context;
    List<Noticeboardclass> MainImageUploadInfoList;
    String catchString;

    public RecyclerViewNoticeAdapter(Context context, List<Noticeboardclass> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_notice, parent, false);

        ViewHolder viewHolder = new ViewHolder(view,MainImageUploadInfoList);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Noticeboardclass noticeboardclass = MainImageUploadInfoList.get(position);

//        catchString=noticeboardclass.getNoticeDate();
//        DateFormat originalFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
//        DateFormat targetFormat = new SimpleDateFormat("dd MMM, yyyy");
//        Date date = null;
//        try {
//            date = originalFormat.parse(catchString);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String formattedDate = targetFormat.format(date);

        holder.EveNameTextView.setText(noticeboardclass.getNoticeDetails());

        holder.EveDateTextView.setText(noticeboardclass.getNoticeName());


    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView EveNameTextView;
        public TextView EveDateTextView;
        private List<Noticeboardclass> MainImageUploadInfoList;
        final Context context = itemView.getContext();



        public ViewHolder(View itemView,List<Noticeboardclass> MainImageUploadInfoList) {


            super(itemView);
            this.MainImageUploadInfoList = MainImageUploadInfoList;
//            Typeface face=Typeface.createFromAsset(itemView.getContext().getAssets(),
//                    "fonts/penna.otf");
            EveNameTextView = (TextView) itemView.findViewById(R.id.EveName);

            EveDateTextView = (TextView) itemView.findViewById(R.id.EveDate);



            itemView.setOnClickListener(this);


//            StudentNameTextView.setTypeface(face);
//            StudentNumberTextView.setTypeface(face);
//            StudentNameTextView2.setTypeface(face);
//            StudentNumberTextView2 .setTypeface(face);
//            StudentPasswordTextView2.setTypeface(face);
        }

        @Override
        public void onClick(View v) {
//            String taskTitle = MainImageUploadInfoList.get(getAdapterPosition()).getEventDate();
//            Toast.makeText(context,taskTitle,Toast.LENGTH_LONG).show();

        }
    }

}
