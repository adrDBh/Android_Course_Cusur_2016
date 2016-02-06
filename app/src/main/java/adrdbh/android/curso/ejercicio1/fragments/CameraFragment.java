package adrdbh.android.curso.ejercicio1.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.util.BitSet;

import adrdbh.android.curso.ejercicio1.R;
import adrdbh.android.curso.ejercicio1.actividades.MainActivity;
import adrdbh.android.curso.ejercicio1.actividades.SectionActivity;

/**
 * Created by adrdbh on 2/3/16.
 */

public class CameraFragment extends Fragment {

    // Image variable that covers all image extensions
    private Bitmap mBitmap;
    private String mDir;
    private static String MEDIA_DIRECTORY = "Ejercicio1/media";
    private static String TEMPORAL_PICTURE_NAME = "temp.jpg";
    // Prestablished return of error code in running camera
    private final static int CAMERA = 100;
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
                            // Opens camera,takes picture and sets background
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
                    // Sets the image in the selected area (view)
                    mSetPicture.setImageURI(path);
                }
                break;
            case CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    mDir = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;
                    mBitmap = BitmapFactory.decodeFile(mDir);
                    mSetPicture.setImageBitmap(mBitmap);
                }
                break;
        }
    }

    //Required method to launch, write, capture and save Camera pictures.
    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        // Saves file t dir
        file.mkdirs();
        // String variale that asigns image save path value
        String path = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;
        // Creates the new file and stores it
        File newFile = new File(path);
        // Grants focus to the camera app
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Get image in Uri format
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
        // Starts intent and opens camera
        startActivityForResult(intent, CAMERA);
    }

}
