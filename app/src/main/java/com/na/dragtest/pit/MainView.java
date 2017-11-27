package com.na.dragtest.pit;

import android.graphics.Rect;

import com.na.dragtest.model.PitPoint;

import java.util.Collection;

/**
 * Created by Nadav Amami on 24/11/2017.
 * describes the contract between the view and the presenter
 */

public interface MainView {
    /*
    *called when the view should render
     */
    void render(Collection<PitPoint> points);
    /*
    * gets the view dimensions
     */
    Rect getDimentions();
    /*
    * reports drag is complete
     */
    void dragComplete(PitPoint point);
}
