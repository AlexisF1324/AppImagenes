package com.stomas.appimagenes;

////Utilizo librerias
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;


// Esta clase ImageAdapter extiende (hereda) de RecyclerView.Adapter.
// Es un adaptador personalizado que se encarga de gestionar cómo se muestran
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    // Lista de URIs de las imágenes seleccionadas por el usuario
    private List<Uri> imageUriList;

    // El contexto actual (actividad o fragmento) donde se va a mostrar el RecyclerView
    private Context context;

    // Constructor del adaptador que recibe el contexto y la lista de imágenes
    public ImageAdapter(Context context, List<Uri> imageUriList) {
        this.context = context;
        this.imageUriList = imageUriList;
    }

    // Método que crea las vistas (cajas) que contendrán las imágenes dentro del RecyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar (crear) la vista desde un archivo XML llamado item_image para cada imagen
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);  // Devolver un ViewHolder que contiene la vista inflada
    }

    // Método que coloca la imagen correcta en la vista creada para cada elemento
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Obtener la URI de la imagen en la posición actual de la lista
        Uri imageUri = imageUriList.get(position);

        // Utilizar la librería Glide para cargar la imagen desde la URI en el ImageView de la vista
        Glide.with(context)
                .load(imageUri)  // Cargar la imagen usando la URI
                .into(holder.imageView);  // Colocar la imagen en el ImageView dentro del ViewHolder
    }

    // Método que devuelve la cantidad total de imágenes en la lista
    @Override
    public int getItemCount() {
        return imageUriList.size();  // Retorna el número total de imágenes a mostrar
    }

    // ViewHolder es una clase que almacena las referencias a las vistas (elementos) de cada imagen
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // El ImageView que mostrará cada imagen
        ImageView imageView;

        // Constructor del ViewHolder que recibe la vista y vincula el ImageView
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Vincular el ImageView de la vista a la variable imageView del ViewHolder
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
