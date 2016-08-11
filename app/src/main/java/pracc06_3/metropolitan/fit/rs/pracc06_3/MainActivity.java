package pracc06_3.metropolitan.fit.rs.pracc06_3;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText inputId, inputName, inputIndex, inputPoints;
    private Button saveInfoButton, viewInfoButton, updateInfoButton, deleteInfoButton;
    private ArrayList<Student> studentsList;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        Toast.makeText(MainActivity.this, "all good baby", Toast.LENGTH_SHORT).show();
    }

    private void init() {
        db = new DatabaseHelper(this);
        studentsList = new ArrayList<Student>();

        inputId     = (EditText) findViewById((R.id.inputId));
        inputName   = (EditText) findViewById(R.id.inputName);
        inputIndex  = (EditText) findViewById(R.id.inputIndex);
        inputPoints = (EditText) findViewById(R.id.inputPoints);
        saveInfoButton = (Button) findViewById(R.id.saveInfoButton);
        viewInfoButton = (Button) findViewById(R.id.viewInfoButton);
        updateInfoButton = (Button) findViewById(R.id.updateInfoButton);
        deleteInfoButton = (Button) findViewById(R.id.deleteInfoButton);
    }

    public void add(View v) {
        boolean isFinishedWithoutErrors = db.add(
            inputName.getText().toString(),
            inputIndex.getText().toString(),
            inputPoints.getText().toString()
        );

        if (isFinishedWithoutErrors) {
            Toast.makeText(MainActivity.this, "Data saved.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Not saved.", Toast.LENGTH_SHORT).show();
        }
    }

    public void showSimpleDialog(String title, String dataBody) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(dataBody);
        builder.show();
    }

    public void view(View v) {
        Cursor dataToView = db.view();

        if (dataToView.getCount() != 0) {

            StringBuffer dataBuffer = new StringBuffer();

            while (dataToView.moveToNext()) {
                dataBuffer.append("ID: " + dataToView.getString(0) + "\n");
                dataBuffer.append("NAME: " + dataToView.getString(1) + "\n");
                dataBuffer.append("INDEX: " + dataToView.getString(2) + "\n");
                dataBuffer.append("POINTS: " + dataToView.getString(3) + "\n");
            }

            showSimpleDialog("Student info", dataBuffer.toString());

        } else {
            Toast.makeText(MainActivity.this, "No data to view.", Toast.LENGTH_SHORT).show();
        }
    }

    public void update(View v) {
        boolean isFinishedWithoutErrors = db.update(
                inputId.getText().toString(),
                inputName.getText().toString(),
                inputIndex.getText().toString(),
                inputPoints.getText().toString()
        );

        if(isFinishedWithoutErrors) {
            Toast.makeText(MainActivity.this, "Student info with name: " + inputName.getText().toString() + " is modifyed.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Problem with updating student..", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(View v) {
        int returnedNumberOfDeletedRows = db.delete(inputId.getText().toString());

        if (returnedNumberOfDeletedRows > 0) {
            Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Problem with deleting the data..", Toast.LENGTH_SHORT).show();
        }
    }

}
