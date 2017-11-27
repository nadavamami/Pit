package com.na.dragtest.model;

import java.util.Collection;

/**
 * Created by Nadav Amami on 24/11/2017.
 * Interface for point data source
 */

public interface IPointRepository {

    void addPoint(PitPoint point);

    void addPoints(Collection<PitPoint> points);

    Collection<PitPoint> getPoints();

    void updatePoint(PitPoint point);
}
