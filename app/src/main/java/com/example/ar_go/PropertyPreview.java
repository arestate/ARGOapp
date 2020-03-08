package com.example.ar_go;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.ar_go.Adapter.RoomComponentsListAdapter;
import com.example.ar_go.Models.PropertyAreaInfoVo;
import com.example.ar_go.Models.RoomComponentResultVo;
import com.example.ar_go.Models.RoomComponentsInfoVo;
import com.example.ar_go.utils.Constants;
import com.google.android.cameraview.CameraView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PropertyPreview extends AppCompatActivity implements RoomComponentsListAdapter.OnItemClickListner {
    private CameraView mCameraView;
    private ImageView image,image1;
    private ImageView imgpick;
    SeekBar seekBar;
    //PhotoViewAttacher photoAttacher;


    private static final String TAG = "PreviewActivity";

    private float xCoOrdinate, yCoOrdinate;

    private static final int REQUEST_CAMERA_PERMISSION = 1;

    private static final String FRAGMENT_DIALOG = "dialog";

    //String dataimage;

    // these matrices will be used to move and zoom image
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();

    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;

    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;

    private float[] lastEvent = null;
    Bitmap bmp= null;

    RecyclerView recvRoomComponent;

    RoomComponentsInfoVo roomComponentsInfoVo = null;
    PropertyAreaInfoVo propertyAreaInfoVo = null;

    ArrayList<String> AreaList = new ArrayList<>();

    RoomComponentsListAdapter adapter= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_preview);

        recvRoomComponent = (RecyclerView)findViewById(R.id.recvRoomComponent);
        recvRoomComponent.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        String data = getIntent().getStringExtra("data");

        if (!TextUtils.isEmpty(data)) {

            roomComponentsInfoVo = new Gson().fromJson(data,RoomComponentsInfoVo.class);

            if (roomComponentsInfoVo != null) {

                adapter = new RoomComponentsListAdapter(roomComponentsInfoVo.getResult(),this);
                recvRoomComponent.setAdapter(adapter);

            }

        }
        String area_data = getIntent().getStringExtra("area_data");

        if (!TextUtils.isEmpty(area_data)) {

            propertyAreaInfoVo = new Gson().fromJson(area_data,PropertyAreaInfoVo.class);

            if (propertyAreaInfoVo != null) {

                for (int i=0 ; i< propertyAreaInfoVo.getResult().size(); i++)
                {
                    AreaList.add(propertyAreaInfoVo.getResult().get(i).getPaRoomtype());
                }
            }

        }

        mCameraView = (CameraView) findViewById(R.id.camera);
        image = (ImageView) findViewById(R.id.image);
        image1 = (ImageView) findViewById(R.id.image1);
        imgpick = (ImageView) findViewById(R.id.imgpick);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        if (mCameraView != null) {
            mCameraView.addCallback(mCallback);
        }
