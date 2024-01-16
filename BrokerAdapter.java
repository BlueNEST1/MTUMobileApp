package com.example.mobileproject2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//for holding multiple data. Will be notes in this case with its title and description

public class BrokerAdapter extends RecyclerView.Adapter <BrokerAdapter.BrokerHolder>{
    List<Broker> brokerArrayList = new ArrayList<>();
    Context context;//for current state of application, used for getting info on any activity

    public BrokerAdapter(List<Broker> notesModelList, Context context) {
        this.brokerArrayList = notesModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public BrokerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //help display items on recyclerview
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.notes_layout,parent, false);//
        return new BrokerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrokerHolder holder, @SuppressLint("RecyclerView") int position) {
        //for binding data to view, setting data to UI component, making it visible to user
        holder.textTitle.setText(brokerArrayList.get(position).getUserName());//set title on list
        holder.textDescription.setText(brokerArrayList.get(position).getEmail());//set description on list
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context, "long click", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(context)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {

        return brokerArrayList.size();
    }

    //for list
    class BrokerHolder extends RecyclerView.ViewHolder{
        TextView textTitle, textDescription;
        public BrokerHolder(@NonNull View itemView) {
            super(itemView);
            textTitle=itemView.findViewById(R.id.notesTitle);
            textDescription=itemView.findViewById(R.id.Description);



        }//

    }
}
