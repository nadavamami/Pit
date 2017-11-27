package com.na.dragtest.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nadav Amami on 24/11/2017.
 * Implements the point data source interface as local in memory
 */

public class PointRepository implements IPointRepository {

    private Map<Integer,PitPoint> pointMap;

    public PointRepository() {
        pointMap = new HashMap<>();
    }

    @Override
    public void addPoint(PitPoint point) {
        pointMap.put(point.getId(),point);
    }

    @Override
    public void addPoints(Collection<PitPoint> points) {
        for (PitPoint point : points){
            pointMap.put(point.getId(),point);
        }
    }

    @Override
    public Collection<PitPoint> getPoints() {
        List<PitPoint> points = new ArrayList<>(pointMap.size());
        for (PitPoint point : pointMap.values()){
            points.add(point);
        }
        Collections.sort(points);
        return points;
    }

    @Override
    public void updatePoint(PitPoint point) {
        if (pointMap.containsKey(point.getId())){
            pointMap.get(point.getId()).setX(point.getX());
            pointMap.get(point.getId()).setY(point.getY());
        }
    }

    public Map<Integer, PitPoint> getPointMap() {
        return pointMap;
    }
}
