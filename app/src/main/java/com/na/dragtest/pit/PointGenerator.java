package com.na.dragtest.pit;

import android.graphics.Rect;

import com.na.dragtest.model.PitPoint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created by Nadav Amami on 25/11/2017.
 * helper class for generating PiPoint
 */

public class PointGenerator {

    private static  final int MIN = 30;
    private static final  int MAX = 600;
    private static int nextId = 0;

    /*
    * generates the initial points for the pit setup
     */
    public static Collection<PitPoint> getInitialPoints(Rect bounds){
        Random randomX = new Random();
        Random randomY = new Random();
        int min = 10;
        int maxX = (bounds.width() - 10) > 0 ? bounds.width() - 10 : 700;
        int maxY = (bounds.height() - 10) > 0 ? bounds.height() - 10 : 700;
        List<PitPoint> points = new ArrayList<>();

        for (int i = 0; i < 5 ; i++){
            int x = randomX.nextInt((maxX - MIN) + 1 ) + MIN;
            int y = randomY.nextInt((maxY - MIN) + 1 ) + MIN;
            PitPoint point = new PitPoint(nextId++,x,y);
            points.add(point);
        }
        return points;
    }

    public static int getNextId() {
        return nextId++;
    }
}
