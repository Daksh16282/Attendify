package com.example.attendancerecord;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {

    ArrayList<Attendance> dataSet;
    boolean flagp=false;
    boolean flaga=false;

    boolean flagu=false;
    Helper helper;
    HomeFragment fragment;

    DateHelper dateHelper;

    ArrayList<DateSet> dateSets;

    Context context;

    SubjectAdapter(Context context, ArrayList<Attendance> dataSet, Helper helper, HomeFragment fragment) {
        this.dataSet = dataSet;
        this.context = context;
        this.helper = helper;
        this.fragment = fragment;
        dateHelper=DateHelper.getInstance(context);
    }



    @NonNull
    @Override
    public SubjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ui = LayoutInflater.from(context).inflate(R.layout.subject_adapter, parent, false);
        return new ViewHolder(ui);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.setPrs(dataSet.get(position).getPresents());
        holder.setAbs(dataSet.get(position).getAbsents());
        holder.setCrequired(dataSet.get(position).getRequired());
        holder.setCpercentage(dataSet.get(position).getPercentage());
        holder.setTotal(dataSet.get(position).getPresents() + dataSet.get(position).getAbsents());

        holder.bind();
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                                                   @Override
                                                   public boolean onLongClick(View v) {
                                                       Dialog dialogMain = new Dialog(context);
                                                       dialogMain.setContentView(R.layout.options);
                                                       WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                                                       lp.copyFrom(dialogMain.getWindow().getAttributes());
                                                       lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                                                       lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                                                       dialogMain.getWindow().setAttributes(lp);
                                                       dialogMain.show();
                                                        dialogMain.setCancelable(true);
                                                       TextView del = dialogMain.findViewById(R.id.del);
                                                       TextView reset = dialogMain.findViewById(R.id.reset);
                                                       TextView edit = dialogMain.findViewById(R.id.edit);
                                                       TextView history = dialogMain.findViewById(R.id.history);

                                                       del.setOnClickListener(new View.OnClickListener() {
                                                           @Override
                                                           public void onClick(View v) {
                                                               dialogMain.dismiss();
                                                               Dialog dialog = new Dialog(context);
                                                               dialog.setContentView(R.layout.delete_dialog);
                                                               dialog.setCancelable(false);
                                                               WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                                                               lp.copyFrom(dialog.getWindow().getAttributes());
                                                               lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                                                               lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                                                               dialog.getWindow().setAttributes(lp);
                                                               dialog.show();

                                                               Button no = dialog.findViewById(R.id.no);
                                                               no.setOnClickListener(new View.OnClickListener() {
                                                                   @Override
                                                                   public void onClick(View v) {
                                                                       dialog.dismiss();

                                                                   }
                                                               });

                                                               Button yes = dialog.findViewById(R.id.yes);
                                                               yes.setOnClickListener(new View.OnClickListener() {
                                                                   @Override
                                                                   public void onClick(View v) {
                                                                       helper.getDao().deleteRecord(new Attendance(dataSet.get(position).getKey(), dataSet.get(position).getSubject(), dataSet.get(position).getAbsents(), dataSet.get(position).getPresents(), dataSet.get(position).getRequired(), dataSet.get(position).getPercentage()));
                                                                       dateHelper.getDateDao().deleteDate(dataSet.get(position).getSubject());
                                                                       fragment.showData();
                                                                       dialog.dismiss();

                                                                   }
                                                               });

                                                           }
                                                       });
                                                       reset.setOnClickListener(new View.OnClickListener() {
                                                           @Override
                                                           public void onClick(View v) {
                                                               dialogMain.dismiss();
                                                               helper.getDao().updateRecord(new Attendance(dataSet.get(position).getKey(), dataSet.get(position).getSubject(), 0, 0,0,0));
                                                               dateHelper.getDateDao().deleteDate(dataSet.get(position).getSubject());
                                                               fragment.showData();

                                                           }
                                                       });
                                                       edit.setOnClickListener(new View.OnClickListener() {
                                                           @Override
                                                           public void onClick(View v) {
                                                               dialogMain.dismiss();
                                                                 Dialog dialog = new Dialog(context);
                                                               dialog.setContentView(R.layout.edit_data);
                                                               dialog.setCancelable(false);
                                                               WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                                                               lp.copyFrom(dialog.getWindow().getAttributes());
                                                               lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                                                               lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                                                               dialog.getWindow().setAttributes(lp);
                                                               dialog.show();

                                                               EditText e1=dialog.findViewById(R.id.cprs);
                                                               EditText e2=dialog.findViewById(R.id.cabs);
                                                               Button esave=dialog.findViewById(R.id.esave);
                                                               Button ecancel=dialog.findViewById(R.id.ecancel);
                                                               esave.setOnClickListener(new View.OnClickListener() {
                                                                   @Override
                                                                   public void onClick(View v) {
                                                                       int a=0,b=0;
                                                                       if(e1.getText().toString().equals("")){
                                                                           Toast.makeText(context, "Please Enter valid value for presents", Toast.LENGTH_SHORT).show();
                                                                       }else{
                                                                           a=Integer.parseInt(e1.getText().toString());
                                                                       }

                                                                       if(e2.getText().toString().equals("")){
                                                                           Toast.makeText(context, "Please Enter valid value for absents", Toast.LENGTH_SHORT).show();
                                                                       }else{
                                                                           b=Integer.parseInt(e2.getText().toString());
                                                                       }
                                                                       if(!e1.getText().toString().equals("") && !e2.getText().toString().equals("")) {
                                                                           helper.getDao().updateRecord(new Attendance(dataSet.get(position).getKey(), dataSet.get(position).getSubject(), b, a, calculateRequired(a, (a + b)), calculatePercentage(a, (a + b))));
                                                                           fragment.showData();
                                                                           dateSets=(ArrayList<DateSet>) dateHelper.getDateDao().getDateRecord();

                                                                           for (int i = 0; i < dateSets.size(); i++) {
                                                                               if(dateSets.get(i).getSubject().equals(dataSet.get(position).getSubject()) && dateSets.get(i).getDate().toString().equals(LocalDate.now().toString())){
                                                                                   dateHelper.getDateDao().updatePA(dataSet.get(position).getSubject(),LocalDate.now(),a,b);
                                                                                   flagu=true;
                                                                                   break;
                                                                               }
                                                                           }
                                                                           if(!flagu){
                                                                               dateHelper.getDateDao().insertDate(new DateSet(dataSet.get(position).getSubject(),LocalDate.now(),a,b));
                                                                           }
                                                                            flagu=false;


                                                                           dialog.dismiss();
                                                                       }

                                                                   }
                                                               });
                                                               ecancel.setOnClickListener(new View.OnClickListener() {
                                                                   @Override
                                                                   public void onClick(View v) {
                                                                       dialog.dismiss();

                                                                   }
                                                               });

                                                           }
                                                       });
                                                       history.setOnClickListener(new View.OnClickListener() {
                                                           @Override
                                                           public void onClick(View v) {
                                                               dateSets=(ArrayList<DateSet>) dateHelper.getDateDao().getDateRecord();
                                                               if(dateSets.size()!=0) {


                                                                   Intent intent = new Intent(context, HistoryRecord.class);
                                                                   intent.putExtra("SUB",dataSet.get(position).getSubject());
                                                                   context.startActivity(intent);
                                                                   dialogMain.dismiss();
                                                               }else{
                                                                   Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show();
                                                                   dialogMain.dismiss();
                                                               }
                                                           }
                                                       });
                                                       return true;
                                                   }
                                               });

        holder.getSubName().setText(dataSet.get(position).subject);
        holder.getAdd().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.addFunction();
                helper.getDao().updateRecord(new Attendance(dataSet.get(position).getKey(), dataSet.get(position).getSubject(), holder.getAbs(), holder.getPrs(), holder.getCrequired(), holder.getCpercentage()));
                fragment.showData();
                dateSets=(ArrayList<DateSet>) dateHelper.getDateDao().getDateRecord();
                for (int i = 0; i < dateSets.size(); i++) {
                    if(dateSets.get(i).getSubject().equals(dataSet.get(position).getSubject()) && dateSets.get(i).getDate().toString().equals(LocalDate.now().toString())){
                        dateHelper.getDateDao().updatePrs(dataSet.get(position).getSubject(),LocalDate.now());
                        flagp=true;
                        break;
                    }
                }
                if(!flagp){
                    dateHelper.getDateDao().insertDate(new DateSet(dataSet.get(position).getSubject(),LocalDate.now(),1,0));
                    flagp=false;
                }

            }
        });

        holder.getRemove().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.removeFunc();
                helper.getDao().updateRecord(new Attendance(dataSet.get(position).getKey(), dataSet.get(position).getSubject(), holder.getAbs(), holder.getPrs(), holder.getCrequired(), holder.getCpercentage()));
                fragment.showData();
                dateSets=(ArrayList<DateSet>) dateHelper.getDateDao().getDateRecord();
                for (int i = 0; i < dateSets.size(); i++) {
                    if(dateSets.get(i).getSubject().equals(dataSet.get(position).getSubject()) && dateSets.get(i).getDate().toString().equals(LocalDate.now().toString())){
                        dateHelper.getDateDao().updateAbs(dataSet.get(position).getSubject(),LocalDate.now());
                        flaga=true;
                        break;
                    }
                }
                if(!flaga){
                    dateHelper.getDateDao().insertDate(new DateSet(dataSet.get(position).getSubject(),LocalDate.now(),1,0));
                    flaga=false;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView subName, absent, present, required, percentage;
        Button add, remove;
        ProgressBar progressBar;
        int prs, abs, crequired, cpercentage, total;

        public ProgressBar getProgressBar() {
            return progressBar;
        }

        public void setProgressBar(ProgressBar progressBar) {
            this.progressBar = progressBar;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subName = itemView.findViewById(R.id.subName);
            absent = itemView.findViewById(R.id.absentCount);
            present = itemView.findViewById(R.id.presentCount);
            required = itemView.findViewById(R.id.requiredCount);
            add = itemView.findViewById(R.id.add);
            remove = itemView.findViewById(R.id.remove);
            percentage = itemView.findViewById(R.id.percentage);
            progressBar = itemView.findViewById(R.id.progress_circular);
        }

        public void bind() {
            getPresent().setText("Present : " + prs);
            getAbsent().setText("Absent : " + abs);
            getPercentage().setText(cpercentage + "%");
            getProgressBar().setProgress(cpercentage, true);
            getRequired().setText("Required : " + crequired);
        }

        public void addFunction() {


            ++prs;
            total = prs + abs;
            if (total != 0) {
                cpercentage = calculatePercentage(prs, total);
                crequired = calculateRequired(prs, total);
            }

            getPresent().setText("Presents : " + prs);

            if (total != 0) {
                cpercentage = calculatePercentage(prs, total);
                getPercentage().setText(cpercentage + "%");
                getProgressBar().setProgress(cpercentage, true);
                crequired = calculateRequired(prs, total);
                getRequired().setText("Required : " + crequired);

                if (cpercentage >= 75) {
                    crequired = 0;
                    getRequired().setText("Required : " + crequired);
                }
            }


        }

        public void removeFunc() {
            ++abs;
            total = prs + abs;
            if (total != 0) {
                cpercentage = calculatePercentage(prs, total);
                crequired = calculateRequired(prs, total);
            }

            getAbsent().setText("Absent : " + getAbs());

            if (total != 0) {
                cpercentage = calculatePercentage(prs, total);
                getPercentage().setText(cpercentage + "%");
                getProgressBar().setProgress(cpercentage, true);
                crequired = calculateRequired(prs, total);
                getRequired().setText("Required : " + crequired);
                if (cpercentage >= 75) {
                    crequired = 0;
                    getRequired().setText("Required : " + crequired);
                }
            }
        }

        public TextView getSubName() {
            return subName;
        }

        public void setSubName(TextView subName) {
            this.subName = subName;
        }

        public TextView getAbsent() {
            return absent;
        }

        public void setAbsent(TextView absent) {
            this.absent = absent;
        }

        public TextView getPresent() {
            return present;
        }

        public void setPresent(TextView present) {
            this.present = present;
        }

        public TextView getRequired() {
            return required;
        }

        public void setRequired(TextView required) {
            this.required = required;
        }

        public Button getAdd() {
            return add;
        }

        public void setAdd(Button add) {
            this.add = add;
        }

        public Button getRemove() {
            return remove;
        }

        public void setRemove(Button remove) {
            this.remove = remove;
        }

        public TextView getPercentage() {
            return percentage;
        }

        public void setPercentage(TextView percentage) {
            this.percentage = percentage;
        }

        public int getPrs() {
            return prs;
        }

        public void setPrs(int prs) {
            this.prs = prs;
        }

        public int getAbs() {
            return abs;
        }

        public void setAbs(int abs) {
            this.abs = abs;
        }

        public int getCrequired() {
            return crequired;
        }

        public void setCrequired(int crequired) {
            this.crequired = crequired;
        }

        public int getCpercentage() {
            return cpercentage;
        }

        public void setCpercentage(int cpercentage) {
            this.cpercentage = cpercentage;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public static int calculatePercentage(int prs, int total) {
        return (prs * 100) / total;
    }

    public static int calculateRequired(int prs, int total) {
        int req = 0;
        while (calculatePercentage(prs + req, total + req) < 75) {
            req++;
        }
        return req;
    }




}



