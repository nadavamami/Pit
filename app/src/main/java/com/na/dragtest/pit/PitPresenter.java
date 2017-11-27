package com.na.dragtest.pit;

import com.na.dragtest.model.PitPoint;

/**
 * Created by Nadav Amami on 24/11/2017.
 * presenter interface for describing the interactions with the view
 */

public interface PitPresenter {
    void pointChanged(PitPoint point);
    void setInitialPoints();
    void addPoint();
}
