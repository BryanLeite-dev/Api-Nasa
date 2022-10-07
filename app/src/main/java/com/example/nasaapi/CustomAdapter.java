package com.example.nasaapi;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<DtoPictureDay> localDataSet;
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewNome;
        private final ImageView imageViewNasa;

        public TextView getTextViewNome() {

            return textViewNome;
        }



        public ImageView getImageViewNasa() {

            return imageViewNasa;
        }

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textViewNome = (TextView) view.findViewById(R.id.textViewNome);
            imageViewNasa = view.findViewById(R.id.imageViewNasa);
        }


    }
    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter(ArrayList<DtoPictureDay> dataSet) {

        localDataSet = dataSet;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view =  LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.itens_lista, viewGroup, false);
        return new ViewHolder(view);
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextViewNome().setText(localDataSet.get(position).title);
        Picasso.get().load(localDataSet.get(position).url).into(viewHolder.imageViewNasa);

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return localDataSet.size();
    }
}


