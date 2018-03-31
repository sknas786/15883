package shiksha.teambitcoders.com.shiksha;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class ReportAttendanceActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_attendance);

        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);

        mPieChart.addPieSlice(new PieModel("This week", 6 , Color.parseColor("#FE6DA8")));
        mPieChart.addPieSlice(new PieModel("This month", 25, Color.parseColor("#56B7F1")));
//        mPieChart.addPieSlice(new PieModel("This semester", 145, Color.parseColor("#CDA67F")));
        mPieChart.addPieSlice(new PieModel("This semester", 70 , Color.parseColor("#FED70E")));

        mPieChart.startAnimation();
    }
}
