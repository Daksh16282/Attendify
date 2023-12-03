package com.example.attendancerecord;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    Context context;
    ArrayList<DateSet> historydataset;


    public HistoryAdapter(Context context, ArrayList<DateSet> historydataset) {
        this.context = context;
        this.historydataset = historydataset;

    }


    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View hi= LayoutInflater.from(context).inflate(R.layout.activity_history_record,parent,false);
        return new ViewHolder(hi);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {

            holder.getDateTitle().setText(historydataset.get(position).getDate().toString());
                holder.getAbsCount().setText(String.valueOf(historydataset.get(position).getAbsents()));
                holder.getPrsCount().setText(String.valueOf(historydataset.get(position).getPresents()));



    }

    @Override
    public int getItemCount() {
        return historydataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTitle,prsCount,absCount;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTitle=itemView.findViewById(R.id.dateTitle);
            prsCount=itemView.findViewById(R.id.prsCount);
            absCount=itemView.findViewById(R.id.absCount);
            linearLayout=itemView.findViewById(R.id.linearLayout);
        }

        public TextView getDateTitle() {
            return dateTitle;
        }

        public void setDateTitle(TextView dateTitle) {
            this.dateTitle = dateTitle;
        }

        public TextView getPrsCount() {
            return prsCount;
        }

        public void setPrsCount(TextView prsCount) {
            this.prsCount = prsCount;
        }

        public TextView getAbsCount() {
            return absCount;
        }

        public void setAbsCount(TextView absCount) {
            this.absCount = absCount;
        }


        public LinearLayout getLinearLayout() {
            return linearLayout;
        }

        public void setLinearLayout(LinearLayout linearLayout) {
            this.linearLayout = linearLayout;
        }
    }
}
