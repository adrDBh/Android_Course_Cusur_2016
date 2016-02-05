package adrdbh.android.curso.ejercicio1.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import adrdbh.android.curso.ejercicio1.R;
import adrdbh.android.curso.ejercicio1.actividades.MainActivity;
import adrdbh.android.curso.ejercicio1.actividades.SectionActivity;

/**
 * Created by adrdbh on 2/3/16.
 */

public class CameraFragment extends Fragment {

    // Prestablished return of error code in image selection
    private static final int SELECT_PICTURE = 200;
    // Sets image background from the ImageView area
    private ImageView mSetPicture;

    public static CameraFragment newInstance() {

        CameraFragment cameraFragment = new CameraFragment();
        return cameraFragment;

    }

    @Nullable
    @Override
    // Creates a new layout and initializes it
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.camera_fragment_layout, container, false);
        // image selector button instance
        Button btnCamara = (Button) view.findViewById(R.id.btn1);
        // asing value to mSetPicture variable via image selection or media
        mSetPicture = (ImageView) view.findViewById(R.id.imgv);
        // Dialog message listener implementation after button event
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Available options Charseq array
                final CharSequence[] opt = {"Tomar foto", "Elegir de galeria", "Cancelar"};
                // Alertdialog builder to create the new focused window popout
                AlertDialog.Builder aD = new AlertDialog.Builder(getActivity());
                //Pretty self explainatory
                aD.setTitle("Opciones");
                // Option listener to wait for option selection
                aD.setItems(opt, new DialogInterface.OnClickListener() {
                    @Override
                    // Action after option selection
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            // Not yet implemented
                            openCamera();
                        } else if (which == 1) {
                            // Selects image from gallery
                            selectFromGallery();
                        } else {
                            // Closes window popout without any further action
                            dialog.dismiss();
                        }
                    }
                });
                // Renders final window
                aD.show();
            }
        });
        return view;
    }

    // Method implementation from image selection choose menu
    private void selectFromGallery() {
        // Intent to pick image from any other app installed on the phone, or the available media source
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //Type|regex extension
        intent.setType("image/*");
        // Needs method implementation to work
        startActivityForResult(intent.createChooser(intent, "Seleciona:"), SELECT_PICTURE);

    }

    @Override
    // Required method implementation to set image
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Default constructor
        super.onActivityResult(requestCode, resultCode, data);
        // Checks requestcode return value
        switch (requestCode) {
            // reads constant value and executes action
            case SELECT_PICTURE:
                // Reads Activity return code and evaluates
                if (resultCode == Activity.RESULT_OK) {
                    // gets path of image
                    Uri path = data.getData();
                    // Sets the image in the selected area
                    mSetPicture.setImageURI(path);
                }
        }
    }

    //NOT IMPLEMENTED YET
    private void openCamera() {

    }

}
