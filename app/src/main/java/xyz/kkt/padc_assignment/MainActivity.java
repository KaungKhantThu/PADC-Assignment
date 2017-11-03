package xyz.kkt.padc_assignment;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.navigate_to_map)
    Button navigateToMap;
    @BindView(R.id.sharing_with_share_compact)
    Button sharingWithShareCompact;
    @BindView(R.id.making_ph_call)
    Button makingPhCall;
    @BindView(R.id.sending_emails)
    Button sendingEmails;
    @BindView(R.id.taking_pic_with_camera)
    Button takingPicWithCamera;
    @BindView(R.id.selecting_pic_from_storage)
    Button selectingPicFromStorage;
    @BindView(R.id.saving_in_calender)
    Button savingInCalender;
    @BindView(R.id.iv_hero)
    ImageView ivHero;

    private static final int PICK_IMAGE = 1;

    private static final int REQUEST_CAMERA = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

    }

    @OnClick(R.id.fab)
    public void clickFab() {
        Snackbar.make(fab, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @OnClick(R.id.saving_in_calender)
    public void clickSavingInCalender() {
        GregorianCalendar calDate = new GregorianCalendar(2012, 10, 02);
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, "DevCon")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "https://goo.gl/maps/FUtneKdKjw62")

                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calDate.getTimeInMillis()+60*60*60*60*2)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calDate.getTimeInMillis()+60*60*60*60*4);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @OnClick(R.id.navigate_to_map)
    public void clickNavigateToMap() {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("https://goo.gl/maps/FUtneKdKjw62"));
        startActivity(intent);
    }

    @OnClick(R.id.sharing_with_share_compact)
    public void clickSharingWithShareCompact() {
        ShareCompat.IntentBuilder intentBuilder = ShareCompat.IntentBuilder.from(this);
        Intent intent = intentBuilder
                .setType("text/html")
                .setText("clickSharingWithShareCompact")
                .setChooserTitle("Choose email client")
                .createChooserIntent();

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @OnClick(R.id.making_ph_call)
    public void clickMakingPhCall() {
        String ph_num = "09770909052";

        String uri = "tel:" + ph_num.trim();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(uri));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);

    }

    @OnClick(R.id.sending_emails)
    public void clickSendingEmails() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    @OnClick(R.id.taking_pic_with_camera)
    public void clickTakingPicWithCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CAMERA);
        }
    }

    @OnClick(R.id.selecting_pic_from_storage)
    public void clickSelectingPicFromStorage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            if (selectedImage != null) {
                ivHero.setImageURI(selectedImage);
            }
        }

        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            if (imgBitmap != null) {
                ivHero.setImageBitmap(imgBitmap);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
