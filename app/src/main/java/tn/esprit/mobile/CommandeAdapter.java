package tn.esprit.mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.mobile.Entities.CommandeModel;

public class CommandeAdapter extends RecyclerView.Adapter<CommandeAdapter.CommandeViewHolder> {

    private Context context;
    private List<CommandeModel> commandesList;

    public CommandeAdapter(Context context, List<CommandeModel> commandesList) {
        this.context = context;
        this.commandesList = commandesList;
    }

    @NonNull
    @Override
    public CommandeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_commande, parent, false);
        return new CommandeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommandeViewHolder holder, int position) {
        CommandeModel commande = commandesList.get(position);

        // Set the data to the TextViews
        holder.textCodeCommande.setText(commande.getCodeCommande());
        holder.textCommandeTitle.setText(commande.getCommandeTitle());
        holder.textAdresse.setText(commande.getAdresse());
        holder.textQte.setText(commande.getQte());
        holder.textTotal.setText(commande.getTotal());

        // Set other data to corresponding TextViews
    }

    @Override
    public int getItemCount() {
        return commandesList.size();
    }

    static class CommandeViewHolder extends RecyclerView.ViewHolder {
        TextView textCodeCommande,textCommandeTitle,textAdresse,textQte,textTotal;

        CommandeViewHolder(@NonNull View itemView) {
            super(itemView);
            textCodeCommande = itemView.findViewById(R.id.textCodeCommande);
            textCommandeTitle = itemView.findViewById(R.id.textCommandeTitle);
            textAdresse = itemView.findViewById(R.id.textAdresse);
            textQte = itemView.findViewById(R.id.textQte);
            textTotal = itemView.findViewById(R.id.textTotal);

            // Initialize other TextViews here
        }
    }
}