/*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        //photoAttacher= new PhotoViewAttacher(image);

        /*image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // handle touch events here
                ImageView view = (ImageView) v;
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        savedMatrix.set(matrix);
                        start.set(event.getX(), event.getY());
                        mode = DRAG;
                        lastEvent = null;
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = spacing(event);
                        if (oldDist > 10f) {
                            savedMatrix.set(matrix);
                            midPoint(mid, event);
                            mode = ZOOM;
                        }
                        lastEvent = new float[4];
                        lastEvent[0] = event.getX(0);
                        lastEvent[1] = event.getX(1);
                        lastEvent[2] = event.getY(0);
                        lastEvent[3] = event.getY(1);
                        d = rotation(event);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        mode = NONE;
                        lastEvent = null;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == DRAG) {
                            matrix.set(savedMatrix);
                            float dx = event.getX() - start.x;
                            float dy = event.getY() - start.y;
                            matrix.postTranslate(dx, dy);
                        } else if (mode == ZOOM) {
                            float newDist = spacing(event);
                            if (newDist > 10f) {
                                matrix.set(savedMatrix);
                                float scale = (newDist / oldDist);
                                matrix.postScale(scale, scale, mid.x, mid.y);
                            }
                            if (lastEvent != null && event.getPointerCount() == 3) {
                                newRot = rotation(event);
                                float r = newRot - d;
                                float[] values = new float[9];
                                matrix.getValues(values);
                                float tx = values[2];
                                float ty = values[5];
                                float sx = values[0];
                                float xc = (view.getWidth() / 2) * sx;
                                float yc = (view.getHeight() / 2) * sx;
                                matrix.postRotate(r, tx + xc, ty + yc);
                            }
                        }
                        break;
                }

                view.setImageMatrix(matrix);
                return true;

            }
        });*/

        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        xCoOrdinate = view.getX() - event.getRawX();
                        yCoOrdinate = view.getY() - event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });


        image1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        xCoOrdinate = view.getX() - event.getRawX();
                        yCoOrdinate = view.getY() - event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                float scale =  ((progress / 15.0f)+1);
                image.setScaleX(scale);
                image.setScaleY(scale);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        /*Target mTarget = new Target() {
            @Override
            public void onBitmapLoaded (final Bitmap bitmap, Picasso.LoadedFrom from){
                //Do something

                //bmp = getResizedBitmap(bitmap,600,600);

                image.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 600, 600, false));
                //photoAttacher.update();

                *//*if (bmp != null && !bmp.isRecycled()) {
                    bmp.recycle();
                }*//*


                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                        float scale =  ((progress / 15.0f)+1);
                        image.setScaleX(scale);
                        image.setScaleY(scale);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        if(!TextUtils.isEmpty(dataimage)) {
            Picasso.with(this)
                    .load(dataimage)
                    .into(mTarget);
        }*/

    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float)Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }


    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    private CameraView.Callback mCallback
            = new CameraView.Callback() {

        @Override
        public void onCameraOpened(CameraView cameraView) {
            Log.d(TAG, "onCameraOpened");
        }

        @Override
        public void onCameraClosed(CameraView cameraView) {
            Log.d(TAG, "onCameraClosed");
        }

        @Override
        public void onPictureTaken(CameraView cameraView, final byte[] data) {
            Log.d(TAG, "onPictureTaken " + data.length);
        }

    };


    @Override
    protected void onResume() {
        super.onResume();

        mCameraView.start();

    }

    @Override
    protected void onPause() {
        mCameraView.stop();
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.rotate_left:

                float r = image.getRotation();

                r= r - 10;

                image.setRotation(r);

                return true;

            case R.id.rotate_right:

                float r1 = image.getRotation();

                r1= r1 + 10;

                image.setRotation(r1);

                return true;

                case R.id.action_select_area:

               getarealist();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemListener(int pos, RoomComponentResultVo roomComponentResultVo) {

        image.setVisibility(View.VISIBLE);

        if (!TextUtils.isEmpty(roomComponentResultVo.getRImage())) {
            Picasso.get().load(Constants.IMAGE_Url + roomComponentResultVo.getRImage()).into(image);
        }


    }

    public void getarealist()
    {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(PropertyPreview.this);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(PropertyPreview.this, android.R.layout.select_dialog_singlechoice,AreaList);

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);

                mCameraView.setVisibility(View.GONE);
                imgpick.setVisibility(View.VISIBLE);

                if (propertyAreaInfoVo != null) {

                    if(propertyAreaInfoVo.getResult().size() > 0) {

                        if (!TextUtils.isEmpty(propertyAreaInfoVo.getResult().get(which).getPaImage())) {
                            Picasso.get().load(Constants.Webserive_Url+propertyAreaInfoVo.getResult().get(which).getPaImage()).into(imgpick);
                        }

                    }

                }

                if (adapter != null)
                    adapter.filter(strName);

            }
        });
        builderSingle.show();

    }
}


