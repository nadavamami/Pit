package com.na.dragtest.pit;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.na.dragtest.R;
import com.na.dragtest.model.PitPoint;
import com.na.dragtest.model.PointRepository;

import java.util.Collection;
/*
* implements the view interface, responsible for rendering the pit and comunicate with the presenter
* */
public class MainActivity extends AppCompatActivity implements MainView{

    private static final String TAG = MainActivity.class.getSimpleName();
    Pit pitLayout;
    PitPresenter presenter;
    float dx = 0,dy = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pitLayout = findViewById(R.id.pit);
        presenter = new PitPresenterImpl(this,new PointRepository());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(mFabClickListener);

        presenter.setInitialPoints();

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

    /*
    * implements the on touch listener for dragging the pit points
     */
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        float dx,dy;
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            final int X = (int) motionEvent.getRawX();
            final int Y = (int) motionEvent.getRawY();
            switch (motionEvent.getActionMasked()){
                case MotionEvent.ACTION_DOWN:
                    dx = view.getX() - motionEvent.getRawX();
                    dy = view.getY() - motionEvent.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    view.setX(X + dx);
                    view.setY(Y + dy);
                    break;
                case MotionEvent.ACTION_UP:
//                    view.setX(motionEvent.getX() + dx);
//                    view.setY(motionEvent.getY() + dy);
                    PitPoint point = new PitPoint(view.getId(),view.getX(),view.getY());
                    dragComplete(point);
                    break;
                default:
                    return false;
            }
            return true;
        }
    };

    /*
    * called by the presenter when the pit should be rendered again
     */
    @Override
    public void render(Collection<PitPoint> points) {

        pitLayout.removeAllViewsInLayout();
        for (PitPoint point : points){
            PitPointView pointView = new PitPointView(this);
            pointView.setId(point.getId());
            Pit.LayoutParams layoutParams = new Pit.LayoutParams(Pit.LayoutParams.MATCH_PARENT, Pit.LayoutParams.MATCH_PARENT);
//            layoutParams.height = 50;
//            layoutParams.width = 50;
            layoutParams.x = (int) point.getX();
            layoutParams.y = (int) point.getY();
            pointView.setLayoutParams(layoutParams);
            pointView.setOnTouchListener(mTouchListener);
            pitLayout.addView(pointView);
        }

        pitLayout.invalidate();
    }

    /*
    * returns the view dimentions for generating the initial points
     */
    @Override
    public Rect getDimentions() {
        Rect rect = new Rect(0,0,pitLayout.getMeasuredWidth(),pitLayout.getMeasuredHeight());
        return rect;
    }

    /*
    * reports when a view dragg is completed and we need to prepare pit for rendering
     */
    @Override
    public void dragComplete(PitPoint point) {
        presenter.pointChanged(point);
    }

    /*
    * click handler for the add point button
     */
    private View.OnClickListener mFabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.addPoint();
        }
    };

}
