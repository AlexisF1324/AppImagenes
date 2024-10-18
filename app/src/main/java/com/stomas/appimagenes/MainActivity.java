package com.stomas.appimagenes;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//Utilizo librerias
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    // Código para identificar la solicitud de selección de imágenes.
    private static final int PICK_IMAGE_REQUEST = 1;
    // Referencia al RecyclerView que mostrará las imágenes.
    private RecyclerView recyclerView;
    // Clase Adaptador que vincula las imágenes al RecyclerView.
    private ImageAdapter imageAdapter;
    // Lista que almacena los URI de las imágenes seleccionadas.
    private List<Uri> imageUriList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencia al botón para seleccionar imágenes
        Button btnSelectImage = findViewById(R.id.btnSeleccionarImagen);
        // Obtener referencia al RecyclerView
        recyclerView = findViewById(R.id.recyclerView);

        // Configurar el RecyclerView con un LayoutManager para mostrar los elementos en una lista vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Crear el adaptador pasando la lista de URIs
        imageAdapter = new ImageAdapter(this, imageUriList);
        // Asignar el adaptador al RecyclerView
        recyclerView.setAdapter(imageAdapter);

        // Configurar el evento para el botón de seleccionar imágenes
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamar al método que abre la galería para seleccionar imágenes
                selectImage();
            }
        });
    }

    // Método para abrir la galería y seleccionar imágenes
    private void selectImage() {
        // Crear un nuevo intent
        Intent intent = new Intent();
        // Establecer que el tipo de archivo a seleccionar es una imagen
        intent.setType("image/*");
        // Definir la acción para obtener contenido
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // Iniciar la actividad de selección de imagen
        startActivityForResult(Intent.createChooser(intent, "Seleccionar Imagen"), PICK_IMAGE_REQUEST);
    }


    // Verificar si la solicitud es la correspondiente a la selección de imágenes (PICK_IMAGE_REQUEST)
    // y si el resultado de la selección fue exitoso (RESULT_OK), además de comprobar que los datos no sean nulos.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Obtener la URI de la imagen seleccionada por el usuario
            Uri imageUri = data.getData();
            // Agregar la URI de la imagen a la lista de imágenes
            imageUriList.add(imageUri);
            // Notificar al adaptador que la lista de imágenes ha cambiado,
            // para que actualice y muestre la nueva imagen en el RecyclerVie
            imageAdapter.notifyDataSetChanged();

        }
    }


}