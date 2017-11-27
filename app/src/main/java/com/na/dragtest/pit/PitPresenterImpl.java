package com.na.dragtest.pit;

import android.graphics.Rect;

import com.na.dragtest.model.IPointRepository;
import com.na.dragtest.model.PitPoint;

import java.util.Collection;
import java.util.List;

/**
 * Created by Nadav Amami on 24/11/2017.
 * implements the presenter interface receives both the repository and the view interfaces as dependencies in the constructor
 * could be done using a dependency injection framework
 */

public class PitPresenterImpl implements PitPresenter {

    private MainView mainView;
    private IPointRepository pointRepository;

    public PitPresenterImpl(MainView mainView, IPointRepository pointRepository) {
        this.mainView = mainView;
        this.pointRepository = pointRepository;
    }

    /*
        when a pit point is dragged we need to render the pit,
        this is the callback that the view calls when that happens
    */
    @Override
    public void pointChanged(PitPoint point) {
        pointRepository.updatePoint(point);
        List<PitPoint> points = (List<PitPoint>) pointRepository.getPoints();
//        Collections.sort(points, new Comparator<PitPoint>() {
//            @Override
//            public int compare(PitPoint point, PitPoint t1) {
//                return (int) (point .getX() - t1.getX());
//            }
//        });
        if (mainView != null){
            mainView.render(points);
        }
    }

    /*
    * called on the initial setup of pit in order to generate the initial points
    * */
    @Override
    public void setInitialPoints() {
        Collection<PitPoint> points = PointGenerator.getInitialPoints(mainView.getDimentions());
        if (mainView != null){
            pointRepository.addPoints(points);
            mainView.render(pointRepository.getPoints());
        }
    }

    /*
    * called when a user clicks the add point FAB
    * the point is added to the data source and the pit is rendered again
    * */
    @Override
    public void addPoint() {
        if (mainView == null){
            return;
        }
        Rect rect = mainView.getDimentions();
        PitPoint point = new PitPoint(PointGenerator.getNextId(),rect.centerX(),rect.centerY() );
        pointRepository.addPoint(point);
        mainView.render(pointRepository.getPoints());
    }

}
